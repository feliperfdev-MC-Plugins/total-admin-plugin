package dev.feliperf.plugins.utils.functions

import org.bukkit.entity.Player

fun playerIsAdmin(player: Player): Boolean {
    return player.displayName.split(" ").first() == "[ADMIN]" ||
            player.displayName == "[ADMIN] ${player.name}"
}