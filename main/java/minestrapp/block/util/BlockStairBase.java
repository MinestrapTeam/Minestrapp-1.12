package minestrapp.block.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockStairBase extends BlockStairs
{
	private int flammability = 0;
	private int firespread = 0;
	
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
	
	public BlockStairBase setFlammable(int flammability, int fireSpread)
	{
		this.flammability = flammability;
		this.firespread = fireSpread;
		
		return this;
	}
	
	public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
		if(this.flammability > 0)
			return this.flammability;
		else
			return super.getFlammability(world, pos, face);
    }
	
	public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
		if(this.firespread > 0)
			return this.firespread;
		else
			return super.getFireSpreadSpeed(world, pos, face);
    }
}
