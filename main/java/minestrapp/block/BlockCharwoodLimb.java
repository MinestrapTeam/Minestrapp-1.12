package minestrapp.block;

import javax.annotation.Nullable;

import minestrapp.MItems;
import minestrapp.block.util.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
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
    	if (!facing.getAxis().isHorizontal())
            facing = EnumFacing.NORTH;

        return this.getDefaultState().withProperty(FACING, facing.getOpposite());
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
    
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        EnumFacing enumfacing = EnumFacing.fromAngle((double)placer.rotationYaw);
        worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
    }
    
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        pos = pos.offset((EnumFacing)state.getValue(FACING));
        IBlockState iblockstate = worldIn.getBlockState(pos);
        boolean flag = isExceptBlockForAttachWithPiston(iblockstate.getBlock());
        return !flag && iblockstate.getBlockFaceShape(worldIn, pos, (EnumFacing)state.getValue(FACING).getOpposite()) == BlockFaceShape.SOLID;
    }
    
    public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side)
    {
        EnumFacing enumfacing = side.getOpposite();
        return side.getAxis().isHorizontal() && canBlockStay(worldIn, pos, this.getDefaultState().withProperty(FACING, enumfacing));
    }
    
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
        {
            if (this.canPlaceBlockOnSide(worldIn, pos, enumfacing))
            {
                return true;
            }
        }

        return false;
    }
    
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
    	if (!this.canBlockStay(worldIn, pos, state))
        {
            this.checkForDrop(worldIn, pos, state);
        }
    }
    
    private void checkForDrop(World worldIn, BlockPos pos, IBlockState state)
    {
    	worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
        this.dropBlockAsItem(worldIn, pos, state, 0);
    }
    
    @Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Items.STICK);
	}
}
