package minestrapp.item.util;

import java.util.AbstractList;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemMetaBase extends ItemBase implements IItemVariants
{
	private int numVariants;
	
	public ItemMetaBase(String name, int metaAmount)
	{
		super(name);
		this.hasSubtypes = true;
		this.numVariants = metaAmount;
	}
	
	@Override
	public void getSubItems(CreativeTabs tabs, NonNullList<ItemStack> tab)
	{
		for(int i = 0 ; i < this.numVariants ; i++)
		{
			if (this.isInCreativeTab(tabs))
	        {
				tab.add(new ItemStack(this, 1, i));
	        }
		}
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return super.getUnlocalizedName(stack) + "_" + stack.getItemDamage();
	}
	
	@Override
	public int getMaxVariants()
	{
		return this.numVariants;
	}
}
