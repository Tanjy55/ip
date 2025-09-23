package tank.commands;

import tank.data.TaskList;

public class ExitCommand extends Command {
    @Override
    public CommandResult execute(TaskList taskList) {
        return new CommandResult("Exiting Tank");
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand; // instanceof returns false if it is null
    }
}
