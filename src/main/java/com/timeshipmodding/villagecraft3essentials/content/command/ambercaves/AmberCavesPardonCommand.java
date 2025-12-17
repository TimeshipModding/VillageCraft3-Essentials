package com.timeshipmodding.villagecraft3essentials.content.command.ambercaves;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.timeshipmodding.villagecraft3essentials.compat.luckperms.LuckpermsMethods;
import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.saveddata.SpawnSavedData;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import net.neoforged.fml.ModList;

import java.util.Collection;
import java.util.Objects;

public class AmberCavesPardonCommand {
    public AmberCavesPardonCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("ambercaves").then(Commands.literal("pardon")
                .then(
                        Commands.argument("targets", EntityArgument.players())
                                .executes(p_137810_ -> execute(p_137810_, EntityArgument.getPlayers(p_137810_, "targets")))
                )));
    }

    private String playerUsername;

    private int execute(CommandContext<CommandSourceStack> context, Collection<? extends ServerPlayer> targets) {
        MinecraftServer server = context.getSource().getServer();
        ServerLevel serverlevel = server.getLevel(ServerLevel.OVERWORLD);
        SpawnSavedData savedData = SpawnSavedData.getData(server);
        int[] spawn = savedData.getAmberCavesSpawn();
        String groupColour;

        if (ModList.get().isLoaded("luckperms")) {
            groupColour = LuckpermsMethods.getGroupColour(ServerConfig.GRIPPERCITY_GROUP_NAME.get());
        } else {
            groupColour = "";
        }

        if (spawn[3] != 0 && spawn[4] != 0 && serverlevel != null) {
            for (ServerPlayer player : targets) {
                player.teleportTo(serverlevel, spawn[0] + 0.5, spawn[1], spawn[2] + 0.5, spawn[3], spawn[4]);
                player.setGameMode(GameType.SURVIVAL);
                BlockPos blockpos = serverlevel.getSharedSpawnPos();
                player.setRespawnPosition(ServerLevel.OVERWORLD, blockpos, player.getYRot(), true, false);
                player.sendSystemMessage(Component.literal("You have been pardoned from jail and teleported to " + groupColour + "The Amber Caves'§r Spawn!"), false);
                playerUsername = Objects.requireNonNull(player.getDisplayName()).getString();

                if (ModList.get().isLoaded("luckperms")) {
                    LuckpermsMethods.removeGroup(player, ServerConfig.JAILED_GROUP_NAME.get());

                    if (ServerConfig.PLAYER_DISPLAY_NAME_FORMATTING.get()) {
                        player.refreshTabListName();
                        player.refreshDisplayName();
                    }
                }
            }

            context.getSource().sendSuccess(() -> Component.literal("You have pardoned " + playerUsername + " from jail and teleported them to " + groupColour + "The Amber Caves'§r Spawn!"), false);
            return 1;

        } else {
            context.getSource().sendFailure(Component.literal("§cNo " + groupColour + "Amber Caves' Spawn§c position has been set."));
            return -1;
        }
    }
}