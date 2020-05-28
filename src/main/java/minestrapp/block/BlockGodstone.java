package minestrapp.block;

import java.util.List;
import java.util.Random;

import minestrapp.MTabs;
import minestrapp.block.util.BlockBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGodstone extends BlockBase
{
	public BlockGodstone()
	{
		super("godstone", Material.ROCK, MapColor.SAND, SoundType.GLASS, 2F, "pickaxe", 2);
		this.setLightLevel(1F);
		this.setCreativeTab(MTabs.utility);
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
		List<EntityLiving> entities = worldIn.getEntitiesWithinAABB(EntityLiving.class, new AxisAlignedBB(x-8, y-8, z-8, x+8, y+8, z+8));
		
		for(EntityLiving living : entities)
		{
			if(living.isEntityUndead())
				living.setFire(30);
		}
	
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
    }
	
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }
	
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	
	public boolean isFullCube(IBlockState state)
    {
        return false;
    }
}
