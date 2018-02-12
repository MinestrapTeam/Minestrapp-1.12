package minestrapp.worldgen;

import java.util.Random;

import minestrapp.MBlocks;
import minestrapp.block.BlockEndermiteHiveHusk;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class MGenMiteHive extends WorldGenerator
{
	public boolean generate(World worldIn, Random rand, BlockPos position)
	{
		Block hive = MBlocks.mite_hive;
		int height = 5;
		boolean flag = true;
		
		if (position.getY() >= 1 && position.getY() + height + 1 < 256)
        {
			for (int y = position.getY(); y <= position.getY() + 1 + height; ++y)
            {	
				BlockPos.MutableBlockPos growthCheckPos = new BlockPos.MutableBlockPos();

                for (int l = position.getX() - 3; l <= position.getX() + 3 && flag; ++l)
                {
                    for (int i1 = position.getZ() - 3; i1 <= position.getZ() + 3 && flag; ++i1)
                    {
                        if (y >= 0 && y < 256)
                        {
                            IBlockState state = worldIn.getBlockState(growthCheckPos.setPos(l, y, i1));

                            if (!state.getBlock().isReplaceable(worldIn, growthCheckPos) && !state.getBlock().isLeaves(state, worldIn, growthCheckPos))
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }
			
			if (!flag)
            {
                return false;
            }
			else
			{
				for(int pass = 0 ; pass < 3 ; pass++)
            	{
                    int layerY = position.getY();
                    int layer = 0;
                    
                    for (int layerIterator = layerY; layerIterator <= position.getY() + height; ++layerIterator)
                    {
                    	int rad = 3;
                    	
                    	if(layer == 1 || layer == 2)
                    		rad = 4;
                    	else if(layer == 5)
                    		rad = 2;

                        int xMin = position.getX() - rad;
                        int xMax = position.getX() + rad;
                        int zMin = position.getZ() - rad;
                        int zMax = position.getZ() + rad;

                        for (int x = xMin; x <= xMax; ++x)
                        {
                            for (int z = zMin; z <= zMax; ++z)
                            {
                            	int xOffset = x - position.getX();
                            	int zOffset = z - position.getZ();
                            	BlockPos checkPos = new BlockPos(position.getX() + xOffset, layerIterator, position.getZ() + zOffset);
                            	
                            	if (pass == 0 && xOffset * xOffset + zOffset * zOffset < rad * rad)
                            	{
                            		worldIn.setBlockState(new BlockPos(position.getX() + xOffset, layerIterator, position.getZ() + zOffset), hive.getDefaultState());
                            	}
                            	if(pass == 1 && worldIn.getBlockState(checkPos).getBlock() == hive)
                            	{
                            		if(worldIn.getBlockState(checkPos.offset(EnumFacing.NORTH)).getBlock() != hive)
                            		{
                            			if(worldIn.getBlockState(checkPos.offset(EnumFacing.EAST)).getBlock() != hive)
                            			{
                            				if(worldIn.getBlockState(checkPos.offset(EnumFacing.WEST)).getBlock() != hive)
                            					worldIn.setBlockState(checkPos, hive.getDefaultState().withProperty(BlockEndermiteHiveHusk.VARIANT, BlockEndermiteHiveHusk.EnumType.ALL_OUTSIDE));
                            				else
                            					worldIn.setBlockState(checkPos, hive.getDefaultState().withProperty(BlockEndermiteHiveHusk.VARIANT, BlockEndermiteHiveHusk.EnumType.NORTH_EAST));
                            			}
                            			else if(worldIn.getBlockState(checkPos.offset(EnumFacing.WEST)).getBlock() != hive)
                            				worldIn.setBlockState(checkPos, hive.getDefaultState().withProperty(BlockEndermiteHiveHusk.VARIANT, BlockEndermiteHiveHusk.EnumType.NORTH_WEST));
                            			else if(worldIn.getBlockState(checkPos.offset(EnumFacing.SOUTH)).getBlock() != hive)
                            				worldIn.setBlockState(checkPos, hive.getDefaultState().withProperty(BlockEndermiteHiveHusk.VARIANT, BlockEndermiteHiveHusk.EnumType.ALL_OUTSIDE));
                            			else
                            				worldIn.setBlockState(checkPos, hive.getDefaultState().withProperty(BlockEndermiteHiveHusk.VARIANT, BlockEndermiteHiveHusk.EnumType.NORTH));
                            		}
                            		else if(worldIn.getBlockState(checkPos.offset(EnumFacing.EAST)).getBlock() != hive)
                            		{
                            			if(worldIn.getBlockState(checkPos.offset(EnumFacing.SOUTH)).getBlock() != hive)
                            			{
                            				if(worldIn.getBlockState(checkPos.offset(EnumFacing.WEST)).getBlock() != hive)
                            					worldIn.setBlockState(checkPos, hive.getDefaultState().withProperty(BlockEndermiteHiveHusk.VARIANT, BlockEndermiteHiveHusk.EnumType.ALL_OUTSIDE));
                            				else
                            					worldIn.setBlockState(checkPos, hive.getDefaultState().withProperty(BlockEndermiteHiveHusk.VARIANT, BlockEndermiteHiveHusk.EnumType.SOUTH_EAST));
                            			}
                            			else if(worldIn.getBlockState(checkPos.offset(EnumFacing.WEST)).getBlock() != hive)
                            				worldIn.setBlockState(checkPos, hive.getDefaultState().withProperty(BlockEndermiteHiveHusk.VARIANT, BlockEndermiteHiveHusk.EnumType.ALL_OUTSIDE));
                            			else
                            				worldIn.setBlockState(checkPos, hive.getDefaultState().withProperty(BlockEndermiteHiveHusk.VARIANT, BlockEndermiteHiveHusk.EnumType.EAST));
                            		}
                            		else if(worldIn.getBlockState(checkPos.offset(EnumFacing.SOUTH)).getBlock() != hive)
                            		{
                            			if(worldIn.getBlockState(checkPos.offset(EnumFacing.WEST)).getBlock() != hive)
                            				worldIn.setBlockState(checkPos, hive.getDefaultState().withProperty(BlockEndermiteHiveHusk.VARIANT, BlockEndermiteHiveHusk.EnumType.SOUTH_WEST));
                            			else
                            				worldIn.setBlockState(checkPos, hive.getDefaultState().withProperty(BlockEndermiteHiveHusk.VARIANT, BlockEndermiteHiveHusk.EnumType.SOUTH));
                            		}
                            		else if(worldIn.getBlockState(checkPos.offset(EnumFacing.WEST)).getBlock() != hive)
                            			worldIn.setBlockState(checkPos, hive.getDefaultState().withProperty(BlockEndermiteHiveHusk.VARIANT, BlockEndermiteHiveHusk.EnumType.WEST));
                            		else if(worldIn.getBlockState(checkPos.offset(EnumFacing.UP)).getBlock() != hive)
                            			worldIn.setBlockState(checkPos, hive.getDefaultState().withProperty(BlockEndermiteHiveHusk.VARIANT, BlockEndermiteHiveHusk.EnumType.CENTER));
                            		else
                            			worldIn.setBlockState(checkPos, hive.getDefaultState().withProperty(BlockEndermiteHiveHusk.VARIANT, BlockEndermiteHiveHusk.EnumType.ALL_INSIDE));
                            	}
                            	if (pass == 2)
                            	{
                            		if(worldIn.getBlockState(checkPos).getBlock() == hive && rand.nextInt(10) < 4)
                            		{
                            			BlockEndermiteHiveHusk.EnumType type = worldIn.getBlockState(checkPos).getValue(BlockEndermiteHiveHusk.VARIANT);
                            			worldIn.setBlockState(checkPos, MBlocks.mite_hive_honeyed.getDefaultState().withProperty(BlockEndermiteHiveHusk.VARIANT, type));
                            		}
                            	}
                            }
                        }
                        
                        layer++;
                    }
            	}
				
				worldIn.setBlockState(position.offset(EnumFacing.UP, 3), MBlocks.mite_eggsack.getDefaultState());
                worldIn.setBlockToAir(position.offset(EnumFacing.UP, 2));
                worldIn.setBlockToAir(position.offset(EnumFacing.UP, 1));
                
                int chanceDir = rand.nextInt(4);
                EnumFacing holeDir = EnumFacing.NORTH;
                EnumFacing currentDir = EnumFacing.NORTH;
                
                if(chanceDir == 1)
                	holeDir = EnumFacing.EAST;
                else if(chanceDir == 2)
                	holeDir = EnumFacing.SOUTH;
                else if(chanceDir == 3)
                	holeDir = EnumFacing.WEST;
                for(int dir = 0 ; dir < 4 ; dir++)
                {
                	if(currentDir == holeDir || rand.nextInt(8) == 1)
                	{
                		worldIn.setBlockToAir(position.offset(EnumFacing.UP).offset(currentDir, 1));
                		worldIn.setBlockToAir(position.offset(EnumFacing.UP).offset(currentDir, 2));
                		worldIn.setBlockToAir(position.offset(EnumFacing.UP).offset(currentDir, 3));
                	}
                	
                	currentDir = currentDir.rotateY();
                }
				
				return true;
			}
        }
		else
		{
			return false;
		}
	}
}
