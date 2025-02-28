package jshi.Jason.bot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandContext {
    private final MessageReceivedEvent event;
    private final String[] args;

    public CommandContext(MessageReceivedEvent _event, String[] _args) {
        this.event = _event;
        this.args = _args;
    }

    public MessageReceivedEvent getEvent() {
        return event;
    }

    public String[] getArgs() {
        return  args;
    }
}
