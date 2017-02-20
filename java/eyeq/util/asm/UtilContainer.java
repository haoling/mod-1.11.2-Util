package eyeq.util.asm;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import eyeq.util.event.UtilEventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.Collections;

public class UtilContainer extends DummyModContainer {
    public static final String MOD_ID = "eyeq_util";

    public UtilContainer() {
        super(new ModMetadata());

        ModMetadata meta = getMetadata();
        meta.modId = MOD_ID;
        meta.name = "Util";
        meta.description = "前提";
        meta.version = "1.1";
        meta.authorList = Collections.singletonList("eyeq");
        meta.credits = "";
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller) {
        bus.register(this);
        return true;
    }

    @Subscribe
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new UtilEventHandler());
    }
}
