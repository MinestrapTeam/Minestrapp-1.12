package minestrapp.block;

import java.util.Map;
import java.util.Random;

import com.google.common.collect.Maps;

import minestrapp.block.util.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBlazium extends BlockBase
{    
	public BlockBlazium(String name, Material material, MapColor mapColor, SoundType soundType, float hardness, String tool, int harvestLevel)
	{
		super(name, material, mapColor, soundType, hardness, tool, harvestLevel);
		this.setTickRandomly(true);
	}
	
	public int tickRate(World worldIn)
    {
        return 30;
    }
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (worldIn.getGameRules().getBoolean("doFireTick"))
        {
        	worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn) + rand.nextInt(10));

            boolean flag1 = worldIn.isBlockinHighHumidity(pos);
            int j = 0;

            if (flag1)
            {
                j = -50;
            }

            this.tryCatchFire(worldIn, pos.east(), 300 + j, rand, EnumFacing.WEST);
            this.tryCatchFire(worldIn, pos.west(), 300 + j, rand, EnumFacing.EAST);
            this.tryCatchFire(worldIn, pos.down(), 250 + j, rand, EnumFacing.UP);
            this.tryCatchFire(worldIn, pos.up(), 250 + j, rand, EnumFacing.DOWN);
            this.tryCatchFire(worldIn, pos.north(), 300 + j, rand, EnumFacing.SOUTH);
            this.tryCatchFire(worldIn, pos.south(), 300 + j, rand, EnumFacing.NORTH);

            for (int k = -1; k <= 1; ++k)
            {
                for (int l = -1; l <= 1; ++l)
                {
                    for (int m = -1; m <= 4; ++m)
                    {
                        if (k != 0 || m != 0 || l != 0)
                        {
                            int n = 100;

                            if (m > 1)
                            {
                                n += (m - 1) * 100;
                            }

                            BlockPos blockpos = pos.add(k, m, l);
                            int o = this.getNeighborEncouragement(worldIn, blockpos);

                            if (o > 0)
                            {
                                int p = (o + 40 + worldIn.getDifficulty().getDifficultyId() * 7) / (30);

                                if (flag1)
                                {
                                    p /= 2;
                                }

                                if (p > 0 && rand.nextInt(n) <= p && (!worldIn.isRaining()))
                                {
                                	int q = rand.nextInt(5) / 4;

                                	if (q > 15)
                                    {
                                        q = 15;
                                    }

                                    worldIn.setBlockState(blockpos, Blocks.FIRE.getDefaultState().withProperty(BlockFire.AGE, Integer.valueOf(q)), 3);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
	
	public boolean requiresUpdates()
    {
        return false;
    }
	
	private void tryCatchFire(World worldIn, BlockPos pos, int chance, Random random, EnumFacing face)
    {
        int i = worldIn.getBlockState(pos).getBlock().getFlammability(worldIn, pos, face);

        if (random.nextInt(chance) < i)
        {
            IBlockState iblockstate = worldIn.getBlockState(pos);

            if (random.nextInt(10) < 5 && !worldIn.isRainingAt(pos))
            {
                int j = random.nextInt(5) / 4;

                if (j > 15)
                {
                    j = 15;
                }

                worldIn.setBlockState(pos, Blocks.FIRE.getDefaultState().withProperty(BlockFire.AGE, Integer.valueOf(j)), 3);
            }

            if (iblockstate.getBlock() == Blocks.TNT)
            {
                Blocks.TNT.onBlockDestroyedByPlayer(worldIn, pos, iblockstate.withProperty(BlockTNT.EXPLODE, Boolean.valueOf(true)));
            }
        }
    }
	
	private int getNeighborEncouragement(World worldIn, BlockPos pos)
    {
        if (!worldIn.isAirBlock(pos))
        {
            return 0;
        }
        else
        {
            int i = 0;

            for (EnumFacing enumfacing : EnumFacing.values())
            {
                i = Math.max(worldIn.getBlockState(pos.offset(enumfacing)).getBlock().getFireSpreadSpeed(worldIn, pos.offset(enumfacing), enumfacing.getOpposite()), i);
            }

            return i;
        }
    }
	
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random random)
	{
		for (int var5 = 0; var5 < 3; ++var5)
		{
			float x1 = pos.getX() + 0.5F;
			float y1 = pos.getY() + random.nextFloat();
			float z1 = pos.getZ() + 0.5F;
			float f = random.nextFloat() * 0.6F - 0.3F;
			float x2 = x1 + f;
			float z2 = z1 + f;

			worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x1 - 0.52F, y1, z2, 0.0D, 0.0D, 0.0D);
			worldIn.spawnParticle(EnumParticleTypes.FLAME, x1 - 0.52F, y1, z2, 0.0D, 0.0D, 0.0D);
			worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x1 + 0.52F, y1, z2, 0.0D, 0.0D, 0.0D);
			worldIn.spawnParticle(EnumParticleTypes.FLAME, x1 + 0.52F, y1, z2, 0.0D, 0.0D, 0.0D);
			worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x2, y1, z1 - 0.52F, 0.0D, 0.0D, 0.0D);
			worldIn.spawnParticle(EnumParticleTypes.FLAME, x2, y1, z1 - 0.52F, 0.0D, 0.0D, 0.0D);
			worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x2, y1, z1 + 0.52F, 0.0D, 0.0D, 0.0D);
			worldIn.spawnParticle(EnumParticleTypes.FLAME, x2, y1, z1 + 0.52F, 0.0D, 0.0D, 0.0D);
		}
	}
}
