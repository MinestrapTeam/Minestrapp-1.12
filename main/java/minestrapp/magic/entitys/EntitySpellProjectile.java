package minestrapp.magic.entitys;

import minestrapp.magic.spells.SpellBase;
import minestrapp.magic.spells.SpellRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntitySpellProjectile extends Entity{
	private int life = 50;
	public int potency;
	
	public double accelX;
	public double accelY;
	public double accelZ;
	
	public Entity entity;
	
	public SpellBase spell;
	
	public EntitySpellProjectile(World worldIn) {
		super(worldIn);
	}
	
	public EntitySpellProjectile(World worldIn, Entity entity, SpellBase spell, double x, double y, double z, int potency) {
		super(worldIn);
		this.entity = entity;
		this.spell = spell;
		this.potency = potency;
		this.setPosition(x, y, z);
		this.setSize(1.0F, 1.0F);
		this.setThrowableHeading(entity.getLookVec().x, entity.getLookVec().y, entity.getLookVec().z, 0.4F);
	}

	@Override
	protected void entityInit() {
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		this.spell = SpellRegistry.getSpellByName(compound.getString("spell"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		compound.setString("spell", this.spell.name);
	}
	
	public void onUpdate()
    {
        super.onUpdate();
        if(world.isRemote) {
        	world.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY, this.posZ, this.accelX, this.accelY, this.accelZ);
        }
        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;
        this.setPosition(this.posX, this.posY, this.posZ);
        RayTraceResult ray = ProjectileHelper.forwardsRaycast(this, true, true, entity);
        
        if(ray != null){
        	this.setDead();
        	if(!world.isRemote){
        		if(ray.typeOfHit == RayTraceResult.Type.ENTITY){
        			spell.effectOnEntity(world, (EntityPlayer)entity, ray.entityHit, this.potency);
        		}
        		if(ray.typeOfHit == RayTraceResult.Type.BLOCK){
        			spell.effectOnBlock(world, ray.getBlockPos(), (EntityPlayer)entity, this.potency);
        		}
        	}
        }
        
        this.life--;
        if(this.life <= 0){
        	this.setDead();
        }
    }
	
	public void setThrowableHeading(double x, double y, double z, float velocity) {
        float f = MathHelper.sqrt(x * x + y * y + z * z);
        x = x / (double)f;
        y = y / (double)f;
        z = z / (double)f;
        x = x * (double)velocity;
        y = y * (double)velocity;
        z = z * (double)velocity;
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
        float f1 = MathHelper.sqrt(x * x + z * z);
        this.rotationYaw = (float)(MathHelper.atan2(x, z) * (180D / Math.PI));
        this.rotationPitch = (float)(MathHelper.atan2(y, (double)f1) * (180D / Math.PI));
        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = this.rotationPitch;
    }

}
