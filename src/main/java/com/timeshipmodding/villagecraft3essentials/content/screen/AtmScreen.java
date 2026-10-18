package com.timeshipmodding.villagecraft3essentials.content.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.content.item.registries.ModItems;
import com.timeshipmodding.villagecraft3essentials.content.menu.AtmMenu;
import com.timeshipmodding.villagecraft3essentials.infrastructure.networking.packet.atm.AtmSwitchTabPacket;
import com.timeshipmodding.villagecraft3essentials.infrastructure.networking.packet.atm.button.AtmRandomConvertButtonPressedPacket;
import com.timeshipmodding.villagecraft3essentials.infrastructure.networking.packet.atm.button.AtmToolConvertButtonPressedPacket;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.clientdata.AtmClientData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;

public class AtmScreen extends AbstractContainerScreen<AtmMenu> {
    private static final WidgetSprites CURRENCY_SELECT_SPRITES = new WidgetSprites(
            ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID,"widget/toolconvert_button"),
            ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID,"widget/toolconvert_button_highlighted")
    );

    private static final WidgetSprites CURRENCY_CONVERT_SPRITES = new WidgetSprites(
            ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID,"widget/randomconvert_button"),
            ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID,"widget/randomconvert_button_highlighted")
    );

    private static final WidgetSprites PREVIOUS_MENU_SPRITES = new WidgetSprites(
            ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID,"widget/previous_menu_button"),
            ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID,"widget/previous_menu_button_highlighted")
    );

    private static final WidgetSprites NEXT_MENU_SPRITES = new WidgetSprites(
            ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID,"widget/next_menu_button"),
            ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID,"widget/next_menu_button_highlighted")
    );

    private static final ResourceLocation SCROLLER_SPRITE = ResourceLocation.withDefaultNamespace("container/stonecutter/scroller");
    private static final int SCROLLER_WIDTH = 12;
    private static final int SCROLLER_HEIGHT = 15;
    private static final int RECIPES_COLUMNS = 1;
    private static final int RECIPES_ROWS = 4;
    private static final int RECIPES_IMAGE_SIZE_WIDTH = 63;
    private static final int RECIPES_IMAGE_SIZE_HEIGHT = 18;
    private static final int SCROLLER_FULL_HEIGHT = 54;
    private static final int RECIPES_X = 66;
    private static final int RECIPES_Y = 21;
    private float scrollOffs;
    private boolean scrolling;
    private int startIndex;
    private final ImageButton[] randomConvertButtons = new ImageButton[20];
    private ImageButton toolConvertButtonDiamond;
    private ImageButton toolConvertButtonJade;
    private ImageButton toolConvertButtonAmber;
    private ImageButton toolConvertButtonSilicon;
    private ImageButton toolConvertButtonRuby;
    private ImageButton nextMenuButton;
    private ImageButton previousMenuButton;
    private boolean localRandomConvertScreen;
    private int[] diamondToJade;
    private int[] diamondToAmber;
    private int[] diamondToSilicon;
    private int[] diamondToRuby;
    private int[] jadeToDiamond;
    private int[] jadeToAmber;
    private int[] jadeToSilicon;
    private int[] jadeToRuby;
    private int[] amberToDiamond;
    private int[] amberToJade;
    private int[] amberToSilicon;
    private int[] amberToRuby;
    private int[] siliconToDiamond;
    private int[] siliconToJade;
    private int[] siliconToAmber;
    private int[] siliconToRuby;
    private int[] rubyToDiamond;
    private int[] rubyToJade;
    private int[] rubyToAmber;
    private int[] rubyToSilicon;

    public AtmScreen(AtmMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
    }

    @Override
    protected void init() {
        super.init();
        this.imageHeight = 202;
        this.imageWidth = 175;
        this.inventoryLabelY = this.imageHeight - 92;
        this.titleLabelY = 5;
        this.titleLabelX = 8;
        this.diamondToJade = AtmClientData.getDiamondToJade() != null ? AtmClientData.getDiamondToJade() : new int[]{};
        this.diamondToAmber = AtmClientData.getDiamondToAmber() != null ? AtmClientData.getDiamondToAmber() : new int[]{};
        this.diamondToSilicon = AtmClientData.getDiamondToSilicon() != null ? AtmClientData.getDiamondToSilicon() : new int[]{};
        this.diamondToRuby = AtmClientData.getDiamondToRuby() != null ? AtmClientData.getDiamondToRuby() : new int[]{};
        this.jadeToDiamond = AtmClientData.getJadeToDiamond() != null ? AtmClientData.getJadeToDiamond() : new int[]{};
        this.jadeToAmber = AtmClientData.getJadeToAmber() != null ? AtmClientData.getJadeToAmber() : new int[]{};
        this.jadeToSilicon = AtmClientData.getJadeToSilicon() != null ? AtmClientData.getJadeToSilicon() : new int[]{};
        this.jadeToRuby = AtmClientData.getJadeToRuby() != null ? AtmClientData.getJadeToRuby() : new int[]{};
        this.amberToDiamond = AtmClientData.getAmberToDiamond() != null ? AtmClientData.getAmberToDiamond() : new int[]{};
        this.amberToJade = AtmClientData.getAmberToJade() != null ? AtmClientData.getAmberToJade() : new int[]{};
        this.amberToSilicon = AtmClientData.getAmberToSilicon() != null ? AtmClientData.getAmberToSilicon() : new int[]{};
        this.amberToRuby = AtmClientData.getAmberToRuby() != null ? AtmClientData.getAmberToRuby() : new int[]{};
        this.siliconToDiamond = AtmClientData.getSiliconToDiamond() != null ? AtmClientData.getSiliconToDiamond() : new int[]{};
        this.siliconToJade = AtmClientData.getSiliconToJade() != null ? AtmClientData.getSiliconToJade() : new int[]{};
        this.siliconToAmber = AtmClientData.getSiliconToAmber() != null ? AtmClientData.getSiliconToAmber() : new int[]{};
        this.siliconToRuby = AtmClientData.getSiliconToRuby() != null ? AtmClientData.getSiliconToRuby() : new int[]{};
        this.rubyToDiamond = AtmClientData.getRubyToDiamond() != null ? AtmClientData.getRubyToDiamond() : new int[]{};
        this.rubyToJade = AtmClientData.getRubyToJade() != null ? AtmClientData.getRubyToJade() : new int[]{};
        this.rubyToAmber = AtmClientData.getRubyToAmber() != null ? AtmClientData.getRubyToAmber() : new int[]{};
        this.rubyToSilicon = AtmClientData.getRubyToSilicon() != null ? AtmClientData.getRubyToSilicon() : new int[]{};
        this.localRandomConvertScreen = this.menu.blockEntity.getRandomConvertScreen();
        rebuildButtons();
    }

    private void onDiamondCurrencyButtonPress(Button button) {
        this.menu.playAtmSound();

        if (Minecraft.getInstance().getConnection() != null) {
            PacketDistributor.sendToServer(new AtmToolConvertButtonPressedPacket(menu.blockEntity.getBlockPos(), 1));
        }
    }

    private void onJadeCurrencyButtonPress(Button button) {
        this.menu.playAtmSound();

        if (Minecraft.getInstance().getConnection() != null) {
            PacketDistributor.sendToServer(new AtmToolConvertButtonPressedPacket(menu.blockEntity.getBlockPos(), 2));
        }
    }

    private void onAmberCurrencyButtonPress(Button button) {
        this.menu.playAtmSound();

        if (Minecraft.getInstance().getConnection() != null) {
            PacketDistributor.sendToServer(new AtmToolConvertButtonPressedPacket(menu.blockEntity.getBlockPos(), 3));
        }
    }

    private void onSiliconCurrencyButtonPress(Button button) {
        this.menu.playAtmSound();

        if (Minecraft.getInstance().getConnection() != null) {
            PacketDistributor.sendToServer(new AtmToolConvertButtonPressedPacket(menu.blockEntity.getBlockPos(), 4));
        }
    }

    private void onRubyCurrencyButtonPress(Button button) {
        this.menu.playAtmSound();

        if (Minecraft.getInstance().getConnection() != null) {
            PacketDistributor.sendToServer(new AtmToolConvertButtonPressedPacket(menu.blockEntity.getBlockPos(), 5));
        }
    }

    private void onPreviousButtonPress(Button button) {
        if (Minecraft.getInstance().getConnection() != null) {
            PacketDistributor.sendToServer(new AtmSwitchTabPacket(menu.blockEntity.getBlockPos(), true));
        }
    }

    private void onNextButtonPress(Button button) {
        if (Minecraft.getInstance().getConnection() != null) {
            PacketDistributor.sendToServer(new AtmSwitchTabPacket(menu.blockEntity.getBlockPos(), false));
        }
    }

    private void onRandomConvertButtonPress(Button button) {
        for (int i = 0; i < randomConvertButtons.length; i++) {
            if (randomConvertButtons[i] == button) {
                this.menu.playAtmSound();

                if (Minecraft.getInstance().getConnection() != null) {
                    PacketDistributor.sendToServer(new AtmRandomConvertButtonPressedPacket(menu.blockEntity.getBlockPos(), i + 1));
                }

                break;
            }
        }
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int guiTextureIndex = this.menu.blockEntity.getGuiTextureIndex();

        if (localRandomConvertScreen) {
            guiGraphics.blit(getRandomConvertGuiTexture(guiTextureIndex), leftPos, topPos, 0, 0, imageWidth, imageHeight);
            int k = (int)(59.0F * this.scrollOffs);
            guiGraphics.blitSprite(SCROLLER_SPRITE, leftPos + 133, topPos + 22 + k, SCROLLER_WIDTH, SCROLLER_HEIGHT);
            int l = this.leftPos + RECIPES_X;
            int i1 = this.topPos + RECIPES_Y;
            int j1 = this.startIndex + 4;
            this.renderRandomConvertButtonItems(guiGraphics, l, i1, j1);

        } else {
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            guiGraphics.blit(getToolConvertGuiTexture(guiTextureIndex), leftPos, topPos, 0, 0, imageWidth, imageHeight);
            guiGraphics.renderFakeItem(new ItemStack(Items.DIAMOND), leftPos + 26, topPos + 66);
            guiGraphics.renderFakeItem(new ItemStack(ModItems.JADE.get()), leftPos + 53, topPos + 66);
            guiGraphics.renderFakeItem(new ItemStack(ModItems.AMBER.get()), leftPos + 80, topPos + 66);
            guiGraphics.renderFakeItem(new ItemStack(ModItems.SILICON.get()), leftPos + 107, topPos + 66);
            guiGraphics.renderFakeItem(new ItemStack(ModItems.RUBY.get()), leftPos + 134, topPos + 66);
        }
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        if (this.menu.blockEntity.getRandomConvertScreen()) {
            renderBackground(guiGraphics, mouseX, mouseY, delta);
            super.render(guiGraphics, mouseX, mouseY, delta);
            renderTooltip(guiGraphics, mouseX, mouseY);

        } else if (this.menu.blockEntity.getToolConvertScreen()) {
            renderBackground(guiGraphics, mouseX, mouseY, delta);
            super.render(guiGraphics, mouseX, mouseY, delta);
            renderTooltip(guiGraphics, mouseX, mouseY);
        }
    }

    @Override
    protected void renderLabels(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        super.renderLabels(guiGraphics, mouseX, mouseY);
        guiGraphics.drawString(this.font, menu.blockEntity.getDisplayName(), 8, 5, 0x404040, false);
    }

    @Override
    protected void renderTooltip(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        super.renderTooltip(guiGraphics, mouseX, mouseY);

        if (this.menu.blockEntity.getRandomConvertScreen()) {
            int i = this.leftPos + RECIPES_X;
            int j = this.topPos + RECIPES_Y;
            int k = this.startIndex + 4;

            if (nextMenuButton != null && nextMenuButton.isHovered()) {
                guiGraphics.renderTooltip(this.font, Component.translatable("tooltips.villagecraft3essentials.atm.nextmenu"), mouseX, mouseY);
            }

            for (int l = this.startIndex; l < k && l < 20; l++) {
                int i1 = l - this.startIndex;
                int k1 = j + i1 / RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_HEIGHT + 2;

                if (mouseX >= i && mouseX < i + RECIPES_IMAGE_SIZE_WIDTH && mouseY >= k1 && mouseY < k1 + RECIPES_IMAGE_SIZE_HEIGHT) {
                    if (l >= 0 && l < randomConvertButtons.length && randomConvertButtons[l] != null) {
                        if (randomConvertButtons[l].isHovered()) {
                            Component tooltipText = switch (l) {
                                case 0 -> Component.translatable("tooltips.villagecraft3essentials.atm.randomconvert1", diamondToJade[0], diamondToJade[1]);
                                case 1 -> Component.translatable("tooltips.villagecraft3essentials.atm.randomconvert2", diamondToAmber[0], diamondToAmber[1]);
                                case 2 -> Component.translatable("tooltips.villagecraft3essentials.atm.randomconvert3", diamondToSilicon[0], diamondToSilicon[1]);
                                case 3 -> Component.translatable("tooltips.villagecraft3essentials.atm.randomconvert4", diamondToRuby[0], diamondToRuby[1]);
                                case 4 -> Component.translatable("tooltips.villagecraft3essentials.atm.randomconvert5", jadeToDiamond[0], jadeToDiamond[1]);
                                case 5 -> Component.translatable("tooltips.villagecraft3essentials.atm.randomconvert6", jadeToAmber[0], jadeToAmber[1]);
                                case 6 -> Component.translatable("tooltips.villagecraft3essentials.atm.randomconvert7", jadeToSilicon[0], jadeToSilicon[1]);
                                case 7 -> Component.translatable("tooltips.villagecraft3essentials.atm.randomconvert8", jadeToRuby[0], jadeToRuby[1]);
                                case 8 -> Component.translatable("tooltips.villagecraft3essentials.atm.randomconvert9", amberToDiamond[0], amberToDiamond[1]);
                                case 9 -> Component.translatable("tooltips.villagecraft3essentials.atm.randomconvert10", amberToJade[0], amberToJade[1]);
                                case 10 -> Component.translatable("tooltips.villagecraft3essentials.atm.randomconvert11", amberToSilicon[0], amberToSilicon[1]);
                                case 11 -> Component.translatable("tooltips.villagecraft3essentials.atm.randomconvert12", amberToRuby[0], amberToRuby[1]);
                                case 12 -> Component.translatable("tooltips.villagecraft3essentials.atm.randomconvert13", siliconToDiamond[0], siliconToDiamond[1]);
                                case 13 -> Component.translatable("tooltips.villagecraft3essentials.atm.randomconvert14", siliconToJade[0], siliconToJade[1]);
                                case 14 -> Component.translatable("tooltips.villagecraft3essentials.atm.randomconvert15", siliconToAmber[0], siliconToAmber[1]);
                                case 15 -> Component.translatable("tooltips.villagecraft3essentials.atm.randomconvert16", siliconToRuby[0], siliconToRuby[1]);
                                case 16 -> Component.translatable("tooltips.villagecraft3essentials.atm.randomconvert17", rubyToDiamond[0], rubyToDiamond[1]);
                                case 17 -> Component.translatable("tooltips.villagecraft3essentials.atm.randomconvert18", rubyToJade[0], rubyToJade[1]);
                                case 18 -> Component.translatable("tooltips.villagecraft3essentials.atm.randomconvert19", rubyToAmber[0], rubyToAmber[1]);
                                case 19 -> Component.translatable("tooltips.villagecraft3essentials.atm.randomconvert20", rubyToSilicon[0], rubyToSilicon[1]);
                                default -> throw new IllegalStateException("Unexpected value: " + l);
                            };

                            guiGraphics.renderTooltip(this.font, tooltipText, mouseX, mouseY);
                        }
                    }
                }
            }

        } else if (this.menu.blockEntity.getToolConvertScreen()) {
            if (toolConvertButtonDiamond != null) {
                if (toolConvertButtonDiamond.isHovered()) {
                    guiGraphics.renderTooltip(this.font, Component.translatable("tooltips.villagecraft3essentials.atm.toolconvertbuttondiamond"), mouseX, mouseY);

                } else if (toolConvertButtonJade.isHovered()) {
                    guiGraphics.renderTooltip(this.font, Component.translatable("tooltips.villagecraft3essentials.atm.toolconvertbuttonjade"), mouseX, mouseY);

                } else if (toolConvertButtonAmber.isHovered()) {
                    guiGraphics.renderTooltip(this.font, Component.translatable("tooltips.villagecraft3essentials.atm.toolconvertbuttonamber"), mouseX, mouseY);

                } else if (toolConvertButtonSilicon.isHovered()) {
                    guiGraphics.renderTooltip(this.font, Component.translatable("tooltips.villagecraft3essentials.atm.toolconvertbuttonsilicon"), mouseX, mouseY);

                } else if (toolConvertButtonRuby.isHovered()) {
                    guiGraphics.renderTooltip(this.font, Component.translatable("tooltips.villagecraft3essentials.atm.toolconvertbuttonruby"), mouseX, mouseY);

                } else if (previousMenuButton.isHovered()) {
                    guiGraphics.renderTooltip(this.font, Component.translatable("tooltips.villagecraft3essentials.atm.previousmenu"), mouseX, mouseY);
                }
            }
        }
    }

    @Override
    public void containerTick() {
        super.containerTick();

        boolean serverRandomConvertScreen = this.menu.blockEntity.getRandomConvertScreen();
        if (this.localRandomConvertScreen != serverRandomConvertScreen) {
            this.localRandomConvertScreen = serverRandomConvertScreen;
            rebuildButtons();
        }
    }

    private void rebuildButtons() {
        this.clearWidgets();
        System.out.println("rebuild");

        if (this.menu.blockEntity.getRandomConvertScreen()) {
            System.out.println("random");

            nextMenuButton = this.addRenderableWidget(new ImageButton(leftPos + 150, topPos + 92, 20, 18,
                    NEXT_MENU_SPRITES, this::onNextButtonPress, Component.empty()));

            updateScrollableWidgets();

        } else if (this.menu.blockEntity.getToolConvertScreen()) {
            System.out.println("tool");
            toolConvertButtonDiamond = this.addRenderableWidget(new ImageButton(leftPos + 24, topPos + 65, 20, 18,
                    CURRENCY_SELECT_SPRITES, this::onDiamondCurrencyButtonPress, Component.empty()));
            toolConvertButtonJade = this.addRenderableWidget(new ImageButton(leftPos + 51, topPos + 65, 20, 18,
                    CURRENCY_SELECT_SPRITES, this::onJadeCurrencyButtonPress, Component.empty()));
            toolConvertButtonAmber = this.addRenderableWidget(new ImageButton(leftPos + 78, topPos + 65, 20, 18,
                    CURRENCY_SELECT_SPRITES, this::onAmberCurrencyButtonPress, Component.empty()));
            toolConvertButtonSilicon = this.addRenderableWidget(new ImageButton(leftPos + 105, topPos + 65, 20, 18,
                    CURRENCY_SELECT_SPRITES, this::onSiliconCurrencyButtonPress, Component.empty()));
            toolConvertButtonRuby = this.addRenderableWidget(new ImageButton(leftPos + 132, topPos + 65, 20, 18,
                    CURRENCY_SELECT_SPRITES, this::onRubyCurrencyButtonPress, Component.empty()));
            previousMenuButton = this.addRenderableWidget(new ImageButton(leftPos + 150, topPos + 92, 20, 18,
                    PREVIOUS_MENU_SPRITES, this::onPreviousButtonPress, Component.empty()));
        }
    }

    private void updateScrollableWidgets() {
        if (!this.menu.blockEntity.getRandomConvertScreen()) return;

        for (int i = 0; i < randomConvertButtons.length; i++) {
            if (randomConvertButtons[i] != null) {
                this.removeWidget(randomConvertButtons[i]);
                randomConvertButtons[i] = null;
            }
        }

        int x = this.leftPos + RECIPES_X;
        int y = this.topPos + RECIPES_Y;
        int lastVisibleElementIndex = this.startIndex + 4;

        for (int i = this.startIndex; i < lastVisibleElementIndex && i < 20; i++) {
            int j = i - this.startIndex;
            int l = j / RECIPES_COLUMNS;
            int i1 = y + l * RECIPES_IMAGE_SIZE_HEIGHT + 2;

            this.randomConvertButtons[i] = this.addRenderableWidget(new ImageButton(x, i1 - 1, RECIPES_IMAGE_SIZE_WIDTH, RECIPES_IMAGE_SIZE_HEIGHT,
                    CURRENCY_CONVERT_SPRITES, this::onRandomConvertButtonPress, Component.empty()
            ));
        }
    }

    private void renderRandomConvertButtonItems(GuiGraphics guiGraphics, int x, int y, int startIndex) {
        for (int i = this.startIndex; i < startIndex && i < 20; i++) {
            int j = i - this.startIndex;
            int k = x + 2;
            int l = j / RECIPES_COLUMNS;
            int i1 = y + l * RECIPES_IMAGE_SIZE_HEIGHT + 2;

            switch (i) {
                case 0 -> renderRandomConvertButtonItemsHandler(guiGraphics, k, i1, Items.DIAMOND, ModItems.JADE.get(), diamondToJade[0], diamondToJade[1]);
                case 1 -> renderRandomConvertButtonItemsHandler(guiGraphics, k, i1, Items.DIAMOND, ModItems.AMBER.get(), diamondToAmber[0], diamondToAmber[1]);
                case 2 -> renderRandomConvertButtonItemsHandler(guiGraphics, k, i1, Items.DIAMOND, ModItems.SILICON.get(), diamondToSilicon[0], diamondToSilicon[1]);
                case 3 -> renderRandomConvertButtonItemsHandler(guiGraphics, k, i1, Items.DIAMOND, ModItems.RUBY.get(), diamondToRuby[0], diamondToRuby[1]);
                case 4 -> renderRandomConvertButtonItemsHandler(guiGraphics, k, i1, ModItems.JADE.get(), Items.DIAMOND, jadeToDiamond[0], jadeToDiamond[1]);
                case 5 -> renderRandomConvertButtonItemsHandler(guiGraphics, k, i1, ModItems.JADE.get(), ModItems.AMBER.get(), jadeToAmber[0], jadeToAmber[1]);
                case 6 -> renderRandomConvertButtonItemsHandler(guiGraphics, k, i1, ModItems.JADE.get(), ModItems.SILICON.get(), jadeToSilicon[0], jadeToSilicon[1]);
                case 7 -> renderRandomConvertButtonItemsHandler(guiGraphics, k, i1, ModItems.JADE.get(), ModItems.RUBY.get(), jadeToRuby[0], jadeToRuby[1]);
                case 8 -> renderRandomConvertButtonItemsHandler(guiGraphics, k, i1, ModItems.AMBER.get(), Items.DIAMOND, amberToDiamond[0], amberToDiamond[1]);
                case 9 -> renderRandomConvertButtonItemsHandler(guiGraphics, k, i1, ModItems.AMBER.get(), ModItems.JADE.get(), amberToJade[0], amberToJade[1]);
                case 10 -> renderRandomConvertButtonItemsHandler(guiGraphics, k, i1, ModItems.AMBER.get(), ModItems.SILICON.get(), amberToSilicon[0], amberToSilicon[1]);
                case 11 -> renderRandomConvertButtonItemsHandler(guiGraphics, k, i1, ModItems.AMBER.get(), ModItems.RUBY.get(), amberToRuby[0], amberToRuby[1]);
                case 12 -> renderRandomConvertButtonItemsHandler(guiGraphics, k, i1, ModItems.SILICON.get(), Items.DIAMOND, siliconToDiamond[0], siliconToDiamond[1]);
                case 13 -> renderRandomConvertButtonItemsHandler(guiGraphics, k, i1, ModItems.SILICON.get(), ModItems.JADE.get(), siliconToJade[0], siliconToJade[1]);
                case 14 -> renderRandomConvertButtonItemsHandler(guiGraphics, k, i1, ModItems.SILICON.get(), ModItems.AMBER.get(), siliconToAmber[0], siliconToAmber[1]);
                case 15 -> renderRandomConvertButtonItemsHandler(guiGraphics, k, i1, ModItems.SILICON.get(), ModItems.RUBY.get(), siliconToRuby[0], siliconToRuby[1]);
                case 16 -> renderRandomConvertButtonItemsHandler(guiGraphics, k, i1, ModItems.RUBY.get(), Items.DIAMOND, rubyToDiamond[0], rubyToDiamond[1]);
                case 17 -> renderRandomConvertButtonItemsHandler(guiGraphics, k, i1, ModItems.RUBY.get(), ModItems.JADE.get(), rubyToJade[0], rubyToJade[1]);
                case 18 -> renderRandomConvertButtonItemsHandler(guiGraphics, k, i1, ModItems.RUBY.get(), ModItems.AMBER.get(), rubyToAmber[0], rubyToAmber[1]);
                case 19 -> renderRandomConvertButtonItemsHandler(guiGraphics, k, i1, ModItems.RUBY.get(), ModItems.SILICON.get(), rubyToSilicon[0], rubyToSilicon[1]);
            }
        }
    }

    private void renderRandomConvertButtonItemsHandler(GuiGraphics guiGraphics, int k, int i1, Item currency1, Item currency2, int currency1Count, int current2Count) {
        ItemStack currency1Stack = new ItemStack(currency1, currency1Count);
        ItemStack currency2Stack = new ItemStack(currency2, current2Count);
        guiGraphics.renderFakeItem(currency1Stack, k, i1);
        guiGraphics.renderFakeItem(currency2Stack, k + 42, i1);
        guiGraphics.renderItemDecorations(this.font, currency1Stack, k, i1);
        guiGraphics.renderItemDecorations(this.font, currency2Stack, k + 42, i1);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        this.scrolling = false;
        int i = this.leftPos + RECIPES_X;
        int j = this.topPos + RECIPES_Y;
        int k = this.startIndex + 4;

        for (int l = this.startIndex; l < k; l++) {
            int i1 = l - this.startIndex;
            double d0 = mouseX - (double)(i);
            double d1 = mouseY - (double)(j + i1 / RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_HEIGHT);

            if (d0 >= 0.0 && d1 >= 0.0 && d0 < RECIPES_IMAGE_SIZE_WIDTH && d1 < RECIPES_IMAGE_SIZE_HEIGHT && this.menu.clickMenuButton(this.minecraft.player, l)) {
                Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0F));
                this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, l);
                return true;
            }
        }

        i = this.leftPos + 133;
        j = this.topPos + 22;

        if (mouseX >= (double)i && mouseX < (double)(i + SCROLLER_WIDTH) && mouseY >= (double)j && mouseY < (double)(j + SCROLLER_FULL_HEIGHT)) {
            this.scrolling = true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (this.scrolling) {
            int i = this.topPos + RECIPES_Y;
            int j = i + SCROLLER_FULL_HEIGHT;
            this.scrollOffs = ((float)mouseY - (float)i - 7.5F) / ((float)(j - i) - 15.0F);
            this.scrollOffs = Mth.clamp(this.scrollOffs, 0.0F, 1.0F);
            this.startIndex = (int)((double)(this.scrollOffs * (float)this.getOffscreenRows()) + 0.5) * RECIPES_COLUMNS;
            updateScrollableWidgets();
            return true;

        }

        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        int i = this.getOffscreenRows();
        float f = (float)scrollY / (float)i;
        this.scrollOffs = Mth.clamp(this.scrollOffs - f, 0.0F, 1.0F);
        this.startIndex = (int)((double)(this.scrollOffs * (float)i) + 0.5) * RECIPES_COLUMNS;
        updateScrollableWidgets();
        return true;
    }

    protected int getOffscreenRows() {
        return (20 + RECIPES_COLUMNS - 1) / RECIPES_COLUMNS - RECIPES_ROWS;
    }

    public void setConversionRates(int[] diamondToJade, int[] diamondToAmber, int[] diamondToSilicon, int[] diamondToRuby, int[] jadeToDiamond, int[] jadeToAmber, int[] jadeToSilicon, int[] jadeToRuby, int[] amberToDiamond, int[] amberToJade, int[] amberToSilicon, int[] amberToRuby, int[] siliconToDiamond, int[] siliconToJade, int[] siliconToAmber, int[] siliconToRuby, int[] rubyToDiamond, int[] rubyToJade, int[] rubyToAmber, int[] rubyToSilicon) {
        this.diamondToJade = diamondToJade;
        this.diamondToAmber = diamondToAmber;
        this.diamondToSilicon = diamondToSilicon;
        this.diamondToRuby = diamondToRuby;
        this.jadeToDiamond = jadeToDiamond;
        this.jadeToAmber = jadeToAmber;
        this.jadeToSilicon = jadeToSilicon;
        this.jadeToRuby = jadeToRuby;
        this.amberToDiamond = amberToDiamond;
        this.amberToJade = amberToJade;
        this.amberToSilicon = amberToSilicon;
        this.amberToRuby = amberToRuby;
        this.siliconToDiamond = siliconToDiamond;
        this.siliconToJade = siliconToJade;
        this.siliconToAmber = siliconToAmber;
        this.siliconToRuby = siliconToRuby;
        this.rubyToDiamond = rubyToDiamond;
        this.rubyToJade = rubyToJade;
        this.rubyToAmber = rubyToAmber;
        this.rubyToSilicon = rubyToSilicon;
    }

    private static @NotNull ResourceLocation getRandomConvertGuiTexture(int guiTextureIndex) {
        return switch (guiTextureIndex) {
            case 2 -> ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/blue_atm_randomconvert.png");
            case 3 -> ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/brown_atm_randomconvert.png");
            case 4 -> ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/cyan_atm_randomconvert.png");
            case 5 -> ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/gray_atm_randomconvert.png");
            case 6 -> ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/green_atm_randomconvert.png");
            case 7 -> ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/light_blue_atm_randomconvert.png");
            case 8 -> ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/light_gray_atm_randomconvert.png");
            case 9 -> ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/lime_atm_randomconvert.png");
            case 10 -> ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/magenta_atm_randomconvert.png");
            case 11 -> ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/orange_atm_randomconvert.png");
            case 12 -> ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/pink_atm_randomconvert.png");
            case 13 -> ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/purple_atm_randomconvert.png");
            case 14 -> ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/red_atm_randomconvert.png");
            case 15 -> ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/white_atm_randomconvert.png");
            case 16 -> ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/yellow_atm_randomconvert.png");
            default -> ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/black_atm_randomconvert.png");
        };
    }

    private static @NotNull ResourceLocation getToolConvertGuiTexture(int guiTextureIndex) {
        return switch (guiTextureIndex) {
            case 2 -> ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/blue_atm_toolconvert.png");
            case 3 -> ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/brown_atm_toolconvert.png");
            case 4 -> ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/cyan_atm_toolconvert.png");
            case 5 -> ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/gray_atm_toolconvert.png");
            case 6 -> ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/green_atm_toolconvert.png");
            case 7 -> ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/light_blue_atm_toolconvert.png");
            case 8 -> ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/light_gray_atm_toolconvert.png");
            case 9 -> ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/lime_atm_toolconvert.png");
            case 10 -> ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/magenta_atm_toolconvert.png");
            case 11 -> ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/orange_atm_toolconvert.png");
            case 12 -> ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/pink_atm_toolconvert.png");
            case 13 -> ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/purple_atm_toolconvert.png");
            case 14 -> ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/red_atm_toolconvert.png");
            case 15 -> ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/white_atm_toolconvert.png");
            case 16 -> ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/yellow_atm_toolconvert.png");
            default -> ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/black_atm_toolconvert.png");
        };
    }
}