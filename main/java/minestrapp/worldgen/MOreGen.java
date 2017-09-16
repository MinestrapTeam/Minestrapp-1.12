package minestrapp.worldgen;

import java.util.Random;

import com.google.common.base.Predicate;

import minestrapp.MBlocks;
import minestrapp.block.EnumStoneType;
import minestrapp.block.crops.BlockBerryBush;
import minestrapp.block.util.BlockStoneBase;
import minestrapp.block.util.BlockStoneBaseMOnly;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.TempCategory;
import net.minecraft.world.biome.BiomeBeach;
import net.minecraft.world.biome.BiomeDesert;
import net.minecraft.world.biome.BiomeForest;
import net.minecraft.world.biome.BiomeHills;
import net.minecraft.world.biome.BiomeJungle;
import net.minecraft.world.biome.BiomeMesa;
import net.minecraft.world.biome.BiomeMushroomIsland;
import net.minecraft.world.biome.BiomeOcean;
import net.minecraft.world.biome.BiomePlains;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeRiver;
import net.minecraft.world.biome.BiomeSavanna;
import net.minecraft.world.biome.BiomeStoneBeach;
import net.minecraft.world.biome.BiomeSwamp;
import net.minecraft.world.biome.BiomeTaiga;
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
	private WorldGenerator meurodite;
	private WorldGenerator irradium;
	private WorldGenerator torite;
	private WorldGenerator titanium;
	private WorldGenerator soul;
	private WorldGenerator dimensium;
	
	public MOreGen()
	{
		copper = new MGenMinable(MBlocks.ore_copper.getDefaultState().withProperty(BlockStoneBase.VARIANT, EnumStoneType.STONE), 10);
		tin = new MGenMinable(MBlocks.ore_tin.getDefaultState().withProperty(BlockStoneBase.VARIANT, EnumStoneType.STONE), 10);
		meurodite = new MGenMinable(MBlocks.ore_meurodite.getDefaultState().withProperty(BlockStoneBase.VARIANT, EnumStoneType.STONE), 5);
		irradium = new MGenMinable(MBlocks.ore_irradium.getDefaultState().withProperty(BlockStoneBase.VARIANT, EnumStoneType.STONE), 2);
		torite = new MGenMinable(MBlocks.ore_torite.getDefaultState().withProperty(BlockStoneBase.VARIANT, EnumStoneType.STONE), 4);
		titanium = new MGenMinable(MBlocks.ore_titanium.getDefaultState().withProperty(BlockStoneBase.VARIANT, EnumStoneType.STONE), 3);
		soul = new MGenMinable(MBlocks.ore_soul.getDefaultState(), 3, new MMinablePredicate(Blocks.SOUL_SAND.getDefaultState()));
		dimensium = new MGenMinable(MBlocks.ore_dimensium.getDefaultState(), 4, new MMinablePredicate(Blocks.END_STONE.getDefaultState()));
	}
	
	private void runGenerator(WorldGenerator generator, World world, Random rand, int chunkX, int chunkZ, int spawnChance, int minHeight, int maxHeight, boolean biomeSpecific)
	{
		if(minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
		{
			throw new IllegalArgumentException("The Ore Height Params are Fucked, God I'm Bad at Math.");
		}
		int heightDiff = maxHeight - minHeight + 1;
		if(biomeSpecific == true && generator instanceof MGenMinable)
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
			this.runGenerator(copper, world, random, chunkX, chunkZ, 16, 30, 110, true);
			this.runGenerator(tin, world, random, chunkX, chunkZ, 16, 30, 110, true);
			this.runGenerator(meurodite, world, random, chunkX, chunkZ, 5, 0, 36, true);
			this.runGenerator(irradium, world, random, chunkX, chunkZ, 4, 0, 32, true);
			this.runGenerator(titanium, world, random, chunkX, chunkZ, 3, 0, 10, true);
			
			Biome biome = world.getBiome(new BlockPos(chunkX * 16, 0, chunkZ * 16));
			if(biome instanceof BiomeJungle || biome instanceof BiomeSwamp || biome == Biome.getBiome(29))
			{
				this.runGenerator(torite, world, random, chunkX, chunkZ, 4, 0, 36, true);
			}
			
			int posX = random.nextInt(16);
			int posY = 128 - random.nextInt(120);
			int posZ = random.nextInt(16);
			
			BlockPos sunstonePos = new BlockPos(chunkX * 16 + posX, posY, chunkZ * 16 + posZ);
			MGenSunstone sunstoneGen = new MGenSunstone(5);
			sunstoneGen.generate(world, random, sunstonePos);
			
			MStoneGen.generate(world, chunkX, chunkZ, random);
		}
		
		else if(world.provider.getDimension() == -1)
		{
			this.runGenerator(soul, world, random, chunkX, chunkZ, 24, 20, 100, false);
		}
		
		else if(world.provider.getDimension() == 1)
		{
			this.runGenerator(dimensium, world, random, chunkX, chunkZ, 8, 20, 128, false);
			
			if((chunkX * chunkX) + (chunkZ * chunkZ) > 3844)
			{
				int posX = random.nextInt(16);
				int posY = 100 - random.nextInt(70);
				int posZ = random.nextInt(16);
				
				BlockPos berryPos = new BlockPos(chunkX * 16 + posX, posY, chunkZ * 16 + posZ);
				MGenVoidberry bushGen = new MGenVoidberry(12);
				bushGen.generate(world, random, berryPos);
				
				posX = random.nextInt(16);
				posY = 100 - random.nextInt(70);
				posZ = random.nextInt(16);
				
				BlockPos sunstonePos = new BlockPos(chunkX * 16 + posX, posY, chunkZ * 16 + posZ);
				MGenSunstone sunstoneGen = new MGenSunstone(8);
				sunstoneGen.generate(world, random, sunstonePos);
			}
		}
	}
}
