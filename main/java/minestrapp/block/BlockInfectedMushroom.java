package minestrapp.block;

import minestrapp.potion.MPotions;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import scala.util.Random;

public class BlockInfectedMushroom extends BlockGlowshroom{
	Random rand = new Random();
	public BlockInfectedMushroom(String name) {
		super(name);
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		if(entity instanceof EntityLivingBase) {
			EntityLivingBase living = (EntityLivingBase)entity;
			living.addPotionEffect(new PotionEffect(MPotions.infection, 3600));
		}
	}

}
