package minestrapp.block;

import java.util.Random;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMDoor extends BlockDoor
{
	private Item doorItem;
	private MapColor mapColor;
	
	public BlockMDoor(String name, Material material, MapColor color)
	{
		super(material);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.mapColor = color;
		this.setHardness(3F);
	}
	
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
		return this.mapColor;
    }
	
	public void setDoorItem(Item item)
	{
		this.doorItem = item;
	}
	
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(this.doorItem);
    }
	
    @SideOnly(Side.CLIENT)
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this.doorItem, 1);
    }
    
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER ? null : this.doorItem;
    }
}
