package com.timeshipmodding.villagecraft3essentials.content.worldgen.placements;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.content.worldgen.features.ModOreFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModOrePlacements {
    public static final ResourceKey<PlacedFeature> ORE_RUBY_SMALL = registerKey("ore_ruby_small");
    public static final ResourceKey<PlacedFeature> ORE_RUBY_MEDIUM = registerKey("ore_ruby_medium");
    public static final ResourceKey<PlacedFeature> ORE_RUBY_LARGE = registerKey("ore_ruby_large");
    public static final ResourceKey<PlacedFeature> ORE_RUBY_BURIED = registerKey("ore_ruby_buried");
    public static final ResourceKey<PlacedFeature> ORE_AMBER_SMALL = registerKey("ore_amber_small");
    public static final ResourceKey<PlacedFeature> ORE_AMBER_MEDIUM = registerKey("ore_amber_medium");
    public static final ResourceKey<PlacedFeature> ORE_AMBER_LARGE = registerKey("ore_amber_large");
    public static final ResourceKey<PlacedFeature> ORE_AMBER_BURIED = registerKey("ore_amber_buried");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, ORE_RUBY_SMALL, configuredFeatures.getOrThrow(ModOreFeatures.ORE_RUBY_SMALL),
                ModOrePlacements.commonOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.absolute(80))));
        register(context, ORE_RUBY_MEDIUM, configuredFeatures.getOrThrow(ModOreFeatures.ORE_RUBY_MEDIUM),
                ModOrePlacements.commonOrePlacement(2, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-4))));
        register(context, ORE_RUBY_LARGE, configuredFeatures.getOrThrow(ModOreFeatures.ORE_RUBY_LARGE),
                ModOrePlacements.rareOrePlacement(9, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.absolute(80))));
        register(context, ORE_RUBY_BURIED, configuredFeatures.getOrThrow(ModOreFeatures.ORE_RUBY_BURIED),
                ModOrePlacements.commonOrePlacement(4, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.absolute(80))));
        register(context, ORE_AMBER_SMALL, configuredFeatures.getOrThrow(ModOreFeatures.ORE_AMBER_SMALL),
                ModOrePlacements.commonOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.absolute(80))));
        register(context, ORE_AMBER_MEDIUM, configuredFeatures.getOrThrow(ModOreFeatures.ORE_AMBER_MEDIUM),
                ModOrePlacements.commonOrePlacement(2, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-4))));
        register(context, ORE_AMBER_LARGE, configuredFeatures.getOrThrow(ModOreFeatures.ORE_AMBER_LARGE),
                ModOrePlacements.rareOrePlacement(9, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.absolute(80))));
        register(context, ORE_AMBER_BURIED, configuredFeatures.getOrThrow(ModOreFeatures.ORE_AMBER_BURIED),
                ModOrePlacements.commonOrePlacement(4, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.absolute(80))));

    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    public static List<PlacementModifier> orePlacement(PlacementModifier pCountPlacement, PlacementModifier pHeightRange) {
        return List.of(pCountPlacement, InSquarePlacement.spread(), pHeightRange, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int pCount, PlacementModifier pHeightRange) {
        return orePlacement(CountPlacement.of(pCount), pHeightRange);
    }

    public static List<PlacementModifier> rareOrePlacement(int pChance, PlacementModifier pHeightRange) {
        return orePlacement(RarityFilter.onAverageOnceEvery(pChance), pHeightRange);
    }
}