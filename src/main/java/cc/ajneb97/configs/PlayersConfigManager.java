package cc.ajneb97.configs;

import cc.ajneb97.ChristmasCalendar;
import cc.ajneb97.model.ChristmasPlayer;
import cc.ajneb97.model.CustomItem;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class PlayersConfigManager {

    private ChristmasCalendar plugin;
    private CustomConfig configFile;
    public PlayersConfigManager(ChristmasCalendar plugin){
        this.plugin = plugin;
        registerConfig();
    }

    public void load(){
        FileConfiguration config = configFile.getConfig();
        ArrayList<ChristmasPlayer> players = new ArrayList<>();

        if(config.contains("Players")){
            for(String key : config.getConfigurationSection("Players").getKeys(false)){
                //uuid = key
                List<String> claimedDays = config.getStringList("Players."+key+".claimed_days");
                ChristmasPlayer christmasPlayer = new ChristmasPlayer(key,claimedDays);

                players.add(christmasPlayer);
            }
        }

        plugin.getPlayersManager().setPlayers(players);
    }

    public void registerConfig(){
        configFile = new CustomConfig("players.yml",plugin);
        configFile.registerConfig();
    }

    public void save(){
        ArrayList<ChristmasPlayer> players = plugin.getPlayersManager().getPlayers();
        FileConfiguration config = configFile.getConfig();
        config.set("Players",null);

        for(ChristmasPlayer player : players){
            config.set("Players."+player.getUuid()+".claimed_days",player.getClaimedDays());
        }

        configFile.saveConfig();
    }
}
