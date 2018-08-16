package minestrapp.block.tileentity;

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
	
	private int ticks = 0;
	private int secondsToTan = 5;
	
	@Override
	public void update() {
		if(!this.hide.get(0).isEmpty()) {
			TannerRecipe recipe = this.getRecipe();
			if(recipe != null) {
				this.ticks++;
				if(this.ticks / 20 >= recipe.time) {
					this.hide.set(0, recipe.output);
					this.ticks = 0;
				}
			}
		}
	}
	
	public boolean tryToAddItem(ItemStack stack, int angle){
		if(this.hide.get(0).isEmpty()) {
			ItemStack temp = stack.copy();
			temp.setCount(1);
			this.hide.set(0, temp);
			this.angle = angle;
			this.markDirty();
			return true;
		}
		return false;
	}
	
	public void takeItem() {
		if(!this.getWorld().isRemote) {
			this.world.spawnEntity(new EntityItem(this.world, this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), this.hide.get(0)));
		}
		this.hide.set(0, ItemStack.EMPTY);
	}
	
	private TannerRecipe getRecipe() {
		return TannerRecipes.instance.recipes.get(this.hide.get(0).getItem());
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.angle = compound.getInteger("angle");
		ItemStackHelper.loadAllItems(compound, this.hide);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("angle", this.angle);
		ItemStackHelper.saveAllItems(compound, this.hide);
		return compound;
	}
	
	@Override
	public NBTTagCompound getUpdateTag(){
		return writeToNBT(new NBTTagCompound());
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket(){
	    return new SPacketUpdateTileEntity(getPos(), 0, getUpdateTag());
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) 
	{
	    this.readFromNBT(packet.getNbtCompound());
	}


}
