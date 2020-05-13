package minestrapp.block;

import java.util.Random;

import javax.annotation.Nullable;

import minestrapp.MBlocks;
import minestrapp.block.util.BlockBase;
import minestrapp.utils.BlockUtil;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockStabilizedDimensium extends BlockBase
{
	public static final PropertyInteger POWER = PropertyInteger.create("power", 0, 15);
	
	private final boolean active;
	
	public BlockStabilizedDimensium(String name, boolean active)
	{
		super(name, Material.IRON, MapColor.MAGENTA, SoundType.METAL, 5F, "pickaxe", 2);
		this.setEntityInvulnerability("dragon");
		this.setResistance(10F);
		this.active = active;
		
		if(this.active)
		{
			this.setDefaultState(this.blockState.getBaseState().withProperty(POWER, 15));
			this.setLightOpacity(0);
		}
		else
		{
			this.setDefaultState(this.blockState.getBaseState().withProperty(POWER, 0));
		}
	}
	
	public int tickRate(World worldIn)
    {
        return 1;
    }
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        update(worldIn, pos);
    }

	@Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
		if(active)
			return NULL_AABB;
		else
			return super.getCollisionBoundingBox(blockState, worldIn, pos);
    }
	
	public boolean isOpaqueCube(IBlockState state)
    {
		if(active)
			return false;
		else
			return true;
    }

    public boolean isFullCube(IBlockState state)
    {
    	if(active)
    		return false;
    	else
    		return true;
    }
    
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
    	if(active)
    		return BlockRenderLayer.CUTOUT;
    	else
    		return super.getBlockLayer();
    }
    
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(MBlocks.block_dimensium_stabilized_inactive);
    }
	
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        if (active)
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
        return new ItemStack(MBlocks.block_dimensium_stabilized_inactive);
    }
	
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(Item.getItemFromBlock(MBlocks.block_dimensium_stabilized_inactive), 1, this.damageDropped(state));
    }
	
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
		worldIn.scheduleBlockUpdate(pos, worldIn.getBlockState(pos).getBlock(), 0, 0);
    }
	
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
		worldIn.scheduleBlockUpdate(pos, worldIn.getBlockState(pos).getBlock(), 0, 0);
    }
	
	public void update(World worldIn, BlockPos pos)
	{
		if(!worldIn.isBlockPowered(pos))
		{
			int neighborPower = 0;
			
			for(EnumFacing facing : EnumFacing.VALUES)
			{
				IBlockState state = worldIn.getBlockState(pos.offset(facing));
				
				if(state.getBlock() == MBlocks.block_dimensium_stabilized_active)
				{
					if(state.getValue(POWER) != null && state.getValue(POWER).intValue() > neighborPower)
					{
						neighborPower = state.getValue(POWER);
					}
				}
			}
			
			if(neighborPower > 0)
				worldIn.setBlockState(pos, MBlocks.block_dimensium_stabilized_active.getDefaultState().withProperty(POWER, neighborPower - 1));
			else if(active)
				worldIn.setBlockState(pos, MBlocks.block_dimensium_stabilized_inactive.getDefaultState());
		}
		else
		{
			worldIn.setBlockState(pos, MBlocks.block_dimensium_stabilized_active.getDefaultState().withProperty(POWER, 15));
		}
	}
	
	public IBlockState getStateFromMeta(int meta)
    {
		return this.getDefaultState().withProperty(POWER, Integer.valueOf(meta));
    }
	
	public int getMetaFromState(IBlockState state)
    {
		return ((Integer)state.getValue(POWER)).intValue();
    }
	
	protected BlockStateContainer createBlockState()
    {
		return new BlockStateContainer(this, new IProperty[] {POWER});
    }
	
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
    {
		if(active)
			return true;
		else
			return super.isPassable(worldIn, pos);
    }
}
