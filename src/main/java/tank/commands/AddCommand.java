package tank.commands;

import tank.data.TaskList;
import tank.data.task.Task;

public class AddCommand extends Command {
    private final Task toAdd;

    public AddCommand(Task toAdd) {
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute(TaskList taskList) {
        taskList.addTask(toAdd);
        return new CommandResult("Added Task successfully!");
    }
}
