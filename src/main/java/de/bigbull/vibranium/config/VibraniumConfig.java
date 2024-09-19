package de.bigbull.vibranium.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class VibraniumConfig {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.IntValue VEINS_PER_CHUNK;
    public static final ModConfigSpec.IntValue MAX_HEIGHT;
    public static final ModConfigSpec.IntValue MIN_HEIGHT;

    public static final ModConfigSpec.DoubleValue OUTLINE_RED;
    public static final ModConfigSpec.DoubleValue OUTLINE_GREEN;
    public static final ModConfigSpec.DoubleValue OUTLINE_BLUE;
    public static final ModConfigSpec.DoubleValue OUTLINE_ALPHA;

    static {
        BUILDER.push("Vibranium Ore Generation Configurations");
        BUILDER.comment();
        VEINS_PER_CHUNK = BUILDER.comment("How many veins of Vibranium ore should spawn per chunk")
                .defineInRange("Veins per Chunk [Default: 2]", 2, 0, 64);
        MAX_HEIGHT = BUILDER.comment("The maximum height Vibranium ore should spawn")
                .defineInRange("MaxHeight [Default: -15]", -20, -64, 320);
        MIN_HEIGHT = BUILDER.comment("The minimum height Vibranium ore should spawn")
                .defineInRange("MinHeight [Default: -64]", -64, -64, 320);
        BUILDER.pop();

        BUILDER.push("Vibranium Mace Configurations");
        BUILDER.comment();
        OUTLINE_RED = BUILDER.comment("Red component of the outline color [0.0 - 1.0]")
                .defineInRange("Red", 0.0, 0.0, 1.0);
        OUTLINE_GREEN = BUILDER.comment("Green component of the outline color [0.0 - 1.0]")
                .defineInRange("Green", 0.0, 0.0, 1.0);
        OUTLINE_BLUE = BUILDER.comment("Blue component of the outline color [0.0 - 1.0]")
                .defineInRange("Blue", 0.0, 0.0, 1.0);
        OUTLINE_ALPHA = BUILDER.comment("Alpha (transparency) component of the outline color [0.0 - 1.0]")
                .defineInRange("Alpha", 0.4, 0.0, 1.0);
        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}