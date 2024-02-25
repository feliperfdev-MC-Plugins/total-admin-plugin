package dev.feliperf.plugins.utils.functions

import dev.feliperf.plugins.datasource.data.models.User
import org.bukkit.entity.Player

fun playerIsBanned(user: User): Boolean {
    return user.permission == "BANNED"
}