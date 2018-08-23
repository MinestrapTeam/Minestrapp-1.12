package minestrapp.potion;

import minestrapp.MBlocks;
import minestrapp.MItems;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class MPotions {
	
	public static final Potion infection = new PotionInfection(true, 5698626, "Infected", 0, 0);
	private static PotionType infectionType = new PotionType("infection", new PotionEffect(infection, 3600));
	
	public static final Potion hydrophobia = new PotionHydrophobia(true, 62207, "Hydrophobia", 1, 0);
	private static PotionType hydrophobiaType = new PotionType("hydrophobia", new PotionEffect(hydrophobia, 1800));
	private static PotionType hydrophobiaStrongType = new PotionType("hydrophobia", new PotionEffect(hydrophobia, 1800, 1));
	
	public static final Potion climbing = new PotionClimbing(false, 4986914, "Climbing", 2, 0);
	private static PotionType climbingType = new PotionType("climbing", new PotionEffect(climbing, 1800));
	
	public static final Potion restoration = new PotionRestoration(false, 16711680, "Restoration", 3, 0);
	private static PotionType restorationType = new PotionType("restoration", new PotionEffect(restoration));
	
	public static final Potion aggression = new PotionAgression(true, 8126464, "Aggression", 4, 0);
	private static PotionType  aggressionType = new PotionType("agression", new PotionEffect(aggression, 100));
	
	public static final Potion harmony = new PotionHarmony(false, 16741116, "Harmony", 5, 0);
	private static PotionType  harmonyType = new PotionType("harmony", new PotionEffect(harmony, 1800));
	
	public static void addBrewingRecipe() {
		PotionHelper.addMix(PotionTypes.WATER, new ItemStack(MBlocks.infected_mushroom).getItem(), infectionType);
		PotionHelper.addMix(PotionTypes.WATER, new ItemStack(MBlocks.blue_glowshroom).getItem(), hydrophobiaType);
		PotionHelper.addMix(PotionTypes.WATER, new ItemStack(MItems.glow_paste).getItem(), climbingType);
		PotionHelper.addMix(PotionTypes.WATER, new ItemStack(MItems.mob_loot, 1, 3).getItem(), restorationType);
		PotionHelper.addMix(PotionTypes.WATER, new ItemStack(MItems.bread_voidberry_salad).getItem(), aggressionType);
		
		PotionHelper.addMix(hydrophobiaType, Items.GLOWSTONE_DUST, hydrophobiaStrongType);
	}
	
    @SubscribeEvent
    public static void registerPotions(RegistryEvent.Register<Potion> event) {
    	event.getRegistry().register(infection.setRegistryName("infection"));
    	event.getRegistry().register(hydrophobia.setRegistryName("hydrophobia"));
    	event.getRegistry().register(climbing.setRegistryName("climbing"));
    	event.getRegistry().register(restoration.setRegistryName("restoration"));
    	event.getRegistry().register(aggression.setRegistryName("aggression"));
    	event.getRegistry().register(harmony.setRegistryName("harmony"));
    }

    @SubscribeEvent
    public static void registerPotionTypes(RegistryEvent.Register<PotionType> event) {
        event.getRegistry().register(infectionType.setRegistryName("infection"));
        event.getRegistry().register(hydrophobiaType.setRegistryName("hydrophobia"));
        event.getRegistry().register(hydrophobiaStrongType.setRegistryName("hydrophobia_strong"));
        event.getRegistry().register(climbingType.setRegistryName("climbing"));
        event.getRegistry().register(restorationType.setRegistryName("restoration"));
        event.getRegistry().register(aggressionType.setRegistryName("aggression"));
        event.getRegistry().register(harmonyType.setRegistryName("harmony"));
    }
}
