package com.timeshipmodding.villagecraft3essentials.content.command.villagecraftcity;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.timeshipmodding.villagecraft3essentials.compat.luckperms.LuckpermsMethods;
import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.fml.ModList;

import java.util.Collection;
import java.util.Objects;

public class VillageCraftCityKickCommand {
    public VillageCraftCityKickCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("villagecraftcity").then(Commands.literal("kick")
                .then(
                        Commands.argument("targets", EntityArgument.players())
                                .executes(p_137810_ -> execute(p_137810_, EntityArgument.getPlayers(p_137810_, "targets"), false))
                                .then(
                                        Commands.argument("hideMessage", BoolArgumentType.bool())
                                                .executes(p_267909_ -> execute(p_267909_, EntityArgument.getPlayers(p_267909_, "targets"), !BoolArgumentType.getBool(p_267909_, "hideMessage")))
                                ))));
    }

    private String targetPlayerUsername;

    private int execute(CommandContext<CommandSourceStack> context, Collection<? extends ServerPlayer> targets, boolean hideMessage) {
        MinecraftServer server = context.getSource().getServer();
        ServerLevel serverlevel = server.getLevel(ServerLevel.OVERWORLD);
        assert serverlevel != null;
        BlockPos blockpos = serverlevel.getSharedSpawnPos();
        String groupColour;

        if (ModList.get().isLoaded("luckperms")) {
            groupColour = LuckpermsMethods.getGroupColour(ServerConfig.VILLAGECRAFTCITY_GROUP_NAME.get());
        } else {
            groupColour = "";
        }

        for (ServerPlayer player : targets) {
            int playerYaw = (int) player.getYRot();
            int playerPitch = (int) player.getXRot();
            player.teleportTo(serverlevel, blockpos.getX() + 0.5, blockpos.getY(), blockpos.getZ() + 0.5, playerYaw, playerPitch);

            if (hideMessage) {
                player.sendSystemMessage(Component.literal("You have been kicked from " + groupColour + "VillageCraft City§r and teleported to §aWorld Spawn§r!"), false);
            }

            targetPlayerUsername = Objects.requireNonNull(player.getDisplayName()).getString();
        }

        context.getSource().sendSuccess(() -> Component.literal("You have kicked " + targetPlayerUsername + " from " + groupColour + "VillageCraft City§r and teleported them to §aWorld Spawn§r!"), false);
        return 1;
    }
}