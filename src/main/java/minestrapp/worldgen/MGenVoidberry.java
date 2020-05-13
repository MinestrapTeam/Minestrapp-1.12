package minestrapp.worldgen;

import java.util.Random;

import minestrapp.MBlocks;
import minestrapp.block.crops.BlockVoidberryBush;
import net.minecraft.block.Block;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class MGenVoidberry extends WorldGenerator
{
	private final int radius;
	
	public MGenVoidberry(int radius)
	{
		this.radius = radius;
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos pos)
	{
		BlockVoidberryBush bush = (BlockVoidberryBush) MBlocks.voidberry_bush;
		if (!bush.canBlockStay(worldIn, pos, bush.getDefaultState()))
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
								int maxHeight = 3;
								
								if (x + z > 1)
									maxHeight = 2;
								if (x + z > 5)
									maxHeight = 1;
								for (int height = 0 ; height < maxHeight ; height++)
								{
									int chance = rand.nextInt(Math.abs(x) + Math.abs(z) + 1);
									
									if (chance == 0 && worldIn.getBlockState(newPos.offset(EnumFacing.UP, height)).getBlock().isReplaceable(worldIn, newPos.offset(EnumFacing.UP, height)))
									{
										worldIn.setBlockState(newPos.offset(EnumFacing.UP, height), bush.getDefaultState().withProperty(BlockVoidberryBush.AGE, ((BlockVoidberryBush) bush).getMaxAge()).withProperty(BlockVoidberryBush.STEM, false), 2);
										if(height > 0)
											worldIn.setBlockState(newPos.offset(EnumFacing.UP, height - 1), bush.getDefaultState().withProperty(BlockVoidberryBush.AGE, ((BlockVoidberryBush) bush).getMaxAge()).withProperty(BlockVoidberryBush.STEM, true), 2);
									}
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
