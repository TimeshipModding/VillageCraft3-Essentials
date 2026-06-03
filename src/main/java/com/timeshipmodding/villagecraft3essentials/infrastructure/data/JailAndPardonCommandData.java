package com.timeshipmodding.villagecraft3essentials.infrastructure.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;

public record JailAndPardonCommandData(String jailerUsername, Component townComponent, int jailReleaseTime, int jailCommandCooldown) {
    public static final Codec<JailAndPardonCommandData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.fieldOf("jailer_username").forGetter(JailAndPardonCommandData::jailerUsername),
                    ComponentSerialization.CODEC.fieldOf("town_component").forGetter(JailAndPardonCommandData::townComponent),
                    Codec.INT.fieldOf("jail_release_time").forGetter(JailAndPardonCommandData::jailReleaseTime),
                    Codec.INT.fieldOf("jail_command_cooldown").forGetter(JailAndPardonCommandData::jailCommandCooldown)
            ).apply(instance, JailAndPardonCommandData::new));


    public JailAndPardonCommandData tick() {
        if (jailReleaseTime() > 0) {
            return new JailAndPardonCommandData(jailerUsername, townComponent, jailReleaseTime - 1, jailCommandCooldown);
        }

        if (jailCommandCooldown() > 0) {
            return new JailAndPardonCommandData(jailerUsername, townComponent, jailReleaseTime, jailCommandCooldown - 1);
        }

        return new JailAndPardonCommandData(jailerUsername, townComponent, jailReleaseTime, jailCommandCooldown);
    }

    public JailAndPardonCommandData releaseTimeReset() {
        return new JailAndPardonCommandData(jailerUsername, townComponent,-1, jailCommandCooldown);
    }

    public JailAndPardonCommandData cooldownReset() {
        return new JailAndPardonCommandData(jailerUsername, townComponent, jailReleaseTime, -1);
    }
}