package minestrapp.block;

import minestrapp.MTabs;
import minestrapp.block.util.BlockBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockInvincium extends BlockBase
{
	public BlockInvincium()
	{
		super("invincium", Material.ROCK, MapColor.BLACK, SoundType.STONE, -1F);
		this.setEntityInvulnerability("all");
		this.setPushReaction(EnumPushReaction.BLOCK);
		this.setBlockUnbreakable();
		this.setLightLevel(0.2F);
		this.setCreativeTab(MTabs.environment);
	}
	
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
    {
        if (!entityIn.isImmuneToFire() && entityIn instanceof EntityLivingBase && !EnchantmentHelper.hasFrostWalkerEnchantment((EntityLivingBase)entityIn))
            entityIn.attackEntityFrom(DamageSource.HOT_FLOOR, 1F);

        super.onEntityWalk(worldIn, pos, entityIn);
    }
	
	public boolean canEntitySpawn(IBlockState state, Entity entityIn)
    {
        return entityIn.isImmuneToFire();
    }
}
