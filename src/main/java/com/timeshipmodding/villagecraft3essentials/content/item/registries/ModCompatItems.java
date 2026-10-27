package com.timeshipmodding.villagecraft3essentials.content.item.registries;

import com.blackgear.vanillabackport.common.level.items.spear.SpearItem;
import com.blackgear.vanillabackport.core.VanillaBackport;
import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.compat.aquaculture.AquaMethods;
import com.timeshipmodding.villagecraft3essentials.compat.betterarcheology.BAMethods;
import com.timeshipmodding.villagecraft3essentials.compat.farmersdelight.FDMethods;
import com.timeshipmodding.villagecraft3essentials.compat.vanillabackport.VanillaBackportMethods;
import net.minecraft.world.item.Item;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCompatItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(VillageCraft3Essentials.MODID);

    public static final DeferredItem<Item> JADE_KNIFE = ITEMS.register("jade_knife", () -> ModList.get().isLoaded("farmersdelight")
            ? FDMethods.getKnifeItemSupplier(ModToolTiers.JADE, 0.5f, -2.0F).get()
            : new Item(new Item.Properties()));
    public static final DeferredItem<Item> AMBER_KNIFE = ITEMS.register("amber_knife", () -> ModList.get().isLoaded("farmersdelight")
            ? FDMethods.getKnifeItemSupplier(ModToolTiers.AMBER, 0.5f, -2.0F).get()
            : new Item(new Item.Properties()));
    public static final DeferredItem<Item> SILICON_KNIFE = ITEMS.register("silicon_knife", () -> ModList.get().isLoaded("farmersdelight")
            ? FDMethods.getKnifeItemSupplier(ModToolTiers.SILICON, 0.5f, -2.0F).get()
            : new Item(new Item.Properties()));
    public static final DeferredItem<Item> RUBY_KNIFE = ITEMS.register("ruby_knife", () -> ModList.get().isLoaded("farmersdelight")
            ? FDMethods.getKnifeItemSupplier(ModToolTiers.RUBY, 0.5f, -2.0F).get()
            : new Item(new Item.Properties()));

    public static final DeferredItem<Item> JADE_FISHING_ROD = ITEMS.register("jade_fishing_rod", () -> ModList.get().isLoaded("aquaculture")
            ? AquaMethods.getAquaFishingRodItemSupplier(ModToolTiers.JADE).get()
            : new Item(new Item.Properties()));
    public static final DeferredItem<Item> AMBER_FISHING_ROD = ITEMS.register("amber_fishing_rod", () -> ModList.get().isLoaded("aquaculture")
            ? AquaMethods.getAquaFishingRodItemSupplier(ModToolTiers.AMBER).get()
            : new Item(new Item.Properties()));
    public static final DeferredItem<Item> SILICON_FISHING_ROD = ITEMS.register("silicon_fishing_rod", () -> ModList.get().isLoaded("aquaculture")
            ? AquaMethods.getAquaFishingRodItemSupplier(ModToolTiers.SILICON).get()
            : new Item(new Item.Properties()));
    public static final DeferredItem<Item> RUBY_FISHING_ROD = ITEMS.register("ruby_fishing_rod", () -> ModList.get().isLoaded("aquaculture")
            ? AquaMethods.getAquaFishingRodItemSupplier(ModToolTiers.RUBY).get()
            : new Item(new Item.Properties()));
    public static final DeferredItem<Item> JADE_FILLET_KNIFE = ITEMS.register("jade_fillet_knife", () -> ModList.get().isLoaded("aquaculture")
            ? AquaMethods.getFilletKnifeItemSupplier(ModToolTiers.JADE).get()
            : new Item(new Item.Properties()));
    public static final DeferredItem<Item> AMBER_FILLET_KNIFE = ITEMS.register("amber_fillet_knife", () -> ModList.get().isLoaded("aquaculture")
            ? AquaMethods.getFilletKnifeItemSupplier(ModToolTiers.AMBER).get()
            : new Item(new Item.Properties()));
    public static final DeferredItem<Item> SILICON_FILLET_KNIFE = ITEMS.register("silicon_fillet_knife", () -> ModList.get().isLoaded("aquaculture")
            ? AquaMethods.getFilletKnifeItemSupplier(ModToolTiers.SILICON).get()
            : new Item(new Item.Properties()));
    public static final DeferredItem<Item> RUBY_FILLET_KNIFE = ITEMS.register("ruby_fillet_knife", () -> ModList.get().isLoaded("aquaculture")
            ? AquaMethods.getFilletKnifeItemSupplier(ModToolTiers.RUBY).get()
            : new Item(new Item.Properties()));
    public static final DeferredItem<Item> JADE_HOOK = ModList.get().isLoaded("aquaculture")
            ? AquaMethods.registerHook("jade")
            : ITEMS.register("jade_hook", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> AMBER_HOOK = ModList.get().isLoaded("aquaculture")
            ? AquaMethods.registerHook("amber")
            : ITEMS.register("amber_hook", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SILICON_HOOK = ModList.get().isLoaded("aquaculture")
            ? AquaMethods.registerHook("silicon")
            : ITEMS.register("silicon_hook", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RUBY_HOOK = ModList.get().isLoaded("aquaculture")
            ? AquaMethods.registerHook("ruby")
            : ITEMS.register("ruby_hook", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> JADE_BRUSH = ITEMS.register("jade_brush", () -> ModList.get().isLoaded("betterarcheology")
            ? BAMethods.getBetterBrushItemSupplier().get()
            : new Item(new Item.Properties()));
    public static final DeferredItem<Item> AMBER_BRUSH = ITEMS.register("amber_brush", () -> ModList.get().isLoaded("betterarcheology")
            ? BAMethods.getBetterBrushItemSupplier().get()
            : new Item(new Item.Properties()));
    public static final DeferredItem<Item> SILICON_BRUSH = ITEMS.register("silicon_brush", () -> ModList.get().isLoaded("betterarcheology")
            ? BAMethods.getBetterBrushItemSupplier().get()
            : new Item(new Item.Properties()));
    public static final DeferredItem<Item> RUBY_BRUSH = ITEMS.register("ruby_brush", () -> ModList.get().isLoaded("betterarcheology")
            ? BAMethods.getBetterBrushItemSupplier().get()
            : new Item(new Item.Properties()));

    public static final DeferredItem<Item> JADE_BACKPORT_SPEAR = ITEMS.register("jade_backport_spear", () -> ModList.get().isLoaded("vanillabackport")
            ? VanillaBackportMethods.getSpearItemSupplier(ModToolTiers.JADE, new Item.Properties()).get()
            : new Item(new Item.Properties()));
    public static final DeferredItem<Item> AMBER_BACKPORT_SPEAR = ITEMS.register("amber_backport_spear", () -> ModList.get().isLoaded("vanillabackport")
            ? VanillaBackportMethods.getSpearItemSupplier(ModToolTiers.AMBER, new Item.Properties()).get()
            : new Item(new Item.Properties()));
    public static final DeferredItem<Item> SILICON_BACKPORT_SPEAR = ITEMS.register("silicon_backport_spear", () -> ModList.get().isLoaded("vanillabackport")
            ? VanillaBackportMethods.getSpearItemSupplier(ModToolTiers.SILICON, new Item.Properties()).get()
            : new Item(new Item.Properties()));
    public static final DeferredItem<Item> RUBY_BACKPORT_SPEAR = ITEMS.register("ruby_backport_spear", () -> ModList.get().isLoaded("vanillabackport")
            ? VanillaBackportMethods.getSpearItemSupplier(ModToolTiers.RUBY, new Item.Properties()).get()
            : new Item(new Item.Properties()));

    public static final DeferredItem<Item> JADE_NAUTILUS_ARMOR = ITEMS.register("jade_nautilus_armor", () -> ModList.get().isLoaded("vanillabackport")
            ? VanillaBackportMethods.getNautilusArmorItemSupplier(ModArmorMaterials.JADE, new Item.Properties()).get()
            : new Item(new Item.Properties()));
    public static final DeferredItem<Item> AMBER_NAUTILUS_ARMOR = ITEMS.register("amber_nautilus_armor", () -> ModList.get().isLoaded("vanillabackport")
            ? VanillaBackportMethods.getNautilusArmorItemSupplier(ModArmorMaterials.AMBER, new Item.Properties()).get()
            : new Item(new Item.Properties()));
    public static final DeferredItem<Item> SILICON_NAUTILUS_ARMOR = ITEMS.register("silicon_nautilus_armor", () -> ModList.get().isLoaded("vanillabackport")
            ? VanillaBackportMethods.getNautilusArmorItemSupplier(ModArmorMaterials.SILICON, new Item.Properties()).get()
            : new Item(new Item.Properties()));
    public static final DeferredItem<Item> RUBY_NAUTILUS_ARMOR = ITEMS.register("ruby_nautilus_armor", () -> ModList.get().isLoaded("vanillabackport")
            ? VanillaBackportMethods.getNautilusArmorItemSupplier(ModArmorMaterials.RUBY, new Item.Properties()).get()
            : new Item(new Item.Properties()));
}