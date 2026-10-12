package com.timeshipmodding.villagecraft3essentials.infrastructure.data.saveddata;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;

public class MissionSavedData extends SavedData {
    private int villagecraftCityCompletedMissions = 0;
    private int oasisCompletedMissions = 0;
    private int swedishFurnitureStoreCompletedMissions = 0;
    private int londonCompletedMissions = 0;
    private int blackWaterCompletedMissions = 0;

    public int getVillagecraftCityCompletedMissions() {
        return this.villagecraftCityCompletedMissions;
    }

    public int getOasisCompletedMissions() {
        return this.oasisCompletedMissions;
    }

    public int getSwedishFurnitureStoreCompletedMissions() {
        return this.swedishFurnitureStoreCompletedMissions;
    }

    public int getLondonCompletedMissions() {
        return this.londonCompletedMissions;
    }

    public int getBlackWaterCompletedMissions() {
        return this.blackWaterCompletedMissions;
    }

    public void addVillagecraftCityCompletedMissions(int amount) {
        this.villagecraftCityCompletedMissions += amount;
        this.setDirty();
    }

    public void addOasisCompletedMissions(int amount) {
        this.oasisCompletedMissions += amount;
        this.setDirty();
    }

    public void addSwedishFurnitureStoreCompletedMissions(int amount) {
        this.swedishFurnitureStoreCompletedMissions += amount;
        this.setDirty();
    }

    public void addLondonCompletedMissions(int amount) {
        this.londonCompletedMissions += amount;
        this.setDirty();
    }

    public void addBlackWaterCompletedMissions(int amount) {
        this.blackWaterCompletedMissions += amount;
        this.setDirty();
    }

    public void resetVillagecraftCityCompletedMissions() {
        this.villagecraftCityCompletedMissions = 0;
        this.setDirty();
    }

    public void resetOasisCompletedMissions() {
        this.oasisCompletedMissions = 0;
        this.setDirty();
    }

    public void resetSwedishFurnitureStoreCompletedMissions() {
        this.swedishFurnitureStoreCompletedMissions = 0;
        this.setDirty();
    }

    public void resetLondonCompletedMissions() {
        this.londonCompletedMissions = 0;
        this.setDirty();
    }

    public void resetBlackWaterCompletedMissions() {
        this.blackWaterCompletedMissions = 0;
        this.setDirty();
    }

    public static MissionSavedData create() {
        return new MissionSavedData();
    }

    public static MissionSavedData load(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        MissionSavedData data = create();
        data.villagecraftCityCompletedMissions = tag.getInt("villagecraftCityCompletedMissions");
        data.oasisCompletedMissions = tag.getInt("oasisCompletedMissions");
        data.swedishFurnitureStoreCompletedMissions = tag.getInt("swedishFurnitureStoreCompletedMissions");
        data.londonCompletedMissions = tag.getInt("londonCompletedMissions");
        data.blackWaterCompletedMissions = tag.getInt("blackWaterCompletedMissions");
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putInt("villagecraftCityCompletedMissions", villagecraftCityCompletedMissions);
        tag.putInt("oasisCompletedMissions", oasisCompletedMissions);
        tag.putInt("swedishFurnitureStoreCompletedMissions", swedishFurnitureStoreCompletedMissions);
        tag.putInt("londonCompletedMissions", londonCompletedMissions);
        tag.putInt("blackWaterCompletedMissions", blackWaterCompletedMissions);
        return tag;
    }

    public static MissionSavedData getData(MinecraftServer server) {
        return server.overworld().getDataStorage().computeIfAbsent(new Factory<>(MissionSavedData::create, MissionSavedData::load), "villagecraft3essentials_completed_missions");
    }
}
