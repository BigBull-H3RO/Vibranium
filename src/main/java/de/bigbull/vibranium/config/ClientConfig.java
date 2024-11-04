package de.bigbull.vibranium.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ClientConfig {
    public static final ModConfigSpec.Builder CLIENT_BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec CLIENT_SPEC;

    public static final ModConfigSpec.DoubleValue OUTLINE_RED;
    public static final ModConfigSpec.DoubleValue OUTLINE_GREEN;
    public static final ModConfigSpec.DoubleValue OUTLINE_BLUE;
    public static final ModConfigSpec.DoubleValue OUTLINE_ALPHA;
    public static final ModConfigSpec.BooleanValue USE_FAST_MODE;

    static {
        CLIENT_BUILDER.push("Vibranium Mace Configurations");
        OUTLINE_RED = CLIENT_BUILDER.comment("Red component of the outline color [0.0 - 1.0]")
                .defineInRange("Red", 0.0, 0.0, 1.0);
        OUTLINE_GREEN = CLIENT_BUILDER.comment("Green component of the outline color [0.0 - 1.0]")
                .defineInRange("Green", 0.0, 0.0, 1.0);
        OUTLINE_BLUE = CLIENT_BUILDER.comment("Blue component of the outline color [0.0 - 1.0]")
                .defineInRange("Blue", 0.0, 0.0, 1.0);
        OUTLINE_ALPHA = CLIENT_BUILDER.comment("Alpha (transparency) component of the outline color [0.0 - 1.0]")
                .defineInRange("Alpha", 0.4, 0.0, 1.0);
        USE_FAST_MODE = CLIENT_BUILDER.comment("Use fast block breaking mode [true = fast, false = detailed]")
                .define("FastMode", false);
        CLIENT_BUILDER.pop();

        CLIENT_SPEC = CLIENT_BUILDER.build();
    }
}
