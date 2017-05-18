package eyeq.util.item;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import java.util.*;

public class UItemTool extends ItemTool implements IItemEnchanted, IItemUsePotion {
    protected List<EnchantmentData> enchants = new ArrayList<>();

    private IItemUsePotion.ClearPotionType clear = IItemUsePotion.ClearPotionType.NONE;
    private Map<PotionEffect, Float> potions = new HashMap<>();
    private int useItemDamage = 1;

    public UItemTool(float damage, float speed, ToolMaterial material, Set<Block> effectiveBlocks) {
        super(damage, speed, material, effectiveBlocks);
    }

    public UItemTool(ToolMaterial material, Set<Block> effectiveBlocks) {
        super(material, effectiveBlocks);
    }

    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected) {
        super.onUpdate(itemStack, world, entity, itemSlot, isSelected);
        IItemEnchanted.updateEnchantment(itemStack, enchants);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if(world.isRemote) {
            return super.onItemRightClick(world, player, hand);
        }
        ItemStack itemStack = player.getHeldItem(hand);
        IItemUsePotion.clearPotion(player, clear);
        if(IItemUsePotion.usePotion(player, potions)) {
            if(useItemDamage != 0) {
                itemStack.damageItem(useItemDamage, player);
            }
            player.swingArm(hand);
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, itemStack);
    }

    @Override
    public UItemTool setEnchantmentData(EnchantmentData enchant) {
        enchants.clear();
        return addEnchantmentData(enchant);
    }

    @Override
    public UItemTool addEnchantmentData(EnchantmentData enchant) {
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
    public UItemTool setClearPotionType(ClearPotionType type) {
        clear = type;
        return this;
    }

    @Override
    public UItemTool setPotionEffect(PotionEffect potion, float probability) {
        potions.clear();
        return addPotionEffect(potion, probability);
    }

    @Override
    public UItemTool addPotionEffect(PotionEffect potion, float probability) {
        potions.put(potion, probability);
        return this;
    }

    @Override
    public UItemTool setUseItemDamage(int damage) {
        this.useItemDamage = damage;
        return this;
    }
}
