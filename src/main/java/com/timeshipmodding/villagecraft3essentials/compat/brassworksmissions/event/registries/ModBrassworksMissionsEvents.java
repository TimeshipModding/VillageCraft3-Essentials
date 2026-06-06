package com.timeshipmodding.villagecraft3essentials.compat.brassworksmissions.event.registries;

import com.timeshipmodding.villagecraft3essentials.compat.luckperms.LuckpermsMethods;
import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.saveddata.MissionSavedData;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.NeoForge;
import net.swzo.brassworksmissions.event.MissionEvent;

public class ModBrassworksMissionsEvents {
    public static void registerEvents() {
        NeoForge.EVENT_BUS.register(ModBrassworksMissionsEvents.class);
    }

    @SubscribeEvent
    public static void onMissionComplete(MissionEvent.Completed event) {
        System.out.println("mission complete");

        if (ModList.get().isLoaded("luckperms") && ModList.get().isLoaded("brassworksmissions") && (ServerConfig.ENABLE_WAR_SYSTEM_FEATURES.get())) {
            MissionSavedData data = MissionSavedData.getData(event.getPlayer().server);

            if (LuckpermsMethods.isInGroup(event.getPlayer(), ServerConfig.VILLAGECRAFTCITY_GROUP_NAME.get())) {
                data.addVillagecraftCompletedMissions(1);

            } else if (LuckpermsMethods.isInGroup(event.getPlayer(), ServerConfig.GRIPPERCITY_GROUP_NAME.get())) {
                data.addGripperCityCompletedMissions(1);

            } else if (LuckpermsMethods.isInGroup(event.getPlayer(), ServerConfig.AMBERCAVES_GROUP_NAME.get())) {
                data.addAmberCavesCompletedMissions(1);
            }
        }
    }
}