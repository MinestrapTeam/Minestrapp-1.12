package minestrapp.block.util;

import java.util.Random;

import minestrapp.entity.block.EntityMFallingBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMFalling extends BlockFalling
{
	private boolean glowing;
	
	public BlockMFalling(String name, Material material, SoundType sound, float hardness, String tool, int harvest)
	{
		super(material);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setSoundType(sound);
		this.setHardness(hardness);
		this.setHarvestLevel(tool, harvest);
	}
	
	/*public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
            this.checkFallable(worldIn, pos);
        }
    }
	
	private void checkFallable(World worldIn, BlockPos pos)
    {
        if ((worldIn.isAirBlock(pos.down()) || canFallThrough(worldIn.getBlockState(pos.down()))) && pos.getY() >= 0)
        {
            int i = 32;

            if (!fallInstantly && worldIn.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32)))
            {
                //if (!worldIn.isRemote)
                //{
                    EntityMFallingBlock entityfallingblock = new EntityMFallingBlock(worldIn, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, worldIn.getBlockState(pos));
                    this.onStartFalling(entityfallingblock);
                    worldIn.spawnEntity(entityfallingblock);
                //}
            }
            else
            {
                IBlockState state = worldIn.getBlockState(pos);
                worldIn.setBlockToAir(pos);
                BlockPos blockpos;

                for (blockpos = pos.down(); (worldIn.isAirBlock(blockpos) || canFallThrough(worldIn.getBlockState(blockpos))) && blockpos.getY() > 0; blockpos = blockpos.down())
                {
                    ;
                }

                if (blockpos.getY() > 0)
                {
                    worldIn.setBlockState(blockpos.up(), state);
                }
            }
        }
    }*/
	
	public BlockMFalling setGlowing()
	{
		this.glowing = true;
		return this;
	}
	
	@SideOnly(Side.CLIENT)
    public int getPackedLightmapCoords(IBlockState state, IBlockAccess source, BlockPos pos)
    {
		if(this.glowing)
			return 15728880;
		else
			return super.getPackedLightmapCoords(state, source, pos);
    }
}
