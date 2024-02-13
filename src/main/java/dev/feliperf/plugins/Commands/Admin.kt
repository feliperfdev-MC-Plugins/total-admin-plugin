package dev.feliperf.plugins.Commands
import dev.feliperf.plugins.utils.Admin.AdminString
import dev.feliperf.plugins.Functions.SpecificPermissions
import dev.feliperf.plugins.Functions.AdminFunctions
import dev.feliperf.plugins.TotalAdmin
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

class AdminCmd(plugin: TotalAdmin) : CommandExecutor, Listener {


    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>,): Boolean {
        if (SpecificPermissions.canBeAdmin(sender.name)) {
            val admName = sender.name
            sender.sendMessage("${ChatColor.LIGHT_PURPLE}${ChatColor.BOLD}$admName entrou no modo ADMIN!")
            val player = (sender as Player)
            player.health = 20.0
            player.foodLevel = 20

            AdminFunctions.addAdminInventoryItems(player)
            AdminFunctions.setCustomName(player)

            return sender.isOnline
        }

        sender.sendMessage(AdminString.adminPermission)

        return false
    }

    @EventHandler
    fun onInventoryInteracts(event: PlayerInteractEvent) {
        if (SpecificPermissions.canBeAdmin(event.player.name)) {
            val clickedItemMaterial = event.item!!.type
            val player = event.player
            if (event.action == Action.RIGHT_CLICK_AIR || event.action == Action.RIGHT_CLICK_BLOCK) {
                when(clickedItemMaterial) {
                    Material.FEATHER -> {
                        player.allowFlight = !player.allowFlight
                        player.sendMessage("${ChatColor.GREEN}${ChatColor.BOLD}FLY: ${if (player.allowFlight) "ON" else "OFF"}")
                    }
                    Material.GLOWSTONE_DUST -> {
                        player.isInvulnerable = !player.isInvulnerable
                        player.isInvisible = !player.isInvisible
                        player.sendMessage("${ChatColor.GREEN}${ChatColor.BOLD}INVISIBLE: ${if (player.isInvisible) "ON" else "OFF"}")
                        player.sendMessage("${ChatColor.GREEN}${ChatColor.BOLD}INVULNERABLE: ${if (player.isInvulnerable) "ON" else "OFF"}")
                    }
                    Material.DIAMOND_SWORD -> {
                        val mode = when(player.gameMode) {
                            GameMode.SURVIVAL -> "1"
                            GameMode.CREATIVE -> "0"
                            else -> "1"
                        }
                        GamemodeCmd.changeGameMode(player, arrayOf(mode))
                    }
                    Material.CLOCK -> {
                        val world = player.world
                        val time = world.time
                        var args = ""
                        if (time in 11001..23999) args = "day"
                        else if (time > 24000) args = "night"
                        TimeSetCmd.changeTimeSet(player, arrayOf(args))
                    }
                    Material.COAL -> {
                        player.allowFlight = false
                        player.isInvisible = false
                        player.isInvulnerable = false
                        player.isCustomNameVisible = false
                        player.inventory.clear()
                        player.sendMessage("${ChatColor.AQUA}${player.name} saiu no modo ADMIN!")
                    }
                    else -> {}
                }
            }
        }
    }

    init {
        Bukkit.getServer().pluginManager.registerEvents(this, plugin)
    }
}
