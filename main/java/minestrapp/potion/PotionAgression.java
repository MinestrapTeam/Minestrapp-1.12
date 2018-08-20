package minestrapp.potion;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIFindEntityNearest;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.entity.player.EntityPlayer;

public class PotionAgression extends MPotion {

	protected PotionAgression(boolean isBadEffectIn, int liquidColorIn, String name, int iconIndex) {
		super(isBadEffectIn, liquidColorIn, name, iconIndex);
	}
	
	@Override
	public void performEffect(EntityLivingBase living, int amplifier) {
		if(living instanceof EntityCreature) {
			EntityCreature e = (EntityCreature) living;
			e.targetTasks.addTask(1, new EntityAINearestAttackableTarget(e, EntityLiving.class, true));
		}
	}

}
