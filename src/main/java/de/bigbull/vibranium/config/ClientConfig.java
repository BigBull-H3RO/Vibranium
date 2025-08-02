package de.bigbull.vibranium.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ClientConfig {
    public static final ModConfigSpec.Builder CLIENT_BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec CLIENT_SPEC;

    public static final ModConfigSpec.DoubleValue OUTLINE_RED;
    public static final ModConfigSpec.DoubleValue OUTLINE_GREEN;
    public static final ModConfigSpec.DoubleValue OUTLINE_BLUE;
    public static final ModConfigSpec.DoubleValue OUTLINE_ALPHA;

    static {
        CLIENT_BUILDER.translation("vibranium.config.title.mace").push("Vibranium Mace Configurations");
        OUTLINE_RED = CLIENT_BUILDER.comment("Red component of the outline color [0.0 - 1.0]")
                .translation("vibranium.config.outlineRed")
                .defineInRange("Red", 0.0, 0.0, 1.0);
        OUTLINE_GREEN = CLIENT_BUILDER.comment("Green component of the outline color [0.0 - 1.0]")
                .translation("vibranium.config.outlineGreen")
                .defineInRange("Green", 0.0, 0.0, 1.0);
        OUTLINE_BLUE = CLIENT_BUILDER.comment("Blue component of the outline color [0.0 - 1.0]")
                .translation("vibranium.config.outlineBlue")
                .defineInRange("Blue", 0.0, 0.0, 1.0);
        OUTLINE_ALPHA = CLIENT_BUILDER.comment("Alpha (transparency) component of the outline color [0.0 - 1.0]")
                .translation("vibranium.config.outlineAlpha")
                .defineInRange("Alpha", 0.4, 0.0, 1.0);
        CLIENT_BUILDER.pop();

        CLIENT_SPEC = CLIENT_BUILDER.build();
    }
}
