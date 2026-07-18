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
    private int gripperCityWarPoints = 0;
    private int amberCavesWarPoints = 0;
    private int villagecraftCityWarScore = 0;
    private int gripperCityWarScore = 0;
    private int amberCavesWarScore = 0;

    public int getVillagecraftCityWarPoints() {
        return this.villagecraftCityWarPoints;
    }

    public int getGripperCityWarPoints() {
        return this.gripperCityWarPoints;
    }

    public int getAmberCavesWarPoints() {
        return this.amberCavesWarPoints;
    }

    public int getVillagecraftCityWarScore() {
        return this.villagecraftCityWarScore;
    }

    public int getGripperCityWarScore() {
        return this.gripperCityWarScore;
    }

    public int getAmberCavesWarScore() {
        return this.amberCavesWarScore;
    }

    public void addVillagecraftCityWarScore(ServerLevel level, int score) {
        this.villagecraftCityWarPoints += ((score * ServerConfig.VILLAGECRAFTCITY_POINT_MULTIPLIER.getAsInt()) + ServerConfig.VILLAGECRAFTCITY_COMPLETED_MISSIONS.getAsInt());
        this.villagecraftCityWarScore += score;
        this.syncDataToScoreboard(level);
        this.setDirty();
    }

    public void addGripperCityWarScore(ServerLevel level, int score) {
        this.gripperCityWarPoints += ((score * ServerConfig.GRIPPERCITY_POINT_MULTIPLIER.getAsInt()) + ServerConfig.GRIPPERCITY_COMPLETED_MISSIONS.getAsInt());
        this.gripperCityWarScore += score;
        this.syncDataToScoreboard(level);
        this.setDirty();
    }

    public void addAmberCavesWarScore(ServerLevel level, int score) {
        this.amberCavesWarPoints += ((score * ServerConfig.AMBERCAVES_POINT_MULTIPLIER.getAsInt()) + ServerConfig.AMBERCAVES_COMPLETED_MISSIONS.getAsInt());
        this.amberCavesWarScore += score;
        this.syncDataToScoreboard(level);
        this.setDirty();
    }

    public void removeVillagecraftCityWarScore(ServerLevel level, int score) {
        MissionSavedData data = MissionSavedData.getData(level.getServer());
        this.villagecraftCityWarPoints -= ((score * ServerConfig.VILLAGECRAFTCITY_POINT_MULTIPLIER.getAsInt()) + data.getVillagecraftCompletedMissions());
        this.villagecraftCityWarScore -= score;
        this.syncDataToScoreboard(level);
        this.setDirty();
    }

    public void removeGripperCityWarScore(ServerLevel level, int score) {
        MissionSavedData data = MissionSavedData.getData(level.getServer());
        this.gripperCityWarPoints -= ((score * ServerConfig.GRIPPERCITY_POINT_MULTIPLIER.getAsInt()) + data.getGripperCityCompletedMissions());
        this.gripperCityWarScore -= score;
        this.syncDataToScoreboard(level);
        this.setDirty();
    }

    public void removeAmberCavesWarScore(ServerLevel level, int score) {
        MissionSavedData data = MissionSavedData.getData(level.getServer());
        this.amberCavesWarPoints -= ((score * ServerConfig.AMBERCAVES_POINT_MULTIPLIER.getAsInt()) + data.getAmberCavesCompletedMissions());
        this.amberCavesWarScore -= score;
        this.syncDataToScoreboard(level);
        this.setDirty();
    }

    public void resetVillagecraftCityWarScore(ServerLevel level) {
        this.villagecraftCityWarPoints = 0;
        this.villagecraftCityWarScore = 0;
        this.syncDataToScoreboard(level);
        this.setDirty();
    }

    public void resetGripperCityWarScore(ServerLevel level) {
        this.gripperCityWarPoints = 0;
        this.gripperCityWarScore = 0;
        this.syncDataToScoreboard(level);
        this.setDirty();
    }

    public void resetAmberCavesWarScore(ServerLevel level) {
        this.amberCavesWarPoints = 0;
        this.amberCavesWarScore = 0;
        this.syncDataToScoreboard(level);
        this.setDirty();
    }

    public static WarPointsSavedData create() {
        return new WarPointsSavedData();
    }

    public static WarPointsSavedData load(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        WarPointsSavedData data = create();
        data.villagecraftCityWarPoints = tag.getInt("villagecraftCityWarPoints");
        data.gripperCityWarPoints = tag.getInt("gripperCityWarPoints");
        data.amberCavesWarPoints = tag.getInt("amberCavesWarPoints");
        data.villagecraftCityWarScore = tag.getInt("villagecraftCityWarScore");
        data.gripperCityWarScore = tag.getInt("gripperCityWarScore");
        data.amberCavesWarScore = tag.getInt("amberCavesWarScore");
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putInt("villagecraftCityWarPoints", villagecraftCityWarPoints);
        tag.putInt("gripperCityWarPoints", gripperCityWarPoints);
        tag.putInt("amberCavesWarPoints", amberCavesWarPoints);
        tag.putInt("villagecraftCityWarScore", villagecraftCityWarScore);
        tag.putInt("gripperCityWarScore", gripperCityWarScore);
        tag.putInt("amberCavesWarScore", amberCavesWarScore);
        return tag;
    }

    private void syncDataToScoreboard(ServerLevel level) {
        Scoreboard scoreboard = level.getScoreboard();
        Objective warPointsLeaderboard = scoreboard.getObjective("war_points_leaderboard");

        if (warPointsLeaderboard != null) {
            scoreboard.getOrCreatePlayerScore(ScoreHolder.forNameOnly("VillageCraft City"), warPointsLeaderboard).set(this.villagecraftCityWarPoints);
            scoreboard.getOrCreatePlayerScore(ScoreHolder.forNameOnly("Gripper City"), warPointsLeaderboard).set(this.gripperCityWarPoints);
            scoreboard.getOrCreatePlayerScore(ScoreHolder.forNameOnly("The Amber Caves"), warPointsLeaderboard).set(this.amberCavesWarPoints);
        }
    }

    public static WarPointsSavedData getData(MinecraftServer server) {
        return server.overworld().getDataStorage().computeIfAbsent(new Factory<>(WarPointsSavedData::create, WarPointsSavedData::load), "villagecraft3essentials_war_points");
    }
}