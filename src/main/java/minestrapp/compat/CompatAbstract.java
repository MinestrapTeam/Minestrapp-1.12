package minestrapp.compat;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;

public abstract class CompatAbstract
{
    public abstract void preInit();
    public abstract void init();
    public abstract void postInit();
    public abstract String getModid();
}
