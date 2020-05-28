package minestrapp.block;

import java.util.List;
import java.util.Queue;
import java.util.Random;

import com.google.common.collect.Lists;

import minestrapp.MItems;
import minestrapp.MTabs;
import minestrapp.block.item.IMetaBlockName;
import minestrapp.block.util.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLavaSponge extends BlockBase implements IMetaBlockName
{
	public static final PropertyBool WET = PropertyBool.create("wet");
	
	public BlockLavaSponge()
	{
		super("lava_sponge", Material.SPONGE, MapColor.BLACK_STAINED_HARDENED_CLAY, SoundType.PLANT, 0.8F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(WET, Boolean.valueOf(false)));
		this.setCreativeTab(MTabs.utility);
	}
	
	public int damageDropped(IBlockState state)
    {
        return ((Boolean)state.getValue(WET)).booleanValue() ? 1 : 0;
    }
	
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> tab)
    {
        tab.add(new ItemStack(this, 1, 0));
        tab.add(new ItemStack(this, 1, 1));
    }
	
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(WET, Boolean.valueOf((meta & 1) == 1));
    }
	
	public int getMetaFromState(IBlockState state)
    {
        return ((Boolean)state.getValue(WET)).booleanValue() ? 1 : 0;
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {WET});
    }
    
    @Override
	public String getSpecialName(ItemStack stack)
	{
		return stack.getMetadata() == 0 ? "dry" : "wet";
	}
    
    @Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
	}
    
    @Deprecated
    public int getLightValue(IBlockState state)
    {
        return ((Boolean)state.getValue(WET)).booleanValue() ? 10 : 0;
    }
    
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        this.tryAbsorb(worldIn, pos, state);
    }
    
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        this.tryAbsorb(worldIn, pos, state);
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
    }
    
    protected void tryAbsorb(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!((Boolean)state.getValue(WET)).booleanValue() && this.absorb(worldIn, pos))
        {
            worldIn.setBlockState(pos, state.withProperty(WET, Boolean.valueOf(true)), 2);
            worldIn.playEvent(2001, pos, Block.getIdFromBlock(Blocks.LAVA));
        }
        else if(((Boolean)state.getValue(WET)).booleanValue())
        {
        	Material north = worldIn.getBlockState(pos.north()).getMaterial();
        	Material south = worldIn.getBlockState(pos.south()).getMaterial();
        	Material east = worldIn.getBlockState(pos.east()).getMaterial();
        	Material west = worldIn.getBlockState(pos.west()).getMaterial();
        	Material up = worldIn.getBlockState(pos.up()).getMaterial();
        	
        	if(isWet(north) || isWet(south) || isWet(east) || isWet(west) || isWet(up))
        	{
        		worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);

                if (worldIn instanceof WorldServer)
                {
                    ((WorldServer)worldIn).spawnParticle(EnumParticleTypes.SMOKE_LARGE, (double)pos.getX() + 0.5D, (double)pos.getY() + 1.25D, (double)pos.getZ() + 0.5D, 8, 0.5D, 0.25D, 0.5D, 0.0D);
                }
                
        		worldIn.setBlockState(pos, this.getDefaultState().withProperty(WET, Boolean.valueOf(false)));
        		int count = worldIn.rand.nextInt(4);
        		
        		for(int i = 0 ; i < count ; i++)
        		{
        			EntityItem chunk = new EntityItem(worldIn, pos.getX() + worldIn.rand.nextDouble(), pos.getY() + 1, pos.getZ() + worldIn.rand.nextDouble(), new ItemStack(MItems.chunks, 1, 1));
        			worldIn.spawnEntity(chunk);
        		}
        	}
        }
    }
    
    public boolean isWet(Material material)
    {
    	if(material == Material.WATER)
    		return true;
    	return false;
    }
    
    private boolean absorb(World worldIn, BlockPos pos)
    {
        Queue<Tuple<BlockPos, Integer>> queue = Lists.<Tuple<BlockPos, Integer>>newLinkedList();
        List<BlockPos> list = Lists.<BlockPos>newArrayList();
        queue.add(new Tuple(pos, Integer.valueOf(0)));
        int i = 0;

        while (!queue.isEmpty())
        {
            Tuple<BlockPos, Integer> tuple = (Tuple)queue.poll();
            BlockPos blockpos = tuple.getFirst();
            int j = ((Integer)tuple.getSecond()).intValue();

            for (EnumFacing enumfacing : EnumFacing.values())
            {
                BlockPos blockpos1 = blockpos.offset(enumfacing);

                if (worldIn.getBlockState(blockpos1).getMaterial() == Material.LAVA)
                {
                    worldIn.setBlockState(blockpos1, Blocks.AIR.getDefaultState(), 2);
                    list.add(blockpos1);
                    ++i;

                    if (j < 6)
                    {
                        queue.add(new Tuple(blockpos1, j + 1));
                    }
                }
            }

            if (i > 64)
            {
                break;
            }
        }

        for (BlockPos blockpos2 : list)
        {
            worldIn.notifyNeighborsOfStateChange(blockpos2, Blocks.AIR, false);
        }

        return i > 0;
    }
    
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        if (((Boolean)stateIn.getValue(WET)).booleanValue())
        {
            EnumFacing enumfacing = EnumFacing.random(rand);

            if (enumfacing != EnumFacing.UP && !worldIn.getBlockState(pos.offset(enumfacing)).isTopSolid())
            {
                double d0 = (double)pos.getX();
                double d1 = (double)pos.getY();
                double d2 = (double)pos.getZ();

                if (enumfacing == EnumFacing.DOWN)
                {
                    d1 = d1 - 0.05D;
                    d0 += rand.nextDouble();
                    d2 += rand.nextDouble();
                }
                else
                {
                    d1 = d1 + rand.nextDouble() * 0.8D;

                    if (enumfacing.getAxis() == EnumFacing.Axis.X)
                    {
                        d2 += rand.nextDouble();

                        if (enumfacing == EnumFacing.EAST)
                        {
                            ++d0;
                        }
                        else
                        {
                            d0 += 0.05D;
                        }
                    }
                    else
                    {
                        d0 += rand.nextDouble();

                        if (enumfacing == EnumFacing.SOUTH)
                        {
                            ++d2;
                        }
                        else
                        {
                            d2 += 0.05D;
                        }
                    }
                }

                worldIn.spawnParticle(EnumParticleTypes.DRIP_LAVA, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}
