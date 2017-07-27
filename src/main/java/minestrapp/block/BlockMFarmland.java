package minestrapp.block;

import java.util.Random;

import minestrapp.MBlocks;
import minestrapp.block.util.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMFarmland extends BlockBase
{
	public static final PropertyInteger MOISTURE = PropertyInteger.create("moisture", 0, 7);
    protected static final AxisAlignedBB FARMLAND_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.9375D, 1.0D);
    private BlockMDirt dirt;
	
	public BlockMFarmland(String name, MapColor mapColor, SoundType soundType, float hardness, int harvestLevel, BlockMDirt dirt)
	{
		super(name, Material.GROUND, mapColor, soundType, hardness, "shovel", harvestLevel);
		this.setDefaultState(this.blockState.getBaseState().withProperty(MOISTURE, Integer.valueOf(0)));
        this.setTickRandomly(true);
        this.setLightOpacity(255);
	}

	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return FARMLAND_AABB;
    }
	
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
    	if(this == MBlocks.clay_farmland)
    	{
	        int i = ((Integer)state.getValue(MOISTURE)).intValue();
	
	        if (!this.hasWater(worldIn, pos) && !worldIn.isRainingAt(pos.up()))
	        {
	            if (i > 0)
	            {
	                worldIn.setBlockState(pos, state.withProperty(MOISTURE, Integer.valueOf(i - 1)), 2);
	            }
	            else if (!this.hasCrops(worldIn, pos))
	            {
	                this.turnToDirt(worldIn, pos);
	            }
	        }
	        else if (i < 7)
	        {
	            worldIn.setBlockState(pos, state.withProperty(MOISTURE, Integer.valueOf(7)), 2);
	        }
    	}
    }
    
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance)
    {
    	if(this == MBlocks.clay_farmland)
    	{
	        if (!worldIn.isRemote && entityIn.canTrample(worldIn, this, pos, fallDistance))
	        {
	            this.turnToDirt(worldIn, pos);
	        }
    	}

        super.onFallenUpon(worldIn, pos, entityIn, fallDistance);
    }
    
    private void turnToDirt(World world, BlockPos pos)
    {
    	IBlockState iblockstate = Blocks.DIRT.getDefaultState();
    	if(this == MBlocks.clay_farmland)
    		iblockstate = MBlocks.clay_soil.getDefaultState();
    	else if(this == MBlocks.permafrost_farmland)
    		iblockstate = MBlocks.permafrost.getDefaultState();
        world.setBlockState(pos, iblockstate);
        AxisAlignedBB axisalignedbb = iblockstate.getCollisionBoundingBox(world, pos).offset(pos);

        for (Entity entity : world.getEntitiesWithinAABBExcludingEntity((Entity)null, axisalignedbb))
        {
            entity.setPosition(entity.posX, axisalignedbb.maxY, entity.posZ);
        }
    }
    
    private boolean hasCrops(World worldIn, BlockPos pos)
    {
        Block block = worldIn.getBlockState(pos.up()).getBlock();
        return block instanceof net.minecraftforge.common.IPlantable && canSustainPlant(worldIn.getBlockState(pos), worldIn, pos, net.minecraft.util.EnumFacing.UP, (net.minecraftforge.common.IPlantable)block);
    }
    
    private boolean hasWater(World worldIn, BlockPos pos)
    {
        for (BlockPos.MutableBlockPos blockpos$mutableblockpos : BlockPos.getAllInBoxMutable(pos.add(-4, 0, -4), pos.add(4, 1, 4)))
        {
            if (worldIn.getBlockState(blockpos$mutableblockpos).getMaterial() == Material.WATER)
            {
                return true;
            }
        }

        return false;
    }
    
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos);

        if (worldIn.getBlockState(pos.up()).getMaterial().isSolid())
        {
            this.turnToDirt(worldIn, pos);
        }
    }
    
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        super.onBlockAdded(worldIn, pos, state);

        if (worldIn.getBlockState(pos.up()).getMaterial().isSolid())
        {
            this.turnToDirt(worldIn, pos);
        }
    }
    
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        switch (side)
        {
            case UP:
                return true;
            case NORTH:
            case SOUTH:
            case WEST:
            case EAST:
                IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
                Block block = iblockstate.getBlock();
                return !iblockstate.isOpaqueCube() && block != Blocks.FARMLAND && block != Blocks.GRASS_PATH && block != MBlocks.clay_grass_path && block != MBlocks.lichen_path && block != MBlocks.clay_farmland && block != MBlocks.permafrost_farmland;
            default:
                return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
        }
    }
    
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return this.dirt.getItemDropped(this.dirt.getDefaultState().withProperty(BlockMDirt.VARIANT, BlockMDirt.DirtType.DEFAULT), rand, fortune);
    }
    
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(MOISTURE, Integer.valueOf(meta & 7));
    }
    
    public int getMetaFromState(IBlockState state)
    {
        return ((Integer)state.getValue(MOISTURE)).intValue();
    }
    
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {MOISTURE});
    }
    
    public BlockFaceShape func_193383_a(IBlockAccess p_193383_1_, IBlockState p_193383_2_, BlockPos p_193383_3_, EnumFacing p_193383_4_)
    {
        return p_193383_4_ == EnumFacing.DOWN ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
    }
    
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable)
    {
        IBlockState plant = plantable.getPlant(world, pos.offset(direction));
        net.minecraftforge.common.EnumPlantType plantType = plantable.getPlantType(world, pos.offset(direction));

        switch (plantType)
        {
        	case Crop: return true;
            case Plains:
            	if(plantable instanceof BlockFlower)
            		return true;
            	else
            		return this == MBlocks.clay_grass;
            default: break;
        }

        return false;
    }
}
