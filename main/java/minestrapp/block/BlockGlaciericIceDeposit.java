package minestrapp.block;

import java.util.Random;

import minestrapp.MBlocks;
import minestrapp.MItems;
import minestrapp.MTabs;
import minestrapp.block.util.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGlaciericIceDeposit extends BlockBase
{
	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 5);
	
	public BlockGlaciericIceDeposit()
	{
		super("glacieric_ice_deposit", Material.PACKED_ICE, MapColor.ICE, SoundType.GLASS, -1F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(this.getAgeProperty(), Integer.valueOf(0)));
		this.setCreativeTab(MTabs.environment);
		this.setBlockUnbreakable();
		this.setResistance(9999F);
		this.setEntityInvulnerability("all");
		this.setPushReaction(EnumPushReaction.BLOCK);
		this.setSlipperiness(1F);
		this.setTickRandomly(true);
		this.setDropsItem(new ItemStack(MItems.gems, 5, 6), 5, 10, 20, false, false);
	}
	
	public IBlockState getStateFromMeta(int meta)
    {
        return this.withAge(meta);
    }
    
    public int getMetaFromState(IBlockState state)
    {
        return this.getAge(state);
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {AGE});
    }
	
	public AxisAlignedBB getBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
		return FULL_BLOCK_AABB;
    }
	
	public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing)
    {
		return BlockFaceShape.UNDEFINED;
    }
	
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
    public int tickRate(World worldIn)
    {
        return 30;
    }
	
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
	}
    
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }
    
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        Block block = worldIn.getBlockState(pos.down()).getBlock();

        return block == MBlocks.glacial_invincium;
    }
    
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        this.checkAndDropBlock(worldIn, pos, state);
    }
    
    private boolean checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!this.canPlaceBlockAt(worldIn, pos))
        {
            worldIn.setBlockToAir(pos);
            return false;
        }
        else
            return true;
    }
    
    protected PropertyInteger getAgeProperty()
    {
        return AGE;
    }
	
	public int getMaxAge()
    {
        return 5;
    }
	
	protected int getAge(IBlockState state)
    {
        return ((Integer)state.getValue(this.getAgeProperty())).intValue();
    }

    public IBlockState withAge(int age)
    {
        return this.getDefaultState().withProperty(this.getAgeProperty(), Integer.valueOf(age));
    }

    public boolean isMaxAge(IBlockState state)
    {
        return ((Integer)state.getValue(this.getAgeProperty())).intValue() >= this.getMaxAge();
    }
    
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
    	if(playerIn.getHeldItem(hand).getItem() == Items.SNOWBALL)
    	{
    		spawnParticles(worldIn, pos, worldIn.rand);
    		
    		if(!worldIn.isRemote)
	        {
	        	if(worldIn.rand.nextInt(5) == 1)
	        		this.updateTick(worldIn, pos, state, worldIn.rand);
	        	return true;
	        }
    	}
        
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }
    
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
    	int chance = 5;
    	if(worldIn.isRaining())
    		chance -= 3;
    	if(!worldIn.isDaytime())
    		chance -= 1;
    	if(rand.nextInt(chance) == 0 && this.getAge(state) < this.getMaxAge())
    		worldIn.setBlockState(pos, state.withProperty(AGE, this.getAge(state) + 1));
    	if(this.getAge(state) == this.getMaxAge())
    	{
    		growBranch(worldIn, pos.up(), rand, EnumFacing.UP);
    		worldIn.setBlockState(pos, state.withProperty(AGE, 0));
    	}
    }
    
    public void growBranch(World worldIn, BlockPos pos, Random rand, EnumFacing facing)
    {
    	IBlockState state = worldIn.getBlockState(pos);
    	
    	if(state.getBlock() instanceof BlockGlaciericIceBranch)
    	{
    		int stage = ((BlockGlaciericIceBranch)state.getBlock()).getStage();
    		
    		if(stage < 7 && (worldIn.getBlockState(pos.offset(facing.getOpposite())).getBlock() == MBlocks.glacieric_ice_deposit || (worldIn.getBlockState(pos.offset(facing.getOpposite())).getBlock() instanceof BlockGlaciericIceBranch && ((BlockGlaciericIceBranch)worldIn.getBlockState(pos.offset(facing.getOpposite())).getBlock()).getStage() > stage + 1)))
    		{
    			state = BlockGlaciericIceBranch.getBranchBlock(stage + 1).getDefaultState().withProperty(BlockGlaciericIceBranch.FACING, facing);
    			worldIn.setBlockState(pos, state);
    			stage++;
    		}
    		
    		if(stage > 0)
    		{
	    		if((worldIn.getBlockState(pos.offset(facing)).getBlock() instanceof BlockGlaciericIceBranch && worldIn.getBlockState(pos.offset(facing)).getValue(BlockGlaciericIceBranch.FACING) == facing) || worldIn.getBlockState(pos.offset(facing)).getBlock().isReplaceable(worldIn, pos.offset(facing)))
	    		{
	    			growBranch(worldIn, pos.offset(facing), rand, facing);
	    		}
	    		
	    		int horizontalSplitChance = 10;
	    		int verticalSplitChance = 30;
	    		boolean horizontalSplit = false;
	    		boolean verticalSplit = false;
	    		
	    		if(worldIn.isRaining())
	    		{
	    			horizontalSplitChance -= 3;
	    			verticalSplitChance -= 6;
	    		}
	    		if(!worldIn.isDaytime())
	    		{
	    			horizontalSplitChance -= 1;
	    			verticalSplitChance -= 2;
	    		}
	    		
	    		if(rand.nextInt(horizontalSplitChance) == 1)
	    			horizontalSplit = true;
	    		if(rand.nextInt(verticalSplitChance) == 1)
	    			verticalSplit = true;
	    		
	    		if(facing.getAxis().isVertical())
	    		{
	    			boolean alreadySplit = false;
	    			
	    			for(EnumFacing horizFacing : EnumFacing.HORIZONTALS)
	    			{
	    				if(worldIn.getBlockState(pos.offset(horizFacing)).getBlock() instanceof BlockGlaciericIceBranch && worldIn.getBlockState(pos.offset(horizFacing)).getValue(BlockGlaciericIceBranch.FACING) == horizFacing)
	    					alreadySplit = true;
	    			}
	    			
	    			if(alreadySplit)
	    			{
	    				for(EnumFacing horizFacing : EnumFacing.HORIZONTALS)
	        			{
	    					if(worldIn.getBlockState(pos.offset(horizFacing)).getBlock() instanceof BlockGlaciericIceBranch && worldIn.getBlockState(pos.offset(horizFacing)).getValue(BlockGlaciericIceBranch.FACING) == horizFacing)
		    					growBranch(worldIn, pos.offset(horizFacing), rand, horizFacing);
	        			}
	    			}
	    			else if(horizontalSplit || verticalSplit)
	    			{
	    				for(EnumFacing horizFacing : EnumFacing.HORIZONTALS)
	        			{
	    					if(worldIn.getBlockState(pos.offset(horizFacing)).getBlock().isReplaceable(worldIn, pos.offset(horizFacing)))
	    						growBranch(worldIn, pos.offset(horizFacing), rand, horizFacing);
	        			}
	    			}
	    		}
	    		else
	    		{
	    			boolean alreadySplit = false;
	    			EnumFacing checkFacing = EnumFacing.UP;
	    			
	    			for(int i = 0 ; i < 4 ; i++)
	    			{
	    				checkFacing = checkFacing.rotateAround(facing.getAxis());
	    				if(worldIn.getBlockState(pos.offset(checkFacing)).getBlock() instanceof BlockGlaciericIceBranch && worldIn.getBlockState(pos.offset(checkFacing)).getValue(BlockGlaciericIceBranch.FACING) == checkFacing)
	    					alreadySplit = true;
	    			}
	    			
	    			if(alreadySplit)
	    			{
	    				for(int i = 0 ; i < 4 ; i++)
	        			{
	        				checkFacing = checkFacing.rotateAround(facing.getAxis());
	        				if(worldIn.getBlockState(pos.offset(checkFacing)).getBlock() instanceof BlockGlaciericIceBranch && worldIn.getBlockState(pos.offset(checkFacing)).getValue(BlockGlaciericIceBranch.FACING) == checkFacing)
	        					growBranch(worldIn, pos.offset(checkFacing), rand, checkFacing);
	        			}
	    			}
	    			else
	    			{
	    				if(horizontalSplit)
	    				{
	    					if(worldIn.getBlockState(pos.offset(facing.rotateY())).getBlock().isReplaceable(worldIn, pos.offset(facing.rotateY())))
	    						growBranch(worldIn, pos.offset(facing.rotateY()), rand, facing.rotateY());
	    					if(worldIn.getBlockState(pos.offset(facing.rotateYCCW())).getBlock().isReplaceable(worldIn, pos.offset(facing.rotateYCCW())))
	    						growBranch(worldIn, pos.offset(facing.rotateYCCW()), rand, facing.rotateYCCW());
	    				}
	    				if(verticalSplit)
	    				{
	    					if(worldIn.getBlockState(pos.up()).getBlock().isReplaceable(worldIn, pos.up()))
	    						growBranch(worldIn, pos.up(), rand, EnumFacing.UP);
	    					if(worldIn.getBlockState(pos.down()).getBlock().isReplaceable(worldIn, pos.down()))
	    						growBranch(worldIn, pos.down(), rand, EnumFacing.DOWN);
	    				}
	    			}
	    		}
    		}
    	}
    	else
    	{
    		worldIn.setBlockState(pos, MBlocks.glacieric_ice_branch_0.getDefaultState().withProperty(BlockGlaciericIceBranch.FACING, facing));
    	}
    }
    
	public void spawnParticles (World worldIn, BlockPos pos, Random rand)
	{
    	for(int i = 0; i < 20 ; i++)
		{
			worldIn.spawnParticle(EnumParticleTypes.SNOW_SHOVEL, pos.getX() + rand.nextDouble(), pos.getY() + 1D, pos.getZ() + rand.nextDouble(), 0.5D - rand.nextDouble(), rand.nextDouble(), 0.5D - rand.nextDouble(), null);
		}
	}
}
