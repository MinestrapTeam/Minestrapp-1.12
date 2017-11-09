package minestrapp.block.magnetpiston;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import minestrapp.tileentity.TileEntityMagnetPiston;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonMoving;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMagnetPistonMoving extends BlockPistonMoving
//Credit to crazysnailboy for all code.
{

//	public static final PropertyDirection FACING = BlockMagnetPistonExtension.FACING;
//	public static final PropertyEnum<net.minecraft.block.BlockPistonExtension.EnumPistonType> TYPE = BlockMagnetPistonExtension.TYPE;

	public BlockMagnetPistonMoving()
	{
		super();
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(TYPE, net.minecraft.block.BlockPistonExtension.EnumPistonType.DEFAULT));
		this.setHardness(-1.0F);
	}

	@Override
	@Nullable
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return null;
	}

	public static TileEntity createTilePiston(IBlockState state, EnumFacing facing, boolean extending, boolean shouldHeadBeRendered)
	{
		return new TileEntityMagnetPiston(state, facing, extending, shouldHeadBeRendered);
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state)
	{
		TileEntity tileentity = world.getTileEntity(pos);

		if (tileentity instanceof TileEntityMagnetPiston)
		{
			((TileEntityMagnetPiston)tileentity).clearPistonTileEntity();
		}
		else
		{
			super.breakBlock(world, pos, state);
		}
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos)
	{
		return false;
	}

	@Override
	public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side)
	{
		return false;
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state)
	{
		BlockPos blockpos = pos.offset(((EnumFacing)state.getValue(FACING)).getOpposite());
		IBlockState iblockstate = world.getBlockState(blockpos);

		if (iblockstate.getBlock() instanceof BlockMagnetPistonBase && ((Boolean)iblockstate.getValue(BlockMagnetPistonBase.EXTENDED)).booleanValue())
		{
			world.setBlockToAir(blockpos);
		}
	}

	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote && world.getTileEntity(pos) == null)
		{
			world.setBlockToAir(pos);
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public Item getItemDropped(IBlockState state, Random random, int fortune)
	{
		return Items.AIR;
	}

	@Override
	public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float chance, int fortune)
	{
//        if (false && !world.isRemote) // Forge: Noop this out
//        {
//            TileEntityMagnetPiston TileEntityMagnetPiston = this.getTilePistonAt(world, pos);
//
//            if (TileEntityMagnetPiston != null)
//            {
//                IBlockState iblockstate = TileEntityMagnetPiston.getPistonState();
//                iblockstate.getBlock().dropBlockAsItem(world, pos, iblockstate, 0);
//            }
//        }
		super.dropBlockAsItemWithChance(world, pos, state, 1, fortune); // mimic vanilla behavior from above and ignore chance
	}

	@Override
	@Nullable
	public RayTraceResult collisionRayTrace(IBlockState blockState, World world, BlockPos pos, Vec3d start, Vec3d end)
	{
		return null;
	}

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos)
	{
		if (!world.isRemote)
		{
			world.getTileEntity(pos);
		}
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		TileEntityMagnetPiston TileEntityMagnetPiston = this.getTilePistonAt(world, pos);
		return TileEntityMagnetPiston == null ? null : TileEntityMagnetPiston.getAABB(world, pos);
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entity, boolean p_185477_7_)
	{
		TileEntityMagnetPiston TileEntityMagnetPiston = this.getTilePistonAt(world, pos);
		if (TileEntityMagnetPiston != null)
		{
			TileEntityMagnetPiston.addCollissionAABBs(world, pos, entityBox, collidingBoxes, entity);
		}
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		TileEntityMagnetPiston TileEntityMagnetPiston = this.getTilePistonAt(source, pos);
		return TileEntityMagnetPiston != null ? TileEntityMagnetPiston.getAABB(source, pos) : FULL_BLOCK_AABB;
	}

	@Nullable
	private TileEntityMagnetPiston getTilePistonAt(IBlockAccess world, BlockPos pos)
	{
		TileEntity tileentity = world.getTileEntity(pos);
		return tileentity instanceof TileEntityMagnetPiston ? (TileEntityMagnetPiston)tileentity : null;
	}

	@Override
	public ItemStack getItem(World world, BlockPos pos, IBlockState state)
	{
		return ItemStack.EMPTY;
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(FACING, BlockMagnetPistonExtension.getFacing(meta)).withProperty(TYPE, (meta & 8) > 0 ? net.minecraft.block.BlockPistonExtension.EnumPistonType.STICKY : net.minecraft.block.BlockPistonExtension.EnumPistonType.DEFAULT);
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rotation)
	{
		return state.withProperty(FACING, rotation.rotate((EnumFacing)state.getValue(FACING)));
	}

	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirror)
	{
		return state.withRotation(mirror.toRotation((EnumFacing)state.getValue(FACING)));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		int i = 0;
		i = i | ((EnumFacing)state.getValue(FACING)).getIndex();

		if (state.getValue(TYPE) == net.minecraft.block.BlockPistonExtension.EnumPistonType.STICKY)
		{
			i |= 8;
		}

		return i;
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { FACING, TYPE });
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
	{
		TileEntityMagnetPiston TileEntityMagnetPiston = this.getTilePistonAt(world, pos);
		if (TileEntityMagnetPiston != null)
		{
			IBlockState pushed = TileEntityMagnetPiston.getPistonState();
			drops.addAll(pushed.getBlock().getDrops(world, pos, pushed, fortune)); // use the old method until it gets removed, for backward compatibility
		}
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing)
	{
		return BlockFaceShape.UNDEFINED;
	}
}
