package cc.ajneb97.listeners;

import cc.ajneb97.ChristmasCalendar;
import cc.ajneb97.managers.CustomItemManager;
import cc.ajneb97.managers.InventoryManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryListener implements Listener {

    private ChristmasCalendar plugin;
    public InventoryListener(ChristmasCalendar plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void closeInventory(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();
        plugin.getInventoryManager().removePlayer(player);
    }

    @EventHandler
    public void clickInventory(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        InventoryManager inventoryManager = plugin.getInventoryManager();

        if(inventoryManager.isPlayerInInventory(player)){
            event.setCancelled(true);
            if(event.getCurrentItem() == null){
                return;
            }
            if(event.getSlotType() == null){
                return;
            }

            if(event.getClickedInventory().equals(player.getOpenInventory().getTopInventory())){
                ItemStack item = event.getCurrentItem();
                String type = CustomItemManager.getTagStringItem(item,"calendario_navidad_tipo");
                if(type == null){
                    return;
                }

                if(type.equals("regalo_disponible")){
                    plugin.getPlayersManager().addPlayerClaimedDay(player.getUniqueId().toString(),item.getAmount());
                    inventoryManager.createInventory(player);
                    //recompensa
                    plugin.getRewardsManager().giveRewardPlayer(player,item.getAmount());
                }
            }
        }
    }
}
