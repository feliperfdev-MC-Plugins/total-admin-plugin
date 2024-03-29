package dev.feliperf.plugins.Commands.Users

import dev.feliperf.plugins.utils.Contants.AdminString
import dev.feliperf.plugins.utils.functions.playerIsAdmin
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object TimeSetCmd : CommandExecutor {

    fun changeTimeSet(player: Player, args: Array<out String>) {
        if (args.first().toLongOrNull() != null) {
            val value = (args.first()).toLong()
            player.world.time = value
        } else {
            player.world.time = when(args.first().lowercase()) {
                "day" -> 1000 // 7h a.m
                "midday" -> 6000 // 12h p.m
                "night" -> 12000 // 6h p.m
                "midnight" -> 24000 // 12h a.m
                else -> 0 // 6h a.m
            }
        }
        player.sendMessage("${ChatColor.BLUE}World TimeSet changed!")
    }

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>,): Boolean {

        val player = (sender as Player)
        if (playerIsAdmin(player)) {
            changeTimeSet(player, args)
            return player.isOnline
        }
        sender.sendMessage(AdminString.adminPermission)
        return false
    }


}