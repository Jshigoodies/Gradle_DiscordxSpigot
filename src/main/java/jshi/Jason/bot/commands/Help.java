package jshi.Jason.bot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.Map;

public class Help implements Command{
    Map<String, Command> list;
    public Help(Map<String, Command> _list) {
        this.list = _list;
    }

    @Override
    public void execute(CommandContext ctx) {
        MessageReceivedEvent event = ctx.getEvent();


        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Available Commands")
                .setColor(Color.BLUE);

        for(String command : list.keySet()) {
            embed.addField(command, "-----", false);
        }

        event.getChannel().sendMessageEmbeds(embed.build()).queue();
    }
}
