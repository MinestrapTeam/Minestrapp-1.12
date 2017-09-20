package minestrapp.block;

import java.util.List;
import java.util.Random;

import minestrapp.block.util.BlockBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockIrradiantSunstone extends BlockBase
{
	public BlockIrradiantSunstone()
	{
		super("block_irradiant_sunstone", Material.ROCK, MapColor.LIME, SoundType.GLASS, 2F, "pickaxe", 2);
		this.setTickRandomly(true);
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
		List<EntityLiving> entities = worldIn.getEntitiesWithinAABB(EntityLiving.class, new AxisAlignedBB(x-4, y-4, z-4, x+4, y+4, z+4));
		
		for(EntityLiving living : entities)
		{
			living.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 60, 0, true, false));
		}
		
		List<EntityPlayer> players = worldIn.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(x-4, y-4, z-4, x+4, y+4, z+4));
		
		for(EntityPlayer player : players)
		{
			player.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 60, 0, true, false));
		}
	
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
    }
}
