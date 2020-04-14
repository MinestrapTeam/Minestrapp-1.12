package minestrapp.block;

import java.util.List;

import javax.annotation.Nullable;

import minestrapp.MTabs;
import minestrapp.block.util.BlockBase;
import minestrapp.block.util.BlockBaseNonSolid;
import minestrapp.utils.BlockUtil;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class BlockPalmFronds extends BlockBaseNonSolid implements IShearable
{
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final PropertyBool TOP = PropertyBool.create("top");
	public static final PropertyBool UPWARD = PropertyBool.create("upward");
	
	protected static final AxisAlignedBB BOUNDING_TOP = BlockUtil.createBoundingBox(0, 8, 0, 16, 16, 16);
	protected static final AxisAlignedBB BOUNDING_BOTTOM = BlockUtil.createBoundingBox(0, 0, 0, 16, 8, 16);
	
	protected static final AxisAlignedBB D_TOP_2 = BlockUtil.createBoundingBox(4, 12, 4, 12, 14, 12);
	
	protected static final AxisAlignedBB DNE_TOP_1 = BlockUtil.createBoundingBox(0, 14, 8, 8, 16, 16);
	protected static final AxisAlignedBB DNE_TOP_3 = BlockUtil.createBoundingBox(8, 10, 0, 16, 12, 8);
	protected static final AxisAlignedBB DNE_TOP_4 = BlockUtil.createBoundingBox(12, 8, -4, 20, 10, 4);
	
	protected static final AxisAlignedBB DSE_TOP_1 = BlockUtil.createBoundingBox(0, 14, 0, 8, 16, 8);
	protected static final AxisAlignedBB DSE_TOP_3 = BlockUtil.createBoundingBox(8, 10, 8, 16, 12, 16);
	protected static final AxisAlignedBB DSE_TOP_4 = BlockUtil.createBoundingBox(12, 8, 12, 20, 10, 20);
	
	protected static final AxisAlignedBB DSW_TOP_1 = BlockUtil.createBoundingBox(8, 14, 0, 16, 16, 8);
	protected static final AxisAlignedBB DSW_TOP_3 = BlockUtil.createBoundingBox(0, 10, 8, 8, 12, 16);
	protected static final AxisAlignedBB DSW_TOP_4 = BlockUtil.createBoundingBox(-4, 8, 12, 4, 10, 20);
	
	protected static final AxisAlignedBB DNW_TOP_1 = BlockUtil.createBoundingBox(8, 14, 8, 16, 16, 16);
	protected static final AxisAlignedBB DNW_TOP_3 = BlockUtil.createBoundingBox(0, 10, 0, 8, 12, 8);
	protected static final AxisAlignedBB DNW_TOP_4 = BlockUtil.createBoundingBox(-4, 8, -4, 4, 10, 4);
	
	protected static final AxisAlignedBB D_BOTTOM_2 = BlockUtil.createBoundingBox(4, 4, 4, 12, 6, 12);
	
	protected static final AxisAlignedBB DNE_BOTTOM_1 = BlockUtil.createBoundingBox(0, 6, 8, 8, 8, 16);
	protected static final AxisAlignedBB DNE_BOTTOM_3 = BlockUtil.createBoundingBox(8, 2, 0, 16, 4, 8);
	protected static final AxisAlignedBB DNE_BOTTOM_4 = BlockUtil.createBoundingBox(12, 0, -4, 20, 2, 4);
	
	protected static final AxisAlignedBB DSE_BOTTOM_1 = BlockUtil.createBoundingBox(0, 6, 0, 8, 8, 8);
	protected static final AxisAlignedBB DSE_BOTTOM_3 = BlockUtil.createBoundingBox(8, 2, 8, 16, 4, 16);
	protected static final AxisAlignedBB DSE_BOTTOM_4 = BlockUtil.createBoundingBox(12, 0, 12, 20, 2, 20);
	
	protected static final AxisAlignedBB DSW_BOTTOM_1 = BlockUtil.createBoundingBox(8, 6, 0, 16, 8, 8);
	protected static final AxisAlignedBB DSW_BOTTOM_3 = BlockUtil.createBoundingBox(0, 2, 8, 8, 4, 16);
	protected static final AxisAlignedBB DSW_BOTTOM_4 = BlockUtil.createBoundingBox(-4, 0, 12, 4, 2, 20);
	
	protected static final AxisAlignedBB DNW_BOTTOM_1 = BlockUtil.createBoundingBox(8, 6, 8, 16, 8, 16);
	protected static final AxisAlignedBB DNW_BOTTOM_3 = BlockUtil.createBoundingBox(0, 2, 0, 8, 4, 8);
	protected static final AxisAlignedBB DNW_BOTTOM_4 = BlockUtil.createBoundingBox(-4, 0, -4, 4, 2, 4);
	
	protected static final AxisAlignedBB U_TOP_2 = BlockUtil.createBoundingBox(4, 10, 4, 12, 12, 12);
	
	protected static final AxisAlignedBB UNE_TOP_1 = BlockUtil.createBoundingBox(0, 8, 8, 8, 10, 16);
	protected static final AxisAlignedBB UNE_TOP_3 = BlockUtil.createBoundingBox(8, 12, 0, 16, 14, 8);
	protected static final AxisAlignedBB UNE_TOP_4 = BlockUtil.createBoundingBox(12, 14, -4, 20, 16, 4);
	
	protected static final AxisAlignedBB USE_TOP_1 = BlockUtil.createBoundingBox(0, 8, 0, 8, 10, 8);
	protected static final AxisAlignedBB USE_TOP_3 = BlockUtil.createBoundingBox(8, 12, 8, 16, 14, 16);
	protected static final AxisAlignedBB USE_TOP_4 = BlockUtil.createBoundingBox(12, 14, 12, 20, 16, 20);
	
	protected static final AxisAlignedBB USW_TOP_1 = BlockUtil.createBoundingBox(8, 8, 0, 16, 10, 8);
	protected static final AxisAlignedBB USW_TOP_3 = BlockUtil.createBoundingBox(0, 12, 8, 8, 14, 16);
	protected static final AxisAlignedBB USW_TOP_4 = BlockUtil.createBoundingBox(-4, 14, 12, 4, 16, 20);

	protected static final AxisAlignedBB UNW_TOP_1 = BlockUtil.createBoundingBox(8, 8, 8, 16, 10, 16);
	protected static final AxisAlignedBB UNW_TOP_3 = BlockUtil.createBoundingBox(0, 12, 0, 8, 14, 8);
	protected static final AxisAlignedBB UNW_TOP_4 = BlockUtil.createBoundingBox(-4, 14, -4, 4, 16, 4);
	
	protected static final AxisAlignedBB U_BOTTOM_2 = BlockUtil.createBoundingBox(4, 2, 4, 12, 4, 12);
	
	protected static final AxisAlignedBB UNE_BOTTOM_1 = BlockUtil.createBoundingBox(0, 0, 8, 8, 2, 16);
	protected static final AxisAlignedBB UNE_BOTTOM_3 = BlockUtil.createBoundingBox(8, 4, 0, 16, 6, 8);
	protected static final AxisAlignedBB UNE_BOTTOM_4 = BlockUtil.createBoundingBox(12, 6, -4, 20, 8, 4);
	
	protected static final AxisAlignedBB USE_BOTTOM_1 = BlockUtil.createBoundingBox(0, 0, 0, 8, 2, 8);
	protected static final AxisAlignedBB USE_BOTTOM_3 = BlockUtil.createBoundingBox(8, 4, 8, 16, 6, 16);
	protected static final AxisAlignedBB USE_BOTTOM_4 = BlockUtil.createBoundingBox(12, 6, 12, 20, 8, 20);
	
	protected static final AxisAlignedBB USW_BOTTOM_1 = BlockUtil.createBoundingBox(8, 0, 0, 16, 2, 8);
	protected static final AxisAlignedBB USW_BOTTOM_3 = BlockUtil.createBoundingBox(0, 4, 8, 8, 6, 16);
	protected static final AxisAlignedBB USW_BOTTOM_4 = BlockUtil.createBoundingBox(-4, 6, 12, 4, 8, 20);

	protected static final AxisAlignedBB UNW_BOTTOM_1 = BlockUtil.createBoundingBox(8, 0, 8, 16, 2, 16);
	protected static final AxisAlignedBB UNW_BOTTOM_3 = BlockUtil.createBoundingBox(0, 4, 0, 8, 6, 8);
	protected static final AxisAlignedBB UNW_BOTTOM_4 = BlockUtil.createBoundingBox(-4, 6, -4, 4, 8, 4);
	
	public BlockPalmFronds(String name, boolean live)
	{
		super(name, Material.LEAVES, live ? MapColor.FOLIAGE : MapColor.SAND, SoundType.PLANT, 0F);
		this.setRenderLayer(BlockRenderLayer.CUTOUT_MIPPED);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(TOP, Boolean.valueOf(true)).withProperty(UPWARD, Boolean.valueOf(false)));
		this.setCreativeTab(MTabs.plant);
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
		boolean top = state.getValue(TOP).booleanValue();
		
		if(top)
			return BOUNDING_TOP;
		else
			return BOUNDING_BOTTOM;
    }
	
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean p_185477_7_)
    {
		boolean top = state.getValue(TOP).booleanValue();
		boolean upward = state.getValue(UPWARD).booleanValue();
		EnumFacing facing = state.getValue(FACING);
		
		if(upward)
		{
			if(top)
			{
				addCollisionBoxToList(pos, entityBox, collidingBoxes, U_TOP_2);
				
				if(facing == EnumFacing.NORTH)
				{
					addCollisionBoxToList(pos, entityBox, collidingBoxes, USE_TOP_1);
					addCollisionBoxToList(pos, entityBox, collidingBoxes, USE_TOP_3);
					addCollisionBoxToList(pos, entityBox, collidingBoxes, USE_TOP_4);
				}
				else if(facing == EnumFacing.EAST)
				{
					addCollisionBoxToList(pos, entityBox, collidingBoxes, USW_TOP_1);
					addCollisionBoxToList(pos, entityBox, collidingBoxes, USW_TOP_3);
					addCollisionBoxToList(pos, entityBox, collidingBoxes, USW_TOP_4);
				}
				else if(facing == EnumFacing.SOUTH)
				{
					addCollisionBoxToList(pos, entityBox, collidingBoxes, UNW_TOP_1);
					addCollisionBoxToList(pos, entityBox, collidingBoxes, UNW_TOP_3);
					addCollisionBoxToList(pos, entityBox, collidingBoxes, UNW_TOP_4);
				}
				else
				{
					addCollisionBoxToList(pos, entityBox, collidingBoxes, UNE_TOP_1);
					addCollisionBoxToList(pos, entityBox, collidingBoxes, UNE_TOP_3);
					addCollisionBoxToList(pos, entityBox, collidingBoxes, UNE_TOP_4);
				}
			}
			else
			{
				addCollisionBoxToList(pos, entityBox, collidingBoxes, U_BOTTOM_2);
				
				if(facing == EnumFacing.NORTH)
				{
					addCollisionBoxToList(pos, entityBox, collidingBoxes, USE_BOTTOM_1);
					addCollisionBoxToList(pos, entityBox, collidingBoxes, USE_BOTTOM_3);
					addCollisionBoxToList(pos, entityBox, collidingBoxes, USE_BOTTOM_4);
				}
				else if(facing == EnumFacing.EAST)
				{
					addCollisionBoxToList(pos, entityBox, collidingBoxes, USW_BOTTOM_1);
					addCollisionBoxToList(pos, entityBox, collidingBoxes, USW_BOTTOM_3);
					addCollisionBoxToList(pos, entityBox, collidingBoxes, USW_BOTTOM_4);
				}
				else if(facing == EnumFacing.SOUTH)
				{
					addCollisionBoxToList(pos, entityBox, collidingBoxes, UNW_BOTTOM_1);
					addCollisionBoxToList(pos, entityBox, collidingBoxes, UNW_BOTTOM_3);
					addCollisionBoxToList(pos, entityBox, collidingBoxes, UNW_BOTTOM_4);
				}
				else
				{
					addCollisionBoxToList(pos, entityBox, collidingBoxes, UNE_BOTTOM_1);
					addCollisionBoxToList(pos, entityBox, collidingBoxes, UNE_BOTTOM_3);
					addCollisionBoxToList(pos, entityBox, collidingBoxes, UNE_BOTTOM_4);
				}
			}
		}
		else
		{
			if(top)
			{
				addCollisionBoxToList(pos, entityBox, collidingBoxes, D_TOP_2);
				
				if(facing == EnumFacing.NORTH)
				{
					addCollisionBoxToList(pos, entityBox, collidingBoxes, DSE_TOP_1);
					addCollisionBoxToList(pos, entityBox, collidingBoxes, DSE_TOP_3);
					addCollisionBoxToList(pos, entityBox, collidingBoxes, DSE_TOP_4);
				}
				else if(facing == EnumFacing.EAST)
				{
					addCollisionBoxToList(pos, entityBox, collidingBoxes, DSW_TOP_1);
					addCollisionBoxToList(pos, entityBox, collidingBoxes, DSW_TOP_3);
					addCollisionBoxToList(pos, entityBox, collidingBoxes, DSW_TOP_4);
				}
				else if(facing == EnumFacing.SOUTH)
				{
					addCollisionBoxToList(pos, entityBox, collidingBoxes, DNW_TOP_1);
					addCollisionBoxToList(pos, entityBox, collidingBoxes, DNW_TOP_3);
					addCollisionBoxToList(pos, entityBox, collidingBoxes, DNW_TOP_4);
				}
				else
				{
					addCollisionBoxToList(pos, entityBox, collidingBoxes, DNE_TOP_1);
					addCollisionBoxToList(pos, entityBox, collidingBoxes, DNE_TOP_3);
					addCollisionBoxToList(pos, entityBox, collidingBoxes, DNE_TOP_4);
				}
			}
			else
			{
				addCollisionBoxToList(pos, entityBox, collidingBoxes, D_BOTTOM_2);
				
				if(facing == EnumFacing.NORTH)
				{
					addCollisionBoxToList(pos, entityBox, collidingBoxes, DSE_BOTTOM_1);
					addCollisionBoxToList(pos, entityBox, collidingBoxes, DSE_BOTTOM_3);
					addCollisionBoxToList(pos, entityBox, collidingBoxes, DSE_BOTTOM_4);
				}
				else if(facing == EnumFacing.EAST)
				{
					addCollisionBoxToList(pos, entityBox, collidingBoxes, DSW_BOTTOM_1);
					addCollisionBoxToList(pos, entityBox, collidingBoxes, DSW_BOTTOM_3);
					addCollisionBoxToList(pos, entityBox, collidingBoxes, DSW_BOTTOM_4);
				}
				else if(facing == EnumFacing.SOUTH)
				{
					addCollisionBoxToList(pos, entityBox, collidingBoxes, DNW_BOTTOM_1);
					addCollisionBoxToList(pos, entityBox, collidingBoxes, DNW_BOTTOM_3);
					addCollisionBoxToList(pos, entityBox, collidingBoxes, DNW_BOTTOM_4);
				}
				else
				{
					addCollisionBoxToList(pos, entityBox, collidingBoxes, DNE_BOTTOM_1);
					addCollisionBoxToList(pos, entityBox, collidingBoxes, DNE_BOTTOM_3);
					addCollisionBoxToList(pos, entityBox, collidingBoxes, DNE_BOTTOM_4);
				}
			}
		}
    }
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
	{
		boolean top = true;
		boolean upward = false;
		EnumFacing direction = placer.getHorizontalFacing();
		
		if(facing == EnumFacing.UP || facing == EnumFacing.DOWN)
		{
			if(facing == EnumFacing.UP)
			{
				top = false;
				upward = true;
			}
			
			if(hitX < 0.5F)
			{
				if(hitZ < 0.5F)
					direction = EnumFacing.NORTH;
				else
					direction = EnumFacing.WEST;
			}
			else
			{
				if(hitZ < 0.5F)
					direction = EnumFacing.EAST;
				else
					direction = EnumFacing.SOUTH;
			}
		}
		else
		{
			if(hitY < 0.5F)
			{
				top = false;
				
				if(hitY < 0.25F)
					upward = true;
			}
			else if(hitY < 0.75F)
				upward = true;
			
			if(facing == EnumFacing.NORTH)
			{
				if(hitX < 0.5F)
					direction = EnumFacing.WEST;
				else
					direction = EnumFacing.SOUTH;
			}
			else if(facing == EnumFacing.EAST)
			{
				if(hitZ < 0.5F)
					direction = EnumFacing.NORTH;
				else
					direction = EnumFacing.WEST;
			}
			else if(facing == EnumFacing.SOUTH)
			{
				if(hitX < 0.5F)
					direction = EnumFacing.NORTH;
				else
					direction = EnumFacing.EAST;
			}
			else
			{
				if(hitZ < 0.5F)
					direction = EnumFacing.EAST;
				else
					direction = EnumFacing.SOUTH;
			}
		}
		
		return this.getDefaultState().withProperty(FACING, direction).withProperty(TOP, top).withProperty(UPWARD, upward);
	}
	
	public IBlockState getStateFromMeta(int meta)
	{
		EnumFacing facing = EnumFacing.NORTH;
		boolean top = false;
		boolean upward = false;
		int moddedMeta = meta;
		
		if(meta >= 8)
		{
			upward = true;
			moddedMeta -= 8;
		}
		if(moddedMeta >= 4)
		{
			top = true;
			moddedMeta -= 4;
		}
		
		if(moddedMeta == 1)
			facing = EnumFacing.SOUTH;
		else if(moddedMeta == 2)
			facing = EnumFacing.WEST;
		else if(moddedMeta == 3)
			facing = EnumFacing.EAST;
		
		return this.getDefaultState().withProperty(FACING, facing).withProperty(TOP, top).withProperty(UPWARD, upward);
    }

    public int getMetaFromState(IBlockState state)
    {
		EnumFacing facing = state.getValue(FACING);
		boolean top = state.getValue(TOP).booleanValue();
		boolean upward = state.getValue(UPWARD).booleanValue();
		int meta = 0;
		
		if(upward)
			meta += 8;
		if(top)
			meta += 4;
		if(facing == EnumFacing.SOUTH)
			meta += 1;
		else if(facing == EnumFacing.WEST)
			meta += 2;
		else if(facing == EnumFacing.EAST)
			meta += 3;
		
		return meta;
    }

    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }
    
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }
    
    @Override
    protected BlockStateContainer createBlockState()
    {
    	return new BlockStateContainer(this, new IProperty[] {TOP, UPWARD, FACING});
    }

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos)
	{
		return true;
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
	{
		return NonNullList.withSize(1, new ItemStack(this));
	}
}
