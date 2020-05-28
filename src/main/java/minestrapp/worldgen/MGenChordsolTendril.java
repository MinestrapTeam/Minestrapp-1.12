package minestrapp.worldgen;

import java.util.Random;

import minestrapp.MBlocks;
import minestrapp.block.BlockMDirt;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class MGenChordsolTendril extends WorldGenerator
{
	private EnumFacing facing;
	
	public MGenChordsolTendril(EnumFacing facing)
	{
		this.facing = facing;
	}
	
	public MGenChordsolTendril setFacing(EnumFacing facing)
	{
		this.facing = facing;
		return this;
	}
	
	public boolean generate(World world, Random rand, BlockPos pos)
	{
		IBlockState chordsol = MBlocks.portal_dust.getDefaultState().withProperty(BlockMDirt.VARIANT, BlockMDirt.DirtType.PODZOL);
		
		if(world.getBlockState(pos.offset(this.facing.rotateY())) != chordsol && world.getBlockState(pos.offset(this.facing.rotateYCCW())) != chordsol && world.getBlockState(pos.offset(this.facing)) != chordsol)
		{
			world.setBlockState(pos, chordsol, 2);
			if(rand.nextInt(10) < 7 && world.getBlockState(pos.up()).getBlock() == MBlocks.clutchthorn && world.isAirBlock(pos.up().up()))
				world.setBlockState(pos.up().up(), MBlocks.clutchthorn.getDefaultState());
		
			for(int i = 0 ; i < 3 ; i++)
			{
				int dir = rand.nextInt(4);
				EnumFacing facing = EnumFacing.NORTH;
				
				if(dir == 1)
					facing = EnumFacing.EAST;
				else if(dir == 2)
					facing = EnumFacing.SOUTH;
				else if(dir == 3)
					facing = EnumFacing.WEST;
				
				MGenChordsolTendril generator = new MGenChordsolTendril(facing);
				
				BlockPos offsetPos = pos.offset(facing);
				if(world.getBlockState(offsetPos).getBlock() != MBlocks.fargrowth)
					offsetPos = pos.offset(facing).down();
				if(world.getBlockState(offsetPos).getBlock() != MBlocks.fargrowth)
					offsetPos = pos.offset(facing).up();
				if(world.getBlockState(offsetPos).getBlock() == MBlocks.fargrowth && world.getBlockState(offsetPos.offset(facing)) != chordsol)
					generator.generate(world, rand, offsetPos);
			}
		}
		
		return true;
	}
}
