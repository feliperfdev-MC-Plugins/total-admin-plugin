package dev.feliperf.plugins

import dev.feliperf.plugins.Commands.Ban.BanCmd
import dev.feliperf.plugins.Commands.Users.*
import dev.feliperf.plugins.datasource.controllers.BanController
import dev.feliperf.plugins.datasource.controllers.UsersController
import dev.feliperf.plugins.utils.functions.playerIsBanned
import dev.feliperf.plugins.utils.functions.playerIsLogged
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.plugin.java.JavaPlugin
import kotlin.concurrent.timer

class TotalAdmin : JavaPlugin(), Listener {

    @EventHandler
    fun onQuitServer(event: PlayerQuitEvent) {
        val player = event.player
        val auth = UsersController.disconnect(player.name)
        if (auth != null) {
            if (!auth.logged) {
                Bukkit.broadcastMessage("${ChatColor.YELLOW}${player.name} desconectou do servidor!")
            }
        }
    }

    @EventHandler
    fun onJoinServer(event: PlayerJoinEvent) {

        BanController.verifyBannedUsers()

        val player = event.player
        val playerFromDB = UsersController.getSpecificUser(player.name)

        if (playerFromDB != null) {
            if (playerIsBanned(playerFromDB)) {

                Bukkit.getPlayer(playerFromDB.name)?.kickPlayer("${ChatColor.RED} VOCÊ ESTÁ BANIDO DO SERVIDOR!")
            }
        }

        player.setDisplayName("[UNLOGGED] ${player.name}")
        val authCounter = 20


        var times = 0
        val useServerMessage = "para poder continuar usando o servidor"

        timer(initialDelay = 1000L, period = 2000L) {
            if (playerIsLogged(player)) {
                player.setDisplayName("${if (playerFromDB!!.permission == "ADMIN") "[ADMIN]" else "[PLAYER]"} ${player.name}")
                player.sendMessage("${ChatColor.GREEN}${ChatColor.BOLD}BEM VINDE ${player.name}!!!")
                this.cancel()
            } else {
                times++
                player.sendMessage(
                        if (playerFromDB == null) "${ChatColor.RED}[$times/$authCounter] ${ChatColor.YELLOW}Use /register <password> $useServerMessage"
                        else "${ChatColor.RED}[$times/$authCounter] ${ChatColor.YELLOW}Use /login <password> $useServerMessage"
                )
                if (times == authCounter) {
                    this.cancel()
                    Bukkit.getPlayer(player.uniqueId)!!.kickPlayer("${ChatColor.RED}Demorou para autenticar!")
                }
            }
        }
    }

    override fun onEnable() {
        Bukkit.getServer().pluginManager.registerEvents(this, this)

        Bukkit.getServer().motd = "${ChatColor.LIGHT_PURPLE}BEM VINDE AO CHERRY CRAFT\n${ChatColor.GREEN}Divirta-se!!!"

        // USER Commands
        getCommand("admin")?.setExecutor(AdminCmd(this))
        getCommand("noadmin")?.setExecutor(NoAdminCmd)
        getCommand("gamemode")?.setExecutor(GamemodeCmd)
        getCommand("setspawn")?.setExecutor(SetSpawnCmd)
        getCommand("fly")?.setExecutor(FlyCmd)
        getCommand("timeset")?.setExecutor(TimeSetCmd)
        getCommand("kill")?.setExecutor(KillCmd)
        getCommand("warning")?.setExecutor(WarningCmd)
        getCommand("login")?.setExecutor(LoginCmd)
        getCommand("register")?.setExecutor(RegisterCmd)
        getCommand("spawn")?.setExecutor(SpawnCmd)

        // BAN/KICK Commands
        getCommand("ban")?.setExecutor(BanCmd)
    }

    override fun onLoad() {
        Bukkit.getServer().broadcastMessage("Plugins inicializados com sucesso!")
        super.onLoad()
    }

    override fun onDisable() {
    }
}
