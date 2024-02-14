package dev.feliperf.plugins.Commands

import dev.feliperf.plugins.utils.Contants.AdminString
import dev.feliperf.plugins.Functions.SpecificPermissions
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object NoAdminCmd : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>,): Boolean {

        if (SpecificPermissions.canBeAdmin(sender.name)) {
            val admName = sender.name
            sender.sendMessage("${ChatColor.AQUA}$admName saiu no modo ADMIN!")

            val player = (sender as Player)

            player.allowFlight = false
            player.isCustomNameVisible = false
            sender.setDisplayName(sender.name)
            sender.customName = sender.name
            sender.isCustomNameVisible = false

            sender.isInvulnerable = false
            sender.isInvisible = false

            return sender.isOnline
        }

        sender.sendMessage(AdminString.adminPermission)

        return false
    }


}