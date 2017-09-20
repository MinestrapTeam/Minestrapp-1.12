package minestrapp.block;

import java.util.Random;

import minestrapp.MBlocks;
import minestrapp.MItems;
import minestrapp.MTabs;
import minestrapp.block.util.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGlaciericIceDeposit extends BlockBase
{
	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 5);
	protected static final AxisAlignedBB GLACIERIC_ICE_AABB = new AxisAlignedBB(0.25D, 0D, 0.25D, 0.75D, 0.5D, 0.75D);
	
	public BlockGlaciericIceDeposit()
	{
		super("glacieric_ice_deposit", Material.PACKED_ICE, MapColor.ICE, SoundType.GLASS, -1F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(this.getAgeProperty(), Integer.valueOf(0)));
		this.setCreativeTab(MTabs.environment);
		this.setBlockUnbreakable();
		this.setEntityInvulnerability("all");
		this.setPushReaction(EnumPushReaction.BLOCK);
		this.setSlipperiness(1F);
		this.setTickRandomly(true);
	}
	
	public IBlockState getStateFromMeta(int meta)
    {
        return this.withAge(meta);
    }
    
    public int getMetaFromState(IBlockState state)
    {
        return this.getAge(state);
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {AGE});
    }
	
	public AxisAlignedBB getBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
		return GLACIERIC_ICE_AABB;
    }
	
	public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing)
    {
		return BlockFaceShape.UNDEFINED;
    }
	
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }
    
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        Block block = worldIn.getBlockState(pos.down()).getBlock();

        return block == MBlocks.glacial_invincium;
    }
    
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        this.checkAndDropBlock(worldIn, pos, state);
    }
    
    private boolean checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!this.canPlaceBlockAt(worldIn, pos))
        {
            worldIn.setBlockToAir(pos);
            return false;
        }
        else
            return true;
    }
    
    protected PropertyInteger getAgeProperty()
    {
        return AGE;
    }
	
	public int getMaxAge()
    {
        return 5;
    }
	
	protected int getAge(IBlockState state)
    {
        return ((Integer)state.getValue(this.getAgeProperty())).intValue();
    }

    public IBlockState withAge(int age)
    {
        return this.getDefaultState().withProperty(this.getAgeProperty(), Integer.valueOf(age));
    }

    public boolean isMaxAge(IBlockState state)
    {
        return ((Integer)state.getValue(this.getAgeProperty())).intValue() >= this.getMaxAge();
    }
    
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
    	int chance = 5;
    	if(worldIn.isRaining())
    		chance -= 3;
    	if(!worldIn.isDaytime())
    		chance -= 1;
    	if(rand.nextInt(chance) == 0 && this.getAge(state) < this.getMaxAge())
    		worldIn.setBlockState(pos, state.withProperty(AGE, this.getAge(state) + 1));
    }
    
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
    	Item heldItem = playerIn.getHeldItem(hand).getItem();
    	if(!worldIn.isRemote && this.getAge(state) == this.getMaxAge() && heldItem instanceof ItemPickaxe && ToolMaterial.valueOf(((ItemPickaxe) heldItem).getToolMaterialName()).getHarvestLevel() >= 2)
    	{
    		worldIn.playSound(playerIn, pos, SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.BLOCKS, 1, 1);
    		playerIn.getHeldItem(hand).damageItem(1, playerIn);
    		EntityItem item = new EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, new ItemStack(MItems.gems, 1, 6));
    		worldIn.spawnEntity(item);
    		worldIn.setBlockState(pos, state.withProperty(AGE, 0));
    		return true;
    	}
    	else
    		return false;
    }
}
