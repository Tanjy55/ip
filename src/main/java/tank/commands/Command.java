package tank.commands;

import tank.data.TaskList;

/**
 * All commands are based on this parent class
 */
public abstract class Command {
    public abstract CommandResult execute(TaskList taskList);
}
