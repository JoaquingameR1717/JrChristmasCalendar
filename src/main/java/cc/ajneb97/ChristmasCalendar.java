package cc.ajneb97;

import cc.ajneb97.configs.MainConfigManager;
import cc.ajneb97.configs.PlayersConfigManager;
import cc.ajneb97.listeners.InventoryListener;
import cc.ajneb97.managers.CustomItemManager;
import cc.ajneb97.managers.InventoryManager;
import cc.ajneb97.managers.PlayersManager;
import cc.ajneb97.managers.RewardsManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ChristmasCalendar extends JavaPlugin {

    private CustomItemManager customItemManager;
    private InventoryManager inventoryManager;
    private PlayersManager playersManager;
    private RewardsManager rewardsManager;
    private MainConfigManager mainConfigManager;
    private PlayersConfigManager playersConfigManager;

    public void onEnable(){
        this.customItemManager = new CustomItemManager(this);
        this.inventoryManager = new InventoryManager(this);
        this.playersManager = new PlayersManager(this);
        this.rewardsManager = new RewardsManager(this);

        this.mainConfigManager = new MainConfigManager(this);
        this.mainConfigManager.load();
        this.playersConfigManager = new PlayersConfigManager(this);
        this.playersConfigManager.load();

        registerCommands();
        registerEvents();
    }

    public void onDisable(){
        this.playersConfigManager.save();
    }

    public void registerCommands(){
        this.getCommand("calendario").setExecutor(new MainCommand(this));
    }

    public void registerEvents(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new InventoryListener(this),this);
    }

    public CustomItemManager getCustomItemManager() {
        return customItemManager;
    }

    public MainConfigManager getMainConfigManager() {
        return mainConfigManager;
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public PlayersManager getPlayersManager() {
        return playersManager;
    }

    public RewardsManager getRewardsManager() {
        return rewardsManager;
    }
}
