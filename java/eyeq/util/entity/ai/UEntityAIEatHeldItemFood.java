package eyeq.util.entity.ai;

import java.util.Set;

import com.google.common.collect.Sets;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class UEntityAIEatHeldItemFood extends EntityAIBase {
    protected final EntityLiving entity;
    private final Set<ItemFood> eatItem;

    public UEntityAIEatHeldItemFood(EntityLiving entity, Set<ItemFood> eatItem) {
        this.entity = entity;
        this.eatItem = eatItem;
        this.setMutexBits(3);
    }

    public UEntityAIEatHeldItemFood(EntityLiving entity, ItemFood eatItem) {
        this(entity, Sets.newHashSet(eatItem));
    }

    @Override
    public boolean shouldExecute() {
        for(ItemStack itemStack : this.entity.getHeldEquipment()) {
            if(eatItem.contains(itemStack.getItem())) {
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
        if(this.entity.getHealth() >= this.entity.getMaxHealth()) {
            return;
        }
        ItemStack eatItemStack = null;
        for(ItemStack itemStack : this.entity.getHeldEquipment()) {
            if(eatItem.contains(itemStack.getItem())) {
                eatItemStack = itemStack;
                break;
            }
        }
        if(eatItemStack == null) {
            return;
        }
        this.entity.heal(((ItemFood) eatItemStack.getItem()).getHealAmount(eatItemStack));
        eatItemStack.shrink(1);
    }
}
