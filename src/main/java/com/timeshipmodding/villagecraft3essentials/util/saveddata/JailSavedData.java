package com.timeshipmodding.villagecraft3essentials.util.saveddata;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;

public class JailSavedData extends SavedData { ;
    private int[] villagecraftCityJail = {0, 0, 0, 0, 0};
    private int[] gripperCityJail = {0, 0, 0, 0, 0};
    private int[] amberCavesJail = {0, 0, 0, 0, 0};

    public int[] getVillagecraftCityJail() {
        return this.villagecraftCityJail;
    }
    
    public int[] getGripperCityJail() {
        return this.gripperCityJail;
    }
    
    public int[] getAmberCavesJail() {
        return this.amberCavesJail;
    }

    public void setVillagecraftCityJail(int[] jail) {
        this.villagecraftCityJail = jail;
        this.setDirty();
    }

    public void setGripperCityJail(int[] jail) {
        this.gripperCityJail = jail;
        this.setDirty();
    }

    public void setAmberCavesJail(int[] jail) {
        this.amberCavesJail = jail;
        this.setDirty();
    }

    // Create new instance of saved data
    public static JailSavedData create() {
        return new JailSavedData();
    }

    // Load existing instance of saved data
    public static JailSavedData load(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        JailSavedData data = create();
        data.villagecraftCityJail = tag.getIntArray("villagecraftCityJail");
        data.gripperCityJail = tag.getIntArray("gripperCityJail");
        data.amberCavesJail = tag.getIntArray("amberCavesJail");
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putIntArray("villagecraftCityJail", villagecraftCityJail);
        tag.putIntArray("gripperCityJail", gripperCityJail);
        tag.putIntArray("amberCavesJail", amberCavesJail);
        return tag;
    }

    public static JailSavedData getData(MinecraftServer server) {
        return server.overworld().getDataStorage().computeIfAbsent(new Factory<>(JailSavedData::create, JailSavedData::load), "villagecraft3essentials_jails");
    }
}