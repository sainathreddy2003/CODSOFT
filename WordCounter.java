import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordCounter {
    public static void main(String[] args) {
        // Step 1: Prompt user for input text or file
        System.out.print("Enter text or provide a file path: ");
        String input = getUserInput();

        // Step 2: Read input text or file
        String text = readInputText(input);

        // Step 3: Split the string into an array of words
        String[] words = text.split("[\\s.,;:!?()]+");

        // Step 4: Initialize counter variable
        int wordCount = 0;

        // Step 7: Ignore common words or stop words
        Set<String> stopWords = new HashSet<>(Arrays.asList("the", "and", "is", "in", "of", "to", "a", "with", "for"));
        Map<String, Integer> wordFrequency = new HashMap<>();

        // Step 5: Iterate through the array of words and increment the counter
        for (String word : words) {
            // Step 7: Ignore common words
            if (!stopWords.contains(word.toLowerCase())) {
                wordCount++;

                // Step 8: Provide statistics - word frequency
                wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            }
        }

        // Step 6: Display the total count of words to the user
        System.out.println("\nTotal number of words: " + wordCount);

        // Step 8: Provide statistics - number of unique words and word frequency
        System.out.println("Number of unique words: " + wordFrequency.size());
        System.out.println("Word frequency:");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static String getUserInput() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        } catch (IOException e) {
            System.err.println("Error reading user input: " + e.getMessage());
            return "";
        }
    }

    private static String readInputText(String input) {
        // If the input is a file path, read the file; otherwise, use the provided text.
        if (input.endsWith(".txt")) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                return sb.toString();
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
                System.exit(1);
            }
        }
        return input;
    }
}
