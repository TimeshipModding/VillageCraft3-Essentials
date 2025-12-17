package com.timeshipmodding.villagecraft3essentials.content.command.tpa.manager;

import com.electronwill.nightconfig.core.AbstractCommentedConfig;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.TpaCommandData;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class TpaCommandManager {
    private static final Map<UUID, TpaCommandData> pendingTpaRequests = new ConcurrentHashMap<>();
    private static final long TIMEOUT_DURATION_MS = TimeUnit.MINUTES.toMillis(5);
    private static String requestingPlayerUsername;
    private static String targetPlayerUsername;
    private static String teleportType;

    public static void requestTpa(ServerPlayer requestingPlayer, ServerPlayer targetPlayer, String commandType) {
        requestingPlayerUsername = requestingPlayer.getName().getString();
        targetPlayerUsername = targetPlayer.getName().getString();
        teleportType = commandType;

        if (requestingPlayer.getUUID().equals(targetPlayer.getUUID())) {
            requestingPlayer.sendSystemMessage(Component.literal("§cYou can't send a teleport request to yourself"));
            return;
        }

        if (pendingTpaRequests.containsKey(targetPlayer.getUUID())) {
            requestingPlayer.sendSystemMessage(Component.literal("§cYou already sent a TPA request to " + requestingPlayerUsername + ". §cWait for them to type §a/tpaccept§r"));
            return;
        }

        TpaCommandData data = new TpaCommandData(requestingPlayer.getUUID(), System.currentTimeMillis());
        pendingTpaRequests.put(targetPlayer.getUUID(), data);
        requestingPlayer.sendSystemMessage(net.minecraft.network.chat.Component.literal("You sent a teleport request to " + targetPlayerUsername + "! Waiting for them to type §a/tpaccept§r..."));
        targetPlayer.sendSystemMessage(net.minecraft.network.chat.Component.literal(requestingPlayerUsername + " has requested to teleport to you! Type §a/tpaccept§r to accept or §a/tpadeny§r to deny"));
    }

    public static boolean acceptTpa(ServerPlayer targetPlayer) {
        UUID targetPlayerUuid = targetPlayer.getUUID();

        if (pendingTpaRequests.containsKey(targetPlayerUuid)) {
            TpaCommandData data = pendingTpaRequests.remove(targetPlayerUuid);
            UUID playerUuid = data.requestingPlayerUUID();
            MinecraftServer server = targetPlayer.getServer();
            ServerLevel serverlevel = server.getLevel(targetPlayer.level().dimension());
            ServerPlayer requestingPlayer = server.getPlayerList().getPlayer(playerUuid);
            assert serverlevel != null;

            if (Objects.equals(teleportType, "tpa")) {
                int requestingPlayerYaw = (int) requestingPlayer.getYRot();
                int requestingPlayerPitch = (int) requestingPlayer.getXRot();
                requestingPlayer.teleportTo(serverlevel, targetPlayer.getX(), targetPlayer.getY(), targetPlayer.getZ(), requestingPlayerYaw, requestingPlayerPitch);
                requestingPlayer.sendSystemMessage(Component.literal("You have been teleported to §e" + targetPlayerUsername + "§r!"));
                targetPlayer.sendSystemMessage(Component.literal("You accepted a teleport request from §e" + requestingPlayerUsername + "§r!"));

            } else if (Objects.equals(teleportType, "tpahere")) {
                int targetPlayerYaw = (int) requestingPlayer.getYRot();
                int targetPlayerPitch = (int) requestingPlayer.getXRot();
                targetPlayer.teleportTo(serverlevel, requestingPlayer.getX(), requestingPlayer.getY(), requestingPlayer.getZ(), targetPlayerYaw, targetPlayerPitch);
                requestingPlayer.sendSystemMessage(Component.literal(targetPlayerUsername + " has been teleported to you!"));
                targetPlayer.sendSystemMessage(Component.literal("You accepted a teleport here request from " + requestingPlayerUsername + " and have been teleported to them!"));
            }

            pendingTpaRequests.remove(playerUuid);
            return true;

        } else {
            targetPlayer.sendSystemMessage(Component.literal("§cYou have no pending tpa requests to accept"));
            return false;
        }
    }

    public static boolean denyTpa(ServerPlayer targetPlayer, Boolean hideMessage) {
        UUID targetPlayerUuid = targetPlayer.getUUID();

        if (pendingTpaRequests.containsKey(targetPlayerUuid)) {
            TpaCommandData data = pendingTpaRequests.remove(targetPlayerUuid);
            UUID requestingPlayerUuid = data.requestingPlayerUUID();
            MinecraftServer server = targetPlayer.getServer();
            ServerPlayer player = server.getPlayerList().getPlayer(requestingPlayerUuid);

            if (!hideMessage) {
                player.sendSystemMessage(Component.literal("§cYour tpa request to " + targetPlayerUsername + " has been denied."));
            }

            targetPlayer.sendSystemMessage(Component.literal("You denied a teleport request from " + requestingPlayerUsername));
            pendingTpaRequests.remove(requestingPlayerUuid);
            return true;

        } else {
            targetPlayer.sendSystemMessage(Component.literal("§cYou have no pending tpa requests to deny."));
            return false;
        }
    }

    public static void cleanupTimedOutChallenges(MinecraftServer server) {
        long currentTime = System.currentTimeMillis();
        Iterator<Map.Entry<UUID, TpaCommandData>> iterator = pendingTpaRequests.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<UUID, TpaCommandData> entry = iterator.next();
            TpaCommandData data = entry.getValue();

            if (currentTime - data.timeCreatedMilliseconds() >= TIMEOUT_DURATION_MS) {
                iterator.remove();
                ServerPlayer requestingPlayer = server.getPlayerList().getPlayer(entry.getKey());

                if (requestingPlayer != null) {
                    requestingPlayer.sendSystemMessage(Component.literal("§cYour pending tpa request has expired."));
                }
            }
        }
    }
}