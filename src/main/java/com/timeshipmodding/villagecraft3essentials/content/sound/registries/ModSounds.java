package com.timeshipmodding.villagecraft3essentials.content.sound.registries;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, VillageCraft3Essentials.MODID);

    public static final Supplier<SoundEvent> MOLE_DEATH = registerSoundEvent("mole_death");
    public static final Supplier<SoundEvent> MOLE_HURT = registerSoundEvent("mole_hurt");
    public static final Supplier<SoundEvent> MOLE_AMBIENT = registerSoundEvent("mole_ambient");
    public static final Supplier<SoundEvent> ATM_USE = registerSoundEvent("atm_use");

    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }
}