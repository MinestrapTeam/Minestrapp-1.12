package minestrapp;

import java.util.ArrayList;
import java.util.List;

import minestrapp.item.tools.MAxe;
import minestrapp.item.util.MItemDagger;
import minestrapp.item.tools.MFireSword;
import minestrapp.item.util.MItemHealthCrystal;
import minestrapp.item.armor.MArmor;
import minestrapp.item.tools.MHoe;
import minestrapp.item.tools.MPickaxe;
import minestrapp.item.tools.MShovel;
import minestrapp.item.tools.MSword;
import minestrapp.item.util.IItemVariants;
import minestrapp.item.util.ItemMetaBase;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EntityEquipmentSlot;
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
	
	public static MArmor tin_helm;
	public static MArmor tin_chest;
	public static MArmor tin_legs;
	public static MArmor tin_feet;
	
	public static final ToolMaterial COPPER = EnumHelper.addToolMaterial(Minestrapp5.MODID + ":copper", 2, 200, 5F, 1.5F, 17);
	
	public static final ArmorMaterial TIN = EnumHelper.addArmorMaterial("tin", Minestrapp5.MODID + ":tin", 4, new int[]{1, 3, 4, 1}, 12, SoundEvents.ENTITY_IRONGOLEM_ATTACK, 0.0F);
	
	public static void init()
	{
		register(ingots = new ItemMetaBase("m_ingot", 9).setCreativeTab(MTabs.minerals));
		
		register(health_crystal = new MItemHealthCrystal("health_crystal"));
		register(fire_sword = new MFireSword("fire_sword"));
		register(wooden_dagger = new MItemDagger(Item.ToolMaterial.WOOD, "wooden_dagger"));
		register(copper_pickaxe = new MPickaxe(COPPER, "copper_pickaxe"));
		register(copper_axe = new MAxe(COPPER, "copper_axe", 8.0F, -3.15F));
		register(copper_shovel = new MShovel(COPPER, "copper_shovel"));
		register(copper_hoe = new MHoe(COPPER, "copper_hoe"));
		register(copper_sword = new MSword(COPPER, "copper_sword"));
		
		register(tin_helm = new MArmor(TIN, 1, EntityEquipmentSlot.HEAD, "tin_helm"));
		register(tin_chest = new MArmor(TIN, 1, EntityEquipmentSlot.CHEST, "tin_chest"));
		register(tin_legs = new MArmor(TIN, 2, EntityEquipmentSlot.LEGS, "tin_legs"));
		register(tin_feet = new MArmor(TIN, 1, EntityEquipmentSlot.FEET, "tin_feet"));
	
		GameRegistry.addSmelting(MBlocks.ore_copper, new ItemStack(ingots, 1,0), 0F);
		GameRegistry.addSmelting(MBlocks.ore_tin, new ItemStack(ingots, 1,1), 0F);
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
