package minestrapp.block;

import java.util.Random;

import minestrapp.MTabs;
import minestrapp.block.util.BlockBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockShimmeringOre extends BlockBase
{
	public BlockShimmeringOre()
	{
		super("ore_shimmering", Material.ROCK, MapColor.NETHERRACK, SoundType.STONE, 10F, "pickaxe", 4);
		this.setResistance(20F).setCreativeTab(MTabs.ore).setLightLevel(0.75F);
	}
	
	/*public int tickRate(World worldIn)
    {
        return 5;
    }
	
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
	}
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
		this.spawnParticles(worldIn, pos, rand);
		
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
    }
	
	private void spawnParticles(World worldIn, BlockPos pos, Random rand)
	{
		double angle = Math.toRadians(worldIn.getWorldTime());
        double x = (double)pos.getX() + 0.5D + (Math.sin(angle) * 0.5);
        double z = (double)pos.getZ() + 0.5D + (Math.cos(angle) * 0.5);
        double y = (double)pos.getY() + 1.2D;

        worldIn.spawnParticle(EnumParticleTypes.END_ROD, true, x, y, z, 0, 0.25D, 0);
	}*/
	
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
		double angle1 = Math.toRadians(worldIn.getWorldTime() * 5);
        double x1 = (double)pos.getX() + 0.5D + (Math.sin(angle1) * 0.5);
        double z1 = (double)pos.getZ() + 0.5D + (Math.cos(angle1) * 0.5);
        double angle2 = Math.toRadians((worldIn.getWorldTime() + 180) * 5);
        double x2 = (double)pos.getX() + 0.5D + (Math.sin(angle2) * 0.5);
        double z2 = (double)pos.getZ() + 0.5D + (Math.cos(angle2) * 0.5);
        double y = (double)pos.getY() + 1.2D;

        worldIn.spawnParticle(EnumParticleTypes.END_ROD, true, x1, y, z1, 0, 1.25D, 0);
        worldIn.spawnParticle(EnumParticleTypes.END_ROD, true, x2, y, z2, 0, 1.25D, 0);
    }
}
