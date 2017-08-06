package minestrapp.block;

import java.util.Random;

import minestrapp.MBlocks;
import minestrapp.MTabs;
import minestrapp.Minestrapp5;
import minestrapp.block.item.IMetaBlockName;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockStoneSlab2 extends BlockSlab implements IMetaBlockName
{
	public static final PropertyBool SEAMLESS = PropertyBool.create("seamless");
    public static final PropertyEnum<BlockStoneSlab2.EnumType> VARIANT = PropertyEnum.<BlockStoneSlab2.EnumType>create("variant", BlockStoneSlab2.EnumType.class);
    
    public BlockStoneSlab2(String name)
    {
        super(Material.ROCK);
        IBlockState iblockstate = this.blockState.getBaseState();

        if (this.isDouble())
        {
            iblockstate = iblockstate.withProperty(SEAMLESS, Boolean.valueOf(false));
        }
        else
        {
            iblockstate = iblockstate.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
        }

        this.setDefaultState(iblockstate.withProperty(VARIANT, BlockStoneSlab2.EnumType.DEEP_OCEANSTONE_SMOOTH));
        this.setUnlocalizedName(name);
        this.setCreativeTab(MTabs.stone);
        this.useNeighborBrightness = true;
        this.setHardness(2F);
        this.setResistance(10F);
        this.setHarvestLevel("pickaxe", 0);
    }
    
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(MBlocks.stone_slab_2);
    }
    
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(MBlocks.stone_slab_2, 1, ((BlockStoneSlab2.EnumType)state.getValue(VARIANT)).getMetadata());
    }
    
    @Override
	public String getSpecialName(ItemStack stack)
	{
		return BlockStoneSlab2.EnumType.byMetadata(stack.getMetadata()).getName();
	}
    
    public IProperty<?> getVariantProperty()
    {
        return VARIANT;
    }
    
    public Comparable<?> getTypeForItem(ItemStack stack)
    {
        return BlockStoneSlab2.EnumType.byMetadata(stack.getMetadata() & 7);
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> tab)
    {
    	if(!this.isDouble())
    	{
	        for (BlockStoneSlab2.EnumType blockstoneslab$enumtype : BlockStoneSlab2.EnumType.values())
	        {
	            tab.add(new ItemStack(this, 1, blockstoneslab$enumtype.getMetadata()));
	        }
    	}
    }
    
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, BlockStoneSlab2.EnumType.byMetadata(meta & 7));

        if (this.isDouble())
        {
            iblockstate = iblockstate.withProperty(SEAMLESS, Boolean.valueOf((meta & 8) != 0));
        }
        else
        {
            iblockstate = iblockstate.withProperty(HALF, (meta & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
        }

        return iblockstate;
    }
    
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | ((BlockStoneSlab2.EnumType)state.getValue(VARIANT)).getMetadata();

        if (this.isDouble())
        {
            if (((Boolean)state.getValue(SEAMLESS)).booleanValue())
            {
                i |= 8;
            }
        }
        else if (state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP)
        {
            i |= 8;
        }

        return i;
    }
    
    protected BlockStateContainer createBlockState()
    {
        return this.isDouble() ? new BlockStateContainer(this, new IProperty[] {SEAMLESS, VARIANT}) : new BlockStateContainer(this, new IProperty[] {HALF, VARIANT});
    }
    
    public int damageDropped(IBlockState state)
    {
        return ((BlockStoneSlab2.EnumType)state.getValue(VARIANT)).getMetadata();
    }
    
    public MapColor getMapColor(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return ((BlockStoneSlab2.EnumType)state.getValue(VARIANT)).getMapColor();
    }
    
    public float getBlockHardness(IBlockState blockState, World worldIn, BlockPos pos)
    {
		if(((BlockStoneSlab2.EnumType)blockState.getValue(VARIANT)).isDeep())
		{
			return this.blockHardness * 1.5F;
		}
        return this.blockHardness;
    }
    
    public void setHarvestLevel(String toolClass, int level)
    {
		for(int i = 0 ; i < BlockStoneSlab2.EnumType.values().length ; i++)
		{
        	if(BlockStoneSlab2.EnumType.byMetadata(i).isDeep())
        	{
        		setHarvestLevel(toolClass, 2, this.getDefaultState().withProperty(VARIANT, BlockStoneSlab2.EnumType.byMetadata(i)));
        	}
        	else
        	{
        		setHarvestLevel(toolClass, level, this.getDefaultState().withProperty(VARIANT, BlockStoneSlab2.EnumType.byMetadata(i)));
        	}
        }
    }

	@Override
	public String getUnlocalizedName(int meta)
	{
		return BlockStoneSlab2.EnumType.byMetadata(meta).getName();
	}
    
    public static enum EnumType implements IStringSerializable
    {
    	DEEP_OCEANSTONE_SMOOTH(0, EnumStoneType.DEEP_OCEANSTONE.getMapColor(), "deep_oceanstone_smooth", true),
        RED_ROCK_COBBLED(1, EnumStoneType.RED_ROCK.getMapColor(), "red_rock_cobbled", false),
        DEEP_RED_ROCK_COBBLED(2, EnumStoneType.DEEP_RED_ROCK.getMapColor(), "deep_red_rock_cobbled", true),
        DEEPSTONE_COBBLED(3, EnumStoneType.DEEPSTONE.getMapColor(), "deepstone_cobbled", true),
        COLDSTONE_COBBLED(4, EnumStoneType.COLDSTONE.getMapColor(), "coldstone_cobbled", false),
        DEEP_COLDSTONE_COBBLED(5, EnumStoneType.DEEP_COLDSTONE.getMapColor(), "deep_coldstone_cobbled", true),
        ICESTONE_COBBLED(6, EnumStoneType.ICESTONE.getMapColor(), "icestone_cobbled", false),
        GLACIERROCK_COBBLED(7, EnumStoneType.GLACIERROCK.getMapColor(), "glacierrock_cobbled", true);

        private static final BlockStoneSlab2.EnumType[] META_LOOKUP = new BlockStoneSlab2.EnumType[values().length];
        private final int meta;
        private final MapColor mapColor;
        private final String name;
        private final String unlocalizedName;
        private final boolean isDeep;

        private EnumType(int meta, MapColor color, String name, boolean isDeep)
        {
            this.meta = meta;
            this.mapColor = color;
            this.name = name;
            this.unlocalizedName = name;
            this.isDeep = isDeep;
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

        public static BlockStoneSlab2.EnumType byMetadata(int meta)
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
        
        public boolean isDeep()
        {
        	return this.isDeep;
        }

        static
        {
            for (BlockStoneSlab2.EnumType blockstoneslab$enumtype : values())
            {
                META_LOOKUP[blockstoneslab$enumtype.getMetadata()] = blockstoneslab$enumtype;
            }
        }
    }
    
    @SideOnly(Side.CLIENT)
    protected static boolean isHalfSlab(IBlockState state)
    {
        Block block = state.getBlock();
        return block == MBlocks.stone_slab_2;
    }
}
