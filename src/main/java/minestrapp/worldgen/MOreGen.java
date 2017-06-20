package minestrapp.worldgen;

import java.util.Random;

import minestrapp.MBlocks;
import minestrapp.block.EnumStoneType;
import minestrapp.block.util.BlockStoneBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class MOreGen implements IWorldGenerator
{
	private WorldGenerator copper;
	
	public MOreGen()
	{
		copper = new WorldGenMinable(MBlocks.ore_copper.getDefaultState().withProperty(BlockStoneBase.VARIANT, EnumStoneType.STONE), 10);
	}
	
	private void runGenerator(WorldGenerator generator, World world, Random rand, int chunkX, int chunkZ, int spawnChance, int minHeight, int maxHeight)
	{
		if(minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
		{
			throw new IllegalArgumentException("The Ore Height Params are Fucked, God I'm Bad at Math.");
		}
		int heightDiff = maxHeight - minHeight + 1;
		for(int i = 0 ; i < spawnChance ; i++)
		{
			int x = chunkX * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightDiff);
			int z = chunkZ * 16 + rand.nextInt(16);
			generator.generate(world, rand, new BlockPos(x, y, z));
		}
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
		if(world.provider.getDimension() == 0)
			this.runGenerator(copper, world, random, chunkX, chunkZ, 16, 30, 110);
	}
}
