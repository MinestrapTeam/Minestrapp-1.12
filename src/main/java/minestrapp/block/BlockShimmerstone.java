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

public class BlockShimmerstone extends BlockBase implements IMetaBlockName
{
	public static final PropertyEnum<BlockShimmerstone.ShimmerstoneType> VARIANT = PropertyEnum.<BlockShimmerstone.ShimmerstoneType>create("variant", BlockShimmerstone.ShimmerstoneType.class);
	
	public BlockShimmerstone()
	{
		super("shimmerstone", Material.ROCK, MapColor.SNOW, SoundType.STONE, 1.2F, "pickaxe", 0);
		this.setResistance(20F);
		this.setCreativeTab(MTabs.stone);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockShimmerstone.ShimmerstoneType.STONE));
	}
	
	public float getBlockHardness(IBlockState blockState, World worldIn, BlockPos pos)
    {
		if(blockState.getValue(VARIANT) == BlockShimmerstone.ShimmerstoneType.COBBLESTONE)
			return 1.8F;
		
        return this.blockHardness;
    }
	
	public int damageDropped(IBlockState state)
    {
        return ((BlockShimmerstone.ShimmerstoneType)state.getValue(VARIANT)).getMetadata();
    }
	
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> tab)
    {
        for (BlockShimmerstone.ShimmerstoneType shimmerstone$enumtype : BlockShimmerstone.ShimmerstoneType.values())
        {
            tab.add(new ItemStack(this, 1, shimmerstone$enumtype.getMetadata()));
        }
    }
	
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockShimmerstone.ShimmerstoneType.byMetadata(meta));
    }
	
	public int getMetaFromState(IBlockState state)
    {
        return ((BlockShimmerstone.ShimmerstoneType)state.getValue(VARIANT)).getMetadata();
    }
	
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT});
    }
	
	@Override
	public String getSpecialName(ItemStack stack)
	{
		return BlockShimmerstone.ShimmerstoneType.byMetadata(stack.getMetadata()).getName();
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
	}
	
	public int getLightValue(IBlockState state)
    {
    	if(state.getValue(VARIANT) == BlockShimmerstone.ShimmerstoneType.LAMP_PURPLE)
    		return 13;
    	else if(state.getValue(VARIANT) == BlockShimmerstone.ShimmerstoneType.LAMP_GREEN || state.getValue(VARIANT) == BlockShimmerstone.ShimmerstoneType.LAMP_BLUE)
    		return 14;
    	else if(state.getValue(VARIANT) == BlockShimmerstone.ShimmerstoneType.BRICKS_MOSSY)
    		return 12;
    	else
    		return 8;
    }
	
	public static enum ShimmerstoneType implements IStringSerializable
    {
		STONE(0, "stone"),
		COBBLESTONE(1, "cobblestone"),
		BRICKS(2, "bricks"),
		BRICKS_CRACKED(3, "bricks_cracked"),
		BRICKS_MOSSY(4, "bricks_mossy"),
		TILES(5, "tiles"),
		CHISELED(6, "chiseled"),
		LAMP_BLUE(7, "lamp_blue"),
		LAMP_PURPLE(8, "lamp_purple"),
		LAMP_GREEN(9, "lamp_green");

        private static final BlockShimmerstone.ShimmerstoneType[] META_LOOKUP = new BlockShimmerstone.ShimmerstoneType[values().length];
        private final int meta;
        private final String name;
        private final String unlocalizedName;

        private ShimmerstoneType(int meta, String name)
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

        public static BlockShimmerstone.ShimmerstoneType byMetadata(int meta)
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
            for (BlockShimmerstone.ShimmerstoneType blockshimmerstone$enumtype : values())
            {
                META_LOOKUP[blockshimmerstone$enumtype.getMetadata()] = blockshimmerstone$enumtype;
            }
        }
    }
}
