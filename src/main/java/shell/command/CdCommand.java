package shell.command;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CdCommand implements ICommand {
    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("cd: missing operand");
            return;
        }

        String inputPath = args[1];
        Path newPath;

        if (inputPath.startsWith("/")) {
            // Handle absolute path
            newPath = Paths.get(inputPath);
        } else {
            // Handle relative path
            newPath = Paths.get(System.getProperty("user.dir")).resolve(inputPath).normalize();
        }

        File directory = newPath.toFile();

        if (directory.exists() && directory.isDirectory()) {
            System.setProperty("user.dir", directory.getAbsolutePath());
        } else {
            System.out.println("cd: " + inputPath + ": No such file or directory");
        }
    }
}