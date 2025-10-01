package com.timeshipmodding.villagecraft3essentials.content.commands.grippercity;

import com.google.common.collect.ImmutableList;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

import java.util.Collection;
import java.util.Objects;

public class GripperCityKickCommand {
    public GripperCityKickCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("grippercity").then(Commands.literal("kick")
                .executes(p_137817_ -> execute(p_137817_, ImmutableList.of(p_137817_.getSource().getPlayerOrException()), !BoolArgumentType.getBool(p_137817_, "hideMessage")))
                        .then(
                                Commands.argument("targets", EntityArgument.players())
                                        .executes(p_137810_ -> execute(p_137810_, EntityArgument.getPlayers(p_137810_, "targets"), !BoolArgumentType.getBool(p_137810_, "hideMessage")))
                                        .then(
                                                Commands.argument("hideMessage", BoolArgumentType.bool())
                                                        .executes(p_267909_ -> execute(p_267909_, EntityArgument.getPlayers(p_267909_, "targets"), !BoolArgumentType.getBool(p_267909_, "hideMessage"))
                                                        )
                        ))));
    }

    private String targetPlayerUsername;
    private final ResourceKey<Level> spawnDimension = ServerLevel.OVERWORLD;

    public ResourceKey<Level> getSpawnDimension() {
        return this.spawnDimension;
    }

    private int execute(CommandContext<CommandSourceStack> context, Collection<? extends ServerPlayer> targets, boolean hideMessage) {
        MinecraftServer server = context.getSource().getServer();
        ServerLevel serverlevel = server.getLevel(this.getSpawnDimension());
        assert serverlevel != null;
        BlockPos blockpos = serverlevel.getSharedSpawnPos();
        float angle = serverlevel.getSharedSpawnAngle();
        for (ServerPlayer player : targets) {
            player.teleportTo(serverlevel, blockpos.getX(), blockpos.getY(), blockpos.getZ(), angle, 0);

            if (hideMessage) {
                player.sendSystemMessage(Component.literal("You have been kicked from Gripper City and teleported to World Spawn!"), false);
            }

            targetPlayerUsername = Objects.requireNonNull(player.getDisplayName()).getString();
        }

        context.getSource().sendSuccess(() -> Component.literal("You have kicked " + targetPlayerUsername + " from Gripper City and teleported them to World Spawn!"), false);
        return 1;
    }
}