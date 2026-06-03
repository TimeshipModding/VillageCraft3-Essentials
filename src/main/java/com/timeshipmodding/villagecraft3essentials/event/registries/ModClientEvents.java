package com.timeshipmodding.villagecraft3essentials.event.registries;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.compat.aquaculture.AquaMethods;
import com.timeshipmodding.villagecraft3essentials.content.entity.client.registries.ModEntities;
import com.timeshipmodding.villagecraft3essentials.content.entity.client.renderers.MoleRenderer;
import com.timeshipmodding.villagecraft3essentials.content.item.registries.ModCompatItems;
import com.timeshipmodding.villagecraft3essentials.content.item.registries.ModItems;
import com.timeshipmodding.villagecraft3essentials.content.menu.registries.ModMenus;
import com.timeshipmodding.villagecraft3essentials.content.screen.AtmScreen;
import com.timeshipmodding.villagecraft3essentials.content.screen.WalletScreen;
import com.timeshipmodding.villagecraft3essentials.infrastructure.mixin.MouseHandlerMixinAccessor;
import com.timeshipmodding.villagecraft3essentials.infrastructure.networking.packet.wallet.OpenWalletMenuPacket;
import com.timeshipmodding.villagecraft3essentials.infrastructure.tags.registries.ModItemTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = VillageCraft3Essentials.MODID, value = Dist.CLIENT)
public class ModClientEvents {
    private static ImageButton walletButton;
    public static double savedX;
    public static double savedY;
    public static boolean shouldRestore = false;

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(ModEntities.MOLE.get(), MoleRenderer::new);

        if (ModList.get().isLoaded("aquaculture")) {
            AquaMethods.registerAquaFishingRodModelProperties(ModCompatItems.RUBY_FISHING_ROD.get());
            AquaMethods.registerAquaFishingRodModelProperties(ModCompatItems.AMBER_FISHING_ROD.get());
        }
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenus.ATM_MENU.get(), AtmScreen::new);
        event.register(ModMenus.WALLET_MENU.get(), WalletScreen::new);
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

    @SubscribeEvent
    public static void onScreenInit(ScreenEvent.Init.Post event) {
        if (event.getScreen() instanceof InventoryScreen inventoryScreen) {
            WidgetSprites WALLET__BUTTON_SPRITES = new WidgetSprites(
                    ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "widget/wallet_button"),
                    ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "widget/wallet_button_highlighted")
            );

            walletButton = new ImageButton(inventoryScreen.getGuiLeft() + 132, inventoryScreen.getGuiTop() + 61, 20, 18, WALLET__BUTTON_SPRITES, (button) -> {
                PacketDistributor.sendToServer(new OpenWalletMenuPacket());
            });

            event.addListener(walletButton);

        }

        if (shouldRestore && event.getScreen() instanceof InventoryScreen) {
            Minecraft minecraft = Minecraft.getInstance();
            org.lwjgl.glfw.GLFW.glfwSetCursorPos(minecraft.getWindow().getWindow(), savedX, savedY);
            MouseHandlerMixinAccessor accessor = (MouseHandlerMixinAccessor) minecraft.mouseHandler;
            accessor.setXpos(savedX);
            accessor.setYpos(savedY);

            shouldRestore = false;
        }
    }

    @SubscribeEvent
    public static void onScreenRender(ScreenEvent.Render.Post event) {
        if (event.getScreen() instanceof InventoryScreen inventoryScreen) {
            GuiGraphics graphics = event.getGuiGraphics();
            graphics.blitSprite(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "wallet_icon"), inventoryScreen.getGuiLeft() + 134, inventoryScreen.getGuiTop() + 62, 16, 16);
            walletButton.setX(inventoryScreen.getGuiLeft() + 132);
        }
    }
}