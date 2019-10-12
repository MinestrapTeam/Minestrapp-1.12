package minestrapp.worldgen;

import java.util.Random;

import minestrapp.MBlocks;
import minestrapp.block.BlockDesertQuartzDeposit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class MGenDesertQuartz extends WorldGenerator
{
	@Override
	public boolean generate(World world, Random rand, BlockPos pos)
	{
		if(world.getBlockState(pos).getBlock() == Blocks.SAND || world.getBlockState(pos).getBlock() == Blocks.SANDSTONE || world.getBlockState(pos).getBlock() == Blocks.HARDENED_CLAY || world.getBlockState(pos).getBlock() == Blocks.STAINED_HARDENED_CLAY)
		{
			int baseHeight = 0;
			
			for(int height = 0 ; height < 20 ; height++)
			{
				Block soil = world.getBlockState(pos.offset(EnumFacing.UP, height)).getBlock();
				
				if(soil == Blocks.SAND || soil == Blocks.SANDSTONE || soil == Blocks.HARDENED_CLAY || soil == Blocks.STAINED_HARDENED_CLAY)
				{
					if(world.isAirBlock(pos.offset(EnumFacing.UP, height).offset(EnumFacing.NORTH, (20 - height) / 2)))
						baseHeight++;
					else if(world.isAirBlock(pos.offset(EnumFacing.UP, height).offset(EnumFacing.EAST, (20 - height) / 2)))
						baseHeight++;
					else if(world.isAirBlock(pos.offset(EnumFacing.UP, height).offset(EnumFacing.SOUTH, (20 - height) / 2)))
						baseHeight++;
					else if(world.isAirBlock(pos.offset(EnumFacing.UP, height).offset(EnumFacing.WEST, (20 - height) / 2)))
						baseHeight++;
				}
				else
					break;
			}
			
			if(baseHeight >= 3)
			{
				int maxHeight = baseHeight + rand.nextInt(baseHeight / 2);
				
				for(int layer = 0 ; layer < maxHeight ; layer++)
				{
					int radius = Math.round((maxHeight - layer) / 2);
					
					for (int x = pos.getX() - radius ; x <= pos.getX() + radius ; ++x)
		            {
						for(int z = pos.getZ() - radius ; z <= pos.getZ() + radius ; ++z)
						{
							int xOffset = x - pos.getX();
			                int zOffset = z - pos.getZ();
			
			                if (xOffset * xOffset + zOffset * zOffset <= radius * radius)
			                {
			                	boolean generate = true;
			                	
			                	if(xOffset * xOffset + zOffset * zOffset > (radius-1) * (radius-1))
			                		generate = rand.nextBoolean();
			                	
			                	if(generate)
			                	{
			                		if(layer == 0 || world.getBlockState(new BlockPos(x, pos.getY() + layer - 2, z)).getBlock() == MBlocks.desert_quartz_deposit)
			                		{
			                			BlockPos placePos = new BlockPos(x, pos.getY() + layer - 1, z);
			                			IBlockState placeState = MBlocks.desert_quartz_deposit.getDefaultState().withProperty(BlockDesertQuartzDeposit.VARIANT, BlockDesertQuartzDeposit.DepositType.DEFAULT);
			                			
			                			if(world.getBlockState(placePos.offset(EnumFacing.NORTH)) == Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.SAND) || world.getBlockState(placePos.offset(EnumFacing.NORTH)).getBlock() == Blocks.SANDSTONE || world.getBlockState(placePos.offset(EnumFacing.EAST)) == Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.SAND) || world.getBlockState(placePos.offset(EnumFacing.EAST)).getBlock() == Blocks.SANDSTONE || world.getBlockState(placePos.offset(EnumFacing.SOUTH)) == Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.SAND) || world.getBlockState(placePos.offset(EnumFacing.SOUTH)).getBlock() == Blocks.SANDSTONE || world.getBlockState(placePos.offset(EnumFacing.WEST)) == Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.SAND) || world.getBlockState(placePos.offset(EnumFacing.WEST)).getBlock() == Blocks.SANDSTONE)
			                				placeState = MBlocks.desert_quartz_deposit.getDefaultState().withProperty(BlockDesertQuartzDeposit.VARIANT, BlockDesertQuartzDeposit.DepositType.SAND);
			                			else if(world.getBlockState(placePos.offset(EnumFacing.NORTH)) == Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND) || world.getBlockState(placePos.offset(EnumFacing.NORTH)).getBlock() == Blocks.RED_SANDSTONE || world.getBlockState(placePos.offset(EnumFacing.NORTH)).getBlock() == Blocks.HARDENED_CLAY || world.getBlockState(placePos.offset(EnumFacing.NORTH)).getBlock() == Blocks.STAINED_HARDENED_CLAY || world.getBlockState(placePos.offset(EnumFacing.EAST)) == Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND) || world.getBlockState(placePos.offset(EnumFacing.EAST)).getBlock() == Blocks.RED_SANDSTONE || world.getBlockState(placePos.offset(EnumFacing.EAST)).getBlock() == Blocks.HARDENED_CLAY || world.getBlockState(placePos.offset(EnumFacing.EAST)).getBlock() == Blocks.STAINED_HARDENED_CLAY || world.getBlockState(placePos.offset(EnumFacing.SOUTH)) == Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND) || world.getBlockState(placePos.offset(EnumFacing.SOUTH)).getBlock() == Blocks.RED_SANDSTONE || world.getBlockState(placePos.offset(EnumFacing.SOUTH)).getBlock() == Blocks.HARDENED_CLAY || world.getBlockState(placePos.offset(EnumFacing.SOUTH)).getBlock() == Blocks.STAINED_HARDENED_CLAY || world.getBlockState(placePos.offset(EnumFacing.WEST)) == Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND) || world.getBlockState(placePos.offset(EnumFacing.WEST)).getBlock() == Blocks.RED_SANDSTONE || world.getBlockState(placePos.offset(EnumFacing.WEST)).getBlock() == Blocks.HARDENED_CLAY || world.getBlockState(placePos.offset(EnumFacing.WEST)).getBlock() == Blocks.STAINED_HARDENED_CLAY)
			                				placeState = MBlocks.desert_quartz_deposit.getDefaultState().withProperty(BlockDesertQuartzDeposit.VARIANT, BlockDesertQuartzDeposit.DepositType.RED_SAND);
			                			
			                			world.setBlockState(placePos, placeState);
			                		}
			                	}
			                }
						}
		            }
				}
				
				for(int layerB = 0 ; layerB < (maxHeight / 2) ; layerB++)
				{
					int radiusB = Math.round(((maxHeight - layerB) / 2) - 1);
					
					for (int xB = pos.getX() - radiusB ; xB <= pos.getX() + radiusB ; ++xB)
		            {
						for(int zB = pos.getZ() - radiusB ; zB <= pos.getZ() + radiusB ; ++zB)
						{
							int xOffsetB = xB - pos.getX();
			                int zOffsetB = zB - pos.getZ();
			
			                if (xOffsetB * xOffsetB + zOffsetB * zOffsetB <= radiusB * radiusB)
			                {
			                	boolean generate = true;
			                	
			                	if(xOffsetB * xOffsetB + zOffsetB * zOffsetB > (radiusB-1) * (radiusB-1))
			                		generate = rand.nextBoolean();
			                	
			                	if(generate)
			                	{
			                		if(layerB == 0 || world.getBlockState(new BlockPos(xB, pos.getY() - layerB + 1, zB)).getBlock() == MBlocks.desert_quartz_deposit)
			                		{
			                			BlockPos placePosB = new BlockPos(xB, pos.getY() - layerB, zB);
			                			IBlockState placeStateB = MBlocks.desert_quartz_deposit.getDefaultState().withProperty(BlockDesertQuartzDeposit.VARIANT, BlockDesertQuartzDeposit.DepositType.DEFAULT);
			                			
			                			if(world.getBlockState(placePosB.offset(EnumFacing.NORTH)) == Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.SAND) || world.getBlockState(placePosB.offset(EnumFacing.NORTH)).getBlock() == Blocks.SANDSTONE || world.getBlockState(placePosB.offset(EnumFacing.EAST)) == Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.SAND) || world.getBlockState(placePosB.offset(EnumFacing.EAST)).getBlock() == Blocks.SANDSTONE || world.getBlockState(placePosB.offset(EnumFacing.SOUTH)) == Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.SAND) || world.getBlockState(placePosB.offset(EnumFacing.SOUTH)).getBlock() == Blocks.SANDSTONE || world.getBlockState(placePosB.offset(EnumFacing.WEST)) == Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.SAND) || world.getBlockState(placePosB.offset(EnumFacing.WEST)).getBlock() == Blocks.SANDSTONE)
			                				placeStateB = MBlocks.desert_quartz_deposit.getDefaultState().withProperty(BlockDesertQuartzDeposit.VARIANT, BlockDesertQuartzDeposit.DepositType.SAND);
			                			else if(world.getBlockState(placePosB.offset(EnumFacing.NORTH)) == Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND) || world.getBlockState(placePosB.offset(EnumFacing.NORTH)).getBlock() == Blocks.RED_SANDSTONE || world.getBlockState(placePosB.offset(EnumFacing.EAST)) == Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND) || world.getBlockState(placePosB.offset(EnumFacing.EAST)).getBlock() == Blocks.RED_SANDSTONE || world.getBlockState(placePosB.offset(EnumFacing.SOUTH)) == Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND) || world.getBlockState(placePosB.offset(EnumFacing.SOUTH)).getBlock() == Blocks.RED_SANDSTONE || world.getBlockState(placePosB.offset(EnumFacing.WEST)) == Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND) || world.getBlockState(placePosB.offset(EnumFacing.WEST)).getBlock() == Blocks.RED_SANDSTONE)
			                				placeStateB = MBlocks.desert_quartz_deposit.getDefaultState().withProperty(BlockDesertQuartzDeposit.VARIANT, BlockDesertQuartzDeposit.DepositType.RED_SAND);
			                			
			                			world.setBlockState(placePosB, placeStateB);
			                		}
			                	}
			                }
						}
		            }
				}
			}
		}
		
		return false;
	}
}
