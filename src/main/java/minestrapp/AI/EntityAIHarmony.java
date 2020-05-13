package minestrapp.AI;

import java.util.Set;

import com.google.common.collect.Sets;

import minestrapp.potion.MPotions;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigateGround;

public class EntityAIHarmony extends EntityAIBase{
    private final EntityCreature entity;
    private final double speed;
    
    private double targetX;
    private double targetY;
    private double targetZ;

    private double pitch;
    private double yaw;

    private EntityPlayer playerToFollow;

    private boolean isRunning;

    public EntityAIHarmony(EntityCreature entityIn, double speedIn) {
        this.entity = entityIn;
        this.speed = speedIn;
        this.setMutexBits(3);

        if (!(entityIn.getNavigator() instanceof PathNavigateGround)) {
            throw new IllegalArgumentException("Unsupported mob type for TemptGoal");
        }
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
    	this.playerToFollow = this.entity.world.getClosestPlayerToEntity(this.entity, 10.0D);

        if (this.playerToFollow != null && this.entity.isPotionActive(MPotions.harmony)) {
             return true;
        }
        return false;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting() {
           
    	if(!this.entity.isPotionActive(MPotions.harmony)) {
    		return false;
    	}
    	
    	if (this.entity.getDistanceSq(this.playerToFollow) < 36.0D) {
                if (this.playerToFollow.getDistanceSq(this.targetX, this.targetY, this.targetZ) > 0.010000000000000002D) {
                    return false;
                }

                if (Math.abs((double)this.playerToFollow.rotationPitch - this.pitch) > 5.0D || Math.abs((double)this.playerToFollow.rotationYaw - this.yaw) > 5.0D) {
                    return false;
                }
            } else {
                this.targetX = this.playerToFollow.posX;
                this.targetY = this.playerToFollow.posY;
                this.targetZ = this.playerToFollow.posZ;
            }

            this.pitch = (double)this.playerToFollow.rotationPitch;
            this.yaw = (double)this.playerToFollow.rotationYaw;
            return this.shouldExecute();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.targetX = this.playerToFollow.posX;
        this.targetY = this.playerToFollow.posY;
        this.targetZ = this.playerToFollow.posZ;
        this.isRunning = true;
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask() {
        this.playerToFollow = null;
        this.entity.getNavigator().clearPath();
        this.isRunning = false;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void updateTask() {
        this.entity.getLookHelper().setLookPositionWithEntity(this.playerToFollow, (float)(this.entity.getHorizontalFaceSpeed() + 20), (float)this.entity.getVerticalFaceSpeed());

        if (this.entity.getDistanceSq(this.playerToFollow) < 6.25D) {
            this.entity.getNavigator().clearPath();
        } else {
            this.entity.getNavigator().tryMoveToEntityLiving(this.playerToFollow, this.speed);
        }
    }

    /**
     * @see #isRunning
     */
    public boolean isRunning() {
        return this.isRunning;
    }
}
