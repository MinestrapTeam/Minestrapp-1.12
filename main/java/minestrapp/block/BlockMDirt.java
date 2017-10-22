package minestrapp.block;

import minestrapp.MBlocks;
import minestrapp.MTabs;
import minestrapp.Minestrapp5;
import minestrapp.block.item.IMetaBlockName;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMDirt extends Block implements IMetaBlockName
{
	public static final PropertyEnum<BlockMDirt.DirtType> VARIANT = PropertyEnum.<BlockMDirt.DirtType>create("variant", BlockMDirt.DirtType.class);
    public static final PropertyBool SNOWY = PropertyBool.create("snowy");
    private MapColor mapColor;
	
	public BlockMDirt(String name, MapColor mapColor, SoundType soundType, float hardness, int harvestLevel)
	{
		super(Material.GROUND);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockMDirt.DirtType.DEFAULT).withProperty(SNOWY, Boolean.valueOf(false)));
		this.setUnlocalizedName(name);
		this.setRegistryName(new ResourceLocation(Minestrapp5.MODID, this.getUnlocalizedName().substring(5)));
		this.setSoundType(soundType);
		this.setHardness(hardness);
		this.setHarvestLevel("shovel", harvestLevel);
		this.setCreativeTab(MTabs.environment);
		this.mapColor = mapColor;
	}
	
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        if (state.getValue(VARIANT) == BlockMDirt.DirtType.PODZOL)
        {
            Block block = worldIn.getBlockState(pos.up()).getBlock();
            state = state.withProperty(SNOWY, Boolean.valueOf(block == Blocks.SNOW || block == Blocks.SNOW_LAYER));
        }

        return state;
    }
	
	public int damageDropped(IBlockState state)
    {
		if((BlockMDirt.DirtType)state.getValue(VARIANT) == BlockMDirt.DirtType.PODZOL)
		{
			return BlockMDirt.DirtType.DEFAULT.getMetadata();
		}
        return ((BlockMDirt.DirtType)state.getValue(VARIANT)).getMetadata();
    }
	
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> tab)
    {
        for (BlockMDirt.DirtType dirt$enumtype : BlockMDirt.DirtType.values())
        {
            tab.add(new ItemStack(this, 1, dirt$enumtype.getMetadata()));
        }
    }
	
	public MapColor getMapColor(IBlockState state, IBlockAccess access, BlockPos pos)
    {
		if(((BlockMDirt.DirtType)state.getValue(VARIANT)) == BlockMDirt.DirtType.PODZOL)
			return ((BlockMDirt.DirtType)state.getValue(VARIANT)).getMapColor();
		else
			return this.mapColor;
    }
	
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockMDirt.DirtType.byMetadata(meta));
    }
	
	public int getMetaFromState(IBlockState state)
    {
        return ((BlockMDirt.DirtType)state.getValue(VARIANT)).getMetadata();
    }
	
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT, SNOWY});
    }
	
	@Override
	public String getSpecialName(ItemStack stack)
	{
		return BlockMDirt.DirtType.byMetadata(stack.getMetadata()).getName();
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
	}
	
	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable)
    {
        IBlockState plant = plantable.getPlant(world, pos.offset(direction));
        net.minecraftforge.common.EnumPlantType plantType = plantable.getPlantType(world, pos.offset(direction));

        switch (plantType)
        {
            case Cave:   return state.isSideSolid(world, pos, EnumFacing.UP);
            case Plains: 
            	if(plantable instanceof BlockFlower || plantable instanceof BlockSapling)
            		return true;
            	else
            		return this == MBlocks.clay_soil;
            case Beach:
                boolean isBeach = this == MBlocks.clay_soil;
                boolean hasWater = (world.getBlockState(pos.east()).getMaterial() == Material.WATER ||
                                    world.getBlockState(pos.west()).getMaterial() == Material.WATER ||
                                    world.getBlockState(pos.north()).getMaterial() == Material.WATER ||
                                    world.getBlockState(pos.south()).getMaterial() == Material.WATER);
                return isBeach && hasWater;
            default: break;
        }

        return false;
    }
	
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		ItemStack itemstack = playerIn.getHeldItem(hand);
		
		if (!playerIn.canPlayerEdit(pos.offset(facing), facing, itemstack))
        {
            return false;
        }
		else if (itemstack.getItem() instanceof ItemHoe && facing != EnumFacing.DOWN && worldIn.isAirBlock(pos.up()))
        {
			worldIn.playSound(playerIn, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!worldIn.isRemote)
            {
            	itemstack.damageItem(1, playerIn);
            	switch ((BlockMDirt.DirtType)state.getValue(BlockMDirt.VARIANT))
                {
                    case DEFAULT:
                    	if(this == MBlocks.clay_soil)
        					worldIn.setBlockState(pos, MBlocks.clay_farmland.getDefaultState(), 11);
        				else if(this == MBlocks.permafrost)
        					worldIn.setBlockState(pos, MBlocks.permafrost_farmland.getDefaultState(), 11);
                        return true;
                    case COARSE:
                    	if(this == MBlocks.clay_soil)
        					worldIn.setBlockState(pos, MBlocks.clay_soil.getDefaultState().withProperty(VARIANT, BlockMDirt.DirtType.DEFAULT), 11);
        				else if(this == MBlocks.permafrost)
        					worldIn.setBlockState(pos, MBlocks.permafrost.getDefaultState().withProperty(VARIANT, BlockMDirt.DirtType.DEFAULT), 11);
                        return true;
                    default:
                    	break;
                }
            }

            return true;
        }
		
        return false;
    }
	
	public static enum DirtType implements IStringSerializable
    {
		DEFAULT(0, "default", "default", MapColor.DIRT),
        COARSE(1, "coarse", "coarse", MapColor.DIRT),
        PODZOL(2, "podzol", "podzol", MapColor.OBSIDIAN);

        private static final BlockMDirt.DirtType[] META_LOOKUP = new BlockMDirt.DirtType[values().length];
        private final int meta;
        private final String name;
        private final MapColor mapColor;
        private final String unlocalizedName;

        private DirtType(int meta, String name, String unlocalized, MapColor mapColor)
        {
            this.meta = meta;
            this.name = name;
            this.mapColor = mapColor;
            this.unlocalizedName = unlocalized;
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

        public static BlockMDirt.DirtType byMetadata(int meta)
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
            for (BlockMDirt.DirtType blockdirt$enumtype : values())
            {
                META_LOOKUP[blockdirt$enumtype.getMetadata()] = blockdirt$enumtype;
            }
        }
    }
}
