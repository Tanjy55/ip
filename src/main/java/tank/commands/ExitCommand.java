package tank.commands;

import tank.data.TaskList;

/**
 * Terminates the program
 */
public class ExitCommand extends Command {
    @Override
    public CommandResult execute(TaskList taskList) {
        return new CommandResult("Exiting Tank");
    }

    /**
     * Used by runCommandLoopUntilExitCommand as loop exit condition
     *
     * @param command reference to latest command returned by parser
     * @return true if latest command is ExitCommand
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
