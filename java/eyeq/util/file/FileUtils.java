package eyeq.util.file;

import net.minecraft.client.Minecraft;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraftforge.common.config.Configuration;

import java.io.*;

public class FileUtils {
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

    public static Configuration getSaveConfiguration(String modid) {
        IntegratedServer server = Minecraft.getMinecraft().getIntegratedServer();
        if(server == null) {
            return null;
        }
        String dir = server.getFolderName();
        String name = modid + ".data";
        File file = new File(dir + "/" + name);
        return new Configuration(file);
    }
}
