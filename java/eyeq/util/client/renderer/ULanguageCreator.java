package eyeq.util.client.renderer;

import eyeq.util.client.renderer.block.statemap.LanguageResource;
import eyeq.util.file.FileUtils;
import eyeq.util.common.Utils;

import java.io.File;

public class ULanguageCreator {
    public static void createLanguage(File project, String modid, ULanguageResourceManager manager) {
        for(LanguageResource resource : manager.getLanguageResources()) {
            createLanguage(project, modid, resource);
        }
    }

    public static void createLanguage(File project, String modid, LanguageResource resource) {
        if(!Utils.isDevelopment()) {
            return;
        }
        String language = resource.getLanguage();
        String text = resource.toString();
        File dir = new File(project, "src/main/resources/assets/" + modid + "/lang/");
        createLanguage(dir, language, text);
    }

    private static void createLanguage(File dir, String name, String lang) {
        FileUtils.write(dir, name + ".lang", lang);
    }
}
