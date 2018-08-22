package minestrapp.AI;

import minestrapp.mobs.entitys.EntityTheInfected;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityAIEatMushrooms extends EntityAIBase{

	private EntityTheInfected entity;
	
	public EntityAIEatMushrooms(EntityTheInfected entity) {
		this.entity = entity;
		this.setMutexBits(4);
	}
	
	@Override
	public boolean shouldContinueExecuting() {
		IBlockState entityStandingOn = this.entity.getEntityWorld().getBlockState(this.entity.getPosition());
        return entityStandingOn == Blocks.RED_MUSHROOM.getDefaultState() || entityStandingOn == Blocks.BROWN_MUSHROOM.getDefaultState();
    }
	
	@Override
	public boolean shouldExecute() {
		IBlockState entityStandingOn = this.entity.getEntityWorld().getBlockState(this.entity.getPosition());
		if(entityStandingOn == Blocks.RED_MUSHROOM.getDefaultState() || entityStandingOn == Blocks.BROWN_MUSHROOM.getDefaultState()) {
			return true;
		}
		return false;
	}
	
	@Override
	public void updateTask() {
		IBlockState block = this.entity.getEntityWorld().getBlockState(this.entity.getPosition());
		if(block == Blocks.RED_MUSHROOM.getDefaultState() || block == Blocks.BROWN_MUSHROOM.getDefaultState()) {
			World world = entity.getEntityWorld();
			BlockPos mushroomPos = this.entity.getPosition();
			world.destroyBlock(mushroomPos, false);
			this.entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 500));
		}
    }

}
