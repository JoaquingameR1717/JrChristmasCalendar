package cc.ajneb97.configs;

import cc.ajneb97.ChristmasCalendar;
import cc.ajneb97.model.CustomItem;
import cc.ajneb97.model.Reward;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;

public class MainConfigManager {

    private ChristmasCalendar plugin;
    private CustomConfig configFile;
    public MainConfigManager(ChristmasCalendar plugin){
        this.plugin = plugin;
        registerConfig();
    }

    public void load(){
        FileConfiguration config = configFile.getConfig();
        ArrayList<CustomItem> items = new ArrayList<>();

        for(String key : config.getConfigurationSection("Items").getKeys(false)){
            String path = "Items."+key;
            String id = config.getString(path+".id");
            CustomItem customitem = new CustomItem(key,id);

            if(config.contains(path+".name")){
                customitem.setName(config.getString(path+".name"));
            }
            if(config.contains(path+".lore")){
                customitem.setLore(config.getStringList(path+".lore"));
            }

            items.add(customitem);
        }

        plugin.getCustomItemManager().setItems(items);

        ArrayList<Reward> rewards = new ArrayList<>();
        for(String key : config.getConfigurationSection("Recompensas").getKeys(false)){
            Reward reward = new Reward(Integer.parseInt(key),config.getStringList("Recompensas."+key));
            rewards.add(reward);
        }
        plugin.getRewardsManager().setRewards(rewards);
    }

    public void registerConfig(){
        configFile = new CustomConfig("config.yml",plugin);
        configFile.registerConfig();
    }

    public void reloadConfig(){
        configFile.reloadConfig();
        load();
    }
}
