package tank.commands;

import tank.data.TaskList;
import tank.data.task.Task;
import tank.ui.TextUi;

public class AddCommand extends Command {
    private final Task toAdd;
    private TextUi ui;

    public AddCommand(Task toAdd) {
        this.toAdd = toAdd;
        this.ui = new TextUi();
    }

    @Override
    public CommandResult execute(TaskList taskList) {
        taskList.addTask(toAdd);
        ui.printMessage("Added this task: ");
        ui.printTask(taskList.getAllTasks(), taskList.getLatestIndex());
        ui.printNumberOfTasks(taskList.getAllTasks());
        return new CommandResult("Add complete");
    }
}
