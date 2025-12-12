package com.timeshipmodding.villagecraft3essentials.event.registries;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.compat.luckperms.LuckpermsMethods;
import com.timeshipmodding.villagecraft3essentials.interfacing.IHorseInventoryMenuEntity;
import com.timeshipmodding.villagecraft3essentials.networking.handler.client.HorseCurrencyArmorClientHandler;
import com.timeshipmodding.villagecraft3essentials.networking.packet.HorseSyncCurrencyArmorEquipPacket;
import com.timeshipmodding.villagecraft3essentials.util.itemhandler.HorseCurrencyArmorItemHandler;
import com.timeshipmodding.villagecraft3essentials.util.tags.registries.ModItemTags;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetCarriedItemPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.TickTask;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.inventory.HorseInventoryMenu;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.LogicalSide;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@EventBusSubscriber(modid = VillageCraft3Essentials.MODID)
public class ModCurrencyToolsEvents {
    private static boolean cancelDamage = false;

    @SubscribeEvent
    public static void onPlayerLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        if (ModList.get().isLoaded("luckperms")) {
            ItemStack stack = event.getItemStack();

            if (event.getSide() == LogicalSide.CLIENT) {
                return;
            }

            if (LuckpermsMethods.isInGroup(event.getEntity(), "villagecraftcity") && stack.is(ModItemTags.RUBY_CONVERTIBLE_TOOLS) || LuckpermsMethods.isInGroup(event.getEntity(), "villagecraftcity") && stack.is(ModItemTags.AMBER_CONVERTIBLE_TOOLS)) {
                if (stack.getItem() instanceof ArmorItem) {
                    event.getEntity().displayClientMessage(Component.translatable("actionbar.villagecraft3essentials.armor_conversation_hint"), true);

                } else {
                    event.getEntity().displayClientMessage(Component.translatable("actionbar.villagecraft3essentials.tool_conversation_hint"), true);
                }

                event.getEntity().drop(stack.copy(), true);
                stack.shrink(1);
                event.setCanceled(true);

            } else if (LuckpermsMethods.isInGroup(event.getEntity(), "grippercity") && stack.is(ModItemTags.DIAMOND_CONVERTIBLE_TOOLS) || LuckpermsMethods.isInGroup(event.getEntity(), "grippercity") && stack.is(ModItemTags.AMBER_CONVERTIBLE_TOOLS)) {
                if (stack.getItem() instanceof ArmorItem) {
                    event.getEntity().displayClientMessage(Component.translatable("actionbar.villagecraft3essentials.armor_conversation_hint"), true);

                } else {
                    event.getEntity().displayClientMessage(Component.translatable("actionbar.villagecraft3essentials.tool_conversation_hint"), true);
                }

                event.getEntity().drop(stack.copy(), true);
                stack.shrink(1);
                event.setCanceled(true);

            } else if (LuckpermsMethods.isInGroup(event.getEntity(), "ambercaves") && stack.is(ModItemTags.DIAMOND_CONVERTIBLE_TOOLS) || LuckpermsMethods.isInGroup(event.getEntity(), "ambercaves") && stack.is(ModItemTags.RUBY_CONVERTIBLE_TOOLS)) {
                if (stack.getItem() instanceof ArmorItem) {
                    event.getEntity().displayClientMessage(Component.translatable("actionbar.villagecraft3essentials.armor_conversation_hint"), true);

                } else {
                    event.getEntity().displayClientMessage(Component.translatable("actionbar.villagecraft3essentials.tool_conversation_hint"), true);
                }

                event.getEntity().drop(stack.copy(), true);
                stack.shrink(1);
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (ModList.get().isLoaded("luckperms")) {
            ItemStack stack = event.getItemStack();

            if (event.getSide() == LogicalSide.CLIENT) {
                return;
            }

            if (stack.getItem() instanceof ArmorItem) {
                return;
            }

            if (LuckpermsMethods.isInGroup(event.getEntity(), "villagecraftcity") && stack.is(ModItemTags.RUBY_CONVERTIBLE_TOOLS) || LuckpermsMethods.isInGroup(event.getEntity(), "villagecraftcity") && stack.is(ModItemTags.AMBER_CONVERTIBLE_TOOLS)) {
                event.getEntity().displayClientMessage(Component.translatable("actionbar.villagecraft3essentials.tool_conversation_hint"), true);
                event.getEntity().drop(stack.copy(), true);
                stack.shrink(1);
                event.setCancellationResult(InteractionResult.FAIL);
                event.setCanceled(true);

            } else if (LuckpermsMethods.isInGroup(event.getEntity(), "grippercity") && stack.is(ModItemTags.DIAMOND_CONVERTIBLE_TOOLS) || LuckpermsMethods.isInGroup(event.getEntity(), "grippercity") && stack.is(ModItemTags.AMBER_CONVERTIBLE_TOOLS)) {
                event.getEntity().displayClientMessage(Component.translatable("actionbar.villagecraft3essentials.tool_conversation_hint"), true);
                event.getEntity().drop(stack.copy(), true);
                stack.shrink(1);
                event.setCancellationResult(InteractionResult.FAIL);
                event.setCanceled(true);

            } else if (LuckpermsMethods.isInGroup(event.getEntity(), "ambercaves") && stack.is(ModItemTags.DIAMOND_CONVERTIBLE_TOOLS) || LuckpermsMethods.isInGroup(event.getEntity(), "ambercaves") && stack.is(ModItemTags.RUBY_CONVERTIBLE_TOOLS)) {
                event.getEntity().displayClientMessage(Component.translatable("actionbar.villagecraft3essentials.tool_conversation_hint"), true);
                event.getEntity().drop(stack.copy(), true);
                stack.shrink(1);
                event.setCancellationResult(InteractionResult.FAIL);
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerEntityInteract(PlayerInteractEvent.EntityInteract event) {
        if (ModList.get().isLoaded("luckperms")) {
            ItemStack stack = event.getItemStack();

            if (event.getLevel().isClientSide()) {
                if (stack.getItem() instanceof AnimalArmorItem) {
                    if (!HorseCurrencyArmorClientHandler.canEquip()) {
                        event.setCanceled(true);
                        event.setCancellationResult(InteractionResult.FAIL);
                    }
                }

                return;
            }

            if (event.getTarget() instanceof AbstractHorse && stack.getItem() instanceof AnimalArmorItem) {
                HorseCurrencyArmorItemHandler handler = event.getTarget().getCapability(ModCapabilityEvents.HORSE_ARMOR_CAPABILITY);
                boolean canEquip = false;

                if (handler != null) {
                    canEquip = handler.canPlayerEquip(event.getEntity(), stack);
                }

                if (event.getEntity() instanceof ServerPlayer serverPlayer) {
                    PacketDistributor.sendToPlayer(serverPlayer, new HorseSyncCurrencyArmorEquipPacket(canEquip));
                    serverPlayer.getServer().tell(new TickTask(serverPlayer.getServer().getTickCount() + 1, () -> {
                        PacketDistributor.sendToPlayer(serverPlayer, new HorseSyncCurrencyArmorEquipPacket(false));
                    }));
                }

                if (!canEquip) {
                    event.getEntity().displayClientMessage(Component.translatable("actionbar.villagecraft3essentials.armor_conversation_hint"), true);
                    event.getEntity().drop(stack.copy(), true);
                    stack.shrink(1);
                    event.setCancellationResult(InteractionResult.FAIL);
                    event.setCanceled(true);

                    if (event.getEntity() instanceof ServerPlayer serverPlayer && event.getTarget() instanceof Horse horse) {
                        List<SynchedEntityData.DataValue<?>> changes = horse.getEntityData().packDirty();
                        if (changes != null && !changes.isEmpty()) {
                            ClientboundSetEntityDataPacket packet = new ClientboundSetEntityDataPacket(horse.getId(), changes);
                            serverPlayer.connection.send(packet);
                        }
                    }

                } else {
                    if (event.getEntity() instanceof ServerPlayer serverPlayer) {
                        serverPlayer.connection.send(new ClientboundSetCarriedItemPacket(serverPlayer.getInventory().selected));
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTickPost(PlayerTickEvent.Post event) {
        if (ModList.get().isLoaded("luckperms")) {
            if (event.getEntity().level().isClientSide()) return;

            if (event.getEntity().containerMenu instanceof HorseInventoryMenu horseInventoryMenu) {
                ItemStack stack = horseInventoryMenu.getSlot(1).getItem();

                if (!stack.isEmpty() && horseInventoryMenu instanceof IHorseInventoryMenuEntity iHorseInventoryMenuEntity) {
                    HorseCurrencyArmorItemHandler handler = iHorseInventoryMenuEntity.getEntity().getCapability(ModCapabilityEvents.HORSE_ARMOR_CAPABILITY);

                    if (handler != null && !handler.canPlayerEquip(event.getEntity(), stack)) {
                        ItemStack stackToDrop = horseInventoryMenu.getSlot(1).remove(stack.getCount());
                        event.getEntity().drop(stackToDrop.copy(), true);
                        stackToDrop.shrink(1);
                        horseInventoryMenu.getSlot(0).setChanged();
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerAttack(AttackEntityEvent event) {
        if (ModList.get().isLoaded("luckperms")) {
            ItemStack stack = event.getEntity().getInventory().getSelected();

            if (event.getEntity().level().isClientSide) {
                return;
            }

            if (LuckpermsMethods.isInGroup(event.getEntity(), "villagecraftcity") && stack.is(ModItemTags.RUBY_CONVERTIBLE_TOOLS) || LuckpermsMethods.isInGroup(event.getEntity(), "villagecraftcity") && stack.is(ModItemTags.AMBER_CONVERTIBLE_TOOLS)) {
                if (stack.getItem() instanceof ArmorItem) {
                    event.getEntity().displayClientMessage(Component.translatable("actionbar.villagecraft3essentials.armor_conversation_hint"), true);

                } else {
                    event.getEntity().displayClientMessage(Component.translatable("actionbar.villagecraft3essentials.tool_conversation_hint"), true);
                }

                cancelDamage = true;
                event.getEntity().drop(stack.copy(), true);
                stack.shrink(1);
                event.setCanceled(true);

            } else if (LuckpermsMethods.isInGroup(event.getEntity(), "grippercity") && stack.is(ModItemTags.DIAMOND_CONVERTIBLE_TOOLS) || LuckpermsMethods.isInGroup(event.getEntity(), "grippercity") && stack.is(ModItemTags.AMBER_CONVERTIBLE_TOOLS)) {
                if (stack.getItem() instanceof ArmorItem) {
                    event.getEntity().displayClientMessage(Component.translatable("actionbar.villagecraft3essentials.armor_conversation_hint"), true);

                } else {
                    event.getEntity().displayClientMessage(Component.translatable("actionbar.villagecraft3essentials.tool_conversation_hint"), true);
                }

                cancelDamage = true;
                event.getEntity().drop(stack.copy(), true);
                stack.shrink(1);
                event.setCanceled(true);

            } else if (LuckpermsMethods.isInGroup(event.getEntity(), "ambercaves") && stack.is(ModItemTags.DIAMOND_CONVERTIBLE_TOOLS) || LuckpermsMethods.isInGroup(event.getEntity(), "ambercaves") && stack.is(ModItemTags.RUBY_CONVERTIBLE_TOOLS)) {
                if (stack.getItem() instanceof ArmorItem) {
                    event.getEntity().displayClientMessage(Component.translatable("actionbar.villagecraft3essentials.armor_conversation_hint"), true);

                } else {
                    event.getEntity().displayClientMessage(Component.translatable("actionbar.villagecraft3essentials.tool_conversation_hint"), true);
                }

                cancelDamage = true;
                event.getEntity().drop(stack.copy(), true);
                stack.shrink(1);
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onEntityHurt(@NotNull LivingIncomingDamageEvent event) {
        if (ModList.get().isLoaded("luckperms")) {
            if (!event.getEntity().level().isClientSide && cancelDamage) {
                event.setCanceled(true);
                cancelDamage = false;
            }
        }
    }
}