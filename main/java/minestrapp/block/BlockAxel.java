package minestrapp.block;

import minestrapp.block.util.BlockBase;
import minestrapp.block.util.BlockMechanical;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAxel extends BlockMechanical
{
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	
	protected static final AxisAlignedBB AABB_UD = new AxisAlignedBB(0.25D, 0D, 0.25D, 0.75D, 1D, 0.75D);
	protected static final AxisAlignedBB AABB_NS = new AxisAlignedBB(0.25D, 0.25D, 0D, 0.75D, 0.75D, 1D);
	protected static final AxisAlignedBB AABB_EW = new AxisAlignedBB(0D, 0.25D, 0.25D, 1D, 0.75D, 0.75D);
	
	public BlockAxel(String name, Material material, MapColor mapColor, SoundType soundType, float hardness, String tool, int harvestLevel)
	{
		super(name, material, mapColor, soundType, hardness, tool, harvestLevel);
		this.setDefaultState(this.blockState.getBaseState().withProperty(POWER, Integer.valueOf(0)).withProperty(FACING, EnumFacing.UP));
	}
	
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
		EnumFacing facing = state.getValue(FACING).getOpposite();
        IBlockState checkState = worldIn.getBlockState(pos.offset(facing));
        
        if(checkState.getBlock() instanceof BlockMechanical)
        	return state.withProperty(POWER, ((BlockMechanical)checkState.getBlock()).isProvidingPowerFromFace(worldIn, pos, facing.getOpposite()));
        
        return state.withProperty(POWER, Integer.valueOf(0));
    }
	
	public AxisAlignedBB getBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        if(blockState.getValue(FACING) == EnumFacing.NORTH || blockState.getValue(FACING) == EnumFacing.SOUTH)
        	return AABB_NS;
        if(blockState.getValue(FACING) == EnumFacing.EAST || blockState.getValue(FACING) == EnumFacing.WEST)
        	return AABB_EW;
        return AABB_UD;
    }
	
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
	
	public int isProvidingPowerFromFace(IBlockAccess world, BlockPos pos, EnumFacing face)
	{
		IBlockState state = world.getBlockState(pos);
		return face == state.getValue(FACING) ? state.getValue(POWER).intValue() : 0;
	}
	
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {POWER, FACING});
    }
	
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, facing);
    }
    
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing;
        IBlockState state = this.getDefaultState();
        
        if(meta == 0)
        	enumfacing = EnumFacing.UP;
        else if(meta == 1)
        	enumfacing = EnumFacing.DOWN;
        else if(meta == 2)
        	enumfacing = EnumFacing.NORTH;
        else if(meta == 3)
        	enumfacing = EnumFacing.SOUTH;
        else if(meta == 4)
        	enumfacing = EnumFacing.EAST;
        else
        	enumfacing = EnumFacing.WEST;
        
        return state.withProperty(FACING, enumfacing);
    }
    
    public int getMetaFromState(IBlockState state)
    {
    	int i;
    	EnumFacing enumfacing = state.getValue(FACING);
    	
    	if(enumfacing == EnumFacing.UP)
    		i = 0;
    	else if(enumfacing == EnumFacing.DOWN)
    		i = 1;
    	else if(enumfacing == EnumFacing.NORTH)
    		i = 2;
    	else if(enumfacing == EnumFacing.SOUTH)
    		i = 3;
    	else if(enumfacing == EnumFacing.EAST)
    		i = 4;
    	else
    		i = 5;
    	
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
    
    public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing)
    {
        return BlockFaceShape.UNDEFINED;
    }
}