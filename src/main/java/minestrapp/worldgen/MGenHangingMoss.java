package minestrapp.worldgen;

import java.util.Random;

import minestrapp.MBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class MGenHangingMoss extends WorldGenerator
{
	private int tries;
	private Block block;
	
	public MGenHangingMoss(int tries, Block block)
	{
		this.tries = tries;
		this.block = block;
	}
	
	public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        for (int i = 0; i < this.tries; ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(this.tries / 3) - rand.nextInt(this.tries / 3), rand.nextInt(3) - rand.nextInt(3), rand.nextInt(this.tries / 3) - rand.nextInt(this.tries / 3));

            if (worldIn.getBlockState(blockpos) == Blocks.AIR.getDefaultState() && this.block.canPlaceBlockAt(worldIn, blockpos))
            {
                worldIn.setBlockState(blockpos, this.block.getDefaultState(), 2);
            }
        }

        return true;
    }
}