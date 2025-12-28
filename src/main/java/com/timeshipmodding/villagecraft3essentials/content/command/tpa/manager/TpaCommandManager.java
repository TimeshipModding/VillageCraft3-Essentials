package com.timeshipmodding.villagecraft3essentials.content.command.tpa.manager;

import com.timeshipmodding.villagecraft3essentials.infrastructure.data.TpaCommandData;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
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
    private static Component requestingPlayerUsername;
    private static Component targetPlayerUsername;
    private static String teleportType;

    public static void requestTpa(ServerPlayer requestingPlayer, ServerPlayer targetPlayer, String commandType) {
        requestingPlayerUsername = Objects.requireNonNull(requestingPlayer.getDisplayName());
        targetPlayerUsername = Objects.requireNonNull(targetPlayer.getDisplayName());
        teleportType = commandType;

        if (requestingPlayer.getUUID().equals(targetPlayer.getUUID())) {
            requestingPlayer.sendSystemMessage(Component.literal("You can't send a TPA request to yourself.").withStyle(ChatFormatting.RED));
            return;
        }

        if (pendingTpaRequests.containsKey(targetPlayer.getUUID())) {
            MutableComponent message = Component.literal("You already sent a TPA request to ").withStyle(ChatFormatting.RED);
            message.append(requestingPlayerUsername);
            message.append(Component.literal("Wait for them to type ").withStyle(ChatFormatting.RED));
            message.append(Component.literal("/tpaccept").withStyle(ChatFormatting.GREEN));
            message.append(Component.literal(".").withStyle(ChatFormatting.RED));
            requestingPlayer.sendSystemMessage(message);
            return;
        }

        TpaCommandData data = new TpaCommandData(requestingPlayer.getUUID(), System.currentTimeMillis());
        pendingTpaRequests.put(targetPlayer.getUUID(), data);

        if (Objects.equals(teleportType, "tpa")) {
            MutableComponent requestingPlayerMessage = Component.literal("You sent a TPA request to ");
            requestingPlayerMessage.append(targetPlayerUsername);
            requestingPlayerMessage.append(". Waiting for them to type ");
            requestingPlayerMessage.append(Component.literal("/tpaccept").withStyle(ChatFormatting.GREEN));
            requestingPlayerMessage.append(Component.literal("..."));
            MutableComponent targetPlayerMessage = (MutableComponent) requestingPlayerUsername;
            targetPlayerMessage.append(" has requested to TPA to you! Type ");
            targetPlayerMessage.append(Component.literal("/tpaccept").withStyle(ChatFormatting.GREEN));
            targetPlayerMessage.append(Component.literal(" to accept or "));
            targetPlayerMessage.append(Component.literal("/tpdeny").withStyle(ChatFormatting.RED));
            targetPlayerMessage.append(Component.literal(" to deny."));
            requestingPlayer.sendSystemMessage(requestingPlayerMessage);
            targetPlayer.sendSystemMessage(targetPlayerMessage);

        } else if (Objects.equals(teleportType, "tpahere")) {
            MutableComponent requestingPlayerMessage = Component.literal("You sent a TPA here request to ");
            requestingPlayerMessage.append(targetPlayerUsername);
            requestingPlayerMessage.append(". Waiting for them to type ");
            requestingPlayerMessage.append(Component.literal("/tpaccept").withStyle(ChatFormatting.GREEN));
            requestingPlayerMessage.append(Component.literal("..."));
            MutableComponent targetPlayerMessage = (MutableComponent) requestingPlayerUsername;
            targetPlayerMessage.append(" has requested to TPA here to them! Type ");
            targetPlayerMessage.append(Component.literal("/tpaccept").withStyle(ChatFormatting.GREEN));
            targetPlayerMessage.append(Component.literal(" to accept or "));
            targetPlayerMessage.append(Component.literal("/tpdeny").withStyle(ChatFormatting.RED));
            targetPlayerMessage.append(Component.literal(" to deny."));
            requestingPlayer.sendSystemMessage(requestingPlayerMessage);
            targetPlayer.sendSystemMessage(targetPlayerMessage);
        }
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
                MutableComponent requestingPlayerMessage = Component.literal("You have been teleported to ");
                requestingPlayerMessage.append(targetPlayerUsername);
                requestingPlayerMessage.append(Component.literal("!"));
                MutableComponent targetPlayerMessage = Component.literal("You accepted a TPA request from ");
                targetPlayerMessage.append(requestingPlayerUsername);
                targetPlayerMessage.append(Component.literal("!"));
                requestingPlayer.sendSystemMessage(requestingPlayerMessage);
                targetPlayer.sendSystemMessage(targetPlayerMessage);

            } else if (Objects.equals(teleportType, "tpahere")) {
                int targetPlayerYaw = (int) requestingPlayer.getYRot();
                int targetPlayerPitch = (int) requestingPlayer.getXRot();
                targetPlayer.teleportTo(serverlevel, requestingPlayer.getX(), requestingPlayer.getY(), requestingPlayer.getZ(), targetPlayerYaw, targetPlayerPitch);
                MutableComponent requestingPlayerMessage = (MutableComponent) targetPlayerUsername;
                requestingPlayerMessage.append(Component.literal(" has been teleported to you!"));
                MutableComponent targetPlayerMessage = Component.literal("You accepted a TPA here request from ");
                targetPlayerMessage.append(requestingPlayerUsername);
                targetPlayerMessage.append(Component.literal(" and have been teleported to them!"));
                requestingPlayer.sendSystemMessage(requestingPlayerMessage);
                targetPlayer.sendSystemMessage(targetPlayerMessage);
            }

            pendingTpaRequests.remove(playerUuid);
            return true;

        } else {
            targetPlayer.sendSystemMessage(Component.literal("You have no pending tpa requests to accept").withStyle(ChatFormatting.RED));
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
                MutableComponent message = Component.literal("Your tpa request to ").withStyle(ChatFormatting.RED);
                message.append(targetPlayerUsername);
                message.append(Component.literal(" has been denied.").withStyle(ChatFormatting.RED));
                player.sendSystemMessage(message);
            }

            targetPlayer.sendSystemMessage(Component.literal("You denied a teleport request from " + requestingPlayerUsername));
            pendingTpaRequests.remove(requestingPlayerUuid);
            return true;

        } else {
            targetPlayer.sendSystemMessage(Component.literal("You have no pending tpa requests to deny.").withStyle(ChatFormatting.RED));
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
                    requestingPlayer.sendSystemMessage(Component.literal("Your pending tpa request has expired.").withStyle(ChatFormatting.RED));
                }
            }
        }
    }
}