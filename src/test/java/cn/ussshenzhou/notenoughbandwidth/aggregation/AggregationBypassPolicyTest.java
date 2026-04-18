package cn.ussshenzhou.notenoughbandwidth.aggregation;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AggregationBypassPolicyTest {

    @Test
    void controlPacketsAlwaysBypassAggregation() {
        assertTrue(AggregationBypassPolicy.shouldBypass(
                "minecraft:chat", false, Set.of(), false));
    }

    @Test
    void latencySensitivePacketsBypassByDefault() {
        assertTrue(AggregationBypassPolicy.shouldBypass(
                "minecraft:move_player_pos", false, Set.of(), true));
        assertTrue(AggregationBypassPolicy.shouldBypass(
                "minecraft:interact", false, Set.of(), true));
        assertTrue(AggregationBypassPolicy.shouldBypass(
                "minecraft:container_click", false, Set.of(), true));
    }

    @Test
    void latencySensitiveBypassCanBeDisabled() {
        assertFalse(AggregationBypassPolicy.shouldBypass(
                "minecraft:move_player_pos", false, Set.of(), false));
    }

    @Test
    void compatibilityBlacklistStillWorks() {
        assertFalse(AggregationBypassPolicy.shouldBypass(
                "example:custom_packet", false, Set.of("example:custom_packet"), true));
        assertTrue(AggregationBypassPolicy.shouldBypass(
                "example:custom_packet", true, Set.of("example:custom_packet"), true));
    }

    @Test
    void nonSensitivePacketsStillAggregate() {
        assertFalse(AggregationBypassPolicy.shouldBypass(
                "minecraft:level_chunk_with_light", false, Set.of(), true));
    }
}
