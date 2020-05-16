package minestrapp;

import minestrapp.compat.CompatAbstract;
import minestrapp.compat.MinestrappCompat;
import minestrapp.config.MConfig;
import minestrapp.proxy.CommonProxy;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.ArrayList;

@Mod(modid = Minestrapp5.MODID, name = Minestrapp5.NAME, version = Minestrapp5.VERSION, guiFactory = "minestrapp.config.MConfigGUIFactory", useMetadata = true)
public class Minestrapp5
{
    public static final String MODID = "minestrapp";
    public static final String NAME = "Minestrappolation";
    public static final String VERSION = "5.7.0";

    public ArrayList<CompatAbstract> plugins;
    
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
        MinestrappCompat.registerPlugins();
        plugins = MinestrappCompat.getPlugins();
        for(CompatAbstract plugin : plugins)
        {
            if(Loader.isModLoaded(plugin.getModid()))
            {
                try
                {
                    plugin.preInit();
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
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	proxy.init(event);
        for(CompatAbstract plugin : plugins)
        {
            if(Loader.isModLoaded(plugin.getModid()))
            {
                try
                {
                    plugin.init();
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
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	proxy.postInit(event);
        for(CompatAbstract plugin : plugins)
        {
            if(Loader.isModLoaded(plugin.getModid()))
            {
                try
                {
                    plugin.postInit();
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
    }
}
