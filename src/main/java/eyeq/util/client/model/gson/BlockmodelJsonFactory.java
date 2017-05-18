package eyeq.util.client.model.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class BlockmodelJsonFactory {
    public static JsonObject createBlockmodelJson(String parent, JsonElement textures) {
        JsonObject root = new JsonObject();

        root.addProperty("parent", parent);
        // ambientocclusion
        // display
        root.add("textures", textures);
        // elements
        return root;
    }

    public static JsonObject createBlockmodelJson(String parent, Map<String, String> texturesMap) {
        JsonObject textures = new JsonObject();
        texturesMap.forEach(textures::addProperty);
        return createBlockmodelJson(parent, textures);
    }

    public static JsonObject createOneBlockmodelJson(String parent, String property, String texture) {
        Map<String, String> map = new HashMap<>();
        map.put(property, texture);
        return createBlockmodelJson(parent, map);
    }

    public static JsonObject createCubeallBlockmodelJson(String texture) {
        return createOneBlockmodelJson("block/cube_all", "all", texture);
    }

    public static JsonObject createNormalBlockmodelJson(String parent, String texture) {
        return createOneBlockmodelJson(parent, "texture", texture);
    }

    public static JsonObject createBTSBlockmodelJson(String parent, String bottom, String top, String side) {
        Map<String, String> map = new HashMap<>();
        map.put("bottom", bottom);
        map.put("top", top);
        map.put("side", side);
        return createBlockmodelJson(parent, map);
    }

    public static JsonObject createDirectionBlockmodelJson(String parent, String particle, String down, String up, String north, String east, String south, String west) {
        Map<String, String> map = new HashMap<>();
        map.put("particle", particle);
        map.put("down", down);
        map.put("up", up);
        map.put("north", north);
        map.put("east", east);
        map.put("south", south);
        map.put("west", west);
        return createBlockmodelJson(parent, map);
    }

    public static JsonObject createCubeBlockmodelJson(String particle, String down, String up, String north, String east, String south, String west) {
        return createDirectionBlockmodelJson("cube/all", particle, down, up, north, east, south, west);
    }
}
