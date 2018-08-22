package minestrapp.AI;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIMoveToBlock;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityAIFindMushrooms extends EntityAIMoveToBlock{

	EntityLiving entity;
	
	public EntityAIFindMushrooms(EntityCreature creature, double speedIn, int length) {
		super(creature, speedIn, length);
		this.entity = creature;
	}
	
	@Override
	public boolean shouldContinueExecuting() {
		IBlockState entityStandingOn = this.entity.getEntityWorld().getBlockState(this.entity.getPosition());
		 return entityStandingOn == Blocks.RED_MUSHROOM.getDefaultState() || entityStandingOn == Blocks.BROWN_MUSHROOM.getDefaultState();
    }

	@Override
	protected boolean shouldMoveTo(World worldIn, BlockPos pos) {
		Block block = worldIn.getBlockState(pos).getBlock();
		if(block == Blocks.RED_MUSHROOM || block == Blocks.BROWN_MUSHROOM) {
			this.destinationBlock = pos;
			return true;
		}
		return false;
	}

}
