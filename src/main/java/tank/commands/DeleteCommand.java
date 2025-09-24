package tank.commands;

import tank.data.exception.TankArgumentMissingException;
import tank.data.exception.TankCommandInvalidException;
import tank.data.task.Task;
import tank.data.TaskList;
import tank.ui.TextUi;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    private int arrayIndex;
    private TextUi ui;

    public DeleteCommand(int arrayIndex) {
        this.arrayIndex = arrayIndex;
        this.ui = new TextUi();
    }

    @Override
    public CommandResult execute(TaskList taskList) {
        try {
            checkIndexValidity(taskList.getAllTasks(), arrayIndex);
            ui.printMessage("Removing this task... ");
            ui.printTask(taskList.getAllTasks(), arrayIndex);
            taskList.removeTask(arrayIndex);
            ui.printNumberOfTasks(taskList.getAllTasks());
            return new CommandResult("Delete successful");
        } catch (TankCommandInvalidException e) {
            return new CommandResult("Incorrect specified number in command");
        }
    }

    static void checkIndexValidity(ArrayList<Task> list, int index) throws TankCommandInvalidException {
        boolean isValid = index <= list.size() - 1;
        if (!isValid) {
            throw new TankCommandInvalidException("Incorrect specified number in command");
        }
    }
}
