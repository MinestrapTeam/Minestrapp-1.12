package minestrapp.block;

import javax.annotation.Nullable;

import minestrapp.MTabs;
import minestrapp.Minestrapp;
import minestrapp.block.tileentity.TileEntityPipe;
import minestrapp.gui.MGuiHandler;
import net.minecraft.block.BlockContainer;
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
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;

public class BlockPipe extends BlockContainer
{
	private boolean covered;
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	
	protected static final AxisAlignedBB AABB_DOWN = new AxisAlignedBB(0.25D, 0D, 0.25D, 0.75D, 0.75D, 0.75D);
	protected static final AxisAlignedBB AABB_UP = new AxisAlignedBB(0.25D, 0.25D, 0.25D, 0.75D, 1D, 0.75D);
	protected static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(0.25D, 0.25D, 0.25D, 0.75D, 0.75D, 1D);
	protected static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(0D, 0.25D, 0.25D, 0.75D, 0.75D, 0.75D);
	protected static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(0.25D, 0.25D, 0D, 0.75D, 0.75D, 0.75D);
	protected static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(0.25D, 0.25D, 0.25D, 1D, 0.75D, 0.75D);
	
	public BlockPipe(String name, boolean covered, boolean deep)
	{
		super(Material.IRON);
		this.setSoundType(SoundType.METAL);
		
		float hardMult = 1F;
		if(deep)
			hardMult = 1.5F;
		
		this.setHardness(3.0F * hardMult);
		this.setHarvestLevel("pickaxe", deep ? 1 : 0);
		this.setResistance(6F);
		this.setCreativeTab(MTabs.utility);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setOverridableDefaultState();
		this.covered = covered;
	}
	
	public void setOverridableDefaultState()
	{
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.UP));
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
		if(this.covered)
			return FULL_BLOCK_AABB;
        if(blockState.getValue(FACING) == EnumFacing.UP)
        	return AABB_UP;
        if(blockState.getValue(FACING) == EnumFacing.DOWN)
        	return AABB_DOWN;
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
        return MapColor.ADOBE;
    }
	
	public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
	
	public boolean isOpaqueCube(IBlockState state)
    {
        return this.covered;
    }

    public boolean isFullCube(IBlockState state)
    {
        return this.covered;
    }
    
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, facing.getOpposite());
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
                playerIn.openGui(Minestrapp.instance, MGuiHandler.PIPE, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }

            return true;
        }
    }
    
    @Nullable
    public ILockableContainer getLockableContainer(World worldIn, BlockPos pos)
    {
        return this.getContainer(worldIn, pos, false);
    }
    
    @Nullable
    public ILockableContainer getContainer(World worldIn, BlockPos pos, boolean allowBlocking)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (!(tileentity instanceof TileEntityPipe))
        {
            return null;
        }
        else
        {
            ILockableContainer ilockablecontainer = (TileEntityPipe)tileentity;

            return ilockablecontainer;
        }
    }

	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
		TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof IInventory)
        {
            InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory)tileentity);
            worldIn.updateComparatorOutputLevel(pos, this);
        }

        super.breakBlock(worldIn, pos, state);
    }
	
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);

        if (stack.hasDisplayName())
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityPipe)
            {
                ((TileEntityPipe)tileentity).setCustomName(stack.getDisplayName());
            }
        }
    }

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityPipe();
	}
	
	public boolean hasComparatorInputOverride(IBlockState state)
    {
        return true;
    }
	
	public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return Container.calcRedstoneFromInventory(this.getLockableContainer(worldIn, pos));
    }
	
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }
}
