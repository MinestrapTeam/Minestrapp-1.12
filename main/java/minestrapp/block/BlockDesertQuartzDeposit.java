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

public class BlockDesertQuartzDeposit extends BlockBase implements IMetaBlockName
{
	public static final PropertyEnum<BlockDesertQuartzDeposit.DepositType> VARIANT = PropertyEnum.<BlockDesertQuartzDeposit.DepositType>create("variant", BlockDesertQuartzDeposit.DepositType.class);
	
	public BlockDesertQuartzDeposit()
	{
		super("desert_quartz_deposit", Material.ROCK, MapColor.STONE, SoundType.STONE, 3F, "pickaxe", 0);
		this.setResistance(5F);
		this.setCreativeTab(MTabs.stone);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockDesertQuartzDeposit.DepositType.DEFAULT));
	}
	
	public int damageDropped(IBlockState state)
    {
        return ((BlockDesertQuartzDeposit.DepositType)state.getValue(VARIANT)).getMetadata();
    }
	
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> tab)
    {
        for (BlockDesertQuartzDeposit.DepositType stone$enumtype : BlockDesertQuartzDeposit.DepositType.values())
        {
            tab.add(new ItemStack(this, 1, stone$enumtype.getMetadata()));
        }
    }
	
	public MapColor getMapColor(IBlockState state, IBlockAccess access, BlockPos pos)
    {
		return ((BlockDesertQuartzDeposit.DepositType)state.getValue(VARIANT)).getMapColor();
    }
	
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockDesertQuartzDeposit.DepositType.byMetadata(meta));
    }
	
	public int getMetaFromState(IBlockState state)
    {
        return ((BlockDesertQuartzDeposit.DepositType)state.getValue(VARIANT)).getMetadata();
    }
	
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT});
    }
	
	@Override
	public String getSpecialName(ItemStack stack)
	{
		return BlockDesertQuartzDeposit.DepositType.byMetadata(stack.getMetadata()).getName();
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
	}
	
	public static enum DepositType implements IStringSerializable
    {
		DEFAULT(0, "default", MapColor.SILVER_STAINED_HARDENED_CLAY),
		SAND(1, "sand", MapColor.SAND),
		COLD_SAND(2, "cold_sand", MapColor.WHITE_STAINED_HARDENED_CLAY),
		RED_SAND(3, "red_sand", MapColor.ADOBE),
		COLD_RED_SAND(4, "cold_red_sand", MapColor.RED_STAINED_HARDENED_CLAY);

        private static final BlockDesertQuartzDeposit.DepositType[] META_LOOKUP = new BlockDesertQuartzDeposit.DepositType[values().length];
        private final int meta;
        private final String name;
        private final MapColor mapColor;
        private final String unlocalizedName;

        private DepositType(int meta, String name, MapColor mapColor)
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

        public static BlockDesertQuartzDeposit.DepositType byMetadata(int meta)
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
            for (BlockDesertQuartzDeposit.DepositType blockstone$enumtype : values())
            {
                META_LOOKUP[blockstone$enumtype.getMetadata()] = blockstone$enumtype;
            }
        }
    }
}
