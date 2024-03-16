package dev.feliperf.plugins.Commands.Ban

import dev.feliperf.plugins.datasource.controllers.BanController
import dev.feliperf.plugins.utils.Contants.AdminString
import dev.feliperf.plugins.utils.functions.playerIsAdmin
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.time.Duration

object UnbanCmd : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>,): Boolean {

        val player = (sender as Player)
        if (playerIsAdmin(player)) {
            // Get by player name
            val target = Bukkit.getPlayer(args.first())
            if (target != null) {
                val unbannedStatus = BanController.unbanUser(
                    id= target.uniqueId.toString(),
                    name= target.name,
                )

                if (unbannedStatus != null) {
                    sender.sendMessage("${ChatColor.RED}${ChatColor.BOLD}${unbannedStatus.playerName} ${ChatColor.GREEN}foi desbanido com sucesso!")
                }

            }
            return true
        }

        sender.sendMessage(AdminString.adminPermission)

        return false
    }
}