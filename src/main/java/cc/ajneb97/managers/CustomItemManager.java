package cc.ajneb97.managers;

import cc.ajneb97.ChristmasCalendar;
import cc.ajneb97.model.CustomItem;
import cc.ajneb97.utils.MessageUtils;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CustomItemManager {

    private ChristmasCalendar plugin;
    private ArrayList<CustomItem> items;
    public CustomItemManager(ChristmasCalendar plugin){
        this.plugin = plugin;
        this.items = new ArrayList<>();
    }

    public ArrayList<CustomItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<CustomItem> items) {
        this.items = items;
    }

    public ItemStack getItemFromPathName(String pathName,int day){
        for(CustomItem customItem : items){
            if(customItem.getPath().equals(pathName)){
                ItemStack item = createItem(customItem,day);
                setNBTStringItem(item,"calendario_navidad_tipo",pathName);

                return item;
            }
        }
        return null;
    }

    public ItemStack createItem(CustomItem customItem,int day){
        String id = customItem.getId();
        ItemStack item = null;
        if(id.startsWith("eyJ")){
            item = new ItemStack(Material.PLAYER_HEAD);

            SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
            GameProfile profile = new GameProfile(UUID.randomUUID(), null);
            profile.getProperties().put("textures",new Property("textures", id));
            try{
                Field profileField = skullMeta.getClass().getDeclaredField("profile");
                profileField.setAccessible(true);
                profileField.set(skullMeta, profile);
            }catch(IllegalArgumentException|NoSuchFieldException|SecurityException|IllegalAccessException error){
                error.printStackTrace();
            }
            item.setItemMeta(skullMeta);
        }else{
            item = new ItemStack(Material.valueOf(id));
        }

        ItemMeta meta = item.getItemMeta();
        if(customItem.getName() != null){
            meta.setDisplayName(MessageUtils.getColoredMessage(customItem.getName().replace("%day%",day+"")));
        }

        if(customItem.getLore() != null){
            List<String> lore = new ArrayList<>(customItem.getLore());
            for(int i=0;i<lore.size();i++){
                lore.set(i,MessageUtils.getColoredMessage(lore.get(i).replace("%day%",day+"")));
            }
            meta.setLore(lore);
        }

        item.setItemMeta(meta);
        item.setAmount(day);
        return item;
    }

    public static void setNBTStringItem(ItemStack item, String key, String value){
        if(item != null && !item.getType().equals(Material.AIR)){
            NBTItem nbti = new NBTItem(item);
            nbti.setString(key, value);
            nbti.applyNBT(item);
        }
    }

    public static String getTagStringItem(ItemStack item,String key){
        if(item != null && !item.getType().equals(Material.AIR)){
            NBTItem nbti = new NBTItem(item);
            if(nbti.hasKey(key)){
                return nbti.getString(key);
            }
        }
        return null;
    }
}
