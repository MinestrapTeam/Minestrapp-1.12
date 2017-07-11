package minestrapp.worldgen;

import com.google.common.base.Predicate;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class MGenMinable extends WorldGenMinable
{
	private final IBlockState oreBlock;
	private final int blockCount;
	private final Predicate<IBlockState> predicate;
	
	public MGenMinable(IBlockState state, int blockCount)
    {
        super(state, blockCount);
        this.oreBlock = state;
        this.blockCount = blockCount;
        this.predicate = new MMinablePredicate(state);
    }

    public MGenMinable(IBlockState state, int blockCount, Predicate<IBlockState> p_i45631_3_)
    {
        super(state, blockCount, p_i45631_3_);
        this.oreBlock = state;
        this.blockCount = blockCount;
        this.predicate = p_i45631_3_;
    }
    
    public IBlockState getOreState()
    {
    	return this.oreBlock;
    }
    
    public int getBlockCount()
    {
    	return this.blockCount;
    }
    
    public Predicate<IBlockState> getPredicate()
    {
    	return this.predicate;
    }
}
