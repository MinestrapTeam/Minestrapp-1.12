package minestrapp.block;

import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import minestrapp.MBlocks;
import minestrapp.MItems;
import minestrapp.MTabs;
import minestrapp.block.util.BlockBase;
import minestrapp.block.util.BlockBaseNonSolid;
import minestrapp.utils.BlockUtil;
import minestrapp.worldgen.MGenPalmTree;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockSand;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCoconut extends BlockBaseNonSolid implements IGrowable
{
	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 15);
	public static final PropertyDirection STEM = PropertyDirection.create("stem", new Predicate<EnumFacing>()
    {
        public boolean apply(@Nullable EnumFacing p_apply_1_)
        {
            return p_apply_1_ != EnumFacing.DOWN;
        }
    });
	
	protected static final AxisAlignedBB SMALL_AABB = BlockUtil.createBoundingBoxColumn(6, 6, 9);
	protected static final AxisAlignedBB MEDIUM_AABB = BlockUtil.createBoundingBoxColumn(10, 10, 4);
	protected static final AxisAlignedBB LARGE_AABB = BlockUtil.createBoundingBoxColumn(14, 14, 0);
	
	public BlockCoconut() {
		super("coconut", Material.WOOD, MapColor.BROWN, SoundType.WOOD, 3.5F, "axe", 0);
		this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, 8).withProperty(STEM, EnumFacing.NORTH));
		this.setTickRandomly(true);
		this.setCreativeTab(MTabs.plant);
		this.setRenderLayer(BlockRenderLayer.CUTOUT);
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
		if(state.getValue(AGE).intValue() < 3)
			return SMALL_AABB;
		else if(state.getValue(AGE).intValue() < 6)
			return MEDIUM_AABB;
		else
			return LARGE_AABB;
    }
	
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
    }
	
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
		if(!this.canBlockStay(worldIn, pos, state))
		{
			if(state.getValue(AGE).intValue() >= 6)
				worldIn.setBlockState(pos, state.withProperty(AGE, 8));
			else
				worldIn.destroyBlock(pos, true);
		}
		else
			worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
    }
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
		if(!this.canBlockStay(worldIn, pos, state))
		{
			if(state.getValue(AGE).intValue() >= 6)
				worldIn.setBlockState(pos, state.withProperty(AGE, 8));
			else
				worldIn.destroyBlock(pos, true);
		}
		else if(!worldIn.isRemote && !this.checkFallable(worldIn, pos) && rand.nextInt(100) < 35)
			grow(worldIn, pos, state, rand);
    }
	
	private boolean checkFallable(World worldIn, BlockPos pos)
    {
		if(worldIn.getBlockState(pos).getValue(AGE).intValue() < 8)
			return false;
		
        if ((worldIn.isAirBlock(pos.down()) || BlockFalling.canFallThrough(worldIn.getBlockState(pos.down()))) && pos.getY() >= 0)
        {
            int i = 32;

            if (worldIn.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32)))
            {
                if (!worldIn.isRemote)
                {
                    EntityFallingBlock entityfallingblock = new EntityFallingBlock(worldIn, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, worldIn.getBlockState(pos));
                    worldIn.spawnEntity(entityfallingblock);
                }
            }
            else
            {
                IBlockState state = worldIn.getBlockState(pos);
                worldIn.setBlockToAir(pos);
                BlockPos blockpos;

                for (blockpos = pos.down(); (worldIn.isAirBlock(blockpos) || BlockFalling.canFallThrough(worldIn.getBlockState(blockpos))) && blockpos.getY() > 0; blockpos = blockpos.down())
                {
                    ;
                }

                if (blockpos.getY() > 0)
                {
                    worldIn.setBlockState(blockpos.up(), state);
                }
            }
            
            return true;
        }
        
        return false;
    }
	
	public boolean grow(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		int age = state.getValue(AGE).intValue();
		
		if(age < 8)
		{
			worldIn.setBlockState(pos, state.withProperty(AGE, age + 1));
			return true;
		}
		if((worldIn.getBlockState(pos.up()).getBlock() instanceof BlockSand || worldIn.getBlockState(pos.up()).getBlock() instanceof BlockColdSand) && worldIn.isAirBlock(pos.up().up()))
		{
			if(age < 15)
			{
				worldIn.setBlockState(pos, state.withProperty(AGE, age + 1));
				return true;
			}
			
			MGenPalmTree treeGen = new MGenPalmTree();
			if(treeGen.generate(worldIn, rand, pos.up().up()))
			{
				worldIn.setBlockState(pos, worldIn.getBlockState(pos.up()));
				return true;
			}
		}
		return false;
	}
	
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
		if(state.getValue(AGE).intValue() < 6 && worldIn.getBlockState(pos.up()).getBlock() != MBlocks.palm_fronds)
			return false;
		return true;
    }
	
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
        return true;
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        return true;
    }
    
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        this.grow(worldIn, pos, state, rand);
    }
    
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(AGE, meta);
    }
    
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(AGE).intValue();
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {AGE, STEM});
    }
    
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(AGE, 8);
    }
    
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
    	if(worldIn.getBlockState(pos.up().north().east()).getBlock() == MBlocks.palm_crown)
    		return state.withProperty(STEM, EnumFacing.NORTH);
    	if(worldIn.getBlockState(pos.up().north().west()).getBlock() == MBlocks.palm_crown)
    		return state.withProperty(STEM, EnumFacing.WEST);
    	if(worldIn.getBlockState(pos.up().south().west()).getBlock() == MBlocks.palm_crown)
    		return state.withProperty(STEM, EnumFacing.SOUTH);
    	if(worldIn.getBlockState(pos.up().south().east()).getBlock() == MBlocks.palm_crown)
    		return state.withProperty(STEM, EnumFacing.EAST);
    	return state.withProperty(STEM, EnumFacing.UP);
    }
    
    @Override public int quantityDropped(IBlockState state, int fortune, Random random)
	{
		if(state.getValue(AGE).intValue() >= 6)
		{
			return super.quantityDropped(state, fortune, random);
		}
		
		return 0;
	}
	
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        if(state.getValue(AGE).intValue() >= 6)
        	return true;
        return false;
    }
}
