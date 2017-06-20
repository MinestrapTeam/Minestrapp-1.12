package minestrapp;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MTabs
{
	public static CreativeTabs environment = new CreativeTabs("EnvironmentalBlocks")
	{
		@Override
		public String getTabLabel()
		{
			return "EnvironmentalBlocks";
		}
		@Override
		@SideOnly(Side.CLIENT)
		public ItemStack getTabIconItem()
		{
			return new ItemStack(Item.getItemFromBlock(MBlocks.cold_sand), 1, 0);
		}
	};
	
	public static CreativeTabs ore = new CreativeTabs("Ores")
	{
		@Override
		public String getTabLabel()
		{
			return "Ores";
		}
		@Override
		@SideOnly(Side.CLIENT)
		public ItemStack getTabIconItem()
		{
			return new ItemStack(Item.getItemFromBlock(MBlocks.ore_copper), 1, 9);
		}
	};
	
	public static CreativeTabs resource = new CreativeTabs("ResourceBlocks")
	{
		@Override
		public String getTabLabel()
		{
			return "ResourceBlocks";
		}
		@Override
		@SideOnly(Side.CLIENT)
		public ItemStack getTabIconItem()
		{
			return new ItemStack(Item.getItemFromBlock(MBlocks.block_copper), 1, 0);
		}
	};
}
