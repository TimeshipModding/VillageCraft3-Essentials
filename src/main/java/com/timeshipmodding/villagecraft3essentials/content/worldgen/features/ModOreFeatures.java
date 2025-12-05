package com.timeshipmodding.villagecraft3essentials.content.worldgen.features;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.content.block.registries.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModOreFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_RUBY_SMALL = registerKey("ore_ruby_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_RUBY_MEDIUM = registerKey("ore_ruby_medium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_RUBY_LARGE = registerKey("ore_ruby_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_RUBY_BURIED = registerKey("ore_ruby_buried");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_AMBER_SMALL = registerKey("ore_amber_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_AMBER_MEDIUM = registerKey("ore_amber_medium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_AMBER_LARGE = registerKey("ore_amber_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_AMBER_BURIED = registerKey("ore_amber_buried");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> rubyOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.RUBY_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_RUBY_ORE.get().defaultBlockState()));
        List<OreConfiguration.TargetBlockState> amberOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.AMBER_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_AMBER_ORE.get().defaultBlockState()));

        register(context, ORE_RUBY_SMALL, Feature.ORE, new OreConfiguration(rubyOres, 4, 0.5f));
        register(context, ORE_RUBY_MEDIUM, Feature.ORE, new OreConfiguration(rubyOres, 12, 0.7f));
        register(context, ORE_RUBY_LARGE, Feature.ORE, new OreConfiguration(rubyOres, 8, 1.0F));
        register(context, ORE_RUBY_BURIED, Feature.ORE, new OreConfiguration(rubyOres, 8,0.5f));
        register(context, ORE_AMBER_SMALL, Feature.ORE, new OreConfiguration(amberOres, 4, 0.5f));
        register(context, ORE_AMBER_MEDIUM, Feature.ORE, new OreConfiguration(amberOres, 12, 0.7f));
        register(context, ORE_AMBER_LARGE, Feature.ORE, new OreConfiguration(amberOres, 8, 1.0F));
        register(context, ORE_AMBER_BURIED, Feature.ORE, new OreConfiguration(amberOres, 8,0.5f));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, name));
    }
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}