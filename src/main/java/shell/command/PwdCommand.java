package shell.command;

public class PwdCommand implements ICommand {
    @Override
    public void execute(String[] args) {
        System.out.println(System.getProperty("user.dir"));
    }
}
