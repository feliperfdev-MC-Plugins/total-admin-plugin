package dev.feliperf.plugins.Commands

import dev.feliperf.plugins.utils.Contants.AdminString
import dev.feliperf.plugins.Functions.SpecificPermissions
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object FlyCmd : CommandExecutor {

    fun allowsFly(player: Player) {
        player.allowFlight = !player.allowFlight
        player.sendMessage("${ChatColor.GREEN}${ChatColor.BOLD}FLY: ${if (player.allowFlight) "ON" else "OFF"}")
    }

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>,): Boolean {

        if (SpecificPermissions.canBeAdmin(sender.name)) {
            val player = (sender as Player)
            allowsFly(player)
            return sender.isOnline
        }

        sender.sendMessage(AdminString.adminPermission)

        return false
    }


}