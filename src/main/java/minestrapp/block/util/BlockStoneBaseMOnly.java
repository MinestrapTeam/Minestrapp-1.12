package minestrapp.block.util;

import minestrapp.Minestrapp5;
import minestrapp.block.EnumStoneTypeMOnly;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockStoneBaseMOnly extends Block implements IMetaBlockName
{
public static final PropertyEnum<EnumStoneTypeMOnly> VARIANT = PropertyEnum.<EnumStoneTypeMOnly>create("variant", EnumStoneTypeMOnly.class);
	
	public BlockStoneBaseMOnly(String name, Material material, SoundType soundType, float hardness, String tool, int harvestLevel)
	{
		super(material);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumStoneTypeMOnly.DEEPSTONE));
		this.setUnlocalizedName(name);
		this.setRegistryName(new ResourceLocation(Minestrapp5.MODID, this.getUnlocalizedName().substring(5)));
		this.setSoundType(soundType);
		this.setHardness(hardness);
		this.setHarvestLevel(tool, harvestLevel);
	}

	public int damageDropped(IBlockState state)
    {
        return ((EnumStoneTypeMOnly)state.getValue(VARIANT)).getMetadata();
    }
	
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> tab)
    {
        for (EnumStoneTypeMOnly stone$enumtype : EnumStoneTypeMOnly.values())
        {
            tab.add(new ItemStack(this, 1, stone$enumtype.getMetadata()));
        }
    }
	
	public MapColor getMapColor(IBlockState state, IBlockAccess access, BlockPos pos)
    {
        return ((EnumStoneTypeMOnly)state.getValue(VARIANT)).getMapColor();
    }
	
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, EnumStoneTypeMOnly.byMetadata(meta));
    }
	
	public int getMetaFromState(IBlockState state)
    {
        return ((EnumStoneTypeMOnly)state.getValue(VARIANT)).getMetadata();
    }
	
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT});
    }
	
	@Override
	public String getSpecialName(ItemStack stack)
	{
		return EnumStoneTypeMOnly.byMetadata(stack.getMetadata()).getName();
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
	}
	
	public float getBlockHardness(IBlockState blockState, World worldIn, BlockPos pos)
    {
		if(((EnumStoneTypeMOnly)blockState.getValue(VARIANT)).isDeep())
		{
			return this.blockHardness * 1.5F;
		}
        return this.blockHardness;
    }
	
	public void setHarvestLevel(String toolClass, int level)
    {
		for(int i = 0 ; i < EnumStoneTypeMOnly.values().length ; i++)
		{
        	if(EnumStoneTypeMOnly.byMetadata(i).isDeep())
        	{
        		if(level < 2)
        		{
        			setHarvestLevel(toolClass, 2, this.getDefaultState().withProperty(VARIANT, EnumStoneTypeMOnly.byMetadata(i)));
        		}
        		else
        		{
        			setHarvestLevel(toolClass, level, this.getDefaultState().withProperty(VARIANT, EnumStoneTypeMOnly.byMetadata(i)));
        		}
        	}
        	else
        	{
        		setHarvestLevel(toolClass, level, this.getDefaultState().withProperty(VARIANT, EnumStoneTypeMOnly.byMetadata(i)));
        	}
        }
    }
}
