package dev.feliperf.plugins.Commands

import dev.feliperf.plugins.datasource.controllers.UsersController
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object LoginCmd : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>,): Boolean {

        val player = (sender as Player)

        val user = UsersController.getSpecificUser(player.name)

        if (user != null) {
            val validPassword = args.first() == user.password
            if (validPassword) {
                player.isInvisible = false
            }
            return true
        }

        return false
    }


}