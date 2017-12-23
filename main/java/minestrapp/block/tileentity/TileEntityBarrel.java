package minestrapp.block.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

import minestrapp.container.ContainerBarrel;

public class TileEntityBarrel extends TileEntityLockableLoot
{
	private NonNullList<ItemStack> barrelContents = NonNullList.<ItemStack>withSize(36, ItemStack.EMPTY);

	private ItemStackHandler inventory = new ItemStackHandler(36);

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);

        if (!this.checkLootAndWrite(compound))
        {
            ItemStackHelper.saveAllItems(compound, this.barrelContents);
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
        this.barrelContents = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);

        if (!this.checkLootAndRead(compound))
        {
            ItemStackHelper.loadAllItems(compound, this.barrelContents);
        }

        if (compound.hasKey("CustomName", 8))
        {
            this.customName = compound.getString("CustomName");
        }
	}

	@Override
	public String getName()
	{
		return this.hasCustomName() ? this.customName : "Barrel";
	}

	@Override
	public int getSizeInventory()
	{
		return 36;
	}

	@Override
	public boolean isEmpty()
	{
		for (ItemStack itemstack : this.barrelContents)
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
        return new ContainerBarrel(playerInventory, this, playerIn);
	}

	@Override
	public String getGuiID()
	{
		return "minestrapp:barrel";
	}

	@Override
	protected NonNullList<ItemStack> getItems()
	{
		return this.barrelContents;
	}
}