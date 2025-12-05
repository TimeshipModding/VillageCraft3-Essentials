package com.timeshipmodding.villagecraft3essentials.content.block.entity;

import com.timeshipmodding.villagecraft3essentials.content.block.entity.registries.ModBlockEntities;
import com.timeshipmodding.villagecraft3essentials.content.item.registries.ModItems;
import com.timeshipmodding.villagecraft3essentials.content.menu.AtmMenu;
import com.timeshipmodding.villagecraft3essentials.content.screen.AtmScreen;
import com.timeshipmodding.villagecraft3essentials.event.registries.ModEvents;
import com.timeshipmodding.villagecraft3essentials.util.tags.registries.ModItemTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
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

    public AtmBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.ATM_BLOCKENTITY.get(), pos, blockState);
    }

    @Override
    public Component getDisplayName() {
        if (AtmScreen.randomConvertScreen) {
            return Component.translatable("blockentity.villagecraft3essentials.atm.randomconvert");
        } else if (AtmScreen.toolConvertScreen) {
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
        int randomConvertButtonPressed = AtmScreen.randomConvertButtonPressed;
        updateOutputWhenButtonChanged();

        if (AtmScreen.randomConvertScreen && itemStackHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() && hasRecipe(randomConvertInputItemstack, randomConvertOutputItemstack)) {
            randomConvertCurrency(randomConvertOutputItemstack, randomConvertButtonPressed);
        } else if (AtmScreen.toolConvertScreen && itemStackHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() && hasRecipe(toolConvertInputItemstack, toolConvertOutputItemstack)) {
            toolConvertItem(toolConvertOutputItemstack);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", itemStackHandler.serializeNBT(registries));
        tag.putInt("guiTextureIndex", guiTextureIndex);
        super.saveAdditional(tag, registries);
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        guiTextureIndex = tag.getInt("guiTextureIndex");
        itemStackHandler.deserializeNBT(registries, tag.getCompound("inventory"));
    }

    public int getGuiTextureIndex() {
        return this.guiTextureIndex;
    }

    public void setGuiTextureIndex(int guiTextureIndex) {
        this.guiTextureIndex = guiTextureIndex;
        setChanged();
    }

    private void randomConvertCurrency(ItemStack outputItemstack, int randomConvertButtonPressed) {
        convertAmount = getRandomConvertInputAmount(randomConvertButtonPressed);
        itemStackHandler.setStackInSlot(OUTPUT_SLOT, outputItemstack);
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

    private void toolConvertItem(ItemStack outputItemstack) {
        convertAmount = 1;
        DataComponentMap INPUT_ITEM_COMPONENTS = itemStackHandler.getStackInSlot(INPUT_SLOT).getComponents();
        itemStackHandler.setStackInSlot(OUTPUT_SLOT, outputItemstack);
        itemStackHandler.getStackInSlot(OUTPUT_SLOT).applyComponents(INPUT_ITEM_COMPONENTS);
    }

    public ItemStack[] getRandomConvertRecipe() {
        ItemStack OUPUT_ITEMSTACK = ItemStack.EMPTY;
        ItemStack INPUT_ITEMSTACK = ItemStack.EMPTY;
        Item INPUT_ITEM = itemStackHandler.getStackInSlot(INPUT_SLOT).getItem();

        if (AtmScreen.randomConvertButtonPressed == 1) {
            if (INPUT_ITEM == Items.DIAMOND && (itemStackHandler.getStackInSlot(INPUT_SLOT).getCount() >= ModEvents.diamondToRuby[0])) {
                INPUT_ITEMSTACK = new ItemStack(Items.DIAMOND, ModEvents.diamondToRuby[0]);
                OUPUT_ITEMSTACK = new ItemStack(ModItems.RUBY.get(), ModEvents.diamondToRuby[1]);
            }
        } else if (AtmScreen.randomConvertButtonPressed == 2) {
            if (INPUT_ITEM == Items.DIAMOND && (itemStackHandler.getStackInSlot(INPUT_SLOT).getCount() >= ModEvents.diamondToAmber[0])) {
                INPUT_ITEMSTACK = new ItemStack(Items.DIAMOND, ModEvents.diamondToAmber[0]);
                OUPUT_ITEMSTACK = new ItemStack(ModItems.AMBER.get(), ModEvents.diamondToAmber[1]);
            }
        } else if (AtmScreen.randomConvertButtonPressed == 3) {
            if (INPUT_ITEM == ModItems.RUBY.get() && (itemStackHandler.getStackInSlot(INPUT_SLOT).getCount() >= ModEvents.rubyToDiamond[0])) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.RUBY.get(), ModEvents.rubyToDiamond[0]);
                OUPUT_ITEMSTACK = new ItemStack(Items.DIAMOND, ModEvents.rubyToDiamond[1]);
            }
        } else if (AtmScreen.randomConvertButtonPressed == 4) {
            if (INPUT_ITEM == ModItems.RUBY.get() && (itemStackHandler.getStackInSlot(INPUT_SLOT).getCount() >= ModEvents.rubyToAmber[0])) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.RUBY.get(), ModEvents.rubyToAmber[0]);
                OUPUT_ITEMSTACK = new ItemStack(ModItems.AMBER.get(), ModEvents.rubyToAmber[1]);
            }
        } else if (AtmScreen.randomConvertButtonPressed == 5) {
            if (INPUT_ITEM == ModItems.AMBER.get() && (itemStackHandler.getStackInSlot(INPUT_SLOT).getCount() >= ModEvents.amberToDiamond[0])) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.AMBER.get(), ModEvents.amberToDiamond[0]);
                OUPUT_ITEMSTACK = new ItemStack(Items.DIAMOND, ModEvents.amberToDiamond[1]);
            }
        } else if (AtmScreen.randomConvertButtonPressed == 6) {
            if (INPUT_ITEM == ModItems.AMBER.get() && (itemStackHandler.getStackInSlot(INPUT_SLOT).getCount() >= ModEvents.amberToRuby[0])) {
                INPUT_ITEMSTACK = new ItemStack(ModItems.AMBER.get(), ModEvents.amberToRuby[0]);
                OUPUT_ITEMSTACK = new ItemStack(ModItems.RUBY.get(), ModEvents.amberToRuby[1]);
            }
        }
        return new ItemStack[]{INPUT_ITEMSTACK, OUPUT_ITEMSTACK};
    }

    private ItemStack[] getToolConvertRecipe() {
        ItemStack OUPUT_ITEMSTACK = ItemStack.EMPTY;
        ItemStack INPUT_ITEMSTACK = ItemStack.EMPTY;
        Item INPUT_ITEM = itemStackHandler.getStackInSlot(INPUT_SLOT).getItem();

        if (AtmScreen.toolConvertButtonPressed == 1) {
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

        } else if (AtmScreen.toolConvertButtonPressed == 2) {
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

        } else if (AtmScreen.toolConvertButtonPressed == 3) {
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
        if (AtmScreen.randomConvertScreen && !itemStackHandler.getStackInSlot(OUTPUT_SLOT).isEmpty()) {
            if (itemStackHandler.getStackInSlot(OUTPUT_SLOT).getItem() == Items.DIAMOND) {
                if ((itemStackHandler.getStackInSlot(OUTPUT_SLOT).getCount() == ModEvents.rubyToDiamond[1]) && AtmScreen.randomConvertButtonPressed != 3) {
                    itemStackHandler.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);
                } else if ((itemStackHandler.getStackInSlot(OUTPUT_SLOT).getCount() == ModEvents.amberToDiamond[1]) && AtmScreen.randomConvertButtonPressed != 5) {
                    itemStackHandler.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);
                }
            } else if (itemStackHandler.getStackInSlot(OUTPUT_SLOT).getItem() == ModItems.RUBY.get()) {
                if ((itemStackHandler.getStackInSlot(OUTPUT_SLOT).getCount() == ModEvents.diamondToRuby[1]) && AtmScreen.randomConvertButtonPressed != 1) {
                    itemStackHandler.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);
                } else if ((itemStackHandler.getStackInSlot(OUTPUT_SLOT).getCount() == ModEvents.amberToRuby[1]) && AtmScreen.randomConvertButtonPressed != 6) {
                    itemStackHandler.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);
                }
            } else if (itemStackHandler.getStackInSlot(OUTPUT_SLOT).getItem() == ModItems.AMBER.get()) {
                if ((itemStackHandler.getStackInSlot(OUTPUT_SLOT).getCount() == ModEvents.diamondToAmber[1]) && AtmScreen.randomConvertButtonPressed != 2) {
                    itemStackHandler.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);
                } else if ((itemStackHandler.getStackInSlot(OUTPUT_SLOT).getCount() == ModEvents.rubyToAmber[1]) && AtmScreen.randomConvertButtonPressed != 4) {
                    itemStackHandler.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);
                }
            }

        } else if (AtmScreen.toolConvertScreen && !itemStackHandler.getStackInSlot(OUTPUT_SLOT).isEmpty()) {
            ItemStack inputItemstack = itemStackHandler.getStackInSlot(INPUT_SLOT);
            ItemStack outputItemstack = itemStackHandler.getStackInSlot(OUTPUT_SLOT);

            if (inputItemstack.is(ModItemTags.DIAMOND_CONVERTIBLE_TOOLS) && (outputItemstack.is(ModItemTags.RUBY_CONVERTIBLE_TOOLS)) && AtmScreen.toolConvertButtonPressed != 1) {
                itemStackHandler.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);
            } else if (inputItemstack.is(ModItemTags.DIAMOND_CONVERTIBLE_TOOLS) && (outputItemstack.is(ModItemTags.AMBER_CONVERTIBLE_TOOLS)) && AtmScreen.toolConvertButtonPressed != 1) {
                itemStackHandler.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);
            } else if (inputItemstack.is(ModItemTags.RUBY_CONVERTIBLE_TOOLS) && (outputItemstack.is(ModItemTags.DIAMOND_CONVERTIBLE_TOOLS)) && AtmScreen.toolConvertButtonPressed != 2) {
                itemStackHandler.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);
            } else if (inputItemstack.is(ModItemTags.RUBY_CONVERTIBLE_TOOLS) && (outputItemstack.is(ModItemTags.AMBER_CONVERTIBLE_TOOLS)) && AtmScreen.toolConvertButtonPressed != 2) {
                itemStackHandler.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);
            } else if (inputItemstack.is(ModItemTags.AMBER_CONVERTIBLE_TOOLS) && (outputItemstack.is(ModItemTags.DIAMOND_CONVERTIBLE_TOOLS)) && AtmScreen.toolConvertButtonPressed != 3) {
                itemStackHandler.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);
            } else if (inputItemstack.is(ModItemTags.AMBER_CONVERTIBLE_TOOLS) && (outputItemstack.is(ModItemTags.RUBY_CONVERTIBLE_TOOLS)) && AtmScreen.toolConvertButtonPressed != 3) {
                itemStackHandler.setStackInSlot(OUTPUT_SLOT, ItemStack.EMPTY);
            }
        }
    }

    private boolean hasRecipe(ItemStack inputItemstack, ItemStack outputItemstack) {
        return canInsertAmountIntoOutputSlot(outputItemstack.getCount()) && canInsertItemIntoOutputSlot(outputItemstack)
                && this.itemStackHandler.getStackInSlot(INPUT_SLOT).getItem() == inputItemstack.getItem();
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

    private static int getRandomConvertInputAmount(int conversionButtonPressed) {
        int amount = 0;

        if (conversionButtonPressed == 1) {
            amount = ModEvents.diamondToRuby[0];
        } else if (conversionButtonPressed == 2) {
            amount = ModEvents.diamondToAmber[0];
        } else if (conversionButtonPressed == 3) {
            amount = ModEvents.rubyToDiamond[0];
        } else if (conversionButtonPressed == 4) {
            amount = ModEvents.rubyToAmber[0];
        } else if (conversionButtonPressed == 5) {
            amount = ModEvents.amberToDiamond[0];
        } else if (conversionButtonPressed == 6) {
            amount = ModEvents.amberToRuby[0];
        }
        return amount;
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