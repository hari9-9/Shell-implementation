package shell.command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private final Map<String, ICommand> commandMap;

    public CommandExecutor() {
        commandMap = new HashMap<>();
        commandMap.put("echo", new EchoCommand());
        commandMap.put("exit", new ExitCommand());
        commandMap.put("type", new TypeCommand());
        commandMap.put("pwd", new PwdCommand());
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
            executeExternalCommand(commandParts);
        }
    }

    private void executeExternalCommand(String[] commandParts) {
        String command = commandParts[0];

        // Find the full path of the executable in the PATH environment variable
        String executablePath = findExecutableInPath(command);

        if (executablePath != null) {
            try {
                ProcessBuilder processBuilder = new ProcessBuilder(commandParts);
                processBuilder.inheritIO(); // Pass IO to console for output display
                Process process = processBuilder.start();
                process.waitFor();
            } catch (IOException | InterruptedException e) {
                System.out.println(command + ": error executing command");
                Thread.currentThread().interrupt();
            }
        } else {
            System.out.println(command + ": command not found");
        }
    }
    private String findExecutableInPath(String command) {
        String pathEnv = System.getenv("PATH");
        if (pathEnv == null || pathEnv.isEmpty()) {
            return null;
        }

        for (String dir : pathEnv.split(":")) {
            Path fullPath = Path.of(dir, command);
            if (Files.isRegularFile(fullPath) && Files.isExecutable(fullPath)) {
                return fullPath.toString();
            }
        }
        return null;
    }
}
