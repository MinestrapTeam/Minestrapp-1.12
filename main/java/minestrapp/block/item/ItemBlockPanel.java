package minestrapp.block.item;

import minestrapp.block.util.BlockPanelBase;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlockPanel extends MItemBlock
{
	public ItemBlockPanel(BlockPanelBase block)
	{
		super(block);
	}
	
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		//Get the player's held item.
        ItemStack itemstack = player.getHeldItem(hand);
        
        //If the player is holding an item and can edit the block in question,
        if (!itemstack.isEmpty() && player.canPlayerEdit(pos.offset(facing), facing, itemstack))
        {
        	//save the blockstate at the position where the player is trying to place the panel.
        	IBlockState state = worldIn.getBlockState(pos);
        	
        	//System.out.println(state.getBlock().getUnlocalizedName());
        	
        	//If the block is a matching panel and it isn't already a double-panel,
        	if (state.getBlock() == this.block && state.getValue(BlockPanelBase.FACING).getAxis() != EnumFacing.Axis.Y)
            {
        		//check its facing.
        		EnumFacing panelFacing = state.getValue(BlockPanelBase.FACING);
        		
        		//If it is facing the opposite direction of the face the player clicked on,
        		if (facing == panelFacing.getOpposite())
        		{
        			//and there are no entities in the way, and it successfully places the double-panel,
        			if (worldIn.checkNoEntityCollision(Block.FULL_BLOCK_AABB.offset(pos)) && worldIn.setBlockState(pos, state.withProperty(BlockPanelBase.FACING, EnumFacing.UP), 11))
                    {
        				//play a sound and reduce the itemstack by 1.
        				SoundType soundtype = this.block.getSoundType(state.withProperty(BlockPanelBase.FACING, EnumFacing.UP), worldIn, pos, player);
                        worldIn.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                        itemstack.shrink(1);

                        //Then increase the player's Placed Block criteria value.
                        if (player instanceof EntityPlayerMP)
                        {
                            CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, itemstack);
                        }
                    }
        			
        			//Item successfully used.
        			return EnumActionResult.SUCCESS;
        		}
            }
        	
        	state = worldIn.getBlockState(pos.offset(facing));
        	
        	if(state.getBlock() == this.block && state.getValue(BlockPanelBase.FACING).getAxis() != EnumFacing.Axis.Y)
        	{
        		if (worldIn.checkNoEntityCollision(Block.FULL_BLOCK_AABB.offset(pos.offset(facing))) && worldIn.setBlockState(pos.offset(facing), state.withProperty(BlockPanelBase.FACING, EnumFacing.UP), 11))
                {
    				//play a sound and reduce the itemstack by 1.
    				SoundType soundtype = this.block.getSoundType(state.withProperty(BlockPanelBase.FACING, EnumFacing.UP), worldIn, pos, player);
                    worldIn.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                    itemstack.shrink(1);

                    //Then increase the player's Placed Block criteria value.
                    if (player instanceof EntityPlayerMP)
                    {
                        CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, itemstack);
                    }
                }
    			
    			//Item successfully used.
    			return EnumActionResult.SUCCESS;
        	}
        	
        	return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
        }
        
        return EnumActionResult.FAIL;
    }
	
	@SideOnly(Side.CLIENT)
    public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side, EntityPlayer player, ItemStack stack)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos.offset(side));
       
        if (iblockstate.getBlock() == this.block && iblockstate.getValue(BlockPanelBase.FACING).getAxis() != EnumFacing.Axis.Y)
        {
            return true;
        }

        return super.canPlaceBlockOnSide(worldIn, pos, side, player, stack);
    }
	
	public static EnumFacing getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
		if(facing.getAxis().isHorizontal())
			return facing.getOpposite();
		else
		{
			EnumFacing playerDir = placer.getHorizontalFacing();
			if(playerDir.getAxis() == EnumFacing.Axis.Z)
			{
				if(hitZ > 0.5F)
					return EnumFacing.SOUTH;
				else
					return EnumFacing.NORTH;
			}
			else
			{
				if(hitX > 0.5F)
					return EnumFacing.EAST;
				else
					return EnumFacing.WEST;
			}
		}
    }
}
