package com.timeshipmodding.villagecraft3essentials.event.registries;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.content.command.WorldSpawnCommand;
import com.timeshipmodding.villagecraft3essentials.content.command.ambercaves.*;
import com.timeshipmodding.villagecraft3essentials.content.command.grippercity.*;
import com.timeshipmodding.villagecraft3essentials.content.command.tpa.TpaCommand;
import com.timeshipmodding.villagecraft3essentials.content.command.tpa.TpacceptCommand;
import com.timeshipmodding.villagecraft3essentials.content.command.tpa.TpadenyCommand;
import com.timeshipmodding.villagecraft3essentials.content.command.tpa.TpahereCommand;
import com.timeshipmodding.villagecraft3essentials.content.command.villagecraftcity.*;
import com.timeshipmodding.villagecraft3essentials.infrastructure.networking.packet.atm.AtmRandomConversionRatesPacket;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.saveddata.AtmRandomConversionRatesSavedData;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.server.command.ConfigCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@EventBusSubscriber(modid = VillageCraft3Essentials.MODID)
public class ModEvents {
    public static final Map<UUID, TeleportCommandsData> pendingTeleports = new HashMap<>();
    public static final Map<UUID, TpaCommandData> pendingTPAs = new HashMap<>();
    public record TeleportCommandsData(ServerLevel serverLevel, int ticksLeft, Vec3 startPos, int[] destination, Component teleportMessage) {}
    public record TpaCommandData(ServerLevel serverLevel, int ticksLeft, Vec3 startPos, double[] destination, ServerPlayer targetPlayer, Component targetPlayerMessage, Component requestingPlayerMessage, String teleportType) {}

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new AmberCavesBlacklistCommand(event.getDispatcher());
        new AmberCavesJailCommand(event.getDispatcher());
        new AmberCavesKickCommand(event.getDispatcher());
        new AmberCavesPardonCommand(event.getDispatcher());
        new AmberCavesSetJailCommand(event.getDispatcher());
        new AmberCavesSetSpawnCommand(event.getDispatcher());
        new AmberCavesSpawnCommand(event.getDispatcher());
        new AmberCavesWhitelistCommand(event.getDispatcher());
        new GripperCityBlacklistCommand(event.getDispatcher());
        new GripperCityJailCommand(event.getDispatcher());
        new GripperCityKickCommand(event.getDispatcher());
        new GripperCityPardonCommand(event.getDispatcher());
        new GripperCitySetJailCommand(event.getDispatcher());
        new GripperCitySetSpawnCommand(event.getDispatcher());
        new GripperCitySpawnCommand(event.getDispatcher());
        new GripperCityWhitelistCommand(event.getDispatcher());
        new TpacceptCommand(event.getDispatcher());
        new TpaCommand(event.getDispatcher());
        new TpadenyCommand(event.getDispatcher());
        new TpahereCommand(event.getDispatcher());
        new VillageCraftCityBlacklistCommand(event.getDispatcher());
        new VillageCraftCityJailCommand(event.getDispatcher());
        new VillageCraftCityKickCommand(event.getDispatcher());
        new VillageCraftCityPardonCommand(event.getDispatcher());
        new VillageCraftCitySetJailCommand(event.getDispatcher());
        new VillageCraftCitySetSpawnCommand(event.getDispatcher());
        new VillageCraftCitySpawnCommand(event.getDispatcher());
        new VillageCraftCityWhitelistCommand(event.getDispatcher());
        new WorldSpawnCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onPlayerJoined(OnDatapackSyncEvent event) {
        if (event.getPlayer() instanceof ServerPlayer player) {
            MinecraftServer server = player.getServer();

            if (server != null) {
                int[] diamondToRuby = AtmRandomConversionRatesSavedData.getData(server).getDiamondToRuby();
                int[] diamondToAmber = AtmRandomConversionRatesSavedData.getData(server).getDiamondToAmber();
                int[] rubyToDiamond = AtmRandomConversionRatesSavedData.getData(server).getRubyToDiamond();
                int[] rubyToAmber = AtmRandomConversionRatesSavedData.getData(server).getRubyToAmber();
                int[] amberToDiamond = AtmRandomConversionRatesSavedData.getData(server).getAmberToDiamond();
                int[] amberToRuby = AtmRandomConversionRatesSavedData.getData(server).getAmberToRuby();

                if (diamondToRuby != null && diamondToRuby.length > 0) {
                    PacketDistributor.sendToPlayer(player, new AtmRandomConversionRatesPacket(diamondToRuby, diamondToAmber, rubyToDiamond, rubyToAmber, amberToDiamond, amberToRuby));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (event.getEntity().level().isClientSide()) return;

        ServerPlayer player = (ServerPlayer) event.getEntity();
        UUID uuid = player.getUUID();

        if (pendingTeleports.containsKey(uuid)) {
            TeleportCommandsData data = pendingTeleports.get(uuid);

            if (player.position().distanceToSqr(data.startPos) > 0.01) {
                player.sendSystemMessage(Component.literal("Teleport cancelled: you moved.").withStyle(ChatFormatting.RED));
                pendingTeleports.remove(uuid);
                return;
            }

            if (data.ticksLeft <= 0) {
                int[] destination = data.destination;
                player.teleportTo(data.serverLevel, destination[0] + 0.5, destination[1], destination[2] + 0.5, destination[3], destination[4]);
                player.sendSystemMessage(data.teleportMessage);
                pendingTeleports.remove(uuid);

            } else {
                if (data.ticksLeft % 20 == 0) {
                    player.displayClientMessage(Component.literal("Teleporting in " + (data.ticksLeft / 20) + "...").withStyle(ChatFormatting.GOLD), true);
                }

                pendingTeleports.put(uuid, new TeleportCommandsData(data.serverLevel, data.ticksLeft - 1, data.startPos, data.destination, data.teleportMessage));
            }
        }

        if (pendingTPAs.containsKey(uuid)) {
            TpaCommandData data = pendingTPAs.get(uuid);

            if (Objects.equals(data.teleportType, "tpa")) {
                if (player.position().distanceToSqr(data.startPos) > 0.01) {
                    player.sendSystemMessage(Component.literal("TPA cancelled: you moved.").withStyle(ChatFormatting.RED));
                    MutableComponent targetPlayerMessage = Component.literal("TPA cancelled: ").withStyle(ChatFormatting.RED);

                    if (ModList.get().isLoaded("luckperms")) {
                        targetPlayerMessage.append(player.getDisplayName().copy());
                    } else {
                        targetPlayerMessage.append(player.getDisplayName().copy().withStyle(ChatFormatting.WHITE));
                    }

                    targetPlayerMessage.append(Component.literal(" moved.").withStyle(ChatFormatting.RED));
                    data.targetPlayer.sendSystemMessage(targetPlayerMessage);
                    pendingTPAs.remove(uuid);
                    return;
                }

                if (data.ticksLeft <= 0) {
                    double[] destination = data.destination;
                    player.teleportTo(data.serverLevel, destination[0], destination[1], destination[2], player.getYRot(), player.getXRot());
                    player.sendSystemMessage(data.requestingPlayerMessage);
                    data.targetPlayer.sendSystemMessage(data.targetPlayerMessage);
                    pendingTPAs.remove(uuid);

                } else {
                    if (data.ticksLeft % 20 == 0) {
                        player.displayClientMessage(Component.literal("Teleporting in " + (data.ticksLeft / 20) + "...").withStyle(ChatFormatting.GOLD), true);
                        MutableComponent targetPlayerMessage = player.getDisplayName().copy();
                        targetPlayerMessage.append(Component.literal(" is teleporting to you in " + (data.ticksLeft / 20) + "...").withStyle(ChatFormatting.GOLD));
                        data.targetPlayer.displayClientMessage(targetPlayerMessage, true);
                    }

                    pendingTPAs.put(uuid, new TpaCommandData(data.serverLevel, data.ticksLeft - 1, data.startPos, data.destination(), data.targetPlayer(), data.targetPlayerMessage(), data.requestingPlayerMessage(), data.teleportType()));
                }

            } else if (Objects.equals(data.teleportType, "tpahere")) {
                if (data.targetPlayer.position().distanceToSqr(data.startPos) > 0.01) {
                    data.targetPlayer.sendSystemMessage(Component.literal("TPA here cancelled: you moved.").withStyle(ChatFormatting.RED));
                    MutableComponent requestingPlayerMessage = Component.literal("TPA here cancelled: ").withStyle(ChatFormatting.RED);

                    if (ModList.get().isLoaded("luckperms")) {
                        requestingPlayerMessage.append(data.targetPlayer.getDisplayName().copy());
                    } else {
                        requestingPlayerMessage.append(data.targetPlayer.getDisplayName().copy().withStyle(ChatFormatting.WHITE));
                    }

                    requestingPlayerMessage.append(Component.literal(" moved.").withStyle(ChatFormatting.RED));
                    player.sendSystemMessage(requestingPlayerMessage);
                    pendingTPAs.remove(uuid);
                    return;
                }

                if (data.ticksLeft <= 0) {
                    double[] destination = data.destination;
                    data.targetPlayer.teleportTo(data.serverLevel, destination[0], destination[1], destination[2], data.targetPlayer.getYRot(), data.targetPlayer.getXRot());
                    data.targetPlayer.sendSystemMessage(data.targetPlayerMessage);
                    player.sendSystemMessage(data.requestingPlayerMessage);
                    pendingTPAs.remove(uuid);

                } else {
                    if (data.ticksLeft % 20 == 0) {
                        data.targetPlayer.displayClientMessage(Component.literal("Teleporting in " + (data.ticksLeft / 20) + "...").withStyle(ChatFormatting.GOLD), true);
                        MutableComponent requestingPlayerMessage = data.targetPlayer.getDisplayName().copy();
                        requestingPlayerMessage.append(Component.literal(" is teleporting to you in " + (data.ticksLeft / 20) + "...").withStyle(ChatFormatting.GOLD));
                        player.displayClientMessage(requestingPlayerMessage, true);
                    }

                    pendingTPAs.put(uuid, new TpaCommandData(data.serverLevel, data.ticksLeft - 1, data.startPos, data.destination, data.targetPlayer, data.targetPlayerMessage, data.requestingPlayerMessage, data.teleportType));
                }
            }
        }
    }
}