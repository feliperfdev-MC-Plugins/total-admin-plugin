package dev.feliperf.plugins.Commands.Users

import dev.feliperf.plugins.utils.Contants.AdminString
import dev.feliperf.plugins.utils.functions.playerIsAdmin
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object WarningCmd : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>,): Boolean {

        if (playerIsAdmin(sender as Player)) {
            if (args.isEmpty()) {
                sender.sendMessage("${ChatColor.RED}Uso: /warning <mensagem>")
            } else {
                val message = args.joinToString(" ")
                Bukkit.getServer().broadcastMessage("${ChatColor.DARK_RED}${ChatColor.BOLD}[SERVER-WARNING]: ${ChatColor.RED}$message")
            }
            return sender.isOnline
        }

        sender.sendMessage(AdminString.adminPermission)
        return false
    }


}