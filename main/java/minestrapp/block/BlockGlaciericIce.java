package minestrapp.block;

import java.util.Random;

import minestrapp.MItems;
import minestrapp.MTabs;
import minestrapp.block.util.BlockBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGlaciericIce extends BlockBase
{
	public BlockGlaciericIce()
	{
		super("glacieric_ice", Material.PACKED_ICE, MapColor.ICE, SoundType.GLASS, 0.7F);
		this.setSlipperiness(1.1F);
		this.setCreativeTab(MTabs.utility);
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
    
    public int quantityDropped(Random random)
    {
        return random.nextInt(4) + 1;
    }
    
    public int quantityDroppedWithBonus(int fortune, Random random)
    {
    	int bonus = 4 - fortune;
    	
    	if(bonus < 1);
    		bonus = 1;
    		
		return 4 - random.nextInt(bonus);
    }
    
    public int damageDropped(IBlockState state)
    {
		return 6;
    }
    
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
    	return MItems.gems;
    }
}
