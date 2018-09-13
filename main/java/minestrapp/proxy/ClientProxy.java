package minestrapp.proxy;

import javax.annotation.Nullable;

import minestrapp.MBlocks;
import minestrapp.MItems;
import minestrapp.MSounds;
import minestrapp.Minestrapp5;
import minestrapp.block.BlockBiomeRedstoneWire;
import minestrapp.block.tileentity.TileEntityCrusher;
import minestrapp.block.tileentity.TileEntityMagnetPiston;
import minestrapp.block.tileentity.TileEntityPlate;
import minestrapp.block.tileentity.TileEntityTanningRack;
import minestrapp.block.tileentity.renderer.TESRCrusher;
import minestrapp.block.tileentity.renderer.TESRPlate;
import minestrapp.block.tileentity.renderer.TESRTanningRack;
import minestrapp.block.tileentity.renderer.TileEntityMagnetPistonRenderer;
import minestrapp.entity.mob.EntityBurfalaunt;
import minestrapp.entity.model.ModelBurfalaunt;
import minestrapp.entity.render.RenderBurfalaunt;
import minestrapp.entity.render.RenderMBoat;
import minestrapp.entity.vehicle.EntityMBoat;
import minestrapp.mobs.registry.MobRegistry;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
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
		MobRegistry.register();
		registerRenderers();
		registerEntities();
		
	}
	
	public void init(FMLInitializationEvent event)
	{
		super.init(event);
		
		registerColorHandlers();
	}
	
	public static void registerColorHandlers()
	{
		final BlockColors blockcolors = minecraft.getBlockColors();
		
		final IBlockColor mGrassColorHandler = (IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) -> 
		{
			return worldIn != null && pos != null ? BiomeColorHelper.getGrassColorAtPos(worldIn, pos) : ColorizerGrass.getGrassColor(0.5D, 1.0D);
		};
		
		final IBlockColor sandyRedstoneColorHandler = (IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) -> 
		{
			return BlockBiomeRedstoneWire.colorMultiplier(worldIn.getBlockState(pos).getValue(BlockBiomeRedstoneWire.POWER).intValue(), MBlocks.redstone_sandy);
		};
		
		final IBlockColor frostedRedstoneColorHandler = (IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) -> 
		{
			return BlockBiomeRedstoneWire.colorMultiplier(worldIn.getBlockState(pos).getValue(BlockBiomeRedstoneWire.POWER).intValue(), MBlocks.redstone_frosted);
		};
		
		final IBlockColor icyRedstoneColorHandler = (IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) -> 
		{
			return BlockBiomeRedstoneWire.colorMultiplier(worldIn.getBlockState(pos).getValue(BlockBiomeRedstoneWire.POWER).intValue(), MBlocks.redstone_icy);
		};
		
		final IBlockColor brinyRedstoneColorHandler = (IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) -> 
		{
			return BlockBiomeRedstoneWire.colorMultiplier(worldIn.getBlockState(pos).getValue(BlockBiomeRedstoneWire.POWER).intValue(), MBlocks.redstone_briny);
		};
		
		blockcolors.registerBlockColorHandler(mGrassColorHandler, MBlocks.clay_grass);
		blockcolors.registerBlockColorHandler(sandyRedstoneColorHandler, MBlocks.redstone_sandy);
		blockcolors.registerBlockColorHandler(frostedRedstoneColorHandler, MBlocks.redstone_frosted);
		blockcolors.registerBlockColorHandler(icyRedstoneColorHandler, MBlocks.redstone_icy);
		blockcolors.registerBlockColorHandler(brinyRedstoneColorHandler, MBlocks.redstone_briny);
	}
	
	public static void registerRenderers()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMagnetPiston.class, new TileEntityMagnetPistonRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrusher.class, new TESRCrusher());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTanningRack.class, new TESRTanningRack());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlate.class, new TESRPlate());
		registerEntityRenderer(EntityMBoat.class, RenderMBoat.class);
	}
	
	public static void registerEntities()
	{
		EntityRegistry.registerModEntity(new ResourceLocation(Minestrapp5.MODID, "m_boat"), EntityMBoat.class, "m_boat", 500, Minestrapp5.instance, 64, 1, true);
	}

	private static <E extends Entity> void registerEntityRenderer(Class<E> entityClass, Class<? extends Render<E>> renderClass)
    {
        RenderingRegistry.registerEntityRenderingHandler(entityClass, new EntityRenderFactory<E>(renderClass));
    }
	
	private static class EntityRenderFactory<E extends Entity> implements IRenderFactory<E>
    {
        private Class<? extends Render<E>> renderClass;

        private EntityRenderFactory(Class<? extends Render<E>> renderClass)
        {
            this.renderClass = renderClass;
        }

        @Override
        public Render<E> createRenderFor(RenderManager manager) 
        {
            Render<E> renderer = null;

            try 
            {
                renderer = renderClass.getConstructor(RenderManager.class).newInstance(manager);
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }

            return renderer;
        }
    }
}
