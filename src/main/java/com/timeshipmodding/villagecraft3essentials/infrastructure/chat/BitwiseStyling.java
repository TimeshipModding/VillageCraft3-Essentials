package com.timeshipmodding.villagecraft3essentials.infrastructure.chat;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class BitwiseStyling {
    public static final byte BOLD_BIT          = 1;
    public static final byte UNDERLINE_BIT     = 2;
    public static final byte ITALIC_BIT        = 4;
    public static final byte OBFUSCATED_BIT    = 8;
    public static final byte STRIKETHROUGH_BIT = 16;

    public static final byte NO_STYLE = 0;
    public static final byte ALL_STYLES = BOLD_BIT | UNDERLINE_BIT | ITALIC_BIT | OBFUSCATED_BIT | STRIKETHROUGH_BIT;

    public static final String styleString(byte mask) {
        String output = "";

        if((mask & BitwiseStyling.BOLD_BIT) != 0) {
            output += TextFormatter.BOLD_FORMAT;
        }

        if((mask & BitwiseStyling.ITALIC_BIT) != 0) {
            output += TextFormatter.ITALIC_FORMAT;
        }

        if((mask & BitwiseStyling.UNDERLINE_BIT) != 0) {
            output += TextFormatter.UNDERLINE_FORMAT;
        }

        if((mask & BitwiseStyling.STRIKETHROUGH_BIT) != 0) {
            output += TextFormatter.STRIKETHROUGH_FORMAT;
        }

        if((mask & BitwiseStyling.OBFUSCATED_BIT) != 0) {
            output += TextFormatter.OBFUSCATED_FORMAT;
        }

        return output;
    }

    public static final MutableComponent makeEncapsulatingTextComponent(String msg, byte mask) {
        MutableComponent output = Component.literal(msg);

        if((mask & BitwiseStyling.BOLD_BIT) != 0) {
            output.withStyle(ChatFormatting.BOLD);
        }

        if((mask & BitwiseStyling.ITALIC_BIT) != 0) {
            output.withStyle(ChatFormatting.ITALIC);
        }

        if((mask & BitwiseStyling.UNDERLINE_BIT) != 0) {
            output.withStyle(ChatFormatting.UNDERLINE);
        }

        if((mask & BitwiseStyling.STRIKETHROUGH_BIT) != 0) {
            output.withStyle(ChatFormatting.STRIKETHROUGH);
        }

        if((mask & BitwiseStyling.OBFUSCATED_BIT) != 0) {
            output.withStyle(ChatFormatting.OBFUSCATED);
        }

        return output;
    }

    public static final byte getStyleBit(char c) {
        return switch (c) {
            case 'l' -> BOLD_BIT;
            case 'n' -> UNDERLINE_BIT;
            case 'o' -> ITALIC_BIT;
            case 'k' -> OBFUSCATED_BIT;
            case 'm' -> STRIKETHROUGH_BIT;
            default -> 0;
        };
    }
}
