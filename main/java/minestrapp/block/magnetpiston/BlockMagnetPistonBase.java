package minestrapp.block.magnetpiston;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import minestrapp.MBlocks;
import minestrapp.block.helper.BlockMagnetPistonStructureHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonExtension.EnumPistonType;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMagnetPistonBase extends net.minecraft.block.BlockPistonBase
//Credit to crazysnailboy for all code.
{
	public static final PropertyInteger STRENGTH = PropertyInteger.create("strength", 1, 4);
	private final boolean isSticky;
	private int strength;


	public BlockMagnetPistonBase(int strength)
	{
		super(false);
		this.isSticky = false;
		this.strength = strength;
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(EXTENDED, false).withProperty(STRENGTH, this.strength));
		this.setSoundType(SoundType.STONE);
		this.setHardness(0.5F);
		this.setCreativeTab(this.strength == 1 ? CreativeTabs.REDSTONE : null);
	}


	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		world.setBlockState(pos, state.withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer)), 2);
		if (!world.isRemote)
		{
			this.checkForMove(world, pos, state);
		}
	}

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos)
	{
		if (!world.isRemote)
		{
			this.checkForMove(world, pos, state);
		}
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state)
	{
		if (!world.isRemote && world.getTileEntity(pos) == null)
		{
			this.checkForMove(world, pos, state);
		}
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		return this.getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer)).withProperty(EXTENDED, false).withProperty(STRENGTH, 1);
	}

	private void checkForMove(World world, BlockPos pos, IBlockState state)
	{
		EnumFacing facing = state.getValue(FACING);
		boolean shouldBeExtended = this.shouldBeExtended(world, pos, facing);

		if (shouldBeExtended && !state.getValue(EXTENDED))
		{
			if ((new BlockMagnetPistonStructureHelper(world, pos, facing, true, this.strength)).canMove())
			{
				world.addBlockEvent(pos, this, 0, facing.getIndex());
			}
		}
		else if (!shouldBeExtended && state.getValue(EXTENDED))
		{
			world.addBlockEvent(pos, this, 1, facing.getIndex());
		}
	}

	private boolean shouldBeExtended(World world, BlockPos pos, EnumFacing facing)
	{
		for (EnumFacing enumfacing : EnumFacing.values())
		{
			if (enumfacing != facing && world.isSidePowered(pos.offset(enumfacing), enumfacing))
			{
				return true;
			}
		}
		if (world.isSidePowered(pos, EnumFacing.DOWN))
		{
			return true;
		}
		else
		{
			BlockPos blockpos = pos.up();
			for (EnumFacing enumfacing1 : EnumFacing.values())
			{
				if (enumfacing1 != EnumFacing.DOWN && world.isSidePowered(blockpos.offset(enumfacing1), enumfacing1))
				{
					return true;
				}
			}
			return false;
		}
	}

	@Override
	public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int id, int param)
	{
		EnumFacing enumfacing = state.getValue(FACING);

		if (!worldIn.isRemote)
		{
			boolean shouldBeExtended = this.shouldBeExtended(worldIn, pos, enumfacing);
			if (shouldBeExtended && id == 1)
			{
				worldIn.setBlockState(pos, state.withProperty(EXTENDED, true), 2);
				return false;
			}
			if (!shouldBeExtended && id == 0)
			{
				return false;
			}
		}

		if (id == 0)
		{
			if (!this.doMove(worldIn, pos, enumfacing, true))
			{
				return false;
			}

			worldIn.setBlockState(pos, state.withProperty(EXTENDED, Boolean.valueOf(true)), 3);
			worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_PISTON_EXTEND, SoundCategory.BLOCKS, 0.5F, worldIn.rand.nextFloat() * 0.25F + 0.6F);
		}
		else if (id == 1)
		{
			TileEntity tileentity1 = worldIn.getTileEntity(pos.offset(enumfacing));
			if (tileentity1 instanceof net.minecraft.tileentity.TileEntityPiston)
			{
				((net.minecraft.tileentity.TileEntityPiston)tileentity1).clearPistonTileEntity();
			}

			worldIn.setBlockState(pos, MBlocks.magnet_piston_extension.getDefaultState().withProperty(BlockMagnetPistonMoving.FACING, enumfacing).withProperty(BlockMagnetPistonMoving.TYPE, this.isSticky ? EnumPistonType.STICKY : EnumPistonType.DEFAULT), 3);
			worldIn.setTileEntity(pos, BlockMagnetPistonMoving.createTilePiston(this.getStateFromMeta(param), enumfacing, false, true));
			
			BlockPos blockpos = pos.add(enumfacing.getFrontOffsetX() * 2, enumfacing.getFrontOffsetY() * 2, enumfacing.getFrontOffsetZ() * 2);
			IBlockState iblockstate = worldIn.getBlockState(blockpos);
			Block block = iblockstate.getBlock();
			boolean flag1 = false;

			if (block instanceof net.minecraft.block.BlockPistonMoving)
			{
				TileEntity tileentity = worldIn.getTileEntity(blockpos);
				if (tileentity instanceof net.minecraft.tileentity.TileEntityPiston)
				{
					net.minecraft.tileentity.TileEntityPiston tileentitypiston = (net.minecraft.tileentity.TileEntityPiston)tileentity;
					if (tileentitypiston.getFacing() == enumfacing && tileentitypiston.isExtending())
					{
						tileentitypiston.clearPistonTileEntity();
						flag1 = true;
					}
				}
			}

			if (!flag1 && !iblockstate.getBlock().isAir(iblockstate, worldIn, blockpos) && canPush(iblockstate, worldIn, blockpos, enumfacing.getOpposite(), false, enumfacing) && (iblockstate.getMobilityFlag() == EnumPushReaction.NORMAL || block instanceof net.minecraft.block.BlockPistonBase))
			{
				this.doMove(worldIn, pos, enumfacing, false);
			}

			worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_PISTON_CONTRACT, SoundCategory.BLOCKS, 0.5F, worldIn.rand.nextFloat() * 0.15F + 0.6F);
		}

		return true;
	}

	@SuppressWarnings("incomplete-switch")
	public static boolean canPush(IBlockState state, World world, BlockPos pos, EnumFacing facing, boolean destroyBlocks, EnumFacing p_185646_5_)
	{
		Block block = state.getBlock();

		if (block == Blocks.OBSIDIAN)
		{
			return false;
		}
		else if (!world.getWorldBorder().contains(pos))
		{
			return false;
		}
		else if (pos.getY() >= 0 && (facing != EnumFacing.DOWN || pos.getY() != 0))
		{
			if (pos.getY() <= world.getHeight() - 1 && (facing != EnumFacing.UP || pos.getY() != world.getHeight() - 1))
			{
				if (!(block instanceof net.minecraft.block.BlockPistonBase))
				{
					if (state.getBlockHardness(world, pos) == -1.0F)
					{
						return false;
					}

					switch (state.getMobilityFlag())
					{
						case BLOCK:
							return false;
						case DESTROY:
							return destroyBlocks;
						case PUSH_ONLY:
							return facing == p_185646_5_;
					}
				}
				else if (state.getValue(EXTENDED))
				{
					return false;
				}

				return !block.hasTileEntity(state);
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	private boolean doMove(World world, BlockPos pos, EnumFacing direction, boolean extending)
	{
		if (!extending)
		{
			world.setBlockToAir(pos.offset(direction));
		}

		BlockMagnetPistonStructureHelper blockpistonstructurehelper = new BlockMagnetPistonStructureHelper(world, pos, direction, extending, this.strength);

		if (!blockpistonstructurehelper.canMove())
		{
			return false;
		}
		else
		{
			List<BlockPos> list = blockpistonstructurehelper.getBlocksToMove();
			List<IBlockState> list1 = Lists.<IBlockState>newArrayList();

			for (int i = 0; i < list.size(); ++i)
			{
				BlockPos blockpos = list.get(i);
				list1.add(world.getBlockState(blockpos).getActualState(world, blockpos));
			}

			List<BlockPos> list2 = blockpistonstructurehelper.getBlocksToDestroy();
			int k = list.size() + list2.size();
			IBlockState[] aiblockstate = new IBlockState[k];
			EnumFacing enumfacing = extending ? direction : direction.getOpposite();

			for (int j = list2.size() - 1; j >= 0; --j)
			{
				BlockPos blockpos1 = list2.get(j);
				IBlockState iblockstate = world.getBlockState(blockpos1);
				// Forge: With our change to how snowballs are dropped this needs to disallow to mimic vanilla behavior.
				float chance = iblockstate.getBlock() instanceof BlockSnow ? -1.0f : 1.0f;
				iblockstate.getBlock().dropBlockAsItemWithChance(world, blockpos1, iblockstate, chance, 0);
				world.setBlockState(blockpos1, Blocks.AIR.getDefaultState(), 4);
				--k;
				aiblockstate[k] = iblockstate;
			}

			for (int l = list.size() - 1; l >= 0; --l)
			{
				BlockPos blockpos3 = list.get(l);
				IBlockState iblockstate2 = world.getBlockState(blockpos3);
				world.setBlockState(blockpos3, Blocks.AIR.getDefaultState(), 2);
				blockpos3 = blockpos3.offset(enumfacing);
				world.setBlockState(blockpos3, MBlocks.magnet_piston_extension.getDefaultState().withProperty(FACING, direction), 4);
				world.setTileEntity(blockpos3, BlockMagnetPistonMoving.createTilePiston(list1.get(l), direction, extending, false));
				--k;
				aiblockstate[k] = iblockstate2;
			}

			BlockPos blockpos2 = pos.offset(direction);

			if (extending)
			{
				IBlockState iblockstate3 = MBlocks.magnet_piston_head.getDefaultState().withProperty(BlockMagnetPistonExtension.FACING, direction).withProperty(BlockMagnetPistonExtension.TYPE, EnumPistonType.DEFAULT);
				IBlockState iblockstate1 = MBlocks.magnet_piston_extension.getDefaultState().withProperty(BlockMagnetPistonMoving.FACING, direction).withProperty(BlockMagnetPistonMoving.TYPE, EnumPistonType.DEFAULT);
				world.setBlockState(blockpos2, iblockstate1, 4);
				world.setTileEntity(blockpos2, BlockMagnetPistonMoving.createTilePiston(iblockstate3, direction, true, true));
			}

			for (int i1 = list2.size() - 1; i1 >= 0; --i1)
			{
				world.notifyNeighborsOfStateChange(list2.get(i1), aiblockstate[k++].getBlock(), false);
			}

			for (int j1 = list.size() - 1; j1 >= 0; --j1)
			{
				world.notifyNeighborsOfStateChange(list.get(j1), aiblockstate[k++].getBlock(), false);
			}

			if (extending)
			{
				world.notifyNeighborsOfStateChange(blockpos2, MBlocks.magnet_piston_head, false);
			}

			return true;
		}
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { FACING, EXTENDED, STRENGTH });
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return world.getBlockState(pos).withProperty(STRENGTH, this.strength);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (facing != state.getValue(FACING))
		{
			int newStrengh = (this.strength == 4 ? 1 : this.strength + 1);
			IBlockState newState;
			switch (newStrengh)
			{
				case 1:
					newState = MBlocks.magnet_piston_1.getDefaultState();
					break;
				case 2:
					newState = MBlocks.magnet_piston_2.getDefaultState();
					break;
				case 3:
					newState = MBlocks.magnet_piston_3.getDefaultState();
					break;
				case 4:
					newState = MBlocks.magnet_piston_4.getDefaultState();
					break;
				default:
					return false;
			}

			newState = newState.withProperty(FACING, state.getValue(FACING)).withProperty(EXTENDED, state.getValue(EXTENDED)).withProperty(STRENGTH, newStrengh);
			world.setBlockState(pos, newState);
			return true;
		}
		else return false;
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
	{
		return new ItemStack(MBlocks.magnet_piston_1);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(MBlocks.magnet_piston_1);
	}
	
	public EnumPushReaction getMobilityFlag(IBlockState state)
    {
		if(state.getValue(EXTENDED).booleanValue() == false)
			return EnumPushReaction.NORMAL;
		else
			return EnumPushReaction.BLOCK;
    }
}
