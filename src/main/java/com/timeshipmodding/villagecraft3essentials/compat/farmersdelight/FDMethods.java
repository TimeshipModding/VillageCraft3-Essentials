package com.timeshipmodding.villagecraft3essentials.compat.farmersdelight;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import vectorwing.farmersdelight.common.item.KnifeItem;
import java.util.function.Supplier;

public class FDMethods {
    public static Supplier<Item> getKnifeItemSupplier(Tier tier, Float attackDamage, Float attackSpeed) {
        return () -> new vectorwing.farmersdelight.common.item.KnifeItem(tier, new Item.Properties().attributes(KnifeItem.createAttributes(tier, attackDamage, attackSpeed)));
    }
}
