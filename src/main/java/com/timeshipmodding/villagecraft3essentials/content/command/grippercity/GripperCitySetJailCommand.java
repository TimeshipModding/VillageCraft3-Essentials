package com.timeshipmodding.villagecraft3essentials.content.command.grippercity;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.timeshipmodding.villagecraft3essentials.compat.luckperms.LuckpermsMethods;
import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.saveddata.JailSavedData;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.neoforged.fml.ModList;

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
        String groupColour;

        if (ModList.get().isLoaded("luckperms")) {
            groupColour = LuckpermsMethods.getGroupColour(ServerConfig.GRIPPERCITY_GROUP_NAME.get());
        } else {
            groupColour = "";
        }

        if (serverLevel.dimension() != Level.OVERWORLD) {
            context.getSource().sendFailure(Component.literal("§cCan only set " + groupColour + "Gripper City's§c Jail in the overworld."));
            return 0;

        } else {
            context.getSource().sendSuccess(() -> Component.literal("Set " + groupColour + "Gripper City's§r Jail to " + positionString), true);
            return 1;
        }
    }
}