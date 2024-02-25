package dev.feliperf.plugins.Commands.Ban

import dev.feliperf.plugins.datasource.controllers.BanController
import dev.feliperf.plugins.utils.Contants.AdminString
import dev.feliperf.plugins.utils.functions.playerIsAdmin
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.time.Duration

object BanCmd : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>,): Boolean {

        val player = (sender as Player)
        if (playerIsAdmin(player)) {
            // Get by player name
            val target = Bukkit.getPlayer(args.first())
            if (target != null) {
                val bannedUser = BanController.banUser(
                        id= target.uniqueId.toString(),
                        name= target.name,
                        reason= args[1],
                        durationInDays= args[2].toIntOrNull() ?: 0,
                )
                if (bannedUser != null) {
                    target.banIp(
                            bannedUser.name,
                            Duration.ofDays(bannedUser.durationInDays.toLong()),
                            bannedUser.reason,
                            true,
                    )
                }
            }
            return true
        }

        sender.sendMessage(AdminString.adminPermission)

        return false
    }
}