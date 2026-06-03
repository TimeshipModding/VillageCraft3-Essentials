package com.timeshipmodding.villagecraft3essentials.content.screen;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.content.menu.WalletMenu;
import com.timeshipmodding.villagecraft3essentials.event.registries.ModClientEvents;
import com.timeshipmodding.villagecraft3essentials.infrastructure.networking.packet.wallet.CloseWalletMenuPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;

public class WalletScreen extends EffectRenderingInventoryScreen<WalletMenu> implements RecipeUpdateListener {
    WidgetSprites WALLET_BUTTON_SPRITES = new WidgetSprites(
            ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "widget/wallet_button"),
            ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "widget/wallet_button_highlighted")
    );

    private final RecipeBookComponent recipeBookComponent = new RecipeBookComponent();
    private boolean widthTooNarrow;
    private static ImageButton inventoryButton;

    private static final ResourceLocation GUI_TEXTURE  = ResourceLocation.fromNamespaceAndPath("villagecraft3essentials", "textures/gui/container/wallet.png");
    private static final ResourceLocation WALLET_ICON = ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "wallet_icon");

    public WalletScreen(WalletMenu menu, Inventory inv, Component title) {
        super(menu, inv, Component.translatable("container.crafting"));
        this.imageWidth = 176;
        this.imageHeight = 166;
        this.titleLabelX = 97;
        this.inventoryLabelX = 10000;
        this.inventoryLabelY = 10000;
    }

    @Override
    public void containerTick() {
        this.recipeBookComponent.tick();
    }

    @Override
    protected void init() {
        super.init();
        this.widthTooNarrow = this.width < 379;
        this.recipeBookComponent.init(this.width, this.height, this.minecraft, this.widthTooNarrow, this.menu);
        this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);

        ImageButton recipeBookButton = this.addRenderableWidget(new ImageButton(this.leftPos + 104, this.topPos + 61, 20, 18, RecipeBookComponent.RECIPE_BUTTON_SPRITES, (button) -> {
            this.recipeBookComponent.toggleVisibility();
            this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
            button.setPosition(this.leftPos + 104, this.topPos + 61);
            inventoryButton.setPosition(this.leftPos + 132, this.topPos + 61);
        }));

        inventoryButton = this.addRenderableWidget(new ImageButton(this.getGuiLeft() + 132, this.getGuiTop() + 61, 20, 18, WALLET_BUTTON_SPRITES, (button) -> {
            Minecraft minecraft = Minecraft.getInstance();
            double currentMouseX = minecraft.mouseHandler.xpos();
            double currentMouseY = minecraft.mouseHandler.ypos();
            ModClientEvents.shouldRestore = true;
            PacketDistributor.sendToServer(new CloseWalletMenuPacket());
            this.onClose();
            minecraft.setScreen(new InventoryScreen(minecraft.player));
            GLFW.glfwSetCursorPos(minecraft.getWindow().getWindow(), currentMouseX, currentMouseY);
        }));

        this.addWidget(this.recipeBookComponent);
    }

    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 4210752, false);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);

        if (this.recipeBookComponent.isVisible() && !this.widthTooNarrow) {
            this.recipeBookComponent.render(graphics, mouseX, mouseY, partialTick);
        }

        graphics.blitSprite(WALLET_ICON, this.getGuiLeft() + 134, this.getGuiTop() + 62, 16, 16);
        this.recipeBookComponent.renderGhostRecipe(graphics, this.leftPos, this.topPos, false, partialTick);
        this.renderTooltip(graphics, mouseX, mouseY);
        this.recipeBookComponent.renderTooltip(graphics, this.leftPos, this.topPos, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        graphics.blit(GUI_TEXTURE, this.leftPos, this.topPos, 0, 0, 176, 166, 256, 256);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.recipeBookComponent.mouseClicked(mouseX, mouseY, button)) {
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void recipesUpdated() {
        this.recipeBookComponent.recipesUpdated();
    }

    @Override
    public RecipeBookComponent getRecipeBookComponent() {
        return this.recipeBookComponent;
    }
}