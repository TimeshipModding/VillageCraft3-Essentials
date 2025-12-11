package com.timeshipmodding.villagecraft3essentials.content.block.entity;

import com.timeshipmodding.villagecraft3essentials.content.block.entity.registries.ModBlockEntities;
import com.timeshipmodding.villagecraft3essentials.content.item.registries.ModItems;
import com.timeshipmodding.villagecraft3essentials.content.menu.AtmMenu;
import com.timeshipmodding.villagecraft3essentials.util.saveddata.AtmRandomConversionRatesSavedData;
import com.timeshipmodding.villagecraft3essentials.util.tags.registries.ModItemTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
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
        ItemStack randomConvertInputItemstack = getRandomConvertRecipe()[0];
        ItemStack randomConvertOutputItemstack = getRandomConvertRecipe()[1];
        ItemStack toolConvertInputItemstack = getToolConvertRecipe()[0];
        ItemStack toolConvertOutputItemstack = getToolConvertRecipe()[1];
        updateOutputWhenButtonChanged();

        if (randomConvertScreen && itemStackHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() && hasRecipe(randomConvertInputItemstack, randomConvertOutputItemstack)) {
            randomConvertCurrency(randomConvertOutputItemstack, randomConvertButtonPressed);
        } else if (toolConvertScreen && itemStackHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() && hasRecipe(toolConvertInputItemstack, toolConvertOutputItemstack)) {
            toolConvertItem(toolConvertOutputItemstack);
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

    public int quickRandomConvertCurrency(ItemStack initialOutputItemstack, int randomConvertButtonPressed) {
        convertAmount = getRandomConvertInputAmount(randomConvertButtonPressed);
        ItemStack initialInputItemstack = itemStackHandler.getStackInSlot(INPUT_SLOT);
        int currentInputItemCount = initialInputItemstack.getCount();
        int maxConversions;
        int conversionRemainder;
        maxConversions = currentInputItemCount / convertAmount;
        conversionRemainder = currentInputItemCount % convertAmount;

        if (conversionRemainder == 0) {
            itemStackHandler.setStackInSlot(INPUT_SLOT, ItemStack.EMPTY);
        } else {
            initialInputItemstack.setCount(conversionRemainder);
            itemStackHandler.setStackInSlot(INPUT_SLOT, initialInputItemstack);
        }

        return initialOutputItemstack.getCount() * maxConversions;
    }

    private void toolConvertItem(ItemStack outputStack) {
        convertAmount = 1;
        DataComponentMap INPUT_ITEM_COMPONENTS = itemStackHandler.getStackInSlot(INPUT_SLOT).getComponents();
        itemStackHandler.setStackInSlot(OUTPUT_SLOT, outputStack);
        itemStackHandler.getStackInSlot(OUTPUT_SLOT).applyComponents(INPUT_ITEM_COMPONENTS);
    }

    public ItemStack[] getRandomConvertRecipe() {
        MinecraftServer server = this.level.getServer();
        ItemStack OUPUT_ITEMSTACK = ItemStack.EMPTY;
        ItemStack INPUT_ITEMSTACK = ItemStack.EMPTY;
        Item INPUT_ITEM = itemStackHandler.getStackInSlot(INPUT_SLOT).getItem();

        if (server != null) {
            if (randomConvertButtonPressed == 1) {
                if (INPUT_ITEM == Items.DIAMOND && (itemStackHandler.getStackInSlot(INPUT_SLOT).getCount() >= AtmRandomConversionRatesSavedData.getData(server).getDiamondToRuby()[0])) {
                    INPUT_ITEMSTACK = new ItemStack(Items.DIAMOND, AtmRandomConversionRatesSavedData.getData(server).getDiamondToRuby()[0]);
                    OUPUT_ITEMSTACK = new ItemStack(ModItems.RUBY.get(), AtmRandomConversionRatesSavedData.getData(server).getDiamondToRuby()[1]);
                }
            } else if (randomConvertButtonPressed == 2) {
                if (INPUT_ITEM == Items.DIAMOND && (itemStackHandler.getStackInSlot(INPUT_SLOT).getCount() >= AtmRandomConversionRatesSavedData.getData(server).getDiamondToAmber()[0])) {
                    INPUT_ITEMSTACK = new ItemStack(Items.DIAMOND, AtmRandomConversionRatesSavedData.getData(server).getDiamondToAmber()[0]);
                    OUPUT_ITEMSTACK = new ItemStack(ModItems.AMBER.get(), AtmRandomConversionRatesSavedData.getData(server).getDiamondToAmber()[1]);
                }
            } else if (randomConvertButtonPressed == 3) {
                if (INPUT_ITEM == ModItems.RUBY.get() && (itemStackHandler.getStackInSlot(INPUT_SLOT).getCount() >= AtmRandomConversionRatesSavedData.getData(server).getRubyToDiamond()[0])) {
                    INPUT_ITEMSTACK = new ItemStack(ModItems.RUBY.get(), AtmRandomConversionRatesSavedData.getData(server).getRubyToDiamond()[0]);
                    OUPUT_ITEMSTACK = new ItemStack(Items.DIAMOND, AtmRandomConversionRatesSavedData.getData(server).getRubyToDiamond()[1]);
                }
            } else if (randomConvertButtonPressed == 4) {
                if (INPUT_ITEM == ModItems.RUBY.get() && (itemStackHandler.getStackInSlot(INPUT_SLOT).getCount() >= AtmRandomConversionRatesSavedData.getData(server).getRubyToAmber()[0])) {
                    INPUT_ITEMSTACK = new ItemStack(ModItems.RUBY.get(), AtmRandomConversionRatesSavedData.getData(server).getRubyToAmber()[0]);
                    OUPUT_ITEMSTACK = new ItemStack(ModItems.AMBER.get(), AtmRandomConversionRatesSavedData.getData(server).getRubyToAmber()[1]);
                }
            } else if (randomConvertButtonPressed == 5) {
                if (INPUT_ITEM == ModItems.AMBER.get() && (itemStackHandler.getStackInSlot(INPUT_SLOT).getCount() >= AtmRandomConversionRatesSavedData.getData(server).getAmberToDiamond()[0])) {
                    INPUT_ITEMSTACK = new ItemStack(ModItems.AMBER.get(), AtmRandomConversionRatesSavedData.getData(server).getAmberToDiamond()[0]);
                    OUPUT_ITEMSTACK = new ItemStack(Items.DIAMOND, AtmRandomConversionRatesSavedData.getData(server).getAmberToDiamond()[1]);
                }
            } else if (randomConvertButtonPressed == 6) {
                if (INPUT_ITEM == ModItems.AMBER.get() && (itemStackHandler.getStackInSlot(INPUT_SLOT).getCount() >= AtmRandomConversionRatesSavedData.getData(server).getAmberToRuby()[0])) {
                    INPUT_ITEMSTACK = new ItemStack(ModItems.AMBER.get(), AtmRandomConversionRatesSavedData.getData(server).getAmberToRuby()[0]);
                    OUPUT_ITEMSTACK = new ItemStack(ModItems.RUBY.get(), AtmRandomConversionRatesSavedData.getData(server).getAmberToRuby()[1]);
                }
            }
            return new ItemStack[]{INPUT_ITEMSTACK, OUPUT_ITEMSTACK};
        }
        return new ItemStack[]{ItemStack.EMPTY, ItemStack.EMPTY};
    }

    private ItemStack[] getToolConvertRecipe() {
        ItemStack OUPUT_ITEMSTACK = ItemStack.EMPTY;
        ItemStack INPUT_ITEMSTACK = ItemStack.EMPTY;
        Item INPUT_ITEM = itemStackHandler.getStackInSlot(INPUT_SLOT).getItem();

        if (toolConvertButtonPressed == 1) {
            if(INPUT_ITEM == ModItems.RUBY_SWORD.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_SWORD.get());
                OUPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_SWORD);
            } else if (INPUT_ITEM == ModItems.RUBY_SHOVEL.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_SHOVEL.get());
                OUPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_SHOVEL);
            } else if (INPUT_ITEM == ModItems.RUBY_PICKAXE.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_PICKAXE.get());
                OUPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_PICKAXE);
            } else if (INPUT_ITEM == ModItems.RUBY_AXE.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_AXE.get());
                OUPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_AXE);
            } else if (INPUT_ITEM == ModItems.RUBY_HOE.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_HOE.get());
                OUPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_HOE);
            } else if (INPUT_ITEM == ModItems.RUBY_HELMET.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_HELMET.get());
                OUPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_HELMET);
            } else if (INPUT_ITEM == ModItems.RUBY_CHESTPLATE.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_CHESTPLATE.get());
                OUPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_CHESTPLATE);
            } else if (INPUT_ITEM == ModItems.RUBY_LEGGINGS.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_LEGGINGS.get());
                OUPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_LEGGINGS);
            } else if (INPUT_ITEM == ModItems.RUBY_BOOTS.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_BOOTS.get());
                OUPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_BOOTS);
            } else if (INPUT_ITEM == ModItems.RUBY_HORSE_ARMOR.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_HORSE_ARMOR.get());
                OUPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_HORSE_ARMOR);
            } else if(INPUT_ITEM == ModItems.AMBER_SWORD.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_SWORD.get());
                OUPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_SWORD);
            } else if (INPUT_ITEM == ModItems.AMBER_SHOVEL.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_SHOVEL.get());
                OUPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_SHOVEL);
            } else if (INPUT_ITEM == ModItems.AMBER_PICKAXE.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_PICKAXE.get());
                OUPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_PICKAXE);
            } else if (INPUT_ITEM == ModItems.AMBER_AXE.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_AXE.get());
                OUPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_AXE);
            } else if (INPUT_ITEM == ModItems.AMBER_HOE.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_HOE.get());
                OUPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_HOE);
            } else if (INPUT_ITEM == ModItems.AMBER_HELMET.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_HELMET.get());
                OUPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_HELMET);
            } else if (INPUT_ITEM == ModItems.AMBER_CHESTPLATE.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_CHESTPLATE.get());
                OUPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_CHESTPLATE);
            } else if (INPUT_ITEM == ModItems.AMBER_LEGGINGS.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_LEGGINGS.get());
                OUPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_LEGGINGS);
            } else if (INPUT_ITEM == ModItems.AMBER_BOOTS.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_BOOTS.get());
                OUPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_BOOTS);
            } else if (INPUT_ITEM == ModItems.AMBER_HORSE_ARMOR.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_HORSE_ARMOR.get());
                OUPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_HORSE_ARMOR);
            }

        } else if (toolConvertButtonPressed == 2) {
            if(INPUT_ITEM == Items.DIAMOND_SWORD) {
                INPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_SWORD);
                OUPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_SWORD.get());
            } else if (INPUT_ITEM == Items.DIAMOND_SHOVEL) {
                INPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_SHOVEL);
                OUPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_SHOVEL.get());
            } else if (INPUT_ITEM == Items.DIAMOND_PICKAXE) {
                INPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_PICKAXE);
                OUPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_PICKAXE.get());
            } else if (INPUT_ITEM == Items.DIAMOND_AXE) {
                INPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_AXE);
                OUPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_AXE.get());
            } else if (INPUT_ITEM == Items.DIAMOND_HOE) {
                INPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_HOE);
                OUPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_HOE.get());
            } else if (INPUT_ITEM == Items.DIAMOND_HELMET) {
                INPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_HELMET);
                OUPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_HELMET.get());
            } else if (INPUT_ITEM == Items.DIAMOND_CHESTPLATE) {
                INPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_CHESTPLATE);
                OUPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_CHESTPLATE.get());
            } else if (INPUT_ITEM == Items.DIAMOND_LEGGINGS) {
                INPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_LEGGINGS);
                OUPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_LEGGINGS.get());
            } else if (INPUT_ITEM == Items.DIAMOND_BOOTS) {
                INPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_BOOTS);
                OUPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_BOOTS.get());
            } else if (INPUT_ITEM == Items.DIAMOND_HORSE_ARMOR) {
                INPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_HORSE_ARMOR);
                OUPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_HORSE_ARMOR.get());
            } else if(INPUT_ITEM == ModItems.AMBER_SWORD.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_SWORD.get());
                OUPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_SWORD.get());
            } else if (INPUT_ITEM == ModItems. AMBER_SHOVEL.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_SHOVEL.get());
                OUPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_SHOVEL.get());
            } else if (INPUT_ITEM == ModItems.AMBER_PICKAXE.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_PICKAXE.get());
                OUPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_PICKAXE.get());
            } else if (INPUT_ITEM == ModItems.AMBER_AXE.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_AXE.get());
                OUPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_AXE.get());
            } else if (INPUT_ITEM == ModItems.AMBER_HOE.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_HOE.get());
                OUPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_HOE.get());
            } else if (INPUT_ITEM == ModItems.AMBER_HELMET.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_HELMET.get());
                OUPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_HELMET.get());
            } else if (INPUT_ITEM == ModItems.AMBER_CHESTPLATE.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_CHESTPLATE.get());
                OUPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_CHESTPLATE.get());
            } else if (INPUT_ITEM == ModItems.AMBER_LEGGINGS.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_LEGGINGS.get());
                OUPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_LEGGINGS.get());
            } else if (INPUT_ITEM == ModItems.AMBER_BOOTS.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_BOOTS.get());
                OUPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_BOOTS.get());
            } else if (INPUT_ITEM == ModItems.AMBER_HORSE_ARMOR.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_HORSE_ARMOR.get());
                OUPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_HORSE_ARMOR.get());
            }

        } else if (toolConvertButtonPressed == 3) {
            if(INPUT_ITEM == Items.DIAMOND_SWORD) {
                INPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_SWORD);
                OUPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_SWORD.get());
            } else if (INPUT_ITEM == Items.DIAMOND_SHOVEL) {
                INPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_SHOVEL);
                OUPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_SHOVEL.get());
            } else if (INPUT_ITEM == Items.DIAMOND_PICKAXE) {
                INPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_PICKAXE);
                OUPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_PICKAXE.get());
            } else if (INPUT_ITEM == Items.DIAMOND_AXE) {
                INPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_AXE);
                OUPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_AXE.get());
            } else if (INPUT_ITEM == Items.DIAMOND_HOE) {
                INPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_HOE);
                OUPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_HOE.get());
            } else if (INPUT_ITEM == Items.DIAMOND_HELMET) {
                INPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_HELMET);
                OUPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_HELMET.get());
            } else if (INPUT_ITEM == Items.DIAMOND_CHESTPLATE) {
                INPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_CHESTPLATE);
                OUPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_CHESTPLATE.get());
            } else if (INPUT_ITEM == Items.DIAMOND_LEGGINGS) {
                INPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_LEGGINGS);
                OUPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_LEGGINGS.get());
            } else if (INPUT_ITEM == Items.DIAMOND_BOOTS) {
                INPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_BOOTS);
                OUPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_BOOTS.get());
            } else if (INPUT_ITEM == Items.DIAMOND_HORSE_ARMOR) {
                INPUT_ITEMSTACK = new ItemStack(Items.DIAMOND_HORSE_ARMOR);
                OUPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_HORSE_ARMOR.get());
            } else if(INPUT_ITEM == ModItems.RUBY_SWORD.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_SWORD.get());
                OUPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_SWORD.get());
            } else if (INPUT_ITEM == ModItems.RUBY_SHOVEL.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_SHOVEL.get());
                OUPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_SHOVEL.get());
            } else if (INPUT_ITEM == ModItems.RUBY_PICKAXE.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_PICKAXE.get());
                OUPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_PICKAXE.get());
            } else if (INPUT_ITEM == ModItems.RUBY_AXE.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_AXE.get());
                OUPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_AXE.get());
            } else if (INPUT_ITEM == ModItems.RUBY_HOE.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_HOE.get());
                OUPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_HOE.get());
            } else if (INPUT_ITEM == ModItems.RUBY_HELMET.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_HELMET.get());
                OUPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_HELMET.get());
            } else if (INPUT_ITEM == ModItems.RUBY_CHESTPLATE.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_CHESTPLATE.get());
                OUPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_CHESTPLATE.get());
            } else if (INPUT_ITEM == ModItems.RUBY_LEGGINGS.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_LEGGINGS.get());
                OUPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_LEGGINGS.get());
            } else if (INPUT_ITEM == ModItems.RUBY_BOOTS.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_BOOTS.get());
                OUPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_BOOTS.get());
            } else if (INPUT_ITEM == ModItems.RUBY_HORSE_ARMOR.get()) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.RUBY_HORSE_ARMOR.get());
                OUPUT_ITEMSTACK = new ItemStack(ModItems.AMBER_HORSE_ARMOR.get());
            }
        }
        return new ItemStack[]{INPUT_ITEMSTACK, OUPUT_ITEMSTACK};
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
        return canInsertAmountIntoOutputSlot(outputStack.getCount()) && canInsertItemIntoOutputSlot(outputStack)
                && this.itemStackHandler.getStackInSlot(INPUT_SLOT).getItem() == inputStack.getItem();
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return itemStackHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ||
                itemStackHandler.getStackInSlot(OUTPUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = itemStackHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ? 64 : itemStackHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
        int currentCount = itemStackHandler.getStackInSlot(OUTPUT_SLOT).getCount();

        return maxCount >= currentCount + count;
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