package eyeq.util.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.math.Vec3d;

public class EntityLivingUtils {
    public static void updateLeashedState(EntityLiving theEntity) {
        double speed;
        if(theEntity instanceof IEntityLeashable) {
            speed = ((IEntityLeashable) theEntity).followLeashSpeed();
        } else {
            speed = 1.0;
        }
        updateLeashedState(theEntity, speed);
    }

    public static void updateLeashedState(EntityLiving theEntity, double speed) {
        Entity entity = theEntity.getLeashedToEntity();
        if(!theEntity.getLeashed() || entity == null || entity.world != theEntity.world) {
            return;
        }
        float f = theEntity.getDistanceToEntity(entity);
        if(theEntity instanceof IEntityLeashable) {
            ((IEntityLeashable) theEntity).onLeashDistance(f);
        }
        if(f > 10.0F) {
            theEntity.clearLeashed(true, true);
            theEntity.tasks.disableControlFlag(1);
        } else if(f > 6.0F) {
            double x = (entity.posX - theEntity.posX) / (double) f;
            double y = (entity.posY - theEntity.posY) / (double) f;
            double z = (entity.posZ - theEntity.posZ) / (double) f;
            theEntity.motionX += x * Math.abs(x) * 0.4;
            theEntity.motionY += y * Math.abs(y) * 0.4;
            theEntity.motionZ += z * Math.abs(z) * 0.4;
        } else {
            theEntity.tasks.enableControlFlag(1);
            Vec3d vec3d = (new Vec3d(entity.posX - theEntity.posX, entity.posY - theEntity.posY, entity.posZ - theEntity.posZ)).normalize().scale(Math.max(f - 2.0F, 0.0F));
            theEntity.getNavigator().tryMoveToXYZ(theEntity.posX + vec3d.xCoord, theEntity.posY + vec3d.yCoord, theEntity.posZ + vec3d.zCoord, speed);
        }
    }
}
