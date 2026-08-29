package com.timeshipmodding.villagecraft3essentials.compat.bluemap;

import com.flowpowered.math.vector.Vector3d;
import de.bluecolored.bluemap.api.BlueMapAPI;
import de.bluecolored.bluemap.api.BlueMapMap;
import de.bluecolored.bluemap.api.gson.MarkerGson;
import de.bluecolored.bluemap.api.markers.MarkerSet;
import de.bluecolored.bluemap.api.markers.POIMarker;
import net.minecraft.server.level.ServerPlayer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BlueMapMethods {
    public static void manageSpawnMarker(int xPos, int yPos, int zPos, String label, String markerSetId, ServerPlayer player) {
        BlueMapAPI.getInstance().flatMap(api -> api.getWorld(player.getServer().overworld())).ifPresent(world -> {
            for (BlueMapMap map : world.getMaps()) {
                if (map.getMarkerSets().get(markerSetId) != null) {
                    POIMarker spawnPointMarker = (POIMarker) map.getMarkerSets().get(markerSetId).getMarkers().get(markerSetId + "-spawn");
                    spawnPointMarker.setPosition(new Vector3d(xPos + 0.5, yPos, zPos + 0.5));

                    try (FileWriter writer = new FileWriter(new File(getMarkersFolder(), markerSetId + ".json"))) {
                        MarkerGson.INSTANCE.toJson(map.getMarkerSets().get(markerSetId), writer);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                } else {
                    createSpawnMarker(xPos + 0.5, yPos, zPos + 0.5, label, markerSetId, player);
                }
            }
        });
    }

    private static void createSpawnMarker(double xPos, int yPos, double zPos, String label, String markerSetId, ServerPlayer player) {
        BlueMapAPI.getInstance().ifPresent(api -> {

            POIMarker spawnPointMarker = POIMarker.builder()
                    .label(label + " Spawn Point")
                    .position(new Vector3d(xPos, yPos, zPos))
                    .maxDistance(10000000)
                    .build();

            api.getWorld(player.getServer().overworld()).ifPresent(world -> {
                for (BlueMapMap map : world.getMaps()) {
                    if (map.getMarkerSets().get(markerSetId) == null) {
                        MarkerSet markerSet = MarkerSet.builder()
                                .label(label + " Markers")
                                .build();

                        markerSet.getMarkers().put(markerSetId + "-spawn", spawnPointMarker);
                        map.getMarkerSets().put(markerSetId, markerSet);

                        try (FileWriter writer = new FileWriter(new File(getMarkersFolder(), markerSetId + ".json"))) {
                            MarkerGson.INSTANCE.toJson(markerSet, writer);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }

                    else {
                        map.getMarkerSets().get(markerSetId).put(markerSetId + "-spawn", spawnPointMarker);
                    }
                }
            });
        });
    }

    private static File getMarkersFolder() {
        File bluemapFolder = new File("bluemap/web/maps/world");
        File markersFolder = new File(bluemapFolder, "markers");

        if (!markersFolder.exists()) {
            markersFolder.mkdirs();
        }

        return markersFolder;
    }
}