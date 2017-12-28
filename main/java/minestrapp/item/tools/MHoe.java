package minestrapp.item.tools;

import minestrapp.MItems;
import minestrapp.MTabs;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MHoe extends ItemHoe
{
	public MHoe(ToolMaterial material, String name)
	{
		super(material);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(MTabs.tools);
	}
	
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        if(this.toolMaterial == MItems.BLAZIUM)
        {
        	target.setFire(4);
        }
        else if(this.toolMaterial == MItems.GLACIERITE)
        {
        	target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 200, 1));
        }
        return super.hitEntity(stack, target, attacker);
    }
	
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
    	if(this.toolMaterial == MItems.BLAZIUM && super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ) == EnumActionResult.PASS)
    	{
	        pos = pos.offset(facing);
	        ItemStack itemstack = player.getHeldItem(hand);
	
	        if (!player.canPlayerEdit(pos, facing, itemstack))
	        {
	            return EnumActionResult.FAIL;
	        }
	        else
	        {
	            if (worldIn.isAirBlock(pos))
	            {
	                worldIn.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
	                worldIn.setBlockState(pos, Blocks.FIRE.getDefaultState(), 11);
	            }
	
	            if (player instanceof EntityPlayerMP)
	            {
	                CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, itemstack);
	            }
	
	            itemstack.damageItem(4, player);
	            return EnumActionResult.SUCCESS;
	        }
    	}
    	return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }
}
