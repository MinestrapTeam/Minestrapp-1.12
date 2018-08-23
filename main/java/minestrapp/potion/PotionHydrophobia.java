package minestrapp.potion;

import minestrapp.MBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PotionHydrophobia extends MPotion{

	protected PotionHydrophobia(boolean isBadEffectIn, int liquidColorIn, String name, int iconIndexX, int iconIndexY) {
		super(isBadEffectIn, liquidColorIn, name, iconIndexX, iconIndexY);
	}
	
	@Override
	public void performEffect(EntityLivingBase living, int amplifier) {
		if(living.isWet()) {
			float amount = 1F * (2F * amplifier);
			living.attackEntityFrom(DamageSource.DROWN, amount);
		}
    }

}
