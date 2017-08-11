package minestrapp.container;

import minestrapp.block.tileentity.TileEntityAlloy;
import minestrapp.crafting.AlloyRecipes;
import minestrapp.crafting.SlotAlloyFuel;
import minestrapp.crafting.SlotAlloyOutput;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerAlloy extends Container {
	
	private final TileEntityAlloy te;
	
	private int cookTime;
	private int totalCookTime;
	private int burnTime;
	private int currentBurnTime;
	
	public ContainerAlloy(InventoryPlayer player, TileEntityAlloy tileentity) {
		this.te = tileentity;
		this.addSlotToContainer(new Slot(tileentity, 0, 37, 17));
		this.addSlotToContainer(new Slot(tileentity, 1, 55, 17));
		this.addSlotToContainer(new SlotAlloyFuel(tileentity, 2, 46, 53));
		this.addSlotToContainer(new SlotAlloyOutput(player.player, tileentity, 3, 116, 35));
		
		// Player Inventory, Slot 9-35, Slot IDs 4-30
	    for (int y = 0; y < 3; ++y) {
	        for (int x = 0; x < 9; ++x) {
	            this.addSlotToContainer(new Slot(player, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
	        }
	    }

	    // Player Inventory, Slot 0-8, Slot IDs 31-39
	    for (int x = 0; x < 9; ++x) {
	        this.addSlotToContainer(new Slot(player, x, 8 + x * 18, 142));
	    }
	}
	
	@Override
	public void addListener(IContainerListener listener) {
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.te);
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.listeners.size(); ++i) {
			IContainerListener listener = (IContainerListener)this.listeners.get(i);
			
			if(this.cookTime != this.te.getField(2))
				listener.sendProgressBarUpdate(this, 2, this.te.getField(2));
			if(this.burnTime != this.te.getField(0))
				listener.sendProgressBarUpdate(this, 0, this.te.getField(0));
			if(this.currentBurnTime != this.te.getField(1))
				listener.sendProgressBarUpdate(this, 1, this.te.getField(1));
			if(this.totalCookTime != this.te.getField(3))
				listener.sendProgressBarUpdate(this, 3, this.te.getField(3));
		}
		
		this.cookTime = this.te.getField(2);
		this.burnTime = this.te.getField(0);
		this.currentBurnTime = this.te.getField(1);
		this.totalCookTime = this.te.getField(3);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		this.te.setField(id, data);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.te.isUsableByPlayer(playerIn);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = (Slot)this.inventorySlots.get(index);
		
		if(slot != null && slot.getHasStack()) {
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			
			if(index == 3) {
				if(!this.mergeItemStack(stack1, 4, 40, true))
					return ItemStack.EMPTY;
				slot.onSlotChange(stack1, stack);
			}
			else if(index != 2 && index != 1 && index != 0) {
				
				Slot slot1 = (Slot)this.inventorySlots.get(index + 1);
				
				if(!AlloyRecipes.instance().getAlloyResult(stack1, slot1.getStack()).isEmpty())
					if(!this.mergeItemStack(stack1, 0, 2, false))
						return ItemStack.EMPTY;
				else if(TileEntityAlloy.isItemFuel(stack1))
					if(!this.mergeItemStack(stack1, 2, 3, false))
						return ItemStack.EMPTY;
				else if(index >= 4 && index < 31)
					if(!this.mergeItemStack(stack1, 31, 40, false))
						return ItemStack.EMPTY;
				else if(index >= 31 && index < 40 && !this.mergeItemStack(stack1, 4, 31, false))
					return ItemStack.EMPTY;
			} else if(!this.mergeItemStack(stack1, 4, 40, false))
				return ItemStack.EMPTY;
			if(stack1.isEmpty())
				slot.putStack(ItemStack.EMPTY);
			else
				slot.onSlotChanged();
			if(stack1.getCount() == stack.getCount())
				return ItemStack.EMPTY;
			slot.onTake(playerIn, stack1);
		}
		return stack;
	}
	public TileEntityAlloy getTE(){
		return te;
		
	}
}