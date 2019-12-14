package minestrapp.proxy;

import javax.annotation.Nullable;

import minestrapp.MBlocks;
import minestrapp.MItems;
import minestrapp.Minestrapp5;
import minestrapp.block.BlockBiomeRedstoneWire;
import minestrapp.block.tileentity.TileEntityActivator;
import minestrapp.block.tileentity.TileEntityCrusher;
import minestrapp.block.tileentity.TileEntityMagnetPiston;
import minestrapp.block.tileentity.TileEntityPlate;
import minestrapp.block.tileentity.TileEntityTanningRack;
import minestrapp.block.tileentity.renderer.TESRActivator;
import minestrapp.block.tileentity.renderer.TESRCrusher;
import minestrapp.block.tileentity.renderer.TESRPlate;
import minestrapp.block.tileentity.renderer.TESRTanningRack;
import minestrapp.block.tileentity.renderer.TileEntityMagnetPistonRenderer;
import minestrapp.entity.render.RenderMBoat;
import minestrapp.entity.vehicle.EntityMBoat;
import minestrapp.mobs.registry.MobRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.client.model.obj.OBJLoader;
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
		OBJLoader.INSTANCE.addDomain(Minestrapp5.MODID);
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
		
		final IBlockColor mFoliageColorHandler = (IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) -> 
		{
			return worldIn != null && pos != null ? BiomeColorHelper.getFoliageColorAtPos(worldIn, pos) : ColorizerFoliage.getFoliageColor(0.5D, 1.0D);
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
		blockcolors.registerBlockColorHandler(mFoliageColorHandler, MBlocks.leaves);
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
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityActivator.class, new TESRActivator());
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
	
	@Override
	public boolean handleMaterialAcceleration(Entity entityIn, Material materialIn)
    {
		World parWorld = entityIn.world;
		AxisAlignedBB bb = entityIn.getEntityBoundingBox().grow(0.0D, -0.4000000059604645D, 0.0D).shrink(0.001D);
     
        int j2 = MathHelper.floor(bb.minX);
        int k2 = MathHelper.ceil(bb.maxX);
        int l2 = MathHelper.floor(bb.minY);
        int i3 = MathHelper.ceil(bb.maxY);
        int j3 = MathHelper.floor(bb.minZ);
        int k3 = MathHelper.ceil(bb.maxZ);

        boolean flag = false;
        Vec3d vec3d = Vec3d.ZERO;
        BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain();

        for (int l3 = j2; l3 < k2; ++l3)
        {
            for (int i4 = l2; i4 < i3; ++i4)
            {
                for (int j4 = j3; j4 < k3; ++j4)
                {
                    blockpos$pooledmutableblockpos.setPos(l3, i4, j4);
                    IBlockState iblockstate1 = parWorld.getBlockState(blockpos$pooledmutableblockpos);
                    Block block = iblockstate1.getBlock();

                    Boolean result = block.isEntityInsideMaterial(parWorld, blockpos$pooledmutableblockpos, iblockstate1, entityIn, i3, materialIn, false);
                    if (result != null && result == true)
                    {
                        // Forge: When requested call blocks modifyAcceleration method, and more importantly cause this method to return true, which results in an entity being "inWater"
                        flag = true;
                        vec3d = block.modifyAcceleration(parWorld, blockpos$pooledmutableblockpos, entityIn, vec3d);
                     
//	                        // DEBUG
//	                     System.out.println("Entity is inside material = "+materialIn+" and motion add vector = "+vec3d);
                     
                        continue;
                    }
                    else if (result != null && result == false) continue;

                    if (iblockstate1.getMaterial() == materialIn)
                    {
//	                     // DEBUG
//	                     System.out.println("blockstate material matches material in");
                     
                        double d0 = i4 + 1 - BlockLiquid.getLiquidHeightPercent(iblockstate1.getValue(BlockLiquid.LEVEL).intValue());

                        if (i3 >= d0)
                        {
                         flag = true;
                         vec3d = block.modifyAcceleration(parWorld, blockpos$pooledmutableblockpos, entityIn, vec3d);
                         
//	                            // DEBUG
//	                         System.out.println("deep enough to push entity and motion add = "+vec3d);                 
                         }
                    }
                }
            }
        }

        blockpos$pooledmutableblockpos.release();

        if (vec3d.lengthVector() > 0.0D && entityIn.isPushedByWater())
        {
//	         // DEBUG
//	         System.out.println("motion vector is non-zero");
         
         /*
          * Although applied to all entities, EntityPlayer doesn't really take
          * affect, so the fluid motion control is handled in the client-side
          * PlayerTickEvent
          */
            vec3d = vec3d.normalize();
            double d1 = 0.014D;
            entityIn.motionX += vec3d.x * d1;
            entityIn.motionY += vec3d.y * d1;
            entityIn.motionZ += vec3d.z * d1;
        }
        else
        {
//	             // DEBUG
//	             System.out.println("motion vector is zero");
        }
     
        entityIn.fallDistance = 0.0F;

        return flag;
    }
}
