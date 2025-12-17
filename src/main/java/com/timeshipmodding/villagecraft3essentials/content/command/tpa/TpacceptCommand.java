package com.timeshipmodding.villagecraft3essentials.content.command.tpa;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.timeshipmodding.villagecraft3essentials.content.command.tpa.manager.TpaCommandManager;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;

public class TpacceptCommand {
    public TpacceptCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("tpaccept").executes(this::execute));
    }

    private int execute(CommandContext<CommandSourceStack> context) {
        ServerPlayer player = context.getSource().getPlayer();

        if (player != null) {
            boolean successful = TpaCommandManager.acceptTpa(player);

            if (successful) {
                return 1;
            }
        }

        return -1;
    }
}