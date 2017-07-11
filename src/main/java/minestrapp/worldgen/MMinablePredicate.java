package minestrapp.worldgen;

import com.google.common.base.Predicate;

import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

public class MMinablePredicate implements Predicate<IBlockState>
{
	public static IBlockState state;
	
	public MMinablePredicate (IBlockState state)
	{
		this.state = state;
	}
	
	@Override
	public boolean apply(IBlockState input)
	{
		if(input != null)
		{
			if(input.getBlock() == Blocks.STONE)
			{
				BlockStone.EnumType blockstone$enumtype = (BlockStone.EnumType)input.getValue(BlockStone.VARIANT);
	            return blockstone$enumtype.isNatural();
			}
			return input == state;
		}
		return false;
	}

}
