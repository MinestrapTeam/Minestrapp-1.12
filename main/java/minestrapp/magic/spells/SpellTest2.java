package minestrapp.magic.spells;

import minestrapp.magic.EnumMagicType;
import minestrapp.magic.capability.MagicProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SpellTest2 extends SpellBase{

	public SpellTest2(String name) {
		super(name, 0, 4, 0);
	}

	@Override
	public void effectOnBlock(World world, BlockPos pos, EntityPlayer player, int potency) {
		world.setBlockState(pos, Blocks.FLOWING_WATER.getDefaultState());
	}
}
