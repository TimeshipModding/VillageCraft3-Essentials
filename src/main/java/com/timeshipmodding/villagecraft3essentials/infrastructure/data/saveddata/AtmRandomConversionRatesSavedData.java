package com.timeshipmodding.villagecraft3essentials.infrastructure.data.saveddata;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;

public class AtmRandomConversionRatesSavedData extends SavedData { ;
    private int[] diamondToRuby;
    private int[] diamondToAmber;
    private int[] rubyToDiamond;
    private int[] rubyToAmber;
    private int[] amberToDiamond;
    private int[] amberToRuby;

    public int[] getDiamondToRuby() {
        return this.diamondToRuby;
    }

    public int[] getDiamondToAmber() {
        return this.diamondToAmber;
    }

    public int[] getRubyToDiamond() {
        return this.rubyToDiamond;
    }

    public int[] getRubyToAmber() {
        return this.rubyToAmber;
    }

    public int[] getAmberToDiamond() {
        return this.amberToDiamond;
    }

    public int[] getAmberToRuby() {
        return this.amberToRuby;
    }

    public void setDiamondToRuby(int[] jail) {
        this.diamondToRuby = jail;
        this.setDirty();
    }

    public void setDiamondToAmber(int[] jail) {
        this.diamondToAmber = jail;
        this.setDirty();
    }

    public void setRubyToDiamond(int[] jail) {
        this.rubyToDiamond = jail;
        this.setDirty();
    }

    public void setRubyToAmber(int[] jail) {
        this.rubyToAmber = jail;
        this.setDirty();
    }

    public void setAmberToDiamond(int[] jail) {
        this.amberToDiamond = jail;
        this.setDirty();
    }

    public void setAmberToRuby(int[] jail) {
        this.amberToRuby = jail;
        this.setDirty();
    }

    public static AtmRandomConversionRatesSavedData create() {
        return new AtmRandomConversionRatesSavedData();
    }

    public static AtmRandomConversionRatesSavedData load(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        AtmRandomConversionRatesSavedData data = create();
        data.diamondToRuby = tag.getIntArray("diamondToRuby");
        data.diamondToAmber = tag.getIntArray("diamondToAmber");
        data.rubyToDiamond = tag.getIntArray("rubyToDiamond");
        data.rubyToAmber = tag.getIntArray("rubyToAmber");
        data.amberToDiamond = tag.getIntArray("amberToDiamond");
        data.amberToRuby = tag.getIntArray("amberToRuby");
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putIntArray("diamondToRuby", diamondToRuby);
        tag.putIntArray("diamondToAmber", diamondToAmber);
        tag.putIntArray("rubyToDiamond", rubyToDiamond);
        tag.putIntArray("rubyToAmber", rubyToAmber);
        tag.putIntArray("amberToDiamond", amberToDiamond);
        tag.putIntArray("amberToRuby", amberToRuby);
        return tag;
    }

    public static AtmRandomConversionRatesSavedData getData(MinecraftServer server) {
        return server.overworld().getDataStorage().computeIfAbsent(new Factory<>(AtmRandomConversionRatesSavedData::create, AtmRandomConversionRatesSavedData::load), "villagecraft3essentials_conversion_rates");
    }
}