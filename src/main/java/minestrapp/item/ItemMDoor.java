package minestrapp.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;

public class ItemMDoor extends ItemDoor
{
	private Block door;
	
	public ItemMDoor(Block block)
	{
		super(block);
		this.setUnlocalizedName(block.getUnlocalizedName().substring(5));
		this.setRegistryName(block.getUnlocalizedName().substring(5));
		this.door = block;
	}
	
	public int getItemBurnTime(ItemStack itemStack)
    {
		return 200;
    }
	
	public Block getBlock()
	{
		return this.door;
	}
}
