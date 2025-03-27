package jshi.Jason.bot;

import jshi.Jason.bot.commands.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.awt.*;

public class Bot extends ListenerAdapter implements Listener {
    private JDA jda;
    private final String prefix = "~";
    private final CommandManager commandManager = new CommandManager();
    private final JavaPlugin plugin;

    private final String TARGET_CHANNEL_ID = "1344600035014348800"; //main channel

    public Bot(String _token, JavaPlugin _plugin) throws LoginException {

        //Intialize
        jda = JDABuilder.createDefault(_token)
                .enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT)
                .setActivity(Activity.playing("CraftMine"))
                .addEventListeners(this)
                .build();
        this.plugin = _plugin;

        registerCommands();

    }
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[DiscordXSpigot] BOT IS READY");
    }

    public void registerCommands() {
        //Register commands
        commandManager.registerCommand("hello", new HelloCommand());
        commandManager.registerCommand("whitelist", new WhiteList(plugin));


        //put this one last <------ Help command to get the list
        commandManager.registerCommand("help", new Help(commandManager.getMapCommands()));
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        if(event.getAuthor().isBot()) {
            return;
        }

        String message = event.getMessage().getContentRaw();
        String author = event.getAuthor().getName();


        if(message.startsWith(prefix)) {
            String[] parts = message.substring(prefix.length()).split("\\s+");
            String commandName = parts[0];
            String[] args = new String[parts.length - 1];
            System.arraycopy(parts, 1, args, 0, args.length);
            CommandContext ctx = new CommandContext(event, args);
            commandManager.executeCommand(commandName, ctx);

        }
        else if(event.getChannel().getId().equals(TARGET_CHANNEL_ID)){
            //send it to the minecraft server
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[" + author + "]: " + ChatColor.WHITE + message);
            }
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String playerName = event.getPlayer().getName();
        String message = event.getMessage();
        TextChannel channel = jda.getTextChannelById(TARGET_CHANNEL_ID);


        EmbedBuilder embed = new EmbedBuilder().setColor(Color.BLACK).setTitle("[" + playerName + "]: " + message);


        assert channel != null;
        channel.sendMessageEmbeds(embed.build()).queue();

    }
}
