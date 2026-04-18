package cn.ussshenzhou.notenoughbandwidth.aggregation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AggregationFlushHelperTest {

    @Test
    void clampsFlushPeriodToSafeRange() {
        assertEquals(5, AggregationFlushHelper.clampFlushPeriodInMilliseconds(1));
        assertEquals(10, AggregationFlushHelper.clampFlushPeriodInMilliseconds(10));
        assertEquals(20, AggregationFlushHelper.clampFlushPeriodInMilliseconds(50));
    }

    @Test
    void clampsExtraCyclesToSafeRange() {
        assertEquals(0, AggregationFlushHelper.clampMaxExtraCycles(-1));
        assertEquals(1, AggregationFlushHelper.clampMaxExtraCycles(1));
        assertEquals(2, AggregationFlushHelper.clampMaxExtraCycles(5));
    }
}
