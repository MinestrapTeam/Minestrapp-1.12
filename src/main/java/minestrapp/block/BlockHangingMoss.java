package minestrapp.block;

import java.util.Random;

import javax.annotation.Nullable;

import minestrapp.MBlocks;
import minestrapp.MTabs;
import minestrapp.item.tools.MDagger;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
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
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockHangingMoss extends BlockBush implements IGrowable
{
	public static final PropertyBool BOTTOM = PropertyBool.create("bottom");
	
	public BlockHangingMoss(String name)
	{
		super(Material.VINE);
		this.setDefaultState(this.blockState.getBaseState().withProperty(BOTTOM, Boolean.valueOf(false)));
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setSoundType(SoundType.PLANT);
		this.setCreativeTab(MTabs.plant);
		this.setTickRandomly(true);
	}
	
	public int tickRate(World worldIn)
    {
        return 30;
    }
	
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
		if(worldIn.getBlockState(pos.down()).getBlock() == this)
			return this.getDefaultState().withProperty(BOTTOM, Boolean.valueOf(false));
		return this.getDefaultState().withProperty(BOTTOM, Boolean.valueOf(true));
    }
	
	public int getMetaFromState(IBlockState state)
    {
        return 0;
    }
	
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {BOTTOM});
    }
	
	public MapColor getMapColor(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return MapColor.GOLD;
    }
	
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        IBlockState soil = worldIn.getBlockState(pos.up());
        
        if(soil.getBlock() == this)
        	return false;
        
        return worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos) && this.canSustainBush(soil);
    }
	
	protected boolean canSustainBush(IBlockState state)
    {
		return state.isFullBlock() || state.getBlock() == this;
    }
	
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        IBlockState soil = worldIn.getBlockState(pos.up());
        
        return this.canSustainBush(soil);
    }
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return FULL_BLOCK_AABB;
    }
	
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
    {
        return true;
    }
	
	public int quantityDropped(Random random)
    {
		return 0;
    }
	
	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
    {
        if (!worldIn.isRemote && stack.getItem() == Items.SHEARS)
        {
            player.addStat(StatList.getBlockStats(this));
            spawnAsEntity(worldIn, pos, new ItemStack(MBlocks.hanging_glow_moss));
        }
        super.harvestBlock(worldIn, player, pos, state, te, stack);
    }
	
	public Block.EnumOffsetType getOffsetType()
    {
        return Block.EnumOffsetType.XZ;
    }

	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
	{
		if(worldIn.isAirBlock(pos.down()) && !worldIn.getBlockState(pos.down().down()).isTopSolid())
			return true;
		return false;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
	{
		if(!worldIn.isRemote)
			if(this.canGrow(worldIn, pos, state, false))
				return true;
		return false;
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
	{
		worldIn.setBlockState(pos.down(), this.getDefaultState());
	}

	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
		if(!worldIn.isRemote)
		{
			if(this.canGrow(worldIn, pos, state, false))
				this.grow(worldIn, rand, pos, state);
		}
    }
}
