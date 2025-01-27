package shell.command;

import java.io.File;

public class CdCommand implements ICommand {
    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("cd: missing operand");
            return;
        }

        String path = args[1];
        File directory = new File(path);

        if (directory.isAbsolute() && directory.isDirectory()) {
            if (directory.exists()) {
                System.setProperty("user.dir", directory.getAbsolutePath());
            } else {
                System.out.println("cd: " + path + ": No such file or directory");
            }
        } else {
            System.out.println("cd: " + path + ": No such file or directory");
        }
    }
}