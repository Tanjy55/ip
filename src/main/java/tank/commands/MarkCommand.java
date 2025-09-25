package tank.commands;

import tank.data.TaskList;
import tank.ui.TextUi;

/**
 * Sets user specified Task as done
 */
public class MarkCommand extends Command {
    int arrayIndex;
    private TextUi ui;

    public MarkCommand(int arrayIndex) {
        this.arrayIndex = arrayIndex;
        this.ui = new TextUi();
    }

    /**
     * Set isDone attribute in Task to True then prints result
     *
     * @param taskList TaskList to mutate
     * @return CommandResult message of result
     */
    @Override
    public CommandResult execute(TaskList taskList) {
        try {
            taskList.setTaskDone(arrayIndex);
            ui.printMessage("Marked this task as done!");
            ui.printTask(taskList.getAllTasks(), arrayIndex);
            return new CommandResult("Mark successful");
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult("Invalid Number given!");
        }
    }
}
