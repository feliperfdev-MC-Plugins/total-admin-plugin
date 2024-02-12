package dev.feliperf.plugins

import dev.feliperf.plugins.Commands.*
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class TotalAdmin : JavaPlugin() {
    override fun onEnable() {
        getCommand("admin")?.setExecutor(AdminCmd(this))

        getCommand("noadmin")?.setExecutor(NoAdminCmd)
        getCommand("gamemode")?.setExecutor(GamemodeCmd)
        getCommand("spawn")?.setExecutor(SpawnCmd)
        getCommand("fly")?.setExecutor(FlyCmd)
        getCommand("timeset")?.setExecutor(TimeSetCmd)
        getCommand("kill")?.setExecutor(KillCmd)
    }

    override fun onLoad() {
        Bukkit.getServer().broadcastMessage("Plugins inicializados com sucesso!")
        super.onLoad()
    }

    override fun onDisable() {
    }
}
