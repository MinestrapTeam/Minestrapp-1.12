package minestrapp;

import java.util.ArrayList;
import java.util.List;

import minestrapp.item.tools.MAxe;
import minestrapp.item.tools.MDagger;
import minestrapp.item.tools.MFireSword;
import minestrapp.item.ItemSoulGem;
import minestrapp.item.MItemHealthCrystal;
import minestrapp.item.armor.MArmor;
import minestrapp.item.tools.MHoe;
import minestrapp.item.tools.MPickaxe;
import minestrapp.item.tools.MShovel;
import minestrapp.item.tools.MSword;
import minestrapp.item.util.IItemVariants;
import minestrapp.item.util.ItemMetaBase;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
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
	public static Item gem_soul;
	
	public static Item tech_components;
	
	public static Item copper_pickaxe;
	public static Item copper_axe;
	public static Item copper_shovel;
	public static Item copper_hoe;
	
	public static Item gold_dagger;
	public static Item wooden_dagger;
	public static Item stone_dagger;
	public static Item copper_sword;
	public static Item copper_dagger;
	public static Item iron_dagger;
	public static Item fire_sword;
	public static Item diamond_dagger;
	
	public static Item health_crystal;
	
	public static Item tin_helm;
	public static Item tin_chest;
	public static Item tin_legs;
	public static Item tin_feet;
	
	public static final ToolMaterial COPPER = EnumHelper.addToolMaterial(Minestrapp5.MODID + ":copper", 2, 200, 5F, 1.5F, 17);
	
	public static final ArmorMaterial TIN = EnumHelper.addArmorMaterial("tin", Minestrapp5.MODID + ":tin", 4, new int[]{1, 3, 4, 1}, 12, SoundEvents.ENTITY_IRONGOLEM_ATTACK, 0.0F);
	
	public static void init()
	{
		//0=Copper, 1=Tin, 2=Bronze, 3=Steel, 4=Torite, 5=Titanium, 6=Glacierite, 7=Blazium, 8=Dimensium
		register(ingots = new ItemMetaBase("m_ingot", 9).setCreativeTab(MTabs.minerals));
		//0=Reinforced Stick, 1=Wing Segment, 2=Propeller, 3=Inert Chip, 4=Technological Doodad, 5=Adv. Technological Doodad
		register(tech_components = new ItemMetaBase("m_tech_component", 6).setCreativeTab(MTabs.ingredients));
		register(gem_soul = new ItemSoulGem("gem_soul"));
		
		register(health_crystal = new MItemHealthCrystal("health_crystal"));

		register(copper_pickaxe = new MPickaxe(COPPER, "copper_pickaxe"));
		register(copper_axe = new MAxe(COPPER, "copper_axe", 8.0F, -3.15F));
		register(copper_shovel = new MShovel(COPPER, "copper_shovel"));
		register(copper_hoe = new MHoe(COPPER, "copper_hoe"));
		
		register(gold_dagger = new MDagger(Item.ToolMaterial.GOLD, "gold_dagger"));
		register(wooden_dagger = new MDagger(Item.ToolMaterial.WOOD, "wooden_dagger"));
		register(stone_dagger = new MDagger(Item.ToolMaterial.STONE, "stone_dagger"));
		register(copper_sword = new MSword(COPPER, "copper_sword"));
		register(copper_dagger = new MDagger(COPPER, "copper_dagger"));
		register(iron_dagger = new MDagger(Item.ToolMaterial.IRON, "iron_dagger"));
		register(fire_sword = new MFireSword("fire_sword"));
		register(diamond_dagger = new MDagger(Item.ToolMaterial.DIAMOND, "diamond_dagger"));
		
		register(tin_helm = new MArmor(TIN, 1, EntityEquipmentSlot.HEAD, "tin_helm"));
		register(tin_chest = new MArmor(TIN, 1, EntityEquipmentSlot.CHEST, "tin_chest"));
		register(tin_legs = new MArmor(TIN, 2, EntityEquipmentSlot.LEGS, "tin_legs"));
		register(tin_feet = new MArmor(TIN, 1, EntityEquipmentSlot.FEET, "tin_feet"));
	
		GameRegistry.addSmelting(MBlocks.ore_coal, new ItemStack(Items.COAL, 1, 0), 0.1F);
		GameRegistry.addSmelting(MBlocks.ore_copper, new ItemStack(ingots, 1, 0), 0.5F);
		GameRegistry.addSmelting(MBlocks.ore_tin, new ItemStack(ingots, 1, 1), 0.5F);
		GameRegistry.addSmelting(MBlocks.ore_iron, new ItemStack(Items.IRON_INGOT), 0.7F);
		GameRegistry.addSmelting(MBlocks.ore_gold, new ItemStack(Items.GOLD_INGOT), 1F);
		GameRegistry.addSmelting(MBlocks.ore_lapis, new ItemStack(Items.DYE, 1, EnumDyeColor.BLUE.getDyeDamage()), 0.2F);
		GameRegistry.addSmelting(MBlocks.ore_diamond, new ItemStack(Items.DIAMOND), 1F);
		GameRegistry.addSmelting(MBlocks.ore_emerald, new ItemStack(Items.EMERALD), 1F);
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
