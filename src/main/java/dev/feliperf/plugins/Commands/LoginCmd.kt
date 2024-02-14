package dev.feliperf.plugins.Commands

import dev.feliperf.plugins.datasource.controllers.UsersController
import dev.feliperf.plugins.utils.functions.playerIsLogged
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object LoginCmd : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>,): Boolean {

        val player = sender as Player

        if (!playerIsLogged(player)) {
            val user = UsersController.getSpecificUser(player.name)

            if (user != null) {
                val validPassword = args.first() == user.password
                if (validPassword) {
                    player.setDisplayName("[PLAYER] ${sender.name}")
                }
            }

            return user != null
        }
        return false
    }


}