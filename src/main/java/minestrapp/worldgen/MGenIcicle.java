package minestrapp.worldgen;

import java.util.Random;

import minestrapp.MBlocks;
import minestrapp.block.BlockGlaciericIceBranch;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class MGenIcicle extends WorldGenerator
{
	private int boost;
	
	public MGenIcicle(int boost)
	{
		if(boost > 3)
			boost = 3;
		this.boost = boost;
	}
	
	public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        for (int i = 0; i < 3 + (boost * 4); ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(6) - rand.nextInt(6), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(6) - rand.nextInt(6));
            int height = 0;
            
            for(int j = 0 ; j < 4 + boost ; j++)
            {
	            if (worldIn.isAirBlock(blockpos.offset(EnumFacing.UP, j)))
	            	height++;
	            else if(worldIn.getBlockState(blockpos.offset(EnumFacing.UP, j)).isFullBlock())
	            	break;
	            else
	            {
	            	height = 0;
	            	break;
	            }
            }
            
            if(height > 0 && height <= 7)
            {
            	for(int k = height ; k > 0 ; k--)
            	{
            		if(BlockGlaciericIceBranch.getBranchBlock(k - 1).canPlaceBlockOnSide(worldIn, blockpos.offset(EnumFacing.UP, k), EnumFacing.DOWN))
            				worldIn.setBlockState(blockpos.offset(EnumFacing.UP, k - 1), BlockGlaciericIceBranch.getBranchBlock(k - 1).getDefaultState().withProperty(BlockGlaciericIceBranch.FACING, EnumFacing.DOWN));
            		else
            			break;
            	}
            }
        }

        return true;
    }
}
