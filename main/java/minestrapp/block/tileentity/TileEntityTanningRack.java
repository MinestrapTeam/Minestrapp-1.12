package minestrapp.block.tileentity;

import java.util.Map;

import minestrapp.crafting.TannerRecipes;
import minestrapp.crafting.TannerRecipes.TannerRecipe;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;

public class TileEntityTanningRack extends TileEntity implements ITickable{

	public NonNullList<ItemStack> hide = NonNullList.<ItemStack>withSize(1, ItemStack.EMPTY);
	public int angle;
	
	public boolean isTanning = true;
	private int ticks = 0;
	private int secondsToTan = 5;
	
	@Override
	public void update()
	{
		if(this.hide.get(0).isEmpty())
			this.isTanning = false;
		if(this.isTanning)
		{
			TannerRecipe recipe = this.getRecipe();
			
			if(recipe == null)
				this.isTanning = false;
			else
			{
				boolean progress = true;
				
				if(recipe.sun && (!this.world.isDaytime() || !this.world.canSeeSky(this.pos)))
					progress = false;
				
				if(progress)
				{
					this.ticks++;
				
					if(this.ticks / 20 >= recipe.time)
					{
						this.hide.set(0, recipe.output);
						this.ticks = 0;
						this.isTanning = false;
					}
				}
			}
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
			this.world.spawnEntity(new EntityItem(this.world, this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), this.hide.get(0)));
			this.isTanning = false;
		}
		this.hide.set(0, ItemStack.EMPTY);
	}
	
	public boolean isHoldingItem()
	{
		return !this.hide.get(0).isEmpty();
	}
	
	public TannerRecipe getRecipe()
	{
        for(Map.Entry<ItemStack, TannerRecipe> entry: TannerRecipes.instance.recipes.entrySet())
        {
            TannerRecipe recipe = entry.getValue();
            if(ItemStack.areItemStacksEqual(this.hide.get(0), recipe.input))
            {
                return recipe;
            }
        }
        return null;
    }
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.angle = compound.getInteger("angle");
		this.isTanning = compound.getBoolean("tanning");
		ItemStackHelper.loadAllItems(compound, this.hide);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setInteger("angle", this.angle);
		compound.setBoolean("tanning", this.isTanning);
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
