package minestrapp.crafting;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

import minestrapp.MBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class FreezingRecipes
{
	private static final FreezingRecipes FREEZING = new FreezingRecipes();
	private final Map<ItemStack, ItemStack> physicalFreezing = Maps.<ItemStack, ItemStack>newHashMap();
	private final Map<ItemStack, ItemStack> lightFreezing = Maps.<ItemStack, ItemStack>newHashMap();
	private final Map<ItemStack, ItemStack> lightFreezingGUISafe = Maps.<ItemStack, ItemStack>newHashMap();
	private final Map<ItemStack, ItemStack> deepFreezing = Maps.<ItemStack, ItemStack>newHashMap();
	private final Map<ItemStack, ItemStack> deepFreezingGUISafe = Maps.<ItemStack, ItemStack>newHashMap();
	
	public static FreezingRecipes instance()
	{
		return FREEZING;
	}
	
	private FreezingRecipes()
	{
		//In-World-Only Freezing Recipes
		this.addPhysicalFreezingRecipe(new ItemStack(Blocks.WATER, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.FROSTED_ICE));
		this.addPhysicalFreezingRecipe(new ItemStack(Blocks.FLOWING_WATER, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.FROSTED_ICE));
		this.addPhysicalFreezingRecipe(new ItemStack(Blocks.LAVA, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.MAGMA));
		this.addPhysicalFreezingRecipe(new ItemStack(Blocks.FLOWING_LAVA, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.MAGMA));
		this.addPhysicalFreezingRecipe(new ItemStack(Blocks.SNOW_LAYER, 1, 0), new ItemStack(Blocks.SNOW_LAYER, 1, 1));
		this.addPhysicalFreezingRecipe(new ItemStack(Blocks.SNOW_LAYER, 1, 1), new ItemStack(Blocks.SNOW_LAYER, 1, 2));
		this.addPhysicalFreezingRecipe(new ItemStack(Blocks.SNOW_LAYER, 1, 2), new ItemStack(Blocks.SNOW_LAYER, 1, 3));
		this.addPhysicalFreezingRecipe(new ItemStack(Blocks.SNOW_LAYER, 1, 3), new ItemStack(Blocks.SNOW_LAYER, 1, 4));
		this.addPhysicalFreezingRecipe(new ItemStack(Blocks.SNOW_LAYER, 1, 4), new ItemStack(Blocks.SNOW_LAYER, 1, 5));
		this.addPhysicalFreezingRecipe(new ItemStack(Blocks.SNOW_LAYER, 1, 5), new ItemStack(Blocks.SNOW_LAYER, 1, 6));
		this.addPhysicalFreezingRecipe(new ItemStack(Blocks.SNOW_LAYER, 1, 6), new ItemStack(Blocks.SNOW));
		this.addPhysicalFreezingRecipe(new ItemStack(Blocks.FIRE, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.AIR));
		this.addPhysicalFreezingRecipe(new ItemStack(Blocks.GRASS_PATH), new ItemStack(MBlocks.lichen_path));
		this.addPhysicalFreezingRecipe(new ItemStack(Blocks.FARMLAND, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(MBlocks.permafrost_farmland));
		this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.clay_grass_path), new ItemStack(MBlocks.lichen_path));
		this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.clay_farmland, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(MBlocks.permafrost_farmland));
		this.addPhysicalFreezingRecipe(new ItemStack(Blocks.DEADBUSH), new ItemStack(Blocks.SNOW_LAYER));
		this.addPhysicalFreezingRecipe(new ItemStack(Blocks.TALLGRASS, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.SNOW_LAYER));
		this.addPhysicalFreezingRecipe(new ItemStack(Blocks.DOUBLE_PLANT, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.SNOW_LAYER, 1, 2));
		this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.savanna_grass), new ItemStack(Blocks.SNOW_LAYER, 1, 3));
		this.addPhysicalFreezingRecipe(new ItemStack(Blocks.YELLOW_FLOWER), new ItemStack(Blocks.SNOW_LAYER));
		this.addPhysicalFreezingRecipe(new ItemStack(Blocks.RED_FLOWER, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.SNOW_LAYER));
		this.addPhysicalFreezingRecipe(new ItemStack(Blocks.RED_MUSHROOM), new ItemStack(Blocks.SNOW_LAYER));
		this.addPhysicalFreezingRecipe(new ItemStack(Blocks.BROWN_MUSHROOM), new ItemStack(Blocks.SNOW_LAYER));
		this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.blue_glowshroom), new ItemStack(Blocks.SNOW_LAYER));
		this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.green_glowshroom), new ItemStack(Blocks.SNOW_LAYER));
		this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.purple_glowshroom), new ItemStack(Blocks.SNOW_LAYER));
		this.addPhysicalFreezingRecipe(new ItemStack(Blocks.SAPLING, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.SNOW_LAYER, 1, 1));
		this.addPhysicalFreezingRecipe(new ItemStack(Blocks.REEDS), new ItemStack(Blocks.SNOW_LAYER, 1, 3));
		this.addPhysicalFreezingRecipe(new ItemStack(Blocks.VINE, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.SNOW_LAYER, 1, 1));
		this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.moss), new ItemStack(Blocks.SNOW_LAYER));
		this.addPhysicalFreezingRecipe(new ItemStack(Blocks.CAKE, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.SNOW_LAYER, 1, 1));
		this.addPhysicalFreezingRecipe(new ItemStack(Blocks.FROSTED_ICE, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.PACKED_ICE));
		this.addPhysicalFreezingRecipe(new ItemStack(Blocks.BEETROOTS, 3), new ItemStack(Blocks.BEETROOTS, 1, 2));
		this.addPhysicalFreezingRecipe(new ItemStack(Blocks.BEETROOTS, 2), new ItemStack(Blocks.BEETROOTS, 1, 1));
		this.addPhysicalFreezingRecipe(new ItemStack(Blocks.BEETROOTS, 1), new ItemStack(Blocks.BEETROOTS, 1, 0));
		this.addPhysicalFreezingRecipe(new ItemStack(Blocks.BEETROOTS, 0), new ItemStack(MBlocks.crop_withered));
		this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.crop_withered), new ItemStack(Blocks.SNOW_LAYER));
		for(int j = 1 ; j < 6 ; j++)
		{
			this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.blackberry_bush, 1, j), new ItemStack(MBlocks.blackberry_bush, 1, j - 1));
			this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.blueberry_bush, 1, j), new ItemStack(MBlocks.blueberry_bush, 1, j - 1));
			this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.raspberry_bush, 1, j), new ItemStack(MBlocks.raspberry_bush, 1, j - 1));
			this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.strawberry_bush, 1, j), new ItemStack(MBlocks.strawberry_bush, 1, j - 1));
			this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.mana_bush, 1, j), new ItemStack(MBlocks.mana_bush, 1, j - 1));
			this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.voidberry_bush, 1, j), new ItemStack(MBlocks.voidberry_bush, 1, j - 1));
			this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.glacieric_ice_deposit, 1, j - 1), new ItemStack(MBlocks.glacieric_ice_deposit, 1, j));
		}
		for(int i = 0 ; i < 8 ; i++)
		{
			if(i == 0)
			{
				this.addPhysicalFreezingRecipe(new ItemStack(Blocks.WHEAT, 1, 0), new ItemStack(MBlocks.crop_withered));
				this.addPhysicalFreezingRecipe(new ItemStack(Blocks.CARROTS, 1, 0), new ItemStack(MBlocks.crop_withered));
				this.addPhysicalFreezingRecipe(new ItemStack(Blocks.POTATOES, 1, 0), new ItemStack(MBlocks.crop_withered));
				this.addPhysicalFreezingRecipe(new ItemStack(Blocks.PUMPKIN_STEM, 1, 0), new ItemStack(MBlocks.crop_withered));
				this.addPhysicalFreezingRecipe(new ItemStack(Blocks.MELON_STEM, 1, 0), new ItemStack(MBlocks.crop_withered));
				this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.crop_cabbage, 1, 0), new ItemStack(MBlocks.crop_withered));
				this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.crop_celery, 1, 0), new ItemStack(MBlocks.crop_withered));
				this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.crop_corn, 1, 0), new ItemStack(MBlocks.crop_withered));
				this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.crop_lettuce, 1, 0), new ItemStack(MBlocks.crop_withered));
				this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.crop_onion, 1, 0), new ItemStack(MBlocks.crop_withered));
				this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.crop_peanuts, 1, 0), new ItemStack(MBlocks.crop_withered));
				this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.crop_pepper, 1, 0), new ItemStack(MBlocks.crop_withered));
				this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.crop_tomato, 1, 0), new ItemStack(MBlocks.crop_withered));
			}
			else
			{
				this.addPhysicalFreezingRecipe(new ItemStack(Blocks.WHEAT, 1, i), new ItemStack(Blocks.WHEAT, 1, i - 1));
				this.addPhysicalFreezingRecipe(new ItemStack(Blocks.CARROTS, 1, i), new ItemStack(Blocks.CARROTS, 1, i - 1));
				this.addPhysicalFreezingRecipe(new ItemStack(Blocks.POTATOES, 1, i), new ItemStack(Blocks.POTATOES, 1, i - 1));
				this.addPhysicalFreezingRecipe(new ItemStack(Blocks.PUMPKIN_STEM, 1, i), new ItemStack(Blocks.PUMPKIN_STEM, 1, i - 1));
				this.addPhysicalFreezingRecipe(new ItemStack(Blocks.MELON_STEM, 1, i), new ItemStack(Blocks.MELON_STEM, 1, i - 1));
				this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.crop_cabbage, 1, i), new ItemStack(MBlocks.crop_cabbage, 1, i - 1));
				this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.crop_celery, 1, i), new ItemStack(MBlocks.crop_celery, 1, i - 1));
				this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.crop_corn, 1, i), new ItemStack(MBlocks.crop_corn, 1, i - 1));
				this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.crop_lettuce, 1, i), new ItemStack(MBlocks.crop_lettuce, 1, i - 1));
				this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.crop_onion, 1, i), new ItemStack(MBlocks.crop_onion, 1, i - 1));
				this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.crop_peanuts, 1, i), new ItemStack(MBlocks.crop_peanuts, 1, i - 1));
				this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.crop_pepper, 1, i), new ItemStack(MBlocks.crop_pepper, 1, i - 1));
				this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.crop_tomato, 1, i), new ItemStack(MBlocks.crop_tomato, 1, i - 1));
			}
		}
		for(int l = 0 ; l < 16 ; l++)
		{
			this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.candle_fire, 1, l), new ItemStack(MBlocks.candle, 1, l));
			this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.candle_ender, 1, l), new ItemStack(MBlocks.candle, 1, l));
		}
		for(int m = 0 ; m < 12 ; m++)
		{
			this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.pumpkin_normie_fire, 1, m), new ItemStack(MBlocks.pumpkin_normie, 1, m));
			this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.pumpkin_normie_ender, 1, m), new ItemStack(MBlocks.pumpkin_normie, 1, m));
			this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.pumpkin_smiley_fire, 1, m), new ItemStack(MBlocks.pumpkin_smiley, 1, m));
			this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.pumpkin_smiley_ender, 1, m), new ItemStack(MBlocks.pumpkin_smiley, 1, m));
			this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.pumpkin_creepy_fire, 1, m), new ItemStack(MBlocks.pumpkin_creepy, 1, m));
			this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.pumpkin_creepy_ender, 1, m), new ItemStack(MBlocks.pumpkin_creepy, 1, m));
			this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.pumpkin_dumpy_fire, 1, m), new ItemStack(MBlocks.pumpkin_dumpy, 1, m));
			this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.pumpkin_dumpy_ender, 1, m), new ItemStack(MBlocks.pumpkin_dumpy, 1, m));
			this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.pumpkin_grumpy_fire, 1, m), new ItemStack(MBlocks.pumpkin_grumpy, 1, m));
			this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.pumpkin_grumpy_ender, 1, m), new ItemStack(MBlocks.pumpkin_grumpy, 1, m));
		}
		for(int n = 0 ; n < 4 ; n++)
		{
			this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.pumpkin_smashed_fire, 1, n), new ItemStack(MBlocks.pumpkin_smashed, 1, n));
			this.addPhysicalFreezingRecipe(new ItemStack(MBlocks.pumpkin_smashed_ender, 1, n), new ItemStack(MBlocks.pumpkin_smashed, 1, n));
		}
		
		//Light Freezing Recipes
		this.addLightFreezingRecipe(new ItemStack(Blocks.DIRT, 1, 0), new ItemStack(MBlocks.permafrost, 1, 0), true);
		this.addLightFreezingRecipe(new ItemStack(Blocks.DIRT, 1, 1), new ItemStack(MBlocks.permafrost, 1, 1), true);
		this.addLightFreezingRecipe(new ItemStack(Blocks.DIRT, 1, 2), new ItemStack(MBlocks.permafrost, 1, 2), true);
		this.addLightFreezingRecipe(new ItemStack(Blocks.GRASS), new ItemStack(MBlocks.lichen), true);
		this.addLightFreezingRecipe(new ItemStack(Blocks.MYCELIUM), new ItemStack(MBlocks.lichen), true);
		this.addLightFreezingRecipe(new ItemStack(Blocks.GRASS_PATH), new ItemStack(MBlocks.permafrost), true);
		this.addLightFreezingRecipe(new ItemStack(Blocks.FARMLAND, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(MBlocks.permafrost), true);
		this.addLightFreezingRecipe(new ItemStack(MBlocks.clay_soil, 1, 0), new ItemStack(MBlocks.permafrost, 1, 0), true);
		this.addLightFreezingRecipe(new ItemStack(MBlocks.clay_soil, 1, 1), new ItemStack(MBlocks.permafrost, 1, 1), true);
		this.addLightFreezingRecipe(new ItemStack(MBlocks.clay_soil, 1, 2), new ItemStack(MBlocks.permafrost, 1, 2), true);
		this.addLightFreezingRecipe(new ItemStack(MBlocks.clay_grass), new ItemStack(MBlocks.lichen), true);
		this.addLightFreezingRecipe(new ItemStack(MBlocks.clay_grass_path), new ItemStack(MBlocks.permafrost), true);
		this.addLightFreezingRecipe(new ItemStack(MBlocks.clay_farmland, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(MBlocks.permafrost), true);
		this.addLightFreezingRecipe(new ItemStack(MBlocks.mud), new ItemStack(MBlocks.permafrost), true);
		this.addLightFreezingRecipe(new ItemStack(MBlocks.dried_mud), new ItemStack(MBlocks.cold_sand), true);
		this.addLightFreezingRecipe(new ItemStack(Blocks.ICE), new ItemStack(Blocks.PACKED_ICE), true);
		this.addLightFreezingRecipe(new ItemStack(Blocks.SAND, 1, 0), new ItemStack(MBlocks.cold_sand, 1, 0), true);
		this.addLightFreezingRecipe(new ItemStack(Blocks.SAND, 1, 1), new ItemStack(MBlocks.cold_sand, 1, 1), true);
		this.addLightFreezingRecipe(new ItemStack(MBlocks.desert_quartz_deposit, 1, 1), new ItemStack(MBlocks.desert_quartz_deposit, 1, 2), true);
		this.addLightFreezingRecipe(new ItemStack(MBlocks.desert_quartz_deposit, 1, 3), new ItemStack(MBlocks.desert_quartz_deposit, 1, 4), true);
		this.addLightFreezingRecipe(new ItemStack(Blocks.SNOW_LAYER, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.SNOW), true);
		this.addLightFreezingRecipe(new ItemStack(Blocks.SNOW), new ItemStack(Blocks.ICE), true);
		this.addLightFreezingRecipe(new ItemStack(Blocks.MAGMA), new ItemStack(MBlocks.stone, 1, 1), true);
		this.addLightFreezingRecipe(new ItemStack(Blocks.LEAVES, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.SNOW), true);
		this.addLightFreezingRecipe(new ItemStack(Blocks.LEAVES2, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.SNOW), true);
		this.addLightFreezingRecipe(new ItemStack(MBlocks.cobblestone, 1, 5), new ItemStack(MBlocks.mossy_cobblestone, 1, 5), true);
		this.addLightFreezingRecipe(new ItemStack(MBlocks.cobblestone, 1, 6), new ItemStack(MBlocks.mossy_cobblestone, 1, 6), true);
		this.addLightFreezingRecipe(new ItemStack(MBlocks.stone_bricks, 1, 5), new ItemStack(MBlocks.mossy_stone_bricks, 1, 5), true);
		this.addLightFreezingRecipe(new ItemStack(MBlocks.stone_bricks, 1, 6), new ItemStack(MBlocks.mossy_stone_bricks, 1, 6), true);
		this.addLightFreezingRecipe(new ItemStack(MBlocks.cracked_stone_bricks, 1, 5), new ItemStack(MBlocks.mossy_stone_bricks, 1, 5), true);
		this.addLightFreezingRecipe(new ItemStack(MBlocks.cracked_stone_bricks, 1, 6), new ItemStack(MBlocks.mossy_stone_bricks, 1, 6), true);
		this.addLightFreezingRecipe(new ItemStack(Blocks.BEETROOTS, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.SNOWBALL, 2), false);
		this.addLightFreezingRecipe(new ItemStack(Blocks.WHEAT, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.SNOWBALL, 2), false);
		this.addLightFreezingRecipe(new ItemStack(Blocks.CARROTS, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.SNOWBALL, 2), false);
		this.addLightFreezingRecipe(new ItemStack(Blocks.POTATOES, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.SNOWBALL, 2), false);
		this.addLightFreezingRecipe(new ItemStack(Blocks.PUMPKIN_STEM, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.SNOWBALL, 2), false);
		this.addLightFreezingRecipe(new ItemStack(Blocks.MELON_STEM, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.SNOWBALL, 2), false);
		this.addLightFreezingRecipe(new ItemStack(MBlocks.crop_cabbage, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.SNOWBALL, 2), false);
		this.addLightFreezingRecipe(new ItemStack(MBlocks.crop_celery, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.SNOWBALL, 2), false);
		this.addLightFreezingRecipe(new ItemStack(MBlocks.crop_corn, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.SNOWBALL, 2), false);
		this.addLightFreezingRecipe(new ItemStack(MBlocks.crop_lettuce, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.SNOWBALL, 2), false);
		this.addLightFreezingRecipe(new ItemStack(MBlocks.crop_onion, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.SNOWBALL, 2), false);
		this.addLightFreezingRecipe(new ItemStack(MBlocks.crop_peanuts, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.SNOWBALL, 2), false);
		this.addLightFreezingRecipe(new ItemStack(MBlocks.crop_pepper, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.SNOWBALL, 2), false);
		this.addLightFreezingRecipe(new ItemStack(MBlocks.crop_tomato, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.SNOWBALL, 2), false);
		this.addLightFreezingRecipe(new ItemStack(MBlocks.crop_withered, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.SNOWBALL, 2), false);
		this.addLightFreezingRecipe(new ItemStack(Blocks.COCOA, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.SNOWBALL, 3), false);
		this.addLightFreezingRecipe(new ItemStack(Blocks.DEADBUSH, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.SNOWBALL, 1), true);
		this.addLightFreezingRecipe(new ItemStack(Blocks.TALLGRASS, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.SNOWBALL, 2), true);
		this.addLightFreezingRecipe(new ItemStack(Blocks.DOUBLE_PLANT, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.SNOWBALL, 4), true);
		this.addLightFreezingRecipe(new ItemStack(Blocks.WATERLILY, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.SNOWBALL, 1), true);
		this.addLightFreezingRecipe(new ItemStack(Blocks.SAPLING, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.SNOWBALL, 4), true);
		this.addLightFreezingRecipe(new ItemStack(Blocks.REEDS, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.SNOWBALL, 4), false);
		this.addLightFreezingRecipe(new ItemStack(Blocks.VINE, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.SNOWBALL, 1), true);
		this.addLightFreezingRecipe(new ItemStack(Blocks.RED_FLOWER, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.SNOWBALL, 1), true);
		this.addLightFreezingRecipe(new ItemStack(Blocks.YELLOW_FLOWER, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.SNOWBALL, 1), true);
		this.addLightFreezingRecipe(new ItemStack(MBlocks.moss), new ItemStack(Items.SNOWBALL, 1), true);
		this.addLightFreezingRecipe(new ItemStack(Blocks.RED_MUSHROOM), new ItemStack(Items.SNOWBALL, 1), true);
		this.addLightFreezingRecipe(new ItemStack(Blocks.BROWN_MUSHROOM), new ItemStack(Items.SNOWBALL, 1), true);
		this.addLightFreezingRecipe(new ItemStack(MBlocks.purple_glowshroom), new ItemStack(Items.SNOWBALL, 1), true);
		this.addLightFreezingRecipe(new ItemStack(MBlocks.green_glowshroom), new ItemStack(Items.SNOWBALL, 1), true);
		this.addLightFreezingRecipe(new ItemStack(MBlocks.blue_glowshroom), new ItemStack(Items.SNOWBALL, 1), true);
		this.addLightFreezingRecipe(new ItemStack(MBlocks.savanna_grass, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.SNOWBALL, 3), true);
		
		//Deep Freezing Recipes
		this.addDeepFreezingRecipe(new ItemStack(Blocks.WATER, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.ICE), false);
		this.addDeepFreezingRecipe(new ItemStack(Blocks.FLOWING_WATER, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.ICE), false);
		this.addDeepFreezingRecipe(new ItemStack(Blocks.LAVA, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.OBSIDIAN), false);
		this.addDeepFreezingRecipe(new ItemStack(Blocks.FLOWING_LAVA, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.MAGMA), false);
		this.addDeepFreezingRecipe(new ItemStack(Blocks.PACKED_ICE), new ItemStack(MBlocks.glacieric_ice), true);
		this.addDeepFreezingRecipe(new ItemStack(Blocks.SNOW), new ItemStack(Blocks.PACKED_ICE), true);
		this.addDeepFreezingRecipe(new ItemStack(Blocks.NETHERRACK), new ItemStack(MBlocks.stone, 1, 1), true);
		this.addDeepFreezingRecipe(new ItemStack(Blocks.MAGMA), new ItemStack(Blocks.OBSIDIAN), true);
		this.addDeepFreezingRecipe(new ItemStack(Blocks.CACTUS), new ItemStack(Blocks.ICE), true);
		this.addDeepFreezingRecipe(new ItemStack(Blocks.LEAVES, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.ICE), true);
		this.addDeepFreezingRecipe(new ItemStack(Blocks.LEAVES2, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.ICE), true);
		this.addDeepFreezingRecipe(new ItemStack(Blocks.MELON_BLOCK), new ItemStack(Blocks.ICE), true);
		this.addDeepFreezingRecipe(new ItemStack(MBlocks.melon_bricks), new ItemStack(Blocks.ICE), true);
		this.addDeepFreezingRecipe(new ItemStack(Blocks.PUMPKIN, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.ICE), true);
		this.addDeepFreezingRecipe(new ItemStack(Blocks.LIT_PUMPKIN, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.ICE), true);
		this.addDeepFreezingRecipe(new ItemStack(MBlocks.pumpkin_normie, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.ICE), true);
		this.addDeepFreezingRecipe(new ItemStack(MBlocks.pumpkin_smiley, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.ICE), true);
		this.addDeepFreezingRecipe(new ItemStack(MBlocks.pumpkin_creepy, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.ICE), true);
		this.addDeepFreezingRecipe(new ItemStack(MBlocks.pumpkin_dumpy, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.ICE), true);
		this.addDeepFreezingRecipe(new ItemStack(MBlocks.pumpkin_grumpy, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.ICE), true);
		this.addDeepFreezingRecipe(new ItemStack(MBlocks.pumpkin_smashed, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.ICE), true);
		this.addDeepFreezingRecipe(new ItemStack(Blocks.RED_MUSHROOM_BLOCK, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.ICE), true);
		this.addDeepFreezingRecipe(new ItemStack(Blocks.BROWN_MUSHROOM_BLOCK, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.ICE), true);
		this.addDeepFreezingRecipe(new ItemStack(MBlocks.blue_glowshroom_block, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.ICE), true);
		this.addDeepFreezingRecipe(new ItemStack(MBlocks.green_glowshroom_block, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.ICE), true);
		this.addDeepFreezingRecipe(new ItemStack(MBlocks.purple_glowshroom_block, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.ICE), true);
		for(int k = 0 ; k < 6 ; k++)
		{
			boolean guiSafe = false;
			if(k == 0)
				guiSafe = true;
			
			this.addDeepFreezingRecipe(new ItemStack(MBlocks.bauble_glowshroom_blue, 1, k), new ItemStack(MBlocks.bauble_ice, 1, k), guiSafe);
			this.addDeepFreezingRecipe(new ItemStack(MBlocks.bauble_glowshroom_purple, 1, k), new ItemStack(MBlocks.bauble_ice, 1, k), guiSafe);
			this.addDeepFreezingRecipe(new ItemStack(MBlocks.bauble_glowshroom_green, 1, k), new ItemStack(MBlocks.bauble_ice, 1, k), guiSafe);
		}
		this.addDeepFreezingRecipe(new ItemStack(Blocks.HAY_BLOCK, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.SNOW), true);
		this.addDeepFreezingRecipe(new ItemStack(Blocks.REDSTONE_BLOCK), new ItemStack(MBlocks.block_redstone_frosted_lit), true);
		this.addDeepFreezingRecipe(new ItemStack(MBlocks.block_redstone_frosted_lit), new ItemStack(MBlocks.block_redstone_icy_lit), true);
		this.addDeepFreezingRecipe(new ItemStack(MBlocks.block_redstone_frosted_unlit), new ItemStack(MBlocks.block_redstone_icy_unlit), true);
	}
	
	public void addPhysicalFreezingRecipe(ItemStack input1, ItemStack result)
	{
		this.physicalFreezing.put(input1, result);
	}
	
	public void addLightFreezingRecipe(ItemStack input1, ItemStack result, boolean guiSafe)
	{
		this.lightFreezing.put(input1, result);
		
		if(guiSafe)
			this.lightFreezingGUISafe.put(input1, result);
	}
	
	public void addDeepFreezingRecipe(ItemStack input1, ItemStack result, boolean guiSafe)
	{
		this.deepFreezing.put(input1, result);
		
		if(guiSafe)
			this.deepFreezingGUISafe.put(input1, result);
	}
	
	public ItemStack getFreezingResult(ItemStack stack, String type)
    {
		Map<ItemStack, ItemStack> recipePool = this.lightFreezing;
		if(type == "deep")
			recipePool = this.deepFreezing;
		else if(type == "physical")
			recipePool = this.physicalFreezing;
		
        for (Entry<ItemStack, ItemStack> entry : recipePool.entrySet())
        {
            if (this.compareItemStacks(stack, entry.getKey()))
            {
                return entry.getValue();
            }
        }

        return ItemStack.EMPTY;
    }
	
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
    {
		if(stack1.getItem() != Items.AIR)
			return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == OreDictionary.WILDCARD_VALUE || stack2.getMetadata() == stack1.getMetadata());
		else
			return false;
    }
	
	public Map<ItemStack, ItemStack> getPhysicalFreezingList()
    {
        return this.physicalFreezing;
    }
	
	public Map<ItemStack, ItemStack> getLightFreezingList(boolean GUISafeOnly)
    {
		if(GUISafeOnly)
			return this.lightFreezingGUISafe;
		else
			return this.lightFreezing;
    }
	
	public Map<ItemStack, ItemStack> getDeepFreezingList(boolean GUISafeOnly)
    {
		if(GUISafeOnly)
			return this.deepFreezingGUISafe;
		else
			return this.deepFreezing;
    }
}
