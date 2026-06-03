package com.timeshipmodding.villagecraft3essentials.content.command.removecooldown;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.timeshipmodding.villagecraft3essentials.compat.luckperms.LuckpermsMethods;
import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.JailAndPardonCommandData;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.KickCommandData;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.attachment.registries.ModDataAttachments;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.fml.ModList;

import java.util.Collection;
import java.util.Objects;

public class RemoveCooldownJailCommand {
    public RemoveCooldownJailCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("removecooldown").requires(context -> context.hasPermission(2)).then(Commands.literal("jail")
                .then(Commands.argument("targets", EntityArgument.players())
                        .executes(context -> execute(context, EntityArgument.getPlayers(context, "targets"), false))
                        .then(Commands.argument("hideMessage", BoolArgumentType.bool())
                                .executes(context -> execute(context, EntityArgument.getPlayers(context, "targets"), !BoolArgumentType.getBool(context, "hideMessage")))))));
    }

    private Component targetPlayerUsername;

    private int execute(CommandContext<CommandSourceStack> context, Collection<? extends ServerPlayer> targets, boolean hideMessage) {
        for (ServerPlayer player : targets) {
            targetPlayerUsername = Objects.requireNonNull(player.getDisplayName());

            if (player.hasData(ModDataAttachments.JAIL_COMMAND_DATA)) {
                JailAndPardonCommandData jailAndPardonCommandData = player.getData(ModDataAttachments.JAIL_COMMAND_DATA);
                player.setData(ModDataAttachments.JAIL_COMMAND_DATA, jailAndPardonCommandData.cooldownReset());

                if (hideMessage) {
                    MutableComponent message = Component.literal("Your jail command cooldown has been reset!");
                    player.sendSystemMessage(message, false);
                    return 1;
                }

            } else {
                MutableComponent message = targetPlayerUsername.copy();
                message.append(" has no jail command cooldown.");
                context.getSource().sendFailure(message);
                return -1;
            }
        }

        MutableComponent message = Component.literal("You have reset ");
        message.append(targetPlayerUsername);
        message.append("'s jail command cooldown!");
        context.getSource().sendSuccess(() -> message, false);
        return 1;
    }
}