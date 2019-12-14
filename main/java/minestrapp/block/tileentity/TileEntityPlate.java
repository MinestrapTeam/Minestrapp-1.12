package minestrapp.block.tileentity;

import com.jcraft.jorbis.Block;

import minestrapp.MItems;
import minestrapp.block.BlockPlate;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;

public class TileEntityPlate extends TileEntity{
	public NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(1, ItemStack.EMPTY);
	private int height;
	public int angle;
	
	public boolean isEmpty()
	{
		return this.inventory.get(0).isEmpty() || this.inventory.get(0).getItem() == MItems.nothing;
	}
	
	public boolean tryToAddItem(ItemStack item)
	{
		if(this.isEmpty())
		{
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
		if(!this.getWorld().isRemote && !this.isEmpty()) {
			this.world.spawnEntity(new EntityItem(this.world, this.getPos().getX() + 0.5D, this.getPos().getY() + 0.5D, this.getPos().getZ() + 0.5D, this.inventory.get(0)));
		}
		this.inventory.set(0, ItemStack.EMPTY);
		
		this.markDirty();
	}
	
	public void eatOffPlate(EntityPlayer player) {
		ItemStack stack = this.inventory.get(0);
		if(!this.isEmpty() && stack.getItem() instanceof ItemFood) {
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
	
	public boolean processActivatorInteract(BlockPos pos, EnumFacing facing)
	{
		TileEntityActivator tea = (TileEntityActivator)this.world.getTileEntity(pos);
		ItemStack stack = tea.getStackInSlot(0);
		Item item = stack.getItem();
		
		if(facing == EnumFacing.NORTH)
    		this.angle = 180;
		else if(facing == EnumFacing.SOUTH)
    		this.angle = 0;
		else if(facing == EnumFacing.EAST)
    		this.angle = 270;
		else if(facing == EnumFacing.WEST)
    		this.angle = 90;
		
		if(this.isEmpty() && item instanceof ItemFood)
		{
			ItemStack temp = stack.copy();
			temp.setCount(1);
			this.inventory.set(0, temp);
			this.markDirty();
			this.world.scheduleBlockUpdate(this.pos, this.world.getBlockState(this.pos).getBlock(), 0, 0);
			return true;
		}
		else
		{
			if(!this.isEmpty())
				this.world.spawnEntity(new EntityItem(this.world, this.getPos().getX() + 0.5D, this.getPos().getY() + 0.5D, this.getPos().getZ() + 0.5D, this.inventory.get(0).copy()));
			this.inventory.set(0, new ItemStack(MItems.nothing));
			this.markDirty();
			return false;
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
