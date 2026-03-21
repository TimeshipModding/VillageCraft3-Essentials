package com.timeshipmodding.villagecraft3essentials.content.loot.modifier;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

public class SwapItemLootModifier extends LootModifier {
    private final Item target;
    private final Item currency1;
    private final Item currency2;

    public static final MapCodec<SwapItemLootModifier> CODEC = RecordCodecBuilder.mapCodec(inst ->
            LootModifier.codecStart(inst).and(inst.group(
                            BuiltInRegistries.ITEM.byNameCodec().fieldOf("target").forGetter(m -> m.target),
                            BuiltInRegistries.ITEM.byNameCodec().fieldOf("currency1").forGetter(m -> m.currency1),
                            BuiltInRegistries.ITEM.byNameCodec().fieldOf("currency2").forGetter(m -> m.currency2)))
                            .apply(inst, SwapItemLootModifier::new));

    public SwapItemLootModifier(LootItemCondition[] conditionsIn, Item target, Item currency1, Item currency2) {
        super(conditionsIn);
        this.target = target;
        this.currency1 = currency1;
        this.currency2 = currency2;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        for (int i = 0; i < generatedLoot.size(); i++) {
            ItemStack stack = generatedLoot.get(i);

            if (stack.is(target)) {
                float chance = context.getRandom().nextFloat();
                ItemStack replacement;

                if (chance <= 0.3f) {
                    replacement = new ItemStack(currency1, stack.getCount());

                } else if (chance > 0.3f && chance <= 0.6) {
                    replacement = new ItemStack(currency2, stack.getCount());

                } else {
                    replacement = new ItemStack(target, stack.getCount());
                }

                replacement.applyComponents(stack.getComponents());
                generatedLoot.set(i, replacement);
            }
        }

        return generatedLoot;
    }
}