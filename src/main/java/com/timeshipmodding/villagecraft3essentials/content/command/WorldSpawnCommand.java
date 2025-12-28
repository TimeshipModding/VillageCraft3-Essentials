package com.timeshipmodding.villagecraft3essentials.content.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class WorldSpawnCommand {
    public WorldSpawnCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("worldspawn").executes(this::execute));
    }

    private int execute(CommandContext<CommandSourceStack> context) {
        ServerPlayer player = context.getSource().getPlayer();
        MinecraftServer server = context.getSource().getServer();
        ServerLevel serverlevel = server.getLevel(ServerLevel.OVERWORLD);
        assert serverlevel != null;
        BlockPos blockpos = serverlevel.getSharedSpawnPos();

        if(player != null) {
            int playerYaw = (int) player.getYRot();
            int playerPitch = (int) player.getXRot();
            player.teleportTo(serverlevel, blockpos.getX() + 0.5, blockpos.getY(), blockpos.getZ() + 0.5, playerYaw, playerPitch);
            MutableComponent message = Component.literal("You have been teleported to ");
            message.append(Component.literal("World spawn").withStyle(ChatFormatting.GREEN));
            message.append(Component.literal("!"));
            context.getSource().sendSuccess(() -> message, false);
            return 1;

        } else {
            return -1;
        }
    }
}