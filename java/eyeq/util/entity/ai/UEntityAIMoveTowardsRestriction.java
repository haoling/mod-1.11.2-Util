package eyeq.util.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;

public class UEntityAIMoveTowardsRestriction extends EntityAIBase {
    private final EntityLiving entity;
    private double movePosX;
    private double movePosY;
    private double movePosZ;
    private double speed;

    public UEntityAIMoveTowardsRestriction(EntityLiving entity, double speed) {
        this.entity = entity;
        this.speed = speed;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute() {
        EntityMoveHelper move = this.entity.getMoveHelper();
        if(!move.isUpdating()) {
            return true;
        }
        this.movePosX = move.getX() - this.entity.posX;
        this.movePosY = move.getY() - this.entity.posY;
        this.movePosZ = move.getZ() - this.entity.posZ;
        double d = movePosX * movePosX + movePosY * movePosY + movePosZ * movePosZ;
        return d < 1.0 || d > 3600.0;
    }

    @Override
    public boolean continueExecuting() {
        return !this.entity.getNavigator().noPath();
    }

    @Override
    public void startExecuting() {
        this.entity.getNavigator().tryMoveToXYZ(this.movePosX, this.movePosY, this.movePosZ, this.speed);
    }
}