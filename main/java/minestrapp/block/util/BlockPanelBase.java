package minestrapp.block.util;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
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
	protected static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(0D, 0D, 0.5D, 1D, 1D, 1D);
	protected static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(0D, 0D, 0D, 0.5D, 1D, 1D);
	protected static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(0D, 0D, 0D, 1D, 1D, 0.5D);
	protected static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(0.5D, 0D, 0D, 1D, 1D, 1D);
	
	private Block block;
	private int meta;
	
	public BlockPanelBase(Block block)
	{
		super("panel_" + block.getUnlocalizedName().substring(5), block.getMaterial(block.getDefaultState()), MapColor.AIR, block.getSoundType(), 0F, block.getHarvestTool(block.getDefaultState()), block.getHarvestLevel(block.getDefaultState()));
		this.block = block;
		this.meta = 0;
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}
	
	public BlockPanelBase(Block block, int meta, String variant)
	{
		super("panel_" + block.getUnlocalizedName().substring(5) + "_" + variant, block.getStateFromMeta(meta).getMaterial(), MapColor.AIR, block.getSoundType(), 0F, block.getHarvestTool(block.getStateFromMeta(meta)), block.getHarvestLevel(block.getStateFromMeta(meta)));
		this.meta = meta;
		this.meta = meta;
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
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }

    public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing)
    {
        return BlockFaceShape.UNDEFINED;
    }
}
