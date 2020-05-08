package minestrapp.crafting;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

import minestrapp.MBlocks;
import minestrapp.MItems;
import minestrapp.block.BlockMGlowDyed;
import minestrapp.utils.ItemUtil;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.oredict.OreDictionary;

public class SawmillRecipes
{
	private static final SawmillRecipes MILLING = new SawmillRecipes();
	public final Table<EnumFacing, ItemStack, ItemStack> milling = HashBasedTable.<EnumFacing, ItemStack, ItemStack>create();
	public final Map<ItemStack, ItemStack> jeiMilling = Maps.<ItemStack, ItemStack>newHashMap();
	public final Table<EnumFacing, ItemStack, ItemStack> extra = HashBasedTable.<EnumFacing, ItemStack, ItemStack>create();
	public final Map<ItemStack, ItemStack> jeiExtra = Maps.<ItemStack, ItemStack>newHashMap();
	public final Table<EnumFacing, ItemStack, Integer> chance = HashBasedTable.<EnumFacing, ItemStack, Integer>create();
	public final Table<EnumFacing, ItemStack, Boolean> isStone = HashBasedTable.<EnumFacing, ItemStack, Boolean>create();
	public final Map<ItemStack, Boolean> jeiIsStone = Maps.<ItemStack, Boolean>newHashMap();
	
	public static SawmillRecipes instance(){ return MILLING; }
	
	private SawmillRecipes()
	{
		addSawmillRecipe(new ItemStack(Blocks.LOG, 1, 0), new ItemStack(Blocks.PLANKS, 6, 0), new ItemStack(MItems.bark), 20, false);
		addSawmillRecipe(new ItemStack(Blocks.LOG, 1, 1), new ItemStack(Blocks.PLANKS, 6, 1), new ItemStack(MItems.bark), 20, false);
		addSawmillRecipe(new ItemStack(Blocks.LOG, 1, 2), new ItemStack(Blocks.PLANKS, 6, 2), new ItemStack(MItems.bark), 20, false);
		addSawmillRecipe(new ItemStack(Blocks.LOG, 1, 3), new ItemStack(Blocks.PLANKS, 6, 3), new ItemStack(MItems.bark), 20, false);
		addSawmillRecipe(new ItemStack(Blocks.LOG2, 1, 0), new ItemStack(Blocks.PLANKS, 6, 4), new ItemStack(MItems.bark), 20, false);
		addSawmillRecipe(new ItemStack(Blocks.LOG2, 1, 1), new ItemStack(Blocks.PLANKS, 6, 5), new ItemStack(MItems.bark), 20, false);
		addSawmillRecipe(new ItemStack(MBlocks.log, 1, 0), new ItemStack(MBlocks.planks, 6, 0), new ItemStack(MItems.bark), 20, false);
		addSawmillRecipe(new ItemStack(MBlocks.log, 1, 1), new ItemStack(MBlocks.planks, 6, 1), new ItemStack(MItems.bark), 20, false);
		addSawmillRecipe(new ItemStack(MBlocks.log, 1, 2), new ItemStack(MBlocks.planks, 6, 2), new ItemStack(MItems.bark), 20, false);
		addSawmillRecipe(new ItemStack(MBlocks.log, 1, 3), new ItemStack(MBlocks.planks, 6, 3), new ItemStack(MItems.bark), 20, false);
		addSawmillRecipe(new ItemStack(MBlocks.palm_crown), new ItemStack(MBlocks.planks, 6, 3), new ItemStack(MBlocks.palm_fronds_dead, 4), 70, false);
		addSawmillRecipe(new ItemStack(MBlocks.palm_crown_dead), new ItemStack(MBlocks.planks, 6, 3), new ItemStack(MBlocks.palm_fronds_dead, 4), 70, false);
		addSawmillRecipe(new ItemStack(Blocks.SNOW), new ItemStack(Blocks.SNOW_LAYER, 7), new ItemStack(Items.SNOWBALL), 50, false);
		addSawmillRecipe(new ItemStack(MBlocks.bauble_ice), new ItemStack(Items.SNOWBALL, 1), new ItemStack(Items.SNOWBALL), 10, false);
		addSawmillRecipe(new ItemStack(Blocks.ICE), new ItemStack(MBlocks.bauble_ice, 8), new ItemStack(Items.SNOWBALL), 30, false);
		addSawmillRecipe(new ItemStack(Blocks.PACKED_ICE), new ItemStack(Blocks.ICE, 4), new ItemStack(Items.SNOWBALL), 30, false);
		addSawmillRecipe(new ItemStack(MBlocks.glacieric_ice), new ItemStack(Blocks.PACKED_ICE, 1), new ItemStack(MItems.gems, 1, 6), 20, false);
		addSawmillRecipe(new ItemStack(Blocks.PUMPKIN), new ItemStack(Items.PUMPKIN_SEEDS, 8), new ItemStack(Items.PUMPKIN_SEEDS), 50, false);
		addSawmillRecipe(new ItemStack(Blocks.LIT_PUMPKIN), new ItemStack(Items.PUMPKIN_SEEDS, 8), new ItemStack(Items.PUMPKIN_SEEDS), 50, false);
		addSawmillRecipe(new ItemStack(Blocks.MELON_BLOCK), new ItemStack(Items.MELON, 8), new ItemStack(Items.MELON), 50, false);
		addSawmillRecipe(new ItemStack(MBlocks.melon_bricks), new ItemStack(Items.MELON, 8), new ItemStack(Items.MELON), 50, false);
		addSawmillRecipe(new ItemStack(MBlocks.coconut), new ItemStack(MItems.coconut_slice, 3), new ItemStack(MItems.coconut_slice), 50, false);
		addSawmillRecipe(new ItemStack(Blocks.HAY_BLOCK), new ItemStack(Items.WHEAT, 8), new ItemStack(Items.WHEAT), 50, false);
		addSawmillRecipe(new ItemStack(Blocks.RED_MUSHROOM_BLOCK), new ItemStack(Blocks.RED_MUSHROOM), new ItemStack(Blocks.RED_MUSHROOM), 20, false);
		addSawmillRecipe(new ItemStack(Blocks.BROWN_MUSHROOM_BLOCK), new ItemStack(Blocks.BROWN_MUSHROOM), new ItemStack(Blocks.BROWN_MUSHROOM), 20, false);
		addSawmillRecipe(new ItemStack(MBlocks.purple_glowshroom_block), new ItemStack(MBlocks.purple_glowshroom), new ItemStack(MBlocks.purple_glowshroom), 20, false);
		addSawmillRecipe(new ItemStack(MBlocks.green_glowshroom_block), new ItemStack(MBlocks.green_glowshroom), new ItemStack(MBlocks.green_glowshroom), 20, false);
		addSawmillRecipe(new ItemStack(MBlocks.blue_glowshroom_block), new ItemStack(MBlocks.blue_glowshroom), new ItemStack(MBlocks.blue_glowshroom), 20, false);
		addSawmillRecipe(new ItemStack(Blocks.LEAVES, 1, 0), new ItemStack(Items.STICK), new ItemStack(Blocks.SAPLING, 1, 0), 40, false);
		addSawmillRecipe(new ItemStack(Blocks.LEAVES, 1, 1), new ItemStack(Items.STICK), new ItemStack(Blocks.SAPLING, 1, 1), 40, false);
		addSawmillRecipe(new ItemStack(Blocks.LEAVES, 1, 2), new ItemStack(Items.STICK), new ItemStack(Blocks.SAPLING, 1, 2), 40, false);
		addSawmillRecipe(new ItemStack(Blocks.LEAVES, 1, 3), new ItemStack(Items.STICK), new ItemStack(Blocks.SAPLING, 1, 3), 40, false);
		addSawmillRecipe(new ItemStack(Blocks.LEAVES2, 1, 0), new ItemStack(Items.STICK), new ItemStack(Blocks.SAPLING, 1, 4), 40, false);
		addSawmillRecipe(new ItemStack(Blocks.LEAVES2, 1, 1), new ItemStack(Items.STICK), new ItemStack(Blocks.SAPLING, 1, 5), 40, false);
		addSawmillRecipe(new ItemStack(MBlocks.mite_hive), new ItemStack(MItems.mite_honey), new ItemStack(MItems.mite_honey), 20, false);
		addSawmillRecipe(new ItemStack(MBlocks.mite_hive_honeyed), new ItemStack(MItems.mite_honey), new ItemStack(MItems.mite_honey), 80, false);
		addSawmillRecipe(new ItemStack(MBlocks.mite_comb), new ItemStack(MItems.mite_honey), new ItemStack(MItems.mite_honey), 20, false);
		addSawmillRecipe(new ItemStack(Blocks.GLOWSTONE), new ItemStack(MBlocks.bauble_glowstone, 4), true);
		addSawmillRecipe(new ItemStack(MBlocks.block_sunstone), new ItemStack(MBlocks.bauble_sunstone, 4), true);
		addSawmillRecipe(new ItemStack(Blocks.SEA_LANTERN), new ItemStack(Items.PRISMARINE_CRYSTALS, 5), new ItemStack(Items.PRISMARINE_SHARD, 4), 100, true);
		addSawmillRecipe(new ItemStack(Blocks.HARDENED_CLAY), new ItemStack(Items.BRICK, 4), true);
		addSawmillRecipe(new ItemStack(MBlocks.dried_mud), new ItemStack(MItems.bricks, 4), true);
		
		for(int i = 0 ; i < EnumDyeColor.values().length ; i++)
		{
			addSawmillRecipe(new ItemStack(Blocks.WOOL, 1, i), new ItemStack(Blocks.CARPET, 4, i), false);
		}
		for(int i = 0 ; i < BlockMGlowDyed.EnumGlowDye.values().length ; i++)
		{
			addSawmillRecipe(new ItemStack(MBlocks.glow_wool, 1, i), new ItemStack(MBlocks.glow_carpet, 4, i), false);
		}
		
		addSawmillRecipe(EnumFacing.UP, new ItemStack(Blocks.CARPET, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.STRING), false);
		addSawmillRecipe(EnumFacing.UP, new ItemStack(MBlocks.glow_carpet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.STRING), false);
		addSawmillRecipe(EnumFacing.UP, new ItemStack(Items.CAKE), new ItemStack(MItems.cake_slice, 6), new ItemStack(MItems.cake_slice), 50, false);
		addSawmillRecipe(EnumFacing.UP, new ItemStack(MBlocks.block_cheese), new ItemStack(MItems.cheese, 13), new ItemStack(MItems.cheese), 50, false);
		addSawmillRecipe(EnumFacing.UP, new ItemStack(MBlocks.quesadilla), new ItemStack(MItems.quesadilla_slice, 3), new ItemStack(MItems.quesadilla_slice), 50, false);
		addSawmillRecipe(EnumFacing.UP, new ItemStack(MBlocks.pizza), new ItemStack(MItems.pizza_slice, 3), new ItemStack(MItems.pizza_slice), 50, false);
		
		addSawmillRecipe(true, new ItemStack(Blocks.PLANKS, 1, 0), new ItemStack(MBlocks.oak_plank_panel, 2), new ItemStack(MItems.sawdust), 10, false);
		addSawmillRecipe(true, new ItemStack(Blocks.PLANKS, 1, 1), new ItemStack(MBlocks.spruce_plank_panel, 2), new ItemStack(MItems.sawdust), 10, false);
		addSawmillRecipe(true, new ItemStack(Blocks.PLANKS, 1, 2), new ItemStack(MBlocks.birch_plank_panel, 2), new ItemStack(MItems.sawdust), 10, false);
		addSawmillRecipe(true, new ItemStack(Blocks.PLANKS, 1, 3), new ItemStack(MBlocks.jungle_plank_panel, 2), new ItemStack(MItems.sawdust), 10, false);
		addSawmillRecipe(true, new ItemStack(Blocks.PLANKS, 1, 4), new ItemStack(MBlocks.acacia_plank_panel, 2), new ItemStack(MItems.sawdust), 10, false);
		addSawmillRecipe(true, new ItemStack(Blocks.PLANKS, 1, 5), new ItemStack(MBlocks.dark_oak_plank_panel, 2), new ItemStack(MItems.sawdust), 10, false);
		addSawmillRecipe(true, new ItemStack(MBlocks.planks, 1, 0), new ItemStack(MBlocks.redwood_plank_panel, 2), new ItemStack(MItems.sawdust), 10, false);
		addSawmillRecipe(true, new ItemStack(MBlocks.planks, 1, 1), new ItemStack(MBlocks.frozen_oak_plank_panel, 2), new ItemStack(MItems.sawdust), 10, false);
		addSawmillRecipe(true, new ItemStack(MBlocks.planks, 1, 2), new ItemStack(MBlocks.charwood_plank_panel, 2), new ItemStack(MItems.sawdust), 10, false);
		addSawmillRecipe(true, new ItemStack(MBlocks.planks, 1, 3), new ItemStack(MBlocks.palm_plank_panel, 2), new ItemStack(MItems.sawdust), 10, false);
		addSawmillRecipe(true, new ItemStack(MBlocks.mud_bricks), new ItemStack(MBlocks.mud_brick_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(Blocks.BRICK_BLOCK), new ItemStack(MBlocks.clay_brick_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.decor_stone, 1, 0), new ItemStack(MBlocks.granite_brick_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.decor_stone, 1, 2), new ItemStack(MBlocks.diorite_brick_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.decor_stone, 1, 4), new ItemStack(MBlocks.andesite_brick_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.decor_stone, 1, 8), new ItemStack(MBlocks.slate_brick_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(Blocks.SANDSTONE, 1, 0), new ItemStack(MBlocks.sandstone_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(Blocks.RED_SANDSTONE, 1, 0), new ItemStack(MBlocks.red_sandstone_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.sandstone, 1, 0), new ItemStack(MBlocks.sandstone_brick_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.sandstone, 1, 1), new ItemStack(MBlocks.red_sandstone_brick_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.sandstone, 1, 2), new ItemStack(MBlocks.cold_sandstone_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.sandstone, 1, 5), new ItemStack(MBlocks.cold_sandstone_brick_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.sandstone, 1, 6), new ItemStack(MBlocks.cold_red_sandstone_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.sandstone, 1, 9), new ItemStack(MBlocks.cold_red_sandstone_brick_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.stone, 1, 0), new ItemStack(MBlocks.red_rock_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.cobblestone, 1, 0), new ItemStack(MBlocks.cobbled_red_rock_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.stone_bricks, 1, 0), new ItemStack(MBlocks.red_rock_brick_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.stone, 1, 1), new ItemStack(MBlocks.deep_red_rock_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.stone_bricks, 1, 1), new ItemStack(MBlocks.deep_red_rock_brick_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.cobblestone, 1, 1), new ItemStack(MBlocks.cobbled_deep_red_rock_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(Blocks.STONE, 1, 0), new ItemStack(MBlocks.stone_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(Blocks.COBBLESTONE, 1, 0), new ItemStack(MBlocks.cobblestone_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(Blocks.STONEBRICK, 1, 0), new ItemStack(MBlocks.stone_brick_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.stone, 1, 2), new ItemStack(MBlocks.deepstone_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.cobblestone, 1, 2), new ItemStack(MBlocks.cobbled_deepstone_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.stone_bricks, 1, 2), new ItemStack(MBlocks.deepstone_brick_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.stone, 1, 3), new ItemStack(MBlocks.coldstone_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.cobblestone, 1, 3), new ItemStack(MBlocks.cobbled_coldstone_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.stone_bricks, 1, 3), new ItemStack(MBlocks.coldstone_brick_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.stone, 1, 4), new ItemStack(MBlocks.deep_coldstone_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.cobblestone, 1, 4), new ItemStack(MBlocks.cobbled_deep_coldstone_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.stone_bricks, 1, 4), new ItemStack(MBlocks.deep_coldstone_brick_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.stone, 1, 5), new ItemStack(MBlocks.icestone_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.cobblestone, 1, 5), new ItemStack(MBlocks.cobbled_icestone_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.stone_bricks, 1, 5), new ItemStack(MBlocks.icestone_brick_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.stone, 1, 6), new ItemStack(MBlocks.glacierrock_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.cobblestone, 1, 6), new ItemStack(MBlocks.cobbled_glacierrock_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.stone_bricks, 1, 6), new ItemStack(MBlocks.glacierrock_brick_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.stone, 1, 7), new ItemStack(MBlocks.oceanstone_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.cobblestone, 1, 7), new ItemStack(MBlocks.cobbled_oceanstone_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.stone_bricks, 1, 7), new ItemStack(MBlocks.oceanstone_brick_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.stone, 1, 8), new ItemStack(MBlocks.deep_oceanstone_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.cobblestone, 1, 8), new ItemStack(MBlocks.cobbled_deep_oceanstone_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.stone_bricks, 1, 8), new ItemStack(MBlocks.deep_oceanstone_brick_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.desert_quartz, 1, 0), new ItemStack(MBlocks.desert_quartz_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(Blocks.QUARTZ_BLOCK, 1, 0), new ItemStack(MBlocks.nether_quartz_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.irradiant_quartz, 1, 0), new ItemStack(MBlocks.irradiant_quartz_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(Blocks.NETHER_BRICK, 1, 0), new ItemStack(MBlocks.nether_brick_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.shimmerstone, 1, 0), new ItemStack(MBlocks.shimmerstone_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.shimmerstone, 1, 1), new ItemStack(MBlocks.cobbled_shimmerstone_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.shimmerstone, 1, 2), new ItemStack(MBlocks.shimmerstone_brick_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.portar, 1, 0), new ItemStack(MBlocks.portar_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(Blocks.PURPUR_BLOCK, 1, 0), new ItemStack(MBlocks.purpur_panel, 2), true);
		addSawmillRecipe(true, new ItemStack(MBlocks.gilded_stone, 1, 0), new ItemStack(MBlocks.gilded_brick_panel, 2), true);

		addSawmillRecipe(false, new ItemStack(Blocks.PLANKS, 1, 0), new ItemStack(Blocks.WOODEN_SLAB, 2, 0), new ItemStack(MItems.sawdust), 10, false);
		addSawmillRecipe(false, new ItemStack(Blocks.PLANKS, 1, 1), new ItemStack(Blocks.WOODEN_SLAB, 2, 1), new ItemStack(MItems.sawdust), 10, false);
		addSawmillRecipe(false, new ItemStack(Blocks.PLANKS, 1, 2), new ItemStack(Blocks.WOODEN_SLAB, 2, 2), new ItemStack(MItems.sawdust), 10, false);
		addSawmillRecipe(false, new ItemStack(Blocks.PLANKS, 1, 3), new ItemStack(Blocks.WOODEN_SLAB, 2, 3), new ItemStack(MItems.sawdust), 10, false);
		addSawmillRecipe(false, new ItemStack(Blocks.PLANKS, 1, 4), new ItemStack(Blocks.WOODEN_SLAB, 2, 4), new ItemStack(MItems.sawdust), 10, false);
		addSawmillRecipe(false, new ItemStack(Blocks.PLANKS, 1, 5), new ItemStack(Blocks.WOODEN_SLAB, 2, 5), new ItemStack(MItems.sawdust), 10, false);
		addSawmillRecipe(false, new ItemStack(MBlocks.planks, 1, 0), new ItemStack(MBlocks.wood_slab_1, 2, 0), new ItemStack(MItems.sawdust), 10, false);
		addSawmillRecipe(false, new ItemStack(MBlocks.planks, 1, 1), new ItemStack(MBlocks.wood_slab_1, 2, 1), new ItemStack(MItems.sawdust), 10, false);
		addSawmillRecipe(false, new ItemStack(MBlocks.planks, 1, 2), new ItemStack(MBlocks.wood_slab_1, 2, 2), new ItemStack(MItems.sawdust), 10, false);
		addSawmillRecipe(false, new ItemStack(MBlocks.planks, 1, 3), new ItemStack(MBlocks.wood_slab_1, 2, 3), new ItemStack(MItems.sawdust), 10, false);
		addSawmillRecipe(false, new ItemStack(MBlocks.mud_bricks), new ItemStack(MBlocks.misc_stone_slab_1, 2, 0), true);
		addSawmillRecipe(false, new ItemStack(Blocks.BRICK_BLOCK), new ItemStack(Blocks.STONE_SLAB, 2, 4), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.decor_stone, 1, 0), new ItemStack(MBlocks.misc_stone_slab_1, 2, 1), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.decor_stone, 1, 2), new ItemStack(MBlocks.misc_stone_slab_1, 2, 2), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.decor_stone, 1, 4), new ItemStack(MBlocks.misc_stone_slab_1, 2, 3), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.decor_stone, 1, 8), new ItemStack(MBlocks.misc_stone_slab_1, 2, 4), true);
		addSawmillRecipe(false, new ItemStack(Blocks.SANDSTONE, 1, 0), new ItemStack(Blocks.STONE_SLAB, 2, 1), true);
		addSawmillRecipe(false, new ItemStack(Blocks.RED_SANDSTONE, 1, 0), new ItemStack(Blocks.STONE_SLAB2, 2, 0), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.sandstone, 1, 0), new ItemStack(MBlocks.misc_stone_slab_2, 2, 3), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.sandstone, 1, 1), new ItemStack(MBlocks.misc_stone_slab_2, 2, 4), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.sandstone, 1, 2), new ItemStack(MBlocks.misc_stone_slab_2, 2, 5), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.sandstone, 1, 5), new ItemStack(MBlocks.misc_stone_slab_2, 2, 7), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.sandstone, 1, 6), new ItemStack(MBlocks.misc_stone_slab_2, 2, 6), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.sandstone, 1, 9), new ItemStack(MBlocks.misc_stone_slab_3, 2, 0), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.stone, 1, 0), new ItemStack(MBlocks.stone_slab_1, 2, 0), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.cobblestone, 1, 0), new ItemStack(MBlocks.stone_slab_2, 2, 1), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.stone_bricks, 1, 0), new ItemStack(MBlocks.stone_slab_3, 2, 2), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.stone, 1, 1), new ItemStack(MBlocks.stone_slab_1, 2, 1), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.cobblestone, 1, 1), new ItemStack(MBlocks.stone_slab_2, 2, 2), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.stone_bricks, 1, 1), new ItemStack(MBlocks.stone_slab_3, 2, 3), true);
		addSawmillRecipe(false, new ItemStack(Blocks.STONE, 1, 0), new ItemStack(Blocks.STONE_SLAB, 2, 0), true);
		addSawmillRecipe(false, new ItemStack(Blocks.COBBLESTONE, 1, 0), new ItemStack(Blocks.STONE_SLAB, 2, 3), true);
		addSawmillRecipe(false, new ItemStack(Blocks.STONEBRICK, 1, 0), new ItemStack(Blocks.STONE_SLAB, 2, 5), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.stone, 1, 2), new ItemStack(MBlocks.stone_slab_1, 2, 2), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.cobblestone, 1, 2), new ItemStack(MBlocks.stone_slab_2, 2, 3), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.stone_bricks, 1, 2), new ItemStack(MBlocks.stone_slab_3, 2, 4), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.stone, 1, 3), new ItemStack(MBlocks.stone_slab_1, 2, 3), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.cobblestone, 1, 3), new ItemStack(MBlocks.stone_slab_2, 2, 4), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.stone_bricks, 1, 3), new ItemStack(MBlocks.stone_slab_3, 2, 5), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.stone, 1, 4), new ItemStack(MBlocks.stone_slab_1, 2, 4), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.cobblestone, 1, 4), new ItemStack(MBlocks.stone_slab_2, 2, 5), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.stone_bricks, 1, 4), new ItemStack(MBlocks.stone_slab_3, 2, 6), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.stone, 1, 5), new ItemStack(MBlocks.stone_slab_1, 2, 5), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.cobblestone, 1, 5), new ItemStack(MBlocks.stone_slab_2, 2, 6), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.stone_bricks, 1, 5), new ItemStack(MBlocks.stone_slab_3, 2, 7), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.stone, 1, 6), new ItemStack(MBlocks.stone_slab_1, 2, 6), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.cobblestone, 1, 6), new ItemStack(MBlocks.stone_slab_2, 2, 7), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.stone_bricks, 1, 6), new ItemStack(MBlocks.stone_slab_4, 2, 0), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.stone, 1, 7), new ItemStack(MBlocks.stone_slab_1, 2, 7), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.cobblestone, 1, 7), new ItemStack(MBlocks.stone_slab_3, 2, 0), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.stone_bricks, 1, 7), new ItemStack(MBlocks.stone_slab_4, 2, 1), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.stone, 1, 8), new ItemStack(MBlocks.stone_slab_2, 2, 0), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.cobblestone, 1, 8), new ItemStack(MBlocks.stone_slab_3, 2, 1), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.stone_bricks, 1, 8), new ItemStack(MBlocks.stone_slab_4, 2, 2), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.desert_quartz, 1, 0), new ItemStack(MBlocks.misc_stone_slab_2, 2, 1), true);
		addSawmillRecipe(false, new ItemStack(Blocks.QUARTZ_BLOCK, 1, 0), new ItemStack(Blocks.STONE_SLAB, 2, 7), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.irradiant_quartz, 1, 0), new ItemStack(MBlocks.misc_stone_slab_2, 2, 2), true);
		addSawmillRecipe(false, new ItemStack(Blocks.NETHER_BRICK, 1, 0), new ItemStack(Blocks.STONE_SLAB, 2, 6), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.shimmerstone, 1, 0), new ItemStack(MBlocks.misc_stone_slab_1, 2, 6), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.shimmerstone, 1, 1), new ItemStack(MBlocks.misc_stone_slab_1, 2, 7), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.shimmerstone, 1, 2), new ItemStack(MBlocks.misc_stone_slab_2, 2, 0), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.portar, 1, 0), new ItemStack(MBlocks.misc_stone_slab_1, 2, 5), true);
		addSawmillRecipe(false, new ItemStack(Blocks.PURPUR_BLOCK, 1, 0), new ItemStack(Blocks.PURPUR_SLAB, 2), true);
		addSawmillRecipe(false, new ItemStack(MBlocks.gilded_stone, 1, 0), new ItemStack(MBlocks.misc_stone_slab_3, 2, 1), true);
		
		addSawmillRecipe(new ItemStack(MBlocks.pumpkin_creepy, 1, 0), new ItemStack(MBlocks.pumpkin_creepy, 1, 1), new ItemStack(Items.PUMPKIN_SEEDS), 50, false);
		addSawmillRecipe(new ItemStack(MBlocks.pumpkin_creepy, 1, 1), new ItemStack(MBlocks.pumpkin_creepy, 1, 2), new ItemStack(Items.PUMPKIN_SEEDS), 30, false);
		addSawmillRecipe(new ItemStack(MBlocks.pumpkin_creepy, 1, 2), new ItemStack(MBlocks.pumpkin_smashed), new ItemStack(Items.PUMPKIN_SEEDS), 10, false);
		addSawmillRecipe(new ItemStack(MBlocks.pumpkin_dumpy, 1, 0), new ItemStack(MBlocks.pumpkin_dumpy, 1, 1), new ItemStack(Items.PUMPKIN_SEEDS), 50, false);
		addSawmillRecipe(new ItemStack(MBlocks.pumpkin_dumpy, 1, 1), new ItemStack(MBlocks.pumpkin_dumpy, 1, 2), new ItemStack(Items.PUMPKIN_SEEDS), 30, false);
		addSawmillRecipe(new ItemStack(MBlocks.pumpkin_dumpy, 1, 2), new ItemStack(MBlocks.pumpkin_smashed), new ItemStack(Items.PUMPKIN_SEEDS), 10, false);
		addSawmillRecipe(new ItemStack(MBlocks.pumpkin_grumpy, 1, 0), new ItemStack(MBlocks.pumpkin_grumpy, 1, 1), new ItemStack(Items.PUMPKIN_SEEDS), 50, false);
		addSawmillRecipe(new ItemStack(MBlocks.pumpkin_grumpy, 1, 1), new ItemStack(MBlocks.pumpkin_grumpy, 1, 2), new ItemStack(Items.PUMPKIN_SEEDS), 30, false);
		addSawmillRecipe(new ItemStack(MBlocks.pumpkin_grumpy, 1, 2), new ItemStack(MBlocks.pumpkin_smashed), new ItemStack(Items.PUMPKIN_SEEDS), 10, false);
		addSawmillRecipe(new ItemStack(MBlocks.pumpkin_normie, 1, 0), new ItemStack(MBlocks.pumpkin_normie, 1, 1), new ItemStack(Items.PUMPKIN_SEEDS), 50, false);
		addSawmillRecipe(new ItemStack(MBlocks.pumpkin_normie, 1, 1), new ItemStack(MBlocks.pumpkin_normie, 1, 2), new ItemStack(Items.PUMPKIN_SEEDS), 30, false);
		addSawmillRecipe(new ItemStack(MBlocks.pumpkin_normie, 1, 2), new ItemStack(MBlocks.pumpkin_smashed), new ItemStack(Items.PUMPKIN_SEEDS), 10, false);
		addSawmillRecipe(new ItemStack(MBlocks.pumpkin_smiley, 1, 0), new ItemStack(MBlocks.pumpkin_smiley, 1, 1), new ItemStack(Items.PUMPKIN_SEEDS), 50, false);
		addSawmillRecipe(new ItemStack(MBlocks.pumpkin_smiley, 1, 1), new ItemStack(MBlocks.pumpkin_smiley, 1, 2), new ItemStack(Items.PUMPKIN_SEEDS), 30, false);
		addSawmillRecipe(new ItemStack(MBlocks.pumpkin_smiley, 1, 2), new ItemStack(MBlocks.pumpkin_smashed), new ItemStack(Items.PUMPKIN_SEEDS), 10, false);
	}
	
	public void addSawmillRecipe(ItemStack input, ItemStack result, ItemStack extra, Integer chance, boolean isStone)
	{
		this.jeiMilling.put(input, result);
		this.jeiExtra.put(input, extra);
		this.jeiIsStone.put(input, isStone);
		
		for(EnumFacing facing : EnumFacing.VALUES)
		{
			this.milling.put(facing, input, result);
			this.extra.put(facing, input, extra);
			this.chance.put(facing, input, chance);
			this.isStone.put(facing, input, isStone);
		}
	}
	
	public void addSawmillRecipe(ItemStack input, ItemStack result, boolean isStone)
	{
		this.jeiMilling.put(input, result);
		this.jeiIsStone.put(input, isStone);
		
		for(EnumFacing facing : EnumFacing.VALUES)
		{
			this.milling.put(facing, input, result);
			this.isStone.put(facing, input, isStone);
		}
	}
	
	public void addSawmillRecipe(boolean vertical, ItemStack input, ItemStack result, ItemStack extra, Integer chance, boolean isStone)
	{
		this.jeiMilling.put(input, result);
		this.jeiExtra.put(input, extra);
		this.jeiIsStone.put(input, isStone);
		
		if(vertical)
		{
			this.milling.put(EnumFacing.UP, input, result);
			this.milling.put(EnumFacing.DOWN, input, result);
			this.extra.put(EnumFacing.UP, input, extra);
			this.extra.put(EnumFacing.DOWN, input, extra);
			this.chance.put(EnumFacing.UP, input, chance);
			this.chance.put(EnumFacing.DOWN, input, chance);
			this.isStone.put(EnumFacing.UP, input, isStone);
			this.isStone.put(EnumFacing.DOWN, input, isStone);
		}
		else
		{
			for(EnumFacing facing : EnumFacing.HORIZONTALS)
			{
				this.milling.put(facing, input, result);
				this.extra.put(facing, input, extra);
				this.chance.put(facing, input, chance);
				this.isStone.put(facing, input, isStone);
			}
		}
	}
	
	public void addSawmillRecipe(boolean vertical, ItemStack input, ItemStack result, boolean isStone)
	{
		this.jeiMilling.put(input, result);
		this.jeiIsStone.put(input, isStone);
		
		if(vertical)
		{
			this.milling.put(EnumFacing.UP, input, result);
			this.milling.put(EnumFacing.DOWN, input, result);
			this.isStone.put(EnumFacing.UP, input, isStone);
			this.isStone.put(EnumFacing.DOWN, input, isStone);
		}
		else
		{
			for(EnumFacing facing : EnumFacing.HORIZONTALS)
			{
				this.milling.put(facing, input, result);
				this.isStone.put(facing, input, isStone);
			}
		}
	}
	
	public void addSawmillRecipe(EnumFacing direction, ItemStack input, ItemStack result, ItemStack extra, Integer chance, boolean isStone)
	{
		this.jeiMilling.put(input, result);
		this.jeiExtra.put(input, extra);
		this.jeiIsStone.put(input, isStone);
		
		this.milling.put(direction, input, result);
		this.extra.put(direction, input, extra);
		this.chance.put(direction, input, chance);
		this.isStone.put(direction, input, isStone);
	}
	
	public void addSawmillRecipe(EnumFacing direction, ItemStack input, ItemStack result, boolean isStone)
	{
		this.jeiMilling.put(input, result);
		this.jeiIsStone.put(input, isStone);
		
		this.milling.put(direction, input, result);
		this.isStone.put(direction, input, isStone);
	}
	
	public ItemStack getSawmillResult(EnumFacing facing, ItemStack input1)
	{
        for(Table.Cell<EnumFacing, ItemStack, ItemStack> table : this.milling.cellSet())
        {
            if(((table.getRowKey() == null || facing == table.getRowKey()) && ItemUtil.compareStacks(input1, table.getColumnKey())))
            {
                return table.getValue();
            }
        }
        
        return ItemStack.EMPTY;
	}
	
	public ItemStack getExtra(EnumFacing facing, ItemStack input1)
	{
        for(Table.Cell<EnumFacing, ItemStack, ItemStack> table : this.extra.cellSet())
        {
            if(((table.getRowKey() == null || facing == table.getRowKey()) && ItemUtil.compareStacks(input1, table.getColumnKey())))
            {
                return table.getValue();
            }
        }
        
        return ItemStack.EMPTY;
	}
	
	public int getChance(EnumFacing facing, ItemStack input1)
	{
        for(Table.Cell<EnumFacing, ItemStack, Integer> table : this.chance.cellSet())
        {
            if(((table.getRowKey() == null || facing == table.getRowKey()) && ItemUtil.compareStacks(input1, table.getColumnKey())))
            {
                return table.getValue();
            }
        }
        
        return 0;
	}
	
	public boolean isStone(EnumFacing facing, ItemStack input1)
	{
        for(Table.Cell<EnumFacing, ItemStack, Boolean> table : this.isStone.cellSet())
        {
            if(((table.getRowKey() == null || facing == table.getRowKey()) && ItemUtil.compareStacks(input1, table.getColumnKey())))
            {
                return table.getValue();
            }
        }
        
        return false;
	}
}
