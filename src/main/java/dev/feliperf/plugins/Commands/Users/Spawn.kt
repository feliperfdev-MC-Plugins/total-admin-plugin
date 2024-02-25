package dev.feliperf.plugins.Commands.Users

import dev.feliperf.plugins.utils.Contants.AdminString
import dev.feliperf.plugins.Functions.SpecificPermissions
import dev.feliperf.plugins.utils.Contants.ServerSpawn
import dev.feliperf.plugins.utils.functions.playerIsAdmin
import dev.feliperf.plugins.utils.functions.playerIsLogged
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object SpawnCmd : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>,): Boolean {
        val player = (sender as Player)
        if (playerIsLogged(player)) {
            val serverSpawn = ServerSpawn()
            val teleported = player.teleport(Location(
                    player.world,
                    serverSpawn.x,
                    serverSpawn.y,
                    serverSpawn.z,
            ))
            if (teleported) {
                player.sendMessage("${ChatColor.GREEN}Teleportou para o SPAWN")
            }
        }
        return true
    }


}