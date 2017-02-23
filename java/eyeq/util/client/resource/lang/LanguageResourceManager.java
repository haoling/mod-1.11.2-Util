package eyeq.util.client.resource.lang;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class LanguageResourceManager {
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

    public void register(String language, Class<? extends Entity> entityClass, String value) {
        String name = null;
        for(EntityEntry entity : ForgeRegistries.ENTITIES) {
            if(entity.getEntityClass().equals(entityClass)) {
                name = entity.getName();
                break;
            }
        }
        if(name != null) {
            name = "entity." + name + ".name";
            register(language, name, value);
        }
    }

    public Collection<LanguageResource> getLanguageResources() {
        return languageMap.values();
    }
}
