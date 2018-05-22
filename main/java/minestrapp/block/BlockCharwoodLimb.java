package minestrapp.block;

import javax.annotation.Nullable;

import minestrapp.block.util.BlockBase;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCharwoodLimb extends BlockBase
{
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	protected static final AxisAlignedBB LIMB_NORTH_AABB = new AxisAlignedBB(0.40625D, 0D, 0D, 0.59375D, 1D, 0.4375D);
    protected static final AxisAlignedBB LIMB_SOUTH_AABB = new AxisAlignedBB(0.40625D, 0D, 0.5625D, 0.59375D, 1D, 1D);
    protected static final AxisAlignedBB LIMB_WEST_AABB = new AxisAlignedBB(0D, 0D, 0.40625D, 0.4375D, 1D, 0.59375D);
    protected static final AxisAlignedBB LIMB_EAST_AABB = new AxisAlignedBB(0.5625D, 0D, 0.40625D, 1D, 1D, 0.59375D);
	
	public BlockCharwoodLimb()
	{
		super("charwood_limb", Material.WOOD, BlockMPlanks.EnumType.CHARWOOD.getMapColor(), SoundType.WOOD, 0.8F, "axe", 0);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.setDropsItem(new ItemStack(Items.STICK), 2, 0, 0, false, true);
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        switch ((EnumFacing)state.getValue(FACING))
        {
            case EAST:
                return LIMB_EAST_AABB;
            case WEST:
                return LIMB_WEST_AABB;
            case SOUTH:
                return LIMB_SOUTH_AABB;
            default:
                return LIMB_NORTH_AABB;
        }
    }
	
	@Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return this.getBoundingBox(blockState, worldIn, pos);
    }
	
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
    }
    
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing;
        IBlockState state = this.getDefaultState();
        
        if(meta == 0)
        	enumfacing = EnumFacing.NORTH;
        else if(meta == 1)
        	enumfacing = EnumFacing.SOUTH;
        else if(meta == 2)
        	enumfacing = EnumFacing.EAST;
        else
        	enumfacing = EnumFacing.WEST;
        
        return state.withProperty(FACING, enumfacing);
    }
    
    public int getMetaFromState(IBlockState state)
    {
    	int i;
    	EnumFacing enumfacing = state.getValue(FACING);
    	
    	if(enumfacing == EnumFacing.NORTH)
    		i = 0;
    	else if(enumfacing == EnumFacing.SOUTH)
    		i = 1;
    	else if(enumfacing == EnumFacing.EAST)
    		i = 2;
    	else
    		i = 3;
    	
    	return i;
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
