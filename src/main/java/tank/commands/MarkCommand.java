package tank.commands;

import tank.data.TaskList;

public class MarkCommand extends Command {
    int arrayIndex;

    public MarkCommand(int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    @Override
    public CommandResult execute(TaskList taskList) {
        try {
            taskList.setTaskDone(arrayIndex);
            return new CommandResult("Mark successful");
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult("Invalid Number given!");
        }
    }
}
