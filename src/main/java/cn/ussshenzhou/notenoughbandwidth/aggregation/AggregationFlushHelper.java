package cn.ussshenzhou.notenoughbandwidth.aggregation;

import cn.ussshenzhou.notenoughbandwidth.NotEnoughBandwidthConfig;

public class AggregationFlushHelper {
    public static int getFlushPeriodInMilliseconds() {
        return clampFlushPeriodInMilliseconds(
                NotEnoughBandwidthConfig.get().getAggregationFlushPeriodMs());
    }

    static int clampFlushPeriodInMilliseconds(int configuredMillis) {
        return Math.clamp(configuredMillis, 5, 20);
    }

    public static int getMaxExtraCycles() {
        return clampMaxExtraCycles(NotEnoughBandwidthConfig.get().getAggregationMaxExtraCycles());
    }

    static int clampMaxExtraCycles(int configuredCycles) {
        return Math.clamp(configuredCycles, 0, 2);
    }

    public static int getFlushCountInSeconds() {
        return Math.max(1000 / getFlushPeriodInMilliseconds(), 1);
    }

    public static int getThresholdCount1s() {
        return 20 * 2;
    }
}
