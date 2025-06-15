package Core.Utilities;

public class StringUtilities {
    // Rewritten from assignment 1
    // Converts a string to title case, where the first letter of each word is capitalized
    public static String toTitleCase(String input) {
        String[] words = input.split(" ");
        StringBuilder titleCase = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                titleCase.append(Character.toUpperCase(word.charAt(0)))
                         .append(word.substring(1).toLowerCase())
                         .append(" ");
            }
        }

        return titleCase.toString().trim();
    }
}
