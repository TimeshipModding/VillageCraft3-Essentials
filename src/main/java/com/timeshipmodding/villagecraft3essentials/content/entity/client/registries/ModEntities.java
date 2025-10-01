package com.timeshipmodding.villagecraft3essentials.content.entity.client.registries;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.content.entity.MoleEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, VillageCraft3Essentials.MODID);

    public static final Supplier<EntityType<MoleEntity>> MOLE = ENTITY_TYPES.register("mole", () -> EntityType.Builder.of(MoleEntity::new, MobCategory.CREATURE).sized(0.75f, 0.95f).build("mole"));
}
