package com.timeshipmodding.villagecraft3essentials.compat.archers.event.registries;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.content.item.registries.ModItems;
import net.archers.ArchersMod;
import net.archers.item.Group;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.spell_engine.rpg_series.item.Equipment;
import net.spell_engine.rpg_series.item.Weapon;
import net.spell_engine.rpg_series.item.Weapons;

import java.util.ArrayList;

@EventBusSubscriber(modid = VillageCraft3Essentials.MODID)
public class ModArcherEvents {

    @SubscribeEvent
    public static void registerItems(RegisterEvent event) {
        if (ModList.get().isLoaded("archers")) {
            ArrayList<Weapon.Entry> meleeEntries = new ArrayList();
            meleeEntries.add(Weapons.spearWithSkill("villagecraft3essentials", "ruby_spear", Equipment.Tier.TIER_2, () -> Ingredient.of(ModItems.RUBY)));
            meleeEntries.add(Weapons.spearWithSkill("villagecraft3essentials", "amber_spear", Equipment.Tier.TIER_2, () -> Ingredient.of(ModItems.AMBER)));
            event.register(Registries.ITEM, (reg) -> Weapon.register((ArchersMod.itemConfig.value).melee_weapons, meleeEntries, Group.KEY));
        }
    }
}