package minestrapp.worldgen;

import java.util.Random;

import minestrapp.MBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class MGenMud extends WorldGenerator
{
    private final Block block;
    private final int radius;

    public MGenMud(Block block, int maxRadius)
    {
        this.block = block;
        this.radius = maxRadius;
    }

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        if (worldIn.getBlockState(position).getMaterial() != Material.WATER)
        {
            return false;
        }
        else
        {
            int i = rand.nextInt(this.radius - 3) + 3;

            for (int k = position.getX() - i; k <= position.getX() + i; ++k)
            {
                for (int l = position.getZ() - i; l <= position.getZ() + i; ++l)
                {
                    int m = k - position.getX();
                    int n = l - position.getZ();

                    if (m * m + n * n <= i * i)
                    {
                        for (int o = position.getY() - 2; o <= position.getY() + 2; ++o)
                        {
                            BlockPos blockpos = new BlockPos(k, o, l);
                            Block block = worldIn.getBlockState(blockpos).getBlock();

                            if (block == Blocks.DIRT || block == Blocks.GRASS || block == MBlocks.clay_soil || block == MBlocks.clay_grass || block == MBlocks.permafrost || block == MBlocks.lichen)
                            {
                                worldIn.setBlockState(blockpos, this.block.getDefaultState(), 2);
                            }
                        }
                    }
                }
            }

            return true;
        }
    }
}
