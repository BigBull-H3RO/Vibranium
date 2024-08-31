package de.bigbull.vibranium.config;

import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Files;
import java.nio.file.Path;

public class VibraniumConfigValues {
    static Path CONFIG_PATH = FMLPaths.CONFIGDIR.get().resolve("vibranium.toml");
    private static final boolean CONFIG_FILE = Files.exists(CONFIG_PATH);

    public static int VEINS_PER_CHUNK = CONFIG_FILE ? VibraniumConfig.VEINS_PER_CHUNK.get() : VibraniumConfig.VEINS_PER_CHUNK.getDefault();
    public static int MAX_HEIGHT = CONFIG_FILE ? VibraniumConfig.MAX_HEIGHT.get() : VibraniumConfig.MAX_HEIGHT.getDefault();
    public static int MIN_HEIGHT = CONFIG_FILE ? VibraniumConfig.MIN_HEIGHT.get() : VibraniumConfig.MIN_HEIGHT.getDefault();
}