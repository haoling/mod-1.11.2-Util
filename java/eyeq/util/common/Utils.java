package eyeq.util.common;

import java.io.File;

public class Utils {
    private static final boolean isDepelopment;

    static {
        File file = new File("development");
        // System.out.println(file.getAbsolutePath());
        isDepelopment = file.exists();
    }

    public static boolean isDevelopment() {
        return isDepelopment;
    }
}
