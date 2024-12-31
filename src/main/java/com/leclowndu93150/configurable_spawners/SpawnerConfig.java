package com.leclowndu93150.configurable_spawners;

import net.neoforged.neoforge.common.ModConfigSpec;

public class SpawnerConfig {
    public static final ModConfigSpec.IntValue maxNearbyEntities;
    public static final ModConfigSpec.IntValue minSpawnDelay;
    public static final ModConfigSpec.IntValue maxSpawnDelay;
    public static final ModConfigSpec.IntValue requiredPlayerRange;
    public static final ModConfigSpec.IntValue spawnCount;
    public static final ModConfigSpec.IntValue spawnRange;

    public static final ModConfigSpec SPEC;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        maxNearbyEntities = builder
                .comment("Maximum number of nearby entities")
                .defineInRange("maxNearbyEntities", 6, 1, 100);

        minSpawnDelay = builder
                .comment("Minimum spawn delay in ticks")
                .defineInRange("minSpawnDelay", 200, 1, 1000);

        maxSpawnDelay = builder
                .comment("Maximum spawn delay in ticks")
                .defineInRange("maxSpawnDelay", 800, 1, 2000);

        requiredPlayerRange = builder
                .comment("Required player range")
                .defineInRange("requiredPlayerRange", 16, 1, 100);

        spawnCount = builder
                .comment("Number of entities to spawn")
                .defineInRange("spawnCount", 4, 1, 50);

        spawnRange = builder
                .comment("Spawn radius")
                .defineInRange("spawnRange", 4, 1, 32);

        SPEC = builder.build();
    }
}
