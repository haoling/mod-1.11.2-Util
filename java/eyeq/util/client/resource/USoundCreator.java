package eyeq.util.client.resource;

import com.google.gson.JsonElement;
import eyeq.util.client.resource.gson.SoundResource;
import eyeq.util.client.resource.gson.SoundResourceJsonFactory;
import eyeq.util.client.resource.gson.SoundResourceManager;
import eyeq.util.common.Utils;
import eyeq.util.file.FileUtils;

import java.io.File;
import java.util.List;

public class USoundCreator {
    public static void createSoundJson(File project, String modid, SoundResourceManager manager) {
        if(!Utils.isDevelopment()) {
            return;
        }
        List<SoundResource> sounds = manager.getSounds();
        JsonElement json = SoundResourceJsonFactory.createSoundJson(sounds);
        File dir = new File(project, FileUtils.ASSETS + modid);
        FileUtils.createJson(dir, "sounds", json);
    }
}
