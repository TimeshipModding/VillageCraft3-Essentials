package com.timeshipmodding.villagecraft3essentials.content.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

public class RefreshPlayerDisplayNameCommand {
    public RefreshPlayerDisplayNameCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("refreshplayerdisplayname").executes(this::execute));
    }

    private int execute(CommandContext<CommandSourceStack> context) {
        MinecraftServer server = context.getSource().getServer();

        for (ServerPlayer serverPlayer : server.getPlayerList().getPlayers()) {
            serverPlayer.refreshTabListName();
            serverPlayer.refreshDisplayName();
        }

        context.getSource().sendSuccess(() -> Component.literal("All online players' display names have been refreshed"), false);
        return 1;
    }
}