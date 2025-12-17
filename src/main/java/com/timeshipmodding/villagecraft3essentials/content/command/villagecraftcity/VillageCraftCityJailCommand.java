package com.timeshipmodding.villagecraft3essentials.content.command.villagecraftcity;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.timeshipmodding.villagecraft3essentials.compat.luckperms.LuckpermsMethods;
import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.saveddata.JailSavedData;
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

public class VillageCraftCityJailCommand {
    public VillageCraftCityJailCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("villagecraftcity").then(Commands.literal("jail")
                .then(
                        Commands.argument("targets", EntityArgument.players())
                                .executes(p_137810_ -> execute(p_137810_, EntityArgument.getPlayers(p_137810_, "targets")))
                )));
    }

    private String targetPlayerUsername;

    private int execute(CommandContext<CommandSourceStack> context, Collection<? extends ServerPlayer> targets) {
        MinecraftServer server = context.getSource().getServer();
        ServerLevel serverlevel = server.getLevel(ServerLevel.OVERWORLD);
        JailSavedData savedData = JailSavedData.getData(server);
        int[] jail = savedData.getVillagecraftCityJail();
        String groupColour;

        if (ModList.get().isLoaded("luckperms")) {
            groupColour = LuckpermsMethods.getGroupColour(ServerConfig.VILLAGECRAFTCITY_GROUP_NAME.get());
        } else {
            groupColour = "";
        }

        if (jail[3] != 0 && jail[4] != 0 && serverlevel != null) {
            for (ServerPlayer player : targets) {
                player.teleportTo(serverlevel, jail[0] + 0.5, jail[1], jail[2] + 0.5, jail[3], jail[4]);
                player.setGameMode(GameType.ADVENTURE);
                BlockPos playerPos = player.blockPosition();
                player.setRespawnPosition(ServerLevel.OVERWORLD, playerPos, player.getYRot(), true, false);
                player.sendSystemMessage(Component.literal("You have been arrested and teleported to " + groupColour + "VillageCraft City's§r Jail!"), false);
                targetPlayerUsername = Objects.requireNonNull(player.getDisplayName()).getString();

                if (ModList.get().isLoaded("luckperms")) {
                    LuckpermsMethods.addGroup(player, ServerConfig.JAILED_GROUP_NAME.get());

                    if (ServerConfig.PLAYER_DISPLAY_NAME_FORMATTING.get()) {
                        player.refreshTabListName();
                        player.refreshDisplayName();
                    }
                }
            }

            context.getSource().sendSuccess(() -> Component.literal("You have arrested and teleported " + targetPlayerUsername + " to " + groupColour + "VillageCraft City's Jail§r!"), false);
            return 1;

        } else {
            context.getSource().sendFailure(Component.literal("§cNo " + groupColour + "VillageCraft City§c Jail position has been set."));
            return -1;
        }
    }
}