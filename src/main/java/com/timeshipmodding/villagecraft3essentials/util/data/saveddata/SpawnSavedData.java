package com.timeshipmodding.villagecraft3essentials.util.data.saveddata;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;

public class SpawnSavedData extends SavedData { ;
    private int[] villagecraftCitySpawn = {0, 0, 0, 0, 0};
    private int[] gripperCitySpawn = {0, 0, 0, 0, 0};
    private int[] amberCavesSpawn = {0, 0, 0, 0, 0};

    public int[] getVillagecraftCitySpawn() {
        return this.villagecraftCitySpawn;
    }

    public int[] getGripperCitySpawn() {
        return this.gripperCitySpawn;
    }

    public int[] getAmberCavesSpawn() {
        return this.amberCavesSpawn;
    }

    public void setVillagecraftCitySpawn(int[] spawn) {
        this.villagecraftCitySpawn = spawn;
        this.setDirty();
    }

    public void setGripperCitySpawn(int[] spawn) {
        this.gripperCitySpawn = spawn;
        this.setDirty();
    }

    public void setAmberCavesSpawn(int[] spawn) {
        this.amberCavesSpawn = spawn;
        this.setDirty();
    }

    public static SpawnSavedData create() {
        return new SpawnSavedData();
    }

    public static SpawnSavedData load(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        SpawnSavedData data = create();
        data.villagecraftCitySpawn = tag.getIntArray("villagecraftCitySpawn");
        data.gripperCitySpawn = tag.getIntArray("gripperCitySpawn");
        data.amberCavesSpawn = tag.getIntArray("amberCavesSpawn");
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putIntArray("villagecraftCitySpawn", villagecraftCitySpawn);
        tag.putIntArray("gripperCitySpawn", gripperCitySpawn);
        tag.putIntArray("amberCavesSpawn", amberCavesSpawn);
        return tag;
    }

    public static SpawnSavedData getData(MinecraftServer server) {
        return server.overworld().getDataStorage().computeIfAbsent(new Factory<>(SpawnSavedData::create, SpawnSavedData::load), "villagecraft3essentials_spawns");
    }
}