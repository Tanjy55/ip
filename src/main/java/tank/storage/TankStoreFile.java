package tank.storage;

import tank.data.task.Task;
import tank.ui.TextUi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import tank.data.task.Task;

public class TankStoreFile {
    public final String storagePath = "./data/storage.txt";

    TextUi ui = new TextUi();

    public void save(ArrayList<Task> list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(storagePath))) {
            for (Task t : list) {
                writer.write(t.toSave());   // Write the line
                writer.newLine();      // Add a newline after each line
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving. Check if file path is invalid or file deleted.");
        }
    }

    public void load(ArrayList<Task> list) {
        try (BufferedReader reader = new BufferedReader(new FileReader(storagePath))) {
            String readInput;
            while ((readInput = reader.readLine()) != null) {
                String[] parts = readInput.split(" ", 4);
                list.add(Task.fromString(parts[0], parts));
            }
        } catch (Exception e) {
            ui.printInvalidInput();
        }
    }
}
