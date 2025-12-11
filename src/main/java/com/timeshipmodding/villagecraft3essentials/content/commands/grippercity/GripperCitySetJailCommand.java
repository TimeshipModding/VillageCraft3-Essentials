package com.timeshipmodding.villagecraft3essentials.content.commands.grippercity;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.timeshipmodding.villagecraft3essentials.util.data.saveddata.JailSavedData;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

public class GripperCitySetJailCommand {
    public GripperCitySetJailCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("grippercity").then(Commands.literal("setjail")
                .executes(this::execute)));
    }

    private int execute(CommandContext<CommandSourceStack> context) {
        ServerPlayer player = context.getSource().getPlayer();
        ServerLevel serverLevel = context.getSource().getLevel();
        MinecraftServer server = context.getSource().getServer();
        assert player != null;
        BlockPos playerPos = player.blockPosition();
        String positionString = playerPos.getX() + ", " + playerPos.getY() + ", " + playerPos.getZ();
        int playerYaw = (int) player.getYRot();
        int playerPitch = (int) player.getXRot();
        int[] jail = {playerPos.getX(), playerPos.getY(), playerPos.getZ(), playerYaw, playerPitch};
        JailSavedData savedData = JailSavedData.getData(server);
        savedData.setGripperCityJail(jail);

        if (serverLevel.dimension() != Level.OVERWORLD) {
            context.getSource().sendFailure(Component.literal("Can only set Gripper City's Jail in the overworld."));
            return 0;
        } else {
            context.getSource().sendSuccess(() -> Component.literal("Set Gripper City's Jail to " + positionString), true);
            return 1;
        }
    }
}