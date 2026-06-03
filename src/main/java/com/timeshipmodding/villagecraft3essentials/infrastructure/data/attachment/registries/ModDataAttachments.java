package com.timeshipmodding.villagecraft3essentials.infrastructure.data.attachment.registries;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.attachment.AttachmentSyncHandler;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.JailAndPardonCommandData;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.KickCommandData;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModDataAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, VillageCraft3Essentials.MODID);

    public static final StreamCodec<RegistryFriendlyByteBuf, ItemStackHandler> WALLET_STREAM_CODEC =
            ItemStack.OPTIONAL_STREAM_CODEC.apply(ByteBufCodecs.list())
                    .map(list -> {
                        ItemStackHandler handler = new ItemStackHandler(list.size());
                        for (int i = 0; i < list.size(); i++) handler.setStackInSlot(i, list.get(i));
                        return handler;
                    }, handler -> {
                        List<ItemStack> list = new ArrayList<>();
                        for (int i = 0; i < handler.getSlots(); i++) list.add(handler.getStackInSlot(i));
                        return list;
                    });

    public static final Supplier<AttachmentType<ItemStackHandler>> WALLET =
            ATTACHMENT_TYPES.register("wallet", () ->
                    AttachmentType.serializable(() -> new ItemStackHandler(16))
                            .copyOnDeath()
                            .sync(new AttachmentSyncHandler<ItemStackHandler>() {

                                @Override
                                public void write(RegistryFriendlyByteBuf buffer, ItemStackHandler data, boolean isFullUpdate) {
                                    WALLET_STREAM_CODEC.encode(buffer, data);
                                }

                                @Override
                                public ItemStackHandler read(IAttachmentHolder holder, RegistryFriendlyByteBuf buf, ItemStackHandler existing) {
                                    ItemStackHandler incoming = WALLET_STREAM_CODEC.decode(buf);
                                    ItemStackHandler dataToUpdate = (existing != null) ? existing : new ItemStackHandler(16);

                                    for (int i = 0; i < incoming.getSlots(); i++) {
                                        dataToUpdate.setStackInSlot(i, incoming.getStackInSlot(i));
                                    }
                                    return dataToUpdate;
                                }

                                @Override
                                public boolean sendToPlayer(IAttachmentHolder holder, ServerPlayer player) {
                                    return true;
                                }
                            })
                            .build());

    public static final Supplier<AttachmentType<JailAndPardonCommandData>> JAIL_COMMAND_DATA =
            ATTACHMENT_TYPES.register("jail_commmand_data", () ->
                    AttachmentType.builder(() -> new JailAndPardonCommandData("", Component.empty(), -1, -1))
                            .serialize(JailAndPardonCommandData.CODEC)
                            .copyOnDeath()
                            .build());

    public static final Supplier<AttachmentType<KickCommandData>> KICK_COMMAND_DATA =
            ATTACHMENT_TYPES.register("kick_command_data", () ->
                    AttachmentType.builder(() -> new KickCommandData(-1, -1, -1))
                            .serialize(KickCommandData.CODEC)
                            .copyOnDeath()
                            .build());
}