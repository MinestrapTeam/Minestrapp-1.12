package minestrapp.magic.spells;

import minestrapp.magic.EnumMagicType;
import minestrapp.magic.capability.MagicProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SpellBase {

	public String name;
	
	public int costWater;
	public int costFire;
	
	
	public SpellBase(String name, int potency, int costWater, int costFire) {
		this.name = name;
		
		this.costWater = costWater;
		this.costFire = costFire;
	}
	
	public void onCast(World world, EntityPlayer player) {
		MagicProvider.get(player).changeManaForType(player, EnumMagicType.WATER, -this.costWater);
		MagicProvider.get(player).changeManaForType(player, EnumMagicType.FIRE, -this.costFire);
	}
	
	public boolean canCast(EntityPlayer player) {
		if(MagicProvider.get(player).getManaForType(EnumMagicType.FIRE) >= this.costFire && MagicProvider.get(player).getManaForType(EnumMagicType.WATER) >= this.costWater) {
			return true;
		}
		return false;
	}
	
	public void effectOnBlock(World world, BlockPos pos, EntityPlayer player, int potency) {
		
	}
	
	public void effectOnEntity(World world, EntityPlayer player, Entity entity, int potency) {
		
	}

}
