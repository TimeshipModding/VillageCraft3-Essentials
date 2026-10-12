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
    private final Item targetCurrency;
    private final Item replacementCurrency1;
    private final Item replacementCurrency2;
    private final Item replacementCurrency3;
    private final Item replacementCurrency4;

    public static final MapCodec<SwapItemLootModifier> CODEC = RecordCodecBuilder.mapCodec(inst ->
            LootModifier.codecStart(inst).and(inst.group(
                            BuiltInRegistries.ITEM.byNameCodec().fieldOf("targetCurrency").forGetter(m -> m.targetCurrency),
                            BuiltInRegistries.ITEM.byNameCodec().fieldOf("replacementCurrency1").forGetter(m -> m.replacementCurrency1),
                            BuiltInRegistries.ITEM.byNameCodec().fieldOf("replacementCurrency2").forGetter(m -> m.replacementCurrency2),
                            BuiltInRegistries.ITEM.byNameCodec().fieldOf("replacementCurrency3").forGetter(m -> m.replacementCurrency3),
                            BuiltInRegistries.ITEM.byNameCodec().fieldOf("replacementCurrency4").forGetter(m -> m.replacementCurrency4)))
                            .apply(inst, SwapItemLootModifier::new));

    public SwapItemLootModifier(LootItemCondition[] conditionsIn, Item targetCurrency, Item replacementCurrency1, Item replacementCurrency2, Item replacementCurrency3, Item replacementCurrency4) {
        super(conditionsIn);
        this.targetCurrency = targetCurrency;
        this.replacementCurrency1 = replacementCurrency1;
        this.replacementCurrency2 = replacementCurrency2;
        this.replacementCurrency3 = replacementCurrency3;
        this.replacementCurrency4 = replacementCurrency4;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        for (int i = 0; i < generatedLoot.size(); i++) {
            ItemStack stack = generatedLoot.get(i);

            if (stack.is(targetCurrency)) {
                float chance = context.getRandom().nextFloat();
                ItemStack replacement = getCurrencyItemStack(chance, stack);
                replacement.applyComponents(stack.getComponents());
                generatedLoot.set(i, replacement);
            }
        }

        return generatedLoot;
    }

    private @NotNull ItemStack getCurrencyItemStack(float chance, ItemStack stack) {
        ItemStack replacement;

        if (chance <= 0.2f) {
            replacement = new ItemStack(replacementCurrency1, stack.getCount());

        } else if (chance > 0.2f && chance <= 0.4f) {
            replacement = new ItemStack(replacementCurrency2, stack.getCount());

        } else if (chance > 0.4f && chance <= 0.6f) {
            replacement = new ItemStack(replacementCurrency3, stack.getCount());

        } else if (chance > 0.6f && chance <= 0.8f) {
            replacement = new ItemStack(replacementCurrency4, stack.getCount());

        } else {
            replacement = new ItemStack(targetCurrency, stack.getCount());
        }

        return replacement;
    }
}