package minestrapp.entity.mob;

import minestrapp.MSounds;
import minestrapp.Minestrapp;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityBurfalaunt extends EntityCow {
	public static final ResourceLocation LOOT_TABLE = new ResourceLocation(Minestrapp.MODID, "mob/burfalaunt/burfalauntloottable");

	public EntityBurfalaunt(World world) {
		super(world);
		setSize(0.9F, 1.3F);
	}

	public EntityBurfalaunt(World world, double x, double y, double z) {
		this(world);
		this.setPosition(x, y, z);
	}
	@Override
	public boolean processInteract(EntityPlayer entityplayer, EnumHand hand) {
		ItemStack itemstack = entityplayer.inventory.getCurrentItem();
		if (!itemstack.isEmpty() && itemstack.getItem() == Items.BUCKET) {
			return false;
		} else {
			return super.processInteract(entityplayer, hand);
		}
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		return MSounds.BURFALAUNT_LIVING;
	}
	@Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
		return MSounds.BURFALAUNT_HURT;
    }
	@Override
    protected SoundEvent getDeathSound()
    {
		return MSounds.BURFALAUNT_DEATH;
    }
	@Override
	public ResourceLocation getLootTable() {
		return LOOT_TABLE;
	}
	@Override
	public EntityCow createChild(EntityAgeable entityanimal) {
		return new EntityBurfalaunt(world);
	}

}