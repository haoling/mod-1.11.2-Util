package eyeq.util.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class UItem extends Item implements IItemUsePotion {
    private IItemUsePotion.ClearPotionType clear = IItemUsePotion.ClearPotionType.NONE;
    private Map<PotionEffect, Float> potions = new HashMap<>();
    private int useItemDamage = 1;

    public UItem(boolean isTool) {
        super();
        if(isTool) {
            this.setFull3D();
            this.setMaxStackSize(1);
            this.setCreativeTab(CreativeTabs.TOOLS);
        }
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
    public UItem setClearPotionType(ClearPotionType type) {
        clear = type;
        return this;
    }

    @Override
    public UItem setPotionEffect(PotionEffect potion, float probability) {
        potions.clear();
        return addPotionEffect(potion, probability);
    }

    @Override
    public UItem addPotionEffect(PotionEffect potion, float probability) {
        potions.put(potion, probability);
        return this;
    }

    @Override
    public UItem setUseItemDamage(int damage) {
        this.useItemDamage = damage;
        return this;
    }
}
