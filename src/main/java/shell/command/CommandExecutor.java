package shell.command;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private final Map<String, ICommand> commandMap;

    public CommandExecutor() {
        commandMap = new HashMap<>();
        commandMap.put("echo", new EchoCommand());
        commandMap.put("exit", new ExitCommand());
        commandMap.put("type", new TypeCommand());
    }

    public void execute(String[] commandParts) {
        if (commandParts.length == 0) {
            System.out.println("No command entered.");
            return;
        }

        ICommand command = commandMap.get(commandParts[0]);
        if (command != null) {
            command.execute(commandParts);
        } else {
            System.out.println(commandParts[0] + ": command not found");
        }
    }
}
