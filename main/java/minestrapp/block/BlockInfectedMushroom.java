package minestrapp.block;

import javax.annotation.Nullable;

import minestrapp.potion.MPotions;
import net.minecraft.block.Block;
import net.minecraft.block.BlockMycelium;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiScreenEvent.PotionShiftEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.util.Random;

public class BlockInfectedMushroom extends BlockGlowshroom
{
	Random rand = new Random();
	public static final AxisAlignedBB COLLISION_AABB = new AxisAlignedBB(-1D, 0D, -1D, 2D, 1D, 2D);
	
	public BlockInfectedMushroom(String name)
	{
		super(name);
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
	{
		if(entity instanceof EntityLivingBase)
		{	
			EntityLivingBase living = (EntityLivingBase)entity;
			if(!living.isPotionActive(MPotions.infection))
			{
				living.addPotionEffect(new PotionEffect(MPotions.infection, 900));
				living.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 900));
				
				int particle = rand.nextInt(50) + 10;
				
				for(int i = 0 ; i < particle ; i++)
				{
					this.spawnParticles(state, world, pos, rand);
				}
			}
		}
	}
	
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return super.canPlaceBlockAt(worldIn, pos) && this.canBlockStay(worldIn, pos, this.getDefaultState());
    }
	
	protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() instanceof BlockMycelium;
    }
	
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
		if (pos.getY() > 0 && pos.getY() < 256)
	        return worldIn.getBlockState(pos.down()).getBlock() instanceof BlockMycelium;
		return false;
    }
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        this.checkAndDropBlock(worldIn, pos, state);
    }
	
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
        return false;
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        return false;
    }
    
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
    }
    
    public void spawnParticles(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        double d0 = (double)pos.getX() + 0.5D + ((double)rand.nextFloat() - 0.5D) * 2D;
        double d1 = (double)((float)pos.getY() + ((double)rand.nextFloat()));
        double d2 = (double)pos.getZ() + 0.5D + ((double)rand.nextFloat() - 0.5D) * 2D;
        float r = (rand.nextInt(50) + 70) / 255F;
        float g = (rand.nextInt(100) + 120) / 255F;
        float b = (rand.nextInt(50) + 80) / 255F;
        worldIn.spawnParticle(EnumParticleTypes.REDSTONE, d0, d1, d2, (double)r, (double)g, (double)b);
    }
    
    public Block.EnumOffsetType getOffsetType()
    {
        return Block.EnumOffsetType.XZ;
    }
}
