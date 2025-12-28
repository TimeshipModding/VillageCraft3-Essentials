package com.timeshipmodding.villagecraft3essentials.infrastructure.data;

import java.util.UUID;

public record TpaCommandData(UUID requestingPlayerUUID, long timeCreatedMilliseconds) { }