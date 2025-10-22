package com.timeshipmodding.villagecraft3essentials.util.saveddata;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;

public class CurrencyConversionSavedData extends SavedData { ;
    private int[] diamondToRuby = {0, 0};
    private int[] diamondToAmber = {0, 0};
    private int[] rubyToDiamond = {0, 0};
    private int[] rubyToAmber = {0, 0};
    private int[] amberToDiamond = {0, 0};
    private int[] amberToRuby = {0, 0};

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

    public void setDiamondToRuby(int[] conversion) {
        this.diamondToRuby = conversion;
        this.setDirty();
    }

    public void setDiamondToAmber(int[] conversion) {
        this.diamondToAmber = conversion;
        this.setDirty();
    }

    public void setRubyToDiamond(int[] conversion) {
        this.rubyToDiamond = conversion;
        this.setDirty();
    }

    public void setRubyToAmber(int[] conversion) {
        this.rubyToAmber = conversion;
        this.setDirty();
    }

    public void setAmberToDiamond(int[] conversion) {
        this.amberToDiamond = conversion;
        this.setDirty();
    }

    public void setAmberToRuby(int[] conversion) {
        this.amberToRuby = conversion;
        this.setDirty();
    }

    // Create new instance of saved data
    public static CurrencyConversionSavedData create() {
        return new CurrencyConversionSavedData();
    }

    // Load existing instance of saved data
    public static CurrencyConversionSavedData load(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        CurrencyConversionSavedData data = create();
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
        tag.putIntArray("amberToRuby", amberToDiamond);
        return tag;
    }

    public static CurrencyConversionSavedData getData(MinecraftServer server) {
        return server.overworld().getDataStorage().computeIfAbsent(new Factory<>(CurrencyConversionSavedData::create, CurrencyConversionSavedData::load), "villagecraft3essentials_spawns");
    }
}