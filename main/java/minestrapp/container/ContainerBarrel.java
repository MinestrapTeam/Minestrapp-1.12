package minestrapp.container;

import minestrapp.block.tileentity.TileEntityBarrel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerBarrel extends Container{

	public ContainerBarrel(InventoryPlayer playerInv, final TileEntityBarrel barrel) {
		IItemHandler inventory = barrel.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
		for (int j = 0; j < 4; ++j)
		{
			for (int k = 0; k < 9; ++k)
			{
				addSlotToContainer(new SlotItemHandler(inventory, k + j * 9, 8 + k * 18, 18 + j * 18 -10){
					@Override
					public void onSlotChanged() {
						barrel.markDirty();
					}
				});
				
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 104 + i * 18 -10));
			}
		}
	
		for (int k = 0; k < 9; k++) {
			addSlotToContainer(new Slot(playerInv, k , 8 + k * 18, 162 -10));
		}
	}
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = inventorySlots.get(index);
	
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
	
			int containerSlots = inventorySlots.size() - player.inventory.mainInventory.size();
	
			if (index < containerSlots) {
				if (!this.mergeItemStack(itemstack1, containerSlots, inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, containerSlots, false)) {
				return ItemStack.EMPTY;
			}
	
			if (itemstack1.getCount() == 0) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
	
			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}
	
			slot.onTake(player, itemstack1);
		}
	
		return itemstack;
	}
	
	
	
}
