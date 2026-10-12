package com.timeshipmodding.villagecraft3essentials.infrastructure.data.saveddata;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;

public class JailSavedData extends SavedData { ;
    private int[] villagecraftCityJail = {0, 0, 0, 0, 0};
    private int[] oasisJail = {0, 0, 0, 0, 0};
    private int[] swedishFurnitureStoreJail = {0, 0, 0, 0, 0};
    private int[] londonJail = {0, 0, 0, 0, 0};
    private int[] blackWaterJail = {0, 0, 0, 0, 0};

    public int[] getVillagecraftCityJail() {
        return this.villagecraftCityJail;
    }

    public int[] getOasisJail() {
        return this.oasisJail;
    }
    
    public int[] getSwedishFurnitureStoreJail() {
        return this.swedishFurnitureStoreJail;
    }
    
    public int[] getLondonJail() {
        return this.londonJail;
    }

    public int[] getBlackWaterJail() {
        return this.blackWaterJail;
    }

    public void setVillagecraftCityJail(int[] jail) {
        this.villagecraftCityJail = jail;
        this.setDirty();
    }

    public void setOasisJail(int[] jail) {
        this.oasisJail = jail;
        this.setDirty();
    }

    public void setSwedishFurnitureStoreJail(int[] jail) {
        this.swedishFurnitureStoreJail = jail;
        this.setDirty();
    }

    public void setLondonJail(int[] jail) {
        this.londonJail = jail;
        this.setDirty();
    }

    public void setBlackWaterJail(int[] jail) {
        this.blackWaterJail = jail;
        this.setDirty();
    }

    public static JailSavedData create() {
        return new JailSavedData();
    }

    public static JailSavedData load(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        JailSavedData data = create();
        data.villagecraftCityJail = tag.getIntArray("villagecraftCityJail");
        data.oasisJail = tag.getIntArray("oasisJail");
        data.swedishFurnitureStoreJail = tag.getIntArray("swedishFurnitureStoreJail");
        data.londonJail = tag.getIntArray("londonJail");
        data.blackWaterJail = tag.getIntArray("blackWaterJail");
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putIntArray("villagecraftCityJail", villagecraftCityJail);
        tag.putIntArray("oasisJail", oasisJail);
        tag.putIntArray("swedishFurnitureStoreJail", swedishFurnitureStoreJail);
        tag.putIntArray("londonJail", londonJail);
        tag.putIntArray("blackWaterJail", blackWaterJail);
        return tag;
    }

    public static JailSavedData getData(MinecraftServer server) {
        return server.overworld().getDataStorage().computeIfAbsent(new Factory<>(JailSavedData::create, JailSavedData::load), "villagecraft3essentials_jails");
    }
}