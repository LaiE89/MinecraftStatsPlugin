package me.laie.playercommands.commands;

import me.laie.playercommands.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Score;

public class StatsCommand implements CommandExecutor {

    private Main plugin;

    public StatsCommand(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("stats").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can execute this command!");
            return true;
        }

        Player p = (Player) sender;
        
        if (cmd.getName().equalsIgnoreCase("stats")) {
            Score balance;
            Score rank;
            Score wins;
            Score games;
            Score winrate;
            Score kills;
            Score deaths;
            Score bosskills;
            if (args.length == 0) {
                balance = p.getScoreboard().getObjective("Money").getScore(p.getName());
                rank = p.getScoreboard().getObjective("Rank").getScore(p.getName());
                wins = p.getScoreboard().getObjective("SSBWins").getScore(p.getName());
                games = p.getScoreboard().getObjective("SSBGames").getScore(p.getName());
                winrate = p.getScoreboard().getObjective("SSBWinrate").getScore(p.getName());
                kills = p.getScoreboard().getObjective("LabyrinthKills").getScore(p.getName());
                deaths = p.getScoreboard().getObjective("LabyrinthDeath").getScore(p.getName());
                bosskills = p.getScoreboard().getObjective("BossKills").getScore(p.getName());
                display(p, p, balance, rank, wins, games, winrate, kills, deaths, bosskills);
                return true;
            }else if (args.length == 1){
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    p.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD.toString() + "That player is not online...");

                }else {
                    balance = p.getScoreboard().getObjective("Money").getScore(target.getName());
                    rank = p.getScoreboard().getObjective("Rank").getScore(target.getName());
                    wins = p.getScoreboard().getObjective("SSBWins").getScore(target.getName());
                    games = p.getScoreboard().getObjective("SSBGames").getScore(target.getName());
                    winrate = p.getScoreboard().getObjective("SSBWinrate").getScore(target.getName());
                    kills = p.getScoreboard().getObjective("LabyrinthKills").getScore(target.getName());
                    deaths = p.getScoreboard().getObjective("LabyrinthDeath").getScore(target.getName());
                    bosskills = p.getScoreboard().getObjective("BossKills").getScore(target.getName());
                    display(p, target, balance, rank, wins, games, winrate, kills, deaths, bosskills);
                }
            }else {
                p.sendMessage(ChatColor.RED + "Correct Command Format: /stats [username]");
                return true;
            }
        }
        return false;
    }
    
    private void display(Player self, Player target, Score balance, Score rank, Score wins, Score games, Score winrate, Score kills, Score deaths, Score bosskills) {
        self.sendMessage("\n========================================");
        self.sendMessage(ChatColor.GOLD.toString() + ChatColor.UNDERLINE.toString() + ChatColor.BOLD.toString() + target.getName() + "'s Stats");
        self.sendMessage(ChatColor.AQUA + "\nBalance: " + ChatColor.WHITE + "$" + Integer.toString(balance.getScore()));
        self.sendMessage(ChatColor.LIGHT_PURPLE + "\nRank: " + ChatColor.WHITE + Integer.toString(rank.getScore()));
        self.sendMessage(ChatColor.GREEN + "\nPVP Wins: " + ChatColor.WHITE + Integer.toString(wins.getScore()));
        self.sendMessage(ChatColor.GREEN + "PVP Games: " + ChatColor.WHITE + Integer.toString(games.getScore()));
        self.sendMessage(ChatColor.GREEN + "PVP Winrate: " + ChatColor.WHITE + Integer.toString(winrate.getScore()) + "%");
        self.sendMessage(ChatColor.RED + "\nLabyrinth Kills: " + ChatColor.WHITE + Integer.toString(kills.getScore()));
        self.sendMessage(ChatColor.RED + "Labyrinth Deaths: " + ChatColor.WHITE + Integer.toString(deaths.getScore()));
        self.sendMessage(ChatColor.RED + "Boss Kills: " + ChatColor.WHITE + Integer.toString(bosskills.getScore()));
        self.sendMessage("========================================");
    }
}
