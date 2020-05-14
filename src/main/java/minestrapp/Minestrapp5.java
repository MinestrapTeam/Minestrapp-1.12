package minestrapp;

import minestrapp.config.MConfig;
import minestrapp.proxy.CommonProxy;

import minestrapp.tconstruct.TConstructCompat;
import net.minecraft.init.Blocks;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

@Mod(modid = Minestrapp5.MODID, name = Minestrapp5.NAME, version = Minestrapp5.VERSION, guiFactory = "minestrapp.config.MConfigGUIFactory", useMetadata = true)
public class Minestrapp5
{
    public static final String MODID = "minestrapp";
    public static final String NAME = "Minestrappolation";
    public static final String VERSION = "5.7.0";
    
    @SidedProxy(clientSide = "minestrapp.proxy.ClientProxy", serverSide = "minestrapp.proxy.CommonProxy")
    public static CommonProxy proxy;
    
    @Instance("minestrapp")
    public static Minestrapp5 instance;
    
    static
    {
        FluidRegistry.enableUniversalBucket();
    }
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	proxy.preInit(event);
    	MConfig.preInit();
    	MConfig.clientPreInit();
    	if(Loader.isModLoaded("tconstruct"))
    	{
            try
            {
                TConstructCompat.preInit();
            } catch (Exception e)
            {
                //log
                e.printStackTrace();
            }
        }
    	else
        {
    	    //log
        }
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	proxy.init(event);
        if(Loader.isModLoaded("tconstruct"))
        {
            try
            {
                TConstructCompat.init();
            } catch (Exception e)
            {
                //log
                e.printStackTrace();
            }
        }
        else
        {
            //log
        }
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	proxy.postInit(event);
    }
}
