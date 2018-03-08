package minestrapp.block;

import java.util.Random;

import minestrapp.MTabs;
import minestrapp.block.util.BlockStoneBaseMOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockSilverfishStone extends BlockStoneBaseMOnly
{
	private Block dropBlock;
	
	public BlockSilverfishStone(String name, Block dropBlock)
	{
		super(name, Material.CLAY, SoundType.STONE, 0F, null, 0);
		this.setCreativeTab(MTabs.stone);
		this.dropBlock = dropBlock;
	}
	
	public int quantityDropped(Random random)
    {
        return 0;
    }
	
	protected ItemStack getSilkTouchDrop(IBlockState state)
    {
        switch ((EnumStoneTypeMOnly)state.getValue(VARIANT))
        {
            case RED_ROCK:
                return new ItemStack(this.dropBlock, 1, EnumStoneTypeMOnly.RED_ROCK.getMetadata());
            case DEEP_RED_ROCK:
            	return new ItemStack(this.dropBlock, 1, EnumStoneTypeMOnly.DEEP_RED_ROCK.getMetadata());
            case DEEPSTONE:
            	return new ItemStack(this.dropBlock, 1, EnumStoneTypeMOnly.DEEPSTONE.getMetadata());
            case COLDSTONE:
            	return new ItemStack(this.dropBlock, 1, EnumStoneTypeMOnly.COLDSTONE.getMetadata());
            case DEEP_COLDSTONE:
            	return new ItemStack(this.dropBlock, 1, EnumStoneTypeMOnly.DEEP_COLDSTONE.getMetadata());
            case ICESTONE:
            	return new ItemStack(this.dropBlock, 1, EnumStoneTypeMOnly.ICESTONE.getMetadata());
            case GLACIERROCK:
            	return new ItemStack(this.dropBlock, 1, EnumStoneTypeMOnly.GLACIERROCK.getMetadata());
            case OCEANSTONE:
            	return new ItemStack(this.dropBlock, 1, EnumStoneTypeMOnly.OCEANSTONE.getMetadata());
            case DEEP_OCEANSTONE:
            	return new ItemStack(this.dropBlock, 1, EnumStoneTypeMOnly.DEEP_OCEANSTONE.getMetadata());
            default:
            	return new ItemStack(this.dropBlock, 1, EnumStoneTypeMOnly.RED_ROCK.getMetadata());
        }
    }
	
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {
        if (!worldIn.isRemote && worldIn.getGameRules().getBoolean("doTileDrops"))
        {
            EntitySilverfish entitysilverfish = new EntitySilverfish(worldIn);
            entitysilverfish.setLocationAndAngles((double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, 0.0F, 0.0F);
            worldIn.spawnEntity(entitysilverfish);
            entitysilverfish.spawnExplosionParticle();
        }
    }
}
