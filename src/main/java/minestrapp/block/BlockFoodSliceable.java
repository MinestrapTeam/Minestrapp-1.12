package minestrapp.block;

import java.util.Random;

import minestrapp.MItems;
import minestrapp.MTabs;
import minestrapp.block.util.BlockBaseNonSolid;
import minestrapp.item.tools.MDagger;
import minestrapp.item.util.MItemsFood;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockFoodSliceable extends BlockBaseNonSolid
{
	public static final PropertyInteger SLICES = PropertyInteger.create("slices", 0, 15);
	
	private boolean dropsItems;
	private int maxSlices;
	private MItemsFood slice = null;
	
	public BlockFoodSliceable(String name, MapColor mapColor, SoundType soundType, float hardness, int maxSlices, boolean dropsItems)
	{
		super(name, Material.CAKE, mapColor, soundType, hardness);
		this.setDefaultState(this.blockState.getBaseState().withProperty(SLICES, Integer.valueOf(0)));
		this.setPushReaction(EnumPushReaction.DESTROY);
		this.dropsItems = dropsItems;
		this.maxSlices = maxSlices;
		this.setCreativeTab(MTabs.food);
	}
	
	public BlockFoodSliceable setSlice(MItemsFood slice)
	{
		this.slice = slice;
		return this;
	}
	
	public MItemsFood getSlice()
	{
		return this.slice;
	}

	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(SLICES, meta);
    }
    
    public int getMetaFromState(IBlockState state)
    {
    	return state.getValue(SLICES).intValue();
    }
    
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {SLICES});
    }
    
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
    	ItemStack stack = playerIn.getHeldItem(hand);
    	
    	if(stack.getItem() instanceof MDagger)
    	{
    		stack.damageItem(1, playerIn);
    		if(!worldIn.isRemote && slice != null)
    			worldIn.spawnEntity(new EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, new ItemStack(slice)));
    		tryRemoveSlice(worldIn, pos, state);
    		
    		return true;
    	}
    	else if(playerIn.canEat(false) && slice != null)
    	{
    		if(!worldIn.isRemote)
    		{
    			playerIn.getFoodStats().addStats(slice.getHealAmount(new ItemStack(slice)), slice.getSaturationModifier(new ItemStack(slice)));
    			System.out.println("eaten");
    		}
    		tryRemoveSlice(worldIn, pos, state);
    		
    		return true;
    	}
    	
    	return false;
    }
    
    public void tryRemoveSlice(World worldIn, BlockPos pos, IBlockState state)
    {
    	if(state.getValue(SLICES).intValue() < maxSlices - 1)
			worldIn.setBlockState(pos, state.withProperty(SLICES, state.getValue(SLICES).intValue() + 1));
		else
			worldIn.setBlockToAir(pos);
    }
    
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
		Block block = worldIn.getBlockState(pos.down()).getBlock();
		
		if(worldIn.isSideSolid(pos.down(), EnumFacing.UP))
			return true;
		
		return false;
    }
	
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
		if(state.getValue(SLICES).intValue() >= this.maxSlices)
			worldIn.setBlockToAir(pos);
		else if(!worldIn.isRemote && !this.canPlaceBlockAt(worldIn, pos))
        {
			if(this.dropsItems)
				this.dropBlockAsItem(worldIn, pos, state, 0);
        	worldIn.setBlockToAir(pos);
        }
    }
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
		if(state.getValue(SLICES).intValue() == 0 || slice == null)
			return Item.getItemFromBlock(this);
		else
			return slice;
    }
	
	@Override public int quantityDropped(IBlockState state, int fortune, Random random)
	{
		if(state.getValue(SLICES).intValue() >= this.maxSlices)
			return 0;
		if(state.getValue(SLICES).intValue() == 0)
			return 1;
		return (maxSlices - state.getValue(SLICES).intValue());
	}
	
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        if(this.dropsItems && state.getValue(SLICES).intValue() == 0)
        	return true;
        return false;
    }
}
