package com.timeshipmodding.villagecraft3essentials.infrastructure.data.saveddata;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;

public class SpawnSavedData extends SavedData { ;
    private int[] villagecraftCitySpawn = {0, 0, 0, 0, 0};
    private int[] oasisSpawn = {0, 0, 0, 0, 0};
    private int[] swedishFurnitureStoreSpawn = {0, 0, 0, 0, 0};
    private int[] londonSpawn = {0, 0, 0, 0, 0};
    private int[] blackWaterSpawn = {0, 0, 0, 0, 0};

    public int[] getVillagecraftCitySpawn() {
        return this.villagecraftCitySpawn;
    }

    public int[] getOasisSpawn() {
        return this.oasisSpawn;
    }

    public int[] getSwedishFurnitureStoreSpawn() {
        return this.swedishFurnitureStoreSpawn;
    }

    public int[] getLondonSpawn() {
        return this.londonSpawn;
    }

    public int[] getBlackWaterSpawn() {
        return this.blackWaterSpawn;
    }

    public void setVillagecraftCitySpawn(int[] spawn) {
        this.villagecraftCitySpawn = spawn;
        this.setDirty();
    }

    public void setOasisSpawn(int[] spawn) {
        this.oasisSpawn = spawn;
        this.setDirty();
    }

    public void setSwedishFurnitureStoreSpawn(int[] spawn) {
        this.swedishFurnitureStoreSpawn = spawn;
        this.setDirty();
    }

    public void setLondonSpawn(int[] spawn) {
        this.londonSpawn = spawn;
        this.setDirty();
    }

    public void setBlackWaterSpawn(int[] spawn) {
        this.blackWaterSpawn = spawn;
        this.setDirty();
    }

    public static SpawnSavedData create() {
        return new SpawnSavedData();
    }

    public static SpawnSavedData load(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        SpawnSavedData data = create();
        data.villagecraftCitySpawn = tag.getIntArray("villagecraftCitySpawn");
        data.oasisSpawn = tag.getIntArray("oasisSpawn");
        data.swedishFurnitureStoreSpawn = tag.getIntArray("swedishFurnitureStoreSpawn");
        data.londonSpawn = tag.getIntArray("londonSpawn");
        data.blackWaterSpawn = tag.getIntArray("blackWaterSpawn");
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putIntArray("villagecraftCitySpawn", villagecraftCitySpawn);
        tag.putIntArray("oasisSpawn", oasisSpawn);
        tag.putIntArray("swedishFurnitureStoreSpawn", swedishFurnitureStoreSpawn);
        tag.putIntArray("londanSpawn", londonSpawn);
        tag.putIntArray("blackWaterSpawn", blackWaterSpawn);
        return tag;
    }

    public static SpawnSavedData getData(MinecraftServer server) {
        return server.overworld().getDataStorage().computeIfAbsent(new Factory<>(SpawnSavedData::create, SpawnSavedData::load), "villagecraft3essentials_spawns");
    }
}