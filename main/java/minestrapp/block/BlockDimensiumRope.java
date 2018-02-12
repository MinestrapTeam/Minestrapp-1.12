package minestrapp.block;

import java.util.Random;

import minestrapp.MTabs;
import minestrapp.block.util.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockWall;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDimensiumRope extends BlockBase
{
	public static final PropertyBool TOP = PropertyBool.create("top");
	
	public BlockDimensiumRope()
	{
		super("dimensium_rope", Material.CLOTH, MapColor.MAGENTA, SoundType.CLOTH, 0.8F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(TOP, true));
		this.setLightOpacity(0);
		this.setLightLevel(1F);
		this.setPushReaction(EnumPushReaction.DESTROY);
		this.setCreativeTab(MTabs.decor);
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
		return new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);
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
    	BlockPos toppos = null;
    	if(this.canBlockStay(worldIn, pos, EnumFacing.UP))
	    	return true;
    	else
    	{
			for(int upY = pos.up().getY() ; upY < worldIn.getHeight() ; upY++)
			{
				BlockPos checkpos = new BlockPos(pos.getX(), upY, pos.getZ());
				
				if(!worldIn.getBlockState(checkpos).getBlock().isReplaceable(worldIn, checkpos))
				{
					break;
				}
				else if(this.canBlockStay(worldIn, checkpos, EnumFacing.UP))
				{
					return true;
				}
			}
			return false;
    	}
    }
    
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
    	BlockPos toppos = null;
    	
    	if(this.canBlockStay(worldIn, pos, EnumFacing.UP))
	    	toppos = pos;
    	else
    	{
			for(int upY = pos.up().getY() ; upY < worldIn.getHeight() ; upY++)
			{
				BlockPos checkpos = new BlockPos(pos.getX(), upY, pos.getZ());
				
				if(!worldIn.getBlockState(checkpos).getBlock().isReplaceable(worldIn, checkpos))
				{
					break;
				}
				else if(this.canBlockStay(worldIn, checkpos, EnumFacing.UP))
				{
					toppos = checkpos;
					break;
				}
			}
    	}
		
		if(toppos != null)
		{
			worldIn.setBlockState(toppos, this.getDefaultState().withProperty(TOP, true));
			
			for(int downY = toppos.getY() - 1 ; downY >= 0 ; downY--)
	    	{
	    		BlockPos checkpos = new BlockPos(pos.getX(), downY, pos.getZ());
	    		if(worldIn.getBlockState(checkpos).getBlock().isReplaceable(worldIn, checkpos) || worldIn.getBlockState(checkpos).getBlock() == this)
	    			worldIn.setBlockState(checkpos, this.getDefaultState().withProperty(TOP, false));
	    		else
	    			break;
	    	}
		}
    }
    
    protected boolean canBlockStay(World worldIn, BlockPos pos, EnumFacing facing)
    {
    	return worldIn.isSideSolid(pos.offset(facing), facing.getOpposite()) || worldIn.getBlockState(pos.offset(facing)).getBlock() == this || worldIn.getBlockState(pos.offset(facing)).getBlock() instanceof BlockFence || worldIn.getBlockState(pos.offset(facing)).getBlock() instanceof BlockWall;
    }
    
    @Override
    public boolean isLadder(IBlockState state, IBlockAccess world, BlockPos pos, EntityLivingBase entity)
    {
    	return true;
    }

    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return 60;
    }
    
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return 30;
    }
    
    public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing)
    {
        return BlockFaceShape.UNDEFINED;
    }
    
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
    	if(state.getValue(TOP) == true)
    		return Item.getItemFromBlock(this);
    	else
    		return Items.AIR;
    }
    
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
    	if(worldIn.getBlockState(pos.up()).getBlock() == this)
    		worldIn.destroyBlock(pos.up(), true);
    	if(worldIn.getBlockState(pos.down()).getBlock() == this)
    		worldIn.destroyBlock(pos.down(), false);
        super.breakBlock(worldIn, pos, state);
    }
    
    public int damageDropped(IBlockState state)
    {
        return 0;
    }
    
    public IBlockState getStateFromMeta(int meta)
    {
    	if(meta == 0)
    		return this.getDefaultState().withProperty(TOP, false);
    	else
    		return this.getDefaultState().withProperty(TOP, true);
    }
	
	public int getMetaFromState(IBlockState state)
    {
        return (state.getValue(TOP) == true) ? 1 : 0;
    }
	
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {TOP});
    }
	
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos neighbor)
	{
		if (!this.canBlockStay(worldIn, pos, EnumFacing.UP))
		{
			this.dropBlockAsItem(worldIn, pos, state, 0);
			worldIn.setBlockToAir(pos);
		}

		super.neighborChanged(state, worldIn, pos, blockIn, neighbor);
	}
	
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        return false;
    }
}
