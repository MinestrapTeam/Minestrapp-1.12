package minestrapp.block;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import minestrapp.MBlocks;
import minestrapp.MItems;
import minestrapp.block.item.IMetaBlockName;
import minestrapp.block.util.BlockBase;
import minestrapp.utils.BlockUtil;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBasketCheesemaking extends BlockBasket implements IMetaBlockName
{
	public static final PropertyEnum<BlockBasketCheesemaking.EnumProgress> PROGRESS = PropertyEnum.<BlockBasketCheesemaking.EnumProgress>create("progress", BlockBasketCheesemaking.EnumProgress.class);
	
	protected static final AxisAlignedBB AABB_CORK_1 = BlockUtil.createBoundingBoxColumn(10, 3, 16);
	protected static final AxisAlignedBB AABB_CORK_2 = BlockUtil.createBoundingBoxColumn(10, 2, 16);
	protected static final AxisAlignedBB AABB_CORK_3 = BlockUtil.createBoundingBoxColumn(10, 1, 16);
	protected static final AxisAlignedBB AABB_CHEESE = BlockUtil.createBoundingBoxColumn(12, 12, 2);
	
	public BlockBasketCheesemaking()
	{
		super("basket_cheesemaking");
		this.setDefaultState(this.blockState.getBaseState().withProperty(PROGRESS, BlockBasketCheesemaking.EnumProgress.WAX));
		this.setTickRandomly(true);
	}
	
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
		BlockBasketCheesemaking.EnumProgress progress = state.getValue(PROGRESS);
		
		if(progress == BlockBasketCheesemaking.EnumProgress.CULTURING_SEALED)
			worldIn.scheduleUpdate(pos, state.getBlock(), 600);
		else if(progress == BlockBasketCheesemaking.EnumProgress.FORMING)
			worldIn.scheduleUpdate(pos, state.getBlock(), 600);
    }
	
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		BlockBasketCheesemaking.EnumProgress progress = state.getValue(PROGRESS);
		ItemStack stack = playerIn.getHeldItem(hand);
		Item item = stack.getItem();
		
		if(progress == BlockBasketCheesemaking.EnumProgress.WAX && item == Items.MILK_BUCKET)
		{
			stack.shrink(1);
			if(!worldIn.isRemote)
				worldIn.spawnEntity(new EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, new ItemStack(Items.BUCKET)));
			worldIn.setBlockState(pos, state.withProperty(PROGRESS, BlockBasketCheesemaking.EnumProgress.MILK));
			playerIn.playSound(SoundEvents.ITEM_BUCKET_EMPTY, 1.0F, 1.0F);
			return true;
		}
		if(progress == BlockBasketCheesemaking.EnumProgress.MILK && item == MItems.tannic && stack.getMetadata() == 3)
		{
			stack.shrink(1);
			if(!worldIn.isRemote)
				worldIn.spawnEntity(new EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, new ItemStack(Items.GLASS_BOTTLE)));
			worldIn.setBlockState(pos, state.withProperty(PROGRESS, BlockBasketCheesemaking.EnumProgress.CULTURING_UNSEALED));
			playerIn.playSound(SoundEvents.ENTITY_GENERIC_SPLASH, 1, 1);
			return true;
		}
		if(progress == BlockBasketCheesemaking.EnumProgress.CULTURING_UNSEALED && item == Item.getItemFromBlock(MBlocks.cork))
		{
			stack.shrink(1);
			worldIn.setBlockState(pos, state.withProperty(PROGRESS, BlockBasketCheesemaking.EnumProgress.CULTURING_SEALED));
			playerIn.playSound(SoundEvents.BLOCK_WOOD_PLACE, 1, 1);
			return true;
		}
		if(progress == BlockBasketCheesemaking.EnumProgress.CURDS_SEALED)
		{
			if(!worldIn.isRemote)
				worldIn.spawnEntity(new EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, new ItemStack(MBlocks.cork)));
			worldIn.setBlockState(pos, state.withProperty(PROGRESS, BlockBasketCheesemaking.EnumProgress.CURDS_UNSEALED));
			playerIn.playSound(SoundEvents.BLOCK_WOOD_PLACE, 1, 0.6F);
			return true;
		}
		if(progress == BlockBasketCheesemaking.EnumProgress.CURDS_UNSEALED && item == MItems.fat)
		{
			stack.shrink(1);
			worldIn.setBlockState(pos, state.withProperty(PROGRESS, BlockBasketCheesemaking.EnumProgress.FAT));
			playerIn.playSound(SoundEvents.ENTITY_GENERIC_SPLASH, 1, 1);
			return true;
		}
		if(progress == BlockBasketCheesemaking.EnumProgress.FAT && item == MItems.salt)
		{
			stack.shrink(1);
			worldIn.setBlockState(pos, state.withProperty(PROGRESS, BlockBasketCheesemaking.EnumProgress.SALT));
			playerIn.playSound(SoundEvents.ENTITY_GENERIC_SPLASH, 1, 1);
			return true;
		}
		if(progress == BlockBasketCheesemaking.EnumProgress.SALT && item == Item.getItemFromBlock(MBlocks.cork))
		{
			stack.shrink(1);
			worldIn.setBlockState(pos, state.withProperty(PROGRESS, BlockBasketCheesemaking.EnumProgress.FORMING));
			playerIn.playSound(SoundEvents.BLOCK_WOOD_PLACE, 1, 1);
			return true;
		}
		if(progress == BlockBasketCheesemaking.EnumProgress.DRAINING_4)
		{
			if(!worldIn.isRemote)
				worldIn.spawnEntity(new EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, new ItemStack(MBlocks.cork)));
			worldIn.setBlockState(pos, state.withProperty(PROGRESS, BlockBasketCheesemaking.EnumProgress.CHEESE));
			playerIn.playSound(SoundEvents.BLOCK_WOOD_PLACE, 1, 0.6F);
			return true;
		}
		if(progress == BlockBasketCheesemaking.EnumProgress.CHEESE)
		{
			if(!worldIn.isRemote)
				worldIn.spawnEntity(new EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, new ItemStack(MBlocks.block_cheese)));
			worldIn.setBlockState(pos, MBlocks.basket.getDefaultState());
			playerIn.playSound(SoundEvents.BLOCK_CLOTH_BREAK, 1, 0.6F);
			return true;
		}
			
		return false;
    }
	
	public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance)
    {
		BlockBasketCheesemaking.EnumProgress progress = worldIn.getBlockState(pos).getValue(PROGRESS);
		
    	if(progress.getMetadata() > 8 && progress.getMetadata() < 12 && !worldIn.isRemote)
    	{
    		worldIn.setBlockState(pos, MBlocks.basket_cheesemaking.getStateFromMeta(progress.getMetadata() + 1));
    		
    		float f = (float)MathHelper.ceil(fallDistance - 3.0F);
    		double d0 = Math.min((double)(0.2F + f / 15.0F), 2.5D);
            int i = (int)(150.0D * d0);
            
    		((WorldServer)worldIn).spawnParticle(EnumParticleTypes.BLOCK_DUST, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, i, 0.0D, 0.0D, 0.0D, 0.15000000596046448D, Block.getStateId(Blocks.WATER.getDefaultState()));
    		((WorldServer)worldIn).playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_GENERIC_SPLASH, SoundCategory.BLOCKS, 1, 1, false);
    	}
    	else
    		super.onFallenUpon(worldIn, pos, entityIn, fallDistance);
    }
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
		if(rand.nextInt(100) < 8)
		{
			if(state.getValue(PROGRESS) == BlockBasketCheesemaking.EnumProgress.CULTURING_SEALED)
			{
				worldIn.setBlockState(pos, state.withProperty(PROGRESS, BlockBasketCheesemaking.EnumProgress.CURDS_SEALED));
				worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_BREWING_STAND_BREW, SoundCategory.BLOCKS, 1, 1, false);
			}
			else if(state.getValue(PROGRESS) == BlockBasketCheesemaking.EnumProgress.FORMING)
			{
				worldIn.setBlockState(pos, state.withProperty(PROGRESS, BlockBasketCheesemaking.EnumProgress.DRAINING_1));
				worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_BREWING_STAND_BREW, SoundCategory.BLOCKS, 1, 1, false);
			}
		}
    }
	
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand)
    {
		BlockBasketCheesemaking.EnumProgress progress = state.getValue(PROGRESS);
		
        if (progress.getMetadata() > 0 && progress.getMetadata() < 13)
            this.spawnWaterParticles(worldIn, pos, rand, progress.getMetadata() == 12 ? true : false);
        if (progress == BlockBasketCheesemaking.EnumProgress.CULTURING_UNSEALED || progress == BlockBasketCheesemaking.EnumProgress.CULTURING_SEALED || progress == BlockBasketCheesemaking.EnumProgress.FORMING)
        	this.spawnProcessingParticles(worldIn, pos, rand);
    }
	
	public void spawnWaterParticles(World worldIn, BlockPos pos, Random random, boolean swol)
	{
		EnumParticleTypes particle = EnumParticleTypes.DRIP_WATER;
		float offsetPatch = 0.05F;
		
		if(swol)
			offsetPatch = 0.2F;
		
		for (int i = 0; i < 3; i++)
		{
			float x1 = pos.getX() + 0.5F;
			float y1 = pos.getY() + random.nextFloat() * 0.9F - 0.2F;
			float z1 = pos.getZ() + 0.5F;
			float f = random.nextFloat() * 0.9F - 0.3F;
			float x2 = x1 + f;
			float z2 = z1 + f;

			if(random.nextInt(10) == 1)
				worldIn.spawnParticle(particle, x1 - 0.56F, y1, z2, 0.0D, 0.0D, 0.0D);
			if(random.nextInt(10) == 1)
				worldIn.spawnParticle(particle, x1 + 0.52F + offsetPatch, y1, z2, 0.0D, 0.0D, 0.0D);
			if(random.nextInt(10) == 1)
				worldIn.spawnParticle(particle, x2, y1, z1 - 0.56F, 0.0D, 0.0D, 0.0D);
			if(random.nextInt(10) == 1)
				worldIn.spawnParticle(particle, x2, y1, z1 + 0.52F + offsetPatch, 0.0D, 0.0D, 0.0D);
		}
	}
	
	public void spawnProcessingParticles(World worldIn, BlockPos pos, Random random)
	{
		if(random.nextInt() * 100 < 8)
		worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_BREWING_STAND_BREW, SoundCategory.BLOCKS, 0.1F, 0.1F, false);
		EnumParticleTypes particle = EnumParticleTypes.SPELL;
		
		for (int i = 0; i < 3; i++)
		{
			float x1 = pos.getX() + 0.5F;
			float y1 = pos.getY() + 1.1F;
			float z1 = pos.getZ() + 0.5F;
			float f = random.nextFloat() * 0.5F - 0.25F;
			float x2 = x1 + f;
			float z2 = z1 + f;

			if(random.nextInt(10) == 1)
				worldIn.spawnParticle(particle, x1 - 0.26F, y1, z2, 0.0D, 0.0D, 0.0D);
			if(random.nextInt(10) == 1)
				worldIn.spawnParticle(particle, x1 + 0.26F, y1, z2, 0.0D, 0.0D, 0.0D);
			if(random.nextInt(10) == 1)
				worldIn.spawnParticle(particle, x2, y1, z1 - 0.26F, 0.0D, 0.0D, 0.0D);
			if(random.nextInt(10) == 1)
				worldIn.spawnParticle(particle, x2, y1, z1 + 0.26F, 0.0D, 0.0D, 0.0D);
		}
	}
	
	public void spawnSplashParticles(World worldIn, BlockPos pos)
	{
		
	}

	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean p_185477_7_)
    {
		int meta = state.getValue(PROGRESS).getMetadata();
		boolean sealed = state.getValue(PROGRESS).isSealed();
		
		if(sealed)
		{
			if(meta < 10)
				addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_CORK_1);
			else if(meta == 10)
				addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_CORK_2);
			else if(meta == 11)
				addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_CORK_3);
			
			addCollisionBoxToList(pos, entityBox, collidingBoxes, FULL_BLOCK_AABB);
		}
		else
		{
			if(meta == 13)
				addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_CHEESE);
			
			super.addCollisionBoxToList(state, worldIn, pos, entityBox, collidingBoxes, entityIn, p_185477_7_);
		}
    }
	
	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
		super.getDrops(drops, world, pos, state, fortune);
		
        if(state.getValue(PROGRESS).sealed)
        	drops.add(new ItemStack(MBlocks.cork));
    }
	
	public int damageDropped(IBlockState state)
    {
        return 0;
    }
	
	/*
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> tab)
    {
        for (BlockBasketCheesemaking.EnumProgress progress : BlockBasketCheesemaking.EnumProgress.values())
        {
            tab.add(new ItemStack(this, 1, progress.getMetadata()));
        }
    }
    */
	
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(PROGRESS, BlockBasketCheesemaking.EnumProgress.byMetadata(meta));
    }
	
	public int getMetaFromState(IBlockState state)
    {
        return ((BlockBasketCheesemaking.EnumProgress)state.getValue(PROGRESS)).getMetadata();
    }
	
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {PROGRESS});
    }
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(MBlocks.basket_cheesemaking, 1, 0);
	}
	
	@Override
	public String getSpecialName(ItemStack stack)
	{
		return BlockBasketCheesemaking.EnumProgress.byMetadata(stack.getMetadata()).getName();
	}
	
	public static enum EnumProgress implements IStringSerializable
    {
        WAX(0, "wax", false),
        MILK(1, "milk", false),
        CULTURING_UNSEALED(2, "culturing_unsealed", false),
        CULTURING_SEALED(3, "culturing_sealed", true),
        CURDS_SEALED(4, "curds_sealed", true),
        CURDS_UNSEALED(5, "curds_unsealed", false),
        FAT(6, "fat", false),
        SALT(7, "salt", false),
        FORMING(8, "forming", true),
        DRAINING_1(9, "draining_1", true),
        DRAINING_2(10, "draining_2", true),
        DRAINING_3(11, "draining_3", true),
        DRAINING_4(12, "draining_4", true),
        CHEESE(13, "cheese", false);

        private static final BlockBasketCheesemaking.EnumProgress[] META_LOOKUP = new BlockBasketCheesemaking.EnumProgress[values().length];
        private final int meta;
        private final String name;
        private final boolean sealed;

        private EnumProgress(int meta, String name, boolean sealed)
        {
            this.meta = meta;
            this.name = name;
            this.sealed = sealed;
        }

        public int getMetadata()
        {
            return this.meta;
        }

        public String toString()
        {
            return this.name;
        }

        public static BlockBasketCheesemaking.EnumProgress byMetadata(int meta)
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
            return this.name;
        }
        
        public String getRegisryName()
        {
        	return this.name;
        }
        
        public boolean isSealed()
        {
        	return this.sealed;
        }

        static
        {
            for (BlockBasketCheesemaking.EnumProgress progress : values())
            {
                META_LOOKUP[progress.getMetadata()] = progress;
            }
        }
    }
}
