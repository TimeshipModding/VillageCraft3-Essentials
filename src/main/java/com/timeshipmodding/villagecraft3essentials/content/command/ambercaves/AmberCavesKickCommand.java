package com.timeshipmodding.villagecraft3essentials.content.command.ambercaves;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.timeshipmodding.villagecraft3essentials.compat.luckperms.LuckpermsMethods;
import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.attachment.registries.ModDataAttachments;
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
import net.neoforged.fml.ModList;

import java.util.Collection;
import java.util.Objects;

public class AmberCavesKickCommand {
    public AmberCavesKickCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("ambercaves").then(Commands.literal("kick")
                .then(
                        Commands.argument("targets", EntityArgument.players())
                                .executes(p_137810_ -> execute(p_137810_, EntityArgument.getPlayers(p_137810_, "targets"), false))
                                .then(
                                        Commands.argument("hideMessage", BoolArgumentType.bool())
                                                .executes(p_267909_ -> execute(p_267909_, EntityArgument.getPlayers(p_267909_, "targets"), !BoolArgumentType.getBool(p_267909_, "hideMessage")))
                                ))));
    }

    private Component targetPlayerUsername;

    private int execute(CommandContext<CommandSourceStack> context, Collection<? extends ServerPlayer> targets, boolean hideMessage) {
        MinecraftServer server = context.getSource().getServer();
        ServerLevel serverlevel = server.getLevel(ServerLevel.OVERWORLD);
        assert serverlevel != null;
        BlockPos blockpos = serverlevel.getSharedSpawnPos();
        ChatFormatting groupStyling = ChatFormatting.WHITE;
        long currentTime = System.currentTimeMillis();

        if (ModList.get().isLoaded("luckperms")) {
            groupStyling = LuckpermsMethods.getGroupStyling(ServerConfig.AMBERCAVES_GROUP_NAME.get());
        }

        for (ServerPlayer player : targets) {
            targetPlayerUsername = Objects.requireNonNull(player.getDisplayName());
            long lastKicked = player.getData(ModDataAttachments.KICK_COMMAND_DATA).amberCavesKickCommandCooldown();
            long cooldownMs = ServerConfig.KICK_COMMAND_COOLDOWN.get() * 1000L;
            long timeLeft = (lastKicked + cooldownMs) - currentTime;

            if (lastKicked != 0 && timeLeft > 0) {
                long hoursLeft = timeLeft / 3600000L;
                long minutesLeft = (timeLeft % 3600000L) / 60000L;

                String timeString = hoursLeft > 0 ?
                        hoursLeft + " hours and " + minutesLeft + " minutes" :
                        minutesLeft + " minutes";

                MutableComponent message = targetPlayerUsername.copy();
                message.append(Component.literal(" has already been kicked from "));
                message.append(Component.literal("The Amber Caves").withStyle(groupStyling));
                message.append(Component.literal("! Wait " + timeString + " to kick them again."));
                context.getSource().sendFailure(message);
                return -1;
            }

            int playerYaw = (int) player.getYRot();
            int playerPitch = (int) player.getXRot();
            player.teleportTo(serverlevel, blockpos.getX() + 0.5, blockpos.getY(), blockpos.getZ() + 0.5, playerYaw, playerPitch);

            if (hideMessage) {
                MutableComponent message = Component.literal("You have been kicked from ");
                message.append(Component.literal("The Amber Caves").withStyle(groupStyling));
                message.append(Component.literal(" and teleported to "));
                message.append(Component.literal("World spawn!").withStyle(ChatFormatting.GREEN));
                player.sendSystemMessage(message, false);
            }

            player.setData(ModDataAttachments.KICK_COMMAND_DATA, player.getData(ModDataAttachments.KICK_COMMAND_DATA).amberCavesSetData(currentTime));
        }

        MutableComponent message = Component.literal("You have kicked ");
        message.append(targetPlayerUsername);
        message.append(Component.literal(" from "));
        message.append(Component.literal("The Amber Caves").withStyle(groupStyling));
        message.append(Component.literal(" and teleported them to "));
        message.append(Component.literal("World spawn!").withStyle(ChatFormatting.GREEN));
        context.getSource().sendSuccess(() -> message, false);
        return 1;
    }
}