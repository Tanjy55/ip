package tank.storage;

import tank.data.task.Task;
import tank.ui.TextUi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class TankStoreFile {
    public Path storagePath;

    TextUi ui = new TextUi();

    public TankStoreFile() {
        try {
            // Directory where the JAR lives
            Path jarDir = Paths.get(
                    TankStoreFile.class.getProtectionDomain()
                            .getCodeSource().getLocation().toURI()
            ).getParent();

            // storage.txt will be in the same folder as the JAR
            this.storagePath = jarDir.resolve("storage.txt");
        } catch (Exception e) {
            throw new RuntimeException("Cannot resolve storage path", e);
        }
    }

    public void save(ArrayList<Task> list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(storagePath.toString()))) {
            for (Task t : list) {
                writer.write(t.toSave());   // Write the line
                writer.newLine();      // Add a newline after each line
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving. Check if file path is invalid or file deleted.");
        }
    }

    public void load(ArrayList<Task> list) {
        try (BufferedReader reader = new BufferedReader(new FileReader(storagePath.toString()))) {
            String readInput;
            while ((readInput = reader.readLine()) != null) {
                String[] parts = readInput.split("\\s*\\|\\s*", 5);
                list.add(Task.fromString(parts[0], parts));
            }
        } catch (Exception e) {
            ui.printInvalidInput();
        }
    }
}
