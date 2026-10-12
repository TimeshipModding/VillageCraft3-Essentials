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

public record AtmRandomConversionRatesPacket(int[] diamondToJade, int[] diamondToAmber, int[] diamondToSilicon, int[] diamondToRuby, int[] jadeToDiamond, int[] jadeToAmber, int[] jadeToSilicon, int[] jadeToRuby, int[] amberToDiamond, int[] amberToJade, int[] amberToSilicon, int[] amberToRuby, int[] siliconToDiamond, int[] siliconToJade, int[] siliconToAmber, int[] siliconToRuby, int[] rubyToDiamond, int[] rubyToJade, int[] rubyToAmber, int[] rubyToSilicon) implements CustomPacketPayload {
    public static final Type<AtmRandomConversionRatesPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "atm_random_conversion_rates"));
    public static final StreamCodec<RegistryFriendlyByteBuf, AtmRandomConversionRatesPacket> STREAM_CODEC = StreamCodec.of(
                    AtmRandomConversionRatesPacket::encode,
                    AtmRandomConversionRatesPacket::decode
    );

    private static void encode(RegistryFriendlyByteBuf buf, AtmRandomConversionRatesPacket payload) {
        buf.writeVarIntArray(payload.diamondToJade());
        buf.writeVarIntArray(payload.diamondToAmber());
        buf.writeVarIntArray(payload.diamondToSilicon());
        buf.writeVarIntArray(payload.diamondToRuby());
        buf.writeVarIntArray(payload.jadeToDiamond());
        buf.writeVarIntArray(payload.jadeToAmber());
        buf.writeVarIntArray(payload.jadeToSilicon());
        buf.writeVarIntArray(payload.jadeToRuby());
        buf.writeVarIntArray(payload.amberToDiamond());
        buf.writeVarIntArray(payload.amberToJade());
        buf.writeVarIntArray(payload.amberToSilicon());
        buf.writeVarIntArray(payload.amberToRuby());
        buf.writeVarIntArray(payload.siliconToDiamond());
        buf.writeVarIntArray(payload.siliconToJade());
        buf.writeVarIntArray(payload.siliconToAmber());
        buf.writeVarIntArray(payload.siliconToRuby());
        buf.writeVarIntArray(payload.rubyToDiamond());
        buf.writeVarIntArray(payload.rubyToJade());
        buf.writeVarIntArray(payload.rubyToAmber());
        buf.writeVarIntArray(payload.rubyToSilicon());
    }

    private static AtmRandomConversionRatesPacket decode(RegistryFriendlyByteBuf buf) {
        int[] diamondToJade = buf.readVarIntArray();
        int[] diamondToAmber = buf.readVarIntArray();
        int[] diamondToSilicon = buf.readVarIntArray();
        int[] diamondToRuby = buf.readVarIntArray();
        int[] jadeToDiamond = buf.readVarIntArray();
        int[] jadeToAmber = buf.readVarIntArray();
        int[] jadeToSilicon = buf.readVarIntArray();
        int[] jadeToRuby = buf.readVarIntArray();
        int[] amberToDiamond = buf.readVarIntArray();
        int[] amberToJade = buf.readVarIntArray();
        int[] amberToSilicon = buf.readVarIntArray();
        int[] amberToRuby = buf.readVarIntArray();
        int[] siliconToDiamond = buf.readVarIntArray();
        int[] siliconToJade = buf.readVarIntArray();
        int[] siliconToAmber = buf.readVarIntArray();
        int[] siliconToRuby = buf.readVarIntArray();
        int[] rubyToDiamond = buf.readVarIntArray();
        int[] rubyToJade = buf.readVarIntArray();
        int[] rubyToAmber = buf.readVarIntArray();
        int[] rubyToSilicon = buf.readVarIntArray();
        return new AtmRandomConversionRatesPacket(diamondToJade, diamondToAmber, diamondToSilicon, diamondToRuby, jadeToDiamond, jadeToAmber, jadeToSilicon, jadeToRuby, amberToDiamond, amberToJade, amberToSilicon, amberToRuby, siliconToDiamond, siliconToJade, siliconToAmber, siliconToRuby, rubyToDiamond, rubyToJade, rubyToAmber, rubyToSilicon);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(AtmRandomConversionRatesPacket payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            AtmClientData.setRandomConversionRates(payload.diamondToJade(), payload.diamondToAmber(), payload.diamondToSilicon(), payload.diamondToRuby(), payload.jadeToDiamond(), payload.jadeToAmber(), payload.jadeToSilicon(), payload.jadeToRuby(), payload.amberToDiamond(), payload.amberToJade(), payload.amberToSilicon(), payload.amberToRuby(), payload.siliconToDiamond(), payload.siliconToJade(), payload.siliconToAmber(), payload.siliconToRuby(), payload.rubyToDiamond(), payload.rubyToJade(), payload.rubyToAmber(), payload.rubyToSilicon());
            Minecraft minecraft = Minecraft.getInstance();
            Screen currentScreen = minecraft.screen;

            if (currentScreen instanceof AtmScreen) {
                ((AtmScreen) currentScreen).setConversionRates(payload.diamondToJade(), payload.diamondToAmber(), payload.diamondToSilicon(), payload.diamondToRuby(), payload.jadeToDiamond(), payload.jadeToAmber(), payload.jadeToSilicon(), payload.jadeToRuby(), payload.amberToDiamond(), payload.amberToJade(), payload.amberToSilicon(), payload.amberToRuby(), payload.siliconToDiamond(), payload.siliconToJade(), payload.siliconToAmber(), payload.siliconToRuby(), payload.rubyToDiamond(), payload.rubyToJade(), payload.rubyToAmber(), payload.rubyToSilicon());
            }
        });
    }
}