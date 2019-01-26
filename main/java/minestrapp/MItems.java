package minestrapp;

import java.util.ArrayList;
import java.util.List;

import minestrapp.block.BlockMDoor;
import minestrapp.block.crops.BlockBerryBush;
import minestrapp.block.item.MItemBlock;
import minestrapp.block.util.BlockBase;
import minestrapp.block.util.BlockStoneBase;
import minestrapp.entity.vehicle.EntityMBoat;
import minestrapp.item.ItemBackpack;
import minestrapp.item.ItemCandy;
import minestrapp.item.ItemCharroot;
import minestrapp.item.ItemDrySpaghetti;
import minestrapp.item.ItemGlowshroomStew;
import minestrapp.item.ItemHangGlider;
import minestrapp.item.ItemHeartContainer;
import minestrapp.item.ItemJamBottle;
import minestrapp.item.ItemMBoat;
import minestrapp.item.ItemMDoor;
import minestrapp.item.ItemPBJ;
import minestrapp.item.ItemSieve;
import minestrapp.item.ItemSmellingSalts;
import minestrapp.item.ItemSoulGem;
import minestrapp.item.ItemTannic;
import minestrapp.item.MItemHealthCrystal;
import minestrapp.item.armor.MArmor;
import minestrapp.item.tools.MAxe;
import minestrapp.item.tools.MDagger;
import minestrapp.item.tools.MHoe;
import minestrapp.item.tools.MMace;
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
import net.minecraft.creativetab.CreativeTabs;
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
	
	public static Item door_charwood;
	
	public static Item natural_ingredients;
	public static Item sawdust;
	public static Item mob_loot;
	public static Item effervexcense;
	public static Item leather;
	public static Item tannic;
	
	public static Item mud_ball;
	public static Item chunks;
	public static Item irradium;
	public static Item nuggets;
	public static Item ingots;
	public static Item plating;
	public static Item salt;
	public static Item gems;
	public static Item gem_soul;
	
	public static Item bricks;
	public static Item tech_components;
	public static Item glow_paste;
	public static Item heart_piece;
	public static Item heart_container;
	
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
	public static Item fire_pickaxe;
	public static Item fire_axe;
	public static Item fire_shovel;
	public static Item fire_hoe;
	public static Item ice_pickaxe;
	public static Item ice_axe;
	public static Item ice_shovel;
	public static Item ice_hoe;
	public static Item bedrock_pickaxe;
	public static Item bedrock_axe;
	public static Item bedrock_shovel;
	public static Item bedrock_hoe;
	
	public static Item gold_dagger;
	public static Item gold_mace;
	public static Item wooden_dagger;
	public static Item wooden_mace;
	public static Item stone_dagger;
	public static Item stone_mace;
	public static Item copper_sword;
	public static Item copper_dagger;
	public static Item copper_mace;
	public static Item bronze_sword;
	public static Item bronze_dagger;
	public static Item bronze_mace;
	public static Item steel_sword;
	public static Item steel_dagger;
	public static Item steel_mace;
	public static Item meurodite_sword;
	public static Item meurodite_dagger;
	public static Item meurodite_mace;
	public static Item torite_sword;
	public static Item torite_dagger;
	public static Item torite_mace;
	public static Item iron_dagger;
	public static Item iron_mace;
	public static Item diamond_dagger;
	public static Item diamond_mace;
	public static Item titanium_sword;
	public static Item titanium_dagger;
	public static Item titanium_mace;
	public static Item fire_sword;
	public static Item fire_dagger;
	public static Item fire_mace;
	public static Item ice_sword;
	public static Item ice_dagger;
	public static Item ice_mace;
	
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
	public static Item fire_helm;
	public static Item fire_chest;
	public static Item fire_legs;
	public static Item fire_feet;
	public static Item ice_helm;
	public static Item ice_chest;
	public static Item ice_legs;
	public static Item ice_feet;
	
	public static Item smelling_salts;
	public static Item health_crystal;
	public static Item boat_redwood;
	public static Item boat_frozen_oak;
	public static Item boat_charwood;
	public static Item sieve_copper;
	public static Item sieve_iron;
	public static Item sieve_bronze;
	public static Item sieve_archantine;
	public static Item sieve_archantine_broken;
	public static Item sieve_adamantium;
	public static Item hang_glider_wood;
	public static Item hang_glider_steel;

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
	public static Item charroot;
	
	public static Item corn_meal;
	public static Item corn_bread;
	public static Item dough;
	public static Item sugar_cookie;
	public static Item bun;
	public static Item pbj;
	public static Item blt;
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
	public static Item bread_ice_cream;
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
	public static Item coleslaw;
	public static Item bread_coleslaw;
	public static Item voidberry_salad;
	public static Item bread_voidberry_salad;
	
	public static Item melonade;
	public static Item jam;
	public static Item void_jam;
	public static Item peanut_butter;
	public static Item hot_sauce;
	public static Item mite_honey;
	
	public static Item fat;
	public static Item grease;
	public static Item salted_chicken;
	public static Item salted_rabbit;
	public static Item salted_mutton;
	public static Item salted_steak;
	public static Item salted_porkchop;
	public static Item chicken_jerky;
	public static Item rabbit_jerky;
	public static Item mutton_jerky;
	public static Item beef_jerky;
	public static Item pork_jerky;
	public static Item cod_jerky;
	public static Item salmon_jerky;
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
	public static Item bacon;
	
	public static Item candy_red;
	public static Item candy_blue;
	public static Item candy_yellow;
	
	public static Item backpack;
	public static Item satchel;
	
	public static final ToolMaterial COPPER = EnumHelper.addToolMaterial(Minestrapp5.MODID + ":copper", 1, 200, 5F, 1.5F, 17);
	public static final ToolMaterial BRONZE = EnumHelper.addToolMaterial(Minestrapp5.MODID + ":bronze", 2, 1000, 5F, 1.5F, 13);
	public static final ToolMaterial STEEL = EnumHelper.addToolMaterial(Minestrapp5.MODID + ":steel", 2, 500, 7.5F, 2.5F, 19);
	public static final ToolMaterial MEURODITE = EnumHelper.addToolMaterial(Minestrapp5.MODID + ":meurodite", 2, 906, 7F, 2.5F, 16);
	public static final ToolMaterial TORITE = EnumHelper.addToolMaterial(Minestrapp5.MODID + ":torite", 2, 1200, 7F, 3.5F, 30);
	public static final ToolMaterial TITANIUM = EnumHelper.addToolMaterial(Minestrapp5.MODID + ":titanium", 4, 3122, 16F, 6F, 5);
	public static final ToolMaterial BLAZIUM = EnumHelper.addToolMaterial(Minestrapp5.MODID + ":blazium", 2, 960, 7.5F, 3F, 22);
	public static final ToolMaterial GLACIERITE = EnumHelper.addToolMaterial(Minestrapp5.MODID + ":glacierite", 3, 1400, 8F, 4F, 34);
	public static final ToolMaterial BEDROCK = EnumHelper.addToolMaterial(Minestrapp5.MODID + ":bedrock", 4, 6244, 6F, 1.5F, 6);
	
	public static final ArmorMaterial ARMOR_TIN = EnumHelper.addArmorMaterial("tin", Minestrapp5.MODID + ":tin", 4, new int[]{1, 3, 4, 1}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F);
	public static final ArmorMaterial ARMOR_BRONZE = EnumHelper.addArmorMaterial("bronze", Minestrapp5.MODID + ":bronze", 28, new int[]{2, 4, 5, 2}, 8, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
	public static final ArmorMaterial ARMOR_STEEL = EnumHelper.addArmorMaterial("steel", Minestrapp5.MODID + ":steel", 18, new int[]{3, 6, 7, 3}, 18, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
	public static final ArmorMaterial ARMOR_MEURODITE = EnumHelper.addArmorMaterial("meurodite", Minestrapp5.MODID + ":meurodite", 24, new int[]{2, 6, 7, 3}, 12, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0F);
	public static final ArmorMaterial ARMOR_TORITE = EnumHelper.addArmorMaterial("torite", Minestrapp5.MODID + ":torite", 26, new int[]{2, 6, 7, 2}, 30, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 1.5F);
	public static final ArmorMaterial ARMOR_TITANIUM = EnumHelper.addArmorMaterial("titanium", Minestrapp5.MODID + ":titanium", 66, new int[]{3, 6, 8, 3}, 5, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 8.0F);
	public static final ArmorMaterial ARMOR_BLAZIUM = EnumHelper.addArmorMaterial("blazium", Minestrapp5.MODID + ":blazium", 25, new int[]{2, 5, 6, 3}, 23, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 1.2F);
	public static final ArmorMaterial ARMOR_GLACIERITE = EnumHelper.addArmorMaterial("glacierite", Minestrapp5.MODID + ":glacierite", 28, new int[]{2, 6, 7, 2}, 34, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 2F);
	
	public static void init()
	{
		register(door_charwood = new ItemMDoor(MBlocks.door_charwood).setCreativeTab(MTabs.utility));
		
		//0=Grass Fibers, 1=Mana Leaf, 2=Clutchthorn Fibers
		register(natural_ingredients = new ItemMetaBase("m_natural_item", 3).setBurnTime(100, 0).setCreativeTab(MTabs.ingredients));
		register(sawdust = new ItemBase("sawdust").setBurnTime(50).setCreativeTab(MTabs.ingredients));
		//0=Animal Bones, 1=Tallow, 2=Wing Sinew, 3=Horse Hide, 4=Pig Hide, 5=Wolf Hide, 6=Polar Bear Hide, 7=Sheep Hoof, 8=Spider Leg
		register(mob_loot = new ItemMetaBase("m_mob_loot", 9).setCreativeTab(MTabs.ingredients));
		register(effervexcense = new ItemBase("effervexcense").setCreativeTab(MTabs.ingredients));
		//0=Cured Rabbit Hide, 1=Cured Cow Hide, 2=Cured Horse Hide, 3=Cured Pig Hide, 4=Cured Wolf Hide, 5=Cured Flesh, 6=Cured Rotten Flesh, 7=Poor Scudded Hide, 8=Small Scudded Hide, 9=Scudded Hide, 10=Large Scudded Hide, 11=Botched Leather, 12=Suede, 13=Leather, 14=Fine Leather, 15=Cured Polar Bear Hide
		register(leather = new ItemMetaBase("m_leather", 16).setCreativeTab(MTabs.ingredients));
		//0=Poor Tannic, 1=Tannic, 2=Fine Tannic 3=Tanning Solution
		register(tannic = new ItemTannic().setAlwaysEdible().setCreativeTab(MTabs.ingredients));
		
		//TODO: Add projectile effect.
		register(mud_ball = new ItemBase("mud_ball").setCreativeTab(MTabs.minerals));
		//0=Red Rock, 1=Stone, 2=Coldstone, 3=Icestone, 4=Oceanstone, 5=Netherrack, 6=Endstone, 7=Copper, 8=Tin, 9=Iron, 10=Gold, 11=Torite, 12=Titanium, 13=Dimensium 14=Shimmering
		register(chunks = new ItemMetaBase("m_chunks", 15).setCreativeTab(MTabs.minerals));
		register(irradium = new ItemBase("irradium").setBurnTime(25600).setCreativeTab(MTabs.minerals));
		//0=Copper, 1=Tin, 2=Bronze, 3=Steel, 4=Torite, 5=Glacierite, 6=Blazium, 7=Archantine, 8=Dimensium
		register(nuggets = new ItemMetaBase("m_nugget", 9).setBurnTime(480, 6).setCreativeTab(MTabs.minerals));
		//0=Copper, 1=Tin, 2=Bronze, 3=Steel, 4=Torite, 5=Archantine, 6=Glacierite, 7=Blazium, 8=Dimensium
		register(ingots = new ItemMetaBase("m_ingot", 9).setBurnTime(4800, 7).setBeaconPayment().setCreativeTab(MTabs.minerals));
		//0=Tin, 1=Bronze, 2=Steel, 3=Meurodite, 4=Copper, 5=Gold, 6=Iron, 7=Torite, 8=Glacierite, 9=Blazium, 10=Diamond, 11=Archantine, 12=Dimensium
		register(plating = new ItemMetaBase("m_plating", 13).setCreativeTab(MTabs.ingredients));
		register(salt = new ItemBase("salt").setCreativeTab(MTabs.food));
		//We, are the crystal MItems.gems
		//0=Sunstone, 1=Desert Quartz, 2=Rock Crystal, 3=Radiant Quartz, 4=Meurodite, 5=Blaze Shard, 6=Glacieric Ice Shard
		register(gems = new ItemMetaBase("m_gem", 7).setBurnTime(2000, 5).setCreativeTab(MTabs.minerals));
		register(gem_soul = new ItemSoulGem("gem_soul").setBeaconPayment());
		
		//0=Mud Brick, 1=Portar
		register(bricks = new ItemMetaBase("m_bricks", 2).setCreativeTab(MTabs.ingredients));
		//0=Reinforced Stick, 1=Wing Segment, 2=Propeller, 3=Inert Chip, 4=Technological Doodad, 5=Adv. Technological Doodad, 6=Magnet, 7=Grass Weave
		register(tech_components = new ItemMetaBase("m_tech_component", 8).setCreativeTab(MTabs.ingredients));
		register(heart_piece = new ItemBase("heart_piece").setCreativeTab(MTabs.ingredients));
		register(heart_container = new ItemHeartContainer());
		
		register(smelling_salts = new ItemSmellingSalts());
		register(health_crystal = new MItemHealthCrystal("health_crystal"));
		register(boat_redwood = new ItemMBoat(EntityMBoat.Type.REDWOOD));
		register(boat_frozen_oak = new ItemMBoat(EntityMBoat.Type.FROZEN_OAK));
		register(boat_charwood = new ItemMBoat(EntityMBoat.Type.CHARWOOD));
		register(sieve_copper = new ItemSieve("copper_sieve", 32, new ItemStack(ingots, 1, 0)));
		register(sieve_iron = new ItemSieve("iron_sieve", 64, new ItemStack(Items.IRON_INGOT)));
		register(sieve_bronze = new ItemSieve("bronze_sieve", 128, new ItemStack(ingots, 1, 2)));
		register(sieve_archantine_broken = new ItemBase("archantine_sieve_broken").setCreativeTab(MTabs.tools));
		register(sieve_archantine = new ItemSieve("archantine_sieve", 512, new ItemStack(ingots, 1, 5), new ItemStack(sieve_archantine_broken)));
		register(sieve_adamantium = new ItemSieve("adamantium_sieve").setCreativeTab(MTabs.tools));
		register(hang_glider_wood = new ItemHangGlider("hang_glider_wood", 150, 0.75D, 0.03D, 1D, 1.05D).setCreativeTab(MTabs.tools));
		register(hang_glider_steel = new ItemHangGlider("hang_glider_steel", 250, 0.65D, 0.015D, 1.06D, 1.115D).setCreativeTab(MTabs.tools));

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
		register(fire_pickaxe = new MPickaxe(BLAZIUM, "fire_pickaxe"));
		register(fire_axe = new MAxe(BLAZIUM, "fire_axe", 9.0F, -3.2F));
		register(fire_shovel = new MShovel(BLAZIUM, "fire_shovel"));
		register(fire_hoe = new MHoe(BLAZIUM, "fire_hoe"));
		register(ice_pickaxe = new MPickaxe(GLACIERITE, "ice_pickaxe"));
		register(ice_axe = new MAxe(GLACIERITE, "ice_axe", 11.0F, -3.4F));
		register(ice_shovel = new MShovel(GLACIERITE, "ice_shovel"));
		register(ice_hoe = new MHoe(GLACIERITE, "ice_hoe"));
		register(bedrock_pickaxe = new MPickaxe(BEDROCK, "bedrock_pickaxe"));
		register(bedrock_axe = new MAxe(BEDROCK, "bedrock_axe", 8.0F, -3.15F));
		register(bedrock_shovel = new MShovel(BEDROCK, "bedrock_shovel"));
		register(bedrock_hoe = new MHoe(BEDROCK, "bedrock_hoe"));
		
		register(gold_dagger = new MDagger(Item.ToolMaterial.GOLD, "gold_dagger"));
		register(gold_mace = new MMace(Item.ToolMaterial.GOLD, "gold_mace"));
		register(wooden_dagger = new MDagger(Item.ToolMaterial.WOOD, "wooden_dagger").setBurnTime(200));
		register(wooden_mace = new MMace(Item.ToolMaterial.WOOD, "wooden_mace").setBurnTime(200));
		register(stone_dagger = new MDagger(Item.ToolMaterial.STONE, "stone_dagger"));
		register(stone_mace = new MMace(Item.ToolMaterial.STONE, "stone_mace"));
		register(copper_sword = new MSword(COPPER, "copper_sword"));
		register(copper_dagger = new MDagger(COPPER, "copper_dagger"));
		register(copper_mace = new MMace(COPPER, "copper_mace"));
		register(iron_dagger = new MDagger(Item.ToolMaterial.IRON, "iron_dagger"));
		register(iron_mace = new MMace(Item.ToolMaterial.IRON, "iron_mace"));
		register(bronze_sword = new MSword(BRONZE, "bronze_sword"));
		register(bronze_dagger = new MDagger(BRONZE, "bronze_dagger"));
		register(bronze_mace = new MMace(BRONZE, "bronze_mace"));
		register(steel_sword = new MSword(STEEL, "steel_sword"));
		register(steel_dagger = new MDagger(STEEL, "steel_dagger"));
		register(steel_mace = new MMace(STEEL, "steel_mace"));
		register(meurodite_sword = new MSword(MEURODITE, "meurodite_sword"));
		register(meurodite_dagger = new MDagger(MEURODITE, "meurodite_dagger"));
		register(meurodite_mace = new MMace(MEURODITE, "meurodite_mace"));
		register(torite_sword = new MSword(TORITE, "torite_sword"));
		register(torite_dagger = new MDagger(TORITE, "torite_dagger"));
		register(torite_mace = new MMace(TORITE, "torite_mace"));
		register(diamond_dagger = new MDagger(Item.ToolMaterial.DIAMOND, "diamond_dagger"));
		register(diamond_mace = new MMace(Item.ToolMaterial.DIAMOND, "diamond_mace"));
		register(titanium_sword = new MSword(TITANIUM, "titanium_sword"));
		register(titanium_dagger = new MDagger(TITANIUM, "titanium_dagger"));
		register(titanium_mace = new MMace(TITANIUM, "titanium_mace"));
		register(fire_sword = new MSword(BLAZIUM, "fire_sword"));
		register(fire_dagger = new MDagger(BLAZIUM, "fire_dagger"));
		register(fire_mace = new MMace(BLAZIUM, "fire_mace"));
		register(ice_sword = new MSword(GLACIERITE, "ice_sword"));
		register(ice_dagger = new MDagger(GLACIERITE, "ice_dagger"));
		register(ice_mace = new MMace(GLACIERITE, "ice_mace"));
		
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
		register(fire_helm = new MArmor(ARMOR_BLAZIUM, 1, EntityEquipmentSlot.HEAD, "fire_helm"));
		register(fire_chest = new MArmor(ARMOR_BLAZIUM, 1, EntityEquipmentSlot.CHEST, "fire_chest"));
		register(fire_legs = new MArmor(ARMOR_BLAZIUM, 2, EntityEquipmentSlot.LEGS, "fire_legs"));
		register(fire_feet = new MArmor(ARMOR_BLAZIUM, 1, EntityEquipmentSlot.FEET, "fire_feet"));
		register(ice_helm = new MArmor(ARMOR_GLACIERITE, 1, EntityEquipmentSlot.HEAD, "ice_helm"));
		register(ice_chest = new MArmor(ARMOR_GLACIERITE, 1, EntityEquipmentSlot.CHEST, "ice_chest"));
		register(ice_legs = new MArmor(ARMOR_GLACIERITE, 2, EntityEquipmentSlot.LEGS, "ice_legs"));
		register(ice_feet = new MArmor(ARMOR_GLACIERITE, 1, EntityEquipmentSlot.FEET, "ice_feet"));
		
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
		register(charroot = new ItemCharroot().setBurnTime(200).setPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 100), 1F));
		
		register(corn_meal = new MItemsFood(1, 0.1F, false, "corn_meal"));
		register(corn_bread = new MItemsFood(4, 1F, false, "corn_bread"));
		register(dough = new MItemsFood(1, 0.15F, false, "dough"));
		register(sugar_cookie = new MItemsFood(1, 0.4F, false, "sugar_cookie"));
		register(bun = new MItemsFood(2, 0.75F, false, "bun"));
		register(pbj = new ItemPBJ(12, 1.5F, false, "pbj"));
		register(blt = new MItemsFood(18, 0.6667F, false, "blt"));
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
		register(ice_cream = new MItemBowlFood(10, 0.15F, false, "ice_cream").setIgnitesPlayer(-1).setCuresEffects());
		register(bread_ice_cream = new MItemsFood(12, 0.1667F, false, "bread_ice_cream").setIgnitesPlayer(-1).setCuresEffects());
		register(glowshroom_stew = new ItemGlowshroomStew(6, 0.6F, false, "glowshroom_stew", false).setContainerItem(Items.BOWL));
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
		register(coleslaw = new MItemBowlFood(11, 0.4091F, false, "coleslaw").setCuresEffects());
		register(bread_coleslaw = new MItemsFood(12, 0.4167F, false, "bread_coleslaw").setCuresEffects());
		register(voidberry_salad = new MItemBowlFood(11, 0.2864F, false, "voidberry_salad").setPotionEffect(new PotionEffect(MobEffects.LEVITATION, 240, 1), 1F));
		register(bread_voidberry_salad = new MItemsFood(13, 0.2808F, false, "bread_voidberry_salad").setPotionEffect(new PotionEffect(MobEffects.LEVITATION, 240, 1), 1F));
		
		register(melonade = new MItemBowlFood(6, 0.3F, false, "melonade", new ItemStack(Items.GLASS_BOTTLE), true).setIgnitesPlayer(-1).setAlwaysEdible());
		register(jam = new ItemJamBottle(7, 0.2714F, false, "jam", new ItemStack(Items.GLASS_BOTTLE), true).setAlwaysEdible());
		register(void_jam = new MItemBowlFood(8, 0.075F, false, "void_jam", new ItemStack(Items.GLASS_BOTTLE), true).setAlwaysEdible().setPotionEffect(new PotionEffect(MobEffects.LEVITATION, 440), 0.8F));
		register(peanut_butter = new MItemBowlFood(6, 1.1667F, false, "peanut_butter", new ItemStack(Items.GLASS_BOTTLE), true));
		register(hot_sauce = new MItemBowlFood(4, 0.35F, false, "hot_sauce", new ItemStack(Items.GLASS_BOTTLE), true).setIgnitesPlayer(10).setAlwaysEdible().setPotionEffect(new PotionEffect(MobEffects.SPEED, 600, 2), 1F));
		register(mite_honey = new MItemsFood(2, 0.6F, false, "mite_honey").setBurnTime(1200).setPotionEffect(new PotionEffect(MobEffects.GLOWING, 300, 0), 1F));
		
		register(fat = new MItemsFood(1, 2.5F, true, "fat").setBurnTime(1200).setPotionEffect(new PotionEffect(MobEffects.HUNGER, 300, 0), 0.55F));
		register(grease = new ItemBase("grease").setBurnTime(2000).setCreativeTab(MTabs.food));
		register(salted_chicken = new MItemsFood(8, 0.575F, true, "salted_chicken"));
		register(salted_mutton = new MItemsFood(8, 0.725F, true, "salted_mutton"));
		register(salted_rabbit = new MItemsFood(7, 0.5714F, true, "salted_rabbit"));
		register(salted_steak = new MItemsFood(10, 0.74F, true, "salted_steak"));
		register(salted_porkchop = new MItemsFood(10, 0.74F, true, "salted_porkchop"));
		
		//Jerky: hunger = cooked food hunger value - 2, sat. = 2 x cooked food effective sat. value.
		register(chicken_jerky = new MItemsFood(4, 1.8F, true, "chicken_jerky"));
		register(rabbit_jerky = new MItemsFood(3, 2F, true, "rabbit_jerky"));
		register(mutton_jerky = new MItemsFood(4, 2.4F, true, "mutton_jerky"));
		register(beef_jerky = new MItemsFood(6, 2.13F, true, "beef_jerky"));
		register(pork_jerky = new MItemsFood(6, 2.13F, true, "pork_jerky"));
		register(cod_jerky = new MItemsFood(3, 2F, true, "cod_jerky"));
		register(salmon_jerky = new MItemsFood(4, 2.4F, true, "salmon_jerky"));
		
		register(squid_tentacle = new MItemsFood(2, 0.075F, false, "squid_tentacle").setPotionEffect(new PotionEffect(MobEffects.HUNGER, 240, 0), 0.4F));
		register(calamari = new MItemsFood(5, 0.78F, false, "calamari"));
		register(sushi = new MItemsFood(11, 0.7273F, false, "sushi"));
		register(lucky_sushi = new MItemsFood(9, 0.7778F, false, "lucky_sushi").setPotionEffect(new PotionEffect(MobEffects.LUCK, 320, 0), 1F));
		register(onigiri = new MItemsFood(5, 0.43F, false, "onigiri"));
		register(flesh = new MItemsFood(4, 0.2F, true, "flesh").setPotionEffect(new PotionEffect(MobEffects.HUNGER, 160, 0), 0.2F));
		
		//Fried Foods: hunger = 4 x raw food hunger value, sat. = 1.5 x cooked food effective sat. value.
		register(fries = new MItemsFood(4, 1.125F, false, "fries"));
		register(fried_fish = new MItemsFood(8, 0.5625F, false, "fried_fish"));
		register(fried_salmon = new MItemsFood(8, 0.9F, false, "fried_salmon"));
		register(fish_and_chips = new MItemsFood(12, 0.75F, false, "fish_and_chips"));
		register(bacon = new MItemsFood(12, 0.8F, true, "bacon"));
		
		register(candy_red = new ItemCandy("candy_red", new PotionEffect(MobEffects.HEALTH_BOOST, 600, 1), new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 1), new PotionEffect(MobEffects.REGENERATION, 600, 0)).setAlwaysEdible());
		register(candy_blue = new ItemCandy("candy_blue", new PotionEffect(MobEffects.STRENGTH, 600, 0), new PotionEffect(MobEffects.INVISIBILITY, 600, 0), new PotionEffect(MobEffects.NIGHT_VISION, 600, 0)).setAlwaysEdible());
		register(candy_yellow = new ItemCandy("candy_yellow", new PotionEffect(MobEffects.SPEED, 600, 1), new PotionEffect(MobEffects.LEVITATION, 600, 1), new PotionEffect(MobEffects.JUMP_BOOST, 700, 1)).setAlwaysEdible());
		
		register(backpack = new ItemBackpack("backpack", 1));
		register(satchel = new ItemBackpack("satchel", 2));
		
		//Add Item drops for M5 Blocks that drop M5 Items because registry ordering is stupid.
		((BlockStoneBase) MBlocks.ore_salt).setDropsItem(new ItemStack(MItems.salt, 1), 1, 0, 2, true, true, false);
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
		((BlockMDoor) MBlocks.door_charwood).setDoorItem(door_charwood);
		MBlocks.glacieric_ice_branch_0.setDropsItem(new ItemStack(MItems.gems, 0, 6), 0, 0, 0, true, false);
		MBlocks.glacieric_ice_branch_1.setDropsItem(new ItemStack(MItems.gems, 0, 6), 1, 0, 0, true, false);
		MBlocks.glacieric_ice_branch_2.setDropsItem(new ItemStack(MItems.gems, 0, 6), 1, 0, 0, true, false);
		MBlocks.glacieric_ice_branch_3.setDropsItem(new ItemStack(MItems.gems, 0, 6), 1, 0, 0, true, false);
		MBlocks.glacieric_ice_branch_4.setDropsItem(new ItemStack(MItems.gems, 0, 6), 1, 0, 0, true, false);
		MBlocks.glacieric_ice_branch_5.setDropsItem(new ItemStack(MItems.gems, 1, 6), 0, 0, 0, true, false);
		MBlocks.glacieric_ice_branch_6.setDropsItem(new ItemStack(MItems.gems, 1, 6), 0, 0, 0, true, true);
		MBlocks.glacieric_ice_branch_7.setDropsItem(new ItemStack(MItems.gems, 1, 6), 1, 0, 0, true, true);
		
		((MItemBlock) Item.getItemFromBlock(MBlocks.barrel)).setBurnTime(300);
		((MItemBlock) Item.getItemFromBlock(MBlocks.plate_weave)).setBurnTime(100);
		((MItemBlock) Item.getItemFromBlock(MBlocks.basket)).setBurnTime(100);
		((MItemBlock) Item.getItemFromBlock(MBlocks.rope)).setBurnTime(100);
		((MItemBlock) Item.getItemFromBlock(MBlocks.tanning_rack)).setBurnTime(200);
		((MItemBlock) Item.getItemFromBlock(MBlocks.blackberry_bush)).setBurnTime(100);
		((MItemBlock) Item.getItemFromBlock(MBlocks.blueberry_bush)).setBurnTime(100);
		((MItemBlock) Item.getItemFromBlock(MBlocks.raspberry_bush)).setBurnTime(100);
		((MItemBlock) Item.getItemFromBlock(MBlocks.strawberry_bush)).setBurnTime(100);
		((MItemBlock) Item.getItemFromBlock(MBlocks.mana_bush)).setBurnTime(100);
		((MItemBlock) Item.getItemFromBlock(MBlocks.block_blazium)).setBurnTime(48000);
		((MItemBlock) Item.getItemFromBlock(MBlocks.block_irradium)).setBurnTime(256000);
		((MItemBlock) Item.getItemFromBlock(MBlocks.moss)).setBurnTime(800);
		((MItemBlock) Item.getItemFromBlock(MBlocks.savanna_grass)).setBurnTime(50);
		((MItemBlock) Item.getItemFromBlock(MBlocks.planks)).setBurnTime(300);
		((MItemBlock) Item.getItemFromBlock(MBlocks.log)).setBurnTime(300);
		((MItemBlock) Item.getItemFromBlock(MBlocks.mossy_m_planks)).setBurnTime(400);
		((MItemBlock) Item.getItemFromBlock(MBlocks.fence)).setBurnTime(300);
		((MItemBlock) Item.getItemFromBlock(MBlocks.wood_slab_1)).setBurnTime(300);
		((MItemBlock) Item.getItemFromBlock(MBlocks.oak_plank_panel)).setBurnTime(300);
		((MItemBlock) Item.getItemFromBlock(MBlocks.spruce_plank_panel)).setBurnTime(300);
		((MItemBlock) Item.getItemFromBlock(MBlocks.birch_plank_panel)).setBurnTime(300);
		((MItemBlock) Item.getItemFromBlock(MBlocks.jungle_plank_panel)).setBurnTime(300);
		((MItemBlock) Item.getItemFromBlock(MBlocks.acacia_plank_panel)).setBurnTime(300);
		((MItemBlock) Item.getItemFromBlock(MBlocks.dark_oak_plank_panel)).setBurnTime(300);
		((MItemBlock) Item.getItemFromBlock(MBlocks.redwood_plank_panel)).setBurnTime(300);
		((MItemBlock) Item.getItemFromBlock(MBlocks.frozen_oak_plank_panel)).setBurnTime(300);
		((MItemBlock) Item.getItemFromBlock(MBlocks.charwood_plank_panel)).setBurnTime(300);
		((MItemBlock) Item.getItemFromBlock(MBlocks.redwood_plank_stairs)).setBurnTime(300);
		((MItemBlock) Item.getItemFromBlock(MBlocks.frozen_oak_plank_stairs)).setBurnTime(300);
		((MItemBlock) Item.getItemFromBlock(MBlocks.charwood_plank_stairs)).setBurnTime(300);
		((MItemBlock) Item.getItemFromBlock(MBlocks.redwood_fence_gate)).setBurnTime(300);
		((MItemBlock) Item.getItemFromBlock(MBlocks.frozen_oak_fence_gate)).setBurnTime(300);
		((MItemBlock) Item.getItemFromBlock(MBlocks.charwood_fence_gate)).setBurnTime(300);
		((MItemBlock) Item.getItemFromBlock(MBlocks.block_mite_honey)).setBurnTime(12000);
		((MItemBlock) Item.getItemFromBlock(MBlocks.spike_oak_wood)).setBurnTime(300);
		((MItemBlock) Item.getItemFromBlock(MBlocks.spike_spruce_wood)).setBurnTime(300);
		((MItemBlock) Item.getItemFromBlock(MBlocks.spike_birch_wood)).setBurnTime(300);
		((MItemBlock) Item.getItemFromBlock(MBlocks.spike_jungle_wood)).setBurnTime(300);
		((MItemBlock) Item.getItemFromBlock(MBlocks.spike_acacia_wood)).setBurnTime(300);
		((MItemBlock) Item.getItemFromBlock(MBlocks.spike_dark_oak_wood)).setBurnTime(300);
		
		
		COPPER.setRepairItem(new ItemStack(ingots, 1, 0));
		BRONZE.setRepairItem(new ItemStack(ingots, 1, 2));
		STEEL.setRepairItem(new ItemStack(ingots, 1, 3));
		MEURODITE.setRepairItem(new ItemStack(gems, 1, 4));
		TORITE.setRepairItem(new ItemStack(ingots, 1, 4));
		TITANIUM.setRepairItem(new ItemStack(ingots, 1, 5));
		BLAZIUM.setRepairItem(new ItemStack(ingots, 1, 7));
		GLACIERITE.setRepairItem(new ItemStack(ingots, 1, 6));
		BEDROCK.setRepairItem(new ItemStack(Blocks.BEDROCK));
		
		ARMOR_TIN.setRepairItem(new ItemStack(ingots, 1, 1));
		ARMOR_BRONZE.setRepairItem(new ItemStack(ingots, 1, 2));
		ARMOR_STEEL.setRepairItem(new ItemStack(ingots, 1, 3));
		ARMOR_MEURODITE.setRepairItem(new ItemStack(gems, 1, 4));
		ARMOR_TORITE.setRepairItem(new ItemStack(ingots, 1, 4));
		ARMOR_TITANIUM.setRepairItem(new ItemStack(ingots, 1, 5));
		ARMOR_BLAZIUM.setRepairItem(new ItemStack(ingots, 1, 7));
		ARMOR_GLACIERITE.setRepairItem(new ItemStack(ingots, 1, 6));
		
		Items.WATER_BUCKET.setContainerItem(Items.BUCKET);
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
