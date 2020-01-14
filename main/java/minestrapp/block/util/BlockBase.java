package minestrapp.block.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import minestrapp.block.BlockBauble;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
	private boolean customPushLogic;
	private EnumPushReaction pushReaction;
	private BlockRenderLayer layer = BlockRenderLayer.SOLID;
	private boolean ignoreSimilarity = false;
	private boolean glowing = false;
	private List<Integer> flammability = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0));
	private List<Integer> firespread = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0));
	private String tooltip = null;
	
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
		this.customPushLogic = false;
	}
	
	public BlockBase(String name, Material material, MapColor mapColor, SoundType soundType, float hardness, String tool, int harvestLevel)
	{
		this(name, material, mapColor, soundType, hardness);
		this.setHarvestLevel(tool, harvestLevel);
	}
	
	public BlockBase(String name)
	{
		this(name, Material.AIR, MapColor.AIR, SoundType.STONE, 0F);
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
	
	public BlockBase setPushReaction(EnumPushReaction pushReaction)
	{
		this.customPushLogic = true;
		this.pushReaction = pushReaction;
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
	
	public EnumPushReaction getMobilityFlag(IBlockState state)
    {
		if(this.customPushLogic)
			return this.pushReaction;
		else
			return super.getMobilityFlag(state);
    }
	
	public BlockBase setRenderLayer(BlockRenderLayer layer)
	{
		this.layer = layer;
		return this;
	}
	
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return this.layer;
    }
	
	public BlockBase setIgnoresSimilarity()
	{
		this.ignoreSimilarity = true;
		return this;
	}
	
	@SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
        Block block = iblockstate.getBlock();

        if(this.ignoreSimilarity)
        {
        	if (blockState != iblockstate)
                return true;

            if (block == this)
                return false;
            
            return !this.ignoreSimilarity && block == this ? false : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
        }
        
        else
        	return super.shouldSideBeRendered(iblockstate, blockAccess, pos, side);
    }
	
	public BlockBase setGlowing()
	{
		this.glowing = true;
		return this;
	}
	
	@SideOnly(Side.CLIENT)
    public int getPackedLightmapCoords(IBlockState state, IBlockAccess source, BlockPos pos)
    {
		if(this.glowing)
			return 15728880;
		else
			return super.getPackedLightmapCoords(state, source, pos);
    }
	
	public BlockBase setFlammable(int flammability, int fireSpread)
	{
		for(int i = 0 ; i < 16 ; i++)
		{
			this.flammability.set(i, flammability);
			this.firespread.set(i, fireSpread);
		}
		
		return this;
	}
	
	public BlockBase setFlammable(int flammability, int fireSpread, int meta)
	{
		this.flammability.set(meta, flammability);
		this.firespread.set(meta, fireSpread);
		
		return this;
	}
	
	public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
		int index = world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos));
		
		if(this.flammability.get(index) > 0)
			return this.flammability.get(index);
		else
			return super.getFlammability(world, pos, face);
    }
	
	public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
		int index = world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos));
		
		if(this.firespread.get(index) > 0)
			return this.firespread.get(index);
		else
			return super.getFireSpreadSpeed(world, pos, face);
    }
	
	public BlockBase setTooltip(String tooltip)
	{
		this.tooltip = tooltip;
		return this;
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		if(this.tooltip != null)
			tooltip.add(this.tooltip);
		else
			super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
