package dev.feliperf.plugins.Commands
import dev.feliperf.plugins.Contants.SpecificPermissions
import dev.feliperf.plugins.TotalAdmin
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

class AdminCmd(plugin: TotalAdmin) : CommandExecutor, Listener {

    private val flyFeather = ItemStack(Material.FEATHER)
    private val invisibleGlowDust = ItemStack(Material.GLOWSTONE_DUST)

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>,): Boolean {
        if (SpecificPermissions.canBeAdmin(sender.name)) {
            val admName = sender.name
            sender.sendMessage("${ChatColor.LIGHT_PURPLE}${ChatColor.BOLD}$admName entrou no modo ADMIN!")

            val player = (sender as Player)

            addAdminInventoryItems(player)

            adminCustomName(player)

            return sender.isOnline
        }

        sender.sendMessage("${ChatColor.RED}${ChatColor.BOLD}Você não tem permissão para entrar no modo de ADMIN")

        return false
    }

    private fun addAdminInventoryItems(player: Player) {
        val featherMeta = flyFeather.itemMeta
        featherMeta!!.setDisplayName("${ChatColor.GREEN}[ADMIN] FLY")
        val featherLore = listOf("Able/disable FLY")
        featherMeta.lore = featherLore
        flyFeather.setItemMeta(featherMeta)

        val dustMeta = invisibleGlowDust.itemMeta
        dustMeta!!.setDisplayName("${ChatColor.GREEN}[ADMIN] Invisible")
        val dustLore = listOf("Able/disable FLY")
        dustMeta.lore = dustLore
        invisibleGlowDust.setItemMeta(dustMeta)

        player.inventory.clear()
        player.inventory.addItem(flyFeather)
        player.inventory.addItem(invisibleGlowDust)
    }

    private fun adminCustomName(sender: CommandSender) {
        val player = (sender as Player)
        player.customName = "${ChatColor.DARK_RED}${ChatColor.BOLD}${player.displayName}"
        player.setDisplayName(player.customName)
        player.isCustomNameVisible = true
        player.sendMessage("Seu nome mudou para: ${ChatColor.DARK_RED}${ChatColor.BOLD}${player.customName}")
        sender.setDisplayName(player.customName)
        sender.isCustomNameVisible = true
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
                    else -> {}
                }
            }
        }
    }

    init {
        Bukkit.getServer().pluginManager.registerEvents(this, plugin)
    }
}
