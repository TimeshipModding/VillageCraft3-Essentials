package com.timeshipmodding.villagecraft3essentials.compat.vanillabackport.registries;

import com.blackgear.vanillabackport.common.level.items.NautilusArmorItem;
import com.blackgear.vanillabackport.common.level.items.spear.SpearItem;
import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.content.item.registries.ModArmorMaterials;
import com.timeshipmodding.villagecraft3essentials.content.item.registries.ModToolTiers;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModVanillaBackportRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(VillageCraft3Essentials.MODID);

    // Items
    public static final DeferredItem<Item> JADE_BACKPORT_SPEAR = ITEMS.register("jade_backport_spear", () -> new SpearItem(ModToolTiers.JADE, 1.05F, 1.075F, 0.5F, 3.0F, 7.5F, 6.5F, 5.1F, 10.0F, 4.6F, new Item.Properties()));
    public static final DeferredItem<Item> AMBER_BACKPORT_SPEAR = ITEMS.register("amber_backport_spear", () -> new SpearItem(ModToolTiers.AMBER, 1.05F, 1.075F, 0.5F, 3.0F, 7.5F, 6.5F, 5.1F, 10.0F, 4.6F, new Item.Properties()));
    public static final DeferredItem<Item> SILICON_BACKPORT_SPEAR = ITEMS.register("silicon_backport_spear", () -> new SpearItem(ModToolTiers.SILICON, 1.05F, 1.075F, 0.5F, 3.0F, 7.5F, 6.5F, 5.1F, 10.0F, 4.6F, new Item.Properties()));
    public static final DeferredItem<Item> RUBY_BACKPORT_SPEAR = ITEMS.register("ruby_backport_spear", () -> new SpearItem(ModToolTiers.RUBY, 1.05F, 1.075F, 0.5F, 3.0F, 7.5F, 6.5F, 5.1F, 10.0F, 4.6F, new Item.Properties()));
    public static final DeferredItem<Item> JADE_NAUTILUS_ARMOR = ITEMS.register("jade_nautilus_armor", () -> new NautilusArmorItem(ModArmorMaterials.JADE, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> AMBER_NAUTILUS_ARMOR = ITEMS.register("amber_nautilus_armor", () -> new NautilusArmorItem(ModArmorMaterials.AMBER, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> SILICON_NAUTILUS_ARMOR = ITEMS.register("silicon_nautilus_armor", () -> new NautilusArmorItem(ModArmorMaterials.SILICON, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> RUBY_NAUTILUS_ARMOR = ITEMS.register("ruby_nautilus_armor", () -> new NautilusArmorItem(ModArmorMaterials.RUBY, new Item.Properties().stacksTo(1)));

    // Register methods
    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
