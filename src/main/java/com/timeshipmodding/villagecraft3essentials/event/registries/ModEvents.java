package com.timeshipmodding.villagecraft3essentials.event.registries;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.content.command.WorldSpawnCommand;
import com.timeshipmodding.villagecraft3essentials.content.command.ambercaves.*;
import com.timeshipmodding.villagecraft3essentials.content.command.grippercity.*;
import com.timeshipmodding.villagecraft3essentials.content.command.tpa.TpaCommand;
import com.timeshipmodding.villagecraft3essentials.content.command.tpa.TpacceptCommand;
import com.timeshipmodding.villagecraft3essentials.content.command.tpa.TpadenyCommand;
import com.timeshipmodding.villagecraft3essentials.content.command.tpa.TpahereCommand;
import com.timeshipmodding.villagecraft3essentials.content.command.villagecraftcity.*;
import com.timeshipmodding.villagecraft3essentials.infrastructure.networking.packet.atm.AtmRandomConversionRatesPacket;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.saveddata.AtmRandomConversionRatesSavedData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.server.command.ConfigCommand;

@EventBusSubscriber(modid = VillageCraft3Essentials.MODID)
public class ModEvents {
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new AmberCavesBlacklistCommand(event.getDispatcher());
        new AmberCavesJailCommand(event.getDispatcher());
        new AmberCavesKickCommand(event.getDispatcher());
        new AmberCavesPardonCommand(event.getDispatcher());
        new AmberCavesSetJailCommand(event.getDispatcher());
        new AmberCavesSetSpawnCommand(event.getDispatcher());
        new AmberCavesSpawnCommand(event.getDispatcher());
        new AmberCavesWhitelistCommand(event.getDispatcher());
        new GripperCityBlacklistCommand(event.getDispatcher());
        new GripperCityJailCommand(event.getDispatcher());
        new GripperCityKickCommand(event.getDispatcher());
        new GripperCityPardonCommand(event.getDispatcher());
        new GripperCitySetJailCommand(event.getDispatcher());
        new GripperCitySetSpawnCommand(event.getDispatcher());
        new GripperCitySpawnCommand(event.getDispatcher());
        new GripperCityWhitelistCommand(event.getDispatcher());
        new TpacceptCommand(event.getDispatcher());
        new TpaCommand(event.getDispatcher());
        new TpadenyCommand(event.getDispatcher());
        new TpahereCommand(event.getDispatcher());
        new VillageCraftCityBlacklistCommand(event.getDispatcher());
        new VillageCraftCityJailCommand(event.getDispatcher());
        new VillageCraftCityKickCommand(event.getDispatcher());
        new VillageCraftCityPardonCommand(event.getDispatcher());
        new VillageCraftCitySetJailCommand(event.getDispatcher());
        new VillageCraftCitySetSpawnCommand(event.getDispatcher());
        new VillageCraftCitySpawnCommand(event.getDispatcher());
        new VillageCraftCityWhitelistCommand(event.getDispatcher());
        new WorldSpawnCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onPlayerJoined(OnDatapackSyncEvent event) {
        if (event.getPlayer() instanceof ServerPlayer player) {
            MinecraftServer server = player.getServer();

            if (server != null) {
                int[] diamondToRuby = AtmRandomConversionRatesSavedData.getData(server).getDiamondToRuby();
                int[] diamondToAmber = AtmRandomConversionRatesSavedData.getData(server).getDiamondToAmber();
                int[] rubyToDiamond = AtmRandomConversionRatesSavedData.getData(server).getRubyToDiamond();
                int[] rubyToAmber = AtmRandomConversionRatesSavedData.getData(server).getRubyToAmber();
                int[] amberToDiamond = AtmRandomConversionRatesSavedData.getData(server).getAmberToDiamond();
                int[] amberToRuby = AtmRandomConversionRatesSavedData.getData(server).getAmberToRuby();

                if (diamondToRuby != null && diamondToRuby.length > 0) {
                    PacketDistributor.sendToPlayer(player, new AtmRandomConversionRatesPacket(diamondToRuby, diamondToAmber, rubyToDiamond, rubyToAmber, amberToDiamond, amberToRuby));
                }
            }
        }
    }
}