package com.timeshipmodding.villagecraft3essentials.infrastructure.util;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;

public class TabListVariables {
    public static String tablistChars(String template, ServerPlayer player) {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server == null || template == null) return "";
        String output = template;
        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("#TPS", String.format("%.1f", getTPS(server)));
        placeholders.put("#MSPT", String.format("%.1f", getMSPT(server)));
        placeholders.put("#PLAYERCOUNT", String.valueOf(getPlayerCount(server)));
        placeholders.put("#UPTIME", getServerUptime());
        placeholders.put("#N", "\n");

        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            output = output.replace(entry.getKey(), entry.getValue());
        }

        return convertColorCodes(output);
    }

    private static double getTPS(MinecraftServer server) {
        double mspt = getMSPT(server);
        return mspt == 0.0 ? 20.0 : Math.round(Math.min(1000.0 / mspt, 20.0) * 10.0) / 10.0;
    }

    private static double getMSPT(MinecraftServer server) {
        return Math.round(server.getAverageTickTimeNanos() / 100000.0) / 10.0;
    }

    private static int getPlayerCount(MinecraftServer server) {
        return server.getPlayerList().getPlayerCount();
    }

    private static String getServerUptime() {
        long uptimeMillis = ManagementFactory.getRuntimeMXBean().getUptime();
        long seconds = (uptimeMillis / 1000) % 60;
        long minutes = (uptimeMillis / (1000 * 60)) % 60;
        long hours = (uptimeMillis / (1000 * 60 * 60)) % 24;
        long days = uptimeMillis / (1000 * 60 * 60 * 24);
        return days > 0
                ? String.format("%d days %02d:%02d:%02d", days, hours, minutes, seconds)
                : String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    private static String convertColorCodes(String text) {
        return text.replace("&", "§");
    }
}