package minestrapp;

import java.util.ArrayList;
import java.util.List;

import minestrapp.block.crops.BlockBerryBush;
import minestrapp.block.item.MItemBlock;
import minestrapp.block.util.BlockBase;
import minestrapp.block.util.BlockStoneBase;
import minestrapp.item.ItemCandy;
import minestrapp.item.ItemDrySpaghetti;
import minestrapp.item.ItemGlowshroomStew;
import minestrapp.item.ItemJamBottle;
import minestrapp.item.ItemPBJ;
import minestrapp.item.ItemSoulGem;
import minestrapp.item.MItemHealthCrystal;
import minestrapp.item.armor.MArmor;
import minestrapp.item.tools.MAxe;
import minestrapp.item.tools.MDagger;
import minestrapp.item.tools.MFireSword;
import minestrapp.item.tools.MHoe;
import minestrapp.item.tools.MPickaxe;
import minestrapp.item.tools.MShovel;
import minestrapp.item.tools.MSword;
import minestrapp.item.util.IItemVariants;
import minestrapp.item.util.ItemBase;
import minestrapp.item.util.ItemMetaBase;
import minestrapp.item.util.MItemBowlFood;
import minestrapp.item.util.MItemsFood;
import minestrapp.item.util.MItemsSeedFood;
import minestrapp.item.util.MItemsSeeds;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class MItems
{
	static List<Item> itemList = new ArrayList<Item>();
	
	public static Item natural_ingredients;
	public static Item mob_loot;
	
	public static Item mud_ball;
	public static Item chunks;
	public static Item irradium;
	public static Item ingots;
	public static Item plating;
	public static Item salt;
	public static Item gems;
	public static Item gem_soul;
	
	public static Item bricks;
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
	public static Item meurodite_pickaxe;
	public static Item meurodite_axe;
	public static Item meurodite_shovel;
	public static Item meurodite_hoe;
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
	public static Item meurodite_sword;
	public static Item meurodite_dagger;
	public static Item torite_sword;
	public static Item torite_dagger;
	public static Item iron_dagger;
	public static Item fire_sword;
	public static Item diamond_dagger;
	public static Item titanium_sword;
	public static Item titanium_dagger;
	
	public static Item tin_helm;
	public static Item tin_chest;
	public static Item tin_legs;
	public static Item tin_feet;
	public static Item bronze_helm;
	public static Item bronze_chest;
	public static Item bronze_legs;
	public static Item bronze_feet;
	public static Item steel_helm;
	public static Item steel_chest;
	public static Item steel_legs;
	public static Item steel_feet;
	public static Item meurodite_helm;
	public static Item meurodite_chest;
	public static Item meurodite_legs;
	public static Item meurodite_feet;
	public static Item torite_helm;
	public static Item torite_chest;
	public static Item torite_legs;
	public static Item torite_feet;
	public static Item titanium_helm;
	public static Item titanium_chest;
	public static Item titanium_legs;
	public static Item titanium_feet;
	
	public static Item health_crystal;

	public static Item pepper_seeds;
	public static Item cabbage_seeds;
	public static Item celery_seeds;
	public static Item tomato_seeds;
	
	public static Item blueberry;
	public static Item blackberry;
	public static Item raspberry;
	public static Item strawberry;
	public static Item voidberry;
	public static Item pepper;	
	public static Item cabbage;
	public static Item celery;	
	public static Item lettuce;
	public static Item onion;
	public static Item peanuts;
	public static Item tomato;
	public static Item corn;
	public static Item corn_on_the_cob;
	public static Item grilled_corn;
	
	public static Item corn_meal;
	public static Item corn_bread;
	public static Item dough;
	public static Item sugar_cookie;
	public static Item bun;
	public static Item pbj;
	public static Item salmon_burger;
	public static Item hamburger;
	public static Item dry_spaghetti;
	public static Item pie_crust;
	public static Item apple_pie;
	public static Item blueberry_pie;
	public static Item blackberry_pie;
	public static Item raspberry_pie;
	public static Item strawberry_pie;
	public static Item voidberry_pie;
	
	public static Item bread_bowl;
	public static Item bread_mushroom_stew;
	public static Item bread_beetroot_soup;
	public static Item bread_rabbit_stew;
	public static Item fried_egg;
	public static Item bread_fried_egg;
	public static Item rice_bowl;
	public static Item bread_rice_bowl;
	public static Item stir_fry;
	public static Item bread_stir_fry;
	public static Item tomato_sauce;
	public static Item bread_tomato_sauce;
	public static Item tomato_soup;
	public static Item bread_tomato_soup;
	public static Item spaghetti;
	public static Item bread_spaghetti;
	public static Item ice_cream;
	public static Item popcorn;
	public static Item bread_popcorn;
	public static Item glowshroom_stew;
	public static Item bread_glowshroom_stew;
	
	public static Item salad;
	public static Item bread_salad;
	public static Item blueberry_salad;
	public static Item bread_blueberry_salad;
	public static Item blackberry_salad;
	public static Item bread_blackberry_salad;
	public static Item raspberry_salad;
	public static Item bread_raspberry_salad;
	public static Item strawberry_salad;
	public static Item bread_strawberry_salad;
	public static Item voidberry_salad;
	public static Item bread_voidberry_salad;
	
	public static Item jam;
	public static Item void_jam;
	public static Item peanut_butter;
	public static Item hot_sauce;
	
	public static Item fat;
	public static Item grease;
	public static Item squid_tentacle;
	public static Item calamari;
	public static Item sushi;
	public static Item lucky_sushi;
	public static Item onigiri;
	public static Item flesh;
	
	public static Item fries;
	public static Item fried_fish;
	public static Item fried_salmon;
	public static Item fish_and_chips;
	
	public static Item candy_red;
	public static Item candy_blue;
	public static Item candy_yellow;
	
	public static final ToolMaterial COPPER = EnumHelper.addToolMaterial(Minestrapp5.MODID + ":copper", 1, 200, 5F, 1.5F, 17).setRepairItem(new ItemStack(ingots, 1, 0));
	public static final ToolMaterial BRONZE = EnumHelper.addToolMaterial(Minestrapp5.MODID + ":bronze", 2, 1000, 5F, 1.5F, 13).setRepairItem(new ItemStack(ingots, 1, 2));
	public static final ToolMaterial STEEL = EnumHelper.addToolMaterial(Minestrapp5.MODID + ":steel", 2, 500, 7.5F, 2.5F, 19).setRepairItem(new ItemStack(ingots, 1, 3));
	public static final ToolMaterial MEURODITE = EnumHelper.addToolMaterial(Minestrapp5.MODID + ":meurodite", 2, 906, 7F, 2.5F, 16).setRepairItem(new ItemStack(gems, 1, 4));
	public static final ToolMaterial TORITE = EnumHelper.addToolMaterial(Minestrapp5.MODID + ":torite", 2, 1200, 7F, 3.5F, 30).setRepairItem(new ItemStack(ingots, 1, 4));
	public static final ToolMaterial TITANIUM = EnumHelper.addToolMaterial(Minestrapp5.MODID + ":titanium", 4, 3122, 16F, 6F, 5).setRepairItem(new ItemStack(ingots, 1, 5));
	
	public static final ArmorMaterial ARMOR_TIN = EnumHelper.addArmorMaterial("tin", Minestrapp5.MODID + ":tin", 4, new int[]{1, 3, 4, 1}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F).setRepairItem(new ItemStack(ingots, 1, 1));
	public static final ArmorMaterial ARMOR_BRONZE = EnumHelper.addArmorMaterial("bronze", Minestrapp5.MODID + ":bronze", 28, new int[]{2, 4, 5, 2}, 8, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F).setRepairItem(new ItemStack(ingots, 1, 2));
	public static final ArmorMaterial ARMOR_STEEL = EnumHelper.addArmorMaterial("steel", Minestrapp5.MODID + ":steel", 18, new int[]{3, 6, 7, 3}, 18, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F).setRepairItem(new ItemStack(ingots, 1, 3));
	public static final ArmorMaterial ARMOR_MEURODITE = EnumHelper.addArmorMaterial("meurodite", Minestrapp5.MODID + ":meurodite", 24, new int[]{2, 6, 7, 3}, 12, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0F).setRepairItem(new ItemStack(gems, 1, 4));
	public static final ArmorMaterial ARMOR_TORITE = EnumHelper.addArmorMaterial("torite", Minestrapp5.MODID + ":torite", 26, new int[]{2, 6, 7, 2}, 30, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 1.5F).setRepairItem(new ItemStack(ingots, 1, 4));
	public static final ArmorMaterial ARMOR_TITANIUM = EnumHelper.addArmorMaterial("titanium", Minestrapp5.MODID + ":titanium", 66, new int[]{3, 6, 8, 3}, 5, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 8.0F).setRepairItem(new ItemStack(ingots, 1, 5));
	
	public static void init()
	{
		//0=Grass Fibers, 1=Mana Leaf
		register(natural_ingredients = new ItemMetaBase("m_natural_item", 2).setBurnTime(100, 0).setCreativeTab(MTabs.ingredients));
		//0=Animal Bones, 1=Tallow
		register(mob_loot = new ItemMetaBase("m_mob_loot", 2).setCreativeTab(MTabs.ingredients));
		
		//TODO: Add projectile effect.
		register(mud_ball = new ItemBase("mud_ball").setCreativeTab(MTabs.minerals));
		//0=Red Rock, 1=Stone, 2=Coldstone, 3=Icestone, 4=Oceanstone, 5=Netherrack, 6=Endstone, 7=Copper, 8=Tin, 9=Iron, 10=Gold, 11=Torite, 12=Titanium, 13=Dimensium
		register(chunks = new ItemMetaBase("m_chunks", 14).setCreativeTab(MTabs.minerals));
		register(irradium = new ItemBase("irradium").setBurnTime(25600).setCreativeTab(MTabs.minerals));
		//0=Copper, 1=Tin, 2=Bronze, 3=Steel, 4=Torite, 5=Titanium, 6=Glacierite, 7=Blazium, 8=Dimensium
		register(ingots = new ItemMetaBase("m_ingot", 9).setBurnTime(4800, 7).setBeaconPayment().setCreativeTab(MTabs.minerals));
		//0=Tin, 1=Bronze, 2=Steel, 3=Meurodite
		register(plating = new ItemMetaBase("m_plating", 4).setCreativeTab(MTabs.ingredients));
		register(salt = new ItemBase("salt").setCreativeTab(MTabs.food));
		//We, are the crystal MItems.gems
		//0=Sunstone, 1=Desert Quartz, 2=Rock Crystal, 3=Radiant Quartz, 4=Meurodite, 5=Blaze Shard, 6=Glacieric Ice Shard
		register(gems = new ItemMetaBase("m_gem", 7).setBurnTime(2000, 5).setCreativeTab(MTabs.minerals));
		register(gem_soul = new ItemSoulGem("gem_soul").setBeaconPayment());
		
		//0=Mud Brick
		register(bricks = new ItemMetaBase("m_bricks", 1).setCreativeTab(MTabs.ingredients));
		//0=Reinforced Stick, 1=Wing Segment, 2=Propeller, 3=Inert Chip, 4=Technological Doodad, 5=Adv. Technological Doodad
		register(tech_components = new ItemMetaBase("m_tech_component", 6).setCreativeTab(MTabs.ingredients));
		
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
		register(steel_axe = new MAxe(STEEL, "steel_axe", 8.0F, -3.08F));
		register(steel_shovel = new MShovel(STEEL, "steel_shovel"));
		register(steel_hoe = new MHoe(STEEL, "steel_hoe"));
		register(meurodite_pickaxe = new MPickaxe(MEURODITE, "meurodite_pickaxe"));
		register(meurodite_axe = new MAxe(MEURODITE, "meurodite_axe", 8.0F, -3.05F));
		register(meurodite_shovel = new MShovel(MEURODITE, "meurodite_shovel"));
		register(meurodite_hoe = new MHoe(MEURODITE, "meurodite_hoe"));
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
		register(meurodite_sword = new MSword(MEURODITE, "meurodite_sword"));
		register(meurodite_dagger = new MDagger(MEURODITE, "meurodite_dagger"));
		register(torite_sword = new MSword(TORITE, "torite_sword"));
		register(torite_dagger = new MDagger(TORITE, "torite_dagger"));
		register(fire_sword = new MFireSword("fire_sword"));
		register(diamond_dagger = new MDagger(Item.ToolMaterial.DIAMOND, "diamond_dagger"));
		register(titanium_sword = new MSword(TITANIUM, "titanium_sword"));
		register(titanium_dagger = new MDagger(TITANIUM, "titanium_dagger"));
		
		register(tin_helm = new MArmor(ARMOR_TIN, 1, EntityEquipmentSlot.HEAD, "tin_helm"));
		register(tin_chest = new MArmor(ARMOR_TIN, 1, EntityEquipmentSlot.CHEST, "tin_chest"));
		register(tin_legs = new MArmor(ARMOR_TIN, 2, EntityEquipmentSlot.LEGS, "tin_legs"));
		register(tin_feet = new MArmor(ARMOR_TIN, 1, EntityEquipmentSlot.FEET, "tin_feet"));
		register(bronze_helm = new MArmor(ARMOR_BRONZE, 1, EntityEquipmentSlot.HEAD, "bronze_helm"));
		register(bronze_chest = new MArmor(ARMOR_BRONZE, 1, EntityEquipmentSlot.CHEST, "bronze_chest"));
		register(bronze_legs = new MArmor(ARMOR_BRONZE, 2, EntityEquipmentSlot.LEGS, "bronze_legs"));
		register(bronze_feet = new MArmor(ARMOR_BRONZE, 1, EntityEquipmentSlot.FEET, "bronze_feet"));
		register(steel_helm = new MArmor(ARMOR_STEEL, 1, EntityEquipmentSlot.HEAD, "steel_helm"));
		register(steel_chest = new MArmor(ARMOR_STEEL, 1, EntityEquipmentSlot.CHEST, "steel_chest"));
		register(steel_legs = new MArmor(ARMOR_STEEL, 2, EntityEquipmentSlot.LEGS, "steel_legs"));
		register(steel_feet = new MArmor(ARMOR_STEEL, 1, EntityEquipmentSlot.FEET, "steel_feet"));
		register(meurodite_helm = new MArmor(ARMOR_MEURODITE, 1, EntityEquipmentSlot.HEAD, "meurodite_helm"));
		register(meurodite_chest = new MArmor(ARMOR_MEURODITE, 1, EntityEquipmentSlot.CHEST, "meurodite_chest"));
		register(meurodite_legs = new MArmor(ARMOR_MEURODITE, 2, EntityEquipmentSlot.LEGS, "meurodite_legs"));
		register(meurodite_feet = new MArmor(ARMOR_MEURODITE, 1, EntityEquipmentSlot.FEET, "meurodite_feet"));
		register(torite_helm = new MArmor(ARMOR_TORITE, 1, EntityEquipmentSlot.HEAD, "torite_helm"));
		register(torite_chest = new MArmor(ARMOR_TORITE, 1, EntityEquipmentSlot.CHEST, "torite_chest"));
		register(torite_legs = new MArmor(ARMOR_TORITE, 2, EntityEquipmentSlot.LEGS, "torite_legs"));
		register(torite_feet = new MArmor(ARMOR_TORITE, 1, EntityEquipmentSlot.FEET, "torite_feet"));
		register(titanium_helm = new MArmor(ARMOR_TITANIUM, 1, EntityEquipmentSlot.HEAD, "titanium_helm"));
		register(titanium_chest = new MArmor(ARMOR_TITANIUM, 1, EntityEquipmentSlot.CHEST, "titanium_chest"));
		register(titanium_legs = new MArmor(ARMOR_TITANIUM, 2, EntityEquipmentSlot.LEGS, "titanium_legs"));
		register(titanium_feet = new MArmor(ARMOR_TITANIUM, 1, EntityEquipmentSlot.FEET, "titanium_feet"));
		
		register(pepper_seeds = new MItemsSeeds(MBlocks.crop_pepper, Blocks.FARMLAND, "pepper_seeds"));
		register(cabbage_seeds = new MItemsSeeds(MBlocks.crop_cabbage, Blocks.FARMLAND, "cabbage_seeds"));
		register(celery_seeds = new MItemsSeeds(MBlocks.crop_celery, Blocks.FARMLAND, "celery_seeds"));
		register(tomato_seeds = new MItemsSeeds(MBlocks.crop_tomato, Blocks.FARMLAND, "tomato_seeds"));
		
		//Total Saturation Restored = Sat Val * 2 * Hunger Val
		//I.E.; Peppers would restore 1.8 points of Saturation, half as much as Carrots, but at the same food value.
		//For balance reference, a list of all Vanilla Hunger & Sat Vals can be found at minecraft.gamepedia.com/Food
		register(blueberry = new MItemsFood(1, 1F, false, "blueberry").setPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 400, 0), 0.2F));
		register(blackberry = new MItemsFood(2, 0.25F, false, "blackberry").setPotionEffect(new PotionEffect(MobEffects.HASTE, 400, 0), 0.2F));
		register(raspberry = new MItemsFood(1, 0F, false, "raspberry").setPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 0), 0.2F));
		register(strawberry = new MItemsFood(3, 0.1333F, false, "strawberry").setPotionEffect(new PotionEffect(MobEffects.STRENGTH, 400, 0), 0.2F));
		register(voidberry = new MItemsFood(2, 0.075F, false, "voidberry").setPotionEffect(new PotionEffect(MobEffects.LEVITATION, 400, 0), 0.2F));
		register(pepper = new MItemsFood(3, 0.3F, false, "pepper").setPotionEffect(new PotionEffect(MobEffects.SPEED, 400, 0), 0.25F));	
		register(cabbage = new MItemsFood(5, 0.3F, false, "cabbage"));
		register(celery = new MItemsFood(2, 1.5F, false, "celery"));
		register(onion = new MItemsSeedFood(3, 0.8F, MBlocks.crop_onion, Blocks.FARMLAND, "onion").setPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 100), 0.7F));	
		register(peanuts = new MItemsSeedFood(2, 2.4F, MBlocks.crop_peanuts, Blocks.FARMLAND, "peanuts"));
		register(lettuce = new MItemsSeedFood(4, 0.375F, MBlocks.crop_lettuce, Blocks.FARMLAND, "lettuce"));
		register(tomato = new MItemsFood(4, 0.275F, false, "tomato"));
		register(corn = new MItemsSeeds(MBlocks.crop_corn, Blocks.FARMLAND, "corn"));
		register(corn_on_the_cob = new MItemsFood(4, 0.4F, false, "corn_on_the_cob").setDroppedItem(new ItemStack(Items.STICK)));
		register(grilled_corn = new MItemsFood(6, 0.7F, false, "grilled_corn").setDroppedItem(new ItemStack(Items.STICK)));
		
		register(corn_meal = new MItemsFood(1, 0.1F, false, "corn_meal"));
		register(corn_bread = new MItemsFood(4, 1F, false, "corn_bread"));
		register(dough = new MItemsFood(1, 0.15F, false, "dough"));
		register(sugar_cookie = new MItemsFood(1, 0.4F, false, "sugar_cookie"));
		register(bun = new MItemsFood(2, 0.75F, false, "bun"));
		register(pbj = new ItemPBJ(12, 1.5F, false, "pbj"));
		register(salmon_burger = new MItemsFood(17, 0.6529F, false, "salmon_burger"));
		register(hamburger = new MItemsFood(19, 0.6F, false, "hamburger"));
		register(dry_spaghetti = new ItemDrySpaghetti(1, 0.1F, false, "dry_spaghetti"));
		register(pie_crust = new MItemsFood(2, 0.125F, false, "pie_crust"));
		register(apple_pie = new MItemsFood(6, 0.3F, false, "apple_pie"));
		register(blueberry_pie = new MItemsFood(6, 0.75F, false, "blueberry_pie").setPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 600), 0.85F));
		register(blackberry_pie = new MItemsFood(10, 0.25F, false, "blackberry_pie").setPotionEffect(new PotionEffect(MobEffects.HASTE, 600), 0.85F));
		register(raspberry_pie = new MItemsFood(6, 0.1667F, false, "raspberry_pie").setPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 1), 0.85F));
		register(strawberry_pie = new MItemsFood(14, 0.15F, false, "strawberry_pie").setPotionEffect(new PotionEffect(MobEffects.STRENGTH, 600), 0.85F));
		register(voidberry_pie = new MItemsFood(10, 0.11F, false, "voidberry_pie").setPotionEffect(new PotionEffect(MobEffects.LEVITATION, 600), 0.85F));
		
		register(bread_bowl = new MItemsFood(2, 1F, false, "bread_bowl"));
		register(bread_mushroom_stew = new MItemsFood(8, 0.5125F, false, "bread_mushroom_stew"));
		register(bread_beetroot_soup = new MItemsFood(8, 0.5125F, false, "bread_beetroot_soup"));
		register(bread_rabbit_stew = new MItemsFood(12, 0.5417F, false, "bread_rabbit_stew"));
		register(fried_egg = new MItemBowlFood(4, 0.625F, false, "fried_egg"));
		register(bread_fried_egg = new MItemsFood(6, 0.5F, false, "bread_fried_egg"));
		register(rice_bowl = new MItemBowlFood(4, 0.5F, false, "rice_bowl").setDroppedItem(new ItemStack(Items.STICK)));
		register(bread_rice_bowl = new MItemsFood(6, 0.4167F, false, "bread_rice_bowl").setDroppedItem(new ItemStack(Items.STICK)));
		register(stir_fry = new MItemBowlFood(16, 0.5625F, false, "stir_fry").setDroppedItem(new ItemStack(Items.STICK)).setIgnitesPlayer(10).setPotionEffect(new PotionEffect(MobEffects.SPEED, 440, 2), 0.55F));
		register(bread_stir_fry = new MItemsFood(18, 0.5278F, false, "bread_stir_fry").setDroppedItem(new ItemStack(Items.STICK)).setIgnitesPlayer(10).setPotionEffect(new PotionEffect(MobEffects.SPEED, 440, 2), 0.55F));
		register(tomato_sauce = new MItemBowlFood(5, 0.24F, false, "tomato_sauce"));
		register(bread_tomato_sauce = new MItemsFood(7, 0.2429F, false, "bread_tomato_sauce"));
		register(tomato_soup = new MItemBowlFood(8, 0.425F, false, "tomato_soup"));
		register(bread_tomato_soup = new MItemsFood(10, 0.39F, false, "bread_tomato_soup"));
		register(spaghetti = new MItemBowlFood(12, 0.5833F, false, "spaghetti"));
		register(bread_spaghetti = new MItemsFood(14, 0.5357F, false, "bread_spaghetti"));
		register(popcorn= new MItemBowlFood(6, 0.0333F, false, "popcorn"));
		register(bread_popcorn = new MItemsFood(8, 0.0875F, false, "bread_popcorn"));
		register(ice_cream = new MItemBowlFood(10, 0.15F, false, "ice_cream", new ItemStack(Items.BUCKET), false).setIgnitesPlayer(-1).setCuresEffects());
		register(glowshroom_stew = new ItemGlowshroomStew(6, 0.6F, false, "glowshroom_stew", false));
		register(bread_glowshroom_stew = new ItemGlowshroomStew(8, 0.5125F, false, "bread_glowshroom_stew", true));
		
		register(salad = new MItemBowlFood(12, 0.375F, false, "salad"));
		register(bread_salad = new MItemsFood(14, 0.3571F, false, "bread_salad"));
		register(blueberry_salad = new MItemBowlFood(10, 0.4F, false, "blueberry_salad").setPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 240, 1), 1F));
		register(bread_blueberry_salad = new MItemsFood(12, 0.375F, false, "bread_blueberry_salad").setPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 240, 1), 1F));
		register(blackberry_salad = new MItemBowlFood(11, 0.3182F, false, "blackberry_salad").setPotionEffect(new PotionEffect(MobEffects.HASTE, 240, 1), 1F));
		register(bread_blackberry_salad = new MItemsFood(13, 0.3077F, false, "bread_blackberry_salad").setPotionEffect(new PotionEffect(MobEffects.HASTE, 240, 1), 1F));
		register(raspberry_salad = new MItemBowlFood(10, 0.3F, false, "raspberry_salad").setPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 0), 1F));
		register(bread_raspberry_salad = new MItemsFood(12, 0.2917F, false, "bread_raspberry_salad").setPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 0), 1F));
		register(strawberry_salad = new MItemBowlFood(12, 0.2833F, false, "strawberry_salad").setPotionEffect(new PotionEffect(MobEffects.STRENGTH, 240, 1), 1F));
		register(bread_strawberry_salad = new MItemsFood(14, 0.2786F, false, "bread_strawberry_salad").setPotionEffect(new PotionEffect(MobEffects.STRENGTH, 240, 1), 1F));
		register(voidberry_salad = new MItemBowlFood(11, 0.2864F, false, "voidberry_salad").setPotionEffect(new PotionEffect(MobEffects.LEVITATION, 240, 1), 1F));
		register(bread_voidberry_salad = new MItemsFood(13, 0.2808F, false, "bread_voidberry_salad").setPotionEffect(new PotionEffect(MobEffects.LEVITATION, 240, 1), 1F));
		
		register(jam = new ItemJamBottle(7, 0.2714F, false, "jam", new ItemStack(Items.GLASS_BOTTLE), true).setAlwaysEdible());
		register(void_jam = new MItemBowlFood(8, 0.075F, false, "void_jam", new ItemStack(Items.GLASS_BOTTLE), true).setAlwaysEdible().setPotionEffect(new PotionEffect(MobEffects.LEVITATION, 440), 0.6F));
		register(peanut_butter = new MItemBowlFood(6, 1.1667F, false, "peanut_butter", new ItemStack(Items.GLASS_BOTTLE), true));
		register(hot_sauce = new MItemBowlFood(4, 0.35F, false, "hot_sauce", new ItemStack(Items.GLASS_BOTTLE), true).setIgnitesPlayer(10).setAlwaysEdible().setPotionEffect(new PotionEffect(MobEffects.SPEED, 600, 2), 1F));
		
		register(fat = new MItemsFood(1, 2.5F, true, "fat").setBurnTime(1200).setPotionEffect(new PotionEffect(MobEffects.HUNGER, 300, 0), 0.55F));
		register(grease = new ItemBase("grease").setBurnTime(2000).setCreativeTab(MTabs.food));
		register(squid_tentacle = new MItemsFood(2, 0.075F, false, "squid_tentacle").setPotionEffect(new PotionEffect(MobEffects.HUNGER, 240, 0), 0.4F));
		register(calamari = new MItemsFood(5, 0.78F, false, "calamari"));
		register(sushi = new MItemsFood(11, 0.7273F, false, "sushi"));
		register(lucky_sushi = new MItemsFood(9, 0.7778F, false, "lucky_sushi").setPotionEffect(new PotionEffect(MobEffects.LUCK, 320, 0), 1F));
		register(onigiri = new MItemsFood(5, 0.43F, false, "onigiri"));
		register(flesh = new MItemsFood(4, 0.2F, true, "flesh").setPotionEffect(new PotionEffect(MobEffects.HUNGER, 160, 0), 0.2F));
		
		register(fries = new MItemsFood(4, 1.125F, false, "fries"));
		register(fried_fish = new MItemsFood(8, 0.5625F, false, "fried_fish"));
		register(fried_salmon = new MItemsFood(8, 0.9F, false, "fried_salmon"));
		register(fish_and_chips = new MItemsFood(12, 0.75F, false, "fish_and_chips"));
		
		register(candy_red = new ItemCandy("candy_red", new PotionEffect(MobEffects.HEALTH_BOOST, 600, 1), new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 1), new PotionEffect(MobEffects.REGENERATION, 600, 0)).setAlwaysEdible());
		register(candy_blue = new ItemCandy("candy_blue", new PotionEffect(MobEffects.STRENGTH, 600, 0), new PotionEffect(MobEffects.INVISIBILITY, 600, 0), new PotionEffect(MobEffects.NIGHT_VISION, 600, 0)).setAlwaysEdible());
		register(candy_yellow = new ItemCandy("candy_yellow", new PotionEffect(MobEffects.SPEED, 600, 1), new PotionEffect(MobEffects.LEVITATION, 600, 1), new PotionEffect(MobEffects.JUMP_BOOST, 700, 1)).setAlwaysEdible());
		
		//Add Item drops for M5 Blocks that drop M5 Items because registry ordering is stupid.
		((BlockStoneBase) MBlocks.ore_meurodite).setDropsItem(new ItemStack(gems, 1, 4), 0, 1, 5, true, true, false);
		((BlockStoneBase) MBlocks.ore_irradium).setDropsItem(new ItemStack(irradium), 1, 3, 6, true, true, false);
		((BlockBase) MBlocks.ore_blazium).setDropsItem(new ItemStack(gems, 2, 5), 2, 2, 7, true, true);
		((BlockBase) MBlocks.ore_soul).setDropsItem(new ItemStack(gem_soul), 0, 5, 9, true, true);
		((BlockBase) MBlocks.bauble_sunstone).setDropsItem(new ItemStack(gems, 1, 0), 0, 0, 0, true, false);
		((BlockBase) MBlocks.block_sunstone).setDropsItem(new ItemStack(gems, 2, 0), 2, 0, 0, true, false);
		((BlockBerryBush) MBlocks.blueberry_bush).setBushDrop(new ItemStack(blueberry));
		((BlockBerryBush) MBlocks.blackberry_bush).setBushDrop(new ItemStack(blackberry));
		((BlockBerryBush) MBlocks.raspberry_bush).setBushDrop(new ItemStack(raspberry));
		((BlockBerryBush) MBlocks.strawberry_bush).setBushDrop(new ItemStack(strawberry));
		((BlockBerryBush) MBlocks.mana_bush).setBushDrop(new ItemStack(natural_ingredients, 1, 1));
		
		((MItemBlock) Item.getItemFromBlock(MBlocks.barrel)).setBurnTime(300);
		((MItemBlock) Item.getItemFromBlock(MBlocks.basket)).setBurnTime(100);
		((MItemBlock) Item.getItemFromBlock(MBlocks.blackberry_bush)).setBurnTime(100);
		((MItemBlock) Item.getItemFromBlock(MBlocks.blueberry_bush)).setBurnTime(100);
		((MItemBlock) Item.getItemFromBlock(MBlocks.raspberry_bush)).setBurnTime(100);
		((MItemBlock) Item.getItemFromBlock(MBlocks.strawberry_bush)).setBurnTime(100);
		((MItemBlock) Item.getItemFromBlock(MBlocks.mana_bush)).setBurnTime(100);
		((MItemBlock) Item.getItemFromBlock(MBlocks.block_blazium)).setBurnTime(48000);
		((MItemBlock) Item.getItemFromBlock(MBlocks.block_irradium)).setBurnTime(256000);
		((MItemBlock) Item.getItemFromBlock(MBlocks.moss)).setBurnTime(800);
		((MItemBlock) Item.getItemFromBlock(MBlocks.savanna_grass)).setBurnTime(50);
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
		ForgeRegistries.ITEMS.register(item);
		itemList.add(item);
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
