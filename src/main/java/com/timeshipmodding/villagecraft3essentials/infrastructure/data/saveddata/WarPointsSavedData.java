package com.timeshipmodding.villagecraft3essentials.infrastructure.data.saveddata;

import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.ScoreHolder;
import net.minecraft.world.scores.Scoreboard;

public class WarPointsSavedData extends SavedData {
    private int villagecraftCityWarPoints = 0;
    private int oasisWarPoints = 0;
    private int swedishFurnitureStoreWarPoints = 0;
    private int londonWarPoints = 0;
    private int blackWaterWarPoints = 0;
    private int villagecraftCityWarScore = 0;
    private int oasisWarScore = 0;
    private int swedishFurnitureStoreWarScore = 0;
    private int londonWarScore = 0;
    private int blackWaterWarScore = 0;

    public int getVillagecraftCityWarPoints() {
        return this.villagecraftCityWarPoints;
    }

    public int getOasisWarPoints() {
        return this.oasisWarPoints;
    }

    public int getSwedishFurnitureStoreWarPoints() {
        return this.swedishFurnitureStoreWarPoints;
    }

    public int getLondonWarPoints() {
        return this.londonWarPoints;
    }

    public int getBlackWaterWarPoints() {
        return this.blackWaterWarPoints;
    }

    public int getVillagecraftCityWarScore() {
        return this.villagecraftCityWarScore;
    }

    public int getOasisWarScore() {
        return this.oasisWarScore;
    }

    public int getSwedishFurnitureStoreWarScore() {
        return this.swedishFurnitureStoreWarScore;
    }

    public int getLondonWarScore() {
        return this.londonWarScore;
    }

    public int getBlackWaterWarScore() {
        return this.blackWaterWarScore;
    }

    public void addVillagecraftCityWarScore(ServerLevel level, int score) {
        this.villagecraftCityWarPoints += ((score * ServerConfig.VILLAGECRAFTCITY_POINT_MULTIPLIER.getAsInt()) + ServerConfig.VILLAGECRAFTCITY_COMPLETED_MISSIONS.getAsInt());
        this.villagecraftCityWarScore += score;
        this.syncDataToScoreboard(level);
        this.setDirty();
    }

    public void addOasisWarScore(ServerLevel level, int score) {
        this.oasisWarPoints += ((score * ServerConfig.OASIS_POINT_MULTIPLIER.getAsInt()) + ServerConfig.OASIS_COMPLETED_MISSIONS.getAsInt());
        this.oasisWarScore += score;
        this.syncDataToScoreboard(level);
        this.setDirty();
    }

    public void addSwedishFurnitureStoreWarScore(ServerLevel level, int score) {
        this.swedishFurnitureStoreWarPoints += ((score * ServerConfig.SWEDISHFURNITURESTORE_POINT_MULTIPLIER.getAsInt()) + ServerConfig.SWEDISHFURNITURESTORE_COMPLETED_MISSIONS.getAsInt());
        this.swedishFurnitureStoreWarScore += score;
        this.syncDataToScoreboard(level);
        this.setDirty();
    }

    public void addLondonWarScore(ServerLevel level, int score) {
        this.londonWarPoints += ((score * ServerConfig.LONDON_POINT_MULTIPLIER.getAsInt()) + ServerConfig.LONDON_COMPLETED_MISSIONS.getAsInt());
        this.londonWarScore += score;
        this.syncDataToScoreboard(level);
        this.setDirty();
    }

    public void addBlackWaterWarScore(ServerLevel level, int score) {
        this.blackWaterWarPoints += ((score * ServerConfig.BLACKWATER_POINT_MULTIPLIER.getAsInt()) + ServerConfig.BLACKWATER_COMPLETED_MISSIONS.getAsInt());
        this.blackWaterWarScore += score;
        this.syncDataToScoreboard(level);
        this.setDirty();
    }

    public void removeVillagecraftCityWarScore(ServerLevel level, int score) {
        this.villagecraftCityWarPoints -= ((score * ServerConfig.VILLAGECRAFTCITY_POINT_MULTIPLIER.getAsInt()) + ServerConfig.VILLAGECRAFTCITY_COMPLETED_MISSIONS.getAsInt());
        this.villagecraftCityWarScore -= score;
        this.syncDataToScoreboard(level);
        this.setDirty();
    }

    public void removeOasisWarScore(ServerLevel level, int score) {
        this.oasisWarPoints -= ((score * ServerConfig.OASIS_POINT_MULTIPLIER.getAsInt()) + ServerConfig.OASIS_COMPLETED_MISSIONS.getAsInt());
        this.oasisWarScore -= score;
        this.syncDataToScoreboard(level);
        this.setDirty();
    }

    public void removeSwedishFurnitureStoreWarScore(ServerLevel level, int score) {
        this.swedishFurnitureStoreWarScore -= ((score * ServerConfig.SWEDISHFURNITURESTORE_POINT_MULTIPLIER.getAsInt()) + ServerConfig.SWEDISHFURNITURESTORE_COMPLETED_MISSIONS.getAsInt());
        this.swedishFurnitureStoreWarScore -= score;
        this.syncDataToScoreboard(level);
        this.setDirty();
    }

    public void removeLondonWarScore(ServerLevel level, int score) {
        this.londonWarPoints -= ((score * ServerConfig.LONDON_POINT_MULTIPLIER.getAsInt()) + ServerConfig.LONDON_COMPLETED_MISSIONS.getAsInt());
        this.londonWarScore -= score;
        this.syncDataToScoreboard(level);
        this.setDirty();
    }

    public void removeBlackWaterWarScore(ServerLevel level, int score) {
        this.blackWaterWarPoints -= ((score * ServerConfig.BLACKWATER_POINT_MULTIPLIER.getAsInt()) + ServerConfig.BLACKWATER_COMPLETED_MISSIONS.getAsInt());
        this.blackWaterWarScore -= score;
        this.syncDataToScoreboard(level);
        this.setDirty();
    }

    public void resetVillagecraftCityWarScore(ServerLevel level) {
        this.villagecraftCityWarPoints = 0;
        this.villagecraftCityWarScore = 0;
        this.syncDataToScoreboard(level);
        this.setDirty();
    }

    public void resetOasisWarScore(ServerLevel level) {
        this.oasisWarPoints = 0;
        this.oasisWarScore = 0;
        this.syncDataToScoreboard(level);
        this.setDirty();
    }

    public void resetSwedishFurnitureStoreWarScore(ServerLevel level) {
        this.swedishFurnitureStoreWarPoints = 0;
        this.swedishFurnitureStoreWarScore = 0;
        this.syncDataToScoreboard(level);
        this.setDirty();
    }

    public void resetLondonWarScore(ServerLevel level) {
        this.londonWarPoints = 0;
        this.londonWarScore = 0;
        this.syncDataToScoreboard(level);
        this.setDirty();
    }

    public void resetBlackWaterWarScore(ServerLevel level) {
        this.blackWaterWarPoints = 0;
        this.blackWaterWarScore = 0;
        this.syncDataToScoreboard(level);
        this.setDirty();
    }

    public static WarPointsSavedData create() {
        return new WarPointsSavedData();
    }

    public static WarPointsSavedData load(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        WarPointsSavedData data = create();
        data.villagecraftCityWarPoints = tag.getInt("villagecraftCityWarPoints");
        data.oasisWarPoints = tag.getInt("oasisWarPoints");
        data.swedishFurnitureStoreWarPoints = tag.getInt("swedishFurnitureStoreWarPoints");
        data.londonWarPoints = tag.getInt("londonWarPoints");
        data.blackWaterWarPoints = tag.getInt("blackWaterWarPoints");
        data.villagecraftCityWarScore = tag.getInt("villagecraftCityWarScore");
        data.oasisWarScore = tag.getInt("oasisWarScore");
        data.swedishFurnitureStoreWarScore = tag.getInt("swedishFurnitureStoreWarScore");
        data.londonWarScore = tag.getInt("londonWarScore");
        data.blackWaterWarScore = tag.getInt("blackWaterWarScore");
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putInt("villagecraftCityWarPoints", villagecraftCityWarPoints);
        tag.putInt("oasisWarPoints", oasisWarPoints);
        tag.putInt("swedishFurnitureStoreWarPoints", swedishFurnitureStoreWarPoints);
        tag.putInt("londonWarPoints", londonWarPoints);
        tag.putInt("blackWaterWarPoints", blackWaterWarPoints);
        tag.putInt("villagecraftCityWarScore", villagecraftCityWarScore);
        tag.putInt("oasisWarScore", oasisWarScore);
        tag.putInt("swedishFurnitureStoreWarScore", swedishFurnitureStoreWarScore);
        tag.putInt("londonWarScore", londonWarScore);
        tag.putInt("blackWaterWarScore", blackWaterWarScore);
        return tag;
    }

    private void syncDataToScoreboard(ServerLevel level) {
        Scoreboard scoreboard = level.getScoreboard();
        Objective warPointsLeaderboard = scoreboard.getObjective("war_points_leaderboard");

        if (warPointsLeaderboard != null) {
            scoreboard.getOrCreatePlayerScore(ScoreHolder.forNameOnly("VillageCraft City"), warPointsLeaderboard).set(this.villagecraftCityWarPoints);
            scoreboard.getOrCreatePlayerScore(ScoreHolder.forNameOnly("Oasis"), warPointsLeaderboard).set(this.oasisWarPoints);
            scoreboard.getOrCreatePlayerScore(ScoreHolder.forNameOnly("Swedish Furniture Store"), warPointsLeaderboard).set(this.swedishFurnitureStoreWarPoints);
            scoreboard.getOrCreatePlayerScore(ScoreHolder.forNameOnly("London"), warPointsLeaderboard).set(this.londonWarPoints);
            scoreboard.getOrCreatePlayerScore(ScoreHolder.forNameOnly("BlackWater"), warPointsLeaderboard).set(this.blackWaterWarPoints);
        }
    }

    public static WarPointsSavedData getData(MinecraftServer server) {
        return server.overworld().getDataStorage().computeIfAbsent(new Factory<>(WarPointsSavedData::create, WarPointsSavedData::load), "villagecraft3essentials_war_points");
    }
}