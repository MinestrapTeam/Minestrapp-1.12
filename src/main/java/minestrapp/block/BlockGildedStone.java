package minestrapp.block;

import minestrapp.MTabs;
import minestrapp.block.item.IMetaBlockName;
import minestrapp.block.util.BlockBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
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

public class BlockGildedStone extends BlockBase implements IMetaBlockName
{
	public static final PropertyEnum<BlockGildedStone.EnumType> VARIANT = PropertyEnum.<BlockGildedStone.EnumType>create("variant", BlockGildedStone.EnumType.class);
	
	public BlockGildedStone()
	{
		super("gilded_stone", Material.ROCK, MapColor.GOLD, SoundType.METAL, 50F, "pickaxe", 4);
		this.setResistance(160000F);
		this.setCreativeTab(MTabs.stone);
		this.setPushReaction(EnumPushReaction.BLOCK);
	}
	
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        if (meta == BlockGildedStone.EnumType.LINES_Y.getMetadata())
        {
            switch (facing.getAxis())
            {
                case Z:
                    return this.getDefaultState().withProperty(VARIANT, BlockGildedStone.EnumType.LINES_Z);
                case X:
                    return this.getDefaultState().withProperty(VARIANT, BlockGildedStone.EnumType.LINES_X);
                case Y:
                    return this.getDefaultState().withProperty(VARIANT, BlockGildedStone.EnumType.LINES_Y);
            }
        }

        return meta == BlockGildedStone.EnumType.BRICKS.getMetadata() ? this.getDefaultState().withProperty(VARIANT, BlockGildedStone.EnumType.BRICKS) : meta == BlockGildedStone.EnumType.TILES.getMetadata() ? this.getDefaultState().withProperty(VARIANT, BlockGildedStone.EnumType.TILES) : meta == BlockGildedStone.EnumType.PILLAR_BOTTOM.getMetadata() ? this.getDefaultState().withProperty(VARIANT, BlockGildedStone.EnumType.PILLAR_BOTTOM) : this.getDefaultState().withProperty(VARIANT, BlockGildedStone.EnumType.PILLAR_TOP);
    }
	
	public int damageDropped(IBlockState state)
    {
        BlockGildedStone.EnumType blockquartz$enumtype = (BlockGildedStone.EnumType)state.getValue(VARIANT);
        return blockquartz$enumtype != BlockGildedStone.EnumType.LINES_X && blockquartz$enumtype != BlockGildedStone.EnumType.LINES_Z ? blockquartz$enumtype.getMetadata() : BlockGildedStone.EnumType.LINES_Y.getMetadata();
    }
	
	protected ItemStack getSilkTouchDrop(IBlockState state)
    {
        BlockGildedStone.EnumType blockquartz$enumtype = (BlockGildedStone.EnumType)state.getValue(VARIANT);
        return blockquartz$enumtype != BlockGildedStone.EnumType.LINES_X && blockquartz$enumtype != BlockGildedStone.EnumType.LINES_Z ? super.getSilkTouchDrop(state) : new ItemStack(Item.getItemFromBlock(this), 1, BlockGildedStone.EnumType.LINES_Y.getMetadata());
    }
	
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        items.add(new ItemStack(this, 1, BlockGildedStone.EnumType.BRICKS.getMetadata()));
        items.add(new ItemStack(this, 1, BlockGildedStone.EnumType.TILES.getMetadata()));
        items.add(new ItemStack(this, 1, BlockGildedStone.EnumType.PILLAR_TOP.getMetadata()));
        items.add(new ItemStack(this, 1, BlockGildedStone.EnumType.PILLAR_BOTTOM.getMetadata()));
        items.add(new ItemStack(this, 1, BlockGildedStone.EnumType.LINES_Y.getMetadata()));
    }
	
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockGildedStone.EnumType.byMetadata(meta));
    }
	
	public int getMetaFromState(IBlockState state)
    {
        return ((BlockGildedStone.EnumType)state.getValue(VARIANT)).getMetadata();
    }
	
	public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        switch (rot)
        {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:

                switch ((BlockGildedStone.EnumType)state.getValue(VARIANT))
                {
                    case LINES_X:
                        return state.withProperty(VARIANT, BlockGildedStone.EnumType.LINES_Z);
                    case LINES_Z:
                        return state.withProperty(VARIANT, BlockGildedStone.EnumType.LINES_X);
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
        BRICKS(0, "bricks", "bricks"),
        TILES(1, "tiles", "tiles"),
        PILLAR_TOP(2, "pillar_cap_top", "pillar_cap_top"),
        PILLAR_BOTTOM(3, "pillar_cap_bottom", "pillar_cap_bottom"),
        LINES_Y(4, "lines_y", "pillar"),
        LINES_X(5, "lines_x", "pillar"),
        LINES_Z(6, "lines_z", "pillar");

        private static final BlockGildedStone.EnumType[] META_LOOKUP = new BlockGildedStone.EnumType[values().length];
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

        public static BlockGildedStone.EnumType byMetadata(int meta)
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
            for (BlockGildedStone.EnumType blockquartz$enumtype : values())
            {
                META_LOOKUP[blockquartz$enumtype.getMetadata()] = blockquartz$enumtype;
            }
        }
    }

	@Override
	public String getSpecialName(ItemStack stack)
	{
		return BlockGildedStone.EnumType.byMetadata(stack.getMetadata()).toString();
	}
}
