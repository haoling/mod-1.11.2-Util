package eyeq.util.item;

import eyeq.util.entity.EntityLivingBaseUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;

import java.util.Map;
import java.util.Random;

public interface IItemUsePotion {
    enum ClearPotionType {
        NONE,
        CLEAR_ALL,
        CLEAR_BAD,
        CLEAR_GOOD,
        ;
    }

    IItemUsePotion setClearPotionType(ClearPotionType type);

    IItemUsePotion setPotionEffect(PotionEffect potion, float probability);

    IItemUsePotion addPotionEffect(PotionEffect potion, float probability);

    IItemUsePotion setUseItemDamage(int damage);

    static void clearPotion(EntityLivingBase entity, ClearPotionType clear) {
        switch(clear) {
        case NONE:
            break;
        case CLEAR_ALL:
            EntityLivingBaseUtils.clearActivePotions(entity);
            break;
        case CLEAR_BAD:
            EntityLivingBaseUtils.clearActiveBadPotions(entity);
            break;
        case CLEAR_GOOD:
            EntityLivingBaseUtils.clearActiveGoodPotions(entity);
            break;
        }
    }

    static boolean usePotion(EntityLivingBase entity, Map<PotionEffect, Float> potions) {
        Random rand = entity.world.rand;
        boolean isUsed = false;
        for(PotionEffect potion : potions.keySet()) {
            float probability = potions.get(potion);
            if(potion != null && rand.nextFloat() < probability) {
                isUsed = true;
                entity.addPotionEffect(new PotionEffect(potion));
            }
        }
        return isUsed;
    }
}
