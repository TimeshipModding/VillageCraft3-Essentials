package com.timeshipmodding.villagecraft3essentials.content.command.villagecraftcity;

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

public class VillageCraftCitySetJailCommand {
    public VillageCraftCitySetJailCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("villagecraftcity").then(Commands.literal("setjail")
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
        savedData.setVillagecraftCityJail(jail);
        String groupColour;

        if (ModList.get().isLoaded("luckperms")) {
            groupColour = LuckpermsMethods.getGroupColour(ServerConfig.VILLAGECRAFTCITY_GROUP_NAME.get());
        } else {
            groupColour = "";
        }

        if (serverLevel.dimension() != Level.OVERWORLD) {
            context.getSource().sendFailure(Component.literal("§cCan only set " + groupColour + "Villagecraft City's§c Jail in the overworld."));
            return 0;

        } else {
            context.getSource().sendSuccess(() -> Component.literal("Set " + groupColour + "VillageCraft City's§r Jail to " + positionString), true);
            return 1;
        }
    }
}