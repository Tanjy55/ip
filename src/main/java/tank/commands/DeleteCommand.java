package tank.commands;

import tank.data.exception.TankCommandInvalidException;
import tank.data.task.Task;
import tank.data.TaskList;
import tank.ui.TextUi;

import java.util.ArrayList;

/**
 * Removes a Task to TaskList
 */
public class DeleteCommand extends Command {
    private int arrayIndex;
    private TextUi ui;

    public DeleteCommand(int arrayIndex) {
        this.arrayIndex = arrayIndex;
        this.ui = new TextUi();
    }

    /**
     * Removes a task and prints result using ui.
     * If unsuccessful, return CommandResult with error message
     *
     * @param taskList TaskList to delete from
     * @return CommandResult with message
     */
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

    /**
     * Fetch index validity of Task index to be deleted
     *
     * @param list  TaskList to delete from
     * @param index Index of Task to delete
     * @throws TankCommandInvalidException thrown if index not valid
     */
    static void checkIndexValidity(ArrayList<Task> list, int index)
            throws TankCommandInvalidException {

        boolean isValid = index <= list.size() - 1;
        if (!isValid) {
            throw new TankCommandInvalidException(
                    "Incorrect specified number in command");
        }
    }
}
