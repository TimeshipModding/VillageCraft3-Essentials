package com.timeshipmodding.villagecraft3essentials.compat.aquaculture.registries;

import com.teammetallurgy.aquaculture.item.AquaFishingRodItem;
import com.teammetallurgy.aquaculture.item.ItemFilletKnife;
import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.compat.aquaculture.AquaMethods;
import com.timeshipmodding.villagecraft3essentials.content.item.registries.ModToolTiers;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModAquaRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(VillageCraft3Essentials.MODID);

    // Items
    public static final DeferredItem<Item> JADE_FISHING_ROD = ITEMS.register("jade_fishing_rod", () -> new AquaFishingRodItem(ModToolTiers.JADE, new Item.Properties().durability(450)));
    public static final DeferredItem<Item> AMBER_FISHING_ROD = ITEMS.register("amber_fishing_rod", () -> new AquaFishingRodItem(ModToolTiers.AMBER, new Item.Properties().durability(450)));
    public static final DeferredItem<Item> SILICON_FISHING_ROD = ITEMS.register("silicon_fishing_rod", () -> new AquaFishingRodItem(ModToolTiers.SILICON, new Item.Properties().durability(450)));
    public static final DeferredItem<Item> RUBY_FISHING_ROD = ITEMS.register("ruby_fishing_rod", () -> new AquaFishingRodItem(ModToolTiers.RUBY, new Item.Properties().durability(450)));
    public static final DeferredItem<Item> JADE_FILLET_KNIFE = ITEMS.register("jade_fillet_knife", () -> new ItemFilletKnife(ModToolTiers.JADE, new Item.Properties()));
    public static final DeferredItem<Item> AMBER_FILLET_KNIFE = ITEMS.register("amber_fillet_knife", () -> new ItemFilletKnife(ModToolTiers.AMBER, new Item.Properties()));
    public static final DeferredItem<Item> SILICON_FILLET_KNIFE = ITEMS.register("silicon_fillet_knife", () -> new ItemFilletKnife(ModToolTiers.SILICON, new Item.Properties()));
    public static final DeferredItem<Item> RUBY_FILLET_KNIFE = ITEMS.register("ruby_fillet_knife", () -> new ItemFilletKnife(ModToolTiers.RUBY, new Item.Properties()));
    public static final DeferredItem<Item> JADE_HOOK = AquaMethods.registerHook("jade");
    public static final DeferredItem<Item> AMBER_HOOK = AquaMethods.registerHook("amber");
    public static final DeferredItem<Item> SILICON_HOOK = AquaMethods.registerHook("silicon");
    public static final DeferredItem<Item> RUBY_HOOK = AquaMethods.registerHook("ruby");

    // Register Methods
    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}