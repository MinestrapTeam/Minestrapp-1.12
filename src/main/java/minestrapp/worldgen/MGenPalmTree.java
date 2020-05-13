package minestrapp.worldgen;

import java.util.Random;

import minestrapp.MBlocks;
import minestrapp.block.BlockMLog;
import minestrapp.block.BlockMPlanks;
import minestrapp.block.BlockPalmFronds;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome.TempCategory;
import net.minecraft.world.gen.feature.WorldGenerator;

public class MGenPalmTree extends WorldGenerator
{
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position)
	{
		int dir = rand.nextInt(4);
		EnumFacing facing = EnumFacing.NORTH;
		
		if(dir == 1)
			facing = EnumFacing.SOUTH;
		else if(dir == 2)
			facing = EnumFacing.WEST;
		else if(dir == 3)
			facing = EnumFacing.EAST;
		
		int width = rand.nextInt(2) + 3;
		int height = 2;
		
		for(int i = 0; i < width + 1 ; i++)
		{
			height += i;
		}
		
		BlockPos topPos = position.offset(facing, width - 1).offset(EnumFacing.UP, height);
		
		boolean canGrow = true;
		
		for(int x = position.getX() - 1 ; x < position.getX() + 1 ; x++)
		{
			if(!canGrow)
				break;
			
			for(int z = position.getZ() - 1 ; z < position.getZ() + 1 ; z++)
			{
				BlockPos checkPos = new BlockPos(x, position.getY(), z);
				
				if(!worldIn.getBlockState(checkPos).getBlock().isReplaceable(worldIn, checkPos))
				{
					canGrow = false;
					break;
				}
			}
		}
		
		for(int y = 1 ; y < height + 1 ; y++)
		{
			if(!canGrow)
				break;
			
			for(int w = 0 ; w < width ; w++)
			{
				BlockPos checkPos = new BlockPos(position.up(y).offset(facing, w));
				
				if(!worldIn.getBlockState(checkPos).getBlock().isReplaceable(worldIn, checkPos))
				{
					canGrow = false;
					break;
				}
			}
			
			/*if(facing == EnumFacing.NORTH)
			{
				for(int z = position.getZ() ; z > position.getZ() - width ; z--)
				{
					BlockPos checkPos = new BlockPos(position.getX(), y, z);
					
					if(!worldIn.getBlockState(checkPos).getBlock().isReplaceable(worldIn, checkPos))
					{
						canGrow = false;
						break;
					}
				}
			}
			else if(facing == EnumFacing.SOUTH)
			{
				for(int z = position.getZ() ; z < position.getZ() + width ; z++)
				{
					BlockPos checkPos = new BlockPos(position.getX(), y, z);
					
					if(!worldIn.getBlockState(checkPos).getBlock().isReplaceable(worldIn, checkPos))
					{
						canGrow = false;
						break;
					}
				}
			}
			else if(facing == EnumFacing.WEST)
			{
				for(int x = position.getX() ; x > position.getX() - width ; x--)
				{
					BlockPos checkPos = new BlockPos(x, y, position.getZ());
					
					if(!worldIn.getBlockState(checkPos).getBlock().isReplaceable(worldIn, checkPos))
					{
						canGrow = false;
						break;
					}
				}
			}
			else
			{
				for(int x = position.getX() ; x < position.getX() + width ; x++)
				{
					BlockPos checkPos = new BlockPos(x, y, position.getZ());
					
					if(!worldIn.getBlockState(checkPos).getBlock().isReplaceable(worldIn, checkPos))
					{
						canGrow = false;
						break;
					}
				}
			}*/
		}
		
		for(int x = topPos.getX() - 3 ; x < topPos.getX() + 3 ; x++)
		{
			if(!canGrow)
				break;
			
			for(int z = topPos.getZ() - 3 ; z < topPos.getZ() + 3 ; z++)
			{
				if(!canGrow)
					break;
				
				for(int y = topPos.getY() - 1 ; y < topPos.getY() + 1 ; y++)
				{
					BlockPos checkPos = new BlockPos(x, y, z);
					
					if(!worldIn.getBlockState(checkPos).getBlock().isReplaceable(worldIn, checkPos))
					{
						canGrow = false;
						break;
					}
				}
			}
		}
		
		if(!canGrow)
			return false;
		else
		{
			for(int yOffset = 0 ; yOffset < height + 1; yOffset++)
			{
				if(yOffset == 0)
					worldIn.setBlockState(topPos, MBlocks.palm_crown.getDefaultState());
				else
				{
					int horizOffset = 0;
					
					if(yOffset >= 2)
						horizOffset ++;
					if(yOffset >= 5)
						horizOffset ++;
					if(yOffset >= 9)
						horizOffset ++;
					if(yOffset >= 14)
						horizOffset ++;
					if(yOffset >= 20)
						horizOffset ++;
					
					BlockPos logPos = new BlockPos(topPos).offset(facing.getOpposite(), horizOffset).offset(EnumFacing.DOWN, yOffset);
					
					if(yOffset + 1 != height && (yOffset == 1 || yOffset == 2 || yOffset == 4 || yOffset == 5 || yOffset == 8 || yOffset == 9 || yOffset == 13 || yOffset == 14 || yOffset == 19 || yOffset == 20))
						worldIn.setBlockState(logPos, MBlocks.log.getDefaultState().withProperty(BlockMLog.VARIANT, BlockMPlanks.EnumType.PALM).withProperty(BlockMLog.LOG_AXIS, BlockLog.EnumAxis.NONE));
					else
						worldIn.setBlockState(logPos, MBlocks.log.getDefaultState().withProperty(BlockMLog.VARIANT, BlockMPlanks.EnumType.PALM));
				}
			}
			
			worldIn.setBlockState(position.north(), MBlocks.log.getDefaultState().withProperty(BlockMLog.VARIANT, BlockMPlanks.EnumType.PALM).withProperty(BlockMLog.LOG_AXIS, BlockLog.EnumAxis.Z));
			worldIn.setBlockState(position.south(), MBlocks.log.getDefaultState().withProperty(BlockMLog.VARIANT, BlockMPlanks.EnumType.PALM).withProperty(BlockMLog.LOG_AXIS, BlockLog.EnumAxis.Z));
			worldIn.setBlockState(position.west(), MBlocks.log.getDefaultState().withProperty(BlockMLog.VARIANT, BlockMPlanks.EnumType.PALM).withProperty(BlockMLog.LOG_AXIS, BlockLog.EnumAxis.X));
			worldIn.setBlockState(position.east(), MBlocks.log.getDefaultState().withProperty(BlockMLog.VARIANT, BlockMPlanks.EnumType.PALM).withProperty(BlockMLog.LOG_AXIS, BlockLog.EnumAxis.X));
			worldIn.setBlockState(position, MBlocks.log.getDefaultState().withProperty(BlockMLog.VARIANT, BlockMPlanks.EnumType.PALM));
			
			int deadChance = 3;
			if(worldIn.getBiome(topPos).getTempCategory() == TempCategory.WARM)
				deadChance = 9;
			else if(worldIn.getBiome(topPos).getTempCategory() == TempCategory.COLD)
				deadChance = 25;
			
			placeFronds(worldIn, topPos.north().east(), false, true, EnumFacing.WEST, deadChance);
			placeFronds(worldIn, topPos.north().west(), false, true, EnumFacing.SOUTH, deadChance);
			placeFronds(worldIn, topPos.south().west(), false, true, EnumFacing.EAST, deadChance);
			placeFronds(worldIn, topPos.south().east(), false, true, EnumFacing.NORTH, deadChance);
			
			placeFronds(worldIn, topPos.north().east(2), false, true, EnumFacing.NORTH, deadChance);
			placeFronds(worldIn, topPos.north(2).east(), false, true, EnumFacing.SOUTH, deadChance);
			placeFronds(worldIn, topPos.north(2).west(), false, true, EnumFacing.WEST, deadChance);
			placeFronds(worldIn, topPos.north().west(2), false, true, EnumFacing.EAST, deadChance);
			placeFronds(worldIn, topPos.south().west(2), false, true, EnumFacing.SOUTH, deadChance);
			placeFronds(worldIn, topPos.south(2).west(), false, true, EnumFacing.NORTH, deadChance);
			placeFronds(worldIn, topPos.south(2).east(), false, true, EnumFacing.EAST, deadChance);
			placeFronds(worldIn, topPos.south().east(2), false, true, EnumFacing.WEST, deadChance);
			
			placeFronds(worldIn, topPos.north().east(3), false, true, EnumFacing.WEST, deadChance);
			placeFronds(worldIn, topPos.north(3).east(), false, true, EnumFacing.WEST, deadChance);
			placeFronds(worldIn, topPos.north(3).west(), false, true, EnumFacing.SOUTH, deadChance);
			placeFronds(worldIn, topPos.north().west(3), false, true, EnumFacing.SOUTH, deadChance);
			placeFronds(worldIn, topPos.south().west(3), false, true, EnumFacing.EAST, deadChance);
			placeFronds(worldIn, topPos.south(3).west(), false, true, EnumFacing.EAST, deadChance);
			placeFronds(worldIn, topPos.south(3).east(), false, true, EnumFacing.NORTH, deadChance);
			placeFronds(worldIn, topPos.south().east(3), false, true, EnumFacing.NORTH, deadChance);
			
			placeFronds(worldIn, topPos.up().north().east(), true, false, EnumFacing.WEST, deadChance);
			placeFronds(worldIn, topPos.up().north().west(), true, false, EnumFacing.SOUTH, deadChance);
			placeFronds(worldIn, topPos.up().south().west(), true, false, EnumFacing.EAST, deadChance);
			placeFronds(worldIn, topPos.up().south().east(), true, false, EnumFacing.NORTH, deadChance);
			
			placeFronds(worldIn, topPos.up().north().east(2), true, false, EnumFacing.NORTH, deadChance);
			placeFronds(worldIn, topPos.up().north(2).east(), true, false, EnumFacing.SOUTH, deadChance);
			placeFronds(worldIn, topPos.up().north(2).west(), true, false, EnumFacing.WEST, deadChance);
			placeFronds(worldIn, topPos.up().north().west(2), true, false, EnumFacing.EAST, deadChance);
			placeFronds(worldIn, topPos.up().south().west(2), true, false, EnumFacing.SOUTH, deadChance);
			placeFronds(worldIn, topPos.up().south(2).west(), true, false, EnumFacing.NORTH, deadChance);
			placeFronds(worldIn, topPos.up().south(2).east(), true, false, EnumFacing.EAST, deadChance);
			placeFronds(worldIn, topPos.up().south().east(2), true, false, EnumFacing.WEST, deadChance);
			
			placeFronds(worldIn, topPos.up().north(2).east(2), true, false, EnumFacing.WEST, deadChance);
			placeFronds(worldIn, topPos.up().north(2).west(2), true, false, EnumFacing.SOUTH, deadChance);
			placeFronds(worldIn, topPos.up().south(2).west(2), true, false, EnumFacing.EAST, deadChance);
			placeFronds(worldIn, topPos.up().south(2).east(2), true, false, EnumFacing.NORTH, deadChance);
			
			return true;
		}
	}
	
	public void placeFronds(World worldIn, BlockPos pos, boolean upward, boolean top, EnumFacing facing, int deadChance)
	{
		if(worldIn.rand.nextInt(100) < deadChance)
			worldIn.setBlockState(pos, MBlocks.palm_fronds_dead.getDefaultState().withProperty(BlockPalmFronds.TOP, top).withProperty(BlockPalmFronds.UPWARD, upward).withProperty(BlockPalmFronds.FACING, facing));
		else
			worldIn.setBlockState(pos, MBlocks.palm_fronds.getDefaultState().withProperty(BlockPalmFronds.TOP, top).withProperty(BlockPalmFronds.UPWARD, upward).withProperty(BlockPalmFronds.FACING, facing));
	}
}
