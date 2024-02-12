package dev.feliperf.plugins

import dev.feliperf.plugins.Commands.*
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.plugin.java.JavaPlugin

class TotalAdmin : JavaPlugin() {
    override fun onEnable() {

        Bukkit.getServer().motd = "${ChatColor.LIGHT_PURPLE}BEM VINDO AO CHERRY CRAFT\n${ChatColor.GREEN}Clara Swift Powered"

        getCommand("admin")?.setExecutor(AdminCmd(this))

        getCommand("noadmin")?.setExecutor(NoAdminCmd)
        getCommand("gamemode")?.setExecutor(GamemodeCmd)
        getCommand("setspawn")?.setExecutor(SetSpawnCmd)
        getCommand("fly")?.setExecutor(FlyCmd)
        getCommand("timeset")?.setExecutor(TimeSetCmd)
        getCommand("kill")?.setExecutor(KillCmd)
        getCommand("warning")?.setExecutor(WarningCmd)
    }

    override fun onLoad() {
        Bukkit.getServer().broadcastMessage("Plugins inicializados com sucesso!")
        super.onLoad()
    }

    override fun onDisable() {
    }
}
