package minestrapp.block.item;

import minestrapp.block.util.BlockPanelBase;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemBlockPanel extends MItemBlock
{
	public ItemBlockPanel(BlockPanelBase block)
	{
		super(block);
	}
	
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = player.getHeldItem(hand);

        if (!itemstack.isEmpty() && player.canPlayerEdit(pos.offset(facing), facing, itemstack))
        {
        	IBlockState state = worldIn.getBlockState(pos);
        	
        	if (state.getBlock() == this.block && state.getValue(BlockPanelBase.FACING).getAxis() != EnumFacing.Axis.Y)
            {
        		EnumFacing panelFacing = state.getValue(BlockPanelBase.FACING);
        		
        		if(facing == panelFacing.getOpposite())
        		{
        			if (worldIn.checkNoEntityCollision(Block.FULL_BLOCK_AABB.offset(pos)) && worldIn.setBlockState(pos, state.withProperty(BlockPanelBase.FACING, EnumFacing.UP), 11))
                    {
        				SoundType soundtype = this.block.getSoundType(state.withProperty(BlockPanelBase.FACING, EnumFacing.UP), worldIn, pos, player);
                        worldIn.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                        itemstack.shrink(1);

                        if (player instanceof EntityPlayerMP)
                        {
                            CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, itemstack);
                        }
                    }
        			
        			return EnumActionResult.SUCCESS;
        		}
            }
        	
        	return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
        }
        
        return EnumActionResult.FAIL;
    }
}
