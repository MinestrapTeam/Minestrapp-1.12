package minestrapp.worldgen;

import java.util.Random;

import minestrapp.MBlocks;
import minestrapp.block.BlockSunstoneDeposit;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class MGenSunstone extends WorldGenerator
{
    private final int radius;

    public MGenSunstone(int maxRadius)
    {
        this.radius = maxRadius;
    }

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
    	int i = rand.nextInt(this.radius - 3) + 3;

        for (int k = position.getX() - i; k <= position.getX() + i; ++k)
        {
        	for (int l = position.getY() - i; l <= position.getY() + i; ++l)
            {
	            for (int m = position.getZ() - i; m <= position.getZ() + i; ++m)
	            {
	                int n = k - position.getX();
	                int o = l - position.getY();
	                int p = m - position.getZ();
	
	                if (n * n + o * o <= i * i && n * n + p * p <= i * i && o * o + p * p <= i * i)
	                {
	                	int chance = rand.nextInt(100);
	                	
	                	if (chance < 40)
	                	{
		                    BlockPos blockpos = new BlockPos(k, l, m);

	                        if (worldIn.isAirBlock(blockpos))
	                        {
	                        	for(int q = 0 ; q < EnumFacing.VALUES.length ; q++)
	                        	{

	                        		if(BlockSunstoneDeposit.canGenerateBlock(worldIn, blockpos, EnumFacing.getFront(q)))
	                        		{
	                        			worldIn.setBlockState(blockpos, MBlocks.sunstone_deposit.getDefaultState().withProperty(BlockSunstoneDeposit.FACING, EnumFacing.getFront(q)), 2);
	                        			break;
	                        		}
	                        	}
	                        }
	                	}
	                }
	            }
            }
        }

        return true;
    }
}
