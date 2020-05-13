package minestrapp.worldgen;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

import minestrapp.entity.mob.EntityBurfalaunt;


public class MGenBurfalaunt extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {

		for (int i = 0; i < 10; i++) {
			int dx = (pos.getX() + rand.nextInt(8)) - rand.nextInt(8);
			int dz = (pos.getZ() + rand.nextInt(8)) - rand.nextInt(8);
			int dy = world.getTopSolidOrLiquidBlock(new BlockPos(dx, 0, dz)).getY();

			EntityBurfalaunt burfalaunt = new EntityBurfalaunt(world);
			burfalaunt.setLocationAndAngles(dx, dy, dz, rand.nextFloat() * 360.0F, 0.0F);

			world.spawnEntity(burfalaunt);

		}

		return true;

	}

}