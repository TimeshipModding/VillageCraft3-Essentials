package com.timeshipmodding.villagecraft3essentials.content.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.content.item.registries.ModItems;
import com.timeshipmodding.villagecraft3essentials.content.menu.AtmMenu;
import com.timeshipmodding.villagecraft3essentials.infrastructure.networking.packet.atm.*;
import com.timeshipmodding.villagecraft3essentials.infrastructure.networking.packet.atm.button.AtmRandomConvertButtonPressedPacket;
import com.timeshipmodding.villagecraft3essentials.infrastructure.networking.packet.atm.button.AtmRefreshSlotsPacket;
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
    private static final int RECIPES_ROWS = 3;
    private static final int RECIPES_IMAGE_SIZE_WIDTH = 63;
    private static final int RECIPES_IMAGE_SIZE_HEIGHT = 18;
    private static final int SCROLLER_FULL_HEIGHT = 54;
    private static final int RECIPES_X = 66;
    private static final int RECIPES_Y = 27;
    private float scrollOffs;
    private boolean scrolling;
    private int startIndex;
    private final ImageButton[] randomConvertButtons = new ImageButton[6];
    private ImageButton toolConvertButtonDiamond;
    private ImageButton toolConvertButtonRuby;
    private ImageButton toolConvertButtonAmber;
    private ImageButton nextMenuButton;
    private ImageButton previousMenuButton;

    private int[] diamondToRuby;
    private int[] diamondToAmber;
    private int[] rubyToDiamond;
    private int[] rubyToAmber;
    private int[] amberToDiamond;
    private int[] amberToRuby;

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
        this.diamondToRuby = AtmClientData.getDiamondToRuby() != null ? AtmClientData.getDiamondToRuby() : new int[]{};
        this.diamondToAmber = AtmClientData.getDiamondToAmber() != null ? AtmClientData.getDiamondToAmber() : new int[]{};
        this.rubyToDiamond = AtmClientData.getRubyToDiamond() != null ? AtmClientData.getRubyToDiamond() : new int[]{};
        this.rubyToAmber = AtmClientData.getRubyToAmber() != null ? AtmClientData.getRubyToAmber() : new int[]{};
        this.amberToDiamond = AtmClientData.getAmberToDiamond() != null ? AtmClientData.getAmberToDiamond() : new int[]{};
        this.amberToRuby = AtmClientData.getAmberToRuby() != null ? AtmClientData.getAmberToRuby() : new int[]{};
    }

    private void onDiamondCurrencyButtonPress(Button button) {
        this.menu.playAtmSound();

        if (Minecraft.getInstance().getConnection() != null) {
            PacketDistributor.sendToServer(new AtmToolConvertButtonPressedPacket(menu.blockEntity.getBlockPos(), 1));
        }
    }

    private void onRubyCurrencyButtonPress(Button button) {
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

    private void onPreviousButtonPress(Button button) {
        if (Minecraft.getInstance().getConnection() != null) {
            PacketDistributor.sendToServer(new AtmReturnItemPacket());
            PacketDistributor.sendToServer(new AtmRandomConvertScreenPacket(menu.blockEntity.getBlockPos(), true));
            PacketDistributor.sendToServer(new AtmToolConvertScreenPacket(menu.blockEntity.getBlockPos(), false));
            PacketDistributor.sendToServer(new AtmRefreshSlotsPacket(menu.blockEntity.getBlockPos()));
        }
    }

    private void onNextButtonPress(Button button) {
        if (Minecraft.getInstance().getConnection() != null) {
            PacketDistributor.sendToServer(new AtmReturnItemPacket());
            PacketDistributor.sendToServer(new AtmRandomConvertScreenPacket(menu.blockEntity.getBlockPos(), false));
            PacketDistributor.sendToServer(new AtmToolConvertScreenPacket(menu.blockEntity.getBlockPos(), true));
            PacketDistributor.sendToServer(new AtmRefreshSlotsPacket(menu.blockEntity.getBlockPos()));
        }
    }

    private void onRandomConvertButtonPress(Button button) {
        if (randomConvertButtons[0] == button) {
            this.menu.playAtmSound();

            if (Minecraft.getInstance().getConnection() != null) {
                PacketDistributor.sendToServer(new AtmRandomConvertButtonPressedPacket(menu.blockEntity.getBlockPos(), 1));
            }

        } else if (randomConvertButtons[1] == button) {
            this.menu.playAtmSound();

            if (Minecraft.getInstance().getConnection() != null) {
                PacketDistributor.sendToServer(new AtmRandomConvertButtonPressedPacket(menu.blockEntity.getBlockPos(), 2));
            }

        } else if (randomConvertButtons[2] == button) {
            this.menu.playAtmSound();

            if (Minecraft.getInstance().getConnection() != null) {
                PacketDistributor.sendToServer(new AtmRandomConvertButtonPressedPacket(menu.blockEntity.getBlockPos(), 3));
            }

        } else if (randomConvertButtons[3] == button) {
            this.menu.playAtmSound();

            if (Minecraft.getInstance().getConnection() != null) {
                PacketDistributor.sendToServer(new AtmRandomConvertButtonPressedPacket(menu.blockEntity.getBlockPos(), 4));
            }

        } else if (randomConvertButtons[4] == button) {
            this.menu.playAtmSound();

            if (Minecraft.getInstance().getConnection() != null) {
                PacketDistributor.sendToServer(new AtmRandomConvertButtonPressedPacket(menu.blockEntity.getBlockPos(), 5));
            }

        } else if (randomConvertButtons[5] == button) {
            this.menu.playAtmSound();

            if (Minecraft.getInstance().getConnection() != null) {
                PacketDistributor.sendToServer(new AtmRandomConvertButtonPressedPacket(menu.blockEntity.getBlockPos(), 6));
            }
        }
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int guiTextureIndex = this.menu.blockEntity.getGuiTextureIndex();

        if (this.menu.blockEntity.getRandomConvertScreen()) {
            this.clearWidgets();
            guiGraphics.blit(getRandomConvertGuiTexture(guiTextureIndex), leftPos, topPos, 0, 0, imageWidth, imageHeight);

            nextMenuButton = this.addRenderableWidget(new ImageButton(leftPos + 141, topPos + 92, 20, 18,
                    NEXT_MENU_SPRITES, this::onNextButtonPress, Component.empty()));

            int k = (int)(41.0F * this.scrollOffs);
            guiGraphics.blitSprite(SCROLLER_SPRITE, leftPos + 133, topPos + 28 + k, SCROLLER_WIDTH, SCROLLER_HEIGHT);
            int l = this.leftPos + RECIPES_X;
            int i1 = this.topPos + RECIPES_Y;
            int j1 = this.startIndex + 3;
            this.renderRandomConvertButtons(guiGraphics, mouseX, mouseY, l, i1, j1);
            this.renderRandomConvertButtonItems(guiGraphics, l, i1, j1);

        } else if (this.menu.blockEntity.getToolConvertScreen()) {
            this.clearWidgets();
            ItemStack diamond = new ItemStack(Items.DIAMOND, 3);

            toolConvertButtonDiamond = this.addRenderableWidget(new ImageButton(leftPos + 51, topPos + 65, 20, 18,
                    CURRENCY_SELECT_SPRITES, this::onDiamondCurrencyButtonPress, Component.empty()));
            toolConvertButtonRuby = this.addRenderableWidget(new ImageButton(leftPos + 78, topPos + 65, 20, 18,
                    CURRENCY_SELECT_SPRITES, this::onRubyCurrencyButtonPress, Component.empty()));
            toolConvertButtonAmber = this.addRenderableWidget(new ImageButton(leftPos + 105, topPos + 65, 20, 18,
                    CURRENCY_SELECT_SPRITES, this::onAmberCurrencyButtonPress, Component.empty()));
            previousMenuButton = this.addRenderableWidget(new ImageButton(leftPos + 114, topPos + 92, 20, 18,
                    PREVIOUS_MENU_SPRITES, this::onPreviousButtonPress, Component.empty()));

            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            guiGraphics.blit(getToolConvertGuiTexture(guiTextureIndex), leftPos, topPos, 0, 0, imageWidth, imageHeight);
            guiGraphics.renderFakeItem(diamond, leftPos + 53, topPos + 66);
            guiGraphics.renderFakeItem(new ItemStack(ModItems.RUBY.get()), leftPos + 80, topPos + 66);
            guiGraphics.renderFakeItem(new ItemStack(ModItems.AMBER.get()), leftPos + 107, topPos + 66);
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
            int k = this.startIndex + 3;

            if (nextMenuButton.isHovered()) {
                guiGraphics.renderTooltip(this.font, Component.translatable("tooltips.villagecraft3essentials.atm.nextmenu"), mouseX, mouseY);
            }

            for (int l = this.startIndex; l < k && l < 6; l++) {
                int i1 = l - this.startIndex;
                int k1 = j + i1 / RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_HEIGHT + 2;
                if (mouseX >= i && mouseX < i + RECIPES_IMAGE_SIZE_WIDTH && mouseY >= k1 && mouseY < k1 + RECIPES_IMAGE_SIZE_HEIGHT) {
                    if (randomConvertButtons[l].isHovered()) {
                        Component tooltipText;
                        switch (l) {
                            case 0:
                                tooltipText = Component.translatable("tooltips.villagecraft3essentials.atm.randomconvert1", diamondToRuby[0], diamondToRuby[1]);
                                break;
                            case 1:
                                tooltipText = Component.translatable("tooltips.villagecraft3essentials.atm.randomconvert2", diamondToAmber[0], diamondToAmber[1]);
                                break;
                            case 2:
                                tooltipText = Component.translatable("tooltips.villagecraft3essentials.atm.randomconvert3", rubyToDiamond[0], rubyToDiamond[1]);
                                break;
                            case 3:
                                tooltipText = Component.translatable("tooltips.villagecraft3essentials.atm.randomconvert4", rubyToAmber[0], rubyToAmber[1]);
                                break;
                            case 4:
                                tooltipText = Component.translatable("tooltips.villagecraft3essentials.atm.randomconvert5", amberToDiamond[0], amberToDiamond[1]);
                                break;
                            case 5:
                                tooltipText = Component.translatable("tooltips.villagecraft3essentials.atm.randomconvert6", amberToRuby[0],amberToRuby[1]);
                                break;
                            default:
                                continue;
                        }

                        guiGraphics.renderTooltip(this.font, tooltipText, mouseX, mouseY);
                    }
                }
            }

        } else if (this.menu.blockEntity.getToolConvertScreen()) {
            if (toolConvertButtonDiamond.isHovered()) {
                guiGraphics.renderTooltip(this.font, Component.translatable("tooltips.villagecraft3essentials.atm.toolconvertbuttondiamond"), mouseX, mouseY);

            } else if (toolConvertButtonRuby.isHovered()) {
                guiGraphics.renderTooltip(this.font, Component.translatable("tooltips.villagecraft3essentials.atm.toolconvertbuttonruby"), mouseX, mouseY);

            } else if (toolConvertButtonAmber.isHovered()) {
                guiGraphics.renderTooltip(this.font, Component.translatable("tooltips.villagecraft3essentials.atm.toolconvertbuttonamber"), mouseX, mouseY);

            } else if (previousMenuButton.isHovered()) {
                guiGraphics.renderTooltip(this.font, Component.translatable("tooltips.villagecraft3essentials.atm.previousmenu"), mouseX, mouseY);
            }
        }
    }

    private void renderRandomConvertButtons(GuiGraphics guiGraphics, int mouseX, int mouseY, int x, int y, int lastVisibleElementIndex) {
        for (int i = this.startIndex; i < lastVisibleElementIndex && i < 6; i++) {
            int j = i - this.startIndex;
            int l = j / RECIPES_COLUMNS;
            int i1 = y + l * RECIPES_IMAGE_SIZE_HEIGHT + 2;

            this.randomConvertButtons[i] = this.addRenderableWidget(new ImageButton(x, i1 - 1, RECIPES_IMAGE_SIZE_WIDTH, RECIPES_IMAGE_SIZE_HEIGHT,
                    CURRENCY_CONVERT_SPRITES, this::onRandomConvertButtonPress, Component.empty()));
        }
    }

    private void renderRandomConvertButtonItems(GuiGraphics guiGraphics, int x, int y, int startIndex) {
        for (int i = this.startIndex; i < startIndex && i < 6; i++) {
            int j = i - this.startIndex;
            int k = x + 2;
            int l = j / RECIPES_COLUMNS;
            int i1 = y + l * RECIPES_IMAGE_SIZE_HEIGHT + 2;

            if (i == 0) {
                ItemStack diamond = new ItemStack(Items.DIAMOND, diamondToRuby[0]);
                ItemStack ruby = new ItemStack(ModItems.RUBY.get(), diamondToRuby[1]);
                guiGraphics.renderFakeItem(diamond, k, i1);
                guiGraphics.renderFakeItem(ruby, k + 42, i1);
                guiGraphics.renderItemDecorations(this.font, diamond, k, i1);
                guiGraphics.renderItemDecorations(this.font, ruby, k + 42, i1);

            } else if (i == 1) {
                ItemStack diamond = new ItemStack(Items.DIAMOND, diamondToAmber[0]);
                ItemStack amber = new ItemStack(ModItems.AMBER.get(), diamondToAmber[1]);
                guiGraphics.renderFakeItem(diamond, k, i1);
                guiGraphics.renderFakeItem(amber, k + 42, i1);
                guiGraphics.renderItemDecorations(this.font, diamond, k, i1);
                guiGraphics.renderItemDecorations(this.font, amber, k + 42, i1);

            } else if (i == 2) {
                ItemStack ruby = new ItemStack(ModItems.RUBY.get(), rubyToDiamond[0]);
                ItemStack diamond = new ItemStack(Items.DIAMOND, rubyToDiamond[1]);
                guiGraphics.renderFakeItem(ruby, k, i1);
                guiGraphics.renderFakeItem(diamond, k + 42, i1);
                guiGraphics.renderItemDecorations(this.font, ruby, k, i1);
                guiGraphics.renderItemDecorations(this.font, diamond, k + 42, i1);

            } else if (i == 3) {
                ItemStack ruby = new ItemStack(ModItems.RUBY.get(), rubyToAmber[0]);
                ItemStack amber = new ItemStack(ModItems.AMBER.get(), rubyToAmber[1]);
                guiGraphics.renderFakeItem(ruby, k, i1);
                guiGraphics.renderFakeItem(amber, k + 42, i1);
                guiGraphics.renderItemDecorations(this.font, ruby, k, i1);
                guiGraphics.renderItemDecorations(this.font, amber, k + 42, i1);

            } else if (i == 4) {
                ItemStack amber = new ItemStack(ModItems.AMBER.get(), amberToDiamond[0]);
                ItemStack diamond = new ItemStack(Items.DIAMOND, amberToDiamond[1]);
                guiGraphics.renderFakeItem(amber, k, i1);
                guiGraphics.renderFakeItem(diamond, k + 42, i1);
                guiGraphics.renderItemDecorations(this.font, amber, k, i1);
                guiGraphics.renderItemDecorations(this.font, diamond, k + 42, i1);

            } else if (i == 5) {
                ItemStack amber = new ItemStack(ModItems.AMBER.get(), amberToRuby[0]);
                ItemStack ruby = new ItemStack(ModItems.RUBY.get(), amberToRuby[1]);
                guiGraphics.renderFakeItem(amber, k, i1);
                guiGraphics.renderFakeItem(ruby, k + 42, i1);
                guiGraphics.renderItemDecorations(this.font, amber, k, i1);
                guiGraphics.renderItemDecorations(this.font, ruby, k + 42, i1);
            }
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        this.scrolling = false;
        int i = this.leftPos + RECIPES_X;
        int j = this.topPos + RECIPES_Y;
        int k = this.startIndex + 3;

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
            return true;

        } else {
            return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
        }
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        int i = this.getOffscreenRows();
        float f = (float)scrollY / (float)i;
        this.scrollOffs = Mth.clamp(this.scrollOffs - f, 0.0F, 1.0F);
        this.startIndex = (int)((double)(this.scrollOffs * (float)i) + 0.5) * RECIPES_COLUMNS;
        return true;
    }

    protected int getOffscreenRows() {
        return (6 + RECIPES_COLUMNS - 1) / RECIPES_COLUMNS - RECIPES_ROWS;
    }

    public void setConversionRates(int[] diamondToRuby, int[] diamondToAmber, int[] rubyToDiamond, int[] rubyToAmber, int[] amberToDiamond, int[] amberToRuby) {
        this.diamondToRuby = diamondToRuby;
        this.diamondToAmber = diamondToAmber;
        this.rubyToDiamond = rubyToDiamond;
        this.rubyToAmber = rubyToAmber;
        this.amberToDiamond = amberToDiamond;
        this.amberToRuby = amberToRuby;
    }

    private static @NotNull ResourceLocation getRandomConvertGuiTexture(int guiTextureIndex) {
        ResourceLocation GUI_TEXTURE;

        switch (guiTextureIndex) {
            default -> GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/black_atm_randomconvert.png");
            case 2 -> GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/blue_atm_randomconvert.png");
            case 3 -> GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/brown_atm_randomconvert.png");
            case 4 -> GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/cyan_atm_randomconvert.png");
            case 5 -> GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/gray_atm_randomconvert.png");
            case 6 -> GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/green_atm_randomconvert.png");
            case 7 -> GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/light_blue_atm_randomconvert.png");
            case 8 -> GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/light_gray_atm_randomconvert.png");
            case 9 -> GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/lime_atm_randomconvert.png");
            case 10 -> GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/magenta_atm_randomconvert.png");
            case 11 -> GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/orange_atm_randomconvert.png");
            case 12 -> GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/pink_atm_randomconvert.png");
            case 13 -> GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/purple_atm_randomconvert.png");
            case 14 -> GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/red_atm_randomconvert.png");
            case 15 -> GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/white_atm_randomconvert.png");
            case 16 -> GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/yellow_atm_randomconvert.png");
        }
        return GUI_TEXTURE;
    }


    private static @NotNull ResourceLocation getToolConvertGuiTexture(int guiTextureIndex) {
        ResourceLocation GUI_TEXTURE;

        switch (guiTextureIndex) {
            default -> GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/black_atm_toolconvert.png");
            case 2 -> GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/blue_atm_toolconvert.png");
            case 3 -> GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/brown_atm_toolconvert.png");
            case 4 -> GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/cyan_atm_toolconvert.png");
            case 5 -> GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/gray_atm_toolconvert.png");
            case 6 -> GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/green_atm_toolconvert.png");
            case 7 -> GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/light_blue_atm_toolconvert.png");
            case 8 -> GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/light_gray_atm_toolconvert.png");
            case 9 -> GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/lime_atm_toolconvert.png");
            case 10 -> GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/magenta_atm_toolconvert.png");
            case 11 -> GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/orange_atm_toolconvert.png");
            case 12 -> GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/pink_atm_toolconvert.png");
            case 13 -> GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/purple_atm_toolconvert.png");
            case 14 -> GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/red_atm_toolconvert.png");
            case 15 -> GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/white_atm_toolconvert.png");
            case 16 -> GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "textures/gui/container/atm/yellow_atm_toolconvert.png");
        }
        return GUI_TEXTURE;
    }
}