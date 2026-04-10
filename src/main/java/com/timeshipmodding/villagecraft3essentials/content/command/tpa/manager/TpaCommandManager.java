package com.timeshipmodding.villagecraft3essentials.content.command.tpa.manager;

import com.timeshipmodding.villagecraft3essentials.event.registries.ModEvents;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.TpaCommandData;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.fml.ModList;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class TpaCommandManager {
    private static final Map<UUID, TpaCommandData> pendingTpaRequests = new ConcurrentHashMap<>();
    private static final long TIMEOUT_DURATION_MS = TimeUnit.MINUTES.toMillis(5);

    private static final Style TPACCEPT = Style.EMPTY.withColor(ChatFormatting.GREEN)
            .withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpaccept"))
            .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Component.literal("/tpaccept").withStyle(ChatFormatting.GREEN)));
    private static final Style TPADENY = Style.EMPTY.withColor(ChatFormatting.RED)
            .withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpadeny"))
            .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Component.literal("/tpadeny").withStyle(ChatFormatting.RED)));

    public static void requestTpa(ServerPlayer requestingPlayer, ServerPlayer targetPlayer, String type) {
        if (requestingPlayer.getUUID().equals(targetPlayer.getUUID())) {
            requestingPlayer.sendSystemMessage(Component.literal("You can't send a TPA request to yourself.").withStyle(ChatFormatting.RED));
            return;
        }

        TpaCommandData data = new TpaCommandData(
                requestingPlayer.getUUID(),
                requestingPlayer.getDisplayName(),
                targetPlayer.getDisplayName(),
                type,
                System.currentTimeMillis()
        );

        if (pendingTpaRequests.containsKey(targetPlayer.getUUID())) {
            MutableComponent message = Component.empty();

            if ((Objects.equals(data.type(), "tpa"))) {
                message = Component.literal("You already sent a TPA request to ").withStyle(ChatFormatting.RED);

            } else if ((Objects.equals(data.type(), "tpahere"))) {
                message = Component.literal("You already sent a TPA here request to ").withStyle(ChatFormatting.RED);
            }

            if (ModList.get().isLoaded("luckperms")) {
                message.append(data.targetPlayerName().copy());

            } else {
                message.append(data.targetPlayerName().copy().withStyle(ChatFormatting.WHITE));
            }

            message.append(Component.literal(". Wait for them to type ").withStyle(ChatFormatting.RED));
            message.append(Component.literal("/tpaccept").withStyle(ChatFormatting.GREEN));
            message.append(Component.literal(".").withStyle(ChatFormatting.RED));
            requestingPlayer.sendSystemMessage(message);
            return;
        }

        pendingTpaRequests.put(targetPlayer.getUUID(), data);

        if (Objects.equals(data.type(), "tpa")) {
            MutableComponent requestingPlayerMessage = Component.literal("You sent a TPA request to ");
            requestingPlayerMessage.append(data.targetPlayerName().copy());
            requestingPlayerMessage.append(". Waiting for them to type ");
            requestingPlayerMessage.append(Component.literal("/tpaccept").withStyle(ChatFormatting.GREEN));
            requestingPlayerMessage.append(Component.literal("..."));
            MutableComponent targetPlayerMessage = data.requestingPlayerName().copy();
            targetPlayerMessage.append(" has requested to TPA to you! Type or press ");
            targetPlayerMessage.append(Component.literal("/tpaccept").setStyle(TPACCEPT));
            targetPlayerMessage.append(Component.literal(" to accept or "));
            targetPlayerMessage.append(Component.literal("/tpadeny").setStyle(TPADENY));
            targetPlayerMessage.append(Component.literal(" to deny."));
            requestingPlayer.sendSystemMessage(requestingPlayerMessage);
            targetPlayer.sendSystemMessage(targetPlayerMessage);

        } else if (Objects.equals(data.type(), "tpahere")) {
            MutableComponent requestingPlayerMessage = Component.literal("You sent a TPA here request to ");
            requestingPlayerMessage.append(data.targetPlayerName().copy());
            requestingPlayerMessage.append(". Waiting for them to type ");
            requestingPlayerMessage.append(Component.literal("/tpaccept").withStyle(ChatFormatting.GREEN));
            requestingPlayerMessage.append(Component.literal("..."));
            MutableComponent targetPlayerMessage = data.requestingPlayerName().copy();
            targetPlayerMessage.append(" has requested to TPA here to them! Type or press ");
            targetPlayerMessage.append(Component.literal("/tpaccept").setStyle(TPACCEPT));
            targetPlayerMessage.append(Component.literal(" to accept or "));
            targetPlayerMessage.append(Component.literal("/tpadeny").setStyle(TPADENY));
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

            if (Objects.equals(data.type(), "tpa")) {
                MutableComponent requestingPlayerMessage = Component.literal("You have been teleported to ");
                requestingPlayerMessage.append(data.targetPlayerName().copy());
                requestingPlayerMessage.append(Component.literal("!"));
                MutableComponent targetPlayerMessage = Component.literal("You accepted a TPA request from ");
                targetPlayerMessage.append(data.requestingPlayerName().copy());
                targetPlayerMessage.append(Component.literal("!"));
                System.out.println(targetPlayerMessage);

                ModEvents.pendingTPAs.put(requestingPlayer.getUUID(), new ModEvents.TpaCommandData(
                        serverlevel,
                        40,
                        requestingPlayer.position(),
                        targetPlayer,
                        requestingPlayer,
                        targetPlayerMessage,
                        requestingPlayerMessage,
                        "tpa"
                ));

            } else if (Objects.equals(data.type(), "tpahere")) {
                MutableComponent requestingPlayerMessage = data.targetPlayerName().copy();
                requestingPlayerMessage.append(Component.literal(" has been teleported to you!"));
                MutableComponent targetPlayerMessage = Component.literal("You accepted a TPA here request from ");
                targetPlayerMessage.append(data.requestingPlayerName().copy());
                targetPlayerMessage.append(Component.literal(" and have been teleported to them!"));

                ModEvents.pendingTPAs.put(requestingPlayer.getUUID(), new ModEvents.TpaCommandData(
                        serverlevel,
                        40,
                        targetPlayer.position(),
                        targetPlayer,
                        requestingPlayer,
                        targetPlayerMessage,
                        requestingPlayerMessage,
                        "tpahere"
                ));
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

                if (ModList.get().isLoaded("luckperms")) {
                    message.append(data.targetPlayerName().copy());

                } else {
                    message.append(data.targetPlayerName().copy().withStyle(ChatFormatting.WHITE));
                }

                message.append(Component.literal(" has been denied.").withStyle(ChatFormatting.RED));
                player.sendSystemMessage(message);
            }

            MutableComponent message = Component.literal("You denied a teleport request from ");
            message.append(data.requestingPlayerName().copy());
            targetPlayer.sendSystemMessage(message);
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