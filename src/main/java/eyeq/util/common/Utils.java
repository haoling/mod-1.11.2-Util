package eyeq.util.common;

import net.minecraftforge.fml.common.eventhandler.EventBus;

import java.io.File;

public class Utils {
    public static final EventBus EVENT_BUS = new EventBus();

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
