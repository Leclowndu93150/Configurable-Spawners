package com.leclowndu93150.configurable_spawners;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(SpawnerTweaks.MODID)
public class SpawnerTweaks {

    public static final String MODID = "spawner_tweaks";

    public SpawnerTweaks(ModContainer container) {
        container.registerConfig(ModConfig.Type.COMMON, SpawnerConfig.SPEC);
    }

}
