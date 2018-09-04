package minestrapp.magic.capability;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

public class MagicProvider implements ICapabilityProvider, INBTSerializable<NBTTagCompound> {
	
	private IMagicCapability cMana = null;
	
	public MagicProvider(){
		cMana = new DefaultMagicCapability();
	}
	
	public MagicProvider(IMagicCapability p){
		this.cMana = p;
	}
	
	@CapabilityInject(IMagicCapability.class)
	public static final Capability<IMagicCapability> MagicCapability = null;

	@Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing){
        return capability == MagicCapability;
    }
    
    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing){
    	if (MagicCapability != null && capability == MagicCapability) return (T)cMana;
    	return null;
    }
    
    public static IMagicCapability get(EntityPlayer player) {
		return player != null && player.hasCapability(MagicCapability, null)? player.getCapability(MagicCapability, null): null;
    }
	
	@Override
	public NBTTagCompound serializeNBT() {
		return cMana.saveNBTData();
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		cMana.loadNBTData(nbt);
	}

}
