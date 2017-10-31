package minestrapp.block;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.properties.PropertyEnum;

public class BlockMLog extends BlockLog
{
	public static final PropertyEnum<BlockMPlanks.EnumType> VARIANT = PropertyEnum.<BlockMPlanks.EnumType>create("variant", BlockMPlanks.EnumType.class, new Predicate<BlockMPlanks.EnumType>()
    {
        public boolean apply(@Nullable BlockMPlanks.EnumType p_apply_1_)
        {
            return p_apply_1_.getMetadata() <= 3;
        }
    });
	
	public BlockMLog()
    {
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockMPlanks.EnumType.REDWOOD).withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
    }
}
