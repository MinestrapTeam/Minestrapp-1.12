package minestrapp.utils;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ItemUtil
{
	public static boolean compareStacks(ItemStack stack1, ItemStack stack2)
	{
		if(stack1.getItem() == stack2.getItem() && (stack1.getMetadata() == OreDictionary.WILDCARD_VALUE || stack2.getMetadata() == OreDictionary.WILDCARD_VALUE || stack1.getMetadata() == stack2.getMetadata()))
			return true;
		else
			return false;
	}
}
