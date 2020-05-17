package minestrapp.block;

import minestrapp.Minestrapp;
import minestrapp.block.item.IMetaBlockName;
import net.minecraft.block.BlockFalling;
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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockColdSand extends BlockFalling implements IMetaBlockName
{
	public static final PropertyEnum<BlockColdSand.EnumType> VARIANT = PropertyEnum.<BlockColdSand.EnumType>create("variant", BlockColdSand.EnumType.class);
	
	public BlockColdSand(String name, Material material, SoundType soundType, float hardness, String tool, int harvestLevel)
	{
		super(material);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockColdSand.EnumType.SAND));
		this.setUnlocalizedName(name);
		this.setRegistryName(new ResourceLocation(Minestrapp.MODID, this.getUnlocalizedName().substring(5)));
		this.setSoundType(soundType);
		this.setHardness(hardness);
		this.setHarvestLevel(tool, harvestLevel);
	}

	public int damageDropped(IBlockState state)
    {
        return ((BlockColdSand.EnumType)state.getValue(VARIANT)).getMetadata();
    }
	
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> tab)
    {
        for (BlockColdSand.EnumType blocksand$enumtype : BlockColdSand.EnumType.values())
        {
            tab.add(new ItemStack(this, 1, blocksand$enumtype.getMetadata()));
        }
    }
	
	public MapColor getMapColor(IBlockState state, IBlockAccess access, BlockPos pos)
    {
        return ((BlockColdSand.EnumType)state.getValue(VARIANT)).getMapColor();
    }
	
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockColdSand.EnumType.byMetadata(meta));
    }
	
	public int getMetaFromState(IBlockState state)
    {
        return ((BlockColdSand.EnumType)state.getValue(VARIANT)).getMetadata();
    }
	
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT});
    }
	
	@SideOnly(Side.CLIENT)
    public int getDustColor(IBlockState state)
    {
        BlockColdSand.EnumType blocksand$enumtype = (BlockColdSand.EnumType)state.getValue(VARIANT);
        return blocksand$enumtype.getDustColor();
    }
	
	public static enum EnumType implements IStringSerializable
    {
        SAND(0, "default", "default", MapColor.WHITE_STAINED_HARDENED_CLAY, -5464706),
        RED_SAND(1, "red", "red", MapColor.RED_STAINED_HARDENED_CLAY, -8568786);

        private static final BlockColdSand.EnumType[] META_LOOKUP = new BlockColdSand.EnumType[values().length];
        private final int meta;
        private final String name;
        private final MapColor mapColor;
        private final String unlocalizedName;
        private final int dustColor;

        private EnumType(int meta, String name, String unlocalized, MapColor mapColor, int dustColor)
        {
            this.meta = meta;
            this.name = name;
            this.mapColor = mapColor;
            this.unlocalizedName = unlocalized;
            this.dustColor = dustColor;
        }

        @SideOnly(Side.CLIENT)
        public int getDustColor()
        {
            return this.dustColor;
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

        public static BlockColdSand.EnumType byMetadata(int meta)
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
            for (BlockColdSand.EnumType blocksand$enumtype : values())
            {
                META_LOOKUP[blocksand$enumtype.getMetadata()] = blocksand$enumtype;
            }
        }
    }

	@Override
	public String getSpecialName(ItemStack stack)
	{
		return BlockColdSand.EnumType.byMetadata(stack.getMetadata()).getName();
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
	}
}
