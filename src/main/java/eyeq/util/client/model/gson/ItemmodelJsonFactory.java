package eyeq.util.client.model.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ItemmodelJsonFactory {
    public enum ItemmodelParent {
        GENERATED {
            @Override
            public JsonObject create(String layer0) {
                return ItemmodelJsonFactory.createGeneratedItemmodelJson(layer0);
            }
        },
        HANDHELD {
            @Override
            public JsonObject create(String layer0) {
                return ItemmodelJsonFactory.createHandheldItemmodelJson(layer0);
            }
        },
        HANDHELD_ROD {
            @Override
            public JsonObject create(String layer0) {
                return ItemmodelJsonFactory.createHandheldrodItemmodelJson(layer0);
            }
        },
        ;

        public abstract JsonObject create(String layer0);
    }

    public static JsonObject createItemmodelJson(String parent, JsonElement textures) {
        JsonObject root = new JsonObject();

        root.addProperty("parent", parent);
        root.add("textures", textures);
        // elements
        // display
        // overrides
        return root;
    }

    public static JsonObject createItemmodelJson(String parent) {
        return createItemmodelJson(parent, null);
    }

    public static JsonObject createGeneratedItemmodelJson(String parent, String layer0, String layer1) {
        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", layer0);
        textures.addProperty("layer1", layer1);
        return createItemmodelJson(parent, textures);
    }

    public static JsonObject createGeneratedItemmodelJson(String layer0, String layer1) {
        return createGeneratedItemmodelJson("item/generated", layer0, layer1);
    }

    public static JsonObject createGeneratedItemmodelJson(String layer0) {
        return createGeneratedItemmodelJson(layer0, null);
    }

    public static JsonObject createHandheldItemmodelJson(String layer0) {
        return createGeneratedItemmodelJson("item/handheld", layer0, null);
    }

    public static JsonObject createHandheldrodItemmodelJson(String layer0) {
        return createGeneratedItemmodelJson("item/handheld_rod", layer0, null);
    }
}
