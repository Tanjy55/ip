package tank.commands;

import tank.data.TaskList;
import tank.ui.TextUi;

public class ListCommand extends Command{
    private final TextUi ui = new TextUi();

    @Override
    public CommandResult execute(TaskList taskList) {
        ui.displayList(taskList.getAllTasks());
        return new CommandResult("Displayed list successfully");
    }
}
