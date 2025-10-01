package com.timeshipmodding.villagecraft3essentials.content.worldgen.biomemodifiers;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.content.worldgen.placements.ModOrePlacements;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModOreBiomeModifers {
    public static final ResourceKey<BiomeModifier> ORE_RUBY_SMALL = registerKey("ore_ruby_small");
    public static final ResourceKey<BiomeModifier> ORE_RUBY_MEDIUM = registerKey("ore_ruby_medium");
    public static final ResourceKey<BiomeModifier> ORE_RUBY_LARGE = registerKey("ore_ruby_large");
    public static final ResourceKey<BiomeModifier> ORE_RUBY_BURIED = registerKey("ore_ruby_buried");
    public static final ResourceKey<BiomeModifier> ORE_AMBER_SMALL = registerKey("ore_amber_small");
    public static final ResourceKey<BiomeModifier> ORE_AMBER_MEDIUM = registerKey("ore_amber_medium");
    public static final ResourceKey<BiomeModifier> ORE_AMBER_LARGE = registerKey("ore_amber_large");
    public static final ResourceKey<BiomeModifier> ORE_AMBER_BURIED = registerKey("ore_amber_buried");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ORE_RUBY_SMALL, new BiomeModifiers.AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModOrePlacements.ORE_RUBY_SMALL)), GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ORE_RUBY_MEDIUM, new BiomeModifiers.AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModOrePlacements.ORE_RUBY_MEDIUM)), GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ORE_RUBY_LARGE, new BiomeModifiers.AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModOrePlacements.ORE_RUBY_LARGE)), GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ORE_RUBY_BURIED, new BiomeModifiers.AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModOrePlacements.ORE_RUBY_BURIED)), GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ORE_AMBER_SMALL, new BiomeModifiers.AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModOrePlacements.ORE_AMBER_SMALL)), GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ORE_AMBER_MEDIUM, new BiomeModifiers.AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModOrePlacements.ORE_AMBER_MEDIUM)), GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ORE_AMBER_LARGE, new BiomeModifiers.AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModOrePlacements.ORE_AMBER_LARGE)), GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ORE_AMBER_BURIED, new BiomeModifiers.AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModOrePlacements.ORE_AMBER_BURIED)), GenerationStep.Decoration.UNDERGROUND_ORES));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, name));
    }
}

