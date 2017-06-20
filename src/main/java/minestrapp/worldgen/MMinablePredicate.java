package minestrapp.worldgen;

import com.google.common.base.Predicate;

import net.minecraft.block.state.IBlockState;

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
		return input != null && input == state;
	}

}
