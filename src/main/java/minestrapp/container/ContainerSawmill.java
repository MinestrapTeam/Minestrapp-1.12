package minestrapp.container;

import minestrapp.block.tileentity.TileEntityPipe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerSawmill extends Container
{
	private final IInventory sawmillInventory;
	private final int numRows;

	public ContainerSawmill(IInventory playerInventory, IInventory activatorInventory, EntityPlayer player)
	{
		this.sawmillInventory = activatorInventory;
		this.numRows = 1;
		activatorInventory.openInventory(player);
		int i = (this.numRows - 4) * 18;
		
		this.addSlotToContainer(new Slot(activatorInventory, 0, 8 + 4 * 18, 17));
		
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
		return this.sawmillInventory.isUsableByPlayer(player);
	}
	
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < this.sawmillInventory.getSizeInventory())
            {
                if (!this.mergeItemStack(itemstack1, this.sawmillInventory.getSizeInventory(), this.inventorySlots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, this.sawmillInventory.getSizeInventory(), false))
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
        this.sawmillInventory.closeInventory(playerIn);
    }
	
	public IInventory getSawmillInventory()
    {
        return this.sawmillInventory;
    }
}
