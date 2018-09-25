package minestrapp;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

import minestrapp.fluid.FluidBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;

public class MFluids
{
	public static FluidBase crystalfloe;
	
	public static void init()
	{
		register(crystalfloe = (FluidBase) new FluidBase("crystalfloe", new ResourceLocation(Minestrapp5.MODID, "blocks/liquid/crystalfloe_still"), new ResourceLocation(Minestrapp5.MODID, "blocks/liquid/crystalfloe_flow")).setHasBucket(true).setDensity(1100).setGaseous(false).setLuminosity(9).setViscosity(25000).setTemperature(300));
	}
    
    public static void register(FluidBase fluid)
    {
    	FluidRegistry.registerFluid(fluid);
    	
    	if (fluid.isBucketEnabled())
    		FluidRegistry.addBucketForFluid(fluid);
    }
}
