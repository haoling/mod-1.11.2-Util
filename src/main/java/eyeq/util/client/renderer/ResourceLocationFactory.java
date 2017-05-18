package eyeq.util.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ResourceLocationFactory {
    public static final ResourceLocationFactory mc = new ResourceLocationFactory("minecraft");

    protected final String modId;

    public ResourceLocationFactory(String modid) {
        this.modId = modid;
    }

    public ResourceLocation createResourceLocation(String name) {
        return new ResourceLocation(modId, name);
    }

    public ModelResourceLocation createModelResourceLocation(String name) {
        return createModelResourceLocation(createResourceLocation(name));
    }

    public ModelResourceLocation createModelResourceLocation(String name, String variant) {
        return createModelResourceLocation(createResourceLocation(name), variant);
    }

    public static ModelResourceLocation createModelResourceLocation(Block block) {
        return createModelResourceLocation(block.getRegistryName());
    }

    public static ModelResourceLocation createModelResourceLocation(Item item) {
        return createModelResourceLocation(item.getRegistryName());
    }

    public static ModelResourceLocation createModelResourceLocation(ResourceLocation resource) {
        return createModelResourceLocation(resource, "inventory");
    }

    public static ModelResourceLocation createModelResourceLocation(Block block, String variant) {
        return createModelResourceLocation(block.getRegistryName(), variant);
    }

    public static ModelResourceLocation createModelResourceLocation(Item item, String variant) {
        return createModelResourceLocation(item.getRegistryName(), variant);
    }

    public static ModelResourceLocation createModelResourceLocation(ResourceLocation resource, String variant) {
        return new ModelResourceLocation(resource, variant);
    }

    public String toBlockFilePath(String fileName) {
        return toBlockFilePath(modId, fileName);
    }

    public String toItemFilePath(String fileName) {
        return toItemFilePath(modId, fileName);
    }

    public String toBlockTexturePath(String textureName) {
        return toBlockTexturePath(modId, textureName);
    }

    public String toItemTexturePath(String textureName) {
        return toItemTexturePath(modId, textureName);
    }

    public static String toBlockFilePath(String modid, String fileName) {
        return modid + ":block/" + fileName;
    }

    public static String toItemFilePath(String modid, String fileName) {
        return modid + ":item/" + fileName;
    }

    public static String toBlockTexturePath(String modid, String textureName) {
        return modid + ":blocks/" + textureName;
    }

    public static String toItemTexturePath(String modid, String textureName) {
        return modid + ":items/" + textureName;
    }

    public static String toBlockFilePath(ResourceLocation resource) {
        return toBlockFilePath(resource.getResourceDomain(), resource.getResourcePath());
    }

    public static String toItemFilePath(ResourceLocation resource) {
        return toItemFilePath(resource.getResourceDomain(), resource.getResourcePath());
    }

    public static String toBlockTexturePath(ResourceLocation resource) {
        return toBlockTexturePath(resource.getResourceDomain(), resource.getResourcePath());
    }

    public static String toItemTexturePath(ResourceLocation resource) {
        return toItemTexturePath(resource.getResourceDomain(), resource.getResourcePath());
    }
}
