package com.timeshipmodding.villagecraft3essentials.datagen;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.content.trim.registries.ModTrimMaterials;
import com.timeshipmodding.villagecraft3essentials.content.worldgen.biomemodifiers.ModOreBiomeModifers;
import com.timeshipmodding.villagecraft3essentials.content.worldgen.features.ModOreFeatures;
import com.timeshipmodding.villagecraft3essentials.content.worldgen.placements.ModOrePlacements;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class DataWorldgenAndDatapack extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModOreFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModOrePlacements::bootstrap)
            .add(Registries.TRIM_MATERIAL, ModTrimMaterials::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModOreBiomeModifers::bootstrap);

    public DataWorldgenAndDatapack(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(VillageCraft3Essentials.MODID));
    }
}