package eyeq.util.client.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityRendererUtils {
    public static Entity getMouseOver(float partialTicks, float blockReachDistance) {
        Minecraft mc = Minecraft.getMinecraft();
        return getMouseOver(mc.world, mc.getRenderViewEntity(), partialTicks, blockReachDistance);
    }

    public static Entity getMouseOver(World world, Entity entity, float partialTicks, float blockReachDistance) {
        if(entity == null || world == null) {
            return null;
        }
        RayTraceResult mouseOver = entity.rayTrace(blockReachDistance, partialTicks);
        Vec3d positionEyes = entity.getPositionEyes(partialTicks);
        final double distanceMax;
        if(mouseOver != null) {
            distanceMax = mouseOver.hitVec.distanceTo(positionEyes);
        } else {
            distanceMax = blockReachDistance;
        }

        Vec3d look = entity.getLook(1.0F);
        look = new Vec3d(look.xCoord * blockReachDistance, look.yCoord * blockReachDistance, look.zCoord * blockReachDistance);

        Vec3d view = positionEyes.add(look);
        AxisAlignedBB aabb = entity.getEntityBoundingBox().addCoord(look.xCoord, look.yCoord, look.zCoord).expand(1.0, 1.0, 1.0);
        double distance = distanceMax;
        Entity pointedEntity = null;
        for(Entity target : world.getEntitiesWithinAABBExcludingEntity(entity, aabb)) {
            if(!target.canBeCollidedWith()) {
                continue;
            }
            AxisAlignedBB targetAabb = target.getEntityBoundingBox().expandXyz(target.getCollisionBorderSize());
            RayTraceResult result = targetAabb.calculateIntercept(positionEyes, view);
            if(targetAabb.isVecInside(positionEyes)) {
                if(0.0 <= distance) {
                    pointedEntity = target;
                    distance = 0.0;
                }
            } else if(result != null) {
                double distanceTemp = positionEyes.distanceTo(result.hitVec);
                if(distanceTemp < distance || distance == 0.0) {
                    pointedEntity = target;
                    distance = distanceTemp;
                }
            }
        }
        if(distance < distanceMax || mouseOver == null) {
            return pointedEntity;
        }
        return null;
    }

    public static double getDistance(Entity entity, Entity target, float partialTicks, float blockReachDistance) {
        Vec3d positionEyes = entity.getPositionEyes(partialTicks);
        Vec3d look = entity.getLook(1.0F);
        look = new Vec3d(look.xCoord * blockReachDistance, look.yCoord * blockReachDistance, look.zCoord * blockReachDistance);

        Vec3d view = positionEyes.add(look);
        AxisAlignedBB targetAabb = target.getEntityBoundingBox().expandXyz(target.getCollisionBorderSize());
        RayTraceResult result = targetAabb.calculateIntercept(positionEyes, view);
        if(result == null) {
            return -1;
        }
        return positionEyes.distanceTo(result.hitVec);
    }
}
