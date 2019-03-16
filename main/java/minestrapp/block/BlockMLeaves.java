package minestrapp.block;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import minestrapp.block.item.IMetaBlockName;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockMLeaves extends BlockLeaves implements IMetaBlockName
{
	public static final PropertyEnum<BlockMPlanks.EnumType> VARIANT = PropertyEnum.<BlockMPlanks.EnumType>create("variant", BlockMPlanks.EnumType.class, new Predicate<BlockMPlanks.EnumType>()
    {
        public boolean apply(@Nullable BlockMPlanks.EnumType p_apply_1_)
        {
            return p_apply_1_.getMetadata() <= 2;
        }
    });
	
	public BlockMLeaves()
    {
		this.setUnlocalizedName("m_leaves");
		this.setRegistryName("m_leaves");
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockMPlanks.EnumType.REDWOOD).withProperty(DECAYABLE, false).withProperty(CHECK_DECAY, false));
    }
	
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        items.add(new ItemStack(this, 1, BlockMPlanks.EnumType.REDWOOD.getMetadata()));
        items.add(new ItemStack(this, 1, BlockMPlanks.EnumType.FROZEN_OAK.getMetadata()));
    }
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(Blocks.SAPLING);
    }
	
	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
	{
		return null;
	}

	@Override
	public String getSpecialName(ItemStack stack)
	{
		return null;
	}

	@Override
	public EnumType getWoodType(int meta)
	{
		return null;
	}
}
