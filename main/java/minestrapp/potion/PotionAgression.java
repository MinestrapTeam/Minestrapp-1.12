package minestrapp.potion;

import java.util.List;

import minestrapp.AI.EntityAIAggressionTarget;
import minestrapp.AI.EntityAIHarmony;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIFindEntityNearest;
import net.minecraft.entity.ai.EntityAIFollow;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;

public class PotionAgression extends MPotion {

	protected PotionAgression(boolean isBadEffectIn, int liquidColorIn, String name, int iconIndexX, int iconIndexY) {
		super(isBadEffectIn, liquidColorIn, name, iconIndexX, iconIndexY);
	}
	
	@Override
	public void performEffect(EntityLivingBase living, int amplifier) {
		if(living instanceof EntityCreature) {
			EntityCreature e = (EntityCreature) living;
			double x = e.getPosition().getX();
			double y = e.getPosition().getY();
			double z = e.getPosition().getZ();
			if(e.targetTasks.taskEntries.parallelStream().noneMatch(task -> task.action instanceof EntityAIAggressionTarget)) {
				e.targetTasks.addTask(0, new EntityAIAggressionTarget(e, EntityLiving.class, true));
			}
			
			List<EntityAnimal> entities = living.getEntityWorld().getEntitiesWithinAABB(EntityAnimal.class, new AxisAlignedBB(x-10, y-10, z-10, x+10, y+10, z+10));
			for(EntityAnimal passives : entities) {
				passives.setRevengeTarget(e);
			}
		}
	}

}
