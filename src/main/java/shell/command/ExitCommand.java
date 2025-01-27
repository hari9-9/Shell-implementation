package shell.command;

public class ExitCommand implements ICommand {
    @Override
    public void execute(String[] args) {
        System.exit(0);
    }
}
