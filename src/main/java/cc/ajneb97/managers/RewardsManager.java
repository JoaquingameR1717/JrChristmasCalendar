package cc.ajneb97.managers;

import cc.ajneb97.ChristmasCalendar;
import cc.ajneb97.model.ChristmasPlayer;
import cc.ajneb97.model.Reward;
import cc.ajneb97.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class RewardsManager {
    private ChristmasCalendar plugin;
    private ArrayList<Reward> rewards;
    public RewardsManager(ChristmasCalendar plugin){
        this.plugin = plugin;
        this.rewards = new ArrayList<>();
    }

    public ArrayList<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(ArrayList<Reward> rewards) {
        this.rewards = rewards;
    }

    public void giveRewardPlayer(Player player, int day){
        for(Reward reward : rewards){
            if(reward.getDay() == day){
                player.sendMessage(MessageUtils.getColoredMessage("&f&l¡RECOMPENSA NAVIDEÑA DE DIA &a"+day+" &f&lRECIBIDA!"));
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,10,2);

                List<String> commands = reward.getCommands();
                for(String command : commands){
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),command.replace("%player%",player.getName()));
                }

                return;
            }
        }
    }
}
