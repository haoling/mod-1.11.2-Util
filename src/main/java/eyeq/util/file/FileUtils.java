package eyeq.util.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import net.minecraft.client.Minecraft;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraftforge.common.config.Configuration;

import java.io.*;

public class FileUtils {
    public static final String ASSETS = "src/main/resources/assets/";

    private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

    public static void write(File dir, String fileName, String text) {
        if(!dir.exists()) {
            if(!dir.mkdirs()) {
                return;
            }
        }

        File file = new File(dir, fileName);
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            writer.write(text);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void createJson(File dir, String name, JsonElement json) {
        createJson(dir, name, GSON.toJson(json));
    }

    public static void createJson(File dir, String name, String json) {
        write(dir, name + ".json", json);
    }

    public static Configuration getSaveConfiguration(String modid) {
        IntegratedServer server = Minecraft.getMinecraft().getIntegratedServer();
        if(server == null) {
            return null;
        }
        String dir = "saves/" + server.getFolderName() + "/eyeq/";
        String name = modid + ".data";
        File file = new File(dir + name);
        return new Configuration(file);
    }
}
