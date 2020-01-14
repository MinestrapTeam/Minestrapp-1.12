package minestrapp.block;

import minestrapp.MBlocks;
import minestrapp.MTabs;
import minestrapp.block.crops.BlockBerryBush;
import minestrapp.block.item.IMetaBlockName;
import minestrapp.block.util.BlockBase;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMGlowDyed extends BlockBase implements IMetaBlockName
{
	private boolean clayColors = false;
	
	public static final PropertyEnum<BlockMGlowDyed.EnumGlowDye> COLOR = PropertyEnum.<BlockMGlowDyed.EnumGlowDye>create("color", BlockMGlowDyed.EnumGlowDye.class);
	
	public BlockMGlowDyed(String name, Material material, MapColor color, SoundType sound, float hardness)
	{
		super(name, material, color, sound, hardness);
		this.setDefaultState(this.blockState.getBaseState().withProperty(COLOR, BlockMGlowDyed.EnumGlowDye.WHITE));
		this.setGlowing();
		this.setLightLevel(0.3F);
		this.setCreativeTab(MTabs.dye);
	}
	
	public BlockMGlowDyed(String name, Material material, MapColor color, SoundType sound, float hardness, String tool, int harvest)
	{
		super(name, material, color, sound, hardness, tool, harvest);
		this.setDefaultState(this.blockState.getBaseState().withProperty(COLOR, BlockMGlowDyed.EnumGlowDye.WHITE));
		this.setGlowing();
		this.setLightLevel(0.3F);
		this.setCreativeTab(MTabs.dye);
	}
	
	public int damageDropped(IBlockState state)
    {
        return ((EnumGlowDye)state.getValue(COLOR)).getMetadata();
    }
	
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        for (BlockMGlowDyed.EnumGlowDye enumdyecolor : BlockMGlowDyed.EnumGlowDye.values())
        {
            items.add(new ItemStack(this, 1, enumdyecolor.getMetadata()));
        }
    }
	
	public BlockMGlowDyed setTerracottaColored()
	{
		this.clayColors = true;
		return this;
	}
	
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
		if(clayColors)
			return state.getValue(COLOR).getClayColor();
        return state.getValue(COLOR).getMapColor();
    }
	
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(COLOR, EnumGlowDye.byMetadata(meta));
    }
	
	public int getMetaFromState(IBlockState state)
    {
        return ((EnumGlowDye)state.getValue(COLOR)).getMetadata();
    }
	
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {COLOR});
    }
	
	@Override
	public String getSpecialName(ItemStack stack)
	{
		return BlockMGlowDyed.EnumGlowDye.byMetadata(stack.getMetadata()).getName();
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
	}
	
	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable)
    {
        IBlockState plant = plantable.getPlant(world, pos.offset(direction));
        
        return this == MBlocks.glow_terracotta && plant == Blocks.DEADBUSH.getDefaultState();
    }
	
	public static enum EnumGlowDye implements IStringSerializable
    {
        WHITE(0, "glowing_white", MapColor.CLOTH, MapColor.WHITE_STAINED_HARDENED_CLAY, 14872562),
        MAGENTA(1, "glowing_magenta", MapColor.MAGENTA, MapColor.MAGENTA_STAINED_HARDENED_CLAY, 15879644),
        RED(2, "glowing_red", MapColor.RED, MapColor.RED_STAINED_HARDENED_CLAY, 16711680),
        ORANGE(3, "glowing_orange", MapColor.ADOBE, MapColor.ORANGE_STAINED_HARDENED_CLAY, 16754215),
        YELLOW(4, "glowing_yellow", MapColor.YELLOW, MapColor.YELLOW_STAINED_HARDENED_CLAY, 16771073),
        GREEN(5, "glowing_green", MapColor.LIME, MapColor.LIME_STAINED_HARDENED_CLAY, 10085664),
        CYAN(6, "glowing_cyan", MapColor.LIGHT_BLUE, MapColor.LIGHT_BLUE_STAINED_HARDENED_CLAY, 7071231),
        BLUE(7, "glowing_blue", MapColor.BLUE, MapColor.BLUE_STAINED_HARDENED_CLAY, 24831),
        PURPLE(8, "glowing_purple", MapColor.PURPLE, MapColor.PURPLE_STAINED_HARDENED_CLAY, 10748140);

        private static final BlockMGlowDyed.EnumGlowDye[] META_LOOKUP = new BlockMGlowDyed.EnumGlowDye[values().length];
        private final int meta;
        private final String name;
        private final String unlocalizedName;
        private final MapColor mapColor;
        private final MapColor clayColor;
        private final int particleColor;

        private EnumGlowDye(int metaIn, String nameIn, MapColor mapColorIn, MapColor clayColorIn, int particleColor)
        {
            this(metaIn, nameIn, nameIn, mapColorIn, clayColorIn, particleColor);
        }

        private EnumGlowDye(int metaIn, String nameIn, String unlocalizedNameIn, MapColor mapColorIn, MapColor clayColorIn, int particleColor)
        {
            this.meta = metaIn;
            this.name = nameIn;
            this.unlocalizedName = unlocalizedNameIn;
            this.mapColor = mapColorIn;
            this.clayColor = clayColorIn;
            this.particleColor = particleColor;
        }

        public int getMetadata()
        {
            return this.meta;
        }

        public MapColor getMapColor()
        {
            return this.mapColor;
        }
        
        public MapColor getClayColor()
        {
        	return this.clayColor;
        }

        public String toString()
        {
            return this.name;
        }

        public static BlockMGlowDyed.EnumGlowDye byMetadata(int meta)
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
        
        public int getParticleColor()
        {
        	return this.particleColor;
        }

        static
        {
            for (BlockMGlowDyed.EnumGlowDye blockmGlowDyed$enumtype : values())
            {
                META_LOOKUP[blockmGlowDyed$enumtype.getMetadata()] = blockmGlowDyed$enumtype;
            }
        }
    }
}
