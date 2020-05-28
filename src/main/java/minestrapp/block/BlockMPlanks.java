package minestrapp.block;

import minestrapp.MTabs;
import minestrapp.block.item.IMetaBlockName;
import minestrapp.block.util.BlockBase;
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
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMPlanks extends BlockBase implements IMetaBlockName
{
	public static final PropertyEnum<BlockMPlanks.EnumType> VARIANT = PropertyEnum.<BlockMPlanks.EnumType>create("variant", BlockMPlanks.EnumType.class);
	
	public BlockMPlanks(String name)
	{
		super(name, Material.WOOD, MapColor.WOOD, SoundType.WOOD, 2F, "axe", 0);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockMPlanks.EnumType.REDWOOD));
		this.setResistance(5F);
		this.setCreativeTab(MTabs.wood);
		this.setFlammable(20, 5, 0).setFlammable(20, 5, 1).setFlammable(20, 5, 3);
	}

	public int damageDropped(IBlockState state)
    {
        return ((BlockMPlanks.EnumType)state.getValue(VARIANT)).getMetadata();
    }
	
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        for (BlockMPlanks.EnumType blockplanks$enumtype : BlockMPlanks.EnumType.values())
        {
            items.add(new ItemStack(this, 1, blockplanks$enumtype.getMetadata()));
        }
    }
	
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockMPlanks.EnumType.byMetadata(meta));
    }
	
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return ((BlockMPlanks.EnumType)state.getValue(VARIANT)).getMapColor();
    }
	
	public int getMetaFromState(IBlockState state)
    {
        return ((BlockMPlanks.EnumType)state.getValue(VARIANT)).getMetadata();
    }
	
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT});
    }
	
	public static enum EnumType implements IStringSerializable
    {
        REDWOOD(0, "redwood", MapColor.RED_STAINED_HARDENED_CLAY),
        FROZEN_OAK(1, "frozen_oak", MapColor.LIGHT_BLUE),
        CHARWOOD(2, "charwood", MapColor.GRAY_STAINED_HARDENED_CLAY),
		PALM(3, "palm", MapColor.WHITE_STAINED_HARDENED_CLAY);

        private static final BlockMPlanks.EnumType[] META_LOOKUP = new BlockMPlanks.EnumType[values().length];
        private final int meta;
        private final String name;
        private final String unlocalizedName;
        private final MapColor mapColor;

        private EnumType(int metaIn, String nameIn, MapColor mapColorIn)
        {
            this(metaIn, nameIn, nameIn, mapColorIn);
        }

        private EnumType(int metaIn, String nameIn, String unlocalizedNameIn, MapColor mapColorIn)
        {
            this.meta = metaIn;
            this.name = nameIn;
            this.unlocalizedName = unlocalizedNameIn;
            this.mapColor = mapColorIn;
        }

        public int getMetadata()
        {
            return this.meta;
        }

        public MapColor getMapColor()
        {
            return this.mapColor;
        }

        public String toString()
        {
            return this.name;
        }

        public static BlockMPlanks.EnumType byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        public String getName()
        {
            return this.name;
        }

        public String getUnlocalizedName()
        {
            return this.unlocalizedName;
        }

        static
        {
            for (BlockMPlanks.EnumType blockplanks$enumtype : values())
            {
                META_LOOKUP[blockplanks$enumtype.getMetadata()] = blockplanks$enumtype;
            }
        }
    }

	@Override
	public String getSpecialName(ItemStack stack)
	{
		return BlockMPlanks.EnumType.byMetadata(stack.getMetadata()).getName();
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(state));
	}
}
