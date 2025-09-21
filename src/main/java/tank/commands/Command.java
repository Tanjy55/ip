package tank.commands;

import tank.data.TaskList;

public class Command {
    protected TaskList tasklist;

    protected Command() {}

    public CommandResult execute() {
        return new CommandResult("Execute method is to be implemented by subclasses");
    }

    public void setData(TaskList list) {
        this.tasklist = list;
    }
}
