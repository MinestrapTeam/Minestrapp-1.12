package minestrapp.crafting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import minestrapp.MBlocks;
import minestrapp.MItems;
import minestrapp.block.BlockMGlowDyed.EnumGlowDye;
import minestrapp.utils.ItemUtil;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class PressurizerRecipes
{
	private static final PressurizerRecipes PRESSURIZING = new PressurizerRecipes();
	public final Map<List<ItemStack>, ItemStack> pressurizing = Maps.<List<ItemStack>, ItemStack>newHashMap();
	private final Map<ItemStack, Float> expValues = Maps.<ItemStack, Float>newHashMap();
	
	public static PressurizerRecipes instance()
	{
		return PRESSURIZING;
	}
	
	private PressurizerRecipes()
	{
		//Geological
		addPressurizerRecipe(new ItemStack(MItems.mud_ball), new ItemStack(MItems.mud_ball), new ItemStack(MItems.mud_ball), new ItemStack(MItems.mud_ball), new ItemStack(MBlocks.mud), 0.2F);
		addPressurizerRecipe(new ItemStack(Blocks.DIRT, 3, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.DIRT, 3, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.DIRT, 3, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.WATER_BUCKET), new ItemStack(MBlocks.mud, 9), 0.2F);
		addPressurizerRecipe(new ItemStack(MBlocks.clay_soil, 3, OreDictionary.WILDCARD_VALUE), new ItemStack(MBlocks.clay_soil, 3, OreDictionary.WILDCARD_VALUE), new ItemStack(MBlocks.clay_soil, 3, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.WATER_BUCKET), new ItemStack(MBlocks.mud, 9), 0.2F);
		addPressurizerRecipe(new ItemStack(MBlocks.permafrost, 3, OreDictionary.WILDCARD_VALUE), new ItemStack(MBlocks.permafrost, 3, OreDictionary.WILDCARD_VALUE), new ItemStack(MBlocks.permafrost, 3, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.WATER_BUCKET), new ItemStack(MBlocks.mud, 9), 0.2F);
		addPressurizerRecipe(new ItemStack(MItems.mud_ball), new ItemStack(MItems.mud_ball), new ItemStack(MItems.mud_ball), new ItemStack(MItems.chunks, 1, 1), new ItemStack(Blocks.DIRT, 1, 0), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.mud_ball), new ItemStack(MItems.mud_ball), new ItemStack(Items.FLINT), new ItemStack(MItems.chunks, 1, 1), new ItemStack(Blocks.DIRT, 1, 1), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.mud_ball), new ItemStack(MItems.mud_ball), new ItemStack(Blocks.LEAVES, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(MItems.chunks, 1, 1), new ItemStack(Blocks.DIRT, 1, 2), 0.4F);
		addPressurizerRecipe(new ItemStack(MItems.mud_ball), new ItemStack(MItems.mud_ball), new ItemStack(MBlocks.leaves, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(MItems.chunks, 1, 1), new ItemStack(Blocks.DIRT, 1, 2), 0.4F);
		addPressurizerRecipe(new ItemStack(MItems.mud_ball), new ItemStack(MItems.mud_ball), new ItemStack(MBlocks.palm_fronds), new ItemStack(MItems.chunks, 1, 1), new ItemStack(Blocks.DIRT, 1, 2), 0.4F);
		addPressurizerRecipe(new ItemStack(MItems.mud_ball), new ItemStack(MItems.mud_ball), new ItemStack(MBlocks.palm_fronds_dead), new ItemStack(MItems.chunks, 1, 1), new ItemStack(Blocks.DIRT, 1, 2), 0.4F);
		addPressurizerRecipe(new ItemStack(MItems.mud_ball), new ItemStack(MItems.mud_ball), new ItemStack(Items.CLAY_BALL), new ItemStack(Items.CLAY_BALL), new ItemStack(MBlocks.clay_soil, 1, 0), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.mud_ball), new ItemStack(MItems.mud_ball), new ItemStack(Items.FLINT), new ItemStack(Items.CLAY_BALL), new ItemStack(MBlocks.clay_soil, 1, 1), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.mud_ball), new ItemStack(MItems.mud_ball), new ItemStack(Blocks.LEAVES, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.CLAY_BALL), new ItemStack(MBlocks.clay_soil, 1, 2), 0.4F);
		addPressurizerRecipe(new ItemStack(MItems.mud_ball), new ItemStack(MItems.mud_ball), new ItemStack(MBlocks.leaves, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.CLAY_BALL), new ItemStack(MBlocks.clay_soil, 1, 2), 0.4F);
		addPressurizerRecipe(new ItemStack(MItems.mud_ball), new ItemStack(MItems.mud_ball), new ItemStack(MBlocks.palm_fronds), new ItemStack(Items.CLAY_BALL), new ItemStack(MBlocks.clay_soil, 1, 2), 0.4F);
		addPressurizerRecipe(new ItemStack(MItems.mud_ball), new ItemStack(MItems.mud_ball), new ItemStack(MBlocks.palm_fronds_dead), new ItemStack(Items.CLAY_BALL), new ItemStack(MBlocks.clay_soil, 1, 2), 0.4F);
		addPressurizerRecipe(new ItemStack(MItems.mud_ball), new ItemStack(MItems.mud_ball), new ItemStack(Items.SNOWBALL), new ItemStack(Items.SNOWBALL), new ItemStack(MBlocks.permafrost, 1, 0), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.mud_ball), new ItemStack(MItems.mud_ball), new ItemStack(Items.FLINT), new ItemStack(Items.SNOWBALL), new ItemStack(MBlocks.permafrost, 1, 1), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.mud_ball), new ItemStack(MItems.mud_ball), new ItemStack(Blocks.LEAVES, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.SNOWBALL), new ItemStack(MBlocks.permafrost, 1, 2), 0.4F);
		addPressurizerRecipe(new ItemStack(MItems.mud_ball), new ItemStack(MItems.mud_ball), new ItemStack(MBlocks.leaves, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.SNOWBALL), new ItemStack(MBlocks.permafrost, 1, 2), 0.4F);
		addPressurizerRecipe(new ItemStack(MItems.mud_ball), new ItemStack(MItems.mud_ball), new ItemStack(MBlocks.palm_fronds), new ItemStack(Items.SNOWBALL), new ItemStack(MBlocks.permafrost, 1, 2), 0.4F);
		addPressurizerRecipe(new ItemStack(MItems.mud_ball), new ItemStack(MItems.mud_ball), new ItemStack(MBlocks.palm_fronds_dead), new ItemStack(Items.SNOWBALL), new ItemStack(MBlocks.permafrost, 1, 2), 0.4F);
		addPressurizerRecipe(new ItemStack(MItems.chunks, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(MItems.chunks, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.FLINT), new ItemStack(Items.FLINT), new ItemStack(Blocks.GRAVEL), 0.3F);
		addPressurizerRecipe(new ItemStack(Blocks.SAND, 2, 0), new ItemStack(Blocks.SAND, 2, 0), new ItemStack(Items.CLAY_BALL), new ItemStack(Items.IRON_NUGGET), new ItemStack(Blocks.SAND, 4, 1), 0.4F);
		addPressurizerRecipe(new ItemStack(Blocks.SAND, 1, 0), new ItemStack(Blocks.SAND, 1, 0), new ItemStack(Blocks.SAND, 1, 0), new ItemStack(Blocks.SAND, 1, 0), new ItemStack(Blocks.SANDSTONE, 2, 0), 0.3F);
		addPressurizerRecipe(new ItemStack(Blocks.SAND, 1, 1), new ItemStack(Blocks.SAND, 1, 1), new ItemStack(Blocks.SAND, 1, 1), new ItemStack(Blocks.SAND, 1, 1), new ItemStack(Blocks.RED_SANDSTONE, 2, 0), 0.3F);
		addPressurizerRecipe(new ItemStack(MBlocks.cold_sand, 1, 0), new ItemStack(MBlocks.cold_sand, 1, 0), new ItemStack(MBlocks.cold_sand, 1, 0), new ItemStack(MBlocks.cold_sand, 1, 0), new ItemStack(MBlocks.sandstone, 2, 2), 0.3F);
		addPressurizerRecipe(new ItemStack(MBlocks.cold_sand, 1, 1), new ItemStack(MBlocks.cold_sand, 1, 1), new ItemStack(MBlocks.cold_sand, 1, 1), new ItemStack(MBlocks.cold_sand, 1, 1), new ItemStack(MBlocks.sandstone, 2, 6), 0.3F);
		addPressurizerRecipe(new ItemStack(Items.CLAY_BALL), new ItemStack(Items.CLAY_BALL), new ItemStack(Items.CLAY_BALL), new ItemStack(Items.CLAY_BALL), new ItemStack(Blocks.CLAY), 0.2F);
		addPressurizerRecipe(new ItemStack(Blocks.SAND, 1, 0), new ItemStack(Blocks.SAND, 1, 0), new ItemStack(MItems.gems, 1, 1), new ItemStack(MItems.mud_ball), new ItemStack(Blocks.CLAY), 0.4F);
		addPressurizerRecipe(new ItemStack(Blocks.GRAVEL), new ItemStack(Blocks.GRAVEL), new ItemStack(Blocks.GRAVEL), new ItemStack(Blocks.GRAVEL), new ItemStack(Blocks.STONE, 4), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.chunks, 1, 0), new ItemStack(MItems.chunks, 1, 0), new ItemStack(MItems.chunks, 1, 0), new ItemStack(MItems.chunks, 1, 0), new ItemStack(MBlocks.cobblestone, 1, 0), 0.2F);
		addPressurizerRecipe(new ItemStack(MItems.chunks, 1, 1), new ItemStack(MItems.chunks, 1, 1), new ItemStack(MItems.chunks, 1, 1), new ItemStack(MItems.chunks, 1, 1), new ItemStack(Blocks.COBBLESTONE), 0.2F);
		addPressurizerRecipe(new ItemStack(MItems.chunks, 1, 2), new ItemStack(MItems.chunks, 1, 2), new ItemStack(MItems.chunks, 1, 2), new ItemStack(MItems.chunks, 1, 2), new ItemStack(MBlocks.cobblestone, 1, 3), 0.2F);
		addPressurizerRecipe(new ItemStack(MItems.chunks, 1, 3), new ItemStack(MItems.chunks, 1, 3), new ItemStack(MItems.chunks, 1, 3), new ItemStack(MItems.chunks, 1, 3), new ItemStack(MBlocks.cobblestone, 1, 5), 0.2F);
		addPressurizerRecipe(new ItemStack(MItems.chunks, 1, 4), new ItemStack(MItems.chunks, 1, 4), new ItemStack(MItems.chunks, 1, 4), new ItemStack(MItems.chunks, 1, 4), new ItemStack(MBlocks.cobblestone, 1, 7), 0.2F);
		addPressurizerRecipe(new ItemStack(MBlocks.cobblestone, 1, 0), new ItemStack(MBlocks.cobblestone, 1, 0), new ItemStack(MBlocks.cobblestone, 1, 0), new ItemStack(MBlocks.cobblestone, 1, 0), new ItemStack(MBlocks.stone, 1, 1), 0.3F);
		addPressurizerRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(Blocks.COBBLESTONE), new ItemStack(Blocks.COBBLESTONE), new ItemStack(Blocks.COBBLESTONE), new ItemStack(MBlocks.stone, 1, 2), 0.3F);
		addPressurizerRecipe(new ItemStack(MBlocks.cobblestone, 1, 3), new ItemStack(MBlocks.cobblestone, 1, 3), new ItemStack(MBlocks.cobblestone, 1, 3), new ItemStack(MBlocks.cobblestone, 1, 3), new ItemStack(MBlocks.stone, 1, 4), 0.3F);
		addPressurizerRecipe(new ItemStack(MBlocks.cobblestone, 1, 5), new ItemStack(MBlocks.cobblestone, 1, 5), new ItemStack(MBlocks.cobblestone, 1, 5), new ItemStack(MBlocks.cobblestone, 1, 5), new ItemStack(MBlocks.stone, 1, 6), 0.3F);
		addPressurizerRecipe(new ItemStack(MBlocks.cobblestone, 1, 7), new ItemStack(MBlocks.cobblestone, 1, 7), new ItemStack(MBlocks.cobblestone, 1, 7), new ItemStack(MBlocks.cobblestone, 1, 7), new ItemStack(MBlocks.stone, 1, 8), 0.3F);
		addPressurizerRecipe(new ItemStack(Blocks.COBBLESTONE, 3), new ItemStack(Blocks.SAND), new ItemStack(Items.CLAY_BALL), new ItemStack(Items.IRON_NUGGET), new ItemStack(MBlocks.stone, 4, 0), 0.4F);
		addPressurizerRecipe(new ItemStack(Blocks.COBBLESTONE, 4), new ItemStack(Items.SNOWBALL), new ItemStack(MItems.nuggets, 1, 0), new ItemStack(MItems.nuggets, 1, 1), new ItemStack(MBlocks.stone, 4, 3), 0.4F);
		addPressurizerRecipe(new ItemStack(MBlocks.cobblestone, 3, 3), new ItemStack(Blocks.ICE), new ItemStack(Items.SNOWBALL), new ItemStack(MItems.mud_ball), new ItemStack(MBlocks.stone, 4, 5), 0.4F);
		addPressurizerRecipe(new ItemStack(Blocks.COBBLESTONE, 4), new ItemStack(Items.CLAY_BALL), new ItemStack(MItems.salt), new ItemStack(MItems.mud_ball), new ItemStack(MBlocks.stone, 4, 7), 0.4F);
		addPressurizerRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(MBlocks.cobblestone, 1, 0), new ItemStack(Blocks.SAND, 1, 1), new ItemStack(MItems.gems, 1, 1), new ItemStack(Blocks.STONE, 4, 1), 0.5F);
		addPressurizerRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(MBlocks.cobblestone, 1, 7), new ItemStack(Blocks.SAND, 1, 0), new ItemStack(Items.QUARTZ), new ItemStack(Blocks.STONE, 4, 3), 0.5F);
		addPressurizerRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(MBlocks.cobblestone, 1, 3), new ItemStack(MBlocks.cold_sand, 1, 0), new ItemStack(MItems.gems, 1, 2), new ItemStack(Blocks.STONE, 4, 5), 0.5F);
		addPressurizerRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(MBlocks.cobblestone, 1, 5), new ItemStack(Blocks.CLAY), new ItemStack(Items.FLINT), new ItemStack(MBlocks.decor_stone, 4, 6), 0.5F);
		addPressurizerRecipe(new ItemStack(MItems.gems, 1, 1), new ItemStack(MItems.gems, 1, 1), new ItemStack(MItems.gems, 1, 1), new ItemStack(MItems.gems, 1, 1), new ItemStack(MBlocks.desert_quartz, 1, 0), 0.2F);
		addPressurizerRecipe(new ItemStack(MItems.gems, 1, 3), new ItemStack(MItems.gems, 1, 3), new ItemStack(MItems.gems, 1, 3), new ItemStack(MItems.gems, 1, 3), new ItemStack(MBlocks.irradiant_quartz, 1, 0), 0.2F);
		addPressurizerRecipe(new ItemStack(MItems.gems, 1, 0), new ItemStack(MItems.gems, 1, 0), new ItemStack(MItems.gems, 1, 0), new ItemStack(MItems.gems, 1, 0), new ItemStack(MBlocks.block_sunstone, 1, 0), 0.2F);
		addPressurizerRecipe(new ItemStack(MItems.chunks, 1, 3), new ItemStack(MItems.gems, 1, 2), new ItemStack(MItems.gems, 1, 3), new ItemStack(MItems.salt), new ItemStack(Items.PRISMARINE_SHARD, 4), 0.5F);
		addPressurizerRecipe(new ItemStack(Items.PRISMARINE_SHARD), new ItemStack(Items.PRISMARINE_SHARD), new ItemStack(MItems.gems, 1, 0), new ItemStack(MItems.gems, 1, 2), new ItemStack(Items.PRISMARINE_CRYSTALS, 2), 0.6F);
		addPressurizerRecipe(new ItemStack(Items.PRISMARINE_SHARD), new ItemStack(Items.PRISMARINE_SHARD), new ItemStack(Items.PRISMARINE_SHARD), new ItemStack(Items.PRISMARINE_SHARD), new ItemStack(Blocks.PRISMARINE, 1, 0), 0.2F);
		addPressurizerRecipe(new ItemStack(Items.REDSTONE, 3), new ItemStack(Items.REDSTONE, 3), new ItemStack(Items.REDSTONE, 3), new ItemStack(Blocks.SAND, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(MBlocks.block_redstone_sandy_lit), 0.8F);
		addPressurizerRecipe(new ItemStack(Items.REDSTONE, 3), new ItemStack(Items.REDSTONE, 3), new ItemStack(Items.REDSTONE, 3), new ItemStack(MBlocks.cold_sand, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(MBlocks.block_redstone_frosted_lit), 0.8F);
		addPressurizerRecipe(new ItemStack(MBlocks.redstone_frosted, 3), new ItemStack(MBlocks.redstone_frosted, 3), new ItemStack(MBlocks.redstone_frosted, 3), new ItemStack(Blocks.SNOW), new ItemStack(MBlocks.block_redstone_icy_lit), 0.8F);
		addPressurizerRecipe(new ItemStack(Items.REDSTONE, 3), new ItemStack(Items.REDSTONE, 3), new ItemStack(Items.REDSTONE, 3), new ItemStack(MItems.salt), new ItemStack(MBlocks.block_redstone_briny_lit), 0.8F);
		addPressurizerRecipe(new ItemStack(MItems.chunks, 1, 5), new ItemStack(MItems.chunks, 1, 5), new ItemStack(MItems.chunks, 1, 5), new ItemStack(MItems.chunks, 1, 5), new ItemStack(Blocks.NETHERRACK), 0.2F);
		addPressurizerRecipe(new ItemStack(Items.QUARTZ), new ItemStack(Items.QUARTZ), new ItemStack(Items.QUARTZ), new ItemStack(Items.QUARTZ), new ItemStack(Blocks.QUARTZ_BLOCK, 1, 0), 0.2F);
		addPressurizerRecipe(new ItemStack(Items.GLOWSTONE_DUST), new ItemStack(Items.GLOWSTONE_DUST), new ItemStack(Items.GLOWSTONE_DUST), new ItemStack(Items.GLOWSTONE_DUST), new ItemStack(Blocks.GLOWSTONE), 0.2F);
		addPressurizerRecipe(new ItemStack(MItems.chunks, 2, 5), new ItemStack(MItems.chunks, 2, 5), new ItemStack(Items.MAGMA_CREAM), new ItemStack(Items.MAGMA_CREAM), new ItemStack(Blocks.MAGMA, 2), 0.3F);
		addPressurizerRecipe(new ItemStack(Blocks.MAGMA, 4), new ItemStack(Blocks.MAGMA, 4), new ItemStack(Blocks.MAGMA, 4), new ItemStack(Blocks.MAGMA, 4), new ItemStack(Blocks.OBSIDIAN), 1F);
		addPressurizerRecipe(new ItemStack(MItems.chunks, 1, 6), new ItemStack(MItems.chunks, 1, 6), new ItemStack(MItems.chunks, 1, 6), new ItemStack(MItems.chunks, 1, 6), new ItemStack(Blocks.END_STONE), 0.2F);
		addPressurizerRecipe(new ItemStack(MBlocks.stone, 64, 1), new ItemStack(MBlocks.stone, 64, 4), new ItemStack(MBlocks.stone, 64, 6), new ItemStack(MBlocks.stone, 64, 8), new ItemStack(Blocks.BEDROCK), 10F);
		addPressurizerRecipe(new ItemStack(Blocks.BEDROCK, 64), new ItemStack(Blocks.BEDROCK, 64), new ItemStack(Blocks.BEDROCK, 64), new ItemStack(Blocks.BEDROCK, 64), new ItemStack(MBlocks.invincium), 100F);
		addPressurizerRecipe(new ItemStack(MBlocks.invincium), new ItemStack(MBlocks.glacieric_ice, 16), new ItemStack(MBlocks.glacieric_ice, 16), new ItemStack(MBlocks.glacieric_ice, 16), new ItemStack(MBlocks.glacial_invincium), 200F);
		
		//Material Compacting
		addPressurizerRecipe(new ItemStack(Items.SNOWBALL), new ItemStack(Items.SNOWBALL), new ItemStack(Items.SNOWBALL), new ItemStack(Items.SNOWBALL), new ItemStack(Blocks.SNOW), 0.2F);
		addPressurizerRecipe(new ItemStack(Blocks.SNOW_LAYER, 2), new ItemStack(Blocks.SNOW_LAYER, 2), new ItemStack(Blocks.SNOW_LAYER, 2), new ItemStack(Blocks.SNOW_LAYER, 2), new ItemStack(Blocks.SNOW), 0.2F);
		addPressurizerRecipe(new ItemStack(Blocks.SNOW), new ItemStack(Blocks.SNOW), new ItemStack(Blocks.SNOW), new ItemStack(Blocks.SNOW), new ItemStack(Blocks.ICE), 0.3F);
		addPressurizerRecipe(new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE), new ItemStack(Blocks.PACKED_ICE), 0.3F);
		addPressurizerRecipe(new ItemStack(Blocks.PACKED_ICE), new ItemStack(Blocks.PACKED_ICE), new ItemStack(Blocks.PACKED_ICE), new ItemStack(MItems.gems, 1, 6), new ItemStack(MBlocks.glacieric_ice), 0.6F);
		addPressurizerRecipe(new ItemStack(Blocks.BROWN_MUSHROOM), new ItemStack(Blocks.BROWN_MUSHROOM), new ItemStack(Blocks.BROWN_MUSHROOM), new ItemStack(Blocks.BROWN_MUSHROOM), new ItemStack(Blocks.BROWN_MUSHROOM_BLOCK), 0.5F);
		addPressurizerRecipe(new ItemStack(Blocks.RED_MUSHROOM), new ItemStack(Blocks.RED_MUSHROOM), new ItemStack(Blocks.RED_MUSHROOM), new ItemStack(Blocks.RED_MUSHROOM), new ItemStack(Blocks.RED_MUSHROOM_BLOCK), 0.5F);
		addPressurizerRecipe(new ItemStack(MBlocks.purple_glowshroom), new ItemStack(MBlocks.purple_glowshroom), new ItemStack(MBlocks.purple_glowshroom), new ItemStack(MBlocks.purple_glowshroom), new ItemStack(MBlocks.purple_glowshroom_block), 0.5F);
		addPressurizerRecipe(new ItemStack(MBlocks.green_glowshroom), new ItemStack(MBlocks.green_glowshroom), new ItemStack(MBlocks.green_glowshroom), new ItemStack(MBlocks.green_glowshroom), new ItemStack(MBlocks.green_glowshroom_block), 0.5F);
		addPressurizerRecipe(new ItemStack(MBlocks.blue_glowshroom), new ItemStack(MBlocks.blue_glowshroom), new ItemStack(MBlocks.blue_glowshroom), new ItemStack(MBlocks.blue_glowshroom), new ItemStack(MBlocks.blue_glowshroom_block), 0.5F);
		addPressurizerRecipe(new ItemStack(Items.WHEAT, 3), new ItemStack(Items.WHEAT, 2), new ItemStack(Items.WHEAT, 2), new ItemStack(Items.WHEAT, 2), new ItemStack(Blocks.HAY_BLOCK), 0.2F);
		addPressurizerRecipe(new ItemStack(Items.NETHER_WART, 3), new ItemStack(Items.NETHER_WART, 2), new ItemStack(Items.NETHER_WART, 2), new ItemStack(Items.NETHER_WART, 2), new ItemStack(Blocks.NETHER_WART_BLOCK), 0.2F);
		addPressurizerRecipe(new ItemStack(Items.DYE, 3, 15), new ItemStack(Items.DYE, 2, 15), new ItemStack(Items.DYE, 2, 15), new ItemStack(Items.DYE, 2, 15), new ItemStack(Blocks.BONE_BLOCK), 0.2F);
		addPressurizerRecipe(new ItemStack(Items.SLIME_BALL, 3), new ItemStack(Items.SLIME_BALL, 2), new ItemStack(Items.SLIME_BALL, 2), new ItemStack(Items.SLIME_BALL, 2), new ItemStack(Blocks.SLIME_BLOCK), 0.2F);
		addPressurizerRecipe(new ItemStack(MItems.mite_honey, 3), new ItemStack(MItems.mite_honey, 2), new ItemStack(MItems.mite_honey, 2), new ItemStack(MItems.mite_honey, 2), new ItemStack(MBlocks.block_mite_honey), 0.2F);
		addPressurizerRecipe(new ItemStack(MItems.salt, 3), new ItemStack(MItems.salt, 2), new ItemStack(MItems.salt, 2), new ItemStack(MItems.salt, 2), new ItemStack(MBlocks.block_salt), 0.2F);
		addPressurizerRecipe(new ItemStack(MItems.nuggets, 3, 0), new ItemStack(MItems.nuggets, 2, 0), new ItemStack(MItems.nuggets, 2, 0), new ItemStack(MItems.nuggets, 2, 0), new ItemStack(MItems.ingots, 1, 0), 0.2F);
		addPressurizerRecipe(new ItemStack(MItems.nuggets, 3, 1), new ItemStack(MItems.nuggets, 2, 1), new ItemStack(MItems.nuggets, 2, 1), new ItemStack(MItems.nuggets, 2, 1), new ItemStack(MItems.ingots, 1, 1), 0.2F);
		addPressurizerRecipe(new ItemStack(Items.IRON_NUGGET, 3), new ItemStack(Items.IRON_NUGGET, 2), new ItemStack(Items.IRON_NUGGET, 2), new ItemStack(Items.IRON_NUGGET, 2), new ItemStack(Items.IRON_INGOT), 0.2F);
		addPressurizerRecipe(new ItemStack(MItems.nuggets, 3, 2), new ItemStack(MItems.nuggets, 2, 2), new ItemStack(MItems.nuggets, 2, 2), new ItemStack(MItems.nuggets, 2, 2), new ItemStack(MItems.ingots, 1, 2), 0.2F);
		addPressurizerRecipe(new ItemStack(MItems.nuggets, 3, 3), new ItemStack(MItems.nuggets, 2, 3), new ItemStack(MItems.nuggets, 2, 3), new ItemStack(MItems.nuggets, 2, 3), new ItemStack(MItems.ingots, 1, 3), 0.2F);
		addPressurizerRecipe(new ItemStack(Items.GOLD_NUGGET, 3), new ItemStack(Items.GOLD_NUGGET, 2), new ItemStack(Items.GOLD_NUGGET, 2), new ItemStack(Items.GOLD_NUGGET, 2), new ItemStack(Items.GOLD_INGOT), 0.2F);
		addPressurizerRecipe(new ItemStack(MItems.nuggets, 3, 4), new ItemStack(MItems.nuggets, 2, 4), new ItemStack(MItems.nuggets, 2, 4), new ItemStack(MItems.nuggets, 2, 4), new ItemStack(MItems.ingots, 1, 4), 0.2F);
		addPressurizerRecipe(new ItemStack(MItems.nuggets, 3, 5), new ItemStack(MItems.nuggets, 2, 5), new ItemStack(MItems.nuggets, 2, 5), new ItemStack(MItems.nuggets, 2, 5), new ItemStack(MItems.ingots, 1, 6), 0.2F);
		addPressurizerRecipe(new ItemStack(MItems.nuggets, 3, 7), new ItemStack(MItems.nuggets, 2, 7), new ItemStack(MItems.nuggets, 2, 7), new ItemStack(MItems.nuggets, 2, 7), new ItemStack(MItems.ingots, 1, 5), 0.2F);
		addPressurizerRecipe(new ItemStack(MItems.nuggets, 3, 6), new ItemStack(MItems.nuggets, 2, 6), new ItemStack(MItems.nuggets, 2, 6), new ItemStack(MItems.nuggets, 2, 6), new ItemStack(MItems.ingots, 1, 7), 0.2F);
		addPressurizerRecipe(new ItemStack(MItems.nuggets, 3, 8), new ItemStack(MItems.nuggets, 2, 8), new ItemStack(MItems.nuggets, 2, 8), new ItemStack(MItems.nuggets, 2, 8), new ItemStack(MItems.ingots, 1, 8), 0.2F);
		addPressurizerRecipe(new ItemStack(MItems.nuggets, 3, 9), new ItemStack(MItems.nuggets, 2, 9), new ItemStack(MItems.nuggets, 2, 9), new ItemStack(MItems.nuggets, 2, 9), new ItemStack(MItems.ingots, 1, 9), 0.2F);
		addPressurizerRecipe(new ItemStack(Items.COAL, 3, 0), new ItemStack(Items.COAL, 2, 0), new ItemStack(Items.COAL, 2, 0), new ItemStack(Items.COAL, 2, 0), new ItemStack(Blocks.COAL_BLOCK), 0.2F);
		addPressurizerRecipe(new ItemStack(MItems.gems, 3, 2), new ItemStack(MItems.gems, 2, 2), new ItemStack(MItems.gems, 2, 2), new ItemStack(MItems.gems, 2, 2), new ItemStack(MBlocks.block_rock_crystal), 0.2F);
		addPressurizerRecipe(new ItemStack(MItems.ingots, 3, 0), new ItemStack(MItems.ingots, 2, 0), new ItemStack(MItems.ingots, 2, 0), new ItemStack(MItems.ingots, 2, 0), new ItemStack(MBlocks.block_copper), 0.2F);
		addPressurizerRecipe(new ItemStack(MItems.ingots, 3, 1), new ItemStack(MItems.ingots, 2, 1), new ItemStack(MItems.ingots, 2, 1), new ItemStack(MItems.ingots, 2, 1), new ItemStack(MBlocks.block_tin), 0.2F);
		addPressurizerRecipe(new ItemStack(Items.IRON_INGOT, 3), new ItemStack(Items.IRON_INGOT, 2), new ItemStack(Items.IRON_INGOT, 2), new ItemStack(Items.IRON_INGOT, 2), new ItemStack(Blocks.IRON_BLOCK), 0.2F);
		addPressurizerRecipe(new ItemStack(MItems.ingots, 3, 2), new ItemStack(MItems.ingots, 2, 2), new ItemStack(MItems.ingots, 2, 2), new ItemStack(MItems.ingots, 2, 2), new ItemStack(MBlocks.block_bronze), 0.2F);
		addPressurizerRecipe(new ItemStack(MItems.ingots, 3, 3), new ItemStack(MItems.ingots, 2, 3), new ItemStack(MItems.ingots, 2, 3), new ItemStack(MItems.ingots, 2, 3), new ItemStack(MBlocks.block_steel), 0.2F);
		addPressurizerRecipe(new ItemStack(Items.GOLD_INGOT, 3), new ItemStack(Items.GOLD_INGOT, 2), new ItemStack(Items.GOLD_INGOT, 2), new ItemStack(Items.GOLD_INGOT, 2), new ItemStack(Blocks.GOLD_BLOCK), 0.2F);
		addPressurizerRecipe(new ItemStack(MItems.gems, 3, 4), new ItemStack(MItems.gems, 2, 4), new ItemStack(MItems.gems, 2, 4), new ItemStack(MItems.gems, 2, 4), new ItemStack(MBlocks.block_meurodite), 0.2F);
		addPressurizerRecipe(new ItemStack(MBlocks.redstone_sandy, 3), new ItemStack(MBlocks.redstone_sandy, 2), new ItemStack(MBlocks.redstone_sandy, 2), new ItemStack(MBlocks.redstone_sandy, 2), new ItemStack(MBlocks.block_redstone_sandy_lit), 0.2F);
		addPressurizerRecipe(new ItemStack(Items.REDSTONE, 3), new ItemStack(Items.REDSTONE, 2), new ItemStack(Items.REDSTONE, 2), new ItemStack(Items.REDSTONE, 2), new ItemStack(Blocks.REDSTONE_BLOCK), 0.2F);
		addPressurizerRecipe(new ItemStack(MBlocks.redstone_frosted, 3), new ItemStack(MBlocks.redstone_frosted, 2), new ItemStack(MBlocks.redstone_frosted, 2), new ItemStack(MBlocks.redstone_frosted, 2), new ItemStack(MBlocks.block_redstone_frosted_lit), 0.2F);
		addPressurizerRecipe(new ItemStack(MBlocks.redstone_icy, 3), new ItemStack(MBlocks.redstone_icy, 2), new ItemStack(MBlocks.redstone_icy, 2), new ItemStack(MBlocks.redstone_icy, 2), new ItemStack(MBlocks.block_redstone_icy_lit), 0.2F);
		addPressurizerRecipe(new ItemStack(MBlocks.redstone_briny, 3), new ItemStack(MBlocks.redstone_briny, 2), new ItemStack(MBlocks.redstone_briny, 2), new ItemStack(MBlocks.redstone_briny, 2), new ItemStack(MBlocks.block_redstone_briny_lit), 0.2F);
		addPressurizerRecipe(new ItemStack(MBlocks.redstone_sandy, 3), new ItemStack(Items.REDSTONE, 2), new ItemStack(Items.REDSTONE, 2), new ItemStack(Items.REDSTONE, 2), new ItemStack(Blocks.REDSTONE_BLOCK), 0.2F);
		addPressurizerRecipe(new ItemStack(MBlocks.redstone_frosted, 3), new ItemStack(Items.REDSTONE, 2), new ItemStack(Items.REDSTONE, 2), new ItemStack(Items.REDSTONE, 2), new ItemStack(Blocks.REDSTONE_BLOCK), 0.2F);
		addPressurizerRecipe(new ItemStack(MBlocks.redstone_icy, 3), new ItemStack(Items.REDSTONE, 2), new ItemStack(Items.REDSTONE, 2), new ItemStack(Items.REDSTONE, 2), new ItemStack(Blocks.REDSTONE_BLOCK), 0.2F);
		addPressurizerRecipe(new ItemStack(MBlocks.redstone_briny, 3), new ItemStack(Items.REDSTONE, 2), new ItemStack(Items.REDSTONE, 2), new ItemStack(Items.REDSTONE, 2), new ItemStack(Blocks.REDSTONE_BLOCK), 0.2F);
		addPressurizerRecipe(new ItemStack(Items.DYE, 3, 4), new ItemStack(Items.DYE, 2, 4), new ItemStack(Items.DYE, 2, 4), new ItemStack(Items.DYE, 2, 4), new ItemStack(Blocks.LAPIS_BLOCK), 0.2F);
		addPressurizerRecipe(new ItemStack(MItems.irradium, 3), new ItemStack(MItems.irradium, 2), new ItemStack(MItems.irradium, 2), new ItemStack(MItems.irradium, 2), new ItemStack(MBlocks.block_irradium), 0.2F);
		addPressurizerRecipe(new ItemStack(MItems.ingots, 3, 4), new ItemStack(MItems.ingots, 2, 4), new ItemStack(MItems.ingots, 2, 4), new ItemStack(MItems.ingots, 2, 4), new ItemStack(MBlocks.block_torite), 0.2F);
		addPressurizerRecipe(new ItemStack(MItems.ingots, 3, 6), new ItemStack(MItems.ingots, 2, 6), new ItemStack(MItems.ingots, 2, 6), new ItemStack(MItems.ingots, 2, 6), new ItemStack(MBlocks.block_glacierite), 0.2F);
		addPressurizerRecipe(new ItemStack(Items.DIAMOND, 3), new ItemStack(Items.DIAMOND, 2), new ItemStack(Items.DIAMOND, 2), new ItemStack(Items.DIAMOND, 2), new ItemStack(Blocks.DIAMOND_BLOCK), 0.2F);
		addPressurizerRecipe(new ItemStack(Items.EMERALD, 3), new ItemStack(Items.EMERALD, 2), new ItemStack(Items.EMERALD, 2), new ItemStack(Items.EMERALD, 2), new ItemStack(Blocks.EMERALD_BLOCK), 0.2F);
		addPressurizerRecipe(new ItemStack(MItems.ingots, 3, 5), new ItemStack(MItems.ingots, 2, 5), new ItemStack(MItems.ingots, 2, 5), new ItemStack(MItems.ingots, 2, 5), new ItemStack(MBlocks.block_titanium), 0.2F);
		addPressurizerRecipe(new ItemStack(MItems.ingots, 3, 7), new ItemStack(MItems.ingots, 2, 7), new ItemStack(MItems.ingots, 2, 7), new ItemStack(MItems.ingots, 2, 7), new ItemStack(MBlocks.block_blazium), 0.2F);
		addPressurizerRecipe(new ItemStack(MItems.gem_soul, 3), new ItemStack(MItems.gem_soul, 2), new ItemStack(MItems.gem_soul, 2), new ItemStack(MItems.gem_soul, 2), new ItemStack(MBlocks.block_soul), 0.2F);
		addPressurizerRecipe(new ItemStack(MItems.ingots, 3, 8), new ItemStack(MItems.ingots, 2, 8), new ItemStack(MItems.ingots, 2, 8), new ItemStack(MItems.ingots, 2, 8), new ItemStack(MBlocks.block_dimensium), 0.2F);
		
		//Misc.
		addPressurizerRecipe(new ItemStack(Blocks.SPONGE, 1, 1), new ItemStack(Blocks.SPONGE, 1, 1), new ItemStack(Blocks.SPONGE, 1, 1), new ItemStack(Blocks.SPONGE, 1, 1), new ItemStack(Blocks.SPONGE, 4, 0), 0.2F);
		addPressurizerRecipe(new ItemStack(MBlocks.lava_sponge, 1, 1), new ItemStack(MBlocks.lava_sponge, 1, 1), new ItemStack(MBlocks.lava_sponge, 1, 1), new ItemStack(MBlocks.lava_sponge, 1, 1), new ItemStack(MBlocks.lava_sponge, 4, 0), 0.2F);
		addPressurizerRecipe(new ItemStack(MItems.sawdust), new ItemStack(MItems.sawdust), new ItemStack(MItems.bark), new ItemStack(MItems.bark), new ItemStack(MBlocks.cork), 0.6F);
		addPressurizerRecipe(new ItemStack(MItems.sawdust), new ItemStack(MItems.sawdust), new ItemStack(MItems.bark), new ItemStack(MItems.coconut_shell), new ItemStack(MBlocks.cork), 0.6F);
		addPressurizerRecipe(new ItemStack(MItems.sawdust), new ItemStack(MItems.sawdust), new ItemStack(MItems.coconut_shell), new ItemStack(MItems.coconut_shell), new ItemStack(MBlocks.cork), 0.6F);
		addPressurizerRecipe(new ItemStack(MItems.sawdust), new ItemStack(MItems.sawdust), new ItemStack(MItems.bark), new ItemStack(MItems.charroot), new ItemStack(MBlocks.cork), 0.6F);
		addPressurizerRecipe(new ItemStack(MItems.sawdust), new ItemStack(MItems.sawdust), new ItemStack(MItems.coconut_shell), new ItemStack(MItems.charroot), new ItemStack(MBlocks.cork), 0.6F);
		addPressurizerRecipe(new ItemStack(MItems.sawdust), new ItemStack(MItems.sawdust), new ItemStack(MItems.charroot), new ItemStack(MItems.charroot), new ItemStack(MBlocks.cork), 0.6F);
		addPressurizerRecipe(new ItemStack(Items.DYE, 1, 4), new ItemStack(Items.DYE, 1, 4), new ItemStack(Items.DYE, 1, 1), new ItemStack(Items.DYE, 1, 1), new ItemStack(Items.DYE, 4, 5), 0.3F);
		addPressurizerRecipe(new ItemStack(Items.DYE, 1, 4), new ItemStack(Items.DYE, 1, 4), new ItemStack(Items.DYE, 1, 2), new ItemStack(Items.DYE, 1, 2), new ItemStack(Items.DYE, 4, 6), 0.3F);
		addPressurizerRecipe(new ItemStack(Items.DYE, 2, 0), new ItemStack(Items.DYE, 1, 15), new ItemStack(Items.DYE, 1, 15), new ItemStack(Items.DYE, 2, 15), new ItemStack(Items.DYE, 6, 7), 0.3F);
		addPressurizerRecipe(new ItemStack(Items.DYE, 1, 0), new ItemStack(Items.DYE, 1, 0), new ItemStack(Items.DYE, 2, 15), new ItemStack(Items.DYE, 2, 15), new ItemStack(Items.DYE, 6, 7), 0.3F);
		addPressurizerRecipe(new ItemStack(Items.DYE, 1, 8), new ItemStack(Items.DYE, 1, 8), new ItemStack(Items.DYE, 1, 15), new ItemStack(Items.DYE, 1, 15), new ItemStack(Items.DYE, 4, 7), 0.3F);
		addPressurizerRecipe(new ItemStack(Items.DYE, 1, 0), new ItemStack(Items.DYE, 1, 0), new ItemStack(Items.DYE, 1, 15), new ItemStack(Items.DYE, 1, 15), new ItemStack(Items.DYE, 4, 8), 0.3F);
		addPressurizerRecipe(new ItemStack(Items.DYE, 1, 1), new ItemStack(Items.DYE, 1, 1), new ItemStack(Items.DYE, 1, 15), new ItemStack(Items.DYE, 1, 15), new ItemStack(Items.DYE, 4, 9), 0.3F);
		addPressurizerRecipe(new ItemStack(Items.DYE, 1, 2), new ItemStack(Items.DYE, 1, 2), new ItemStack(Items.DYE, 1, 15), new ItemStack(Items.DYE, 1, 15), new ItemStack(Items.DYE, 4, 10), 0.3F);
		addPressurizerRecipe(new ItemStack(Items.DYE, 1, 4), new ItemStack(Items.DYE, 1, 4), new ItemStack(Items.DYE, 1, 15), new ItemStack(Items.DYE, 1, 15), new ItemStack(Items.DYE, 4, 12), 0.3F);
		addPressurizerRecipe(new ItemStack(Items.DYE, 1, 5), new ItemStack(Items.DYE, 1, 5), new ItemStack(Items.DYE, 1, 9), new ItemStack(Items.DYE, 1, 9), new ItemStack(Items.DYE, 4, 13), 0.3F);
		addPressurizerRecipe(new ItemStack(Items.DYE, 2, 4), new ItemStack(Items.DYE, 2, 1), new ItemStack(Items.DYE, 1, 9), new ItemStack(Items.DYE, 1, 9), new ItemStack(Items.DYE, 6, 13), 0.3F);
		addPressurizerRecipe(new ItemStack(Items.DYE, 2, 1), new ItemStack(Items.DYE, 2, 9), new ItemStack(Items.DYE, 1, 4), new ItemStack(Items.DYE, 1, 4), new ItemStack(Items.DYE, 6, 13), 0.3F);
		addPressurizerRecipe(new ItemStack(Items.DYE, 2, 4), new ItemStack(Items.DYE, 2, 9), new ItemStack(Items.DYE, 1, 1), new ItemStack(Items.DYE, 1, 1), new ItemStack(Items.DYE, 6, 13), 0.3F);
		addPressurizerRecipe(new ItemStack(Items.DYE, 1, 4), new ItemStack(Items.DYE, 1, 1), new ItemStack(Items.DYE, 1, 1), new ItemStack(Items.DYE, 1, 9), new ItemStack(Items.DYE, 4, 13), 0.3F);
		addPressurizerRecipe(new ItemStack(Items.DYE, 1, 1), new ItemStack(Items.DYE, 1, 1), new ItemStack(Items.DYE, 1, 11), new ItemStack(Items.DYE, 1, 11), new ItemStack(Items.DYE, 4, 14), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.dye, 1, 0), new ItemStack(MItems.dye, 1, 0), new ItemStack(Items.DYE, 1, 13), new ItemStack(Items.DYE, 1, 13), new ItemStack(MItems.dye, 4, 1), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.dye, 1, 0), new ItemStack(MItems.dye, 1, 0), new ItemStack(Items.DYE, 1, 1), new ItemStack(Items.DYE, 1, 1), new ItemStack(MItems.dye, 4, 2), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.dye, 1, 0), new ItemStack(MItems.dye, 1, 0), new ItemStack(Items.DYE, 1, 14), new ItemStack(Items.DYE, 1, 14), new ItemStack(MItems.dye, 4, 3), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.dye, 1, 0), new ItemStack(MItems.dye, 1, 0), new ItemStack(Items.DYE, 1, 11), new ItemStack(Items.DYE, 1, 11), new ItemStack(MItems.dye, 4, 4), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.dye, 1, 0), new ItemStack(MItems.dye, 1, 0), new ItemStack(Items.DYE, 1, 10), new ItemStack(Items.DYE, 1, 10), new ItemStack(MItems.dye, 4, 5), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.dye, 1, 0), new ItemStack(MItems.dye, 1, 0), new ItemStack(Items.DYE, 1, 12), new ItemStack(Items.DYE, 1, 12), new ItemStack(MItems.dye, 4, 6), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.dye, 1, 0), new ItemStack(MItems.dye, 1, 0), new ItemStack(Items.DYE, 1, 4), new ItemStack(Items.DYE, 1, 4), new ItemStack(MItems.dye, 4, 7), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.dye, 1, 0), new ItemStack(MItems.dye, 1, 0), new ItemStack(Items.DYE, 1, 5), new ItemStack(Items.DYE, 1, 5), new ItemStack(MItems.dye, 4, 8), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.dye, 1, 4), new ItemStack(MItems.dye, 1, 4), new ItemStack(MItems.dye, 1, 7), new ItemStack(MItems.dye, 1, 7), new ItemStack(MItems.dye, 4, 0), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.dye, 2, 4), new ItemStack(MItems.dye, 2, 5), new ItemStack(MItems.dye, 1, 8), new ItemStack(MItems.dye, 1, 8), new ItemStack(MItems.dye, 6, 0), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.dye, 2, 4), new ItemStack(MItems.dye, 1, 5), new ItemStack(MItems.dye, 1, 5), new ItemStack(MItems.dye, 2, 8), new ItemStack(MItems.dye, 6, 0), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.dye, 1, 4), new ItemStack(MItems.dye, 1, 4), new ItemStack(MItems.dye, 2, 5), new ItemStack(MItems.dye, 2, 8), new ItemStack(MItems.dye, 4, 0), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.dye, 1, 2), new ItemStack(MItems.dye, 1, 5), new ItemStack(MItems.dye, 1, 5), new ItemStack(MItems.dye, 1, 8), new ItemStack(MItems.dye, 4, 0), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.dye, 1, 2), new ItemStack(MItems.dye, 1, 2), new ItemStack(MItems.dye, 1, 6), new ItemStack(MItems.dye, 1, 6), new ItemStack(MItems.dye, 4, 0), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.dye, 2, 3), new ItemStack(MItems.dye, 2, 6), new ItemStack(MItems.dye, 1, 7), new ItemStack(MItems.dye, 1, 7), new ItemStack(MItems.dye, 6, 0), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.dye, 2, 3), new ItemStack(MItems.dye, 1, 6), new ItemStack(MItems.dye, 1, 6), new ItemStack(MItems.dye, 2, 7), new ItemStack(MItems.dye, 6, 0), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.dye, 1, 3), new ItemStack(MItems.dye, 1, 3), new ItemStack(MItems.dye, 2, 6), new ItemStack(MItems.dye, 2, 7), new ItemStack(MItems.dye, 6, 0), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.dye, 1, 3), new ItemStack(MItems.dye, 1, 5), new ItemStack(MItems.dye, 1, 7), new ItemStack(MItems.dye, 1, 7), new ItemStack(MItems.dye, 4, 0), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.dye, 1, 1), new ItemStack(MItems.dye, 1, 1), new ItemStack(MItems.dye, 1, 5), new ItemStack(MItems.dye, 1, 5), new ItemStack(MItems.dye, 4, 0), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.dye, 2, 2), new ItemStack(MItems.dye, 2, 5), new ItemStack(MItems.dye, 1, 7), new ItemStack(MItems.dye, 1, 7), new ItemStack(MItems.dye, 6, 0), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.dye, 2, 2), new ItemStack(MItems.dye, 1, 5), new ItemStack(MItems.dye, 1, 5), new ItemStack(MItems.dye, 2, 7), new ItemStack(MItems.dye, 6, 0), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.dye, 1, 2), new ItemStack(MItems.dye, 1, 2), new ItemStack(MItems.dye, 2, 5), new ItemStack(MItems.dye, 2, 7), new ItemStack(MItems.dye, 4, 0), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.dye, 1, 2), new ItemStack(MItems.dye, 1, 2), new ItemStack(MItems.dye, 1, 7), new ItemStack(MItems.dye, 1, 7), new ItemStack(MItems.dye, 4, 1), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.dye, 1, 2), new ItemStack(MItems.dye, 1, 2), new ItemStack(MItems.dye, 1, 4), new ItemStack(MItems.dye, 1, 4), new ItemStack(MItems.dye, 4, 3), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.dye, 2, 5), new ItemStack(MItems.dye, 1, 4), new ItemStack(MItems.dye, 1, 4), new ItemStack(MItems.dye, 2, 4), new ItemStack(MItems.dye, 6, 3), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.dye, 1, 5), new ItemStack(MItems.dye, 1, 5), new ItemStack(MItems.dye, 2, 4), new ItemStack(MItems.dye, 2, 4), new ItemStack(MItems.dye, 6, 3), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.dye, 1, 2), new ItemStack(MItems.dye, 1, 2), new ItemStack(MItems.dye, 1, 5), new ItemStack(MItems.dye, 1, 5), new ItemStack(MItems.dye, 4, 4), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.dye, 1, 5), new ItemStack(MItems.dye, 1, 5), new ItemStack(MItems.dye, 1, 7), new ItemStack(MItems.dye, 1, 7), new ItemStack(MItems.dye, 4, 6), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.dye, 1, 1), new ItemStack(MItems.dye, 1, 1), new ItemStack(MItems.dye, 1, 7), new ItemStack(MItems.dye, 1, 7), new ItemStack(MItems.dye, 4, 8), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.dye, 2, 2), new ItemStack(MItems.dye, 1, 7), new ItemStack(MItems.dye, 1, 7), new ItemStack(MItems.dye, 2, 7), new ItemStack(MItems.dye, 6, 8), 0.3F);
		addPressurizerRecipe(new ItemStack(MItems.dye, 1, 2), new ItemStack(MItems.dye, 1, 2), new ItemStack(MItems.dye, 2, 7), new ItemStack(MItems.dye, 2, 7), new ItemStack(MItems.dye, 6, 8), 0.3F);
		addPressurizerRecipe(new ItemStack(MBlocks.moss), new ItemStack(MBlocks.moss), new ItemStack(MBlocks.moss), new ItemStack(MBlocks.moss), new ItemStack(Items.COAL, 2, 1), 0.3F);
		addPressurizerRecipe(new ItemStack(Items.COAL, 1, 1), new ItemStack(Items.COAL, 1, 1), new ItemStack(Items.COAL, 1, 1), new ItemStack(Items.COAL, 1, 1), new ItemStack(Items.COAL, 2, 0), 0.3F);
		addPressurizerRecipe(new ItemStack(Blocks.COAL_BLOCK, 4), new ItemStack(Blocks.COAL_BLOCK, 4), new ItemStack(Blocks.COAL_BLOCK, 4), new ItemStack(Blocks.COAL_BLOCK, 4), new ItemStack(Items.DIAMOND), 0.8F);
		addPressurizerRecipe(new ItemStack(Items.COAL, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.GUNPOWDER), new ItemStack(Items.BLAZE_POWDER), new ItemStack(MItems.salt), new ItemStack(Items.FIRE_CHARGE, 4), 0.6F);
		addPressurizerRecipe(new ItemStack(MItems.gems, 1, 1), new ItemStack(MItems.gems, 1, 1), new ItemStack(MItems.gems, 1, 1), new ItemStack(MItems.irradium), new ItemStack(MItems.gems, 1, 3), 0.5F);
		addPressurizerRecipe(new ItemStack(Items.BLAZE_POWDER), new ItemStack(Items.BLAZE_POWDER), new ItemStack(Items.BLAZE_POWDER), new ItemStack(MItems.gems, 1, 0), new ItemStack(MItems.gems, 1, 5), 0.5F);
		addPressurizerRecipe(new ItemStack(Items.BLAZE_POWDER), new ItemStack(Items.BLAZE_POWDER), new ItemStack(Items.BLAZE_POWDER), new ItemStack(Items.BONE), new ItemStack(Items.BLAZE_ROD), 0.5F);
		
		//Concrete
		for(int i = 0 ; i < EnumDyeColor.values().length ; i++)
		{
			addPressurizerRecipe(new ItemStack(Blocks.GRAVEL, 4), new ItemStack(Blocks.SAND, 2, 0), new ItemStack(Blocks.SAND, 2, 0), new ItemStack(Items.DYE, 1, 15 - i), new ItemStack(Blocks.CONCRETE_POWDER, 8, i), 0.3F);
			addPressurizerRecipe(new ItemStack(Blocks.GRAVEL, 2), new ItemStack(Blocks.GRAVEL, 2), new ItemStack(Blocks.SAND, 4, 0), new ItemStack(Items.DYE, 1, 15 - i), new ItemStack(Blocks.CONCRETE_POWDER, 8, i), 0.3F);
		}
		for(int i = 0 ; i < EnumGlowDye.values().length ; i++)
		{
			addPressurizerRecipe(new ItemStack(Blocks.GRAVEL, 4), new ItemStack(Blocks.SAND, 2, 0), new ItemStack(Blocks.SAND, 2, 0), new ItemStack(MItems.dye, 1, i), new ItemStack(MBlocks.glow_concrete_powder, 8, i), 0.3F);
			addPressurizerRecipe(new ItemStack(Blocks.GRAVEL, 2), new ItemStack(Blocks.GRAVEL, 2), new ItemStack(Blocks.SAND, 4, 0), new ItemStack(MItems.dye, 1, i), new ItemStack(MBlocks.glow_concrete_powder, 8, i), 0.3F);
		}
	}
	
	public void addPressurizerRecipe(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack input4, ItemStack output, float exp)
	{
		List<ItemStack> inputs = new ArrayList<ItemStack>();
		inputs.add(input1);
		inputs.add(input2);
		inputs.add(input3);
		inputs.add(input4);
		
		this.pressurizing.put(inputs, output);
		this.expValues.put(output, exp);
	}
	
	public ItemStack getPressurizingResult(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack input4)
	{
		Entry<List<ItemStack>, ItemStack> entry = getEntryFromIngredients(input1, input2, input3, input4);
		
		if(entry != null)
			return ((ItemStack)entry.getValue());
		return ItemStack.EMPTY;
	}
	
	public float getPressurizingExperience(ItemStack stack)
	{
		for (Entry<ItemStack, Float> entry : this.expValues.entrySet())
		{
			if(ItemUtil.compareStacks(stack, (ItemStack)entry.getKey()))
				return ((Float)entry.getValue()).floatValue();
		}
		
		return 0.0F;
	}
	
	public Map<List<ItemStack>, ItemStack> getPressurizing()
	{
		return this.pressurizing;
	}
	
	public ItemStack getIngredient(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack input4, int index)
	{
		Entry<List<ItemStack>, ItemStack> entry = getEntryFromIngredients(input1, input2, input3, input4);
		
		if(entry != null)
			return ((ItemStack)entry.getKey().get(index));
		return ItemStack.EMPTY;
	}
	
	public Entry<List<ItemStack>, ItemStack> getEntryFromIngredients(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack input4)
	{
		for(Entry<List<ItemStack>, ItemStack> entry : this.pressurizing.entrySet())
		{
			boolean match1 = false;
			boolean match2 = false;
			boolean match3 = false;
			List<Integer> matchedItems = new ArrayList<Integer>();
			
			for(int i = 0 ; i < 4 ; i++)
			{
				if(ItemUtil.compareStacks(input1, ((List<ItemStack>)entry.getKey()).get(i)))
				{
					match1 = true;
					matchedItems.add(i);
					break;
				}
			}
			if(match1)
			{
				for(int i = 0 ; i < 4 ; i++)
				{
					if(!matchedItems.contains(i) && ItemUtil.compareStacks(input2, ((List<ItemStack>)entry.getKey()).get(i)))
					{
						match2 = true;
						matchedItems.add(i);
						break;
					}
				}
				if(match2)
				{
					for(int i = 0 ; i < 4 ; i++)
					{
						if(!matchedItems.contains(i) && ItemUtil.compareStacks(input3, ((List<ItemStack>)entry.getKey()).get(i)))
						{
							match3 = true;
							matchedItems.add(i);
							break;
						}
					}
					if(match3)
					{
						for(int i = 0 ; i < 4 ; i++)
						{
							if(!matchedItems.contains(i) && ItemUtil.compareStacks(input4, ((List<ItemStack>)entry.getKey()).get(i)))
							{
								return entry;
							}
						}
					}
				}
			}
		}
		
		return null;
	}
}
