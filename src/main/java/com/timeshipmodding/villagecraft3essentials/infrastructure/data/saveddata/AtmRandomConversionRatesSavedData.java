package com.timeshipmodding.villagecraft3essentials.infrastructure.data.saveddata;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;

public class AtmRandomConversionRatesSavedData extends SavedData { ;
    private int[] diamondToJade;
    private int[] diamondToAmber;
    private int[] diamondToSilicon;
    private int[] diamondToRuby;
    private int[] jadeToDiamond;
    private int[] jadeToAmber;
    private int[] jadeToSilicon;
    private int[] jadeToRuby;
    private int[] amberToDiamond;
    private int[] amberToJade;
    private int[] amberToSilicon;
    private int[] amberToRuby;
    private int[] siliconToDiamond;
    private int[] siliconToJade;
    private int[] siliconToAmber;
    private int[] siliconToRuby;
    private int[] rubyToDiamond;
    private int[] rubyToJade;
    private int[] rubyToAmber;
    private int[] rubyToSilicon;

    public int[] getDiamondToJade() {
        return diamondToJade;
    }

    public int[] getDiamondToAmber() {
        return diamondToAmber;
    }

    public int[] getDiamondToSilicon() {
        return diamondToSilicon;
    }

    public int[] getDiamondToRuby() {
        return diamondToRuby;
    }

    public int[] getJadeToDiamond() {
        return jadeToDiamond;
    }

    public int[] getJadeToAmber() {
        return jadeToAmber;
    }

    public int[] getJadeToSilicon() {
        return jadeToSilicon;
    }

    public int[] getJadeToRuby() {
        return jadeToRuby;
    }

    public int[] getAmberToDiamond() {
        return amberToDiamond;
    }

    public int[] getAmberToJade() {
        return amberToJade;
    }

    public int[] getAmberToSilicon() {
        return amberToSilicon;
    }

    public int[] getAmberToRuby() {
        return amberToRuby;
    }

    public int[] getSiliconToDiamond() {
        return siliconToDiamond;
    }

    public int[] getSiliconToJade() {
        return siliconToJade;
    }

    public int[] getSiliconToAmber() {
        return siliconToAmber;
    }

    public int[] getSiliconToRuby() {
        return siliconToRuby;
    }

    public int[] getRubyToDiamond() {
        return rubyToDiamond;
    }

    public int[] getRubyToJade() {
        return rubyToJade;
    }

    public int[] getRubyToAmber() {
        return rubyToAmber;
    }

    public int[] getRubyToSilicon() {
        return rubyToSilicon;
    }

    public void setDiamondToJade(int[] conversionRate) {
        diamondToJade = conversionRate;
        setDirty();
    }

    public void setDiamondToAmber(int[] conversionRate) {
        diamondToAmber = conversionRate;
        setDirty();
    }

    public void setDiamondToSilicon(int[] conversionRate) {
        diamondToSilicon = conversionRate;
        setDirty();
    }

    public void setDiamondToRuby(int[] conversionRate) {
        diamondToRuby = conversionRate;
        setDirty();
    }

    public void setJadeToDiamond(int[] conversionRate) {
        jadeToDiamond = conversionRate;
        setDirty();
    }

    public void setJadeToAmber(int[] conversionRate) {
        jadeToAmber = conversionRate;
        setDirty();
    }

    public void setJadeToSilicon(int[] conversionRate) {
        jadeToSilicon = conversionRate;
        setDirty();
    }

    public void setJadeToRuby(int[] conversionRate) {
        jadeToRuby = conversionRate;
        setDirty();
    }

    public void setAmberToDiamond(int[] conversionRate) {
        amberToDiamond = conversionRate;
        setDirty();
    }

    public void setAmberToJade(int[] conversionRate) {
        amberToJade = conversionRate;
        setDirty();
    }

    public void setAmberToSilicon(int[] conversionRate) {
        amberToSilicon = conversionRate;
        setDirty();
    }

    public void setAmberToRuby(int[] conversionRate) {
        amberToRuby = conversionRate;
        setDirty();
    }

    public void setSiliconToDiamond(int[] conversionRate) {
        siliconToDiamond = conversionRate;
        setDirty();
    }

    public void setSiliconToJade(int[] conversionRate) {
        siliconToJade = conversionRate;
        setDirty();
    }

    public void setSiliconToAmber(int[] conversionRate) {
        siliconToAmber = conversionRate;
        setDirty();
    }

    public void setSiliconToRuby(int[] conversionRate) {
        siliconToRuby = conversionRate;
        setDirty();
    }

    public void setRubyToDiamond(int[] conversionRate) {
        rubyToDiamond = conversionRate;
        setDirty();
    }

    public void setRubyToJade(int[] conversionRate) {
        rubyToJade = conversionRate;
        setDirty();
    }

    public void setRubyToAmber(int[] conversionRate) {
        rubyToAmber = conversionRate;
        setDirty();
    }

    public void setRubyToSilicon(int[] conversionRate) {
        rubyToSilicon = conversionRate;
        setDirty();
    }

    public static AtmRandomConversionRatesSavedData create() {
        return new AtmRandomConversionRatesSavedData();
    }

    public static AtmRandomConversionRatesSavedData load(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        AtmRandomConversionRatesSavedData data = create();
        data.diamondToJade = tag.getIntArray("diamondToJade");
        data.diamondToAmber = tag.getIntArray("diamondToAmber");
        data.diamondToSilicon = tag.getIntArray("diamondToSilicon");
        data.diamondToRuby = tag.getIntArray("diamondToRuby");
        data.jadeToDiamond = tag.getIntArray("jadeToDiamond");
        data.jadeToAmber = tag.getIntArray("jadeToAmber");
        data.jadeToSilicon = tag.getIntArray("jadeToSilicon");
        data.jadeToRuby = tag.getIntArray("jadeToRuby");
        data.amberToDiamond = tag.getIntArray("amberToDiamond");
        data.amberToJade = tag.getIntArray("amberToJade");
        data.amberToSilicon = tag.getIntArray("amberToSilicon");
        data.amberToRuby = tag.getIntArray("amberToRuby");
        data.siliconToDiamond = tag.getIntArray("siliconToDiamond");
        data.siliconToJade = tag.getIntArray("siliconToJade");
        data.siliconToAmber = tag.getIntArray("siliconToAmber");
        data.siliconToRuby = tag.getIntArray("siliconToRuby");
        data.rubyToDiamond = tag.getIntArray("rubyToDiamond");
        data.rubyToJade = tag.getIntArray("rubyToJade");
        data.rubyToAmber = tag.getIntArray("rubyToAmber");
        data.rubyToSilicon = tag.getIntArray("rubyToSilicon");
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putIntArray("diamondToJade", diamondToJade);
        tag.putIntArray("diamondToAmber", diamondToAmber);
        tag.putIntArray("diamondToSilicon", diamondToSilicon);
        tag.putIntArray("diamondToRuby", diamondToRuby);
        tag.putIntArray("jadeToDiamond", jadeToDiamond);
        tag.putIntArray("jadeToAmber", jadeToAmber);
        tag.putIntArray("jadeToSilicon", jadeToSilicon);
        tag.putIntArray("jadeToRuby", jadeToRuby);
        tag.putIntArray("amberToDiamond", amberToDiamond);
        tag.putIntArray("amberToJade", amberToJade);
        tag.putIntArray("amberToSilicon", amberToSilicon);
        tag.putIntArray("amberToRuby", amberToRuby);
        tag.putIntArray("siliconToDiamond", siliconToDiamond);
        tag.putIntArray("siliconToJade", siliconToJade);
        tag.putIntArray("siliconToAmber", siliconToAmber);
        tag.putIntArray("siliconToRuby", siliconToRuby);
        tag.putIntArray("rubyToDiamond", rubyToDiamond);
        tag.putIntArray("rubyToJade", rubyToJade);
        tag.putIntArray("rubyToAmber", rubyToAmber);
        tag.putIntArray("rubyToSilicon", rubyToSilicon);
        return tag;
    }

    public static AtmRandomConversionRatesSavedData getData(MinecraftServer server) {
        return server.overworld().getDataStorage().computeIfAbsent(new Factory<>(AtmRandomConversionRatesSavedData::create, AtmRandomConversionRatesSavedData::load), "villagecraft3essentials_conversion_rates");
    }
}