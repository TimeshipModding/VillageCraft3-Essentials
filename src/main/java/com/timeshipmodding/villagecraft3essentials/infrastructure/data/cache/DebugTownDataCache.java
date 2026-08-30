package com.timeshipmodding.villagecraft3essentials.infrastructure.data.cache;

public class DebugTownDataCache {
    private static String town = "";

    public static void setTown(String town) {
        DebugTownDataCache.town = town;
    }

    public static String getTown() {
        return town;
    }
}