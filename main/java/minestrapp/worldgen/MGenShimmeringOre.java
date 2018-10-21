package minestrapp.worldgen;

import java.util.Random;

import minestrapp.MBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class MGenShimmeringOre extends WorldGenerator
{
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos pos)
	{
		if((worldIn.getBlockState(pos) == Blocks.NETHERRACK.getDefaultState() || worldIn.getBlockState(pos) == Blocks.MAGMA.getDefaultState()) && worldIn.getBlockState(pos.up()).getMaterial() == Material.LAVA)
		{
			worldIn.setBlockState(pos, MBlocks.ore_shimmering.getDefaultState());
			
			for(int i = 0 ; i < 6 ; i++)
			{
				BlockPos newpos = pos.offset(EnumFacing.UP, rand.nextInt(3) - 1).offset(EnumFacing.NORTH, rand.nextInt(9) - 4).offset(EnumFacing.EAST, rand.nextInt(9) - 4);
				
				if((worldIn.getBlockState(newpos) == Blocks.NETHERRACK.getDefaultState() || worldIn.getBlockState(newpos) == Blocks.MAGMA.getDefaultState()) && worldIn.getBlockState(newpos.up()).getMaterial() == Material.LAVA)
					worldIn.setBlockState(newpos, MBlocks.ore_shimmering.getDefaultState());
			}
			
			return true;
		}
		
		return false;
	}
}
