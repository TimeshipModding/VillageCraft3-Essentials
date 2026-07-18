package com.timeshipmodding.villagecraft3essentials.content.command.warscore;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.timeshipmodding.villagecraft3essentials.compat.luckperms.LuckpermsMethods;
import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.saveddata.MissionSavedData;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.MinecraftServer;
import net.neoforged.fml.ModList;

public class CompletedMissionsGetCommand {
    public CompletedMissionsGetCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("warscore").requires(context -> context.hasPermission(2)).then(Commands.literal("completedmissions")
                .then(
                        Commands.literal("get").executes(this::execute))));
    }

    private int execute(CommandContext<CommandSourceStack> context) {
        MinecraftServer server = context.getSource().getServer();
        MissionSavedData data = MissionSavedData.getData(server);
        ChatFormatting villageCraftCityStyling = ChatFormatting.WHITE;
        ChatFormatting gripperCityStyling = ChatFormatting.WHITE;
        ChatFormatting amberCavesStyling = ChatFormatting.WHITE;

        if (ModList.get().isLoaded("luckperms")) {
            villageCraftCityStyling = LuckpermsMethods.getGroupStyling(ServerConfig.VILLAGECRAFTCITY_GROUP_NAME.get());
            gripperCityStyling = LuckpermsMethods.getGroupStyling(ServerConfig.GRIPPERCITY_GROUP_NAME.get());
            amberCavesStyling = LuckpermsMethods.getGroupStyling(ServerConfig.AMBERCAVES_GROUP_NAME.get());
        }

        MutableComponent message = Component.literal("VillageCraft City").withStyle(villageCraftCityStyling);
        message.append(Component.literal(" : " + data.getVillagecraftCompletedMissions()));
        message.append(Component.literal("\nGripper City").withStyle(gripperCityStyling));
        message.append(Component.literal(" : " + data.getGripperCityCompletedMissions()));
        message.append(Component.literal("\nThe Amber Caves").withStyle(amberCavesStyling));
        message.append(Component.literal(" : " + data.getAmberCavesCompletedMissions()));
        context.getSource().sendSuccess(() -> message, false);
        return 1;
    }
}