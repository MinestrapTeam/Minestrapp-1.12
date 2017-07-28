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
	
	public static CreativeTabs stone = new CreativeTabs("Stone")
	{
		@Override
		public String getTabLabel()
		{
			return "Stone";
		}
		@Override
		@SideOnly(Side.CLIENT)
		public ItemStack getTabIconItem()
		{
			return new ItemStack(Item.getItemFromBlock(MBlocks.stone), 1, 2);
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
	
	public static CreativeTabs utility = new CreativeTabs("UtilityBlocks")
	{
		@Override
		public String getTabLabel()
		{
			return "UtilityBlocks";
		}
		@Override
		@SideOnly(Side.CLIENT)
		public ItemStack getTabIconItem()
		{
			return new ItemStack(Item.getItemFromBlock(MBlocks.soulsteel_vessel), 1, 0);
		}
	};
	
	public static CreativeTabs minerals = new CreativeTabs("Minerals")
	{
		@Override
		public String getTabLabel()
		{
			return "Minerals";
		}
		@Override
		@SideOnly(Side.CLIENT)
		public ItemStack getTabIconItem()
		{
			return new ItemStack(MItems.ingots, 1, 0);
		}
	};
	
	public static CreativeTabs ingredients = new CreativeTabs("Ingredients")
	{
		@Override
		public String getTabLabel()
		{
			return "Ingredients";
		}
		@Override
		@SideOnly(Side.CLIENT)
		public ItemStack getTabIconItem()
		{
			return new ItemStack(MItems.tech_components, 1, 4);
		}
	};
	
	public static CreativeTabs tools = new CreativeTabs("Tools")
	{
		@Override
		public String getTabLabel()
		{
			return "Tools";
		}
		@Override
		@SideOnly(Side.CLIENT)
		public ItemStack getTabIconItem()
		{
			return new ItemStack(MItems.copper_pickaxe);
		}
	};
	
	public static CreativeTabs combat = new CreativeTabs("Combat")
	{
		@Override
		public String getTabLabel()
		{
			return "Combat";
		}
		@Override
		@SideOnly(Side.CLIENT)
		public ItemStack getTabIconItem()
		{
			return new ItemStack(MItems.copper_sword);
		}
	};	
	
	public static CreativeTabs food = new CreativeTabs("Food")
	{
		@Override
		public String getTabLabel()
		{
			return "Food";
		}
		@Override
		@SideOnly(Side.CLIENT)
		public ItemStack getTabIconItem()
		{
			return new ItemStack(MItems.pepper);
		}
	};
}
