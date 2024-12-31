package com.leclowndu93150.configurable_spawners;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(SpawnerTweaks.MODID)
public class SpawnerTweaks {

    public static final String MODID = "spawner_tweaks";

    public SpawnerTweaks() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SpawnerConfig.SPEC);
    }

}
