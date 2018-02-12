package minestrapp.block;

import java.util.Random;

import javax.annotation.Nullable;

import minestrapp.MBlocks;
import minestrapp.MTabs;
import minestrapp.item.tools.MDagger;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockClutchthorn extends BlockBush implements IGrowable
{
	public static final PropertyEnum<BlockClutchthorn.EnumThornSection> SECTION = PropertyEnum.<BlockClutchthorn.EnumThornSection>create("section", BlockClutchthorn.EnumThornSection.class);
	public static final PropertyBool GLOWING = PropertyBool.create("glowing");
	
	public BlockClutchthorn()
	{
		super(Material.VINE);
		this.setDefaultState(this.blockState.getBaseState().withProperty(SECTION, BlockClutchthorn.EnumThornSection.DEFAULT).withProperty(GLOWING, Boolean.valueOf(false)));
		this.setUnlocalizedName("clutchthorn");
		this.setRegistryName("clutchthorn");
		this.setHardness(0.2F);
		this.setSoundType(SoundType.PLANT);
		this.setCreativeTab(MTabs.plant);
	}
	
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
		BlockClutchthorn.EnumThornSection section = BlockClutchthorn.EnumThornSection.DEFAULT;
		Boolean glowing = false;
		
        if(worldIn.getBlockState(pos.down()).getBlock() instanceof BlockClutchthorn)
        	section = BlockClutchthorn.EnumThornSection.FLOWER;
        else if(worldIn.getBlockState(pos.up()).getBlock() instanceof BlockClutchthorn)
        	section = BlockClutchthorn.EnumThornSection.BOTTOM;
        
        if(worldIn.getBlockState(pos.down()) == MBlocks.portal_dust.getDefaultState().withProperty(BlockPortalDust.VARIANT, BlockMDirt.DirtType.PODZOL) || (worldIn.getBlockState(pos.down()).getBlock() instanceof BlockClutchthorn && worldIn.getBlockState(pos.down()).getActualState(worldIn, pos.down()).getValue(GLOWING).booleanValue() == true))
        	glowing = true;
        	
        return state.withProperty(SECTION, section).withProperty(GLOWING, Boolean.valueOf(glowing));
    }
	
	public int getMetaFromState(IBlockState state)
    {
        return 0;
    }
	
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {SECTION, GLOWING});
    }
	
	public MapColor getMapColor(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return MapColor.NETHERRACK;
    }
	
	@Deprecated
    public int getLightValue(IBlockState state)
    {
        return ((Boolean)state.getValue(GLOWING)).booleanValue() ? 10 : 0;
    }
	
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        IBlockState soil = worldIn.getBlockState(pos.down());
        
        if(soil.getBlock() instanceof BlockClutchthorn)
        	return false;
        
        return this.canSustainBush(soil);
    }
	
	protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() == MBlocks.portal_dust || state.getBlock() == MBlocks.fargrowth || state.getBlock() == Blocks.END_STONE || state.getBlock() instanceof BlockClutchthorn;
    }
	
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        IBlockState soil = worldIn.getBlockState(pos.down());
        
        return this.canSustainBush(soil);
    }
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return FULL_BLOCK_AABB;
    }
	
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
    {
        return true;
    }
	
	public int quantityDropped(Random random)
    {
		return 0;
    }
	
	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
    {
        if (!worldIn.isRemote && stack.getItem() == Items.SHEARS)
        {
            player.addStat(StatList.getBlockStats(this));
            spawnAsEntity(worldIn, pos, new ItemStack(MBlocks.clutchthorn));
        }
        else
        {
        	if(!(stack.getItem() instanceof ItemTool) && !(stack.getItem() instanceof ItemSword) && !(stack.getItem() instanceof ItemHoe) && !(stack.getItem() instanceof MDagger))
        		player.attackEntityFrom(DamageSource.CACTUS, 1);
        	
            super.harvestBlock(worldIn, player, pos, state, te, stack);
        }
    }
	
	public Block.EnumOffsetType getOffsetType()
    {
        return Block.EnumOffsetType.XZ;
    }
	
	public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return 60;
    }
	
	public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return 30;
    }
	
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
		if(!(entityIn instanceof EntityEnderman) && !(entityIn instanceof EntityEndermite))
		{
	        entityIn.motionX *= 0.3D;
	        entityIn.motionZ *= 0.3D;
	        
	        if(worldIn.rand.nextInt(120) == 1)
	        	entityIn.attackEntityFrom(DamageSource.CACTUS, 1);
		}
    }
	
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
		return state.getActualState(worldIn, pos).getValue(SECTION) == BlockClutchthorn.EnumThornSection.DEFAULT;
    }
	
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
		return state.getActualState(worldIn, pos).getValue(SECTION) == BlockClutchthorn.EnumThornSection.DEFAULT;
    }
	
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
		if(worldIn.isAirBlock(pos.up()))
			worldIn.setBlockState(pos.up(), MBlocks.clutchthorn.getDefaultState());
    }
	
	public static enum EnumThornSection implements IStringSerializable
    {
		DEFAULT("default"),
        BOTTOM("bottom"),
        FLOWER("flower");

        private static final BlockClutchthorn.EnumThornSection[] META_LOOKUP = new BlockClutchthorn.EnumThornSection[values().length];
        private final String name;

        private EnumThornSection(String name)
        {
            this.name = name;
        }

        public String toString()
        {
            return this.name;
        }

        public String getName()
        {
            return this.name;
        }
    }
}
