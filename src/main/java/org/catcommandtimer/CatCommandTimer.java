package org.catcommandtimer;

import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.ArrayList;
import java.util.List;

public final class CatCommandTimer extends JavaPlugin {
    public static List<Integer> tasks = new ArrayList<>();
    public static Configuration configuration;
    public static Plugin plugin;
    @Override
    public void onEnable() {
        Bukkit.getLogger().info("[CatCommandTimer] Starting (made in 2024/11/7)");
        Bukkit.getLogger().info("[CatCommandTimer] 生存動力已完全遺失，資料高機率崩潰並rebuild");
        plugin = this;
        FileConfiguration config = this.getConfig();
        config.options().copyDefaults(true);
        saveDefaultConfig();
        configuration = config;
        loadPlugin();
        getCommand("CatCommandTimer_reload").setExecutor(new Command());

    }
    public void loadPlugin(){
        reloadConfig();
        configuration = this.getConfig();
        for (Integer task : tasks){
            Bukkit.getScheduler().cancelTask(task);
        }
        List<String> commands = configuration.getStringList("commands");
        for (String command : commands) {
            try {
                String cmd = command.split(" ; ")[0];
                int ticks = Integer.parseInt(command.split(" ; ")[1]);
                Bukkit.getLogger().info("[CatCommandTimer] 嘗試註冊指令:" + cmd + " 間隔:" + ticks +" 秒");

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
                        tasks.add(getTaskId());
                    }
                }.runTaskTimer(this,0L,ticks* 20L);

            }catch (Exception e){
                e.printStackTrace();
                Bukkit.getLogger().info("[CatCommandTimer] config錯誤，請檢查。就跟我的人生一樣都是坨錯誤");
            }
        }
    }
    @Override
    public void onDisable() {
        Bukkit.getLogger().info("[CatCommandTimer] 已結束人生，我不想留有遺憾。所以我帶著遺憾離開了這個世界");
    }
}
// 隨便 不幹了 反正我死了也沒人會發現