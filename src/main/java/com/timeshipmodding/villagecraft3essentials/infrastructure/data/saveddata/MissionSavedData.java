package com.timeshipmodding.villagecraft3essentials.infrastructure.data.saveddata;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;

public class MissionSavedData extends SavedData {
    private int villagecraftCompletedMissions = 0;
    private int gripperCityCompletedMissions = 0;
    private int amberCavesCompletedMissions = 0;

    public int getVillagecraftCompletedMissions() {
        return this.villagecraftCompletedMissions;
    }

    public int getGripperCityCompletedMissions() {
        return this.gripperCityCompletedMissions;
    }

    public int getAmberCavesCompletedMissions() {
        return this.amberCavesCompletedMissions;
    }

    public void addVillagecraftCompletedMissions(int amount) {
        this.villagecraftCompletedMissions += amount;
        this.setDirty();
    }

    public void addGripperCityCompletedMissions(int amount) {
        this.gripperCityCompletedMissions += amount;
        this.setDirty();
    }

    public void addAmberCavesCompletedMissions(int amount) {
        this.amberCavesCompletedMissions += amount;
        this.setDirty();
    }

    public void resetVillagecraftCompletedMissions() {
        this.villagecraftCompletedMissions = 0;
        this.setDirty();
    }

    public void resetGripperCityCompletedMissions() {
        this.gripperCityCompletedMissions = 0;
        this.setDirty();
    }

    public void resetAmberCavesCompletedMissions() {
        this.amberCavesCompletedMissions = 0;
        this.setDirty();
    }

    public static MissionSavedData create() {
        return new MissionSavedData();
    }

    public static MissionSavedData load(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        MissionSavedData data = create();
        data.villagecraftCompletedMissions = tag.getInt("villagecraftCompletedMissions");
        data.gripperCityCompletedMissions = tag.getInt("gripperCityCompletedMissions");
        data.amberCavesCompletedMissions = tag.getInt("amberCavesCompletedMissions");
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putInt("villagecraftCompletedMissions", villagecraftCompletedMissions);
        tag.putInt("gripperCityCompletedMissions", gripperCityCompletedMissions);
        tag.putInt("amberCavesCompletedMissions", amberCavesCompletedMissions);
        return tag;
    }

    public static MissionSavedData getData(MinecraftServer server) {
        return server.overworld().getDataStorage().computeIfAbsent(new Factory<>(MissionSavedData::create, MissionSavedData::load), "villagecraft3essentials_completed_missions");
    }
}
