package minestrapp.utils;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EntityUtil {

	public static boolean isWearingArmor(EntityLivingBase living, Item head, Item torso, Item legs, Item feet) {
		if(!living.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty() && !living.getItemStackFromSlot(EntityEquipmentSlot.CHEST).isEmpty() && !living.getItemStackFromSlot(EntityEquipmentSlot.LEGS).isEmpty() && !living.getItemStackFromSlot(EntityEquipmentSlot.FEET).isEmpty()) {
			if(living.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == head && living.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == torso && living.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == legs && living.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == feet) {
				return true;
			}
		}
		
		return false;
	}
	
}
