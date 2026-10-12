package com.timeshipmodding.villagecraft3essentials.compat.bluemap.event.registries;

import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
import de.bluecolored.bluemap.api.BlueMapAPI;
import de.bluecolored.bluemap.api.BlueMapMap;
import de.bluecolored.bluemap.api.gson.MarkerGson;
import de.bluecolored.bluemap.api.markers.MarkerSet;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartedEvent;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ModBluemapEvents {
    public static void registerEvents() {
        NeoForge.EVENT_BUS.register(ModBluemapEvents.class);
    }

    @SubscribeEvent
    public static void onCommandsRegister(ServerStartedEvent event) {
        if (ModList.get().isLoaded("bluemap") && ServerConfig.ENABLE_SETSPAWN_BLUEMAP_MARKER_CREATION.get()) {
            BlueMapAPI.onEnable(api -> {
                File markersFolder = new File("bluemap/web/maps/world/markers");
                MarkerSet villagecraftCity = null;
                MarkerSet oasis = null;
                MarkerSet swedishFurnitureStore = null;
                MarkerSet london = null;
                MarkerSet blackWater = null;

                try (FileReader reader = new FileReader(new File(markersFolder, "villagecraft-city.json"))) {
                    villagecraftCity = MarkerGson.INSTANCE.fromJson(reader, MarkerSet.class);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                try (FileReader reader = new FileReader(new File(markersFolder, "oasis.json"))) {
                    oasis = MarkerGson.INSTANCE.fromJson(reader, MarkerSet.class);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                try (FileReader reader = new FileReader(new File(markersFolder,"swedish-furniture-store.json"))) {
                    swedishFurnitureStore = MarkerGson.INSTANCE.fromJson(reader, MarkerSet.class);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                try (FileReader reader = new FileReader(new File(markersFolder,"london.json"))) {
                    london = MarkerGson.INSTANCE.fromJson(reader, MarkerSet.class);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                try (FileReader reader = new FileReader(new File(markersFolder,"blackwater.json"))) {
                    blackWater = MarkerGson.INSTANCE.fromJson(reader, MarkerSet.class);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                MarkerSet finalVillagecraftCity = villagecraftCity;
                MarkerSet finalOasis = oasis;
                MarkerSet finalSwedishFurnitureStore = swedishFurnitureStore;
                MarkerSet finalLondon = london;
                MarkerSet finalBlackWater = blackWater;

                api.getWorld(event.getServer().overworld()).ifPresent(world -> {
                    for (BlueMapMap map : world.getMaps()) {
                        map.getMarkerSets().put("villagecraft-city", finalVillagecraftCity);
                        map.getMarkerSets().put("oasis", finalOasis);
                        map.getMarkerSets().put("swedishFurnitureStore", finalSwedishFurnitureStore);
                        map.getMarkerSets().put("london", finalLondon);
                        map.getMarkerSets().put("blackwater", finalBlackWater);
                    }
                });
            });
        }
    }
}