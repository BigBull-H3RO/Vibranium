package de.bigbull.vibranium.main;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config {
    public static final ModConfigSpec COMMON_SPEC;
    public static final CommonConfig COMMON;

    static {
        final Pair<CommonConfig, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(CommonConfig::new);
        COMMON = specPair.getLeft();
        COMMON_SPEC = specPair.getRight();
    }

    public static class CommonConfig{
        public final ModConfigSpec.IntValue VibraniumVeinSize;
        public final ModConfigSpec.IntValue VibraniumMinHeight;
        public final ModConfigSpec.IntValue VibraniumMaxHeight;

        public CommonConfig(ModConfigSpec.Builder builder) {
            builder.push("Ore Generation");
            VibraniumVeinSize = builder.translation(ModInfo.MODID + ".config.range")
                    .comment("How many veins of vibranium ore should spawn per chunk")
                    .defineInRange("Veins per Chunk [Default: 2]", 2, 0, 64);
            VibraniumMinHeight = builder.translation(ModInfo.MODID + ".config.minHeight")
                    .comment("The minimum height vibranium ore should spawn")
                    .defineInRange("MinHeight [Default: -64]", -64, -64, 320);
            VibraniumMaxHeight = builder.translation(ModInfo.MODID + ".config.maxHeight")
                    .comment("The maximum height vibranium ore should spawn")
                    .defineInRange("MaxHeight [Default: -15]", -15, -64, 320);
            builder.pop();
        }
    }
}