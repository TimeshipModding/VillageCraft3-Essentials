package com.timeshipmodding.villagecraft3essentials.infrastructure.data;

import net.minecraft.network.chat.Component;

import java.util.UUID;

public record TpaCommandData(UUID requestingPlayerUUID, Component requestingPlayerName, Component targetPlayerName, String type, long timeCreatedMilliseconds) { }