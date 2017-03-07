package eyeq.util.event;

import eyeq.util.client.renderer.EntityRendererUtils;
import eyeq.util.item.IItemHasReach;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class UtilEventHandler {
    @SubscribeEvent
    public void onPlayerAttackEntity(AttackEntityEvent event) {
        EntityPlayer player = event.getEntityPlayer();
        if(player.isCreative()) {
            return;
        }
        ItemStack itemStack = player.getHeldItemMainhand();
        Item item = itemStack.getItem();
        if(item instanceof IItemHasReach) {
            float reach = ((IItemHasReach) item).getReach(player.world, player, itemStack);
            float distance = (float) EntityRendererUtils.getDistance(player, event.getTarget(), 0, reach * 2);
            if(distance == -1 || distance >= reach) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;
        if(player.isCreative()) {
            return;
        }
        World world = player.getEntityWorld();
        if(world.isRemote) {
            return;
        }
        if(event.phase == TickEvent.Phase.END) {
            if(player.swingProgressInt == 1) {
                ItemStack itemStack = player.getHeldItemMainhand();
                Item item = itemStack.getItem();
                if(item instanceof IItemHasReach) {
                    float reach = ((IItemHasReach) item).getReach(world, player, itemStack);
                    Entity entity = EntityRendererUtils.getMouseOver(0, reach);
                    if(entity != null && entity != player && entity.hurtResistantTime == 0) {
                        FMLClientHandler.instance().getClient().playerController.attackEntity(player, entity);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlace(BlockEvent.PlaceEvent event) {
        // UBlock.openGate(event.world, event.pos, event.state);
        // UEntity.summons(event.world, event.pos, event.state);
    }
}
