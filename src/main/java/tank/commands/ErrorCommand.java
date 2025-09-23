package tank.commands;

import tank.data.TaskList;

public class ErrorCommand extends Command {
    private final String errorMessage;

    public ErrorCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public CommandResult execute(TaskList taskList) {
        return new CommandResult(errorMessage);
    }
}
