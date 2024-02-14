package dev.feliperf.plugins

import dev.feliperf.plugins.Commands.*
import dev.feliperf.plugins.datasource.controllers.UsersController
import dev.feliperf.plugins.utils.functions.playerIsLogged
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin
import kotlin.concurrent.timer

class TotalAdmin : JavaPlugin(), Listener {

    @EventHandler
    fun onJoinServer(event: PlayerJoinEvent) {
        val player = event.player
        player.setDisplayName("[UNLOGGED] ${player.name}")

        val users = UsersController.fetch()

        var times = 0
        val useServerMessage = "para poder continuar usando o servidor"

            timer(initialDelay = 1000L, period = 2000L) {
                if (playerIsLogged(player)) {
                    player.sendMessage("${ChatColor.GREEN}${ChatColor.BOLD}BEM VINDE ${player.name}!!!")
                    this.cancel()
                } else {
                    times++
                    player.sendMessage(
                            if (!users.any { it.name == player.name }) "${ChatColor.RED}[$times/50] ${ChatColor.YELLOW}Use /register <password> $useServerMessage"
                            else "${ChatColor.RED}[$times/50] ${ChatColor.YELLOW}Use /login <password> $useServerMessage"
                    )
                    if (times == 50) {
                        Bukkit.getPlayer(player.name)!!.kickPlayer("${ChatColor.RED}Demorou para autenticar!")
                    }
                }
            }
    }

    override fun onEnable() {
        Bukkit.getServer().pluginManager.registerEvents(this, this)

        Bukkit.getServer().motd = "${ChatColor.LIGHT_PURPLE}BEM VINDE AO CHERRY CRAFT\n${ChatColor.GREEN}Divirta-se!!!"

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
    }

    override fun onLoad() {
        Bukkit.getServer().broadcastMessage("Plugins inicializados com sucesso!")
        super.onLoad()
    }

    override fun onDisable() {
    }
}
