package tank.commands;

import tank.data.TaskList;

import static tank.common.Messages.HELP_SHEET;
import static tank.common.Messages.INVALID_INPUT;


public class HelpCommand extends Command {
    @Override
    public CommandResult execute(TaskList taskList) {
        return new CommandResult(INVALID_INPUT + "\n" + HELP_SHEET);
    }
}
