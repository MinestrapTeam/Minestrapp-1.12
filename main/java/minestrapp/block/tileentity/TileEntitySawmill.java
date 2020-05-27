package minestrapp.block.tileentity;

import java.util.List;

import javax.annotation.Nullable;

import minestrapp.MBlocks;
import minestrapp.block.BlockSawmill;
import minestrapp.block.BlockPipe;
import minestrapp.container.ContainerSawmill;
import minestrapp.item.ItemSawblade;
import minestrapp.container.ContainerCrate;
import minestrapp.container.ContainerPipe;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.IHopper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntitySawmill extends TileEntityLockableLoot
{
	private int angle;
	
	public NonNullList<ItemStack> sawmillContents = NonNullList.<ItemStack>withSize(1, ItemStack.EMPTY);

	public ItemStackHandler inventory = new ItemStackHandler(1);
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("angle", this.angle);

        if (!this.checkLootAndWrite(compound))
        {
            ItemStackHelper.saveAllItems(compound, this.sawmillContents);
        }

        if (this.hasCustomName())
        {
            compound.setString("CustomName", this.customName);
        }

        return compound;
    }
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.angle = compound.getInteger("angle");
        this.sawmillContents = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);

        if (!this.checkLootAndRead(compound))
        {
            ItemStackHelper.loadAllItems(compound, this.sawmillContents);
        }

        if (compound.hasKey("CustomName", 8))
        {
            this.customName = compound.getString("CustomName");
        }
	}

	@Override
	public String getName()
	{
		return this.hasCustomName() ? this.customName : "container.sawmill";
	}
	
	public int getAngle()
	{
		return this.angle;
	}
	
	public void setAngle(int angle)
	{
		this.angle = angle;
		this.markDirty();
	}

	@Override
	public int getSizeInventory()
	{
		return 1;
	}
	
	@Override
	public boolean isEmpty()
	{
		for (ItemStack itemstack : this.sawmillContents)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }

        return true;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
	{
		this.fillWithLoot(playerIn);
        return new ContainerSawmill(playerInventory, this, playerIn);
	}

	@Override
	public String getGuiID()
	{
		return "minestrapp:sawmill";
	}

	@Override
	protected NonNullList<ItemStack> getItems()
	{
		return this.sawmillContents;
	}
	
	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState)
	{
		return oldState.getBlock() == MBlocks.sawmill && newState.getBlock() == MBlocks.sawmill ? false : true;
	}
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack)
	{
		if(index == 0)
			return stack.getItem() instanceof ItemSawblade;
		else
			return super.isItemValidForSlot(index, stack);
	}
	
	public void setInventorySlotContents(int index, @Nullable ItemStack stack)
    {
		super.setInventorySlotContents(index, stack);
		
        if(index == 0)
        {
        	IBlockState state = world.getBlockState(pos);
        	Block block = state.getBlock();
        	if(block instanceof BlockSawmill)
        	{
        		EnumFacing facing = state.getValue(BlockSawmill.FACING);
	        	((BlockSawmill)block).tryMillBlock(world, pos.offset(facing), facing);
	        	world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 4);
        	}
        }
    }
	
	public boolean isRedstonePowered()
	{
		return this.world.isBlockIndirectlyGettingPowered(pos) > 0;
	}
}