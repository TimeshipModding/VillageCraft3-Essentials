package com.timeshipmodding.villagecraft3essentials.infrastructure.networking.packet.atm;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.content.screen.AtmScreen;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.clientdata.AtmClientData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record AtmRandomConversionRatesPacket(int[] diamondToRuby, int[] diamondToAmber, int[] rubyToDiamond, int[] rubyToAmber, int[] amberToDiamond, int[] amberToRuby) implements CustomPacketPayload {
    public static final Type<AtmRandomConversionRatesPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "atm_random_conversion_rates"));
    public static final StreamCodec<RegistryFriendlyByteBuf, AtmRandomConversionRatesPacket> STREAM_CODEC = StreamCodec.of(
                    AtmRandomConversionRatesPacket::encode,
                    AtmRandomConversionRatesPacket::decode);

    private static void encode(RegistryFriendlyByteBuf buf, AtmRandomConversionRatesPacket payload) {
        buf.writeVarIntArray(payload.diamondToRuby());
        buf.writeVarIntArray(payload.diamondToAmber());
        buf.writeVarIntArray(payload.rubyToDiamond());
        buf.writeVarIntArray(payload.rubyToAmber());
        buf.writeVarIntArray(payload.amberToDiamond());
        buf.writeVarIntArray(payload.amberToRuby());
    }

    private static AtmRandomConversionRatesPacket decode(RegistryFriendlyByteBuf buf) {
        int[] diamondToRuby = buf.readVarIntArray();
        int[] diamondToAmber = buf.readVarIntArray();
        int[] rubyToDiamond = buf.readVarIntArray();
        int[] rubyToAmber = buf.readVarIntArray();
        int[] amberToDiamond = buf.readVarIntArray();
        int[] amberToRuby = buf.readVarIntArray();
        return new AtmRandomConversionRatesPacket(diamondToRuby, diamondToAmber, rubyToDiamond, rubyToAmber, amberToDiamond, amberToRuby);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(AtmRandomConversionRatesPacket payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            AtmClientData.setRandomConversionRates(payload.diamondToRuby(), payload.diamondToAmber(), payload.rubyToDiamond(), payload.rubyToAmber(), payload.amberToDiamond(), payload.amberToRuby());
            System.out.println("Client received and stored data array.");

            Minecraft minecraft = Minecraft.getInstance();
            Screen currentScreen = minecraft.screen;

            if (currentScreen instanceof AtmScreen) {
                ((AtmScreen) currentScreen).setConversionRates(payload.diamondToRuby(), payload.diamondToAmber(), payload.rubyToDiamond(), payload.rubyToAmber(), payload.amberToDiamond(), payload.amberToRuby());
            }
        });
    }
}