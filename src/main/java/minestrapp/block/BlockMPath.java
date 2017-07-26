package minestrapp.block;

import minestrapp.block.util.BlockBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class BlockMPath extends BlockBase
{
	private BlockMDirt dirt;
	
	public BlockMPath(String name, Material material, MapColor mapColor, SoundType soundType, float hardness, String tool, int harvestLevel)
	{
		super(name, Material.GROUND, mapColor, soundType, hardness, "shovel", harvestLevel);
	}
	
}
