package com.leclowndu93150.configurable_spawners;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(ConfigurableSpawners.MODID)
public class ConfigurableSpawners {

    public static final String MODID = "configurable_spawners";

    public ConfigurableSpawners() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SpawnerConfig.SPEC);
    }

}
