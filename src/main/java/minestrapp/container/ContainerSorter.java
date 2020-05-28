package minestrapp.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSorter extends Container
{
	private final IInventory sorterInventory;
	private final int numRows;

	public ContainerSorter(IInventory playerInventory, IInventory sorterInventory, EntityPlayer player)
	{
		this.sorterInventory = sorterInventory;
		this.numRows = 2;
		sorterInventory.openInventory(player);
		int i = (this.numRows - 4) * 18;
		
		this.addSlotToContainer(new Slot(sorterInventory, 0, 8 + 4 * 18, 17));
		
		this.addSlotToContainer(new Slot(sorterInventory, 1, 8 + 4 * 18, 52)
        {
            public boolean isItemValid(ItemStack stack)
            {
                return true;
            }

            public int getSlotStackLimit()
            {
                return 1;
            }
        });
		
		for (int l = 0; l < 3; ++l)
        {
            for (int m = 0; m < 9; ++m)
            {
                this.addSlotToContainer(new Slot(playerInventory, m + l * 9 + 9, 8 + m * 18, 123 + l * 18 + i));
            }
        }
	
		for (int n = 0; n < 9; ++n)
        {
            this.addSlotToContainer(new Slot(playerInventory, n, 8 + n * 18, 181 + i));
        }
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return this.sorterInventory.isUsableByPlayer(player);
	}
	
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < this.sorterInventory.getSizeInventory())
            {
                if (!this.mergeItemStack(itemstack1, this.sorterInventory.getSizeInventory(), this.inventorySlots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, this.sorterInventory.getSizeInventory(), false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }
	
	public void onContainerClosed(EntityPlayer playerIn)
    {
        super.onContainerClosed(playerIn);
        this.sorterInventory.closeInventory(playerIn);
    }
	
	public IInventory getSorterInventory()
    {
        return this.sorterInventory;
    }
}
