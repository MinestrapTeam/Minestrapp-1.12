package minestrapp.block;

import java.util.Random;

import javax.annotation.Nullable;

import minestrapp.MBlocks;
import minestrapp.MItems;
import minestrapp.MTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

public class BlockSavannaGrass extends BlockBush implements IGrowable
{
	public static final PropertyEnum<BlockSavannaGrass.EnumGrassSection> SECTION = PropertyEnum.<BlockSavannaGrass.EnumGrassSection>create("section", BlockSavannaGrass.EnumGrassSection.class);
	
	public BlockSavannaGrass(String name)
	{
		super(Material.VINE);
		this.setDefaultState(this.blockState.getBaseState().withProperty(SECTION, BlockSavannaGrass.EnumGrassSection.SHORT));
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setSoundType(SoundType.PLANT);
		this.setCreativeTab(MTabs.plant);
	}
	
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        if(worldIn.getBlockState(pos.down()).getBlock() instanceof BlockSavannaGrass)
        {
        	if(worldIn.getBlockState(pos.up()).getBlock() instanceof BlockSavannaGrass)
        		return this.getDefaultState().withProperty(SECTION, BlockSavannaGrass.EnumGrassSection.MIDDLE);
        	else
        		return this.getDefaultState().withProperty(SECTION, BlockSavannaGrass.EnumGrassSection.TOP);
        }
        else
        {
        	if(worldIn.getBlockState(pos.up()).getBlock() instanceof BlockSavannaGrass)
        		return this.getDefaultState().withProperty(SECTION, BlockSavannaGrass.EnumGrassSection.BOTTOM);
        	else
        		return this.getDefaultState().withProperty(SECTION, BlockSavannaGrass.EnumGrassSection.SHORT);
        }
    }
	
	public int getMetaFromState(IBlockState state)
    {
        return 0;
    }
	
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {SECTION});
    }
	
	public MapColor getMapColor(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return MapColor.WOOD;
    }
	
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        IBlockState soil = worldIn.getBlockState(pos.down());
        
        if(soil.getBlock() == MBlocks.savanna_grass)
        	return false;
        
        return this.canSustainBush(soil);
    }
	
	protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() == MBlocks.clay_soil || state.getBlock() == MBlocks.clay_grass || state.getBlock() == MBlocks.clay_farmland || state.getBlock() == Blocks.GRASS || state.getBlock() == Blocks.DIRT || state.getBlock() == Blocks.FARMLAND || state.getBlock() == MBlocks.savanna_grass;
    }
	
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        IBlockState soil = worldIn.getBlockState(pos.down());
        return this.canSustainBush(worldIn.getBlockState(pos.down()));
    }
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return FULL_BLOCK_AABB;
    }
	
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
    {
        return true;
    }
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        //TODO: Should drop Corn.
		return super.getItemDropped(state, rand, fortune);
    }
	
	public int quantityDroppedWithBonus(int fortune, Random random)
    {
		//int i = random.nextInt(10 - fortune);
        //return i == 0 ? 1 : 0;
		return 0;
    }
	
	public int quantityDropped(Random random)
    {
		//int i = random.nextInt(10);
        //return i == 0 ? 1 : 0;
		return 0;
    }
	
	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
    {
        if (!worldIn.isRemote && stack.getItem() == Items.SHEARS)
        {
            player.addStat(StatList.getBlockStats(this));
            spawnAsEntity(worldIn, pos, new ItemStack(MBlocks.savanna_grass));
        }
        else
        {
            super.harvestBlock(worldIn, player, pos, state, te, stack);
        }
    }
	
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
		int grassCount = 0;
		
		for(int i = -2 ; i < 3 ; i++)
		{
			if (worldIn.getBlockState(new BlockPos(pos.getX(), pos.getY() + i, pos.getZ())).getBlock() instanceof BlockSavannaGrass)
				grassCount++;
		}
		
		return grassCount < 3;
    }
	
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        return true;
    }
	
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
		BlockPos newpos = pos;
		
		for(int i = 1 ; i < 3 ; i++)
		{
			newpos = new BlockPos(pos.getX(), pos.getY() + i, pos.getZ());
			
			if(worldIn.getBlockState(newpos).getBlock() != MBlocks.savanna_grass && worldIn.getBlockState(newpos).getBlock().isReplaceable(worldIn, newpos))
				break;
		}
		
		worldIn.setBlockState(newpos, MBlocks.savanna_grass.getDefaultState());
    }
	
	public Block.EnumOffsetType getOffsetType()
    {
        return Block.EnumOffsetType.XZ;
    }
	
	@Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos)
    {
		return EnumPlantType.Plains;
    }
	
	public static enum EnumGrassSection implements IStringSerializable
    {
		SHORT("short"),
        BOTTOM("bottom"),
        MIDDLE("middle"),
        TOP("top");

        private static final BlockSavannaGrass.EnumGrassSection[] META_LOOKUP = new BlockSavannaGrass.EnumGrassSection[values().length];
        private final String name;

        private EnumGrassSection(String name)
        {
            this.name = name;
        }

        public String toString()
        {
            return this.name;
        }

        public String getName()
        {
            return this.name;
        }
    }
}
