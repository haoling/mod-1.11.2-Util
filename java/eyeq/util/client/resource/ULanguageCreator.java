package eyeq.util.client.resource;

import eyeq.util.client.resource.lang.LanguageResource;
import eyeq.util.client.resource.lang.LanguageResourceManager;
import eyeq.util.file.FileUtils;
import eyeq.util.common.Utils;

import java.io.File;

public class ULanguageCreator {
    public static void createLanguage(File project, String modid, LanguageResourceManager manager) {
        if(!Utils.isDevelopment()) {
            return;
        }
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
        File dir = new File(project, FileUtils.ASSETS + modid + "/lang/");
        FileUtils.write(dir, language + ".lang", text);
    }
}
