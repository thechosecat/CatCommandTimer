package org.catcommandtimer;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, org.bukkit.command.@NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender.hasPermission("CatCommandTimer.use")){
            if (CatCommandTimer.plugin != null) {
                ((CatCommandTimer) CatCommandTimer.plugin).loadPlugin();
                commandSender.sendMessage("已重設所有task並reload config");
            }else {
                commandSender.sendMessage("plugin class錯誤，跟我的人生一樣");
            }

        }
        return true;
    }
}
