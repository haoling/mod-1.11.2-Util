package eyeq.util.dispenser;

import eyeq.util.item.IItemSpawnEntity;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

public class UBehaviorDispenseEntity extends BehaviorDefaultDispenseItem {
    public int soundId = 1000;
    public float velocity = 1.1F;
    public float inaccuracy = 6.0F;

    @Override
    public ItemStack dispenseStack(IBlockSource source, ItemStack itemStack) {
        EnumFacing facing = source.getBlockState().getValue(BlockDispenser.FACING);
        double x = source.getX() + facing.getFrontOffsetX();
        double y = source.getBlockPos().getY() + 0.2F;
        double z = source.getZ() + facing.getFrontOffsetZ();
        Entity entity = ((IItemSpawnEntity) itemStack.getItem()).spawnEntity(itemStack, source.getWorld(), x, y, z);
        if(entity instanceof IProjectile) {
            ((IProjectile) entity).setThrowableHeading(facing.getFrontOffsetX(), facing.getFrontOffsetY() + 0.1F, facing.getFrontOffsetZ(), this.velocity, this.inaccuracy);
        }
        itemStack.splitStack(1);
        return itemStack;
    }

    @Override
    protected void playDispenseSound(IBlockSource source) {
        source.getWorld().playEvent(soundId, source.getBlockPos(), 0);
    }

    // if IProjectile, id = 1002
    public UBehaviorDispenseEntity setSoundId(int id) {
        this.soundId = id;
        return this;
    }

    public UBehaviorDispenseEntity setVeiocity(float velocity) {
        this.velocity = velocity;
        return this;
    }

    public UBehaviorDispenseEntity setInaccuracy(float inaccuracy) {
        this.inaccuracy = inaccuracy;
        return this;
    }
}
