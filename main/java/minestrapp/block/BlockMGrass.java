package minestrapp.block;

import java.util.Random;

import minestrapp.MBlocks;
import minestrapp.MTabs;
import minestrapp.block.crops.BlockBerryBush;
import minestrapp.block.util.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.IGrowable;
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
import net.minecraft.world.biome.BiomeSavanna;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMGrass extends BlockBase  implements IGrowable
{
	private BlockMDirt dirt;
	private int minLight;
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
        this.minLight = 9;
        this.setLightOpacity(255);
	}
	
	public void setMinLight(int light)
	{
		this.minLight = light;
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
        	if (!worldIn.isAreaLoaded(pos, 3)) return;
            if (worldIn.getLightFromNeighbors(pos.up()) < 4 && worldIn.getBlockState(pos.up()).getLightOpacity(worldIn, pos.up()) > 2)
            {
                worldIn.setBlockState(pos, this.dirt.getDefaultState());
            }
            else
            {
                if (worldIn.getLightFromNeighbors(pos.up()) >= this.minLight)
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
            	if(plantable instanceof BlockFlower || plantable instanceof BlockSapling || plantable instanceof BlockBerryBush)
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
	
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
        return true;
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        return true;
    }
	
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        BlockPos blockpos = pos.up();

        for (int i = 0; i < 128; ++i)
        {
            BlockPos blockpos1 = blockpos;
            int j = 0;

            while (true)
            {
                if (j >= i / 16)
                {
                    if (worldIn.isAirBlock(blockpos1))
                    {
                    	int flowerChance = 6;
                    	
                    	if(this == MBlocks.lichen)
                    		flowerChance = 12;
                    	
                        if (rand.nextInt(flowerChance) == 0)
                        {
                            worldIn.getBiome(blockpos1).plantFlower(worldIn, rand, blockpos1);
                        }
                        else
                        {
                        	IBlockState iblockstate1 = Blocks.TALLGRASS.getDefaultState().withProperty(BlockTallGrass.TYPE, BlockTallGrass.EnumType.GRASS);
                            
                        	if(this == MBlocks.savanna_grass && worldIn.getBiome(blockpos1) instanceof BiomeSavanna)	
                            	iblockstate1 = MBlocks.savanna_grass.getDefaultState();
                            else if(this == MBlocks.lichen)
                            	iblockstate1 = MBlocks.tundra_grass.getDefaultState();

                            if (((BlockBush) iblockstate1.getBlock()).canBlockStay(worldIn, blockpos1, iblockstate1))
                            {
                                worldIn.setBlockState(blockpos1, iblockstate1, 3);
                            }
                        }
                    }

                    break;
                }

                blockpos1 = blockpos1.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);

                if (worldIn.getBlockState(blockpos1.down()).getBlock() != this || worldIn.getBlockState(blockpos1).isNormalCube())
                {
                    break;
                }

                ++j;
            }
        }
    }
}
