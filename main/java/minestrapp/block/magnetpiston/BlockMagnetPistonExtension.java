package minestrapp.block.magnetpiston;

import minestrapp.MBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.BlockPistonExtension;
import net.minecraft.block.BlockPistonExtension.EnumPistonType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockMagnetPistonExtension extends BlockPistonExtension
//Credit to crazysnailboy for all code.
{
	public BlockMagnetPistonExtension()
	{
		super();
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(TYPE, EnumPistonType.DEFAULT).withProperty(SHORT, false));
	}


	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player)
	{
		if (player.capabilities.isCreativeMode)
		{
			BlockPos blockpos = pos.offset(state.getValue(FACING).getOpposite());
			Block block = world.getBlockState(blockpos).getBlock();
			if (block instanceof net.minecraft.block.BlockPistonBase)
			{
				world.setBlockToAir(blockpos);
			}
		}
		super.onBlockHarvested(world, pos, state, player);
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state)
	{
		super.breakBlock(world, pos, state);
		EnumFacing enumfacing = state.getValue(FACING).getOpposite();
		pos = pos.offset(enumfacing);
		IBlockState iblockstate = world.getBlockState(pos);

		if (iblockstate.getBlock() instanceof net.minecraft.block.BlockPistonBase && iblockstate.getValue(BlockPistonBase.EXTENDED))
		{
			iblockstate.getBlock().dropBlockAsItem(world, pos, iblockstate, 0);
			world.setBlockToAir(pos);
		}
	}

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos)
	{
		EnumFacing enumfacing = state.getValue(FACING);
		BlockPos blockpos = pos.offset(enumfacing.getOpposite());
		IBlockState iblockstate = world.getBlockState(blockpos);

		if (!(iblockstate.getBlock() instanceof net.minecraft.block.BlockPistonBase))
		{
			world.setBlockToAir(pos);
		}
		else
		{
			iblockstate.neighborChanged(world, blockpos, block, fromPos);
		}
	}

	@Override
	public ItemStack getItem(World world, BlockPos pos, IBlockState state)
	{
		return new ItemStack(MBlocks.magnet_piston_1);
	}
}
