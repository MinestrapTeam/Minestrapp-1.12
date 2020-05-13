package minestrapp.item;

import minestrapp.MBlocks;
import minestrapp.block.BlockMLog;
import minestrapp.block.BlockMPlanks;
import minestrapp.item.util.MItemsFood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemCharroot extends MItemsFood
{
	public ItemCharroot()
	{
		super(1, 2F, false, "charroot");
	}
	
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        net.minecraft.block.state.IBlockState state = worldIn.getBlockState(pos);
        if (facing.getAxis().isHorizontal() && player.canPlayerEdit(pos.offset(facing), facing, itemstack) && state.getBlock() == MBlocks.log && state.getValue(BlockMLog.VARIANT) == BlockMPlanks.EnumType.CHARWOOD && worldIn.getBlockState(pos.offset(facing)).getBlock().isReplaceable(worldIn, pos))
        {
            worldIn.setBlockState(pos.offset(facing), MBlocks.charwood_limb.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, 0, player, hand), 11);
            itemstack.shrink(1);
            return EnumActionResult.SUCCESS;
        }
        else
        {
            return EnumActionResult.FAIL;
        }
    }
	
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
		player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 100, 0));
		player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 140, 0));
    }
}
