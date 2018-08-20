package minestrapp.block.tileentity;

import minestrapp.block.BlockPlate;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;

public class TileEntityPlate extends TileEntity{
	public NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(1, ItemStack.EMPTY);
	private int height;
	public int angle;
	
	public boolean tryToAddItem(ItemStack item) {
		if(this.inventory.get(0).isEmpty()) {
			ItemStack temp = item.copy();
			temp.setCount(1);
			this.inventory.set(0,temp);
			this.markDirty();
			return true;
		}
		return false;
	}
	
	public TileEntityPlate setHeight(int height)
	{
		this.height = height;
		return this;
	}
	
	public int getHeight()
	{
		return this.height;
	}
	
	public void takeItem() {
		if(!this.getWorld().isRemote) {
			this.world.spawnEntity(new EntityItem(this.world, this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), this.inventory.get(0)));
		}
		this.inventory.set(0, ItemStack.EMPTY);
	}
	
	public void eatOffPlate(EntityPlayer player) {
		ItemStack stack = this.inventory.get(0);
		if(!stack.isEmpty() && stack.getItem() instanceof ItemFood) {
			ItemFood food = (ItemFood)stack.getItem();
			player.getFoodStats().addStats(food, stack);
			this.inventory.set(0, ItemStack.EMPTY);
			this.markDirty();
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.angle = compound.getInteger("angle");
		this.height = compound.getInteger("height");
		ItemStackHelper.loadAllItems(compound, this.inventory);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("angle", this.angle);
		compound.setInteger("height", this.height);
		ItemStackHelper.saveAllItems(compound, this.inventory);
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
