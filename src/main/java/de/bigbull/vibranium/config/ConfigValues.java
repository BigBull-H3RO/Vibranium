package de.bigbull.vibranium.config;

import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigValues {
    static Path CONFIG_PATH = FMLPaths.CONFIGDIR.get().resolve("vibranium.toml");
    private static final boolean CONFIG_FILE = Files.exists(CONFIG_PATH);

    public static int VEINS_PER_CHUNK = CONFIG_FILE ? ServerConfig.VEINS_PER_CHUNK.get() : ServerConfig.VEINS_PER_CHUNK.getDefault();
    public static int MAX_HEIGHT = CONFIG_FILE ? ServerConfig.MAX_HEIGHT.get() : ServerConfig.MAX_HEIGHT.getDefault();
    public static int MIN_HEIGHT = CONFIG_FILE ? ServerConfig.MIN_HEIGHT.get() : ServerConfig.MIN_HEIGHT.getDefault();

    public static int GEODES_RARITY = CONFIG_FILE ? ServerConfig.GEODES_RARITY.get() : ServerConfig.GEODES_RARITY.getDefault();
    public static int GEODES_MAX_HEIGHT = CONFIG_FILE ? ServerConfig.GEODES_MAX_HEIGHT.get() : ServerConfig.GEODES_MAX_HEIGHT.getDefault();
    public static int GEODES_MIN_HEIGHT = CONFIG_FILE ? ServerConfig.GEODES_MIN_HEIGHT.get() : ServerConfig.GEODES_MIN_HEIGHT.getDefault();

    public static double OUTLINE_RED = CONFIG_FILE ? ClientConfig.OUTLINE_RED.get() : ClientConfig.OUTLINE_RED.getDefault();
    public static double OUTLINE_GREEN = CONFIG_FILE ? ClientConfig.OUTLINE_GREEN.get() : ClientConfig.OUTLINE_GREEN.getDefault();
    public static double OUTLINE_BLUE = CONFIG_FILE ? ClientConfig.OUTLINE_BLUE.get() : ClientConfig.OUTLINE_BLUE.getDefault();
    public static double OUTLINE_ALPHA = CONFIG_FILE ? ClientConfig.OUTLINE_ALPHA.get() : ClientConfig.OUTLINE_ALPHA.getDefault();

    public static boolean USE_FAST_MODE = CONFIG_FILE ? ClientConfig.USE_FAST_MODE.get() : ClientConfig.USE_FAST_MODE.getDefault();
}