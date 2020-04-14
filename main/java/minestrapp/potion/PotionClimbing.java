package minestrapp.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;

public class PotionClimbing extends MPotion
{

	protected PotionClimbing(boolean isBadEffectIn, int liquidColorIn, String name, int iconIndexX, int iconIndexY)
	{
		super(isBadEffectIn, liquidColorIn, name, iconIndexX, iconIndexY);
	}
	
	@Override
	public void performEffect(EntityLivingBase living, int amplifier)
	{
		if(living.collidedHorizontally && !living.isOnLadder())
		{
	        final float factor = 0.15F;

	        if(living.isSneaking())
	        {
	        	living.motionY = 0;
	        }
	        else
	        {
	        	living.motionX = MathHelper.clamp(living.motionX, -factor, factor);
	        	living.motionY = 0.2;
	        	living.motionZ = MathHelper.clamp(living.motionZ, -factor, factor);
	        }
	        
	        living.fallDistance = 0.0F;
		}
	}
}
