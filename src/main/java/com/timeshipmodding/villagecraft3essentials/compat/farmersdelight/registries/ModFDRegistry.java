package com.timeshipmodding.villagecraft3essentials.compat.farmersdelight.registries;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.content.item.registries.ModToolTiers;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import vectorwing.farmersdelight.common.item.KnifeItem;

public class ModFDRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(VillageCraft3Essentials.MODID);

    public static final DeferredItem<Item> JADE_KNIFE = ITEMS.register("jade_knife", () -> new vectorwing.farmersdelight.common.item.KnifeItem(ModToolTiers.JADE, new Item.Properties().attributes(KnifeItem.createAttributes(ModToolTiers.JADE, 0.5f, -2.0F))));
    public static final DeferredItem<Item> AMBER_KNIFE = ITEMS.register("amber_knife", () -> new vectorwing.farmersdelight.common.item.KnifeItem(ModToolTiers.AMBER, new Item.Properties().attributes(KnifeItem.createAttributes(ModToolTiers.AMBER, 0.5f, -2.0F))));
    public static final DeferredItem<Item> SILICON_KNIFE = ITEMS.register("silicon_knife", () -> new vectorwing.farmersdelight.common.item.KnifeItem(ModToolTiers.SILICON, new Item.Properties().attributes(KnifeItem.createAttributes(ModToolTiers.SILICON, 0.5f, -2.0F))));
    public static final DeferredItem<Item> RUBY_KNIFE = ITEMS.register("ruby_knife", () -> new vectorwing.farmersdelight.common.item.KnifeItem(ModToolTiers.RUBY, new Item.Properties().attributes(KnifeItem.createAttributes(ModToolTiers.RUBY, 0.5f, -2.0F))));

    // Helper methods
    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
