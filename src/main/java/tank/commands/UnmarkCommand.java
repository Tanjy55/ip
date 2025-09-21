package tank.commands;

import tank.storage.TankStoreFile;

public class UnmarkCommand extends Command{
    int arrayIndex;
    TankStoreFile store = new TankStoreFile();

    public UnmarkCommand(int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    @Override
    public CommandResult execute() {
        try {
            tasklist.setTaskNotDone(arrayIndex);
            store.save(tasklist.getAllTasks());
            return new CommandResult("Unmark successful");
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult("Invalid Index");
        }
    }
}
