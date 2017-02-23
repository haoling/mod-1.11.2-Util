package eyeq.util.client.resource.gson;

import com.google.common.collect.Lists;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

import java.util.ArrayList;
import java.util.List;

public class SoundResourceManager {
    private List<SoundResource> sounds = new ArrayList<>();

    public void register(SoundResource sound) {
        sounds.add(sound);
    }


    public void register(SoundEvent soundEvent, String category, List<String> filePath) {
        SoundResource sound = new SoundResource(soundEvent.getRegistryName().getResourcePath(), category, filePath);
        register(sound);
    }

    public void register(ResourceLocation resource, String category) {
        SoundResource sound = new SoundResource(resource.getResourcePath(), category, Lists.newArrayList(resource.toString()));
        register(sound);
    }

    public void register(SoundEvent soundEvent, String category) {
        ResourceLocation resource = soundEvent.getRegistryName();
        register(resource, category);
    }

    public void register(String modid, String name, String category) {
        ResourceLocation resource = new ResourceLocation(modid, name);
        register(resource, category);
    }

    public List<SoundResource> getSounds() {
        return this.sounds;
    }
}
