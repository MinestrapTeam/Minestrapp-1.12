package minestrapp.block.util;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPanelBase extends BlockBase
{
	public static final PropertyDirection FACING = PropertyDirection.create("facing", new Predicate<EnumFacing>()
    {
        public boolean apply(@Nullable EnumFacing p_apply_1_)
        {
            return p_apply_1_ != EnumFacing.DOWN;
        }
    });
	
	/**
	 
	The quadrant to which the block is connected. Quadrants are assigned an int value as follows:
	0 = Not Connected
	
	     N
	   1 | 2
	 W --+-- E
	   3 | 4
	     S
	     
	**/
	public static final PropertyInteger CONNECTED = PropertyInteger.create("connected", 0, 5);
	
	protected static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(0D, 0D, 0.5D, 1D, 1D, 1D);
	protected static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(0D, 0D, 0D, 0.5D, 1D, 1D);
	protected static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(0D, 0D, 0D, 1D, 1D, 0.5D);
	protected static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(0.5D, 0D, 0D, 1D, 1D, 1D);
	protected static final AxisAlignedBB AABB_SE = new AxisAlignedBB(0.5D, 0D, 0.5D, 1D, 1D, 1D);
	protected static final AxisAlignedBB AABB_SW = new AxisAlignedBB(0D, 0D, 0.5D, 0.5D, 1D, 1D);
	protected static final AxisAlignedBB AABB_NE = new AxisAlignedBB(0.5D, 0D, 0D, 1D, 1D, 0.5D);
	protected static final AxisAlignedBB AABB_NW = new AxisAlignedBB(0D, 0D, 0D, 0.5D, 1D, 0.5D);
	
	private Block block;
	private int meta;
	
	public BlockPanelBase(Block block)
	{
		super("panel_" + block.getUnlocalizedName().substring(5), block.getMaterial(block.getDefaultState()), MapColor.AIR, block.getSoundType(), 0F, block.getHarvestTool(block.getDefaultState()), block.getHarvestLevel(block.getDefaultState()));
		this.block = block;
		this.meta = 0;
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(CONNECTED, Integer.valueOf(0)));
	}
	
	public BlockPanelBase(Block block, int meta, String variant)
	{
		super("panel_" + block.getUnlocalizedName().substring(5) + "_" + variant, block.getStateFromMeta(meta).getMaterial(), MapColor.AIR, block.getSoundType(), 0F, block.getHarvestTool(block.getStateFromMeta(meta)), block.getHarvestLevel(block.getStateFromMeta(meta)));
		this.meta = meta;
		this.meta = meta;
	}

	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState)
    {
        if (!isActualState)
        	state = state.getActualState(worldIn, pos);
        
        EnumFacing facing = state.getValue(FACING);
        int connected = Integer.valueOf(state.getValue(CONNECTED));
        
        if(facing == EnumFacing.UP)
        	addCollisionBoxToList(pos, entityBox, collidingBoxes, FULL_BLOCK_AABB);
        else if(facing == EnumFacing.NORTH)
        {
        	if(connected != 2)
        		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_NW);
        	if(connected != 1)
        		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_NE);
        	if(connected == 3)
        		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_SW);
        	if(connected == 4)
        		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_SE);
        }
        else if(facing == EnumFacing.SOUTH)
        {
        	if(connected == 1)
        		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_NW);
        	if(connected == 2)
        		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_NE);
        	if(connected != 4)
        		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_SW);
        	if(connected != 3)
        		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_SE);
        }
        else if(facing == EnumFacing.EAST)
        {
        	if(connected == 1)
        		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_NW);
        	if(connected != 4)
        		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_NE);
        	if(connected == 3)
        		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_SW);
        	if(connected != 2)
        		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_SE);
        }
        else if(facing == EnumFacing.WEST)
        {
        	if(connected != 3)
        		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_NW);
        	if(connected == 2)
        		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_NE);
        	if(connected != 1)
        		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_SW);
        	if(connected == 4)
        		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_SE);
        }
    }
	
	public AxisAlignedBB getBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        if(blockState.getValue(FACING) == EnumFacing.NORTH)
        	return AABB_NORTH;
        if(blockState.getValue(FACING) == EnumFacing.EAST)
        	return AABB_EAST;
        if(blockState.getValue(FACING) == EnumFacing.SOUTH)
        	return AABB_SOUTH;
        if(blockState.getValue(FACING) == EnumFacing.WEST)
        	return AABB_WEST;
        else
        	return FULL_BLOCK_AABB;
    }
	
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        int quadrant = 0;
        EnumFacing facing = state.getValue(FACING);
        
        if (facing == EnumFacing.UP)
        	return state;
        
        boolean connectedLeft = false;
        IBlockState leftState = worldIn.getBlockState(pos.offset(facing.rotateYCCW()));
        boolean connectedRight = false;
        IBlockState rightState = worldIn.getBlockState(pos.offset(facing.rotateY()));
        
        if(leftState.getBlock() instanceof BlockPanelBase && leftState.getValue(FACING) == facing)
        	connectedLeft = true;
        if(rightState.getBlock() instanceof BlockPanelBase && rightState.getValue(FACING) == facing)
        	connectedRight = true;
        
        EnumFacing facingFace = null;
        IBlockState facingState = worldIn.getBlockState(pos.offset(facing));
        EnumFacing oppositeFace = null;
        IBlockState oppositeState = worldIn.getBlockState(pos.offset(facing.getOpposite()));
        
        if(facingState.getBlock() instanceof BlockPanelBase)
        	facingFace = facingState.getValue(FACING);
        if(oppositeState.getBlock() instanceof BlockPanelBase)
        	oppositeFace = oppositeState.getValue(FACING);
        
        if(oppositeFace != null && oppositeFace != EnumFacing.UP && oppositeFace.getAxis() != facing.getAxis())
        {
        	if(facing == EnumFacing.NORTH)
        	{
        		if(oppositeFace == EnumFacing.EAST)
        			quadrant = 4;
        		else if(oppositeFace == EnumFacing.WEST)
        			quadrant = 3;
        	}
        	else if(facing == EnumFacing.SOUTH)
        	{
        		if(oppositeFace == EnumFacing.EAST)
        			quadrant = 2;
        		else if(oppositeFace == EnumFacing.WEST)
        			quadrant = 1;
        	}
        	else if(facing == EnumFacing.EAST)
        	{
        		if(oppositeFace == EnumFacing.NORTH)
        			quadrant = 1;
        		else if(oppositeFace == EnumFacing.SOUTH)
        			quadrant = 3;
        	}
        	else if(facing == EnumFacing.WEST)
        	{
        		if(oppositeFace == EnumFacing.NORTH)
        			quadrant = 2;
        		else if(oppositeFace == EnumFacing.SOUTH)
        			quadrant = 4;
        	}
        }
        else if(!(connectedLeft && connectedRight) && facingFace != null && facingFace != EnumFacing.UP && facingFace.getAxis() != facing.getAxis())
        {
        	if(facing == EnumFacing.NORTH)
        	{
        		if(facingFace == EnumFacing.EAST && !connectedLeft)
        			quadrant = 2;
        		else if(facingFace == EnumFacing.WEST && !connectedRight)
        			quadrant = 1;
        	}
        	else if(facing == EnumFacing.SOUTH)
        	{
        		if(facingFace == EnumFacing.EAST && !connectedRight)
        			quadrant = 4;
        		else if(facingFace == EnumFacing.WEST && !connectedLeft)
        			quadrant = 3;
        	}
        	else if(facing == EnumFacing.EAST)
        	{
        		if(facingFace == EnumFacing.NORTH && !connectedRight)
        			quadrant = 2;
        		else if(facingFace == EnumFacing.SOUTH && !connectedLeft)
        			quadrant = 4;
        	}
        	else if(facing == EnumFacing.WEST)
        	{
        		if(facingFace == EnumFacing.NORTH && !connectedLeft)
        			quadrant = 1;
        		else if(facingFace == EnumFacing.SOUTH && !connectedRight)
        			quadrant = 3;
        	}
        }
        
        return state.withProperty(CONNECTED, Integer.valueOf(quadrant));
    }
	
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, getFacing(meta));
    }
	
	public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | ((EnumFacing)state.getValue(FACING)).getIndex();

        return i;
    }
	
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
		if(facing.getAxis().isHorizontal())
			return this.getDefaultState().withProperty(FACING, facing.getOpposite());
		else
		{
			EnumFacing playerDir = placer.getHorizontalFacing();
			if(playerDir.getAxis() == EnumFacing.Axis.Z)
			{
				if(hitZ > 0.5F)
					return this.getDefaultState().withProperty(FACING, EnumFacing.SOUTH);
				else
					return this.getDefaultState().withProperty(FACING, EnumFacing.NORTH);
			}
			else
			{
				if(hitX > 0.5F)
					return this.getDefaultState().withProperty(FACING, EnumFacing.EAST);
				else
					return this.getDefaultState().withProperty(FACING, EnumFacing.WEST);
			}
		}
    }
	
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
		return super.canPlaceBlockAt(worldIn, pos);
        //return worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos) || worldIn.getBlockState(pos).getBlock() == this;
    }
    
	public static EnumFacing getFacing(int meta)
    {
    	return EnumFacing.getFront(meta & 7);
    }
    
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }
    
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING, CONNECTED});
    }

    public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing)
    {
        return BlockFaceShape.UNDEFINED;
    }
}
