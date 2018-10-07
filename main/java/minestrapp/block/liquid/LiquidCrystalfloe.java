package minestrapp.block.liquid;

import javax.annotation.Nonnull;

import minestrapp.MBlocks;
import minestrapp.block.BlockShimmerstone;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class LiquidCrystalfloe extends BlockFluidClassic
{
	private static boolean pushesEntity;
    /**
     * @param parFluid: the fluid
     * @param parMaterial: the material
     */
    public LiquidCrystalfloe(Fluid parFluid, Material parMaterial)
    {
        super(parFluid, parMaterial);
        this.setPushesEntity(true);
    }

    /**
     * @see net.minecraftforge.fluids.BlockFluidClassic#place(net.minecraft.world.World, net.minecraft.util.math.BlockPos, net.minecraftforge.fluids.FluidStack, boolean)
     */
    @Override
    public int place(World world, BlockPos pos, @Nonnull FluidStack fluidStack, boolean doPlace)
    {

        if (doPlace)
        {
            FluidUtil.destroyBlockOnFluidPlacement(world, pos);
            world.setBlockState(pos, this.getDefaultState(), 11);
        }
        return fluidStack.amount;
    }

    /**
     * @see net.minecraftforge.fluids.BlockFluidBase#getFogColor(net.minecraft.world.World, net.minecraft.util.math.BlockPos, net.minecraft.block.state.IBlockState, net.minecraft.entity.Entity, net.minecraft.util.math.Vec3d, float)
     */
    @Override
    @SideOnly(Side.CLIENT)
    public Vec3d getFogColor(World world, BlockPos pos, IBlockState state, Entity entity, Vec3d originalColor, float partialTicks)
    {
        return new Vec3d(188, 213, 202);
    }

    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        this.checkForMixing(worldIn, pos, state);
        super.onBlockAdded(worldIn, pos, state);
    }
    
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        this.checkForMixing(worldIn, pos, state);
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
    }
    
    public boolean checkForMixing(World worldIn, BlockPos pos, IBlockState state)
    {
    	//1 = Adjacent Water, 2 = Above Water, 3 = Adjacent Lava, 4 = Above Lava
        int flag = 0;

        for (EnumFacing enumfacing : EnumFacing.values())
        {
            if (worldIn.getBlockState(pos.offset(enumfacing)).getMaterial() == Material.WATER)
            {
            	if(enumfacing != EnumFacing.DOWN)
            		flag = 1;
            	else
            		flag = 2;
                break;
            }
            
            if (worldIn.getBlockState(pos.offset(enumfacing)).getMaterial() == Material.LAVA)
            {
            	if(enumfacing != EnumFacing.DOWN)
            		flag = 1;
            	else
            		flag = 2;
                break;
            }
        }

        if (flag > 0)
        {
        	Integer integer = (Integer)state.getValue(LEVEL);
        	
        	if(flag == 1 || flag == 3)
        	{
        		if (integer.intValue() == 0)
                {
                    worldIn.setBlockState(pos, net.minecraftforge.event.ForgeEventFactory.fireFluidPlaceBlockEvent(worldIn, pos, pos, Blocks.OBSIDIAN.getDefaultState()));
                    this.triggerMixEffects(worldIn, pos);
                    return true;
                }

        		if (integer.intValue() <= 4)
                {
                    worldIn.setBlockState(pos, net.minecraftforge.event.ForgeEventFactory.fireFluidPlaceBlockEvent(worldIn, pos, pos, MBlocks.shimmerstone.getDefaultState().withProperty(BlockShimmerstone.VARIANT, BlockShimmerstone.ShimmerstoneType.COBBLESTONE)));
                    this.triggerMixEffects(worldIn, pos);
                    return true;
                }
        	}
        	else if(flag == 2 || flag == 4)
        	{
        		worldIn.setBlockState(pos.down(), net.minecraftforge.event.ForgeEventFactory.fireFluidPlaceBlockEvent(worldIn, pos.down(), pos, MBlocks.shimmerstone.getDefaultState()));
                this.triggerMixEffects(worldIn, pos.down());
                return true;
        	}
        }

        return false;
    }
    
    protected void triggerMixEffects(World worldIn, BlockPos pos)
    {
        double d0 = (double)pos.getX();
        double d1 = (double)pos.getY();
        double d2 = (double)pos.getZ();
        worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);

        for (int i = 0; i < 8; ++i)
        {
            worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0 + Math.random(), d1 + 1.2D, d2 + Math.random(), 0.0D, 0.0D, 0.0D);
        }
    }
    
    public static boolean getPushesEntity()
    {
     return pushesEntity;
    }

    public static void setPushesEntity(boolean parPushesEntity)
    {
     pushesEntity = parPushesEntity;
    }

    @Override
    public Vec3d modifyAcceleration(World worldIn, BlockPos pos, Entity entityIn, Vec3d motion)
       {
//        // DEBUG
//        System.out.println("modifyAcceleration for "+entityIn+" with isPushedByWater() = "+entityIn.isPushedByWater());
        
        if (getPushesEntity())
        {
         Vec3d flowAdder = getFlow(worldIn, pos, worldIn.getBlockState(pos));

//         // DEBUG
//         System.out.println("may push entity with motion adder = "+flowAdder);
         
      return motion.add(flowAdder);
        }
        else
        {
//         // DEBUG
//         System.out.println("may not push entity");
         
         return motion;
        }
       }

       protected Vec3d getFlow(IBlockAccess worldIn, BlockPos pos, IBlockState state)
       {
           double d0 = 0.0D;
           double d1 = 0.0D;
           double d2 = 0.0D;
           int i = this.getRenderedDepth(state);
           BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain();

           for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
           {
               blockpos$pooledmutableblockpos.setPos(pos).move(enumfacing);
               int j = this.getRenderedDepth(worldIn.getBlockState(blockpos$pooledmutableblockpos));

               if (j < 0)
               {
                   if (!worldIn.getBlockState(blockpos$pooledmutableblockpos).getMaterial().blocksMovement())
                   {
                       j = this.getRenderedDepth(worldIn.getBlockState(blockpos$pooledmutableblockpos.down()));

                       if (j >= 0)
                       {
                           int k = j - (i - 8);
                           d0 += enumfacing.getFrontOffsetX() * k;
                           d1 += enumfacing.getFrontOffsetY() * k;
                           d2 += enumfacing.getFrontOffsetZ() * k;
                       }
                   }
               }
               else if (j >= 0)
               {
                   int l = j - i;
                   d0 += enumfacing.getFrontOffsetX() * l;
                   d1 += enumfacing.getFrontOffsetY() * l;
                   d2 += enumfacing.getFrontOffsetZ() * l;
               }
           }

           Vec3d vec3d = new Vec3d(d0, d1, d2);

           if (state.getValue(LEVEL).intValue() >= 8)
           {
//            // DEBUG
//            System.out.println("fluid level greater than zero");
            
               for (EnumFacing enumfacing1 : EnumFacing.Plane.HORIZONTAL)
               {
                   blockpos$pooledmutableblockpos.setPos(pos).move(enumfacing1);

                   if (this.causesDownwardCurrent(worldIn, blockpos$pooledmutableblockpos, enumfacing1) || this.causesDownwardCurrent(worldIn, blockpos$pooledmutableblockpos.up(), enumfacing1))
                   {
//                    // DEBUG
//                    System.out.println("Causes downward current");
                    
                       vec3d = vec3d.normalize().addVector(0.0D, -6.0D, 0.0D);
                       break;
                   }
               }
           }

           blockpos$pooledmutableblockpos.release();
           return vec3d.normalize();
       }

       protected int getDepth(IBlockState state)
       {
           return state.getMaterial() == this.blockMaterial ? state.getValue(LEVEL).intValue() : -1;
       }

       protected int getRenderedDepth(IBlockState state)
       {
           int i = this.getDepth(state);
           return i >= 8 ? 0 : i;
       }

       /**
        * Checks if an additional {@code -6} vertical drag should be applied to the entity. See {#link
        * net.minecraft.block.BlockLiquid#getFlow()}
        */
       protected boolean causesDownwardCurrent(IBlockAccess worldIn, BlockPos pos, EnumFacing side)
       {
           IBlockState iblockstate = worldIn.getBlockState(pos);
           Block block = iblockstate.getBlock();
           Material material = iblockstate.getMaterial();

           if (material == this.blockMaterial)
           {
               return false;
           }
           else if (side == EnumFacing.UP)
           {
               return true;
           }
           else if (material == Material.ICE)
           {
               return false;
           }
           else
           {
               boolean flag = isExceptBlockForAttachWithPiston(block) || block instanceof BlockStairs;
               return !flag && iblockstate.getBlockFaceShape(worldIn, pos, side) == BlockFaceShape.SOLID;
           }
       }
}
