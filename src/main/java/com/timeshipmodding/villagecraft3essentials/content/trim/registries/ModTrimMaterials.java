package com.timeshipmodding.villagecraft3essentials.content.trim.registries;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.content.item.registries.ModItems;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;

import java.util.Map;

public class ModTrimMaterials {
    public static final ResourceKey<TrimMaterial> JADE =
            ResourceKey.create(Registries.TRIM_MATERIAL, ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "jade"));
    public static final ResourceKey<TrimMaterial> AMBER =
            ResourceKey.create(Registries.TRIM_MATERIAL, ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "amber"));
    public static final ResourceKey<TrimMaterial> SILICON =
            ResourceKey.create(Registries.TRIM_MATERIAL, ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "silicon"));
    public static final ResourceKey<TrimMaterial> RUBY =
            ResourceKey.create(Registries.TRIM_MATERIAL, ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "ruby"));

    public static void bootstrap(BootstrapContext<TrimMaterial> context) {
        // TODO: change jade and silicon text colours based on gem textures
        register(context, JADE, ModItems.JADE.get(), Style.EMPTY.withColor(TextColor.parseColor("#cf7483").getOrThrow()), 0.7F);
        register(context, AMBER, ModItems.AMBER.get(), Style.EMPTY.withColor(TextColor.parseColor("#ca8513").getOrThrow()), 0.6F);
        register(context, SILICON, ModItems.SILICON.get(), Style.EMPTY.withColor(TextColor.parseColor("#c71836").getOrThrow()), 0.2F);
        register(context, RUBY, ModItems.RUBY.get(), Style.EMPTY.withColor(TextColor.parseColor("#c71836").getOrThrow()), 0.4F);
    }

    private static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> trimKey, Item item, Style style, float itemModelIndex) {
        TrimMaterial trimmaterial = TrimMaterial.create(trimKey.location().getPath(), item, itemModelIndex, Component.translatable(Util.makeDescriptionId("trim_material", trimKey.location())).withStyle(style), Map.of());
        context.register(trimKey, trimmaterial);
    }
}