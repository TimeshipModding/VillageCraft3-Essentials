package com.timeshipmodding.villagecraft3essentials.content.command.villagecraftcity;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.timeshipmodding.villagecraft3essentials.infrastructure.tags.registries.ModItemTags;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.commands.arguments.item.ItemInput;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;

public class VillageCraftCityGetRoleplayItemCommand {
    public VillageCraftCityGetRoleplayItemCommand(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext context) {
        dispatcher.register(Commands.literal("villagecraftcity").then(Commands.literal("getroleplayitem")
                .then(
                        Commands.argument("item", ItemArgument.item(context))
                                .suggests((cmdContext, builder) -> {
                                    var tagOptional = BuiltInRegistries.ITEM.getTag(ModItemTags.VILLAGECRAFT_CITY_ROLEPLAY_ITEMS);

                                    return tagOptional.map(holders -> SharedSuggestionProvider.suggestResource(
                                            holders.stream()
                                                    .flatMap(holder -> holder.unwrapKey().stream())
                                                    .map(ResourceKey::location),
                                            builder
                                    )).orElseGet(builder::buildFuture);
                                })
                        .executes((p_137784_) -> execute(p_137784_, ItemArgument.getItem(p_137784_, "item"), 1))
                                .then(Commands.argument("count", IntegerArgumentType.integer(1))
                                        .executes((p_137784_) -> execute(p_137784_, ItemArgument.getItem(p_137784_, "item"), IntegerArgumentType.getInteger(p_137784_, "count")))
                ))));
    }

    private static int execute(CommandContext<CommandSourceStack> context, ItemInput item, int count) throws CommandSyntaxException {
        ItemStack itemstack = item.createItemStack(1, false);
        ServerPlayer player = context.getSource().getPlayer();
        int i = itemstack.getMaxStackSize();
        int j = i * 100;

        if (count > j) {
            context.getSource().sendFailure(Component.translatable("commands.give.failed.toomanyitems", new Object[]{j, itemstack.getDisplayName()}));
            return 0;

        } else {
            int k = count;

            while(k > 0) {
                int l = Math.min(i, k);
                k -= l;
                ItemStack itemstack1 = item.createItemStack(l, false);
                boolean flag = player.getInventory().add(itemstack1);

                if (flag && itemstack1.isEmpty()) {
                    ItemEntity itementity1 = player.drop(itemstack, false);

                    if (itementity1 != null) {
                        itementity1.makeFakeItem();
                    }

                    player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 0.2F, ((player.getRandom().nextFloat() - player.getRandom().nextFloat()) * 0.7F + 1.0F) * 2.0F);
                    player.containerMenu.broadcastChanges();

                } else {
                    ItemEntity itementity = player.drop(itemstack1, false);

                    if (itementity != null) {
                        itementity.setNoPickUpDelay();
                        itementity.setTarget(player.getUUID());
                    }
                }
            }

            context.getSource().sendSuccess(() -> Component.translatable("commands.give.success.single", count, itemstack.getDisplayName(), player.getDisplayName()), true);
            return 1;
        }
    }
}