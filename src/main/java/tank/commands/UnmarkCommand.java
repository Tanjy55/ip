package tank.commands;

import tank.data.TaskList;

/**
 * Sets user specified Task as not done
 */
public class UnmarkCommand extends Command {
    int arrayIndex;

    public UnmarkCommand(int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    /**
     * Set isDone attribute in Task to False
     *
     * @param taskList TaskList to mutate
     * @return CommandResult message of result
     */
    @Override
    public CommandResult execute(TaskList taskList) {
        try {
            taskList.setTaskNotDone(arrayIndex);
            return new CommandResult("Unmark successful");
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult("Invalid Index");
        }
    }
}
