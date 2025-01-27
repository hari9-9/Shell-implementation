package shell.command;

public class ExitCommand implements ICommand {
    @Override
    public void execute(String[] args) {
        System.out.println("Exiting...");
        System.exit(0);
    }
}
