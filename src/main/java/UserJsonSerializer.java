import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class UserJsonSerializer {
    public static void serializeUsers(String inputFilePath, String outputFilePath) {
        System.out.println("Running User JSON Serialization");

        List<User> users = readUsers(inputFilePath);
        writeUsersToJson(users, outputFilePath);

        System.out.println("User JSON Serialization completed.\n");
    }

    private static List<User> readUsers(String filePath) {
        List<User> users = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            br.readLine(); // Пропускаємо заголовок

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+");
                if (parts.length == 2) {
                    users.add(new User(parts[0], Integer.parseInt(parts[1])));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + filePath);
        }

        return users;
    }

    private static void writeUsersToJson(List<User> users, String filePath) {
        try (Writer writer = Files.newBufferedWriter(Paths.get(filePath))) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(users, writer);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filePath);
        }
    }
}