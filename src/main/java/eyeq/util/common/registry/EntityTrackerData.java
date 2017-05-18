package eyeq.util.common.registry;

import net.minecraft.entity.*;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.*;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.entity.projectile.EntityShulkerBullet;
import net.minecraft.entity.projectile.EntityThrowable;

import java.util.LinkedHashMap;
import java.util.Map;

public class EntityTrackerData {
    protected static final Map<Class, Data> dataFromClass = new LinkedHashMap<>();

    // EntityTracker ## public void trackEntity(Entity entityIn)
    static {
        // EntityPlayerMP
        registerData(EntityPlayerMP.class, 512, 2, false);
        // EntityFishHook
        registerData(EntityFishHook.class, 64, 5, true);
        // EntitySmallFireball EntityFireball
        registerData(EntityFireball.class, 64, 10, false);
        // EntitySnowball EntityEnderPearl EntityEgg EntityPotion EntityExpBottle
        registerData(EntityThrowable.class, 64, 10, true);
        // EntityArrow EntityThrowable
        registerData(IProjectile.class, 64, 20, false);
        // EntityEnderEye
        registerData(EntityEnderEye.class, 64, 4, true);
        // EntityFireworkRocket
        registerData(EntityFireworkRocket.class, 64, 10, true);
        // EntityItem
        registerData(EntityItem.class, 64, 20, true);
        // EntityMinecart
        registerData(EntityMinecart.class, 80, 3, true);
        // EntityBoat
        registerData(EntityBoat.class, 80, 3, true);
        // EntitySquid
        registerData(EntityWaterMob.class, 64, 3, true);
        // EntityWither
        registerData(EntityWither.class, 80, 3, false);
        // EntityShulkerBullet
        registerData(EntityShulkerBullet.class, 80, 3, true);
        // EntityBat
        registerData(EntityAmbientCreature.class, 80, 3, false);
        // EntityDragon
        registerData(EntityDragon.class, 160, 3, true);
        // IAnimals IMob
        registerData(IAnimals.class, 80, 3, true);
        // EntityTNTPrimed
        registerData(EntityTNTPrimed.class, 160, 10, true);
        // EntityFallingBlock
        registerData(EntityFallingBlock.class, 160, 20, true);
        // EntityHanging
        registerData(EntityHanging.class, 160, Integer.MAX_VALUE, false);
        // EntityArmorStand
        registerData(EntityLivingBase.class, 160, 3, true);
        // EntityXPOrb
        registerData(EntityXPOrb.class, 160, 20, true);
        // EntityAreaEffectCloud
        registerData(EntityAreaEffectCloud.class, 160, Integer.MAX_VALUE, true);
        // EntityEnderCrystal
        registerData(EntityEnderCrystal.class, 256, Integer.MAX_VALUE, false);
    }

    public static void registerData(Class entityClass, int trackingRange, int updateFrequency, boolean sendVelocityUpdates) {
        dataFromClass.put(entityClass, new Data(trackingRange, updateFrequency, sendVelocityUpdates));
    }

    protected static Data getData(Class<? extends Entity> entityClass) {
        for(Class aClass : dataFromClass.keySet()) {
            if(aClass.isAssignableFrom(entityClass)) {
                return dataFromClass.get(aClass);
            }
        }
        return new Data(80, 3, false);
    }

    private final Data data;

    public EntityTrackerData(Class<? extends Entity> entityClass) {
        data = getData(entityClass);
    }

    public int getTrackingRange() {
        return data.trackingRange;
    }

    public int getUpdateFrequency() {
        return data.updateFrequency;
    }

    public boolean isSendVelocityUpdates() {
        return data.sendVelocityUpdates;
    }

    private static class Data {
        private final int trackingRange;
        private final int updateFrequency;
        private final boolean sendVelocityUpdates;

        public Data(int trackingRange, int updateFrequency, boolean sendVelocityUpdates) {
            this.trackingRange = trackingRange;
            this.updateFrequency = updateFrequency;
            this.sendVelocityUpdates = sendVelocityUpdates;
        }
    }
}
