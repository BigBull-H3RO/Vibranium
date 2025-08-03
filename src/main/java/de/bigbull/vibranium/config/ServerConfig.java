package de.bigbull.vibranium.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ServerConfig {
    public static final ModConfigSpec.Builder SERVER_BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SERVER_SPEC;

    public static final ModConfigSpec.BooleanValue USE_FAST_MODE;

    static {
        SERVER_BUILDER.translation("vibranium.config.title.mace").push("Vibranium Mace Configurations");
        USE_FAST_MODE = SERVER_BUILDER.comment("Use fast block breaking mode [true = fast, false = detailed] Better performance, but less detail")
                .translation("vibranium.config.useFastMode")
                .define("fastMode", false);
        SERVER_BUILDER.pop();

        SERVER_SPEC = SERVER_BUILDER.build();
    }
}
