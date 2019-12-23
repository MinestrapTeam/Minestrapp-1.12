package minestrapp.block;

import java.util.Random;

import javax.annotation.Nullable;

import minestrapp.MBlocks;
import minestrapp.MTabs;
import minestrapp.block.util.BlockBase;
import minestrapp.item.tools.MDagger;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockOre;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
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

public class BlockCreepingGlowMoss extends BlockBase
{
	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool EAST = PropertyBool.create("east");
	public static final PropertyBool WEST = PropertyBool.create("west");
	
	protected static final AxisAlignedBB AABB_UP = new AxisAlignedBB(0D, 0.9375D, 0D, 1D, 1D, 1D);
	protected static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(0D, 0D, 0.9375D, 1D, 1D, 1D);
	protected static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(0D, 0D, 0D, 0.0625D, 1D, 1D);
	protected static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(0D, 0D, 0D, 1D, 1D, 0.0625D);
	protected static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(0.9375D, 0D, 0D, 1D, 1D, 1D);
			
	public BlockCreepingGlowMoss()
	{
		super("creeping_glow_moss", Material.SNOW, MapColor.LIGHT_BLUE, SoundType.CLOTH, 0.4F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(NORTH, Boolean.valueOf(true)).withProperty(EAST, Boolean.valueOf(true)).withProperty(SOUTH, Boolean.valueOf(true)).withProperty(WEST, Boolean.valueOf(true)));
		this.setHarvestLevel("shovel", 0);
		this.setCreativeTab(MTabs.plant);
		this.setTickRandomly(true);
		this.setLightLevel(0.2F);
		this.setGlowing();
		this.setRenderLayer(BlockRenderLayer.CUTOUT);
	}
	
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
		boolean north=false;
		boolean south=false;
		boolean east=false;
		boolean west=false;
		
		if(worldIn.isSideSolid(pos.north(), EnumFacing.SOUTH, false))
			north = true;
		if(worldIn.isSideSolid(pos.south(), EnumFacing.NORTH, false))
			south = true;
		if(worldIn.isSideSolid(pos.east(), EnumFacing.WEST, false))
			east = true;
		if(worldIn.isSideSolid(pos.west(), EnumFacing.EAST, false))
			west = true;
		return this.getDefaultState().withProperty(NORTH, Boolean.valueOf(north)).withProperty(EAST, Boolean.valueOf(east)).withProperty(SOUTH, Boolean.valueOf(south)).withProperty(WEST, Boolean.valueOf(west));
    }
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
    	int sides = 0;
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
    		if(state.getActualState(source, pos).getValue(NORTH) == true)
        		return AABB_NORTH;
    		else if(state.getActualState(source, pos).getValue(EAST) == true)
        		return AABB_EAST;
    		else if(state.getActualState(source, pos).getValue(SOUTH) == true)
        		return AABB_SOUTH;
    		else if(state.getActualState(source, pos).getValue(WEST) == true)
        		return AABB_WEST;
    		else
    			return AABB_UP;
    	}
    }
	
	public int getMetaFromState(IBlockState state)
    {
        return 0;
    }
	
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {NORTH, SOUTH, EAST, WEST});
    }
	
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
    {
		return true;
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

    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return this.canBlockStay(worldIn, pos, this.getDefaultState());
    }
    
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        this.checkAndDropBlock(worldIn, pos, state);
    }
    
    private boolean checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!this.canBlockStay(worldIn, pos, state))
        {
            worldIn.setBlockToAir(pos);
            return false;
        }
        else
            return true;
    }
    
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
    {
        super.harvestBlock(worldIn, player, pos, state, te, stack);
        worldIn.setBlockToAir(pos);
    }
    
    public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
    {
        return true;
    }
    
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (rand.nextInt(20) == 0)
        {
            int i = 12;

            for (BlockPos blockpos : BlockPos.getAllInBoxMutable(pos.add(-4, -1, -4), pos.add(4, 1, 4)))
            {
                if (worldIn.getBlockState(blockpos).getBlock() == this)
                {
                    --i;

                    if (i <= 0)
                        return;
                }
            }

            BlockPos blockpos1 = pos.add(rand.nextInt(3) - 1, rand.nextInt(2) - rand.nextInt(2), rand.nextInt(3) - 1);

            for (int k = 0; k < 4; ++k)
            {
                if (worldIn.isAirBlock(blockpos1) && this.canBlockStay(worldIn, blockpos1, this.getDefaultState()))
                {
                    pos = blockpos1;
                }

                blockpos1 = pos.add(rand.nextInt(3) - 1, rand.nextInt(2) - rand.nextInt(2), rand.nextInt(3) - 1);
            }

            if (worldIn.isAirBlock(blockpos1) && this.canBlockStay(worldIn, blockpos1, this.getDefaultState()))
            {
                worldIn.setBlockState(blockpos1, this.getDefaultState(), 2);
            }
        }
    }
    
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        if(pos.getY() > 0 && pos.getY() < 256 && worldIn.getLight(pos) < 8 && worldIn.isSideSolid(pos.up(), EnumFacing.DOWN, false))
        	return true;
        return false;
    }
}