package cn.ussshenzhou.notenoughbandwidth;

import cn.ussshenzhou.notenoughbandwidth.aggregation.AggregationBypassPolicy;
import cn.ussshenzhou.notenoughbandwidth.config.ConfigHelper;
import cn.ussshenzhou.notenoughbandwidth.config.TConfig;
import net.minecraft.util.math.MathHelper;

import java.util.HashSet;

public class NotEnoughBandwidthConfig implements TConfig {

    public String serverUUID = "";
    public boolean compatibleMode = true;
    public HashSet<String> blackList = new HashSet<>() {{
        add("minecraft:command_suggestion");
        add("minecraft:command_suggestions");
        add("minecraft:commands");
        add("minecraft:player_info_update");
        add("minecraft:player_info_remove");
    }};
    public boolean requireClientMod = true;
    public boolean debugLog = false;
    public int aggregationFlushPeriodMs = 5;
    public int aggregationMaxExtraCycles = 0;
    public int compressionLevel = 6;
    public int contextLevel = 23;
    public int dccSizeLimit = 60;
    public int dccDistance = 5;
    public int dccTimeout = 60;
    public boolean chunkCacheEnabled = true;
    public int chunkCacheMaxSizeMB = 2048;

    public static NotEnoughBandwidthConfig get() {
        return ConfigHelper.getConfigRead(NotEnoughBandwidthConfig.class);
    }

    public static boolean skipType(String type) {
        var cfg = get();
        return AggregationBypassPolicy.shouldBypass(type, cfg.compatibleMode, cfg.blackList);
    }

    public int getAggregationFlushPeriodMs() {
        return MathHelper.clamp(aggregationFlushPeriodMs, 5, 20);
    }

    public int getAggregationMaxExtraCycles() {
        return MathHelper.clamp(aggregationMaxExtraCycles, 0, 2);
    }

    public int getCompressionLevel() {
        return MathHelper.clamp(compressionLevel, 1, 19);
    }

    public int getContextLevel() {
        return MathHelper.clamp(contextLevel, 21, 25);
    }
}
