package cn.ussshenzhou.notenoughbandwidth.aggregation;

import java.util.Set;

public final class AggregationBypassPolicy {
    private static final Set<String> CONTROL_PACKET_TYPES = Set.of(
            "minecraft:finish_configuration",
            "neb:packet_aggregation_packet",
            "neb:dictionary_sync",
            "neb:index_sync",
            "neb:neb_ack",
            "neb:chunk_cache_manifest",
            "neb:chunk_hash",
            "neb:chunk_request",
            "minecraft:login",
            "minecraft:chat_command",
            "minecraft:chat_command_signed",
            "minecraft:chat"
    );

    private AggregationBypassPolicy() {}

    public static boolean shouldBypass(String type,
                                       boolean compatibleMode,
                                       Set<String> compatibilityBlacklist) {
        return isControlPacket(type)
                || (compatibleMode && compatibilityBlacklist.contains(type));
    }

    public static boolean isControlPacket(String type) {
        return CONTROL_PACKET_TYPES.contains(type);
    }
}
