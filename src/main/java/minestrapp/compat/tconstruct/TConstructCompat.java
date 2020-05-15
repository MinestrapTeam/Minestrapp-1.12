package minestrapp.compat.tconstruct;

import minestrapp.MBlocks;
import minestrapp.MItems;
import minestrapp.compat.CompatAbstract;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import slimeknights.tconstruct.TinkerIntegration;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.fluid.FluidMolten;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.HarvestLevels;
import slimeknights.tconstruct.shared.TinkerFluids;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

public class TConstructCompat extends CompatAbstract
{
    private ItemStack meuroditeGem = new ItemStack(MItems.gems, 1, 4);
    public Material meuroditeMaterial = new Material("meurodite", 0x21_29_70);
    public FluidMolten meuroditeFluid;
    public final AbstractTrait meuroditeTrait = new TraitMeurodite();

    public void preInit()
    {
        IForgeRegistry<Block> registry = ForgeRegistries.BLOCKS;

        meuroditeFluid = fluidMetal(meuroditeMaterial);
        meuroditeFluid.setTemperature(999);
        TinkerFluids.registerMoltenBlock(registry, meuroditeFluid);

        TinkerRegistry.addMaterialStats(meuroditeMaterial,
                new HeadMaterialStats(1024, 5.5f, 8.0f, HarvestLevels.DIAMOND),
                new HandleMaterialStats(1.4f, 1024),
                new ExtraMaterialStats(20),
                new BowMaterialStats(1.2f, 2f, 5));
        TinkerRegistry.integrate(meuroditeMaterial, meuroditeFluid).preInit();
    }

    public void init()
    {
        meuroditeMaterial.addItem(meuroditeGem, 1, Material.VALUE_Ingot);
        meuroditeMaterial
                .addTrait(meuroditeTrait)
                .setCraftable(false).setCastable(true)
                .setRepresentativeItem(meuroditeGem);

        //FluidRegistry.addBucketForFluid(meuroditeFluid); TODO: Copy fluid system from tconstruct and make molten fluids in minstrapp
    }

    public void postInit()
    {
        TinkerRegistry.registerMelting(meuroditeGem, meuroditeFluid, 144);
        TinkerRegistry.registerMelting(MBlocks.ore_meurodite, meuroditeFluid, 144);

        TinkerRegistry.registerTableCasting(meuroditeGem, TinkerSmeltery.castGem, meuroditeFluid, 144);
        TinkerRegistry.registerBasinCasting(new ItemStack(MBlocks.block_meurodite), ItemStack.EMPTY, meuroditeFluid, 600);

        System.out.println("MEUROI " + TinkerIntegration.isIntegrated(meuroditeFluid));
    }

    public String getModid()
    {
        return "tconstruct";
    }

    private static FluidMolten fluidMetal(Material material)
    {
        return fluidMetal(material.getIdentifier(), material.materialTextColor);
    }

    private static FluidMolten fluidMetal(String name, int color)
    {
        FluidMolten fluid = new FluidMolten(name, color);
        return registerFluid(fluid);
    }

    protected static <T extends Fluid> T registerFluid(T fluid)
    {
        fluid.setUnlocalizedName(Util.prefix(fluid.getName()));
        FluidRegistry.registerFluid(fluid);

        return fluid;
    }
}

