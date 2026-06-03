package com.timeshipmodding.villagecraft3essentials.infrastructure.data.attachment.registries;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.JailAndPardonCommandData;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.KickCommandData;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModDataAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, VillageCraft3Essentials.MODID);

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