package minestrapp.block;

import minestrapp.MTabs;
import minestrapp.block.util.BlockMechanical;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCreativeEngine extends BlockMechanical
{
	public BlockCreativeEngine()
	{
		super("creative_engine", Material.WOOD, MapColor.BROWN, SoundType.METAL, -1F);
		this.setBlockUnbreakable();
		this.setEntityInvulnerability("all");
		this.setResistance(9999F);
		this.setCreativeTab(MTabs.utility);
	}
	
	public int isProvidingPowerFromFace(World world, BlockPos pos, EnumFacing face)
	{
		return 15;
	}
	
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState();
    }
    
    public int getMetaFromState(IBlockState state)
    {
    	return 0;
    }
}
