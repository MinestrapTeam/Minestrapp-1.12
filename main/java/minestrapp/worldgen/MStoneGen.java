package minestrapp.worldgen;

import java.util.Random;

import minestrapp.MBlocks;
import minestrapp.block.BlockColdSand;
import minestrapp.block.BlockMDirt;
import minestrapp.block.EnumStoneTypeMOnly;
import minestrapp.block.util.BlockStoneBaseMOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOre;
import net.minecraft.block.BlockRedstoneOre;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.biome.BiomeSavanna;
import net.minecraft.world.biome.BiomeSwamp;
import net.minecraft.world.biome.Biome.TempCategory;
import net.minecraft.world.biome.BiomeJungle;
import net.minecraft.world.biome.BiomeMushroomIsland;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

public class MStoneGen
{
	public static void generate(World world, int chunkX, int chunkZ, Random random)
	{
		BlockPos pos = new BlockPos(chunkX * 16, 0, chunkZ * 16);
		Chunk chunk = world.getChunkFromBlockCoords(pos);
		BiomeProvider chunkManager = world.getBiomeProvider();
		IBlockState stone = MBlocks.stone.getDefaultState();
		EnumStoneTypeMOnly sType = null;
		EnumStoneTypeMOnly dType = EnumStoneTypeMOnly.DEEPSTONE;
		int deepStoneDepth = 35;

		for (int x = 0; x < 16; x++)
		{
			for (int z = 0; z < 16; z++)
			{
				BlockPos subpos = new BlockPos(x, 0, z);
				Biome biome = chunkManager.getBiome(subpos, Biomes.PLAINS);
				
				int i = subpos.getX();
		        int j = subpos.getZ();
		        int k = chunk.getBiomeArray()[j << 4 | i] & 255;

		        if (k == 255)
		        {
		            k = Biome.getIdForBiome(biome);
		            chunk.getBiomeArray()[j << 4 | i] = (byte)(k & 255);
		        }

		        biome = Biome.getBiome(k);

				sType = MStoneGen.getStoneForBiome(biome);
				dType = MStoneGen.getDeepStoneForBiome(biome);
				deepStoneDepth = MStoneGen.getDeepstoneDepthForBiome(biome, random);

				for (int y = 256; y >= 0; y--)
				{
					BlockPos subpos2 = new BlockPos((chunkX * 16 + x), y, (chunkZ * 16 + z));
					IBlockState state = world.getBlockState(subpos2);
					Block block = state.getBlock();

					if (y == 0)
						chunk.setBlockState(subpos2, MBlocks.invincium.getDefaultState());
					else if (state.isFullBlock() == true)
					{
						if (state == Blocks.STONE.getDefaultState())
						{
							if (y < deepStoneDepth)
							{
								chunk.setBlockState(subpos2, stone.withProperty(BlockStoneBaseMOnly.VARIANT, dType));
							}
							else if (sType != null)
							{
								chunk.setBlockState(subpos2, stone.withProperty(BlockStoneBaseMOnly.VARIANT, sType));
							}
						}
						else if(state.getBlock() instanceof BlockStone && biome.getDefaultTemperature() >= 0.4F)
						{
							if((biome.getTempCategory() == TempCategory.OCEAN || biome instanceof BiomeMushroomIsland) && state == Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE))
							{
								chunk.setBlockState(subpos2, MBlocks.decor_stone.getDefaultState());
							}
							else if(biome.getDefaultTemperature() < 1F && state == Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE))
							{
								chunk.setBlockState(subpos2, MBlocks.decor_stone.getDefaultState());
							}
							else if(biome.getDefaultTemperature() >= 1F && state == Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE))
							{
								chunk.setBlockState(subpos2, MBlocks.decor_stone.getDefaultState());
							}
						}
					}
				}
			}
		}
	}
	
	public static EnumStoneTypeMOnly getStoneForBiome(Biome biome)
	{
		if (biome.getTempCategory() == TempCategory.OCEAN || biome instanceof BiomeMushroomIsland)
		{
			return EnumStoneTypeMOnly.OCEANSTONE;
		}
		else if (biome.getDefaultTemperature() < 0.2F)
		{
			return EnumStoneTypeMOnly.ICESTONE;
		}
		else if (biome.getDefaultTemperature() < 0.4F)
		{
			return EnumStoneTypeMOnly.COLDSTONE;
		}
		else if (biome.getDefaultTemperature() >= 1.0F)
		{
			return EnumStoneTypeMOnly.RED_ROCK;
		}
		else
		{
			return null;
		}
	}
	
	public static EnumStoneTypeMOnly getDeepStoneForBiome(Biome biome)
	{
		if (biome.getTempCategory() == TempCategory.OCEAN || biome instanceof BiomeMushroomIsland)
		{
			return EnumStoneTypeMOnly.DEEP_OCEANSTONE;
		}
		else if (biome.getDefaultTemperature() < 0.2F)
		{
			return EnumStoneTypeMOnly.GLACIERROCK;
		}
		else if (biome.getDefaultTemperature() < 0.4F)
		{
			return EnumStoneTypeMOnly.DEEP_COLDSTONE;
		}
		else if (biome.getDefaultTemperature() >= 1.0F)
		{
			return EnumStoneTypeMOnly.DEEP_RED_ROCK;
		}
		else
		{
			return EnumStoneTypeMOnly.DEEPSTONE;
		}
	}
	
	public static int getDeepstoneDepthForBiome(Biome biome, Random random)
	{
		if (biome.getTempCategory() == TempCategory.OCEAN || biome instanceof BiomeMushroomIsland)
		{
			return random.nextInt(5) + 20;
		}
		else if (biome.getDefaultTemperature() < 0.2F)
		{
			return random.nextInt(5) + 30;
		}
		else if (biome.getDefaultTemperature() < 0.4F)
		{
			return random.nextInt(5) + 40;
		}
		else if (biome.getDefaultTemperature() >= 1.0F)
		{
			return random.nextInt(5) + 25;
		}
		else
		{
			return random.nextInt(5) + 35;
		}
	}
}
