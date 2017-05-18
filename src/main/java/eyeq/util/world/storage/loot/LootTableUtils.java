package eyeq.util.world.storage.loot;

import net.minecraft.item.Item;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.ArrayList;
import java.util.List;

public class LootTableUtils {
    public static List<LootPool> getLootPools(LootTable table) {
        List<LootPool> list = new ArrayList<>();
        list.add(table.getPool("main"));
        for(int i = 1; ; i++) {
            LootPool pool = table.getPool("pool" + i);
            if(pool == null) {
                return list;
            }
            list.add(pool);
        }
    }

    public static LootEntryItem getLootEntryItem(Item item, int weight) {
        return new LootEntryItem(item, weight, 0, new LootFunction[0], new LootCondition[0], item.getRegistryName().toString());
    }

    public static LootEntryItem getLootEntryItem(Item item, LootEntry base, String num) {
        if(base instanceof LootEntryItem) {
            return getLootEntryItem(item, (LootEntryItem) base, item.getRegistryName().toString() + num);
        }
        return null;
    }

    public static LootEntryItem getLootEntryItem(Item item, LootEntryItem base, String entryName) {
        return new LootEntryItem(item, getWeight(base), getQuality(base), getFunctions(base), getConditions(base), entryName);
    }

    public static int getWeight(LootEntry entry) {
        return ObfuscationReflectionHelper.getPrivateValue(LootEntry.class, entry, "weight", "field_186364_c");
    }

    public static int getQuality(LootEntry entry) {
        return ObfuscationReflectionHelper.getPrivateValue(LootEntry.class, entry, "quality", "field_186365_d");
    }

    public static LootCondition[] getConditions(LootEntry entry) {
        return ObfuscationReflectionHelper.getPrivateValue(LootEntry.class, entry, "conditions", "field_186366_e");
    }

    public static LootFunction[] getFunctions(LootEntryItem entry) {
        return ObfuscationReflectionHelper.getPrivateValue(LootEntryItem.class, entry, "functions", "field_186369_b");
    }
}
