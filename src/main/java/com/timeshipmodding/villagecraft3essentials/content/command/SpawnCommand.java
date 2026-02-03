package com.timeshipmodding.villagecraft3essentials.content.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.timeshipmodding.villagecraft3essentials.event.registries.ModEvents;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class SpawnCommand {
    public SpawnCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("spawn").executes(this::execute));
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
            MutableComponent message = Component.literal("You have been teleported to ");
            message.append(Component.literal("World Spawn").withStyle(ChatFormatting.GREEN));
            message.append(Component.literal("!"));

            ModEvents.pendingTeleports.put(player.getUUID(), new ModEvents.TeleportCommandsData(
                    serverlevel,
                    60,
                    player.position(),
                    new int[]{blockpos.getX(), blockpos.getY(), blockpos.getZ(), playerYaw, playerPitch},
                    message
            ));

            return 1;

        } else {
            return -1;
        }
    }
}