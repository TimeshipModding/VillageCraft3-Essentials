package com.timeshipmodding.villagecraft3essentials.content.item.registries;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;

public class ModArmorMaterials {
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(BuiltInRegistries.ARMOR_MATERIAL, VillageCraft3Essentials.MODID);

    public static final Holder<ArmorMaterial> RUBY = ARMOR_MATERIALS.register("ruby", () -> new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.LEGGINGS, 6);
                map.put(ArmorItem.Type.CHESTPLATE, 8);
                map.put(ArmorItem.Type.HELMET, 3);
                map.put(ArmorItem.Type.BODY, 11);
            }), 10, SoundEvents.ARMOR_EQUIP_DIAMOND, () -> Ingredient.of(ModItems.RUBY), List.of(
                    new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "ruby"))
    ), 2.0f, 0.0f
    ));

    public static final Holder<ArmorMaterial> AMBER = ARMOR_MATERIALS.register("amber", () -> new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.LEGGINGS, 6);
                map.put(ArmorItem.Type.CHESTPLATE, 8);
                map.put(ArmorItem.Type.HELMET, 3);
                map.put(ArmorItem.Type.BODY, 11);
            }), 10, SoundEvents.ARMOR_EQUIP_DIAMOND, () -> Ingredient.of(ModItems.AMBER), List.of(
                    new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "amber"))
    ), 2.0f, 0.0f
    ));
}
