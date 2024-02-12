package dev.feliperf.plugins.Functions

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

data class CustomItem(val material: Material, val name: String, val lore: String?)

class AdminFunctions {

    companion object {
        fun addAdminInventoryItems(player: Player) {
            fun addToInventory(item: CustomItem) {
                val itemStack = ItemStack(item.material)
                val itemMeta = itemStack.itemMeta
                if (itemMeta != null) {
                    itemMeta.setDisplayName(item.name)
                    if (item.lore != null) {
                        itemMeta.lore = listOf(item.lore)
                    }
                    itemStack.setItemMeta(itemMeta)
                    player.inventory.addItem(itemStack)
                } else return
            }

            val flyMode = CustomItem(
                    material = Material.FEATHER,
                    "${ChatColor.GREEN}[ADMIN] FLY",
                    "Able/disable FLY",
            )
            val invisibility = CustomItem(
                    material = Material.GLOWSTONE_DUST,
                    "${ChatColor.GREEN}[ADMIN] Invisible",
                    "Able/disable invisibility",
            )
            val gameMode = CustomItem(
                    material = Material.DIAMOND_SWORD,
                    "${ChatColor.GREEN}[ADMIN] Gamemode",
                    "Easy change of gamemode",
            )
            val timeSet = CustomItem(
                    material = Material.CLOCK,
                    "${ChatColor.GREEN}[ADMIN] Timeset",
                    "Easy change of TimeSet",
            )

            val leaveAdmin = CustomItem(
                    material = Material.COAL,
                    "${ChatColor.DARK_RED}LEAVE ADMIN",
                    "Leave Admin mode",
            )

            player.inventory.clear()
            addToInventory(flyMode)
            addToInventory(invisibility)
            addToInventory(gameMode)
            addToInventory(timeSet)
            addToInventory(leaveAdmin)
        }

        fun setCustomName(sender: CommandSender) {
            val player = (sender as Player)
            player.customName = "${ChatColor.DARK_RED}${ChatColor.BOLD}${player.displayName}"
            player.setDisplayName(player.customName)
            player.isCustomNameVisible = true
            player.sendMessage("Seu nome mudou para: ${ChatColor.DARK_RED}${ChatColor.BOLD}${player.customName}")
            sender.setDisplayName(player.customName)
            sender.isCustomNameVisible = true
        }
    }
}