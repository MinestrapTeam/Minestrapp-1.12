package minestrapp.block.util;

import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockItemDropBase extends BlockBase
{
	public static Item dropItem;
	public static int baseQuantity;
	public static int quantityVariance;
	public static int dropMeta;
	public static int xpMin;
	public static int xpMax;
	public static boolean silkHarvest;
	
	public BlockItemDropBase(String name, Material material, MapColor mapColor, SoundType soundType, float hardness, String tool, int harvestLevel, ItemStack itemDrop, int variance, int xpMin, int xpMax, boolean silkHarvest)
	{
		super(name, material, mapColor, soundType, hardness, tool, harvestLevel);
		this.dropItem = itemDrop.getItem();
		this.baseQuantity = itemDrop.getCount();
		this.quantityVariance = variance + 1;
		this.dropMeta = itemDrop.getMetadata();
		this.xpMin = xpMin;
		this.xpMax = xpMax;
		this.silkHarvest = silkHarvest;
	}
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return dropItem;
    }
	
	public int quantityDropped(Random random)
    {
        return baseQuantity + random.nextInt(quantityVariance);
    }
	
	public int quantityDroppedWithBonus(int fortune, Random random)
    {
        return this.quantityDropped(random) + random.nextInt(fortune + 1);
    }
	
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {
        super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
    }
	
	public int damageDropped(IBlockState state)
    {
        return dropMeta;
    }
	
	@Override
    public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
    {
		Random rand = world instanceof World ? ((World)world).rand : new Random();
        if (this.getItemDropped(state, RANDOM, fortune) != Item.getItemFromBlock(this))
        {
            return MathHelper.getInt(rand, xpMin, xpMax);
        }
        return 0;
    }
	
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(this);
    }
	
	public boolean canSilkHarvest()
	{
		return silkHarvest;
	}
}
