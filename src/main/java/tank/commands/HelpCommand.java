package tank.commands;

import tank.data.TaskList;
import tank.ui.TextUi;

/**
 * Prints help message when user does not give valid command.
 */
public class HelpCommand extends Command {
    private TextUi ui;

    public HelpCommand() {
        this.ui = new TextUi();
    }

    @Override
    public CommandResult execute(TaskList taskList) {
        ui.printHelp();
        return new CommandResult("Displayed help message above");
    }
}
