package me.laie.playercommands;

import org.bukkit.plugin.java.JavaPlugin;

import me.laie.playercommands.commands.StatsCommand;

public class Main extends JavaPlugin {
    
    @Override
    public void onEnable() {
        new StatsCommand(this);
    }
}
