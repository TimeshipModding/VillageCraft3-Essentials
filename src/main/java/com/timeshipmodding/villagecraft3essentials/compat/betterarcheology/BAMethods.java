package com.timeshipmodding.villagecraft3essentials.compat.betterarcheology;

import net.Pandarix.item.BetterBrushItem;
import net.Pandarix.util.BetterBrushTiers;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class BAMethods {
    public static Supplier<Item> getBetterBrushItemSupplier() {
        return () -> new BetterBrushItem(new Item.Properties().durability(512), BetterBrushTiers.DIAMOND);
    }
}
