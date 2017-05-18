package eyeq.util.item;

import eyeq.util.world.WorldUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public abstract class ItemThrow extends Item {
    public ItemThrow() {
        this.setMaxStackSize(16);
        this.setCreativeTab(CreativeTabs.MISC);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack itemStack = player.getHeldItem(hand);
        if(!player.isCreative()) {
            itemStack.shrink(1);
        }

        WorldUtils.playSound(world, player, getSoundEvent(), itemRand);
        if(!world.isRemote) {
            EntityThrowable entity = createEntityThrowable(world, player);
            entity.setHeadingFromThrower(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
            world.spawnEntity(entity);
        }
        player.addStat(StatList.getObjectUseStats(this));
        return new ActionResult<>(EnumActionResult.SUCCESS, itemStack);
    }

    public abstract SoundEvent getSoundEvent();

    public abstract EntityThrowable createEntityThrowable(World world, EntityPlayer player);
}
