package minestrapp.compat.tconstruct;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitIncendiary extends AbstractTrait
{
    boolean didntDamage = false;

    public TraitIncendiary()
    {
        super("incendiary", TextFormatting.DARK_RED);
    }

    @Override
    public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical)
    {
        target.setFire(4);
    }

    @Override
    public void afterBlockBreak(ItemStack tool, World world, IBlockState state, BlockPos pos, EntityLivingBase player, boolean wasEffective)
    {
        if(state.getBlock().isFlammable(world, pos, player.getAdjustedHorizontalFacing()) && wasEffective)
        {
            if(Math.random() < 0.5)
            {
                BlockPos firePos = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());

                if (world.isAirBlock(firePos))
                {
                    world.setBlockState(firePos, Blocks.FIRE.getDefaultState(), 11);
                    tool.damageItem(1, player);
                }

            }
        }


    }
}