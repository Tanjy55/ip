package tank.data.task;

import java.time.LocalDateTime;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone() {
        isDone = true;
    }

    public void setNotDone() {
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return getStatusIcon() + " " + description;
    }

    public static Task fromString(String type, String[] arguments) {
        switch (type) {
        case "Todo":
            Todo addTodo = new Todo(arguments[2]);
            setTaskDoneWhenLoading(addTodo,
                    Boolean.parseBoolean(arguments[1]));
            return addTodo;
        case "Deadline":
            Deadline addDeadline = new Deadline(arguments[2],
                    LocalDateTime.parse(arguments[3]));
            setTaskDoneWhenLoading(addDeadline,
                    Boolean.parseBoolean(arguments[1]));
            return addDeadline;
        case "Event":
            Event addEvent = new Event(arguments[2],
                    LocalDateTime.parse(arguments[3]),
                    LocalDateTime.parse(arguments[4]));
            setTaskDoneWhenLoading(addEvent,
                    Boolean.parseBoolean(arguments[1]));
            return addEvent;
        }
        return null;
    }

    public String toSave() {
        return "Invalid";
    }

    private static void setTaskDoneWhenLoading(Task task, boolean isDone) {
        if (isDone) {
            task.setDone();
        }
    }
}
