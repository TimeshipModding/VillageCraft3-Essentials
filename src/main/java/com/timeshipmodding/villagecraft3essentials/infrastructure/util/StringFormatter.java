package com.timeshipmodding.villagecraft3essentials.infrastructure.util;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.ArrayList;
import java.util.Objects;
import java.util.SortedMap;

public class StringFormatter {
    public static ArrayList<Component> formatString(SortedMap<Integer, String> unformattedStrings) {
        ArrayList<Component> formattedStrings = new ArrayList<>();

        if (unformattedStrings == null || unformattedStrings.isEmpty()) {
            return formattedStrings;
        }

        unformattedStrings.forEach((key, unformattedString) -> {
            MutableComponent formattedString = Component.empty();
            StringBuilder currentSegment = new StringBuilder();
            ChatFormatting currentStyle = ChatFormatting.WHITE;

            for (int i = 0; i < unformattedString.length(); i++) {
                if ((unformattedString.charAt(i) == '&' || unformattedString.charAt(i) == '§') && i + 1 < unformattedString.length()) {
                    if (currentSegment.length() > 0) {
                        formattedString.append(Component.literal(currentSegment.toString()).withStyle(currentStyle));
                        currentSegment.setLength(0);
                    }

                    char code = unformattedString.charAt(i + 1);
                    ChatFormatting foundStyle = ChatFormatting.getByCode(code);

                    if (foundStyle != null) {
                        currentStyle = (foundStyle == ChatFormatting.RESET) ? ChatFormatting.WHITE : foundStyle;
                        i++;

                    } else {
                        currentSegment.append(unformattedString.charAt(i));
                    }

                } else {
                    currentSegment.append(unformattedString.charAt(i));
                }
            }

            if (currentSegment.length() > 0) {
                formattedString.append(Component.literal(currentSegment.toString()).withStyle(currentStyle));
            }

            formattedStrings.add(formattedString);
        });

        return formattedStrings;
    }

    public static Component getFinalDisplayName(ArrayList<Component> prefixes, Component name, ArrayList<Component> suffixes) {
        MutableComponent finalDisplayName = Component.empty();

        for (Component prefix : prefixes) {
            if (!Objects.equals(prefix, Component.empty())) {
                finalDisplayName.append(prefix);
                finalDisplayName.append(" | ").withStyle(ChatFormatting.GRAY);
            }
        }

        finalDisplayName.append(name);

        for (Component suffix : suffixes) {
            if (!Objects.equals(suffix, Component.empty())) {
                finalDisplayName.append(" | ").withStyle(ChatFormatting.GRAY);
                finalDisplayName.append(suffix);
            }
        }

        return finalDisplayName;
    }
}