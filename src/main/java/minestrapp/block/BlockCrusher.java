package minestrapp.block;

import java.util.Random;

import minestrapp.MBlocks;
import minestrapp.MTabs;
import minestrapp.Minestrapp5;
import minestrapp.block.tileentity.TileEntityCrusher;
import minestrapp.block.util.BlockBase;
import minestrapp.block.util.BlockStoneBaseMOnly;
import minestrapp.gui.MGuiHandler;
import net.minecraft.block.Block;
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
import net.minecraft.client.particle.ParticleDigging;
import net.minecraft.creativetab.CreativeTabs;
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

public class BlockCrusher extends BlockBase implements ITileEntityProvider
{
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final PropertyBool BURNING = PropertyBool.create("burning");
	
	public BlockCrusher()
	{
		super("crusher", Material.IRON, MapColor.YELLOW_STAINED_HARDENED_CLAY, SoundType.METAL, 3F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(BURNING, Boolean.valueOf(false)));
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(MBlocks.crusher);
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		this.setDefaultFacing(worldIn, pos, state);
	}
	
	private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state){
        if (!worldIn.isRemote) {
            IBlockState north = worldIn.getBlockState(pos.north());
            IBlockState south = worldIn.getBlockState(pos.south());
            IBlockState west = worldIn.getBlockState(pos.west());
            IBlockState east = worldIn.getBlockState(pos.east());
            EnumFacing face = (EnumFacing)state.getValue(FACING);
           
           
            if (face == EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock()) {
            	face = EnumFacing.SOUTH; 
            }else if (face == EnumFacing.SOUTH && south.isFullBlock() && !north.isFullBlock()) {
            	 face = EnumFacing.NORTH;
            } else if (face == EnumFacing.WEST && west.isFullBlock() && !east.isFullBlock()) {
            	 face = EnumFacing.EAST;
            } else if (face == EnumFacing.EAST && east.isFullBlock() && !west.isFullBlock()) {
            	face = EnumFacing.WEST;
            }
            worldIn.setBlockState(pos, state.withProperty(FACING, face), 2);      	          
        }
    }
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote)
  			player.openGui(Minestrapp5.instance, MGuiHandler.CRUSHER, world, pos.getX(), pos.getY(), pos.getZ());  			

		TileEntityCrusher tec = (TileEntityCrusher)world.getTileEntity(pos);
		EnumFacing face = (EnumFacing)state.getValue(FACING);
		if(face == EnumFacing.NORTH) {
			tec.itemAngel = 0;
		}
		if(face == EnumFacing.SOUTH) {
			tec.itemAngel = 90;
		}
		if(face == EnumFacing.WEST) {
			tec.itemAngel = 180;
		}
		if(face == EnumFacing.EAST) {
			tec.itemAngel = 270;
		}
  		return true;

	}
	
	public static void setState(boolean active, World worldIn, BlockPos pos)
	{
		IBlockState state = worldIn.getBlockState(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		
		if(active)
			worldIn.setBlockState(pos, MBlocks.crusher.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(BURNING, Boolean.valueOf(true)), 3);
		else
			worldIn.setBlockState(pos, MBlocks.crusher.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(BURNING, Boolean.valueOf(false)), 3);
		
		if(tileentity != null)
		{
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityCrusher();
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
		TileEntityCrusher tileentity = (TileEntityCrusher)worldIn.getTileEntity(pos);
		InventoryHelper.dropInventoryItems(worldIn, pos, tileentity);
		super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
	{
		return new ItemStack(MBlocks.crusher);
	}
	
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
	
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
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
        if (stateIn.getValue(BURNING) == Boolean.valueOf(true) && worldIn.getTileEntity(pos) != null && !((TileEntityCrusher)worldIn.getTileEntity(pos)).getStackInSlot(0).isEmpty())
        {
        	ItemStack stack = ((TileEntityCrusher)worldIn.getTileEntity(pos)).getStackInSlot(0);
            EnumFacing enumfacing = (EnumFacing)stateIn.getValue(FACING);
            double d0 = (double)pos.getX() + 0.5D;
            double d1 = (double)pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
            double d2 = (double)pos.getZ() + 0.5D;
            double d3 = 0.52D;
            double d4 = rand.nextDouble() * 0.6D - 0.3D;

            if (rand.nextDouble() < 0.1D)
            {
                worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_GRAVEL_HIT, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }

            switch (enumfacing)
            {
                case WEST:
                    worldIn.spawnParticle(EnumParticleTypes.ITEM_CRACK, d0 - 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D, Item.getIdFromItem(stack.getItem()), stack.getMetadata());
                    break;
                case EAST:
                    worldIn.spawnParticle(EnumParticleTypes.ITEM_CRACK, d0 + 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D, Item.getIdFromItem(stack.getItem()), stack.getMetadata());
                    break;
                case NORTH:
                    worldIn.spawnParticle(EnumParticleTypes.ITEM_CRACK, d0 + d4, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D, Item.getIdFromItem(stack.getItem()), stack.getMetadata());
                    break;
                case SOUTH:
                    worldIn.spawnParticle(EnumParticleTypes.ITEM_CRACK, d0 + d4, d1, d2 + 0.52D, 0.0D, 0.0D, 0.0D, Item.getIdFromItem(stack.getItem()), stack.getMetadata());
            }
        }
    }
}