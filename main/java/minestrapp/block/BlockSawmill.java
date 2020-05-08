package minestrapp.block;

import java.util.Random;

import minestrapp.MItems;
import minestrapp.MTabs;
import minestrapp.Minestrapp5;
import minestrapp.block.tileentity.TileEntitySawmill;
import minestrapp.block.util.BlockBase;
import minestrapp.crafting.SawmillRecipes;
import minestrapp.gui.MGuiHandler;
import minestrapp.item.ItemSawblade;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class BlockSawmill extends BlockBase implements ITileEntityProvider
{
	public static final PropertyEnum<BlockSawmill.EnumBladeType> BLADE = PropertyEnum.<BlockSawmill.EnumBladeType>create("blade", BlockSawmill.EnumBladeType.class);
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	public static final PropertyBool POWERED = PropertyBool.create("powered");
	public static final PropertyBool STOPPED = PropertyBool.create("stopped");
	
	public BlockSawmill()
	{
		super("sawmill", Material.ROCK, MapColor.STONE, SoundType.STONE, 3.5F, "pickaxe", 0);
		this.setDefaultState(this.blockState.getBaseState().withProperty(BLADE, BlockSawmill.EnumBladeType.NONE).withProperty(FACING, EnumFacing.NORTH).withProperty(POWERED, Boolean.valueOf(false)).withProperty(STOPPED, Boolean.valueOf(false)));
		this.setRenderLayer(BlockRenderLayer.CUTOUT);
		this.setCreativeTab(MTabs.utility);
		this.setResistance(10F);
		this.setPushReaction(EnumPushReaction.BLOCK);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote)
  			player.openGui(Minestrapp5.instance, MGuiHandler.SAWMILL, world, pos.getX(), pos.getY(), pos.getZ());  			
		
  		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntitySawmill();
	}
	
	public void millBlock(World world, BlockPos pos, IBlockState state, EnumFacing facing, boolean stone)
    {
    	world.setBlockToAir(pos);
    	
    	if(!stone)
    	{
	    	world.playSound((EntityPlayer)null, pos, SoundEvents.ENTITY_CAT_HISS, SoundCategory.BLOCKS, 0.3F, 10F);
	    	world.playSound((EntityPlayer)null, pos, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.BLOCKS, 0.2F, 10F);
	    	world.playSound((EntityPlayer)null, pos, SoundEvents.ENTITY_DONKEY_HURT, SoundCategory.BLOCKS, 0.1F, 0.6F);
	    	world.playSound((EntityPlayer)null, pos, SoundEvents.ENTITY_GUARDIAN_ATTACK, SoundCategory.BLOCKS, 0.2F, 10F);
    		world.playSound((EntityPlayer)null, pos, SoundEvents.ENTITY_HORSE_ANGRY, SoundCategory.BLOCKS, 0.05F, 0.6F);
    	}
    	else
    	{
    		world.playSound((EntityPlayer)null, pos, SoundEvents.EVOCATION_ILLAGER_CAST_SPELL, SoundCategory.BLOCKS, 0.3F, 10F);
    		world.playSound((EntityPlayer)null, pos, SoundEvents.ENTITY_HORSE_ANGRY, SoundCategory.BLOCKS, 0.05F, 0.5F);
	    	world.playSound((EntityPlayer)null, pos, SoundEvents.ENTITY_ZOMBIE_INFECT, SoundCategory.BLOCKS, 0.2F, 10F);
	    	//world.playSound((EntityPlayer)null, pos, SoundEvents.ENTITY_DONKEY_HURT, SoundCategory.BLOCKS, 0.1F, 0.6F);
    	}
    	
    	Random rand = world.rand;
    	double baseOffset = 1D;
    	double forwardSpeed = 0.5D;
    	double xOffset = 0;
    	double yOffset = 0;
    	double zOffset = 0;
    	double xSpeed = 0;
    	double ySpeed = 0;
    	double zSpeed = 0;
    	
    	if(facing == EnumFacing.UP)
    	{
    		yOffset = baseOffset;
    		ySpeed = forwardSpeed;
    	}
    	else if(facing == EnumFacing.DOWN)
    	{
    		yOffset = 1 - baseOffset;
    		ySpeed = -forwardSpeed;
    	}
    	else if(facing == EnumFacing.NORTH)
    	{
    		zOffset = 1 - baseOffset;
    		zSpeed = -forwardSpeed;
    	}
    	else if(facing == EnumFacing.SOUTH)
    	{
    		zOffset = baseOffset;
    		zSpeed = forwardSpeed;
    	}
    	else if(facing == EnumFacing.EAST)
    	{
    		xOffset = baseOffset;
    		xSpeed = forwardSpeed;
    	}
    	else if(facing == EnumFacing.WEST)
    	{
    		xOffset = 1 - baseOffset;
    		xSpeed = -forwardSpeed;
    	}
    	
    	for(int i = 0 ; i < 10 ; i++)
    	{
    		if(xOffset == 0)
    			xOffset = rand.nextDouble();
    		if(yOffset == 0)
    			yOffset = rand.nextDouble();
    		if(zOffset == 0)
    			zOffset = rand.nextDouble();
    		if(xSpeed == 0)
    			xSpeed = rand.nextDouble() * 0.5D;
    		if(ySpeed == 0)
    			ySpeed = rand.nextDouble() * 0.5D;
    		if(zSpeed == 0)
    			zSpeed = rand.nextDouble() * 0.5D;
    		
    		if(!world.isRemote)
    			((WorldServer)world).spawnParticle(EnumParticleTypes.BLOCK_CRACK, pos.getX() + xOffset, pos.getY() + yOffset, pos.getZ() + zOffset, 10, -xSpeed, -ySpeed, -zSpeed, state.getBlock().getIdFromBlock(state.getBlock()), state.getBlock().getStateId(state));
    	}
    }
	
	public boolean tryMillBlock(World world, BlockPos pos, EnumFacing facing)
	{
		BlockPos millPos = pos.offset(facing.getOpposite());
		SawmillRecipes milling = SawmillRecipes.instance();
		ItemStack input = new ItemStack(Item.getItemFromBlock(world.getBlockState(pos).getBlock()), 1, world.getBlockState(pos).getBlock().damageDropped(world.getBlockState(pos)));
		ItemStack result = milling.getSawmillResult(facing, input).copy();
		ItemStack extra = ItemStack.EMPTY;
		if(world.rand.nextInt(100) < milling.getChance(facing, input))
			extra = milling.getExtra(facing, input).copy();
		TileEntity te = world.getTileEntity(millPos);
		ItemStack blade = ItemStack.EMPTY;
		if(te != null && te instanceof TileEntitySawmill)
			blade = ((TileEntitySawmill)te).getStackInSlot(0);
		boolean stone = milling.isStone(facing, input);
		
		if(blade != ItemStack.EMPTY && blade.getItem() instanceof ItemSawblade && result.getItem() != Item.getItemFromBlock(Blocks.AIR) && (!stone || ((ItemSawblade)blade.getItem()).getBladeType().canBreakStone()))
		{
			boolean powered = world.isBlockPowered(millPos) || world.isBlockPowered(millPos.up());
			
			if(powered)
			{
				millBlock(world, pos, world.getBlockState(pos), facing, stone);
				if(!world.isRemote)
				{
					world.spawnEntity(new EntityItem(world, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, result));
					if(extra != ItemStack.EMPTY)
						world.spawnEntity(new EntityItem(world, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, extra));
				}
				if(blade.getMaxDamage() > 0)
				{
					if(blade.getItemDamage() < blade.getMaxDamage())
						blade.attemptDamageItem(1, world.rand, null);
					else
					{
						((TileEntitySawmill)te).setInventorySlotContents(0, ItemStack.EMPTY);
						world.setBlockState(millPos, world.getBlockState(millPos));
					}
				}
			}
			return true;
		}
		
		return false;
	}
	
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
		EnumFacing facing = state.getValue(FACING);
		
		if(!worldIn.getBlockState(pos.offset(state.getValue(FACING))).getBlock().isReplaceable(worldIn, pos.offset(state.getValue(FACING))) && !this.tryMillBlock(worldIn, pos.offset(state.getValue(FACING)), state.getValue(FACING)))
        	worldIn.setBlockState(pos, state.withProperty(STOPPED, true));
		else
			worldIn.setBlockState(pos, state.withProperty(STOPPED, false));
    }
	
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        super.onBlockAdded(worldIn, pos, state);
        if(!worldIn.getBlockState(pos.offset(state.getValue(FACING))).getBlock().isReplaceable(worldIn, pos.offset(state.getValue(FACING))) && !this.tryMillBlock(worldIn, pos.offset(state.getValue(FACING)), state.getValue(FACING)))
        	worldIn.setBlockState(pos, state.withProperty(STOPPED, true));
    }
	
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer)).withProperty(STOPPED, Boolean.valueOf(false));
    }
	
	public IBlockState getStateFromMeta(int meta)
    {
		return this.getDefaultState().withProperty(FACING, EnumFacing.getFront(meta & 7)).withProperty(STOPPED, Boolean.valueOf((meta & 8) > 0));
    }
    
    public int getMetaFromState(IBlockState state)
    {
    	int i = 0;
        i = i | ((EnumFacing)state.getValue(FACING)).getIndex();

        if (((Boolean)state.getValue(STOPPED)).booleanValue())
            i |= 8;

        return i;
    }
    
    public static EnumFacing getFacing(int meta) { return EnumFacing.getFront(meta & 7); }
    
    public IBlockState withRotation(IBlockState state, Rotation rot) { return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING))); }
    
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) { return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING))); }

    protected BlockStateContainer createBlockState() { return new BlockStateContainer(this, new IProperty[] {FACING, STOPPED, POWERED, BLADE}); }
	
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
		BlockSawmill.EnumBladeType blade = EnumBladeType.NONE;
		TileEntity te = worldIn.getTileEntity(pos);
		if(te != null && te instanceof TileEntitySawmill && ((TileEntitySawmill)te).getStackInSlot(0).getItem() instanceof ItemSawblade)
			blade = ((ItemSawblade)((TileEntitySawmill)te).getStackInSlot(0).getItem()).getBladeType();
		
		boolean powered = false;
		
		if(Minecraft.getMinecraft().world.isBlockIndirectlyGettingPowered(pos) > 0)
			powered = true;
		
		return state.withProperty(BLADE, blade).withProperty(POWERED, powered);
    }
	
	public static enum EnumBladeType implements IStringSerializable
    {
        NONE(0, "none", false, 0, 0),
        COPPER(1, "copper", false, MItems.COPPER.getAttackDamage(), 128),
        IRON(2, "iron", false, ToolMaterial.IRON.getAttackDamage(), 256),
        BRONZE(3, "bronze", false, MItems.BRONZE.getAttackDamage(), 768),
        STEEL(4, "steel", true, MItems.STEEL.getAttackDamage(), 512),
        ARCHANTINE(5, "archantine", true, MItems.TITANIUM.getAttackDamage(), 0);

        private static final BlockSawmill.EnumBladeType[] META_LOOKUP = new BlockSawmill.EnumBladeType[values().length];
        private final int meta;
        private final String name;
        private final boolean canBreakStone;
        private final float damage;
        private final int durability;

        private EnumBladeType(int meta, String name, boolean canBreakStone, float damage, int durability)
        {
            this.meta = meta;
            this.name = name;
            this.canBreakStone = canBreakStone;
            this.damage = damage;
            this.durability = durability;
        }

        public int getMetadata()
        {
            return this.meta;
        }

        public String toString()
        {
            return this.name;
        }

        public static BlockSawmill.EnumBladeType byMetadata(int meta)
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
        
        public boolean canBreakStone()
        {
        	return this.canBreakStone;
        }
        
        public float getDamage()
        {
        	return this.damage;
        }
        
        public int getDurability()
        {
        	return this.durability;
        }

        static
        {
            for (BlockSawmill.EnumBladeType progress : values())
            {
                META_LOOKUP[progress.getMetadata()] = progress;
            }
        }
    }
}
