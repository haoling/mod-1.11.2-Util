package eyeq.util.client.renderer;

import eyeq.util.client.renderer.block.statemap.LanguageResource;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ULanguageResourceManager {
    public static final String EN_US = "en_US";
    public static final String JA_JP = "ja_JP";

    private final Map<String, LanguageResource> languageMap = new HashMap<>();

    public void register(String language, String key, String value) {
        LanguageResource languageResource;
        if(languageMap.containsKey(language)) {
            languageResource = languageMap.get(language);
        } else {
            languageResource = new LanguageResource(language);
            languageMap.put(language, languageResource);
        }
        languageResource.register(key, value);
    }

    public void register(String language, CreativeTabs tab, String value) {
        String name = tab.getTranslatedTabLabel();
        register(language, name, value);
    }

    public void register(String language, Block block, String value) {
        String name = block.getUnlocalizedName() + ".name";
        register(language, name, value);
    }

    public void register(String language, Item item, String value) {
        String name = item.getUnlocalizedName() + ".name";
        register(language, name, value);
    }

    public Collection<LanguageResource> getLanguageResources() {
        return languageMap.values();
    }
}
