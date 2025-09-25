package tank.commands;

import tank.data.TaskList;
import tank.ui.TextUi;

/**
 * Sets user specified Task as not done
 */
public class UnmarkCommand extends Command {
    int arrayIndex;
    private TextUi ui;


    public UnmarkCommand(int arrayIndex) {
        this.arrayIndex = arrayIndex;
        this.ui = new TextUi();
    }

    /**
     * Set isDone attribute in Task to False then prints result
     *
     * @param taskList TaskList to mutate
     * @return CommandResult message of result
     */
    @Override
    public CommandResult execute(TaskList taskList) {
        try {
            taskList.setTaskNotDone(arrayIndex);
            ui.printMessage("Marked this task as not done!");
            ui.printTask(taskList.getAllTasks(), arrayIndex);
            return new CommandResult("Unmark successful");
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult("Invalid Index");
        }
    }
}
