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
        //    - Captures everything inside single quotes as a group (Group 1).
        // 2. Unquoted text: \\S+
        //    - Matches sequences of non-whitespace characters.
        // 3. Allow seamless concatenation of quoted and unquoted parts.
        Pattern pattern = Pattern.compile("'([^']*)'|\\S+");

        // Create a matcher to apply the pattern on the input string.
        Matcher matcher = pattern.matcher(input);

        // List to store the parsed tokens (arguments) from the input string.
        List<String> tokens = new ArrayList<>();

        // Temporary variable to handle concatenation of adjacent tokens.
        StringBuilder currentToken = new StringBuilder();

        // Loop through all matches in the input string.
        while (matcher.find()) {
            if (matcher.group(1) != null) {
                // Group 1: Text inside single quotes
                currentToken.append(matcher.group(1)); // Append quoted content to the current token
            } else {
                // Group 0: Unquoted text
                currentToken.append(matcher.group()); // Append unquoted content to the current token
            }

            // If the next match is not adjacent, finalize the current token.
            if (matcher.hitEnd() || input.charAt(matcher.end()) == ' ') {
                tokens.add(currentToken.toString()); // Add the token to the list
                currentToken.setLength(0); // Reset the builder for the next token
            }
        }

        // Convert the List of tokens into a String array and return it.
        return tokens.toArray(new String[0]);
    }
}
