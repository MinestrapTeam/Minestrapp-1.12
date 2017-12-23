package minestrapp.block;

import java.util.Random;

import minestrapp.MItems;
import minestrapp.MTabs;
import minestrapp.block.util.BlockStoneBase;
import minestrapp.block.util.BlockStoneBaseMOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockStone;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSunstoneDeposit extends BlockDirectional
{
	protected static final AxisAlignedBB AABB_UP = new AxisAlignedBB(0.0625D, 0D, 0.0625D, 0.9375D, 0.0625D, 0.9375D);
	protected static final AxisAlignedBB AABB_DOWN = new AxisAlignedBB(0.0625D, 0.9375D, 0.0625D, 0.9375D, 1D, 0.9375D);
	protected static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(0.0625D, 0.0625D, 0.9375D, 0.9375D, 0.9375D, 1D);
	protected static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(0D, 0.0625D, 0.0625D, 0.0625D, 0.9375D, 0.9375D);
	protected static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(0.0625D, 0.0625D, 0D, 0.9375D, 0.9375D, 0.0625D);
	protected static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(0.9375D, 0.0625D, 0.0625D, 1D, 0.9375D, 0.9375D);
	
	public BlockSunstoneDeposit(String name)
	{
		super(Material.ROCK);
		this.setSoundType(SoundType.GLASS);
		this.setHardness(1.2F);
		this.setHarvestLevel("pickaxe", 1);
		this.setLightLevel(0.75F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.UP));
		this.setCreativeTab(MTabs.environment);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
	}

	public AxisAlignedBB getBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
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
        return MapColor.SAND;
    }
	
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
    public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side)
    {
        return canPlaceBlock(worldIn, pos, side);
    }

    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        for (EnumFacing enumfacing : EnumFacing.values())
        {
            if (canPlaceBlock(worldIn, pos, enumfacing))
            {
                return true;
            }
        }

        return false;
    }
    
    public static boolean canPlaceBlock(World worldIn, BlockPos pos, EnumFacing direction)
    {
        BlockPos blockpos = pos.offset(direction.getOpposite());
        IBlockState iblockstate = worldIn.getBlockState(blockpos);
        boolean flag = iblockstate.getBlockFaceShape(worldIn, blockpos, direction) == BlockFaceShape.SOLID;
        Block block = iblockstate.getBlock();

        if (direction == EnumFacing.UP)
        {
            return iblockstate.isTopSolid() || !isExceptionBlockForAttaching(block) && flag;
        }
        else
        {
            return !isExceptBlockForAttachWithPiston(block) && flag;
        }
    }
    
    public static boolean canGenerateBlock(World worldIn, BlockPos pos, EnumFacing direction)
    {
    	if(worldIn.getBlockState(pos.offset(direction.getOpposite())).getBlock() instanceof BlockStone || worldIn.getBlockState(pos.offset(direction.getOpposite())).getBlock() instanceof BlockStoneBaseMOnly || worldIn.getBlockState(pos.offset(direction.getOpposite())).getBlock() == Blocks.END_STONE)
    		return canPlaceBlock(worldIn, pos, direction);
    	else
    		return false;
    }
    
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return canPlaceBlock(worldIn, pos, facing) ? this.getDefaultState().withProperty(FACING, facing) : this.getDefaultState().withProperty(FACING, EnumFacing.DOWN);
    }
    
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if (this.checkForDrop(worldIn, pos, state) && !canPlaceBlock(worldIn, pos, (EnumFacing)state.getValue(FACING)))
        {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockToAir(pos);
        }
    }

    private boolean checkForDrop(World worldIn, BlockPos pos, IBlockState state)
    {
        if (this.canPlaceBlockAt(worldIn, pos))
        {
            return true;
        }
        else
        {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockToAir(pos);
            return false;
        }
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

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }

    public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing)
    {
        return BlockFaceShape.UNDEFINED;
    }
    
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
		return MItems.gems;
    }
    
    public int quantityDropped(Random random)
    {
		return 1 + random.nextInt(2);
    }
    
    public int quantityDroppedWithBonus(int fortune, Random random)
    {
		return this.quantityDropped(random) + random.nextInt(fortune + 1);
    }
    
    public int damageDropped(IBlockState state)
    {
		return 0;
    }
    
    @Override
    public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
    {
		Random rand = world instanceof World ? ((World)world).rand : new Random();
	    if (this.getItemDropped(state, RANDOM, fortune) != Item.getItemFromBlock(this))
	    {
	        return MathHelper.getInt(rand, 1, 4);
	    }
	    return 0;
    }
    
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(this);
    }
	
	public boolean canSilkHarvest()
	{
		return true;
	}
	
	public EnumPushReaction getMobilityFlag(IBlockState state)
    {
		return EnumPushReaction.DESTROY;
    }
}
