package minestrapp.compat.tconstruct;

import minestrapp.MBlocks;
import minestrapp.MItems;
import minestrapp.compat.CompatAbstract;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.fluid.FluidMolten;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.HarvestLevels;
import slimeknights.tconstruct.shared.TinkerFluids;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.tools.TinkerMaterials;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.TinkerTraits;

public class TConstructCompat extends CompatAbstract
{
    private ItemStack meuroditeGem = new ItemStack(MItems.gems, 1, 4);
    public Material meuroditeMaterial = new Material("meurodite", 0x212970);
    public FluidMolten meuroditeFluid;
    public final AbstractTrait traitHeatResistant = new TraitHeatResistant();

    private ItemStack toriteIngot = new ItemStack(MItems.ingots, 1, 4);
    public Material toriteMaterial = new Material("torite", 0x1d9c09);
    public FluidMolten toriteFluid;

    private ItemStack glacieriteIngot = new ItemStack(MItems.ingots, 1, 6);
    public Material glacieriteMaterial = new Material("glacierite", 0x56a1cd);
    public FluidMolten glacieriteFluid;

    private ItemStack blazeShard = new ItemStack(MItems.gems, 1, 5);
    public Material blazeMaterial = TinkerMaterials.blaze;
    public FluidMolten blazeFluid;

    private ItemStack blaziumIngot = new ItemStack(MItems.ingots, 1, 7);
    public Material blaziumMaterial = new Material("blazium", 0xe1890b);
    public FluidMolten blaziumFluid;

    private ItemStack archantineIngot = new ItemStack(MItems.ingots, 1, 5);
    public Material archantineMaterial = new Material("archantine", 0x642936);
    public FluidMolten archantineFluid;

    public void preInit()
    {
        IForgeRegistry<Block> registry = ForgeRegistries.BLOCKS;

        meuroditeFluid = fluidMetal(meuroditeMaterial);
        meuroditeFluid.setTemperature(700);
        TinkerFluids.registerMoltenBlock(registry, meuroditeFluid);

        TinkerRegistry.addMaterialStats(meuroditeMaterial,
                new HeadMaterialStats(524, 7.4f, 5.0f, HarvestLevels.DIAMOND),
                new HandleMaterialStats(0.6f, 20),
                new ExtraMaterialStats(250),
                new BowMaterialStats(0.2f, 2f, 5));
        TinkerRegistry.integrate(meuroditeMaterial, meuroditeFluid).preInit();

        toriteFluid = fluidMetal(toriteMaterial);
        toriteFluid.setTemperature(700);
        TinkerFluids.registerMoltenBlock(registry, toriteFluid);

        TinkerRegistry.addMaterialStats(toriteMaterial,
                new HeadMaterialStats(640, 7.8f, 6.0f, HarvestLevels.DIAMOND),
                new HandleMaterialStats(1f, 120),
                new ExtraMaterialStats(280),
                new BowMaterialStats(0.2f, 2f, 5));
        TinkerRegistry.integrate(toriteMaterial, toriteFluid).preInit();

        glacieriteFluid = fluidMetal(glacieriteMaterial);
        glacieriteFluid.setTemperature(200);
        TinkerFluids.registerMoltenBlock(registry, glacieriteFluid);

        TinkerRegistry.addMaterialStats(glacieriteMaterial,
                new HeadMaterialStats(680, 8.0f, 6.25f, HarvestLevels.OBSIDIAN),
                new HandleMaterialStats(1.2f, 250),
                new ExtraMaterialStats(300),
                new BowMaterialStats(0.2f, 2f, 5));
        TinkerRegistry.integrate(glacieriteMaterial, glacieriteFluid).preInit();

        blazeFluid = fluidLiquid(blazeMaterial);
        blazeFluid.setTemperature(1200);
        TinkerFluids.registerMoltenBlock(registry, blazeFluid);
        blazeMaterial.setFluid(blazeFluid);

        blaziumFluid = fluidMetal(blaziumMaterial);
        blaziumFluid.setTemperature(1200);
        TinkerFluids.registerMoltenBlock(registry, blaziumFluid);

        TinkerRegistry.addMaterialStats(blaziumMaterial,
                new HeadMaterialStats(596, 7.9f, 5.5f, HarvestLevels.DIAMOND),
                new HandleMaterialStats(0.9f, 180),
                new ExtraMaterialStats(180),
                new BowMaterialStats(0.2f, 2f, 5));
        TinkerRegistry.integrate(blaziumMaterial, blaziumFluid).preInit();

        archantineFluid = fluidMetal(archantineMaterial);
        archantineFluid.setTemperature(999);
        TinkerFluids.registerMoltenBlock(registry, archantineFluid);

        TinkerRegistry.addMaterialStats(archantineMaterial,
                new HeadMaterialStats(1108, 10f, 8.0f, HarvestLevels.COBALT),
                new HandleMaterialStats(1.8f, -650),
                new ExtraMaterialStats(500),
                new BowMaterialStats(0.2f, 2f, 5));
        TinkerRegistry.integrate(archantineMaterial, archantineFluid).preInit();
    }

    public void init()
    {
        meuroditeMaterial.addItem(meuroditeGem, 1, Material.VALUE_Ingot);
        meuroditeMaterial
                .addTrait(traitHeatResistant)
                .setCraftable(false).setCastable(true)
                .setRepresentativeItem(meuroditeGem);

        toriteMaterial.addItem(toriteIngot, 1, Material.VALUE_Ingot);
        toriteMaterial
                .setCraftable(false).setCastable(true)
                .setRepresentativeItem(toriteIngot);

        glacieriteMaterial.addItem(glacieriteIngot, 1, Material.VALUE_Ingot);
        glacieriteMaterial
                .addTrait(TinkerTraits.freezing)
                .setCraftable(false).setCastable(true)
                .setRepresentativeItem(glacieriteIngot);

        blazeMaterial.addItem(blazeShard, 1, Material.VALUE_Gem);
        blazeMaterial.setRepresentativeItem(blazeShard);

        blaziumMaterial.addItem(blaziumIngot, 1, Material.VALUE_Ingot);
        blaziumMaterial
                .addTrait(TinkerTraits.superheat)
                .addTrait(TinkerTraits.autosmelt)
                .setCraftable(false).setCastable(true)
                .setRepresentativeItem(blaziumIngot);

        archantineMaterial.addItem(archantineIngot, 1, Material.VALUE_Ingot);
        archantineMaterial
                .setCraftable(false).setCastable(true)
                .setRepresentativeItem(archantineIngot);
    }

    public void postInit()
    {
        TinkerRegistry.registerMelting(meuroditeGem, meuroditeFluid, Material.VALUE_Ingot);
        TinkerRegistry.registerMelting(MBlocks.ore_meurodite, meuroditeFluid, Material.VALUE_Ingot);
        TinkerRegistry.registerMelting(MBlocks.block_meurodite, meuroditeFluid, Material.VALUE_Block);
        TinkerRegistry.registerTableCasting(meuroditeGem, TinkerSmeltery.castGem, meuroditeFluid, Material.VALUE_Ingot);
        TinkerRegistry.registerBasinCasting(new ItemStack(MBlocks.block_meurodite), ItemStack.EMPTY, meuroditeFluid, Material.VALUE_Block);

        TinkerRegistry.registerMelting(toriteIngot, toriteFluid, Material.VALUE_Ingot);
        TinkerRegistry.registerMelting(MBlocks.ore_torite, toriteFluid, Material.VALUE_Ingot);
        TinkerRegistry.registerMelting(MBlocks.block_torite, toriteFluid, Material.VALUE_Block);
        TinkerRegistry.registerTableCasting(toriteIngot, TinkerSmeltery.castIngot, toriteFluid, Material.VALUE_Ingot);
        TinkerRegistry.registerBasinCasting(new ItemStack(MBlocks.block_torite), ItemStack.EMPTY, toriteFluid, Material.VALUE_Block);

        TinkerRegistry.registerMelting(glacieriteIngot, glacieriteFluid, Material.VALUE_Ingot);
        TinkerRegistry.registerMelting(MBlocks.block_glacierite, glacieriteFluid, Material.VALUE_Block);
        TinkerRegistry.registerTableCasting(glacieriteIngot, TinkerSmeltery.castIngot, glacieriteFluid, Material.VALUE_Ingot);
        TinkerRegistry.registerBasinCasting(new ItemStack(MBlocks.block_glacierite), ItemStack.EMPTY, glacieriteFluid, Material.VALUE_Block);

        TinkerRegistry.registerMelting(blazeShard, blazeFluid, Material.VALUE_Shard);
        TinkerRegistry.registerMelting(MBlocks.ore_blazium, blazeFluid, Material.VALUE_Shard * 2);
        TinkerRegistry.registerMelting(new ItemStack(Items.BLAZE_ROD), blazeFluid, Material.VALUE_Shard);
        TinkerRegistry.registerMelting(new ItemStack(Items.BLAZE_POWDER), blazeFluid, Material.VALUE_Shard / 2);
        TinkerRegistry.registerTableCasting(blazeShard, TinkerSmeltery.castGem, blazeFluid, Material.VALUE_Shard);

        TinkerRegistry.registerMelting(blaziumIngot, blaziumFluid, Material.VALUE_Ingot);
        TinkerRegistry.registerMelting(MBlocks.block_blazium, blaziumFluid, Material.VALUE_Block);
        TinkerRegistry.registerAlloy(
                new FluidStack(blaziumFluid, Material.VALUE_Ingot),
                new FluidStack(TinkerFluids.gold, Material.VALUE_Ingot),
                new FluidStack(blazeFluid, Material.VALUE_Shard * 4));
        TinkerRegistry.registerTableCasting(blaziumIngot, TinkerSmeltery.castIngot, blaziumFluid, Material.VALUE_Ingot);
        TinkerRegistry.registerBasinCasting(new ItemStack(MBlocks.block_blazium), ItemStack.EMPTY, blaziumFluid, Material.VALUE_Block);

        TinkerRegistry.registerMelting(archantineIngot, archantineFluid, Material.VALUE_Ingot);
        TinkerRegistry.registerTableCasting(archantineIngot, TinkerSmeltery.castIngot, archantineFluid, Material.VALUE_Ingot);
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

    private static FluidMolten fluidLiquid(Material material) {
        return fluidLiquid(material.getIdentifier(), material.materialTextColor);
    }

    private static FluidMolten fluidLiquid(String name, int color) {
        FluidMolten fluid = new FluidMolten(name, color, FluidMolten.ICON_LiquidStill, FluidMolten.ICON_LiquidFlowing);
        return registerFluid(fluid);
    }

    protected static <T extends Fluid> T registerFluid(T fluid)
    {
        fluid.setUnlocalizedName(Util.prefix(fluid.getName()));
        FluidRegistry.registerFluid(fluid);

        return fluid;
    }
}

