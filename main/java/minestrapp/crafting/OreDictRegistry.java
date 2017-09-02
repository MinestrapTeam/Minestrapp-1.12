package minestrapp.crafting;

import minestrapp.MBlocks;
import minestrapp.MItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictRegistry
{
	public static void register()
	{
		OreDictionary.registerOre("sand", new ItemStack(MBlocks.cold_sand, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("dirt", MBlocks.clay_soil);
		OreDictionary.registerOre("grass", MBlocks.clay_grass);
		OreDictionary.registerOre("dirt", MBlocks.permafrost);
		OreDictionary.registerOre("grass", MBlocks.lichen);
		OreDictionary.registerOre("dirt", MBlocks.mud);
		OreDictionary.registerOre("dirt", MBlocks.dried_mud);
		OreDictionary.registerOre("stone", new ItemStack(MBlocks.stone, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("cobblestone", new ItemStack(MBlocks.cobblestone, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("oreCoal", new ItemStack(MBlocks.ore_coal, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("oreCopper", new ItemStack(MBlocks.ore_copper, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("oreTin", new ItemStack(MBlocks.ore_tin, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("oreIron", new ItemStack(MBlocks.ore_iron, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("oreGold", new ItemStack(MBlocks.ore_gold, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("oreRedstone", new ItemStack(MBlocks.ore_redstone, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("oreLapis", new ItemStack(MBlocks.ore_lapis, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("oreMeurodite", new ItemStack(MBlocks.ore_meurodite, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("oreTorite", new ItemStack(MBlocks.ore_torite, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("oreDiamond", new ItemStack(MBlocks.ore_diamond, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("oreEmerald", new ItemStack(MBlocks.ore_emerald, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("oreTitanium", new ItemStack(MBlocks.ore_titanium, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("oreSoul", new ItemStack(MBlocks.ore_soul, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("oreDimensium", new ItemStack(MBlocks.ore_dimensium, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("blockCopper", MBlocks.block_copper);
		OreDictionary.registerOre("blockTin", MBlocks.block_tin);
		OreDictionary.registerOre("blockBronze", MBlocks.block_bronze);
		OreDictionary.registerOre("blockSteel", MBlocks.block_steel);
		OreDictionary.registerOre("blockMeurodite", MBlocks.block_meurodite);
		OreDictionary.registerOre("blockTorite", MBlocks.block_torite);
		OreDictionary.registerOre("blockTitanium", MBlocks.block_titanium);
		OreDictionary.registerOre("blockBlazium", MBlocks.block_blazium);
		OreDictionary.registerOre("blockDimensium", MBlocks.block_dimensium);
		OreDictionary.registerOre("ingotCopper", new ItemStack(MItems.ingots, 1, 0));
		OreDictionary.registerOre("ingotTin", new ItemStack(MItems.ingots, 1, 1));
		OreDictionary.registerOre("ingotBronze", new ItemStack(MItems.ingots, 1, 2));
		OreDictionary.registerOre("ingotSteel", new ItemStack(MItems.ingots, 1, 3));
		OreDictionary.registerOre("ingotTorite", new ItemStack(MItems.ingots, 1, 4));
		OreDictionary.registerOre("ingotTitanium", new ItemStack(MItems.ingots, 1, 5));
		OreDictionary.registerOre("ingotGlacierite", new ItemStack(MItems.ingots, 1, 6));
		OreDictionary.registerOre("ingotBlazium", new ItemStack(MItems.ingots, 1, 7));
		OreDictionary.registerOre("ingotDimensium", new ItemStack(MItems.ingots, 1, 8));
		OreDictionary.registerOre("gemSunstone", new ItemStack(MItems.gems, 1, 0));
		OreDictionary.registerOre("gemQuartz", new ItemStack(MItems.gems, 1, 1));
		OreDictionary.registerOre("gemCrystal", new ItemStack(MItems.gems, 1, 2));
		OreDictionary.registerOre("gemQuartz", new ItemStack(MItems.gems, 1, 3));
		OreDictionary.registerOre("gemMeurodite", new ItemStack(MItems.gems, 1, 4));
		OreDictionary.registerOre("gemBlazium", new ItemStack(MItems.gems, 1, 5));
		OreDictionary.registerOre("gemGlacierite", new ItemStack(MItems.gems, 1, 6));
		OreDictionary.registerOre("gemSoul", MItems.gem_soul);
		OreDictionary.registerOre("seedPepper", MItems.pepper_seeds);
		OreDictionary.registerOre("seedCabbage", MItems.cabbage_seeds);
		OreDictionary.registerOre("seedCelery", MItems.celery_seeds);
		OreDictionary.registerOre("seedTomato", MItems.tomato_seeds);
	}
}
