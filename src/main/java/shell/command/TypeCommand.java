package shell.command;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class TypeCommand implements ICommand {
    private static final Set<String> BUILTIN_COMMANDS = new HashSet<>();

    static {
        BUILTIN_COMMANDS.add("echo");
        BUILTIN_COMMANDS.add("exit");
        BUILTIN_COMMANDS.add("type");
        BUILTIN_COMMANDS.add("PWD");
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("type: missing operand");
            return;
        }

        String command = args[1];

        // Check if it's a shell builtin
        if (BUILTIN_COMMANDS.contains(command)) {
            System.out.println(command + " is a shell builtin");
            return;
        }

        // Check PATH environment variable for executables
        String pathEnv = System.getenv("PATH");
        if (pathEnv == null || pathEnv.isEmpty()) {
            System.out.println(command + ": not found");
            return;
        }

        String[] paths = pathEnv.split(":");
        for (String path : paths) {
            File file = new File(path, command);
            if (file.exists() && file.canExecute()) {
                System.out.println(command + " is " + file.getAbsolutePath());
                return;
            }
        }

        // If command is not found in PATH directories
        System.out.println(command + ": not found");
    }
}
