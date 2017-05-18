package eyeq.util.common.registry;

import net.minecraft.util.SoundEvent;

public class USoundEventRegistry {
    public static void registry(SoundEvent soundEvent) {
        int size = SoundEvent.REGISTRY.getKeys().size();
        SoundEvent.REGISTRY.register(size, soundEvent.getSoundName(), soundEvent);
    }
}
