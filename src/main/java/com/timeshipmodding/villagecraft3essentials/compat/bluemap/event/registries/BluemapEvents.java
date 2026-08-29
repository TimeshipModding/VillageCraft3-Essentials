package com.timeshipmodding.villagecraft3essentials.compat.bluemap.event.registries;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
import de.bluecolored.bluemap.api.BlueMapAPI;
import de.bluecolored.bluemap.api.BlueMapMap;
import de.bluecolored.bluemap.api.gson.MarkerGson;
import de.bluecolored.bluemap.api.markers.MarkerSet;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerStartedEvent;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@EventBusSubscriber(modid = VillageCraft3Essentials.MODID)
public class BluemapEvents {
    @SubscribeEvent
    public static void onCommandsRegister(ServerStartedEvent event) {
        if (ModList.get().isLoaded("bluemap") && ServerConfig.ENABLE_SETSPAWN_BLUEMAP_MARKER_CREATION.get()) {
            BlueMapAPI.onEnable(api -> {
                File markersFolder = new File("bluemap/web/maps/world/markers");
                MarkerSet villagecraftCity = null;
                MarkerSet gripperCity = null;
                MarkerSet amberCaves = null;

                try (FileReader reader = new FileReader(new File(markersFolder, "villagecraft-city.json"))) {
                    villagecraftCity = MarkerGson.INSTANCE.fromJson(reader, MarkerSet.class);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                try (FileReader reader = new FileReader(new File(markersFolder,"gripper-city.json"))) {
                    gripperCity = MarkerGson.INSTANCE.fromJson(reader, MarkerSet.class);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                try (FileReader reader = new FileReader(new File(markersFolder,"amber-caves.json"))) {
                    amberCaves = MarkerGson.INSTANCE.fromJson(reader, MarkerSet.class);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                MarkerSet finalVillagecraftCity = villagecraftCity;
                MarkerSet finalGripperCity = gripperCity;
                MarkerSet finalAmberCaves = amberCaves;

                api.getWorld(event.getServer().overworld()).ifPresent(world -> {
                    for (BlueMapMap map : world.getMaps()) {
                        map.getMarkerSets().put("villagecraft-city", finalVillagecraftCity);
                        map.getMarkerSets().put("gripper-city", finalGripperCity);
                        map.getMarkerSets().put("amber-caves", finalAmberCaves);
                    }
                });
            });
        }
    }
}