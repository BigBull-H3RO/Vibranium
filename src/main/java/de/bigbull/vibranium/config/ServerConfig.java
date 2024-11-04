package de.bigbull.vibranium.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ServerConfig {
    public static final ModConfigSpec.Builder SERVER_BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SERVER_SPEC;

    public static final ModConfigSpec.IntValue VEINS_PER_CHUNK;
    public static final ModConfigSpec.IntValue MAX_HEIGHT;
    public static final ModConfigSpec.IntValue MIN_HEIGHT;

    public static final ModConfigSpec.IntValue GEODES_RARITY;
    public static final ModConfigSpec.IntValue GEODES_MAX_HEIGHT;
    public static final ModConfigSpec.IntValue GEODES_MIN_HEIGHT;

    static {
        SERVER_BUILDER.push("Vibranium Ore Generation Configurations");
        VEINS_PER_CHUNK = SERVER_BUILDER.comment("How many veins of Vibranium ore should spawn per chunk")
                .defineInRange("Veins per Chunk [Default: 3]", 3, 0, 64);
        MAX_HEIGHT = SERVER_BUILDER.comment("The maximum height Vibranium ore should spawn")
                .defineInRange("MaxHeight [Default: -10]", -10, -64, 320);
        MIN_HEIGHT = SERVER_BUILDER.comment("The minimum height Vibranium ore should spawn")
                .defineInRange("MinHeight [Default: -64]", -64, -64, 320);
        SERVER_BUILDER.pop();

        SERVER_BUILDER.push("Vibranium Geode Generation Configurations");
        GEODES_RARITY = SERVER_BUILDER.comment("How often should a Vibranium geode spawn")
                .defineInRange("Rarity [Default: 140]", 140, 1, 10000);
        GEODES_MAX_HEIGHT = SERVER_BUILDER.comment("The maximum height Vibranium geodes should spawn")
                .defineInRange("MaxHeight [Default: 15]", 15, -64, 320);
        GEODES_MIN_HEIGHT = SERVER_BUILDER.comment("The minimum height Vibranium geodes should spawn")
                .defineInRange("MinHeight [Default: -45]", -45, -64, 320);
        SERVER_BUILDER.pop();

        SERVER_SPEC = SERVER_BUILDER.build();
    }
}
