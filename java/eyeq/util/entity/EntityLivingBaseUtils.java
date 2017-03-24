package eyeq.util.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EntityLivingBaseUtils {
    public static void clearActivePotions(EntityLivingBase entity) {
        entity.clearActivePotions();
    }

    public static void clearActivePotions(EntityLivingBase entity, boolean isBad) {
        Collection<PotionEffect> potionEffects = entity.getActivePotionEffects();
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

    public static boolean canDropLoot(EntityLivingBase entity) throws InvocationTargetException, IllegalAccessException {
        Method method = ReflectionHelper.findMethod(EntityLivingBase.class, entity, new String[]{"canDropLoot", "func_146066_aG"});
        return (boolean) method.invoke(entity);
    }

    public static void dropLoot(EntityLivingBase entity, boolean wasRecentlyHit, int lootingModifier, DamageSource source) throws InvocationTargetException, IllegalAccessException {
        Method method = ReflectionHelper.findMethod(EntityLivingBase.class, entity, new String[]{"dropLoot", "func_184610_a"}, Boolean.TYPE, Integer.TYPE, DamageSource.class);
        method.invoke(entity, wasRecentlyHit, lootingModifier, source);
    }
}
