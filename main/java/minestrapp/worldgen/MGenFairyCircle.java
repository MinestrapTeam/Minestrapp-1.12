package minestrapp.worldgen;

import java.util.Random;

import minestrapp.block.BlockGlowshroom;
import minestrapp.block.crops.BlockBerryBush;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class MGenFairyCircle extends WorldGenerator
{
	private final BlockGlowshroom glowshroom;
	private final int radius;
	
	public MGenFairyCircle(BlockGlowshroom glowshroom, int radius)
	{
		this.glowshroom = glowshroom;
		this.radius = radius;
	}
	
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos pos)
	{
		if (!worldIn.isAirBlock(pos) || !this.glowshroom.canBlockStay(worldIn, pos, glowshroom.getDefaultState()))
			return false;
		int rad = rand.nextInt(this.radius) + 2;
		
		for (int x = -rad ; x < rad + 1 ; x++)
		{
			for (int z = -rad ; z < rad + 1 ; z++)
			{
				if ((x * x) + (z * z) <= (rad * rad) && (x * x) + (z * z) > ((rad-1) * (rad-1)))
				{
					for (int y = -2 ; y < 2 ; y++)
					{
						BlockPos newPos = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
						boolean placed = false;
						
						if (worldIn.getBlockState(newPos).getBlock().isReplaceable(worldIn, newPos) && glowshroom.canBlockStay(worldIn, newPos, glowshroom.getDefaultState()))
						{
							worldIn.setBlockState(newPos, glowshroom.getDefaultState());
							placed = true;
						}
						if(placed)
							break;
					}
				}
			}
		}
		return true;
	}
}
