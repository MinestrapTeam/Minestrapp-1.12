package minestrapp;

import java.util.ArrayList;
import java.util.List;

import minestrapp.item.tools.MAxe;
import minestrapp.item.util.MItemDagger;
import minestrapp.item.tools.MFireSword;
import minestrapp.item.util.MItemHealthCrystal;
import minestrapp.item.tools.MHoe;
import minestrapp.item.tools.MPickaxe;
import minestrapp.item.tools.MShovel;
import minestrapp.item.tools.MSword;
import minestrapp.item.util.IItemVariants;
import minestrapp.item.util.ItemMetaBase;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MItems
{
	static List<Item> itemList = new ArrayList<Item>();
	
	public static Item ingots;
	
	public static MPickaxe copper_pickaxe;
	public static MAxe copper_axe;
	public static MShovel copper_shovel;
	public static MHoe copper_hoe;
	public static MSword copper_sword;
	public static MItemDagger wooden_dagger;
	public static MItemHealthCrystal health_crystal;
	public static MFireSword fire_sword;
	
	public static final ToolMaterial COPPER = EnumHelper.addToolMaterial(Minestrapp5.MODID + ":copper", 2, 200, 5F, 1.5F, 17);
	
	public static void init()
	{
		register(ingots = new ItemMetaBase("m_ingot", 9).setCreativeTab(MTabs.minerals));
		
		register(health_crystal = new MItemHealthCrystal("health_crystal"));
		register(fire_sword = new MFireSword("fire_sword"));
		register(wooden_dagger = new MItemDagger(Item.ToolMaterial.WOOD, "wooden_dagger"));
		register(copper_pickaxe = new MPickaxe(COPPER, "copper_pickaxe"));
		register(copper_axe = new MAxe(COPPER, "copper_axe"));
		register(copper_shovel = new MShovel(COPPER, "copper_shovel"));
		register(copper_hoe = new MHoe(COPPER, "copper_hoe"));
		register(copper_sword = new MSword(COPPER, "copper_sword"));
	}
	
	public static void registerRenders()
	{
		for(Item item: itemList)
		{
			if(item instanceof IItemVariants)
			{
				initModelWithVariants(item);
			}
			else
			{
				initModel(item);
			}
		}
	}
	
	public static void register(Item item)
	{
		GameRegistry.register(item);
		itemList.add(item);
	}
	
	private static void initModel(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
	private static void initModelWithVariants(Item item)
	{
		for(int i = 0 ; i < ((IItemVariants)item).getMaxVariants() ; i++)
		{
			ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(item.getRegistryName() + "_" + i, "inventory"));
		}
	}
}
