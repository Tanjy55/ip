package tank.commands;

import static tank.common.Messages.HELP_SHEET;
import static tank.common.Messages.INVALID_INPUT;


public class HelpCommand extends Command {
    @Override
    public CommandResult execute() {
        return new CommandResult(INVALID_INPUT + "\n" + HELP_SHEET);
    }
}
