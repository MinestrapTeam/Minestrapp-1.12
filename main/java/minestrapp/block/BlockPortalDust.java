package minestrapp.block;

import java.util.Random;

import minestrapp.MBlocks;
import minestrapp.block.crops.BlockVoidberryBush;
import net.minecraft.block.BlockChorusFlower;
import net.minecraft.block.BlockChorusPlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPortalDust extends BlockMDirt
{
	public BlockPortalDust()
	{
		super("portal_dust", MapColor.PURPLE, SoundType.GLASS, 0.8F, 0);
		this.setHarvestLevel("pickaxe", 0);
	}
	
	public MapColor getMapColor(IBlockState state, IBlockAccess access, BlockPos pos)
    {
		if(((BlockMDirt.DirtType)state.getValue(VARIANT)) == BlockMDirt.DirtType.PODZOL)
			return MapColor.YELLOW_STAINED_HARDENED_CLAY;
		else
			return super.getMapColor(state, access, pos);
    }
	
	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable)
    {
        IBlockState plant = plantable.getPlant(world, pos.offset(direction));
        net.minecraftforge.common.EnumPlantType plantType = plantable.getPlantType(world, pos.offset(direction));

        switch (plantType)
        {
            case Cave:   return state.isSideSolid(world, pos, EnumFacing.UP);
            default: break;
        }

        return plantable instanceof BlockVoidberryBush || plantable instanceof BlockChorusPlant || plantable instanceof BlockChorusFlower;
    }
	
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn)
    {
        this.spawnParticles(worldIn, pos);
        super.onBlockClicked(worldIn, pos, playerIn);
    }
	
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		this.spawnParticles(worldIn, pos);
		
		ItemStack itemstack = playerIn.getHeldItem(hand);
		
		if (!playerIn.canPlayerEdit(pos.offset(facing), facing, itemstack))
        {
            return false;
        }
		else if (itemstack.getItem() instanceof ItemHoe && facing != EnumFacing.DOWN && worldIn.isAirBlock(pos.up()))
        {
			if((BlockMDirt.DirtType)state.getValue(BlockMDirt.VARIANT) == BlockMDirt.DirtType.COARSE)
			{
				worldIn.playSound(playerIn, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
	            if (!worldIn.isRemote)
	            {
	            	itemstack.damageItem(1, playerIn);
	            	worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, BlockMDirt.DirtType.DEFAULT), 11);
	                	return true;
	            }
			}
        }
		
        return false;
    }
	
	public int getLightValue(IBlockState state)
    {
    	if(state.getValue(VARIANT) == BlockMDirt.DirtType.PODZOL)
    		return 7;
    	else
    		return 0;
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
                worldIn.spawnParticle(EnumParticleTypes.PORTAL, d1, d2, d3, 0.0D, -1.0D, 0.0D);
            }
        }
    }
}
