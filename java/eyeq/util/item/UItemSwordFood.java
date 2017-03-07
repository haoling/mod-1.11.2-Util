package eyeq.util.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class UItemSwordFood extends UItemSword {
    private IItemUsePotion.ClearPotionType clear = IItemUsePotion.ClearPotionType.NONE;
    private Map<PotionEffect, Float> potions = new HashMap<>();

    private int fireSec;
    private float fireProb;

    private final int healAmount;
    private final float saturationModifier;
    private boolean alwaysEdible;

    private ItemStack restItem = ItemStack.EMPTY;
    private int maxItemUseDuration = 32;

    public UItemSwordFood(ToolMaterial material, int amount, float saturation) {
        super(material);
        this.healAmount = amount;
        this.saturationModifier = saturation;
    }

    public UItemSwordFood(ToolMaterial material, int amount) {
        this(material, amount, 0.6F);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack itemStack = player.getHeldItem(hand);
        if(player.canEat(this.alwaysEdible)) {
            player.setActiveHand(hand);
            return new ActionResult<>(EnumActionResult.SUCCESS, itemStack);
        }
        return new ActionResult<>(EnumActionResult.FAIL, itemStack);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack itemStack, World world, EntityLivingBase entity) {
        // itemStack = super.onItemUseFinish(itemStack, world, entity);
        if(entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            player.getFoodStats().addStats(this.getHealAmount(itemStack), this.getSaturationModifier(itemStack));
            world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
            this.onFoodEaten(itemStack, world, player);
            player.addStat(StatList.getObjectUseStats(this));
        }
        itemStack.shrink(1);

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

    public int getHealAmount(ItemStack itemStack) {
        return this.healAmount;
    }

    public float getSaturationModifier(ItemStack itemStack) {
        return this.saturationModifier;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.EAT;
    }

    public UItemSwordFood setMaxItemUseDuration(int maxItemUseDuration) {
        this.maxItemUseDuration = maxItemUseDuration;
        return this;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return maxItemUseDuration;
    }

    public UItemSwordFood setAlwaysEdible() {
        this.alwaysEdible = true;
        return this;
    }

    public UItemSwordFood setRestItem(ItemStack itemStack) {
        this.restItem = itemStack;
        return this;
    }

    public ItemStack getRestItem(ItemStack itemStack) {
        return restItem.copy();
    }

    public UItemSword setEatClearPotionType(ClearPotionType type) {
        clear = type;
        return this;
    }

    public UItemSword setEatPotionEffect(PotionEffect potion, float probability) {
        potions.clear();
        return addPotionEffect(potion, probability);
    }

    public UItemSword addEatPotionEffect(PotionEffect potion, float probability) {
        potions.put(potion, probability);
        return this;
    }

    public UItemSwordFood setEatFire(int second, float probability) {
        fireSec = second;
        fireProb = probability;
        return this;
    }
}
