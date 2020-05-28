package minestrapp.block;

import minestrapp.block.item.IMetaBlockName;
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

public class BlockPlateMetal extends BlockPlate implements IMetaBlockName
{
	public static final PropertyEnum<BlockPlateMetal.EnumType> VARIANT = PropertyEnum.<BlockPlateMetal.EnumType>create("variant", BlockPlateMetal.EnumType.class);
	
	public BlockPlateMetal()
	{
		super("plate_metal", Material.IRON, MapColor.IRON, SoundType.METAL, 2F, 2, 2, "pickaxe", 1);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockPlateMetal.EnumType.COPPER));
	}
	
	public int damageDropped(IBlockState state)
    {
        return ((BlockPlateMetal.EnumType)state.getValue(VARIANT)).getMetadata();
    }
	
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        for (BlockPlateMetal.EnumType blockplanks$enumtype : BlockPlateMetal.EnumType.values())
        {
            items.add(new ItemStack(this, 1, blockplanks$enumtype.getMetadata()));
        }
    }
	
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockPlateMetal.EnumType.byMetadata(meta));
    }
	
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return ((BlockPlateMetal.EnumType)state.getValue(VARIANT)).getMapColor();
    }
	
	public int getMetaFromState(IBlockState state)
    {
        return ((BlockPlateMetal.EnumType)state.getValue(VARIANT)).getMetadata();
    }
	
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT});
    }
	
	public static enum EnumType implements IStringSerializable
    {
        COPPER(0, "copper", MapColor.ADOBE),
        BRONZE(1, "bronze", MapColor.YELLOW_STAINED_HARDENED_CLAY),
        STEEL(2, "steel", MapColor.LIGHT_BLUE_STAINED_HARDENED_CLAY),
        GOLD(3, "gold", MapColor.GOLD);

        private static final BlockPlateMetal.EnumType[] META_LOOKUP = new BlockPlateMetal.EnumType[values().length];
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

        public static BlockPlateMetal.EnumType byMetadata(int meta)
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
            for (BlockPlateMetal.EnumType blockplanks$enumtype : values())
            {
                META_LOOKUP[blockplanks$enumtype.getMetadata()] = blockplanks$enumtype;
            }
        }
    }

	@Override
	public String getSpecialName(ItemStack stack)
	{
		return BlockPlateMetal.EnumType.byMetadata(stack.getMetadata()).getName();
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
	}
}
