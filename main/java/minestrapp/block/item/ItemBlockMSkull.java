package minestrapp.block.item;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Predicates;
import com.mojang.authlib.GameProfile;

import minestrapp.block.BlockMSkull;
import minestrapp.block.tileentity.TileEntityMSkull;
import minestrapp.item.util.ItemBase;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.state.IBlockState;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlockMSkull extends MItemBlock
{
	protected final Block block;
	
	public ItemBlockMSkull(Block block)
	{
		super(block);
		this.block = block;
	}
	
	@Nullable
    public EntityEquipmentSlot getEquipmentSlot(ItemStack stack)
    {
        return EntityEquipmentSlot.HEAD;
    }
	
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (facing == EnumFacing.DOWN)
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            if (worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos))
            {
                facing = EnumFacing.UP;
                pos = pos.down();
            }
            IBlockState iblockstate = worldIn.getBlockState(pos);
            Block block = iblockstate.getBlock();
            boolean flag = block.isReplaceable(worldIn, pos);

            if (!flag)
            {
                if (!worldIn.getBlockState(pos).getMaterial().isSolid() && !worldIn.isSideSolid(pos, facing, true))
                {
                    return EnumActionResult.FAIL;
                }

                pos = pos.offset(facing);
            }

            ItemStack itemstack = player.getHeldItem(hand);

            if (player.canPlayerEdit(pos, facing, itemstack) && block.canPlaceBlockAt(worldIn, pos))
            {
                /*if (worldIn.isRemote)
                {
                    return EnumActionResult.SUCCESS;
                }
                else
                {*/
                	boolean wall = facing.getAxis().isHorizontal();
                    worldIn.setBlockState(pos, this.block.getDefaultState().withProperty(BlockMSkull.FACING, player.getHorizontalFacing()).withProperty(BlockMSkull.WALL, wall), 11);
                    int i = 0;
                    BlockMSkull.EnumAngle angle = BlockMSkull.EnumAngle.ANGLE0;
                    //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
                    /*
                    if (!wall)
                    {
                        i = MathHelper.floor((double)(player.rotationYaw * 16.0F / 360.0F) + 0.5D) & 15;
                        
                        if(i < 0)
                        	i = 16 + i;
                        
                        if(i >= 4)
                        	i -= 4;
                        else if(i >= 8)
                        	i -= 8;
                        else if(i >= 12)
                        	i -= 12;
                        
                        if(i < 1)
                        	angle = BlockMSkull.EnumAngle.ANGLE0;
                        else if(i < 2)
                        	angle = BlockMSkull.EnumAngle.ANGLE1;
                        else if(i < 3)
                        	angle = BlockMSkull.EnumAngle.ANGLE2;
                        else
                        	angle = BlockMSkull.EnumAngle.ANGLE3;
                    }

                    TileEntity tileentity = worldIn.getTileEntity(pos);
                    
                    if (tileentity instanceof TileEntityMSkull)
                    {
                        TileEntityMSkull tileentityskull = (TileEntityMSkull)tileentity;

                        tileentityskull.setAngle(angle);
                    }*/

                    if (player instanceof EntityPlayerMP)
                    {
                        CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, itemstack);
                    }

                    itemstack.shrink(1);
                    return EnumActionResult.SUCCESS;
                //}
            }
            else
            {
                return EnumActionResult.FAIL;
            }
        }
    }
	/*
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState)
    {
        if (!world.setBlockState(pos, newState, 11)) return false;

        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() == this.block)
        {
        	setSkullNBT(world, player, pos, stack);
            setTileEntityNBT(world, player, pos, stack);
            this.block.onBlockPlacedBy(world, pos, state, player, stack);

            if (player instanceof EntityPlayerMP)
                CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, stack);
        }

        return true;
    }
	
	public static boolean setSkullNBT(World worldIn, EntityPlayer player, BlockPos pos, ItemStack stackIn)
	{
		MinecraftServer minecraftserver = worldIn.getMinecraftServer();

        if (minecraftserver == null)
        {
            return false;
        }
        else
        {
        	int i = 0;
            BlockMSkull.EnumAngle angle = BlockMSkull.EnumAngle.ANGLE2;
            
            i = MathHelper.floor((double)(player.rotationYaw * 16.0F / 360.0F) + 0.5D) & 15;
            
            if(i / 4 < 1)
            	angle = BlockMSkull.EnumAngle.ANGLE0;
            else if(i / 4 < 2)
            	angle = BlockMSkull.EnumAngle.ANGLE1;
            else if(i / 4 < 3)
            	angle = BlockMSkull.EnumAngle.ANGLE2;
            else
            	angle = BlockMSkull.EnumAngle.ANGLE3;

            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity != null)
            {
                if (!worldIn.isRemote && tileentity.onlyOpsCanSetNbt() && (player == null || !player.canUseCommandBlock()))
                {
                    return false;
                }

                if(tileentity instanceof TileEntityMSkull)
                {
                	((TileEntityMSkull)tileentity).setAngle(angle);
                }
//                NBTTagCompound nbttagcompound1 = tileentity.writeToNBT(new NBTTagCompound());
//                NBTTagCompound nbttagcompound2 = nbttagcompound1.copy();
//                nbttagcompound1.merge();
//                nbttagcompound1.setInteger("x", pos.getX());
//                nbttagcompound1.setInteger("y", pos.getY());
//                nbttagcompound1.setInteger("z", pos.getZ());
//
//                if (!nbttagcompound1.equals(nbttagcompound2))
//                {
//                    tileentity.readFromNBT(nbttagcompound1);
//                    tileentity.markDirty();
//                    return true;
//                }
            }
            
            return false;
		
        }
	}*/
}
