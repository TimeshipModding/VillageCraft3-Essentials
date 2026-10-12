package com.timeshipmodding.villagecraft3essentials.infrastructure.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;

public record KickCommandData(long villagecraftCityKickCooldown, long oasisKickCooldown, long swedishFurnitureStoreKickCooldown, long londonKickCooldown, long blackWaterKickCooldown) {
    public static final Codec<KickCommandData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.LONG.fieldOf("villagecraftcity_kick_cooldown").forGetter(KickCommandData::villagecraftCityKickCooldown),
                    Codec.LONG.fieldOf("oasis_kick_cooldown").forGetter(KickCommandData::oasisKickCooldown),
                    Codec.LONG.fieldOf("swedishfurniturestore_kick_cooldown").forGetter(KickCommandData::swedishFurnitureStoreKickCooldown),
                    Codec.LONG.fieldOf("london_kick_cooldown").forGetter(KickCommandData::londonKickCooldown),
                    Codec.LONG.fieldOf("blackwater_kick_cooldown").forGetter(KickCommandData::blackWaterKickCooldown)
            ).apply(instance, KickCommandData::new));

    public KickCommandData setVillageCraftCityKickCooldown(long currentTime) {
        return new KickCommandData(currentTime, oasisKickCooldown, swedishFurnitureStoreKickCooldown, londonKickCooldown, blackWaterKickCooldown);
    }

    public KickCommandData setOasisKickCooldown(long currentTime) {
        return new KickCommandData(villagecraftCityKickCooldown, currentTime, swedishFurnitureStoreKickCooldown, londonKickCooldown, blackWaterKickCooldown);
    }

    public KickCommandData setSwedishFurnitureStoreKickCooldown(long currentTime) {
        return new KickCommandData(villagecraftCityKickCooldown, oasisKickCooldown, currentTime, londonKickCooldown, blackWaterKickCooldown);
    }

    public KickCommandData setLondonKickCooldown(long currentTime) {
        return new KickCommandData(villagecraftCityKickCooldown, oasisKickCooldown, swedishFurnitureStoreKickCooldown, currentTime, blackWaterKickCooldown);
    }

    public KickCommandData setBlackWaterKickCooldown(long currentTime) {
        return new KickCommandData(villagecraftCityKickCooldown, oasisKickCooldown, swedishFurnitureStoreKickCooldown, londonKickCooldown, currentTime);
    }

    public KickCommandData resetVillageCraftCityKickCooldown() {
        return new KickCommandData(-1, oasisKickCooldown, swedishFurnitureStoreKickCooldown, londonKickCooldown, blackWaterKickCooldown);
    }

    public KickCommandData resetOasisKickCooldown() {
        return new KickCommandData(villagecraftCityKickCooldown, -1, swedishFurnitureStoreKickCooldown, londonKickCooldown, blackWaterKickCooldown);
    }

    public KickCommandData resetSwedishFurnitureStoreCooldown() {
        return new KickCommandData(villagecraftCityKickCooldown, oasisKickCooldown, -1, londonKickCooldown, blackWaterKickCooldown);
    }

    public KickCommandData resetLondonKickCooldown() {
        return new KickCommandData(villagecraftCityKickCooldown, oasisKickCooldown, swedishFurnitureStoreKickCooldown, -1, blackWaterKickCooldown);
    }

    public KickCommandData resetBlackWaterKickCooldown() {
        return new KickCommandData(villagecraftCityKickCooldown, oasisKickCooldown, swedishFurnitureStoreKickCooldown, londonKickCooldown, -1);
    }
}