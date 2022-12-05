package cc.ajneb97.model;

import java.util.List;

public class Reward {
    private int day;
    private List<String> commands;

    public Reward(int day, List<String> commands) {
        this.day = day;
        this.commands = commands;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public List<String> getCommands() {
        return commands;
    }

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }
}
