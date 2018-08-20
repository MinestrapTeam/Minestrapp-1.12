package minestrapp.potion;

import minestrapp.MBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PotionHydrophobia extends MPotion{

	protected PotionHydrophobia(boolean isBadEffectIn, int liquidColorIn, String name, int iconIndex) {
		super(isBadEffectIn, liquidColorIn, name, iconIndex);
	}
	
	@Override
	public void performEffect(EntityLivingBase living, int amplifier) {
		if(living.isWet()) {
			living.attackEntityFrom(DamageSource.DROWN, 1F);
		}
    }

}
