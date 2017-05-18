package eyeq.util.entity.ai;

import com.google.common.collect.Sets;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Set;

public class UEntityAIDumpHeldItem extends EntityAIBase {
    protected final EntityLiving entity;
    private final Set<Item> holdItem;

    public UEntityAIDumpHeldItem(EntityLiving entity, Set<Item> holdItem) {
        this.entity = entity;
        this.holdItem = holdItem;
    }

    public UEntityAIDumpHeldItem(EntityLiving entity, Item holdItem) {
        this(entity, Sets.newHashSet(holdItem));
    }

    @Override
    public boolean shouldExecute() {
        for(ItemStack itemStack : this.entity.getHeldEquipment()) {
            if(!itemStack.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean continueExecuting() {
        return false;
    }

    @Override
    public void updateTask() {
        if(this.entity.getEntityWorld().isRemote) {
            return;
        }
        for(ItemStack itemStack : this.entity.getHeldEquipment()) {
            if(!itemStack.isEmpty() && !holdItem.contains(itemStack.getItem())) {
                this.entity.entityDropItem(new ItemStack(itemStack.getItem(), 1, itemStack.getMetadata()), 0.0F);
                itemStack.shrink(1);
            }
        }
    }
}
