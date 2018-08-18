package minestrapp.container;

import minestrapp.inventories.InventoryBackpack;
import minestrapp.inventories.SlotBackpack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;

public class ContainerBackpack extends Container {
	
	public final InventoryBackpack inventory;
	
	public ContainerBackpack(InventoryPlayer player, InventoryBackpack backpack) {
		this.inventory = backpack;
		
		for (int j = 0; j < 2; ++j) {
			for (int k = 0; k < 9; ++k) {
				this.addSlotToContainer(new SlotBackpack(this.inventory, k + j * 9, 8 + k * 18, 18 + j * 18));
			}
		}
		
		// Player Inventory, Slot 9-35, Slot IDs 4-30
	    for (int y = 0; y < 3; ++y) {
	        for (int x = 0; x < 9; ++x) {
	            this.addSlotToContainer(new Slot(player, x + y * 9 + 9, 8 + x * 18, 104 + y * 18));
	        }
	    }

	    // Player Inventory, Slot 0-8, Slot IDs 31-39
	    for (int x = 0; x < 9; ++x) {
	        this.addSlotToContainer(new Slot(player, x, 8 + x * 18, 162));
	    }
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
	
}
