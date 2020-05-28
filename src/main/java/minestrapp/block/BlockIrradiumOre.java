package minestrapp.block;

import java.util.List;
import java.util.Random;

import minestrapp.MTabs;
import minestrapp.block.util.BlockStoneBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockIrradiumOre extends BlockStoneBase
{
	public BlockIrradiumOre()
	{
		super("ore_irradium", Material.ROCK, SoundType.STONE, 3F, "pickaxe", 2);
		this.setTickRandomly(true);
		this.setCreativeTab(MTabs.ore);
	}
	
	public int tickRate(World worldIn)
    {
        return 30;
    }
	
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
	}
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		List<EntityLiving> entities = worldIn.getEntitiesWithinAABB(EntityLiving.class, new AxisAlignedBB(x-2, y-2, z-2, x+2, y+2, z+2));
		
		for(EntityLiving living : entities)
		{
			if(living instanceof EntityCreeper)
			{
				living.addPotionEffect(new PotionEffect(MobEffects.POISON, 240, 2));
				living.addPotionEffect(new PotionEffect(MobEffects.SPEED, 240, 2));
			}
			else if(living instanceof AbstractSkeleton)
			{
				living.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 320, 1));
				living.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 320));
			}
			else if(living instanceof EntityZombie)
			{
				living.addPotionEffect(new PotionEffect(MobEffects.SPEED, 320, 3));
				living.addPotionEffect(new PotionEffect(MobEffects.POISON, 320, 1));
			}
			else if(living instanceof EntitySlime)
			{
				living.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 320, 2));
				living.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 320, 2));
			}
			else if(!(living instanceof EntityBlaze || living instanceof EntityGuardian || living instanceof EntityGolem))
			{
				living.addPotionEffect(new PotionEffect(MobEffects.WITHER, 60, 1));
			}
		}
		
		List<EntityPlayer> players = worldIn.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(x-2, y-2, z-2, x+2, y+2, z+2));
		
		for(EntityPlayer player : players)
		{
			player.addPotionEffect(new PotionEffect(MobEffects.WITHER, 60, 1));
		}
		
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
    }
	
	public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn)
    {
        if (!worldIn.isRemote)
        {
        	this.explode(worldIn, pos);
        }
    }
	public boolean canDropFromExplosion(Explosion explosionIn)
    {
        return false;
    }
	
	public void explode(World worldIn, BlockPos pos)
	{
		worldIn.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 8.0F, true);
		EntityAreaEffectCloud entityareaeffectcloud = new EntityAreaEffectCloud(worldIn, pos.getX(), pos.getY(), pos.getZ());
        entityareaeffectcloud.setRadius(3F);
        entityareaeffectcloud.setRadiusOnUse(-0.25F);
        entityareaeffectcloud.setWaitTime(10);
        entityareaeffectcloud.setDuration(entityareaeffectcloud.getDuration() * 3);
        entityareaeffectcloud.setRadiusPerTick(-entityareaeffectcloud.getRadius() / (float)entityareaeffectcloud.getDuration());
        entityareaeffectcloud.setPotion(new PotionType(new PotionEffect(MobEffects.POISON, 240, 1)));
		worldIn.spawnEntity(entityareaeffectcloud);
		worldIn.setBlockToAir(pos);
	}
}
