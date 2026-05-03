package com.timeshipmodding.villagecraft3essentials.content.item.registries;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.compat.aquaculture.AquaMethods;
import com.timeshipmodding.villagecraft3essentials.compat.betterarcheology.BAMethods;
import com.timeshipmodding.villagecraft3essentials.compat.farmersdelight.FDMethods;
import net.minecraft.world.item.Item;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCompatItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(VillageCraft3Essentials.MODID);

    public static final DeferredItem<Item> RUBY_KNIFE = ITEMS.register("ruby_knife", () -> ModList.get().isLoaded("farmersdelight")
            ? FDMethods.getKnifeItemSupplier(ModToolTiers.RUBY, 0.5f, -2.0F).get()
            : new Item(new Item.Properties()));
    public static final DeferredItem<Item> AMBER_KNIFE = ITEMS.register("amber_knife", () -> ModList.get().isLoaded("farmersdelight")
            ? FDMethods.getKnifeItemSupplier(ModToolTiers.RUBY, 0.5f, -2.0F).get()
            : new Item(new Item.Properties()));

    public static final DeferredItem<Item> RUBY_FISHING_ROD = ITEMS.register("ruby_fishing_rod", () -> ModList.get().isLoaded("aquaculture")
            ? AquaMethods.getAquaFishingRodItemSupplier(ModToolTiers.RUBY).get()
            : new Item(new Item.Properties()));
    public static final DeferredItem<Item> AMBER_FISHING_ROD = ITEMS.register("amber_fishing_rod", () -> ModList.get().isLoaded("aquaculture")
            ? AquaMethods.getAquaFishingRodItemSupplier(ModToolTiers.RUBY).get()
            : new Item(new Item.Properties()));
    public static final DeferredItem<Item> RUBY_FILLET_KNIFE = ITEMS.register("ruby_fillet_knife", () -> ModList.get().isLoaded("aquaculture")
            ? AquaMethods.getFilletKnifeItemSupplier(ModToolTiers.RUBY).get()
            : new Item(new Item.Properties()));
    public static final DeferredItem<Item> AMBER_FILLET_KNIFE = ITEMS.register("amber_fillet_knife", () -> ModList.get().isLoaded("aquaculture")
            ? AquaMethods.getFilletKnifeItemSupplier(ModToolTiers.RUBY).get()
            : new Item(new Item.Properties()));
    public static final DeferredItem<Item> RUBY_HOOK = ModList.get().isLoaded("aquaculture")
            ? AquaMethods.registerHook("ruby")
            : ITEMS.register("ruby_hook", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> AMBER_HOOK = ModList.get().isLoaded("aquaculture")
            ? AquaMethods.registerHook("amber")
            : ITEMS.register("amber_hook", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> RUBY_BRUSH = ITEMS.register("ruby_brush", () -> ModList.get().isLoaded("betterarcheology")
            ? BAMethods.getBetterBrushItemSupplier().get()
            : new Item(new Item.Properties()));
    public static final DeferredItem<Item> AMBER_BRUSH = ITEMS.register("amber_brush", () -> ModList.get().isLoaded("betterarcheology")
            ? BAMethods.getBetterBrushItemSupplier().get()
            : new Item(new Item.Properties()));

}
