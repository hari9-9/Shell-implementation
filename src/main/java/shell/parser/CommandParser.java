package shell.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The CommandParser class provides a utility method to parse a command-line input string
 * into an array of arguments, respecting the rules for quoted and unquoted tokens.
 *
 * This parser supports:
 * - Single-quoted strings: Treats the content inside single quotes as a single token,
 *   preserving spaces and special characters literally.
 * - Unquoted tokens: Splits tokens by whitespace.
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
        //    - Captures everything inside single quotes as a group (Group 1).
        // 2. Unquoted text: \\S+
        //    - Matches sequences of non-whitespace characters.
        Pattern pattern = Pattern.compile("'([^']*)'|\\S+");

        // Create a matcher to apply the pattern on the input string.
        Matcher matcher = pattern.matcher(input);

        // List to store the parsed tokens (arguments) from the input string.
        List<String> tokens = new ArrayList<>();

        // Loop through all matches in the input string.
        while (matcher.find()) {
            // If Group 1 (text inside single quotes) is not null, add it as a token.
            if (matcher.group(1) != null) {
                tokens.add(matcher.group(1)); // Add the content inside single quotes.
            } else {
                // If Group 1 is null, add the full match (unquoted text) as a token.
                tokens.add(matcher.group()); // Add the unquoted token.
            }
        }

        // Convert the List of tokens into a String array and return it.
        // Passing `new String[0]` ensures type safety and allows the method to dynamically
        // create an appropriately sized array to hold all tokens.
        return tokens.toArray(new String[0]);
    }
}
