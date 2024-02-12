package dev.feliperf.plugins.Commands

import dev.feliperf.plugins.Contants.SpecificPermissions
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object TimeSetCmd : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>,): Boolean {

        if (SpecificPermissions.canBeAdmin(sender.name)) {
            val player = (sender as Player)
            val world = player.world

            if (args.first().toLongOrNull() != null) {
                val value = (args.first()).toLong()
                world.time = value
            } else {
                world.time = when(args.first().lowercase()) {
                    "day" -> 1000 // 7h a.m
                    "midday" -> 6000 // 12h p.m
                    "night" -> 12000 // 6h p.m
                    "midnight" -> 24000 // 12h a.m
                    else -> 0 // 6h a.m
                }
            }
            sender.sendMessage("${ChatColor.BLUE}World TimeSet changed!")

            return player.isOnline
        }
        sender.sendMessage("Você não tem permissão de ADMIN para executar este comando!")
        return false
    }


}