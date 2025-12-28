package com.timeshipmodding.villagecraft3essentials.content.command.tpa;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.timeshipmodding.villagecraft3essentials.content.command.tpa.manager.TpaCommandManager;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;

public class TpadenyCommand {
    public TpadenyCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("tpadeny")
                .executes(p_267909_ -> execute(p_267909_, false))
                .then(
                        Commands.argument("hideMessage", BoolArgumentType.bool())
                                .executes(p_267909_ -> execute(p_267909_, BoolArgumentType.getBool(p_267909_, "hideMessage")
                                ))));
    }

    private int execute(CommandContext<CommandSourceStack> context, Boolean hideMessage) {
        ServerPlayer player = context.getSource().getPlayer();

        if (player != null) {
            boolean successful = TpaCommandManager.denyTpa(player, hideMessage);

            if (successful) {
                return 1;
            }
        }

        return -1;
    }
}