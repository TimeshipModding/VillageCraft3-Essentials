package com.timeshipmodding.villagecraft3essentials.compat.creategoggles.event.registries;

import com.simibubi.create.api.stress.BlockStressValues;
import com.simibubi.create.content.equipment.armor.BacktankRenderer;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipModifier;
import dev.engine_room.flywheel.lib.visualization.SimpleBlockEntityVisualizer;
import net.createmod.catnip.lang.FontHelper;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import static com.timeshipmodding.villagecraft3essentials.compat.creategoggles.registries.ModCGRegistry.*;

public class ModCGEvents {
    public static void registerEvents(IEventBus modEventBus) {
        modEventBus.addListener(ModCGEvents::registerEntityRenderers);
        modEventBus.addListener(ModCGEvents::onClientSetup);
    }

    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(JADE_BACKTANK_BLOCKENTITY.get(), BacktankRenderer::new);
        event.registerBlockEntityRenderer(AMBER_BACKTANK_BLOCKENTITY.get(), BacktankRenderer::new);
        event.registerBlockEntityRenderer(SILICON_BACKTANK_BLOCKENTITY.get(), BacktankRenderer::new);
        event.registerBlockEntityRenderer(RUBY_BACKTANK_BLOCKENTITY.get(), BacktankRenderer::new);
    }

    private static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            SimpleBlockEntityVisualizer.builder(JADE_BACKTANK_BLOCKENTITY.get()).factory(SingleAxisRotatingVisual::backtank).skipVanillaRender(be -> false).apply();
            SimpleBlockEntityVisualizer.builder(AMBER_BACKTANK_BLOCKENTITY.get()).factory(SingleAxisRotatingVisual::backtank).skipVanillaRender(be -> false).apply();
            SimpleBlockEntityVisualizer.builder(SILICON_BACKTANK_BLOCKENTITY.get()).factory(SingleAxisRotatingVisual::backtank).skipVanillaRender(be -> false).apply();
            SimpleBlockEntityVisualizer.builder(RUBY_BACKTANK_BLOCKENTITY.get()).factory(SingleAxisRotatingVisual::backtank).skipVanillaRender(be -> false).apply();
            BlockStressValues.IMPACTS.register(JADE_BACKTANK_BLOCK.get(), () -> 4.0);
            BlockStressValues.IMPACTS.register(AMBER_BACKTANK_BLOCK.get(), () -> 4.0);
            BlockStressValues.IMPACTS.register(SILICON_BACKTANK_BLOCK.get(), () -> 4.0);
            BlockStressValues.IMPACTS.register(RUBY_BACKTANK_BLOCK.get(), () -> 4.0);
            TooltipModifier.REGISTRY.register(JADE_BACKTANK.get(), new ItemDescription.Modifier(JADE_BACKTANK.get(), FontHelper.Palette.STANDARD_CREATE).andThen(TooltipModifier.mapNull(KineticStats.create(JADE_BACKTANK.get()))));
            TooltipModifier.REGISTRY.register(AMBER_BACKTANK.get(), new ItemDescription.Modifier(AMBER_BACKTANK.get(), FontHelper.Palette.STANDARD_CREATE).andThen(TooltipModifier.mapNull(KineticStats.create(AMBER_BACKTANK.get()))));
            TooltipModifier.REGISTRY.register(SILICON_BACKTANK.get(), new ItemDescription.Modifier(SILICON_BACKTANK.get(), FontHelper.Palette.STANDARD_CREATE).andThen(TooltipModifier.mapNull(KineticStats.create(SILICON_BACKTANK.get()))));
            TooltipModifier.REGISTRY.register(RUBY_BACKTANK.get(), new ItemDescription.Modifier(RUBY_BACKTANK.get(), FontHelper.Palette.STANDARD_CREATE).andThen(TooltipModifier.mapNull(KineticStats.create(RUBY_BACKTANK.get()))));
        });
    }
}