package minestrapp.block;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import minestrapp.Minestrapp5;
import minestrapp.block.tileentity.TileEntityPipe;
import minestrapp.block.tileentity.TileEntitySorter;
import minestrapp.gui.MGuiHandler;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;

public class BlockSorter extends BlockPipe
{
	public static final PropertyDirection FACING = PropertyDirection.create("facing", new Predicate<EnumFacing>()
    {
        public boolean apply(@Nullable EnumFacing p_apply_1_)
        {
            return p_apply_1_ != EnumFacing.DOWN;
        }
    });
	protected static final AxisAlignedBB AABB_UP = new AxisAlignedBB(0.25D, 0D, 0.25D, 0.75D, 1D, 0.75D);
	protected static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(0.25D, 0D, 0.25D, 0.75D, 0.75D, 1D);
	protected static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(0D, 0.25D, 0D, 0.75D, 0.75D, 0.75D);
	protected static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(0.25D, 0D, 0D, 0.75D, 0.75D, 0.75D);
	protected static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(0.25D, 0D, 0.25D, 1D, 0.75D, 0.75D);
	
	public BlockSorter(String name)
	{
		super(name, false, false);
	}
	
	public void setOverridableDefaultState()
	{
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.UP));
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        if(blockState.getValue(FACING) == EnumFacing.UP)
        	return AABB_UP;
        if(blockState.getValue(FACING) == EnumFacing.NORTH)
        	return AABB_NORTH;
        if(blockState.getValue(FACING) == EnumFacing.EAST)
        	return AABB_EAST;
        if(blockState.getValue(FACING) == EnumFacing.SOUTH)
        	return AABB_SOUTH;
        if(blockState.getValue(FACING) == EnumFacing.WEST)
        	return AABB_WEST;
        else
        	return AABB_UP;
    }
	
	public MapColor getMapColor(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return MapColor.YELLOW_STAINED_HARDENED_CLAY;
    }
	
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
		if(facing == EnumFacing.UP)
			return this.getDefaultState().withProperty(FACING, EnumFacing.UP);
        return this.getDefaultState().withProperty(FACING, facing.getOpposite());
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
	
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }
	
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            ILockableContainer ilockablecontainer = this.getLockableContainer(worldIn, pos);

            if (ilockablecontainer != null)
            {
                playerIn.openGui(Minestrapp5.instance, MGuiHandler.SORTER, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }

            return true;
        }
    }
	
	@Nullable
    public ILockableContainer getContainer(World worldIn, BlockPos pos, boolean allowBlocking)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (!(tileentity instanceof TileEntitySorter))
        {
            return null;
        }
        else
        {
            ILockableContainer ilockablecontainer = (TileEntitySorter)tileentity;

            return ilockablecontainer;
        }
    }
	
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);

        if (stack.hasDisplayName())
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntitySorter)
            {
                ((TileEntitySorter)tileentity).setCustomName(stack.getDisplayName());
            }
        }
    }
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntitySorter();
	}
}
