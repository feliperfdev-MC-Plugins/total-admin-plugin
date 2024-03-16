package dev.feliperf.plugins.Commands.Users

import dev.feliperf.plugins.datasource.controllers.UsersController
import dev.feliperf.plugins.utils.Contants.AdminString
import dev.feliperf.plugins.utils.functions.playerIsAdmin
import dev.feliperf.plugins.utils.models.UserPermission
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object PromoteCmd : CommandExecutor {

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>,): Boolean {

        val player = (sender as Player)
        if (playerIsAdmin(player)) {
            val permission = args.first()
            val userPermission = UserPermission().find(permission)
            val promoteStatus = UsersController.promote(player.name, (userPermission ?: UserPermission.player))
            if (promoteStatus != null) {
                val settedPermission = UserPermission().find(promoteStatus.permission)
                sender.sendMessage("Você foi promovido à $settedPermission")
                player.customName = "[$settedPermission] ${player.name}"
            }
            return sender.isOnline
        }

        sender.sendMessage(AdminString.adminPermission)

        return false
    }
}