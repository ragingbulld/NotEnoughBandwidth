package cn.ussshenzhou.notenoughbandwidth.aggregation;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AggregationBypassPolicyTest {

    @Test
    void controlPacketsAlwaysBypassAggregation() {
        assertTrue(AggregationBypassPolicy.shouldBypass(
                "minecraft:chat", false, Set.of()));
    }

    @Test
    void latencySensitivePacketsNoLongerBypassByDefault() {
        assertFalse(AggregationBypassPolicy.shouldBypass(
                "minecraft:move_player_pos", false, Set.of()));
    }

    @Test
    void compatibilityBlacklistStillWorks() {
        assertFalse(AggregationBypassPolicy.shouldBypass(
                "example:custom_packet", false, Set.of("example:custom_packet")));
        assertTrue(AggregationBypassPolicy.shouldBypass(
                "example:custom_packet", true, Set.of("example:custom_packet")));
    }

    @Test
    void regularPacketsStillAggregate() {
        assertFalse(AggregationBypassPolicy.shouldBypass(
                "minecraft:level_chunk_with_light", false, Set.of()));
    }
}
