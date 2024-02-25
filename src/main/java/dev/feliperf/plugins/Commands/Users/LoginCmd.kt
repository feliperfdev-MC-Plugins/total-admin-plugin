package dev.feliperf.plugins.Commands.Users

import dev.feliperf.plugins.datasource.controllers.UsersController
import dev.feliperf.plugins.utils.functions.playerIsAdmin
import dev.feliperf.plugins.utils.functions.playerIsLogged
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object LoginCmd : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>,): Boolean {

        val player = sender as Player

        if (!playerIsLogged(player)) {
            val auth = UsersController.login(player.name, args.first())

            if (auth != null) {
                if (auth.logged) {
                    if (!playerIsAdmin(player)) {
                        player.setDisplayName("[PLAYER] ${sender.name}")
                    }
                }
            }
            return auth != null
        }
        return false
    }


}