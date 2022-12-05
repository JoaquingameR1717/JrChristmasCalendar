package cc.ajneb97;

import cc.ajneb97.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {

    private ChristmasCalendar plugin;
    public MainCommand(ChristmasCalendar plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }

        if(args.length == 1 && args[0].equalsIgnoreCase("reload")){
            if(sender.isOp()){
                sender.sendMessage(MessageUtils.getColoredMessage("&8[&cChristmasCalendar&8] &aConfiguración recargada"));
                plugin.getMainConfigManager().reloadConfig();
            }
            return true;
        }

        Player player = (Player) sender;
        plugin.getInventoryManager().createInventory(player);

        return true;
    }
}
