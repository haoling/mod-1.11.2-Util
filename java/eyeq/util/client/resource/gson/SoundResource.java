package eyeq.util.client.resource.gson;

import com.google.gson.*;

import java.util.List;

public class SoundResource {
    private final String name;
    private final String category;
    private final List<String> sounds;
    private final String subtitle;

    public SoundResource(String name, String category, List<String> sounds, String subtitle) {
        this.name = name;
        this.category = category;
        this.sounds = sounds;
        this.subtitle = subtitle;
    }

    public SoundResource(String name, String category, List<String> sounds) {
        this(name, category, sounds, null);
    }

    public String getName() {
        return this.name;
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("category", category);
        JsonArray array = new JsonArray();
        for(String sound : sounds) {
            array.add(new JsonPrimitive(sound));
        }
        json.add("sounds", array);
        if(subtitle != null) {
            json.addProperty("subtitle", subtitle);
        }
        return json;
    }
}
