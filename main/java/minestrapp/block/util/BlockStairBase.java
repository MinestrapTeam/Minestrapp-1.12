package minestrapp.block.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;

public class BlockStairBase extends BlockStairs
{
	public BlockStairBase(Block modelState)
	{
		super(modelState.getDefaultState());
		this.setUnlocalizedName(modelState.getUnlocalizedName().substring(5) + "_stairs");
		this.setRegistryName(modelState.getUnlocalizedName().substring(5) + "_stairs");
		this.useNeighborBrightness = true;
		this.setCreativeTab(modelState.getCreativeTabToDisplayOn());
	}

	public BlockStairBase(IBlockState modelState, String unlocalized)
	{
		super(modelState);
		this.setUnlocalizedName(unlocalized.substring(5) + "_stairs");
		this.setRegistryName(unlocalized.substring(5) + "_stairs");
		this.useNeighborBrightness = true;
		this.setCreativeTab(modelState.getBlock().getCreativeTabToDisplayOn());
	}
}
