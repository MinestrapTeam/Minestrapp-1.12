package minestrapp;

import minestrapp.fluid.FluidBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;

public class MFluids
{
	public static FluidBase crystalfloe;
	
	public static void init()
	{
		register(crystalfloe = (FluidBase) new FluidBase("crystalfloe", new ResourceLocation(Minestrapp.MODID, "blocks/liquid/crystalfloe_still"), new ResourceLocation(Minestrapp.MODID, "blocks/liquid/crystalfloe_flow")).setHasBucket(true).setDensity(40).setGaseous(false).setLuminosity(12).setViscosity(1000).setTemperature(500));
	}
    
    public static void register(FluidBase fluid)
    {
    	FluidRegistry.registerFluid(fluid);
    	
    	if (fluid.isBucketEnabled())
    		FluidRegistry.addBucketForFluid(fluid);
    }
}
