package eyeq.util.client.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import eyeq.util.client.model.gson.BlockmodelJsonFactory;
import eyeq.util.client.model.gson.BlockstateJsonFactory;
import eyeq.util.client.model.gson.ItemmodelJsonFactory;
import eyeq.util.client.renderer.ResourceLocationFactory;
import eyeq.util.util.FileUtils;
import eyeq.util.util.Utils;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UModelCreator {
    private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

    public static void createBlockstateJson(File project, ResourceLocation resource) {
        createBlockJson(project, resource);
        createBlockstateJson(resource, project, BlockstateJsonFactory.createNormalBlockstateJson(resource.getResourcePath()));
    }

    public static void createBlockstateJson(File project, ResourceLocation resource, List<ResourceLocation> meta) {
        List<String> list = new ArrayList<>();
        for(ResourceLocation resourceLocation : meta) {
            createBlockJson(project, resourceLocation);
            list.add(resourceLocation.getResourcePath());
        }
        createBlockstateJson(resource, project, BlockstateJsonFactory.createNormalBlockstateJson(list));
    }

    public static void createBlockJson(File project, ResourceLocation resource) {
        createBlockmodelJson(project, resource, BlockmodelJsonFactory.createCubeallBlockmodelJson(ResourceLocationFactory.toBlockTexturePath(resource)));
        createItemmodelJson(project, resource, ItemmodelJsonFactory.createItemmodelJson(ResourceLocationFactory.toBlockFilePath(resource)));
    }

    public static void createItemJson(File project, Item item, ItemmodelJsonFactory.ItemmodelParent parent) {
        createItemJson(project, item.getRegistryName(), parent);
    }

    public static void createItemJson(File project, ResourceLocation resource, ItemmodelJsonFactory.ItemmodelParent parent) {
        createItemmodelJson(project, resource, parent.create(ResourceLocationFactory.toItemTexturePath(resource)));
    }

    public static void createBlockstateJson(ResourceLocation resource, File project, JsonElement blockstates) {
        if(!Utils.isDevelopment()) {
            return;
        }
        File dir = new File(project, "src/main/resources/assets/" + resource.getResourceDomain() + "/blockstates/");
        createJson(dir, resource.getResourcePath(), blockstates);
    }

    public static void createBlockmodelJson(File project, ResourceLocation resource, JsonElement blockmodel) {
        if(!Utils.isDevelopment()) {
            return;
        }
        File dir = new File(project, "src/main/resources/assets/" + resource.getResourceDomain() + "/models/block/");
        createJson(dir, resource.getResourcePath(), blockmodel);
    }

    public static void createItemmodelJson(File project, ResourceLocation resource, JsonElement itemmodel) {
        if(!Utils.isDevelopment()) {
            return;
        }
        File dir = new File(project, "src/main/resources/assets/" + resource.getResourceDomain() + "/models/item/");
        createJson(dir, resource.getResourcePath(), itemmodel);
    }

    public static void createJson(File dir, String name, JsonElement json) {
        createJson(dir, name, GSON.toJson(json));
    }

    public static void createJson(File dir, String name, String json) {
        FileUtils.write(dir, name + ".json", json);
    }
}
