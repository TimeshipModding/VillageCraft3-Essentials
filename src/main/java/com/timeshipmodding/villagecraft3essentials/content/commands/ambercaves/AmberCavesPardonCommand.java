package com.timeshipmodding.villagecraft3essentials.content.commands.ambercaves;

import com.google.common.collect.ImmutableList;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.timeshipmodding.villagecraft3essentials.compat.luckperms.LuckpermsMethods;
import com.timeshipmodding.villagecraft3essentials.util.saveddata.SpawnSavedData;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.neoforged.fml.ModList;

import java.util.Collection;

public class AmberCavesPardonCommand {
    public AmberCavesPardonCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("ambercaves").then(Commands.literal("pardon")
                .executes(p_137817_ -> execute(p_137817_, ImmutableList.of(p_137817_.getSource().getPlayerOrException())))
                        .then(
                                Commands.argument("targets", EntityArgument.players())
                                        .executes(p_137810_ -> execute(p_137810_, EntityArgument.getPlayers(p_137810_, "targets")))
                        )));
    }

    private String targetPlayerUsername;
    private final ResourceKey<Level> spawnDimension = ServerLevel.OVERWORLD;

    public ResourceKey<Level> getJailDimension() {
        return this.spawnDimension;
    }

    private int execute(CommandContext<CommandSourceStack> context, Collection<? extends ServerPlayer> targets) {
        MinecraftServer server = context.getSource().getServer();
        ServerLevel serverlevel = server.getLevel(this.getJailDimension());
        SpawnSavedData savedData = SpawnSavedData.getData(server);
        int[] spawn = savedData.getAmberCavesSpawn();

        if(spawn[3] != 0 && spawn[4] != 0 && serverlevel != null) {
            for (ServerPlayer player : targets) {
                player.teleportTo(serverlevel, spawn[0], spawn[1], spawn[2], spawn[3], spawn[4]);
                player.setGameMode(GameType.SURVIVAL);
                BlockPos blockpos = serverlevel.getSharedSpawnPos();
                player.setRespawnPosition(ServerLevel.OVERWORLD, blockpos, player.getYRot(), true, false);
                player.sendSystemMessage(Component.literal("You have been pardoned from jail and teleported to The Amber Caves Spawn!"), false);
                targetPlayerUsername = player.getDisplayName().getString();

                if (ModList.get().isLoaded("luckperms")) {
                    LuckpermsMethods.removeGroup(player, "jailed");
                }
            }

            context.getSource().sendSuccess(() -> Component.literal("You have pardoned " + targetPlayerUsername + " from jail and teleported them to The Amber Caves Spawn!"), false);
            return 1;
        } else {
            context.getSource().sendFailure(Component.literal("No Amber Caves' Spawn position has been set."));
            return -1;
        }
    }
}