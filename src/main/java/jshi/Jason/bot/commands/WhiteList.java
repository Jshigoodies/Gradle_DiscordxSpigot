package jshi.Jason.bot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;

public class WhiteList implements Command{
    private String[] arguments;
    private MessageReceivedEvent event;
    private final JavaPlugin plugin;

    public WhiteList(JavaPlugin _plugin) {
        this.plugin = _plugin;
    }

    @Override
    public void execute(CommandContext ctx) {
        arguments = ctx.getArgs();
        event = ctx.getEvent();
        runTask();
    }

    public void runTask() {

        String playerName = arguments[0];

        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Minecraft Player " + playerName + " has been added to the Whitelist!")
                .setColor(Color.GREEN).setTimestamp(java.time.Instant.now());


        Bukkit.getScheduler().runTask(plugin, () -> {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "whitelist add " + playerName);
            event.getChannel().sendMessageEmbeds(embed.build()).queue();
        });
    }
}
