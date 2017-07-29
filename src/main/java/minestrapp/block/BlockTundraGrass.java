package minestrapp.block;

import java.util.Random;

import javax.annotation.Nullable;

import minestrapp.MBlocks;
import minestrapp.MItems;
import minestrapp.MTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IShearable;

public class BlockTundraGrass extends BlockBush
{
	private static final AxisAlignedBB GRASS_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D);
	public static final PropertyBool SNOWY = PropertyBool.create("snowy");
	
	public BlockTundraGrass (String name)
	{
		super(Material.VINE);
		this.setDefaultState(this.blockState.getBaseState().withProperty(SNOWY, Boolean.valueOf(false)));
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setSoundType(SoundType.PLANT);
		this.setCreativeTab(MTabs.plant);
	}
	
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        boolean snow = false;
        if(worldIn.getBlockState(pos.north()).getBlock() instanceof BlockSnow || worldIn.getBlockState(pos.east()).getBlock() instanceof BlockSnow || worldIn.getBlockState(pos.south()).getBlock() instanceof BlockSnow || worldIn.getBlockState(pos.west()).getBlock() instanceof BlockSnow || worldIn.getBlockState(pos.north()) == this.getDefaultState().withProperty(SNOWY, true) || worldIn.getBlockState(pos.east()) == this.getDefaultState().withProperty(SNOWY, true) || worldIn.getBlockState(pos.south()) == this.getDefaultState().withProperty(SNOWY, true) || worldIn.getBlockState(pos.west()) == this.getDefaultState().withProperty(SNOWY, true))
        	snow = true;
        return state.withProperty(SNOWY, snow);
    }
	
	public int getMetaFromState(IBlockState state)
    {
        return 0;
    }
	
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {SNOWY});
    }
	
	public MapColor getMapColor(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return MapColor.WOOD;
    }
	
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        IBlockState soil = worldIn.getBlockState(pos.down());
        return this.canSustainBush(soil);
    }
	
	protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() == MBlocks.permafrost || state.getBlock() == MBlocks.lichen || state.getBlock() == MBlocks.permafrost_farmland;
    }
	
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        IBlockState soil = worldIn.getBlockState(pos.down());
        return this.canSustainBush(worldIn.getBlockState(pos.down()));
    }
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return GRASS_AABB;
    }
	
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
    {
        return true;
    }
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return MItems.cabbage_seeds;
    }
	
	public int quantityDroppedWithBonus(int fortune, Random random)
    {
		int i = random.nextInt(10 - fortune);
        return i == 0 ? 1 : 0;
    }
	
	public int quantityDropped(Random random)
    {
		int i = random.nextInt(10);
        return i == 0 ? 1 : 0;
    }
	
	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
    {
        if (!worldIn.isRemote && stack.getItem() == Items.SHEARS)
        {
            player.addStat(StatList.getBlockStats(this));
            spawnAsEntity(worldIn, pos, new ItemStack(MBlocks.tundra_grass));
        }
        else
        {
            super.harvestBlock(worldIn, player, pos, state, te, stack);
        }
    }
	
	@Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos)
    {
		return EnumPlantType.Plains;
    }
}
