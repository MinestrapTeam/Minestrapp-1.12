package minestrapp.potion;

import minestrapp.MBlocks;
import minestrapp.MItems;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraftforge.client.event.GuiScreenEvent.PotionShiftEvent;
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
	
	private static PotionType hasteType = new PotionType("haste", new PotionEffect(MobEffects.HASTE, 3600));
	private static PotionType hasteLongType = new PotionType("haste", new PotionEffect(MobEffects.HASTE, 9600));
	private static PotionType hasteStrongType = new PotionType("haste", new PotionEffect(MobEffects.HASTE, 1800, 1));
	
	private static PotionType fatigueType = new PotionType("fatigue", new PotionEffect(MobEffects.MINING_FATIGUE, 1800));
	private static PotionType fatigueLongType = new PotionType("fatigue", new PotionEffect(MobEffects.MINING_FATIGUE, 4800));
	private static PotionType fatigueStrongType = new PotionType("fatigue", new PotionEffect(MobEffects.MINING_FATIGUE, 400, 1));
	
	public static final Potion wellFed = new PotionWellFed(false, 13412982, "Well-Fed", 6, 0).registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "2ECFE137-ACE8-2A3F-80E1-7D839C26CE2E", 0.19D, 2).registerPotionAttributeModifier(SharedMonsterAttributes.ATTACK_SPEED, "EDE4015F-164D-6371-D3C8-E26A5C5507EC", 0.18D, 2).registerPotionAttributeModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, "860C9E37-5326-B239-5004-9B1AE5B3B426", 0.12D, 0);
	
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
		
		PotionHelper.addMix(PotionTypes.AWKWARD, Ingredient.fromStacks(new ItemStack(MItems.mob_loot, 1, 12)), hasteType);
		PotionHelper.addMix(hasteType, Items.REDSTONE, hasteLongType);
		PotionHelper.addMix(hasteType, Items.GLOWSTONE_DUST, hasteStrongType);
		PotionHelper.addMix(hasteType, Items.FERMENTED_SPIDER_EYE, fatigueType);
		PotionHelper.addMix(hasteLongType, Items.FERMENTED_SPIDER_EYE, fatigueLongType);
		PotionHelper.addMix(hasteStrongType, Items.FERMENTED_SPIDER_EYE, fatigueStrongType);
		PotionHelper.addMix(fatigueType, Items.REDSTONE, fatigueLongType);
		PotionHelper.addMix(fatigueType, Items.GLOWSTONE_DUST, fatigueStrongType);
	}
	
    @SubscribeEvent
    public static void registerPotions(RegistryEvent.Register<Potion> event) {
    	event.getRegistry().register(infection.setRegistryName("infection"));
    	event.getRegistry().register(hydrophobia.setRegistryName("hydrophobia"));
    	event.getRegistry().register(climbing.setRegistryName("climbing"));
    	event.getRegistry().register(restoration.setRegistryName("restoration"));
    	event.getRegistry().register(aggression.setRegistryName("aggression"));
    	event.getRegistry().register(harmony.setRegistryName("harmony"));
    	event.getRegistry().register(wellFed.setRegistryName("well_fed"));
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
        event.getRegistry().register(hasteType.setRegistryName("haste"));
        event.getRegistry().register(hasteLongType.setRegistryName("haste_long"));
        event.getRegistry().register(hasteStrongType.setRegistryName("haste_strong"));
        event.getRegistry().register(fatigueType.setRegistryName("fatigue"));
        event.getRegistry().register(fatigueLongType.setRegistryName("fatigue_long"));
        event.getRegistry().register(fatigueStrongType.setRegistryName("fatigue_strong"));
    }
}
