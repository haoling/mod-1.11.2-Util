package eyeq.util.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EntityLivingBaseUtils {
    public static void clearActivePotions(EntityLivingBase entity) {
        entity.clearActivePotions();
    }

    public static void clearActivePotions(EntityLivingBase entity, boolean isBad) {
        Collection<PotionEffect> potionEffects = entity.getActivePotionEffects();
        if(potionEffects.isEmpty()) {
            return;
        }
        List<Potion> removeList = new ArrayList<>();
        for(PotionEffect potionEffect : potionEffects) {
            Potion potion = potionEffect.getPotion();
            if(potion.isBadEffect() == isBad) {
                removeList.add(potion);
            }
        }
        for(Potion remove : removeList) {
            entity.removePotionEffect(remove);
        }
    }

    public static void clearActiveBadPotions(EntityLivingBase entity) {
        clearActivePotions(entity, true);
    }

    public static void clearActiveGoodPotions(EntityLivingBase entity) {
        clearActivePotions(entity, false);
    }
}
