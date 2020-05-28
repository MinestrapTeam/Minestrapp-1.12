package minestrapp.compat.tconstruct;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitAlternativeMedicine extends AbstractTrait
{
    public TraitAlternativeMedicine()
    {
        super("alternative_medicine", TextFormatting.DARK_GREEN);
    }

    @Override
    public int onToolDamage(ItemStack tool, int damage, int newDamage, EntityLivingBase entity) {
        if(entity.getMaxHealth() - entity.getHealth() != 0)
        {
            if(Math.random() < 0.3) //30% chance to heal half a heart
                entity.heal(0.5f);
        }
        return newDamage;
    }
}