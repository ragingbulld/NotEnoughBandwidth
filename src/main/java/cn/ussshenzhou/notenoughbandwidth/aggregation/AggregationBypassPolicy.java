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

    private static final Set<String> LATENCY_SENSITIVE_PACKET_TYPES = Set.of(
            "minecraft:accept_teleportation",
            "minecraft:container_button_click",
            "minecraft:container_click",
            "minecraft:container_set_content",
            "minecraft:container_set_data",
            "minecraft:container_set_slot",
            "minecraft:container_slot_state_changed",
            "minecraft:interact",
            "minecraft:move_player_pos",
            "minecraft:move_player_pos_rot",
            "minecraft:move_player_rot",
            "minecraft:move_player_status_only",
            "minecraft:pick_item_from_block",
            "minecraft:pick_item_from_entity",
            "minecraft:player_action",
            "minecraft:player_input",
            "minecraft:player_position",
            "minecraft:player_rotation",
            "minecraft:set_carried_item",
            "minecraft:set_creative_mode_slot",
            "minecraft:set_cursor_item",
            "minecraft:set_held_slot",
            "minecraft:set_player_inventory",
            "minecraft:swing",
            "minecraft:use_item",
            "minecraft:use_item_on"
    );

    private AggregationBypassPolicy() {}

    public static boolean shouldBypass(String type,
                                       boolean compatibleMode,
                                       Set<String> compatibilityBlacklist,
                                       boolean prioritizeLatencySensitivePackets) {
        return isControlPacket(type)
                || (prioritizeLatencySensitivePackets && isLatencySensitivePacket(type))
                || (compatibleMode && compatibilityBlacklist.contains(type));
    }

    public static boolean isControlPacket(String type) {
        return CONTROL_PACKET_TYPES.contains(type);
    }

    public static boolean isLatencySensitivePacket(String type) {
        return LATENCY_SENSITIVE_PACKET_TYPES.contains(type);
    }
}
