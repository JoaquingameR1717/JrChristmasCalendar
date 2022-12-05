package cc.ajneb97.model;

import java.util.List;

public class ChristmasPlayer {
    private String uuid;
    private List<String> claimedDays;

    public ChristmasPlayer(String uuid, List<String> claimedDays) {
        this.uuid = uuid;
        this.claimedDays = claimedDays;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<String> getClaimedDays() {
        return claimedDays;
    }

    public void setClaimedDays(List<String> claimedDays) {
        this.claimedDays = claimedDays;
    }
}
