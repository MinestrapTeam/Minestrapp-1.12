package minestrapp.block.tileentity;

import java.util.Map;
import java.util.Set;

import minestrapp.MBlocks;
import minestrapp.MItems;
import minestrapp.block.BlockActivator;
import minestrapp.block.BlockTanningRack;
import minestrapp.crafting.TannerRecipes;
import minestrapp.crafting.TannerRecipes.TannerRecipe;
import minestrapp.item.tools.MDagger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class TileEntityTanningRack extends TileEntity implements ITickable{

	public NonNullList<ItemStack> hide = NonNullList.<ItemStack>withSize(1, ItemStack.EMPTY);
	private int angle;
	
	public boolean isTanning = false;
	public boolean canProgress = true;
	public ItemStack lastToolUsed = ItemStack.EMPTY;
	private int ticks = 0;
	private int secondsToTan = 5;
	
	@Override
	public void update()
	{
			if(this.isEmpty())
				this.isTanning = false;
			if(this.isTanning)
			{
				TannerRecipe recipe = this.getRecipe(lastToolUsed);
				this.setCanProgress(true);
				if(recipe == null)
					this.isTanning = false;
				else
				{
					
					if(recipe.sun && (this.getLight() < 10 || !this.world.canSeeSky(this.getPos()) )) {
						this.setCanProgress(false);
					}
						
					if(this.canProgress)
					{
						this.ticks++;
					
						if(this.ticks / 20 >= recipe.time)
						{
							this.hide.set(0, recipe.output);
							this.ticks = 0;
							this.isTanning = false;
							this.markDirty();
						}
					}
				}
			}	
	}
	
	public boolean isEmpty()
	{
		return this.hide.get(0).copy().isEmpty() || this.hide.get(0).copy().getItem() == MItems.nothing;
	}
	
	//Stolen from the vanilla daylight sensor
	private int getLight() {
		IBlockState iblockstate = this.world.getBlockState(pos);
        int i = this.world.getLightFor(EnumSkyBlock.SKY, pos) - this.world.getSkylightSubtracted();
        float f = this.world.getCelestialAngleRadians(1.0F);

        if (i > 0)
        {
            float f1 = f < (float)Math.PI ? 0.0F : ((float)Math.PI * 2F);
            f = f + (f1 - f) * 0.2F;
            i = Math.round((float)i * MathHelper.cos(f));
        }

        i = MathHelper.clamp(i, 0, 15);
        return i;
	}
	
	public void setCanProgress(boolean progress) {
		if(this.canProgress != progress) {
			this.canProgress = progress;
			this.markDirty();
		}
	}
	
	public boolean tryToAddItem(ItemStack stack, int angle)
	{
		ItemStack temp = stack.copy();
		temp.setCount(1);
		this.hide.set(0, temp);
		this.angle = angle;
		this.ticks = 0;
		this.markDirty();
		return true;
	}
	
	public void takeItem()
	{
		if(!this.getWorld().isRemote)
		{
			double xOffset = 0.5D;
			double zOffset = 0.5D;
			
			if(this.angle == 0)
				zOffset = 1D;
			else if(this.angle == 90)
				xOffset = 0D;
			else if(this.angle == 180)
				zOffset = 0D;
			else
				xOffset = 1D;
			this.world.spawnEntity(new EntityItem(this.world, this.getPos().getX() + xOffset, this.getPos().getY() + 0.25D, this.getPos().getZ() + zOffset, this.hide.get(0).copy()));
			this.isTanning = false;
		}
		this.hide.set(0, new ItemStack(MItems.nothing));
		this.markDirty();
	}
	
	public boolean isHoldingItem()
	{
		return !this.isEmpty();
	}
	
	public TannerRecipe getRecipe(ItemStack tool)
	{
        for(Map.Entry<ItemStack, TannerRecipe> entry: TannerRecipes.instance.recipes.entrySet())
        {
            TannerRecipe recipe = entry.getValue();
            if(ItemStack.areItemStacksEqual(this.hide.get(0).copy(), recipe.input) && tool != null && ItemStack.areItemStacksEqual(recipe.tool, new ItemStack(tool.getItem(), 1, tool.getMetadata())))
            {
                return recipe;
            }
        }
        return null;
    }
	
	public int getAngle()
	{
		return this.angle;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.angle = compound.getInteger("angle");
		this.isTanning = compound.getBoolean("tanning");
		this.canProgress = compound.getBoolean("progress");
		this.lastToolUsed = new ItemStack(Item.getItemById(compound.getInteger("lastToolID")), 1, compound.getInteger("lastToolMeta"));
		ItemStackHelper.loadAllItems(compound, this.hide);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setInteger("angle", this.angle);
		compound.setBoolean("tanning", this.isTanning);
		compound.setBoolean("progress", this.canProgress);
		compound.setInteger("lastToolID", Item.getIdFromItem(this.lastToolUsed.getItem()));
		compound.setInteger("lastToolMeta", this.lastToolUsed.getMetadata());
		ItemStackHelper.saveAllItems(compound, this.hide);
		return compound;
	}
	
	@Override
	public NBTTagCompound getUpdateTag()
	{
		return writeToNBT(new NBTTagCompound());
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket()
	{
	    return new SPacketUpdateTileEntity(getPos(), 0, getUpdateTag());
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) 
	{
	    this.readFromNBT(packet.getNbtCompound());
	}
	
	public boolean processActivatorInteract(BlockPos pos, EnumFacing facing)
	{
		TileEntityActivator tea = (TileEntityActivator)this.world.getTileEntity(pos);
		ItemStack stack = tea.getStackInSlot(0);
		int angle = ((BlockTanningRack) world.getBlockState(this.pos).getBlock()).getMetaFromState(world.getBlockState(this.pos)) * 90;
		
		if(!this.isHoldingItem() && this.tryToAddItem(stack, angle))
		{
				return true;
		}
		else
		{
			ItemStack tool = stack.copy();
			
			if(tool.getItem() instanceof MDagger)
			{
				if(((MDagger)tool.getItem()).getToolMaterialHarvestLevel() == 0)
				{
					tool = new ItemStack(MItems.wooden_dagger);
				}
				else if(((MDagger)tool.getItem()).getToolMaterialHarvestLevel() == 1)
				{
					tool = new ItemStack(MItems.stone_dagger);
				}
				else if(((MDagger)tool.getItem()).getToolMaterialHarvestLevel() == 2)
				{
					tool = new ItemStack(MItems.iron_dagger);
				}
				else if(((MDagger)tool.getItem()).getToolMaterialHarvestLevel() == 3)
				{
					tool = new ItemStack(MItems.diamond_dagger);
				}
				else if(((MDagger)tool.getItem()).getToolMaterialHarvestLevel() == 4)
				{
					tool = new ItemStack(MItems.titanium_dagger);
				}
			}
			
			if(this.getRecipe(tool) == null || this.getRecipe(tool).tool == null || this.isTanning)
			{
				this.takeItem();
				return false;
			}
			else
			{
				boolean isTool = false;
				
				
				TannerRecipe recipe = this.getRecipe(tool);
	
				if(ItemStack.areItemsEqual(tool, recipe.tool))
					isTool = true;
				else
				{
					Set<String> recipeToolClasses  = recipe.tool.getItem().getToolClasses(recipe.tool);
					Set<String> heldToolClasses  = tool.getItem().getToolClasses(tool);
					
					for(String toolClass : recipeToolClasses) {
						if(heldToolClasses.contains(toolClass)){
							isTool = true;
							break;
						}
					}
				}

				if(isTool && this.isTanning == false)
				{
					boolean consume = false;
					this.lastToolUsed = tool.copy();
					
					if(recipe.time == 0)
						this.tryToAddItem(recipe.output, angle);
					else
						this.isTanning = true;
					
					if(recipe.consumeItem)
					{
						consume = true;
						
						if((tool.getItem() == MItems.tannic || tool.getItem() == Item.getItemFromBlock(MBlocks.glow_paste)) && !world.isRemote)
						{
							double xOffset = 0.5D;
							double zOffset = 0.5D;
							
							if(this.angle == 0)
								zOffset = 1D;
							else if(this.angle == 90)
								xOffset = 0D;
							else if(this.angle == 180)
								zOffset = 0D;
							else
								xOffset = 1D;
							
							this.world.spawnEntity(new EntityItem(this.world, this.getPos().getX() + xOffset, this.getPos().getY() + 0.25D, this.getPos().getZ() + zOffset, new ItemStack(Items.GLASS_BOTTLE)));
						}
					}
					else if(stack.getItem() instanceof MDagger)
						stack.attemptDamageItem(1, world.rand, null);
					
					return consume;
				}
				else
				{
					this.takeItem();
					return false;
				}
			}
		}
	}
	
	public void sendUpdates()
	{
		world.markBlockRangeForRenderUpdate(pos, pos);
		world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
		world.scheduleBlockUpdate(pos, this.getBlockType(), 0, 0);
		markDirty();
	}
}
