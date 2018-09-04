package minestrapp.magic.capability;

import minestrapp.Minestrapp5;
import minestrapp.magic.EnumMagicType;
import minestrapp.network.MagicUpdateMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;

public class DefaultMagicCapability implements IMagicCapability{
	
	//Amount of mana player holds of each type
	public int water = 16;
	public int fire = 16;
	public int earth = 16;
	public int wind = 16;
	public int elec = 16;
	public int frost = 16;
	public int life = 16;
	public int arcane = 16;
	
	//Max mana player can have of each type
	public int MAX_WATER = 16;
	public int MAX_FIRE = 16;
	public int MAX_EARTH = 16;
	public int MAX_WIND = 16;
	public int MAX_ELEC = 16;
	public int MAX_LIFE = 16;
	public int MAX_FROST = 16;
	public int MAX_ARCANE = 16;
	
	@Override
	public void changeManaForType(EntityPlayer player, EnumMagicType type, int amount) {
		switch(type) {
		case WATER:
			this.water += this.getModifiedAmount(type, amount);
			break;
		case FIRE:
			this.fire += this.getModifiedAmount(type, amount);
			break;
		case EARTH:
			this.earth += this.getModifiedAmount(type, amount);
			break;
		case WIND:
			this.wind += this.getModifiedAmount(type, amount);
			break;
		case ELEC:
			this.elec += this.getModifiedAmount(type, amount);
			break;
		case FROST:
			this.frost += this.getModifiedAmount(type, amount);
			break;
		case LIFE:
			this.life += this.getModifiedAmount(type, amount);
			break;
		case ARCANE:
			this.arcane += this.getModifiedAmount(type, amount);
			break;
		case NONE:
			break;
		}
		this.dataChanged(player);
	}
	
	private int getModifiedAmount(EnumMagicType type, int amount) {
		if(this.getManaForType(type) + amount > this.getMaxManaForType(type)) {
			amount = this.getMaxManaForType(type) - this.getManaForType(type);
		}
		return amount;
	}
	
	@Override
	public void setManaForType(EntityPlayer player, EnumMagicType type, int amount) {
		switch(type) {
		case WATER:
			this.water = amount;
			break;
		case FIRE:
			this.fire = amount;
			break;
		case EARTH:
			this.earth = amount;
			break;
		case WIND:
			this.wind = amount;
			break;
		case ELEC:
			this.elec = amount;
			break;
		case FROST:
			this.frost = amount;
			break;
		case LIFE:
			this.life = amount;
			break;
		case ARCANE:
			this.arcane = amount;
			break;
		case NONE:
			break;
		}
		this.dataChanged(player);
	}
	
	@Override
	public int getManaForType(EnumMagicType type) {
		switch(type) {
		case WATER:
			return this.water;
		case FIRE:
			return this.fire;
		case EARTH:
			return this.earth;
		case WIND:
			return this.wind;
		case ELEC:
			return this.wind;
		case FROST:
			return this.frost;
		case LIFE:
			return this.life;
		case ARCANE:
			return this.arcane;
		case NONE:
			return 0;
		}
		return 0;
	}
	
	@Override
	public int getMaxManaForType(EnumMagicType type) {
		switch(type) {
		case WATER:
			return this.MAX_WATER;
		case FIRE:
			return this.MAX_FIRE;
		case EARTH:
			return this.MAX_EARTH;
		case WIND:
			return this.MAX_WIND;
		case ELEC:
			return this.MAX_ELEC;
		case FROST:
			return this.MAX_FROST;
		case LIFE:
			return this.MAX_LIFE;
		case ARCANE:
			return this.MAX_ARCANE;
		case NONE:
			return 0;
		}
		return 0;
	}
	
	@Override
	public void resetMana(EntityPlayer player) {
		for(int i = 0; i < EnumMagicType.values().length; i++) {
			EnumMagicType type = EnumMagicType.getById(i);
			this.setManaForType(player, type, Math.max(16, this.getMaxManaForType(type) / 2));
		}
	}
	
	@Override
	public void dataChanged(EntityPlayer player) {
		if(player != null && !player.getEntityWorld().isRemote){
			Minestrapp5.proxy.network.sendTo(new MagicUpdateMessage(player, saveNBTData()), (EntityPlayerMP) player);
		}
	}

	@Override
	public NBTTagCompound saveNBTData() {
		return (NBTTagCompound)MagicCapabilityStorage.storage.writeNBT(MagicProvider.MagicCapability, this, null);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		MagicCapabilityStorage.storage.readNBT(MagicProvider.MagicCapability, this, null, compound);
	}
}
