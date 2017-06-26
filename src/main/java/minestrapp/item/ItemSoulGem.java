package minestrapp.item;

import java.util.Random;

import minestrapp.MItems;
import minestrapp.MTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemSoulGem extends Item
{
	public ItemSoulGem(String name)
	{
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(MTabs.minerals);
	}
	
	public boolean hasEffect(ItemStack item)
	{
		return true;
	}
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		ItemStack stack = playerIn.getHeldItem(handIn);
		playerIn.addExperienceLevel(1);
		worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.5F + 0.7F));
		this.useEffects(worldIn, playerIn.getPosition());
		stack.shrink(1);
		return super.onItemRightClick(worldIn, playerIn, handIn);
    }
	
	public void useEffects(World worldIn, BlockPos blockPosIn)
	{
		Random random = worldIn.rand;
		double d0 = (double)blockPosIn.getX() + 0.5D;
        double d1 = (double)blockPosIn.getY();
        double d2 = (double)blockPosIn.getZ() + 0.5D;

        for (int j = 0; j < 8; ++j)
        {
            worldIn.spawnParticle(EnumParticleTypes.ITEM_CRACK, d0, d1 + 1, d2, random.nextGaussian() * 0.15D, random.nextDouble() * 0.2D, random.nextGaussian() * 0.15D, Item.getIdFromItem(MItems.gem_soul));
        }
        
        for (double d11 = 0.0D; d11 < (Math.PI * 2D); d11 += 0.15707963267948966D)
        {
        	worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0 + Math.cos(d11) * 5.0D, d1 + 0.6D, d2 + Math.sin(d11) * 5.0D, Math.cos(d11) * -7.0D, 0.0D, Math.sin(d11) * -7.0D);
        }
	}
}
