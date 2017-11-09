package minestrapp;

import java.util.ArrayList;
import java.util.List;


import minestrapp.block.BlockBasket;
import minestrapp.block.BlockBauble;
import minestrapp.block.BlockAlloy;
import minestrapp.block.BlockBarrel;
import minestrapp.block.BlockBlazium;
import minestrapp.block.BlockCandle;
import minestrapp.block.BlockColdSand;
import minestrapp.block.BlockCrusher;
import minestrapp.block.BlockDecorativeStones;
import minestrapp.block.BlockDimensium;
import minestrapp.block.BlockDoubleMiscStoneSlab1;
import minestrapp.block.BlockDoubleStoneSlab1;
import minestrapp.block.BlockDoubleStoneSlab2;
import minestrapp.block.BlockDoubleStoneSlab3;
import minestrapp.block.BlockDoubleStoneSlab4;
import minestrapp.block.BlockDriedMud;
import minestrapp.block.BlockGlaciericIce;
import minestrapp.block.BlockGlaciericIceDeposit;
import minestrapp.block.BlockGlacierite;
import minestrapp.block.BlockGlowshroom;
import minestrapp.block.BlockHalfMiscStoneSlab1;
import minestrapp.block.BlockHalfStoneSlab1;
import minestrapp.block.BlockHalfStoneSlab2;
import minestrapp.block.BlockHalfStoneSlab3;
import minestrapp.block.BlockHalfStoneSlab4;
import minestrapp.block.BlockInvincium;
import minestrapp.block.BlockIrradiantSunstone;
import minestrapp.block.BlockIrradium;
import minestrapp.block.BlockIrradiumOre;
import minestrapp.block.BlockJackOLantern;
import minestrapp.block.BlockJackOLanternSmashed;
import minestrapp.block.BlockMDirt;
import minestrapp.block.BlockMFarmland;
import minestrapp.block.BlockMGrass;
import minestrapp.block.BlockMHugeMushroom;
import minestrapp.block.BlockMPath;
import minestrapp.block.BlockMPlanks;
import minestrapp.block.BlockMiscStoneSlab1;
import minestrapp.block.BlockMoss;
import minestrapp.block.BlockMud;
import minestrapp.block.BlockRedstoneOre;
import minestrapp.block.BlockRope;
import minestrapp.block.BlockSavannaGrass;
import minestrapp.block.BlockSoulOre;
import minestrapp.block.BlockSoulsteelVessel;
import minestrapp.block.BlockStoneCutter;
import minestrapp.block.BlockStoneSlab1;
import minestrapp.block.BlockStoneSlab2;
import minestrapp.block.BlockStoneSlab3;
import minestrapp.block.BlockStoneSlab4;
import minestrapp.block.BlockSunstoneDeposit;
import minestrapp.block.BlockTundraGrass;
import minestrapp.block.EnumStoneType;
import minestrapp.block.EnumStoneTypeMOnly;
import minestrapp.block.crops.CropWithered;
import minestrapp.block.crops.BlockBerryBush;
import minestrapp.block.crops.BlockVoidberryBush;
import minestrapp.block.crops.CabbagePlant;
import minestrapp.block.crops.CeleryPlant;
import minestrapp.block.crops.CornPlant;
import minestrapp.block.crops.LettucePlant;
import minestrapp.block.crops.OnionPlant;
import minestrapp.block.crops.PeanutsPlant;
import minestrapp.block.crops.PepperPlant;
import minestrapp.block.crops.TomatoPlant;
import minestrapp.block.item.ItemBlockMSlab;
import minestrapp.block.item.ItemBlockMultistate;
import minestrapp.block.item.MItemBlock;
import minestrapp.block.magnetpiston.BlockMagnetPistonBase;
import minestrapp.block.magnetpiston.BlockMagnetPistonExtension;
import minestrapp.block.magnetpiston.BlockMagnetPistonMoving;
import minestrapp.block.magnetpiston.BlockPistonBase;
import minestrapp.block.magnetpiston.BlockPistonExtension;
import minestrapp.block.magnetpiston.BlockPistonMoving;
import minestrapp.block.util.BlockBase;
import minestrapp.block.util.BlockStairBase;
import minestrapp.block.util.BlockStoneBase;
import minestrapp.block.util.BlockStoneBaseMOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHugeMushroom;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

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
	public static Block voidberry_bush;
	
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
	
	//Wood
	public static Block planks;
	public static Block mossy_m_planks;
	
	//Stone
	public static Block mud_bricks;
	public static Block mud_brick_stairs;
	public static Block decor_stone;
	public static Block stone;
	public static Block cobblestone;
	public static Block mossy_cobblestone;
	public static Block stone_bricks;
	public static Block mossy_stone_bricks;
	public static Block cracked_stone_bricks;
	public static Block chiseled_stone;
	public static Block invincium;
	public static Block glacial_invincium;
	public static BlockSlab misc_stone_slab_1;
	public static BlockDoubleMiscStoneSlab1 double_misc_stone_slab_1;
	public static BlockSlab stone_slab_1;
	public static BlockDoubleStoneSlab1 double_stone_slab_1;
	public static BlockSlab stone_slab_2;
	public static BlockDoubleStoneSlab2 double_stone_slab_2;
	public static BlockSlab stone_slab_3;
	public static BlockDoubleStoneSlab3 double_stone_slab_3;
	public static BlockSlab stone_slab_4;
	public static BlockDoubleStoneSlab4 double_stone_slab_4;
	public static Block granite_brick_stairs;
	public static Block diorite_brick_stairs;
	public static Block andesite_brick_stairs;
	public static Block slate_brick_stairs;
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
	
	//Ore
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
	public static Block sunstone_deposit;
	public static Block glacieric_ice_deposit;
	public static Block ore_torite;
	public static Block ore_diamond;
	public static Block ore_emerald;
	public static Block ore_titanium;
	public static Block ore_blazium;
	public static Block ore_soul;
	public static Block ore_dimensium;
	
	//Resource
	public static Block block_copper;
	public static Block block_tin;
	public static Block block_bronze;
	public static Block block_steel;
	public static Block block_meurodite;
	public static Block block_irradium;
	public static Block block_sunstone;
	public static Block block_torite;
	public static Block block_titanium;
	public static Block block_glacierite;
	public static Block block_blazium;
	public static Block block_dimensium;
	public static Block block_dimensium_destabilized;
	
	//Decor
	public static Block bauble_sunstone;
	public static Block bauble_glowstone;
	public static Block bauble_glowshroom_blue;
	public static Block bauble_glowshroom_green;
	public static Block bauble_glowshroom_purple;
	public static Block bauble_blazium;
	public static Block candle;
	public static Block candle_fire;
	public static Block candle_ender;
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
	
	//Utility
	public static Block rope;
	public static Block basket;
	public static Block barrel;
	public static Block glacieric_ice;
	public static Block PISTON;
	public static Block STICKY_PISTON;
	public static Block PISTON_HEAD;
	public static Block PISTON_EXTENSION;
	public static Block magnet_piston_1;
	public static Block magnet_piston_2;
	public static Block magnet_piston_3;
	public static Block magnet_piston_4;
	public static Block magnet_piston_head;
	public static Block magnet_piston_extension;
	public static Block block_irradiant_sunstone;
	public static Block block_irradium_insulated;
	public static Block soulsteel_vessel;
	public static Block stonecutter;
	public static Block alloy;
	public static Block crusher;

	
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
	
	public static void init()
	{
		//Plant
		register(moss = new BlockMoss().setPushReaction(EnumPushReaction.DESTROY));
		register(blue_glowshroom = new BlockGlowshroom("blue_glowshroom").setLightLevel(0.6F));
		register(green_glowshroom = new BlockGlowshroom("green_glowshroom").setLightLevel(0.8F));
		register(purple_glowshroom = new BlockGlowshroom("purple_glowshroom").setLightLevel(0.7F));
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
		register(voidberry_bush = new BlockVoidberryBush("voidberry_bush"));
		
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
		
		//Wood
		register(planks = new BlockMPlanks("m_planks"), new ItemBlockMultistate(planks));
		register(mossy_m_planks = new BlockMPlanks("m_planks_mossy"), new ItemBlockMultistate(mossy_m_planks));
		
		//Stone
		register(mud_bricks = new BlockBase("mud_bricks", Material.ROCK, MapColor.WOOD, SoundType.STONE, 0.85F, "pickaxe", 0).setCreativeTab(MTabs.stone));
		register(decor_stone = new BlockDecorativeStones(), new ItemBlockMultistate(decor_stone));
		register(cobblestone = new BlockStoneBaseMOnly("m_cobblestone", Material.ROCK, SoundType.STONE, 2F, "pickaxe", 0).setResistance(10F).setCreativeTab(MTabs.stone), new ItemBlockMultistate(cobblestone));
		register(mossy_cobblestone = new BlockStoneBaseMOnly("m_mossy_cobblestone", Material.ROCK, SoundType.STONE, 2F, "pickaxe", 0).setResistance(10F).setCreativeTab(MTabs.stone), new ItemBlockMultistate(mossy_cobblestone));
		register(stone = new BlockStoneBaseMOnly("m_stone", Material.ROCK, SoundType.STONE, 1.5F, "pickaxe", 0).setDropsItem(new ItemStack(Item.getItemFromBlock(MBlocks.cobblestone)), 0, 0, 0, true, false, true).setResistance(10F).setCreativeTab(MTabs.stone), new ItemBlockMultistate(stone));
		register(stone_bricks = new BlockStoneBaseMOnly("m_stone_bricks", Material.ROCK, SoundType.STONE, 1.5F, "pickaxe", 0).setResistance(10F).setCreativeTab(MTabs.stone), new ItemBlockMultistate(stone_bricks));
		register(mossy_stone_bricks = new BlockStoneBaseMOnly("m_stone_bricks_mossy", Material.ROCK, SoundType.STONE, 1.5F, "pickaxe", 0).setResistance(10F).setCreativeTab(MTabs.stone), new ItemBlockMultistate(mossy_stone_bricks));
		register(cracked_stone_bricks = new BlockStoneBaseMOnly("m_stone_bricks_cracked", Material.ROCK, SoundType.STONE, 1.5F, "pickaxe", 0).setResistance(10F).setCreativeTab(MTabs.stone), new ItemBlockMultistate(cracked_stone_bricks));
		register(chiseled_stone = new BlockStoneBaseMOnly("m_chiseled_stone", Material.ROCK, SoundType.STONE, 1.5F, "pickaxe", 0).setResistance(10F).setCreativeTab(MTabs.stone), new ItemBlockMultistate(chiseled_stone));
		register(invincium = new BlockInvincium());
		register(glacial_invincium = new BlockBase("glacial_invincium", Material.ROCK, MapColor.CYAN, SoundType.STONE, -1F).setPushReaction(EnumPushReaction.BLOCK).setSlipperiness(0.85F).setEntityInvulnerability("all").setBlockUnbreakable().setCreativeTab(MTabs.environment));
		register(double_misc_stone_slab_1 = new BlockDoubleMiscStoneSlab1("m_misc_stone_slab_1"));
		register(misc_stone_slab_1 = new BlockHalfMiscStoneSlab1("m_misc_stone_slab_1"), new ItemBlockMSlab(misc_stone_slab_1, misc_stone_slab_1, double_misc_stone_slab_1));
		register(double_stone_slab_1 = new BlockDoubleStoneSlab1("m_stone_slab_1"));
		register(stone_slab_1 = new BlockHalfStoneSlab1("m_stone_slab_1"), new ItemBlockMSlab(stone_slab_1, stone_slab_1, double_stone_slab_1));
		register(double_stone_slab_2 = new BlockDoubleStoneSlab2("m_stone_slab_2"));
		register(stone_slab_2 = new BlockHalfStoneSlab2("m_stone_slab_2"), new ItemBlockMSlab(stone_slab_2, stone_slab_2, double_stone_slab_2));
		register(double_stone_slab_3 = new BlockDoubleStoneSlab3("m_stone_slab_3"));
		register(stone_slab_3 = new BlockHalfStoneSlab3("m_stone_slab_3"), new ItemBlockMSlab(stone_slab_3, stone_slab_3, double_stone_slab_3));
		register(double_stone_slab_4 = new BlockDoubleStoneSlab4("m_stone_slab_4"));
		register(stone_slab_4 = new BlockHalfStoneSlab4("m_stone_slab_4"), new ItemBlockMSlab(stone_slab_4, stone_slab_4, double_stone_slab_4));
		register(mud_brick_stairs = new BlockStairBase(mud_bricks));
		register(granite_brick_stairs = new BlockStairBase(decor_stone.getDefaultState().withProperty(BlockDecorativeStones.VARIANT, BlockDecorativeStones.DecorStoneType.GRANITE_BRICKS), decor_stone.getUnlocalizedName() + "_" + BlockDecorativeStones.DecorStoneType.GRANITE_BRICKS.getUnlocalizedName()));
		register(diorite_brick_stairs = new BlockStairBase(decor_stone.getDefaultState().withProperty(BlockDecorativeStones.VARIANT, BlockDecorativeStones.DecorStoneType.DIORITE_BRICKS), decor_stone.getUnlocalizedName() + "_" + BlockDecorativeStones.DecorStoneType.DIORITE_BRICKS.getUnlocalizedName()));
		register(andesite_brick_stairs = new BlockStairBase(decor_stone.getDefaultState().withProperty(BlockDecorativeStones.VARIANT, BlockDecorativeStones.DecorStoneType.ANDESITE_BRICKS), decor_stone.getUnlocalizedName() + "_" + BlockDecorativeStones.DecorStoneType.ANDESITE_BRICKS.getUnlocalizedName()));
		register(slate_brick_stairs = new BlockStairBase(decor_stone.getDefaultState().withProperty(BlockDecorativeStones.VARIANT, BlockDecorativeStones.DecorStoneType.SLATE_BRICKS), decor_stone.getUnlocalizedName() + "_" + BlockDecorativeStones.DecorStoneType.SLATE_BRICKS.getUnlocalizedName()));
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
		
		//Ore
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
		register(sunstone_deposit = new BlockSunstoneDeposit());
		register(glacieric_ice_deposit = new BlockGlaciericIceDeposit());
		register(ore_diamond = new BlockStoneBaseMOnly("ore_diamond", Material.ROCK, SoundType.STONE, 3F, "pickaxe", 2).setDropsItem(new ItemStack(Items.DIAMOND, 1, 0), 0, 3, 7, true, true, false).setResistance(5F).setCreativeTab(MTabs.ore), new ItemBlockMultistate(ore_diamond));
		register(ore_emerald = new BlockStoneBaseMOnly("ore_emerald", Material.ROCK, SoundType.STONE, 3F, "pickaxe", 2).setDropsItem(new ItemStack(Items.EMERALD, 1, 0), 0, 3, 7, true, true, false).setResistance(5F).setCreativeTab(MTabs.ore), new ItemBlockMultistate(ore_emerald));
		register(ore_titanium = new BlockStoneBase("ore_titanium", Material.ROCK, SoundType.STONE, 3F, "pickaxe", 0).setResistance(100F).setCreativeTab(MTabs.ore), new ItemBlockMultistate(ore_titanium));
		register(ore_blazium = new BlockBase("ore_blazium", Material.ROCK, MapColor.NETHERRACK, SoundType.STONE, 3F, "pickaxe", 2).setResistance(5F).setLightLevel(0.675F).setCreativeTab(MTabs.ore));
		register(ore_soul = new BlockSoulOre());
		register(ore_dimensium = new BlockBase("ore_dimensium", Material.ROCK, MapColor.SAND, SoundType.STONE, 6.0F, "pickaxe", 4).setEntityInvulnerability("dragon").setLightLevel(0.5F).setCreativeTab(MTabs.ore));
		
		//Resource
		register(block_copper = new BlockBase("block_copper", Material.IRON, MapColor.ADOBE, SoundType.METAL, 4F, "pickaxe", 0).setResistance(10F).setCreativeTab(MTabs.resource));
		register(block_tin = new BlockBase("block_tin", Material.IRON, MapColor.CLOTH, SoundType.METAL, 4F, "pickaxe", 0).setResistance(5F).setCreativeTab(MTabs.resource));
		register(block_bronze = new BlockBase("block_bronze", Material.IRON, MapColor.YELLOW_STAINED_HARDENED_CLAY, SoundType.METAL, 6F, "pickaxe", 1).setBeaconBase().setResistance(15F).setCreativeTab(MTabs.resource));
		register(block_steel = new BlockBase("block_steel", Material.IRON, MapColor.LIGHT_BLUE_STAINED_HARDENED_CLAY, SoundType.METAL, 5F, "pickaxe", 1).setBeaconBase().setResistance(12F).setCreativeTab(MTabs.resource));
		register(block_meurodite = new BlockBase("block_meurodite", Material.IRON, MapColor.BLUE_STAINED_HARDENED_CLAY, SoundType.METAL, 5F, "pickaxe", 2).setBeaconBase().setResistance(10F).setCreativeTab(MTabs.resource));
		register(block_irradium = new BlockIrradium("block_irradium", Material.ROCK, MapColor.LIME, SoundType.STONE, 5F, false).setCreativeTab(MTabs.resource));
		register(block_sunstone = new BlockBase("block_sunstone", Material.ROCK, MapColor.SAND, SoundType.GLASS, 2F, "pickaxe", 2).setLightLevel(0.95F).setCreativeTab(MTabs.resource));
		register(block_torite = new BlockBase("block_torite", Material.IRON, MapColor.FOLIAGE, SoundType.METAL, 5F, "pickaxe", 2).setBeaconBase().setResistance(10F).setCreativeTab(MTabs.resource));
		register(block_titanium = new BlockBase("block_titanium", Material.IRON, MapColor.BLACK, SoundType.METAL, 10F, "pickaxe", 3).setBeaconBase().setResistance(6000000.0F).setCreativeTab(MTabs.resource));
		register(block_glacierite = new BlockGlacierite());
		register(block_blazium = new BlockBlazium("block_blazium", Material.IRON, MapColor.ADOBE, SoundType.METAL, 5F, "pickaxe", 2).setBeaconBase().setResistance(10F).setLightLevel(0.8F).setCreativeTab(MTabs.resource));
		register(block_dimensium = new BlockDimensium("block_dimensium", Material.IRON, MapColor.MAGENTA, SoundType.METAL, 5F, "pickaxe", 2, false).setEntityInvulnerability("dragon").setBeaconBase().setResistance(10F).setCreativeTab(MTabs.resource));
		register(block_dimensium_destabilized = new BlockDimensium("block_dimensium_destabilized", Material.IRON, MapColor.MAGENTA, SoundType.METAL, -1F, "pickaxe", 999, true).setEntityInvulnerability("all").setPushReaction(EnumPushReaction.BLOCK).setBlockUnbreakable().setResistance(6000000.0F));
		
		//Decor
		register(rope = new BlockRope());
		register(bauble_sunstone = new BlockBauble("bauble_sunstone", Material.ROCK, MapColor.SAND, SoundType.GLASS, 2F, "pickaxe", 2).setPushReaction(EnumPushReaction.DESTROY).setLightLevel(0.85F).setCreativeTab(MTabs.decor));
		register(bauble_glowstone = new BlockBauble("bauble_glowstone", Material.GLASS, MapColor.SAND, SoundType.GLASS, 0.3F).setDropsItem(new ItemStack(Items.GLOWSTONE_DUST), 0, 0, 0, true, false).setPushReaction(EnumPushReaction.DESTROY).setLightLevel(0.9F).setCreativeTab(MTabs.decor));
		register(bauble_glowshroom_blue = new BlockBauble("bauble_glowshroom_blue", Material.WOOD, MapColor.BLUE, SoundType.WOOD, 0.2F).setRenderLayer(BlockRenderLayer.TRANSLUCENT).setPushReaction(EnumPushReaction.DESTROY).setLightLevel(0.5F).setCreativeTab(MTabs.decor));
		register(bauble_glowshroom_green = new BlockBauble("bauble_glowshroom_green", Material.WOOD, MapColor.LIME, SoundType.WOOD, 0.2F).setRenderLayer(BlockRenderLayer.TRANSLUCENT).setPushReaction(EnumPushReaction.DESTROY).setLightLevel(0.7F).setCreativeTab(MTabs.decor));
		register(bauble_glowshroom_purple = new BlockBauble("bauble_glowshroom_purple", Material.WOOD, MapColor.PURPLE, SoundType.WOOD, 0.2F).setRenderLayer(BlockRenderLayer.TRANSLUCENT).setPushReaction(EnumPushReaction.DESTROY).setLightLevel(0.6F).setCreativeTab(MTabs.decor));
		register(bauble_blazium = new BlockBauble("bauble_blazium", Material.IRON, MapColor.ADOBE, SoundType.METAL, 5F, "pickaxe", 2).setLightLevel(0.7F).setResistance(5F).setCreativeTab(MTabs.decor));
		register(candle = new BlockCandle("candle", "unlit"), new ItemBlockMultistate(candle));
		register(candle_fire = new BlockCandle("candle_fire", "fire"));
		register(candle_ender = new BlockCandle("candle_ender", "ender"));
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
		
		//Utility
		register(basket = new BlockBasket().setCreativeTab(MTabs.utility));
		register(barrel = new BlockBarrel("barrel", Material.WOOD, MapColor.WOOD, SoundType.WOOD, 1F, "axe", 2).setPushReaction(EnumPushReaction.BLOCK).setResistance(2F).setCreativeTab(MTabs.utility));
		register(glacieric_ice = new BlockGlaciericIce());
		register(block_irradiant_sunstone = new BlockIrradiantSunstone().setLightLevel(1F).setCreativeTab(MTabs.utility));
		register(PISTON = new BlockPistonBase(false).setRegistryName("minecraft:piston").setUnlocalizedName("pistonBase"));
		register(STICKY_PISTON = new BlockPistonBase(true).setRegistryName("minecraft:sticky_piston").setUnlocalizedName("pistonStickyBase"));
		ForgeRegistries.BLOCKS.register(PISTON_HEAD = new BlockPistonExtension().setRegistryName("minecraft:piston_head"));
		ForgeRegistries.BLOCKS.register(PISTON_EXTENSION = new BlockPistonMoving().setRegistryName("minecraft:piston_extension"));
		register(magnet_piston_1 = new BlockMagnetPistonBase(1).setRegistryName("magnet_piston1").setUnlocalizedName("magnet_piston").setCreativeTab(MTabs.utility));
		register(magnet_piston_2 = new BlockMagnetPistonBase(2).setRegistryName("magnet_piston2").setUnlocalizedName("magnet_piston"));
		register(magnet_piston_3 = new BlockMagnetPistonBase(3).setRegistryName("magnet_piston3").setUnlocalizedName("magnet_piston"));
		register(magnet_piston_4 = new BlockMagnetPistonBase(4).setRegistryName("magnet_piston4").setUnlocalizedName("magnet_piston"));
		ForgeRegistries.BLOCKS.register(magnet_piston_head = new BlockMagnetPistonExtension().setRegistryName("magnet_piston_head"));
		ForgeRegistries.BLOCKS.register(magnet_piston_extension = new BlockMagnetPistonMoving().setRegistryName("magnet_piston_extension"));
		register(block_irradium_insulated = new BlockIrradium("block_irradium_insulated", Material.IRON, MapColor.LIGHT_BLUE_STAINED_HARDENED_CLAY, SoundType.METAL, 6.5F, true).setCreativeTab(MTabs.utility));
		register(alloy = new BlockAlloy().setPushReaction(EnumPushReaction.BLOCK).setCreativeTab(MTabs.utility));
		register(crusher = new BlockCrusher().setPushReaction(EnumPushReaction.BLOCK).setCreativeTab(MTabs.utility));
		register(soulsteel_vessel = new BlockSoulsteelVessel().setHardness(3.0F).setCreativeTab(MTabs.utility));
		//register(stonecutter = new BlockStoneCutter().setHardness(3.0F).setCreativeTab(MTabs.utility));
		
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
	}
	
	public static void register(Block block)
	{
		ForgeRegistries.BLOCKS.register(block);
		ForgeRegistries.ITEMS.register(new MItemBlock(block).setRegistryName(block.getRegistryName()));
		blockList.add(block);
	}
	
	public static void register(Block block, MItemBlock itemBlock)
	{
		ForgeRegistries.BLOCKS.register(block);
		ForgeRegistries.ITEMS.register(itemBlock.setRegistryName(block.getRegistryName()));
	}
	
	public static void registerRenders()
	{
		for(Block block: blockList)
		{
			initModel(block);
		}
		for(int i = 0 ; i < BlockColdSand.EnumType.values().length ; i++)
		{
			initModel(cold_sand, i, "cold_sand_" + BlockColdSand.EnumType.values()[i].getName());
		}
		for(int i = 0 ; i < BlockMDirt.DirtType.values().length ; i++)
		{
			initModel(clay_soil, i, "clay_soil_" + BlockMDirt.DirtType.values()[i].getName());
			initModel(permafrost, i, "permafrost_" + BlockMDirt.DirtType.values()[i].getName());
		}
		for(int i = 0 ; i < BlockMPlanks.EnumType.values().length ; i++)
		{
			initModel(planks, i, "m_planks_" + BlockMPlanks.EnumType.values()[i].getName());
			initModel(mossy_m_planks, i, "m_planks_mossy_" + BlockMPlanks.EnumType.values()[i].getName());
		}
		for(int i = 0 ; i < BlockDecorativeStones.DecorStoneType.values().length ; i++)
		{
			initModel(decor_stone, i, "decor_stone_" + BlockDecorativeStones.DecorStoneType.values()[i].getName());
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
		}
		for(int i = 0 ; i < EnumStoneType.values().length ; i++)
		{
			initModel(ore_copper, i, "ore_copper_" + EnumStoneType.values()[i].getName());
			initModel(ore_tin, i, "ore_tin_" + EnumStoneType.values()[i].getName());
			initModel(ore_meurodite, i, "ore_meurodite_" + EnumStoneType.values()[i].getName());
			initModel(ore_irradium, i, "ore_irradium_" + EnumStoneType.values()[i].getName());
			initModel(ore_torite, i, "ore_torite_" + EnumStoneType.values()[i].getName());
			initModel(ore_titanium, i, "ore_titanium_" + EnumStoneType.values()[i].getName());
		}
		for(int i = 0 ; i < BlockMiscStoneSlab1.EnumType.values().length ; i++)
		{
			initModel(misc_stone_slab_1, i, "m_misc_stone_slab_1_" + BlockMiscStoneSlab1.EnumType.values()[i].getName());
		}
		for(int i = 0 ; i < 8 ; i++)
		{
			initModel(stone_slab_1, i, "m_stone_slab_1_" + BlockStoneSlab1.EnumType.values()[i].getName());
			initModel(stone_slab_2, i, "m_stone_slab_2_" + BlockStoneSlab2.EnumType.values()[i].getName());
			initModel(stone_slab_3, i, "m_stone_slab_3_" + BlockStoneSlab3.EnumType.values()[i].getName());
		}
		for(int i = 0 ; i < BlockStoneSlab4.EnumType.values().length ; i++)
		{
			initModel(stone_slab_4, i, "m_stone_slab_4_" + BlockStoneSlab4.EnumType.values()[i].getName());
		}
		for(int i = 0 ; i < EnumDyeColor.values().length ; i++)
		{
			initModel(candle, i, "candle_" + EnumDyeColor.values()[i].getName());
		}
		for(int i = 0 ; i < BlockJackOLantern.EnumFaceType.values().length ; i++)
		{
			initModel(pumpkin_normie, i, "pumpkin_normie_" + BlockJackOLantern.EnumFaceType.values()[i].getName());
			initModel(pumpkin_grumpy, i, "pumpkin_grumpy_" + BlockJackOLantern.EnumFaceType.values()[i].getName());
			initModel(pumpkin_dumpy, i, "pumpkin_dumpy_" + BlockJackOLantern.EnumFaceType.values()[i].getName());
			initModel(pumpkin_creepy, i, "pumpkin_creepy_" + BlockJackOLantern.EnumFaceType.values()[i].getName());
			initModel(pumpkin_smiley, i, "pumpkin_smiley_" + BlockJackOLantern.EnumFaceType.values()[i].getName());
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
