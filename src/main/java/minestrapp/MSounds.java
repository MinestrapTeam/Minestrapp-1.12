package minestrapp;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Minestrapp5.MODID)
public final class MSounds {
	public static final SoundEvent BURFALAUNT_HURT = createEvent("mob.burfalaunt.hurt");
	public static final SoundEvent BURFALAUNT_LIVING = createEvent("mob.burfalaunt.living");
	public static final SoundEvent BURFALAUNT_DEATH = createEvent("mob.burfalaunt.death");
	private static SoundEvent createEvent(String sound) {
		final ResourceLocation name = new ResourceLocation(Minestrapp5.MODID, sound);
		return new SoundEvent(name).setRegistryName(name);
	}
	@SubscribeEvent
	public static void registerSounds(RegistryEvent.Register<SoundEvent> evt) {
		evt.getRegistry().register(BURFALAUNT_HURT);
		evt.getRegistry().register(BURFALAUNT_LIVING);
		evt.getRegistry().register(BURFALAUNT_DEATH);
	}
	private MSounds() {
	}

}