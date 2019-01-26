package minestrapp.item;

import java.util.Iterator;

import minestrapp.MItems;
import minestrapp.MTabs;
import minestrapp.potion.MPotions;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiScreenEvent.PotionShiftEvent;

public class ItemSmellingSalts extends Item
{
	public ItemSmellingSalts()
	{
		this.setUnlocalizedName("smelling_salts");
		this.setRegistryName("smelling_salts");
		this.setCreativeTab(MTabs.minerals);
	}
	
	public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BOW;
    }
	
	public int getMaxItemUseDuration(ItemStack stack)
    {
        return 40;
    }
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        
        if(playerIn.isPotionActive(MobEffects.BLINDNESS) || playerIn.isPotionActive(MobEffects.GLOWING) || playerIn.isPotionActive(MobEffects.HUNGER) || playerIn.isPotionActive(MobEffects.LEVITATION) || playerIn.isPotionActive(MobEffects.MINING_FATIGUE) || playerIn.isPotionActive(MobEffects.NAUSEA) || playerIn.isPotionActive(MobEffects.POISON) || playerIn.isPotionActive(MobEffects.SLOWNESS) || playerIn.isPotionActive(MobEffects.UNLUCK) || playerIn.isPotionActive(MobEffects.WEAKNESS) || playerIn.isPotionActive(MobEffects.WITHER))
        {
            playerIn.setActiveHand(handIn);
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
        }
        else
        {
            return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
        }
    }
	
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BREATH, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);

            if (!worldIn.isRemote)
            {
	            entityplayer.removePotionEffect(MobEffects.BLINDNESS);
	            entityplayer.removePotionEffect(MobEffects.GLOWING);
	            entityplayer.removePotionEffect(MobEffects.HUNGER);
	            entityplayer.removePotionEffect(MobEffects.LEVITATION);
	            entityplayer.removePotionEffect(MobEffects.MINING_FATIGUE);
	            entityplayer.removePotionEffect(MobEffects.NAUSEA);
	            entityplayer.removePotionEffect(MobEffects.POISON);
	            entityplayer.removePotionEffect(MobEffects.SLOWNESS);
	            entityplayer.removePotionEffect(MobEffects.UNLUCK);
	            entityplayer.removePotionEffect(MobEffects.WEAKNESS);
	            entityplayer.removePotionEffect(MobEffects.WITHER);
	            entityplayer.removePotionEffect(MPotions.infection);
	            entityplayer.removePotionEffect(MPotions.aggression);
	            entityplayer.removePotionEffect(MPotions.hydrophobia);
            }
            
            entityplayer.addStat(StatList.getObjectUseStats(this));

            if (entityplayer instanceof EntityPlayerMP)
            {
                CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP)entityplayer, stack);
            }
        }

        stack.shrink(1);
        return stack;
    }
}
