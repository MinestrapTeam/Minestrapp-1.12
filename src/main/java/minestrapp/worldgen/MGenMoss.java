package minestrapp.worldgen;

import java.util.Random;

import minestrapp.MBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class MGenMoss extends WorldGenerator
{
	private int tries;
	private Block block;
	
	public MGenMoss(int tries, Block block)
	{
		this.tries = tries;
		this.block = block;
	}
	
	public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        for (int i = 0; i < tries; ++i)
        {
        	BlockPos blockpos = position.add(rand.nextInt(this.tries / 5) - rand.nextInt(this.tries / 5), rand.nextInt(3) - rand.nextInt(3), rand.nextInt(this.tries / 5) - rand.nextInt(this.tries / 5));

            if (worldIn.isAirBlock(blockpos) && this.block.canPlaceBlockAt(worldIn, blockpos))
            {
                worldIn.setBlockState(blockpos, this.block.getDefaultState(), 2);
            }
        }

        return true;
    }
}
