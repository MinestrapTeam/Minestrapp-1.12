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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDecorativeStones extends BlockBase implements IMetaBlockName
{
	public static final PropertyEnum<BlockDecorativeStones.DecorStoneType> VARIANT = PropertyEnum.<BlockDecorativeStones.DecorStoneType>create("variant", BlockDecorativeStones.DecorStoneType.class);
	
	public BlockDecorativeStones()
	{
		super("decor_stone", Material.ROCK, MapColor.STONE, SoundType.STONE, 1.5F, "pickaxe", 0);
		this.setResistance(10F);
		this.setCreativeTab(MTabs.stone);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockDecorativeStones.DecorStoneType.SLATE));
	}
	
	public int damageDropped(IBlockState state)
    {
        return ((BlockDecorativeStones.DecorStoneType)state.getValue(VARIANT)).getMetadata();
    }
	
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> tab)
    {
        for (BlockDecorativeStones.DecorStoneType stone$enumtype : BlockDecorativeStones.DecorStoneType.values())
        {
            tab.add(new ItemStack(this, 1, stone$enumtype.getMetadata()));
        }
    }
	
	public MapColor getMapColor(IBlockState state, IBlockAccess access, BlockPos pos)
    {
		return ((BlockDecorativeStones.DecorStoneType)state.getValue(VARIANT)).getMapColor();
    }
	
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockDecorativeStones.DecorStoneType.byMetadata(meta));
    }
	
	public int getMetaFromState(IBlockState state)
    {
        return ((BlockDecorativeStones.DecorStoneType)state.getValue(VARIANT)).getMetadata();
    }
	
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT});
    }
	
	@Override
	public String getSpecialName(ItemStack stack)
	{
		return BlockDecorativeStones.DecorStoneType.byMetadata(stack.getMetadata()).getName();
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
	}
	
	public static enum DecorStoneType implements IStringSerializable
    {
		GRANITE_BRICKS(0, "granite_bricks", MapColor.DIRT),
		GRANITE_TILES(1, "granite_tiles", MapColor.DIRT),
		DIORITE_BRICKS(2, "diorite_bricks", MapColor.QUARTZ),
		DIORITE_TILES(3, "diorite_tiles", MapColor.QUARTZ),
		ANDESITE_BRICKS(4, "andesite_bricks", MapColor.STONE),
		ANDESITE_TILES(5, "andesite_tiles", MapColor.STONE),
		SLATE(6, "slate", MapColor.GRAY),
        POLISHED_SLATE(7, "polished_slate", MapColor.GRAY),
        SLATE_BRICKS(8, "slate_bricks", MapColor.GRAY),
		SLATE_TILES(9, "slate_tiles", MapColor.GRAY);

        private static final BlockDecorativeStones.DecorStoneType[] META_LOOKUP = new BlockDecorativeStones.DecorStoneType[values().length];
        private final int meta;
        private final String name;
        private final MapColor mapColor;
        private final String unlocalizedName;

        private DecorStoneType(int meta, String name, MapColor mapColor)
        {
            this.meta = meta;
            this.name = name;
            this.mapColor = mapColor;
            this.unlocalizedName = name;
        }

        public int getMetadata()
        {
            return this.meta;
        }

        public String toString()
        {
            return this.name;
        }

        public MapColor getMapColor()
        {
            return this.mapColor;
        }

        public static BlockDecorativeStones.DecorStoneType byMetadata(int meta)
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
        
        public String getRegisryName()
        {
        	return this.unlocalizedName;
        }

        static
        {
            for (BlockDecorativeStones.DecorStoneType blockstone$enumtype : values())
            {
                META_LOOKUP[blockstone$enumtype.getMetadata()] = blockstone$enumtype;
            }
        }
    }
}
