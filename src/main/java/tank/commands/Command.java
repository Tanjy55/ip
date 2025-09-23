package tank.commands;

import tank.data.TaskList;

public abstract class Command {
    public abstract CommandResult execute(TaskList taskList);
}
