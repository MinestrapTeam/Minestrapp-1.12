package minestrapp;

import java.util.ArrayList;
import java.util.List;

import minestrapp.block.*;
import minestrapp.block.crops.BlockBerryBush;
import minestrapp.block.crops.BlockVoidberryBush;
import minestrapp.block.crops.CabbagePlant;
import minestrapp.block.crops.CeleryPlant;
import minestrapp.block.crops.CornPlant;
import minestrapp.block.crops.CropWithered;
import minestrapp.block.crops.LettucePlant;
import minestrapp.block.crops.OnionPlant;
import minestrapp.block.crops.PeanutsPlant;
import minestrapp.block.crops.PepperPlant;
import minestrapp.block.crops.TomatoPlant;
import minestrapp.block.item.ItemBlockContainer;
import minestrapp.block.item.ItemBlockMSkull;
import minestrapp.block.item.ItemBlockMSlab;
import minestrapp.block.item.ItemBlockMultistate;
import minestrapp.block.item.ItemBlockPanel;
import minestrapp.block.item.MItemBlock;
import minestrapp.block.liquid.LiquidCrystalfloe;
import minestrapp.block.magnetpiston.BlockMagnetPistonBase;
import minestrapp.block.magnetpiston.BlockMagnetPistonExtension;
import minestrapp.block.magnetpiston.BlockMagnetPistonMoving;
import minestrapp.block.util.BlockBase;
import minestrapp.block.util.BlockBaseNonSolid;
import minestrapp.block.util.BlockPanelBase;
import minestrapp.block.util.BlockStairBase;
import minestrapp.block.util.BlockStoneBase;
import minestrapp.block.util.BlockStoneBaseMOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class MBlocks
{
	public static List<Block> blockList = new ArrayList<Block>();
	
	//Plant
	public static Block moss;
	public static Block blue_glowshroom;
	public static Block green_glowshroom;
	public static Block purple_glowshroom;
	public static Block blue_glowshroom_block;
	public static Block green_glowshroom_block;
	public static Block purple_glowshroom_block;
	public static Block tundra_grass;
	public static Block savanna_grass;
	public static Block blueberry_bush;
	public static Block blackberry_bush;
	public static Block raspberry_bush;
	public static Block strawberry_bush;
	public static Block mana_bush;
	public static Block carpet_glow_moss;
	public static Block creeping_glow_moss;
	public static Block hanging_glow_moss;
	public static Block clutchthorn;
	public static Block voidberry_bush;
	public static Block terracreep;
	public static Block melon_bricks;
	public static Block mite_hive;
	public static Block mite_hive_honeyed;
	public static Block mite_comb;
	public static Block infected_mushroom;
	public static Block leaves;
	public static Block palm_fronds;
	public static Block palm_fronds_dead;
	
	//Food
	public static BlockFoodSliceable block_cheese;
	public static BlockFoodSliceable quesadilla;
	public static BlockFoodSliceable pizza;
	
	//Soil
	public static Block cold_sand;
	public static BlockMDirt clay_soil;
	public static Block clay_grass;
	public static Block clay_grass_path;
	public static Block clay_farmland;
	public static BlockMDirt permafrost;
	public static Block lichen;
	public static Block lichen_path;
	public static Block permafrost_farmland;
	public static Block mud;
	public static Block dried_mud;
	public static BlockMDirt portal_dust;
	public static Block fargrowth;
	public static Block fargrowth_path;
	
	//Wood
	public static Block cork;
	public static Block log;
	public static Block palm_crown;
	public static Block palm_crown_dead;
	public static Block coconut;
	public static Block planks;
	public static Block mossy_m_planks;
	public static BlockSlab wood_slab_1;
	public static BlockDoubleWoodSlab1 double_wood_slab_1;
	public static BlockPanelBase oak_plank_panel;
	public static BlockPanelBase spruce_plank_panel;
	public static BlockPanelBase birch_plank_panel;
	public static BlockPanelBase jungle_plank_panel;
	public static BlockPanelBase acacia_plank_panel;
	public static BlockPanelBase dark_oak_plank_panel;
	public static BlockPanelBase redwood_plank_panel;
	public static BlockPanelBase frozen_oak_plank_panel;
	public static BlockPanelBase charwood_plank_panel;
	public static BlockPanelBase palm_plank_panel;
	public static Block redwood_plank_stairs;
	public static Block frozen_oak_plank_stairs;
	public static Block charwood_plank_stairs;
	public static Block palm_plank_stairs;
	public static Block fence;
	public static Block redwood_fence_gate;
	public static Block frozen_oak_fence_gate;
	public static Block charwood_fence_gate;
	public static Block palm_fence_gate;
	public static Block charwood_limb;
	
	//Stone
	public static Block mud_bricks;
	public static Block mud_brick_stairs;
	public static Block decor_stone;
	public static Block sandstone;
	public static Block desert_quartz;
	public static Block irradiant_quartz;
	public static Block stone;
	public static Block cobblestone;
	public static Block mossy_cobblestone;
	public static Block stone_bricks;
	public static Block mossy_stone_bricks;
	public static Block cracked_stone_bricks;
	public static Block chiseled_stone;
	public static Block shimmerstone;
	public static Block soul_glass;
	public static Block blazed_soul_glass;
	public static Block purpur;
	public static Block portar;
	public static Block gilded_stone;
	public static Block invincium;
	public static Block glacial_invincium;
	public static BlockSlab misc_stone_slab_1;
	public static BlockDoubleMiscStoneSlab1 double_misc_stone_slab_1;
	public static BlockSlab misc_stone_slab_2;
	public static BlockDoubleMiscStoneSlab2 double_misc_stone_slab_2;
	public static BlockSlab misc_stone_slab_3;
	public static BlockDoubleMiscStoneSlab3 double_misc_stone_slab_3;
	public static BlockSlab stone_slab_1;
	public static BlockDoubleStoneSlab1 double_stone_slab_1;
	public static BlockSlab stone_slab_2;
	public static BlockDoubleStoneSlab2 double_stone_slab_2;
	public static BlockSlab stone_slab_3;
	public static BlockDoubleStoneSlab3 double_stone_slab_3;
	public static BlockSlab stone_slab_4;
	public static BlockDoubleStoneSlab4 double_stone_slab_4;
	public static BlockPanelBase mud_brick_panel;
	public static BlockPanelBase granite_brick_panel;
	public static BlockPanelBase diorite_brick_panel;
	public static BlockPanelBase andesite_brick_panel;
	public static BlockPanelBase slate_brick_panel;
	public static BlockPanelBase clay_brick_panel;
	public static BlockPanelBase sandstone_panel;
	public static BlockPanelBase red_sandstone_panel;
	public static BlockPanelBase cold_sandstone_panel;
	public static BlockPanelBase cold_red_sandstone_panel;
	public static BlockPanelBase sandstone_brick_panel;
	public static BlockPanelBase red_sandstone_brick_panel;
	public static BlockPanelBase cold_sandstone_brick_panel;
	public static BlockPanelBase cold_red_sandstone_brick_panel;
	public static BlockPanelBase desert_quartz_panel;
	public static BlockPanelBase cobbled_red_rock_panel;
	public static BlockPanelBase cobbled_deep_red_rock_panel;
	public static BlockPanelBase cobblestone_panel;
	public static BlockPanelBase cobbled_deepstone_panel;
	public static BlockPanelBase cobbled_coldstone_panel;
	public static BlockPanelBase cobbled_deep_coldstone_panel;
	public static BlockPanelBase cobbled_icestone_panel;
	public static BlockPanelBase cobbled_glacierrock_panel;
	public static BlockPanelBase cobbled_oceanstone_panel;
	public static BlockPanelBase cobbled_deep_oceanstone_panel;
	public static BlockPanelBase red_rock_panel;
	public static BlockPanelBase deep_red_rock_panel;
	public static BlockPanelBase stone_panel;
	public static BlockPanelBase deepstone_panel;
	public static BlockPanelBase coldstone_panel;
	public static BlockPanelBase deep_coldstone_panel;
	public static BlockPanelBase icestone_panel;
	public static BlockPanelBase glacierrock_panel;
	public static BlockPanelBase oceanstone_panel;
	public static BlockPanelBase deep_oceanstone_panel;
	public static BlockPanelBase red_rock_brick_panel;
	public static BlockPanelBase deep_red_rock_brick_panel;
	public static BlockPanelBase stone_brick_panel;
	public static BlockPanelBase deepstone_brick_panel;
	public static BlockPanelBase coldstone_brick_panel;
	public static BlockPanelBase deep_coldstone_brick_panel;
	public static BlockPanelBase icestone_brick_panel;
	public static BlockPanelBase glacierrock_brick_panel;
	public static BlockPanelBase oceanstone_brick_panel;
	public static BlockPanelBase deep_oceanstone_brick_panel;
	public static BlockPanelBase irradiant_quartz_panel;
	public static BlockPanelBase nether_brick_panel;
	public static BlockPanelBase nether_quartz_panel;
	public static BlockPanelBase shimmerstone_panel;
	public static BlockPanelBase cobbled_shimmerstone_panel;
	public static BlockPanelBase shimmerstone_brick_panel;
	public static BlockPanelBase portar_panel;
	public static BlockPanelBase purpur_panel;
	public static BlockPanelBase gilded_brick_panel;
	public static Block granite_brick_stairs;
	public static Block diorite_brick_stairs;
	public static Block andesite_brick_stairs;
	public static Block slate_brick_stairs;
	public static Block cold_sandstone_stairs;
	public static Block cold_red_sandstone_stairs;
	public static Block sandstone_brick_stairs;
	public static Block red_sandstone_brick_stairs;
	public static Block cold_sandstone_brick_stairs;
	public static Block cold_red_sandstone_brick_stairs;
	public static Block desert_quartz_stairs;
	public static Block cobbled_red_rock_stairs;
	public static Block cobbled_deep_red_rock_stairs;
	public static Block cobbled_deepstone_stairs;
	public static Block cobbled_coldstone_stairs;
	public static Block cobbled_deep_coldstone_stairs;
	public static Block cobbled_icestone_stairs;
	public static Block cobbled_glacierrock_stairs;
	public static Block cobbled_oceanstone_stairs;
	public static Block cobbled_deep_oceanstone_stairs;
	public static Block red_rock_brick_stairs;
	public static Block deep_red_rock_brick_stairs;
	public static Block deepstone_brick_stairs;
	public static Block coldstone_brick_stairs;
	public static Block deep_coldstone_brick_stairs;
	public static Block icestone_brick_stairs;
	public static Block glacierrock_brick_stairs;
	public static Block oceanstone_brick_stairs;
	public static Block deep_oceanstone_brick_stairs;
	public static Block irradiant_quartz_stairs;
	public static Block cobbled_shimmerstone_stairs;
	public static Block shimmerstone_brick_stairs;
	public static Block portar_stairs;
	public static Block gilded_brick_stairs;
	public static Block silverfish_stone;
	public static Block silverfish_cobblestone;
	public static Block silverfish_stone_bricks;
	public static Block silverfish_mossy_stone_bricks;
	public static Block silverfish_cracked_stone_bricks;
	public static Block silverfish_chiseled_stone;
	
	//Ore
	public static Block desert_quartz_deposit;
	public static Block ore_salt;
	public static Block ore_coal;
	public static Block ore_copper;
	public static Block ore_tin;
	public static Block ore_iron;
	public static Block ore_gold;
	public static Block ore_meurodite;
	public static Block ore_redstone;
	public static Block ore_redstone_lit;
	public static Block ore_lapis;
	public static Block ore_irradium;
	public static Block rock_crystal_deposit;
	public static Block sunstone_deposit;
	public static Block glacieric_ice_deposit;
	public static BlockGlaciericIceBranch glacieric_ice_branch_0;
	public static BlockGlaciericIceBranch glacieric_ice_branch_1;
	public static BlockGlaciericIceBranch glacieric_ice_branch_2;
	public static BlockGlaciericIceBranch glacieric_ice_branch_3;
	public static BlockGlaciericIceBranch glacieric_ice_branch_4;
	public static BlockGlaciericIceBranch glacieric_ice_branch_5;
	public static BlockGlaciericIceBranch glacieric_ice_branch_6;
	public static BlockGlaciericIceBranch glacieric_ice_branch_7;
	public static Block soul_eyes;
	public static Block ore_torite;
	public static Block ore_diamond;
	public static Block ore_emerald;
	public static Block ore_titanium;
	public static Block ore_blazium;
	public static Block ore_shimmering;
	public static Block ore_soul;
	public static Block ore_dimensium;
	public static Block geode_shimmerstone_clear;
	public static Block geode_shimmerstone_dark;
	public static Block heart_spot;
	public static Block adamantium_nugget;
	
	//Resource
	public static Block block_salt;
	public static Block block_rock_crystal;
	public static Block block_copper;
	public static Block block_tin;
	public static Block block_bronze;
	public static Block block_steel;
	public static Block block_meurodite;
	public static Block block_redstone_sandy_unlit;
	public static Block block_redstone_sandy_lit;
	public static Block block_redstone_frosted_unlit;
	public static Block block_redstone_frosted_lit;
	public static Block block_redstone_icy_unlit;
	public static Block block_redstone_icy_lit;
	public static Block block_redstone_briny_unlit;
	public static Block block_redstone_briny_lit;
	public static Block block_irradium;
	public static Block block_sunstone;
	public static Block block_torite;
	public static Block block_titanium;
	public static Block block_glacierite;
	public static Block block_blazium;
	public static Block block_soul;
	public static Block block_mite_honey;
	public static Block block_dimensium;
	public static Block block_dimensium_destabilized;
	
	//Dyeables
	public static Block glow_wool;
	public static Block glow_carpet;
	public static Block glow_stained_glass;
	public static Block glow_stained_glass_pane;
	public static Block glow_concrete_powder;
	public static Block glow_concrete;
	public static Block glow_terracotta;
	public static Block glow_glazed_terracotta_white;
	public static Block glow_glazed_terracotta_magenta;
	public static Block glow_glazed_terracotta_red;
	public static Block glow_glazed_terracotta_orange;
	public static Block glow_glazed_terracotta_yellow;
	public static Block glow_glazed_terracotta_green;
	public static Block glow_glazed_terracotta_cyan;
	public static Block glow_glazed_terracotta_blue;
	public static Block glow_glazed_terracotta_purple;
	
	//Decor
	public static Block glass_tiles;
	public static Block glass_bricks;
	public static Block wooden_window;
	public static Block iron_window;
	public static Block reinforced_glass;
	public static Block glass_tile_pane;
	public static Block glass_brick_pane;
	public static Block wooden_window_pane;
	public static Block iron_window_pane;
	public static Block reinforced_glass_pane;
	public static Block honeycomb_bronze;
	public static Block honeycomb_steel;
	public static Block honeycomb_meurodite;
	public static Block bauble_ice;
	public static Block bauble_sunstone;
	public static Block bauble_glowstone;
	public static Block bauble_glowshroom_blue;
	public static Block bauble_glowshroom_green;
	public static Block bauble_glowshroom_purple;
	public static Block bauble_blazium;
	public static Block cobblestone_wall;
	public static Block mossy_cobblestone_wall;
	public static Block steel_mesh;
	public static Block candle;
	public static Block candle_fire;
	public static Block candle_ender;
	public static Block candle2;
	public static Block candle2_fire;
	public static Block candle2_ender;
	public static Block chandelier_halloween;
	public static Block chandelier_halloween_fire;
	public static Block chandelier_halloween_ender;
	public static Block chandelier_new_year;
	public static Block chandelier_new_year_fire;
	public static Block chandelier_new_year_ender;
	public static BlockJackOLantern pumpkin_normie;
	public static Block pumpkin_normie_fire;
	public static Block pumpkin_normie_ender;
	public static BlockJackOLantern pumpkin_grumpy;
	public static Block pumpkin_grumpy_fire;
	public static Block pumpkin_grumpy_ender;
	public static BlockJackOLantern pumpkin_dumpy;
	public static Block pumpkin_dumpy_fire;
	public static Block pumpkin_dumpy_ender;
	public static BlockJackOLantern pumpkin_creepy;
	public static Block pumpkin_creepy_fire;
	public static Block pumpkin_creepy_ender;
	public static BlockJackOLantern pumpkin_smiley;
	public static Block pumpkin_smiley_fire;
	public static Block pumpkin_smiley_ender;
	public static Block pumpkin_smashed;
	public static Block pumpkin_smashed_fire;
	public static Block pumpkin_smashed_ender;
	public static BlockMSkull skull_bat;
	public static BlockMSkull skull_parrot;
	public static BlockMSkull skull_chicken;
	public static BlockMSkull skull_rabbit;
	public static BlockMSkull skull_pig;
	public static BlockMSkull skull_sheep;
	public static BlockMSkull skull_cow;
	public static BlockMSkull skull_llama;
	public static BlockMSkull skull_horse;
	public static BlockMSkull skull_ocelot;
	public static BlockMSkull skull_wolf;
	public static BlockMSkull skull_polar_bear;
	public static BlockMSkull skull_squid;
	public static BlockMSkull skull_villager;
	public static BlockMSkull skull_husk;
	public static BlockMSkull skull_stray;
	public static BlockMSkull skull_creeper;
	public static BlockMSkull skull_guardian;
	public static BlockMSkull skull_enderman;
	public static BlockMSkull skull_shulker;
	public static Block plate_weave;
	public static Block plate_slate;
	public static Block plate_metal;
	
	//Utility
	public static Block red_rock_road;
	public static Block stone_road;
	public static Block coldstone_road;
	public static Block icestone_road;
	public static Block oceanstone_road;
	public static Block netherrack_road;
	public static Block end_stone_road;
	public static Block rope;
	public static Block dimensium_rope;
	public static Block door_charwood;
	public static Block door_palm;
	public static Block basket;
	public static Block basket_cheesemaking;
	public static Block crate;
	public static Block barrel;
	public static Block tanning_rack;
	public static Block glacieric_ice;
	public static Block lava_sponge;
	public static Block mite_eggsack;
	public static Block pipe;
	public static Block covered_pipe_red_rock;
	public static Block covered_pipe_deep_red_rock;
	public static Block covered_pipe_stone;
	public static Block covered_pipe_deepstone;
	public static Block covered_pipe_coldstone;
	public static Block covered_pipe_deep_coldstone;
	public static Block covered_pipe_icestone;
	public static Block covered_pipe_glacierrock;
	public static Block covered_pipe_oceanstone;
	public static Block covered_pipe_reefstone;
	public static Block sorter;
	public static Block activator;
	public static Block wooden_axel;
	public static Block creative_engine;
	public static Block spike_oak_wood;
	public static Block spike_spruce_wood;
	public static Block spike_birch_wood;
	public static Block spike_jungle_wood;
	public static Block spike_acacia_wood;
	public static Block spike_dark_oak_wood;
	public static Block spike_redwood_wood;
	public static Block spike_frozen_oak_wood;
	public static Block spike_charwood_wood;
	public static Block spike_palm_wood;
	public static Block spike_red_rock;
	public static Block spike_stone;
	public static Block spike_coldstone;
	public static Block spike_icestone;
	public static Block spike_oceanstone;
	public static Block spike_copper;
	public static Block spike_tin;
	public static Block spike_iron;
	public static Block spike_bronze;
	public static Block spike_steel;
	public static Block spike_gold;
	public static Block spike_meurodite;
	public static Block spike_torite;
	public static Block spike_diamond;
	public static Block spike_archantine;
	public static Block spike_glacierite;
	public static Block spike_blazium;
	public static Block spike_dimensium;
	public static Block magnet_piston_1;
	public static Block magnet_piston_2;
	public static Block magnet_piston_3;
	public static Block magnet_piston_4;
	public static Block magnet_piston_head;
	public static Block magnet_piston_extension;
	public static Block block_irradiant_sunstone;
	public static Block godstone;
	public static Block block_irradium_insulated;
	public static Block block_dimensium_stabilized_inactive;
	public static Block block_dimensium_stabilized_active;
	public static Block stasis_field;
	public static Block soulsteel_vessel;
	public static Block stonecutter;
	public static Block sawmill;
	public static Block alloy;
	public static Block pressurizer;
	public static Block crusher;
	public static BlockBiomeRedstoneWire redstone_sandy;
	public static BlockBiomeRedstoneWire redstone_frosted;
	public static BlockBiomeRedstoneWire redstone_icy;
	public static BlockBiomeRedstoneWire redstone_briny;
	public static Block glow_paste;
	
	public static Block hacky_jei_fix_light_freezing;
	public static Block hacky_jei_fix_deep_freezing;
	public static Block hacky_jei_fix_sieving;
	public static Block hacky_jei_fix_sawmill_all;
	public static Block hacky_jei_fix_sawmill_stone;
	
	//Crops
	public static Block crop_withered;
	public static Block crop_pepper;
	public static Block crop_cabbage;
	public static Block crop_celery;
	public static Block crop_lettuce;
	public static Block crop_onion;
	public static Block crop_peanuts;
	public static Block crop_tomato;
	public static Block crop_corn;
	
	//Liquids
	public static Block liquid_crystalfloe;
	
	public static void init()
	{
		//Plant
		register(moss = new BlockMoss().setPushReaction(EnumPushReaction.DESTROY));
		register(blue_glowshroom = new BlockGlowshroom("blue_glowshroom").setLightLevel(0.6F));
		register(green_glowshroom = new BlockGlowshroom("green_glowshroom").setLightLevel(0.8F));
		register(purple_glowshroom = new BlockGlowshroom("purple_glowshroom").setLightLevel(0.7F));
		register(infected_mushroom = new BlockInfectedMushroom("infected_mushroom").setLightLevel(0.5F));
		register(blue_glowshroom_block = new BlockMHugeMushroom(Material.WOOD, MapColor.BLUE, blue_glowshroom, "blue_glowshroom_block").setHardness(0.2F).setLightLevel(0.6F));
		register(green_glowshroom_block = new BlockMHugeMushroom(Material.WOOD, MapColor.LIME, green_glowshroom, "green_glowshroom_block").setHardness(0.2F).setLightLevel(0.8F));
		register(purple_glowshroom_block = new BlockMHugeMushroom(Material.WOOD, MapColor.PURPLE, purple_glowshroom, "purple_glowshroom_block").setHardness(0.2F).setLightLevel(0.7F));
		register(tundra_grass = new BlockTundraGrass("tundra_grass"));
		register(savanna_grass = new BlockSavannaGrass("savanna_grass"));
		register(blueberry_bush = new BlockBerryBush("blueberry_bush", MapColor.FOLIAGE, "plains"));
		register(blackberry_bush = new BlockBerryBush("blackberry_bush", MapColor.FOLIAGE, "plains"));
		register(raspberry_bush = new BlockBerryBush("raspberry_bush", MapColor.FOLIAGE, "plains"));
		register(strawberry_bush = new BlockBerryBush("strawberry_bush", MapColor.FOLIAGE, "plains"));
		register(mana_bush = new BlockBerryBush("mana_bush", MapColor.LAPIS, "coast"));
		register(leaves = new BlockMLeaves(), new ItemBlockMultistate(leaves));
		register(palm_fronds = new BlockPalmFronds("palm_fronds", true));
		register(palm_fronds_dead = new BlockPalmFronds("dead_palm_fronds", false));
		register(clutchthorn = new BlockClutchthorn());
		register(voidberry_bush = new BlockVoidberryBush("voidberry_bush"));
		register(terracreep = new BlockTerracreep());
		register(carpet_glow_moss = new BlockCarpetGlowMoss().setPushReaction(EnumPushReaction.DESTROY));
		register(creeping_glow_moss = new BlockCreepingGlowMoss().setPushReaction(EnumPushReaction.DESTROY));
		register(hanging_glow_moss = new BlockHangingMoss("hanging_glow_moss").setLightLevel(0.8F));
		register(melon_bricks = new BlockBase("melon_bricks", Material.GOURD, MapColor.LIME, SoundType.WOOD, 1F, "axe", 0).setDropsItem(new ItemStack(Items.MELON, 3), 6, 0, 0, true, false).setPushReaction(EnumPushReaction.DESTROY).setCreativeTab(MTabs.plant));
		register(mite_hive = new BlockEndermiteHiveHusk("mite_hive", false));
		register(mite_hive_honeyed = new BlockEndermiteHiveHusk("mite_hive_honeyed", true).setLightLevel(0.5F));
		register(mite_comb = new BlockBase("mite_comb", Material.GOURD, MapColor.MAGENTA_STAINED_HARDENED_CLAY, SoundType.WOOD, 0.4F, "axe", 0).setFlammable(20, 5).setCreativeTab(MTabs.environment));
		
		//Food
		register(block_cheese = new BlockCheese());
		register(quesadilla = new BlockQuesadilla());
		register(pizza = new BlockPizza());
		
		//Soil
		register(cold_sand = new BlockColdSand("cold_sand", Material.SAND, SoundType.SAND, 0.7F, "shovel", 0).setCreativeTab(MTabs.environment), new ItemBlockMultistate(cold_sand));
		register(clay_soil = new BlockMDirt("clay_soil", MapColor.ADOBE, SoundType.GROUND, 0.5F, 0), new ItemBlockMultistate(clay_soil));
		register(clay_grass = new BlockMGrass("clay_grass", MapColor.GRASS, SoundType.PLANT, 0.6F, 0, clay_soil, true));
		register(clay_grass_path = new BlockMPath("clay_grass_path", MapColor.DIRT, SoundType.PLANT, 0.65F, 0, clay_soil));
		register(clay_farmland = new BlockMFarmland("clay_farmland", MapColor.ADOBE, SoundType.GROUND, 0.6F, 0, clay_soil, 2));
		register(permafrost = new BlockMDirt("permafrost", MapColor.LIGHT_BLUE, SoundType.GROUND, 0.7F, 0), new ItemBlockMultistate(permafrost));
		register(lichen = new BlockMGrass("lichen", MapColor.SAND, SoundType.PLANT, 0.8F, 0, permafrost, false));
		register(lichen_path = new BlockMPath("lichen_path", MapColor.DIRT, SoundType.PLANT, 0.75F, 0, permafrost));
		register(permafrost_farmland = new BlockMFarmland("permafrost_farmland", MapColor.ADOBE, SoundType.GROUND, 0.6F, 0, permafrost, 6));
		register(mud = new BlockMud());
		register(dried_mud = new BlockDriedMud());
		register(portal_dust = new BlockPortalDust(), new ItemBlockMultistate(portal_dust));
		register(fargrowth = new BlockFargrowth());
		register(fargrowth_path = new BlockFargrowthPath());
		
		//Wood
		register(cork = new BlockBase("block_cork", Material.WOOD, MapColor.BROWN, SoundType.WOOD, 0.5F, "axe", 0).setFlammable(10, 10).setCreativeTab(MTabs.wood));
		register(log = new BlockMLog().setCreativeTab(MTabs.wood), new ItemBlockMultistate(log));
		register(palm_crown = new BlockPalmCrown("palm_crown", true));
		register(palm_crown_dead = new BlockPalmCrown("dead_palm_crown", false));
		register(coconut = new BlockCoconut());
		register(planks = new BlockMPlanks("m_planks"), new ItemBlockMultistate(planks));
		register(mossy_m_planks = new BlockMPlanks("m_planks_mossy"), new ItemBlockMultistate(mossy_m_planks));
		register(double_wood_slab_1 = new BlockDoubleWoodSlab1("m_wood_slab_1"));
		register(wood_slab_1 = new BlockHalfWoodSlab1("m_wood_slab_1"), new ItemBlockMSlab(wood_slab_1, wood_slab_1, double_wood_slab_1));
		register(oak_plank_panel = new BlockPanelBase(Blocks.PLANKS, MTabs.wood , 0, "oak").setFlammable(20, 5), new ItemBlockPanel(oak_plank_panel));
		register(spruce_plank_panel = new BlockPanelBase(Blocks.PLANKS, MTabs.wood , 1, "spruce").setFlammable(20, 5), new ItemBlockPanel(spruce_plank_panel));
		register(birch_plank_panel = new BlockPanelBase(Blocks.PLANKS, MTabs.wood , 2, "birch").setFlammable(20, 5), new ItemBlockPanel(birch_plank_panel));
		register(jungle_plank_panel = new BlockPanelBase(Blocks.PLANKS, MTabs.wood , 3, "jungle").setFlammable(20, 5), new ItemBlockPanel(jungle_plank_panel));
		register(acacia_plank_panel = new BlockPanelBase(Blocks.PLANKS, MTabs.wood , 4, "acacia").setFlammable(20, 5), new ItemBlockPanel(acacia_plank_panel));
		register(dark_oak_plank_panel = new BlockPanelBase(Blocks.PLANKS, MTabs.wood , 5, "dark_oak").setFlammable(20, 5), new ItemBlockPanel(dark_oak_plank_panel));
		register(redwood_plank_panel = new BlockPanelBase(MBlocks.planks, MTabs.wood, 0, "redwood").setFlammable(20, 5), new ItemBlockPanel(redwood_plank_panel));
		register(frozen_oak_plank_panel = new BlockPanelBase(MBlocks.planks, MTabs.wood, 1, "frozen_oak").setFlammable(20, 5), new ItemBlockPanel(frozen_oak_plank_panel));
		register(charwood_plank_panel = new BlockPanelBase(MBlocks.planks, MTabs.wood, 2, "charwood"), new ItemBlockPanel(charwood_plank_panel));
		register(palm_plank_panel = new BlockPanelBase(MBlocks.planks, MTabs.wood, 3, "palm").setFlammable(20, 5), new ItemBlockPanel(palm_plank_panel));
		register(redwood_plank_stairs = new BlockStairBase(planks.getDefaultState().withProperty(BlockMPlanks.VARIANT, BlockMPlanks.EnumType.REDWOOD), planks.getUnlocalizedName() + "_" + BlockMPlanks.EnumType.REDWOOD.getUnlocalizedName()).setFlammable(20, 5));
		register(frozen_oak_plank_stairs = new BlockStairBase(planks.getDefaultState().withProperty(BlockMPlanks.VARIANT, BlockMPlanks.EnumType.FROZEN_OAK), planks.getUnlocalizedName() + "_" + BlockMPlanks.EnumType.FROZEN_OAK.getUnlocalizedName()).setFlammable(20, 5));
		register(charwood_plank_stairs = new BlockStairBase(planks.getDefaultState().withProperty(BlockMPlanks.VARIANT, BlockMPlanks.EnumType.CHARWOOD), planks.getUnlocalizedName() + "_" + BlockMPlanks.EnumType.CHARWOOD.getUnlocalizedName()));
		register(palm_plank_stairs = new BlockStairBase(planks.getDefaultState().withProperty(BlockMPlanks.VARIANT, BlockMPlanks.EnumType.PALM), planks.getUnlocalizedName() + "_" + BlockMPlanks.EnumType.PALM.getUnlocalizedName()).setFlammable(20, 5));
		register(fence = new BlockMFence(), new ItemBlockMultistate(fence));
		register(redwood_fence_gate = new BlockMFenceGate(BlockMPlanks.EnumType.REDWOOD));
		register(frozen_oak_fence_gate = new BlockMFenceGate(BlockMPlanks.EnumType.FROZEN_OAK));
		register(charwood_fence_gate = new BlockMFenceGate(BlockMPlanks.EnumType.CHARWOOD));
		register(palm_fence_gate = new BlockMFenceGate(BlockMPlanks.EnumType.PALM));
		ForgeRegistries.BLOCKS.register(charwood_limb = new BlockCharwoodLimb());
		
		//Stone
		register(mud_bricks = new BlockBase("mud_bricks", Material.ROCK, MapColor.WOOD, SoundType.STONE, 0.85F, "pickaxe", 0).setCreativeTab(MTabs.stone));
		register(decor_stone = new BlockDecorativeStones(), new ItemBlockMultistate(decor_stone));
		register(sandstone = new BlockMSandstone(), new ItemBlockMultistate(sandstone));
		register(desert_quartz = new BlockMQuartz("desert_quartz", MapColor.SILVER_STAINED_HARDENED_CLAY), new ItemBlockMultistate(desert_quartz));
		register(cobblestone = new BlockStoneBaseMOnly("m_cobblestone", Material.ROCK, SoundType.STONE, 2F, "pickaxe", 0).setResistance(10F).setCreativeTab(MTabs.stone), new ItemBlockMultistate(cobblestone));
		register(mossy_cobblestone = new BlockStoneBaseMOnly("m_mossy_cobblestone", Material.ROCK, SoundType.STONE, 2F, "pickaxe", 0).setResistance(10F).setCreativeTab(MTabs.stone), new ItemBlockMultistate(mossy_cobblestone));
		register(stone = new BlockStoneBaseMOnly("m_stone", Material.ROCK, SoundType.STONE, 1.5F, "pickaxe", 0).setDropsItem(new ItemStack(Item.getItemFromBlock(MBlocks.cobblestone)), 0, 0, 0, true, false, true).setResistance(10F).setCreativeTab(MTabs.stone), new ItemBlockMultistate(stone));
		register(stone_bricks = new BlockStoneBaseMOnly("m_stone_bricks", Material.ROCK, SoundType.STONE, 1.5F, "pickaxe", 0).setResistance(10F).setCreativeTab(MTabs.stone), new ItemBlockMultistate(stone_bricks));
		register(mossy_stone_bricks = new BlockStoneBaseMOnly("m_stone_bricks_mossy", Material.ROCK, SoundType.STONE, 1.5F, "pickaxe", 0).setResistance(10F).setCreativeTab(MTabs.stone), new ItemBlockMultistate(mossy_stone_bricks));
		register(cracked_stone_bricks = new BlockStoneBaseMOnly("m_stone_bricks_cracked", Material.ROCK, SoundType.STONE, 1.5F, "pickaxe", 0).setResistance(10F).setCreativeTab(MTabs.stone), new ItemBlockMultistate(cracked_stone_bricks));
		register(chiseled_stone = new BlockStoneBaseMOnly("m_chiseled_stone", Material.ROCK, SoundType.STONE, 1.5F, "pickaxe", 0).setResistance(10F).setCreativeTab(MTabs.stone), new ItemBlockMultistate(chiseled_stone));
		register(irradiant_quartz = new BlockMQuartz("irradiant_quartz", MapColor.PINK).setBeaconBase().setLightLevel(0.5F), new ItemBlockMultistate(irradiant_quartz));
		register(shimmerstone = new BlockShimmerstone(), new ItemBlockMultistate(shimmerstone));
		register(soul_glass = new BlockSoulGlass("soul_glass", MapColor.BROWN).setCreativeTab(MTabs.stone), new ItemBlockMultistate(soul_glass));
		register(blazed_soul_glass = new BlockSoulGlass("blazed_soul_glass", MapColor.ADOBE).setCreativeTab(MTabs.stone).setLightLevel(0.3F), new ItemBlockMultistate(blazed_soul_glass));
		register(purpur = new BlockBase("m_purpur", Material.ROCK, MapColor.MAGENTA, SoundType.STONE, 1.5F, "pickaxe", 0).setResistance(10F).setCreativeTab(MTabs.stone));
		register(portar = new BlockPortar(), new ItemBlockMultistate(portar));
		register(gilded_stone = new BlockGildedStone(), new ItemBlockMultistate(gilded_stone));
		register(invincium = new BlockInvincium());
		register(glacial_invincium = new BlockGlacialInvincium().setPushReaction(EnumPushReaction.BLOCK).setSlipperiness(0.85F).setEntityInvulnerability("all").setBlockUnbreakable().setResistance(9999F).setCreativeTab(MTabs.environment));
		
		register(double_misc_stone_slab_1 = new BlockDoubleMiscStoneSlab1("m_misc_stone_slab_1"));
		register(misc_stone_slab_1 = new BlockHalfMiscStoneSlab1("m_misc_stone_slab_1"), new ItemBlockMSlab(misc_stone_slab_1, misc_stone_slab_1, double_misc_stone_slab_1));
		register(double_misc_stone_slab_2 = new BlockDoubleMiscStoneSlab2("m_misc_stone_slab_2"));
		register(misc_stone_slab_2 = new BlockHalfMiscStoneSlab2("m_misc_stone_slab_2"), new ItemBlockMSlab(misc_stone_slab_2, misc_stone_slab_2, double_misc_stone_slab_2));
		register(double_misc_stone_slab_3 = new BlockDoubleMiscStoneSlab3("m_misc_stone_slab_3"));
		register(misc_stone_slab_3 = new BlockHalfMiscStoneSlab3("m_misc_stone_slab_3"), new ItemBlockMSlab(misc_stone_slab_3, misc_stone_slab_3, double_misc_stone_slab_3));
		register(double_stone_slab_1 = new BlockDoubleStoneSlab1("m_stone_slab_1"));
		register(stone_slab_1 = new BlockHalfStoneSlab1("m_stone_slab_1"), new ItemBlockMSlab(stone_slab_1, stone_slab_1, double_stone_slab_1));
		register(double_stone_slab_2 = new BlockDoubleStoneSlab2("m_stone_slab_2"));
		register(stone_slab_2 = new BlockHalfStoneSlab2("m_stone_slab_2"), new ItemBlockMSlab(stone_slab_2, stone_slab_2, double_stone_slab_2));
		register(double_stone_slab_3 = new BlockDoubleStoneSlab3("m_stone_slab_3"));
		register(stone_slab_3 = new BlockHalfStoneSlab3("m_stone_slab_3"), new ItemBlockMSlab(stone_slab_3, stone_slab_3, double_stone_slab_3));
		register(double_stone_slab_4 = new BlockDoubleStoneSlab4("m_stone_slab_4"));
		register(stone_slab_4 = new BlockHalfStoneSlab4("m_stone_slab_4"), new ItemBlockMSlab(stone_slab_4, stone_slab_4, double_stone_slab_4));
		
		register(mud_brick_panel = new BlockPanelBase(mud_bricks, MTabs.stone), new ItemBlockPanel(mud_brick_panel));
		register(granite_brick_panel = new BlockPanelBase(decor_stone, MTabs.stone, 0, "granite_bricks"), new ItemBlockPanel(granite_brick_panel));
		register(diorite_brick_panel = new BlockPanelBase(decor_stone, MTabs.stone, 2, "diorite_bricks"), new ItemBlockPanel(diorite_brick_panel));
		register(andesite_brick_panel = new BlockPanelBase(decor_stone, MTabs.stone, 4, "andesite_bricks"), new ItemBlockPanel(andesite_brick_panel));
		register(slate_brick_panel = new BlockPanelBase(decor_stone, MTabs.stone, 8, "slate_bricks"), new ItemBlockPanel(slate_brick_panel));
		register(clay_brick_panel = new BlockPanelBase(Blocks.BRICK_BLOCK, MTabs.stone), new ItemBlockPanel(clay_brick_panel));
		register(sandstone_panel = new BlockPanelBase(Blocks.SANDSTONE, MTabs.stone), new ItemBlockPanel(sandstone_panel));
		register(red_sandstone_panel = new BlockPanelBase(Blocks.RED_SANDSTONE, MTabs.stone), new ItemBlockPanel(red_sandstone_panel));
		register(cold_sandstone_panel = new BlockPanelBase(sandstone, MTabs.stone, 2, "cold_sandstone"), new ItemBlockPanel(cold_sandstone_panel));
		register(cold_red_sandstone_panel = new BlockPanelBase(sandstone, MTabs.stone, 6, "cold_red_sandstone"), new ItemBlockPanel(cold_red_sandstone_panel));
		register(sandstone_brick_panel = new BlockPanelBase(sandstone, MTabs.stone, 0, "sandstone_bricks"), new ItemBlockPanel(sandstone_brick_panel));
		register(red_sandstone_brick_panel = new BlockPanelBase(sandstone, MTabs.stone, 1, "red_sandstone_bricks"), new ItemBlockPanel(red_sandstone_brick_panel));
		register(cold_sandstone_brick_panel = new BlockPanelBase(sandstone, MTabs.stone, 5, "cold_sandstone_bricks"), new ItemBlockPanel(cold_sandstone_brick_panel));
		register(cold_red_sandstone_brick_panel = new BlockPanelBase(sandstone, MTabs.stone, 9, "cold_red_sandstone_bricks"), new ItemBlockPanel(cold_red_sandstone_brick_panel));
		register(desert_quartz_panel = new BlockPanelBase(desert_quartz, MTabs.stone, 0, "polished"), new ItemBlockPanel(desert_quartz_panel));
		register(irradiant_quartz_panel = new BlockPanelBase(irradiant_quartz, MTabs.stone, 0, "polished"), new ItemBlockPanel(irradiant_quartz_panel));
		register(cobbled_red_rock_panel = new BlockPanelBase(cobblestone, MTabs.stone, 0, "red_rock"), new ItemBlockPanel(cobbled_red_rock_panel));
		register(cobbled_deep_red_rock_panel = new BlockPanelBase(cobblestone, MTabs.stone, 1, "deep_red_rock"), new ItemBlockPanel(cobbled_deep_red_rock_panel));
		register(cobblestone_panel = new BlockPanelBase(Blocks.COBBLESTONE, MTabs.stone), new ItemBlockPanel(cobblestone_panel));
		register(cobbled_deepstone_panel = new BlockPanelBase(cobblestone, MTabs.stone, 2, "deepstone"), new ItemBlockPanel(cobbled_deepstone_panel));
		register(cobbled_coldstone_panel = new BlockPanelBase(cobblestone, MTabs.stone, 3, "coldstone"), new ItemBlockPanel(cobbled_coldstone_panel));
		register(cobbled_deep_coldstone_panel = new BlockPanelBase(cobblestone, MTabs.stone, 4, "deep_coldstone"), new ItemBlockPanel(cobbled_deep_coldstone_panel));
		register(cobbled_icestone_panel = new BlockPanelBase(cobblestone, MTabs.stone, 5, "icestone"), new ItemBlockPanel(cobbled_icestone_panel));
		register(cobbled_glacierrock_panel = new BlockPanelBase(cobblestone, MTabs.stone, 6, "glacierrock"), new ItemBlockPanel(cobbled_glacierrock_panel));
		register(cobbled_oceanstone_panel = new BlockPanelBase(cobblestone, MTabs.stone, 7, "oceanstone"), new ItemBlockPanel(cobbled_oceanstone_panel));
		register(cobbled_deep_oceanstone_panel = new BlockPanelBase(cobblestone, MTabs.stone, 8, "reefstone"), new ItemBlockPanel(cobbled_deep_oceanstone_panel));
		register(red_rock_panel = new BlockPanelBase(double_stone_slab_1, MTabs.stone, 8, "red_rock"), new ItemBlockPanel(red_rock_panel));
		register(deep_red_rock_panel = new BlockPanelBase(double_stone_slab_1, MTabs.stone, 9, "deep_red_rock"), new ItemBlockPanel(deep_red_rock_panel));
		register(stone_panel = new BlockPanelBase(Blocks.DOUBLE_STONE_SLAB, MTabs.stone), new ItemBlockPanel(stone_panel));
		register(deepstone_panel = new BlockPanelBase(double_stone_slab_1, MTabs.stone, 10, "deepstone"), new ItemBlockPanel(deepstone_panel));
		register(coldstone_panel = new BlockPanelBase(double_stone_slab_1, MTabs.stone, 11, "coldstone"), new ItemBlockPanel(coldstone_panel));
		register(deep_coldstone_panel = new BlockPanelBase(double_stone_slab_1, MTabs.stone, 12, "deep_coldstone"), new ItemBlockPanel(deep_coldstone_panel));
		register(icestone_panel = new BlockPanelBase(double_stone_slab_1, MTabs.stone, 13, "icestone"), new ItemBlockPanel(icestone_panel));
		register(glacierrock_panel = new BlockPanelBase(double_stone_slab_1, MTabs.stone, 14, "glacierrock"), new ItemBlockPanel(glacierrock_panel));
		register(oceanstone_panel = new BlockPanelBase(double_stone_slab_1, MTabs.stone, 15, "oceanstone"), new ItemBlockPanel(oceanstone_panel));
		register(deep_oceanstone_panel = new BlockPanelBase(double_stone_slab_2, MTabs.stone, 8, "reefstone"), new ItemBlockPanel(deep_oceanstone_panel));
		register(red_rock_brick_panel = new BlockPanelBase(stone_bricks, MTabs.stone, 0, "red_rock"), new ItemBlockPanel(red_rock_brick_panel));
		register(deep_red_rock_brick_panel = new BlockPanelBase(stone_bricks, MTabs.stone, 1, "deep_red_rock"), new ItemBlockPanel(deep_red_rock_brick_panel));
		register(stone_brick_panel = new BlockPanelBase(Blocks.STONEBRICK, MTabs.stone), new ItemBlockPanel(stone_brick_panel));
		register(deepstone_brick_panel = new BlockPanelBase(stone_bricks, MTabs.stone, 2, "deepstone"), new ItemBlockPanel(deepstone_brick_panel));
		register(coldstone_brick_panel = new BlockPanelBase(stone_bricks, MTabs.stone, 3, "coldstone"), new ItemBlockPanel(coldstone_brick_panel));
		register(deep_coldstone_brick_panel = new BlockPanelBase(stone_bricks, MTabs.stone, 4, "deep_coldstone"), new ItemBlockPanel(deep_coldstone_brick_panel));
		register(icestone_brick_panel = new BlockPanelBase(stone_bricks, MTabs.stone, 5, "icestone"), new ItemBlockPanel(icestone_brick_panel));
		register(glacierrock_brick_panel = new BlockPanelBase(stone_bricks, MTabs.stone, 6, "glacierrock"), new ItemBlockPanel(glacierrock_brick_panel));
		register(oceanstone_brick_panel = new BlockPanelBase(stone_bricks, MTabs.stone, 7, "oceanstone"), new ItemBlockPanel(oceanstone_brick_panel));
		register(deep_oceanstone_brick_panel = new BlockPanelBase(stone_bricks, MTabs.stone, 8, "reefstone"), new ItemBlockPanel(deep_oceanstone_brick_panel));
		register(nether_brick_panel = new BlockPanelBase(Blocks.NETHER_BRICK, MTabs.stone), new ItemBlockPanel(nether_brick_panel));
		register(nether_quartz_panel = new BlockPanelBase(Blocks.QUARTZ_BLOCK, MTabs.stone), new ItemBlockPanel(nether_quartz_panel));
		register(shimmerstone_panel = new BlockPanelBase(misc_stone_slab_1, MTabs.stone, 6, "shimmerstone"), new ItemBlockPanel(shimmerstone_panel));
		register(cobbled_shimmerstone_panel = new BlockPanelBase(shimmerstone, MTabs.stone, 1, "cobblestone"), new ItemBlockPanel(cobbled_shimmerstone_panel));
		register(shimmerstone_brick_panel = new BlockPanelBase(shimmerstone, MTabs.stone, 2, "bricks"), new ItemBlockPanel(shimmerstone_brick_panel));
		register(purpur_panel = new BlockPanelBase(Blocks.PURPUR_BLOCK, MTabs.stone), new ItemBlockPanel(purpur_panel));
		register(portar_panel = new BlockPanelBase(portar, MTabs.stone, 0, "slabbed"), new ItemBlockPanel(portar_panel));
		register(gilded_brick_panel = new BlockPanelBase(gilded_stone, MTabs.stone, 0, "bricks"), new ItemBlockPanel(gilded_brick_panel));
		
		register(mud_brick_stairs = new BlockStairBase(mud_bricks));
		register(granite_brick_stairs = new BlockStairBase(decor_stone.getDefaultState().withProperty(BlockDecorativeStones.VARIANT, BlockDecorativeStones.DecorStoneType.GRANITE_BRICKS), decor_stone.getUnlocalizedName() + "_" + BlockDecorativeStones.DecorStoneType.GRANITE_BRICKS.getUnlocalizedName()));
		register(diorite_brick_stairs = new BlockStairBase(decor_stone.getDefaultState().withProperty(BlockDecorativeStones.VARIANT, BlockDecorativeStones.DecorStoneType.DIORITE_BRICKS), decor_stone.getUnlocalizedName() + "_" + BlockDecorativeStones.DecorStoneType.DIORITE_BRICKS.getUnlocalizedName()));
		register(andesite_brick_stairs = new BlockStairBase(decor_stone.getDefaultState().withProperty(BlockDecorativeStones.VARIANT, BlockDecorativeStones.DecorStoneType.ANDESITE_BRICKS), decor_stone.getUnlocalizedName() + "_" + BlockDecorativeStones.DecorStoneType.ANDESITE_BRICKS.getUnlocalizedName()));
		register(slate_brick_stairs = new BlockStairBase(decor_stone.getDefaultState().withProperty(BlockDecorativeStones.VARIANT, BlockDecorativeStones.DecorStoneType.SLATE_BRICKS), decor_stone.getUnlocalizedName() + "_" + BlockDecorativeStones.DecorStoneType.SLATE_BRICKS.getUnlocalizedName()));
		register(sandstone_brick_stairs = new BlockStairBase(sandstone.getDefaultState().withProperty(BlockMSandstone.VARIANT, BlockMSandstone.SandstoneType.SANDSTONE_BRICKS), sandstone.getUnlocalizedName() + "_" + BlockMSandstone.SandstoneType.SANDSTONE_BRICKS.getUnlocalizedName()));
		register(red_sandstone_brick_stairs = new BlockStairBase(sandstone.getDefaultState().withProperty(BlockMSandstone.VARIANT, BlockMSandstone.SandstoneType.RED_SANDSTONE_BRICKS), sandstone.getUnlocalizedName() + "_" + BlockMSandstone.SandstoneType.RED_SANDSTONE_BRICKS.getUnlocalizedName()));
		register(cold_sandstone_stairs = new BlockStairBase(sandstone.getDefaultState().withProperty(BlockMSandstone.VARIANT, BlockMSandstone.SandstoneType.COLD_SANDSTONE), sandstone.getUnlocalizedName() + "_" + BlockMSandstone.SandstoneType.COLD_SANDSTONE.getUnlocalizedName()));
		register(cold_red_sandstone_stairs = new BlockStairBase(sandstone.getDefaultState().withProperty(BlockMSandstone.VARIANT, BlockMSandstone.SandstoneType.COLD_RED_SANDSTONE), sandstone.getUnlocalizedName() + "_" + BlockMSandstone.SandstoneType.COLD_RED_SANDSTONE.getUnlocalizedName()));
		register(cold_sandstone_brick_stairs = new BlockStairBase(sandstone.getDefaultState().withProperty(BlockMSandstone.VARIANT, BlockMSandstone.SandstoneType.COLD_SANDSTONE_BRICKS), sandstone.getUnlocalizedName() + "_" + BlockMSandstone.SandstoneType.COLD_SANDSTONE_BRICKS.getUnlocalizedName()));
		register(cold_red_sandstone_brick_stairs = new BlockStairBase(sandstone.getDefaultState().withProperty(BlockMSandstone.VARIANT, BlockMSandstone.SandstoneType.COLD_RED_SANDSTONE_BRICKS), sandstone.getUnlocalizedName() + "_" + BlockMSandstone.SandstoneType.COLD_RED_SANDSTONE_BRICKS.getUnlocalizedName()));
		register(desert_quartz_stairs = new BlockStairBase(desert_quartz.getDefaultState().withProperty(BlockMQuartz.VARIANT, BlockMQuartz.EnumType.POLISHED), desert_quartz.getUnlocalizedName() + "_" + BlockMQuartz.EnumType.POLISHED.getName()));
		register(irradiant_quartz_stairs = new BlockStairBase(irradiant_quartz.getDefaultState().withProperty(BlockMQuartz.VARIANT, BlockMQuartz.EnumType.POLISHED), irradiant_quartz.getUnlocalizedName() + "_" + BlockMQuartz.EnumType.POLISHED.getName()));
		register(cobbled_red_rock_stairs = new BlockStairBase(cobblestone.getDefaultState().withProperty(BlockStoneBaseMOnly.VARIANT, EnumStoneTypeMOnly.RED_ROCK), cobblestone.getUnlocalizedName() + "_" + EnumStoneTypeMOnly.RED_ROCK.getUnlocalizedName()));
		register(cobbled_deep_red_rock_stairs = new BlockStairBase(cobblestone.getDefaultState().withProperty(BlockStoneBaseMOnly.VARIANT, EnumStoneTypeMOnly.DEEP_RED_ROCK), cobblestone.getUnlocalizedName() + "_" + EnumStoneTypeMOnly.DEEP_RED_ROCK.getUnlocalizedName()));
		register(cobbled_deepstone_stairs = new BlockStairBase(cobblestone.getDefaultState().withProperty(BlockStoneBaseMOnly.VARIANT, EnumStoneTypeMOnly.DEEPSTONE), cobblestone.getUnlocalizedName() + "_" + EnumStoneTypeMOnly.DEEPSTONE.getUnlocalizedName()));
		register(cobbled_coldstone_stairs = new BlockStairBase(cobblestone.getDefaultState().withProperty(BlockStoneBaseMOnly.VARIANT, EnumStoneTypeMOnly.COLDSTONE), cobblestone.getUnlocalizedName() + "_" + EnumStoneTypeMOnly.COLDSTONE.getUnlocalizedName()));
		register(cobbled_deep_coldstone_stairs = new BlockStairBase(cobblestone.getDefaultState().withProperty(BlockStoneBaseMOnly.VARIANT, EnumStoneTypeMOnly.DEEP_COLDSTONE), cobblestone.getUnlocalizedName() + "_" + EnumStoneTypeMOnly.DEEP_COLDSTONE.getUnlocalizedName()));
		register(cobbled_icestone_stairs = new BlockStairBase(cobblestone.getDefaultState().withProperty(BlockStoneBaseMOnly.VARIANT, EnumStoneTypeMOnly.ICESTONE), cobblestone.getUnlocalizedName() + "_" + EnumStoneTypeMOnly.ICESTONE.getUnlocalizedName()));
		register(cobbled_glacierrock_stairs = new BlockStairBase(cobblestone.getDefaultState().withProperty(BlockStoneBaseMOnly.VARIANT, EnumStoneTypeMOnly.GLACIERROCK), cobblestone.getUnlocalizedName() + "_" + EnumStoneTypeMOnly.GLACIERROCK.getUnlocalizedName()));
		register(cobbled_oceanstone_stairs = new BlockStairBase(cobblestone.getDefaultState().withProperty(BlockStoneBaseMOnly.VARIANT, EnumStoneTypeMOnly.OCEANSTONE), cobblestone.getUnlocalizedName() + "_" + EnumStoneTypeMOnly.OCEANSTONE.getUnlocalizedName()));
		register(cobbled_deep_oceanstone_stairs = new BlockStairBase(cobblestone.getDefaultState().withProperty(BlockStoneBaseMOnly.VARIANT, EnumStoneTypeMOnly.DEEP_OCEANSTONE), cobblestone.getUnlocalizedName() + "_" + EnumStoneTypeMOnly.DEEP_OCEANSTONE.getUnlocalizedName()));
		register(red_rock_brick_stairs = new BlockStairBase(stone_bricks.getDefaultState().withProperty(BlockStoneBaseMOnly.VARIANT, EnumStoneTypeMOnly.RED_ROCK), stone_bricks.getUnlocalizedName() + "_" + EnumStoneTypeMOnly.RED_ROCK.getUnlocalizedName()));
		register(deep_red_rock_brick_stairs = new BlockStairBase(stone_bricks.getDefaultState().withProperty(BlockStoneBaseMOnly.VARIANT, EnumStoneTypeMOnly.DEEP_RED_ROCK), stone_bricks.getUnlocalizedName() + "_" + EnumStoneTypeMOnly.DEEP_RED_ROCK.getUnlocalizedName()));
		register(deepstone_brick_stairs = new BlockStairBase(stone_bricks.getDefaultState().withProperty(BlockStoneBaseMOnly.VARIANT, EnumStoneTypeMOnly.DEEPSTONE), stone_bricks.getUnlocalizedName() + "_" + EnumStoneTypeMOnly.DEEPSTONE.getUnlocalizedName()));
		register(coldstone_brick_stairs = new BlockStairBase(stone_bricks.getDefaultState().withProperty(BlockStoneBaseMOnly.VARIANT, EnumStoneTypeMOnly.COLDSTONE), stone_bricks.getUnlocalizedName() + "_" + EnumStoneTypeMOnly.COLDSTONE.getUnlocalizedName()));
		register(deep_coldstone_brick_stairs = new BlockStairBase(stone_bricks.getDefaultState().withProperty(BlockStoneBaseMOnly.VARIANT, EnumStoneTypeMOnly.DEEP_COLDSTONE), stone_bricks.getUnlocalizedName() + "_" + EnumStoneTypeMOnly.DEEP_COLDSTONE.getUnlocalizedName()));
		register(icestone_brick_stairs = new BlockStairBase(stone_bricks.getDefaultState().withProperty(BlockStoneBaseMOnly.VARIANT, EnumStoneTypeMOnly.ICESTONE), stone_bricks.getUnlocalizedName() + "_" + EnumStoneTypeMOnly.ICESTONE.getUnlocalizedName()));
		register(glacierrock_brick_stairs = new BlockStairBase(stone_bricks.getDefaultState().withProperty(BlockStoneBaseMOnly.VARIANT, EnumStoneTypeMOnly.GLACIERROCK), stone_bricks.getUnlocalizedName() + "_" + EnumStoneTypeMOnly.GLACIERROCK.getUnlocalizedName()));
		register(oceanstone_brick_stairs = new BlockStairBase(stone_bricks.getDefaultState().withProperty(BlockStoneBaseMOnly.VARIANT, EnumStoneTypeMOnly.OCEANSTONE), stone_bricks.getUnlocalizedName() + "_" + EnumStoneTypeMOnly.OCEANSTONE.getUnlocalizedName()));
		register(deep_oceanstone_brick_stairs = new BlockStairBase(stone_bricks.getDefaultState().withProperty(BlockStoneBaseMOnly.VARIANT, EnumStoneTypeMOnly.DEEP_OCEANSTONE), stone_bricks.getUnlocalizedName() + "_" + EnumStoneTypeMOnly.DEEP_OCEANSTONE.getUnlocalizedName()));
		register(cobbled_shimmerstone_stairs = new BlockStairBase(shimmerstone.getDefaultState().withProperty(BlockShimmerstone.VARIANT, BlockShimmerstone.ShimmerstoneType.COBBLESTONE), shimmerstone.getUnlocalizedName() + "_" + BlockShimmerstone.ShimmerstoneType.COBBLESTONE.getUnlocalizedName()));
		register(shimmerstone_brick_stairs = new BlockStairBase(shimmerstone.getDefaultState().withProperty(BlockShimmerstone.VARIANT, BlockShimmerstone.ShimmerstoneType.BRICKS), shimmerstone.getUnlocalizedName() + "_" + BlockShimmerstone.ShimmerstoneType.BRICKS.getUnlocalizedName()));
		register(portar_stairs = new BlockStairBase(portar.getDefaultState().withProperty(BlockPortar.VARIANT, BlockPortar.PortarType.SLABBED), portar.getUnlocalizedName() + "_" + BlockPortar.PortarType.SLABBED.getUnlocalizedName()));
		register(gilded_brick_stairs = new BlockStairBase(gilded_stone.getDefaultState().withProperty(BlockGildedStone.VARIANT, BlockGildedStone.EnumType.BRICKS), gilded_stone.getUnlocalizedName() + "_" + BlockGildedStone.EnumType.BRICKS.getName()));
		
		register(silverfish_stone = new BlockSilverfishStone("m_silverfish_stone", stone), new ItemBlockMultistate(silverfish_stone));
		register(silverfish_cobblestone = new BlockSilverfishStone("m_silverfish_cobblestone", cobblestone), new ItemBlockMultistate(silverfish_cobblestone));
		register(silverfish_stone_bricks = new BlockSilverfishStone("m_silverfish_stone_bricks", stone_bricks), new ItemBlockMultistate(silverfish_stone_bricks));
		register(silverfish_mossy_stone_bricks = new BlockSilverfishStone("m_silverfish_mossy_stone_bricks", mossy_stone_bricks), new ItemBlockMultistate(silverfish_mossy_stone_bricks));
		register(silverfish_cracked_stone_bricks = new BlockSilverfishStone("m_silverfish_cracked_stone_bricks", cracked_stone_bricks), new ItemBlockMultistate(silverfish_cracked_stone_bricks));
		register(silverfish_chiseled_stone = new BlockSilverfishStone("m_silverfish_chiseled_stone", chiseled_stone), new ItemBlockMultistate(silverfish_chiseled_stone));
		
		//Ore
		register(desert_quartz_deposit = new BlockDesertQuartzDeposit(), new ItemBlockMultistate(desert_quartz_deposit));
		register(rock_crystal_deposit = new BlockOreDeposit("rock_crystal_deposit", Material.ROCK, MapColor.WHITE_STAINED_HARDENED_CLAY, SoundType.GLASS, 1F, "pickaxe", 0).setGlowing().setRenderLayer(BlockRenderLayer.TRANSLUCENT).setPushReaction(EnumPushReaction.DESTROY).setLightLevel(0.25F).setCreativeTab(MTabs.environment));
		register(ore_salt = new BlockStoneBase("ore_salt", Material.ROCK, SoundType.STONE, 3F, "pickaxe", 0).setResistance(5F).setCreativeTab(MTabs.ore), new ItemBlockMultistate(ore_salt));
		register(ore_coal = new BlockStoneBaseMOnly("ore_coal", Material.ROCK, SoundType.STONE, 3F, "pickaxe", 0).setDropsItem(new ItemStack(Items.COAL, 1, 0), 0, 0, 2, true, true, false).setResistance(5F).setCreativeTab(MTabs.ore), new ItemBlockMultistate(ore_coal));
		register(ore_copper = new BlockStoneBase("ore_copper", Material.ROCK, SoundType.STONE, 3F, "pickaxe", 0).setResistance(5F).setCreativeTab(MTabs.ore), new ItemBlockMultistate(ore_copper));
		register(ore_tin = new BlockStoneBase("ore_tin", Material.ROCK, SoundType.STONE, 3F, "pickaxe", 0).setResistance(5F).setCreativeTab(MTabs.ore), new ItemBlockMultistate(ore_tin));
		register(ore_iron = new BlockStoneBaseMOnly("ore_iron", Material.ROCK, SoundType.STONE, 3F, "pickaxe", 1).setResistance(5F).setCreativeTab(MTabs.ore), new ItemBlockMultistate(ore_iron));
		register(ore_gold = new BlockStoneBaseMOnly("ore_gold", Material.ROCK, SoundType.STONE, 3F, "pickaxe", 2).setResistance(5F).setCreativeTab(MTabs.ore), new ItemBlockMultistate(ore_gold));
		register(ore_meurodite = new BlockStoneBase("ore_meurodite", Material.ROCK, SoundType.STONE, 3F, "pickaxe", 2).setResistance(5F).setCreativeTab(MTabs.ore), new ItemBlockMultistate(ore_meurodite));
		register(ore_lapis = new BlockStoneBaseMOnly("ore_lapis", Material.ROCK, SoundType.STONE, 3F, "pickaxe", 1).setDropsItem(new ItemStack(Items.DYE, 4, EnumDyeColor.BLUE.getDyeDamage()), 4, 2, 5, true, true,false).setResistance(5F).setCreativeTab(MTabs.ore), new ItemBlockMultistate(ore_lapis));
		register(ore_redstone = new BlockRedstoneOre("ore_redstone", Material.ROCK, SoundType.STONE, 3F, "pickaxe", 2, false).setResistance(5F).setCreativeTab(MTabs.ore), new ItemBlockMultistate(ore_redstone));
		register(ore_redstone_lit = new BlockRedstoneOre("ore_redstone", Material.ROCK, SoundType.STONE, 3F, "pickaxe", 2, true).setResistance(5F).setLightLevel(0.625F));
		register(ore_irradium = new BlockIrradiumOre().setResistance(5F).setLightLevel(0.2F), new ItemBlockMultistate(ore_irradium));
		register(ore_torite = new BlockStoneBase("ore_torite", Material.ROCK, SoundType.STONE, 3F, "pickaxe", 0).setResistance(5F).setCreativeTab(MTabs.ore), new ItemBlockMultistate(ore_torite));
		register(sunstone_deposit = new BlockOreDeposit("sunstone_deposit", Material.ROCK, MapColor.SAND, SoundType.GLASS, 1.2F, "pickaxe", 1).setPushReaction(EnumPushReaction.DESTROY).setLightLevel(0.75F).setCreativeTab(MTabs.environment));
		register(glacieric_ice_deposit = new BlockGlaciericIceDeposit());
		register(glacieric_ice_branch_0 = new BlockGlaciericIceBranch(0));
		ForgeRegistries.BLOCKS.register(glacieric_ice_branch_1 = new BlockGlaciericIceBranch(1));
		ForgeRegistries.BLOCKS.register(glacieric_ice_branch_2 = new BlockGlaciericIceBranch(2));
		ForgeRegistries.BLOCKS.register(glacieric_ice_branch_3 = new BlockGlaciericIceBranch(3));
		ForgeRegistries.BLOCKS.register(glacieric_ice_branch_4 = new BlockGlaciericIceBranch(4));
		ForgeRegistries.BLOCKS.register(glacieric_ice_branch_5 = new BlockGlaciericIceBranch(5));
		ForgeRegistries.BLOCKS.register(glacieric_ice_branch_6 = new BlockGlaciericIceBranch(6));
		ForgeRegistries.BLOCKS.register(glacieric_ice_branch_7 = new BlockGlaciericIceBranch(7));
		register(soul_eyes = new BlockOreDeposit("soul_eyes", Material.ROCK, MapColor.CYAN, SoundType.GLASS, 1.2F, "pickaxe", 2).setPushReaction(EnumPushReaction.DESTROY).setGlowing().setLightLevel(0.3F).setCreativeTab(MTabs.environment));
		register(ore_diamond = new BlockStoneBaseMOnly("ore_diamond", Material.ROCK, SoundType.STONE, 3F, "pickaxe", 2).setDropsItem(new ItemStack(Items.DIAMOND, 1, 0), 0, 3, 7, true, true, false).setResistance(5F).setCreativeTab(MTabs.ore), new ItemBlockMultistate(ore_diamond));
		register(ore_emerald = new BlockStoneBaseMOnly("ore_emerald", Material.ROCK, SoundType.STONE, 3F, "pickaxe", 2).setDropsItem(new ItemStack(Items.EMERALD, 1, 0), 0, 3, 7, true, true, false).setResistance(5F).setCreativeTab(MTabs.ore), new ItemBlockMultistate(ore_emerald));
		register(ore_titanium = new BlockStoneBase("ore_titanium", Material.ROCK, SoundType.STONE, 3F, "pickaxe", 0).setGlowing().setLightLevel(0.2F).setResistance(100F).setCreativeTab(MTabs.ore), new ItemBlockMultistate(ore_titanium));
		register(ore_blazium = new BlockBase("ore_blazium", Material.ROCK, MapColor.NETHERRACK, SoundType.STONE, 3F, "pickaxe", 2).setResistance(5F).setLightLevel(0.675F).setCreativeTab(MTabs.ore));
		register(ore_shimmering = new BlockShimmeringOre());
		register(ore_soul = new BlockSoulOre());
		register(ore_dimensium = new BlockBase("ore_dimensium", Material.ROCK, MapColor.SAND, SoundType.STONE, 6.0F, "pickaxe", 4).setEntityInvulnerability("dragon").setLightLevel(0.5F).setCreativeTab(MTabs.ore));
		register(geode_shimmerstone_clear = new BlockGeode("geode_shimmerstone_clear", Material.ROCK, MapColor.SNOW, SoundType.STONE, 20F, "pickaxe", 4).setLightLevel(0.7F).setCreativeTab(MTabs.ore));
		register(geode_shimmerstone_dark = new BlockGeode("geode_shimmerstone_dark", Material.ROCK, MapColor.SNOW, SoundType.STONE, 20F, "pickaxe", 4).setLightLevel(0.7F).setCreativeTab(MTabs.ore));
		register(heart_spot = new BlockHeartSpot());
		ForgeRegistries.BLOCKS.register(adamantium_nugget = new BlockAdamantiumNugget());
		
		//Resource
		register(block_salt = new BlockBase("block_salt", Material.ROCK, MapColor.WHITE_STAINED_HARDENED_CLAY, SoundType.STONE, 4F, "pickaxe", 0).setResistance(10F).setCreativeTab(MTabs.resource));
		register(block_rock_crystal = new BlockBaseNonSolid("block_rock_crystal", Material.ROCK, MapColor.WHITE_STAINED_HARDENED_CLAY, SoundType.GLASS, 2F, "pickaxe", 0).setGlowing().setBeaconBase().setRenderLayer(BlockRenderLayer.TRANSLUCENT).setResistance(2F).setCreativeTab(MTabs.resource).setLightOpacity(0).setLightLevel(0.1F));
		register(block_copper = new BlockBase("block_copper", Material.IRON, MapColor.ADOBE, SoundType.METAL, 4F, "pickaxe", 0).setResistance(10F).setCreativeTab(MTabs.resource));
		register(block_tin = new BlockBase("block_tin", Material.IRON, MapColor.CLOTH, SoundType.METAL, 4F, "pickaxe", 0).setResistance(5F).setCreativeTab(MTabs.resource));
		register(block_bronze = new BlockBase("block_bronze", Material.IRON, MapColor.YELLOW_STAINED_HARDENED_CLAY, SoundType.METAL, 6F, "pickaxe", 1).setBeaconBase().setResistance(15F).setCreativeTab(MTabs.resource));
		register(block_steel = new BlockBase("block_steel", Material.IRON, MapColor.LIGHT_BLUE_STAINED_HARDENED_CLAY, SoundType.METAL, 5F, "pickaxe", 1).setBeaconBase().setResistance(12F).setCreativeTab(MTabs.resource));
		register(block_meurodite = new BlockBase("block_meurodite", Material.IRON, MapColor.BLUE_STAINED_HARDENED_CLAY, SoundType.METAL, 5F, "pickaxe", 2).setBeaconBase().setResistance(10F).setCreativeTab(MTabs.resource));
		register(block_redstone_sandy_unlit = new BlockBiomeRedstone("block_redstone_sandy_unlit", MapColor.ADOBE, 0, 20, block_redstone_sandy_lit, 149F, 67F, 0F));
		register(block_redstone_sandy_lit = new BlockBiomeRedstone("block_redstone_sandy_lit", MapColor.ADOBE, 15, 80, block_redstone_sandy_unlit, 149F, 67F, 0F));
		((BlockBiomeRedstone) block_redstone_sandy_unlit).setSwapBlock(block_redstone_sandy_lit);
		register(block_redstone_frosted_unlit = new BlockBiomeRedstone("block_redstone_frosted_unlit", MapColor.PINK, 0, 60, block_redstone_frosted_lit, 140F, 36F, 76F));
		register(block_redstone_frosted_lit = new BlockBiomeRedstone("block_redstone_frosted_lit", MapColor.PINK, 15, 40, block_redstone_frosted_unlit, 140F, 36F, 76F));
		((BlockBiomeRedstone) block_redstone_frosted_unlit).setSwapBlock(block_redstone_frosted_lit);
		register(block_redstone_icy_unlit = new BlockBiomeRedstone("block_redstone_icy_unlit", MapColor.MAGENTA, 0, 80, block_redstone_icy_lit, 159F, 30F, 198F));
		register(block_redstone_icy_lit = new BlockBiomeRedstone("block_redstone_icy_lit", MapColor.MAGENTA, 15, 20, block_redstone_icy_unlit, 159F, 30F, 198F));
		((BlockBiomeRedstone) block_redstone_icy_unlit).setSwapBlock(block_redstone_icy_lit);
		register(block_redstone_briny_unlit = new BlockBiomeRedstone("block_redstone_briny_unlit", MapColor.YELLOW, 0, 40, block_redstone_briny_lit, 172F, 150F, 27F));
		register(block_redstone_briny_lit = new BlockBiomeRedstone("block_redstone_briny_lit", MapColor.YELLOW, 15, 60, block_redstone_briny_unlit, 172F, 150F, 27F));
		((BlockBiomeRedstone) block_redstone_briny_unlit).setSwapBlock(block_redstone_briny_lit);
		register(block_irradium = new BlockIrradium("block_irradium", Material.ROCK, MapColor.LIME, SoundType.STONE, 5F, false).setCreativeTab(MTabs.resource));
		register(block_sunstone = new BlockBase("block_sunstone", Material.ROCK, MapColor.SAND, SoundType.GLASS, 2F, "pickaxe", 2).setLightLevel(0.95F).setCreativeTab(MTabs.resource));
		register(block_torite = new BlockBase("block_torite", Material.IRON, MapColor.FOLIAGE, SoundType.METAL, 5F, "pickaxe", 2).setBeaconBase().setResistance(10F).setCreativeTab(MTabs.resource));
		register(block_titanium = new BlockBase("block_titanium", Material.IRON, MapColor.BLACK, SoundType.METAL, 10F, "pickaxe", 3).setGlowing().setRenderLayer(BlockRenderLayer.CUTOUT).setBeaconBase().setResistance(6000000.0F).setLightLevel(0.2F).setCreativeTab(MTabs.resource));
		register(block_glacierite = new BlockGlacierite());
		register(block_blazium = new BlockBlazium("block_blazium", Material.IRON, MapColor.ADOBE, SoundType.METAL, 5F, "pickaxe", 2).setPushReaction(EnumPushReaction.NORMAL).setBeaconBase().setResistance(10F).setLightLevel(0.8F).setCreativeTab(MTabs.resource));
		register(block_soul = new BlockBase("block_soul", Material.IRON, MapColor.DIAMOND, SoundType.GLASS, 5F, "pickaxe", 3).setGlowing().setBeaconBase().setLightLevel(0.7F).setResistance(10F).setCreativeTab(MTabs.resource));
		register(block_mite_honey = new BlockMiteHoney());
		register(block_dimensium = new BlockDimensium("block_dimensium", Material.IRON, MapColor.MAGENTA, SoundType.METAL, 5F, "pickaxe", 2, false).setEntityInvulnerability("dragon").setBeaconBase().setResistance(10F).setCreativeTab(MTabs.resource));
		register(block_dimensium_destabilized = new BlockDimensium("block_dimensium_destabilized", Material.IRON, MapColor.MAGENTA, SoundType.METAL, -1F, "pickaxe", 999, true).setEntityInvulnerability("all").setPushReaction(EnumPushReaction.BLOCK).setBlockUnbreakable().setResistance(6000000.0F));
		
		//Dyeables
		register(glow_wool = new BlockMGlowDyed("m_wool", Material.CLOTH, MapColor.CLOTH, SoundType.CLOTH, 0.8F), new ItemBlockMultistate(glow_wool));
		register(glow_carpet = new BlockMCarpet(), new ItemBlockMultistate(glow_carpet));
		register(glow_stained_glass = new BlockMStainedGlass(), new ItemBlockMultistate(glow_stained_glass));
		register(glow_stained_glass_pane = new BlockMStainedGlassPane(), new ItemBlockMultistate(glow_stained_glass_pane));
		register(glow_concrete_powder = new BlockMConcretePowder(), new ItemBlockMultistate(glow_concrete_powder));
		register(glow_concrete = new BlockMGlowDyed("m_concrete", Material.ROCK, MapColor.SNOW, SoundType.STONE, 1.8F, "pickaxe", 0), new ItemBlockMultistate(glow_concrete));
		register(glow_terracotta = new BlockMGlowDyed("m_terracotta", Material.ROCK, MapColor.ADOBE, SoundType.STONE, 1.25F, "pickaxe", 0).setResistance(7F), new ItemBlockMultistate(glow_terracotta));
		register(glow_glazed_terracotta_white = new BlockMGlazedTerracotta("glowing_white", BlockMGlowDyed.EnumGlowDye.WHITE.getMapColor()));
		register(glow_glazed_terracotta_magenta = new BlockMGlazedTerracotta("glowing_magenta", BlockMGlowDyed.EnumGlowDye.MAGENTA.getMapColor()));
		register(glow_glazed_terracotta_red = new BlockMGlazedTerracotta("glowing_red", BlockMGlowDyed.EnumGlowDye.RED.getMapColor()));
		register(glow_glazed_terracotta_orange = new BlockMGlazedTerracotta("glowing_orange", BlockMGlowDyed.EnumGlowDye.ORANGE.getMapColor()));
		register(glow_glazed_terracotta_yellow = new BlockMGlazedTerracotta("glowing_yellow", BlockMGlowDyed.EnumGlowDye.YELLOW.getMapColor()));
		register(glow_glazed_terracotta_green = new BlockMGlazedTerracotta("glowing_green", BlockMGlowDyed.EnumGlowDye.GREEN.getMapColor()));
		register(glow_glazed_terracotta_cyan = new BlockMGlazedTerracotta("glowing_cyan", BlockMGlowDyed.EnumGlowDye.CYAN.getMapColor()));
		register(glow_glazed_terracotta_blue = new BlockMGlazedTerracotta("glowing_blue", BlockMGlowDyed.EnumGlowDye.BLUE.getMapColor()));
		register(glow_glazed_terracotta_purple = new BlockMGlazedTerracotta("glowing_purple", BlockMGlowDyed.EnumGlowDye.PURPLE.getMapColor()));
		
		//Decor
		register(glass_tiles = new BlockBaseNonSolid("glass_tiles", Material.GLASS, MapColor.AIR, SoundType.GLASS, 0.3F).setDropsItem(ItemStack.EMPTY, 0, 0, 0, true, false).setRenderLayer(BlockRenderLayer.CUTOUT).setIgnoresSimilarity().setLightOpacity(0).setCreativeTab(MTabs.decor));
		register(glass_bricks = new BlockBaseNonSolid("glass_bricks", Material.GLASS, MapColor.AIR, SoundType.GLASS, 0.3F).setDropsItem(ItemStack.EMPTY, 0, 0, 0, true, false).setRenderLayer(BlockRenderLayer.CUTOUT).setIgnoresSimilarity().setLightOpacity(0).setCreativeTab(MTabs.decor));
		register(wooden_window = new BlockBaseNonSolid("wooden_window", Material.WOOD, MapColor.WOOD, SoundType.GLASS, 1F, "axe", 0).setRenderLayer(BlockRenderLayer.CUTOUT).setIgnoresSimilarity().setLightOpacity(0).setResistance(3F).setCreativeTab(MTabs.decor));
		register(iron_window = new BlockBaseNonSolid("iron_window", Material.IRON, MapColor.IRON, SoundType.GLASS, 2F, "pickaxe", 0).setRenderLayer(BlockRenderLayer.CUTOUT).setIgnoresSimilarity().setLightOpacity(0).setResistance(5F).setCreativeTab(MTabs.decor));
		register(reinforced_glass = new BlockBaseNonSolid("reinforced_glass", Material.IRON, MapColor.LIGHT_BLUE_STAINED_HARDENED_CLAY, SoundType.GLASS, 4F, "pickaxe", 0).setRenderLayer(BlockRenderLayer.CUTOUT).setIgnoresSimilarity().setLightOpacity(0).setResistance(10F).setCreativeTab(MTabs.decor));
		register(glass_tile_pane = new BlockMPane("glass_tile_pane", Material.GLASS, false, MapColor.AIR, SoundType.GLASS).setHardness(0.3F));
		register(glass_brick_pane = new BlockMPane("glass_brick_pane", Material.GLASS, false, MapColor.AIR, SoundType.GLASS).setHardness(0.3F));
		register(wooden_window_pane = new BlockMPane("wooden_window_pane", Material.WOOD, true, MapColor.WOOD, SoundType.GLASS, "axe", 0).setHardness(1F).setResistance(3F));
		register(iron_window_pane = new BlockMPane("iron_window_pane", Material.IRON, true, MapColor.IRON, SoundType.GLASS, "pickaxe", 0).setHardness(2F).setResistance(5F));
		register(reinforced_glass_pane = new BlockMPane("reinforced_glass_pane", Material.IRON, true, MapColor.LIGHT_BLUE_STAINED_HARDENED_CLAY, SoundType.GLASS, "pickaxe", 0).setHardness(4F).setResistance(10F));
		register(steel_mesh = new BlockMPane("steel_mesh", Material.IRON, true, MapColor.LIGHT_BLUE_STAINED_HARDENED_CLAY, SoundType.METAL, "pickaxe", 1).setHardness(5.0F).setResistance(12.0F));
		register(honeycomb_bronze = new BlockBase("honeycomb_bronze", Material.IRON, MapColor.YELLOW_STAINED_HARDENED_CLAY, SoundType.METAL, 20F, "pickaxe", 2).setResistance(6000000.0F).setCreativeTab(MTabs.decor));
		register(honeycomb_steel = new BlockBase("honeycomb_steel", Material.IRON, MapColor.LIGHT_BLUE_STAINED_HARDENED_CLAY, SoundType.METAL, -1F, "pickaxe", 2).setBlockUnbreakable().setResistance(15F).setCreativeTab(MTabs.decor));
		register(honeycomb_meurodite = new BlockBase("honeycomb_meurodite", Material.IRON, MapColor.BLACK_STAINED_HARDENED_CLAY, SoundType.METAL, 20F, "pickaxe", 2).setPushReaction(EnumPushReaction.BLOCK).setResistance(15F).setCreativeTab(MTabs.decor));
		register(rope = new BlockRope());
		register(dimensium_rope = new BlockDimensiumRope());
		ForgeRegistries.BLOCKS.register(door_charwood = new BlockMDoor("door_charwood", Material.WOOD, BlockMPlanks.EnumType.CHARWOOD.getMapColor()));
		ForgeRegistries.BLOCKS.register(door_palm = new BlockMDoor("door_palm", Material.WOOD, BlockMPlanks.EnumType.PALM.getMapColor()));
		register(bauble_ice = new BlockBauble("bauble_ice", Material.ICE, MapColor.ICE, SoundType.GLASS, 0.5F, "pickaxe", 0).setRenderLayer(BlockRenderLayer.TRANSLUCENT).setSlipperiness(0.98F).setPushReaction(EnumPushReaction.DESTROY).setLightOpacity(3).setCreativeTab(MTabs.decor));
		register(bauble_sunstone = new BlockBauble("bauble_sunstone", Material.ROCK, MapColor.SAND, SoundType.GLASS, 2F, "pickaxe", 2).setPushReaction(EnumPushReaction.DESTROY).setLightLevel(0.85F).setCreativeTab(MTabs.decor));
		register(bauble_glowstone = new BlockBauble("bauble_glowstone", Material.GLASS, MapColor.SAND, SoundType.GLASS, 0.3F).setDropsItem(new ItemStack(Items.GLOWSTONE_DUST), 0, 0, 0, true, false).setPushReaction(EnumPushReaction.DESTROY).setLightLevel(0.9F).setCreativeTab(MTabs.decor));
		register(bauble_glowshroom_blue = new BlockBauble("bauble_glowshroom_blue", Material.WOOD, MapColor.BLUE, SoundType.WOOD, 0.2F).setRenderLayer(BlockRenderLayer.TRANSLUCENT).setPushReaction(EnumPushReaction.DESTROY).setLightLevel(0.5F).setCreativeTab(MTabs.decor));
		register(bauble_glowshroom_green = new BlockBauble("bauble_glowshroom_green", Material.WOOD, MapColor.LIME, SoundType.WOOD, 0.2F).setRenderLayer(BlockRenderLayer.TRANSLUCENT).setPushReaction(EnumPushReaction.DESTROY).setLightLevel(0.7F).setCreativeTab(MTabs.decor));
		register(bauble_glowshroom_purple = new BlockBauble("bauble_glowshroom_purple", Material.WOOD, MapColor.PURPLE, SoundType.WOOD, 0.2F).setRenderLayer(BlockRenderLayer.TRANSLUCENT).setPushReaction(EnumPushReaction.DESTROY).setLightLevel(0.6F).setCreativeTab(MTabs.decor));
		register(bauble_blazium = new BlockBauble("bauble_blazium", Material.IRON, MapColor.ADOBE, SoundType.METAL, 5F, "pickaxe", 2).setLightLevel(0.7F).setResistance(5F).setCreativeTab(MTabs.decor));
		register(cobblestone_wall = new BlockCobblestoneWall(cobblestone, "m_cobblestone_wall"), new ItemBlockMultistate(cobblestone_wall));
		register(mossy_cobblestone_wall = new BlockCobblestoneWall(mossy_cobblestone, "m_mossy_cobblestone_wall"), new ItemBlockMultistate(mossy_cobblestone_wall));
		register(candle = new BlockCandleNormal("candle", "unlit"), new ItemBlockMultistate(candle));
		ForgeRegistries.BLOCKS.register(candle_fire = new BlockCandleNormal("candle_fire", "fire"));
		ForgeRegistries.BLOCKS.register(candle_ender = new BlockCandleNormal("candle_ender", "ender"));
		register(candle2 = new BlockCandleGlowing("candle_2", "unlit"), new ItemBlockMultistate(candle2));
		ForgeRegistries.BLOCKS.register(candle2_fire = new BlockCandleGlowing("candle_2_fire", "fire"));
		ForgeRegistries.BLOCKS.register(candle2_ender = new BlockCandleGlowing("candle_2_ender", "ender"));
		register(chandelier_halloween = new BlockChandelierHalloween("halloween_chandelier", "unlit"), new ItemBlockMultistate(chandelier_halloween));
		ForgeRegistries.BLOCKS.register(chandelier_halloween_fire = new BlockChandelierHalloween("halloween_chandelier_fire", "fire"));
		ForgeRegistries.BLOCKS.register(chandelier_halloween_ender = new BlockChandelierHalloween("halloween_chandelier_ender", "ender"));
		((BlockChandelier) chandelier_halloween).setFireBlock(chandelier_halloween_fire).setEnderBlock(chandelier_halloween_ender);
		((BlockChandelier) chandelier_halloween_fire).setUnlitBlock(chandelier_halloween);
		((BlockChandelier) chandelier_halloween_ender).setUnlitBlock(chandelier_halloween);
		register(chandelier_new_year = new BlockChandelierNewYear("new_year_chandelier", "unlit"), new ItemBlockMultistate(chandelier_new_year));
		ForgeRegistries.BLOCKS.register(chandelier_new_year_fire = new BlockChandelierNewYear("new_year_chandelier_fire", "fire"));
		ForgeRegistries.BLOCKS.register(chandelier_new_year_ender = new BlockChandelierNewYear("new_year_chandelier_ender", "ender"));
		((BlockChandelier) chandelier_new_year).setFireBlock(chandelier_new_year_fire).setEnderBlock(chandelier_new_year_ender);
		((BlockChandelier) chandelier_new_year_fire).setUnlitBlock(chandelier_new_year);
		((BlockChandelier) chandelier_new_year_ender).setUnlitBlock(chandelier_new_year);
		register(pumpkin_normie = new BlockJackOLantern("pumpkin_normie", "unlit", pumpkin_normie_fire, pumpkin_normie_ender), new ItemBlockMultistate(pumpkin_normie));
		register(pumpkin_normie_fire = new BlockJackOLantern("pumpkin_normie_fire", "fire", pumpkin_normie, pumpkin_normie_ender));
		register(pumpkin_normie_ender = new BlockJackOLantern("pumpkin_normie_ender", "ender", pumpkin_normie, pumpkin_normie_fire));
		pumpkin_normie.setBlockVariants(pumpkin_normie, pumpkin_normie_fire, pumpkin_normie_ender);
		register(pumpkin_grumpy = new BlockJackOLantern("pumpkin_grumpy", "unlit", pumpkin_grumpy_fire, pumpkin_grumpy_ender), new ItemBlockMultistate(pumpkin_grumpy));
		register(pumpkin_grumpy_fire = new BlockJackOLantern("pumpkin_grumpy_fire", "fire", pumpkin_grumpy, pumpkin_grumpy_ender));
		register(pumpkin_grumpy_ender = new BlockJackOLantern("pumpkin_grumpy_ender", "ender", pumpkin_grumpy, pumpkin_grumpy_fire));
		pumpkin_grumpy.setBlockVariants(pumpkin_grumpy, pumpkin_grumpy_fire, pumpkin_grumpy_ender);
		register(pumpkin_dumpy = new BlockJackOLantern("pumpkin_dumpy", "unlit", pumpkin_dumpy_fire, pumpkin_dumpy_ender), new ItemBlockMultistate(pumpkin_dumpy));
		register(pumpkin_dumpy_fire = new BlockJackOLantern("pumpkin_dumpy_fire", "fire", pumpkin_dumpy, pumpkin_dumpy_ender));
		register(pumpkin_dumpy_ender = new BlockJackOLantern("pumpkin_dumpy_ender", "ender", pumpkin_dumpy, pumpkin_dumpy_fire));
		pumpkin_dumpy.setBlockVariants(pumpkin_dumpy, pumpkin_dumpy_fire, pumpkin_dumpy_ender);
		register(pumpkin_creepy = new BlockJackOLantern("pumpkin_creepy", "unlit", pumpkin_creepy_fire, pumpkin_creepy_ender), new ItemBlockMultistate(pumpkin_creepy));
		register(pumpkin_creepy_fire = new BlockJackOLantern("pumpkin_creepy_fire", "fire", pumpkin_creepy, pumpkin_creepy_ender));
		register(pumpkin_creepy_ender = new BlockJackOLantern("pumpkin_creepy_ender", "ender", pumpkin_creepy, pumpkin_creepy_fire));
		pumpkin_creepy.setBlockVariants(pumpkin_creepy, pumpkin_creepy_fire, pumpkin_creepy_ender);
		register(pumpkin_smiley = new BlockJackOLantern("pumpkin_smiley", "unlit", pumpkin_smiley_fire, pumpkin_smiley_ender), new ItemBlockMultistate(pumpkin_smiley));
		register(pumpkin_smiley_fire = new BlockJackOLantern("pumpkin_smiley_fire", "fire", pumpkin_smiley, pumpkin_smiley_ender));
		register(pumpkin_smiley_ender = new BlockJackOLantern("pumpkin_smiley_ender", "ender", pumpkin_smiley, pumpkin_smiley_fire));
		pumpkin_smiley.setBlockVariants(pumpkin_smiley, pumpkin_smiley_fire, pumpkin_smiley_ender);
		register(pumpkin_smashed = new BlockJackOLanternSmashed("pumpkin_smashed", "unlit", pumpkin_smashed_fire, pumpkin_smashed_ender));
		register(pumpkin_smashed_fire = new BlockJackOLanternSmashed("pumpkin_smashed_fire", "fire", pumpkin_smashed, pumpkin_smashed_ender));
		register(pumpkin_smashed_ender = new BlockJackOLanternSmashed("pumpkin_smashed_ender", "ender", pumpkin_smashed, pumpkin_smashed_fire));
		register(skull_bat = new BlockMSkull("skull_bat", 4, 4, 0));
		register(skull_parrot = new BlockMSkull("skull_parrot", 4, 4, 0));
		register(skull_chicken = new BlockMSkull("skull_chicken", 4, 6, 0));
		register(skull_rabbit = new BlockMSkull("skull_rabbit", 5, 10, 3));
		register(skull_pig = new BlockMSkull("skull_pig", 8, 8, 0));
		register(skull_sheep = new BlockMSkull("skull_sheep", 8, 6, 0));
		register(skull_cow = new BlockMSkull("skull_cow", 8, 8, 0));
		register(skull_llama = new BlockMSkull("skull_llama", 8, 18, 1));
		register(skull_horse = new BlockMSkull("skull_horse", 7, 5, 2));
		register(skull_ocelot = new BlockMSkull("skull_ocelot", 5, 4, 0));
		register(skull_wolf = new BlockMSkull("skull_wolf", 6, 6, 0));
		register(skull_polar_bear = new BlockMSkull("skull_polar_bear", 7, 7, 0));
		register(skull_squid = new BlockMSkull("skull_squid", 12, 16, 0));
		register(skull_villager = new BlockMSkull("skull_villager", 8, 10, 0));
		register(skull_husk = new BlockMSkull("skull_husk", 8, 8, 0));
		register(skull_stray = new BlockMSkull("skull_stray", 8, 8, 0));
		register(skull_creeper = new BlockMSkull("skull_creeper", 8, 8, 0));
		register(skull_guardian = new BlockMSkull("skull_guardian", 16, 16, 0));
		register(skull_enderman = new BlockMSkull("skull_enderman", 8, 13, 0));
		register(skull_shulker = new BlockMSkull("skull_shulker", 6, 6, 0));
		register(plate_weave = new BlockPlate("plate_weave", Material.CLOTH, MapColor.SAND, SoundType.CLOTH, 0.6F, 2, 3));
		register(plate_slate = new BlockPlate("plate_slate", Material.ROCK, MapColor.GRAY, SoundType.STONE, 0.75F, 1, 1, "pickaxe", 0));
		register(plate_metal = new BlockPlateMetal(), new ItemBlockMultistate(plate_metal));
		
		//Utility
		register(red_rock_road = new BlockRoad("red_rock_road", 0.2F, 1.4D));
		register(stone_road = new BlockRoad("stone_road", 0.2F, 1.4D));
		register(coldstone_road = new BlockRoad("coldstone_road", 0.2F, 1.4D));
		register(icestone_road = new BlockRoad("icestone_road", 0.2F, 1.4D));
		register(oceanstone_road = new BlockRoad("oceanstone_road", 0.2F, 1.4D));
		register(netherrack_road = new BlockRoad("netherrack_road", 0.2F, 1.6D));
		register(end_stone_road = new BlockRoad("end_stone_road", 0.2F, 1.8D));
		register(basket = new BlockBasket("basket").setCreativeTab(MTabs.utility));
		register(basket_cheesemaking = new BlockBasketCheesemaking().setCreativeTab(MTabs.utility), new ItemBlockMultistate(basket_cheesemaking));
		register(crate = new BlockCrate());
		register(barrel = new BlockBarrel());
		register(tanning_rack = new BlockTanningRack());
		register(glacieric_ice = new BlockBaseNonSolid("glacieric_ice", Material.PACKED_ICE, MapColor.ICE, SoundType.GLASS, 0.7F).setRenderLayer(BlockRenderLayer.TRANSLUCENT).setSlipperiness(1.1F).setCreativeTab(MTabs.utility));
		register(lava_sponge = new BlockLavaSponge(), new ItemBlockMultistate(lava_sponge));
		register(mite_eggsack = new BlockMiteEggsack());
		register(block_irradiant_sunstone = new BlockIrradiantSunstone().setLightLevel(1F).setCreativeTab(MTabs.utility));
		register(godstone = new BlockGodstone());
		register(spike_oak_wood = new BlockSpike("spike_oak", Material.WOOD, BlockPlanks.EnumType.OAK.getMapColor(), SoundType.WOOD, 2F, "axe", 0, ToolMaterial.WOOD).setFlammable(20, 5).setCreativeTab(MTabs.utility));
		register(spike_spruce_wood = new BlockSpike("spike_spruce", Material.WOOD, BlockPlanks.EnumType.SPRUCE.getMapColor(), SoundType.WOOD, 2F, "axe", 0, ToolMaterial.WOOD).setFlammable(20, 5).setCreativeTab(MTabs.utility));
		register(spike_birch_wood = new BlockSpike("spike_birch", Material.WOOD, BlockPlanks.EnumType.BIRCH.getMapColor(), SoundType.WOOD, 2F, "axe", 0, ToolMaterial.WOOD).setFlammable(20, 5).setCreativeTab(MTabs.utility));
		register(spike_jungle_wood = new BlockSpike("spike_jungle", Material.WOOD, BlockPlanks.EnumType.JUNGLE.getMapColor(), SoundType.WOOD, 2F, "axe", 0, ToolMaterial.WOOD).setFlammable(20, 5).setCreativeTab(MTabs.utility));
		register(spike_acacia_wood = new BlockSpike("spike_acacia", Material.WOOD, BlockPlanks.EnumType.ACACIA.getMapColor(), SoundType.WOOD, 2F, "axe", 0, ToolMaterial.WOOD).setFlammable(20, 5).setCreativeTab(MTabs.utility));
		register(spike_dark_oak_wood = new BlockSpike("spike_dark_oak", Material.WOOD, BlockPlanks.EnumType.DARK_OAK.getMapColor(), SoundType.WOOD, 2F, "axe", 0, ToolMaterial.WOOD).setFlammable(20, 5).setCreativeTab(MTabs.utility));
		register(spike_redwood_wood = new BlockSpike("spike_redwood", Material.WOOD, BlockMPlanks.EnumType.REDWOOD.getMapColor(), SoundType.WOOD, 2F, "axe", 0, ToolMaterial.WOOD).setFlammable(20, 5).setCreativeTab(MTabs.utility));
		register(spike_frozen_oak_wood = new BlockSpike("spike_frozen_oak", Material.WOOD, BlockMPlanks.EnumType.FROZEN_OAK.getMapColor(), SoundType.WOOD, 2F, "axe", 0, ToolMaterial.WOOD).setFlammable(20, 5).setCreativeTab(MTabs.utility));
		register(spike_charwood_wood = new BlockSpike("spike_charwood", Material.WOOD, BlockMPlanks.EnumType.CHARWOOD.getMapColor(), SoundType.WOOD, 2F, "axe", 0, ToolMaterial.WOOD).setCreativeTab(MTabs.utility));
		register(spike_palm_wood = new BlockSpike("spike_palm", Material.WOOD, BlockMPlanks.EnumType.PALM.getMapColor(), SoundType.WOOD, 2F, "axe", 0, ToolMaterial.WOOD).setFlammable(20, 5).setCreativeTab(MTabs.utility));
		register(spike_red_rock = new BlockSpike("spike_red_rock", Material.ROCK, EnumStoneType.RED_ROCK.getMapColor(), SoundType.STONE, 1.5F, "pickaxe", 0, ToolMaterial.STONE).setCreativeTab(MTabs.utility));
		register(spike_stone = new BlockSpike("spike_stone", Material.ROCK, EnumStoneType.STONE.getMapColor(), SoundType.STONE, 1.5F, "pickaxe", 0, ToolMaterial.STONE).setCreativeTab(MTabs.utility));
		register(spike_coldstone = new BlockSpike("spike_coldstone", Material.ROCK, EnumStoneType.COLDSTONE.getMapColor(), SoundType.STONE, 1.5F, "pickaxe", 0, ToolMaterial.STONE).setCreativeTab(MTabs.utility));
		register(spike_icestone = new BlockSpike("spike_icestone", Material.ROCK, EnumStoneType.ICESTONE.getMapColor(), SoundType.STONE, 1.5F, "pickaxe", 0, ToolMaterial.STONE).setCreativeTab(MTabs.utility));
		register(spike_oceanstone = new BlockSpike("spike_oceanstone", Material.ROCK, EnumStoneType.OCEANSTONE.getMapColor(), SoundType.STONE, 1.5F, "pickaxe", 0, ToolMaterial.STONE).setCreativeTab(MTabs.utility));
		register(spike_copper = new BlockSpike("spike_copper", Material.IRON, MapColor.ADOBE, SoundType.METAL, 4F, "pickaxe", 0, MItems.COPPER).setResistance(10F).setCreativeTab(MTabs.utility));
		register(spike_tin = new BlockSpike("spike_tin", Material.IRON, MapColor.CLOTH, SoundType.METAL, 4F, "pickaxe", 0, MItems.COPPER).setResistance(5F).setCreativeTab(MTabs.utility));
		register(spike_iron = new BlockSpike("spike_iron", Material.IRON, MapColor.IRON, SoundType.METAL, 5F, "pickaxe", 1, ToolMaterial.IRON).setResistance(10F).setCreativeTab(MTabs.utility));
		register(spike_bronze = new BlockSpike("spike_bronze", Material.IRON, MapColor.YELLOW_STAINED_HARDENED_CLAY, SoundType.METAL, 6F, "pickaxe", 1, MItems.BRONZE).setResistance(15F).setCreativeTab(MTabs.utility));
		register(spike_steel = new BlockSpike("spike_steel", Material.IRON, MapColor.LIGHT_BLUE_STAINED_HARDENED_CLAY, SoundType.METAL, 5F, "pickaxe", 1, MItems.STEEL).setResistance(12F).setCreativeTab(MTabs.utility));
		register(spike_gold = new BlockSpike("spike_gold", Material.IRON, MapColor.GOLD, SoundType.METAL, 3F, "pickaxe", 2, ToolMaterial.GOLD).setResistance(10F).setCreativeTab(MTabs.utility));
		register(spike_meurodite = new BlockSpike("spike_meurodite", Material.IRON, MapColor.BLUE_STAINED_HARDENED_CLAY, SoundType.METAL, 5F, "pickaxe", 2, MItems.MEURODITE).setResistance(10F).setCreativeTab(MTabs.utility));
		register(spike_torite = new BlockSpike("spike_torite", Material.IRON, MapColor.FOLIAGE, SoundType.METAL, 5F, "pickaxe", 2, MItems.TORITE).setResistance(10F).setCreativeTab(MTabs.utility));
		register(spike_diamond = new BlockSpike("spike_diamond", Material.IRON, MapColor.DIAMOND, SoundType.METAL, 5F, "pickaxe", 2, ToolMaterial.DIAMOND).setResistance(10F).setCreativeTab(MTabs.utility));
		register(spike_archantine = new BlockSpike("spike_archantine", Material.IRON, MapColor.BLACK, SoundType.METAL, 10F, "pickaxe", 3, MItems.TITANIUM).setGlowing().setLightLevel(0.2F).setResistance(6000000.0F).setCreativeTab(MTabs.utility));
		register(spike_glacierite = new BlockSpike("spike_glacierite", Material.IRON, MapColor.LAPIS, SoundType.METAL, 5F, "pickaxe", 2, MItems.GLACIERITE).setResistance(10F).setCreativeTab(MTabs.utility));
		register(spike_blazium = new BlockSpike("spike_blazium", Material.IRON, MapColor.ADOBE, SoundType.METAL, 5F, "pickaxe", 2, MItems.BLAZIUM).setResistance(10F).setLightLevel(0.8F).setCreativeTab(MTabs.utility));
		register(spike_dimensium = new BlockSpike("spike_dimensium", Material.IRON, MapColor.MAGENTA, SoundType.METAL, 5F, "pickaxe", 2, MItems.TITANIUM).setLightLevel(0.5F).setResistance(10F).setCreativeTab(MTabs.utility));
		register(magnet_piston_1 = new BlockMagnetPistonBase(1).setRegistryName("magnet_piston1").setUnlocalizedName("magnet_piston").setCreativeTab(MTabs.utility));
		ForgeRegistries.BLOCKS.register(magnet_piston_2 = new BlockMagnetPistonBase(2).setRegistryName("magnet_piston2").setUnlocalizedName("magnet_piston"));
		ForgeRegistries.BLOCKS.register(magnet_piston_3 = new BlockMagnetPistonBase(3).setRegistryName("magnet_piston3").setUnlocalizedName("magnet_piston"));
		ForgeRegistries.BLOCKS.register(magnet_piston_4 = new BlockMagnetPistonBase(4).setRegistryName("magnet_piston4").setUnlocalizedName("magnet_piston"));
		ForgeRegistries.BLOCKS.register(magnet_piston_head = new BlockMagnetPistonExtension().setRegistryName("magnet_piston_head"));
		ForgeRegistries.BLOCKS.register(magnet_piston_extension = new BlockMagnetPistonMoving().setRegistryName("magnet_piston_extension"));
		register(block_irradium_insulated = new BlockIrradium("block_irradium_insulated", Material.IRON, MapColor.LIGHT_BLUE_STAINED_HARDENED_CLAY, SoundType.METAL, 6.5F, true).setCreativeTab(MTabs.utility));
		register(block_dimensium_stabilized_inactive = new BlockStabilizedDimensium("block_dimensium_stabilized_inactive", false).setBeaconBase().setLightLevel(0.5F).setCreativeTab(MTabs.utility));
		ForgeRegistries.BLOCKS.register(block_dimensium_stabilized_active = new BlockStabilizedDimensium("block_dimensium_stabilized_active", true));
		register(stasis_field = new BlockStasisField());
		register(pipe = new BlockPipe("pipe", false, false));
		register(covered_pipe_red_rock = new BlockPipe("covered_pipe_red_rock", true, false));
		register(covered_pipe_deep_red_rock = new BlockPipe("covered_pipe_deep_red_rock", true, false));
		register(covered_pipe_stone = new BlockPipe("covered_pipe_stone", true, false));
		register(covered_pipe_deepstone = new BlockPipe("covered_pipe_deepstone", true, false));
		register(covered_pipe_coldstone = new BlockPipe("covered_pipe_coldstone", true, false));
		register(covered_pipe_deep_coldstone = new BlockPipe("covered_pipe_deep_coldstone", true, false));
		register(covered_pipe_icestone = new BlockPipe("covered_pipe_icestone", true, false));
		register(covered_pipe_glacierrock = new BlockPipe("covered_pipe_glacierrock", true, false));
		register(covered_pipe_oceanstone = new BlockPipe("covered_pipe_oceanstone", true, false));
		register(covered_pipe_reefstone = new BlockPipe("covered_pipe_reefstone", true, false));
		register(sorter = new BlockSorter("sorter"));
		register(activator = new BlockActivator("activator"));
		//register(wooden_axel = new BlockAxel("axel_wood", Material.WOOD, MapColor.WOOD, SoundType.WOOD, 1.5F, "axe", 0));
		//register(creative_engine = new BlockCreativeEngine());
		register(sawmill = new BlockSawmill());
		register(alloy = new BlockAlloy().setPushReaction(EnumPushReaction.BLOCK).setCreativeTab(MTabs.utility));
		register(pressurizer = new BlockPressurizer().setPushReaction(EnumPushReaction.BLOCK).setCreativeTab(MTabs.utility));
		register(crusher = new BlockCrusher().setPushReaction(EnumPushReaction.BLOCK).setCreativeTab(MTabs.utility));
		register(soulsteel_vessel = new BlockSoulsteelVessel().setHardness(3.0F).setCreativeTab(MTabs.utility));
		register(redstone_sandy = new BlockBiomeRedstoneWire("redstone_sandy", 149F, 67F, 0F));
		register(redstone_frosted = new BlockBiomeRedstoneWire("redstone_frosted", 140F, 36F, 76F));
		register(redstone_icy = new BlockBiomeRedstoneWire("redstone_icy", 159F, 30F, 198F));
		register(redstone_briny = new BlockBiomeRedstoneWire("redstone_briny", 172F, 150F, 27F));
		register(glow_paste = new BlockLightPaste(), new ItemBlockContainer(glow_paste, new ItemStack(Items.GLASS_BOTTLE)));
		
		//Crops
		ForgeRegistries.BLOCKS.register(crop_withered = new CropWithered("crop_withered"));
		ForgeRegistries.BLOCKS.register(crop_pepper = new PepperPlant("crop_pepper"));
		ForgeRegistries.BLOCKS.register(crop_cabbage = new CabbagePlant("crop_cabbage"));
		ForgeRegistries.BLOCKS.register(crop_celery = new CeleryPlant("crop_celery"));
		ForgeRegistries.BLOCKS.register(crop_lettuce = new LettucePlant("crop_lettuce"));
		ForgeRegistries.BLOCKS.register(crop_onion = new OnionPlant("crop_onion"));
		ForgeRegistries.BLOCKS.register(crop_peanuts = new PeanutsPlant("crop_peanuts"));
		ForgeRegistries.BLOCKS.register(crop_tomato = new TomatoPlant("crop_tomato"));
		ForgeRegistries.BLOCKS.register(crop_corn = new CornPlant("crop_corn"));
		
		register(liquid_crystalfloe = new LiquidCrystalfloe(MFluids.crystalfloe, MMaterials.CRYSTALFLOE).setUnlocalizedName("crystalfloe").setRegistryName("crystalfloe"));
		
		Blocks.BEDROCK.setHardness(100F).setResistance(160000F).setHarvestLevel("pickaxe", 4);
		Blocks.CHORUS_FLOWER.setLightLevel(0.7F);
		
		register(hacky_jei_fix_light_freezing = new BlockBase("hacky_jei_fix_light_freezing").setTooltip("Glacierite Tools & Armor"));
		register(hacky_jei_fix_deep_freezing = new BlockBase("hacky_jei_fix_deep_freezing").setTooltip("Frost-Walker Glacierite Boots"));
		register(hacky_jei_fix_sieving = new BlockBase("hacky_jei_fix_sieving"));
		register(hacky_jei_fix_sawmill_all = new BlockBase("hacky_jei_fix_sawmill_all").setTooltip("Any Blade"));
		register(hacky_jei_fix_sawmill_stone = new BlockBase("hacky_jei_fix_sawmill_stone").setTooltip("Steel or Archantine Blade"));
	}
	
	public static void register(Block block)
	{
		ForgeRegistries.BLOCKS.register(block);
		ForgeRegistries.ITEMS.register(new MItemBlock(block).setRegistryName(block.getRegistryName()));
		blockList.add(block);
	}
	
	public static void register(BlockMSkull block)
	{
		ForgeRegistries.BLOCKS.register(block);
		ForgeRegistries.ITEMS.register(new ItemBlockMSkull(block).setRegistryName(block.getRegistryName()));
		blockList.add(block);
	}
	
	public static void register(Block block, MItemBlock itemBlock)
	{
		ForgeRegistries.BLOCKS.register(block);
		ForgeRegistries.ITEMS.register(itemBlock.setRegistryName(block.getRegistryName()));
	}
	
	public static void register(Block block, ItemBlockContainer itemBlock)
	{
		ForgeRegistries.BLOCKS.register(block);
		ForgeRegistries.ITEMS.register(itemBlock.setRegistryName(block.getRegistryName()));
		blockList.add(block);
	}
	
	public static void register(Block block, ItemBlockPanel itemBlock)
	{
		ForgeRegistries.BLOCKS.register(block);
		ForgeRegistries.ITEMS.register(itemBlock.setRegistryName(block.getRegistryName()));
		blockList.add(block);
	}
	
	public static void registerRenders()
	{
		for(Block block: blockList)
		{
			initModel(block);
		}
		for(int i = 0 ; i < 2 ; i++)
		{
			String suffix = i == 0 ? "dry" : "wet";
			initModel(lava_sponge, i, "lava_sponge_" + suffix);
		}
		for(int i = 0 ; i < BlockColdSand.EnumType.values().length ; i++)
		{
			initModel(cold_sand, i, "cold_sand_" + BlockColdSand.EnumType.values()[i].getName());
		}
		for(int i = 0 ; i < BlockMDirt.DirtType.values().length ; i++)
		{
			initModel(clay_soil, i, "clay_soil_" + BlockMDirt.DirtType.values()[i].getName());
			initModel(permafrost, i, "permafrost_" + BlockMDirt.DirtType.values()[i].getName());
			initModel(portal_dust, i, "portal_dust_" + BlockMDirt.DirtType.values()[i].getName());
		}
		for(int i = 0 ; i < BlockMPlanks.EnumType.values().length ; i++)
		{
			initModel(log, i, "m_log_" + BlockMPlanks.EnumType.values()[i].getName());
			initModel(planks, i, "m_planks_" + BlockMPlanks.EnumType.values()[i].getName());
			initModel(mossy_m_planks, i, "m_planks_mossy_" + BlockMPlanks.EnumType.values()[i].getName());
			initModel(fence, i, "m_fence_" + BlockMPlanks.EnumType.values()[i].getName());
			if(i < 2)
				initModel(leaves, i, "m_leaves_" + BlockMPlanks.EnumType.values()[i].getName());
		}
		for(int i = 0 ; i < BlockDecorativeStones.DecorStoneType.values().length ; i++)
		{
			initModel(decor_stone, i, "decor_stone_" + BlockDecorativeStones.DecorStoneType.values()[i].getName());
		}
		for(int i = 0 ; i < BlockMSandstone.SandstoneType.values().length ; i++)
		{
			initModel(sandstone, i, "m_sandstone_" + BlockMSandstone.SandstoneType.values()[i].getName());
		}
		for(int i = 0 ; i < EnumStoneTypeMOnly.values().length ; i++)
		{
			initModel(ore_coal, i, "ore_coal_" + EnumStoneTypeMOnly.values()[i].getName());
			initModel(ore_iron, i, "ore_iron_" + EnumStoneTypeMOnly.values()[i].getName());
			initModel(ore_gold, i, "ore_gold_" + EnumStoneTypeMOnly.values()[i].getName());
			initModel(ore_lapis, i, "ore_lapis_" + EnumStoneTypeMOnly.values()[i].getName());
			initModel(ore_redstone, i, "ore_redstone_" + EnumStoneTypeMOnly.values()[i].getName());
			initModel(ore_redstone_lit, i, "ore_redstone_" + EnumStoneTypeMOnly.values()[i].getName());
			initModel(ore_diamond, i, "ore_diamond_" + EnumStoneTypeMOnly.values()[i].getName());
			initModel(ore_emerald, i, "ore_emerald_" + EnumStoneTypeMOnly.values()[i].getName());
			
			initModel(stone, i, "m_stone_" + EnumStoneTypeMOnly.values()[i].getName());
			initModel(cobblestone, i, "m_cobblestone_" + EnumStoneTypeMOnly.values()[i].getName());
			initModel(mossy_cobblestone, i, "m_mossy_cobblestone_" + EnumStoneTypeMOnly.values()[i].getName());
			initModel(stone_bricks, i, "m_stone_bricks_" + EnumStoneTypeMOnly.values()[i].getName());
			initModel(mossy_stone_bricks, i, "m_stone_bricks_mossy_" + EnumStoneTypeMOnly.values()[i].getName());
			initModel(cracked_stone_bricks, i, "m_stone_bricks_cracked_" + EnumStoneTypeMOnly.values()[i].getName());
			initModel(chiseled_stone, i, "m_chiseled_stone_" + EnumStoneTypeMOnly.values()[i].getName());
			
			initModel(cobblestone_wall, i, "m_cobblestone_wall_" + EnumStoneTypeMOnly.values()[i].getName());
			initModel(mossy_cobblestone_wall, i, "m_mossy_cobblestone_wall_" + EnumStoneTypeMOnly.values()[i].getName());
			
			initModel(silverfish_stone, i, "m_silverfish_stone_" + EnumStoneTypeMOnly.values()[i].getName());
			initModel(silverfish_cobblestone, i, "m_silverfish_cobblestone_" + EnumStoneTypeMOnly.values()[i].getName());
			initModel(silverfish_stone_bricks, i, "m_silverfish_stone_bricks_" + EnumStoneTypeMOnly.values()[i].getName());
			initModel(silverfish_mossy_stone_bricks, i, "m_silverfish_mossy_stone_bricks_" + EnumStoneTypeMOnly.values()[i].getName());
			initModel(silverfish_cracked_stone_bricks, i, "m_silverfish_cracked_stone_bricks_" + EnumStoneTypeMOnly.values()[i].getName());
			initModel(silverfish_chiseled_stone, i, "m_silverfish_chiseled_stone_" + EnumStoneTypeMOnly.values()[i].getName());
		}
		for(int i = 0 ; i < EnumStoneType.values().length ; i++)
		{
			initModel(ore_salt, i, "ore_salt_" + EnumStoneType.values()[i].getName());
			initModel(ore_copper, i, "ore_copper_" + EnumStoneType.values()[i].getName());
			initModel(ore_tin, i, "ore_tin_" + EnumStoneType.values()[i].getName());
			initModel(ore_meurodite, i, "ore_meurodite_" + EnumStoneType.values()[i].getName());
			initModel(ore_irradium, i, "ore_irradium_" + EnumStoneType.values()[i].getName());
			initModel(ore_torite, i, "ore_torite_" + EnumStoneType.values()[i].getName());
			initModel(ore_titanium, i, "ore_titanium_" + EnumStoneType.values()[i].getName());
		}
		for(int i = 0 ; i < BlockShimmerstone.ShimmerstoneType.values().length ; i++)
		{
			initModel(shimmerstone, i, "shimmerstone_" + BlockShimmerstone.ShimmerstoneType.values()[i].getName());
		}
		for(int i = 0 ; i < 3 ; i++)
		{
			initModel(desert_quartz, i, "desert_quartz_" + BlockMQuartz.EnumType.values()[i].toString());
			initModel(irradiant_quartz, i, "irradiant_quartz_" + BlockMQuartz.EnumType.values()[i].toString());
			initModel(soul_glass, i, "soul_glass_" + BlockSoulGlass.EnumType.values()[i].toString());
			initModel(blazed_soul_glass, i, "blazed_soul_glass_" + BlockSoulGlass.EnumType.values()[i].toString());
		}
		for(int i = 0 ; i < 5 ; i++)
		{
			initModel(gilded_stone, i, "gilded_stone_" + BlockGildedStone.EnumType.values()[i].toString());
		}
		for(int i = 0 ; i < BlockPortar.PortarType.values().length ; i++)
		{
			initModel(portar, i, "portar_" + BlockPortar.PortarType.values()[i].getName());
		}
		for(int i = 0 ; i < BlockWoodSlab1.EnumType.values().length ; i++)
		{
			initModel(wood_slab_1, i, "m_wood_slab_1_" + BlockWoodSlab1.EnumType.values()[i].getName());
		}
		for(int i = 0 ; i < BlockMiscStoneSlab3.EnumType.values().length ; i++)
		{
			initModel(misc_stone_slab_3, i, "m_misc_stone_slab_3_" + BlockMiscStoneSlab3.EnumType.values()[i].getName());
		}
		for(int i = 0 ; i < 8 ; i++)
		{
			initModel(misc_stone_slab_1, i, "m_misc_stone_slab_1_" + BlockMiscStoneSlab1.EnumType.values()[i].getName());
			initModel(misc_stone_slab_2, i, "m_misc_stone_slab_2_" + BlockMiscStoneSlab2.EnumType.values()[i].getName());
			initModel(stone_slab_1, i, "m_stone_slab_1_" + BlockStoneSlab1.EnumType.values()[i].getName());
			initModel(stone_slab_2, i, "m_stone_slab_2_" + BlockStoneSlab2.EnumType.values()[i].getName());
			initModel(stone_slab_3, i, "m_stone_slab_3_" + BlockStoneSlab3.EnumType.values()[i].getName());
		}
		for(int i = 0 ; i < BlockStoneSlab4.EnumType.values().length ; i++)
		{
			initModel(stone_slab_4, i, "m_stone_slab_4_" + BlockStoneSlab4.EnumType.values()[i].getName());
		}
		for(int i = 0 ; i < BlockMGlowDyed.EnumGlowDye.values().length ; i++)
		{
			initModel(glow_wool, i, "m_wool_" + BlockMGlowDyed.EnumGlowDye.values()[i].getName());
			initModel(glow_carpet, i, "m_carpet_" + BlockMGlowDyed.EnumGlowDye.values()[i].getName());
			initModel(glow_stained_glass, i, "m_stained_glass_" + BlockMGlowDyed.EnumGlowDye.values()[i].getName());
			initModel(glow_stained_glass_pane, i, "m_stained_glass_pane_" + BlockMGlowDyed.EnumGlowDye.values()[i].getName());
			initModel(glow_terracotta, i, "m_terracotta_" + BlockMGlowDyed.EnumGlowDye.values()[i].getName());
			initModel(glow_concrete_powder, i, "m_concrete_powder_" + BlockMGlowDyed.EnumGlowDye.values()[i].getName());
			initModel(glow_concrete, i, "m_concrete_" + BlockMGlowDyed.EnumGlowDye.values()[i].getName());
			initModel(candle2, i, "candle_2_" + BlockMGlowDyed.EnumGlowDye.values()[i].getName());
		}
		for(int i = 0 ; i < EnumDyeColor.values().length ; i++)
		{
			initModel(candle, i, "candle_" + EnumDyeColor.values()[i].getName());
		}
		for(int i = 0 ; i < BlockChandelierHalloween.EnumColor.values().length ; i++)
		{
			initModel(chandelier_halloween, i, "chandelier_halloween_" + BlockChandelierHalloween.EnumColor.values()[i].getName());
		}
		for(int i = 0 ; i < BlockChandelierNewYear.EnumColor.values().length ; i++)
		{
			initModel(chandelier_new_year, i, "chandelier_new_year_" + BlockChandelierNewYear.EnumColor.values()[i].getName());
		}
		for(int i = 0 ; i < BlockJackOLantern.EnumFaceType.values().length ; i++)
		{
			initModel(pumpkin_normie, i, "pumpkin_normie_" + BlockJackOLantern.EnumFaceType.values()[i].getName());
			initModel(pumpkin_grumpy, i, "pumpkin_grumpy_" + BlockJackOLantern.EnumFaceType.values()[i].getName());
			initModel(pumpkin_dumpy, i, "pumpkin_dumpy_" + BlockJackOLantern.EnumFaceType.values()[i].getName());
			initModel(pumpkin_creepy, i, "pumpkin_creepy_" + BlockJackOLantern.EnumFaceType.values()[i].getName());
			initModel(pumpkin_smiley, i, "pumpkin_smiley_" + BlockJackOLantern.EnumFaceType.values()[i].getName());
		}
		for(int i = 0 ; i < BlockPlateMetal.EnumType.values().length ; i++)
		{
			initModel(plate_metal, i, "plate_metal_" + BlockPlateMetal.EnumType.values()[i].getName());
		}
		for(int i = 0 ; i < BlockDesertQuartzDeposit.DepositType.values().length ; i++)
		{
			initModel(desert_quartz_deposit, i, "desert_quartz_deposit_" + BlockDesertQuartzDeposit.DepositType.values()[i].getUnlocalizedName());
		}
		for(int i = 0 ; i < BlockBasketCheesemaking.EnumProgress.values().length ; i++)
		{
			initModel(basket_cheesemaking, i, "basket_cheesemaking_" + BlockBasketCheesemaking.EnumProgress.values()[i].getUnlocalizedName());
		}
	}
	
	private static void initModel(Block block)
	{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(),"inventory"));
	}
	
	private static void initModel(Block block, int meta, String fileName)
	{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(Minestrapp5.MODID + ":" + fileName,"inventory"));
	}
}
