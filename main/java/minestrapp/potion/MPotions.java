package minestrapp.potion;

import minestrapp.MBlocks;
import minestrapp.MItems;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class MPotions {
	
	public static final Potion infection = new PotionInfection(true, 13099642, "Infected", 0, 0);
	private static PotionType infectionType = new PotionType("infection", new PotionEffect(infection, 1800));
	private static PotionType infectionLongType = new PotionType("infection", new PotionEffect(infection, 4800));
	
	public static final Potion hydrophobia = new PotionHydrophobia(true, 7446171, "Hydrophobia", 1, 0);
	private static PotionType hydrophobiaType = new PotionType("hydrophobia", new PotionEffect(hydrophobia, 1800));
	private static PotionType hydrophobiaLongType = new PotionType("hydrophobia", new PotionEffect(hydrophobia, 4800));
	private static PotionType hydrophobiaStrongType = new PotionType("hydrophobia", new PotionEffect(hydrophobia, 1200, 1));
	
	public static final Potion climbing = new PotionClimbing(false, 5319478, "Climbing", 2, 0);
	private static PotionType climbingType = new PotionType("climbing", new PotionEffect(climbing, 3600));
	private static PotionType climbingLongType = new PotionType("climbing", new PotionEffect(climbing, 9600));
	
	public static final Potion restoration = new PotionRestoration(false, 16571903, "Restoration", 3, 0);
	private static PotionType restorationType = new PotionType("restoration", new PotionEffect(restoration));
	
	public static final Potion aggression = new PotionAgression(true, 9830400, "Aggression", 4, 0);
	private static PotionType  aggressionType = new PotionType("agression", new PotionEffect(aggression, 540));
	private static PotionType  aggressionLongType = new PotionType("agression", new PotionEffect(aggression, 1800));
	
	public static final Potion harmony = new PotionHarmony(false, 14856856, "Harmony", 5, 0);
	private static PotionType  harmonyType = new PotionType("harmony", new PotionEffect(harmony, 1800));
	private static PotionType  harmonyLongType = new PotionType("harmony", new PotionEffect(harmony, 4800));
	
	public static void addBrewingRecipe() {
		PotionHelper.addMix(PotionTypes.AWKWARD, new ItemStack(MBlocks.infected_mushroom).getItem(), infectionType);
		PotionHelper.addMix(infectionType, Items.REDSTONE, infectionLongType);
		
		PotionHelper.addMix(PotionTypes.WATER_BREATHING, Items.FERMENTED_SPIDER_EYE, hydrophobiaType);
		PotionHelper.addMix(PotionTypes.LONG_WATER_BREATHING, Items.FERMENTED_SPIDER_EYE, hydrophobiaLongType);
		PotionHelper.addMix(hydrophobiaType, Items.REDSTONE, hydrophobiaLongType);
		PotionHelper.addMix(hydrophobiaType, Items.GLOWSTONE_DUST, hydrophobiaStrongType);
		
		PotionHelper.addMix(PotionTypes.AWKWARD, Ingredient.fromStacks(new ItemStack(MItems.mob_loot, 1, 8)), climbingType);
		PotionHelper.addMix(climbingType, Items.REDSTONE, climbingLongType);
		
		PotionHelper.addMix(PotionTypes.HEALING, MItems.effervexcense, restorationType);
		PotionHelper.addMix(PotionTypes.STRONG_HEALING, MItems.effervexcense, restorationType);
		
		PotionHelper.addMix(PotionTypes.AWKWARD, Ingredient.fromStacks(new ItemStack(MItems.mob_loot, 1, 7)), harmonyType);
		PotionHelper.addMix(harmonyType, Items.REDSTONE, harmonyLongType);
		PotionHelper.addMix(harmonyType, Items.FERMENTED_SPIDER_EYE, aggressionType);
		PotionHelper.addMix(harmonyLongType, Items.FERMENTED_SPIDER_EYE, aggressionLongType);
		PotionHelper.addMix(aggressionType, Items.REDSTONE, aggressionLongType);
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
        event.getRegistry().register(infectionLongType.setRegistryName("infection_long"));
        event.getRegistry().register(hydrophobiaType.setRegistryName("hydrophobia"));
        event.getRegistry().register(hydrophobiaLongType.setRegistryName("hydrophobia_long"));
        event.getRegistry().register(hydrophobiaStrongType.setRegistryName("hydrophobia_strong"));
        event.getRegistry().register(climbingType.setRegistryName("climbing"));
        event.getRegistry().register(climbingLongType.setRegistryName("climbing_long"));
        event.getRegistry().register(restorationType.setRegistryName("restoration"));
        event.getRegistry().register(aggressionType.setRegistryName("aggression"));
        event.getRegistry().register(aggressionLongType.setRegistryName("aggression_long"));
        event.getRegistry().register(harmonyType.setRegistryName("harmony"));
        event.getRegistry().register(harmonyLongType.setRegistryName("harmony_long"));
    }
}
