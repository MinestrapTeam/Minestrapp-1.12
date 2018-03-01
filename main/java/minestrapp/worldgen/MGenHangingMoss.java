package minestrapp.worldgen;

import java.util.Random;

import minestrapp.MBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class MGenHangingMoss extends WorldGenerator
{
	private final int tries;
	
	public MGenHangingMoss(int tries)
	{
		this.tries = tries;
	}
	
	public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        for (int i = 0; i < this.tries; ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(this.tries / 3) - rand.nextInt(this.tries / 3), rand.nextInt(3) - rand.nextInt(3), rand.nextInt(this.tries / 3) - rand.nextInt(this.tries / 3));

            if (worldIn.getBlockState(blockpos) == Blocks.AIR.getDefaultState() && MBlocks.hanging_glow_moss.canPlaceBlockAt(worldIn, blockpos))
            {
                worldIn.setBlockState(blockpos, MBlocks.hanging_glow_moss.getDefaultState(), 2);
            }
        }

        return true;
    }
}