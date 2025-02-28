package jshi.Jason.spigotcommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Fk implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if(label.equalsIgnoreCase("fk")) {
            if(!(sender instanceof Player)) {
                sender.sendMessage("console say fk");
                return true;
            }


            Player player = (Player) sender;
            player.sendMessage(ChatColor.BLUE + "FK!");
            return true;
        }



        return false;
    }
}
