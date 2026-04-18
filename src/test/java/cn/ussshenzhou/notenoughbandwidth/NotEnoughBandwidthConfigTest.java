package cn.ussshenzhou.notenoughbandwidth;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NotEnoughBandwidthConfigTest {

    @Test
    void defaultsFavorLowLatencyAndRequireClientMod() {
        var config = new NotEnoughBandwidthConfig();

        assertTrue(config.compatibleMode);
        assertTrue(config.requireClientMod);
        assertEquals(5, config.aggregationFlushPeriodMs);
        assertEquals(0, config.aggregationMaxExtraCycles);
    }
}
