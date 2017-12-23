package minestrapp.crafting;

import minestrapp.MBlocks;
import minestrapp.MItems;
import minestrapp.block.EnumStoneTypeMOnly;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FurnaceRecipes
{
	public static void register()
	{
		GameRegistry.addSmelting(new ItemStack(MBlocks.cold_sand, 1, 0), new ItemStack(Blocks.SAND, 1, 0), 0.1F);
		GameRegistry.addSmelting(new ItemStack(MBlocks.cold_sand, 1, 1), new ItemStack(Blocks.SAND, 1, 1), 0.1F);
		GameRegistry.addSmelting(new ItemStack(MBlocks.permafrost, 1, 0), new ItemStack(Blocks.DIRT, 1, 0), 0.1F);
		GameRegistry.addSmelting(new ItemStack(MBlocks.permafrost, 1, 1), new ItemStack(Blocks.DIRT, 1, 1), 0.1F);
		GameRegistry.addSmelting(new ItemStack(MBlocks.permafrost, 1, 2), new ItemStack(Blocks.DIRT, 1, 0), 0.1F);
		GameRegistry.addSmelting(MBlocks.lichen, new ItemStack(Blocks.DIRT, 1, 0), 0.1F);
		GameRegistry.addSmelting(MBlocks.mud, new ItemStack(MBlocks.dried_mud), 0.1F);
		GameRegistry.addSmelting(MItems.mud_ball, new ItemStack(MItems.bricks, 1, 0), 0.1F);
		GameRegistry.addSmelting(MBlocks.ore_salt, new ItemStack(MItems.salt), 0.1F);
		GameRegistry.addSmelting(MBlocks.ore_coal, new ItemStack(Items.COAL, 1, 0), 0.1F);
		GameRegistry.addSmelting(MBlocks.ore_copper, new ItemStack(MItems.ingots, 1, 0), 0.5F);
		GameRegistry.addSmelting(new ItemStack(MItems.chunks, 1, 7), new ItemStack(MItems.ingots, 1, 0), 0.5F);
		GameRegistry.addSmelting(MBlocks.ore_tin, new ItemStack(MItems.ingots, 1, 1), 0.5F);
		GameRegistry.addSmelting(new ItemStack(MItems.chunks, 1, 8), new ItemStack(MItems.ingots, 1, 1), 0.5F);
		GameRegistry.addSmelting(MBlocks.ore_iron, new ItemStack(Items.IRON_INGOT), 0.7F);
		GameRegistry.addSmelting(new ItemStack(MItems.chunks, 1, 9), new ItemStack(Items.IRON_INGOT), 0.7F);
		GameRegistry.addSmelting(MBlocks.ore_gold, new ItemStack(Items.GOLD_INGOT), 1F);
		GameRegistry.addSmelting(new ItemStack(MItems.chunks, 1, 10), new ItemStack(Items.GOLD_INGOT), 1F);
		GameRegistry.addSmelting(MBlocks.ore_meurodite, new ItemStack(MItems.gems, 1, 4), 0.9F);
		GameRegistry.addSmelting(MBlocks.ore_lapis, new ItemStack(Items.DYE, 1, EnumDyeColor.BLUE.getDyeDamage()), 0.2F);
		GameRegistry.addSmelting(new ItemStack(MBlocks.ore_redstone, 1, 0), new ItemStack(MBlocks.redstone_sandy), 0.7F);
		GameRegistry.addSmelting(new ItemStack(MBlocks.ore_redstone, 1, 1), new ItemStack(MBlocks.redstone_sandy), 0.7F);
		GameRegistry.addSmelting(new ItemStack(MBlocks.ore_redstone, 1, 2), new ItemStack(Items.REDSTONE), 0.7F);
		GameRegistry.addSmelting(new ItemStack(MBlocks.ore_redstone, 1, 3), new ItemStack(MBlocks.redstone_frosted), 0.7F);
		GameRegistry.addSmelting(new ItemStack(MBlocks.ore_redstone, 1, 4), new ItemStack(MBlocks.redstone_frosted), 0.7F);
		GameRegistry.addSmelting(new ItemStack(MBlocks.ore_redstone, 1, 5), new ItemStack(MBlocks.redstone_icy), 0.7F);
		GameRegistry.addSmelting(new ItemStack(MBlocks.ore_redstone, 1, 6), new ItemStack(MBlocks.redstone_icy), 0.7F);
		GameRegistry.addSmelting(new ItemStack(MBlocks.ore_redstone, 1, 7), new ItemStack(MBlocks.redstone_briny), 0.7F);
		GameRegistry.addSmelting(new ItemStack(MBlocks.ore_redstone, 1, 8), new ItemStack(MBlocks.redstone_briny), 0.7F);
		GameRegistry.addSmelting(MBlocks.ore_irradium, new ItemStack(MItems.irradium), 0.7F);
		GameRegistry.addSmelting(MBlocks.ore_torite, new ItemStack(MItems.ingots, 1, 4), 1F);
		GameRegistry.addSmelting(new ItemStack(MItems.chunks, 1, 11), new ItemStack(MItems.ingots, 1, 4), 1F);
		GameRegistry.addSmelting(MBlocks.ore_diamond, new ItemStack(Items.DIAMOND), 1F);
		GameRegistry.addSmelting(MBlocks.ore_emerald, new ItemStack(Items.EMERALD), 1F);
		GameRegistry.addSmelting(MBlocks.ore_titanium, new ItemStack(MItems.ingots, 1, 5), 4F);
		GameRegistry.addSmelting(new ItemStack(MItems.chunks, 1, 12), new ItemStack(MItems.ingots, 1, 5), 4F);
		GameRegistry.addSmelting(MBlocks.ore_blazium, new ItemStack(MItems.gems, 1, 5), 0.8F);
		GameRegistry.addSmelting(MBlocks.ore_soul, new ItemStack(MItems.gem_soul), 3F);
		GameRegistry.addSmelting(MBlocks.ore_dimensium, new ItemStack(MItems.ingots, 1, 8), 3F);
		GameRegistry.addSmelting(new ItemStack(MItems.chunks, 1, 13), new ItemStack(MItems.ingots, 1, 8), 3F);
		GameRegistry.addSmelting(MItems.corn_meal, new ItemStack(MItems.corn_bread), 0.35F);
		GameRegistry.addSmelting(MItems.dough, new ItemStack(Items.BREAD), 0.35F);
		GameRegistry.addSmelting(MItems.fat, new ItemStack(MItems.grease), 0.35F);
		GameRegistry.addSmelting(MItems.grease, new ItemStack(MItems.mob_loot, 1, 1), 0.45F);
		GameRegistry.addSmelting(MItems.squid_tentacle, new ItemStack(MItems.calamari), 0.35F);
		
		for(int i = 0 ; i < EnumStoneTypeMOnly.values().length ; i++)
		{
			GameRegistry.addSmelting(new ItemStack(MBlocks.cobblestone, 1, i), new ItemStack(MBlocks.stone, 1, i), 0.1F);
			GameRegistry.addSmelting(new ItemStack(MBlocks.cracked_stone_bricks, 1, i), new ItemStack(MBlocks.stone_bricks, 1, i), 0.1F);
		}
	}
}
