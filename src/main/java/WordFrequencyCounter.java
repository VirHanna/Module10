import java.io.*;
import java.nio.file.*;
import java.util.*;

public class WordFrequencyCounter {
    public static void processWordFrequencies(String inputFilePath, String serializedFilePath) {
        System.out.println("Running Word Frequency Counting");

        Map<String, Integer> wordCountMap = countWords(inputFilePath);
        serializeWordCounts(wordCountMap, serializedFilePath);

        Map<String, Integer> deserializedMap = deserializeWordCounts(serializedFilePath);
        if (deserializedMap != null) {
            printWordFrequencies(deserializedMap);
        }

        System.out.println("Word Frequency Counting completed.\n");
    }

    private static Map<String, Integer> countWords(String filePath) {
        Map<String, Integer> wordCountMap = new HashMap<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.trim().split("\\s+");
                for (String word : words) {
                    word = word.toLowerCase();
                    wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return wordCountMap;
    }

    private static void serializeWordCounts(Map<String, Integer> wordCountMap, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(wordCountMap);
            System.out.println("Word frequencies have been serialized to " + filePath);
        } catch (IOException e) {
            System.out.println("Error serializing data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Integer> deserializeWordCounts(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Map<String, Integer>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error deserializing data: " + e.getMessage());
            return new HashMap<>();
        }
    }

    private static void printWordFrequencies(Map<String, Integer> wordCountMap) {
        wordCountMap.forEach((word, count) -> System.out.println(word + " " + count));
    }
}
