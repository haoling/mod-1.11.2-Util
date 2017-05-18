package eyeq.util.client.model.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BlockstateJsonFactory {
    public static JsonObject createBlockstateJson(JsonElement variants) {
        JsonObject root = new JsonObject();
        root.add("variants", variants);
        // multipart
        return root;
    }

    public static JsonObject createBlockstateJson(Map<String, JsonElement> variantsMap) {
        JsonObject variants = new JsonObject();
        variantsMap.forEach(variants::add);
        return createBlockstateJson(variants);
    }

    public static JsonObject createMapperBlockstateJson(Map<ModelResourceLocation, Object> models) {
        Map<String, JsonElement> map = new TreeMap<>();
        models.forEach((key, value) -> {
            String variant = key.getVariant();
            if(!map.containsKey(variant)) {
                JsonElement element = toJsonElement(value);
                if(element != null) {
                    map.put(variant, element);
                }
            }});
        return createBlockstateJson(map);
    }

    public static JsonObject createMapperBlockstateJson(IStateMapper mapper, Block block, Map<IBlockState, Object> models) {
        return createMapperBlockstateJson(mapper.putStateModelLocations(block), models);
    }

    public static JsonObject createMapperBlockstateJson(Map<IBlockState, ModelResourceLocation> resources, Map<IBlockState, Object> models) {
        Map<String, JsonElement> map = new TreeMap<>();
        resources.forEach((key, value) -> {
            String variant = value.getVariant();
            if(!map.containsKey(variant)) {
                JsonElement element = toJsonElement(models.get(key));
                if(element != null) {
                    map.put(variant, element);
                }
            }
        });
        return createBlockstateJson(map);
    }

    public static JsonObject createNormalBlockstateJson(String model) {
        return createNormalBlockstateJson(new BlockstateModel(model));
    }

    public static JsonObject createNormalBlockstateJson(BlockstateModel model) {
        Map<String, JsonElement> map = new HashMap<>();
        map.put("normal", model.toJson());
        return createBlockstateJson(map);
    }

    public static JsonObject createNormalBlockstateJson(List models) {
        Map<String, JsonElement> map = new HashMap<>();
        JsonArray array = new JsonArray();
        for(Object model : models) {
            JsonElement element = toJsonElement(model);
            if(element != null) {
                array.add(element);
            }
        }
        map.put("normal", array);
        return createBlockstateJson(map);
    }

    public static JsonObject createNumberBlockstateJson(String property, List models) {
        Map<String, JsonElement> map = new TreeMap<>();
        int i = 0;
        for(Object model : models) {
            JsonElement element = toJsonElement(model);
            if(element != null) {
                map.put(property + '=' + i, element);
            }
            i++;
        }
        return createBlockstateJson(map);
    }

    private static JsonElement toJsonElement(Object obj) {
        if(obj == null) {
            return null;
        }
        if(obj instanceof JsonElement) {
            return (JsonElement) obj;
        }
        if(obj instanceof String) {
            return new BlockstateModel((String) obj).toJson();
        }
        if(obj instanceof BlockstateModel) {
            return ((BlockstateModel) obj).toJson();
        }
        return null;
    }
}
