package minestrapp.block;

import java.util.Random;

import minestrapp.MItems;
import minestrapp.block.util.BlockBase;
import minestrapp.block.util.BlockBaseNonSolid;
import minestrapp.item.tools.MDagger;
import minestrapp.utils.BlockUtil;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCheese extends BlockFoodSliceable
{
	protected static final AxisAlignedBB AABB_0 = BlockUtil.createBoundingBox(1, 0, 1, 15, 14, 15);
	protected static final AxisAlignedBB AABB_1 = BlockUtil.createBoundingBox(1, 0, 1, 14, 14, 15);
	protected static final AxisAlignedBB AABB_2 = BlockUtil.createBoundingBox(1, 0, 1, 13, 14, 15);
	protected static final AxisAlignedBB AABB_3 = BlockUtil.createBoundingBox(1, 0, 1, 12, 14, 15);
	protected static final AxisAlignedBB AABB_4 = BlockUtil.createBoundingBox(1, 0, 1, 11, 14, 15);
	protected static final AxisAlignedBB AABB_5 = BlockUtil.createBoundingBox(1, 0, 1, 10, 14, 15);
	protected static final AxisAlignedBB AABB_6 = BlockUtil.createBoundingBox(1, 0, 1, 9, 14, 15);
	protected static final AxisAlignedBB AABB_7 = BlockUtil.createBoundingBox(1, 0, 1, 8, 14, 15);
	protected static final AxisAlignedBB AABB_8 = BlockUtil.createBoundingBox(1, 0, 1, 7, 14, 15);
	protected static final AxisAlignedBB AABB_9 = BlockUtil.createBoundingBox(1, 0, 1, 6, 14, 15);
	protected static final AxisAlignedBB AABB_10 = BlockUtil.createBoundingBox(1, 0, 1, 5, 14, 15);
	protected static final AxisAlignedBB AABB_11 = BlockUtil.createBoundingBox(0, 0, 0, 16, 3, 16);
	protected static final AxisAlignedBB AABB_12 = BlockUtil.createBoundingBox(0, 0, 0, 16, 2, 16);
	protected static final AxisAlignedBB AABB_13 = BlockUtil.createBoundingBox(2, 0, 1, 16, 1, 15);
	
	public BlockCheese()
	{
		super("block_cheese", MapColor.ADOBE, SoundType.WOOD, 0.5F, 14, true);
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        if(blockState.getValue(SLICES).intValue() == 0)
        	return AABB_0;
        if(blockState.getValue(SLICES).intValue() == 1)
        	return AABB_1;
        if(blockState.getValue(SLICES).intValue() == 2)
        	return AABB_2;
        if(blockState.getValue(SLICES).intValue() == 3)
        	return AABB_3;
        if(blockState.getValue(SLICES).intValue() == 4)
        	return AABB_4;
        if(blockState.getValue(SLICES).intValue() == 5)
        	return AABB_5;
        if(blockState.getValue(SLICES).intValue() == 6)
        	return AABB_6;
        if(blockState.getValue(SLICES).intValue() == 7)
        	return AABB_7;
        if(blockState.getValue(SLICES).intValue() == 8)
        	return AABB_8;
        if(blockState.getValue(SLICES).intValue() == 9)
        	return AABB_9;
        if(blockState.getValue(SLICES).intValue() == 10)
        	return AABB_10;
        if(blockState.getValue(SLICES).intValue() == 11)
        	return AABB_11;
        if(blockState.getValue(SLICES).intValue() == 12)
        	return AABB_12;
        return AABB_13;
    }
}
