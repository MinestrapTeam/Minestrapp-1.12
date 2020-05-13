package minestrapp.worldgen;

import java.util.Random;

import minestrapp.MBlocks;
import minestrapp.block.BlockCharwoodLimb;
import minestrapp.block.BlockMLog;
import minestrapp.block.BlockMPlanks;
import minestrapp.block.BlockOreDeposit;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockLog.EnumAxis;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class MGenHellTree extends WorldGenerator
{
	private EnumFacing facing;
	
	public MGenHellTree(EnumFacing facing)
	{
		this.facing = facing;
	}
	
	public boolean generate(World worldIn, Random rand, BlockPos position)
    {
		int acceptableCount = 0;
		
		for(int i = -4 ; i < 5 ; i++)
		{
			for(int j = -1 ; j < 15 ; j++)
			{
				for(int k = -2 ; k < 3 ; k++)
				{
					BlockPos checkPos = position.offset(this.facing, j).offset(this.facing.rotateY(), i).offset(EnumFacing.UP, k);
					
					if(canGenerateTree(worldIn, checkPos) >= 4)
					{
						acceptableCount++;
						break;
					}
				}
			}
			
			if(acceptableCount > 15)
				break;
		}
		
		if(acceptableCount > 15)
		{
			for(int i = 0 ; i < 20 ; i++)
			{
				BlockPos treePos = position.offset(this.facing, rand.nextInt(3) - 2).offset(this.facing.rotateY(), rand.nextInt(9) - 4);
				
				if(canGenerateTree(worldIn, treePos) >= 4)
					this.generateTree(worldIn, treePos, rand, canGenerateTree(worldIn, treePos));
			}
		}
		
		return true;
    }
	
	public int canGenerateTree(World world, BlockPos pos)
	{
		if(world.getBlockState(pos) == Blocks.AIR.getDefaultState() && world.isBlockNormalCube(pos.down(), false) && world.getBlockState(pos.down()) instanceof BlockLiquid == false)
		{
			for(int i = 0 ; i < 10 ; i++)
			{
				BlockPos checkPos = pos.offset(EnumFacing.UP, i);
				
				if(world.getBlockState(checkPos.offset(this.facing.rotateY())) != Blocks.AIR.getDefaultState() || world.getBlockState(checkPos.offset(this.facing.rotateYCCW())) != Blocks.AIR.getDefaultState() || world.getBlockState(checkPos.offset(this.facing)) != Blocks.AIR.getDefaultState() || world.getBlockState(checkPos) != Blocks.AIR.getDefaultState())
					return i;
			}
			
			return 10;
		}
		
		return 0;
	}
	
	public void generateTree(World world, BlockPos pos, Random rand, int maxHeight)
	{
		int height = rand.nextInt(maxHeight - 3) + 3;
		
		for(int i = 0 ; i < height ; i++)
		{
			BlockPos logPos = pos.offset(EnumFacing.UP, i);
			IBlockState log = MBlocks.log.getDefaultState().withProperty(BlockMLog.LOG_AXIS, EnumAxis.Y).withProperty(BlockMLog.VARIANT, BlockMPlanks.EnumType.CHARWOOD);
			
			if(i == height - 1)
				log = MBlocks.log.getDefaultState().withProperty(BlockMLog.LOG_AXIS, EnumAxis.NONE).withProperty(BlockMLog.VARIANT, BlockMPlanks.EnumType.CHARWOOD);
			
			world.setBlockState(logPos, log);
		}
		
		for(int i = 0 ; i < 2 ; i++)
		{
			EnumFacing side = this.facing.rotateY();
			
			if(i > 0)
				side = this.facing.rotateYCCW();
			
			BlockPos branchPos = pos.offset(side);
			
			for(int j = 0 ; j < height ; j++)
			{
				int prob = 2;
				
				if(j == 0 || j == height - 1)
					prob++;
				if(world.getBlockState(branchPos.offset(EnumFacing.UP, j - 1)).getBlock() == MBlocks.charwood_limb)
					prob++;
				
				if(rand.nextInt(prob) == 0)
					world.setBlockState(branchPos.offset(EnumFacing.UP, j), MBlocks.charwood_limb.getDefaultState().withProperty(BlockCharwoodLimb.FACING, side.getOpposite()));
			}
		}
		
		world.setBlockState(pos.offset(EnumFacing.UP, height - 1).offset(this.facing), MBlocks.soul_eyes.getDefaultState().withProperty(BlockOreDeposit.FACING, this.facing));
	}
}
