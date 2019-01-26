package minestrapp.block;

import minestrapp.MTabs;
import minestrapp.block.util.BlockBase;
import net.minecraft.block.BlockPane;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockMPane extends BlockPane
{
	private MapColor mapColor;
	
	public BlockMPane(String name, Material materialIn, boolean canDrop, MapColor mapcolor, SoundType sound)
	{
		super(materialIn, canDrop);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(MTabs.decor);
		this.setSoundType(sound);
		this.mapColor = mapcolor;
	}
	
	public BlockMPane(String name, Material materialIn, boolean canDrop, MapColor mapcolor, SoundType sound, String tool, int level)
	{
		this(name, materialIn, canDrop, mapcolor, sound);
		this.setHarvestLevel(tool, level);
	}
	
	public MapColor getMapColor(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return mapColor;
    }
}
