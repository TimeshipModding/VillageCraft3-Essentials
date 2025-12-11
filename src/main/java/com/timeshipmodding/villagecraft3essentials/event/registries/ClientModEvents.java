package com.timeshipmodding.villagecraft3essentials.event.registries;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.content.entity.client.registries.ModEntities;
import com.timeshipmodding.villagecraft3essentials.content.entity.client.renderers.MoleRenderer;
import com.timeshipmodding.villagecraft3essentials.content.menu.registries.ModMenus;
import com.timeshipmodding.villagecraft3essentials.content.screen.AtmScreen;
import com.timeshipmodding.villagecraft3essentials.util.tags.registries.ModItemTags;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

@EventBusSubscriber(modid = VillageCraft3Essentials.MODID, value = Dist.CLIENT)
public class ClientModEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(ModEntities.MOLE.get(), MoleRenderer::new);
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenus.ATM_MENU.get(), AtmScreen::new);
    }

    @SubscribeEvent
    public static void addItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (stack.is(ModItemTags.DIAMOND_CONVERTIBLE_TOOLS) || stack.is(ModItemTags.RUBY_CONVERTIBLE_TOOLS) || stack.is(ModItemTags.AMBER_CONVERTIBLE_TOOLS)) {
            if(Screen.hasShiftDown()) {
                if (stack.getItem() instanceof ArmorItem) {
                    event.getToolTip().add(Component.translatable("tooltip.villagecraft3essentials.armor_conversation_hint"));
                } else {
                    event.getToolTip().add(Component.translatable("tooltip.villagecraft3essentials.tool_conversation_hint"));
                }
            } else {
                event.getToolTip().add(Component.translatable("tooltip.villagecraft3essentials.press_shift"));
            }
        }
    }
}