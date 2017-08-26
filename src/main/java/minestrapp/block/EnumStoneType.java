package minestrapp.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public enum EnumStoneType implements IStringSerializable
{
    RED_ROCK(0, "red_rock", "red_rock", MapColor.ADOBE, false),
    DEEP_RED_ROCK(1, "deep_red_rock", "deep_red_rock", MapColor.BROWN_STAINED_HARDENED_CLAY, true),
    DEEPSTONE(2, "deepstone", "deepstone", MapColor.GRAY, true),
    COLDSTONE(3, "coldstone", "coldstone", MapColor.LIGHT_BLUE_STAINED_HARDENED_CLAY, false),
    DEEP_COLDSTONE(4, "deep_coldstone", "deep_coldstone", MapColor.BLUE_STAINED_HARDENED_CLAY, true),
    ICESTONE(5, "icestone", "icestone", MapColor.CYAN, false),
    GLACIERROCK(6, "glacierrock", "glacierrock", MapColor.BLUE, true),
    OCEANSTONE(7, "oceanstone", "oceanstone", MapColor.WOOD, false),
    DEEP_OCEANSTONE(8, "deep_oceanstone", "deep_oceanstone", MapColor.BROWN_STAINED_HARDENED_CLAY, true),
    STONE(9, "stone", "stone", MapColor.STONE, false);

    private static final EnumStoneType[] META_LOOKUP = new EnumStoneType[values().length];
    private final int meta;
    private final String name;
    private final MapColor mapColor;
    private final String unlocalizedName;
    private final boolean deep;

    private EnumStoneType(int meta, String name, String unlocalized, MapColor mapColor, boolean deep)
    {
        this.meta = meta;
        this.name = name;
        this.mapColor = mapColor;
        this.unlocalizedName = unlocalized;
        this.deep = deep;
    }

    public boolean isDeep()
    {
        return this.deep;
    }

    public int getMetadata()
    {
        return this.meta;
    }

    public String toString()
    {
        return this.name;
    }

    public MapColor getMapColor()
    {
        return this.mapColor;
    }

    public static EnumStoneType byMetadata(int meta)
    {
        if (meta < 0 || meta >= META_LOOKUP.length)
        {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }

    public String getName()
    {
        return this.name;
    }

    public String getUnlocalizedName()
    {
        return this.unlocalizedName;
    }
    
    public String getRegisryName()
    {
    	return this.unlocalizedName;
    }

    static
    {
        for (EnumStoneType stone$enumtype : values())
        {
            META_LOOKUP[stone$enumtype.getMetadata()] = stone$enumtype;
        }
    }
}
