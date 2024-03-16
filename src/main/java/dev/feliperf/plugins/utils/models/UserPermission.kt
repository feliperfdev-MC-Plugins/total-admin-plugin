package dev.feliperf.plugins.utils.models

class UserPermission {

    companion object {
        val admin: String = "ADMIN"
        val banned: String = "BANNED"
        val player: String = "PLAYER"
        val vip: String = "VIP"
        val dev: String = "DEV"
        val mod: String = "MOD"
        val unlogged: String = "UNLOGGED"
        val logged: String = "LOGGED"

    }
        fun find(permission: String): String? {
            return permissions.find { it == permission }
        }

    private val permissions: List<String> = arrayListOf(admin, banned, player, vip, dev, mod, unlogged, logged)
}