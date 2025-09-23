package tank.commands;

import tank.data.TaskList;

public class UnmarkCommand extends Command{
    int arrayIndex;

    public UnmarkCommand(int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

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
