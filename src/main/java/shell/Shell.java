package shell;

import shell.command.CommandExecutor;
import shell.parser.CommandParser;

import java.util.Scanner;

public class Shell {
    private final CommandExecutor commandExecutor;
    private final Scanner scanner;

    public Shell() {
        this.commandExecutor = new CommandExecutor();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.print("$ ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit 0")) {
                System.out.println("Exiting shell...");
                break;
            }
            String[] commandParts = CommandParser.parse(input);
            commandExecutor.execute(commandParts);
        }
    }
}
