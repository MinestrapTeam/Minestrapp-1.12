package minestrapp.potion;

import minestrapp.AI.EntityAIHarmony;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class PotionHarmony extends MPotion{
	
	protected PotionHarmony(boolean isBadEffectIn, int liquidColorIn, String name, int iconIndexX, int iconIndexY) {
		super(isBadEffectIn, liquidColorIn, name, iconIndexX, iconIndexY);
	}

	@Override
	public void performEffect(EntityLivingBase living, int amplifier) {
			if(living instanceof EntityCreature) {
				
				EntityCreature creature = (EntityCreature)living;
				creature.setAttackTarget(null);
			}
			
			if(living instanceof EntityTameable) {
				
				EntityTameable tame = (EntityTameable) living;
				if(!tame.isTamed()) {
					tame.setTamed(true);
					tame.setTamedBy(tame.getEntityWorld().getClosestPlayerToEntity(living, 15D));
				}
				
			}  else if (living instanceof EntityAnimal){
				
				EntityAnimal animal = (EntityAnimal)living;
				if(animal.tasks.taskEntries.parallelStream().noneMatch(task -> task.action instanceof EntityAIHarmony)) {
					animal.tasks.addTask(0, new EntityAIHarmony(animal, 1.25D));
				}
				
			}
	}
	
	@SubscribeEvent
	public static void livingHurtEvent(LivingHurtEvent e) {
		Entity source = e.getSource().getTrueSource();
		EntityLivingBase entityAttacked = e.getEntityLiving();
			
		if(source instanceof EntityPlayer) {
			EntityPlayer attacker = (EntityPlayer)source;
				
			if(entityAttacked instanceof EntityPlayer) {
				if(entityAttacked.isPotionActive(MPotions.harmony)) {
					e.setCanceled(true);
				}
				if(entityAttacked.isPotionActive(MPotions.harmony)) {
						e.setCanceled(true);
				}
			} else {
				if(entityAttacked.isPotionActive(MPotions.harmony)) {
					entityAttacked.removePotionEffect(MPotions.harmony);
				}
			}
			
		}
			
		if(source instanceof EntityLiving) {
			EntityLiving living = (EntityLiving) source;
			if(living.isPotionActive(MPotions.harmony)) {
				e.setCanceled(true);
			}
		}
	}
	
}
