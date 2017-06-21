package minestrapp.item.util;

import minestrapp.MTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBase extends Item
{
	public boolean foiled;
	
	public ItemBase(String name)
	{
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(MTabs.minerals);
		this.foiled = false;
	}
	
	public void setFoiled()
	{
		this.foiled = true;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack)
	{
		return this.foiled;
	}
}
