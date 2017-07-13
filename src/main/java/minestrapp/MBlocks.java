package minestrapp;

import java.util.ArrayList;
import java.util.List;

import minestrapp.block.BlockColdSand;
import minestrapp.block.BlockSoulsteelVessel;
import minestrapp.block.EnumStoneType;
import minestrapp.block.EnumStoneTypeMOnly;
import minestrapp.block.item.ItemBlockMultistate;
import minestrapp.block.util.BlockBase;
import minestrapp.block.util.BlockItemDropBase;
import minestrapp.block.util.BlockStoneBase;
import minestrapp.block.util.BlockStoneBaseMOnly;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
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
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MBlocks
{
	public static List<Block> blockList = new ArrayList<Block>();
	
	//Soil
	public static Block cold_sand;
	
	//Stone
	public static Block stone;
	
	//Ore
	public static Block ore_coal;
	public static Block ore_copper;
	public static Block ore_tin;
	public static Block ore_iron;
	public static Block ore_gold;
	public static Block ore_redstone;
	public static Block ore_lapis;
	public static Block ore_diamond;
	public static Block ore_emerald;
	public static Block ore_soul;
	
	//Resource
	public static Block block_copper;
	public static Block block_tin;
	
	//Utility
	public static Block soulsteel_vessel;
	
	public static void init()
	{
		//Soil
		register(cold_sand = new BlockColdSand("cold_sand", Material.SAND, SoundType.SAND, 0.5F, "shovel", 0).setCreativeTab(MTabs.environment), new ItemBlockMultistate(cold_sand));
		
		//Stone
		register(stone = new BlockStoneBaseMOnly("m_stone", Material.ROCK, SoundType.STONE, 1.5F, "pickaxe", 0).setResistance(10F).setCreativeTab(MTabs.stone), new ItemBlockMultistate(stone));
		
		//Ore
		register(ore_coal = new BlockStoneBaseMOnly("ore_coal", Material.ROCK, SoundType.STONE, 3F, "pickaxe", 0).setDropsItem(new ItemStack(Items.COAL, 1, 0), 0, 0, 2, true).setResistance(5F).setCreativeTab(MTabs.ore), new ItemBlockMultistate(ore_coal));
		register(ore_copper = new BlockStoneBase("ore_copper", Material.ROCK, SoundType.STONE, 3F, "pickaxe", 0).setResistance(5F).setCreativeTab(MTabs.ore), new ItemBlockMultistate(ore_copper));
		register(ore_tin = new BlockStoneBase("ore_tin", Material.ROCK, SoundType.STONE, 3F, "pickaxe", 0).setResistance(5F).setCreativeTab(MTabs.ore), new ItemBlockMultistate(ore_tin));
		register(ore_iron = new BlockStoneBaseMOnly("ore_iron", Material.ROCK, SoundType.STONE, 3F, "pickaxe", 1).setResistance(5F).setCreativeTab(MTabs.ore), new ItemBlockMultistate(ore_iron));
		register(ore_gold = new BlockStoneBaseMOnly("ore_gold", Material.ROCK, SoundType.STONE, 3F, "pickaxe", 2).setResistance(5F).setCreativeTab(MTabs.ore), new ItemBlockMultistate(ore_gold));
		register(ore_lapis = new BlockStoneBaseMOnly("ore_lapis", Material.ROCK, SoundType.STONE, 3F, "pickaxe", 1).setDropsItem(new ItemStack(Items.DYE, 4, EnumDyeColor.BLUE.getDyeDamage()), 4, 2, 5, true).setResistance(5F).setCreativeTab(MTabs.ore), new ItemBlockMultistate(ore_lapis));
		register(ore_diamond = new BlockStoneBaseMOnly("ore_diamond", Material.ROCK, SoundType.STONE, 3F, "pickaxe", 2).setDropsItem(new ItemStack(Items.DIAMOND, 1, 0), 0, 3, 7, true).setResistance(5F).setCreativeTab(MTabs.ore), new ItemBlockMultistate(ore_diamond));
		register(ore_emerald = new BlockStoneBaseMOnly("ore_emerald", Material.ROCK, SoundType.STONE, 3F, "pickaxe", 2).setDropsItem(new ItemStack(Items.EMERALD, 1, 0), 0, 3, 7, true).setResistance(5F).setCreativeTab(MTabs.ore), new ItemBlockMultistate(ore_emerald));
		register(ore_soul = new BlockItemDropBase("ore_soul", Material.SAND, MapColor.BROWN, SoundType.SAND, 0.8F, "shovel", 3, new ItemStack(MItems.gem_soul, 1), 0, 5, 9, true).setCreativeTab(MTabs.ore));
		
		//Resource
		register(block_copper = new BlockBase("block_copper", Material.IRON, MapColor.ADOBE, SoundType.METAL, 4F, "pickaxe", 0).setResistance(10F).setCreativeTab(MTabs.resource));
		register(block_tin = new BlockBase("block_tin", Material.IRON, MapColor.ADOBE, SoundType.METAL, 4F, "pickaxe", 0).setResistance(10F).setCreativeTab(MTabs.resource));

		//Utility
		register(soulsteel_vessel = new BlockSoulsteelVessel().setHardness(3.0F).setCreativeTab(MTabs.utility));
	}
	
	public static void register(Block block)
	{
		GameRegistry.register(block);
		GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
		blockList.add(block);
	}
	
	public static void register(Block block, ItemBlock itemBlock)
	{
		GameRegistry.register(block);
		GameRegistry.register(itemBlock.setRegistryName(block.getRegistryName()));
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
		for(int i = 0 ; i < EnumStoneTypeMOnly.values().length ; i++)
		{
			initModel(ore_coal, i, "ore_coal_" + EnumStoneTypeMOnly.values()[i].getName());
			initModel(ore_iron, i, "ore_iron_" + EnumStoneTypeMOnly.values()[i].getName());
			initModel(ore_gold, i, "ore_gold_" + EnumStoneTypeMOnly.values()[i].getName());
			initModel(ore_lapis, i, "ore_lapis_" + EnumStoneTypeMOnly.values()[i].getName());
			initModel(ore_diamond, i, "ore_diamond_" + EnumStoneTypeMOnly.values()[i].getName());
			initModel(ore_emerald, i, "ore_emerald_" + EnumStoneTypeMOnly.values()[i].getName());
			initModel(stone, i, "m_stone_" + EnumStoneTypeMOnly.values()[i].getName());
		}
		for(int i = 0 ; i < EnumStoneType.values().length ; i++)
		{
			initModel(ore_copper, i, "ore_copper_" + EnumStoneType.values()[i].getName());
			initModel(ore_tin, i, "ore_tin_" + EnumStoneType.values()[i].getName());
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
