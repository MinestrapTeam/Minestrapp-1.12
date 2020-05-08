package minestrapp.worldgen;

import java.util.Random;

import minestrapp.MBlocks;
import minestrapp.block.BlockGildedStone;
import minestrapp.block.util.BlockPanelBase;
import net.minecraft.block.BlockRedstoneRepeater;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockTripWireHook;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class MGenAdamantiumVault extends WorldGenerator
{
	@Override
	public boolean generate(World world, Random rand, BlockPos pos)
	{
		IBlockState bricks = MBlocks.gilded_stone.getDefaultState().withProperty(BlockGildedStone.VARIANT, BlockGildedStone.EnumType.BRICKS);
		IBlockState tiles = MBlocks.gilded_stone.getDefaultState().withProperty(BlockGildedStone.VARIANT, BlockGildedStone.EnumType.TILES);
		IBlockState capital = MBlocks.gilded_stone.getDefaultState().withProperty(BlockGildedStone.VARIANT, BlockGildedStone.EnumType.PILLAR_TOP);
		IBlockState plinth = MBlocks.gilded_stone.getDefaultState().withProperty(BlockGildedStone.VARIANT, BlockGildedStone.EnumType.PILLAR_BOTTOM);
		IBlockState pillar = MBlocks.gilded_stone.getDefaultState().withProperty(BlockGildedStone.VARIANT, BlockGildedStone.EnumType.LINES_Y);
		IBlockState panel = MBlocks.gilded_brick_panel.getDefaultState();
		IBlockState stairsBottom = MBlocks.gilded_brick_stairs.getDefaultState().withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM);
		IBlockState stairsTop = MBlocks.gilded_brick_stairs.getDefaultState().withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP);
		IBlockState lava = Blocks.LAVA.getDefaultState();
		IBlockState trap = MBlocks.block_dimensium_stabilized_inactive.getDefaultState();
		
		for(int y = -5 ; y < 15 ; y++)
		{
			for(int x = -10 ; x < 11 ; x++)
			{
				for(int z = -10 ; z < 11 ; z++)
				{
					int chance = 0;
					if(Math.abs(x) > 9 || Math.abs(z) > 9 || Math.abs(y-5) > 10)
						chance++;
					if(Math.abs(x) > 8 || Math.abs(z) > 8 || Math.abs(y-5) > 9)
						chance++;
					if(Math.abs(x) > 7 || Math.abs(z) > 7 || Math.abs(y-5) > 8)
						chance++;
					
					IBlockState state = bricks;
					if(y < 2 || y > 9)
						state = tiles;
					
					if(rand.nextInt(10) > chance - 1)
						world.setBlockState(pos.offset(EnumFacing.NORTH, z).offset(EnumFacing.UP, y).offset(EnumFacing.EAST, x), state);
				}
			}
		}
		
		for(EnumFacing facing : EnumFacing.HORIZONTALS)
		{
			EnumFacing right = facing.rotateY();
			
			world.setBlockState(pos.offset(facing), tiles);
			world.setBlockState(pos.offset(facing).offset(right), tiles);
			world.setBlockState(pos.offset(facing).offset(right, 2), tiles);
			world.setBlockState(pos.offset(facing).offset(right, 3), lava);
			world.setBlockState(pos.offset(facing).offset(right, 4), lava);
			world.setBlockState(pos.offset(facing).offset(right, 5), tiles);
			world.setBlockState(pos.offset(facing, 2), tiles);
			world.setBlockState(pos.offset(facing, 2).offset(right), tiles);
			world.setBlockState(pos.offset(facing, 2).offset(right, 2), tiles);
			world.setBlockState(pos.offset(facing, 2).offset(right, 3), lava);
			world.setBlockState(pos.offset(facing, 2).offset(right, 4), lava);
			world.setBlockState(pos.offset(facing, 2).offset(right, 5), tiles);
			world.setBlockState(pos.offset(facing, 3), lava);
			world.setBlockState(pos.offset(facing, 3).offset(right), lava);
			world.setBlockState(pos.offset(facing, 3).offset(right, 2), lava);
			world.setBlockState(pos.offset(facing, 3).offset(right, 3), lava);
			world.setBlockState(pos.offset(facing, 3).offset(right, 4), lava);
			world.setBlockState(pos.offset(facing, 3).offset(right, 5), tiles);
			world.setBlockState(pos.offset(facing, 4), lava);
			world.setBlockState(pos.offset(facing, 4).offset(right), lava);
			world.setBlockState(pos.offset(facing, 4).offset(right, 2), lava);
			world.setBlockState(pos.offset(facing, 4).offset(right, 3), lava);
			world.setBlockState(pos.offset(facing, 4).offset(right, 4), tiles);
			world.setBlockState(pos.offset(facing, 4).offset(right, 5), tiles);
			world.setBlockState(pos.offset(facing, 5), tiles);
			world.setBlockState(pos.offset(facing, 5).offset(right), tiles);
			world.setBlockState(pos.offset(facing, 5).offset(right, 2), tiles);
			world.setBlockState(pos.offset(facing, 5).offset(right, 3), tiles);
			world.setBlockState(pos.offset(facing, 5).offset(right, 4), tiles);
			world.setBlockState(pos.offset(facing, 5).offset(right, 5), tiles);
			
			world.setBlockState(pos.up().offset(facing), Blocks.UNPOWERED_REPEATER.getDefaultState().withProperty(BlockRedstoneRepeater.FACING, facing.getOpposite()));
			world.setBlockState(pos.up().offset(facing).offset(right), tiles);
			world.setBlockState(pos.up().offset(facing).offset(right, 2), tiles);
			world.setBlockState(pos.up().offset(facing).offset(right, 3), trap);
			world.setBlockState(pos.up().offset(facing).offset(right, 4), trap);
			world.setBlockState(pos.up().offset(facing).offset(right, 5), tiles);
			world.setBlockState(pos.up().offset(facing, 2), Blocks.REDSTONE_WIRE.getDefaultState());
			world.setBlockState(pos.up().offset(facing, 2).offset(right), tiles);
			world.setBlockState(pos.up().offset(facing, 2).offset(right, 2), tiles);
			world.setBlockState(pos.up().offset(facing, 2).offset(right, 3), trap);
			world.setBlockState(pos.up().offset(facing, 2).offset(right, 4), trap);
			world.setBlockState(pos.up().offset(facing, 2).offset(right, 5), tiles);
			world.setBlockState(pos.up().offset(facing, 3), trap);
			world.setBlockState(pos.up().offset(facing, 3).offset(right), trap);
			world.setBlockState(pos.up().offset(facing, 3).offset(right, 2), trap);
			world.setBlockState(pos.up().offset(facing, 3).offset(right, 3), trap);
			world.setBlockState(pos.up().offset(facing, 3).offset(right, 4), trap);
			world.setBlockState(pos.up().offset(facing, 3).offset(right, 5), tiles);
			world.setBlockState(pos.up().offset(facing, 4), trap);
			world.setBlockState(pos.up().offset(facing, 4).offset(right), trap);
			world.setBlockState(pos.up().offset(facing, 4).offset(right, 2), trap);
			world.setBlockState(pos.up().offset(facing, 4).offset(right, 3), trap);
			world.setBlockState(pos.up().offset(facing, 4).offset(right, 4), tiles);
			world.setBlockState(pos.up().offset(facing, 4).offset(right, 5), tiles);
			world.setBlockState(pos.up().offset(facing, 5), tiles);
			world.setBlockState(pos.up().offset(facing, 5).offset(right), tiles);
			world.setBlockState(pos.up().offset(facing, 5).offset(right, 2), tiles);
			world.setBlockState(pos.up().offset(facing, 5).offset(right, 3), tiles);
			world.setBlockState(pos.up().offset(facing, 5).offset(right, 4), tiles);
			world.setBlockState(pos.up().offset(facing, 5).offset(right, 5), tiles);
			
			world.setBlockState(pos.up(2).offset(facing), capital);
			world.setBlockState(pos.up(2).offset(facing).offset(right), stairsBottom.withProperty(BlockStairs.FACING, facing.rotateYCCW()));
			world.setBlockState(pos.up(2).offset(facing).offset(right, 2), stairsBottom.withProperty(BlockStairs.FACING, facing.getOpposite()));
			world.setBlockToAir(pos.up(2).offset(facing).offset(right, 3));
			world.setBlockToAir(pos.up(2).offset(facing).offset(right, 4));
			world.setBlockState(pos.up(2).offset(facing).offset(right, 5), stairsBottom.withProperty(BlockStairs.FACING, facing.rotateY()));
			world.setBlockState(pos.up(2).offset(facing, 2), stairsBottom.withProperty(BlockStairs.FACING, facing.getOpposite()));
			world.setBlockState(pos.up(2).offset(facing, 2).offset(right), stairsBottom.withProperty(BlockStairs.FACING, facing.getOpposite()));
			world.setBlockState(pos.up(2).offset(facing, 2).offset(right, 2), plinth);
			world.setBlockToAir(pos.up(2).offset(facing, 2).offset(right, 3));
			world.setBlockToAir(pos.up(2).offset(facing, 2).offset(right, 4));
			world.setBlockState(pos.up(2).offset(facing, 2).offset(right, 5), stairsBottom.withProperty(BlockStairs.FACING, facing.rotateY()));
			world.setBlockToAir(pos.up(2).offset(facing, 3));
			world.setBlockToAir(pos.up(2).offset(facing, 3).offset(right));
			world.setBlockToAir(pos.up(2).offset(facing, 3).offset(right, 2));
			world.setBlockToAir(pos.up(2).offset(facing, 3).offset(right, 3));
			world.setBlockToAir(pos.up(2).offset(facing, 3).offset(right, 4));
			world.setBlockState(pos.up(2).offset(facing, 3).offset(right, 5), stairsBottom.withProperty(BlockStairs.FACING, facing.rotateY()));
			world.setBlockToAir(pos.up(2).offset(facing, 4));
			world.setBlockToAir(pos.up(2).offset(facing, 4).offset(right));
			world.setBlockToAir(pos.up(2).offset(facing, 4).offset(right, 2));
			world.setBlockToAir(pos.up(2).offset(facing, 4).offset(right, 3));
			world.setBlockState(pos.up(2).offset(facing, 4).offset(right, 4), stairsBottom.withProperty(BlockStairs.FACING, facing.rotateY()));
			world.setBlockState(pos.up(2).offset(facing, 4).offset(right, 5), stairsBottom.withProperty(BlockStairs.FACING, facing));
			world.setBlockState(pos.up(2).offset(facing, 5), stairsBottom.withProperty(BlockStairs.FACING, facing));
			world.setBlockState(pos.up(2).offset(facing, 5).offset(right), stairsBottom.withProperty(BlockStairs.FACING, facing));
			world.setBlockState(pos.up(2).offset(facing, 5).offset(right, 2), stairsBottom.withProperty(BlockStairs.FACING, facing));
			world.setBlockState(pos.up(2).offset(facing, 5).offset(right, 3), stairsBottom.withProperty(BlockStairs.FACING, facing));
			world.setBlockState(pos.up(2).offset(facing, 5).offset(right, 4), stairsBottom.withProperty(BlockStairs.FACING, facing));
			world.setBlockState(pos.up(2).offset(facing, 5).offset(right, 5), bricks);
			
			world.setBlockState(pos.up(3).offset(facing), Blocks.TRIPWIRE_HOOK.getDefaultState().withProperty(BlockTripWireHook.FACING, facing));
			world.setBlockToAir(pos.up(3).offset(facing).offset(right));
			world.setBlockToAir(pos.up(3).offset(facing).offset(right, 2));
			world.setBlockToAir(pos.up(3).offset(facing).offset(right, 3));
			world.setBlockToAir(pos.up(3).offset(facing).offset(right, 4));
			world.setBlockState(pos.up(3).offset(facing).offset(right, 5), panel.withProperty(BlockPanelBase.FACING, facing.rotateY()));
			world.setBlockState(pos.up(3).offset(facing, 2), Blocks.TRIPWIRE.getDefaultState());
			world.setBlockToAir(pos.up(3).offset(facing, 2).offset(right));
			world.setBlockState(pos.up(3).offset(facing, 2).offset(right, 2), pillar);
			world.setBlockToAir(pos.up(3).offset(facing, 2).offset(right, 3));
			world.setBlockToAir(pos.up(3).offset(facing, 2).offset(right, 4));
			world.setBlockState(pos.up(3).offset(facing, 2).offset(right, 5), panel.withProperty(BlockPanelBase.FACING, facing.rotateY()));
			world.setBlockState(pos.up(3).offset(facing, 3), Blocks.TRIPWIRE.getDefaultState());
			world.setBlockToAir(pos.up(3).offset(facing, 3).offset(right));
			world.setBlockToAir(pos.up(3).offset(facing, 3).offset(right, 2));
			world.setBlockToAir(pos.up(3).offset(facing, 3).offset(right, 3));
			world.setBlockToAir(pos.up(3).offset(facing, 3).offset(right, 4));
			world.setBlockState(pos.up(3).offset(facing, 3).offset(right, 5), panel.withProperty(BlockPanelBase.FACING, facing.rotateY()));
			world.setBlockState(pos.up(3).offset(facing, 4), Blocks.TRIPWIRE.getDefaultState());
			world.setBlockToAir(pos.up(3).offset(facing, 4).offset(right));
			world.setBlockToAir(pos.up(3).offset(facing, 4).offset(right, 2));
			world.setBlockToAir(pos.up(3).offset(facing, 4).offset(right, 3));
			world.setBlockToAir(pos.up(3).offset(facing, 4).offset(right, 4));
			world.setBlockState(pos.up(3).offset(facing, 4).offset(right, 5), panel.withProperty(BlockPanelBase.FACING, facing.rotateY()));
			world.setBlockState(pos.up(3).offset(facing, 5), Blocks.TRIPWIRE_HOOK.getDefaultState().withProperty(BlockTripWireHook.FACING, facing.getOpposite()));
			world.setBlockState(pos.up(3).offset(facing, 5).offset(right), panel.withProperty(BlockPanelBase.FACING, facing));
			world.setBlockState(pos.up(3).offset(facing, 5).offset(right, 2), panel.withProperty(BlockPanelBase.FACING, facing));
			world.setBlockState(pos.up(3).offset(facing, 5).offset(right, 3), panel.withProperty(BlockPanelBase.FACING, facing));
			world.setBlockState(pos.up(3).offset(facing, 5).offset(right, 4), panel.withProperty(BlockPanelBase.FACING, facing));
			world.setBlockState(pos.up(3).offset(facing, 5).offset(right, 5), plinth);

			world.setBlockToAir(pos.up(4).offset(facing));
			world.setBlockToAir(pos.up(4).offset(facing).offset(right));
			world.setBlockToAir(pos.up(4).offset(facing).offset(right, 2));
			world.setBlockToAir(pos.up(4).offset(facing).offset(right, 3));
			world.setBlockToAir(pos.up(4).offset(facing).offset(right, 4));
			world.setBlockState(pos.up(4).offset(facing).offset(right, 5), panel.withProperty(BlockPanelBase.FACING, facing.rotateY()));
			world.setBlockToAir(pos.up(4).offset(facing, 2));
			world.setBlockToAir(pos.up(4).offset(facing, 2).offset(right));
			world.setBlockState(pos.up(4).offset(facing, 2).offset(right, 2), capital);
			world.setBlockToAir(pos.up(4).offset(facing, 2).offset(right, 3));
			world.setBlockToAir(pos.up(4).offset(facing, 2).offset(right, 4));
			world.setBlockState(pos.up(4).offset(facing, 2).offset(right, 5), panel.withProperty(BlockPanelBase.FACING, facing.rotateY()));
			world.setBlockToAir(pos.up(4).offset(facing, 3));
			world.setBlockToAir(pos.up(4).offset(facing, 3).offset(right));
			world.setBlockToAir(pos.up(4).offset(facing, 3).offset(right, 2));
			world.setBlockToAir(pos.up(4).offset(facing, 3).offset(right, 3));
			world.setBlockToAir(pos.up(4).offset(facing, 3).offset(right, 4));
			world.setBlockState(pos.up(4).offset(facing, 3).offset(right, 5), panel.withProperty(BlockPanelBase.FACING, facing.rotateY()));
			world.setBlockToAir(pos.up(4).offset(facing, 4));
			world.setBlockToAir(pos.up(4).offset(facing, 4).offset(right));
			world.setBlockToAir(pos.up(4).offset(facing, 4).offset(right, 2));
			world.setBlockToAir(pos.up(4).offset(facing, 4).offset(right, 3));
			world.setBlockToAir(pos.up(4).offset(facing, 4).offset(right, 4));
			world.setBlockState(pos.up(4).offset(facing, 4).offset(right, 5), panel.withProperty(BlockPanelBase.FACING, facing.rotateY()));
			world.setBlockState(pos.up(4).offset(facing, 5), panel.withProperty(BlockPanelBase.FACING, facing));
			world.setBlockState(pos.up(4).offset(facing, 5).offset(right), panel.withProperty(BlockPanelBase.FACING, facing));
			world.setBlockState(pos.up(4).offset(facing, 5).offset(right, 2), panel.withProperty(BlockPanelBase.FACING, facing));
			world.setBlockState(pos.up(4).offset(facing, 5).offset(right, 3), panel.withProperty(BlockPanelBase.FACING, facing));
			world.setBlockState(pos.up(4).offset(facing, 5).offset(right, 4), panel.withProperty(BlockPanelBase.FACING, facing));
			world.setBlockState(pos.up(4).offset(facing, 5).offset(right, 5), pillar);

			world.setBlockToAir(pos.up(5).offset(facing));
			world.setBlockToAir(pos.up(5).offset(facing).offset(right));
			world.setBlockToAir(pos.up(5).offset(facing).offset(right, 2));
			world.setBlockToAir(pos.up(5).offset(facing).offset(right, 3));
			world.setBlockToAir(pos.up(5).offset(facing).offset(right, 4));
			world.setBlockState(pos.up(5).offset(facing).offset(right, 5), panel.withProperty(BlockPanelBase.FACING, facing.rotateY()));
			world.setBlockToAir(pos.up(5).offset(facing, 2));
			world.setBlockToAir(pos.up(5).offset(facing, 2).offset(right));
			world.setBlockState(pos.up(5).offset(facing, 2).offset(right, 2), MBlocks.godstone.getDefaultState());
			world.setBlockToAir(pos.up(5).offset(facing, 2).offset(right, 3));
			world.setBlockToAir(pos.up(5).offset(facing, 2).offset(right, 4));
			world.setBlockState(pos.up(5).offset(facing, 2).offset(right, 5), panel.withProperty(BlockPanelBase.FACING, facing.rotateY()));
			world.setBlockToAir(pos.up(5).offset(facing, 3));
			world.setBlockToAir(pos.up(5).offset(facing, 3).offset(right));
			world.setBlockToAir(pos.up(5).offset(facing, 3).offset(right, 2));
			world.setBlockToAir(pos.up(5).offset(facing, 3).offset(right, 3));
			world.setBlockToAir(pos.up(5).offset(facing, 3).offset(right, 4));
			world.setBlockState(pos.up(5).offset(facing, 3).offset(right, 5), panel.withProperty(BlockPanelBase.FACING, facing.rotateY()));
			world.setBlockToAir(pos.up(5).offset(facing, 4));
			world.setBlockToAir(pos.up(5).offset(facing, 4).offset(right));
			world.setBlockToAir(pos.up(5).offset(facing, 4).offset(right, 2));
			world.setBlockToAir(pos.up(5).offset(facing, 4).offset(right, 3));
			world.setBlockToAir(pos.up(5).offset(facing, 4).offset(right, 4));
			world.setBlockState(pos.up(5).offset(facing, 4).offset(right, 5), panel.withProperty(BlockPanelBase.FACING, facing.rotateY()));
			world.setBlockState(pos.up(5).offset(facing, 5), panel.withProperty(BlockPanelBase.FACING, facing));
			world.setBlockState(pos.up(5).offset(facing, 5).offset(right), panel.withProperty(BlockPanelBase.FACING, facing));
			world.setBlockState(pos.up(5).offset(facing, 5).offset(right, 2), panel.withProperty(BlockPanelBase.FACING, facing));
			world.setBlockState(pos.up(5).offset(facing, 5).offset(right, 3), panel.withProperty(BlockPanelBase.FACING, facing));
			world.setBlockState(pos.up(5).offset(facing, 5).offset(right, 4), panel.withProperty(BlockPanelBase.FACING, facing));
			world.setBlockState(pos.up(5).offset(facing, 5).offset(right, 5), pillar);
			
			world.setBlockToAir(pos.up(6).offset(facing));
			world.setBlockToAir(pos.up(6).offset(facing).offset(right));
			world.setBlockToAir(pos.up(6).offset(facing).offset(right, 2));
			world.setBlockToAir(pos.up(6).offset(facing).offset(right, 3));
			world.setBlockToAir(pos.up(6).offset(facing).offset(right, 4));
			world.setBlockState(pos.up(6).offset(facing).offset(right, 5), panel.withProperty(BlockPanelBase.FACING, facing.rotateY()));
			world.setBlockToAir(pos.up(6).offset(facing, 2));
			world.setBlockToAir(pos.up(6).offset(facing, 2).offset(right));
			world.setBlockState(pos.up(6).offset(facing, 2).offset(right, 2), plinth);
			world.setBlockToAir(pos.up(6).offset(facing, 2).offset(right, 3));
			world.setBlockToAir(pos.up(6).offset(facing, 2).offset(right, 4));
			world.setBlockState(pos.up(6).offset(facing, 2).offset(right, 5), panel.withProperty(BlockPanelBase.FACING, facing.rotateY()));
			world.setBlockToAir(pos.up(6).offset(facing, 3));
			world.setBlockToAir(pos.up(6).offset(facing, 3).offset(right));
			world.setBlockToAir(pos.up(6).offset(facing, 3).offset(right, 2));
			world.setBlockToAir(pos.up(6).offset(facing, 3).offset(right, 3));
			world.setBlockToAir(pos.up(6).offset(facing, 3).offset(right, 4));
			world.setBlockState(pos.up(6).offset(facing, 3).offset(right, 5), panel.withProperty(BlockPanelBase.FACING, facing.rotateY()));
			world.setBlockToAir(pos.up(6).offset(facing, 4));
			world.setBlockToAir(pos.up(6).offset(facing, 4).offset(right));
			world.setBlockToAir(pos.up(6).offset(facing, 4).offset(right, 2));
			world.setBlockToAir(pos.up(6).offset(facing, 4).offset(right, 3));
			world.setBlockToAir(pos.up(6).offset(facing, 4).offset(right, 4));
			world.setBlockState(pos.up(6).offset(facing, 4).offset(right, 5), panel.withProperty(BlockPanelBase.FACING, facing.rotateY()));
			world.setBlockState(pos.up(6).offset(facing, 5), panel.withProperty(BlockPanelBase.FACING, facing));
			world.setBlockState(pos.up(6).offset(facing, 5).offset(right), panel.withProperty(BlockPanelBase.FACING, facing));
			world.setBlockState(pos.up(6).offset(facing, 5).offset(right, 2), panel.withProperty(BlockPanelBase.FACING, facing));
			world.setBlockState(pos.up(6).offset(facing, 5).offset(right, 3), panel.withProperty(BlockPanelBase.FACING, facing));
			world.setBlockState(pos.up(6).offset(facing, 5).offset(right, 4), panel.withProperty(BlockPanelBase.FACING, facing));
			world.setBlockState(pos.up(6).offset(facing, 5).offset(right, 5), pillar);
			
			world.setBlockToAir(pos.up(7).offset(facing));
			world.setBlockToAir(pos.up(7).offset(facing).offset(right));
			world.setBlockToAir(pos.up(7).offset(facing).offset(right, 2));
			world.setBlockToAir(pos.up(7).offset(facing).offset(right, 3));
			world.setBlockToAir(pos.up(7).offset(facing).offset(right, 4));
			world.setBlockState(pos.up(7).offset(facing).offset(right, 5), panel.withProperty(BlockPanelBase.FACING, facing.rotateY()));
			world.setBlockToAir(pos.up(7).offset(facing, 2));
			world.setBlockToAir(pos.up(7).offset(facing, 2).offset(right));
			world.setBlockState(pos.up(7).offset(facing, 2).offset(right, 2), pillar);
			world.setBlockToAir(pos.up(7).offset(facing, 2).offset(right, 3));
			world.setBlockToAir(pos.up(7).offset(facing, 2).offset(right, 4));
			world.setBlockState(pos.up(7).offset(facing, 2).offset(right, 5), panel.withProperty(BlockPanelBase.FACING, facing.rotateY()));
			world.setBlockToAir(pos.up(7).offset(facing, 3));
			world.setBlockToAir(pos.up(7).offset(facing, 3).offset(right));
			world.setBlockToAir(pos.up(7).offset(facing, 3).offset(right, 2));
			world.setBlockToAir(pos.up(7).offset(facing, 3).offset(right, 3));
			world.setBlockToAir(pos.up(7).offset(facing, 3).offset(right, 4));
			world.setBlockState(pos.up(7).offset(facing, 3).offset(right, 5), panel.withProperty(BlockPanelBase.FACING, facing.rotateY()));
			world.setBlockToAir(pos.up(7).offset(facing, 4));
			world.setBlockToAir(pos.up(7).offset(facing, 4).offset(right));
			world.setBlockToAir(pos.up(7).offset(facing, 4).offset(right, 2));
			world.setBlockToAir(pos.up(7).offset(facing, 4).offset(right, 3));
			world.setBlockToAir(pos.up(7).offset(facing, 4).offset(right, 4));
			world.setBlockState(pos.up(7).offset(facing, 4).offset(right, 5), panel.withProperty(BlockPanelBase.FACING, facing.rotateY()));
			world.setBlockState(pos.up(7).offset(facing, 5), panel.withProperty(BlockPanelBase.FACING, facing));
			world.setBlockState(pos.up(7).offset(facing, 5).offset(right), panel.withProperty(BlockPanelBase.FACING, facing));
			world.setBlockState(pos.up(7).offset(facing, 5).offset(right, 2), panel.withProperty(BlockPanelBase.FACING, facing));
			world.setBlockState(pos.up(7).offset(facing, 5).offset(right, 3), panel.withProperty(BlockPanelBase.FACING, facing));
			world.setBlockState(pos.up(7).offset(facing, 5).offset(right, 4), panel.withProperty(BlockPanelBase.FACING, facing));
			world.setBlockState(pos.up(7).offset(facing, 5).offset(right, 5), pillar);
			
			world.setBlockToAir(pos.up(8).offset(facing));
			world.setBlockToAir(pos.up(8).offset(facing).offset(right));
			world.setBlockToAir(pos.up(8).offset(facing).offset(right, 2));
			world.setBlockToAir(pos.up(8).offset(facing).offset(right, 3));
			world.setBlockToAir(pos.up(8).offset(facing).offset(right, 4));
			world.setBlockState(pos.up(8).offset(facing).offset(right, 5), panel.withProperty(BlockPanelBase.FACING, facing.rotateY()));
			world.setBlockToAir(pos.up(8).offset(facing, 2));
			world.setBlockToAir(pos.up(8).offset(facing, 2).offset(right));
			world.setBlockState(pos.up(8).offset(facing, 2).offset(right, 2), capital);
			world.setBlockToAir(pos.up(8).offset(facing, 2).offset(right, 3));
			world.setBlockToAir(pos.up(8).offset(facing, 2).offset(right, 4));
			world.setBlockState(pos.up(8).offset(facing, 2).offset(right, 5), panel.withProperty(BlockPanelBase.FACING, facing.rotateY()));
			world.setBlockToAir(pos.up(8).offset(facing, 3));
			world.setBlockToAir(pos.up(8).offset(facing, 3).offset(right));
			world.setBlockToAir(pos.up(8).offset(facing, 3).offset(right, 2));
			world.setBlockToAir(pos.up(8).offset(facing, 3).offset(right, 3));
			world.setBlockToAir(pos.up(8).offset(facing, 3).offset(right, 4));
			world.setBlockState(pos.up(8).offset(facing, 3).offset(right, 5), panel.withProperty(BlockPanelBase.FACING, facing.rotateY()));
			world.setBlockToAir(pos.up(8).offset(facing, 4));
			world.setBlockToAir(pos.up(8).offset(facing, 4).offset(right));
			world.setBlockToAir(pos.up(8).offset(facing, 4).offset(right, 2));
			world.setBlockToAir(pos.up(8).offset(facing, 4).offset(right, 3));
			world.setBlockToAir(pos.up(8).offset(facing, 4).offset(right, 4));
			world.setBlockState(pos.up(8).offset(facing, 4).offset(right, 5), panel.withProperty(BlockPanelBase.FACING, facing.rotateY()));
			world.setBlockState(pos.up(8).offset(facing, 5), panel.withProperty(BlockPanelBase.FACING, facing));
			world.setBlockState(pos.up(8).offset(facing, 5).offset(right), panel.withProperty(BlockPanelBase.FACING, facing));
			world.setBlockState(pos.up(8).offset(facing, 5).offset(right, 2), panel.withProperty(BlockPanelBase.FACING, facing));
			world.setBlockState(pos.up(8).offset(facing, 5).offset(right, 3), panel.withProperty(BlockPanelBase.FACING, facing));
			world.setBlockState(pos.up(8).offset(facing, 5).offset(right, 4), panel.withProperty(BlockPanelBase.FACING, facing));
			world.setBlockState(pos.up(8).offset(facing, 5).offset(right, 5), capital);
			
			world.setBlockToAir(pos.up(9).offset(facing));
			world.setBlockState(pos.up(9).offset(facing).offset(right), stairsTop.withProperty(BlockStairs.FACING, facing));
			world.setBlockState(pos.up(9).offset(facing).offset(right, 2), stairsTop.withProperty(BlockStairs.FACING, facing));
			world.setBlockState(pos.up(9).offset(facing).offset(right, 3), stairsTop.withProperty(BlockStairs.FACING, facing));
			world.setBlockToAir(pos.up(9).offset(facing).offset(right, 4));
			world.setBlockState(pos.up(9).offset(facing).offset(right, 5), stairsTop.withProperty(BlockStairs.FACING, facing.rotateY()));
			world.setBlockToAir(pos.up(9).offset(facing, 2));
			world.setBlockState(pos.up(9).offset(facing, 2).offset(right), stairsTop.withProperty(BlockStairs.FACING, facing.rotateY()));
			world.setBlockState(pos.up(9).offset(facing, 2).offset(right, 2), tiles);
			world.setBlockState(pos.up(9).offset(facing, 2).offset(right, 3), stairsTop.withProperty(BlockStairs.FACING, facing.rotateYCCW()));
			world.setBlockToAir(pos.up(9).offset(facing, 2).offset(right, 4));
			world.setBlockState(pos.up(9).offset(facing, 2).offset(right, 5), stairsTop.withProperty(BlockStairs.FACING, facing.rotateY()));
			world.setBlockToAir(pos.up(9).offset(facing, 3));
			world.setBlockState(pos.up(9).offset(facing, 3).offset(right), stairsTop.withProperty(BlockStairs.FACING, facing.getOpposite()));
			world.setBlockState(pos.up(9).offset(facing, 3).offset(right, 2), stairsTop.withProperty(BlockStairs.FACING, facing.getOpposite()));
			world.setBlockState(pos.up(9).offset(facing, 3).offset(right, 3), stairsTop.withProperty(BlockStairs.FACING, facing.getOpposite()));
			world.setBlockToAir(pos.up(9).offset(facing, 3).offset(right, 4));
			world.setBlockState(pos.up(9).offset(facing, 3).offset(right, 5), stairsTop.withProperty(BlockStairs.FACING, facing.rotateY()));
			world.setBlockToAir(pos.up(9).offset(facing, 4));
			world.setBlockToAir(pos.up(9).offset(facing, 4).offset(right));
			world.setBlockToAir(pos.up(9).offset(facing, 4).offset(right, 2));
			world.setBlockToAir(pos.up(9).offset(facing, 4).offset(right, 3));
			world.setBlockState(pos.up(9).offset(facing, 4).offset(right, 4), stairsTop.withProperty(BlockStairs.FACING, facing.rotateY()));
			world.setBlockState(pos.up(9).offset(facing, 4).offset(right, 5), stairsTop.withProperty(BlockStairs.FACING, facing));
			world.setBlockState(pos.up(9).offset(facing, 5), stairsTop.withProperty(BlockStairs.FACING, facing));
			world.setBlockState(pos.up(9).offset(facing, 5).offset(right), stairsTop.withProperty(BlockStairs.FACING, facing));
			world.setBlockState(pos.up(9).offset(facing, 5).offset(right, 2), stairsTop.withProperty(BlockStairs.FACING, facing));
			world.setBlockState(pos.up(9).offset(facing, 5).offset(right, 3), stairsTop.withProperty(BlockStairs.FACING, facing));
			world.setBlockState(pos.up(9).offset(facing, 5).offset(right, 4), stairsTop.withProperty(BlockStairs.FACING, facing));
			world.setBlockState(pos.up(9).offset(facing, 5).offset(right, 5), tiles);
		}
		
		world.setBlockState(pos, tiles);
		world.setBlockState(pos.up(), tiles);
		world.setBlockState(pos.up(2), Blocks.REDSTONE_WIRE.getDefaultState());
		world.setBlockState(pos.up(3), plinth);
		world.setBlockState(pos.up(4), capital);
		world.setBlockState(pos.up(5), MBlocks.adamantium_nugget.getDefaultState());
		world.setBlockToAir(pos.up(6));
		world.setBlockToAir(pos.up(7));
		world.setBlockToAir(pos.up(8));
		world.setBlockToAir(pos.up(9));
		
		return true;
	}
}
