package eyeq.util.item;

import com.google.common.collect.Multimap;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import java.util.*;

public class UItemSword extends ItemSword implements IItemEnchanted, IItemUsePotion {
    public static final IAttribute ATTACK_REACH = new RangedAttribute(null, "generic.attackReach", 3.0, 0.0, 1024.0);

    public static final UUID ATTACK_REACH_MODIFIER = UUID.fromString("F76D48B9-DC9D-4B8A-B8E4-53C0ACF04DB1");

    protected List<EnchantmentData> enchants = new ArrayList<>();

    private IItemUsePotion.ClearPotionType clear = IItemUsePotion.ClearPotionType.NONE;
    private Map<PotionEffect, Float> potions = new HashMap<>();
    private int useItemDamage = 0;

    private int fireSec;
    private float fireProb;

    protected float attackSpeed = -2.4F;

    public UItemSword(ToolMaterial material) {
        super(material);
    }

    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected) {
        super.onUpdate(itemStack, world, entity, itemSlot, isSelected);
        IItemEnchanted.updateEnchantment(itemStack, enchants);
    }

    @Override
    public boolean hitEntity(ItemStack itemStack, EntityLivingBase target, EntityLivingBase attacker) {
        if(attacker.getEntityWorld().rand.nextFloat() < fireProb) {
            if(fireSec > 0) {
                target.setFire(fireSec);
            } else if(fireSec < 0) {
                target.extinguish();
            }
        }

        IItemUsePotion.clearPotion(target, clear);
        if(IItemUsePotion.usePotion(target, potions)) {
            if(useItemDamage != 0) {
                itemStack.damageItem(useItemDamage, attacker);
            }
        }
        return super.hitEntity(itemStack, target, attacker);
    }

    @Override
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);
        if(equipmentSlot == EntityEquipmentSlot.MAINHAND) {
            multimap.removeAll(SharedMonsterAttributes.ATTACK_SPEED.getName());
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", attackSpeed, 0));
            if(this instanceof IItemHasReach) {
                float reach = ((IItemHasReach) this).getReach();
                if(reach != 3.0F) {
                    multimap.put(ATTACK_REACH.getName(), new AttributeModifier(ATTACK_REACH_MODIFIER, "Weapon modifier", reach - 3.0F, 0));
                }
            }
        }
        return multimap;
    }

    @Override
    public UItemSword setEnchantmentData(EnchantmentData enchant) {
        enchants.clear();
        return addEnchantmentData(enchant);
    }

    @Override
    public UItemSword addEnchantmentData(EnchantmentData enchant) {
        enchants.add(enchant);
        return this;
    }

    @Override
    public boolean hasEffect(ItemStack item) {
        if(super.hasEffect(item)) {
            return true;
        }
        return !enchants.isEmpty();
    }

    @Override
    public UItemSword setClearPotionType(ClearPotionType type) {
        clear = type;
        return this;
    }

    @Override
    public UItemSword setPotionEffect(PotionEffect potion, float probability) {
        potions.clear();
        return addPotionEffect(potion, probability);
    }

    @Override
    public UItemSword addPotionEffect(PotionEffect potion, float probability) {
        potions.put(potion, probability);
        return this;
    }

    @Override
    public UItemSword setUseItemDamage(int damage) {
        this.useItemDamage = damage;
        return this;
    }

    // if(second < 0) extinguish
    public UItemSword setAttackFire(int second, float probability) {
        fireSec = second;
        fireProb = probability;
        return this;
    }

    public UItemSword setAttackSpeed(float attackSpeed) {
        this.attackSpeed = attackSpeed;
        return this;
    }
}
