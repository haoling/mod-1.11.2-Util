package eyeq.util.item;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public abstract class UItemBucket extends ItemBucket {
    public final Block contain;

    public UItemBucket(Block contain) {
        super(contain);
        this.contain = contain;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack preItemStack = player.getHeldItem(hand);
        RayTraceResult raytrace = this.rayTrace(world, player, true);
        if(raytrace == null) {
            return new ActionResult<>(EnumActionResult.PASS, preItemStack);
        }
        int maxDamage = preItemStack.getMaxDamage();
        int damage = preItemStack.getItemDamage();

        boolean isEmpty = this.contain == Blocks.AIR;
        if(isEmpty || (!player.isCreative() && maxDamage > damage)) {
            BlockPos pos = raytrace.getBlockPos();
            IBlockState state = world.getBlockState(pos);

            player.setHeldItem(hand, new ItemStack(getBucketEmpty()));
            ActionResult<ItemStack> result = Items.BUCKET.onItemRightClick(world, player, hand);
            player.setHeldItem(hand, preItemStack);

            ItemStack resultItemStack = result.getResult();
            Item drew = getUBuckt(resultItemStack.getItem());
            if(isEmpty) {
                if(drew == null) {
                    world.setBlockState(pos, state, 11);
                    return new ActionResult<>(EnumActionResult.FAIL, preItemStack);
                }
                if(result.getType() == EnumActionResult.SUCCESS && !player.isCreative()) {
                    ItemStack drewItemStack = new ItemStack(drew);

                    preItemStack.shrink(1);
                    if(preItemStack.isEmpty()) {
                        return new ActionResult<>(EnumActionResult.SUCCESS, drewItemStack);
                    }
                    if (!player.inventory.addItemStackToInventory(drewItemStack)) {
                        player.dropItem(drewItemStack, false);
                    }
                }
                return new ActionResult<>(result.getType(), preItemStack);
            }

            if(result.getType() == EnumActionResult.SUCCESS) {
                if(drew == preItemStack.getItem()) {
                    preItemStack.setItemDamage(damage + 1);
                    return new ActionResult<>(EnumActionResult.SUCCESS, preItemStack);
                }
                world.setBlockState(pos, state, 11);
            }
        }

        if(player.isSneaking() && maxDamage > 0) {
            return new ActionResult<>(EnumActionResult.PASS, preItemStack);
        }
        ActionResult<ItemStack> result = super.onItemRightClick(world, player, hand);
        if(result.getType() != EnumActionResult.SUCCESS) {
            return result;
        }
        ItemStack resultItemStack = result.getResult();
        Item put = getUBuckt(resultItemStack.getItem());
        if(put == getBucketEmpty() && damage > 0) {
            preItemStack.setItemDamage(damage - 1);
            return new ActionResult<>(EnumActionResult.SUCCESS, preItemStack);
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, new ItemStack(put, resultItemStack.getCount()));
    }

    @Override
    public double getDurabilityForDisplay(ItemStack itemStack) {
        double max =itemStack.getMaxDamage();
        return (max - itemStack.getItemDamage()) / max;
    }

    public Item getUBuckt(Item item) {
        if(item == Items.BUCKET) {
            return getBucketEmpty();
        }
        if(item == Items.MILK_BUCKET) {
            return getBucketMilk();
        }
        if(item == Items.WATER_BUCKET) {
            return getBucketWater();
        }
        if(item == Items.LAVA_BUCKET) {
            return getBucketLava();
        }
        return null;
    }

    // contain = Blocks.AIR & maxDamage = 0
    public abstract Item getBucketEmpty();

    // maxStackSize = 1
    public abstract Item getBucketMilk();

    // maxStackSize = 1
    public abstract Item getBucketWater();

    // maxStackSize = 1
    public abstract Item getBucketLava();
}
