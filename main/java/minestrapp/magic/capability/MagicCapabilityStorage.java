package minestrapp.magic.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class MagicCapabilityStorage implements IStorage<IMagicCapability> {
	
	public static final MagicCapabilityStorage storage = new MagicCapabilityStorage();
	
	@Override
	public NBTBase writeNBT(Capability<IMagicCapability> capability, IMagicCapability instance, EnumFacing side) {
		NBTTagCompound nbt= new NBTTagCompound();
		nbt.setInteger("mana_water", ((DefaultMagicCapability)instance).water);
		nbt.setInteger("mana_fire", ((DefaultMagicCapability)instance).fire);
		nbt.setInteger("mana_earth", ((DefaultMagicCapability)instance).earth);
		nbt.setInteger("mana_wind", ((DefaultMagicCapability)instance).wind);
		nbt.setInteger("mana_elec", ((DefaultMagicCapability)instance).elec);
		nbt.setInteger("mana_frost", ((DefaultMagicCapability)instance).frost);
		nbt.setInteger("mana_life", ((DefaultMagicCapability)instance).life);
		nbt.setInteger("mana_arcane", ((DefaultMagicCapability)instance).arcane);
		
		nbt.setInteger("max_mana_water", ((DefaultMagicCapability)instance).MAX_WATER);
		nbt.setInteger("max_mana_fire", ((DefaultMagicCapability)instance).MAX_FIRE);
		nbt.setInteger("max_mana_earth", ((DefaultMagicCapability)instance).MAX_EARTH);
		nbt.setInteger("max_mana_wind", ((DefaultMagicCapability)instance).MAX_WIND);
		nbt.setInteger("max_mana_elec", ((DefaultMagicCapability)instance).MAX_ELEC);
		nbt.setInteger("max_mana_frost", ((DefaultMagicCapability)instance).MAX_FROST);
		nbt.setInteger("max_mana_life", ((DefaultMagicCapability)instance).MAX_LIFE);
		nbt.setInteger("max_mana_arcane", ((DefaultMagicCapability)instance).MAX_ARCANE);
		return nbt;
	}

	@Override
	public void readNBT(Capability<IMagicCapability> capability, IMagicCapability instance, EnumFacing side,NBTBase nbt) {
		 NBTTagCompound tag = (NBTTagCompound) nbt;
	     ((DefaultMagicCapability)instance).water = tag.getInteger("mana_water");
	     ((DefaultMagicCapability)instance).fire = tag.getInteger("mana_fire");
	     ((DefaultMagicCapability)instance).earth = tag.getInteger("mana_earth");
	     ((DefaultMagicCapability)instance).wind = tag.getInteger("mana_wind");
	     ((DefaultMagicCapability)instance).elec = tag.getInteger("mana_elec");
	     ((DefaultMagicCapability)instance).frost = tag.getInteger("mana_frost");
	     ((DefaultMagicCapability)instance).life = tag.getInteger("mana_life");
	     ((DefaultMagicCapability)instance).arcane = tag.getInteger("mana_arcane");
	     
	     ((DefaultMagicCapability)instance).MAX_WATER = tag.getInteger("max_mana_water");
	     ((DefaultMagicCapability)instance).MAX_FIRE = tag.getInteger("max_mana_fire");
	     ((DefaultMagicCapability)instance).MAX_EARTH = tag.getInteger("max_mana_earth");
	     ((DefaultMagicCapability)instance).MAX_WIND = tag.getInteger("max_mana_wind");
	     ((DefaultMagicCapability)instance).MAX_ELEC = tag.getInteger("max_mana_elec");
	     ((DefaultMagicCapability)instance).MAX_FROST = tag.getInteger("max_mana_frost");
	     ((DefaultMagicCapability)instance).MAX_LIFE = tag.getInteger("max_mana_life");
	     ((DefaultMagicCapability)instance).MAX_ARCANE = tag.getInteger("max_mana_arcane");
	}

}
