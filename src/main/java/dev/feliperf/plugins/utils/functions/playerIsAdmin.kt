package dev.feliperf.plugins.utils.functions

import dev.feliperf.plugins.utils.models.UserPermission
import org.bukkit.entity.Player

fun playerIsAdmin(player: Player): Boolean {
    return player.displayName.split(" ").first() == "[${UserPermission.admin}]" ||
            player.displayName == "[${UserPermission.admin}] ${player.name}"
}