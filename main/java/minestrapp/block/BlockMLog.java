package minestrapp.block;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import minestrapp.block.item.IMetaBlockName;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockMLog extends BlockLog implements IMetaBlockName
{	
	public static final PropertyEnum<BlockMPlanks.EnumType> VARIANT = PropertyEnum.<BlockMPlanks.EnumType>create("variant", BlockMPlanks.EnumType.class, new Predicate<BlockMPlanks.EnumType>()
    {
        public boolean apply(@Nullable BlockMPlanks.EnumType p_apply_1_)
        {
            return p_apply_1_.getMetadata() <= 3;
        }
    });
	
	public BlockMLog()
    {
		this.setUnlocalizedName("m_log");
		this.setRegistryName("m_log");
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockMPlanks.EnumType.REDWOOD).withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
    }
	
	@Override public boolean canSustainLeaves(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos){ return state.getValue(VARIANT) != BlockMPlanks.EnumType.CHARWOOD; }
	
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        BlockMPlanks.EnumType blockplanks$enumtype = (BlockMPlanks.EnumType)state.getValue(VARIANT);

        switch ((BlockLog.EnumAxis)state.getValue(LOG_AXIS))
        {
            case X:
            case Z:
            case NONE:
            default:

                switch (blockplanks$enumtype)
                {
                    case REDWOOD:
                    default:
                        return MapColor.NETHERRACK;
                    case FROZEN_OAK:
                        return MapColor.LIGHT_BLUE_STAINED_HARDENED_CLAY;
                    case CHARWOOD:
                        return MapColor.BLACK_STAINED_HARDENED_CLAY;
                    case PALM:
                    	return MapColor.WHITE_STAINED_HARDENED_CLAY;
                }

            case Y:
                return blockplanks$enumtype.getMapColor();
        }
    }
	
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        items.add(new ItemStack(this, 1, BlockMPlanks.EnumType.REDWOOD.getMetadata()));
        items.add(new ItemStack(this, 1, BlockMPlanks.EnumType.FROZEN_OAK.getMetadata()));
        items.add(new ItemStack(this, 1, BlockMPlanks.EnumType.CHARWOOD.getMetadata()));
        items.add(new ItemStack(this, 1, BlockMPlanks.EnumType.PALM.getMetadata()));
    }
	
	public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, BlockMPlanks.EnumType.byMetadata((meta & 3) % 4));

        switch (meta & 12)
        {
            case 0:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y);
                break;
            case 4:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
                break;
            case 8:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
                break;
            default:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
        }

        return iblockstate;
    }
	
	@SuppressWarnings("incomplete-switch")
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | ((BlockMPlanks.EnumType)state.getValue(VARIANT)).getMetadata();

        switch ((BlockLog.EnumAxis)state.getValue(LOG_AXIS))
        {
            case X:
                i |= 4;
                break;
            case Z:
                i |= 8;
                break;
            case NONE:
                i |= 12;
        }

        return i;
    }
	
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT, LOG_AXIS});
    }

    protected ItemStack getSilkTouchDrop(IBlockState state)
    {
        return new ItemStack(Item.getItemFromBlock(this), 1, ((BlockMPlanks.EnumType)state.getValue(VARIANT)).getMetadata());
    }
    
    public int damageDropped(IBlockState state)
    {
        return ((BlockMPlanks.EnumType)state.getValue(VARIANT)).getMetadata();
    }

    @Override
	public String getSpecialName(ItemStack stack)
	{
		return BlockMPlanks.EnumType.byMetadata(stack.getMetadata()).getName();
	}
    
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
		if(world.getBlockState(pos).getValue(VARIANT) != BlockMPlanks.EnumType.CHARWOOD)
			return 5;
		else
			return super.getFlammability(world, pos, face);
    }
	
	public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
		if(world.getBlockState(pos).getValue(VARIANT) != BlockMPlanks.EnumType.CHARWOOD)
			return 5;
		else
			return super.getFireSpreadSpeed(world, pos, face);
    }
}
