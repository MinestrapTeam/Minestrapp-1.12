package minestrapp.block;

import java.util.Random;

import minestrapp.MBlocks;
import minestrapp.MTabs;
import minestrapp.block.util.BlockBase;
import minestrapp.worldgen.MGenPalmTree;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockSand;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPalmCrown extends BlockBase implements IGrowable
{
	private boolean live;
	
	public BlockPalmCrown(String name, boolean live)
	{
		super(name, Material.WOOD, live ? MapColor.FOLIAGE : MapColor.SAND, SoundType.WOOD, 2F, "axe", 0);
		this.setRenderLayer(BlockRenderLayer.CUTOUT);
		if(!live)
			this.setCreativeTab(MTabs.plant);
		this.setTickRandomly(live);
		this.live = live;
		this.setFlammable(5, 30);
	}

	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
		if(live)
			grow(worldIn, pos, state, rand);
    }
	
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
		if(live)
			return (worldIn.isAirBlock(pos.down().north().east()) || worldIn.isAirBlock(pos.down().north().west()) || worldIn.isAirBlock(pos.down().south().east()) || worldIn.isAirBlock(pos.down().south().west()));
		return false;
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        return live;
    }
    
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
    	if(live)
    		this.grow(worldIn, pos, state, rand);
    }
    
    public void grow(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
    	if(rand.nextInt(100) < 8)
    	{
	    	int dir = rand.nextInt(4);
			BlockPos growPos =  pos.down().north().east();
			
			if(dir == 1)
				growPos = pos.down().north().west();
			else if(dir == 2)
				growPos = pos.down().south().west();
			else if(dir == 3)
				growPos = pos.down().south().east();
			
			if(worldIn.getBlockState(growPos).getBlock().isReplaceable(worldIn, growPos) && worldIn.getBlockState(growPos.up()).getBlock() == MBlocks.palm_fronds)
			{
				boolean droppedCoconut = false;
				
				for(int i = 0 ; i < 254 ; i++)
				{
					if(!worldIn.getBlockState(growPos.down(i)).getBlock().isReplaceable(worldIn, growPos.down(i)))
					{
						if(worldIn.getBlockState(growPos.down(i)).getBlock() == MBlocks.coconut)
							droppedCoconut = true;
						break;
					}
				}
				
				if(!droppedCoconut)
					worldIn.setBlockState(growPos, MBlocks.coconut.getDefaultState().withProperty(BlockCoconut.AGE, 0));
			}
    	}
	}
    
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        Random rand = world instanceof World ? ((World)world).rand : RANDOM;
        int frondCount = rand.nextInt(5 + (2 * fortune));
        
        for (int i = 0; i < frondCount; i++)
        {
            drops.add(new ItemStack(MBlocks.palm_fronds_dead));
        }
        
        drops.add(new ItemStack(MBlocks.log, 1, 3));
    }
    
    protected ItemStack getSilkTouchDrop(IBlockState state)
    {
    	return new ItemStack(MBlocks.palm_crown_dead);
    }
    
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
    	if(live)
    	{
	        for(int y = 0 ; y < 2 ; y++)
	        {
	        	for(int x = -3 ; x < 4 ; x++)
	        	{
	        		for(int z = -3 ; z < 4 ; z++)
	        		{
	        			BlockPos checkPos = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
	        			IBlockState checkState = worldIn.getBlockState(checkPos);
	        			
	        			if(checkState.getBlock() instanceof BlockPalmFronds)
	        			{
	        				worldIn.destroyBlock(checkPos, true);
	        			}
	        		}
	        	}
	        }
	        super.breakBlock(worldIn, pos, state);
    	}
    	else
    		super.breakBlock(worldIn, pos, state);
    }
}
