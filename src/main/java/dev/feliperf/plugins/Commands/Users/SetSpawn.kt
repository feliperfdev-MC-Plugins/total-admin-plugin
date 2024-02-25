package dev.feliperf.plugins.Commands.Users

import dev.feliperf.plugins.utils.Contants.AdminString
import dev.feliperf.plugins.Functions.SpecificPermissions
import dev.feliperf.plugins.utils.functions.playerIsAdmin
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object SetSpawnCmd : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>,): Boolean {

        val player = (sender as Player)
        if (playerIsAdmin(player)) {
            val loc = player.location
            player.setRespawnLocation(loc, true)
            player.sendMessage("Respawn definido para: x: ${loc.x} ; y: ${loc.y} ; z: ${loc.z}")

            return true
        }

        sender.sendMessage(AdminString.adminPermission)

        return false
    }


}