package minestrapp.container;

import minestrapp.MItems;
import minestrapp.inventories.InventoryBackpack;
import minestrapp.inventories.SlotBackpack;
import minestrapp.item.ItemBackpack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemShulkerBox;
import net.minecraft.item.ItemStack;

public class ContainerBackpack extends Container {
	
	public final InventoryBackpack inventory;
	
	public ContainerBackpack(InventoryPlayer player, InventoryBackpack backpack) {
		this.inventory = backpack;
		int rows = 1;
		
		//Backpack
		if(this.inventory.type == 1)
			rows = 2;
		
		//Satchel
		for (int j = 0; j < rows; ++j) {
			for (int k = 0; k < 9; ++k) {
				this.addSlotToContainer(new SlotBackpack(this.inventory, k + j * 9, 8 + k * 18, 18 + j * 18));
			}
		}
		
		
		// Player Inventory, Slot 9-35, Slot IDs 4-30
	    for (int y = 0; y < 3; ++y) {
	        for (int x = 0; x < 9; ++x) {
	            this.addSlotToContainer(new Slot(player, x + y * 9 + 9, 8 + x * 18, 33 + (18*rows) + (y * 18)));
	        }
	    }

	    // Player Inventory, Slot 0-8, Slot IDs 31-39
	    for (int x = 0; x < 9; ++x) {
	        this.addSlotToContainer(new Slot(player, x, 8 + x * 18, 91 + (18*rows)));
	    }
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            int numRows = 1;
            if(inventory.type == 1) {
            	numRows = 2;
            }
            if(inventory.type == 2) {
            	numRows = 1;
            }
            
            if (index < numRows * 9) {
                if (!this.mergeItemStack(itemstack1, numRows * 9, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, numRows * 9, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            }
            else {
                slot.onSlotChanged();
            }
        }

        return itemstack;
	}
	
	@Override
	public void addListener(IContainerListener listener) {
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.inventory);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}
	
	public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player)
    {
		Slot slot = null;
		
		if(slotId >= 0)
			slot = this.getSlot(slotId);
		
		if(slot != null && slot.getHasStack() && (slot.getStack().getItem() instanceof ItemBackpack || slot.getStack().getItem() instanceof ItemShulkerBox))
			return ItemStack.EMPTY;
		else
			return super.slotClick(slotId, dragType, clickTypeIn, player);
    }
}
