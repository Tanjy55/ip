package tank.commands;

import tank.data.exception.TankCommandInvalidException;
import tank.data.task.Task;
import tank.storage.TankStoreFile;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    int arrayIndex;
    TankStoreFile store = new TankStoreFile();

    public DeleteCommand(int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    @Override
    public CommandResult execute() {
        try {
            tasklist.removeTask(arrayIndex);
            store.save(tasklist.getAllTasks());
            return new CommandResult("Delete successful");
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult("Invalid Index");
        }
    }

    static void checkIndexValidity(ArrayList<Task> list, int index) throws TankCommandInvalidException {
        boolean isValid = index <= list.size() - 1;
        if (!isValid) {
            throw new TankCommandInvalidException("Incorrect specified number in command");
        }
    }
}
