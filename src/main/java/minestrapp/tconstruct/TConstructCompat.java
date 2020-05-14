package minestrapp.tconstruct;

import minestrapp.MItems;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.fluid.FluidMolten;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.HarvestLevels;
import slimeknights.tconstruct.shared.TinkerFluids;

public class TConstructCompat
{
    public static Material meurodite = new Material("meurodite",  0x212970);
    public static FluidMolten meuroditeFluid;

    public static final AbstractTrait traitMeurodite = new TraitMeurodite();

    public static void preInit()
    {
        TinkerRegistry.addMaterialStats(TConstructCompat.meurodite,
                new HeadMaterialStats(1024, 5.5f, 8.0f, HarvestLevels.DIAMOND),
                new HandleMaterialStats(1.4f, 1024),
                new ExtraMaterialStats(20),
                new BowMaterialStats(1.2f, 2f, 5));
                TinkerRegistry.integrate(TConstructCompat.meurodite).preInit();
    }

    public static void init()
    {
        IForgeRegistry<Block> registry = ForgeRegistries.BLOCKS;

        ItemStack meuroditeGem = new ItemStack(MItems.gems, 1, 4);
        TConstructCompat.meurodite.addItem(meuroditeGem, 1, Material.VALUE_Ingot);
        TConstructCompat.meurodite
                .addTrait(TConstructCompat.traitMeurodite)
                .setCraftable(true).setCastable(false)
                .setRepresentativeItem(meuroditeGem);
        meuroditeFluid = fluidMetal("meurodite", 0x212970);
        meuroditeFluid.setTemperature(999);
        TinkerFluids.registerMoltenBlock(registry, meuroditeFluid);
        FluidRegistry.addBucketForFluid(meuroditeFluid);
        TinkerRegistry.registerMelting(meuroditeGem, meuroditeFluid, 144);
    }

    private static FluidMolten fluidMetal(String name, int color) {
        FluidMolten fluid = new FluidMolten(name, color);
        return registerFluid(fluid);
    }

    protected static <T extends Fluid> T registerFluid(T fluid) {
        fluid.setUnlocalizedName(Util.prefix(fluid.getName()));
        FluidRegistry.registerFluid(fluid);

        return fluid;
    }
}
