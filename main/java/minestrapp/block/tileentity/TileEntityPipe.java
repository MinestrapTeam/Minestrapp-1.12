package minestrapp.block.tileentity;

import java.util.List;

import javax.annotation.Nullable;

import minestrapp.block.BlockPipe;
import minestrapp.container.ContainerPipe;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
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

public class TileEntityPipe extends TileEntityLockableLoot implements IHopper, ITickable
{
	private NonNullList<ItemStack> pipeContents = NonNullList.<ItemStack>withSize(1, ItemStack.EMPTY);
	private int transferCooldown = -1;
    private long tickedGameTime;

	private ItemStackHandler inventory = new ItemStackHandler(1);

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);

        if (!this.checkLootAndWrite(compound))
        {
            ItemStackHelper.saveAllItems(compound, this.pipeContents);
        }
        
        compound.setInteger("TransferCooldown", this.transferCooldown);

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
        this.pipeContents = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);

        if (!this.checkLootAndRead(compound))
        {
            ItemStackHelper.loadAllItems(compound, this.pipeContents);
        }

        if (compound.hasKey("CustomName", 8))
        {
            this.customName = compound.getString("CustomName");
        }
        
        this.transferCooldown = compound.getInteger("TransferCooldown");
	}

	@Override
	public String getName()
	{
		return this.hasCustomName() ? this.customName : "Pipe";
	}

	@Override
	public int getSizeInventory()
	{
		return 1;
	}
	
	public ItemStack decrStackSize(int index, int count)
    {
        this.fillWithLoot((EntityPlayer)null);
        ItemStack itemstack = ItemStackHelper.getAndSplit(this.getItems(), index, count);
        return itemstack;
    }
	
	public void setInventorySlotContents(int index, ItemStack stack)
    {
        this.fillWithLoot((EntityPlayer)null);
        this.getItems().set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit())
        {
            stack.setCount(this.getInventoryStackLimit());
        }
    }

	@Override
	public boolean isEmpty()
	{
		for (ItemStack itemstack : this.pipeContents)
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
        return new ContainerPipe(playerInventory, this, playerIn);
	}

	@Override
	public String getGuiID()
	{
		return "minestrapp:pipe";
	}

	@Override
	protected NonNullList<ItemStack> getItems()
	{
		return this.pipeContents;
	}
	
	public void update()
    {
        if (this.world != null && !this.world.isRemote)
        {
            --this.transferCooldown;
            this.tickedGameTime = this.world.getTotalWorldTime();

            if (!this.isOnTransferCooldown())
            {
                this.setTransferCooldown(0);
                this.updatePipe();
            }
        }
    }
	
	protected boolean updatePipe()
    {
        if (this.world != null && !this.world.isRemote)
        {
            if (!this.isOnTransferCooldown())
            {
                boolean flag = false;

                if (!this.isEmpty())
                {
                    flag = this.transferItemsOut();
                }

                if (flag)
                {
                    this.setTransferCooldown(8);
                    this.markDirty();
                    return true;
                }
            }

            return false;
        }
        else
        {
            return false;
        }
    }
	
	private boolean transferItemsOut()
    {
        IInventory iinventory = this.getInventoryForPipeTransfer();

        if (iinventory == null)
        {
            return false;
        }
        else
        {
            EnumFacing enumfacing = BlockPipe.getFacing(this.getBlockMetadata());
            if(enumfacing == EnumFacing.DOWN || enumfacing == EnumFacing.UP || enumfacing == EnumFacing.WEST || enumfacing == EnumFacing.EAST)
            	enumfacing = enumfacing.getOpposite();
            
            if (this.isInventoryFull(iinventory, enumfacing))
            {
                return false;
            }
            else
            {
                for (int i = 0; i < this.getSizeInventory(); ++i)
                {
                    if (!this.getStackInSlot(i).isEmpty())
                    {
                        ItemStack itemstack = this.getStackInSlot(i).copy();
                        ItemStack itemstack1 = putStackInInventoryAllSlots(this, iinventory, this.decrStackSize(i, 1), enumfacing);

                        if (itemstack1.isEmpty())
                        {
                            iinventory.markDirty();
                            return true;
                        }

                        this.setInventorySlotContents(i, itemstack);
                    }
                }

                return false;
            }
        }
    }
	
	private boolean isInventoryFull(IInventory inventoryIn, EnumFacing side)
    {
        if (inventoryIn instanceof ISidedInventory)
        {
            ISidedInventory isidedinventory = (ISidedInventory)inventoryIn;
            int[] aint = isidedinventory.getSlotsForFace(side);

            for (int k : aint)
            {
                ItemStack itemstack1 = isidedinventory.getStackInSlot(k);

                if (itemstack1.isEmpty() || itemstack1.getCount() != itemstack1.getMaxStackSize())
                {
                    return false;
                }
            }
        }
        else
        {
            int i = inventoryIn.getSizeInventory();

            for (int j = 0; j < i; ++j)
            {
                ItemStack itemstack = inventoryIn.getStackInSlot(j);

                if (itemstack.isEmpty() || itemstack.getCount() != itemstack.getMaxStackSize())
                {
                    return false;
                }
            }
        }

        return true;
    }
	
	public static ItemStack putStackInInventoryAllSlots(IInventory source, IInventory destination, ItemStack stack, @Nullable EnumFacing direction)
    {
        if (destination instanceof ISidedInventory && direction != null)
        {
            ISidedInventory isidedinventory = (ISidedInventory)destination;
            int[] aint = isidedinventory.getSlotsForFace(direction.getOpposite());

            for (int k = 0; k < aint.length && !stack.isEmpty(); ++k)
            {
                stack = insertStack(source, destination, stack, aint[k], direction.getOpposite());
            }
        }
        else
        {
            int i = destination.getSizeInventory();

            for (int j = 0; j < i && !stack.isEmpty(); ++j)
            {
                stack = insertStack(source, destination, stack, j, direction.getOpposite());
            }
        }

        return stack;
    }
	
	private static boolean canInsertItemInSlot(IInventory inventoryIn, ItemStack stack, int index, EnumFacing side)
    {
        if (!inventoryIn.isItemValidForSlot(index, stack))
        {
            return false;
        }
        else
        {
            return !(inventoryIn instanceof ISidedInventory) || ((ISidedInventory)inventoryIn).canInsertItem(index, stack, side);
        }
    }
	
	public long getLastUpdateTime()
	{
		return tickedGameTime;
	}
	
	private static ItemStack insertStack(IInventory source, IInventory destination, ItemStack stack, int index, EnumFacing direction)
    {
        ItemStack itemstack = destination.getStackInSlot(index);

        if (canInsertItemInSlot(destination, stack, index, direction))
        {
            boolean flag = false;
            boolean flag1 = destination.isEmpty();

            if (itemstack.isEmpty())
            {
                destination.setInventorySlotContents(index, stack);
                stack = ItemStack.EMPTY;
                flag = true;
            }
            else if (canCombine(itemstack, stack))
            {
                int i = stack.getMaxStackSize() - itemstack.getCount();
                int j = Math.min(stack.getCount(), i);
                stack.shrink(j);
                itemstack.grow(j);
                flag = j > 0;
            }

            if (flag)
            {
            	if (flag1 && destination instanceof TileEntityHopper)
                {
                    TileEntityHopper tileentityhopper1 = (TileEntityHopper)destination;

                    if (!tileentityhopper1.mayTransfer())
                    {
                        int k = 0;

                        if (source != null && source instanceof TileEntityHopper)
                        {
                            TileEntityHopper tileentityhopper = (TileEntityHopper)source;

                            if (tileentityhopper1.getLastUpdateTime() >= tileentityhopper.getLastUpdateTime())
                            {
                                k = 1;
                            }
                        }

                        tileentityhopper1.setTransferCooldown(8 - k);
                    }
                }
            	else if (flag1 && destination instanceof TileEntityPipe)
            	{
                    TileEntityPipe tileentityhopper1 = (TileEntityPipe)destination;

                    if (!tileentityhopper1.mayTransfer())
                    {
                        int k = 0;

                        if (source != null && source instanceof TileEntityPipe)
                        {
                            TileEntityPipe tileentityhopper = (TileEntityPipe)source;

                            if (tileentityhopper1.getLastUpdateTime() >= tileentityhopper.getLastUpdateTime())
                            {
                                k = 1;
                            }
                        }

                        tileentityhopper1.setTransferCooldown(8 - k);
                    }
                }
            	else if (flag1 && destination instanceof TileEntitySorter)
            	{
                    TileEntitySorter tileentityhopper1 = (TileEntitySorter)destination;

                    if (!tileentityhopper1.mayTransfer())
                    {
                        int k = 0;

                        if (source != null && source instanceof TileEntitySorter)
                        {
                            TileEntitySorter tileentityhopper = (TileEntitySorter)source;

                            if (tileentityhopper1.getLastUpdateTime() >= tileentityhopper.getLastUpdateTime())
                            {
                                k = 1;
                            }
                        }

                        tileentityhopper1.setTransferCooldown(8 - k);
                    }
                }
            	
                destination.markDirty();
            }
        }

        return stack;
    }
	
	private IInventory getInventoryForPipeTransfer()
    {
        EnumFacing enumfacing = BlockPipe.getFacing(this.getBlockMetadata());
        if(enumfacing == EnumFacing.DOWN || enumfacing == EnumFacing.UP || enumfacing == EnumFacing.WEST || enumfacing == EnumFacing.EAST)
        	enumfacing = enumfacing.getOpposite();
        return getInventoryAtPosition(this.getWorld(), this.getXPos() + (double)enumfacing.getFrontOffsetX(), this.getYPos() + (double)enumfacing.getFrontOffsetY(), this.getZPos() + (double)enumfacing.getFrontOffsetZ());
    }
	
	public static IInventory getInventoryAtPosition(World worldIn, double x, double y, double z)
    {
        IInventory iinventory = null;
        int i = MathHelper.floor(x);
        int j = MathHelper.floor(y);
        int k = MathHelper.floor(z);
        BlockPos blockpos = new BlockPos(i, j, k);
        net.minecraft.block.state.IBlockState state = worldIn.getBlockState(blockpos);
        Block block = state.getBlock();

        if (block.hasTileEntity(state))
        {
            TileEntity tileentity = worldIn.getTileEntity(blockpos);

            if (tileentity instanceof IInventory)
            {
                iinventory = (IInventory)tileentity;

                if (iinventory instanceof TileEntityChest && block instanceof BlockChest)
                {
                    iinventory = ((BlockChest)block).getContainer(worldIn, blockpos, true);
                }
            }
        }

        if (iinventory == null)
        {
            List<Entity> list = worldIn.getEntitiesInAABBexcluding((Entity)null, new AxisAlignedBB(x - 0.5D, y - 0.5D, z - 0.5D, x + 0.5D, y + 0.5D, z + 0.5D), EntitySelectors.HAS_INVENTORY);

            if (!list.isEmpty())
            {
                iinventory = (IInventory)list.get(worldIn.rand.nextInt(list.size()));
            }
        }

        return iinventory;
    }
	
	private static boolean canCombine(ItemStack stack1, ItemStack stack2)
    {
        if (stack1.getItem() != stack2.getItem())
        {
            return false;
        }
        else if (stack1.getMetadata() != stack2.getMetadata())
        {
            return false;
        }
        else if (stack1.getCount() > stack1.getMaxStackSize())
        {
            return false;
        }
        else
        {
            return ItemStack.areItemStackTagsEqual(stack1, stack2);
        }
    }
	
	public double getXPos()
    {
        return (double)this.pos.getX() + 0.5D;
    }
	
	public double getYPos()
    {
        return (double)this.pos.getY() + 0.5D;
    }
	
	public double getZPos()
    {
        return (double)this.pos.getZ() + 0.5D;
    }
	
	public void setTransferCooldown(int ticks)
    {
        this.transferCooldown = ticks;
    }
	
	private boolean isOnTransferCooldown()
    {
        return this.transferCooldown > 0;
    }

    public boolean mayTransfer()
    {
        return this.transferCooldown > 8;
    }
}