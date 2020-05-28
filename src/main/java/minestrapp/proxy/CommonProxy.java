package minestrapp.proxy;

import minestrapp.MBlocks;
import minestrapp.MFluids;
import minestrapp.MItems;
import minestrapp.Minestrapp;
import minestrapp.block.tileentity.TileEntityActivator;
import minestrapp.block.tileentity.TileEntityAlloy;
import minestrapp.block.tileentity.TileEntityAxel;
import minestrapp.block.tileentity.TileEntityBarrel;
import minestrapp.block.tileentity.TileEntityCrate;
import minestrapp.block.tileentity.TileEntityCrusher;
import minestrapp.block.tileentity.TileEntityMSkull;
import minestrapp.block.tileentity.TileEntityMagnetPiston;
import minestrapp.block.tileentity.TileEntityPipe;
import minestrapp.block.tileentity.TileEntityPlate;
import minestrapp.block.tileentity.TileEntityPressurizer;
import minestrapp.block.tileentity.TileEntitySawmill;
import minestrapp.block.tileentity.TileEntitySorter;
import minestrapp.block.tileentity.TileEntityStoneCutter;
import minestrapp.block.tileentity.TileEntityTanningRack;
import minestrapp.block.tileentity.TileEntityVessel;
import minestrapp.crafting.FurnaceRecipes;
import minestrapp.crafting.OreDictRegistry;
import minestrapp.event.MEventHandler;
import minestrapp.gui.MGuiHandler;
import minestrapp.potion.MPotions;
import minestrapp.worldgen.MOreGen;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy
{
	public void preInit(FMLPreInitializationEvent event)
	{
		MFluids.init();
		MBlocks.init();
		MItems.init();
		MPotions.addBrewingRecipe();
		OreDictRegistry.register();
		FurnaceRecipes.register();
	}
	
	public void init(FMLInitializationEvent event)
	{
		GameRegistry.registerWorldGenerator(new MOreGen(), 0);
		
		GameRegistry.registerTileEntity(TileEntityAxel.class, new ResourceLocation(Minestrapp.MODID, "TileEntityAxel"));
		GameRegistry.registerTileEntity(TileEntityVessel.class, new ResourceLocation(Minestrapp.MODID, "TileEntityVessel"));
		GameRegistry.registerTileEntity(TileEntityCrate.class, new ResourceLocation(Minestrapp.MODID, "TileEntityCrate"));
		GameRegistry.registerTileEntity(TileEntityBarrel.class, new ResourceLocation(Minestrapp.MODID, "TileEntityBarrel"));
		GameRegistry.registerTileEntity(TileEntityStoneCutter.class, new ResourceLocation(Minestrapp.MODID, "TileEntityStoneCutter"));
		GameRegistry.registerTileEntity(TileEntityAlloy.class, new ResourceLocation(Minestrapp.MODID, "TileEntityAlloy"));
		GameRegistry.registerTileEntity(TileEntityCrusher.class, new ResourceLocation(Minestrapp.MODID, "TileEntityCrusher"));
		GameRegistry.registerTileEntity(TileEntityMagnetPiston.class, new ResourceLocation(Minestrapp.MODID, "TileEntityMagnetPiston"));
		GameRegistry.registerTileEntity(TileEntityPipe.class, new ResourceLocation(Minestrapp.MODID, "TileEntityPipe"));
		GameRegistry.registerTileEntity(TileEntitySorter.class, new ResourceLocation(Minestrapp.MODID, "TileEntitySorter"));
		GameRegistry.registerTileEntity(TileEntityTanningRack.class, new ResourceLocation(Minestrapp.MODID, "TileEntityTanningRack"));
		GameRegistry.registerTileEntity(TileEntityPlate.class, new ResourceLocation(Minestrapp.MODID, "TileEntityPlate"));
		GameRegistry.registerTileEntity(TileEntityMSkull.class, new ResourceLocation(Minestrapp.MODID, "TileEntityMSkull"));
		GameRegistry.registerTileEntity(TileEntityActivator.class, new ResourceLocation(Minestrapp.MODID, "TileEntityActivator"));
		GameRegistry.registerTileEntity(TileEntityPressurizer.class, new ResourceLocation(Minestrapp.MODID, "TileEntityPressurizer"));
		GameRegistry.registerTileEntity(TileEntitySawmill.class, new ResourceLocation(Minestrapp.MODID, "TileEntitySawmill"));
		
		NetworkRegistry.INSTANCE.registerGuiHandler(Minestrapp.instance, new MGuiHandler());
		
		MinecraftForge.EVENT_BUS.register(new MEventHandler());
		MinecraftForge.EVENT_BUS.register(new MPotions());
	}
	
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
	
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
                     
//                        // DEBUG
//                     System.out.println("Entity is inside material = "+materialIn+" and motion add vector = "+vec3d);
                     
                        continue;
                    }
                    else if (result != null && result == false) continue;

                    if (iblockstate1.getMaterial() == materialIn)
                    {
//                     // DEBUG
//                     System.out.println("blockstate material matches material in");
                     
                        double d0 = i4 + 1 - BlockLiquid.getLiquidHeightPercent(iblockstate1.getValue(BlockLiquid.LEVEL).intValue());

                        if (i3 >= d0)
                        {
                         flag = true;
                         vec3d = block.modifyAcceleration(parWorld, blockpos$pooledmutableblockpos, entityIn, vec3d);
                         
//                            // DEBUG
//                         System.out.println("deep enough to push entity and motion add = "+vec3d);                 
                         }
                    }
                }
            }
        }

        blockpos$pooledmutableblockpos.release();

        if (vec3d.lengthVector() > 0.0D && entityIn.isPushedByWater())
        {
//         // DEBUG
//         System.out.println("motion vector is non-zero");
         
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
//             // DEBUG
//             System.out.println("motion vector is zero");
        }
     
        entityIn.fallDistance = 0.0F;

        return flag;
    }
}
