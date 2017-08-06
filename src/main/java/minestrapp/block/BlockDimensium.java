package minestrapp.block;

import java.util.Random;

import javax.annotation.Nullable;

import minestrapp.MBlocks;
import minestrapp.block.util.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
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

public class BlockDimensium extends BlockBase
{
	private final boolean destabilized;
	
	public BlockDimensium(String name, Material material, MapColor mapColor, SoundType soundType, float hardness, String tool, int harvestLevel, boolean destabilized)
	{
		super(name, material, mapColor, soundType, hardness, tool, harvestLevel);
		this.destabilized = destabilized;
		if(destabilized)
		{
			this.setTickRandomly(true);
			this.setLightOpacity(0);
		}
	}
	
	public int tickRate(World worldIn)
    {
        return 8;
    }
	
	@Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
		if(destabilized)
			return NULL_AABB;
		else
			return super.getCollisionBoundingBox(blockState, worldIn, pos);
    }
	
	public boolean isOpaqueCube(IBlockState state)
    {
		if(destabilized)
			return false;
		else
			return true;
    }

    public boolean isFullCube(IBlockState state)
    {
    	if(destabilized)
    		return false;
    	else
    		return true;
    }
    
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
    	if(destabilized)
    		return BlockRenderLayer.CUTOUT;
    	else
    		return super.getBlockLayer();
    }
	
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
    {
        this.activate(worldIn, pos);
        super.onEntityWalk(worldIn, pos, entityIn);
    }
	
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        this.activate(worldIn, pos);
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }
	
	private void activate(World worldIn, BlockPos pos)
    {
        this.spawnParticles(worldIn, pos);
        worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_CHORUS_FLOWER_GROW, SoundCategory.BLOCKS, 0.8F, 2.0F, false);

        if (this == MBlocks.block_dimensium)
            worldIn.setBlockState(pos, MBlocks.block_dimensium_destabilized.getDefaultState());
    }
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
		worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_CHORUS_FLOWER_GROW, SoundCategory.BLOCKS, 0.8F, 1.0F, false);
        if (this == MBlocks.block_dimensium_destabilized)
        {
            worldIn.setBlockState(pos, MBlocks.block_dimensium.getDefaultState());
        }
    }
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(MBlocks.block_dimensium);
    }
	
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        if (destabilized)
            this.spawnParticles(worldIn, pos);
    }
	
	private void spawnParticles(World worldIn, BlockPos pos)
    {
        Random random = worldIn.rand;
        double d0 = 0.0625D;

        for (int i = 0; i < 6; ++i)
        {
            double d1 = (double)((float)pos.getX() + random.nextFloat());
            double d2 = (double)((float)pos.getY() + random.nextFloat());
            double d3 = (double)((float)pos.getZ() + random.nextFloat());

            if (i == 0 && !worldIn.getBlockState(pos.up()).isOpaqueCube())
            {
                d2 = (double)pos.getY() + 0.0625D + 1.0D;
            }

            if (i == 1 && !worldIn.getBlockState(pos.down()).isOpaqueCube())
            {
                d2 = (double)pos.getY() - 0.0625D;
            }

            if (i == 2 && !worldIn.getBlockState(pos.south()).isOpaqueCube())
            {
                d3 = (double)pos.getZ() + 0.0625D + 1.0D;
            }

            if (i == 3 && !worldIn.getBlockState(pos.north()).isOpaqueCube())
            {
                d3 = (double)pos.getZ() - 0.0625D;
            }

            if (i == 4 && !worldIn.getBlockState(pos.east()).isOpaqueCube())
            {
                d1 = (double)pos.getX() + 0.0625D + 1.0D;
            }

            if (i == 5 && !worldIn.getBlockState(pos.west()).isOpaqueCube())
            {
                d1 = (double)pos.getX() - 0.0625D;
            }

            if (d1 < (double)pos.getX() || d1 > (double)(pos.getX() + 1) || d2 < 0.0D || d2 > (double)(pos.getY() + 1) || d3 < (double)pos.getZ() || d3 > (double)(pos.getZ() + 1))
            {
                worldIn.spawnParticle(EnumParticleTypes.SUSPENDED_DEPTH, d1, d2, d3, 0.0D, 0.0D, 0.0D);
            }
        }
    }
	
	protected ItemStack getSilkTouchDrop(IBlockState state)
    {
        return new ItemStack(MBlocks.block_dimensium);
    }
	
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(Item.getItemFromBlock(MBlocks.block_dimensium), 1, this.damageDropped(state));
    }
	
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
		if(worldIn.getBlockState(fromPos) != MBlocks.block_dimensium.getDefaultState())
			this.activate(worldIn, pos);
    }
}
