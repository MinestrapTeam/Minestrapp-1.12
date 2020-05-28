package minestrapp.block;

import minestrapp.MBlocks;
import minestrapp.item.tools.MDagger;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockJackOLanternSmashed extends BlockJackOLantern
{
	private String type;
	private Block base;
	private Block fire;
	private Block ender;
	
	public BlockJackOLanternSmashed(String name, String type, Block block1, Block block2)
	{
		super(name, type, block1, block2);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.type = type;
		this.base = MBlocks.pumpkin_smashed;
		this.fire = MBlocks.pumpkin_smashed_fire;
		this.ender = MBlocks.pumpkin_smashed_ender;
	}
	
	protected BlockStateContainer createBlockState()
    {
    	return new BlockStateContainer(this, new IProperty[] {FACING});
    }
	
	public int damageDropped(IBlockState state)
    {
    	return 0;
    }
	
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> tab)
    {
		if(this.type == "unlit")
			tab.add(new ItemStack(this, 1, 0));
    }
	
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
    	return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }
	
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
    }
	
	public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
    }
	
	@Override
	public String getSpecialName(ItemStack stack)
	{
    	return null;
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Item.getItemFromBlock(this.base), 1, 0);
	}
	
	public boolean isLit()
    {
    	return this.type != "unlit";
    }
    
    public Block getUnlit() {return this.base;}
    
    public Block getFire() {return this.fire;}
    
    public Block getEnder() {return this.ender;}
	
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
				EnumFacing direction = worldIn.getBlockState(pos).getValue(FACING);
				worldIn.setBlockState(pos, MBlocks.pumpkin_smashed_fire.getDefaultState().withProperty(FACING, direction), 2);
				itemstack.damageItem(1, playerIn);
			}
			return true;
		}
		else if(this.type == "unlit" && itemstack.getItem() == Items.DRAGON_BREATH)
		{
			if(!worldIn.isRemote)
			{
				EnumFacing direction = worldIn.getBlockState(pos).getValue(FACING);
				worldIn.setBlockState(pos, MBlocks.pumpkin_smashed_ender.getDefaultState().withProperty(FACING, direction), 2);
				itemstack.shrink(1);
				worldIn.spawnEntity(new EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, new ItemStack(Items.GLASS_BOTTLE, 1)));
			}
			return true;
		}
		else if((this.type == "fire" || this.type == "ender") && itemstack.getItem() == Items.POTIONITEM)
		{
			if(!worldIn.isRemote)
			{
				EnumFacing direction = worldIn.getBlockState(pos).getValue(FACING);
				worldIn.setBlockState(pos, MBlocks.pumpkin_smashed.getDefaultState().withProperty(FACING, direction), 2);
				itemstack.shrink(1);
				worldIn.spawnEntity(new EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, new ItemStack(Items.GLASS_BOTTLE, 1)));
			}
			return true;
		}
		
		return false;
    }
}
