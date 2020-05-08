package minestrapp.block;

import minestrapp.MTabs;
import minestrapp.block.item.IMetaBlockName;
import minestrapp.block.util.BlockBase;
import net.minecraft.block.BlockSand;
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

public class BlockMSandstone extends BlockBase implements IMetaBlockName
{
	public static final PropertyEnum<BlockMSandstone.SandstoneType> VARIANT = PropertyEnum.<BlockMSandstone.SandstoneType>create("variant", BlockMSandstone.SandstoneType.class);
	
	public BlockMSandstone()
	{
		super("m_sandstone", Material.ROCK, MapColor.SAND, SoundType.STONE, 0.8F, "pickaxe", 0);
		this.setCreativeTab(MTabs.stone);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockMSandstone.SandstoneType.SANDSTONE_BRICKS));
	}
	
	public int damageDropped(IBlockState state)
    {
        return ((BlockMSandstone.SandstoneType)state.getValue(VARIANT)).getMetadata();
    }
	
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> tab)
    {
        for (BlockMSandstone.SandstoneType stone$enumtype : BlockMSandstone.SandstoneType.values())
        {
            tab.add(new ItemStack(this, 1, stone$enumtype.getMetadata()));
        }
    }
	
	public MapColor getMapColor(IBlockState state, IBlockAccess access, BlockPos pos)
    {
		return ((BlockMSandstone.SandstoneType)state.getValue(VARIANT)).getMapColor();
    }
	
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockMSandstone.SandstoneType.byMetadata(meta));
    }
	
	public int getMetaFromState(IBlockState state)
    {
        return ((BlockMSandstone.SandstoneType)state.getValue(VARIANT)).getMetadata();
    }
	
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT});
    }
	
	@Override
	public String getSpecialName(ItemStack stack)
	{
		return BlockMSandstone.SandstoneType.byMetadata(stack.getMetadata()).getName();
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
	}
	
	public static enum SandstoneType implements IStringSerializable
    {
		SANDSTONE_BRICKS(0, "sandstone_bricks", BlockSand.EnumType.SAND.getMapColor()),
		RED_SANDSTONE_BRICKS(1, "red_sandstone_bricks", BlockSand.EnumType.RED_SAND.getMapColor()),
		COLD_SANDSTONE(2, "cold_sandstone", BlockColdSand.EnumType.SAND.getMapColor()),
		POLISHED_COLD_SANDSTONE(3, "polished_cold_sandstone", BlockColdSand.EnumType.SAND.getMapColor()),
		CHISELED_COLD_SANDSTONE(4, "chiseled_cold_sandstone", BlockColdSand.EnumType.SAND.getMapColor()),
		COLD_SANDSTONE_BRICKS(5, "cold_sandstone_bricks", BlockColdSand.EnumType.SAND.getMapColor()),
		COLD_RED_SANDSTONE(6, "cold_red_sandstone", BlockColdSand.EnumType.RED_SAND.getMapColor()),
		POLISHED_COLD_RED_SANDSTONE(7, "polished_cold_red_sandstone", BlockColdSand.EnumType.RED_SAND.getMapColor()),
        CHISELED_COLD_RED_SANDSTONE(8, "chiseled_cold_red_sandstone", BlockColdSand.EnumType.RED_SAND.getMapColor()),
        COLD_RED_SANDSTONE_BRICKS(9, "cold_red_sandstone_bricks", BlockColdSand.EnumType.RED_SAND.getMapColor());

        private static final BlockMSandstone.SandstoneType[] META_LOOKUP = new BlockMSandstone.SandstoneType[values().length];
        private final int meta;
        private final String name;
        private final MapColor mapColor;
        private final String unlocalizedName;

        private SandstoneType(int meta, String name, MapColor mapColor)
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

        public static BlockMSandstone.SandstoneType byMetadata(int meta)
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
            for (BlockMSandstone.SandstoneType blockstone$enumtype : values())
            {
                META_LOOKUP[blockstone$enumtype.getMetadata()] = blockstone$enumtype;
            }
        }
    }
}
