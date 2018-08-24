package minestrapp.block;

import java.util.Set;

import minestrapp.MTabs;
import minestrapp.block.tileentity.TileEntityTanningRack;
import minestrapp.block.util.BlockBase;
import minestrapp.crafting.TannerRecipes.TannerRecipe;
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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTanningRack extends BlockBase implements ITileEntityProvider
{
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	public BlockTanningRack()
	{
		super("tanning_rack", Material.WOOD, MapColor.WOOD, SoundType.WOOD, 1F, "axe", 0);
		this.setCreativeTab(MTabs.utility);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntityTanningRack tet = (TileEntityTanningRack) world.getTileEntity(pos);
		
		ItemStack heldItem = player.getHeldItem(hand);
		
		if(!tet.isHoldingItem() && tet.tryToAddItem(heldItem, tet.angle))
				player.getHeldItem(hand).shrink(1);
		else
		{			
			if(tet.getRecipe() == null || tet.getRecipe().tool == null || tet.isTanning)
			{
				tet.takeItem();
			}
			else
			{
				boolean isTool = false;
				
				TannerRecipe recipe = tet.getRecipe();
	
				if(ItemStack.areItemsEqual(heldItem, recipe.tool)) {
					isTool = true;
				} else if(recipe.tool.getItem() instanceof ItemSword && heldItem.getItem() instanceof ItemSword) {
					isTool = true;
				} else {
					Set<String> recipeToolClasses  = recipe.tool.getItem().getToolClasses(recipe.tool);
					Set<String> heldToolClasses  = heldItem.getItem().getToolClasses(heldItem);
					
					for(String toolClass : recipeToolClasses) {
						if(heldToolClasses.contains(toolClass)){
							isTool = true;
							break;
						}
					}
				}

				if(isTool && tet.isTanning == false) {
					if(recipe.time == 0) {
						tet.hide.set(0, recipe.output);
					} else {
						tet.isTanning = true;
					}
					
					if(recipe.consumeItem) {
						heldItem.shrink(1);
					}
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
        else if(meta == 1)
        	enumfacing = EnumFacing.SOUTH;
        else if(meta == 2)
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
    		i = 1;
    	else if(enumfacing == EnumFacing.EAST)
    		i = 2;
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

    public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing)
    {
        return BlockFaceShape.UNDEFINED;
    }

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityTanningRack();
	}
}
