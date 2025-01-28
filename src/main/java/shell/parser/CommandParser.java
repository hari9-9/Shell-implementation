package shell.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The CommandParser class provides a utility method to parse a command-line input string
 * into an array of arguments, respecting the rules for quoted and unquoted tokens.
 */
public class CommandParser {

    /**
     * Parses the given input string into an array of command arguments.
     *
     * @param input The raw input string from the user, which may include quoted or unquoted text.
     * @return A String array where each element is a parsed token (argument).
     */
    public static String[] parse(String input) {
        // Regular expression pattern to match:
        // 1. Text enclosed in single quotes: '([^']*)'
        // 2. Text enclosed in double quotes: "((?:[^\"\\\\]|\\\\.)*)"
        //    - Captures everything inside double quotes, handling escaped characters.
        // 3. Unquoted text: \\S+
        //    - Matches sequences of non-whitespace characters.
        Pattern pattern = Pattern.compile("'([^']*)'|\"((?:[^\"\\\\]|\\\\.)*)\"|\\S+");

        Matcher matcher = pattern.matcher(input);
        List<String> tokens = new ArrayList<>();

        while (matcher.find()) {
            if (matcher.group(1) != null) {
                // Single quotes: Take the content as-is.
                tokens.add(matcher.group(1));
            } else if (matcher.group(2) != null) {
                // Double quotes: Handle escape sequences inside the quoted text.
                tokens.add(unescapeDoubleQuotes(matcher.group(2)));
            } else {
                // Unquoted text.
                tokens.add(matcher.group());
            }
        }

        return tokens.toArray(new String[0]);
    }

    /**
     * Unescapes special characters in double-quoted strings.
     *
     * @param input The raw content inside double quotes.
     * @return The unescaped content.
     */
    private static String unescapeDoubleQuotes(String input) {
        return input.replace("\\\\", "\\")  // Replace \\ with \
                .replace("\\\"", "\"")  // Replace \" with "
                .replace("\\$", "$")    // Replace \$ with $
                .replace("\\n", "\n");  // Replace \n with a newline character
    }
}
