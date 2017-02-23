package eyeq.util.entity.player;

import net.minecraft.block.BlockCommandBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityPlayerUtils {
    public static EnumActionResult onItemUse(EntityPlayer player, World world, ItemStack itemStack, BlockPos pos, EnumFacing facing, Vec3d vec, EnumHand hand) {
        if(player.isSpectator()) {
            return EnumActionResult.PASS;
        }

        if(!world.getWorldBorder().contains(pos)) {
            return EnumActionResult.FAIL;
        }
        if(itemStack.isEmpty()) {
            return EnumActionResult.PASS;
        }

        ItemStack held = player.getHeldItem(hand);
        player.setHeldItem(hand, itemStack);

        float x = (float) (vec.xCoord - pos.getX());
        float y = (float) (vec.yCoord - pos.getY());
        float z = (float) (vec.zCoord - pos.getZ());
        IBlockState state = world.getBlockState(pos);
        if(!player.isSneaking()) {
            boolean isActivated = state.getBlock().onBlockActivated(world, pos, state, player, hand, facing, x, y, z);
            if(isActivated) {
                player.setHeldItem(hand, held);
                return EnumActionResult.SUCCESS;
            }
        }
        Item item = itemStack.getItem();
        if(item instanceof ItemBlock) {
            ItemBlock itemBlock = (ItemBlock) item;
            if(!itemBlock.canPlaceBlockOnSide(world, pos, facing, player, itemStack)) {
                player.setHeldItem(hand, held);
                return EnumActionResult.FAIL;
            }
            if(itemBlock.getBlock() instanceof BlockCommandBlock && !player.canUseCommand(2, "")) {
                player.setHeldItem(hand, held);
                return EnumActionResult.FAIL;
            }
        }

        int metadata = itemStack.getMetadata();
        int stackSize = itemStack.getCount();
        EnumActionResult result = itemStack.onItemUse(player, world, pos, hand, facing, x, y, z);
        if(player.isCreative()) {
            itemStack.setItemDamage(metadata);
            itemStack.setCount(stackSize);
        } else {
            if(itemStack.getCount() < 1) {
                net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, itemStack, hand);
            }
        }
        player.setHeldItem(hand, held);
        return result;
    }
}
