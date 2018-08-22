package minestrapp.mobs.entitys;

import minestrapp.AI.EntityAIEatMushrooms;
import minestrapp.AI.EntityAIFindMushrooms;
import minestrapp.potion.MPotions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityTheInfected extends EntityZombie{
	
	public EntityTheInfected(World worldIn) {
		super(worldIn);
	}
	
	@Override
	protected void initEntityAI() {
		this.tasks.addTask(0, new EntityAIFindMushrooms(this, 1.0D, 15));
		this.tasks.addTask(1, new EntityAIEatMushrooms(this));
		this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(3, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        
        this.targetTasks.addTask(0, new EntityAIFindEntityNearestPlayer(this));
    }
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount){
		super.attackEntityFrom(source, amount);
		return true;
    }
	
	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		boolean flag = super.attackEntityAsMob(entityIn);
        if (flag) {
        	if(entityIn instanceof EntityLivingBase) {
        		EntityLivingBase living = (EntityLivingBase)entityIn;
        		living.addPotionEffect(new PotionEffect(MPotions.infection, 600));
        	}
        }

        return flag;
	}
	
}
