package minestrapp.block.magnetpiston;

import javax.annotation.Nullable;

import net.minecraft.block.BlockPistonExtension.EnumPistonType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityPiston;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPistonMoving extends net.minecraft.block.BlockPistonMoving
//Credit to crazysnailboy for all code.
{
	public BlockPistonMoving()
	{
		super();
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(TYPE, EnumPistonType.DEFAULT));
		this.setHardness(-1.0F);
	}


	public static TileEntity createTilePiston(IBlockState state, EnumFacing facing, boolean extending, boolean shouldHeadBeRendered)
	{
		return new TileEntityPiston(state, facing, extending, shouldHeadBeRendered);
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state)
	{
		TileEntity tileentity = world.getTileEntity(pos);
		if (tileentity instanceof TileEntityPiston)
		{
			((TileEntityPiston)tileentity).clearPistonTileEntity();
		}
		else
		{
			super.breakBlock(world, pos, state);
		}
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state)
	{
		BlockPos blockpos = pos.offset(state.getValue(FACING).getOpposite());
		IBlockState iblockstate = world.getBlockState(blockpos);

		if (iblockstate.getBlock() instanceof net.minecraft.block.BlockPistonBase && (iblockstate.getValue(BlockPistonBase.EXTENDED)))
		{
			world.setBlockToAir(blockpos);
		}
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

	@Nullable
	private TileEntityPiston getTilePistonAt(IBlockAccess world, BlockPos pos)
	{
		TileEntity tileentity = world.getTileEntity(pos);
		return tileentity instanceof TileEntityPiston ? (TileEntityPiston)tileentity : null;
	}

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        TileEntityPiston tileentitypiston = this.getTilePistonAt(world, pos);
        if (tileentitypiston != null)
        {
            IBlockState pushed = tileentitypiston.getPistonState();
            drops.addAll(pushed.getBlock().getDrops(world, pos, pushed, fortune));
        }
    }
}
