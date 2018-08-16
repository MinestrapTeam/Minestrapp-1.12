package minestrapp.worldgen;

import java.util.Random;

import minestrapp.MBlocks;
import minestrapp.block.crops.BlockBerryBush;
import net.minecraft.block.Block;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class MGenBushes extends WorldGenerator
{
	private final BlockBerryBush bush;
	private final int radius;
	
	public MGenBushes(Biome biome, int radius)
	{
		this.bush = this.determinTypeOfBush(biome);
		this.radius = radius;
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos pos)
	{
		if (this.bush == null || !this.bush.canBlockStay(worldIn, pos, bush.getDefaultState()))
			return false;
		else
		{
			int rad = rand.nextInt(this.radius - 2) + 2;
			
			for (int x = -rad ; x < rad ; x++)
			{
				for (int z = -rad ; z < rad ; z++)
				{
					if ((x * x) + (z * z) <= (rad * rad))
					{
						for (int y = -2 ; y < 2 ; y++)
						{
							BlockPos newPos = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
							
							if (bush.canBlockStay(worldIn, newPos, bush.getDefaultState()))
							{
								int maxHeight = 2;
								
								if (x + z > 0)
									maxHeight = 1;
								if (x + z > 3)
									maxHeight = 0;
								for (int height = 0 ; height < maxHeight ; height++)
								{
									int chance = rand.nextInt(Math.abs(x) + Math.abs(z) + 1);
									
									if (chance == 0 && worldIn.getBlockState(newPos.offset(EnumFacing.UP, height)).getBlock().isReplaceable(worldIn, newPos.offset(EnumFacing.UP, height)))
										worldIn.setBlockState(newPos.offset(EnumFacing.UP, height), bush.getDefaultState().withProperty(BlockBerryBush.AGE, ((BlockBerryBush) bush).getMaxAge()));
									else
										break;
								}
							}
						}
					}
				}
			}	
			return true;
		}
	}
	
	private BlockBerryBush determinTypeOfBush(Biome biome) {
		if(BiomeDictionary.hasType(biome, Type.FOREST) && !BiomeDictionary.hasType(biome, Type.CONIFEROUS) && !BiomeDictionary.hasType(biome, Type.SPOOKY)) {
			return (BlockBerryBush)MBlocks.blueberry_bush;
		}
		if(BiomeDictionary.hasType(biome, Type.CONIFEROUS) || BiomeDictionary.hasType(biome, Type.HILLS)) {
			return (BlockBerryBush)MBlocks.blackberry_bush;
		}
		if(BiomeDictionary.hasType(biome, Type.SAVANNA) || BiomeDictionary.hasType(biome, Type.MESA)) {
			return (BlockBerryBush)MBlocks.raspberry_bush;
		}
		if(BiomeDictionary.hasType(biome, Type.SWAMP) || BiomeDictionary.hasType(biome, Type.SPOOKY)) {
			return (BlockBerryBush)MBlocks.strawberry_bush;
		}
		if(BiomeDictionary.hasType(biome, Type.OCEAN) || BiomeDictionary.hasType(biome, Type.MUSHROOM)) {
			return (BlockBerryBush)MBlocks.mana_bush;
		}
		return null;
	}
}
