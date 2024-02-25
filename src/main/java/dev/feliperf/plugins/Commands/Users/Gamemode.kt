package dev.feliperf.plugins.Commands.Users

import dev.feliperf.plugins.utils.Contants.AdminString
import dev.feliperf.plugins.utils.functions.playerIsAdmin
import dev.feliperf.plugins.utils.functions.playerIsLogged
import org.bukkit.ChatColor
import org.bukkit.GameMode
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object GamemodeCmd : CommandExecutor {

    fun changeGameMode(player: Player, args: Array<out String>) {
        player.gameMode = when(args.first()) {
            "0" -> GameMode.SURVIVAL
            "1" -> GameMode.CREATIVE
            "2" -> GameMode.SPECTATOR
            else -> GameMode.SURVIVAL
        }

        player.sendMessage("${ChatColor.GREEN}Você entrou no modo de jogo ${ChatColor.DARK_GREEN}${player.gameMode.name}");
    }

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>,): Boolean {

        val player = (sender as Player)
        if (playerIsAdmin(player)) {
            if (playerIsLogged(player)) {
                changeGameMode(player, args)
            }
            return true
        }

        sender.sendMessage(AdminString.adminPermission)

        return false
    }


}