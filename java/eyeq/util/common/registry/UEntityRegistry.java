package eyeq.util.common.registry;

import eyeq.util.client.renderer.ResourceLocationFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class UEntityRegistry {
    public static void registerModEntity(ResourceLocationFactory resource, Class<? extends Entity> entityClass, String entityName, int id, Object mod) {
        registerModEntity(resource.createResourceLocation(entityName), entityClass, entityName, id, mod);
    }

    public static void registerModEntity(ResourceLocation registryName, Class<? extends Entity> entityClass, String entityName, int id, Object mod) {
        EntityTrackerData data = new EntityTrackerData(entityClass);
        EntityRegistry.registerModEntity(registryName, entityClass, entityName, id, mod, data.getTrackingRange(), data.getUpdateFrequency(), data.isSendVelocityUpdates());
    }

    public static void registerModEntity(ResourceLocationFactory resource, Class<? extends Entity> entityClass, String entityName, int id, Object mod, EntityList.EntityEggInfo egg) {
        registerModEntity(resource.createResourceLocation(entityName), entityClass, entityName, id, mod, egg);
    }
    public static void registerModEntity(ResourceLocation registryName, Class<? extends Entity> entityClass, String entityName, int id, Object mod, EntityList.EntityEggInfo egg) {
        registerModEntity(registryName, entityClass, entityName, id, mod, egg.primaryColor, egg.secondaryColor);
    }

    public static void registerModEntity(ResourceLocationFactory resource, Class<? extends Entity> entityClass, String entityName, int id, Object mod, int eggPrimary, int eggSecondary) {
        registerModEntity(resource.createResourceLocation(entityName), entityClass, entityName, id, mod, eggPrimary, eggSecondary);
    }

    public static void registerModEntity(ResourceLocation registryName, Class<? extends Entity> entityClass, String entityName, int id, Object mod, int eggPrimary, int eggSecondary) {
        EntityTrackerData data = new EntityTrackerData(entityClass);
        EntityRegistry.registerModEntity(registryName, entityClass, entityName, id, mod, data.getTrackingRange(), data.getUpdateFrequency(), data.isSendVelocityUpdates(), eggPrimary, eggSecondary);
    }
}
