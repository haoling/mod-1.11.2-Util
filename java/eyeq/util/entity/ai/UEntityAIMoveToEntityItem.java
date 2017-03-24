package eyeq.util.entity.ai;

import java.util.Set;

import com.google.common.collect.Sets;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;

public class UEntityAIMoveToEntityItem extends EntityAIBase {
    protected final EntityLiving entity;
    private final double speed;
    private final float distance;
    private final Set<Item> targetItem;
    protected EntityItem target;

    public UEntityAIMoveToEntityItem(EntityLiving entity, double speed, float distance, Set<Item> targetItem) {
        this.entity = entity;
        this.speed = speed;
        this.distance = distance;
        this.targetItem = targetItem;
        this.setMutexBits(3);
    }

    public UEntityAIMoveToEntityItem(EntityLiving entity, double speed, float distance, Item targetItem) {
        this(entity, speed, distance, Sets.newHashSet(targetItem));
    }

    @Override
    public void resetTask() {
        target = null;
    }

    @Override
    public boolean continueExecuting() {
        return target != null && target.isEntityAlive();
    }

    @Override
    public void updateTask() {
        entity.getNavigator().tryMoveToXYZ(target.posX, target.posY, target.posZ, speed);
        entity.getLookHelper().setLookPositionWithEntity(target, 10F, entity.getVerticalFaceSpeed());
    }

    @Override
    public boolean shouldExecute() {
        if(target != null) {
            return entity.getDistanceToEntity(target) < distance;
        }
        for(EntityItem entityItem : entity.world.getEntitiesWithinAABB(EntityItem.class, entity.getEntityBoundingBox().expand(distance, distance, distance))) {
            if(targetItem.contains(entityItem.getEntityItem().getItem())) {
                target = entityItem;
                return true;
            }
        }
        return false;
    }
}
