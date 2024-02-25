package dev.feliperf.plugins.Commands.Users

import dev.feliperf.plugins.utils.Contants.AdminString
import dev.feliperf.plugins.Functions.SpecificPermissions
import dev.feliperf.plugins.utils.functions.playerIsAdmin
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object NoAdminCmd : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>,): Boolean {

        val player = (sender as Player)
        if (playerIsAdmin(player)) {
            val admName = sender.name
            sender.sendMessage("${ChatColor.AQUA}$admName saiu no modo ADMIN!")

            player.allowFlight = false
            player.isCustomNameVisible = false
            sender.setDisplayName("[PLAYER] ${sender.name}")
            sender.customName = "${ChatColor.WHITE}${sender.name}"
            sender.isCustomNameVisible = false

            sender.isInvulnerable = false
            sender.isInvisible = false

            return sender.isOnline
        }

        sender.sendMessage(AdminString.adminPermission)

        return false
    }


}