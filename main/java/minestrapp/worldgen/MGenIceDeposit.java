package minestrapp.worldgen;

import java.util.Random;

import minestrapp.MBlocks;
import minestrapp.block.BlockGlaciericIceDeposit;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class MGenIceDeposit extends WorldGenerator
{
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position)
	{
		if(worldIn.getBlockState(position).getBlock() == MBlocks.lichen && worldIn.getBlockState(position.up()).getBlock().isReplaceable(worldIn, position.up()))
		{
			int radius = rand.nextInt(7 - 4) + 4;
			
			for (int x = position.getX() - radius ; x <= position.getX() + radius ; ++x)
            {
				for(int z = position.getZ() - radius ; z <= position.getZ() + radius ; ++z)
				{
					for(int y = position.getY() ; y <= position.getY() + radius ; y++)
					{
						int xOffset = x - position.getX();
		                int yOffset = y - position.getY();
		                int zOffset = z - position.getZ();
		
		                if (xOffset * xOffset + yOffset * yOffset + zOffset * zOffset <= radius * radius)
		                {
		                	worldIn.setBlockState(new BlockPos(x, y, z), MBlocks.glacial_invincium.getDefaultState());
		                }
					}
					
					worldIn.setBlockState(position.offset(EnumFacing.UP, radius), MBlocks.glacieric_ice_deposit.getDefaultState().withProperty(BlockGlaciericIceDeposit.AGE, 5), 1);
					if(worldIn.getBlockState(position.offset(EnumFacing.UP, radius)).getBlock() == MBlocks.glacieric_ice_deposit)
						((BlockGlaciericIceDeposit)worldIn.getBlockState(position.offset(EnumFacing.UP, radius)).getBlock()).updateTick(worldIn, position.offset(EnumFacing.UP, radius), worldIn.getBlockState(position.offset(EnumFacing.UP, radius)), rand);
					int down = 1;
					int shorten = 1;
					int newRadius = radius;
					
					for(int y = position.down().getY() ; newRadius > 1 ; y--)
					{
						down++;
						int xOffset = x - position.getX();
		                int zOffset = z - position.getZ();
						
						if(down > (radius * shorten) - shorten)
						{
							shorten ++;
							newRadius--;
						}
		                
						if (xOffset * xOffset + zOffset * zOffset <= newRadius * newRadius)
		                {
		                	worldIn.setBlockState(new BlockPos(x, y, z), MBlocks.glacial_invincium.getDefaultState());
		                }
					}
				}
            }
			
			return true;
		}
		return false;
	}

}
