package com.timeshipmodding.villagecraft3essentials.content.commands.grippercity;

import com.google.common.collect.ImmutableList;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.timeshipmodding.villagecraft3essentials.compat.luckperms.LuckpermsMethods;
import com.timeshipmodding.villagecraft3essentials.util.saveddata.JailSavedData;
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
import java.util.Objects;

public class GripperCityJailCommand {
    public GripperCityJailCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("grippercity").then(Commands.literal("jail")
                .executes(p_137817_ -> execute(p_137817_, ImmutableList.of(p_137817_.getSource().getPlayerOrException())))
                        .then(
                                Commands.argument("targets", EntityArgument.players())
                                        .executes(p_137810_ -> execute(p_137810_, EntityArgument.getPlayers(p_137810_, "targets")))
                        )));
    }

    private String targetPlayerUsername;
    private final ResourceKey<Level> jailDimension = ServerLevel.OVERWORLD;

    public ResourceKey<Level> getJailDimension() {
        return this.jailDimension;
    }

    private int execute(CommandContext<CommandSourceStack> context, Collection<? extends ServerPlayer> targets) {
        MinecraftServer server = context.getSource().getServer();
        ServerLevel serverlevel = server.getLevel(this.getJailDimension());
        JailSavedData savedData = JailSavedData.getData(server);
        int[] jail = savedData.getGripperCityJail();

        if(jail[3] != 0 && jail[4] != 0 && serverlevel != null) {
            for (ServerPlayer player : targets) {
                player.teleportTo(serverlevel, jail[0] + 0.5, jail[1], jail[2] + 0.5, jail[3], jail[4]);
                player.setGameMode(GameType.ADVENTURE);
                BlockPos playerPos = player.blockPosition();
                player.setRespawnPosition(ServerLevel.OVERWORLD, playerPos, player.getYRot(), true, false);
                player.sendSystemMessage(Component.literal("You have been arrested and teleported to Gripper City Jail!"), false);
                targetPlayerUsername = Objects.requireNonNull(player.getDisplayName()).getString();

                if (ModList.get().isLoaded("luckperms")) {
                    LuckpermsMethods.addGroup(player, "jailed");
                }
            }

            context.getSource().sendSuccess(() -> Component.literal("You have arrested and teleported " + targetPlayerUsername + " to Gripper City Jail!"), false);
            return 1;
        } else {
            context.getSource().sendFailure(Component.literal("No Gripper City Jail position has been set."));
            return -1;
        }
    }
}