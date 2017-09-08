package minestrapp.worldgen;

import java.util.Random;

import minestrapp.MBlocks;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class MGenMoss extends WorldGenerator
{
	public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        for (int i = 0; i < 32; ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(6) - rand.nextInt(6), rand.nextInt(2) - rand.nextInt(2), rand.nextInt(6) - rand.nextInt(6));

            if (worldIn.isAirBlock(blockpos) && MBlocks.moss.canPlaceBlockAt(worldIn, blockpos))
            {
                worldIn.setBlockState(blockpos, MBlocks.moss.getDefaultState(), 2);
            }
        }

        return true;
    }
}
