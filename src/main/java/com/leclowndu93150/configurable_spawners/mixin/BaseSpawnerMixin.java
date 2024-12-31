package com.leclowndu93150.configurable_spawners.mixin;

import com.leclowndu93150.configurable_spawners.SpawnerConfig;
import net.minecraft.world.level.BaseSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BaseSpawner.class)
public class BaseSpawnerMixin {
    @Shadow private int spawnCount;
    @Shadow private int spawnRange;
    @Shadow private int maxNearbyEntities;
    @Shadow private int requiredPlayerRange;
    @Shadow private int minSpawnDelay;
    @Shadow private int maxSpawnDelay;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(CallbackInfo info) {
        this.spawnCount = SpawnerConfig.spawnCount.get();
        this.spawnRange = SpawnerConfig.spawnRange.get();
        this.maxNearbyEntities = SpawnerConfig.maxNearbyEntities.get();
        this.requiredPlayerRange = SpawnerConfig.requiredPlayerRange.get();
        this.minSpawnDelay = SpawnerConfig.minSpawnDelay.get();
        this.maxSpawnDelay = SpawnerConfig.maxSpawnDelay.get();
    }
}