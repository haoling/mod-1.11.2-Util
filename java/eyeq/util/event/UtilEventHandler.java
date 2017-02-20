package eyeq.util.event;

import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class UtilEventHandler {
    @SubscribeEvent
    public void onPlace(BlockEvent.PlaceEvent event) {
        // UBlock.openGate(event.world, event.pos, event.state);
        // UEntity.summons(event.world, event.pos, event.state);
    }
}
