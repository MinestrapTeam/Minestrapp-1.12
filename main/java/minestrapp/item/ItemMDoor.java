package minestrapp.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;

public class ItemMDoor extends ItemDoor
{
	public ItemMDoor(Block block)
	{
		super(block);
		this.setUnlocalizedName(block.getUnlocalizedName().substring(5));
		this.setRegistryName(block.getUnlocalizedName().substring(5));
	}
	
	public int getItemBurnTime(ItemStack itemStack)
    {
		return 200;
    }
}
