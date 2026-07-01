package com.timeshipmodding.villagecraft3essentials.compat.bluemap;

import com.flowpowered.math.vector.Vector3d;
import de.bluecolored.bluemap.api.BlueMapAPI;
import de.bluecolored.bluemap.api.BlueMapMap;
import de.bluecolored.bluemap.api.markers.MarkerSet;
import de.bluecolored.bluemap.api.markers.POIMarker;
import net.minecraft.server.level.ServerPlayer;

public class BlueMapMethods {
    public static void manageSpawnMarker(int xPos, int yPos, int zPos, String label, String markerSetId, ServerPlayer player) {
        BlueMapAPI.getInstance().flatMap(api -> api.getWorld(player.getServer().overworld())).ifPresent(world -> {
            for (BlueMapMap map : world.getMaps()) {
                if (map.getMarkerSets().get(markerSetId) != null) {
                    POIMarker spawnPointMarker = (POIMarker) map.getMarkerSets().get(markerSetId).getMarkers().get(markerSetId + "-spawn");
                    spawnPointMarker.setPosition(new Vector3d(xPos, yPos, zPos));

                } else {
                    createSpawnMarker(xPos + 0.5, yPos, zPos + 0.5, label, markerSetId, player);
                }
            }
        });
    }

    public static void createSpawnMarker(double xPos, int yPos, double zPos, String label, String markerSetId, ServerPlayer player) {
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
                    }

                    else {
                        map.getMarkerSets().get(markerSetId).put(markerSetId + "-spawn", spawnPointMarker);
                    }
                }
            });
        });
    }
}