package minestrapp.block;

import java.util.List;

import javax.annotation.Nullable;

import minestrapp.MItems;
import minestrapp.block.util.BlockBaseNonSolid;
import minestrapp.utils.BlockUtil;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPizza extends BlockFoodSliceable
{
	protected static final AxisAlignedBB AABB_FULL = BlockUtil.createBoundingBox(0, 0, 0, 16, 3, 16);
	protected static final AxisAlignedBB AABB_BL = BlockUtil.createBoundingBox(0, 0, 8, 8, 3, 16);
	protected static final AxisAlignedBB AABB_HALF = BlockUtil.createBoundingBox(0, 0, 0, 16, 3, 8);
	protected static final AxisAlignedBB AABB_TL = BlockUtil.createBoundingBox(0, 0, 0, 8, 3, 8);
	
	public BlockPizza()
	{
		super("pizza", MapColor.YELLOW, SoundType.SLIME, 0.5F, 4, false);
	}
	
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean p_185477_7_)
    {
		int slices = state.getValue(SLICES).intValue();
		
		if(slices == 0)
			addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_FULL);
		else if(slices == 1 || slices == 2)
		{
			addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_HALF);
			
			if(slices == 1)
				addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BL);
		}
		else
			addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_TL);
    }
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
		int slices = state.getValue(SLICES).intValue();
		
		if(slices < 2)
			return AABB_FULL;
		else if(slices == 2)
			return AABB_HALF;
		return AABB_TL;
    }
}
