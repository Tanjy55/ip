package tank.commands;

import tank.storage.TankStoreFile;

public class MarkCommand extends Command {
    int arrayIndex;
    TankStoreFile store = new TankStoreFile();

    public MarkCommand(int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    @Override
    public CommandResult execute() {
        try {
            tasklist.setTaskDone(arrayIndex);
            store.save(tasklist.getAllTasks());
            return new CommandResult("Mark successful");
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult("Invalid Number given!");
        }
    }
}
