package eyeq.util.entity.ai;


import com.google.common.collect.Sets;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigateGround;

import java.util.Set;

public class UEntityAITempt extends EntityAIBase {
    private final EntityLiving temptedEntity;
    private final double speed;
    private final boolean scaredByPlayerMovement;
    private final Set<Item> temptItem;

    private double targetX;
    private double targetY;
    private double targetZ;

    private double targetPitch;
    private double targetYaw;
    private EntityPlayer temptingPlayer;

    private int delayTemptCounter;
    private boolean isRunning;

    public UEntityAITempt(EntityLiving temptedEntity, double speed, boolean scaredByPlayerMovement, Set<Item> temptItem) {
        this.temptedEntity = temptedEntity;
        this.speed = speed;
        this.scaredByPlayerMovement = scaredByPlayerMovement;
        this.temptItem = temptItem;
        this.setMutexBits(3);
        if(!(temptedEntity.getNavigator() instanceof PathNavigateGround)) {
            throw new IllegalArgumentException("Unsupported mob type for TemptGoal");
        }
    }

    public UEntityAITempt(EntityLiving temptedEntity, double speed, boolean scaredByPlayerMovement, Item temptItem) {
        this(temptedEntity, speed, scaredByPlayerMovement, Sets.newHashSet(temptItem));
    }

    @Override
    public boolean shouldExecute() {
        if(this.delayTemptCounter > 0) {
            this.delayTemptCounter--;
            return false;
        }
        this.temptingPlayer = this.temptedEntity.world.getClosestPlayerToEntity(this.temptedEntity, 10);
        if(this.temptingPlayer == null) {
            return false;
        }
        return isTempting(this.temptingPlayer.getHeldItemMainhand()) || isTempting(temptingPlayer.getHeldItemOffhand());
    }

    protected boolean isTempting(ItemStack itemStack)
    {
        return this.temptItem.contains(itemStack.getItem());
    }

    @Override
    public boolean continueExecuting() {
        if(!this.scaredByPlayerMovement) {
            return this.shouldExecute();
        }
        if(this.temptedEntity.getDistanceSqToEntity(this.temptingPlayer) < 36.0) {
            if(this.temptingPlayer.getDistanceSq(this.targetX, this.targetY, this.targetZ) > 0.01) {
                return false;
            }
            if(Math.abs(this.temptingPlayer.rotationPitch - this.targetPitch) > 5.0 || Math.abs((double) this.temptingPlayer.rotationYaw - this.targetYaw) > 5.0) {
                return false;
            }
        } else {
            this.targetX = this.temptingPlayer.posX;
            this.targetY = this.temptingPlayer.posY;
            this.targetZ = this.temptingPlayer.posZ;
        }
        this.targetPitch = this.temptingPlayer.rotationPitch;
        this.targetYaw = this.temptingPlayer.rotationYaw;
        return this.shouldExecute();
    }

    @Override
    public void startExecuting() {
        this.targetX = this.temptingPlayer.posX;
        this.targetY = this.temptingPlayer.posY;
        this.targetZ = this.temptingPlayer.posZ;
        this.isRunning = true;
    }

    @Override
    public void resetTask() {
        this.temptingPlayer = null;
        this.temptedEntity.getNavigator().clearPathEntity();
        this.delayTemptCounter = 100;
        this.isRunning = false;
    }

    @Override
    public void updateTask() {
        this.temptedEntity.getLookHelper().setLookPositionWithEntity(this.temptingPlayer, this.temptedEntity.getHorizontalFaceSpeed() + 20, this.temptedEntity.getVerticalFaceSpeed());
        if(this.temptedEntity.getDistanceSqToEntity(this.temptingPlayer) < 6.25) {
            this.temptedEntity.getNavigator().clearPathEntity();
        } else {
            this.temptedEntity.getNavigator().tryMoveToEntityLiving(this.temptingPlayer, this.speed);
        }
    }

    public boolean isRunning() {
        return this.isRunning;
    }
}
