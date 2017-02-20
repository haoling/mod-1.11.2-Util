package eyeq.util.asm;

import org.objectweb.asm.Type;

public class ClassNames {
    public static final String DESC_BLOCK = "Lnet/minecraft/block/Block;";
    public static final String DESC_I_BLOCK_STATE = "Lnet/minecraft/block/state/IBlockState;";
    public static final String DESC_ENTITY_PLAYER = "Lnet/minecraft/entity/player/EntityPlayer;";
    public static final String DESC_ITEM_STACK = "Lnet/minecraft/item/ItemStack;";
    public static final String DESC_ENUM_FACING = "Lnet/minecraft/util/EnumFacing;";
    public static final String DESC_ENUM_HAND = "Lnet/minecraft/util/EnumHand;";
    public static final String DESC_BLOCK_POS = "Lnet/minecraft/util/math/BlockPos;";
    public static final String DESC_WORLD = "Lnet/minecraft/world/World;";

    public static final String DESC_RANDOM = "Ljava/util/Random;";

    public static String toDesc(Object returnType, Object... desc) {
        String ret = "(";
        for(Object raw : desc) {
            ret += toDesc(raw);
        }
        ret += ")";
        ret += toDesc(returnType);
        return ret;
    }

    public static String toDesc(Object raw) {
        if(raw instanceof Class) {
            return Type.getDescriptor((Class) raw);
        }
        if(raw instanceof String) {
            return (String) raw;
        }
        return raw.toString();
    }
}
