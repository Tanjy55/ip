package tank.commands;

import tank.data.task.Task;
import tank.storage.TankStoreFile;

public class AddCommand extends Command {
    private final Task toAdd;
    TankStoreFile store = new TankStoreFile();

    public AddCommand(Task toAdd) {
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute() {
        tasklist.addTask(toAdd);
        store.save(tasklist.getAllTasks());
        return new CommandResult("Added Task successfully!");
    }
}
