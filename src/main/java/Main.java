public class Main {
    public static void main(String[] args) {
        System.out.println("Starting all tasks...\n");

        PhoneNumberValidator.validate("src/resources/file.txt");
        UserJsonSerializer.serializeUsers("src/resources/file1.txt", "src/resources/users.json");
        WordFrequencyCounter.processWordFrequencies("src/resources/words.txt", "src/resources/word_counts.ser");

        System.out.println("\nAll tasks completed.");
    }
}