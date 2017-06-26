package minestrapp;

import java.util.ArrayList;
import java.util.List;

import minestrapp.block.BlockColdSand;
import minestrapp.block.BlockSoulsteelVessel;
import minestrapp.block.EnumStoneType;
import minestrapp.block.item.ItemBlockMultistate;
import minestrapp.block.util.BlockBase;
import minestrapp.block.util.BlockItemDropBase;
import minestrapp.block.util.BlockStoneBase;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
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
	
	//Ore
	public static Block ore_copper;
	public static Block ore_tin;
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
		
		//Ore
		register(ore_copper = new BlockStoneBase("ore_copper", Material.ROCK, SoundType.STONE, 3F, "pickaxe", 0).setResistance(10F).setCreativeTab(MTabs.ore), new ItemBlockMultistate(ore_copper));
		register(ore_tin = new BlockStoneBase("ore_tin", Material.ROCK, SoundType.STONE, 3F, "pickaxe", 0).setResistance(10F).setCreativeTab(MTabs.ore), new ItemBlockMultistate(ore_tin));
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
