package eyeq.util.client.renderer.block.statemap;


import java.util.HashMap;
import java.util.Map;

public class LanguageResource {
    private final String language;
    private final Map<String, String> properties = new HashMap<>();

    public LanguageResource(String language) {
        this.language = language;
    }

    public void register(String key, String value) {
        properties.put(key, value);
    }

    public String getLanguage() {
        return this.language;
    }

    @Override
    public String toString() {
        final String[] str = {""};
        properties.forEach((key, value) -> str[0] += key + "=" + value + "\r\n");
        return str[0];
    }
}
