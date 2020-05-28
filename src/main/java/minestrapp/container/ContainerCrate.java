package minestrapp.container;

import minestrapp.block.tileentity.TileEntityCrate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerCrate extends Container
{
	private final IInventory crateInventory;
	private final int numRows;

	public ContainerCrate(IInventory playerInventory, IInventory crateInventory, EntityPlayer player)
	{
		this.crateInventory = crateInventory;
		this.numRows = crateInventory.getSizeInventory() / 9;
		crateInventory.openInventory(player);
		int i = (this.numRows - 4) * 18;
		
		for (int j = 0; j < this.numRows; ++j)
        {
            for (int k = 0; k < 9; ++k)
            {
                this.addSlotToContainer(new Slot(crateInventory, k + j * 9, 8 + k * 18, 18 + j * 18));
            }
        }
		
		for (int l = 0; l < 3; ++l)
        {
            for (int m = 0; m < 9; ++m)
            {
                this.addSlotToContainer(new Slot(playerInventory, m + l * 9 + 9, 8 + m * 18, 103 + l * 18 + i));
            }
        }
	
		for (int n = 0; n < 9; ++n)
        {
            this.addSlotToContainer(new Slot(playerInventory, n, 8 + n * 18, 161 + i));
        }
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return this.crateInventory.isUsableByPlayer(player);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index)
	{
		ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < this.numRows * 9)
            {
                if (!this.mergeItemStack(itemstack1, this.numRows * 9, this.inventorySlots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, this.numRows * 9, false))
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
        this.crateInventory.closeInventory(playerIn);
    }
	
	public IInventory getCrateInventory()
    {
        return this.crateInventory;
    }
}
