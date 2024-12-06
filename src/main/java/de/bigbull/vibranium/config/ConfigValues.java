package de.bigbull.vibranium.config;

import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigValues {
    static Path CLIENT_CONFIG_PATH = FMLPaths.CONFIGDIR.get().resolve("vibranium-client.toml");
    static Path SERVER_CONFIG_PATH = FMLPaths.CONFIGDIR.get().resolve("vibranium-server.toml");
    private static final boolean CLIENT_CONFIG_FILE = Files.exists(CLIENT_CONFIG_PATH);
    private static final boolean SERVER_CONFIG_FILE = Files.exists(SERVER_CONFIG_PATH);

    public static int VEINS_PER_CHUNK = SERVER_CONFIG_FILE ? ServerConfig.VEINS_PER_CHUNK.get() : ServerConfig.VEINS_PER_CHUNK.getDefault();
    public static int MAX_HEIGHT = SERVER_CONFIG_FILE ? ServerConfig.MAX_HEIGHT.get() : ServerConfig.MAX_HEIGHT.getDefault();
    public static int MIN_HEIGHT = SERVER_CONFIG_FILE ? ServerConfig.MIN_HEIGHT.get() : ServerConfig.MIN_HEIGHT.getDefault();

    public static int GEODES_RARITY = SERVER_CONFIG_FILE ? ServerConfig.GEODES_RARITY.get() : ServerConfig.GEODES_RARITY.getDefault();
    public static int GEODES_MAX_HEIGHT = SERVER_CONFIG_FILE ? ServerConfig.GEODES_MAX_HEIGHT.get() : ServerConfig.GEODES_MAX_HEIGHT.getDefault();
    public static int GEODES_MIN_HEIGHT = SERVER_CONFIG_FILE ? ServerConfig.GEODES_MIN_HEIGHT.get() : ServerConfig.GEODES_MIN_HEIGHT.getDefault();

    public static double OUTLINE_RED = CLIENT_CONFIG_FILE ? ClientConfig.OUTLINE_RED.get() : ClientConfig.OUTLINE_RED.getDefault();
    public static double OUTLINE_GREEN = CLIENT_CONFIG_FILE ? ClientConfig.OUTLINE_GREEN.get() : ClientConfig.OUTLINE_GREEN.getDefault();
    public static double OUTLINE_BLUE = CLIENT_CONFIG_FILE ? ClientConfig.OUTLINE_BLUE.get() : ClientConfig.OUTLINE_BLUE.getDefault();
    public static double OUTLINE_ALPHA = CLIENT_CONFIG_FILE ? ClientConfig.OUTLINE_ALPHA.get() : ClientConfig.OUTLINE_ALPHA.getDefault();

    public static boolean USE_FAST_MODE = CLIENT_CONFIG_FILE ? ClientConfig.USE_FAST_MODE.get() : ClientConfig.USE_FAST_MODE.getDefault();
}