package minestrapp.worldgen;

import java.util.Random;

import minestrapp.MBlocks;
import minestrapp.block.BlockDesertQuartzDeposit;
import minestrapp.block.BlockOreDeposit;
import minestrapp.block.util.BlockStoneBaseMOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class MGenRockCrystal extends WorldGenerator
{
    private final int radius;

    public MGenRockCrystal(int maxRadius)
    {
        this.radius = maxRadius;
    }

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
    	int i = this.radius;

        for (int k = position.getX() - i; k <= position.getX() + i; ++k)
        {
        	for (int l = position.getY() - (i * 2); l <= position.getY() + (i * 2); ++l)
            {
	            for (int m = position.getZ() - i; m <= position.getZ() + i; ++m)
	            {
	                int n = k - position.getX();
	                int o = l - position.getY();
	                int p = m - position.getZ();
	
	                if (n * n + (o / 2) * (o / 2) <= i * i && n * n + p * p <= i * i && (o / 2) * (o / 2) + p * p <= i * i)
	                {
	                	int chance = rand.nextInt(100);
	                	
	                	if (chance < 20)
	                	{
		                    BlockPos blockpos = new BlockPos(k, l, m);

	                        if (worldIn.isAirBlock(blockpos))
	                        {
	                        	for(int q = 0 ; q < EnumFacing.VALUES.length ; q++)
	                        	{

	                        		if(canGenerateBlock(worldIn, blockpos, EnumFacing.getFront(q)))
	                        		{
	                        			worldIn.setBlockState(blockpos, MBlocks.rock_crystal_deposit.getDefaultState().withProperty(BlockOreDeposit.FACING, EnumFacing.getFront(q)), 2);
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
    
    public static boolean canGenerateBlock(World worldIn, BlockPos pos, EnumFacing direction)
    {
    	if(worldIn.getBlockState(pos.offset(direction.getOpposite())).getBlock() instanceof BlockDesertQuartzDeposit)
    		return BlockOreDeposit.canPlaceBlock(worldIn, pos, direction);
    	else
    		return false;
    }
}
