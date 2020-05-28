package minestrapp.compat.tconstruct;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Biomes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitHeatResistant extends AbstractTrait
{
    boolean didntDamage = false;

    public TraitHeatResistant()
    {
        super("heat_resistant", TextFormatting.DARK_RED);
    }

    @Override
    public void afterBlockBreak(ItemStack tool, World world, IBlockState state, BlockPos pos, EntityLivingBase entity, boolean wasEffective)
    {
        Biome biome = entity.getEntityWorld().getBiome(new BlockPos(entity.posX, entity.posY, entity.posZ));
        if(biome.getTempCategory() == Biome.TempCategory.WARM )
        {
            if(Math.random() <= 0.5)
            {
                tool.damageItem(-1, entity);
            }
        }
    }
}