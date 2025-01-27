package shell.command;

public class EchoCommand implements ICommand {
    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            System.out.println(String.join(" ", args).substring(5));
        } else {
            System.out.println();
        }
    }
}
