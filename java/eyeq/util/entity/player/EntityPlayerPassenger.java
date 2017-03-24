package eyeq.util.entity.player;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class EntityPlayerPassenger {
    private final Map<EntityPlayerMP, Entity> passengerMP = new HashMap<>();
    private final Map<EntityPlayerSP, Entity> passengerSP = new HashMap<>();

    public Map<? extends EntityPlayer, Entity> getPassenger(World world) {
        if(world.isRemote) {
            return passengerSP;
        }
        return passengerMP;
    }

    public boolean containsKey(EntityPlayer player) {
        return getPassenger(player.world).containsKey(player);
    }

    public Entity remove(EntityPlayer player) {
        return getPassenger(player.world).remove(player);
    }

    public Entity get(EntityPlayer player) {
        return getPassenger(player.world).get(player);
    }

    public Entity put(EntityPlayer player, Entity passenger) {
        if(player instanceof EntityPlayerMP) {
            return passengerMP.put(((EntityPlayerMP) player), passenger);
        }
        if(player instanceof EntityPlayerSP) {
            return passengerSP.put(((EntityPlayerSP) player), passenger);
        }
        return null;
    }
}
