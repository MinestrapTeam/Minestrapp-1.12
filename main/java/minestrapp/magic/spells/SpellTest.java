package minestrapp.magic.spells;

import minestrapp.magic.EnumMagicType;
import minestrapp.magic.capability.MagicProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SpellTest extends SpellBase{

	public SpellTest(String name) {
		super(name, 0, 0, 3);
	}

	@Override
	public void effectOnBlock(World world, BlockPos pos, EntityPlayer player, int potency) {
		world.destroyBlock(pos, true);
	}
}
