package minestrapp.block.tileentity;

import javax.annotation.Nullable;

import minestrapp.block.BlockMSkull;
import minestrapp.block.BlockMSkull.EnumAngle;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMSkull extends TileEntity
{
	private BlockMSkull.EnumAngle angle = EnumAngle.ANGLE0;
	
	public TileEntityMSkull()
    {
    }

    public TileEntityMSkull(BlockMSkull.EnumAngle angle)
    {
        this.angle = angle;
    }
    
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("SkullAngle", this.angle.getMetadata());
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.angle = BlockMSkull.EnumAngle.byMetadata(compound.getInteger("SkullAngle"));
    }

    @Nullable
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        return new SPacketUpdateTileEntity(this.pos, 5, this.getUpdateTag());
    }

    public NBTTagCompound getUpdateTag()
    {
        return this.writeToNBT(new NBTTagCompound());
    }

    public void setAngle(BlockMSkull.EnumAngle angle)
    {
        this.angle = angle;
        this.markDirty();
    }

    public BlockMSkull.EnumAngle getAngle()
    {
        return this.angle;
    }
}
