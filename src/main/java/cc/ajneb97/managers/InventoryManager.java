package cc.ajneb97.managers;

import cc.ajneb97.ChristmasCalendar;
import cc.ajneb97.model.CustomItem;
import cc.ajneb97.utils.MessageUtils;
import cc.ajneb97.utils.OtherUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class InventoryManager {

    private ChristmasCalendar plugin;
    private ArrayList<Player> players;
    public InventoryManager(ChristmasCalendar plugin){
        this.plugin = plugin;
        this.players = new ArrayList<>();
    }

    public void createInventory(Player player){
        Inventory inv = Bukkit.createInventory(null, 54, MessageUtils.getColoredMessage("&c&lCalendario Navideño"));

        ArrayList<Integer> slots = new ArrayList<>();
        OtherUtils.addRangeToList(10,16,slots);
        OtherUtils.addRangeToList(19,25,slots);
        OtherUtils.addRangeToList(28,34,slots);
        OtherUtils.addRangeToList(39,41,slots);

        LocalDate date = LocalDate.now();
        int today = date.getDayOfMonth();
        //int today = 4;

        Month month = date.getMonth();

        CustomItemManager customItemManager = plugin.getCustomItemManager();
        PlayersManager playersManager = plugin.getPlayersManager();

        int daySlot = 1;
        for(int slot : slots){
            if(daySlot > today){
                //No ha llegado al dia
                inv.setItem(slot,customItemManager.getItemFromPathName("regalo_proximo",daySlot));
            }else if(daySlot == today){
                //Es el dia
                if(playersManager.dayAlreadyClaimed(player.getUniqueId().toString(),daySlot)){
                    //Ya reclamado
                    inv.setItem(slot,customItemManager.getItemFromPathName("regalo_ya_reclamado",daySlot));
                }else{
                    //No reclamado
                    inv.setItem(slot,customItemManager.getItemFromPathName("regalo_disponible",daySlot));
                }
            }else{
                //El dia ya paso
                if(playersManager.dayAlreadyClaimed(player.getUniqueId().toString(),daySlot)){
                    //Ya reclamado
                    inv.setItem(slot,customItemManager.getItemFromPathName("regalo_ya_reclamado",daySlot));
                }else{
                    //No reclamado
                    inv.setItem(slot,customItemManager.getItemFromPathName("regalo_perdido",daySlot));
                }
            }

            daySlot++;
        }

        player.openInventory(inv);
        players.add(player);
    }

    public boolean isPlayerInInventory(Player player){
        if(players.contains(player)){
            return true;
        }
        return false;
    }

    public void removePlayer(Player player){
        this.players.remove(player);
    }
}
