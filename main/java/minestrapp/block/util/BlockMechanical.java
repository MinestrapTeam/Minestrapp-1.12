package minestrapp.block.util;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMechanical extends BlockBase
{
	public static final PropertyInteger POWER = PropertyInteger.create("power", 0, 15);
	
	public BlockMechanical(String name, Material material, MapColor mapColor, SoundType soundType, float hardness)
	{
		super(name, material, mapColor, soundType, hardness);
		this.setDefaultState(this.blockState.getBaseState().withProperty(POWER, Integer.valueOf(0)));
	}
	
	public BlockMechanical(String name, Material material, MapColor mapColor, SoundType soundType, float hardness, String tool, int harvestLevel)
	{
		super(name, material, mapColor, soundType, hardness, tool, harvestLevel);
		this.setDefaultState(this.blockState.getBaseState().withProperty(POWER, Integer.valueOf(0)));
	}

	public int isProvidingPowerFromFace(IBlockAccess world, BlockPos pos, EnumFacing face)
	{
		return 0;
	}
	
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {POWER});
    }
}
