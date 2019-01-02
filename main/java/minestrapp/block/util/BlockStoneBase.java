package minestrapp.block.util;

import java.util.List;
import java.util.Random;

import minestrapp.Minestrapp5;
import minestrapp.block.EnumStoneType;
import minestrapp.block.item.IMetaBlockName;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockStoneBase extends Block implements IMetaBlockName
{
public static final PropertyEnum<EnumStoneType> VARIANT = PropertyEnum.<EnumStoneType>create("variant", EnumStoneType.class);
	
	public boolean doesDropItem;
	public Item dropItem;
	public int baseQuantity;
	public int quantityVariance;
	public int dropMeta;
	public int xpMin;
	public int xpMax;
	public boolean silkHarvest;
	public boolean fortune;
	public boolean matchMeta;
	private boolean glowing = false;

	public BlockStoneBase(String name, Material material, SoundType soundType, float hardness, String tool, int harvestLevel)
	{
		super(material);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumStoneType.STONE));
		if(name != null)
		{
			this.setUnlocalizedName(name);
			this.setRegistryName(new ResourceLocation(Minestrapp5.MODID, this.getUnlocalizedName().substring(5)));
		}
		this.setSoundType(soundType);
		this.setHardness(hardness);
		this.setHarvestLevel(tool, harvestLevel);
		this.doesDropItem = false;
		this.silkHarvest = true;
		this.fortune = false;
		this.matchMeta = false;
	}
	
	public Block setDropsItem(ItemStack itemDrop, int variance, int xpMin, int xpMax, boolean silkHarvest, boolean fortune, boolean matchMeta)
	{
		this.doesDropItem = true;
		this.dropItem = itemDrop.getItem();
		this.baseQuantity = itemDrop.getCount();
		this.quantityVariance = variance + 1;
		this.dropMeta = itemDrop.getMetadata();
		this.xpMin = xpMin;
		this.xpMax = xpMax;
		this.silkHarvest = silkHarvest;
		this.fortune = fortune;
		this.matchMeta = matchMeta;
		return this;
	}
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
		if(this.doesDropItem == true)
			return dropItem;
		else
			return super.getItemDropped(state, rand, fortune);
    }
	
	public int quantityDropped(Random random)
    {
		if(this.doesDropItem == true)
			return baseQuantity + random.nextInt(quantityVariance);
		else
			return super.quantityDropped(random);
    }
	
	public int quantityDroppedWithBonus(int fortune, Random random)
    {
        if(this.doesDropItem == true && this.fortune == true)
        	return this.quantityDropped(random) + random.nextInt(fortune + 1);
        else
        	return super.quantityDroppedWithBonus(fortune, random);
    }
	
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {
        super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
    }

	public int damageDropped(IBlockState state)
    {
        return ((EnumStoneType)state.getValue(VARIANT)).getMetadata();
    }
	
	@Override
    public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
    {
		if(this.doesDropItem == true)
		{
			Random rand = world instanceof World ? ((World)world).rand : new Random();
	        if (this.getItemDropped(state, RANDOM, fortune) != Item.getItemFromBlock(this))
	        {
	            return MathHelper.getInt(rand, xpMin, xpMax);
	        }
	        return 0;
		}
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
	
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> tab)
    {
        for (EnumStoneType stone$enumtype : EnumStoneType.values())
        {
            tab.add(new ItemStack(this, 1, stone$enumtype.getMetadata()));
        }
    }
	
	public MapColor getMapColor(IBlockState state, IBlockAccess access, BlockPos pos)
    {
        return ((EnumStoneType)state.getValue(VARIANT)).getMapColor();
    }
	
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, EnumStoneType.byMetadata(meta));
    }
	
	public int getMetaFromState(IBlockState state)
    {
        return ((EnumStoneType)state.getValue(VARIANT)).getMetadata();
    }
	
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT});
    }
	
	@Override
	public String getSpecialName(ItemStack stack)
	{
		return EnumStoneType.byMetadata(stack.getMetadata()).getName();
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
	}
	
	public float getBlockHardness(IBlockState blockState, World worldIn, BlockPos pos)
    {
		if(((EnumStoneType)blockState.getValue(VARIANT)).isDeep())
		{
			return this.blockHardness * 1.5F;
		}
        return this.blockHardness;
    }
	
	public void setHarvestLevel(String toolClass, int level)
    {
		for(int i = 0 ; i < EnumStoneType.values().length ; i++)
		{
        	if(EnumStoneType.byMetadata(i).isDeep())
        	{
        		if(level < 2)
        		{
        			setHarvestLevel(toolClass, 2, this.getDefaultState().withProperty(VARIANT, EnumStoneType.byMetadata(i)));
        		}
        		else
        		{
        			setHarvestLevel(toolClass, level, this.getDefaultState().withProperty(VARIANT, EnumStoneType.byMetadata(i)));
        		}
        	}
        	else
        	{
        		setHarvestLevel(toolClass, level, this.getDefaultState().withProperty(VARIANT, EnumStoneType.byMetadata(i)));
        	}
        }
    }
	
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        List<ItemStack> ret = new java.util.ArrayList<ItemStack>();

        Random rand = world instanceof World ? ((World)world).rand : RANDOM;

        int count = this.quantityDropped(state, fortune, rand);
        for(int i = 0; i < count; i++)
        {
            Item item = this.getItemDropped(state, rand, fortune);
            if (item != Items.AIR)
            {
            	if(this.doesDropItem == true)
            	{
            		if(this.matchMeta == true)
            		{
            			ret.add(new ItemStack(item, 1, this.damageDropped(state)));
            		}
            		else
            		{
            			ret.add(new ItemStack(item, 1, this.dropMeta));
            		}
            	}
            	else
            	{
            		ret.add(new ItemStack(item, 1, this.damageDropped(state)));
            	}
            }
        }
        return ret;
    }
	
	public BlockStoneBase setGlowing()
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
}
