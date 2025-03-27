import java.io.*;
import java.nio.file.*;
import java.util.regex.*;

public class PhoneNumberValidator {
    public static void validate(String filePath) {
        System.out.println("Running Phone Number Validation");

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Error: File not found - " + file.getAbsolutePath());
            return;
        }

        Pattern pattern = Pattern.compile("^(\\(\\d{3}\\) \\d{3}-\\d{4}|\\d{3}-\\d{3}-\\d{4})$");

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        System.out.println("Phone Number Validation completed.\n");
    }
}