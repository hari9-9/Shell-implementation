package shell.command;

import java.util.HashSet;
import java.util.Set;

public class TypeCommand implements ICommand {
    private static final Set<String> BUILTIN_COMMANDS = new HashSet<>();

    static {
        BUILTIN_COMMANDS.add("echo");
        BUILTIN_COMMANDS.add("exit");
        BUILTIN_COMMANDS.add("type");
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            String command = args[1];
            if (BUILTIN_COMMANDS.contains(command)) {
                System.out.println(command + " is a shell builtin");
            } else {
                System.out.println(command + ": not found");
            }
        } else {
            System.out.println("type: missing operand");
        }
    }
}
