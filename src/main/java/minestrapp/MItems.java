package minestrapp;

import java.util.ArrayList;
import java.util.List;

import minestrapp.item.tools.MAxe;
import minestrapp.item.tools.MDagger;
import minestrapp.item.tools.MFireSword;
import minestrapp.block.EnumStoneTypeMOnly;
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
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemSeeds;
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
	public static Item bronze_pickaxe;
	public static Item bronze_axe;
	public static Item bronze_shovel;
	public static Item bronze_hoe;
	public static Item steel_pickaxe;
	public static Item steel_axe;
	public static Item steel_shovel;
	public static Item steel_hoe;
	public static Item torite_pickaxe;
	public static Item torite_axe;
	public static Item torite_shovel;
	public static Item torite_hoe;
	public static Item titanium_pickaxe;
	public static Item titanium_axe;
	public static Item titanium_shovel;
	public static Item titanium_hoe;
	
	public static Item gold_dagger;
	public static Item wooden_dagger;
	public static Item stone_dagger;
	public static Item copper_sword;
	public static Item copper_dagger;
	public static Item bronze_sword;
	public static Item bronze_dagger;
	public static Item steel_sword;
	public static Item steel_dagger;
	public static Item torite_sword;
	public static Item torite_dagger;
	public static Item iron_dagger;
	public static Item fire_sword;
	public static Item diamond_dagger;
	public static Item titanium_sword;
	public static Item titanium_dagger;
	
	public static Item health_crystal;

	public static ItemFood pepper;
	public static MItemsSeeds pepper_seeds;	
	public static ItemFood cabbage;
	public static MItemsSeeds cabbage_seeds;	
	public static ItemFood celery;
	public static MItemsSeeds celery_seeds;	
	public static MItemsSeedFood lettuce;
	public static MItemsSeedFood onion;
	public static MItemsSeedFood peanuts;
	public static ItemFood tomato;
	public static MItemsSeeds tomato_seeds;
	
	public static Item tin_helm;
	public static Item tin_chest;
	public static Item tin_legs;
	public static Item tin_feet;
	
	public static final ToolMaterial COPPER = EnumHelper.addToolMaterial(Minestrapp5.MODID + ":copper", 1, 200, 5F, 1.5F, 17).setRepairItem(new ItemStack(ingots, 1, 0));
	public static final ToolMaterial BRONZE = EnumHelper.addToolMaterial(Minestrapp5.MODID + ":bronze", 2, 1000, 5F, 1.5F, 13).setRepairItem(new ItemStack(ingots, 1, 2));
	public static final ToolMaterial STEEL = EnumHelper.addToolMaterial(Minestrapp5.MODID + ":steel", 2, 500, 7.5F, 2.5F, 19).setRepairItem(new ItemStack(ingots, 1, 3));
	public static final ToolMaterial TORITE = EnumHelper.addToolMaterial(Minestrapp5.MODID + ":torite", 2, 1200, 7F, 3.5F, 30).setRepairItem(new ItemStack(ingots, 1, 4));
	public static final ToolMaterial TITANIUM = EnumHelper.addToolMaterial(Minestrapp5.MODID + ":titanium", 4, 3122, 16F, 6F, 5).setRepairItem(new ItemStack(ingots, 1, 5));
	
	public static final ArmorMaterial TIN = EnumHelper.addArmorMaterial("tin", Minestrapp5.MODID + ":tin", 4, new int[]{1, 3, 4, 1}, 10, SoundEvents.ENTITY_IRONGOLEM_ATTACK, 0.0F).setRepairItem(new ItemStack(ingots, 1, 1));
	
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
		register(bronze_pickaxe = new MPickaxe(BRONZE, "bronze_pickaxe"));
		register(bronze_axe = new MAxe(BRONZE, "bronze_axe", 8.0F, -3.15F));
		register(bronze_shovel = new MShovel(BRONZE, "bronze_shovel"));
		register(bronze_hoe = new MHoe(BRONZE, "bronze_hoe"));
		register(steel_pickaxe = new MPickaxe(STEEL, "steel_pickaxe"));
		register(steel_axe = new MAxe(STEEL, "steel_axe", 8.0F, -3.05F));
		register(steel_shovel = new MShovel(STEEL, "steel_shovel"));
		register(steel_hoe = new MHoe(STEEL, "steel_hoe"));
		register(torite_pickaxe = new MPickaxe(TORITE, "torite_pickaxe"));
		register(torite_axe = new MAxe(TORITE, "torite_axe", 9.0F, -3.2F));
		register(torite_shovel = new MShovel(TORITE, "torite_shovel"));
		register(torite_hoe = new MHoe(TORITE, "torite_hoe"));
		register(titanium_pickaxe = new MPickaxe(TITANIUM, "titanium_pickaxe"));
		register(titanium_axe = new MAxe(TITANIUM, "titanium_axe", 16.0F, -3.9F));
		register(titanium_shovel = new MShovel(TITANIUM, "titanium_shovel"));
		register(titanium_hoe = new MHoe(TITANIUM, "titanium_hoe"));
		
		register(gold_dagger = new MDagger(Item.ToolMaterial.GOLD, "gold_dagger"));
		register(wooden_dagger = new MDagger(Item.ToolMaterial.WOOD, "wooden_dagger"));
		register(stone_dagger = new MDagger(Item.ToolMaterial.STONE, "stone_dagger"));
		register(copper_sword = new MSword(COPPER, "copper_sword"));
		register(copper_dagger = new MDagger(COPPER, "copper_dagger"));
		register(iron_dagger = new MDagger(Item.ToolMaterial.IRON, "iron_dagger"));
		register(bronze_sword = new MSword(BRONZE, "bronze_sword"));
		register(bronze_dagger = new MDagger(BRONZE, "bronze_dagger"));
		register(steel_sword = new MSword(STEEL, "steel_sword"));
		register(steel_dagger = new MDagger(STEEL, "steel_dagger"));
		register(torite_sword = new MSword(TORITE, "torite_sword"));
		register(torite_dagger = new MDagger(TORITE, "torite_dagger"));
		register(fire_sword = new MFireSword("fire_sword"));
		register(diamond_dagger = new MDagger(Item.ToolMaterial.DIAMOND, "diamond_dagger"));
		register(titanium_sword = new MSword(TITANIUM, "titanium_sword"));
		register(titanium_dagger = new MDagger(TITANIUM, "titanium_dagger"));
		
		register(tin_helm = new MArmor(TIN, 1, EntityEquipmentSlot.HEAD, "tin_helm"));
		register(tin_chest = new MArmor(TIN, 1, EntityEquipmentSlot.CHEST, "tin_chest"));
		register(tin_legs = new MArmor(TIN, 2, EntityEquipmentSlot.LEGS, "tin_legs"));
		register(tin_feet = new MArmor(TIN, 1, EntityEquipmentSlot.FEET, "tin_feet"));
		
		registerFood(pepper = new MItemsFood(2, 1.0F, false, "pepper"));	
		registerSeeds(pepper_seeds = new MItemsSeeds(MBlocks.crop_pepper, Blocks.FARMLAND, "pepper_seeds"));
		registerFood(cabbage = new MItemsFood(2, 1.0F, false, "cabbage"));	
		registerSeeds(cabbage_seeds = new MItemsSeeds(MBlocks.crop_cabbage, Blocks.FARMLAND, "cabbage_seeds"));
		registerFood(celery = new MItemsFood(2, 1.0F, false, "celery"));	
		registerSeeds(celery_seeds = new MItemsSeeds(MBlocks.crop_celery, Blocks.FARMLAND, "celery_seeds"));
		registerSeedFood(onion = new MItemsSeedFood(2, 1.0F, MBlocks.crop_onion, Blocks.FARMLAND, "onion"));	
		registerSeedFood(peanuts = new MItemsSeedFood(2, 1.0F, MBlocks.crop_peanuts, Blocks.FARMLAND, "peanuts"));
		registerSeedFood(lettuce = new MItemsSeedFood(2, 1.0F, MBlocks.crop_lettuce, Blocks.FARMLAND, "lettuce"));
		registerFood(tomato = new MItemsFood(2, 1.0F, false, "tomato"));	
		registerSeeds(tomato_seeds = new MItemsSeeds(MBlocks.crop_tomato, Blocks.FARMLAND, "tomato_seeds"));
		
		GameRegistry.addSmelting(new ItemStack(MBlocks.cold_sand, 1, 0), new ItemStack(Blocks.SAND, 1, 0), 0.1F);
		GameRegistry.addSmelting(new ItemStack(MBlocks.cold_sand, 1, 1), new ItemStack(Blocks.SAND, 1, 1), 0.1F);
		GameRegistry.addSmelting(new ItemStack(MBlocks.permafrost, 1, 0), new ItemStack(Blocks.DIRT, 1, 0), 0.1F);
		GameRegistry.addSmelting(new ItemStack(MBlocks.permafrost, 1, 1), new ItemStack(Blocks.DIRT, 1, 1), 0.1F);
		GameRegistry.addSmelting(new ItemStack(MBlocks.permafrost, 1, 2), new ItemStack(Blocks.DIRT, 1, 2), 0.1F);
		GameRegistry.addSmelting(MBlocks.ore_coal, new ItemStack(Items.COAL, 1, 0), 0.1F);
		GameRegistry.addSmelting(MBlocks.ore_copper, new ItemStack(ingots, 1, 0), 0.5F);
		GameRegistry.addSmelting(MBlocks.ore_tin, new ItemStack(ingots, 1, 1), 0.5F);
		GameRegistry.addSmelting(MBlocks.ore_iron, new ItemStack(Items.IRON_INGOT), 0.7F);
		GameRegistry.addSmelting(MBlocks.ore_gold, new ItemStack(Items.GOLD_INGOT), 1F);
		GameRegistry.addSmelting(MBlocks.ore_lapis, new ItemStack(Items.DYE, 1, EnumDyeColor.BLUE.getDyeDamage()), 0.2F);
		GameRegistry.addSmelting(MBlocks.ore_redstone, new ItemStack(Items.REDSTONE), 0.7F);
		GameRegistry.addSmelting(MBlocks.ore_torite, new ItemStack(ingots, 1, 4), 1F);
		GameRegistry.addSmelting(MBlocks.ore_diamond, new ItemStack(Items.DIAMOND), 1F);
		GameRegistry.addSmelting(MBlocks.ore_emerald, new ItemStack(Items.EMERALD), 1F);
		GameRegistry.addSmelting(MBlocks.ore_titanium, new ItemStack(ingots, 1, 5), 4F);
		GameRegistry.addSmelting(MBlocks.ore_soul, new ItemStack(gem_soul), 3F);
		
		for(int i = 0 ; i < EnumStoneTypeMOnly.values().length ; i++)
		{
			GameRegistry.addSmelting(new ItemStack(MBlocks.cobblestone, 1, i), new ItemStack(MBlocks.stone, 1, i), 0.1F);
		}
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

	public static void registerFood(ItemFood mItemsFood)
	{
		GameRegistry.register(mItemsFood);
		itemList.add(mItemsFood);
	}
	public static void registerSeeds(ItemSeeds mItemsSeeds)
	{
		GameRegistry.register(mItemsSeeds);
		itemList.add(mItemsSeeds);
	}
	public static void registerSeedFood(MItemsSeedFood mItemsSeedFood)
	{
		GameRegistry.register(mItemsSeedFood);
		itemList.add(mItemsSeedFood);
	}
	
	private static void initModel(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
	private static void initModel(MItemsSeeds item)
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
	private static void initModelWithVariants(MItemsSeeds item)
	{
		for(int i = 0 ; i < ((IItemVariants)item).getMaxVariants() ; i++)
		{
			ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(item.getRegistryName() + "_" + i, "inventory"));
		}
	}
}
