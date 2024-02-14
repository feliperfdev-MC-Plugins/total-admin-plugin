package dev.feliperf.plugins.utils.functions

import org.bukkit.entity.Player

fun playerIsLogged(player: Player): Boolean {
    return player.displayName.split(" ").first() != "[UNLOGGED]" ||
            player.displayName == "[PLAYER] ${player.name}"
}