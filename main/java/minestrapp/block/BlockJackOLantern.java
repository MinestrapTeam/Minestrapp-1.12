package minestrapp.block;

import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import minestrapp.MBlocks;
import minestrapp.MTabs;
import minestrapp.block.item.IMetaBlockName;
import minestrapp.block.util.BlockBase;
import minestrapp.item.tools.MDagger;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMaterialMatcher;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.block.state.pattern.FactoryBlockPattern;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Mirror;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockJackOLantern extends BlockBase implements IMetaBlockName
{	
	public static final PropertyEnum<BlockJackOLantern.EnumFaceType> VARIANT = PropertyEnum.<BlockJackOLantern.EnumFaceType>create("variant", BlockJackOLantern.EnumFaceType.class);
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	
	private String type;
	private Block base;
	private Block fire;
	private Block ender;
	
	private BlockPattern snowmanBasePattern;
    private BlockPattern snowmanPattern;
    private BlockPattern golemBasePattern;
    private BlockPattern golemPattern;
	
	public BlockJackOLantern(String name, String type, Block block1, Block block2)
	{
		super(name, Material.GOURD, MapColor.ADOBE, SoundType.WOOD, 1F);
		if(type == "unlit")
			this.setCreativeTab(MTabs.decor);
		else if(type == "fire")
			this.setLightLevel(1F);
		else if(type == "ender")
			this.setLightLevel(0.8F);
		if(this instanceof BlockJackOLanternSmashed == false)
			this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockJackOLantern.EnumFaceType.SIMPLE).withProperty(FACING, EnumFacing.NORTH));
		this.type = type;
		if(this.type == "unlit")
		{
			this.base = this;
			this.fire = block1;
			this.ender = block2;
		}
		else if(this.type == "fire")
		{
			this.base = block1;
			this.fire = this;
			this.ender = block2;
		}
		else if(this.type == "ender")
		{
			this.base = block1;
			this.fire = block2;
			this.ender = this;
		}
		this.setDropsItem(new ItemStack(this.base), 0, 0, 0, false, false);
	}
	
	public BlockJackOLantern setBlockVariants(Block base, Block fire, Block ender)
	{
		this.base = base;
		this.fire = fire;
		this.ender = ender;
		return this;
	}
	
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos) && worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos, EnumFacing.UP);
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
    	return new BlockStateContainer(this, new IProperty[] {VARIANT, FACING});
    }
    
    public int damageDropped(IBlockState state)
    {
    	return ((BlockJackOLantern.EnumFaceType)state.getValue(VARIANT)).getMetadata();
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> tab)
    {
    	if(this.type == "unlit")
    	{
	        for (BlockJackOLantern.EnumFaceType jack$enumtype : BlockJackOLantern.EnumFaceType.values())
	        {
	            tab.add(new ItemStack(this, 1, jack$enumtype.getMetadata()));
	        }
    	}
    }
    
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
    	return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()).withProperty(VARIANT, BlockJackOLantern.EnumFaceType.byMetadata(meta));
    }
    
    public IBlockState getStateFromMeta(int meta)
    {
    	IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, BlockJackOLantern.EnumFaceType.byMetadata((meta & 3) % 4));
	
		switch (meta & 12)
		{
			case 0:
				iblockstate = iblockstate.withProperty(FACING, EnumFacing.SOUTH);
				break;
			case 4:
				iblockstate = iblockstate.withProperty(FACING, EnumFacing.WEST);
				break;
			case 8:
				iblockstate = iblockstate.withProperty(FACING, EnumFacing.NORTH);
				break;
			default:
				iblockstate = iblockstate.withProperty(FACING, EnumFacing.EAST);
		}
	
		return iblockstate;
    }
    
    public int getMetaFromState(IBlockState state)
    {
    	byte b0 = 0;
		int i = b0 | ((BlockJackOLantern.EnumFaceType) state.getValue(VARIANT)).getMetadata();
	
		switch (state.getValue(FACING).getHorizontalIndex())
		{
			case 1:
				i |= 4;
				break;
			case 2:
				i |= 8;
				break;
			case 3:
				i |= 12;
		}
	
		return i;
    }
    
    @Override
	public String getSpecialName(ItemStack stack)
	{
    	return BlockJackOLantern.EnumFaceType.byMetadata(stack.getMetadata()).getName();
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Item.getItemFromBlock(this.base), 1, ((BlockJackOLantern.EnumFaceType)world.getBlockState(pos).getValue(VARIANT)).getMetadata());
	}
	
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		ItemStack itemstack = playerIn.getHeldItem(hand);
		
		if (!playerIn.canPlayerEdit(pos.offset(facing), facing, itemstack))
        {
            return false;
        }
		else if(this.type == "unlit" && itemstack.getItem() instanceof ItemFlintAndSteel)
		{
			if(!worldIn.isRemote)
			{
				BlockJackOLantern.EnumFaceType variant = worldIn.getBlockState(pos).getValue(VARIANT);
				EnumFacing direction = worldIn.getBlockState(pos).getValue(FACING);
				worldIn.setBlockState(pos, this.fire.getDefaultState().withProperty(VARIANT, variant).withProperty(FACING, direction), 2);
				itemstack.damageItem(1, playerIn);
			}
			return true;
		}
		else if(this.type == "unlit" && itemstack.getItem() == Items.DRAGON_BREATH)
		{
			if(!worldIn.isRemote)
			{
				BlockJackOLantern.EnumFaceType variant = worldIn.getBlockState(pos).getValue(VARIANT);
				EnumFacing direction = worldIn.getBlockState(pos).getValue(FACING);
				worldIn.setBlockState(pos, this.ender.getDefaultState().withProperty(VARIANT, variant).withProperty(FACING, direction), 2);
				itemstack.shrink(1);
				worldIn.spawnEntity(new EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, new ItemStack(Items.GLASS_BOTTLE, 1)));
			}
			return true;
		}
		else if((this.type == "fire" || this.type == "ender") && itemstack.getItem() == Items.POTIONITEM)
		{
			if(!worldIn.isRemote)
			{
				BlockJackOLantern.EnumFaceType variant = worldIn.getBlockState(pos).getValue(VARIANT);
				EnumFacing direction = worldIn.getBlockState(pos).getValue(FACING);
				worldIn.setBlockState(pos, this.base.getDefaultState().withProperty(VARIANT, variant).withProperty(FACING, direction), 2);
				itemstack.shrink(1);
				worldIn.spawnEntity(new EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, new ItemStack(Items.GLASS_BOTTLE, 1)));
			}
			return true;
		}
		else if(itemstack.getItem() instanceof MDagger)
		{
			if(!worldIn.isRemote)
			{
				if(worldIn.getBlockState(pos).getValue(VARIANT).getMetadata() < 2)
				{
					BlockJackOLantern.EnumFaceType variant = worldIn.getBlockState(pos).getValue(VARIANT);
					EnumFacing direction = worldIn.getBlockState(pos).getValue(FACING);
					worldIn.setBlockState(pos, this.getDefaultState().withProperty(VARIANT, BlockJackOLantern.EnumFaceType.byMetadata(variant.getMetadata() + 1)).withProperty(FACING, direction), 2);
					itemstack.damageItem(1, playerIn);
				}
				else
				{
					Block block = MBlocks.pumpkin_smashed;
					
					if(this.type == "fire")
						block = MBlocks.pumpkin_smashed_fire;
					else if(this.type == "ender")
						block = MBlocks.pumpkin_smashed_ender;
					
					EnumFacing direction = worldIn.getBlockState(pos).getValue(FACING);
					worldIn.setBlockState(pos, block.getDefaultState().withProperty(FACING, direction), 2);
					itemstack.damageItem(1, playerIn);
				}
			}
		}
		
		return false;
    }
	
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        double d0 = (double)pos.getX() + 0.5D;
        double d1 = (double)pos.getY() + 0.5D;
        double d2 = (double)pos.getZ() + 0.5D;

        if(this.type == "fire")
        	worldIn.spawnParticle(EnumParticleTypes.FLAME, true, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        else if(this.type == "ender")
        	worldIn.spawnParticle(EnumParticleTypes.REDSTONE, true, d0, d1, d2, 1.0D, 0.0D, 1.0D);
    }
    
    public static enum EnumFaceType implements IStringSerializable
    {
        SIMPLE(0, "simple"),
        AVERAGE(1, "average"),
        COMPLEX(2, "complex");

        private static final BlockJackOLantern.EnumFaceType[] META_LOOKUP = new BlockJackOLantern.EnumFaceType[values().length];
        private final int meta;
        private final String name;

        private EnumFaceType(int meta, String name)
        {
            this.meta = meta;
            this.name = name;
        }

        public int getMetadata()
        {
            return this.meta;
        }

        public String toString()
        {
            return this.name;
        }

        public static BlockJackOLantern.EnumFaceType byMetadata(int meta)
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

        static
        {
            for (BlockJackOLantern.EnumFaceType jack$enumtype : values())
            {
                META_LOOKUP[jack$enumtype.getMetadata()] = jack$enumtype;
            }
        }
    }
    
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        super.onBlockAdded(worldIn, pos, state);
        this.trySpawnGolem(worldIn, pos);
    }

    public boolean canDispenserPlace(World worldIn, BlockPos pos)
    {
        return this.getSnowmanBasePattern().match(worldIn, pos) != null || this.getGolemBasePattern().match(worldIn, pos) != null;
    }

    private void trySpawnGolem(World worldIn, BlockPos pos)
    {
        BlockPattern.PatternHelper blockpattern$patternhelper = this.getSnowmanPattern().match(worldIn, pos);

        if (blockpattern$patternhelper != null)
        {
            for (int i = 0; i < this.getSnowmanPattern().getThumbLength(); ++i)
            {
                BlockWorldState blockworldstate = blockpattern$patternhelper.translateOffset(0, i, 0);
                worldIn.setBlockState(blockworldstate.getPos(), Blocks.AIR.getDefaultState(), 2);
            }

            EntitySnowman entitysnowman = new EntitySnowman(worldIn);
            BlockPos blockpos1 = blockpattern$patternhelper.translateOffset(0, 2, 0).getPos();
            entitysnowman.setLocationAndAngles((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.05D, (double)blockpos1.getZ() + 0.5D, 0.0F, 0.0F);
            worldIn.spawnEntity(entitysnowman);

            for (EntityPlayerMP entityplayermp : worldIn.getEntitiesWithinAABB(EntityPlayerMP.class, entitysnowman.getEntityBoundingBox().grow(5.0D)))
            {
                CriteriaTriggers.SUMMONED_ENTITY.trigger(entityplayermp, entitysnowman);
            }

            for (int l = 0; l < 120; ++l)
            {
                worldIn.spawnParticle(EnumParticleTypes.SNOW_SHOVEL, (double)blockpos1.getX() + worldIn.rand.nextDouble(), (double)blockpos1.getY() + worldIn.rand.nextDouble() * 2.5D, (double)blockpos1.getZ() + worldIn.rand.nextDouble(), 0.0D, 0.0D, 0.0D);
            }

            for (int i1 = 0; i1 < this.getSnowmanPattern().getThumbLength(); ++i1)
            {
                BlockWorldState blockworldstate2 = blockpattern$patternhelper.translateOffset(0, i1, 0);
                worldIn.notifyNeighborsRespectDebug(blockworldstate2.getPos(), Blocks.AIR, false);
            }
        }
        else
        {
            blockpattern$patternhelper = this.getGolemPattern().match(worldIn, pos);

            if (blockpattern$patternhelper != null)
            {
                for (int j = 0; j < this.getGolemPattern().getPalmLength(); ++j)
                {
                    for (int k = 0; k < this.getGolemPattern().getThumbLength(); ++k)
                    {
                        worldIn.setBlockState(blockpattern$patternhelper.translateOffset(j, k, 0).getPos(), Blocks.AIR.getDefaultState(), 2);
                    }
                }

                BlockPos blockpos = blockpattern$patternhelper.translateOffset(1, 2, 0).getPos();
                EntityIronGolem entityirongolem = new EntityIronGolem(worldIn);
                entityirongolem.setPlayerCreated(true);
                entityirongolem.setLocationAndAngles((double)blockpos.getX() + 0.5D, (double)blockpos.getY() + 0.05D, (double)blockpos.getZ() + 0.5D, 0.0F, 0.0F);
                worldIn.spawnEntity(entityirongolem);

                for (EntityPlayerMP entityplayermp1 : worldIn.getEntitiesWithinAABB(EntityPlayerMP.class, entityirongolem.getEntityBoundingBox().grow(5.0D)))
                {
                    CriteriaTriggers.SUMMONED_ENTITY.trigger(entityplayermp1, entityirongolem);
                }

                for (int j1 = 0; j1 < 120; ++j1)
                {
                    worldIn.spawnParticle(EnumParticleTypes.SNOWBALL, (double)blockpos.getX() + worldIn.rand.nextDouble(), (double)blockpos.getY() + worldIn.rand.nextDouble() * 3.9D, (double)blockpos.getZ() + worldIn.rand.nextDouble(), 0.0D, 0.0D, 0.0D);
                }

                for (int k1 = 0; k1 < this.getGolemPattern().getPalmLength(); ++k1)
                {
                    for (int l1 = 0; l1 < this.getGolemPattern().getThumbLength(); ++l1)
                    {
                        BlockWorldState blockworldstate1 = blockpattern$patternhelper.translateOffset(k1, l1, 0);
                        worldIn.notifyNeighborsRespectDebug(blockworldstate1.getPos(), Blocks.AIR, false);
                    }
                }
            }
        }
    }
    
    protected BlockPattern getSnowmanBasePattern()
    {
        if (this.snowmanBasePattern == null)
        {
            this.snowmanBasePattern = FactoryBlockPattern.start().aisle(" ", "#", "#").where('#', BlockWorldState.hasState(BlockStateMatcher.forBlock(Blocks.SNOW))).build();
        }

        return this.snowmanBasePattern;
    }

    protected BlockPattern getSnowmanPattern()
    {
        if (this.snowmanPattern == null)
        {
            this.snowmanPattern = FactoryBlockPattern.start().aisle("^", "#", "#").where('^', BlockWorldState.hasState(BlockStateMatcher.forBlock(this))).where('#', BlockWorldState.hasState(BlockStateMatcher.forBlock(Blocks.SNOW))).build();
        }

        return this.snowmanPattern;
    }

    protected BlockPattern getGolemBasePattern()
    {
        if (this.golemBasePattern == null)
        {
            this.golemBasePattern = FactoryBlockPattern.start().aisle("~ ~", "###", "~#~").where('#', BlockWorldState.hasState(BlockStateMatcher.forBlock(Blocks.IRON_BLOCK))).where('~', BlockWorldState.hasState(BlockMaterialMatcher.forMaterial(Material.AIR))).build();
        }

        return this.golemBasePattern;
    }

    protected BlockPattern getGolemPattern()
    {
        if (this.golemPattern == null)
        {
            this.golemPattern = FactoryBlockPattern.start().aisle("~^~", "###", "~#~").where('^', BlockWorldState.hasState(BlockStateMatcher.forBlock(this))).where('#', BlockWorldState.hasState(BlockStateMatcher.forBlock(Blocks.IRON_BLOCK))).where('~', BlockWorldState.hasState(BlockMaterialMatcher.forMaterial(Material.AIR))).build();
        }

        return this.golemPattern;
    }
}
