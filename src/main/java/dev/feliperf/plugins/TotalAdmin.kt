package dev.feliperf.plugins

import dev.feliperf.plugins.Commands.*
import dev.feliperf.plugins.datasource.controllers.UsersController
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
        player.isInvisible = true

        val users = UsersController.fetch()

        var times = 0

            timer(initialDelay = 1000L, period = 2000L) {
                if (!player.isInvisible) {
                    player.sendMessage("${ChatColor.GREEN}${ChatColor.BOLD}BEM VINDE ${player.name}!!!")
                    this.cancel()
                } else {
                    if (!users.any { it.name == player.name }) {
                        times++
                        player.sendMessage("${ChatColor.YELLOW}Use /register <password> para poder continuar usando o servidor")
                    } else {
                        times++
                        player.sendMessage("${ChatColor.YELLOW}Use /login <password> para poder continuar usando o servidor")
                    }
                    if (times == 100) {
                        Bukkit.getPlayer(player.name)!!.kickPlayer("${ChatColor.RED}Demorou para autenticar!")
                    }
                }
            }
    }

    override fun onEnable() {
        Bukkit.getServer().pluginManager.registerEvents(this, this)

        Bukkit.getServer().motd = "${ChatColor.LIGHT_PURPLE}BEM VINDE AO CHERRY CRAFT\n${ChatColor.GREEN}Felipe & Clara Swift Powered"

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
