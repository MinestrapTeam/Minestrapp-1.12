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
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPortar extends BlockBase implements IMetaBlockName
{
	public static final PropertyEnum<BlockPortar.PortarType> VARIANT = PropertyEnum.<BlockPortar.PortarType>create("variant", BlockPortar.PortarType.class);
	
	public BlockPortar()
	{
		super("portar", Material.GROUND, MapColor.PURPLE, SoundType.GLASS, 1.2F, "pickaxe", 0);
		this.setResistance(5F);
		this.setCreativeTab(MTabs.stone);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockPortar.PortarType.SLABBED));
	}
	
	public int damageDropped(IBlockState state)
    {
        return ((BlockPortar.PortarType)state.getValue(VARIANT)).getMetadata();
    }
	
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> tab)
    {
        for (BlockPortar.PortarType portar$enumtype : BlockPortar.PortarType.values())
        {
            tab.add(new ItemStack(this, 1, portar$enumtype.getMetadata()));
        }
    }
	
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockPortar.PortarType.byMetadata(meta));
    }
	
	public int getMetaFromState(IBlockState state)
    {
        return ((BlockPortar.PortarType)state.getValue(VARIANT)).getMetadata();
    }
	
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT});
    }
	
	@Override
	public String getSpecialName(ItemStack stack)
	{
		return BlockPortar.PortarType.byMetadata(stack.getMetadata()).getName();
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
	}
	
	public int getLightValue(IBlockState state)
    {
    	if(state.getValue(VARIANT) == BlockPortar.PortarType.LAMP_SUNSTONE)
    		return 13;
    	else if(state.getValue(VARIANT) == BlockPortar.PortarType.LAMP_GLOWSTONE || state.getValue(VARIANT) == BlockPortar.PortarType.LAMP_SEA)
    		return 14;
    	else
    		return 0;
    }
	
	public static enum PortarType implements IStringSerializable
    {
		SLABBED(0, "slabbed"),
		FISHBONED(1, "fishboned"),
		TILED(2, "tiled"),
		CHISELED(3, "chiseled"),
		LAMP_SUNSTONE(4, "lamp_sunstone"),
		LAMP_GLOWSTONE(5, "lamp_glowstone"),
		LAMP_SEA(6, "lamp_sea");

        private static final BlockPortar.PortarType[] META_LOOKUP = new BlockPortar.PortarType[values().length];
        private final int meta;
        private final String name;
        private final String unlocalizedName;

        private PortarType(int meta, String name)
        {
            this.meta = meta;
            this.name = name;
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

        public static BlockPortar.PortarType byMetadata(int meta)
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
            for (BlockPortar.PortarType blockportar$enumtype : values())
            {
                META_LOOKUP[blockportar$enumtype.getMetadata()] = blockportar$enumtype;
            }
        }
    }
}
