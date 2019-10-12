package minestrapp.block.util;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockBaseNonSolid extends BlockBase
{
	public BlockBaseNonSolid(String name, Material material, MapColor mapColor, SoundType soundType, float hardness)
	{
		super(name, material, mapColor, soundType, hardness);
	}

	public BlockBaseNonSolid(String name, Material material, MapColor mapColor, SoundType soundType, float hardness, String tool, int harvestLevel)
	{
		super(name, material, mapColor, soundType, hardness, tool, harvestLevel);
	}
	
	public boolean isFullCube(IBlockState state)
    {
        return false;
    }
	
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	
	public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing)
    {
		return BlockFaceShape.UNDEFINED;
    }
}
