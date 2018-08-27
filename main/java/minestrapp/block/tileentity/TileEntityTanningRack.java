package minestrapp.block.tileentity;

import java.util.Map;

import minestrapp.crafting.TannerRecipes;
import minestrapp.crafting.TannerRecipes.TannerRecipe;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumSkyBlock;

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
			if(this.hide.get(0).isEmpty())
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
			this.world.spawnEntity(new EntityItem(this.world, this.getPos().getX() + xOffset, this.getPos().getY() + 0.25D, this.getPos().getZ() + zOffset, this.hide.get(0)));
			this.isTanning = false;
		}
		this.hide.set(0, ItemStack.EMPTY);
	}
	
	public boolean isHoldingItem()
	{
		return !this.hide.get(0).isEmpty();
	}
	
	public TannerRecipe getRecipe(ItemStack tool)
	{
        for(Map.Entry<ItemStack, TannerRecipe> entry: TannerRecipes.instance.recipes.entrySet())
        {
            TannerRecipe recipe = entry.getValue();
            if(ItemStack.areItemStacksEqual(this.hide.get(0), recipe.input) && tool != null && ItemStack.areItemStacksEqual(recipe.tool, new ItemStack(tool.getItem(), 1, tool.getMetadata())))
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
}
