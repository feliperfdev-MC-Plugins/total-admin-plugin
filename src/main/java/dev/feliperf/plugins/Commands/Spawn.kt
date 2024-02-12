package dev.feliperf.plugins.Commands

import dev.feliperf.plugins.Contants.SpecificPermissions
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object SpawnCmd : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>,): Boolean {

        if (SpecificPermissions.canBeAdmin(sender.name)) {

            val player = (sender as Player)

            val loc = player.location

            player.setRespawnLocation(loc, true)
            player.sendMessage("Respawn definido para: x: ${loc.x} ; y: ${loc.y} ; z: ${loc.z}")

            return true
        }

        sender.sendMessage("Você não tem permissão de ADMIN para executar este comando!")

        return false
    }


}