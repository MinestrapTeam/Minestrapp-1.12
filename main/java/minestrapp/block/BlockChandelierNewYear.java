package minestrapp.block;

import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import minestrapp.MBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockChandelierNewYear extends BlockChandelier
{
	public static final PropertyEnum<BlockChandelierNewYear.EnumColor> COLOR = PropertyEnum.<BlockChandelierNewYear.EnumColor>create("color", BlockChandelierNewYear.EnumColor.class);
	
	public BlockChandelierNewYear(String name, String type)
	{
		super(name, Material.IRON, MapColor.GOLD, SoundType.METAL, 3F, "pickaxe", 0, type, MBlocks.chandelier_new_year, MBlocks.chandelier_new_year_fire, MBlocks.chandelier_new_year_ender);
	
		this.setDefaultState(this.blockState.getBaseState().withProperty(COLOR, BlockChandelierNewYear.EnumColor.GRAY));
		this.setHitboxDimensions(6, 13, 3, 20, 5, -2);
	}
	
	public int damageDropped(IBlockState state)
    {
        return ((BlockChandelierNewYear.EnumColor)state.getValue(COLOR)).getMetadata();
    }
	
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        for (BlockChandelierNewYear.EnumColor enumdyecolor : BlockChandelierNewYear.EnumColor.values())
        {
            items.add(new ItemStack(this, 1, enumdyecolor.getMetadata()));
        }
    }
	
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(COLOR, BlockChandelierNewYear.EnumColor.byMetadata(meta));
    }
	
	public int getMetaFromState(IBlockState state)
    {
        return ((BlockChandelierNewYear.EnumColor)state.getValue(COLOR)).getMetadata();
    }
	
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {COLOR});
    }
	
	@Override
	public String getSpecialName(ItemStack stack)
	{
		return BlockChandelierNewYear.EnumColor.byMetadata(stack.getMetadata()).getName();
	}
	
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		ItemStack itemstack = playerIn.getHeldItem(hand);
		
		if (!playerIn.canPlayerEdit(pos.offset(facing), facing, itemstack))
        {
            return false;
        }
		else if(this.getType() == "unlit" && itemstack.getItem() instanceof ItemFlintAndSteel)
		{
			if(!worldIn.isRemote)
			{
				worldIn.setBlockState(pos, this.getReactionBlock("fire").getDefaultState().withProperty(COLOR, worldIn.getBlockState(pos).getValue(COLOR)));
				itemstack.damageItem(1, playerIn);
			}
			return true;
		}
		else if(this.getType() == "unlit" && itemstack.getItem() == Items.DRAGON_BREATH)
		{
			if(!worldIn.isRemote)
			{
				worldIn.setBlockState(pos, this.getReactionBlock("ender").getDefaultState().withProperty(COLOR, worldIn.getBlockState(pos).getValue(COLOR)));
				itemstack.shrink(1);
				worldIn.spawnEntity(new EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, new ItemStack(Items.GLASS_BOTTLE, 1)));
			}
			return true;
		}
		else if((this.getType() == "fire" || this.getType() == "ender") && itemstack.getItem() == Items.POTIONITEM)
		{
			if(!worldIn.isRemote)
			{
				worldIn.setBlockState(pos, this.getReactionBlock("unlit").getDefaultState().withProperty(COLOR, worldIn.getBlockState(pos).getValue(COLOR)));
				itemstack.shrink(1);
				worldIn.spawnEntity(new EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, new ItemStack(Items.GLASS_BOTTLE, 1)));
			}
			return true;
		}
		
		return false;
    }

	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        double x0 = (double)pos.getX();
        double x1 = (double)pos.getX() + 0.5D;
        double x2 = (double)pos.getX() + 1D;
        double y = (double)pos.getY() + 0.5625D;
        double z0 = (double)pos.getZ();
        double z1 = (double)pos.getZ() + 0.5D;
        double z2 = (double)pos.getZ() + 1D;

        if(this.getType() == "fire")
        {
        	worldIn.spawnParticle(EnumParticleTypes.FLAME, true, x0, y, z1, 0.0D, 0.0D, 0.0D);
        	worldIn.spawnParticle(EnumParticleTypes.FLAME, true, x1, y, z0, 0.0D, 0.0D, 0.0D);
        	worldIn.spawnParticle(EnumParticleTypes.FLAME, true, x1, y, z2, 0.0D, 0.0D, 0.0D);
        	worldIn.spawnParticle(EnumParticleTypes.FLAME, true, x2, y, z1, 0.0D, 0.0D, 0.0D);
        }
        else if(this.getType() == "ender")
        {
        	worldIn.spawnParticle(EnumParticleTypes.REDSTONE, true, x0, y, z1, 1.0D, 0.0D, 1.0D);
	        worldIn.spawnParticle(EnumParticleTypes.REDSTONE, true, x1, y, z0, 1.0D, 0.0D, 1.0D);
	        worldIn.spawnParticle(EnumParticleTypes.REDSTONE, true, x1, y, z2, 1.0D, 0.0D, 1.0D);
	        worldIn.spawnParticle(EnumParticleTypes.REDSTONE, true, x2, y, z1, 1.0D, 0.0D, 1.0D);
        }
    }
	
	public static enum EnumColor implements IStringSerializable
    {
        GRAY(0, "gray", MapColor.GRAY),
        RED(1, "red", MapColor.RED),
        CYAN(2, "cyan", MapColor.CYAN),
        PURPLE(3, "purple", MapColor.PURPLE);

        private static final BlockChandelierNewYear.EnumColor[] META_LOOKUP = new BlockChandelierNewYear.EnumColor[values().length];
        private final int meta;
        private final String name;
        private final String unlocalizedName;
        private final MapColor mapColor;

        private EnumColor(int metaIn, String nameIn, MapColor mapColorIn)
        {
            this(metaIn, nameIn, nameIn, mapColorIn);
        }

        private EnumColor(int metaIn, String nameIn, String unlocalizedNameIn, MapColor mapColorIn)
        {
            this.meta = metaIn;
            this.name = nameIn;
            this.unlocalizedName = unlocalizedNameIn;
            this.mapColor = mapColorIn;
        }

        public int getMetadata()
        {
            return this.meta;
        }

        public MapColor getMapColor()
        {
            return this.mapColor;
        }

        public String toString()
        {
            return this.name;
        }

        public static BlockChandelierNewYear.EnumColor byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        public String getName()
        {
            return this.name;
        }

        public String getUnlocalizedName()
        {
            return this.unlocalizedName;
        }

        static
        {
            for (BlockChandelierNewYear.EnumColor blockplanks$enumtype : values())
            {
                META_LOOKUP[blockplanks$enumtype.getMetadata()] = blockplanks$enumtype;
            }
        }
    }
}
