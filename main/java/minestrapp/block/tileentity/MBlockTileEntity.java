package minestrapp.block.tileentity;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import javax.annotation.Nullable;

import minestrapp.block.util.BlockBase;

public abstract class MBlockTileEntity<TE extends TileEntity> extends BlockBase {

	public MBlockTileEntity(String name, Material material, MapColor mapColor, SoundType soundType, float hardness, String tool, int harvestLevel)
	{
		super(name, material, mapColor, soundType, hardness, tool, harvestLevel);
	}
	
	public abstract Class<TE> getTileEntityClass();
	
	public TE getTileEntity(IBlockAccess world, BlockPos pos) {
		return (TE)world.getTileEntity(pos);
	}
	
	
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Nullable
	
	public abstract TE createTileEntity(World world, IBlockState state);

}