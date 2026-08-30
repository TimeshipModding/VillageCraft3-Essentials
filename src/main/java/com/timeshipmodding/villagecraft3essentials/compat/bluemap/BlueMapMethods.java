package com.timeshipmodding.villagecraft3essentials.compat.bluemap;

import com.flowpowered.math.vector.Vector2d;
import com.flowpowered.math.vector.Vector3d;
import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
import de.bluecolored.bluemap.api.BlueMapAPI;
import de.bluecolored.bluemap.api.BlueMapMap;
import de.bluecolored.bluemap.api.gson.MarkerGson;
import de.bluecolored.bluemap.api.markers.ExtrudeMarker;
import de.bluecolored.bluemap.api.markers.Marker;
import de.bluecolored.bluemap.api.markers.MarkerSet;
import de.bluecolored.bluemap.api.markers.POIMarker;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class BlueMapMethods {
    private static ExtrudeMarker townBordersMarker = null;

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

    public static String town(Player player) {
        if (ServerConfig.ENABLE_WAR_SYSTEM_FEATURES.get()) {
            return "The War Server";
        } else if (player.level().dimension() != Level.OVERWORLD) {
            return "The Wild";
        } else if (isPlayerInAreaMarker(player, "villagecraft-city")) {
            return "VillageCraft City";
        } else if (isPlayerInAreaMarker(player, "gripper-city")) {
            return "Gripper City";
        } else if (isPlayerInAreaMarker(player, "amber-caves")) {
            return "The Amber Caves";
        } else {
            return "The Wild";
        }
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

    private static boolean isPlayerInAreaMarker(Player player, String markerSetId) {
        BlueMapAPI.getInstance().flatMap(api -> api.getWorld(player.getServer().overworld())).ifPresent(world -> {
            for (BlueMapMap map : world.getMaps()) {
                MarkerSet markerSet = map.getMarkerSets().get(markerSetId);

                if (markerSet == null) {
                    return;
                }

                Marker marker = markerSet.getMarkers().get(markerSetId + "-borders");

                if (marker instanceof ExtrudeMarker) {
                    townBordersMarker = (ExtrudeMarker) marker;
                }
            }
        });

        if (townBordersMarker == null) {
            return false;
        }

        List<Vector2d> points = List.of(townBordersMarker.getShape().getPoints());
        boolean isPlayerInsideMarker = false;
        int j = points.size() - 1;

        for (int i = 0; i < points.size(); i++) {
            Vector2d point1 = points.get(i);
            Vector2d point2 = points.get(j);

            if ((point1.getY() > player.getZ()) != (point2.getY() > player.getZ()) &&
                    (player.getX() < (point2.getX() - point1.getX()) * (player.getZ() - point1.getY()) / (point2.getY() - point1.getY()) + point1.getX())) {
                isPlayerInsideMarker = !isPlayerInsideMarker;
            }

            j = i;
        }

        return isPlayerInsideMarker;
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