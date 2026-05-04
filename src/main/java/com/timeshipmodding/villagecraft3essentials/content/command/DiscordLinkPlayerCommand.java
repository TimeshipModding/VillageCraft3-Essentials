package com.timeshipmodding.villagecraft3essentials.content.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.timeshipmodding.villagecraft3essentials.compat.dcintegration.DiscordIntegrationMethods;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.UuidArgument;
import net.minecraft.network.chat.Component;

import java.util.UUID;

public class DiscordLinkPlayerCommand {
    public DiscordLinkPlayerCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("discordlinkplayer").requires(context -> context.hasPermission(2))
                        .then(Commands.argument("minecraftUUID", UuidArgument.uuid())
                                .then(Commands.argument("discordID", StringArgumentType.string())
                        .executes(context -> executeVillageCraftCity(context, UuidArgument.getUuid(context, "minecraftUUID"), StringArgumentType.getString(context, "discordID"))))));
    }

    private int executeVillageCraftCity(CommandContext<CommandSourceStack> context, UUID playerUUID, String discordID) {
        DiscordIntegrationMethods.linkPlayer(playerUUID, discordID);
        context.getSource().sendSuccess(() -> Component.literal("Linked " + playerUUID + " to " + discordID + "!"), false);
        return 1;
    }
}