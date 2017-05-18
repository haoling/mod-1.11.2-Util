package eyeq.util.client.model.gson;

import com.google.gson.JsonObject;

public class BlockstateModel {
    private final String model;
    private final int x;
    private final int y;
    private final boolean isUvlock;
    private final int weight;

    public BlockstateModel(String model, int x, int y, boolean isUvlock, int weight) {
        this.model = model;
        this.x = x;
        this.y = y;
        this.isUvlock = isUvlock;
        this.weight = weight;
    }

    public BlockstateModel(String model, int x, int y, boolean isUvlock) {
        this(model, x, y, isUvlock, 1);
    }

    public BlockstateModel(String model, boolean isUvlock) {
        this(model, 0, 0, isUvlock);
    }

    public BlockstateModel(String model, int x, int y) {
        this(model, x, y, false);
    }

    public BlockstateModel(String model) {
        this(model, 0, 0);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("model", model);
        if(x != 0) {
            json.addProperty("x", x);
        }
        if(y != 0) {
            json.addProperty("y", y);
        }
        if(isUvlock) {
            json.addProperty("uvlock", isUvlock);
        }
        if(weight != 1) {
            json.addProperty("weight", weight);
        }
        return json;
    }
}
