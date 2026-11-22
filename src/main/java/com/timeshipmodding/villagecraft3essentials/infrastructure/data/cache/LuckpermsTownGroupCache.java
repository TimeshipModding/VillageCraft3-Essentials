package com.timeshipmodding.villagecraft3essentials.infrastructure.data.cache;

import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class LuckpermsTownGroupCache {
    private static final Set<UUID> VILLAGECRAFT_CITY_PLAYERS = ConcurrentHashMap.newKeySet();
    private static final Set<UUID> OASIS_PLAYERS = ConcurrentHashMap.newKeySet();
    private static final Set<UUID> SWEDISH_FURNITURE_STORE_PLAYERS = ConcurrentHashMap.newKeySet();
    private static final Set<UUID> LONDON_PLAYERS = ConcurrentHashMap.newKeySet();
    private static final Set<UUID> BLACKWATER_PLAYERS = ConcurrentHashMap.newKeySet();

    public static void setPlayerStatus(UUID playerUuid, boolean inVillageCraftCity, boolean inOasis, boolean inSwedishFurnitureStore, boolean inLondon, boolean inBlackWater) {
        if (inVillageCraftCity) {
            VILLAGECRAFT_CITY_PLAYERS.add(playerUuid);

        } else if (inOasis) {
            OASIS_PLAYERS.add(playerUuid);

        } else if (inSwedishFurnitureStore) {
            SWEDISH_FURNITURE_STORE_PLAYERS.add(playerUuid);

        } else if (inLondon) {
            LONDON_PLAYERS.add(playerUuid);

        } else if (inBlackWater) {
            BLACKWATER_PLAYERS.add(playerUuid);

        } else {
            VILLAGECRAFT_CITY_PLAYERS.remove(playerUuid);
            OASIS_PLAYERS.remove(playerUuid);
            SWEDISH_FURNITURE_STORE_PLAYERS.remove(playerUuid);
            LONDON_PLAYERS.remove(playerUuid);
            BLACKWATER_PLAYERS.remove(playerUuid);
        }
    }

    public static boolean isInTownGroup(UUID playerUuid, String town) {
        if (town.equals(ServerConfig.VILLAGECRAFTCITY_GROUP_NAME.get())) {
            return VILLAGECRAFT_CITY_PLAYERS.contains(playerUuid);

        } else if (town.equals(ServerConfig.OASIS_GROUP_NAME.get())) {
            return OASIS_PLAYERS.contains(playerUuid);

        } else if (town.equals(ServerConfig.SWEDISHFURNITURESTORE_GROUP_NAME.get())) {
            return SWEDISH_FURNITURE_STORE_PLAYERS.contains(playerUuid);

        } else if (town.equals(ServerConfig.LONDON_GROUP_NAME.get())) {
            return LONDON_PLAYERS.contains(playerUuid);

        } else if (town.equals(ServerConfig.BLACKWATER_GROUP_NAME.get())) {
            return BLACKWATER_PLAYERS.contains(playerUuid);
        }

        return false;
    }

    public static void clear() {
        VILLAGECRAFT_CITY_PLAYERS.clear();
        OASIS_PLAYERS.clear();
        SWEDISH_FURNITURE_STORE_PLAYERS.clear();
        LONDON_PLAYERS.clear();
        BLACKWATER_PLAYERS.clear();
    }
}