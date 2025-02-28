package jshi.Jason.bot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class HelloCommand implements Command{
    @Override
    public void execute(CommandContext ctx) {
        MessageReceivedEvent event = ctx.getEvent();
        event.getChannel().sendMessage("Hello").queue();
    }
}
