package minestrapp.item.armor;

import minestrapp.MItems;
import minestrapp.MTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;

public class MArmor extends ItemArmor
{
	//armorType feet,legs,chest,head
	/*public MArmor(EntityEquipmentSlot.Type.Armor armorType, ArmorMaterial material, int damageReduceAmount, String name)
	{
		super(material, damageReduceAmount, armorType);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(MTabs.tools);
	} */
	
	public MArmor(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, String name){
		
		super(materialIn, renderIndexIn, equipmentSlotIn);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(MTabs.tools);
		
	}
	
}
