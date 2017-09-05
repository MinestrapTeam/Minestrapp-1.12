package minestrapp.worldgen;

import java.util.Random;

import minestrapp.block.crops.BlockBerryBush;
import net.minecraft.block.Block;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class MGenBushes extends WorldGenerator
{
	private final BlockBerryBush bush;
	private final int radius;
	
	public MGenBushes(BlockBerryBush bush, int radius)
	{
		this.bush = bush;
		this.radius = radius;
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos pos)
	{
		if (!this.bush.canBlockStay(worldIn, pos, bush.getDefaultState()))
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
}
