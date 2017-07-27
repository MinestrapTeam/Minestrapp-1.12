package minestrapp.block;

import java.util.Random;

import minestrapp.MBlocks;
import minestrapp.MTabs;
import minestrapp.block.util.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMGrass extends BlockBase
{
	private BlockMDirt dirt;
	private boolean biomecolored;
	public static final PropertyBool SNOWY = PropertyBool.create("snowy");
	
	public BlockMGrass(String name, MapColor mapColor, SoundType soundType, float hardness, int harvestLevel, BlockMDirt dirt, boolean biome)
	{
		super(name, Material.GRASS, mapColor, soundType, hardness, "shovel", harvestLevel);
		this.setDefaultState(this.blockState.getBaseState().withProperty(SNOWY, Boolean.valueOf(false)));
		this.setTickRandomly(true);
        this.setCreativeTab(MTabs.environment);
        this.dirt = dirt;
        this.biomecolored = biome;
	}
	
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        Block block = worldIn.getBlockState(pos.up()).getBlock();
        return state.withProperty(SNOWY, Boolean.valueOf(block == Blocks.SNOW || block == Blocks.SNOW_LAYER));
    }
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
            if (worldIn.getLightFromNeighbors(pos.up()) < 4 && worldIn.getBlockState(pos.up()).getLightOpacity(worldIn, pos.up()) > 2)
            {
                worldIn.setBlockState(pos, this.dirt.getDefaultState());
            }
            else
            {
                if (worldIn.getLightFromNeighbors(pos.up()) >= 9)
                {
                    for (int i = 0; i < 4; ++i)
                    {
                        BlockPos blockpos = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);

                        if (blockpos.getY() >= 0 && blockpos.getY() < 256 && !worldIn.isBlockLoaded(blockpos))
                        {
                            return;
                        }

                        IBlockState iblockstate = worldIn.getBlockState(blockpos.up());
                        IBlockState iblockstate1 = worldIn.getBlockState(blockpos);

                        if (iblockstate1.getBlock() == this.dirt && iblockstate1.getValue(this.dirt.VARIANT) == BlockMDirt.DirtType.DEFAULT && worldIn.getLightFromNeighbors(blockpos.up()) >= 4 && iblockstate.getLightOpacity(worldIn, pos.up()) <= 2)
                        {
                            worldIn.setBlockState(blockpos, this.getDefaultState());
                        }
                    }
                }
            }
        }
    }
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return this.dirt.getItemDropped(this.dirt.getDefaultState().withProperty(BlockMDirt.VARIANT, BlockMDirt.DirtType.DEFAULT), rand, fortune);
    }
	
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
		if(this.biomecolored)
			return BlockRenderLayer.CUTOUT_MIPPED;
		else
			return super.getBlockLayer();
    }
	
	public int getMetaFromState(IBlockState state)
    {
        return 0;
    }
	
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {SNOWY});
    }
	
	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable)
    {
        IBlockState plant = plantable.getPlant(world, pos.offset(direction));
        net.minecraftforge.common.EnumPlantType plantType = plantable.getPlantType(world, pos.offset(direction));

        switch (plantType)
        {
            case Cave:   return state.isSideSolid(world, pos, EnumFacing.UP);
            case Plains:
            	if(plantable instanceof BlockFlower)
            		return true;
            	else
            		return this == MBlocks.clay_grass;
            case Beach:
                boolean isBeach = this == MBlocks.clay_grass;
                boolean hasWater = (world.getBlockState(pos.east()).getMaterial() == Material.WATER ||
                                    world.getBlockState(pos.west()).getMaterial() == Material.WATER ||
                                    world.getBlockState(pos.north()).getMaterial() == Material.WATER ||
                                    world.getBlockState(pos.south()).getMaterial() == Material.WATER);
                return isBeach && hasWater;
            default: break;
        }

        return false;
    }
	
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		ItemStack itemstack = playerIn.getHeldItem(hand);
		
		if (!playerIn.canPlayerEdit(pos.offset(facing), facing, itemstack))
        {
            return false;
        }	
		else if(itemstack.getItem() instanceof ItemSpade && facing != EnumFacing.DOWN && worldIn.getBlockState(pos.up()).getMaterial() == Material.AIR)
		{
			worldIn.playSound(playerIn, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
			if(!worldIn.isRemote)
			{
				itemstack.damageItem(1, playerIn);
				if(this == MBlocks.clay_grass)
					worldIn.setBlockState(pos, MBlocks.clay_grass_path.getDefaultState());
				else if(this == MBlocks.lichen)
					worldIn.setBlockState(pos, MBlocks.lichen_path.getDefaultState());
			}
			return true;
		}
		else if (itemstack.getItem() instanceof ItemHoe && facing != EnumFacing.DOWN && worldIn.isAirBlock(pos.up()))
        {
			worldIn.playSound(playerIn, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!worldIn.isRemote)
            {
            	itemstack.damageItem(1, playerIn);
				if(this == MBlocks.clay_grass)
					worldIn.setBlockState(pos, MBlocks.clay_farmland.getDefaultState(), 11);
				else if(this == MBlocks.lichen)
					worldIn.setBlockState(pos, MBlocks.permafrost_farmland.getDefaultState(), 11);
            }

            return true;
        }
		
        return false;
    }
}
