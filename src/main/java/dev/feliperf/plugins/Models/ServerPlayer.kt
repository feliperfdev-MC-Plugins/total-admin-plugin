package dev.feliperf.plugins.Models

import java.time.LocalDateTime
import java.util.ArrayList
import java.util.UUID

class ServerPlayer(
        name: String,
        joinedAt: LocalDateTime,
        permission: Permission,
        ) {

    var id = UUID.randomUUID().toString();

}

class ServerPlayerFetch(players: ArrayList<ServerPlayer>) {

    private var serverPlayers = ArrayList<ServerPlayer>();

    init {
        serverPlayers = players;
    }
}