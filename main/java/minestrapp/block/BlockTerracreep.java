package minestrapp.block;

import java.util.Random;

import javax.annotation.Nullable;

import minestrapp.MBlocks;
import minestrapp.MTabs;
import minestrapp.item.tools.MDagger;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockOre;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTerracreep extends BlockBush implements IGrowable
{
	public static final PropertyBool UP = PropertyBool.create("up");
	public static final PropertyBool DOWN = PropertyBool.create("down");
	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool EAST = PropertyBool.create("east");
	public static final PropertyBool WEST = PropertyBool.create("west");
	
	protected static final AxisAlignedBB AABB_DOWN = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.0625D, 1D);
	protected static final AxisAlignedBB AABB_UP = new AxisAlignedBB(0D, 0.9375D, 0D, 1D, 1D, 1D);
	protected static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(0D, 0D, 0.9375D, 1D, 1D, 1D);
	protected static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(0D, 0D, 0D, 0.0625D, 1D, 1D);
	protected static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(0D, 0D, 0D, 1D, 1D, 0.0625D);
	protected static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(0.9375D, 0D, 0D, 1D, 1D, 1D);
	
	public BlockTerracreep()
	{
		super(Material.VINE);
		this.setDefaultState(this.blockState.getBaseState().withProperty(UP, Boolean.valueOf(true)).withProperty(DOWN, Boolean.valueOf(true)).withProperty(NORTH, Boolean.valueOf(true)).withProperty(EAST, Boolean.valueOf(true)).withProperty(SOUTH, Boolean.valueOf(true)).withProperty(WEST, Boolean.valueOf(true)));
		this.setUnlocalizedName("terracreep");
		this.setRegistryName("terracreep");
		this.setHardness(4F);
		this.setSoundType(SoundType.SLIME);
		this.setCreativeTab(MTabs.plant);
		this.setLightLevel(6F);
		this.setTickRandomly(true);
	}
	
	public int tickRate(World worldIn)
    {
        return 30;
    }
	
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
		boolean up=false;
		boolean down=false;
		boolean north=false;
		boolean south=false;
		boolean east=false;
		boolean west=false;
		
		if(this.isBlockConsumable(worldIn.getBlockState(pos.up())))
			up = true;
		if(this.isBlockConsumable(worldIn.getBlockState(pos.down())))
			down = true;
		if(this.isBlockConsumable(worldIn.getBlockState(pos.north())))
			north = true;
		if(this.isBlockConsumable(worldIn.getBlockState(pos.south())))
			south = true;
		if(this.isBlockConsumable(worldIn.getBlockState(pos.east())))
			east = true;
		if(this.isBlockConsumable(worldIn.getBlockState(pos.west())))
			west = true;
		return this.getDefaultState().withProperty(UP, Boolean.valueOf(up)).withProperty(DOWN, Boolean.valueOf(down)).withProperty(NORTH, Boolean.valueOf(north)).withProperty(EAST, Boolean.valueOf(east)).withProperty(SOUTH, Boolean.valueOf(south)).withProperty(WEST, Boolean.valueOf(west));
    }
	
	public int getMetaFromState(IBlockState state)
    {
        return 0;
    }
	
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {UP, DOWN, NORTH, SOUTH, EAST, WEST});
    }
	
	public MapColor getMapColor(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return MapColor.PURPLE;
    }
	
	public boolean isOreBlock(IBlockState state)
	{
		if(state.getBlock() instanceof BlockOre || state.getBlock() instanceof BlockRedstoneOre || state.getBlock() == MBlocks.ore_blazium || state.getBlock() == MBlocks.ore_coal || state.getBlock() == MBlocks.ore_copper || state.getBlock() == MBlocks.ore_diamond || state.getBlock() == MBlocks.ore_dimensium || state.getBlock() == MBlocks.ore_emerald || state.getBlock() == MBlocks.ore_gold || state.getBlock() == MBlocks.ore_iron || state.getBlock() == MBlocks.ore_irradium || state.getBlock() == MBlocks.ore_lapis || state.getBlock() == MBlocks.ore_meurodite || state.getBlock() == MBlocks.ore_redstone || state.getBlock() == MBlocks.ore_redstone_lit || state.getBlock() == MBlocks.ore_salt || state.getBlock() == MBlocks.ore_soul || state.getBlock() == MBlocks.ore_tin || state.getBlock() == MBlocks.ore_titanium || state.getBlock() == MBlocks.ore_torite)
			return true;
		return false;
	}
	
	public boolean isBlockConsumable(IBlockState state)
	{
		if(state.getMaterial() == Material.ROCK && state.isFullBlock() && state.isFullCube() && state.getBlockHardness(null, null) >= 0 && state.getBlockHardness(null, null) < 50 && !this.isOreBlock(state))
			return true;
		return false;
	}
	
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return this.canSustainBush(worldIn.getBlockState(pos.up())) || this.canSustainBush(worldIn.getBlockState(pos.down())) || this.canSustainBush(worldIn.getBlockState(pos.north())) || this.canSustainBush(worldIn.getBlockState(pos.south())) || this.canSustainBush(worldIn.getBlockState(pos.east())) || this.canSustainBush(worldIn.getBlockState(pos.west()));
    }
	
	protected boolean canSustainBush(IBlockState state)
    {
        return this.isBlockConsumable(state);
    }
	
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
		return this.canPlaceBlockAt(worldIn, pos);
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
            spawnAsEntity(worldIn, pos, new ItemStack(MBlocks.terracreep));
        }
        else
        {
        	super.harvestBlock(worldIn, player, pos, state, te, stack);
        }
    }
	
	public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return 120;
    }
	
	public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return 180;
    }
	
	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }
    
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
    	int sides = 0;
    	if(state.getActualState(source, pos).getValue(UP) == true)
    		sides++;
    	if(state.getActualState(source, pos).getValue(DOWN) == true)
    		sides++;
    	if(state.getActualState(source, pos).getValue(NORTH) == true)
    		sides++;
    	if(state.getActualState(source, pos).getValue(EAST) == true)
    		sides++;
    	if(state.getActualState(source, pos).getValue(SOUTH) == true)
    		sides++;
    	if(state.getActualState(source, pos).getValue(WEST) == true)
    		sides++;
    	if(sides > 1)
    		return FULL_BLOCK_AABB;
    	else
    	{
    		if(state.getActualState(source, pos).getValue(UP) == true)
        		return AABB_UP;
    		else if(state.getActualState(source, pos).getValue(DOWN) == true)
        		return AABB_DOWN;
    		else if(state.getActualState(source, pos).getValue(NORTH) == true)
        		return AABB_NORTH;
    		else if(state.getActualState(source, pos).getValue(EAST) == true)
        		return AABB_EAST;
    		else if(state.getActualState(source, pos).getValue(SOUTH) == true)
        		return AABB_SOUTH;
    		else if(state.getActualState(source, pos).getValue(WEST) == true)
        		return AABB_WEST;
    		else
    			return FULL_BLOCK_AABB;
    	}
    }
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
		if(!this.canBlockStay(worldIn, pos, state))
			worldIn.setBlockToAir(pos);
		
		EnumFacing facing = EnumFacing.UP;
		int dir = rand.nextInt(6);
		
		if(dir == 0)
			facing = EnumFacing.DOWN;
		else if(dir == 1)
			facing = EnumFacing.NORTH;
		else if(dir == 2)
			facing = EnumFacing.EAST;
		else if(dir == 3)
			facing = EnumFacing.WEST;
		else if(dir == 4)
			facing = EnumFacing.SOUTH;
		
		if(this.isBlockConsumable(worldIn.getBlockState(pos.offset(facing))))
		{
			worldIn.destroyBlock(pos.offset(facing), true);
			if(rand.nextInt(3) != 1)
				worldIn.setBlockState(pos.offset(facing), this.getDefaultState());
			if(rand.nextInt(3) == 1)
				worldIn.setBlockToAir(pos);
		}
			
    }
}
