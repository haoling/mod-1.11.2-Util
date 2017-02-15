package eyeq.util.util;

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
}
