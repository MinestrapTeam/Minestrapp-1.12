package minestrapp.magic.capability;

import minestrapp.Minestrapp5;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MagicCapabilityManager {
	
	public static void preInit(){
    	CapabilityManager.INSTANCE.register(IMagicCapability.class, new MagicCapabilityStorage(), DefaultMagicCapability::new);
    }
	
	@SubscribeEvent
	public void onAddCapabilities(AttachCapabilitiesEvent<Entity> e){
		if (e.getObject() instanceof EntityPlayer){
			if(!e.getObject().hasCapability(MagicProvider.MagicCapability, null)){
				e.addCapability(new ResourceLocation(Minestrapp5.MODID, "magicCapability"), new MagicProvider(new DefaultMagicCapability()));
			}
		}
	}

}
