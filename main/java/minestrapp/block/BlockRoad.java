package minestrapp.block;

import java.util.Random;

import javax.annotation.Nullable;

import minestrapp.MBlocks;
import minestrapp.MTabs;
import minestrapp.block.item.IMetaBlockName;
import minestrapp.block.util.BlockBase;
import minestrapp.utils.BlockUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.BlockGrassPath;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockRoad extends BlockBase
{
	private double speed;
	
	public static final PropertyEnum<BlockRoad.EnumType> SOIL = PropertyEnum.<BlockRoad.EnumType>create("soil", BlockRoad.EnumType.class);
	
	public BlockRoad(String name, float hardness, double speed)
	{
		super(name, Material.GROUND, MapColor.BLACK, SoundType.STONE, hardness, null, 0);
		this.setCreativeTab(MTabs.utility);
		this.setNonSolid();
		this.speed = speed;
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
		if(blockState.getValue(SOIL).getSoilBlock() != null && blockState.getValue(SOIL).getSoilBlock() != Blocks.AIR)
			return FULL_BLOCK_AABB;
		return BlockUtil.createBoundingBoxColumn(16, 1, 0);
    }
	
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        drops.add(new ItemStack(this));
        
        if(state.getValue(SOIL) != null && state.getValue(SOIL).getDropBlock() != Blocks.AIR)
        	drops.add(new ItemStack(state.getValue(SOIL).getDropBlock()));
    }
	
	public boolean canSilkHarvest()
	{
		return false;
	}
	
	public SoundType getSoundType(IBlockState state, World world, BlockPos pos, @Nullable Entity entity)
    {
		if(world.getBlockState(pos).getValue(SOIL) != null && world.getBlockState(pos).getValue(SOIL).getSoilBlock() != Blocks.AIR)
			return world.getBlockState(pos).getValue(SOIL).getSoilBlock().getSoundType();
        return getSoundType();
    }
	
	public float getBlockHardness(IBlockState blockState, World worldIn, BlockPos pos)
    {
		if(blockState.getValue(SOIL) != null && blockState.getValue(SOIL).getSoilBlock() != Blocks.AIR)
			return blockState.getValue(SOIL).getSoilBlock().getBlockHardness(blockState.getValue(SOIL).getSoilBlock().getDefaultState(), worldIn, pos) + this.blockHardness;
        return this.blockHardness;
    }
	
	public float getExplosionResistance(World world, BlockPos pos, @Nullable Entity exploder, Explosion explosion)
    {
		if(world.getBlockState(pos).getValue(SOIL) != null && world.getBlockState(pos).getValue(SOIL).getSoilBlock() != Blocks.AIR)
			return world.getBlockState(pos).getValue(SOIL).getSoilBlock().getExplosionResistance(exploder);
        return getExplosionResistance(exploder);
    }
	
	@Nullable public String getHarvestTool(IBlockState state)
    {
		if(state.getValue(SOIL) != null && state.getValue(SOIL).getSoilBlock() != Blocks.AIR)
			return state.getValue(SOIL).getSoilBlock().getHarvestTool(state.getValue(SOIL).getSoilBlock().getDefaultState());
        return super.getHarvestTool(state);
    }
	
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(SOIL, BlockRoad.EnumType.byMetadata(meta));
    }
	
	public int getMetaFromState(IBlockState state)
    {
        return ((BlockRoad.EnumType)state.getValue(SOIL)).getMetadata();
    }
	
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {SOIL});
    }
	
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
    {
		if(worldIn.getBlockState(pos).getValue(SOIL) != null && worldIn.getBlockState(pos).getValue(SOIL).getSoilBlock() != Blocks.AIR)
		{
			entityIn.motionX *= this.speed;
			entityIn.motionZ *= this.speed;
		}
    }
	
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
		if(!entityIn.isSneaking())
		{
			if(entityIn.posX >= pos.getX() && entityIn.posX < pos.getX() + 1 && entityIn.posZ >= pos.getZ() && entityIn.posZ < pos.getZ() + 1 && entityIn.posY < pos.getY() + 0.07D && worldIn.getBlockState(pos).getValue(SOIL) != null && worldIn.getBlockState(pos).getValue(SOIL).getSoilBlock() == Blocks.AIR)
			{
				entityIn.motionX *= this.speed;
				entityIn.motionZ *= this.speed;
			}
		}
    }
	
	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable)
    {
		return state.getValue(SOIL).getSoilBlock().canSustainPlant(state, world, pos, direction, plantable);
    }
	
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
    {
        return worldIn.getBlockState(pos).getValue(SOIL) != null && worldIn.getBlockState(pos).getValue(SOIL).getSoilBlock() == Blocks.AIR;
    }
	
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
		Block block = worldIn.getBlockState(pos.down()).getBlock();
		
		return (worldIn.isSideSolid(pos.down(), EnumFacing.UP) || worldIn.getBlockState(pos.down()).getBlock() instanceof BlockFarmland || worldIn.getBlockState(pos.down()).getBlock() instanceof BlockMFarmland || worldIn.getBlockState(pos.down()).getBlock() instanceof BlockMPath || worldIn.getBlockState(pos.down()).getBlock() instanceof BlockGrassPath);
    }
	
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if(!worldIn.isRemote && !this.canBlockStay(state, worldIn, pos))
        {
        	this.dropBlockAsItem(worldIn, pos, state, 0);
        	worldIn.setBlockToAir(pos);
        }
    }
	
	public boolean canBlockStay(IBlockState state, World worldIn, BlockPos pos)
	{
		if(state.getValue(SOIL) != null && state.getValue(SOIL).getSoilBlock() == Blocks.AIR)
			return this.canPlaceBlockAt(worldIn, pos);
		else
			return true;
	}
	
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
		Block soil = worldIn.getBlockState(pos.down()).getBlock();
		
		if(soil == Blocks.GRASS_PATH)
		{
			worldIn.setBlockToAir(pos);
			worldIn.setBlockState(pos.down(), this.getDefaultState().withProperty(SOIL, BlockRoad.EnumType.GRASS_PATH));
		}
		else if(soil == MBlocks.clay_grass_path)
		{
			worldIn.setBlockToAir(pos);
			worldIn.setBlockState(pos.down(), this.getDefaultState().withProperty(SOIL, BlockRoad.EnumType.CLAY_GRASS_PATH));
		}
		else if(soil == MBlocks.lichen_path)
		{
			worldIn.setBlockToAir(pos);
			worldIn.setBlockState(pos.down(), this.getDefaultState().withProperty(SOIL, BlockRoad.EnumType.LICHEN_PATH));
		}
		else if(soil == MBlocks.fargrowth_path)
		{
			worldIn.setBlockToAir(pos);
			worldIn.setBlockState(pos.down(), this.getDefaultState().withProperty(SOIL, BlockRoad.EnumType.FARGROWTH_PATH));
		}
		else if(soil == Blocks.FARMLAND)
		{
			worldIn.setBlockToAir(pos);
			worldIn.setBlockState(pos.down(), this.getDefaultState().withProperty(SOIL, BlockRoad.EnumType.FARMLAND));
		}
		else if(soil == MBlocks.clay_farmland)
		{
			worldIn.setBlockToAir(pos);
			worldIn.setBlockState(pos.down(), this.getDefaultState().withProperty(SOIL, BlockRoad.EnumType.CLAY_FARMLAND));
		}
		else if(soil == MBlocks.permafrost_farmland)
		{
			worldIn.setBlockToAir(pos);
			worldIn.setBlockState(pos.down(), this.getDefaultState().withProperty(SOIL, BlockRoad.EnumType.FROZEN_FARMLAND));
		}
		else
			super.onBlockAdded(worldIn, pos, state);
    }
	
	public static enum EnumType implements IStringSerializable
    {
        NONE(0, "none", Blocks.AIR, Blocks.AIR),
        GRASS_PATH(1, "grass_path", Blocks.DIRT, Blocks.GRASS_PATH),
        CLAY_GRASS_PATH(2, "clay_grass_path", MBlocks.clay_soil, MBlocks.clay_grass_path),
        LICHEN_PATH(3, "lichen_path", MBlocks.permafrost, MBlocks.lichen_path),
        FARGROWTH_PATH(4, "fargrowth_path", MBlocks.portal_dust, MBlocks.fargrowth_path),
        FARMLAND(5, "farmland", Blocks.DIRT, Blocks.FARMLAND),
        CLAY_FARMLAND(6, "clay_farmland", MBlocks.clay_soil, MBlocks.clay_farmland),
        FROZEN_FARMLAND(7, "frozen_farmland", MBlocks.permafrost, MBlocks.permafrost_farmland);

        private static final BlockRoad.EnumType[] META_LOOKUP = new BlockRoad.EnumType[values().length];
        private final int meta;
        private final String name;
        private final Block drop;
        private final Block soil;

        private EnumType(int meta, String name, Block drop, Block soil)
        {
            this.meta = meta;
            this.name = name;
            this.drop = drop;
            this.soil = soil;
        }

        public int getMetadata()
        {
            return this.meta;
        }

        public String toString()
        {
            return this.name;
        }

        public static BlockRoad.EnumType byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        public String getName()
        {
            return this.name;
        }

        public String getUnlocalizedName()
        {
            return this.name;
        }
        
        public String getRegisryName()
        {
        	return this.name;
        }
        
        public Block getDropBlock()
        {
        	return this.drop;
        }
        
        public Block getSoilBlock()
        {
        	return this.soil;
        }

        static
        {
            for (BlockRoad.EnumType blockroad$enumtype : values())
            {
                META_LOOKUP[blockroad$enumtype.getMetadata()] = blockroad$enumtype;
            }
        }
    }
}
