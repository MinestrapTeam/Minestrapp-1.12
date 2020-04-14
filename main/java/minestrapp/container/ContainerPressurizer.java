package minestrapp.container;

import minestrapp.block.tileentity.TileEntityPressurizer;
import minestrapp.crafting.SlotPressurizerFuel;
import minestrapp.crafting.SlotPressurizerOutput;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerPressurizer extends Container
{
	private final TileEntityPressurizer te;
	
	private int cookTime;
	private int totalCookTime;
	private int burnTime;
	private int currentBurnTime;
	
	public ContainerPressurizer(InventoryPlayer player, TileEntityPressurizer tileentity)
	{
		this.te = tileentity;
		this.addSlotToContainer(new Slot(tileentity, 0, 53, 17));
		this.addSlotToContainer(new Slot(tileentity, 1, 107, 17));
		this.addSlotToContainer(new Slot(tileentity, 2, 53, 71));
		this.addSlotToContainer(new Slot(tileentity, 3, 107, 71));
		this.addSlotToContainer(new SlotPressurizerFuel(tileentity, 4, 80, 83));
		this.addSlotToContainer(new SlotPressurizerOutput(player.player, tileentity, 5, 80, 44));
		
		// Player Inventory, Slot 9-35, Slot IDs 6-32
	    for (int y = 0; y < 3; ++y)
	    {
	        for (int x = 0; x < 9; ++x)
	        {
	            this.addSlotToContainer(new Slot(player, x + y * 9 + 9, 8 + x * 18, 114 + y * 18));
	        }
	    }

	    // Player Inventory, Slot 0-8, Slot IDs 33-41
	    for (int x = 0; x < 9; ++x)
	    {
	        this.addSlotToContainer(new Slot(player, x, 8 + x * 18, 172));
	    }
	}
	
	@Override
	public void addListener(IContainerListener listener)
	{
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.te);
	}
	
	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.listeners.size(); ++i)
		{
			IContainerListener listener = (IContainerListener)this.listeners.get(i);
			
			if(this.cookTime != this.te.getField(2))
				listener.sendWindowProperty(this, 2, this.te.getField(2));
			if(this.burnTime != this.te.getField(0))
				listener.sendWindowProperty(this, 0, this.te.getField(0));
			if(this.currentBurnTime != this.te.getField(1))
				listener.sendWindowProperty(this, 1, this.te.getField(1));
			if(this.totalCookTime != this.te.getField(3))
				listener.sendWindowProperty(this, 3, this.te.getField(3));
		}
		
		this.cookTime = this.te.getField(2);
		this.burnTime = this.te.getField(0);
		this.currentBurnTime = this.te.getField(1);
		this.totalCookTime = this.te.getField(3);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data)
	{
		this.te.setField(id, data);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return this.te.isUsableByPlayer(playerIn);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
	{
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = (Slot)this.inventorySlots.get(index);
		
		if(slot != null && slot.getHasStack())
		{
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			
			if(index == 5)
			{
				if(!this.mergeItemStack(stack1, 6, 42, true))
					return ItemStack.EMPTY;
				slot.onSlotChange(stack1, stack);
			}
			else if(index >= 6)
			{
				Slot slot1 = (Slot)this.inventorySlots.get(index);
				
				if(!TileEntityPressurizer.isItemFuel(stack1) && !this.mergeItemStack(stack1, 0, 4, false))
					return ItemStack.EMPTY;
				else if(TileEntityPressurizer.isItemFuel(stack1) && !this.mergeItemStack(stack1, 4, 5, false))
					return ItemStack.EMPTY;
				else if(TileEntityPressurizer.isItemFuel(stack1) && !this.mergeItemStack(stack1, 0, 4, false))
					return ItemStack.EMPTY;
				else if(index < 33)
					if(!this.mergeItemStack(stack1, 33, 42, false))
						return ItemStack.EMPTY;
				else if(index >= 32 && index < 42 && !this.mergeItemStack(stack1, 6, 33, false))
					return ItemStack.EMPTY;
			}
			else if(!this.mergeItemStack(stack1, 6, 42, false))
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
	public TileEntityPressurizer getTE(){
		return te;
		
	}
}