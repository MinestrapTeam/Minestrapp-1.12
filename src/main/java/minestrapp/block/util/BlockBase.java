package minestrapp.block.util;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockBase extends Block
{
	public MapColor mapColor;
	public static boolean canDragonDestroy = true;
	public static boolean canWitherDestroy = true;

	public BlockBase(String name, Material material, MapColor mapColor, SoundType soundType, float hardness, String tool, int harvestLevel)
	{
		super(material);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.mapColor = mapColor;
		this.setSoundType(soundType);
		this.setHardness(hardness);
		this.setHarvestLevel(tool, harvestLevel);
	}

	public BlockBase setSlipperiness(float level)
	{
		this.slipperiness = level;
		return this;
	}
	
	public BlockBase setEntityInvulnerability(String entity)
	{
		if(entity == "dragon")
			this.canDragonDestroy = false;
		if(entity == "wither")
			this.canWitherDestroy = false;
		if(entity == "all")
		{
			this.canDragonDestroy = false;
			this.canWitherDestroy = false;
		}
		return this;
	}
	
	public MapColor getMapColor(IBlockState state)
    {
        return mapColor;
    }
	
	public boolean canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity)
    {
        if (entity instanceof net.minecraft.entity.boss.EntityDragon)
        {
            return this.canDragonDestroy;
        }
        else if (entity instanceof net.minecraft.entity.boss.EntityWither)
        {
            return this.canWitherDestroy;
        }
        return true;
    }
}
