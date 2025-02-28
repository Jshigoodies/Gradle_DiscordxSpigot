package jshi.Jason;

import jshi.Jason.bot.Bot;
import jshi.Jason.spigotcommands.Fk;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

public class Main extends JavaPlugin {
    private Bot bot;
    private final String token = "";
    private TextChannel mainChannel;
    @Override
    public void onEnable() {
        this.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[DiscordXSpigot] Started");
        startDiscordBot();

        //spigot commands
        this.getCommand("Fk").setExecutor(new Fk());
    }

    public void eventMethod() {
        this.getServer().getPluginManager().registerEvents(bot, this);
    }

    @Override
    public void onDisable() {
        this.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[DiscordXSpigot] Stopped");
    }

    private void startDiscordBot() {
        try {
            bot = new Bot(token, this);
            for(int i = 3; i > 0; i--) {
                this.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[DiscordXSpigot] " + i);
            }
            this.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[DiscordXSpigot] Bot Ready");
            //spigot events
            eventMethod();
        }
        catch(LoginException e) {
            e.printStackTrace();
        }
    }
}
