package eyeq.util.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class UItemFood extends ItemFood implements IItemUsePotion {
    private IItemUsePotion.ClearPotionType clear = IItemUsePotion.ClearPotionType.NONE;
    private Map<PotionEffect, Float> potions = new HashMap<>();
    private int useItemDamage = 1;

    private int fireSec;
    private float fireProb;

    private ItemStack restItem = ItemStack.EMPTY;
    private int maxItemUseDuration = 32;

    public UItemFood(int amount, boolean isWolfFood) {
        super(amount, isWolfFood);
    }

    public UItemFood(int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack itemStack, World world, EntityLivingBase entity) {
        itemStack = super.onItemUseFinish(itemStack, world, entity);
        ItemStack containerItem = getRestItem(itemStack);
        if(containerItem.isEmpty()) {
            return itemStack;
        }
        if(itemStack.getCount() < 1) {
            return containerItem;
        }
        if(entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            if(!player.inventory.addItemStackToInventory(containerItem)) {
                player.dropItem(containerItem, false);
            }
        }
        return itemStack;
    }

    @Override
    protected void onFoodEaten(ItemStack itemStack, World world, EntityPlayer player) {
        if(world.isRemote) {
            return;
        }
        if(world.rand.nextFloat() < fireProb) {
            if(fireSec > 0) {
                player.setFire(fireSec);
            } else if(fireSec < 0) {
                player.extinguish();
            }
        }
        IItemUsePotion.clearPotion(player, clear);
        IItemUsePotion.usePotion(player, potions);
    }

    @Override
    public UItemFood setClearPotionType(ClearPotionType type) {
        clear = type;
        return this;
    }

    @Override
    public UItemFood setUseItemDamage(int damage) {
        this.useItemDamage = damage;
        return this;
    }

    @Override
    public UItemFood setPotionEffect(PotionEffect potion, float probability) {
        potions.clear();
        return addPotionEffect(potion, probability);
    }

    @Override
    public UItemFood addPotionEffect(PotionEffect potion, float probability) {
        potions.put(potion, probability);
        return this;
    }

    public UItemFood setFire(int second, float probability) {
        fireSec = second;
        fireProb = probability;
        return this;
    }

    public UItemFood setRestItem(ItemStack itemStack) {
        this.restItem = itemStack;
        return this;
    }

    public ItemStack getRestItem(ItemStack itemStack) {
        return restItem.copy();
    }

    public UItemFood setMaxItemUseDuration(int maxItemUseDuration) {
        this.maxItemUseDuration = maxItemUseDuration;
        return this;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return maxItemUseDuration;
    }
}
