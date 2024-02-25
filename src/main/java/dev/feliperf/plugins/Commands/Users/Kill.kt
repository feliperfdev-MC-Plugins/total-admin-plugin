package dev.feliperf.plugins.Commands.Users

import dev.feliperf.plugins.utils.Contants.AdminString
import dev.feliperf.plugins.utils.functions.playerIsAdmin
import dev.feliperf.plugins.utils.functions.playerIsLogged
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object KillCmd : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>,): Boolean {

        val player = (sender as Player)
        if (playerIsAdmin(player)) {
            if (playerIsLogged(player)) {
                if (args.isEmpty()) {
                    player.damage(100.0, player)
                } else {
                    val target = Bukkit.getPlayer(args.first())
                    player.damage(100.0, target)
                }
            }

            return sender.isOnline
        }

        sender.sendMessage(AdminString.adminPermission)

        return false
    }


}