package com.timeshipmodding.villagecraft3essentials.content.command.warscore;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class ToggleWarScoreGainingCommand {
    public ToggleWarScoreGainingCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("warscore").requires(context -> context.hasPermission(2))
                .then(Commands.literal("togglegainpoints").executes(this::execute)));
    }

    public static boolean gainWarPoints = false;

    private int execute(CommandContext<CommandSourceStack> context) {
        if (ServerConfig.ENABLE_WAR_SYSTEM_FEATURES.get()) {
            MutableComponent message = Component.empty();

            if (!gainWarPoints) {
                message.append(Component.literal("War points can now be earned via breaking cores."));
                gainWarPoints = true;

            } else if (gainWarPoints) {
                message.append(Component.literal("War points can no longer be earned via breaking cores."));
                gainWarPoints = false;
            }

            context.getSource().sendSuccess(() -> message, false);
            return 1;
        }

        MutableComponent message = Component.literal("War features are not enabled. Enable them in the VillageCraft 3 Essentials Common Config.").withStyle(ChatFormatting.RED);
        context.getSource().sendFailure(message);
        return -1;
    }
}