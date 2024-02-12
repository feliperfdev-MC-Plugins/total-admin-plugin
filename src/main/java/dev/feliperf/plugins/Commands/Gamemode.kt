package dev.feliperf.plugins.Commands

import dev.feliperf.plugins.Contants.SpecificPermissions
import org.bukkit.GameMode
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object GamemodeCmd : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>,): Boolean {

        if (SpecificPermissions.canBeAdmin(sender.name)) {

            val player = (sender as Player)

            player.gameMode = when(args.first()) {
                "0" -> GameMode.SURVIVAL
                "1" -> GameMode.CREATIVE
                "2" -> GameMode.SPECTATOR
                else -> GameMode.SURVIVAL
            }

            player.sendMessage("Você entrou no modo de jogo - ${player.gameMode.name}");

            return true
        }

        sender.sendMessage("Você não tem permissão de ADMIN para executar este comando!")

        return false
    }


}