package dev.feliperf.plugins.Commands.Users

import dev.feliperf.plugins.datasource.controllers.UsersController
import dev.feliperf.plugins.utils.functions.playerIsLogged
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object RegisterCmd : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>,): Boolean {

        val player = (sender as Player)

        if (!playerIsLogged(player)) {
            val user = UsersController.register(player.name, args.first(), "PLAYER")

            if (user != null) {
                player.setDisplayName("[PLAYER] ${sender.name}")
            }
            return user != null
        }
        return false
    }


}