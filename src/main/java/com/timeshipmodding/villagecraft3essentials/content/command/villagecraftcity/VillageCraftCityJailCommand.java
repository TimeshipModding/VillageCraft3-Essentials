package com.timeshipmodding.villagecraft3essentials.content.command.villagecraftcity;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.timeshipmodding.villagecraft3essentials.compat.luckperms.LuckpermsMethods;
import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.JailAndPardonCommandData;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.attachment.registries.ModDataAttachments;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.saveddata.JailSavedData;
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

import java.util.*;

public class VillageCraftCityJailCommand {
    public VillageCraftCityJailCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("villagecraftcity").then(Commands.literal("jail")
                .then(
                        Commands.argument("targets", EntityArgument.players())
                                .executes(p_137810_ -> execute(p_137810_, EntityArgument.getPlayers(p_137810_, "targets")))
                )));
    }

    private Component targetPlayerUsername;

    private int execute(CommandContext<CommandSourceStack> context, Collection<? extends ServerPlayer> targets) {
        MinecraftServer server = context.getSource().getServer();
        ServerLevel serverlevel = server.getLevel(ServerLevel.OVERWORLD);
        JailSavedData savedData = JailSavedData.getData(server);
        int[] jail = savedData.getVillagecraftCityJail();
        ChatFormatting groupStyling = ChatFormatting.WHITE;
        long currentTime = System.currentTimeMillis();

        if (ModList.get().isLoaded("luckperms")) {
            groupStyling = LuckpermsMethods.getGroupStyling(ServerConfig.VILLAGECRAFTCITY_GROUP_NAME.get());
        }

        if (jail[3] != 0 && jail[4] != 0 && serverlevel != null) {
            for (ServerPlayer player : targets) {
                targetPlayerUsername = Objects.requireNonNull(player.getDisplayName());
                long lastJailed = player.getData(ModDataAttachments.JAIL_COMMAND_DATA).jailCommandCooldown();
                long cooldownMs = ServerConfig.JAIL_COMMAND_COOLDOWN.get() * 1000L;
                long timeLeft = (lastJailed + cooldownMs) - currentTime;

                if (lastJailed != 0 && timeLeft > 0) {
                    long hoursLeft = timeLeft / 3600000L;
                    long minutesLeft = (timeLeft % 3600000L) / 60000L;

                    String timeString = hoursLeft > 0 ?
                                hoursLeft + " hours and " + minutesLeft + " minutes" :
                                minutesLeft + " minutes";

                    MutableComponent message = targetPlayerUsername.copy();
                    message.append(Component.literal(" has already been jailed! Wait " + timeString + " to jail them again."));
                    context.getSource().sendFailure(message);
                    return -1;
                }

                player.teleportTo(serverlevel, jail[0] + 0.5, jail[1], jail[2] + 0.5, jail[3], jail[4]);
                player.setGameMode(GameType.ADVENTURE);
                BlockPos playerPos = player.blockPosition();
                player.setRespawnPosition(ServerLevel.OVERWORLD, playerPos, player.getYRot(), true, false);
                MutableComponent message = Component.literal("You have been arrested and teleported to ");
                message.append(Component.literal("VillageCraft City's").withStyle(groupStyling));
                message.append(Component.literal(" jail!"));
                player.sendSystemMessage(message, false);
                Component jailerPlayerUsername = Objects.requireNonNull(context.getSource().getPlayer().getDisplayName());
                player.setData(ModDataAttachments.JAIL_COMMAND_DATA, new JailAndPardonCommandData(jailerPlayerUsername.getString(), Component.literal("VillageCraft City's").withStyle(groupStyling), ServerConfig.JAIL_RELEASE_TIME.get() * 20, currentTime));

                if (ModList.get().isLoaded("luckperms")) {
                    LuckpermsMethods.addGroup(player, ServerConfig.JAILED_GROUP_NAME.get());
                }
            }

            MutableComponent message = Component.literal("You have arrested and teleported ");
            message.append(targetPlayerUsername);
            message.append(Component.literal(" to "));
            message.append(Component.literal("VillageCraft City's").withStyle(groupStyling));
            message.append(Component.literal(" jail!"));
            context.getSource().sendSuccess(() -> message, false);
            return 1;

        } else {
            MutableComponent message = Component.literal("No ").withStyle(ChatFormatting.RED);
            message.append(Component.literal("VillageCraft City").withStyle(groupStyling));
            message.append(Component.literal(" jail position has been set.").withStyle(ChatFormatting.RED));
            context.getSource().sendFailure(message);
            return -1;
        }
    }
}