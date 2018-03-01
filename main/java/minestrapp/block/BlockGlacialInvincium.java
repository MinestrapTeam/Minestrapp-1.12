package minestrapp.block;

import minestrapp.block.util.BlockBase;
import minestrapp.config.MConfig;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockGlacialInvincium extends BlockBase
{
	public BlockGlacialInvincium()
	{
		super("glacial_invincium", Material.ROCK, MapColor.CYAN, SoundType.STONE, -1F, "pickaxe", 4);
	}
	
    public float getBlockHardness(IBlockState blockState, World worldIn, BlockPos pos)
    {
    	if(MConfig.minableGlacialInvincium)
    		return 90F;
        return this.blockHardness;
    }
}
