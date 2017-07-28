package minestrapp.proxy;

import javax.annotation.Nullable;

import minestrapp.MBlocks;
import minestrapp.MItems;
import minestrapp.Minestrapp5;
import minestrapp.block.BlockMGrass;
import minestrapp.mobs.registry.MobRegistry;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ClientProxy extends CommonProxy
{
	private static final Minecraft minecraft = Minecraft.getMinecraft();
	
	public void preInit(FMLPreInitializationEvent event)
	{
		super.preInit(event);
		MBlocks.registerRenders();
		MItems.registerRenders();


	}
	
	public void init(FMLInitializationEvent event)
	{
		super.init(event);
		ModelBakery.registerItemVariants(Item.getItemFromBlock(MBlocks.cold_sand), new ResourceLocation(Minestrapp5.MODID, "cold_sand_default"), new ResourceLocation(Minestrapp5.MODID, "cold_sand_red"));
		ModelBakery.registerItemVariants(Item.getItemFromBlock(MBlocks.clay_soil), new ResourceLocation(Minestrapp5.MODID, "clay_soil_default"), new ResourceLocation(Minestrapp5.MODID, "clay_soil_course"), new ResourceLocation(Minestrapp5.MODID, "clay_soil_podzol"));	
		ModelBakery.registerItemVariants(Item.getItemFromBlock(MBlocks.permafrost), new ResourceLocation(Minestrapp5.MODID, "permafrost_default"), new ResourceLocation(Minestrapp5.MODID, "permafrost_course"), new ResourceLocation(Minestrapp5.MODID, "permafrost_podzol"));	
		ModelBakery.registerItemVariants(Item.getItemFromBlock(MBlocks.stone), new ResourceLocation(Minestrapp5.MODID, "m_stone_red_rock"), new ResourceLocation(Minestrapp5.MODID, "m_stone_deep_red_rock"), new ResourceLocation(Minestrapp5.MODID, "m_stone_deepstone"), new ResourceLocation(Minestrapp5.MODID, "m_stone_coldstone"), new ResourceLocation(Minestrapp5.MODID, "m_stone_deep_coldstone"), new ResourceLocation(Minestrapp5.MODID, "m_stone_icestone"), new ResourceLocation(Minestrapp5.MODID, "m_stone_glacierrock"), new ResourceLocation(Minestrapp5.MODID, "m_stone_oceanstone"), new ResourceLocation(Minestrapp5.MODID, "m_stone_deep_oceanstone"), new ResourceLocation(Minestrapp5.MODID, "m_stone_stone"));
		ModelBakery.registerItemVariants(Item.getItemFromBlock(MBlocks.cobblestone), new ResourceLocation(Minestrapp5.MODID, "m_cobblestone_red_rock"), new ResourceLocation(Minestrapp5.MODID, "m_cobblestone_deep_red_rock"), new ResourceLocation(Minestrapp5.MODID, "m_cobblestone_deepstone"), new ResourceLocation(Minestrapp5.MODID, "m_cobblestone_coldstone"), new ResourceLocation(Minestrapp5.MODID, "m_cobblestone_deep_coldstone"), new ResourceLocation(Minestrapp5.MODID, "m_cobblestone_icestone"), new ResourceLocation(Minestrapp5.MODID, "m_cobblestone_glacierrock"), new ResourceLocation(Minestrapp5.MODID, "m_cobblestone_oceanstone"), new ResourceLocation(Minestrapp5.MODID, "m_cobblestone_deep_oceanstone"), new ResourceLocation(Minestrapp5.MODID, "m_cobblestone_stone"));
		ModelBakery.registerItemVariants(Item.getItemFromBlock(MBlocks.ore_coal), new ResourceLocation(Minestrapp5.MODID, "ore_coal_red_rock"), new ResourceLocation(Minestrapp5.MODID, "ore_coal_deep_red_rock"), new ResourceLocation(Minestrapp5.MODID, "ore_coal_deepstone"), new ResourceLocation(Minestrapp5.MODID, "ore_coal_coldstone"), new ResourceLocation(Minestrapp5.MODID, "ore_coal_deep_coldstone"), new ResourceLocation(Minestrapp5.MODID, "ore_coal_icestone"), new ResourceLocation(Minestrapp5.MODID, "ore_coal_glacierrock"), new ResourceLocation(Minestrapp5.MODID, "ore_coal_oceanstone"), new ResourceLocation(Minestrapp5.MODID, "ore_coal_deep_oceanstone"));
		ModelBakery.registerItemVariants(Item.getItemFromBlock(MBlocks.ore_copper), new ResourceLocation(Minestrapp5.MODID, "ore_copper_red_rock"), new ResourceLocation(Minestrapp5.MODID, "ore_copper_deep_red_rock"), new ResourceLocation(Minestrapp5.MODID, "ore_copper_deepstone"), new ResourceLocation(Minestrapp5.MODID, "ore_copper_coldstone"), new ResourceLocation(Minestrapp5.MODID, "ore_copper_deep_coldstone"), new ResourceLocation(Minestrapp5.MODID, "ore_copper_icestone"), new ResourceLocation(Minestrapp5.MODID, "ore_copper_glacierrock"), new ResourceLocation(Minestrapp5.MODID, "ore_copper_oceanstone"), new ResourceLocation(Minestrapp5.MODID, "ore_copper_deep_oceanstone"), new ResourceLocation(Minestrapp5.MODID, "ore_copper_stone"));
		ModelBakery.registerItemVariants(Item.getItemFromBlock(MBlocks.ore_tin), new ResourceLocation(Minestrapp5.MODID, "ore_tin_red_rock"), new ResourceLocation(Minestrapp5.MODID, "ore_tin_deep_red_rock"), new ResourceLocation(Minestrapp5.MODID, "ore_tin_deepstone"), new ResourceLocation(Minestrapp5.MODID, "ore_tin_coldstone"), new ResourceLocation(Minestrapp5.MODID, "ore_tin_deep_coldstone"), new ResourceLocation(Minestrapp5.MODID, "ore_tin_icestone"), new ResourceLocation(Minestrapp5.MODID, "ore_tin_glacierrock"), new ResourceLocation(Minestrapp5.MODID, "ore_tin_oceanstone"), new ResourceLocation(Minestrapp5.MODID, "ore_tin_deep_oceanstone"), new ResourceLocation(Minestrapp5.MODID, "ore_tin_stone"));
		ModelBakery.registerItemVariants(Item.getItemFromBlock(MBlocks.ore_iron), new ResourceLocation(Minestrapp5.MODID, "ore_iron_red_rock"), new ResourceLocation(Minestrapp5.MODID, "ore_iron_deep_red_rock"), new ResourceLocation(Minestrapp5.MODID, "ore_iron_deepstone"), new ResourceLocation(Minestrapp5.MODID, "ore_iron_coldstone"), new ResourceLocation(Minestrapp5.MODID, "ore_iron_deep_coldstone"), new ResourceLocation(Minestrapp5.MODID, "ore_iron_icestone"), new ResourceLocation(Minestrapp5.MODID, "ore_iron_glacierrock"), new ResourceLocation(Minestrapp5.MODID, "ore_iron_oceanstone"), new ResourceLocation(Minestrapp5.MODID, "ore_iron_deep_oceanstone"));
		ModelBakery.registerItemVariants(Item.getItemFromBlock(MBlocks.ore_gold), new ResourceLocation(Minestrapp5.MODID, "ore_gold_red_rock"), new ResourceLocation(Minestrapp5.MODID, "ore_gold_deep_red_rock"), new ResourceLocation(Minestrapp5.MODID, "ore_gold_deepstone"), new ResourceLocation(Minestrapp5.MODID, "ore_gold_coldstone"), new ResourceLocation(Minestrapp5.MODID, "ore_gold_deep_coldstone"), new ResourceLocation(Minestrapp5.MODID, "ore_gold_icestone"), new ResourceLocation(Minestrapp5.MODID, "ore_gold_glacierrock"), new ResourceLocation(Minestrapp5.MODID, "ore_gold_oceanstone"), new ResourceLocation(Minestrapp5.MODID, "ore_gold_deep_oceanstone"));
		ModelBakery.registerItemVariants(Item.getItemFromBlock(MBlocks.ore_lapis), new ResourceLocation(Minestrapp5.MODID, "ore_lapis_red_rock"), new ResourceLocation(Minestrapp5.MODID, "ore_lapis_deep_red_rock"), new ResourceLocation(Minestrapp5.MODID, "ore_lapis_deepstone"), new ResourceLocation(Minestrapp5.MODID, "ore_lapis_coldstone"), new ResourceLocation(Minestrapp5.MODID, "ore_lapis_deep_coldstone"), new ResourceLocation(Minestrapp5.MODID, "ore_lapis_icestone"), new ResourceLocation(Minestrapp5.MODID, "ore_lapis_glacierrock"), new ResourceLocation(Minestrapp5.MODID, "ore_lapis_oceanstone"), new ResourceLocation(Minestrapp5.MODID, "ore_lapis_deep_oceanstone"));
		ModelBakery.registerItemVariants(Item.getItemFromBlock(MBlocks.ore_redstone), new ResourceLocation(Minestrapp5.MODID, "ore_redstone_red_rock"), new ResourceLocation(Minestrapp5.MODID, "ore_redstone_deep_red_rock"), new ResourceLocation(Minestrapp5.MODID, "ore_redstone_deepstone"), new ResourceLocation(Minestrapp5.MODID, "ore_redstone_coldstone"), new ResourceLocation(Minestrapp5.MODID, "ore_redstone_deep_coldstone"), new ResourceLocation(Minestrapp5.MODID, "ore_redstone_icestone"), new ResourceLocation(Minestrapp5.MODID, "ore_redstone_glacierrock"), new ResourceLocation(Minestrapp5.MODID, "ore_redstone_oceanstone"), new ResourceLocation(Minestrapp5.MODID, "ore_redstone_deep_oceanstone"));
		ModelBakery.registerItemVariants(Item.getItemFromBlock(MBlocks.ore_torite), new ResourceLocation(Minestrapp5.MODID, "ore_torite_red_rock"), new ResourceLocation(Minestrapp5.MODID, "ore_torite_deep_red_rock"), new ResourceLocation(Minestrapp5.MODID, "ore_torite_deepstone"), new ResourceLocation(Minestrapp5.MODID, "ore_torite_coldstone"), new ResourceLocation(Minestrapp5.MODID, "ore_torite_deep_coldstone"), new ResourceLocation(Minestrapp5.MODID, "ore_torite_icestone"), new ResourceLocation(Minestrapp5.MODID, "ore_torite_glacierrock"), new ResourceLocation(Minestrapp5.MODID, "ore_torite_oceanstone"), new ResourceLocation(Minestrapp5.MODID, "ore_torite_deep_oceanstone"), new ResourceLocation(Minestrapp5.MODID, "ore_torite_stone"));
		ModelBakery.registerItemVariants(Item.getItemFromBlock(MBlocks.ore_diamond), new ResourceLocation(Minestrapp5.MODID, "ore_diamond_red_rock"), new ResourceLocation(Minestrapp5.MODID, "ore_diamond_deep_red_rock"), new ResourceLocation(Minestrapp5.MODID, "ore_diamond_deepstone"), new ResourceLocation(Minestrapp5.MODID, "ore_diamond_coldstone"), new ResourceLocation(Minestrapp5.MODID, "ore_diamond_deep_coldstone"), new ResourceLocation(Minestrapp5.MODID, "ore_diamond_icestone"), new ResourceLocation(Minestrapp5.MODID, "ore_diamond_glacierrock"), new ResourceLocation(Minestrapp5.MODID, "ore_diamond_oceanstone"), new ResourceLocation(Minestrapp5.MODID, "ore_diamond_deep_oceanstone"));
		ModelBakery.registerItemVariants(Item.getItemFromBlock(MBlocks.ore_emerald), new ResourceLocation(Minestrapp5.MODID, "ore_emerald_red_rock"), new ResourceLocation(Minestrapp5.MODID, "ore_emerald_deep_red_rock"), new ResourceLocation(Minestrapp5.MODID, "ore_emerald_deepstone"), new ResourceLocation(Minestrapp5.MODID, "ore_emerald_coldstone"), new ResourceLocation(Minestrapp5.MODID, "ore_emerald_deep_coldstone"), new ResourceLocation(Minestrapp5.MODID, "ore_emerald_icestone"), new ResourceLocation(Minestrapp5.MODID, "ore_emerald_glacierrock"), new ResourceLocation(Minestrapp5.MODID, "ore_emerald_oceanstone"), new ResourceLocation(Minestrapp5.MODID, "ore_emerald_deep_oceanstone"));
		ModelBakery.registerItemVariants(Item.getItemFromBlock(MBlocks.ore_titanium), new ResourceLocation(Minestrapp5.MODID, "ore_titanium_red_rock"), new ResourceLocation(Minestrapp5.MODID, "ore_titanium_deep_red_rock"), new ResourceLocation(Minestrapp5.MODID, "ore_titanium_deepstone"), new ResourceLocation(Minestrapp5.MODID, "ore_titanium_coldstone"), new ResourceLocation(Minestrapp5.MODID, "ore_titanium_deep_coldstone"), new ResourceLocation(Minestrapp5.MODID, "ore_titanium_icestone"), new ResourceLocation(Minestrapp5.MODID, "ore_titanium_glacierrock"), new ResourceLocation(Minestrapp5.MODID, "ore_titanium_oceanstone"), new ResourceLocation(Minestrapp5.MODID, "ore_titanium_deep_oceanstone"), new ResourceLocation(Minestrapp5.MODID, "ore_titanium_stone"));
		
		ModelBakery.registerItemVariants(Item.getItemFromBlock(MBlocks.crop_pepper), new ResourceLocation(Minestrapp5.MODID, "pepper_stage0"), new ResourceLocation(Minestrapp5.MODID, "pepper_stage1"), new ResourceLocation(Minestrapp5.MODID, "pepper_stage2"), new ResourceLocation(Minestrapp5.MODID, "pepper_stage3"), new ResourceLocation(Minestrapp5.MODID, "pepper_stage4"), new ResourceLocation(Minestrapp5.MODID, "pepper_stage5"), new ResourceLocation(Minestrapp5.MODID, "pepper_stage6"), new ResourceLocation(Minestrapp5.MODID, "pepper_stage7"));

		
		MobRegistry.register();
		
		registerColorHandlers();
	}
	
	public static void registerColorHandlers()
	{
		final BlockColors blockcolors = minecraft.getBlockColors();
		
		final IBlockColor mGrassColorHandler = (IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) -> 
		{
			return worldIn != null && pos != null ? BiomeColorHelper.getGrassColorAtPos(worldIn, pos) : ColorizerGrass.getGrassColor(0.5D, 1.0D);
		};
		
		blockcolors.registerBlockColorHandler(mGrassColorHandler, MBlocks.clay_grass);
//		blockcolors.registerBlockColorHandler(new IBlockColor()
//        {
//            public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex)
//            {
//                return worldIn != null && pos != null ? BiomeColorHelper.getGrassColorAtPos(worldIn, pos) : ColorizerGrass.getGrassColor(0.5D, 1.0D);
//            }
//        }, MBlocks.clay_grass);
	}
}
