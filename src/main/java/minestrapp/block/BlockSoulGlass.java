package minestrapp.block;

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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockSoulGlass extends BlockBase implements IMetaBlockName
{
	public static final PropertyEnum<BlockSoulGlass.EnumType> VARIANT = PropertyEnum.<BlockSoulGlass.EnumType>create("variant", BlockSoulGlass.EnumType.class);
	
	public BlockSoulGlass(String name, MapColor mapColor)
	{
		super(name, Material.ROCK, mapColor, SoundType.GLASS, 1.2F, "pickaxe", 0);
		this.setLightOpacity(1);
	}
	
	public boolean isFullCube(IBlockState state)
    {
        return false;
    }
	
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        if (meta == BlockSoulGlass.EnumType.LINES_Y.getMetadata())
        {
            switch (facing.getAxis())
            {
                case Z:
                    return this.getDefaultState().withProperty(VARIANT, BlockSoulGlass.EnumType.LINES_Z);
                case X:
                    return this.getDefaultState().withProperty(VARIANT, BlockSoulGlass.EnumType.LINES_X);
                case Y:
                    return this.getDefaultState().withProperty(VARIANT, BlockSoulGlass.EnumType.LINES_Y);
            }
        }

        return meta == BlockSoulGlass.EnumType.TILES.getMetadata() ? this.getDefaultState().withProperty(VARIANT, BlockSoulGlass.EnumType.TILES) : this.getDefaultState().withProperty(VARIANT, BlockSoulGlass.EnumType.POLISHED);
    }
	
	public int damageDropped(IBlockState state)
    {
        BlockSoulGlass.EnumType blockquartz$enumtype = (BlockSoulGlass.EnumType)state.getValue(VARIANT);
        return blockquartz$enumtype != BlockSoulGlass.EnumType.LINES_X && blockquartz$enumtype != BlockSoulGlass.EnumType.LINES_Z ? blockquartz$enumtype.getMetadata() : BlockSoulGlass.EnumType.LINES_Y.getMetadata();
    }
	
	protected ItemStack getSilkTouchDrop(IBlockState state)
    {
        BlockSoulGlass.EnumType blockquartz$enumtype = (BlockSoulGlass.EnumType)state.getValue(VARIANT);
        return blockquartz$enumtype != BlockSoulGlass.EnumType.LINES_X && blockquartz$enumtype != BlockSoulGlass.EnumType.LINES_Z ? super.getSilkTouchDrop(state) : new ItemStack(Item.getItemFromBlock(this), 1, BlockSoulGlass.EnumType.LINES_Y.getMetadata());
    }
	
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        items.add(new ItemStack(this, 1, BlockSoulGlass.EnumType.POLISHED.getMetadata()));
        items.add(new ItemStack(this, 1, BlockSoulGlass.EnumType.TILES.getMetadata()));
        items.add(new ItemStack(this, 1, BlockSoulGlass.EnumType.LINES_Y.getMetadata()));
    }
	
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockSoulGlass.EnumType.byMetadata(meta));
    }
	
	public int getMetaFromState(IBlockState state)
    {
        return ((BlockSoulGlass.EnumType)state.getValue(VARIANT)).getMetadata();
    }
	
	public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        switch (rot)
        {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:

                switch ((BlockSoulGlass.EnumType)state.getValue(VARIANT))
                {
                    case LINES_X:
                        return state.withProperty(VARIANT, BlockSoulGlass.EnumType.LINES_Z);
                    case LINES_Z:
                        return state.withProperty(VARIANT, BlockSoulGlass.EnumType.LINES_X);
                    default:
                        return state;
                }

            default:
                return state;
        }
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT});
    }

    public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis)
    {
        IBlockState state = world.getBlockState(pos);
        for (IProperty prop : state.getProperties().keySet())
        {
            if (prop.getName().equals("variant") && prop.getValueClass() == EnumType.class)
            {
                EnumType current = (EnumType)state.getValue(prop);
                EnumType next = current == EnumType.LINES_X ? EnumType.LINES_Y :
                                current == EnumType.LINES_Y ? EnumType.LINES_Z :
                                current == EnumType.LINES_Z ? EnumType.LINES_X : current;
                if (next == current)
                    return false;
                world.setBlockState(pos, state.withProperty(prop, next));
                return true;
            }
        }
        return false;
    }

	public static enum EnumType implements IStringSerializable
    {
        POLISHED(0, "polished", "polished"),
        TILES(1, "tiles", "tiles"),
        LINES_Y(2, "lines_y", "pillar"),
        LINES_X(3, "lines_x", "pillar"),
        LINES_Z(4, "lines_z", "pillar");

        private static final BlockSoulGlass.EnumType[] META_LOOKUP = new BlockSoulGlass.EnumType[values().length];
        private final int meta;
        private final String serializedName;
        private final String unlocalizedName;

        private EnumType(int meta, String name, String unlocalizedName)
        {
            this.meta = meta;
            this.serializedName = name;
            this.unlocalizedName = unlocalizedName;
        }

        public int getMetadata()
        {
            return this.meta;
        }

        public String toString()
        {
            return this.unlocalizedName;
        }

        public static BlockSoulGlass.EnumType byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        public String getName()
        {
            return this.serializedName;
        }

        static
        {
            for (BlockSoulGlass.EnumType blockquartz$enumtype : values())
            {
                META_LOOKUP[blockquartz$enumtype.getMetadata()] = blockquartz$enumtype;
            }
        }
    }

	@Override
	public String getSpecialName(ItemStack stack)
	{
		return BlockSoulGlass.EnumType.byMetadata(stack.getMetadata()).toString();
	}
}
