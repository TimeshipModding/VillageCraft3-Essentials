package com.timeshipmodding.villagecraft3essentials.infrastructure.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;

public record KickCommandData(long villagecraftCityKickCommandCooldown, long gripperCityKickCommandCooldown, long amberCavesKickCommandCooldown) {
    public static final Codec<KickCommandData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.LONG.fieldOf("villagecraftcity_kick_command_cooldown").forGetter(KickCommandData::villagecraftCityKickCommandCooldown),
                    Codec.LONG.fieldOf("grippercity_kick_command_cooldown").forGetter(KickCommandData::gripperCityKickCommandCooldown),
                    Codec.LONG.fieldOf("ambercaves_kick_command_cooldown").forGetter(KickCommandData::amberCavesKickCommandCooldown)
            ).apply(instance, KickCommandData::new));

    public KickCommandData villagecraftCitySetData(long currentTime) {
        return new KickCommandData(currentTime, gripperCityKickCommandCooldown, amberCavesKickCommandCooldown);
    }

    public KickCommandData gripperCitySetData(long currentTime) {
        return new KickCommandData(villagecraftCityKickCommandCooldown, currentTime, amberCavesKickCommandCooldown);
    }

    public KickCommandData amberCavesSetData(long currentTime) {
        return new KickCommandData(villagecraftCityKickCommandCooldown, gripperCityKickCommandCooldown, currentTime);
    }

    public KickCommandData villagecraftCityReset() {
        return new KickCommandData(-1, gripperCityKickCommandCooldown, amberCavesKickCommandCooldown);
    }

    public KickCommandData gripperCityReset() {
        return new KickCommandData(villagecraftCityKickCommandCooldown, -1, amberCavesKickCommandCooldown);
    }

    public KickCommandData amberCavesReset() {
        return new KickCommandData(villagecraftCityKickCommandCooldown, gripperCityKickCommandCooldown, -1);
    }
}