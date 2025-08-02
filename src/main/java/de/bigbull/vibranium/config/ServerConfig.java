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

    public static final ModConfigSpec.BooleanValue USE_FAST_MODE;

    static {
        SERVER_BUILDER.translation("vibranium.config.title.oreGen").push("Vibranium Ore Generation Configurations");
        VEINS_PER_CHUNK = SERVER_BUILDER.comment("How many veins of Vibranium ore should spawn per chunk")
                .translation("vibranium.config.veinsPerChunk")
                .defineInRange("Veins per Chunk [Default: 3]", 3, 0, 64);
        MAX_HEIGHT = SERVER_BUILDER.comment("The maximum height Vibranium ore should spawn")
                .translation("vibranium.config.maxHeight")
                .defineInRange("MaxHeight [Default: -10]", -10, -64, 320);
        MIN_HEIGHT = SERVER_BUILDER.comment("The minimum height Vibranium ore should spawn")
                .translation("vibranium.config.minHeight")
                .defineInRange("MinHeight [Default: -64]", -64, -64, 320);
        SERVER_BUILDER.pop();

        SERVER_BUILDER.translation("vibranium.config.title.geodeGen").push("Vibranium Geode Generation Configurations");
        GEODES_RARITY = SERVER_BUILDER.comment("How often should a Vibranium geode spawn")
                .translation("vibranium.config.geodesRarity")
                .defineInRange("Rarity [Default: 140]", 120, 1, 10000);
        GEODES_MAX_HEIGHT = SERVER_BUILDER.comment("The maximum height Vibranium geodes should spawn")
                .translation("vibranium.config.geodesMaxHeight")
                .defineInRange("MaxHeight [Default: 15]", 15, -64, 320);
        GEODES_MIN_HEIGHT = SERVER_BUILDER.comment("The minimum height Vibranium geodes should spawn")
                .translation("vibranium.config.geodesMinHeight")
                .defineInRange("MinHeight [Default: -45]", -45, -64, 320);
        SERVER_BUILDER.pop();

        SERVER_BUILDER.translation("vibranium.config.title.mace").push("Vibranium Mace Configurations");
        USE_FAST_MODE = SERVER_BUILDER.comment("Use fast block breaking mode [true = fast, false = detailed] Better performance, but less detail")
                .translation("vibranium.config.useFastMode")
                .define("FastMode", false);
        SERVER_BUILDER.pop();

        SERVER_SPEC = SERVER_BUILDER.build();
    }
}
