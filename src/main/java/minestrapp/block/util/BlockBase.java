package minestrapp.block.util;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockBase extends Block
{
	private MapColor mapColor;
	private boolean overrideToolReqs = false;
	private boolean isBeaconBase = false;
	private boolean canDragonDestroy = true;
	private boolean canWitherDestroy = true;

	public BlockBase(String name, Material material, MapColor mapColor, SoundType soundType, float hardness)
	{
		super(material);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.mapColor = mapColor;
		this.setSoundType(soundType);
		this.setHardness(hardness);
	}
	
	public BlockBase(String name, Material material, MapColor mapColor, SoundType soundType, float hardness, String tool, int harvestLevel)
	{
		this(name, material, mapColor, soundType, hardness);
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
	
	public MapColor getMapColor(IBlockState state, IBlockAccess world, BlockPos pos)
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
	
	public BlockBase setToolOverride()
	{
		this.overrideToolReqs = true;
		return this;
	}
	
	public BlockBase setBeaconBase()
	{
		this.isBeaconBase = true;
		return this;
	}
	
	public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon)
    {
        return this.isBeaconBase;
    }
	
	public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player)
    {
		if(this.overrideToolReqs == false)
			return net.minecraftforge.common.ForgeHooks.canHarvestBlock(this, player, world, pos);
		else
		{
			IBlockState state = world.getBlockState(pos);
	        state = state.getBlock().getActualState(state, world, pos);
	        ItemStack stack = player.getHeldItemMainhand();
	        String tool = this.getHarvestTool(state);
	        if (stack.isEmpty() || tool == null)
	        {
	            return player.canHarvestBlock(state);
	        }
	        int toolLevel = stack.getItem().getHarvestLevel(stack, tool, player, state);
	        if (toolLevel < 0)
	        {
	            return player.canHarvestBlock(state);
	        }
	        return toolLevel >= this.getHarvestLevel(state);
		}
    }
}
