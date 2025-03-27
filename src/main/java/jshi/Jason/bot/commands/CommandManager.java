package jshi.Jason.bot.commands;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private final Map<String, Command> commands = new HashMap<>();

    public void registerCommand(String name, Command command) {
        commands.put(name.toLowerCase(), command);
    }

    public void executeCommand(String commandName, CommandContext ctx) {
        Command command = commands.get(commandName.toLowerCase());
        if(command != null) {
            command.execute(ctx);
        }
        else {
            ctx.getEvent().getChannel().sendMessage("Unknown command!").queue();
        }
    }

    public Map<String, Command> getMapCommands() {
        return commands;
    }
}
