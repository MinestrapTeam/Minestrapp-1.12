package minestrapp.block;

import java.util.Random;

import minestrapp.MBlocks;
import minestrapp.Minestrapp;
import minestrapp.block.tileentity.TileEntityPressurizer;
import minestrapp.block.util.BlockBaseNonSolid;
import minestrapp.gui.MGuiHandler;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPressurizer extends BlockBaseNonSolid implements ITileEntityProvider
{	
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final PropertyBool BURNING = PropertyBool.create("burning");
	
	public BlockPressurizer()
	{
		super("pressurizer", Material.IRON, MapColor.SILVER, SoundType.STONE, 3F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(BURNING, Boolean.valueOf(false)));
		this.setRenderLayer(BlockRenderLayer.CUTOUT);
	}
	
	@Deprecated
    public int getLightValue(IBlockState state)
    {
        return ((Boolean)state.getValue(BURNING)).booleanValue() ? 5 : 0;
    }
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(MBlocks.pressurizer);
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		this.setDefaultFacing(worldIn, pos, state);
	}
	
	private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state)
	{
        if (!worldIn.isRemote) {
            IBlockState north = worldIn.getBlockState(pos.north());
            IBlockState south = worldIn.getBlockState(pos.south());
            IBlockState west = worldIn.getBlockState(pos.west());
            IBlockState east = worldIn.getBlockState(pos.east());
            EnumFacing face = (EnumFacing)state.getValue(FACING);

            if (face == EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock())
                face = EnumFacing.SOUTH;
            else if (face == EnumFacing.SOUTH && south.isFullBlock() && !north.isFullBlock())
                face = EnumFacing.NORTH;
            else if (face == EnumFacing.WEST && west.isFullBlock() && !east.isFullBlock())
                face = EnumFacing.EAST;
            else if (face == EnumFacing.EAST && east.isFullBlock() && !west.isFullBlock())
                face = EnumFacing.WEST;
            worldIn.setBlockState(pos, state.withProperty(FACING, face), 2);
        }
    }
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote)
  			player.openGui(Minestrapp.instance, MGuiHandler.PRESSURIZER, world, pos.getX(), pos.getY(), pos.getZ());
		
  		return true;
	}
	
	public static void setState(boolean active, World worldIn, BlockPos pos)
	{
		IBlockState state = worldIn.getBlockState(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		
		if(active)
			worldIn.setBlockState(pos, MBlocks.pressurizer.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(BURNING, Boolean.valueOf(true)), 3);			
		else
			worldIn.setBlockState(pos, MBlocks.pressurizer.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(BURNING, Boolean.valueOf(false)), 3);
		
		if(tileentity != null)
		{
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityPressurizer();
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
	{
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		TileEntityPressurizer tileentity = (TileEntityPressurizer)worldIn.getTileEntity(pos);
		InventoryHelper.dropInventoryItems(worldIn, pos, tileentity);
		super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
	{
		return new ItemStack(MBlocks.pressurizer);
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.MODEL;
	}
	
	public IBlockState getStateFromMeta(int meta)
	{
		EnumFacing enumfacing = EnumFacing.NORTH;
		boolean burning = false;
		
		if(meta >= 4)
			burning = true;
		
		if(meta == 1 || meta == 5)
			enumfacing = EnumFacing.SOUTH;
		else if(meta == 2 || meta == 6)
			enumfacing = EnumFacing.WEST;
		else if(meta == 3 || meta == 7)
			enumfacing = EnumFacing.EAST;

        return this.getDefaultState().withProperty(FACING, enumfacing).withProperty(BURNING, Boolean.valueOf(burning));
    }

    public int getMetaFromState(IBlockState state)
    {
		int metaMod = 0;
		
		if(state.getValue(BURNING) == Boolean.valueOf(true))
			metaMod = 4;
		
		if(state.getValue(FACING) == EnumFacing.NORTH)
			return 0 + metaMod;
		else if(state.getValue(FACING) == EnumFacing.SOUTH)
			return 1 + metaMod;
		else if(state.getValue(FACING) == EnumFacing.WEST)
			return 2 + metaMod;
		else
			return 3 + metaMod;
    }

    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }
    
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }
    
    @Override
    protected BlockStateContainer createBlockState()
    {
    	return new BlockStateContainer(this, new IProperty[] {BURNING, FACING});
    }
    
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("incomplete-switch")
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        if (stateIn.getValue(BURNING) == Boolean.valueOf(true))
        {
        	int dir = rand.nextInt(4);
            EnumFacing enumfacing = EnumFacing.NORTH;
            
            if(dir == 1)
            	enumfacing = EnumFacing.SOUTH;
            else if(dir == 2)
            	enumfacing = EnumFacing.EAST;
            else if(dir == 3)
            	enumfacing = EnumFacing.WEST;
            
            double d0 = (double)pos.getX() + 0.5D;
            double d1 = (double)pos.getY() + rand.nextDouble() * 3D / 16.0D;
            double d2 = (double)pos.getZ() + 0.5D;
            double d3 = 0.52D;
            double d4 = rand.nextDouble() - 0.5D;

            if (rand.nextDouble() < 0.1D)
            {
                worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }
            
            if(rand.nextInt(10) < 5)
            	worldIn.spawnParticle(EnumParticleTypes.CLOUD, d0 - 0.41D, pos.getY() + 1.06D, d2 - 0.41D, 0.0D, 0.07D, 0.0D);
            if(rand.nextInt(10) < 5)
            	worldIn.spawnParticle(EnumParticleTypes.CLOUD, d0 - 0.41D, pos.getY() + 1.06D, d2 + 0.41D, 0.0D, 0.07D, 0.0D);
            if(rand.nextInt(10) < 5)
            	worldIn.spawnParticle(EnumParticleTypes.CLOUD, d0 + 0.41D, pos.getY() + 1.06D, d2 - 0.41D, 0.0D, 0.07D, 0.0D);
            if(rand.nextInt(10) < 5)
            	worldIn.spawnParticle(EnumParticleTypes.CLOUD, d0 + 0.41D, pos.getY() + 1.06D, d2 + 0.41D, 0.0D, 0.07D, 0.0D);

            switch (enumfacing)
            {
                case WEST:
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 - 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                    break;
                case EAST:
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + 0.50D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + 0.50D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                    break;
                case NORTH:
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D);
                    break;
                case SOUTH:
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 + 0.50D, 0.0D, 0.0D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 + 0.50D, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}