package minestrapp.potion;

import java.util.Iterator;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;

public class PotionRestoration extends MPotion{

	protected PotionRestoration(boolean isBadEffectIn, int liquidColorIn, String name, int iconIndex) {
		super(isBadEffectIn, liquidColorIn, name, iconIndex);
	}
	
	@Override
	public boolean isInstant() {
        return true;
    }
	
	@Override
	public boolean isReady(int duration, int amplifier) {
        return duration >= 1;
    }

	@Override
	 public void affectEntity(@Nullable Entity source, @Nullable Entity indirectSource, EntityLivingBase living, int amplifier, double health) {
		if(!(living instanceof EntityDragon) && !(living instanceof EntityDragon)) {
			 
			if(living instanceof EntityMob) {
				 living.attackEntityFrom(DamageSource.MAGIC, living.getMaxHealth());
			 } else {
				Iterator<PotionEffect> it = living.getActivePotionEffects().iterator();
				
				while(it.hasNext()) {
					PotionEffect effect = it.next();
					if(effect.getPotion().isBadEffect()) {
						living.removePotionEffect(effect.getPotion());
					}
				}
				
				living.heal(MathHelper.clamp(living.getHealth() + living.getMaxHealth(), living.getHealth(), living.getMaxHealth()));
				
				if(living instanceof EntityPlayer) {
					EntityPlayer player = (EntityPlayer)living;
					player.getFoodStats().addStats(20, 5F);
				}
			 }
		}
	}

}
