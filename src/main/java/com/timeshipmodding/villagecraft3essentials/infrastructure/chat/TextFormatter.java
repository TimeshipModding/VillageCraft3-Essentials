package com.timeshipmodding.villagecraft3essentials.infrastructure.chat;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class TextFormatter {
    public static final String RESET_ALL_FORMAT = "&r";

    public static final String BOLD_FORMAT          = "&l";
    public static final String UNDERLINE_FORMAT     = "&n";
    public static final String ITALIC_FORMAT        = "&o";
    public static final String OBFUSCATED_FORMAT    = "&k";
    public static final String STRIKETHROUGH_FORMAT = "&m";

    public static final String COLOR_BLACK =        "&0";
    public static final String COLOR_DARK_BLUE =    "&1";
    public static final String COLOR_DARK_GREEN =   "&2";
    public static final String COLOR_DARK_AQUA =    "&3";
    public static final String COLOR_DARK_RED =     "&4";
    public static final String COLOR_DARK_PURPLE =  "&5";
    public static final String COLOR_GOLD =         "&6";
    public static final String COLOR_GRAY =         "&7";
    public static final String COLOR_DARK_GRAY =    "&8";
    public static final String COLOR_BLUE =         "&9";
    public static final String COLOR_GREEN =        "&a";
    public static final String COLOR_AQUA =         "&b";
    public static final String COLOR_RED =          "&c";
    public static final String COLOR_LIGHT_PURPLE = "&d";
    public static final String COLOR_YELLOW =       "&e";
    public static final String COLOR_WHITE =        "&f";

    public static MutableComponent stringToFormattedText(String msg) {
        String DefaultColor = "WHITE";
        ChatFormatting chatFormattingColor = ChatFormatting.getByName(DefaultColor);

        if (chatFormattingColor == null){
            chatFormattingColor = ChatFormatting.WHITE;
        }

        if(msg == null) {
            return null;
        }

        MutableComponent newMsg = Component.empty();
        boolean nextIsStyle = false;
        String chatFormattingString = "";
        byte chatFormattingStyle = 0;

        for(int i = 0; i < ((CharSequence) msg).length(); i++) {
            char c = ((CharSequence) msg).charAt(i);

            if(c == '&') {
                if(nextIsStyle) {
                    nextIsStyle = false;
                    chatFormattingString += "&";

                } else {
                    nextIsStyle = true;
                }

            } else if(nextIsStyle) {
                MutableComponent tmp = BitwiseStyling.makeEncapsulatingTextComponent(chatFormattingString, chatFormattingStyle);
                tmp.withStyle(chatFormattingColor);
                newMsg.append(tmp);
                chatFormattingColor = getColor(c, chatFormattingColor);
                chatFormattingString = "";

                if(c == 'r') {
                    chatFormattingColor = ChatFormatting.getByName(DefaultColor);
                    chatFormattingStyle = 0;

                } else {
                    chatFormattingStyle |= BitwiseStyling.getStyleBit(c);
                }

                nextIsStyle = false;

            } else {
                chatFormattingString += c;
            }
        }

        if(!chatFormattingString.isEmpty()) {
            MutableComponent tmp = BitwiseStyling.makeEncapsulatingTextComponent(chatFormattingString, chatFormattingStyle);
            tmp.withStyle(chatFormattingColor);
            newMsg.append(tmp);
        }

        return newMsg;
    }

    private static ChatFormatting getColor(char c, ChatFormatting chatFormatting) {
        return switch (c) {
            case '0' -> ChatFormatting.BLACK;
            case '1' -> ChatFormatting.DARK_BLUE;
            case '2' -> ChatFormatting.DARK_GREEN;
            case '3' -> ChatFormatting.DARK_AQUA;
            case '4' -> ChatFormatting.DARK_RED;
            case '5' -> ChatFormatting.DARK_PURPLE;
            case '6' -> ChatFormatting.GOLD;
            case '7' -> ChatFormatting.GRAY;
            case '8' -> ChatFormatting.DARK_GRAY;
            case '9' -> ChatFormatting.BLUE;
            case 'a' -> ChatFormatting.GREEN;
            case 'b' -> ChatFormatting.AQUA;
            case 'c' -> ChatFormatting.RED;
            case 'd' -> ChatFormatting.LIGHT_PURPLE;
            case 'e' -> ChatFormatting.YELLOW;
            case 'f' -> ChatFormatting.WHITE;
            case 'r' -> ChatFormatting.WHITE;
            default -> chatFormatting;
        };
    }
}
