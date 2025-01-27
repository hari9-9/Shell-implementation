package shell.parser;

public class CommandParser {
    public static String[] parse(String input) {
        return input.trim().split("\\s+");
    }
}
