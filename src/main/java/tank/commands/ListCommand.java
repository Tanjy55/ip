package tank.commands;

import tank.ui.TextUi;

public class ListCommand extends Command{
    private final TextUi ui = new TextUi();

    @Override
    public CommandResult execute() {
        ui.displayList(tasklist.getAllTasks());
        return new CommandResult("Displayed list successfully");
    }
}
