package minestrapp.magic.capability;

import minestrapp.magic.EnumMagicType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public interface IMagicCapability {
	public void changeManaForType(EntityPlayer player, EnumMagicType type, int amount);
	public void setManaForType(EntityPlayer player, EnumMagicType type, int amount);
	public int getManaForType(EnumMagicType type);
	public int getMaxManaForType(EnumMagicType type);
	public void resetMana(EntityPlayer player);
	
	public void dataChanged(EntityPlayer player);
	public NBTTagCompound saveNBTData();
	public void loadNBTData(NBTTagCompound compound);

}
