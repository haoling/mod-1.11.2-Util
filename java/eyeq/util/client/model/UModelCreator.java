package eyeq.util.client.model;

import com.google.gson.JsonElement;
import eyeq.util.client.model.gson.BlockmodelJsonFactory;
import eyeq.util.client.model.gson.BlockstateJsonFactory;
import eyeq.util.client.model.gson.ItemmodelJsonFactory;
import eyeq.util.client.renderer.ResourceLocationFactory;
import eyeq.util.file.FileUtils;
import eyeq.util.common.Utils;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UModelCreator {
    public static void createBlockstateJson(File project, Block block) {
        createBlockstateJson(project, block.getRegistryName());
    }
    public static void createBlockstateJson(File project, Block block, String texture) {
        createBlockstateJson(project, block.getRegistryName(), texture);
    }

    public static void createBlockstateJson(File project, Block block, JsonElement blockmodel) {
        createBlockstateJson(project, block.getRegistryName(), blockmodel);
    }

    public static void createBlockOreJson(File project, Block block) {
        createBlockOreJson(project, block.getRegistryName());
    }

    public static void createItemJson(File project, Item item, ItemmodelJsonFactory.ItemmodelParent parent) {
        createItemJson(project, item.getRegistryName(), parent);
    }



    public static void createBlockstateJson(File project, ResourceLocation resource) {
        if(!Utils.isDevelopment()) {
            return;
        }
        createBlockJson(project, resource);
        createBlockstateJson(resource, project, BlockstateJsonFactory.createNormalBlockstateJson(resource.toString()));
    }

    public static void createBlockstateJson(File project, ResourceLocation resource, String texture) {
        if(!Utils.isDevelopment()) {
            return;
        }
        createBlockJson(project, resource, texture);
        createBlockstateJson(resource, project, BlockstateJsonFactory.createNormalBlockstateJson(resource.toString()));
    }

    public static void createBlockstateJson(File project, ResourceLocation resource, JsonElement blockmodel) {
        if(!Utils.isDevelopment()) {
            return;
        }
        createBlockJson(project, resource, blockmodel);
        createBlockstateJson(resource, project, BlockstateJsonFactory.createNormalBlockstateJson(resource.toString()));
    }

    public static void createBlockstateJson(File project, ResourceLocation resource, List<ResourceLocation> meta) {
        if(!Utils.isDevelopment()) {
            return;
        }
        List<String> list = new ArrayList<>();
        for(ResourceLocation resourceLocation : meta) {
            createBlockJson(project, resourceLocation);
            list.add(resourceLocation.toString());
        }
        createBlockstateJson(resource, project, BlockstateJsonFactory.createNormalBlockstateJson(list));
    }

    public static void createBlockJson(File project, ResourceLocation resource) {
        if(!Utils.isDevelopment()) {
            return;
        }
        createBlockJson(project, resource, ResourceLocationFactory.toBlockTexturePath(resource));
    }

    public static void createBlockJson(File project, ResourceLocation resource, String texture) {
        if(!Utils.isDevelopment()) {
            return;
        }
        createBlockJson(project, resource, BlockmodelJsonFactory.createCubeallBlockmodelJson(texture));
    }

    public static void createBlockJson(File project, ResourceLocation resource, JsonElement blockmodel) {
        if(!Utils.isDevelopment()) {
            return;
        }
        createBlockmodelJson(project, resource, blockmodel);
        createItemmodelJson(project, resource, ItemmodelJsonFactory.createItemmodelJson(ResourceLocationFactory.toBlockFilePath(resource)));
    }

    public static void createBlockOreJson(File project, ResourceLocation resource) {
        if(!Utils.isDevelopment()) {
            return;
        }
        String texture = ResourceLocationFactory.toBlockTexturePath(resource);
        createBlockmodelJson(project, resource, BlockmodelJsonFactory.createOneBlockmodelJson("eyeq_util:block/ore", "ore", texture));
        createItemmodelJson(project, resource, ItemmodelJsonFactory.createItemmodelJson(ResourceLocationFactory.toBlockFilePath(resource)));
    }

    public static void createItemJson(File project, ResourceLocation resource, ItemmodelJsonFactory.ItemmodelParent parent) {
        if(!Utils.isDevelopment()) {
            return;
        }
        createItemJson(project, resource, parent, resource);
    }

    public static void createItemJson(File project, ResourceLocation resource, ItemmodelJsonFactory.ItemmodelParent parent, ResourceLocation texture) {
        if(!Utils.isDevelopment()) {
            return;
        }
        createItemmodelJson(project, resource, parent.create(ResourceLocationFactory.toItemTexturePath(texture)));
    }



    public static void createBlockstateJson(ResourceLocation resource, File project, JsonElement blockstates) {
        if(!Utils.isDevelopment()) {
            return;
        }
        File dir = new File(project, FileUtils.ASSETS + resource.getResourceDomain() + "/blockstates/");
        FileUtils.createJson(dir, resource.getResourcePath(), blockstates);
    }

    public static void createBlockmodelJson(File project, ResourceLocation resource, JsonElement blockmodel) {
        if(!Utils.isDevelopment()) {
            return;
        }
        File dir = new File(project, FileUtils.ASSETS + resource.getResourceDomain() + "/models/block/");
        FileUtils.createJson(dir, resource.getResourcePath(), blockmodel);
    }

    public static void createItemmodelJson(File project, ResourceLocation resource, JsonElement itemmodel) {
        if(!Utils.isDevelopment()) {
            return;
        }
        File dir = new File(project, FileUtils.ASSETS + resource.getResourceDomain() + "/models/item/");
        FileUtils.createJson(dir, resource.getResourcePath(), itemmodel);
    }
}
