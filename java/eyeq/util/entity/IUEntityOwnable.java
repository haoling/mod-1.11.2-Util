package eyeq.util.entity;

import net.minecraft.entity.IEntityOwnable;

import java.util.UUID;

public interface IUEntityOwnable extends IEntityOwnable {
    void setOwnerId(UUID owner);
}
