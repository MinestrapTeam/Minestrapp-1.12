package minestrapp.worldgen;

import java.util.Random;

import com.google.common.base.Predicate;

import minestrapp.MBlocks;
import minestrapp.block.EnumStoneType;
import minestrapp.block.util.BlockStoneBase;
import minestrapp.block.util.BlockStoneBaseMOnly;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class MOreGen implements IWorldGenerator
{
	private WorldGenerator copper;
	private WorldGenerator tin;
	
	public MOreGen()
	{
		copper = new MGenMinable(MBlocks.ore_copper.getDefaultState().withProperty(BlockStoneBase.VARIANT, EnumStoneType.STONE), 10);
		tin = new MGenMinable(MBlocks.ore_tin.getDefaultState().withProperty(BlockStoneBase.VARIANT, EnumStoneType.STONE), 10);
	}
	
	private void runGenerator(WorldGenerator generator, World world, Random rand, int chunkX, int chunkZ, int spawnChance, int minHeight, int maxHeight, boolean biomeSpecific)
	{
		if(minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
		{
			throw new IllegalArgumentException("The Ore Height Params are Fucked, God I'm Bad at Math.");
		}
		int heightDiff = maxHeight - minHeight + 1;
		if(biomeSpecific = true && generator instanceof MGenMinable)
		{
			Chunk chunk = world.getChunkFromChunkCoords(chunkX, chunkZ);
			BiomeProvider chunkManager = world.getBiomeProvider();
			IBlockState state = ((MGenMinable) generator).getOreState();
			int count = ((MGenMinable)generator).getBlockCount();
			Predicate<IBlockState> predicate = ((MGenMinable)generator).getPredicate();
			for(int i = 0 ; i < spawnChance ; i++)
			{
				int xRand = rand.nextInt(16);
				int zRand = rand.nextInt(16);
				BlockPos pos = new BlockPos(xRand, 0, zRand);
				int x = chunkX * 16 + xRand;
				int y = minHeight + rand.nextInt(heightDiff);
				int z = chunkZ * 16 + zRand;
				Biome biome = chunkManager.getBiome(pos, Biomes.PLAINS);
				int k = chunk.getBiomeArray()[zRand << 4 | xRand] & 255;
				
				if (k == 255)
		        {
		            k = Biome.getIdForBiome(biome);
		            chunk.getBiomeArray()[zRand << 4 | xRand] = (byte)(k & 255);
		        }

		        biome = Biome.getBiome(k);
		        
		        if(y < MStoneGen.getDeepstoneDepthForBiome(biome, rand))
		        {
		        	generator = new MGenMinable(state.withProperty(BlockStoneBase.VARIANT, EnumStoneType.byMetadata(MStoneGen.getDeepStoneForBiome(biome).getMetadata())), count, predicate);
		        }
		        else
		        {
		        	if(MStoneGen.getStoneForBiome(biome) != null)
		        	{
		        		generator = new MGenMinable(state.withProperty(BlockStoneBase.VARIANT, EnumStoneType.byMetadata(MStoneGen.getStoneForBiome(biome).getMetadata())), count, predicate);
		        	}
		        	else
		        	{
		        		generator = new MGenMinable(state.withProperty(BlockStoneBase.VARIANT, EnumStoneType.STONE), count, predicate);
		        	}
		        }
		        
				generator.generate(world, rand, new BlockPos(x, y, z));
			}
		}
		else
		{
			for(int i = 0 ; i < spawnChance ; i++)
			{
				int x = chunkX * 16 + rand.nextInt(16);
				int y = minHeight + rand.nextInt(heightDiff);
				int z = chunkZ * 16 + rand.nextInt(16);
				generator.generate(world, rand, new BlockPos(x, y, z));
			}
		}
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
		if(world.provider.getDimension() == 0)
		{
			this.runGenerator(tin, world, random, chunkX, chunkZ, 16, 30, 110, true);
			this.runGenerator(copper, world, random, chunkX, chunkZ, 16, 30, 110, true);
			MStoneGen.generate(world, chunkX, chunkZ, random);
		}
	}
}
