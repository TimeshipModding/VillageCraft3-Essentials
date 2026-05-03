package com.timeshipmodding.villagecraft3essentials.content.command.grippercity;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.compat.luckperms.LuckpermsMethods;
import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.JailAndPardonCommandData;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.attachment.registries.ModDataAttachments;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.saveddata.SpawnSavedData;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import net.neoforged.fml.ModList;

import java.util.Collection;
import java.util.Objects;

public class GripperCityPardonCommand {
    public GripperCityPardonCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("grippercity").then(Commands.literal("pardon")
                .then(
                        Commands.argument("targets", EntityArgument.players())
                                .executes(p_137810_ -> execute(p_137810_, EntityArgument.getPlayers(p_137810_, "targets")))
                )));
    }

    private Component targetPlayerUsername;

    private int execute(CommandContext<CommandSourceStack> context, Collection<? extends ServerPlayer> targets) {
        MinecraftServer server = context.getSource().getServer();
        ServerLevel serverlevel = server.getLevel(ServerLevel.OVERWORLD);
        SpawnSavedData savedData = SpawnSavedData.getData(server);
        int[] spawn = savedData.getVillagecraftCitySpawn();
        ChatFormatting groupStyling = ChatFormatting.WHITE;
        MutableComponent targetPlayerMessage;
        BlockPos blockpos = serverlevel.getSharedSpawnPos();

        if (ModList.get().isLoaded("luckperms")) {
            groupStyling = LuckpermsMethods.getGroupStyling(ServerConfig.GRIPPERCITY_GROUP_NAME.get());
        }

        for (ServerPlayer player : targets) {
            player.setGameMode(GameType.SURVIVAL);
            player.setRespawnPosition(ServerLevel.OVERWORLD, blockpos, player.getYRot(), true, false);
            targetPlayerUsername = Objects.requireNonNull(player.getDisplayName());

            if (player.hasData(ModDataAttachments.JAIL_COMMAND_DATA)) {
                JailAndPardonCommandData jailCommandData = player.getData(ModDataAttachments.JAIL_COMMAND_DATA);

                if (jailCommandData.jailReleaseTime() > 0) {
                    player.setData(ModDataAttachments.JAIL_COMMAND_DATA, jailCommandData.reset());
                }
            }

            if (ModList.get().isLoaded("luckperms")) {
                LuckpermsMethods.removeGroup(player, ServerConfig.JAILED_GROUP_NAME.get());
            }
        }

        if (spawn[3] != 0 && spawn[4] != 0 && serverlevel != null) {
            for (ServerPlayer player : targets) {
                player.teleportTo(serverlevel, spawn[0] + 0.5, spawn[1], spawn[2] + 0.5, spawn[3], spawn[4]);
                MutableComponent message = Component.literal("You have been pardoned from jail and teleported to ");
                message.append(Component.literal("Gripper City's").withStyle(groupStyling));
                message.append(Component.literal(" spawn!"));
                player.sendSystemMessage(message, false);
                VillageCraft3Essentials.LOGGER.info("[{}: Pardoned {} and teleported them to Gripper City's spawn at {}, {}, {}]", context.getSource().getTextName(), targetPlayerUsername.getString(), spawn[0], spawn[1], spawn[2]);
            }

            MutableComponent message = Component.literal("You have pardoned ");
            message.append(targetPlayerUsername);
            message.append(Component.literal(" from jail and teleported them to "));
            message.append(Component.literal("Gripper City's").withStyle(groupStyling));
            message.append(Component.literal(" spawn!"));
            context.getSource().sendSuccess(() -> message, false);
            return 1;

        } else {
            for (ServerPlayer player : targets) {
                int playerYaw = (int) player.getYRot();
                int playerPitch = (int) player.getXRot();
                player.teleportTo(serverlevel, blockpos.getX(), blockpos.getY(), blockpos.getZ(), playerYaw, playerPitch);
                targetPlayerMessage = Component.literal("You have been pardoned from jail and teleported to ");
                targetPlayerMessage.append(Component.literal("World Spawn").withStyle(ChatFormatting.GREEN));
                targetPlayerMessage.append(Component.literal("!"));
                player.sendSystemMessage(targetPlayerMessage, false);
                VillageCraft3Essentials.LOGGER.info("[{}: Pardoned {} and teleported them to World Spawn]", context.getSource().getTextName(), targetPlayerUsername.getString());
            }

            MutableComponent message = Component.literal("You have pardoned ");
            message.append(targetPlayerUsername);
            message.append(Component.literal(" from jail and teleported them to "));
            message.append(Component.literal("World Spawn").withStyle(ChatFormatting.GREEN));
            message.append(Component.literal("due to "));
            message.append(Component.literal("Gripper City's").withStyle(groupStyling));
            message.append(Component.literal(" unset spawn position."));
            context.getSource().sendSuccess(() -> message, false);
            return 1;
        }
    }
}