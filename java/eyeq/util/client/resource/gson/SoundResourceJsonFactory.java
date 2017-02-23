package eyeq.util.client.resource.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SoundResourceJsonFactory {
    public static JsonObject createSoundJson(Map<String, JsonElement> sounds) {
        JsonObject root = new JsonObject();
        sounds.forEach(root::add);
        return root;
    }

    public static JsonObject createSoundJson(List<SoundResource> sounds) {
        Map<String, JsonElement> map = new TreeMap<>();
        for(SoundResource sound : sounds) {
            map.put(sound.getName(), sound.toJson());
        }
        return createSoundJson(map);
    }

    public static JsonObject createSoundJson(SoundResource sound) {
        Map<String, JsonElement> map = new HashMap<>();
        map.put(sound.getName(), sound.toJson());
        return createSoundJson(map);
    }
}
