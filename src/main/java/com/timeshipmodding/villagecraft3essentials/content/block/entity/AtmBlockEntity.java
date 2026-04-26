package com.timeshipmodding.villagecraft3essentials.content.block.entity;

import com.teammetallurgy.aquaculture.init.AquaItems;
import com.timeshipmodding.villagecraft3essentials.content.block.entity.registries.ModBlockEntities;
import com.timeshipmodding.villagecraft3essentials.content.item.registries.ModCompatItems;
import com.timeshipmodding.villagecraft3essentials.content.item.registries.ModItems;
import com.timeshipmodding.villagecraft3essentials.content.menu.AtmMenu;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.saveddata.AtmRandomConversionRatesSavedData;
import com.timeshipmodding.villagecraft3essentials.infrastructure.tags.registries.ModItemTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

public class AtmBlockEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler itemStackHandler = new ItemStackHandler(2) {
        protected void onContentsChanged(int slot) {
            if (slot == INPUT_SLOT) {
                updateOutput();
            }

            setChanged();
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 2);
        }
    };

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;
    private int guiTextureIndex = 0;
    private int convertAmount = 0;
    public boolean randomConvertScreen = false;
    public boolean toolConvertScreen = false;
    public int randomConvertButtonPressed = 0;
    public int toolConvertButtonPressed = 0;

    public AtmBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.ATM_BLOCKENTITY.get(), pos, blockState);
    }

    @Override
    public Component getDisplayName() {
        if (randomConvertScreen) {
            return Component.translatable("blockentity.villagecraft3essentials.atm.randomconvert");

        } else if (toolConvertScreen) {
            return Component.translatable("blockentity.villagecraft3essentials.atm.toolconvert");
        }

        return null;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new AtmMenu(containerId, playerInventory, this);
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        ItemStack randomConvertInputStack = getRandomConvertRecipe()[0];
        ItemStack randomConvertOutputStack = getRandomConvertRecipe()[1];
        ItemStack toolConvertInputStack = getToolConvertRecipe()[0];
        ItemStack toolConvertOutputStack = getToolConvertRecipe()[1];
        updateOutputWhenButtonChanged();

        if (randomConvertScreen && itemStackHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() && hasRecipe(randomConvertInputStack, randomConvertOutputStack)) {
            randomConvertCurrency(randomConvertOutputStack, randomConvertButtonPressed);

        } else if (toolConvertScreen && itemStackHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() && hasRecipe(toolConvertInputStack, toolConvertOutputStack)) {
            toolConvertItem(toolConvertOutputStack);
        }
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        guiTextureIndex = tag.getInt("guiTextureIndex");
        randomConvertScreen = tag.getBoolean("randomConvertScreen");
        toolConvertScreen = tag.getBoolean("toolConvertScreen");
        randomConvertButtonPressed = tag.getInt("randomConvertButtonPressed");
        toolConvertButtonPressed = tag.getInt("toolConvertButtonPressed");
        itemStackHandler.deserializeNBT(registries, tag.getCompound("inventory"));
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", itemStackHandler.serializeNBT(registries));
        tag.putInt("guiTextureIndex", guiTextureIndex);
        tag.putBoolean("randomConvertScreen", randomConvertScreen);
        tag.putBoolean("toolConvertScreen", toolConvertScreen);
        tag.putInt("randomConvertButtonPressed", randomConvertButtonPressed);
        tag.putInt("toolConvertButtonPressed", toolConvertButtonPressed);
        super.saveAdditional(tag, registries);
    }

    public boolean getRandomConvertScreen() {
        return randomConvertScreen;
    }

    public boolean getToolConvertScreen() {
        return toolConvertScreen;
    }

    public int getRandomConvertButtonPressed() {
        return randomConvertButtonPressed;
    }

    public int getGuiTextureIndex() {
        return guiTextureIndex;
    }

    public void setRandomConvertScreen(boolean value) {
        randomConvertScreen = value;
        setChanged();
    }

    public void setToolConvertScreen(boolean value) {
        toolConvertScreen = value;
        setChanged();
    }

    public void setRandomConvertButtonPressed(int value) {
        randomConvertButtonPressed = value;
        setChanged();
    }

    public void setToolConvertButtonPressed(int value) {
        toolConvertButtonPressed = value;
        setChanged();
    }

    public void setGuiTextureIndex(int guiTextureIndex) {
        this.guiTextureIndex = guiTextureIndex;
        setChanged();
    }

    private void randomConvertCurrency(ItemStack outputStack, int randomConvertButtonPressed) {
        convertAmount = getRandomConvertInputAmount(randomConvertButtonPressed);
        itemStackHandler.setStackInSlot(OUTPUT_SLOT, outputStack);
    }

    public int quickRandomConvertCurrency(ItemStack initialOutputStack, int randomConvertButtonPressed) {
        convertAmount = getRandomConvertInputAmount(randomConvertButtonPressed);
        ItemStack initialInputStack = itemStackHandler.getStackInSlot(INPUT_SLOT);
        int currentInputItemCount = initialInputStack.getCount();
        int maxConversions;
        int conversionRemainder;
        maxConversions = currentInputItemCount / convertAmount;
        conversionRemainder = currentInputItemCount % convertAmount;

        if (conversionRemainder == 0) {
            itemStackHandler.setStackInSlot(INPUT_SLOT, ItemStack.EMPTY);

        } else {
            initialInputStack.setCount(conversionRemainder);
            itemStackHandler.setStackInSlot(INPUT_SLOT, initialInputStack);
        }

        return initialOutputStack.getCount() * maxConversions;
    }

    private void toolConvertItem(ItemStack outputStack) {
        convertAmount = 1;
        DataComponentMap INPUT_ITEM_COMPONENTS = itemStackHandler.getStackInSlot(INPUT_SLOT).getComponents();
        itemStackHandler.setStackInSlot(OUTPUT_SLOT, outputStack);
        itemStackHandler.getStackInSlot(OUTPUT_SLOT).applyComponents(INPUT_ITEM_COMPONENTS);
    }

    public ItemStack[] getRandomConvertRecipe() {
        MinecraftServer server = this.level.getServer();
        Item inputItem = itemStackHandler.getStackInSlot(INPUT_SLOT).getItem();
        ItemStack inputStack = ItemStack.EMPTY;
        ItemStack outputStack = ItemStack.EMPTY;

        if (server != null) {
            if (randomConvertButtonPressed == 1) {
                if (inputItem == Items.DIAMOND && (itemStackHandler.getStackInSlot(INPUT_SLOT).getCount() >= AtmRandomConversionRatesSavedData.getData(server).getDiamondToRuby()[0])) {
                    inputStack = new ItemStack(Items.DIAMOND, AtmRandomConversionRatesSavedData.getData(server).getDiamondToRuby()[0]);
                    outputStack = new ItemStack(ModItems.RUBY.get(), AtmRandomConversionRatesSavedData.getData(server).getDiamondToRuby()[1]);
                }

            } else if (randomConvertButtonPressed == 2) {
                if (inputItem == Items.DIAMOND && (itemStackHandler.getStackInSlot(INPUT_SLOT).getCount() >= AtmRandomConversionRatesSavedData.getData(server).getDiamondToAmber()[0])) {
                    inputStack = new ItemStack(Items.DIAMOND, AtmRandomConversionRatesSavedData.getData(server).getDiamondToAmber()[0]);
                    outputStack = new ItemStack(ModItems.AMBER.get(), AtmRandomConversionRatesSavedData.getData(server).getDiamondToAmber()[1]);
                }

            } else if (randomConvertButtonPressed == 3) {
                if (inputItem == ModItems.RUBY.get() && (itemStackHandler.getStackInSlot(INPUT_SLOT).getCount() >= AtmRandomConversionRatesSavedData.getData(server).getRubyToDiamond()[0])) {
                    inputStack = new ItemStack(ModItems.RUBY.get(), AtmRandomConversionRatesSavedData.getData(server).getRubyToDiamond()[0]);
                    outputStack = new ItemStack(Items.DIAMOND, AtmRandomConversionRatesSavedData.getData(server).getRubyToDiamond()[1]);
                }

            } else if (randomConvertButtonPressed == 4) {
                if (inputItem == ModItems.RUBY.get() && (itemStackHandler.getStackInSlot(INPUT_SLOT).getCount() >= AtmRandomConversionRatesSavedData.getData(server).getRubyToAmber()[0])) {
                    inputStack = new ItemStack(ModItems.RUBY.get(), AtmRandomConversionRatesSavedData.getData(server).getRubyToAmber()[0]);
                    outputStack = new ItemStack(ModItems.AMBER.get(), AtmRandomConversionRatesSavedData.getData(server).getRubyToAmber()[1]);
                }

            } else if (randomConvertButtonPressed == 5) {
                if (inputItem == ModItems.AMBER.get() && (itemStackHandler.getStackInSlot(INPUT_SLOT).getCount() >= AtmRandomConversionRatesSavedData.getData(server).getAmberToDiamond()[0])) {
                    inputStack = new ItemStack(ModItems.AMBER.get(), AtmRandomConversionRatesSavedData.getData(server).getAmberToDiamond()[0]);
                    outputStack = new ItemStack(Items.DIAMOND, AtmRandomConversionRatesSavedData.getData(server).getAmberToDiamond()[1]);
                }

            } else if (randomConvertButtonPressed == 6) {
                if (inputItem == ModItems.AMBER.get() && (itemStackHandler.getStackInSlot(INPUT_SLOT).getCount() >= AtmRandomConversionRatesSavedData.getData(server).getAmberToRuby()[0])) {
                    inputStack = new ItemStack(ModItems.AMBER.get(), AtmRandomConversionRatesSavedData.getData(server).getAmberToRuby()[0]);
                    outputStack = new ItemStack(ModItems.RUBY.get(), AtmRandomConversionRatesSavedData.getData(server).getAmberToRuby()[1]);
                }
            }

            return new ItemStack[]{inputStack, outputStack};
        }

        return new ItemStack[]{ItemStack.EMPTY, ItemStack.EMPTY};
    }

    private ItemStack[] getToolConvertRecipe() {
        Item inputItem = itemStackHandler.getStackInSlot(INPUT_SLOT).getItem();
        ItemStack inputStack = ItemStack.EMPTY;
        ItemStack outputStack = ItemStack.EMPTY;

        if (toolConvertButtonPressed == 1) {
            if(inputItem == ModItems.RUBY_SWORD.get()) {
                inputStack = new ItemStack(ModItems.RUBY_SWORD.get());
                outputStack = new ItemStack(Items.DIAMOND_SWORD);

            } else if (inputItem == ModItems.RUBY_SHOVEL.get()) {
                inputStack = new ItemStack(ModItems.RUBY_SHOVEL.get());
                outputStack = new ItemStack(Items.DIAMOND_SHOVEL);

            } else if (inputItem == ModItems.RUBY_PICKAXE.get()) {
                inputStack = new ItemStack(ModItems.RUBY_PICKAXE.get());
                outputStack = new ItemStack(Items.DIAMOND_PICKAXE);

            } else if (inputItem == ModItems.RUBY_AXE.get()) {
                inputStack = new ItemStack(ModItems.RUBY_AXE.get());
                outputStack = new ItemStack(Items.DIAMOND_AXE);

            } else if (inputItem == ModItems.RUBY_HOE.get()) {
                inputStack = new ItemStack(ModItems.RUBY_HOE.get());
                outputStack = new ItemStack(Items.DIAMOND_HOE);

            } else if (inputItem == ModCompatItems.RUBY_KNIFE.get() && ModList.get().isLoaded("farmersdelight")) {
                inputStack = new ItemStack(ModCompatItems.RUBY_KNIFE.get());
                outputStack = new ItemStack(vectorwing.farmersdelight.common.registry.ModItems.DIAMOND_KNIFE.get());

            } else if (inputItem == ModCompatItems.RUBY_FILLET_KNIFE.get() && ModList.get().isLoaded("aquaculture")) {
                inputStack = new ItemStack(ModCompatItems.RUBY_FILLET_KNIFE.get());
                outputStack = new ItemStack(AquaItems.DIAMOND_FILLET_KNIFE.get());

            } else if (inputItem == ModCompatItems.RUBY_FISHING_ROD.get() && ModList.get().isLoaded("aquaculture")) {
                inputStack = new ItemStack(ModCompatItems.RUBY_FISHING_ROD.get());
                outputStack = new ItemStack(AquaItems.DIAMOND_FISHING_ROD.get());

            } else if (inputItem == ModCompatItems.RUBY_HOOK.get() && ModList.get().isLoaded("aquaculture")) {
                inputStack = new ItemStack(ModCompatItems.RUBY_HOOK.get());
                outputStack = new ItemStack(AquaItems.DIAMOND_HOOK.get());

            } else if (inputItem == ModCompatItems.RUBY_BRUSH.get() && ModList.get().isLoaded("betterarcheology")) {
                inputStack = new ItemStack(ModCompatItems.RUBY_BRUSH.get());
                outputStack = new ItemStack(BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("betterarcheology", "diamond_brush")));

            } else if (inputItem == ModItems.RUBY_HELMET.get()) {
                inputStack = new ItemStack(ModItems.RUBY_HELMET.get());
                outputStack = new ItemStack(Items.DIAMOND_HELMET);

            } else if (inputItem == ModItems.RUBY_CHESTPLATE.get()) {
                inputStack = new ItemStack(ModItems.RUBY_CHESTPLATE.get());
                outputStack = new ItemStack(Items.DIAMOND_CHESTPLATE);

            } else if (inputItem == ModItems.RUBY_LEGGINGS.get()) {
                inputStack = new ItemStack(ModItems.RUBY_LEGGINGS.get());
                outputStack = new ItemStack(Items.DIAMOND_LEGGINGS);

            } else if (inputItem == ModItems.RUBY_BOOTS.get()) {
                inputStack = new ItemStack(ModItems.RUBY_BOOTS.get());
                outputStack = new ItemStack(Items.DIAMOND_BOOTS);

            } else if (inputItem == ModItems.RUBY_HORSE_ARMOR.get()) {
                inputStack = new ItemStack(ModItems.RUBY_HORSE_ARMOR.get());
                outputStack = new ItemStack(Items.DIAMOND_HORSE_ARMOR);

            } else if(inputItem == ModItems.AMBER_SWORD.get()) {
                inputStack = new ItemStack(ModItems.AMBER_SWORD.get());
                outputStack = new ItemStack(Items.DIAMOND_SWORD);

            } else if (inputItem == ModItems.AMBER_SHOVEL.get()) {
                inputStack = new ItemStack(ModItems.AMBER_SHOVEL.get());
                outputStack = new ItemStack(Items.DIAMOND_SHOVEL);

            } else if (inputItem == ModItems.AMBER_PICKAXE.get()) {
                inputStack = new ItemStack(ModItems.AMBER_PICKAXE.get());
                outputStack = new ItemStack(Items.DIAMOND_PICKAXE);

            } else if (inputItem == ModItems.AMBER_AXE.get()) {
                inputStack = new ItemStack(ModItems.AMBER_AXE.get());
                outputStack = new ItemStack(Items.DIAMOND_AXE);

            } else if (inputItem == ModItems.AMBER_HOE.get()) {
                inputStack = new ItemStack(ModItems.AMBER_HOE.get());
                outputStack = new ItemStack(Items.DIAMOND_HOE);

            } else if (inputItem == ModCompatItems.AMBER_KNIFE.get() && ModList.get().isLoaded("farmersdelight")) {
                inputStack = new ItemStack(ModCompatItems.AMBER_KNIFE.get());
                outputStack = new ItemStack(vectorwing.farmersdelight.common.registry.ModItems.DIAMOND_KNIFE.get());

            } else if (inputItem == ModCompatItems.AMBER_FILLET_KNIFE.get() && ModList.get().isLoaded("aquaculture")) {
                inputStack = new ItemStack(ModCompatItems.AMBER_FILLET_KNIFE.get());
                outputStack = new ItemStack(AquaItems.DIAMOND_FILLET_KNIFE.get());

            } else if (inputItem == ModCompatItems.AMBER_FISHING_ROD.get() && ModList.get().isLoaded("aquaculture")) {
                inputStack = new ItemStack(ModCompatItems.AMBER_FISHING_ROD.get());
                outputStack = new ItemStack(AquaItems.DIAMOND_FISHING_ROD.get());

            } else if (inputItem == ModCompatItems.AMBER_HOOK.get() && ModList.get().isLoaded("aquaculture")) {
                inputStack = new ItemStack(ModCompatItems.AMBER_HOOK.get());
                outputStack = new ItemStack(AquaItems.DIAMOND_HOOK.get());

            } else if (inputItem == ModCompatItems.AMBER_BRUSH.get() && ModList.get().isLoaded("betterarcheology")) {
                inputStack = new ItemStack(ModCompatItems.AMBER_BRUSH.get());
                outputStack = new ItemStack(BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("betterarcheology", "diamond_brush")));

            } else if (inputItem == ModItems.AMBER_HELMET.get()) {
                inputStack = new ItemStack(ModItems.AMBER_HELMET.get());
                outputStack = new ItemStack(Items.DIAMOND_HELMET);

            } else if (inputItem == ModItems.AMBER_CHESTPLATE.get()) {
                inputStack = new ItemStack(ModItems.AMBER_CHESTPLATE.get());
                outputStack = new ItemStack(Items.DIAMOND_CHESTPLATE);

            } else if (inputItem == ModItems.AMBER_LEGGINGS.get()) {
                inputStack = new ItemStack(ModItems.AMBER_LEGGINGS.get());
                outputStack = new ItemStack(Items.DIAMOND_LEGGINGS);

            } else if (inputItem == ModItems.AMBER_BOOTS.get()) {
                inputStack = new ItemStack(ModItems.AMBER_BOOTS.get());
                outputStack = new ItemStack(Items.DIAMOND_BOOTS);

            } else if (inputItem == ModItems.AMBER_HORSE_ARMOR.get()) {
                inputStack = new ItemStack(ModItems.AMBER_HORSE_ARMOR.get());
                outputStack = new ItemStack(Items.DIAMOND_HORSE_ARMOR);
            }

        } else if (toolConvertButtonPressed == 2) {
            if(inputItem == Items.DIAMOND_SWORD) {
                inputStack = new ItemStack(Items.DIAMOND_SWORD);
                outputStack = new ItemStack(ModItems.RUBY_SWORD.get());

            } else if (inputItem == Items.DIAMOND_SHOVEL) {
                inputStack = new ItemStack(Items.DIAMOND_SHOVEL);
                outputStack = new ItemStack(ModItems.RUBY_SHOVEL.get());

            } else if (inputItem == Items.DIAMOND_PICKAXE) {
                inputStack = new ItemStack(Items.DIAMOND_PICKAXE);
                outputStack = new ItemStack(ModItems.RUBY_PICKAXE.get());

            } else if (inputItem == Items.DIAMOND_AXE) {
                inputStack = new ItemStack(Items.DIAMOND_AXE);
                outputStack = new ItemStack(ModItems.RUBY_AXE.get());

            } else if (inputItem == Items.DIAMOND_HOE) {
                inputStack = new ItemStack(Items.DIAMOND_HOE);
                outputStack = new ItemStack(ModItems.RUBY_HOE.get());

            } else if (inputItem == vectorwing.farmersdelight.common.registry.ModItems.DIAMOND_KNIFE.get() && ModList.get().isLoaded("farmersdelight")) {
                inputStack = new ItemStack(vectorwing.farmersdelight.common.registry.ModItems.DIAMOND_KNIFE.get());
                outputStack = new ItemStack(ModCompatItems.RUBY_KNIFE.get());

            } else if (inputItem == AquaItems.DIAMOND_FILLET_KNIFE.get() && ModList.get().isLoaded("aquaculture")) {
                inputStack = new ItemStack(AquaItems.DIAMOND_FILLET_KNIFE.get());
                outputStack = new ItemStack(ModCompatItems.RUBY_FILLET_KNIFE.get());

            } else if (inputItem == AquaItems.DIAMOND_FISHING_ROD.get() && ModList.get().isLoaded("aquaculture")) {
                inputStack = new ItemStack(AquaItems.DIAMOND_FISHING_ROD.get());
                outputStack = new ItemStack(ModCompatItems.RUBY_FISHING_ROD.get());

            } else if (inputItem == AquaItems.DIAMOND_HOOK.get() && ModList.get().isLoaded("aquaculture")) {
                inputStack = new ItemStack(AquaItems.DIAMOND_HOOK.get());
                outputStack = new ItemStack(ModCompatItems.RUBY_HOOK.get());

            } else if (inputItem == BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("betterarcheology", "diamond_brush")) && ModList.get().isLoaded("betterarcheology")) {
                inputStack = new ItemStack(BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("betterarcheology", "diamond_brush")));
                outputStack = new ItemStack(ModCompatItems.RUBY_BRUSH.get());

            } else if (inputItem == Items.DIAMOND_HELMET) {
                inputStack = new ItemStack(Items.DIAMOND_HELMET);
                outputStack = new ItemStack(ModItems.RUBY_HELMET.get());

            } else if (inputItem == Items.DIAMOND_CHESTPLATE) {
                inputStack = new ItemStack(Items.DIAMOND_CHESTPLATE);
                outputStack = new ItemStack(ModItems.RUBY_CHESTPLATE.get());

            } else if (inputItem == Items.DIAMOND_LEGGINGS) {
                inputStack = new ItemStack(Items.DIAMOND_LEGGINGS);
                outputStack = new ItemStack(ModItems.RUBY_LEGGINGS.get());

            } else if (inputItem == Items.DIAMOND_BOOTS) {
                inputStack = new ItemStack(Items.DIAMOND_BOOTS);
                outputStack = new ItemStack(ModItems.RUBY_BOOTS.get());

            } else if (inputItem == Items.DIAMOND_HORSE_ARMOR) {
                inputStack = new ItemStack(Items.DIAMOND_HORSE_ARMOR);
                outputStack = new ItemStack(ModItems.RUBY_HORSE_ARMOR.get());

            } else if(inputItem == ModItems.AMBER_SWORD.get()) {
                inputStack = new ItemStack(ModItems.AMBER_SWORD.get());
                outputStack = new ItemStack(ModItems.RUBY_SWORD.get());

            } else if (inputItem == ModItems. AMBER_SHOVEL.get()) {
                inputStack = new ItemStack(ModItems.AMBER_SHOVEL.get());
                outputStack = new ItemStack(ModItems.RUBY_SHOVEL.get());

            } else if (inputItem == ModItems.AMBER_PICKAXE.get()) {
                inputStack = new ItemStack(ModItems.AMBER_PICKAXE.get());
                outputStack = new ItemStack(ModItems.RUBY_PICKAXE.get());

            } else if (inputItem == ModItems.AMBER_AXE.get()) {
                inputStack = new ItemStack(ModItems.AMBER_AXE.get());
                outputStack = new ItemStack(ModItems.RUBY_AXE.get());

            } else if (inputItem == ModItems.AMBER_HOE.get()) {
                inputStack = new ItemStack(ModItems.AMBER_HOE.get());
                outputStack = new ItemStack(ModItems.RUBY_HOE.get());

            } else if (inputItem == ModCompatItems.AMBER_KNIFE.get() && ModList.get().isLoaded("farmersdelight")) {
                inputStack = new ItemStack(ModCompatItems.AMBER_KNIFE.get());
                outputStack = new ItemStack(ModCompatItems.RUBY_KNIFE.get());

            } else if (inputItem == ModCompatItems.AMBER_FILLET_KNIFE.get() && ModList.get().isLoaded("aquaculture")) {
                inputStack = new ItemStack(ModCompatItems.AMBER_FILLET_KNIFE.get());
                outputStack = new ItemStack(ModCompatItems.RUBY_FILLET_KNIFE.get());

            } else if (inputItem == ModCompatItems.AMBER_FISHING_ROD.get() && ModList.get().isLoaded("aquaculture")) {
                inputStack = new ItemStack(ModCompatItems.AMBER_FISHING_ROD.get());
                outputStack = new ItemStack(ModCompatItems.RUBY_FISHING_ROD.get());

            } else if (inputItem == ModCompatItems.AMBER_HOOK.get() && ModList.get().isLoaded("aquaculture")) {
                inputStack = new ItemStack(ModCompatItems.AMBER_HOOK.get());
                outputStack = new ItemStack(ModCompatItems.RUBY_HOOK.get());

            } else if (inputItem == ModCompatItems.AMBER_BRUSH.get() && ModList.get().isLoaded("betterarcheology")) {
                inputStack = new ItemStack(ModCompatItems.AMBER_BRUSH.get());
                outputStack = new ItemStack(ModCompatItems.RUBY_BRUSH.get());

            } else if (inputItem == ModItems.AMBER_HELMET.get()) {
                inputStack = new ItemStack(ModItems.AMBER_HELMET.get());
                outputStack = new ItemStack(ModItems.RUBY_HELMET.get());

            } else if (inputItem == ModItems.AMBER_CHESTPLATE.get()) {
                inputStack = new ItemStack(ModItems.AMBER_CHESTPLATE.get());
                outputStack = new ItemStack(ModItems.RUBY_CHESTPLATE.get());

            } else if (inputItem == ModItems.AMBER_LEGGINGS.get()) {
                inputStack = new ItemStack(ModItems.AMBER_LEGGINGS.get());
                outputStack = new ItemStack(ModItems.RUBY_LEGGINGS.get());

            } else if (inputItem == ModItems.AMBER_BOOTS.get()) {
                inputStack = new ItemStack(ModItems.AMBER_BOOTS.get());
                outputStack = new ItemStack(ModItems.RUBY_BOOTS.get());

            } else if (inputItem == ModItems.AMBER_HORSE_ARMOR.get()) {
                inputStack = new ItemStack(ModItems.AMBER_HORSE_ARMOR.get());
                outputStack = new ItemStack(ModItems.RUBY_HORSE_ARMOR.get());
            }

        } else if (toolConvertButtonPressed == 3) {
            if(inputItem == Items.DIAMOND_SWORD) {
                inputStack = new ItemStack(Items.DIAMOND_SWORD);
                outputStack = new ItemStack(ModItems.AMBER_SWORD.get());

            } else if (inputItem == Items.DIAMOND_SHOVEL) {
                inputStack = new ItemStack(Items.DIAMOND_SHOVEL);
                outputStack = new ItemStack(ModItems.AMBER_SHOVEL.get());

            } else if (inputItem == Items.DIAMOND_PICKAXE) {
                inputStack = new ItemStack(Items.DIAMOND_PICKAXE);
                outputStack = new ItemStack(ModItems.AMBER_PICKAXE.get());

            } else if (inputItem == Items.DIAMOND_AXE) {
                inputStack = new ItemStack(Items.DIAMOND_AXE);
                outputStack = new ItemStack(ModItems.AMBER_AXE.get());

            } else if (inputItem == Items.DIAMOND_HOE) {
                inputStack = new ItemStack(Items.DIAMOND_HOE);
                outputStack = new ItemStack(ModItems.AMBER_HOE.get());

            } else if (inputItem == vectorwing.farmersdelight.common.registry.ModItems.DIAMOND_KNIFE.get() && ModList.get().isLoaded("farmersdelight")) {
                inputStack = new ItemStack(vectorwing.farmersdelight.common.registry.ModItems.DIAMOND_KNIFE.get());
                outputStack = new ItemStack(ModCompatItems.AMBER_KNIFE.get());

            } else if (inputItem == AquaItems.DIAMOND_FILLET_KNIFE.get() && ModList.get().isLoaded("aquaculture")) {
                inputStack = new ItemStack(AquaItems.DIAMOND_FILLET_KNIFE.get());
                outputStack = new ItemStack(ModCompatItems.AMBER_FILLET_KNIFE.get());

            } else if (inputItem == AquaItems.DIAMOND_FISHING_ROD.get() && ModList.get().isLoaded("aquaculture")) {
                inputStack = new ItemStack(AquaItems.DIAMOND_FISHING_ROD.get());
                outputStack = new ItemStack(ModCompatItems.AMBER_FISHING_ROD.get());

            } else if (inputItem == AquaItems.DIAMOND_HOOK.get() && ModList.get().isLoaded("aquaculture")) {
                inputStack = new ItemStack(AquaItems.DIAMOND_HOOK.get());
                outputStack = new ItemStack(ModCompatItems.AMBER_HOOK.get());

            } else if (inputItem == BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("betterarcheology", "diamond_brush")) && ModList.get().isLoaded("betterarcheology")) {
                inputStack = new ItemStack(BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("betterarcheology", "diamond_brush")));
                outputStack = new ItemStack(ModCompatItems.AMBER_BRUSH.get());

            } else if (inputItem == Items.DIAMOND_HELMET) {
                inputStack = new ItemStack(Items.DIAMOND_HELMET);
                outputStack = new ItemStack(ModItems.AMBER_HELMET.get());

            } else if (inputItem == Items.DIAMOND_CHESTPLATE) {
                inputStack = new ItemStack(Items.DIAMOND_CHESTPLATE);
                outputStack = new ItemStack(ModItems.AMBER_CHESTPLATE.get());

            } else if (inputItem == Items.DIAMOND_LEGGINGS) {
                inputStack = new ItemStack(Items.DIAMOND_LEGGINGS);
                outputStack = new ItemStack(ModItems.AMBER_LEGGINGS.get());

            } else if (inputItem == Items.DIAMOND_BOOTS) {
                inputStack = new ItemStack(Items.DIAMOND_BOOTS);
                outputStack = new ItemStack(ModItems.AMBER_BOOTS.get());

            } else if (inputItem == Items.DIAMOND_HORSE_ARMOR) {
                inputStack = new ItemStack(Items.DIAMOND_HORSE_ARMOR);
                outputStack = new ItemStack(ModItems.AMBER_HORSE_ARMOR.get());

            } else if (inputItem == ModItems.RUBY_SWORD.get()) {
                inputStack = new ItemStack(ModItems.RUBY_SWORD.get());
                outputStack = new ItemStack(ModItems.AMBER_SWORD.get());

            } else if (inputItem == ModItems.RUBY_SHOVEL.get()) {
                inputStack = new ItemStack(ModItems.RUBY_SHOVEL.get());
                outputStack = new ItemStack(ModItems.AMBER_SHOVEL.get());

            } else if (inputItem == ModItems.RUBY_PICKAXE.get()) {
                inputStack = new ItemStack(ModItems.RUBY_PICKAXE.get());
                outputStack = new ItemStack(ModItems.AMBER_PICKAXE.get());

            } else if (inputItem == ModItems.RUBY_AXE.get()) {
                inputStack = new ItemStack(ModItems.RUBY_AXE.get());
                outputStack = new ItemStack(ModItems.AMBER_AXE.get());

            } else if (inputItem == ModItems.RUBY_HOE.get()) {
                inputStack = new ItemStack(ModItems.RUBY_HOE.get());
                outputStack = new ItemStack(ModItems.AMBER_HOE.get());

            } else if (inputItem == ModCompatItems.RUBY_KNIFE.get() && ModList.get().isLoaded("farmersdelight")) {
                inputStack = new ItemStack(ModCompatItems.RUBY_KNIFE.get());
                outputStack = new ItemStack(ModCompatItems.AMBER_KNIFE.get());

            } else if (inputItem == ModCompatItems.RUBY_FILLET_KNIFE.get() && ModList.get().isLoaded("aquaculture")) {
                inputStack = new ItemStack(ModCompatItems.RUBY_FILLET_KNIFE.get());
                outputStack = new ItemStack(ModCompatItems.AMBER_FILLET_KNIFE.get());

            } else if (inputItem == ModCompatItems.RUBY_FISHING_ROD.get() && ModList.get().isLoaded("aquaculture")) {
                inputStack = new ItemStack(ModCompatItems.RUBY_FISHING_ROD.get());
                outputStack = new ItemStack(ModCompatItems.AMBER_FISHING_ROD.get());

            } else if (inputItem == ModCompatItems.RUBY_HOOK.get() && ModList.get().isLoaded("aquaculture")) {
                inputStack = new ItemStack(ModCompatItems.RUBY_HOOK.get());
                outputStack = new ItemStack(ModCompatItems.AMBER_HOOK.get());

            } else if (inputItem == ModCompatItems.RUBY_BRUSH.get() && ModList.get().isLoaded("betterarcheology")) {
                inputStack = new ItemStack(ModCompatItems.RUBY_BRUSH.get());
                outputStack = new ItemStack(ModCompatItems.AMBER_BRUSH.get());

            } else if (inputItem == ModItems.RUBY_HELMET.get()) {
                inputStack = new ItemStack(ModItems.RUBY_HELMET.get());
                outputStack = new ItemStack(ModItems.AMBER_HELMET.get());

            } else if (inputItem == ModItems.RUBY_CHESTPLATE.get()) {
                inputStack = new ItemStack(ModItems.RUBY_CHESTPLATE.get());
                outputStack = new ItemStack(ModItems.AMBER_CHESTPLATE.get());

            } else if (inputItem == ModItems.RUBY_LEGGINGS.get()) {
                inputStack = new ItemStack(ModItems.RUBY_LEGGINGS.get());
                outputStack = new ItemStack(ModItems.AMBER_LEGGINGS.get());

            } else if (inputItem == ModItems.RUBY_BOOTS.get()) {
                inputStack = new ItemStack(ModItems.RUBY_BOOTS.get());
                outputStack = new ItemStack(ModItems.AMBER_BOOTS.get());

            } else if (inputItem == ModItems.RUBY_HORSE_ARMOR.get()) {
                inputStack = new ItemStack(ModItems.RUBY_HORSE_ARMOR.get());
                outputStack = new ItemStack(ModItems.AMBER_HORSE_ARMOR.get());
            }
        }

        return new ItemStack[]{inputStack, outputStack};
    }

    private void updateOutput() {
        if (itemStackHandler.getStackInSlot(INPUT_SLOT).isEmpty()) {
            itemStackHandler.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);
        }
    }

    private void updateOutputWhenButtonChanged() {
        MinecraftServer server = this.level.getServer();
        if(server != null) {
            if (randomConvertScreen && !itemStackHandler.getStackInSlot(OUTPUT_SLOT).isEmpty()) {
                if (itemStackHandler.getStackInSlot(OUTPUT_SLOT).getItem() == Items.DIAMOND) {
                    if ((itemStackHandler.getStackInSlot(OUTPUT_SLOT).getCount() == AtmRandomConversionRatesSavedData.getData(server).getRubyToDiamond()[1]) && randomConvertButtonPressed != 3) {
                        itemStackHandler.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);

                    } else if ((itemStackHandler.getStackInSlot(OUTPUT_SLOT).getCount() == AtmRandomConversionRatesSavedData.getData(server).getAmberToDiamond()[1]) && randomConvertButtonPressed != 5) {
                        itemStackHandler.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);
                    }

                } else if (itemStackHandler.getStackInSlot(OUTPUT_SLOT).getItem() == ModItems.RUBY.get()) {
                    if ((itemStackHandler.getStackInSlot(OUTPUT_SLOT).getCount() == AtmRandomConversionRatesSavedData.getData(server).getDiamondToRuby()[1]) && randomConvertButtonPressed != 1) {
                        itemStackHandler.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);

                    } else if ((itemStackHandler.getStackInSlot(OUTPUT_SLOT).getCount() == AtmRandomConversionRatesSavedData.getData(server).getAmberToRuby()[1]) && randomConvertButtonPressed != 6) {
                        itemStackHandler.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);
                    }

                } else if (itemStackHandler.getStackInSlot(OUTPUT_SLOT).getItem() == ModItems.AMBER.get()) {
                    if ((itemStackHandler.getStackInSlot(OUTPUT_SLOT).getCount() == AtmRandomConversionRatesSavedData.getData(server).getDiamondToAmber()[1]) && randomConvertButtonPressed != 2) {
                        itemStackHandler.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);

                    } else if ((itemStackHandler.getStackInSlot(OUTPUT_SLOT).getCount() == AtmRandomConversionRatesSavedData.getData(server).getRubyToAmber()[1]) && randomConvertButtonPressed != 4) {
                        itemStackHandler.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);
                    }
                }

            } else if (toolConvertScreen && !itemStackHandler.getStackInSlot(OUTPUT_SLOT).isEmpty()) {
                ItemStack inputStack = itemStackHandler.getStackInSlot(INPUT_SLOT);
                ItemStack outputStack = itemStackHandler.getStackInSlot(OUTPUT_SLOT);

                if (inputStack.is(ModItemTags.DIAMOND_CONVERTIBLE_TOOLS) && (outputStack.is(ModItemTags.RUBY_CONVERTIBLE_TOOLS)) && toolConvertButtonPressed != 1) {
                    itemStackHandler.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);

                } else if (inputStack.is(ModItemTags.DIAMOND_CONVERTIBLE_TOOLS) && (outputStack.is(ModItemTags.AMBER_CONVERTIBLE_TOOLS)) && toolConvertButtonPressed != 1) {
                    itemStackHandler.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);

                } else if (inputStack.is(ModItemTags.RUBY_CONVERTIBLE_TOOLS) && (outputStack.is(ModItemTags.DIAMOND_CONVERTIBLE_TOOLS)) && toolConvertButtonPressed != 2) {
                    itemStackHandler.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);

                } else if (inputStack.is(ModItemTags.RUBY_CONVERTIBLE_TOOLS) && (outputStack.is(ModItemTags.AMBER_CONVERTIBLE_TOOLS)) && toolConvertButtonPressed != 2) {
                    itemStackHandler.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);

                } else if (inputStack.is(ModItemTags.AMBER_CONVERTIBLE_TOOLS) && (outputStack.is(ModItemTags.DIAMOND_CONVERTIBLE_TOOLS)) && toolConvertButtonPressed != 3) {
                    itemStackHandler.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);

                } else if (inputStack.is(ModItemTags.AMBER_CONVERTIBLE_TOOLS) && (outputStack.is(ModItemTags.RUBY_CONVERTIBLE_TOOLS)) && toolConvertButtonPressed != 3) {
                    itemStackHandler.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);
                }
            }
        }
    }

    private boolean hasRecipe(ItemStack inputStack, ItemStack outputStack) {
        return canInsertAmountIntoOutputSlot(outputStack.getCount()) && canInsertItemIntoOutputSlot(outputStack) && this.itemStackHandler.getStackInSlot(INPUT_SLOT).getItem() == inputStack.getItem();
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack outputStack) {
        return itemStackHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || itemStackHandler.getStackInSlot(OUTPUT_SLOT).getItem() == outputStack.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int amount) {
        int maxAmount = itemStackHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ? 64 : itemStackHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
        int currentAmount = itemStackHandler.getStackInSlot(OUTPUT_SLOT).getCount();
        return maxAmount >= currentAmount + amount;
    }

    public int getConvertAmount() {
        return this.convertAmount;
    }

    private int getRandomConvertInputAmount(int conversionButtonPressed) {
        int amount = 0;
        MinecraftServer server = this.level.getServer();

        if (server != null) {
            if (conversionButtonPressed == 1) {
                amount = AtmRandomConversionRatesSavedData.getData(server).getDiamondToRuby()[0];

            } else if (conversionButtonPressed == 2) {
                amount = AtmRandomConversionRatesSavedData.getData(server).getDiamondToAmber()[0];

            } else if (conversionButtonPressed == 3) {
                amount = AtmRandomConversionRatesSavedData.getData(server).getRubyToDiamond()[0];

            } else if (conversionButtonPressed == 4) {
                amount = AtmRandomConversionRatesSavedData.getData(server).getRubyToAmber()[0];

            } else if (conversionButtonPressed == 5) {
                amount = AtmRandomConversionRatesSavedData.getData(server).getAmberToDiamond()[0];

            } else if (conversionButtonPressed == 6) {
                amount = AtmRandomConversionRatesSavedData.getData(server).getAmberToRuby()[0];
            }

            return amount;
        }

        return 1;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }
}