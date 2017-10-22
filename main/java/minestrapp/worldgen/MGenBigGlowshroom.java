package minestrapp.worldgen;

import java.util.Random;

import minestrapp.MBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHugeMushroom;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class MGenBigGlowshroom extends WorldGenerator
{
	private final Block mushroomType;

    public MGenBigGlowshroom(Block p_i46449_1_)
    {
        super(true);
        this.mushroomType = p_i46449_1_;
    }

    public MGenBigGlowshroom()
    {
        super(false);
        this.mushroomType = null;
    }
    
    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        Block block = this.mushroomType;

        if (block == null)
        {
            block = rand.nextBoolean() ? MBlocks.green_glowshroom_block : MBlocks.purple_glowshroom_block;
        }

        int height = rand.nextInt(3) + 4;

        if (rand.nextInt(12) == 0)
        {
        	height *= 2;
        }

        boolean flag = true;

        if (position.getY() >= 1 && position.getY() + height + 1 < 256)
        {
            for (int y = position.getY(); y <= position.getY() + 1 + height; ++y)
            {
                int growRad = 3;

                if (y <= position.getY() + 3)
                {
                	growRad = 0;
                }

                BlockPos.MutableBlockPos growthCheckPos = new BlockPos.MutableBlockPos();

                for (int l = position.getX() - growRad; l <= position.getX() + growRad && flag; ++l)
                {
                    for (int i1 = position.getZ() - growRad; i1 <= position.getZ() + growRad && flag; ++i1)
                    {
                        if (y >= 0 && y < 256)
                        {
                            IBlockState state = worldIn.getBlockState(growthCheckPos.setPos(l, y, i1));

                            if (!state.getBlock().isAir(state, worldIn, growthCheckPos) && !state.getBlock().isLeaves(state, worldIn, growthCheckPos))
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
                Block soilBlock = worldIn.getBlockState(position.down()).getBlock();

                if (soilBlock != Blocks.DIRT && soilBlock != Blocks.GRASS && soilBlock != Blocks.MYCELIUM && soilBlock != Blocks.NETHERRACK && soilBlock != Blocks.SOUL_SAND && soilBlock != MBlocks.mud && soilBlock != MBlocks.clay_grass && soilBlock != MBlocks.clay_soil)
                {
                    return false;
                }
                else
                {
                	for(int pass = 0 ; pass < 3 ; pass++)
                	{
	                    int layerY = position.getY() + height;
	
	                    if (block == MBlocks.blue_glowshroom_block)
	                    	layerY -= 3;
	                    else if (block == MBlocks.green_glowshroom_block)
	                    	layerY -= 1;
	                    else if (block == MBlocks.purple_glowshroom_block)
	                    	layerY -= 3;
	                    
	                    for (int layerIterator = layerY; layerIterator <= position.getY() + height; ++layerIterator)
	                    {
	                        int rad = 2;
	
	                        if (layerIterator < position.getY() + height)
	                        {
	                        	if (block == MBlocks.blue_glowshroom_block)
	                        	{
	                        		if (layerIterator == layerY)
	                        			rad = 3;
	                        		else
	                        			rad = 4;
	                        	}
	                        	else if (block == MBlocks.green_glowshroom_block)
	                        	{
	                        		rad = 4;
	                        	}
	                        	else if (block == MBlocks.purple_glowshroom_block)
	                        	{
	                        		if(layerIterator == layerY)
	                        			rad = 4;
	                        		else
	                        			rad = 3;
	                        	}
	                        }
	                        else if (block == MBlocks.blue_glowshroom_block)
	                        {
	                        	rad = 3;
	                        }
	                        else if (block == MBlocks.green_glowshroom_block)
	                        {
	                        	rad = 3;
	                        }
	
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
	                            		worldIn.setBlockState(new BlockPos(position.getX() + xOffset, layerIterator, position.getZ() + zOffset), block.getDefaultState());
	                            	}
	                            	if(pass == 1 && worldIn.getBlockState(checkPos).getBlock() == block && worldIn.getBlockState(checkPos) != block.getDefaultState().withProperty(BlockHugeMushroom.VARIANT, BlockHugeMushroom.EnumType.STEM))
	                            	{
	                            		if(worldIn.getBlockState(checkPos.offset(EnumFacing.NORTH)).getBlock() != block)
	                            		{
	                            			if(worldIn.getBlockState(checkPos.offset(EnumFacing.EAST)).getBlock() != block)
	                            			{
	                            				if(worldIn.getBlockState(checkPos.offset(EnumFacing.WEST)).getBlock() != block)
	                            					worldIn.setBlockState(checkPos, block.getDefaultState().withProperty(BlockHugeMushroom.VARIANT, BlockHugeMushroom.EnumType.ALL_OUTSIDE));
	                            				else
	                            					worldIn.setBlockState(checkPos, block.getDefaultState().withProperty(BlockHugeMushroom.VARIANT, BlockHugeMushroom.EnumType.NORTH_EAST));
	                            			}
	                            			else if(worldIn.getBlockState(checkPos.offset(EnumFacing.WEST)).getBlock() != block)
	                            				worldIn.setBlockState(checkPos, block.getDefaultState().withProperty(BlockHugeMushroom.VARIANT, BlockHugeMushroom.EnumType.NORTH_WEST));
	                            			else if(worldIn.getBlockState(checkPos.offset(EnumFacing.SOUTH)).getBlock() != block)
	                            				worldIn.setBlockState(checkPos, block.getDefaultState().withProperty(BlockHugeMushroom.VARIANT, BlockHugeMushroom.EnumType.ALL_OUTSIDE));
	                            			else
	                            				worldIn.setBlockState(checkPos, block.getDefaultState().withProperty(BlockHugeMushroom.VARIANT, BlockHugeMushroom.EnumType.NORTH));
	                            		}
	                            		else if(worldIn.getBlockState(checkPos.offset(EnumFacing.EAST)).getBlock() != block)
	                            		{
	                            			if(worldIn.getBlockState(checkPos.offset(EnumFacing.SOUTH)).getBlock() != block)
	                            			{
	                            				if(worldIn.getBlockState(checkPos.offset(EnumFacing.WEST)).getBlock() != block)
	                            					worldIn.setBlockState(checkPos, block.getDefaultState().withProperty(BlockHugeMushroom.VARIANT, BlockHugeMushroom.EnumType.ALL_OUTSIDE));
	                            				else
	                            					worldIn.setBlockState(checkPos, block.getDefaultState().withProperty(BlockHugeMushroom.VARIANT, BlockHugeMushroom.EnumType.SOUTH_EAST));
	                            			}
	                            			else if(worldIn.getBlockState(checkPos.offset(EnumFacing.WEST)).getBlock() != block)
	                            				worldIn.setBlockState(checkPos, block.getDefaultState().withProperty(BlockHugeMushroom.VARIANT, BlockHugeMushroom.EnumType.ALL_OUTSIDE));
	                            			else
	                            				worldIn.setBlockState(checkPos, block.getDefaultState().withProperty(BlockHugeMushroom.VARIANT, BlockHugeMushroom.EnumType.EAST));
	                            		}
	                            		else if(worldIn.getBlockState(checkPos.offset(EnumFacing.SOUTH)).getBlock() != block)
	                            		{
	                            			if(worldIn.getBlockState(checkPos.offset(EnumFacing.WEST)).getBlock() != block)
	                            				worldIn.setBlockState(checkPos, block.getDefaultState().withProperty(BlockHugeMushroom.VARIANT, BlockHugeMushroom.EnumType.SOUTH_WEST));
	                            			else
	                            				worldIn.setBlockState(checkPos, block.getDefaultState().withProperty(BlockHugeMushroom.VARIANT, BlockHugeMushroom.EnumType.SOUTH));
	                            		}
	                            		else if(worldIn.getBlockState(checkPos.offset(EnumFacing.WEST)).getBlock() != block)
	                            			worldIn.setBlockState(checkPos, block.getDefaultState().withProperty(BlockHugeMushroom.VARIANT, BlockHugeMushroom.EnumType.WEST));
	                            		else if(worldIn.getBlockState(checkPos.offset(EnumFacing.UP)).getBlock() != block)
	                            			worldIn.setBlockState(checkPos, block.getDefaultState().withProperty(BlockHugeMushroom.VARIANT, BlockHugeMushroom.EnumType.CENTER));
	                            		else
	                            			worldIn.setBlockState(checkPos, block.getDefaultState().withProperty(BlockHugeMushroom.VARIANT, BlockHugeMushroom.EnumType.ALL_INSIDE));
	                            	}
	                            	if (pass == 2 && layerIterator < position.getY() + height && xOffset * xOffset + zOffset * zOffset < (rad - 1) * (rad - 1))
	                            	{
	                            		worldIn.setBlockToAir(new BlockPos(position.getX() + xOffset, layerIterator, position.getZ() + zOffset));
	                            	}
	                            }
	                        }
	                    }
                	}

                    for (int i3 = 0; i3 < height; ++i3)
                    {
                        IBlockState iblockstate = worldIn.getBlockState(position.up(i3));

                        if (iblockstate.getBlock().canBeReplacedByLeaves(iblockstate, worldIn, position.up(i3)))
                        {
                            this.setBlockAndNotifyAdequately(worldIn, position.up(i3), block.getDefaultState().withProperty(BlockHugeMushroom.VARIANT, BlockHugeMushroom.EnumType.STEM));
                        }
                    }

                    return true;
                }
            }
        }
        else
        {
            return false;
        }
    }
}
