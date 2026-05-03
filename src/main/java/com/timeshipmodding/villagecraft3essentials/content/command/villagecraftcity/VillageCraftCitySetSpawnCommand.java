package com.timeshipmodding.villagecraft3essentials.content.command.villagecraftcity;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.compat.luckperms.LuckpermsMethods;
import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.saveddata.SpawnSavedData;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.neoforged.fml.ModList;

public class VillageCraftCitySetSpawnCommand {
    public VillageCraftCitySetSpawnCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("villagecraftcity").then(Commands.literal("setspawn")
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
        int[] spawn = {playerPos.getX(), playerPos.getY(), playerPos.getZ(), playerYaw, playerPitch};
        SpawnSavedData savedData = SpawnSavedData.getData(server);
        savedData.setVillagecraftCitySpawn(spawn);
        ChatFormatting groupStyling = ChatFormatting.WHITE;

        if (ModList.get().isLoaded("luckperms")) {
            groupStyling = LuckpermsMethods.getGroupStyling(ServerConfig.VILLAGECRAFTCITY_GROUP_NAME.get());
        }

        if (serverLevel.dimension() != Level.OVERWORLD) {
            MutableComponent message = Component.literal("Can only set ").withStyle(ChatFormatting.RED);
            message.append(Component.literal("VillageCraft City's").withStyle(groupStyling));
            message.append(Component.literal(" spawn in the overworld.").withStyle(ChatFormatting.RED));
            context.getSource().sendFailure(message);
            VillageCraft3Essentials.LOGGER.info("[{}: Set VillageCraft City's spawn to {}, {}, {}]", context.getSource().getTextName(), spawn[0], spawn[1], spawn[2]);
            return 0;

        } else {
            MutableComponent message = Component.literal("Set ");
            message.append(Component.literal("VillageCraft City's").withStyle(groupStyling));
            message.append(Component.literal(" spawn to " + positionString + "!"));
            context.getSource().sendSuccess(() -> message, true);
            return 1;
        }
    }
}