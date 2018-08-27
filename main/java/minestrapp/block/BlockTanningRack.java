package minestrapp.block;

import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Nullable;

import minestrapp.MItems;
import minestrapp.MTabs;
import minestrapp.block.tileentity.TileEntityCrusher;
import minestrapp.block.tileentity.TileEntityTanningRack;
import minestrapp.block.util.BlockBase;
import minestrapp.crafting.TannerRecipes.TannerRecipe;
import minestrapp.item.tools.MDagger;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTanningRack extends BlockBase implements ITileEntityProvider
{
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	protected static final AxisAlignedBB AABB_LEGS = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);
	protected static final AxisAlignedBB AABB_FACE_NORTH = new AxisAlignedBB(0.0D, 0.0D, 0.3125D, 1.0D, 1.0D, 0.4375D);
	protected static final AxisAlignedBB AABB_FACE_EAST = new AxisAlignedBB(0.5625D, 0.0D, 0.0D, 0.6875D, 1.0D, 1.0D);
	protected static final AxisAlignedBB AABB_FACE_SOUTH = new AxisAlignedBB(0.0D, 0.0D, 0.5625D, 1.0D, 1.0D, 0.6875D);
	protected static final AxisAlignedBB AABB_FACE_WEST = new AxisAlignedBB(0.3125D, 0.0D, 0.0D, 0.4375D, 1.0D, 1.0D);
	
	protected static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.8125D);
	protected static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(0.1875D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	protected static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(0.0D, 0.0D, 0.1875D, 1.0D, 1.0D, 1.0D);
	protected static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(0.8125D, 0.0D, 0.0D, 0.0D, 1.0D, 1.0D);
	
	public BlockTanningRack()
	{
		super("tanning_rack", Material.WOOD, MapColor.WOOD, SoundType.WOOD, 1F, "axe", 0);
		this.setCreativeTab(MTabs.utility);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}
	
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean p_185477_7_)
    {
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_LEGS);
        
        if(state.getValue(FACING) == EnumFacing.NORTH)
        	addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_FACE_NORTH);
        else if(state.getValue(FACING) == EnumFacing.EAST)
        	addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_FACE_EAST);
        else if(state.getValue(FACING) == EnumFacing.SOUTH)
        	addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_FACE_SOUTH);
        else
        	addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_FACE_WEST);
    }
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
		if(state.getValue(FACING) == EnumFacing.NORTH)
			return AABB_FACE_NORTH;
		else if(state.getValue(FACING) == EnumFacing.EAST)
			return AABB_FACE_EAST;
		else if(state.getValue(FACING) == EnumFacing.SOUTH)
			return AABB_FACE_SOUTH;
		else
			return AABB_FACE_WEST;
    }
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntityTanningRack tet = (TileEntityTanningRack) world.getTileEntity(pos);
		
		ItemStack heldItem = player.getHeldItem(hand);
		int angle = this.getMetaFromState(world.getBlockState(pos)) * 90;
		
		if(!tet.isHoldingItem() && tet.tryToAddItem(heldItem, angle))
		{
				player.getHeldItem(hand).shrink(1);
		}
		else
		{
			ItemStack tool = heldItem.copy();
			
			if(tool.getItem() instanceof MDagger)
			{
				if(((MDagger)tool.getItem()).getToolMaterialHarvestLevel() == 0)
				{
					tool = new ItemStack(MItems.wooden_dagger);
				}
				else if(((MDagger)tool.getItem()).getToolMaterialHarvestLevel() == 1)
				{
					tool = new ItemStack(MItems.stone_dagger);
				}
				else if(((MDagger)tool.getItem()).getToolMaterialHarvestLevel() == 2)
				{
					tool = new ItemStack(MItems.iron_dagger);
				}
				else if(((MDagger)tool.getItem()).getToolMaterialHarvestLevel() == 3)
				{
					tool = new ItemStack(MItems.diamond_dagger);
				}
				else if(((MDagger)tool.getItem()).getToolMaterialHarvestLevel() == 4)
				{
					tool = new ItemStack(MItems.titanium_dagger);
				}
			}
			
			if(tet.getRecipe(tool) == null || tet.getRecipe(tool).tool == null || tet.isTanning)
			{
				tet.takeItem();
			}
			else
			{
				boolean isTool = false;
				
				
				TannerRecipe recipe = tet.getRecipe(tool);
	
				if(ItemStack.areItemsEqual(tool, recipe.tool)) {
					isTool = true;
				} else {
					Set<String> recipeToolClasses  = recipe.tool.getItem().getToolClasses(recipe.tool);
					Set<String> heldToolClasses  = tool.getItem().getToolClasses(tool);
					
					for(String toolClass : recipeToolClasses) {
						if(heldToolClasses.contains(toolClass)){
							isTool = true;
							break;
						}
					}
				}

				if(isTool && tet.isTanning == false) {
					
					tet.lastToolUsed = tool.copy();
					
					if(recipe.time == 0) {
						tet.tryToAddItem(recipe.output, angle);
					} else {
						tet.isTanning = true;
					}
					
					if(recipe.consumeItem) {
						heldItem.shrink(1);
						if(tool.getItem() == MItems.tannic && !world.isRemote)
							world.spawnEntity(new EntityItem(world, player.posX, player.posY, player.posZ, new ItemStack(Items.GLASS_BOTTLE)));
					}
					else if(heldItem.getItem() instanceof MDagger)
						heldItem.damageItem(1, player);
				} else {
					tet.takeItem();
				}

					
			}
		}
				
		return true;
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntityTanningRack tet = (TileEntityTanningRack) worldIn.getTileEntity(pos);
		tet.takeItem();
        super.breakBlock(worldIn, pos, state);
    }

	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
    public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return false;
    }
    
    public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing)
    {
        return BlockFaceShape.UNDEFINED;
    }
    
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
    }
    
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing;
        IBlockState state = this.getDefaultState();
        
        if(meta == 0)
        	enumfacing = EnumFacing.NORTH;
        else if(meta == 2)
        	enumfacing = EnumFacing.SOUTH;
        else if(meta == 1)
        	enumfacing = EnumFacing.EAST;
        else
        	enumfacing = EnumFacing.WEST;
        
        return state.withProperty(FACING, enumfacing);
    }
    
    public int getMetaFromState(IBlockState state)
    {
    	int i;
    	EnumFacing enumfacing = state.getValue(FACING);
    	
    	if(enumfacing == EnumFacing.NORTH)
    		i = 0;
    	else if(enumfacing == EnumFacing.SOUTH)
    		i = 2;
    	else if(enumfacing == EnumFacing.EAST)
    		i = 1;
    	else
    		i = 3;
    	
    	return i;
    }
    
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }
    
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityTanningRack();
	}
	
	@SideOnly(Side.CLIENT)
    @SuppressWarnings("incomplete-switch")
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
		TileEntityTanningRack tet = (TileEntityTanningRack) worldIn.getTileEntity(pos);
		
		if (tet.isTanning == true && tet.getRecipe(tet.lastToolUsed) != null && tet.getRecipe(tet.lastToolUsed).particle != null)
        {
            EnumFacing enumfacing = (EnumFacing)stateIn.getValue(FACING);
            double d0 = (double)pos.getX() + 0.5D;
            double d1 = (double)pos.getY() + rand.nextDouble() * 12D / 16D + 0.125D;
            double d2 = (double)pos.getZ() + 0.5D;
            double d3 = 0.2D;
            double d4 = rand.nextDouble() * 0.6D - 0.3D;

            if (rand.nextDouble() < 0.1D)
            {
                worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 0.6F, 2.0F, false);
            }

            switch (enumfacing)
            {
                case EAST:
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                    break;
                case WEST:
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                    break;
                case SOUTH:
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 - d3, 0.0D, 0.0D, 0.0D);
                    break;
                case NORTH:
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 + d3, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}
