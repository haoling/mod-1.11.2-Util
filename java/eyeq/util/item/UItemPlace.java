package eyeq.util.item;

import eyeq.util.entity.player.EntityPlayerUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class UItemPlace extends Item {
    protected IBlockState state;
    protected boolean isDamage;

    public UItemPlace(IBlockState place, boolean isDamage) {
        this.state = place;
        this.isDamage = isDamage;
        this.setFull3D();
        this.setMaxStackSize(1);
        this.setMaxDamage(64);
        this.setCreativeTab(CreativeTabs.TOOLS);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemStack = player.getHeldItem(hand);
        if(EntityPlayerUtils.onItemPlace(player, world, itemStack, pos, facing, state)) {
            if(isDamage) {
                itemStack.damageItem(1, player);
            }
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.FAIL;
    }
}
