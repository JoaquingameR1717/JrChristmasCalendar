package cc.ajneb97.managers;

import cc.ajneb97.ChristmasCalendar;
import cc.ajneb97.model.ChristmasPlayer;

import java.util.ArrayList;
import java.util.List;

public class PlayersManager {

    private ChristmasCalendar plugin;
    private ArrayList<ChristmasPlayer> players;
    public PlayersManager(ChristmasCalendar plugin){
        this.plugin = plugin;
        this.players = new ArrayList<>();
    }

    public ArrayList<ChristmasPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<ChristmasPlayer> players) {
        this.players = players;
    }

    public ChristmasPlayer getPlayer(String uuid){
        for(ChristmasPlayer christmasPlayer : players){
            if(christmasPlayer.getUuid().equals(uuid)){
                return christmasPlayer;
            }
        }
        return null;
    }

    public void addPlayerClaimedDay(String uuid,int day){
        ChristmasPlayer player = getPlayer(uuid);
        if(player == null){
            //no existe
            List<String> claimedDays = new ArrayList<>();
            claimedDays.add(day+"");
            players.add(new ChristmasPlayer(uuid,claimedDays));
        }else{
            //existe
            player.getClaimedDays().add(day+"");
        }
    }

    public boolean dayAlreadyClaimed(String uuid,int day){
        ChristmasPlayer player = getPlayer(uuid);
        if(player == null){
            return false;
        }

        return player.getClaimedDays().contains(day+"");
    }
}
