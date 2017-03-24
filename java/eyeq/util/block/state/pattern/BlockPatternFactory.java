package eyeq.util.block.state.pattern;

import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.pattern.BlockMaterialMatcher;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.block.state.pattern.FactoryBlockPattern;

public class BlockPatternFactory {
    public static BlockPattern create(String[] aisle, Object... where) {
        FactoryBlockPattern pattern = FactoryBlockPattern.start().aisle(aisle);
        for(int i = 0; i < where.length; i += 2) {
            Character ch = (Character) where[i];
            Object in = where[i + 1];
            Predicate matcher;
            if(in instanceof Predicate) {
                matcher = (Predicate) in;
            } else if(in instanceof Block) {
                matcher = BlockWorldState.hasState(BlockStateMatcher.forBlock((Block) in));
            } else if(in instanceof Material) {
                matcher = BlockWorldState.hasState(BlockMaterialMatcher.forMaterial((Material) in));
            } else {
                String ret = "Invalid block pattern: ";
                for(String s : aisle) {
                    ret += s;
                }
                for(Object tmp : where) {
                    ret += tmp + ", ";
                }
                throw new RuntimeException(ret);
            }
            pattern.where(ch, matcher);
        }
        return pattern.build();
    }
}
