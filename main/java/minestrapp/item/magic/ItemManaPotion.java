package minestrapp.item.magic;

import minestrapp.MTabs;
import minestrapp.item.util.ItemBase;
import minestrapp.magic.EnumMagicType;
import minestrapp.magic.IMagicItem;
import minestrapp.magic.capability.MagicProvider;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemManaPotion extends ItemBase implements IMagicItem{
	
	public ItemManaPotion(String name) {
		super(name);
		this.setCreativeTab(MTabs.combat);
	}
	
	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn){
		if(!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
			stack.getTagCompound().setInteger(EnumMagicType.WATER.getName(), 4);
			stack.getTagCompound().setInteger(EnumMagicType.FIRE.getName(), 4);
			stack.getTagCompound().setInteger(EnumMagicType.EARTH.getName(), 4);
			stack.getTagCompound().setInteger(EnumMagicType.WIND.getName(), 4);
			stack.getTagCompound().setInteger(EnumMagicType.ELEC.getName(), 4);
			stack.getTagCompound().setInteger(EnumMagicType.FROST.getName(), 4);
			stack.getTagCompound().setInteger(EnumMagicType.LIFE.getName(), 4);
			stack.getTagCompound().setInteger(EnumMagicType.ARCANE.getName(), 4);
		} 
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase entityLiving) {
		EntityPlayer player = (EntityPlayer) entityLiving;
		
		for(int i = 0; i < EnumMagicType.values().length; i++) {
			MagicProvider.get(player).changeManaForType(player, EnumMagicType.getById(i), 4);
		}
		
		return stack;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.setActiveHand(handIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
        return 32;
    }
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack){
        return EnumAction.DRINK;
    }

}
