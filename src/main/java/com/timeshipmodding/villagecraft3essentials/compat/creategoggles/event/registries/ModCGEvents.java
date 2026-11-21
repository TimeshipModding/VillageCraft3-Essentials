package com.timeshipmodding.villagecraft3essentials.compat.creategoggles.event.registries;

import com.simibubi.create.api.stress.BlockStressValues;
import com.simibubi.create.content.equipment.armor.BacktankRenderer;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipModifier;
import com.timeshipmodding.villagecraft3essentials.compat.creategoggles.registries.ModCGRegistry;
import dev.engine_room.flywheel.lib.visualization.SimpleBlockEntityVisualizer;
import net.createmod.catnip.lang.FontHelper;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class ModCGEvents {
    public static void registerEvents(IEventBus modEventBus) {
        modEventBus.addListener(ModCGEvents::registerEntityRenderers);
        modEventBus.addListener(ModCGEvents::onClientSetup);
    }

    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModCGRegistry.JADE_BACKTANK_BLOCKENTITY.get(), BacktankRenderer::new);
        event.registerBlockEntityRenderer(ModCGRegistry.AMBER_BACKTANK_BLOCKENTITY.get(), BacktankRenderer::new);
        event.registerBlockEntityRenderer(ModCGRegistry.SILICON_BACKTANK_BLOCKENTITY.get(), BacktankRenderer::new);
        event.registerBlockEntityRenderer(ModCGRegistry.RUBY_BACKTANK_BLOCKENTITY.get(), BacktankRenderer::new);
    }

    private static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            SimpleBlockEntityVisualizer.builder(ModCGRegistry.JADE_BACKTANK_BLOCKENTITY.get()).factory(SingleAxisRotatingVisual::backtank).skipVanillaRender(be -> false).apply();
            SimpleBlockEntityVisualizer.builder(ModCGRegistry.AMBER_BACKTANK_BLOCKENTITY.get()).factory(SingleAxisRotatingVisual::backtank).skipVanillaRender(be -> false).apply();
            SimpleBlockEntityVisualizer.builder(ModCGRegistry.SILICON_BACKTANK_BLOCKENTITY.get()).factory(SingleAxisRotatingVisual::backtank).skipVanillaRender(be -> false).apply();
            SimpleBlockEntityVisualizer.builder(ModCGRegistry.RUBY_BACKTANK_BLOCKENTITY.get()).factory(SingleAxisRotatingVisual::backtank).skipVanillaRender(be -> false).apply();
            BlockStressValues.IMPACTS.register(ModCGRegistry.JADE_BACKTANK_BLOCK.get(), () -> 4.0);
            BlockStressValues.IMPACTS.register(ModCGRegistry.AMBER_BACKTANK_BLOCK.get(), () -> 4.0);
            BlockStressValues.IMPACTS.register(ModCGRegistry.SILICON_BACKTANK_BLOCK.get(), () -> 4.0);
            BlockStressValues.IMPACTS.register(ModCGRegistry.RUBY_BACKTANK_BLOCK.get(), () -> 4.0);
            TooltipModifier.REGISTRY.register(ModCGRegistry.JADE_BACKTANK.get(), new ItemDescription.Modifier(ModCGRegistry.JADE_BACKTANK.get(), FontHelper.Palette.STANDARD_CREATE).andThen(TooltipModifier.mapNull(KineticStats.create(ModCGRegistry.JADE_BACKTANK.get()))));
            TooltipModifier.REGISTRY.register(ModCGRegistry.AMBER_BACKTANK.get(), new ItemDescription.Modifier(ModCGRegistry.AMBER_BACKTANK.get(), FontHelper.Palette.STANDARD_CREATE).andThen(TooltipModifier.mapNull(KineticStats.create(ModCGRegistry.AMBER_BACKTANK.get()))));
            TooltipModifier.REGISTRY.register(ModCGRegistry.SILICON_BACKTANK.get(), new ItemDescription.Modifier(ModCGRegistry.SILICON_BACKTANK.get(), FontHelper.Palette.STANDARD_CREATE).andThen(TooltipModifier.mapNull(KineticStats.create(ModCGRegistry.SILICON_BACKTANK.get()))));
            TooltipModifier.REGISTRY.register(ModCGRegistry.RUBY_BACKTANK.get(), new ItemDescription.Modifier(ModCGRegistry.RUBY_BACKTANK.get(), FontHelper.Palette.STANDARD_CREATE).andThen(TooltipModifier.mapNull(KineticStats.create(ModCGRegistry.RUBY_BACKTANK.get()))));
        });
    }
}