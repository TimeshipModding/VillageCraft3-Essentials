package com.timeshipmodding.villagecraft3essentials.content.villager.registries;

import com.google.common.collect.ImmutableSet;
import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.content.block.registries.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(BuiltInRegistries.POINT_OF_INTEREST_TYPE, VillageCraft3Essentials.MODID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(BuiltInRegistries.VILLAGER_PROFESSION, VillageCraft3Essentials.MODID);

    /*private static final Set<BlockState> ATMS = ImmutableList.of(
            ModBlocks.RED_ATM
            )
            .stream()
            .flatMap(ATMS -> ATMS.get().getStateDefinition().getPossibleStates().stream())
            //.filter(ATMS -> ATMS.getValue(DoubleBlock.HALF) == DoubleBlockHalf.UPPER)
            .collect(ImmutableSet.toImmutableSet()); */

    //Set<Block> ATMS = BuiltInRegistries.BLOCK.getOrCreateTag(ModBlockTags.ATMS).stream().toSet();

    /* private static final Set<BlockState> BEDS = ImmutableList.of(
                    Blocks.RED_BED,
                    Blocks.BLACK_BED,
                    Blocks.BLUE_BED,
                    Blocks.BROWN_BED,
                    Blocks.CYAN_BED,
                    Blocks.GRAY_BED,
                    Blocks.GREEN_BED,
                    Blocks.LIGHT_BLUE_BED,
                    Blocks.LIGHT_GRAY_BED,
                    Blocks.LIME_BED,
                    Blocks.MAGENTA_BED,
                    Blocks.ORANGE_BED,
                    Blocks.PINK_BED,
                    Blocks.PURPLE_BED,
                    Blocks.WHITE_BED,
                    Blocks.YELLOW_BED
            )
            .stream()
            .flatMap(p_218097_ -> p_218097_.getStateDefinition().getPossibleStates().stream())
            .filter(p_218095_ -> p_218095_.getValue(BedBlock.PART) == BedPart.HEAD)
            .collect(ImmutableSet.toImmutableSet()); */

    // Poi Types
    public static final Holder<PoiType> ATM_POI = POI_TYPES.register("atm_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.WHITE_ATM.get().getStateDefinition().getPossibleStates()),
                    1, 1));

    // Villager Professions
    public static final Holder<VillagerProfession> BANKER = VILLAGER_PROFESSIONS.register("banker",
            () -> new VillagerProfession("banker", holder -> holder.value() == ATM_POI.value(),
                    holder -> holder.value() == ATM_POI.value(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_LIBRARIAN));
}
