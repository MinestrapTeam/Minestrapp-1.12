package minestrapp.block;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import minestrapp.MBlocks;
import minestrapp.MFluids;
import minestrapp.MItems;
import minestrapp.MTabs;
import minestrapp.Minestrapp5;
import minestrapp.block.crops.BlockBerryBush;
import minestrapp.block.crops.BlockVoidberryBush;
import minestrapp.block.magnetpiston.BlockMagnetPistonBase;
import minestrapp.block.tileentity.TileEntityActivator;
import minestrapp.block.tileentity.TileEntityCrusher;
import minestrapp.block.tileentity.TileEntityPlate;
import minestrapp.block.tileentity.TileEntityTanningRack;
import minestrapp.crafting.SieveRecipes;
import minestrapp.entity.vehicle.EntityMBoat;
import minestrapp.gui.MGuiHandler;
import minestrapp.item.ItemMBoat;
import minestrapp.item.ItemMDoor;
import minestrapp.item.ItemSieve;
import minestrapp.item.tools.MDagger;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockButton;
import net.minecraft.block.BlockCake;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockDaylightDetector;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockEndPortalFrame;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockGlazedTerracotta;
import net.minecraft.block.BlockJukebox;
import net.minecraft.block.BlockJukebox.TileEntityJukebox;
import net.minecraft.block.BlockLever;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockObserver;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockSourceImpl;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmorStand;
import net.minecraft.item.ItemBanner;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBoat;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemHangingEntity;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemLingeringPotion;
import net.minecraft.item.ItemMinecart;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemNameTag;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemRedstone;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSplashPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.tileentity.TileEntityNote;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Rotations;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.UniversalBucket;

public class BlockActivator extends BlockContainer
{
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	public static final PropertyBool TRIGGERED = PropertyBool.create("triggered");
	public static final PropertyBool POWERED = PropertyBool.create("powered");
	
	public BlockActivator(String name)
	{
		super(Material.IRON);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(TRIGGERED, Boolean.valueOf(false)).withProperty(POWERED, Boolean.valueOf(false)));
		this.setSoundType(SoundType.METAL);
		this.setHardness(3.0F);
		this.setHarvestLevel("pickaxe", 0);
		this.setResistance(6F);
		this.setCreativeTab(MTabs.utility);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
	}
	
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
		if(worldIn.getTileEntity(pos) != null && worldIn.getTileEntity(pos) instanceof TileEntityActivator && ((TileEntityActivator)worldIn.getTileEntity(pos)).isRedstonePowered())
			return state.withProperty(POWERED, true);
		else
			return state.withProperty(POWERED, false);
    }
	
	public boolean isFullCube(IBlockState state)
    {
        return false;
    }
	
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	
	public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing)
    {
		return BlockFaceShape.UNDEFINED;
    }
	
	public int tickRate(World worldIn)
    {
        return 0;
    }
	
	public MapColor getMapColor(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return MapColor.ADOBE;
    }
	
	public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
	
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        super.onBlockAdded(worldIn, pos, state);
        this.setDefaultDirection(worldIn, pos, state);
    }
	
	public int getLightValue(IBlockState state)
    {
    	if(state.getValue(POWERED).booleanValue() == true)
    		return 8;
    	else
    		return 0;
    }

    private void setDefaultDirection(World worldIn, BlockPos pos, IBlockState state)
    {
        //if (worldIn.isRemote)
        //{
            EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
            boolean flag = worldIn.getBlockState(pos.north()).isFullBlock();
            boolean flag1 = worldIn.getBlockState(pos.south()).isFullBlock();

            if (enumfacing == EnumFacing.NORTH && flag && !flag1)
            {
                enumfacing = EnumFacing.SOUTH;
            }
            else if (enumfacing == EnumFacing.SOUTH && flag1 && !flag)
            {
                enumfacing = EnumFacing.NORTH;
            }
            else
            {
                boolean flag2 = worldIn.getBlockState(pos.west()).isFullBlock();
                boolean flag3 = worldIn.getBlockState(pos.east()).isFullBlock();

                if (enumfacing == EnumFacing.WEST && flag2 && !flag3)
                {
                    enumfacing = EnumFacing.EAST;
                }
                else if (enumfacing == EnumFacing.EAST && flag3 && !flag2)
                {
                    enumfacing = EnumFacing.WEST;
                }
            }

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing).withProperty(TRIGGERED, Boolean.valueOf(false)), 2);
        //}
    }
    
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        boolean flag = worldIn.isBlockPowered(pos) || worldIn.isBlockPowered(pos.up());
        boolean flag1 = ((Boolean)state.getValue(TRIGGERED)).booleanValue();

        if (flag && !flag1)
        {
            worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
            worldIn.setBlockState(pos, state.withProperty(TRIGGERED, Boolean.valueOf(true)), 4);
        }
        else if (!flag && flag1)
        {
            worldIn.setBlockState(pos, state.withProperty(TRIGGERED, Boolean.valueOf(false)), 4);
        }
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
    	if(specialCaseCheck(worldIn, pos))
    	{
    		if (!worldIn.isRemote)
    			this.activate(worldIn, pos);
    	}
    }
	
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer)).withProperty(TRIGGERED, Boolean.valueOf(false));
    }
    
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos, state.withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer)), 2);

        if (stack.hasDisplayName())
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityActivator)
            {
                ((TileEntityActivator)tileentity).setCustomName(stack.getDisplayName());
            }
        }
    }
	
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getFront(meta & 7)).withProperty(TRIGGERED, Boolean.valueOf((meta & 8) > 0));
    }
    
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | ((EnumFacing)state.getValue(FACING)).getIndex();

        if (((Boolean)state.getValue(TRIGGERED)).booleanValue())
        {
            i |= 8;
        }

        return i;
    }
    
    public static EnumFacing getFacing(int meta)
    {
    	return EnumFacing.getFront(meta & 7);
    }
    
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }
    
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING, TRIGGERED, POWERED});
    }
    
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
    	TileEntityActivator tea = (TileEntityActivator)worldIn.getTileEntity(pos);
		EnumFacing face = (EnumFacing)state.getValue(FACING);
		if(face == EnumFacing.NORTH) {
			tea.setAngle(0);
		}
		else if(face == EnumFacing.EAST) {
			tea.setAngle(1);
		}
		else if(face == EnumFacing.SOUTH) {
			tea.setAngle(2);
		}
		else if(face == EnumFacing.WEST) {
			tea.setAngle(3);
		}
		else if(face == EnumFacing.UP) {
			tea.setAngle(10);
		}
		else if(face == EnumFacing.DOWN) {
			tea.setAngle(11);
		}
    	
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            ILockableContainer ilockablecontainer = this.getLockableContainer(worldIn, pos);

            if (ilockablecontainer != null)
            {
                playerIn.openGui(Minestrapp5.instance, MGuiHandler.ACTIVATOR, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }

            return true;
        }
    }
    
    @Nullable
    public ILockableContainer getLockableContainer(World worldIn, BlockPos pos)
    {
        return this.getContainer(worldIn, pos, false);
    }
    
    @Nullable
    public ILockableContainer getContainer(World worldIn, BlockPos pos, boolean allowBlocking)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (!(tileentity instanceof TileEntityActivator))
        {
            return null;
        }
        else
        {
            ILockableContainer ilockablecontainer = (TileEntityActivator)tileentity;

            return ilockablecontainer;
        }
    }

	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
		TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof IInventory)
        {
            InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory)tileentity);
            worldIn.updateComparatorOutputLevel(pos, this);
        }

        super.breakBlock(worldIn, pos, state);
    }

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityActivator();
	}
	
	public boolean hasComparatorInputOverride(IBlockState state)
    {
        return true;
    }
	
	public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return Container.calcRedstoneFromInventory(this.getLockableContainer(worldIn, pos));
    }
	
	public boolean checkPlacability (World worldIn, BlockPos pos1, BlockPos pos2, EnumFacing facing, ItemStack stack, Item item, EntityLiving placer)
	{
		if(worldIn.getBlockState(pos1).getBlock().isReplaceable(worldIn, pos1) && (!worldIn.isAirBlock(pos1) || !worldIn.isAirBlock(pos2)) && (((!worldIn.isAirBlock(pos1) || !worldIn.getBlockState(pos2).getBlock().isReplaceable(worldIn, pos2)) && Block.getBlockFromItem(item).canPlaceBlockAt(worldIn, pos1)) || (worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock().isReplaceable(worldIn, pos2) && Block.getBlockFromItem(item).canPlaceBlockAt(worldIn, pos2))) && !Block.getBlockFromItem(item).hasTileEntity(Block.getBlockFromItem(item).getStateForPlacement(worldIn, pos1, facing.getOpposite(), 0.5F, 0.5F, 0.5F, stack.getMetadata(), placer, EnumHand.MAIN_HAND)))
			return true;
		return false;
	}
	
	public boolean checkForBlock (World worldIn, BlockPos pos1, BlockPos pos2, Block block)
	{
		if(worldIn.getBlockState(pos1).getBlock() == block || (worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock() == block))
			return true;
		else
			return false;
	}
	
	protected boolean specialCaseCheck(World worldIn, BlockPos pos)
	{
		boolean continueCheck = true;
		BlockSourceImpl blocksourceimpl = new BlockSourceImpl(worldIn, pos);
        TileEntityActivator te = (TileEntityActivator)blocksourceimpl.getBlockTileEntity();
		if(te != null)
		{
			ItemStack stack = te.getStackInSlot(0);
			Item item = stack.getItem();
			EnumFacing facing = worldIn.getBlockState(pos).getValue(BlockActivator.FACING);
			BlockPos pos1 = pos.offset(facing);
			BlockPos pos2 = pos1.offset(facing);
	        
	        if(continueCheck && (worldIn.getBlockState(pos1).getBlock() instanceof BlockPlate || (worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock() instanceof BlockPlate)))
			{
				BlockPos platePos = pos1;
				
				if(worldIn.isAirBlock(pos1))
					platePos = pos2;
				
				TileEntityPlate tep = (TileEntityPlate)worldIn.getTileEntity(platePos);
				
				if(tep != null)
				{
					if(tep.processActivatorInteract(pos, facing))
						te.decrStackSize(0, 1);
					
					tep.sendUpdates();
				}
				
				continueCheck = false;
			}
	        
	        if(continueCheck && (worldIn.getBlockState(pos1).getBlock() instanceof BlockTanningRack || (worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock() instanceof BlockTanningRack)))
			{
				BlockPos rackPos = pos1;
				
				if(worldIn.isAirBlock(pos1))
					rackPos = pos2;
				
				TileEntityTanningRack ter = (TileEntityTanningRack)worldIn.getTileEntity(rackPos);
				
				if(ter != null)
				{
					if(ter.processActivatorInteract(pos, facing))
						te.decrStackSize(0, 1);
					
					ter.sendUpdates();
				}
				
				continueCheck = false;
			}
	        
	        if(continueCheck && checkForBlock(worldIn, pos1, pos2, MBlocks.basket_cheesemaking))
	        {
	        	BlockPos basketPos = pos1;
				
				if(worldIn.isAirBlock(pos1))
					basketPos = pos2;
				
				BlockBasketCheesemaking.EnumProgress progress = worldIn.getBlockState(basketPos).getValue(BlockBasketCheesemaking.PROGRESS);
				
				if(progress != null)
				{
					IBlockState basket = MBlocks.basket_cheesemaking.getDefaultState();
					
					if(progress == BlockBasketCheesemaking.EnumProgress.WAX && item == Items.MILK_BUCKET)
					{
						worldIn.setBlockState(basketPos, basket.withProperty(BlockBasketCheesemaking.PROGRESS, BlockBasketCheesemaking.EnumProgress.MILK));
						te.setInventorySlotContents(0, new ItemStack(Items.BUCKET));
						continueCheck = false;
					}
					else if(progress == BlockBasketCheesemaking.EnumProgress.MILK && item == MItems.tannic && stack.getMetadata() == 3)
					{
						worldIn.setBlockState(basketPos, basket.withProperty(BlockBasketCheesemaking.PROGRESS, BlockBasketCheesemaking.EnumProgress.CULTURING_UNSEALED));
						te.decrStackSize(0, 1);
						worldIn.spawnEntity(new EntityItem(worldIn, basketPos.getX() + 0.5D, basketPos.getY() + 0.5D, basketPos.getZ() + 0.5D, new ItemStack(Items.GLASS_BOTTLE)));
						continueCheck = false;
					}
					else if(progress == BlockBasketCheesemaking.EnumProgress.CULTURING_UNSEALED && item == Item.getItemFromBlock(MBlocks.cork))
					{
						worldIn.setBlockState(basketPos, basket.withProperty(BlockBasketCheesemaking.PROGRESS, BlockBasketCheesemaking.EnumProgress.CULTURING_SEALED));
						te.decrStackSize(0, 1);
						continueCheck = false;
					}
					else if(progress == BlockBasketCheesemaking.EnumProgress.CURDS_SEALED)
					{
						worldIn.setBlockState(basketPos, basket.withProperty(BlockBasketCheesemaking.PROGRESS, BlockBasketCheesemaking.EnumProgress.CURDS_UNSEALED));
						worldIn.spawnEntity(new EntityItem(worldIn, basketPos.getX() + 0.5D, basketPos.getY() + 0.5D, basketPos.getZ() + 0.5D, new ItemStack(MBlocks.cork)));
						continueCheck = false;
					}
					else if(progress == BlockBasketCheesemaking.EnumProgress.CURDS_UNSEALED && item == MItems.fat)
					{
						worldIn.setBlockState(basketPos, basket.withProperty(BlockBasketCheesemaking.PROGRESS, BlockBasketCheesemaking.EnumProgress.FAT));
						te.decrStackSize(0, 1);
						continueCheck = false;
					}
					else if(progress == BlockBasketCheesemaking.EnumProgress.FAT && item == MItems.salt)
					{
						worldIn.setBlockState(basketPos, basket.withProperty(BlockBasketCheesemaking.PROGRESS, BlockBasketCheesemaking.EnumProgress.SALT));
						te.decrStackSize(0, 1);
						continueCheck = false;
					}
					else if(progress == BlockBasketCheesemaking.EnumProgress.SALT && item == Item.getItemFromBlock(MBlocks.cork))
					{
						worldIn.setBlockState(basketPos, basket.withProperty(BlockBasketCheesemaking.PROGRESS, BlockBasketCheesemaking.EnumProgress.FORMING));
						te.decrStackSize(0, 1);
						continueCheck = false;
					}
					else if(progress == BlockBasketCheesemaking.EnumProgress.DRAINING_4)
					{
						worldIn.setBlockState(basketPos, basket.withProperty(BlockBasketCheesemaking.PROGRESS, BlockBasketCheesemaking.EnumProgress.CHEESE));
						worldIn.spawnEntity(new EntityItem(worldIn, basketPos.getX() + 0.5D, basketPos.getY() + 0.5D, basketPos.getZ() + 0.5D, new ItemStack(MBlocks.cork)));
						continueCheck = false;
					}
					else if(progress == BlockBasketCheesemaking.EnumProgress.CHEESE)
					{
						worldIn.setBlockState(basketPos, MBlocks.basket.getDefaultState());
						worldIn.spawnEntity(new EntityItem(worldIn, basketPos.getX() + 0.5D, basketPos.getY() + 0.5D, basketPos.getZ() + 0.5D, new ItemStack(MBlocks.block_cheese)));
						continueCheck = false;
					}
				}
	        }
	        
	        if(continueCheck && (worldIn.getBlockState(pos1).getBlock() instanceof BlockFoodSliceable || (worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock() instanceof BlockFoodSliceable)) && item instanceof MDagger)
	        {
	        	BlockPos foodPos = pos1;
				
				if(worldIn.isAirBlock(pos1))
					foodPos = pos2;
				
				te.getStackInSlot(0).attemptDamageItem(1, worldIn.rand, null);
				if(((BlockFoodSliceable)worldIn.getBlockState(foodPos).getBlock()).getSlice() != null)
					worldIn.spawnEntity(new EntityItem(worldIn, foodPos.getX() + 0.5D, foodPos.getY() + 0.5D, foodPos.getZ() + 0.5D, new ItemStack(((BlockFoodSliceable)worldIn.getBlockState(foodPos).getBlock()).getSlice())));
	    		((BlockFoodSliceable)worldIn.getBlockState(foodPos).getBlock()).tryRemoveSlice(worldIn, foodPos, worldIn.getBlockState(foodPos));
	    		
	    		continueCheck = false;
	        }
	        
	        if(continueCheck && checkForBlock(worldIn, pos1, pos2, Blocks.CAKE))
	        {
	        	BlockPos foodPos = pos1;
	        	
	        	if(worldIn.isAirBlock(pos1))
	        		foodPos = pos2;
	        	
	        	te.getStackInSlot(0).attemptDamageItem(1, worldIn.rand, null);
	        	
	        	IBlockState state = worldIn.getBlockState(foodPos);
	        	int slices = state.getValue(BlockCake.BITES).intValue();
	        	
	        	if(slices < 6)
	        		worldIn.setBlockState(foodPos, state.withProperty(BlockCake.BITES, slices + 1));
	        	else
	        		worldIn.setBlockToAir(foodPos);
	        	
	        	worldIn.spawnEntity(new EntityItem(worldIn, foodPos.getX() + 0.5D, foodPos.getY() + 0.5D, foodPos.getZ() + 0.5D, new ItemStack(MItems.cake_slice)));
	        	
	        	continueCheck = false;
	        }
		}
		
		return continueCheck;
	}
	
	protected void activate(World worldIn, BlockPos pos)
    {
		BlockSourceImpl blocksourceimpl = new BlockSourceImpl(worldIn, pos);
        TileEntityActivator te = (TileEntityActivator)blocksourceimpl.getBlockTileEntity();
		if(te != null)
		{
			ItemStack stack = te.getStackInSlot(0);
			Item item = stack.getItem();
			EnumFacing facing = worldIn.getBlockState(pos).getValue(BlockActivator.FACING);
			BlockPos pos1 = pos.offset(facing);
			BlockPos pos2 = pos1.offset(facing);
			
			float yaw = 0F;
			if(facing == EnumFacing.EAST)
				yaw = 90F;
			else if(facing == EnumFacing.SOUTH)
				yaw = 180F;
			else if(facing == EnumFacing.WEST);
				yaw = -90F;
				
			float pitch = 0F;
			if(facing == EnumFacing.UP)
				pitch = -90F;
			else if (facing == EnumFacing.DOWN)
				pitch = 90F;
			
			EntityEnderman placer = new EntityEnderman(worldIn);
			placer.setPositionAndRotation(pos.getX(), pos.getY(), pos.getZ(), yaw, pitch);
			
			boolean continueCheck = true;
			
			if(continueCheck && checkForBlock(worldIn, pos1, pos2, Blocks.LEVER))
			{
				if(worldIn.isAirBlock(pos1))
					((BlockLever)worldIn.getBlockState(pos2).getBlock()).onBlockActivated(worldIn, pos2, worldIn.getBlockState(pos2), null, null, facing, 0.5F, 0.5F, 0.5F);
				else
					((BlockLever)worldIn.getBlockState(pos1).getBlock()).onBlockActivated(worldIn, pos1, worldIn.getBlockState(pos1), null, null, facing, 0.5F, 0.5F, 0.5F);
				
				continueCheck = false;
			}
			
			if(continueCheck && (worldIn.getBlockState(pos1).getBlock() instanceof BlockButton || (worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock() instanceof BlockButton)))
			{
				if(worldIn.isAirBlock(pos1))
					((BlockButton)worldIn.getBlockState(pos2).getBlock()).onBlockActivated(worldIn, pos2, worldIn.getBlockState(pos2), null, null, facing, 0.5F, 0.5F, 0.5F);
				else
					((BlockButton)worldIn.getBlockState(pos1).getBlock()).onBlockActivated(worldIn, pos1, worldIn.getBlockState(pos1), null, null, facing, 0.5F, 0.5F, 0.5F);
				
				continueCheck = false;
			}
			
			if(continueCheck && (worldIn.getBlockState(pos1).getBlock() instanceof BlockTrapDoor || (worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock() instanceof BlockTrapDoor)))
			{
				if(worldIn.isAirBlock(pos1) && ((BlockTrapDoor)worldIn.getBlockState(pos2).getBlock()).onBlockActivated(worldIn, pos2, worldIn.getBlockState(pos2), null, null, facing, 0.5F, 0.5F, 0.5F))
					continueCheck = false;
				else if(!worldIn.isAirBlock(pos1) && ((BlockTrapDoor)worldIn.getBlockState(pos1).getBlock()).onBlockActivated(worldIn, pos1, worldIn.getBlockState(pos1), null, null, facing, 0.5F, 0.5F, 0.5F))
					continueCheck = false;
			}
			
			if(continueCheck && (worldIn.getBlockState(pos1).getBlock() instanceof BlockFenceGate || (worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock() instanceof BlockFenceGate)))
			{
				BlockPos gatePos = pos1;
				
				if(worldIn.isAirBlock(pos1))
					gatePos = pos2;
				
				IBlockState state = worldIn.getBlockState(gatePos);
				
				if (((Boolean)state.getValue(BlockFenceGate.OPEN)).booleanValue())
		            worldIn.setBlockState(gatePos, state.withProperty(BlockFenceGate.OPEN, Boolean.valueOf(false)), 10);
		        else
		        {
		        	if (state.getValue(BlockFenceGate.FACING) == facing.getOpposite())
		                state = state.withProperty(BlockFenceGate.FACING, facing);

		            state = state.withProperty(BlockFenceGate.OPEN, Boolean.valueOf(true));
		            worldIn.setBlockState(gatePos, state, 10);
		        }

		        worldIn.playEvent(null, ((Boolean)state.getValue(BlockFenceGate.OPEN)).booleanValue() ? 1008 : 1014, pos, 0);
		        continueCheck = false;
			}
			
			if(continueCheck && (checkForBlock(worldIn, pos1, pos2, Blocks.DAYLIGHT_DETECTOR) || checkForBlock(worldIn, pos1, pos2, Blocks.DAYLIGHT_DETECTOR_INVERTED)))
			{
				BlockPos detectorPos = pos1;
				
				if(worldIn.isAirBlock(pos1))
					detectorPos = pos2;
				
				if (worldIn.getBlockState(detectorPos).getBlock() == Blocks.DAYLIGHT_DETECTOR_INVERTED)
                {
                    worldIn.setBlockState(detectorPos, Blocks.DAYLIGHT_DETECTOR.getDefaultState().withProperty(BlockDaylightDetector.POWER, worldIn.getBlockState(detectorPos).getValue(BlockDaylightDetector.POWER)), 4);
                    Blocks.DAYLIGHT_DETECTOR.updatePower(worldIn, detectorPos);
                }
                else
                {
                	worldIn.setBlockState(detectorPos, Blocks.DAYLIGHT_DETECTOR_INVERTED.getDefaultState().withProperty(BlockDaylightDetector.POWER, worldIn.getBlockState(detectorPos).getValue(BlockDaylightDetector.POWER)), 4);
                    Blocks.DAYLIGHT_DETECTOR_INVERTED.updatePower(worldIn, detectorPos);
                }
				
				continueCheck = false;
			}
			
			if(continueCheck && (worldIn.getBlockState(pos1).getBlock() instanceof BlockDoor || (worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock() instanceof BlockDoor)))
			{
				if(worldIn.isAirBlock(pos1) && ((BlockDoor)worldIn.getBlockState(pos2).getBlock()).onBlockActivated(worldIn, pos2, worldIn.getBlockState(pos2), null, null, facing, 0.5F, 0.5F, 0.5F))
					continueCheck = false;
				else if(!worldIn.isAirBlock(pos1) && ((BlockDoor)worldIn.getBlockState(pos1).getBlock()).onBlockActivated(worldIn, pos1, worldIn.getBlockState(pos1), null, null, facing, 0.5F, 0.5F, 0.5F))
					continueCheck = false;
			}
			
			if(continueCheck && checkForBlock(worldIn, pos1, pos2, Blocks.CAULDRON))
			{
				BlockPos cauldronPos = pos1;
				
				if(worldIn.isAirBlock(pos1))
					cauldronPos = pos2;
				
				if(item == Items.BUCKET && worldIn.getBlockState(cauldronPos).getValue(BlockCauldron.LEVEL).intValue() == 3)
				{
					worldIn.setBlockState(cauldronPos, worldIn.getBlockState(cauldronPos).withProperty(BlockCauldron.LEVEL, 0));
					worldIn.spawnEntity(new EntityItem(worldIn, cauldronPos.getX() + 0.5D, cauldronPos.getY() + 0.5D, cauldronPos.getZ() + 0.5D, new ItemStack(Items.WATER_BUCKET)));
					te.decrStackSize(0, 1);
					continueCheck = false;
				}
				else if(item == Items.WATER_BUCKET && worldIn.getBlockState(cauldronPos).getValue(BlockCauldron.LEVEL).intValue() < 3)
				{
					worldIn.setBlockState(cauldronPos, worldIn.getBlockState(cauldronPos).withProperty(BlockCauldron.LEVEL, 3));
					te.setInventorySlotContents(0, new ItemStack(Items.BUCKET));
					continueCheck = false;
				}
				else if(item == Items.GLASS_BOTTLE && worldIn.getBlockState(cauldronPos).getValue(BlockCauldron.LEVEL).intValue() > 0)
				{
					worldIn.setBlockState(cauldronPos, worldIn.getBlockState(cauldronPos).withProperty(BlockCauldron.LEVEL, worldIn.getBlockState(cauldronPos).getValue(BlockCauldron.LEVEL).intValue() - 1));
					worldIn.spawnEntity(new EntityItem(worldIn, cauldronPos.getX() + 0.5D, cauldronPos.getY() + 0.5D, cauldronPos.getZ() + 0.5D, PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.WATER)));
					te.decrStackSize(0, 1);
					continueCheck = false;
				}
				else if(item == Items.POTIONITEM && PotionUtils.getPotionFromItem(stack) == PotionTypes.WATER && worldIn.getBlockState(cauldronPos).getValue(BlockCauldron.LEVEL).intValue() < 3)
				{
					worldIn.setBlockState(cauldronPos, worldIn.getBlockState(cauldronPos).withProperty(BlockCauldron.LEVEL, worldIn.getBlockState(cauldronPos).getValue(BlockCauldron.LEVEL).intValue() + 1));
					te.setInventorySlotContents(0, new ItemStack(Items.GLASS_BOTTLE));
					continueCheck = false;
				}
				else if(item instanceof ItemArmor && worldIn.getBlockState(cauldronPos).getValue(BlockCauldron.LEVEL).intValue() > 0)
				{
					ItemArmor itemarmor = (ItemArmor)item;

                    if (itemarmor.getArmorMaterial() == ItemArmor.ArmorMaterial.LEATHER && itemarmor.hasColor(stack) && !worldIn.isRemote)
                    {
                    	worldIn.setBlockState(cauldronPos, worldIn.getBlockState(cauldronPos).withProperty(BlockCauldron.LEVEL, worldIn.getBlockState(cauldronPos).getValue(BlockCauldron.LEVEL).intValue() - 1));
                        itemarmor.removeColor(te.getStackInSlot(0));
                        continueCheck = false;
                    }
				}
				else if(item instanceof ItemBanner && worldIn.getBlockState(cauldronPos).getValue(BlockCauldron.LEVEL).intValue() > 0)
				{
					if (TileEntityBanner.getPatterns(stack) > 0 && !worldIn.isRemote)
                    {
                        ItemStack itemstack1 = stack.copy();
                        itemstack1.setCount(1);
                        TileEntityBanner.removeBannerData(itemstack1);
                        worldIn.setBlockState(cauldronPos, worldIn.getBlockState(cauldronPos).withProperty(BlockCauldron.LEVEL, worldIn.getBlockState(cauldronPos).getValue(BlockCauldron.LEVEL).intValue() - 1));
                        worldIn.spawnEntity(new EntityItem(worldIn, cauldronPos.getX() + 0.5D, cauldronPos.getY() + 0.5D, cauldronPos.getZ() + 0.5D, itemstack1));
                        te.decrStackSize(0, 1);
                        continueCheck = false;
                    }
				}
			}
			
			if(continueCheck && checkForBlock(worldIn, pos1, pos2, Blocks.NOTEBLOCK))
			{
				BlockPos notePos = pos1;
				
				if(worldIn.isAirBlock(pos1))
					notePos = pos2;
				
				TileEntity tileentity = worldIn.getTileEntity(notePos);

	            if (tileentity instanceof TileEntityNote)
	            {
	                TileEntityNote tileentitynote = (TileEntityNote)tileentity;
	                int old = tileentitynote.note;
	                tileentitynote.changePitch();
	                if (old != tileentitynote.note)
	                	tileentitynote.triggerNote(worldIn, notePos);
	                
	                continueCheck = false;
	            }
			}
			
			if(continueCheck && (worldIn.getBlockState(pos1).getBlock() instanceof BlockBerryBush || (worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock() instanceof BlockBerryBush)))
			{
				BlockPos bushPos = pos1;
				
				if(worldIn.isAirBlock(pos1))
					bushPos = pos2;
				
				if(worldIn.getBlockState(bushPos).getValue(BlockBerryBush.AGE) == 5)
				{
					worldIn.spawnEntity(new EntityItem(worldIn, bushPos.getX() + 0.5D, bushPos.getY() + 0.5D, bushPos.getZ() + 0.5D, ((BlockBerryBush)worldIn.getBlockState(bushPos).getBlock()).getDrop()));
					worldIn.setBlockState(bushPos, worldIn.getBlockState(bushPos).withProperty(BlockBerryBush.AGE, 0));
					continueCheck = false;
				}
			}
			
			if(continueCheck && (worldIn.getBlockState(pos1).getBlock() instanceof BlockVoidberryBush || (worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock() instanceof BlockVoidberryBush)))
			{
				BlockPos bushPos = pos1;
				
				if(worldIn.isAirBlock(pos1))
					bushPos = pos2;
				
				if(worldIn.getBlockState(bushPos).getValue(BlockVoidberryBush.STEM).booleanValue() == false && worldIn.getBlockState(bushPos).getValue(BlockVoidberryBush.AGE) == 5)
				{
					worldIn.spawnEntity(new EntityItem(worldIn, bushPos.getX() + 0.5D, bushPos.getY() + 0.5D, bushPos.getZ() + 0.5D, (new ItemStack(MItems.voidberry))));
					worldIn.setBlockState(bushPos, worldIn.getBlockState(bushPos).withProperty(BlockVoidberryBush.AGE, 0));
					continueCheck = false;
				}
			}
			
			if(continueCheck && checkForBlock(worldIn, pos1, pos2, MBlocks.mite_hive_honeyed))
			{
				if(worldIn.isAirBlock(pos1))
				{
					worldIn.setBlockState(pos2, MBlocks.mite_hive.getDefaultState().withProperty(BlockEndermiteHiveHusk.VARIANT, worldIn.getBlockState(pos2).getValue(BlockEndermiteHiveHusk.VARIANT)));
					worldIn.spawnEntity(new EntityItem(worldIn, pos2.getX() + 0.5D, pos2.getY() + 0.5D, pos2.getZ() + 0.5D, (new ItemStack(MItems.mite_honey))));
				}
				else
				{
					worldIn.setBlockState(pos1, MBlocks.mite_hive.getDefaultState().withProperty(BlockEndermiteHiveHusk.VARIANT, worldIn.getBlockState(pos1).getValue(BlockEndermiteHiveHusk.VARIANT)));
					worldIn.spawnEntity(new EntityItem(worldIn, pos1.getX() + 0.5D, pos1.getY() + 0.5D, pos1.getZ() + 0.5D, (new ItemStack(MItems.mite_honey))));
				}
				
				continueCheck = false;
			}
			
			if(continueCheck && (worldIn.getBlockState(pos1).getBlock() instanceof BlockMagnetPistonBase || (worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock() instanceof BlockMagnetPistonBase)))
			{
				BlockPos pistonPos = pos1;
				
				if(worldIn.isAirBlock(pos1))
					pistonPos = pos2;

				if(((BlockMagnetPistonBase)worldIn.getBlockState(pistonPos).getBlock()).onBlockActivated(worldIn, pistonPos, worldIn.getBlockState(pistonPos), null, EnumHand.MAIN_HAND, worldIn.getBlockState(pos).getValue(FACING).getOpposite(), 0.5F, 0.5F, 0.5F))
					continueCheck = false;
			}
			
			if(continueCheck && item instanceof ItemSieve)
			{
				BlockPos sievePos = null;
				
				if(!worldIn.isAirBlock(pos1))
					sievePos = pos1;
				else if(!worldIn.isAirBlock(pos2))
					sievePos = pos2;
				
				if(sievePos != null)
				{
					IBlockState iblockstate = worldIn.getBlockState(sievePos);
		            Block block = iblockstate.getBlock();
		            LootTable table = LootTable.EMPTY_LOOT_TABLE;
		            if(!worldIn.isRemote && SieveRecipes.instance().getSieveResult(iblockstate) != null)
		            	table = worldIn.getLootTableManager().getLootTableFromLocation(SieveRecipes.instance().getSieveResult(iblockstate));
		            
		            if(table != null && table != LootTable.EMPTY_LOOT_TABLE)
		            {
		            	worldIn.destroyBlock(sievePos, false);
		            	if(!((ItemSieve)item).isUnbreakable())
		            	{
		            		if(te.getStackInSlot(0).attemptDamageItem(1, worldIn.rand, null))
							{
								worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.BLOCKS, 0.8F, 0.8F + worldIn.rand.nextFloat() * 0.4F);
								
								if(((ItemSieve)item).getBreakItem() != ItemStack.EMPTY)
									te.setInventorySlotContents(0, ((ItemSieve)item).getBreakItem());
								else
									te.decrStackSize(0, 1);
							}
		            	}
		            	LootContext.Builder context = new LootContext.Builder((WorldServer) worldIn);
		            	List<ItemStack> stackList = table.generateLootForPools(worldIn.rand, context.build());
		            	
		            	if(stackList.size() > 0)
		            		worldIn.spawnEntity(new EntityItem(worldIn, sievePos.getX() + 0.5D, sievePos.getY() + 0.5D, sievePos.getZ() + 0.5D, stackList.get(0)));
		            	continueCheck = false;
		            }
				}
			}
			
			if(continueCheck && item instanceof ItemFood && ((ItemFood)item).isWolfsFavoriteMeat())
			{
				List<EntityWolf> list = worldIn.getEntitiesWithinAABB(EntityWolf.class, new AxisAlignedBB(pos1));
				
				if(!list.isEmpty())
				{
					EntityWolf wolf = null;
					int state = 0;
					
					for(int i = 0 ; i < list.size() ; i++)
					{
						if(list.get(i).isTamed())
						{
							if(list.get(i).getHealth() < list.get(i).getMaxHealth())
							{
								wolf = list.get(i);
								break;
							}
							if(list.get(i).isChild())
							{
								state = 1;
								wolf = list.get(i);
								break;
							}
							else if(list.get(i).getGrowingAge() == 0 && !list.get(i).isInLove())
							{
								state = 2;
								wolf = list.get(i);
								break;
							}
						}
					}
					
					if(wolf != null)
					{
						continueCheck = false;
						
						if(state == 0)
							wolf.heal(((ItemFood)item).getHealAmount(stack));
						else if(state == 2)
							wolf.setInLove(null);
						else if(state == 1)
							wolf.ageUp((int)((float)(-wolf.getGrowingAge() / 20) * 0.1F), true);
						
						te.decrStackSize(0, 1);
					}
				}
			}
			
			if(continueCheck && (item == Items.CARROT || item == Items.POTATO || item == Items.BEETROOT))
			{
		        List<EntityPig> list = worldIn.<EntityPig>getEntitiesWithinAABB(EntityPig.class, new AxisAlignedBB(pos1));

		        if (!list.isEmpty())
		        {
		        	EntityPig pig = null;
		        	boolean child = false;
		        	
		        	for(int i = 0 ; i < list.size() ; i++)
		        	{
		        		if(!list.get(i).isChild() && list.get(i).getGrowingAge() == 0 && !list.get(i).isInLove())
		        		{
		        			pig = list.get(i);
			        		break;
		        		}
		        		else if(list.get(i).isChild())
		        		{
		        			pig = list.get(i);
		        			child = true;
		        			break;
		        		}
		        	}

		        	if(pig != null)
		        	{
		        		if(child)
		        			pig.ageUp((int)((float)(-pig.getGrowingAge() / 20) * 0.1F), true);
		        		else
		        			pig.setInLove(null);
		        		
		                te.decrStackSize(0, 1);
		                continueCheck = false;
		        	}
		        }
			}
			
			if(continueCheck && (item == Items.CARROT || item == Items.GOLDEN_CARROT || Block.getBlockFromItem(item) == Blocks.YELLOW_FLOWER || item == Items.BEETROOT))
			{
		        List<EntityRabbit> list = worldIn.<EntityRabbit>getEntitiesWithinAABB(EntityRabbit.class, new AxisAlignedBB(pos1));

		        if (!list.isEmpty())
		        {
		        	EntityRabbit rabbit = null;
		        	boolean child = false;
		        	
		        	for(int i = 0 ; i < list.size() ; i++)
		        	{
		        		if(!list.get(i).isChild() && list.get(i).getGrowingAge() == 0 && !list.get(i).isInLove())
		        		{
		        			rabbit = list.get(i);
			        		break;
		        		}
		        		else if(list.get(i).isChild())
		        		{
		        			rabbit = list.get(i);
		        			child = true;
		        			break;
		        		}
		        	}

		        	if(rabbit != null)
		        	{
		        		if(child)
		        			rabbit.ageUp((int)((float)(-rabbit.getGrowingAge() / 20) * 0.1F), true);
		        		else
		        			rabbit.setInLove(null);
		        		
		                te.decrStackSize(0, 1);
		                continueCheck = false;
		        	}
		        }
			}
			
			if(continueCheck)
			{
				if(item instanceof ItemBlock)
				{
					if(Block.getBlockFromItem(item) instanceof BlockStairs && facing.getAxis() != EnumFacing.Axis.Y && checkPlacability(worldIn, pos1, pos2, facing, stack, item, placer))
					{
						if(worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock().isReplaceable(worldIn, pos2))
						{
							worldIn.setBlockState(pos2, Block.getBlockFromItem(item).getDefaultState().withProperty(BlockStairs.FACING, facing));
							playPlaceSound(worldIn, pos2);
						}
						else
						{
							worldIn.setBlockState(pos1, Block.getBlockFromItem(item).getDefaultState().withProperty(BlockStairs.FACING, facing));
							playPlaceSound(worldIn, pos1);
						}
						
						te.decrStackSize(0, 1);
					}
					else if(Block.getBlockFromItem(item) instanceof BlockGlazedTerracotta && facing.getAxis() != EnumFacing.Axis.Y && checkPlacability(worldIn, pos1, pos2, facing, stack, item, placer))
					{
						if(worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock().isReplaceable(worldIn, pos2))
						{
							worldIn.setBlockState(pos2, Block.getBlockFromItem(item).getDefaultState().withProperty(BlockGlazedTerracotta.FACING, facing.getOpposite()));
							playPlaceSound(worldIn, pos2);
						}
						else
						{
							worldIn.setBlockState(pos1, Block.getBlockFromItem(item).getDefaultState().withProperty(BlockGlazedTerracotta.FACING, facing.getOpposite()));
							playPlaceSound(worldIn, pos1);
						}
							
						te.decrStackSize(0, 1);
					}
					else if(Block.getBlockFromItem(item) instanceof BlockFenceGate && facing.getAxis() != EnumFacing.Axis.Y && checkPlacability(worldIn, pos1, pos2, facing, stack, item, placer))
					{
						if(worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock().isReplaceable(worldIn, pos2))
						{
							worldIn.setBlockState(pos2, Block.getBlockFromItem(item).getDefaultState().withProperty(BlockFenceGate.FACING, facing.getOpposite()));
							playPlaceSound(worldIn, pos2);
						}
						else
						{
							worldIn.setBlockState(pos1, Block.getBlockFromItem(item).getDefaultState().withProperty(BlockFenceGate.FACING, facing.getOpposite()));
							playPlaceSound(worldIn, pos1);
						}
						
						te.decrStackSize(0, 1);
					}
					else if(Block.getBlockFromItem(item) instanceof BlockPistonBase && checkPlacability(worldIn, pos1, pos2, facing, stack, item, placer))
					{
						if(worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock().isReplaceable(worldIn, pos2))
						{
							worldIn.setBlockState(pos2, Block.getBlockFromItem(item).getDefaultState().withProperty(BlockPistonBase.FACING, facing));
							playPlaceSound(worldIn, pos2);
						}
						else
						{
							worldIn.setBlockState(pos1, Block.getBlockFromItem(item).getDefaultState().withProperty(BlockPistonBase.FACING, facing));
							playPlaceSound(worldIn, pos1);
						}
						
						te.decrStackSize(0, 1);
					}
					else if(Block.getBlockFromItem(item) instanceof BlockObserver && checkPlacability(worldIn, pos1, pos2, facing, stack, item, placer))
					{
						if(worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock().isReplaceable(worldIn, pos2))
						{
							worldIn.setBlockState(pos2, Block.getBlockFromItem(item).getDefaultState().withProperty(BlockObserver.FACING, facing));
							playPlaceSound(worldIn, pos2);
						}
						else
						{
							worldIn.setBlockState(pos1, Block.getBlockFromItem(item).getDefaultState().withProperty(BlockObserver.FACING, facing));
							playPlaceSound(worldIn, pos1);
						}
						
						te.decrStackSize(0, 1);
					}
					else if(Block.getBlockFromItem(item) instanceof BlockDoublePlant && checkPlacability(worldIn, pos1, pos2, facing, stack, item, placer))
					{
						if(worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock().isReplaceable(worldIn, pos2) && worldIn.getBlockState(pos2.up()).getBlock().isReplaceable(worldIn, pos2.up()))
						{
							worldIn.setBlockState(pos2, Block.getBlockFromItem(item).getStateFromMeta(stack.getMetadata()).withProperty(BlockDoublePlant.HALF, BlockDoublePlant.EnumBlockHalf.LOWER));
							worldIn.setBlockState(pos2.up(), Block.getBlockFromItem(item).getStateFromMeta(stack.getMetadata()).withProperty(BlockDoublePlant.HALF, BlockDoublePlant.EnumBlockHalf.UPPER));
							playPlaceSound(worldIn, pos2);
						}
						else
						{
							worldIn.setBlockState(pos1, Block.getBlockFromItem(item).getStateFromMeta(stack.getMetadata()).withProperty(BlockDoublePlant.HALF, BlockDoublePlant.EnumBlockHalf.LOWER));
							worldIn.setBlockState(pos1.up(), Block.getBlockFromItem(item).getStateFromMeta(stack.getMetadata()).withProperty(BlockDoublePlant.HALF, BlockDoublePlant.EnumBlockHalf.UPPER));
							playPlaceSound(worldIn, pos1);
						}
						
						te.decrStackSize(0, 1);
					}
					else if(Block.getBlockFromItem(item) == Blocks.SNOW_LAYER && ((worldIn.getBlockState(pos1).getBlock() == Blocks.SNOW_LAYER && worldIn.getBlockState(pos1).getValue(BlockSnow.LAYERS).intValue() < 8) || (worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock() == Blocks.SNOW_LAYER && worldIn.getBlockState(pos2).getValue(BlockSnow.LAYERS).intValue() < 8)))
					{
						if(!worldIn.isAirBlock(pos1))
						{
							worldIn.setBlockState(pos1, Blocks.SNOW_LAYER.getDefaultState().withProperty(BlockSnow.LAYERS, worldIn.getBlockState(pos1).getValue(BlockSnow.LAYERS).intValue() + 1));
							playPlaceSound(worldIn, pos1);
						}
						else
						{
							worldIn.setBlockState(pos2, Blocks.SNOW_LAYER.getDefaultState().withProperty(BlockSnow.LAYERS, worldIn.getBlockState(pos2).getValue(BlockSnow.LAYERS).intValue() + 1));
							playPlaceSound(worldIn, pos2);
						}
							
						te.decrStackSize(0, 1);
					}
					else if(Block.getBlockFromItem(item) instanceof BlockAnvil && checkPlacability(worldIn, pos1, pos2, facing, stack, item, placer))
					{
						if(worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock().isReplaceable(worldIn, pos2))
						{
							worldIn.setBlockState(pos2, Block.getBlockFromItem(item).getDefaultState().withProperty(BlockAnvil.DAMAGE, stack.getMetadata()).withProperty(BlockAnvil.FACING, facing.rotateY()));
							playPlaceSound(worldIn, pos2);
						}
						else
						{
							worldIn.setBlockState(pos1, Block.getBlockFromItem(item).getDefaultState().withProperty(BlockAnvil.DAMAGE, stack.getMetadata()).withProperty(BlockAnvil.FACING, facing.rotateY()));
							playPlaceSound(worldIn, pos1);
						}
							
						te.decrStackSize(0, 1);
					}
					else if(Block.getBlockFromItem(item) == Blocks.HAY_BLOCK)
					{
						List<EntityHorse> horses = worldIn.<EntityHorse>getEntitiesWithinAABB(EntityHorse.class, new AxisAlignedBB(pos1));
	
				        if (!horses.isEmpty())
				        {
				        	EntityHorse horse = horses.get(0);
				        	boolean child = false;
				        	boolean flag = false;
				        	
				        	if(horse.isChild())
				        		child = true;
	
				        	if (horse.getHealth() < horse.getMaxHealth())
				            {
				                horse.heal(20F);
				                flag = true;
				            }
	
				            if (horse.isChild())
				            {
				                worldIn.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, horse.posX + (double)(worldIn.rand.nextFloat() * horse.width * 2.0F) - (double)horse.width, horse.posY + 0.5D + (double)(worldIn.rand.nextFloat() * horse.height), horse.posZ + (double)(worldIn.rand.nextFloat() * horse.width * 2.0F) - (double)horse.width, 0.0D, 0.0D, 0.0D);
	
				                if (!worldIn.isRemote)
				                {
				                    horse.addGrowth(180);
				                }
	
				                flag = true;
				            }
	
				            if (flag)
				            {
				            	horse.setEatingHaystack(true);
				            	te.decrStackSize(0, 1);
				            }
				        }
				        else if(checkPlacability(worldIn, pos1, pos2, facing, stack, item, placer))
						{
							if(worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock().isReplaceable(worldIn, pos2))
							{
								worldIn.setBlockState(pos2, Block.getBlockFromItem(item).getStateForPlacement(worldIn, pos2, facing.getOpposite(), 0.5F, 0.5F, 0.5F, stack.getMetadata(), placer, EnumHand.MAIN_HAND));
								playPlaceSound(worldIn, pos2);
							}
							else
							{
								worldIn.setBlockState(pos1, Block.getBlockFromItem(item).getStateForPlacement(worldIn, pos1, facing.getOpposite(), 0.5F, 0.5F, 0.5F, stack.getMetadata(), placer, EnumHand.MAIN_HAND));
								playPlaceSound(worldIn, pos1);
							}
								
							te.decrStackSize(0, 1);
						}
					}
					else if(Block.getBlockFromItem(item) == MBlocks.rope)
					{
						boolean extend = false;
						
						if(worldIn.getBlockState(pos1).getBlock() == MBlocks.rope || (worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock() == MBlocks.rope))
						{
							int extension = 0;
							BlockPos ropePos = pos2;
							
							if(!worldIn.isAirBlock(pos1))
								ropePos = pos1;
							
							for(int i = 0 ; worldIn.getBlockState(ropePos.down(i)).getBlock() == MBlocks.rope ; i++)
							{
								if(worldIn.getBlockState(ropePos.down(i + 1)).getBlock().isReplaceable(worldIn, ropePos.down(i + 1)))
								{
									extension = i + 1;
									break;
								}
							}
							
							if(extension > 0)
							{
								worldIn.setBlockState(ropePos.down(extension), Block.getBlockFromItem(item).getStateForPlacement(worldIn, ropePos.down(extension), facing.getOpposite(), 0.5F, 0.5F, 0.5F, stack.getMetadata(), placer, EnumHand.MAIN_HAND));
								playPlaceSound(worldIn, ropePos);
								extend = true;
								te.decrStackSize(0, 1);
							}
						}
						
						if(!extend && checkPlacability(worldIn, pos1, pos2, facing, stack, item, placer))
						{
							if(worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock().isReplaceable(worldIn, pos2))
							{
								worldIn.setBlockState(pos2, Block.getBlockFromItem(item).getStateForPlacement(worldIn, pos2, facing.getOpposite(), 0.5F, 0.5F, 0.5F, stack.getMetadata(), placer, EnumHand.MAIN_HAND));
								playPlaceSound(worldIn, pos2);
							}
							else
							{
								worldIn.setBlockState(pos1, Block.getBlockFromItem(item).getStateForPlacement(worldIn, pos1, facing.getOpposite(), 0.5F, 0.5F, 0.5F, stack.getMetadata(), placer, EnumHand.MAIN_HAND));
								playPlaceSound(worldIn, pos1);
							}
								
							te.decrStackSize(0, 1);
						}
					}
					else if(Block.getBlockFromItem(item) == MBlocks.dimensium_rope && checkPlacability(worldIn, pos1, pos2, facing, stack, item, placer))
					{
						if(worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock().isReplaceable(worldIn, pos2))
						{
							for(int i = 0 ; worldIn.getBlockState(pos2.up(i)).getBlock().isReplaceable(worldIn, pos2.up(i)) ; i++)
							{
								worldIn.setBlockState(pos2.up(i), Block.getBlockFromItem(item).getStateForPlacement(worldIn, pos2, facing.getOpposite(), 0.5F, 0.5F, 0.5F, stack.getMetadata(), placer, EnumHand.MAIN_HAND));
							}
							for(int i = 1 ; worldIn.getBlockState(pos2.down(i)).getBlock().isReplaceable(worldIn, pos2.down(i)) ; i++)
							{
								worldIn.setBlockState(pos2.down(i), Block.getBlockFromItem(item).getStateForPlacement(worldIn, pos2, facing.getOpposite(), 0.5F, 0.5F, 0.5F, stack.getMetadata(), placer, EnumHand.MAIN_HAND));
							}
							
							playPlaceSound(worldIn, pos2);
						}
						else
						{
							for(int i = 0 ; worldIn.getBlockState(pos1.up(i)).getBlock().isReplaceable(worldIn, pos1.up(i)) ; i++)
							{
								worldIn.setBlockState(pos1.up(i), Block.getBlockFromItem(item).getStateForPlacement(worldIn, pos2, facing.getOpposite(), 0.5F, 0.5F, 0.5F, stack.getMetadata(), placer, EnumHand.MAIN_HAND));
							}
							for(int i = 1 ; worldIn.getBlockState(pos1.down(i)).getBlock().isReplaceable(worldIn, pos1.down(i)) ; i++)
							{
								worldIn.setBlockState(pos1.down(i), Block.getBlockFromItem(item).getStateForPlacement(worldIn, pos1, facing.getOpposite(), 0.5F, 0.5F, 0.5F, stack.getMetadata(), placer, EnumHand.MAIN_HAND));
							}
							
							playPlaceSound(worldIn, pos1);
						}
							
						te.decrStackSize(0, 1);
					}
					else if(Block.getBlockFromItem(item) instanceof BlockJackOLantern && facing.getAxis() != EnumFacing.Axis.Y && checkPlacability(worldIn, pos1, pos2, facing, stack, item, placer))
					{
						if(Block.getBlockFromItem(item) instanceof BlockJackOLanternSmashed)
						{
							if(worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock().isReplaceable(worldIn, pos2))
							{
								worldIn.setBlockState(pos2, Block.getBlockFromItem(item).getDefaultState().withProperty(BlockJackOLantern.FACING, facing.getOpposite()));
								playPlaceSound(worldIn, pos2);
							}
							else
							{
								worldIn.setBlockState(pos1, Block.getBlockFromItem(item).getDefaultState().withProperty(BlockJackOLantern.FACING, facing.getOpposite()));
								playPlaceSound(worldIn, pos1);
							}
						}
						else
						{
							if(worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock().isReplaceable(worldIn, pos2))
							{
								worldIn.setBlockState(pos2, Block.getBlockFromItem(item).getDefaultState().withProperty(BlockJackOLantern.FACING, facing.getOpposite()).withProperty(BlockJackOLantern.VARIANT, BlockJackOLantern.EnumFaceType.byMetadata(stack.getMetadata())));
								playPlaceSound(worldIn, pos2);
							}
							else
							{
								worldIn.setBlockState(pos1, Block.getBlockFromItem(item).getDefaultState().withProperty(BlockJackOLantern.FACING, facing.getOpposite()).withProperty(BlockJackOLantern.VARIANT, BlockJackOLantern.EnumFaceType.byMetadata(stack.getMetadata())));
								playPlaceSound(worldIn, pos1);
							}
						}
						
						te.decrStackSize(0, 1);
					}
					else if(Block.getBlockFromItem(item) == MBlocks.glow_paste && checkPlacability(worldIn, pos1, pos2, facing, stack, item, placer))
					{
						if(worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock().isReplaceable(worldIn, pos2))
						{
							worldIn.setBlockState(pos2, Block.getBlockFromItem(item).getStateForPlacement(worldIn, pos2, facing.getOpposite(), 0.5F, 0.5F, 0.5F, stack.getMetadata(), placer, EnumHand.MAIN_HAND));
							playPlaceSound(worldIn, pos2);
							worldIn.spawnEntity(new EntityItem(worldIn, pos2.getX() + 0.5D, pos2.getY() + 0.5D, pos2.getZ() + 0.5D, new ItemStack(Items.GLASS_BOTTLE)));
						}
						else
						{
							worldIn.setBlockState(pos1, Block.getBlockFromItem(item).getStateForPlacement(worldIn, pos1, facing.getOpposite(), 0.5F, 0.5F, 0.5F, stack.getMetadata(), placer, EnumHand.MAIN_HAND));
							playPlaceSound(worldIn, pos1);
							worldIn.spawnEntity(new EntityItem(worldIn, pos1.getX() + 0.5D, pos1.getY() + 0.5D, pos1.getZ() + 0.5D, new ItemStack(Items.GLASS_BOTTLE)));
						}
							
						te.decrStackSize(0, 1);
					}
					else if(checkPlacability(worldIn, pos1, pos2, facing, stack, item, placer))
					{
						if(worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock().isReplaceable(worldIn, pos2))
						{
							worldIn.setBlockState(pos2, Block.getBlockFromItem(item).getStateForPlacement(worldIn, pos2, facing.getOpposite(), 0.5F, 0.5F, 0.5F, stack.getMetadata(), placer, EnumHand.MAIN_HAND));
							playPlaceSound(worldIn, pos2);
						}
						else
						{
							worldIn.setBlockState(pos1, Block.getBlockFromItem(item).getStateForPlacement(worldIn, pos1, facing.getOpposite(), 0.5F, 0.5F, 0.5F, stack.getMetadata(), placer, EnumHand.MAIN_HAND));
							playPlaceSound(worldIn, pos1);
						}
							
						te.decrStackSize(0, 1);
					}
				}
				else if(item instanceof ItemRedstone && worldIn.getBlockState(pos1).getBlock().isReplaceable(worldIn, pos1) && (!worldIn.isAirBlock(pos1) || !worldIn.isAirBlock(pos2)) && (worldIn.getBlockState(pos1.down()).isTopSolid() || (worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock().isReplaceable(worldIn, pos2) && worldIn.getBlockState(pos2.down()).isTopSolid())))
				{
					if(worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock().isReplaceable(worldIn, pos2))
					{
						worldIn.setBlockState(pos2, Blocks.REDSTONE_WIRE.getDefaultState());
						playPlaceSound(worldIn, pos2);
					}
					else
					{
						worldIn.setBlockState(pos1, Blocks.REDSTONE_WIRE.getDefaultState());
						playPlaceSound(worldIn, pos1);
					}
					
					te.decrStackSize(0, 1);
				}
				else if(item == Items.STRING && checkPlacability(worldIn, pos1, pos2, facing, new ItemStack(new ItemBlock(Blocks.TRIPWIRE)), new ItemBlock(Blocks.TRIPWIRE), placer))
				{
					if(worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock().isReplaceable(worldIn, pos2))
					{
						worldIn.setBlockState(pos2, Blocks.TRIPWIRE.getDefaultState());
						playPlaceSound(worldIn, pos2);
					}
					else
					{
						worldIn.setBlockState(pos1, Blocks.TRIPWIRE.getDefaultState());
						playPlaceSound(worldIn, pos1);
					}
					
					te.decrStackSize(0, 1);
				}
				else if(item == Items.REEDS && checkPlacability(worldIn, pos1, pos2, facing, new ItemStack(new ItemBlock(Blocks.REEDS)), new ItemBlock(Blocks.REEDS), placer))
				{
					if(worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock().isReplaceable(worldIn, pos2))
					{
						worldIn.setBlockState(pos2, Blocks.REEDS.getDefaultState());
						playPlaceSound(worldIn, pos2);
					}
					else
					{
						worldIn.setBlockState(pos1, Blocks.REEDS.getDefaultState());
						playPlaceSound(worldIn, pos1);
					}
					
					te.decrStackSize(0, 1);
				}
				else if(item == Items.CAKE && checkPlacability(worldIn, pos1, pos2, facing, new ItemStack(new ItemBlock(Blocks.CAKE)), new ItemBlock(Blocks.CAKE), placer))
				{
					if(worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock().isReplaceable(worldIn, pos2))
					{
						worldIn.setBlockState(pos2, Blocks.CAKE.getDefaultState());
						playPlaceSound(worldIn, pos2);
					}
					else
					{
						worldIn.setBlockState(pos1, Blocks.CAKE.getDefaultState());
						playPlaceSound(worldIn, pos1);
					}
					
					te.decrStackSize(0, 1);
				}
				else if(item == Items.FIRE_CHARGE && worldIn.isAirBlock(pos1) && !worldIn.isAirBlock(pos2))
				{
					worldIn.setBlockState(pos1, Blocks.FIRE.getDefaultState());
					worldIn.playSound((EntityPlayer)null, pos1, SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS, 1.0F, (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.2F + 1.0F);
					te.decrStackSize(0, 1);
				}
				else if(item == Items.BUCKET)
				{
					List<EntityCow> list = worldIn.<EntityCow>getEntitiesWithinAABB(EntityCow.class, new AxisAlignedBB(pos1));
	
			        if (!list.isEmpty())
			        {
			        	EntityCow cow = null;
			        	
			        	for(int i = 0 ; i < list.size() ; i++)
			        	{
			        		if(!list.get(i).isChild() && !(list.get(i) instanceof EntityMooshroom))
			        		{
			        			cow = list.get(i);
			        			break;
			        		}
			        	}
	
			        	if(cow != null)
			        	{
			                cow.world.playSound(null, cow.posX, cow.posY, cow.posZ, SoundEvents.ENTITY_COW_MILK, SoundCategory.NEUTRAL, 0.5F, 1.0F);
			                worldIn.spawnEntity(new EntityItem(worldIn, pos1.getX() + 0.5D, pos1.getY() + 0.5D, pos1.getZ() + 0.5D, new ItemStack(Items.MILK_BUCKET)));
			                te.decrStackSize(0, 1);
			        	}
			        }
			        else
			        {
			        	Block block1 = worldIn.getBlockState(pos1).getBlock();
			        	Block block2 = worldIn.getBlockState(pos2).getBlock();
			        	
			        	if(block1 == Blocks.WATER && ((Integer)worldIn.getBlockState(pos1).getValue(BlockLiquid.LEVEL)).intValue() == 0)
			        	{
			        		worldIn.setBlockToAir(pos1);
			        		worldIn.spawnEntity(new EntityItem(worldIn, pos1.getX() + 0.5D, pos1.getY() + 0.5D, pos1.getZ() + 0.5D, new ItemStack(Items.WATER_BUCKET)));
			        		te.decrStackSize(0, 1);
			        	}
			        	else if(block1 == Blocks.LAVA && ((Integer)worldIn.getBlockState(pos1).getValue(BlockLiquid.LEVEL)).intValue() == 0)
			        	{
			        		worldIn.setBlockToAir(pos1);
			        		worldIn.spawnEntity(new EntityItem(worldIn, pos1.getX() + 0.5D, pos1.getY() + 0.5D, pos1.getZ() + 0.5D, new ItemStack(Items.LAVA_BUCKET)));
			        		te.decrStackSize(0, 1);
			        	}
			        	else if(block1 == MBlocks.liquid_crystalfloe && ((BlockFluidClassic)block1).isSourceBlock(worldIn, pos1))
			        	{
			        		worldIn.setBlockToAir(pos1);
			        		worldIn.spawnEntity(new EntityItem(worldIn, pos1.getX() + 0.5D, pos1.getY() + 0.5D, pos1.getZ() + 0.5D, FluidUtil.getFilledBucket(new FluidStack(MFluids.crystalfloe, 0))));
			        		te.decrStackSize(0, 1);
			        	}
			        	else if(worldIn.isAirBlock(pos1))
			        	{
			        		if(block2 == Blocks.WATER && ((Integer)worldIn.getBlockState(pos2).getValue(BlockLiquid.LEVEL)).intValue() == 0)
				        	{
				        		worldIn.setBlockToAir(pos2);
				        		worldIn.spawnEntity(new EntityItem(worldIn, pos1.getX() + 0.5D, pos1.getY() + 0.5D, pos1.getZ() + 0.5D, new ItemStack(Items.WATER_BUCKET)));
				        		te.decrStackSize(0, 1);
				        	}
				        	else if(block2 == Blocks.LAVA && ((Integer)worldIn.getBlockState(pos2).getValue(BlockLiquid.LEVEL)).intValue() == 0)
				        	{
				        		worldIn.setBlockToAir(pos2);
				        		worldIn.spawnEntity(new EntityItem(worldIn, pos1.getX() + 0.5D, pos1.getY() + 0.5D, pos1.getZ() + 0.5D, new ItemStack(Items.LAVA_BUCKET)));
				        		te.decrStackSize(0, 1);
				        	}
				        	else if(block2 == MBlocks.liquid_crystalfloe && ((BlockFluidClassic)block2).isSourceBlock(worldIn, pos2))
				        	{
				        		worldIn.setBlockToAir(pos2);
				        		worldIn.spawnEntity(new EntityItem(worldIn, pos1.getX() + 0.5D, pos1.getY() + 0.5D, pos1.getZ() + 0.5D, FluidUtil.getFilledBucket(new FluidStack(MFluids.crystalfloe, 0))));
				        		te.decrStackSize(0, 1);
				        	}
			        	}
			        }
				}
				else if(item == Items.WATER_BUCKET && checkPlacability(worldIn, pos1, pos2, facing, new ItemStack(new ItemBlock(Blocks.WATER)), new ItemBlock(Blocks.WATER), placer))
				{
					if(worldIn.isAirBlock(pos1) && ((ItemBucket)item).tryPlaceContainedLiquid(null, worldIn, pos2))
					{
						te.setInventorySlotContents(0, new ItemStack(Items.BUCKET));
					}
					else if(((ItemBucket)item).tryPlaceContainedLiquid(null, worldIn, pos1))
					{
						te.setInventorySlotContents(0, new ItemStack(Items.BUCKET));
					}
				}
				else if(item == Items.LAVA_BUCKET && checkPlacability(worldIn, pos1, pos2, facing, new ItemStack(new ItemBlock(Blocks.LAVA)), new ItemBlock(Blocks.LAVA), placer))
				{
					if(worldIn.isAirBlock(pos1) && ((ItemBucket)item).tryPlaceContainedLiquid(null, worldIn, pos2))
					{
						te.setInventorySlotContents(0, new ItemStack(Items.BUCKET));
					}
					else if(((ItemBucket)item).tryPlaceContainedLiquid(null, worldIn, pos1))
					{
						te.setInventorySlotContents(0, new ItemStack(Items.BUCKET));
					}
				}
				else if(item instanceof UniversalBucket && checkPlacability(worldIn, pos1, pos2, facing, new ItemStack(new ItemBlock(((UniversalBucket)item).getFluid(stack).getFluid().getBlock())), new ItemBlock(((UniversalBucket)item).getFluid(stack).getFluid().getBlock()), placer))
				{
					if(worldIn.isAirBlock(pos1) && FluidUtil.tryPlaceFluid(null, worldIn, pos2, stack, ((UniversalBucket)item).getFluid(stack)).isSuccess())
					{
						te.setInventorySlotContents(0, new ItemStack(Items.BUCKET));
					}
					else if(FluidUtil.tryPlaceFluid(null, worldIn, pos1, stack, ((UniversalBucket)item).getFluid(stack)).isSuccess())
					{
						te.setInventorySlotContents(0, new ItemStack(Items.BUCKET));
					}
				}
				else if(item instanceof ItemDoor && facing.getAxis() != EnumFacing.Axis.Y)
				{
					Block door = null;
					
					if(item instanceof ItemMDoor)
						door = ((ItemMDoor)item).getBlock();
					else if(item == Items.OAK_DOOR)
						door = Blocks.OAK_DOOR;
					else if(item == Items.SPRUCE_DOOR)
						door = Blocks.SPRUCE_DOOR;
					else if(item == Items.BIRCH_DOOR)
						door = Blocks.BIRCH_DOOR;
					else if(item == Items.JUNGLE_DOOR)
						door = Blocks.JUNGLE_DOOR;
					else if(item == Items.ACACIA_DOOR)
						door = Blocks.ACACIA_DOOR;
					else if(item == Items.DARK_OAK_DOOR)
						door = Blocks.DARK_OAK_DOOR;
					else if(item == Items.IRON_DOOR)
						door = Blocks.IRON_DOOR;
					
					if(door != null)
					{
						if(worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2.down()).isTopSolid() && worldIn.getBlockState(pos2).getBlock().isReplaceable(worldIn, pos2) && worldIn.getBlockState(pos2.up()).getBlock().isReplaceable(worldIn, pos2.up()))
						{
							((ItemDoor)item).placeDoor(worldIn, pos2, facing, door, false);
							playPlaceSound(worldIn, pos2);
							te.decrStackSize(0, 1);
						}
						else if(worldIn.getBlockState(pos1).getBlock().isReplaceable(worldIn, pos1) && !worldIn.isAirBlock(pos2) && worldIn.getBlockState(pos1.down()).isTopSolid() && worldIn.getBlockState(pos1.up()).getBlock().isReplaceable(worldIn, pos2.up()))
						{
							((ItemDoor)item).placeDoor(worldIn, pos1, facing, door, false);
							playPlaceSound(worldIn, pos1);
							te.decrStackSize(0, 1);
						}
					}
				}
				else if(item instanceof ItemHangingEntity && facing.getAxis() != EnumFacing.Axis.Y && worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock().isSideSolid(worldIn.getBlockState(pos2), worldIn, pos2, facing.getOpposite()))
				{
					EntityHanging entityhanging = null;
					if(item == Items.PAINTING)
						entityhanging = new EntityPainting(worldIn, pos1, facing.getOpposite());
					else if(item == Items.ITEM_FRAME)
						entityhanging = new EntityItemFrame(worldIn, pos1, facing.getOpposite());
	
		            if (entityhanging != null && entityhanging.onValidSurface())
		            {
		                if (!worldIn.isRemote)
		                {
		                    entityhanging.playPlaceSound();
		                    worldIn.spawnEntity(entityhanging);
		                }
	
		                te.decrStackSize(0, 1);
		            }
				}
				else if(item instanceof ItemArmorStand && facing.getAxis() != EnumFacing.Axis.Y && worldIn.getBlockState(pos1).getBlock().isReplaceable(worldIn, pos1) && (!worldIn.isAirBlock(pos1) || !worldIn.isAirBlock(pos2)))
				{
					boolean flag = worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock().isReplaceable(worldIn, pos2);
		            BlockPos blockpos = flag ? pos2 : pos1;
		            BlockPos blockpos1 = blockpos.up();
		            
	                boolean flag1 = !worldIn.isAirBlock(blockpos) && !worldIn.getBlockState(blockpos).getBlock().isReplaceable(worldIn, blockpos);
	                flag1 = flag1 | (!worldIn.isAirBlock(blockpos1) && !worldIn.getBlockState(blockpos1).getBlock().isReplaceable(worldIn, blockpos1));
	
	                if(!flag1)
	                {
	                    double d0 = (double)blockpos.getX();
	                    double d1 = (double)blockpos.getY();
	                    double d2 = (double)blockpos.getZ();
	                    List<Entity> list = worldIn.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(d0, d1, d2, d0 + 1.0D, d1 + 2.0D, d2 + 1.0D));
	
	                    if(list.isEmpty())
	                    {
	                        if (!worldIn.isRemote)
	                        {
	                            worldIn.setBlockToAir(blockpos);
	                            worldIn.setBlockToAir(blockpos1);
	                            EntityArmorStand entityarmorstand = new EntityArmorStand(worldIn, d0 + 0.5D, d1, d2 + 0.5D);
	                            float f = (float)MathHelper.floor((MathHelper.wrapDegrees(placer.rotationYaw) + 22.5F) / 45.0F) * 45.0F;
	                            entityarmorstand.setLocationAndAngles(d0 + 0.5D, d1, d2 + 0.5D, f, 0.0F);
	                            this.applyArmorStandRotations(entityarmorstand, worldIn.rand);
	                            ItemMonsterPlacer.applyItemEntityDataToEntity(worldIn, null, stack, entityarmorstand);
	                            worldIn.spawnEntity(entityarmorstand);
	                            worldIn.playSound((EntityPlayer)null, entityarmorstand.posX, entityarmorstand.posY, entityarmorstand.posZ, SoundEvents.ENTITY_ARMORSTAND_PLACE, SoundCategory.BLOCKS, 0.75F, 0.8F);
	                        }
	
	                        te.decrStackSize(0, 1);
	                    }
		            }
				}
				else if(item instanceof ItemMinecart)
				{
					BlockPos cartPos = null;
					if(BlockRailBase.isRailBlock(worldIn, pos1))
						cartPos = pos1;
					else if(worldIn.isAirBlock(pos1) && BlockRailBase.isRailBlock(worldIn, pos2))
						cartPos = pos2;
						
					if (cartPos != null && !worldIn.isRemote)
		            {
						EntityMinecart.Type type = EntityMinecart.Type.RIDEABLE;
						
						if(item == Items.CHEST_MINECART)
							type = EntityMinecart.Type.CHEST;
						else if(item == Items.FURNACE_MINECART)
							type = EntityMinecart.Type.FURNACE;
						else if(item == Items.HOPPER_MINECART)
							type = EntityMinecart.Type.HOPPER;
						else if(item == Items.TNT_MINECART)
							type = EntityMinecart.Type.TNT;
						else if(item == Items.COMMAND_BLOCK_MINECART)
							type = EntityMinecart.Type.COMMAND_BLOCK;
						
		                BlockRailBase.EnumRailDirection blockrailbase$enumraildirection = worldIn.getBlockState(cartPos).getBlock() instanceof BlockRailBase ? ((BlockRailBase)worldIn.getBlockState(cartPos).getBlock()).getRailDirection(worldIn, cartPos, worldIn.getBlockState(cartPos), null) : BlockRailBase.EnumRailDirection.NORTH_SOUTH;
		                double yOffset = 0.0D;
	
		                if (blockrailbase$enumraildirection.isAscending())
		                    yOffset = 0.5D;
	
		                EntityMinecart entityminecart = EntityMinecart.create(worldIn, (double)cartPos.getX() + 0.5D, (double)cartPos.getY() + 0.0625D + yOffset, (double)cartPos.getZ() + 0.5D, type);
	
		                if (stack.hasDisplayName())
		                {
		                    entityminecart.setCustomNameTag(stack.getDisplayName());
		                }
	
		                worldIn.spawnEntity(entityminecart);
		                te.decrStackSize(0, 1);
		            }
				}
				else if(item instanceof ItemBoat && worldIn.isAirBlock(pos1) && !worldIn.isAirBlock(pos2))
				{
					Vec3d vec3d = new Vec3d(pos1.getX(), pos1.getY(), pos1.getZ());
		            boolean flag = false;
		            List<Entity> list = worldIn.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(pos1));
	
		            for (int i = 0; i < list.size(); ++i)
		            {
		                Entity entity = list.get(i);
	
		                if (entity.canBeCollidedWith())
		                {
		                    AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().grow((double)entity.getCollisionBorderSize());
	
		                    if (axisalignedbb.contains(vec3d))
		                    {
		                        flag = true;
		                    }
		                }
		            }
	
		            if(!flag)
		            {
		                Block block = worldIn.getBlockState(pos1.down()).getBlock();
		                boolean flag1 = block == Blocks.WATER || block == Blocks.FLOWING_WATER;
		                EntityBoat entityboat = new EntityBoat(worldIn, pos1.getX() + 0.5D, flag1 ? pos1.getY() - 0.12D : pos1.getY(), pos1.getZ() + 0.5D);
		                entityboat.setBoatType(item == Items.BOAT ? EntityBoat.Type.OAK : item == Items.SPRUCE_BOAT ? EntityBoat.Type.SPRUCE : item == Items.BIRCH_BOAT ? EntityBoat.Type.BIRCH : item == Items.JUNGLE_BOAT ? EntityBoat.Type.JUNGLE : item == Items.ACACIA_BOAT ? EntityBoat.Type.ACACIA : EntityBoat.Type.DARK_OAK);
		                entityboat.rotationYaw = yaw;
	
		                if (worldIn.getCollisionBoxes(entityboat, entityboat.getEntityBoundingBox().grow(-0.1D)).isEmpty())
		                {
		                    if (!worldIn.isRemote)
		                        worldIn.spawnEntity(entityboat);
	
		                    te.decrStackSize(0, 1);
		                }
		            }
				}
				else if(item instanceof ItemMBoat && worldIn.isAirBlock(pos1) && !worldIn.isAirBlock(pos2))
				{
					Vec3d vec3d = new Vec3d(pos1.getX(), pos1.getY(), pos1.getZ());
		            boolean flag = false;
		            List<Entity> list = worldIn.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(pos1));
	
		            for (int i = 0; i < list.size(); ++i)
		            {
		                Entity entity = list.get(i);
	
		                if (entity.canBeCollidedWith())
		                {
		                    AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().grow((double)entity.getCollisionBorderSize());
	
		                    if (axisalignedbb.contains(vec3d))
		                    {
		                        flag = true;
		                    }
		                }
		            }
	
		            if(!flag)
		            {
		            	EntityMBoat.Type type = ((ItemMBoat)item).getBoatType();
		                Block block = worldIn.getBlockState(pos1.down()).getBlock();
		                boolean flag1 = (type != EntityMBoat.Type.CHARWOOD && (block == Blocks.WATER || block == Blocks.FLOWING_WATER)) || (type == EntityMBoat.Type.CHARWOOD && (block == Blocks.LAVA || block == Blocks.FLOWING_LAVA));
		                EntityMBoat entityboat = new EntityMBoat(worldIn, pos1.getX() + 0.5D, flag1 ? pos1.getY() - 0.12D : pos1.getY(), pos1.getZ() + 0.5D);
		                entityboat.setBoatType(type);
		                entityboat.rotationYaw = yaw;
	
		                if (worldIn.getCollisionBoxes(entityboat, entityboat.getEntityBoundingBox().grow(-0.1D)).isEmpty())
		                {
		                    if (!worldIn.isRemote)
		                        worldIn.spawnEntity(entityboat);
	
		                    te.decrStackSize(0, 1);
		                }
		            }
				}
				else if(item == Items.SADDLE)
				{
			        List<EntityPig> list = worldIn.<EntityPig>getEntitiesWithinAABB(EntityPig.class, new AxisAlignedBB(pos1));
	
			        if (!list.isEmpty())
			        {
			        	EntityPig pig = null;
			        	
			        	for(int i = 0 ; i < list.size() ; i++)
			        	{
			        		if(!list.get(i).getSaddled() && !list.get(i).isChild())
			        		{
			        			pig = list.get(i);
			        			break;
			        		}
			        	}
	
			        	if(pig != null)
			        	{
			        		pig.setSaddled(true);
			                pig.world.playSound(null, pig.posX, pig.posY, pig.posZ, SoundEvents.ENTITY_PIG_SADDLE, SoundCategory.NEUTRAL, 0.5F, 1.0F);
			                te.decrStackSize(0, 1);
			        	}
			        }
				}
				else if(item == Items.BOWL)
				{
			        List<EntityMooshroom> list = worldIn.<EntityMooshroom>getEntitiesWithinAABB(EntityMooshroom.class, new AxisAlignedBB(pos1));
	
			        if (!list.isEmpty())
			        {
			        	EntityMooshroom mooshroom = null;
			        	
			        	for(int i = 0 ; i < list.size() ; i++)
			        	{
			        		if(!list.get(i).isChild())
			        		{
			        			mooshroom = list.get(i);
			        			break;
			        		}
			        	}
	
			        	if(mooshroom != null)
			        	{
			                mooshroom.world.playSound(null, mooshroom.posX, mooshroom.posY, mooshroom.posZ, SoundEvents.ENTITY_COW_MILK, SoundCategory.NEUTRAL, 0.5F, 1.0F);
			                worldIn.spawnEntity(new EntityItem(worldIn, pos1.getX() + 0.5D, pos1.getY() + 0.5D, pos1.getZ() + 0.5D, new ItemStack(Items.MUSHROOM_STEW)));
			                te.decrStackSize(0, 1);
			        	}
			        }
				}
				else if(item == Items.NETHER_WART && checkPlacability(worldIn, pos1, pos2, facing, new ItemStack(new ItemBlock(Blocks.NETHER_WART)), new ItemBlock(Blocks.NETHER_WART), placer))
				{
					if(worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock().isReplaceable(worldIn, pos2))
					{
						worldIn.setBlockState(pos2, Blocks.NETHER_WART.getDefaultState());
						playPlaceSound(worldIn, pos2);
					}
					else
					{
						worldIn.setBlockState(pos1, Blocks.NETHER_WART.getDefaultState());
						playPlaceSound(worldIn, pos1);
					}
		        	
		        	te.decrStackSize(0, 1);
				}
				else if(item == Items.WHEAT_SEEDS || item == Items.PUMPKIN_SEEDS || item == Items.MELON_SEEDS || item == Items.BEETROOT_SEEDS)
				{
			        List<EntityChicken> list = worldIn.<EntityChicken>getEntitiesWithinAABB(EntityChicken.class, new AxisAlignedBB(pos1));
	
			        if (!list.isEmpty())
			        {
			        	EntityChicken chicken = null;
			        	boolean child = false;
			        	
			        	for(int i = 0 ; i < list.size() ; i++)
			        	{
			        		if(!list.get(i).isChild() && list.get(i).getGrowingAge() == 0 && !list.get(i).isInLove())
			        		{
			        			chicken = list.get(i);
				        		break;
			        		}
			        		else if(list.get(i).isChild())
			        		{
			        			chicken = list.get(i);
			        			child = true;
			        			break;
			        		}
			        	}
	
			        	if(chicken != null)
			        	{
			        		if(child)
			        			chicken.ageUp((int)((float)(-chicken.getGrowingAge() / 20) * 0.1F), true);
			        		else
			        			chicken.setInLove(null);
			        		
			                te.decrStackSize(0, 1);
			        	}
			        }
			        else
			        {
			        	Block crop = Blocks.WHEAT;
			        	
			        	if(item == Items.PUMPKIN_SEEDS)
			        		crop = Blocks.PUMPKIN_STEM;
			        	else if(item == Items.MELON_SEEDS)
			        		crop = Blocks.MELON_STEM;
			        	else if(item == Items.BEETROOT_SEEDS)
			        		crop = Blocks.BEETROOTS;
			        	
			        	if(checkPlacability(worldIn, pos1, pos2, facing, new ItemStack(new ItemBlock(crop)), new ItemBlock(crop), placer))
			        	{
				        	if(worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock().isReplaceable(worldIn, pos2))
							{
								worldIn.setBlockState(pos2, crop.getDefaultState());
								playPlaceSound(worldIn, pos2);
							}
							else
							{
								worldIn.setBlockState(pos1, crop.getDefaultState());
								playPlaceSound(worldIn, pos1);
							}
				        	
				        	te.decrStackSize(0, 1);
			        	}
			        }
				}
				else if(item == Items.WHEAT)
				{
					List<EntityCow> cows = worldIn.<EntityCow>getEntitiesWithinAABB(EntityCow.class, new AxisAlignedBB(pos1));
	
			        if (!cows.isEmpty())
			        {
			        	EntityCow cow = null;
			        	boolean child = false;
			        	
			        	for(int i = 0 ; i < cows.size() ; i++)
			        	{
			        		if(!cows.get(i).isChild() && cows.get(i).getGrowingAge() == 0 && !cows.get(i).isInLove())
			        		{
			        			cow = cows.get(i);
				        		break;
			        		}
			        		else if(cows.get(i).isChild())
			        		{
			        			cow = cows.get(i);
			        			child = true;
			        			break;
			        		}
			        	}
	
			        	if(cow != null)
			        	{
			        		if(child)
			        			cow.ageUp((int)((float)(-cow.getGrowingAge() / 20) * 0.1F), true);
			        		else
			        			cow.setInLove(null);
			        		
			                te.decrStackSize(0, 1);
			        	}
			        }
			        else
			        {
			        	List<EntitySheep> sheeps = worldIn.<EntitySheep>getEntitiesWithinAABB(EntitySheep.class, new AxisAlignedBB(pos1));
	
				        if (!sheeps.isEmpty())
				        {
				        	EntitySheep sheep = null;
				        	boolean child = false;
				        	
				        	for(int i = 0 ; i < sheeps.size() ; i++)
				        	{
				        		if(!sheeps.get(i).isChild() && sheeps.get(i).getGrowingAge() == 0 && !sheeps.get(i).isInLove())
				        		{
				        			sheep = sheeps.get(i);
					        		break;
				        		}
				        		else if(sheeps.get(i).isChild())
				        		{
				        			sheep = sheeps.get(i);
				        			child = true;
				        			break;
				        		}
				        	}
	
				        	if(sheep != null)
				        	{
				        		if(child)
				        			sheep.ageUp((int)((float)(-sheep.getGrowingAge() / 20) * 0.1F), true);
				        		else
				        			sheep.setInLove(null);
				        		
				                te.decrStackSize(0, 1);
				        	}
				        }
				        else
				        {
				        	List<EntityHorse> horses = worldIn.<EntityHorse>getEntitiesWithinAABB(EntityHorse.class, new AxisAlignedBB(pos1));
	
					        if (!horses.isEmpty())
					        {
					        	EntityHorse horse = horses.get(0);
					        	boolean child = false;
					        	boolean flag = false;
					        	
					        	if(horse.isChild())
					        		child = true;
	
					        	if (horse.getHealth() < horse.getMaxHealth())
					            {
					                horse.heal(2F);
					                flag = true;
					            }
	
					            if (horse.isChild())
					            {
					                worldIn.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, horse.posX + (double)(worldIn.rand.nextFloat() * horse.width * 2.0F) - (double)horse.width, horse.posY + 0.5D + (double)(worldIn.rand.nextFloat() * horse.height), horse.posZ + (double)(worldIn.rand.nextFloat() * horse.width * 2.0F) - (double)horse.width, 0.0D, 0.0D, 0.0D);
	
					                if (!worldIn.isRemote)
					                {
					                    horse.addGrowth(20);
					                }
	
					                flag = true;
					            }
	
					            if ((flag || !horse.isTame()) && horse.getTemper() < horse.getMaxTemper())
					            {
					                flag = true;
	
					                if (!worldIn.isRemote)
					                {
					                    horse.increaseTemper(3);
					                }
					            }
	
					            if (flag)
					            {
					            	horse.setEatingHaystack(true);
					            	te.decrStackSize(0, 1);
					            }
					        }
				        }
			        }
				}
				else if(item == Items.SNOWBALL)
				{
					if(worldIn.getBlockState(pos1).getBlock() == MBlocks.glacieric_ice_deposit || (worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock() == MBlocks.glacieric_ice_deposit))
					{
						worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (worldIn.rand.nextFloat() * 0.4F + 0.8F));
						
						if(worldIn.rand.nextInt(5) == 1)
						{
							if(worldIn.isAirBlock(pos1))
							{
								((BlockGlaciericIceDeposit)worldIn.getBlockState(pos2).getBlock()).updateTick(worldIn, pos2, worldIn.getBlockState(pos2), worldIn.rand);
								
								((BlockGlaciericIceDeposit)worldIn.getBlockState(pos2).getBlock()).spawnParticles(worldIn, pos2, worldIn.rand);
							}
							else
							{
								((BlockGlaciericIceDeposit)worldIn.getBlockState(pos1).getBlock()).updateTick(worldIn, pos1, worldIn.getBlockState(pos1), worldIn.rand);
							
								((BlockGlaciericIceDeposit)worldIn.getBlockState(pos1).getBlock()).spawnParticles(worldIn, pos1, worldIn.rand);
							}
						}
						
						te.decrStackSize(0, 1);
					}
					else if(worldIn.isAirBlock(pos1))
					{
						worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (worldIn.rand.nextFloat() * 0.4F + 0.8F));
	
				        if (!worldIn.isRemote)
				        {
				        	Double xOffset = 0.5D;
				        	Double yOffset = 0.5D;
				        	Double zOffset = 0.5D;
				        	Double xVel = 0D;
				        	Double yVel = 0D;
				        	Double zVel = 0D;
				        	
				        	Double offsetP = 1.05D;
				        	Double offsetN = -0.05D;
				        	
				        	if(facing == EnumFacing.UP)
				        	{
				        		yOffset = offsetP;
				        		yVel = 1.5D;
				        	}
				        	else if(facing == EnumFacing.DOWN)
				        	{
				        		yOffset = offsetN;
				        		yVel = -1.5D;
				        	}
				        	else if(facing == EnumFacing.NORTH)
				        	{
				        		zOffset = offsetN;
				        		zVel = -1.5D;
				        	}
				        	else if(facing == EnumFacing.SOUTH)
				        	{
				        		zOffset = offsetP;
				        		zVel = 1.5D;
				        	}
				        	else if(facing == EnumFacing.EAST)
				        	{
				        		xOffset = offsetP;
				        		xVel = 1.5D;
				        	}
				        	else
				        	{
				        		xOffset = offsetN;
				        		xVel = -1.5D;
				        	}
				        	
				            EntitySnowball entitysnowball = new EntitySnowball(worldIn, te.getPos().getX() + xOffset, te.getPos().getY() + yOffset, te.getPos().getZ() + zOffset);
				            entitysnowball.setVelocity(xVel, yVel, zVel);
				            worldIn.spawnEntity(entitysnowball);
				        }
				        
				        te.decrStackSize(0, 1);
					}
				}
				else if(item == Items.EGG && worldIn.isAirBlock(pos1))
				{
					worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_EGG_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (worldIn.rand.nextFloat() * 0.4F + 0.8F));
	
			        if (!worldIn.isRemote)
			        {
			        	Double xOffset = 0.5D;
			        	Double yOffset = 0.5D;
			        	Double zOffset = 0.5D;
			        	Double xVel = 0D;
			        	Double yVel = 0D;
			        	Double zVel = 0D;
			        	
			        	Double offsetP = 1.05D;
			        	Double offsetN = -0.05D;
			        	
			        	if(facing == EnumFacing.UP)
			        	{
			        		yOffset = offsetP;
			        		yVel = 1.5D;
			        	}
			        	else if(facing == EnumFacing.DOWN)
			        	{
			        		yOffset = offsetN;
			        		yVel = -1.5D;
			        	}
			        	else if(facing == EnumFacing.NORTH)
			        	{
			        		zOffset = offsetN;
			        		zVel = -1.5D;
			        	}
			        	else if(facing == EnumFacing.SOUTH)
			        	{
			        		zOffset = offsetP;
			        		zVel = 1.5D;
			        	}
			        	else if(facing == EnumFacing.EAST)
			        	{
			        		xOffset = offsetP;
			        		xVel = 1.5D;
			        	}
			        	else
			        	{
			        		xOffset = offsetN;
			        		xVel = -1.5D;
			        	}
			        	
			            EntityEgg entityegg = new EntityEgg(worldIn, te.getPos().getX() + xOffset, te.getPos().getY() + yOffset, te.getPos().getZ() + zOffset);
			            entityegg.setVelocity(xVel, yVel, zVel);
			            worldIn.spawnEntity(entityegg);
			        }
			        
			        te.decrStackSize(0, 1);
				}
				else if(item == Items.EXPERIENCE_BOTTLE && worldIn.isAirBlock(pos1))
				{
					worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_EXPERIENCE_BOTTLE_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (worldIn.rand.nextFloat() * 0.4F + 0.8F));
					
			        if (!worldIn.isRemote)
			        {
			        	Double xOffset = 0.5D;
			        	Double yOffset = 0.5D;
			        	Double zOffset = 0.5D;
			        	Double xVel = 0D;
			        	Double yVel = 0D;
			        	Double zVel = 0D;
			        	
			        	Double offsetP = 1.05D;
			        	Double offsetN = -0.05D;
			        	
			        	if(facing == EnumFacing.UP)
			        	{
			        		yOffset = offsetP;
			        		yVel = 0.7D;
			        	}
			        	else if(facing == EnumFacing.DOWN)
			        	{
			        		yOffset = offsetN;
			        		yVel = -0.7D;
			        	}
			        	else if(facing == EnumFacing.NORTH)
			        	{
			        		zOffset = offsetN;
			        		zVel = -0.7D;
			        	}
			        	else if(facing == EnumFacing.SOUTH)
			        	{
			        		zOffset = offsetP;
			        		zVel = 0.7D;
			        	}
			        	else if(facing == EnumFacing.EAST)
			        	{
			        		xOffset = offsetP;
			        		xVel = 0.7D;
			        	}
			        	else
			        	{
			        		xOffset = offsetN;
			        		xVel = -0.7D;
			        	}
			        	
			            EntityExpBottle entityExp = new EntityExpBottle(worldIn, te.getPos().getX() + xOffset, te.getPos().getY() + yOffset, te.getPos().getZ() + zOffset);
			            entityExp.setVelocity(xVel, yVel, zVel);
			            worldIn.spawnEntity(entityExp);
			        }
			        
			        te.decrStackSize(0, 1);
				}
				else if(item == Items.DYE)
				{
					List<EntitySheep> list = worldIn.<EntitySheep>getEntitiesWithinAABB(EntitySheep.class, new AxisAlignedBB(pos1));
	
			        if (!list.isEmpty())
			        {
			        	EntitySheep sheep = null;
			        	
			        	for(int i = 0 ; i < list.size() ; i++)
			        	{
			        		if(!list.get(i).getSheared())
			        		{
			        			sheep = list.get(i);
				        		break;
			        		}
			        	}
	
			        	if(sheep != null)
			        	{
			        		sheep.setFleeceColor(EnumDyeColor.byDyeDamage(stack.getMetadata()));
			                te.decrStackSize(0, 1);
			        	}
			        }
			        else if(stack.getMetadata() == 3 && worldIn.getBlockState(pos1).getBlock().isReplaceable(worldIn, pos1))
			        {
			        	BlockPos placePos = null;
			        	
			        	if(!worldIn.isAirBlock(pos1) || !worldIn.getBlockState(pos2).getBlock().isReplaceable(worldIn, pos2))
			        		placePos = pos1;
			        	else if(!worldIn.isAirBlock(pos2) && worldIn.getBlockState(pos2).getBlock().isReplaceable(worldIn, pos2))
			        		placePos = pos2;
			        	
			        	if(placePos != null)
			        	{
				        	EnumFacing placeFace = null;
				        	
				        	if(facing.getAxis() != EnumFacing.Axis.Y && worldIn.getBlockState(placePos.offset(facing)).getBlock() == Blocks.LOG && worldIn.getBlockState(placePos.offset(facing)).getValue(BlockOldLog.VARIANT) == BlockPlanks.EnumType.JUNGLE)
				        		placeFace = facing;
				        	else
				        	{
				        		if(worldIn.getBlockState(placePos.offset(EnumFacing.NORTH)).getBlock() == Blocks.LOG && worldIn.getBlockState(placePos.offset(EnumFacing.NORTH)).getValue(BlockOldLog.VARIANT) == BlockPlanks.EnumType.JUNGLE)
									placeFace = EnumFacing.NORTH;
				        		else if(worldIn.getBlockState(placePos.offset(EnumFacing.EAST)).getBlock() == Blocks.LOG && worldIn.getBlockState(placePos.offset(EnumFacing.EAST)).getValue(BlockOldLog.VARIANT) == BlockPlanks.EnumType.JUNGLE)
									placeFace = EnumFacing.EAST;
				        		else if(worldIn.getBlockState(placePos.offset(EnumFacing.SOUTH)).getBlock() == Blocks.LOG && worldIn.getBlockState(placePos.offset(EnumFacing.SOUTH)).getValue(BlockOldLog.VARIANT) == BlockPlanks.EnumType.JUNGLE)
									placeFace = EnumFacing.SOUTH;
				        		else if(worldIn.getBlockState(placePos.offset(EnumFacing.WEST)).getBlock() == Blocks.LOG && worldIn.getBlockState(placePos.offset(EnumFacing.WEST)).getValue(BlockOldLog.VARIANT) == BlockPlanks.EnumType.JUNGLE)
									placeFace = EnumFacing.WEST;
				        	}
				        		
				        	if(placeFace != null)
				        	{
				        		worldIn.setBlockState(placePos, Blocks.COCOA.getDefaultState().withProperty(BlockCocoa.FACING, placeFace));
								playPlaceSound(worldIn, placePos);
								te.decrStackSize(0, 1);
				        	}
			        	}
			        }
			        else if(stack.getMetadata() == 15)
			        {
			        	BlockPos growPos = null;
			        	
			        	if(!worldIn.isAirBlock(pos1))
			        		growPos = pos1;
			        	else if(!worldIn.isAirBlock(pos2))
			        		growPos = pos2;
			        	
			        	if (growPos != null && ItemDye.applyBonemeal(stack.copy(), worldIn, growPos))
		                {
		                    if (!worldIn.isRemote)
		                        worldIn.playEvent(2005, growPos, 0);
		                    te.decrStackSize(0, 1);
		                }
			        }
				}
				else if(item == Items.SUGAR)
				{
					List<EntityHorse> horses = worldIn.<EntityHorse>getEntitiesWithinAABB(EntityHorse.class, new AxisAlignedBB(pos1));
	
			        if (!horses.isEmpty())
			        {
			        	EntityHorse horse = horses.get(0);
			        	boolean child = false;
			        	boolean flag = false;
			        	
			        	if(horse.isChild())
			        		child = true;
	
			        	if (horse.getHealth() < horse.getMaxHealth())
			            {
			                horse.heal(1F);
			                flag = true;
			            }
	
			            if (horse.isChild())
			            {
			                worldIn.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, horse.posX + (double)(worldIn.rand.nextFloat() * horse.width * 2.0F) - (double)horse.width, horse.posY + 0.5D + (double)(worldIn.rand.nextFloat() * horse.height), horse.posZ + (double)(worldIn.rand.nextFloat() * horse.width * 2.0F) - (double)horse.width, 0.0D, 0.0D, 0.0D);
	
			                if (!worldIn.isRemote)
			                {
			                    horse.addGrowth(30);
			                }
	
			                flag = true;
			            }
	
			            if ((flag || !horse.isTame()) && horse.getTemper() < horse.getMaxTemper())
			            {
			                flag = true;
	
			                if (!worldIn.isRemote)
			                {
			                    horse.increaseTemper(3);
			                }
			            }
	
			            if (flag)
			            {
			            	horse.setEatingHaystack(true);
			            	te.decrStackSize(0, 1);
			            }
			        }
				}
				else if(item == Items.APPLE)
				{
					List<EntityHorse> horses = worldIn.<EntityHorse>getEntitiesWithinAABB(EntityHorse.class, new AxisAlignedBB(pos1));
	
			        if (!horses.isEmpty())
			        {
			        	EntityHorse horse = horses.get(0);
			        	boolean child = false;
			        	boolean flag = false;
			        	
			        	if(horse.isChild())
			        		child = true;
	
			        	if (horse.getHealth() < horse.getMaxHealth())
			            {
			                horse.heal(3F);
			                flag = true;
			            }
	
			            if (horse.isChild())
			            {
			                worldIn.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, horse.posX + (double)(worldIn.rand.nextFloat() * horse.width * 2.0F) - (double)horse.width, horse.posY + 0.5D + (double)(worldIn.rand.nextFloat() * horse.height), horse.posZ + (double)(worldIn.rand.nextFloat() * horse.width * 2.0F) - (double)horse.width, 0.0D, 0.0D, 0.0D);
	
			                if (!worldIn.isRemote)
			                {
			                    horse.addGrowth(60);
			                }
	
			                flag = true;
			            }
	
			            if ((flag || !horse.isTame()) && horse.getTemper() < horse.getMaxTemper())
			            {
			                flag = true;
	
			                if (!worldIn.isRemote)
			                {
			                    horse.increaseTemper(3);
			                }
			            }
	
			            if (flag)
			            {
			            	horse.setEatingHaystack(true);
			            	te.decrStackSize(0, 1);
			            }
			        }
				}
				else if(item == Items.GOLDEN_APPLE)
				{
					List<EntityHorse> horses = worldIn.<EntityHorse>getEntitiesWithinAABB(EntityHorse.class, new AxisAlignedBB(pos1));
	
			        if (!horses.isEmpty())
			        {
			        	EntityHorse horse = horses.get(0);
			        	boolean child = false;
			        	boolean flag = false;
			        	
			        	if(horse.isChild())
			        		child = true;
	
			        	if (horse.getHealth() < horse.getMaxHealth())
			            {
			                horse.heal(10F);
			                flag = true;
			            }
	
			            if (horse.isChild())
			            {
			                worldIn.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, horse.posX + (double)(worldIn.rand.nextFloat() * horse.width * 2.0F) - (double)horse.width, horse.posY + 0.5D + (double)(worldIn.rand.nextFloat() * horse.height), horse.posZ + (double)(worldIn.rand.nextFloat() * horse.width * 2.0F) - (double)horse.width, 0.0D, 0.0D, 0.0D);
	
			                if (!worldIn.isRemote)
			                {
			                    horse.addGrowth(240);
			                }
	
			                flag = true;
			            }
			            else if(horse.isTame() && horse.getGrowingAge() == 0 && !horse.isInLove())
			            {
			            	flag = true;
			                horse.setInLove(null);
			            }
	
			            if ((flag || !horse.isTame()) && horse.getTemper() < horse.getMaxTemper())
			            {
			                flag = true;
	
			                if (!worldIn.isRemote)
			                {
			                    horse.increaseTemper(10);
			                }
			            }
	
			            if (flag)
			            {
			            	horse.setEatingHaystack(true);
			            	te.decrStackSize(0, 1);
			            }
			        }
				}
				else if(item == Items.ENDER_EYE)
				{
					if((worldIn.getBlockState(pos1).getBlock() == Blocks.END_PORTAL_FRAME && !worldIn.getBlockState(pos1).getValue(BlockEndPortalFrame.EYE).booleanValue()) || (worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock() == Blocks.END_PORTAL_FRAME && !worldIn.getBlockState(pos2).getValue(BlockEndPortalFrame.EYE).booleanValue()))
					{
						BlockPos framePos = pos1;
						
						if(worldIn.isAirBlock(pos1))
							framePos = pos2;
						
						worldIn.setBlockState(framePos, worldIn.getBlockState(framePos).withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(true)), 2);
		                worldIn.updateComparatorOutputLevel(framePos, Blocks.END_PORTAL_FRAME);
		
		                for (int i = 0; i < 16; ++i)
		                {
		                    double d0 = (double)((float)framePos.getX() + (5.0F + worldIn.rand.nextFloat() * 6.0F) / 16.0F);
		                    double d1 = (double)((float)framePos.getY() + 0.8125F);
		                    double d2 = (double)((float)framePos.getZ() + (5.0F + worldIn.rand.nextFloat() * 6.0F) / 16.0F);
		                    double d3 = 0.0D;
		                    double d4 = 0.0D;
		                    double d5 = 0.0D;
		                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		                }
		
		                worldIn.playSound(null, framePos, SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
		                BlockPattern.PatternHelper blockpattern$patternhelper = BlockEndPortalFrame.getOrCreatePortalShape().match(worldIn, framePos);
		
		                if (blockpattern$patternhelper != null)
		                {
		                    BlockPos blockpos = blockpattern$patternhelper.getFrontTopLeft().add(-3, 0, -3);
		
		                    for (int j = 0; j < 3; ++j)
		                    {
		                        for (int k = 0; k < 3; ++k)
		                        {
		                            worldIn.setBlockState(blockpos.add(j, 0, k), Blocks.END_PORTAL.getDefaultState(), 2);
		                        }
		                    }
		
		                    worldIn.playBroadcastSound(1038, blockpos.add(1, 0, 1), 0);
		                }
		                
		                te.decrStackSize(0, 1);
					}
					else if(worldIn.isAirBlock(pos1))
					{
						if (!worldIn.isRemote)
			            {
			                BlockPos blockpos = ((WorldServer)worldIn).getChunkProvider().getNearestStructurePos(worldIn, "Stronghold", pos, false);
	
			                if (blockpos != null)
			                {
			                	Double xOffset = 0.5D;
					        	Double yOffset = 0.5D;
					        	Double zOffset = 0.5D;
					        	Double xVel = 0D;
					        	Double yVel = 0D;
					        	Double zVel = 0D;
					        	
					        	Double offsetP = 1.05D;
					        	Double offsetN = -0.05D;
					        	
					        	if(facing == EnumFacing.UP)
					        	{
					        		yOffset = offsetP;
					        		yVel = 1.5D;
					        	}
					        	else if(facing == EnumFacing.DOWN)
					        	{
					        		yOffset = offsetN;
					        		yVel = -1.5D;
					        	}
					        	else if(facing == EnumFacing.NORTH)
					        	{
					        		zOffset = offsetN;
					        		zVel = -1.5D;
					        	}
					        	else if(facing == EnumFacing.SOUTH)
					        	{
					        		zOffset = offsetP;
					        		zVel = 1.5D;
					        	}
					        	else if(facing == EnumFacing.EAST)
					        	{
					        		xOffset = offsetP;
					        		xVel = 1.5D;
					        	}
					        	else
					        	{
					        		xOffset = offsetN;
					        		xVel = -1.5D;
					        	}
					        	
			                    EntityEnderEye entityendereye = new EntityEnderEye(worldIn, pos.getX() + xOffset, pos.getY() + yOffset, pos.getZ() + zOffset);
			                    entityendereye.moveTowards(blockpos);
			                    worldIn.spawnEntity(entityendereye);
			                    worldIn.playSound((EntityPlayer)null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_ENDEREYE_LAUNCH, SoundCategory.NEUTRAL, 0.5F, 0.4F / (worldIn.rand.nextFloat() * 0.4F + 0.8F));
			                    te.decrStackSize(0, 1);
			                }
			            }
					}
				}
				else if(item instanceof ItemMonsterPlacer)
				{
					BlockPos spawnerPos = null;
		            
					if(worldIn.getBlockState(pos1).getBlock() == Blocks.MOB_SPAWNER)
						spawnerPos = pos1;
					else if(worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock() == Blocks.MOB_SPAWNER)
						spawnerPos = pos2;
	
		            if (spawnerPos != null)
		            {
		                TileEntity tileentity = worldIn.getTileEntity(spawnerPos);
	
		                if (tileentity instanceof TileEntityMobSpawner)
		                {
		                    MobSpawnerBaseLogic mobspawnerbaselogic = ((TileEntityMobSpawner)tileentity).getSpawnerBaseLogic();
		                    mobspawnerbaselogic.setEntityId(ItemMonsterPlacer.getNamedIdFrom(stack));
		                    tileentity.markDirty();
		                    worldIn.notifyBlockUpdate(spawnerPos, worldIn.getBlockState(spawnerPos), worldIn.getBlockState(spawnerPos), 3);
		                    te.decrStackSize(0, 1);
		                }
		            }
		            else
		            {
			            BlockPos blockpos = null;
			            
			            if(worldIn.getBlockState(pos1).getBlock().isReplaceable(worldIn, pos1) && (!worldIn.isAirBlock(pos1) || !worldIn.getBlockState(pos2).getBlock().isReplaceable(worldIn, pos2)))
			            	blockpos = pos1;
			            else if(worldIn.getBlockState(pos2).getBlock().isReplaceable(worldIn, pos2) && !worldIn.isAirBlock(pos2))
			            	blockpos = pos2;
			            
			            if(blockpos != null)
			            {
				            double d0 = 0D;
				            
				            AxisAlignedBB axisalignedbb = (new AxisAlignedBB(blockpos)).expand(0.0D, -1.0D, 0.0D);
				            List<AxisAlignedBB> list = worldIn.getCollisionBoxes((Entity)null, axisalignedbb);
		
				            if (!list.isEmpty())
				            {
				                double d1 = axisalignedbb.minY;
		
				                for (AxisAlignedBB axisalignedbb1 : list)
				                {
				                    d1 = Math.max(axisalignedbb1.maxY, d0);
				                }
		
				                d0 = d1 - (double)blockpos.getY();
				            }
				            
				            Entity entity = ItemMonsterPlacer.spawnCreature(worldIn, ItemMonsterPlacer.getNamedIdFrom(stack), (double)blockpos.getX() + 0.5D, (double)blockpos.getY() + d0, (double)blockpos.getZ() + 0.5D);
			
				            if (entity != null)
				            {
				                if (entity instanceof EntityLivingBase && stack.hasDisplayName())
				                {
				                    entity.setCustomNameTag(stack.getDisplayName());
				                }
			
				                ItemMonsterPlacer.applyItemEntityDataToEntity(worldIn, null, stack, entity);
				                te.decrStackSize(0, 1);
				            }
			            }
		            }
				}
				else if(item instanceof ItemRecord)
				{
					BlockPos jukePos = null;
					
					if(worldIn.getBlockState(pos1).getBlock() == Blocks.JUKEBOX)
						jukePos = pos1;
					else if(worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock() == Blocks.JUKEBOX)
						jukePos = pos2;
					
					if(!worldIn.isRemote && jukePos != null && !worldIn.getBlockState(jukePos).getValue(BlockJukebox.HAS_RECORD).booleanValue() && worldIn.getTileEntity(jukePos) != null && worldIn.getTileEntity(jukePos) instanceof TileEntityJukebox)
					{
						((BlockJukebox)Blocks.JUKEBOX).insertRecord(worldIn, jukePos, worldIn.getBlockState(jukePos), stack);
						worldIn.playEvent(null, 1010, jukePos, Item.getIdFromItem(item));
						te.decrStackSize(0, 1);
					}
				}
				else if(item == Items.FISH && stack.getMetadata() < 2)
				{
					List<EntityOcelot> cats = worldIn.<EntityOcelot>getEntitiesWithinAABB(EntityOcelot.class, new AxisAlignedBB(pos1));
	
			        if (!cats.isEmpty())
			        {
			        	EntityOcelot cat = null;
			        	boolean child = false;
			        	
			        	for(int i = 0 ; i < cats.size() ; i++)
			        	{
			        		if(!cats.get(i).isChild() && cats.get(i).getGrowingAge() == 0 && !cats.get(i).isInLove())
			        		{
			        			cat = cats.get(i);
				        		break;
			        		}
			        		else if(cats.get(i).isChild())
			        		{
			        			cat = cats.get(i);
			        			child = true;
			        			break;
			        		}
			        	}
	
			        	if(cat != null)
			        	{
			        		if(child)
			        			cat.ageUp((int)((float)(-cat.getGrowingAge() / 20) * 0.1F), true);
			        		else
			        			cat.setInLove(null);
			        		
			                te.decrStackSize(0, 1);
			        	}
			        }
				}
				else if(item == Items.COOKIE)
				{
					List<EntityParrot> parrots = worldIn.<EntityParrot>getEntitiesWithinAABB(EntityParrot.class, new AxisAlignedBB(pos1));
	
			        if (!parrots.isEmpty())
			        {
			        	parrots.get(0).addPotionEffect(new PotionEffect(MobEffects.POISON, 900));
			        	parrots.get(0).attackEntityFrom(DamageSource.GENERIC, Float.MAX_VALUE);
			        	te.decrStackSize(0, 1);
			        }
				}
				else if(item == Items.CARROT && checkPlacability(worldIn, pos1, pos2, facing, new ItemStack(new ItemBlock(Blocks.CARROTS)), new ItemBlock(Blocks.CARROTS), placer))
		        {
		        	if(worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock().isReplaceable(worldIn, pos2))
					{
						worldIn.setBlockState(pos2, Blocks.CARROTS.getDefaultState());
						playPlaceSound(worldIn, pos2);
					}
					else
					{
						worldIn.setBlockState(pos1, Blocks.CARROTS.getDefaultState());
						playPlaceSound(worldIn, pos1);
					}
		        	
		        	te.decrStackSize(0, 1);
		        }
				else if((item == Items.POTATO) && checkPlacability(worldIn, pos1, pos2, facing, new ItemStack(new ItemBlock(Blocks.POTATOES)), new ItemBlock(Blocks.POTATOES), placer))
		        {
		        	if(worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock().isReplaceable(worldIn, pos2))
					{
						worldIn.setBlockState(pos2, Blocks.POTATOES.getDefaultState());
						playPlaceSound(worldIn, pos2);
					}
					else
					{
						worldIn.setBlockState(pos1, Blocks.POTATOES.getDefaultState());
						playPlaceSound(worldIn, pos1);
					}
		        	
		        	te.decrStackSize(0, 1);
		        }
				else if(item instanceof ItemSpade)
				{
					int damage = 0;
					
					if(facing != EnumFacing.UP && worldIn.getBlockState(pos1).getBlock() == Blocks.GRASS && worldIn.isAirBlock(pos1.up()))
					{
						worldIn.setBlockState(pos1, Blocks.GRASS_PATH.getDefaultState());
						worldIn.playSound(null, pos1, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
						damage = 1;
					}
					else if(facing != EnumFacing.UP && worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock() == Blocks.GRASS && worldIn.isAirBlock(pos2.up()))
					{
						worldIn.setBlockState(pos2, Blocks.GRASS_PATH.getDefaultState());
						worldIn.playSound(null, pos2, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
						damage = 1;
					}
					else if(facing != EnumFacing.UP && worldIn.getBlockState(pos1).getBlock() == MBlocks.clay_grass && worldIn.isAirBlock(pos1.up()))
					{
						worldIn.setBlockState(pos1, MBlocks.clay_grass_path.getDefaultState());
						worldIn.playSound(null, pos1, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
						damage = 1;
					}
					else if(facing != EnumFacing.UP && worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock() == MBlocks.clay_grass && worldIn.isAirBlock(pos2.up()))
					{
						worldIn.setBlockState(pos2, MBlocks.clay_grass_path.getDefaultState());
						worldIn.playSound(null, pos2, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
						damage = 1;
					}
					else if(facing != EnumFacing.UP && worldIn.getBlockState(pos1).getBlock() == MBlocks.lichen && worldIn.isAirBlock(pos1.up()))
					{
						worldIn.setBlockState(pos1, MBlocks.lichen_path.getDefaultState());
						worldIn.playSound(null, pos1, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
						damage = 1;
					}
					else if(facing != EnumFacing.UP && worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock() == MBlocks.lichen && worldIn.isAirBlock(pos2.up()))
					{
						worldIn.setBlockState(pos2, MBlocks.lichen_path.getDefaultState());
						worldIn.playSound(null, pos2, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
						damage = 1;
					}
					else if(facing != EnumFacing.UP && worldIn.getBlockState(pos1).getBlock() == MBlocks.fargrowth && worldIn.isAirBlock(pos1.up()))
					{
						worldIn.setBlockState(pos1, MBlocks.fargrowth_path.getDefaultState());
						worldIn.playSound(null, pos1, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
						damage = 1;
					}
					else if(facing != EnumFacing.UP && worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock() == MBlocks.fargrowth && worldIn.isAirBlock(pos2.up()))
					{
						worldIn.setBlockState(pos2, MBlocks.fargrowth_path.getDefaultState());
						worldIn.playSound(null, pos2, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
						damage = 1;
					}
					else if(item == MItems.fire_shovel && worldIn.isAirBlock(pos1) && !worldIn.isAirBlock(pos2))
					{
						worldIn.setBlockState(pos1, Blocks.FIRE.getDefaultState());
						worldIn.playSound((EntityPlayer)null, pos1, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, worldIn.rand.nextFloat() * 0.4F + 0.8F);
						damage = 4;
					}
					
					if(damage > 0 && te.getStackInSlot(0).attemptDamageItem(damage, worldIn.rand, null))
					{
						worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.BLOCKS, 0.8F, 0.8F + worldIn.rand.nextFloat() * 0.4F);
						te.decrStackSize(0, 1);
					}
				}
				else if(item == Items.FLINT_AND_STEEL)
				{
					boolean damage = false;
					BlockPos interactPos = null;
					
					if(worldIn.getBlockState(pos1).getBlock() == MBlocks.candle || worldIn.getBlockState(pos1).getBlock() == MBlocks.candle2 || (worldIn.getBlockState(pos1).getBlock() instanceof BlockJackOLantern && !((BlockJackOLantern)worldIn.getBlockState(pos1).getBlock()).isLit()) || worldIn.getBlockState(pos1).getBlock() instanceof BlockTNT)
						interactPos = pos1;
					else if(worldIn.isAirBlock(pos1) && (worldIn.getBlockState(pos2).getBlock() == MBlocks.candle || worldIn.getBlockState(pos2).getBlock() == MBlocks.candle2 || (worldIn.getBlockState(pos2).getBlock() instanceof BlockJackOLantern && !((BlockJackOLantern)worldIn.getBlockState(pos2).getBlock()).isLit()) || worldIn.getBlockState(pos2).getBlock() instanceof BlockTNT))
						interactPos = pos2;
					
					if(interactPos != null)
					{
						if(worldIn.getBlockState(interactPos).getBlock() == MBlocks.candle)
						{
							worldIn.setBlockState(interactPos, MBlocks.candle_fire.getDefaultState().withProperty(BlockCandleNormal.COLOR, worldIn.getBlockState(interactPos).getValue(BlockCandleNormal.COLOR)));
							worldIn.playSound((EntityPlayer)null, interactPos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, worldIn.rand.nextFloat() * 0.4F + 0.8F);
							damage = true;
						}
						if(worldIn.getBlockState(interactPos).getBlock() == MBlocks.candle2)
						{
							worldIn.setBlockState(interactPos, MBlocks.candle2_fire.getDefaultState().withProperty(BlockCandleGlowing.GLOWCOLOR, worldIn.getBlockState(interactPos).getValue(BlockCandleGlowing.GLOWCOLOR)));
							worldIn.playSound((EntityPlayer)null, interactPos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, worldIn.rand.nextFloat() * 0.4F + 0.8F);
							damage = true;
						}
						else if(worldIn.getBlockState(interactPos).getBlock() instanceof BlockJackOLanternSmashed)
						{
							worldIn.setBlockState(interactPos, MBlocks.pumpkin_smashed_fire.getDefaultState().withProperty(BlockJackOLanternSmashed.FACING, worldIn.getBlockState(interactPos).getValue(BlockJackOLanternSmashed.FACING)));
							worldIn.playSound((EntityPlayer)null, interactPos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, worldIn.rand.nextFloat() * 0.4F + 0.8F);
							damage = true;
						}
						else if(worldIn.getBlockState(interactPos).getBlock() instanceof BlockJackOLantern)
						{
							worldIn.setBlockState(interactPos, ((BlockJackOLantern)worldIn.getBlockState(interactPos).getBlock()).getFire().getDefaultState().withProperty(BlockJackOLantern.FACING, worldIn.getBlockState(interactPos).getValue(BlockJackOLantern.FACING)).withProperty(BlockJackOLantern.VARIANT, worldIn.getBlockState(interactPos).getValue(BlockJackOLantern.VARIANT)));
							worldIn.playSound((EntityPlayer)null, interactPos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, worldIn.rand.nextFloat() * 0.4F + 0.8F);
							damage = true;
						}
						else if(worldIn.getBlockState(interactPos).getBlock() instanceof BlockTNT)
						{
							((BlockTNT)worldIn.getBlockState(interactPos).getBlock()).explode(worldIn, interactPos, worldIn.getBlockState(interactPos).withProperty(BlockTNT.EXPLODE, Boolean.valueOf(true)), placer);
				            worldIn.setBlockState(interactPos, Blocks.AIR.getDefaultState(), 11);
				            damage = true;
						}
					}
					else if(worldIn.isAirBlock(pos1) && !worldIn.isAirBlock(pos2))
					{
						worldIn.setBlockState(pos1, Blocks.FIRE.getDefaultState());
						worldIn.playSound((EntityPlayer)null, pos1, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, worldIn.rand.nextFloat() * 0.4F + 0.8F);
						damage = true;
					}
					
					if(damage && te.getStackInSlot(0).attemptDamageItem(1, worldIn.rand, null))
					{
						worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.BLOCKS, 0.8F, 0.8F + worldIn.rand.nextFloat() * 0.4F);
						te.decrStackSize(0, 1);
					}
				}
				else if (item instanceof ItemShears)
				{
					List<EntityLiving> entities = worldIn.<EntityLiving>getEntitiesWithinAABB(EntityLiving.class, new AxisAlignedBB(pos1));
					Entity entity = null;
					
					for(int i = 0 ; i < entities.size() ; i++)
					{
						if(entities.get(i) instanceof IShearable)
							entity = entities.get(i);
					}
					
					if (entity != null)
			        {
			            net.minecraftforge.common.IShearable target = (net.minecraftforge.common.IShearable)entity;
			            BlockPos entitypos = new BlockPos(entity.posX, entity.posY, entity.posZ);
			            if (target.isShearable(stack, entity.world, pos))
			            {
			                java.util.List<ItemStack> drops = target.onSheared(stack, entity.world, pos, net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(net.minecraft.init.Enchantments.FORTUNE, stack));

			                java.util.Random rand = new java.util.Random();
			                for(ItemStack dropstack : drops)
			                {
			                    net.minecraft.entity.item.EntityItem ent = entity.entityDropItem(dropstack, 1.0F);
			                    ent.motionY += rand.nextFloat() * 0.05F;
			                    ent.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
			                    ent.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
			                }
			                
			                if(te.getStackInSlot(0).attemptDamageItem(1, worldIn.rand, null))
							{
								worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.BLOCKS, 0.8F, 0.8F + worldIn.rand.nextFloat() * 0.4F);
								te.decrStackSize(0, 1);
							}
			            }
			        }
				}
				else if(item instanceof ItemHoe)
				{
					int damage = 0;
					
					if(facing != EnumFacing.UP && (worldIn.getBlockState(pos1).getBlock() == Blocks.GRASS || worldIn.getBlockState(pos1) == Blocks.DIRT.getDefaultState() || worldIn.getBlockState(pos1).getBlock() == Blocks.GRASS_PATH) && worldIn.isAirBlock(pos1.up()))
					{
						worldIn.setBlockState(pos1, Blocks.FARMLAND.getDefaultState());
						worldIn.playSound(null, pos1, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
						damage = 1;
					}
					else if(facing != EnumFacing.UP && worldIn.isAirBlock(pos1) && (worldIn.getBlockState(pos2).getBlock() == Blocks.GRASS || worldIn.getBlockState(pos2) == Blocks.DIRT.getDefaultState() || worldIn.getBlockState(pos2).getBlock() == Blocks.GRASS_PATH) && worldIn.isAirBlock(pos2.up()))
					{
						worldIn.setBlockState(pos2, Blocks.FARMLAND.getDefaultState());
						worldIn.playSound(null, pos2, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
						damage = 1;
					}
					else if(facing != EnumFacing.UP && (worldIn.getBlockState(pos1).getBlock() == MBlocks.clay_grass || worldIn.getBlockState(pos1) == MBlocks.clay_soil.getDefaultState() || worldIn.getBlockState(pos1).getBlock() == MBlocks.clay_grass_path) && worldIn.isAirBlock(pos1.up()))
					{
						worldIn.setBlockState(pos1, MBlocks.clay_farmland.getDefaultState());
						worldIn.playSound(null, pos1, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
						damage = 1;
					}
					else if(facing != EnumFacing.UP && worldIn.isAirBlock(pos1) && (worldIn.getBlockState(pos2).getBlock() == MBlocks.clay_grass || worldIn.getBlockState(pos2) == MBlocks.clay_soil.getDefaultState() || worldIn.getBlockState(pos2).getBlock() == MBlocks.clay_grass_path) && worldIn.isAirBlock(pos2.up()))
					{
						worldIn.setBlockState(pos2, MBlocks.clay_farmland.getDefaultState());
						worldIn.playSound(null, pos2, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
						damage = 1;
					}
					else if(facing != EnumFacing.UP && (worldIn.getBlockState(pos1).getBlock() == MBlocks.lichen || worldIn.getBlockState(pos1) == MBlocks.permafrost.getDefaultState() || worldIn.getBlockState(pos1).getBlock() == MBlocks.lichen_path) && worldIn.isAirBlock(pos1.up()))
					{
						worldIn.setBlockState(pos1, MBlocks.permafrost_farmland.getDefaultState());
						worldIn.playSound(null, pos1, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
						damage = 1;
					}
					else if(facing != EnumFacing.UP && worldIn.isAirBlock(pos1) && (worldIn.getBlockState(pos2).getBlock() == MBlocks.lichen || worldIn.getBlockState(pos2) == MBlocks.permafrost.getDefaultState() || worldIn.getBlockState(pos2).getBlock() == MBlocks.lichen_path) && worldIn.isAirBlock(pos2.up()))
					{
						worldIn.setBlockState(pos2, MBlocks.permafrost_farmland.getDefaultState());
						worldIn.playSound(null, pos2, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
						damage = 1;
					}
					else if(facing != EnumFacing.UP && worldIn.getBlockState(pos1) == Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT) && worldIn.isAirBlock(pos1.up()))
					{
						worldIn.setBlockState(pos1, Blocks.DIRT.getDefaultState());
						worldIn.playSound(null, pos1, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
						damage = 1;
					}
					else if(facing != EnumFacing.UP && worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2) == Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT) && worldIn.isAirBlock(pos2.up()))
					{
						worldIn.setBlockState(pos2, Blocks.DIRT.getDefaultState());
						worldIn.playSound(null, pos2, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
						damage = 1;
					}
					else if(facing != EnumFacing.UP && worldIn.getBlockState(pos1) == MBlocks.clay_soil.getDefaultState().withProperty(BlockMDirt.VARIANT, BlockMDirt.DirtType.COARSE) && worldIn.isAirBlock(pos1.up()))
					{
						worldIn.setBlockState(pos1, MBlocks.clay_soil.getDefaultState());
						worldIn.playSound(null, pos1, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
						damage = 1;
					}
					else if(facing != EnumFacing.UP && worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2) == MBlocks.clay_soil.getDefaultState().withProperty(BlockMDirt.VARIANT, BlockMDirt.DirtType.COARSE) && worldIn.isAirBlock(pos2.up()))
					{
						worldIn.setBlockState(pos2, MBlocks.clay_soil.getDefaultState());
						worldIn.playSound(null, pos2, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
						damage = 1;
					}
					else if(facing != EnumFacing.UP && worldIn.getBlockState(pos1) == MBlocks.permafrost.getDefaultState().withProperty(BlockMDirt.VARIANT, BlockMDirt.DirtType.COARSE) && worldIn.isAirBlock(pos1.up()))
					{
						worldIn.setBlockState(pos1, MBlocks.permafrost.getDefaultState());
						worldIn.playSound(null, pos1, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
						damage = 1;
					}
					else if(facing != EnumFacing.UP && worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2) == MBlocks.permafrost.getDefaultState().withProperty(BlockMDirt.VARIANT, BlockMDirt.DirtType.COARSE) && worldIn.isAirBlock(pos2.up()))
					{
						worldIn.setBlockState(pos2, MBlocks.permafrost.getDefaultState());
						worldIn.playSound(null, pos2, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
						damage = 1;
					}
					else if(facing != EnumFacing.UP && worldIn.getBlockState(pos1) == MBlocks.portal_dust.getDefaultState().withProperty(BlockMDirt.VARIANT, BlockMDirt.DirtType.COARSE) && worldIn.isAirBlock(pos1.up()))
					{
						worldIn.setBlockState(pos1, MBlocks.portal_dust.getDefaultState());
						worldIn.playSound(null, pos1, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
						damage = 1;
					}
					else if(facing != EnumFacing.UP && worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2) == MBlocks.portal_dust.getDefaultState().withProperty(BlockMDirt.VARIANT, BlockMDirt.DirtType.COARSE) && worldIn.isAirBlock(pos2.up()))
					{
						worldIn.setBlockState(pos2, MBlocks.portal_dust.getDefaultState());
						worldIn.playSound(null, pos2, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
						damage = 1;
					}
					else if(item == MItems.fire_hoe && worldIn.isAirBlock(pos1) && !worldIn.isAirBlock(pos2))
					{
						worldIn.setBlockState(pos1, Blocks.FIRE.getDefaultState());
						worldIn.playSound((EntityPlayer)null, pos1, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, worldIn.rand.nextFloat() * 0.4F + 0.8F);
						damage = 4;
					}
					
					if(damage > 0 && te.getStackInSlot(0).attemptDamageItem(damage, worldIn.rand, null))
					{
						worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.BLOCKS, 0.8F, 0.8F + worldIn.rand.nextFloat() * 0.4F);
						te.decrStackSize(0, 1);
					}
				}
				else if(item instanceof ItemNameTag)
				{
					List<EntityLivingBase> entities = worldIn.<EntityLivingBase>getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(pos1));
					Entity entity = null;
					
					for(int i = 0 ; i < entities.size() ; i++)
					{
						if(!(entities.get(i) instanceof EntityPlayer))
							entity = entities.get(i);
					}
					
					if (stack.hasDisplayName() && entity != null)
			        {
			            entity.setCustomNameTag(stack.getDisplayName());

			            if (entity instanceof EntityLiving)
			            {
			                ((EntityLiving)entity).enablePersistence();
			            }

			            te.decrStackSize(0, 1);
			        }
				}
				else if(item == Items.GOLDEN_CARROT)
				{
					List<EntityHorse> horses = worldIn.<EntityHorse>getEntitiesWithinAABB(EntityHorse.class, new AxisAlignedBB(pos1));
	
			        if (!horses.isEmpty())
			        {
			        	EntityHorse horse = horses.get(0);
			        	boolean child = false;
			        	boolean flag = false;
			        	
			        	if(horse.isChild())
			        		child = true;
	
			        	if (horse.getHealth() < horse.getMaxHealth())
			            {
			                horse.heal(4F);
			                flag = true;
			            }
	
			            if (horse.isChild())
			            {
			                worldIn.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, horse.posX + (double)(worldIn.rand.nextFloat() * horse.width * 2.0F) - (double)horse.width, horse.posY + 0.5D + (double)(worldIn.rand.nextFloat() * horse.height), horse.posZ + (double)(worldIn.rand.nextFloat() * horse.width * 2.0F) - (double)horse.width, 0.0D, 0.0D, 0.0D);
	
			                if (!worldIn.isRemote)
			                {
			                    horse.addGrowth(60);
			                }
	
			                flag = true;
			            }
	
			            if ((flag || !horse.isTame()) && horse.getTemper() < horse.getMaxTemper())
			            {
			                flag = true;
	
			                if (!worldIn.isRemote)
			                {
			                    horse.increaseTemper(5);
			                }
			            }
	
			            if (flag)
			            {
			            	horse.setEatingHaystack(true);
			            	te.decrStackSize(0, 1);
			            }
			        }
				}
				else if(item == Items.DRAGON_BREATH)
				{
					BlockPos interactPos = null;
					boolean decr = false;
					
					if(worldIn.getBlockState(pos1).getBlock() == MBlocks.candle || worldIn.getBlockState(pos1).getBlock() == MBlocks.candle2 || (worldIn.getBlockState(pos1).getBlock() instanceof BlockJackOLantern && !((BlockJackOLantern)worldIn.getBlockState(pos1).getBlock()).isLit()))
						interactPos = pos1;
					else if(worldIn.isAirBlock(pos1) && (worldIn.getBlockState(pos2).getBlock() == MBlocks.candle || worldIn.getBlockState(pos2).getBlock() == MBlocks.candle2 || (worldIn.getBlockState(pos2).getBlock() instanceof BlockJackOLantern && !((BlockJackOLantern)worldIn.getBlockState(pos2).getBlock()).isLit())))
						interactPos = pos2;
					
					if(interactPos != null)
					{
						if(worldIn.getBlockState(interactPos).getBlock() == MBlocks.candle)
						{
							worldIn.setBlockState(interactPos, MBlocks.candle_ender.getDefaultState().withProperty(BlockCandleNormal.COLOR, worldIn.getBlockState(interactPos).getValue(BlockCandleNormal.COLOR)));
							worldIn.playSound((EntityPlayer)null, interactPos, SoundEvents.ENTITY_SPLASH_POTION_BREAK, SoundCategory.BLOCKS, 1.0F, worldIn.rand.nextFloat() * 0.4F + 0.8F);
							decr = true;
						}
						if(worldIn.getBlockState(interactPos).getBlock() == MBlocks.candle2)
						{
							worldIn.setBlockState(interactPos, MBlocks.candle2_ender.getDefaultState().withProperty(BlockCandleGlowing.GLOWCOLOR, worldIn.getBlockState(interactPos).getValue(BlockCandleGlowing.GLOWCOLOR)));
							worldIn.playSound((EntityPlayer)null, interactPos, SoundEvents.ENTITY_SPLASH_POTION_BREAK, SoundCategory.BLOCKS, 1.0F, worldIn.rand.nextFloat() * 0.4F + 0.8F);
							decr = true;
						}
						else if(worldIn.getBlockState(interactPos).getBlock() instanceof BlockJackOLanternSmashed)
						{
							worldIn.setBlockState(interactPos, MBlocks.pumpkin_smashed_ender.getDefaultState().withProperty(BlockJackOLanternSmashed.FACING, worldIn.getBlockState(interactPos).getValue(BlockJackOLanternSmashed.FACING)));
							worldIn.playSound((EntityPlayer)null, interactPos, SoundEvents.ENTITY_SPLASH_POTION_BREAK, SoundCategory.BLOCKS, 1.0F, worldIn.rand.nextFloat() * 0.4F + 0.8F);
							decr = true;
						}
						else if(worldIn.getBlockState(interactPos).getBlock() instanceof BlockJackOLantern)
						{
							worldIn.setBlockState(interactPos, ((BlockJackOLantern)worldIn.getBlockState(interactPos).getBlock()).getEnder().getDefaultState().withProperty(BlockJackOLantern.FACING, worldIn.getBlockState(interactPos).getValue(BlockJackOLantern.FACING)).withProperty(BlockJackOLantern.VARIANT, worldIn.getBlockState(interactPos).getValue(BlockJackOLantern.VARIANT)));
							worldIn.playSound((EntityPlayer)null, interactPos, SoundEvents.ENTITY_SPLASH_POTION_BREAK, SoundCategory.BLOCKS, 1.0F, worldIn.rand.nextFloat() * 0.4F + 0.8F);
							decr = true;
						}
						
						if(decr)
						{
							worldIn.spawnEntity(new EntityItem(worldIn, interactPos.getX() + 0.5D, interactPos.getY() + 0.5D, interactPos.getZ() + 0.5D, new ItemStack(Items.GLASS_BOTTLE)));
							te.decrStackSize(0, 1);
						}
					}
				}
				else if(item instanceof ItemSplashPotion || item instanceof ItemLingeringPotion)
				{
					ItemStack itemstack = stack.copy();
			        worldIn.playSound((EntityPlayer)null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_SPLASH_POTION_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (worldIn.rand.nextFloat() * 0.4F + 0.8F));

			        if (!worldIn.isRemote)
			        {
			        	Double xOffset = 0.5D;
			        	Double yOffset = 0.5D;
			        	Double zOffset = 0.5D;
			        	Double xVel = 0D;
			        	Double yVel = 0D;
			        	Double zVel = 0D;
			        	
			        	Double offsetP = 1.05D;
			        	Double offsetN = -0.05D;
			        	
			        	if(facing == EnumFacing.UP)
			        	{
			        		yOffset = offsetP;
			        		yVel = 0.7D;
			        	}
			        	else if(facing == EnumFacing.DOWN)
			        	{
			        		yOffset = offsetN;
			        		yVel = -0.7D;
			        	}
			        	else if(facing == EnumFacing.NORTH)
			        	{
			        		zOffset = offsetN;
			        		zVel = -0.7D;
			        	}
			        	else if(facing == EnumFacing.SOUTH)
			        	{
			        		zOffset = offsetP;
			        		zVel = 0.7D;
			        	}
			        	else if(facing == EnumFacing.EAST)
			        	{
			        		xOffset = offsetP;
			        		xVel = 0.7D;
			        	}
			        	else
			        	{
			        		xOffset = offsetN;
			        		xVel = -0.7D;
			        	}
			        	
			            EntityPotion entityPotion = new EntityPotion(worldIn, te.getPos().getX() + xOffset, te.getPos().getY() + yOffset, te.getPos().getZ() + zOffset, itemstack);
			            entityPotion.setVelocity(xVel, yVel, zVel);
			            worldIn.spawnEntity(entityPotion);
			            te.decrStackSize(0, 1);
			        }
				}
				else if(item instanceof ItemPotion)
				{
					BlockPos interactPos = null;
					boolean decr = false;
					
					if((worldIn.getBlockState(interactPos).getBlock() instanceof BlockCandleBase && !((BlockCandleBase)worldIn.getBlockState(interactPos).getBlock()).isLit()) || (worldIn.getBlockState(pos1).getBlock() instanceof BlockJackOLantern && ((BlockJackOLantern)worldIn.getBlockState(pos1).getBlock()).isLit()))
						interactPos = pos1;
					else if(worldIn.isAirBlock(pos1) && ((worldIn.getBlockState(interactPos).getBlock() instanceof BlockCandleBase && !((BlockCandleBase)worldIn.getBlockState(interactPos).getBlock()).isLit()) || (worldIn.getBlockState(pos2).getBlock() instanceof BlockJackOLantern && ((BlockJackOLantern)worldIn.getBlockState(pos2).getBlock()).isLit())))
						interactPos = pos2;
					
					if(interactPos != null)
					{
						if(worldIn.getBlockState(interactPos).getBlock() instanceof BlockCandleNormal)
						{
							worldIn.setBlockState(interactPos, MBlocks.candle.getDefaultState().withProperty(BlockCandleNormal.COLOR, worldIn.getBlockState(interactPos).getValue(BlockCandleNormal.COLOR)));
							worldIn.playSound((EntityPlayer)null, interactPos, SoundEvents.ENTITY_SPLASH_POTION_BREAK, SoundCategory.BLOCKS, 1.0F, worldIn.rand.nextFloat() * 0.4F + 0.8F);
							decr = true;
						}
						if(worldIn.getBlockState(interactPos).getBlock() instanceof BlockCandleGlowing)
						{
							worldIn.setBlockState(interactPos, MBlocks.candle2.getDefaultState().withProperty(BlockCandleGlowing.GLOWCOLOR, worldIn.getBlockState(interactPos).getValue(BlockCandleGlowing.GLOWCOLOR)));
							worldIn.playSound((EntityPlayer)null, interactPos, SoundEvents.ENTITY_SPLASH_POTION_BREAK, SoundCategory.BLOCKS, 1.0F, worldIn.rand.nextFloat() * 0.4F + 0.8F);
							decr = true;
						}
						else if(worldIn.getBlockState(interactPos).getBlock() instanceof BlockJackOLanternSmashed)
						{
							worldIn.setBlockState(interactPos, MBlocks.pumpkin_smashed_ender.getDefaultState().withProperty(BlockJackOLanternSmashed.FACING, worldIn.getBlockState(interactPos).getValue(BlockJackOLanternSmashed.FACING)));
							worldIn.playSound((EntityPlayer)null, interactPos, SoundEvents.ENTITY_SPLASH_POTION_BREAK, SoundCategory.BLOCKS, 1.0F, worldIn.rand.nextFloat() * 0.4F + 0.8F);
							decr = true;
						}
						else if(worldIn.getBlockState(interactPos).getBlock() instanceof BlockJackOLantern)
						{
							worldIn.setBlockState(interactPos, ((BlockJackOLantern)worldIn.getBlockState(interactPos).getBlock()).getEnder().getDefaultState().withProperty(BlockJackOLantern.FACING, worldIn.getBlockState(interactPos).getValue(BlockJackOLantern.FACING)).withProperty(BlockJackOLantern.VARIANT, worldIn.getBlockState(interactPos).getValue(BlockJackOLantern.VARIANT)));
							worldIn.playSound((EntityPlayer)null, interactPos, SoundEvents.ENTITY_SPLASH_POTION_BREAK, SoundCategory.BLOCKS, 1.0F, worldIn.rand.nextFloat() * 0.4F + 0.8F);
							decr = true;
						}
						
						if(decr)
						{
							worldIn.spawnEntity(new EntityItem(worldIn, interactPos.getX() + 0.5D, interactPos.getY() + 0.5D, interactPos.getZ() + 0.5D, new ItemStack(Items.GLASS_BOTTLE)));
							te.decrStackSize(0, 1);
						}
					}
				}
				else if(item == MItems.pepper_seeds || item == MItems.cabbage_seeds || item == MItems.celery_seeds || item == MItems.tomato_seeds || item == MItems.corn || item == MItems.onion || item == MItems.peanuts || item == MItems.lettuce)
				{
		        	Block crop = MBlocks.crop_pepper;
		        	
		        	if(item == MItems.cabbage_seeds)
		        		crop = MBlocks.crop_cabbage;
		        	else if(item == MItems.celery_seeds)
		        		crop = MBlocks.crop_celery;
		        	else if(item == MItems.tomato_seeds)
		        		crop = MBlocks.crop_tomato;
		        	else if(item == MItems.corn)
		        		crop = MBlocks.crop_corn;
		        	else if(item == MItems.onion)
		        		crop = MBlocks.crop_onion;
		        	else if(item == MItems.peanuts)
		        		crop = MBlocks.crop_peanuts;
		        	else if(item == MItems.lettuce)
		        		crop = MBlocks.crop_lettuce;
		        	
		        	if(checkPlacability(worldIn, pos1, pos2, facing, new ItemStack(new ItemBlock(crop)), new ItemBlock(crop), placer))
		        	{
			        	if(worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock().isReplaceable(worldIn, pos2))
						{
							worldIn.setBlockState(pos2, crop.getDefaultState());
							playPlaceSound(worldIn, pos2);
						}
						else
						{
							worldIn.setBlockState(pos1, crop.getDefaultState());
							playPlaceSound(worldIn, pos1);
						}
			        	
			        	te.decrStackSize(0, 1);
		        	}
				}
				else if(item == MItems.gems && stack.getMetadata() == 6)
				{
					BlockPos interactPos = null;
					
					if(worldIn.getBlockState(pos1).getBlock() instanceof BlockGlaciericIceBranch && worldIn.getBlockState(pos1).getBlock() != MBlocks.glacieric_ice_branch_7)
						interactPos = pos1;
					else if(worldIn.isAirBlock(pos1) && worldIn.getBlockState(pos2).getBlock() instanceof BlockGlaciericIceBranch && worldIn.getBlockState(pos2).getBlock() != MBlocks.glacieric_ice_branch_7)
						interactPos = pos2;
					
					if(interactPos != null)
					{
						if(worldIn.getBlockState(interactPos).getBlock() == MBlocks.glacieric_ice_branch_0)
							worldIn.setBlockState(interactPos, MBlocks.glacieric_ice_branch_1.getDefaultState().withProperty(BlockGlaciericIceBranch.FACING, worldIn.getBlockState(interactPos).getValue(BlockGlaciericIceBranch.FACING)));
						else if(worldIn.getBlockState(interactPos).getBlock() == MBlocks.glacieric_ice_branch_1)
							worldIn.setBlockState(interactPos, MBlocks.glacieric_ice_branch_2.getDefaultState().withProperty(BlockGlaciericIceBranch.FACING, worldIn.getBlockState(interactPos).getValue(BlockGlaciericIceBranch.FACING)));
						else if(worldIn.getBlockState(interactPos).getBlock() == MBlocks.glacieric_ice_branch_2)
							worldIn.setBlockState(interactPos, MBlocks.glacieric_ice_branch_3.getDefaultState().withProperty(BlockGlaciericIceBranch.FACING, worldIn.getBlockState(interactPos).getValue(BlockGlaciericIceBranch.FACING)));
						else if(worldIn.getBlockState(interactPos).getBlock() == MBlocks.glacieric_ice_branch_3)
							worldIn.setBlockState(interactPos, MBlocks.glacieric_ice_branch_4.getDefaultState().withProperty(BlockGlaciericIceBranch.FACING, worldIn.getBlockState(interactPos).getValue(BlockGlaciericIceBranch.FACING)));
						else if(worldIn.getBlockState(interactPos).getBlock() == MBlocks.glacieric_ice_branch_4)
							worldIn.setBlockState(interactPos, MBlocks.glacieric_ice_branch_5.getDefaultState().withProperty(BlockGlaciericIceBranch.FACING, worldIn.getBlockState(interactPos).getValue(BlockGlaciericIceBranch.FACING)));
						else if(worldIn.getBlockState(interactPos).getBlock() == MBlocks.glacieric_ice_branch_5)
							worldIn.setBlockState(interactPos, MBlocks.glacieric_ice_branch_6.getDefaultState().withProperty(BlockGlaciericIceBranch.FACING, worldIn.getBlockState(interactPos).getValue(BlockGlaciericIceBranch.FACING)));
						else if(worldIn.getBlockState(interactPos).getBlock() == MBlocks.glacieric_ice_branch_6)
							worldIn.setBlockState(interactPos, MBlocks.glacieric_ice_branch_7.getDefaultState().withProperty(BlockGlaciericIceBranch.FACING, worldIn.getBlockState(interactPos).getValue(BlockGlaciericIceBranch.FACING)));
						
						te.decrStackSize(0, 1);
					}
				}
				else if(item == MItems.mob_loot && stack.getMetadata() == 1 && checkForBlock(worldIn, pos1, pos2, MBlocks.basket))
				{
					BlockPos basketPos = pos1;
					
					if(worldIn.isAirBlock(pos1))
						basketPos = pos2;
					
					worldIn.setBlockState(basketPos, MBlocks.basket_cheesemaking.getDefaultState());
					te.decrStackSize(0, 1);
				}
			}

			te.markDirty();
		}
    }
	
	private void applyArmorStandRotations(EntityArmorStand armorStand, Random rand)
    {
        Rotations rotations = armorStand.getHeadRotation();
        float f = rand.nextFloat() * 5.0F;
        float f1 = rand.nextFloat() * 20.0F - 10.0F;
        Rotations rotations1 = new Rotations(rotations.getX() + f, rotations.getY() + f1, rotations.getZ());
        armorStand.setHeadRotation(rotations1);
        rotations = armorStand.getBodyRotation();
        f = rand.nextFloat() * 10.0F - 5.0F;
        rotations1 = new Rotations(rotations.getX(), rotations.getY() + f, rotations.getZ());
        armorStand.setBodyRotation(rotations1);
    }
	
	private void playPlaceSound(World worldIn, BlockPos pos)
	{
		SoundType soundtype = worldIn.getBlockState(pos).getBlock().getSoundType(worldIn.getBlockState(pos), worldIn, pos, null);
        worldIn.playSound(null, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
	}
}
