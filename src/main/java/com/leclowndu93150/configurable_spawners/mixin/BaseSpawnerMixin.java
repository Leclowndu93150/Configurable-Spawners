package com.leclowndu93150.configurable_spawners.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.Shadow;
import com.leclowndu93150.configurable_spawners.SpawnerConfig;

@Mixin(BaseSpawner.class)
public class BaseSpawnerMixin {
    @Shadow private int minSpawnDelay;
    @Shadow private int maxSpawnDelay;
    @Shadow private int spawnCount;
    @Shadow private int maxNearbyEntities;
    @Shadow private int requiredPlayerRange;
    @Shadow private int spawnRange;

    @Inject(method = "load", at = @At("RETURN"))
    private void onLoad(CallbackInfo ci) {
        this.updateConfigValues();
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(CallbackInfo ci) {
        this.updateConfigValues();
    }

    @Redirect(method = "serverTick",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/SpawnPlacements;checkSpawnRules(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/world/entity/MobSpawnType;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;)Z"))
    private boolean forceSpawnRules(EntityType<?> p_217075_, ServerLevelAccessor p_217076_, MobSpawnType p_217077_, BlockPos p_217078_, RandomSource p_217079_) {
        return true;
    }

    @Inject(method = "save", at = @At("HEAD"))
    private void onSave(CompoundTag tag, CallbackInfoReturnable<CompoundTag> cir) {
        tag.putShort("MinSpawnDelay", (short)this.minSpawnDelay);
        tag.putShort("MaxSpawnDelay", (short)this.maxSpawnDelay);
        tag.putShort("SpawnCount", (short)this.spawnCount);
        tag.putShort("MaxNearbyEntities", (short)this.maxNearbyEntities);
        tag.putShort("RequiredPlayerRange", (short)this.requiredPlayerRange);
        tag.putShort("SpawnRange", (short)this.spawnRange);
    }


    @Unique
    private void updateConfigValues() {
        CompoundTag tag = ((BaseSpawner)(Object)this).save(new CompoundTag());

        if (tag.getBoolean("ConfigurableSpawnerModified")) {
            applyConfigSettings();
        } else if (hasDefaultValues()) {
            applyConfigSettings();
            tag.putBoolean("ConfigurableSpawnerModified", true);
        }
    }

    @Unique
    private boolean hasDefaultValues() {
        return this.minSpawnDelay == 200 && this.maxSpawnDelay == 800
                && this.spawnCount == 4 && this.maxNearbyEntities == 6
                && this.requiredPlayerRange == 16 && this.spawnRange == 4;
    }

    @Unique
    private void applyConfigSettings() {
        this.minSpawnDelay = SpawnerConfig.minSpawnDelay.get();
        this.maxSpawnDelay = SpawnerConfig.maxSpawnDelay.get();
        this.spawnCount = SpawnerConfig.spawnCount.get();
        this.maxNearbyEntities = SpawnerConfig.maxNearbyEntities.get();
        this.requiredPlayerRange = SpawnerConfig.requiredPlayerRange.get();
        this.spawnRange = SpawnerConfig.spawnRange.get();
    }
}