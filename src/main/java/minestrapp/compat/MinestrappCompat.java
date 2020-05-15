package minestrapp.compat;

import minestrapp.compat.tconstruct.TConstructCompat;

import java.util.ArrayList;

public class MinestrappCompat
{
    private static ArrayList<CompatAbstract> plugins = new ArrayList<>();

    public static void registerPlugins()
    {
        registerPlugin(new TConstructCompat());
    }

    public static ArrayList<CompatAbstract> getPlugins()
    {
        return plugins;
    }

    private static void registerPlugin(CompatAbstract p)
    {
        plugins.add(p);
    }
}
