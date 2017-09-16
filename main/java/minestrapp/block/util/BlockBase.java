package minestrapp.block.util;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBase extends Block
{
	private MapColor mapColor;
	private boolean overrideToolReqs = false;
	private boolean isBeaconBase = false;
	private boolean canDragonDestroy = true;
	private boolean canWitherDestroy = true;
	private boolean dropsItem;
	private Item dropItem;
	private int baseQuantity;
	private int quantityVariance;
	private int dropMeta;
	private int xpMin;
	private int xpMax;
	private boolean silkHarvest;
	private boolean fortune;

	public BlockBase(String name, Material material, MapColor mapColor, SoundType soundType, float hardness)
	{
		super(material);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.mapColor = mapColor;
		this.setSoundType(soundType);
		this.setHardness(hardness);
		this.dropsItem = false;
		this.dropItem = null;
		this.baseQuantity = 0;
		this.quantityVariance = 0;
		this.dropMeta = 0;
		this.xpMin = 0;
		this.xpMax = 0;
		this.silkHarvest = true;
		this.fortune = false;
	}
	
	public BlockBase(String name, Material material, MapColor mapColor, SoundType soundType, float hardness, String tool, int harvestLevel)
	{
		this(name, material, mapColor, soundType, hardness);
		this.setHarvestLevel(tool, harvestLevel);
	}
	
	public BlockBase setDropsItem(ItemStack itemDrop, int variance, int xpMin, int xpMax, boolean silkHarvest, boolean fortune)
	{
		this.dropsItem = true;
		this.dropItem = itemDrop.getItem();
		this.baseQuantity = itemDrop.getCount();
		this.quantityVariance = variance + 1;
		this.dropMeta = itemDrop.getMetadata();
		this.xpMin = xpMin;
		this.xpMax = xpMax;
		this.silkHarvest = silkHarvest;
		this.fortune = fortune;
		return this;
	}
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
		if(dropsItem && dropItem != null)
			return dropItem;
		else
			return super.getItemDropped(state, rand, fortune);
    }
	
	public int quantityDropped(Random random)
    {
		if(dropsItem)
			return baseQuantity + random.nextInt(quantityVariance);
		else
			return super.quantityDropped(random);
    }
	
	public int quantityDroppedWithBonus(int fortune, Random random)
    {
		if(dropsItem && this.fortune)
			return this.quantityDropped(random) + random.nextInt(fortune + 1);
		else
			return super.quantityDroppedWithBonus(fortune, random);
    }
	
	public int damageDropped(IBlockState state)
    {
		if(dropsItem)
			return dropMeta;
		else
			return super.damageDropped(state);
    }
	
	@Override
    public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
    {
		if(dropsItem)
		{
			Random rand = world instanceof World ? ((World)world).rand : new Random();
	        if (this.getItemDropped(state, RANDOM, fortune) != Item.getItemFromBlock(this))
	        {
	            return MathHelper.getInt(rand, xpMin, xpMax);
	        }
	        return 0;
		}
		else
			return super.getExpDrop(state, world, pos, fortune);
    }
	
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(this);
    }
	
	public boolean canSilkHarvest()
	{
		return silkHarvest;
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
