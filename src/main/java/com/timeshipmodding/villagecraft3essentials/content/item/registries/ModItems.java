package com.timeshipmodding.villagecraft3essentials.content.item.registries;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.content.entity.client.registries.ModEntities;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(VillageCraft3Essentials.MODID);

    // Items
    public static final DeferredItem<Item> RUBY = ITEMS.register("ruby", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RUBY_SWORD = ITEMS.register("ruby_sword", () -> new SwordItem(ModToolTiers.RUBY, new Item.Properties().attributes(SwordItem.createAttributes(ModToolTiers.RUBY, 3, -2.4f))));
    public static final DeferredItem<Item> RUBY_SHOVEL = ITEMS.register("ruby_shovel", () -> new ShovelItem(ModToolTiers.RUBY, new Item.Properties().attributes(ShovelItem.createAttributes(ModToolTiers.RUBY, 1.5f, -3.0f))));
    public static final DeferredItem<Item> RUBY_PICKAXE = ITEMS.register("ruby_pickaxe", () -> new PickaxeItem(ModToolTiers.RUBY, new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.RUBY, 1.0f, -2.8f))));
    public static final DeferredItem<Item> RUBY_AXE = ITEMS.register("ruby_axe", () -> new AxeItem(ModToolTiers.RUBY, new Item.Properties().attributes(AxeItem.createAttributes(ModToolTiers.RUBY, 5.0f, -3.0f))));
    public static final DeferredItem<Item> RUBY_HOE = ITEMS.register("ruby_hoe", () -> new HoeItem(ModToolTiers.RUBY, new Item.Properties().attributes(HoeItem.createAttributes(ModToolTiers.RUBY, -3.0f, 0.0f))));
    public static final DeferredItem<Item> RUBY_HELMET = ITEMS.register("ruby_helmet", () -> new ArmorItem(ModArmorMaterials.RUBY, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(33))));
    public static final DeferredItem<Item> RUBY_CHESTPLATE = ITEMS.register("ruby_chestplate", () -> new ArmorItem(ModArmorMaterials.RUBY, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(33))));
    public static final DeferredItem<Item> RUBY_LEGGINGS = ITEMS.register("ruby_leggings", () -> new ArmorItem(ModArmorMaterials.RUBY, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(33))));
    public static final DeferredItem<Item> RUBY_BOOTS = ITEMS.register("ruby_boots", () -> new ArmorItem(ModArmorMaterials.RUBY, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(33))));
    public static final DeferredItem<Item> RUBY_NETHERITE_SWORD = ITEMS.register("ruby_netherite_sword", () -> new SwordItem(ModToolTiers.RUBY_NETHERITE, new Item.Properties().attributes(SwordItem.createAttributes(ModToolTiers.RUBY_NETHERITE, 3, -2.4f))));
    public static final DeferredItem<Item> RUBY_NETHERITE_SHOVEL = ITEMS.register("ruby_netherite_shovel", () -> new ShovelItem(ModToolTiers.RUBY_NETHERITE, new Item.Properties().attributes(ShovelItem.createAttributes(ModToolTiers.RUBY_NETHERITE, 1.5f, -3.0f))));
    public static final DeferredItem<Item> RUBY_NETHERITE_PICKAXE = ITEMS.register("ruby_netherite_pickaxe", () -> new PickaxeItem(ModToolTiers.RUBY_NETHERITE, new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.RUBY_NETHERITE, 1.0f, -2.8f))));
    public static final DeferredItem<Item> RUBY_NETHERITE_AXE = ITEMS.register("ruby_netherite_axe", () -> new AxeItem(ModToolTiers.RUBY_NETHERITE, new Item.Properties().attributes(AxeItem.createAttributes(ModToolTiers.RUBY_NETHERITE, 5.0f, -3.0f))));
    public static final DeferredItem<Item> RUBY_NETHERITE_HOE = ITEMS.register("ruby_netherite_hoe", () -> new HoeItem(ModToolTiers.RUBY_NETHERITE, new Item.Properties().attributes(HoeItem.createAttributes(ModToolTiers.RUBY_NETHERITE, -4.0f, 0.0f))));
    public static final DeferredItem<Item> RUBY_HORSE_ARMOR = ITEMS.register("ruby_horse_armor",() -> new AnimalArmorItem(ModArmorMaterials.RUBY, AnimalArmorItem.BodyType.EQUESTRIAN, false, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> AMBER = ITEMS.register("amber", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> AMBER_SWORD = ITEMS.register("amber_sword", () -> new SwordItem(ModToolTiers.AMBER, new Item.Properties().attributes(SwordItem.createAttributes(ModToolTiers.AMBER, 3, -2.4f))));
    public static final DeferredItem<Item> AMBER_SHOVEL = ITEMS.register("amber_shovel", () -> new ShovelItem(ModToolTiers.AMBER, new Item.Properties().attributes(ShovelItem.createAttributes(ModToolTiers.AMBER, 1.5f, -3.0f))));
    public static final DeferredItem<Item> AMBER_PICKAXE = ITEMS.register("amber_pickaxe", () -> new PickaxeItem(ModToolTiers.AMBER, new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.AMBER, 1.0f, -2.8f))));
    public static final DeferredItem<Item> AMBER_AXE = ITEMS.register("amber_axe", () -> new AxeItem(ModToolTiers.AMBER, new Item.Properties().attributes(AxeItem.createAttributes(ModToolTiers.AMBER, 5.0f, -3.0f))));
    public static final DeferredItem<Item> AMBER_HOE = ITEMS.register("amber_hoe", () -> new HoeItem(ModToolTiers.AMBER, new Item.Properties().attributes(HoeItem.createAttributes(ModToolTiers.AMBER, -3.0f, 0.0f))));
    public static final DeferredItem<Item> AMBER_HELMET = ITEMS.register("amber_helmet", () -> new ArmorItem(ModArmorMaterials.AMBER, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(33))));
    public static final DeferredItem<Item> AMBER_CHESTPLATE = ITEMS.register("amber_chestplate", () -> new ArmorItem(ModArmorMaterials.AMBER, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(33))));
    public static final DeferredItem<Item> AMBER_LEGGINGS = ITEMS.register("amber_leggings", () -> new ArmorItem(ModArmorMaterials.AMBER, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(33))));
    public static final DeferredItem<Item> AMBER_BOOTS = ITEMS.register("amber_boots", () -> new ArmorItem(ModArmorMaterials.AMBER, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(33))));
    public static final DeferredItem<Item> AMBER_NETHERITE_SWORD = ITEMS.register("amber_netherite_sword", () -> new SwordItem(ModToolTiers.AMBER_NETHERITE, new Item.Properties().attributes(SwordItem.createAttributes(ModToolTiers.AMBER_NETHERITE, 3, -2.4f))));
    public static final DeferredItem<Item> AMBER_NETHERITE_SHOVEL = ITEMS.register("amber_netherite_shovel", () -> new ShovelItem(ModToolTiers.AMBER_NETHERITE, new Item.Properties().attributes(ShovelItem.createAttributes(ModToolTiers.AMBER_NETHERITE, 1.5f, -3.0f))));
    public static final DeferredItem<Item> AMBER_NETHERITE_PICKAXE = ITEMS.register("amber_netherite_pickaxe", () -> new PickaxeItem(ModToolTiers.AMBER_NETHERITE, new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.AMBER_NETHERITE, 1.0f, -2.8f))));
    public static final DeferredItem<Item> AMBER_NETHERITE_AXE = ITEMS.register("amber_netherite_axe", () -> new AxeItem(ModToolTiers.AMBER_NETHERITE, new Item.Properties().attributes(AxeItem.createAttributes(ModToolTiers.AMBER_NETHERITE, 5.0f, -3.0f))));
    public static final DeferredItem<Item> AMBER_NETHERITE_HOE = ITEMS.register("amber_netherite_hoe", () -> new HoeItem(ModToolTiers.AMBER_NETHERITE, new Item.Properties().attributes(HoeItem.createAttributes(ModToolTiers.AMBER_NETHERITE, -4.0f, 0.0f))));
    public static final DeferredItem<Item> AMBER_HORSE_ARMOR = ITEMS.register("amber_horse_armor",() -> new AnimalArmorItem(ModArmorMaterials.AMBER, AnimalArmorItem.BodyType.EQUESTRIAN, false, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> MOLE_SPAWN_EGG = ITEMS.register("mole_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.MOLE, 0x6f6860, 0xcd9a97, new Item.Properties()));
    public static final DeferredItem<Item> WORM = ITEMS.register("worm", () -> new Item(new Item.Properties().food(ModFoodProperties.WORM)));
    public static final DeferredItem<Item> COOKED_WORM = ITEMS.register("cooked_worm", () -> new Item(new Item.Properties().food(ModFoodProperties.COOKED_WORM)));
    public static final DeferredItem<Item> WORM_ON_A_STICK = ITEMS.register("worm_on_a_stick", () -> new FoodOnAStickItem<>(new Item.Properties().durability(25), ModEntities.MOLE.get(), 7));
    public static final DeferredItem<Item> POLICE_BATON = ITEMS.register("police_baton", () -> new MaceItem(new Item.Properties().rarity(Rarity.EPIC).durability(500).component(DataComponents.TOOL, MaceItem.createToolProperties()).attributes(MaceItem.createAttributes())));
}
