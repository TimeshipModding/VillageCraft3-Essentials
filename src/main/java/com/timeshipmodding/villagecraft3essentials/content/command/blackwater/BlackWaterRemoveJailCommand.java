package com.timeshipmodding.villagecraft3essentials.content.command.blackwater;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.timeshipmodding.villagecraft3essentials.compat.luckperms.LuckpermsMethods;
import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.saveddata.JailSavedData;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.fml.ModList;

public class BlackWaterRemoveJailCommand {
    public BlackWaterRemoveJailCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("blackwater").then(Commands.literal("removejail")
                .executes(this::execute)));
    }

    private int execute(CommandContext<CommandSourceStack> context) {
        ServerPlayer player = context.getSource().getPlayer();
        MinecraftServer server = context.getSource().getServer();
        assert player != null;
        JailSavedData savedData = JailSavedData.getData(server);
        int[] jail = savedData.getBlackWaterJail();
        ChatFormatting groupStyling = ChatFormatting.WHITE;

        if (ModList.get().isLoaded("luckperms")) {
            groupStyling = LuckpermsMethods.getGroupStyling(ServerConfig.BLACKWATER_GROUP_NAME.get());
        }

        if (jail[3] != 0 && jail[4] != 0) {
            int[] jail1 = {0, 0, 0, 0, 0};
            savedData.setBlackWaterJail(jail1);
            MutableComponent message = Component.literal("Removed ");
            message.append(Component.literal("BlackWater's").withStyle(groupStyling));
            message.append(Component.literal(" jail."));
            context.getSource().sendSuccess(() -> message, true);
            return 1;
        }

        MutableComponent message = Component.literal("BlackWater's").withStyle(groupStyling);
        message.append(Component.literal(" jail has not been set.").withStyle(ChatFormatting.RED));
        context.getSource().sendFailure(message);
        return 0;
    }
}