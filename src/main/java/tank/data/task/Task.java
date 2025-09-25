package tank.data.task;

import java.time.LocalDateTime;

/**
 * Parent class of Todo, Deadline, Event
 */
public class Task {
    protected String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone() {
        isDone = true;
    }

    public void setNotDone() {
        isDone = false;
    }

    /**
     * Checks done status and return a string
     *
     * @return a string that represents the state of Task, if finished or not
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return getStatusIcon() + " " + description;
    }

    /**
     * Used in conjunction with load method in TankStoreFile
     * Convert String in .txt to Todo, Deadline or Event
     *
     * @param type      represents the type of Task to be created
     * @param arguments attributes of Task type
     * @return new instance of Task type
     */
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

    /**
     * To be used in conjunction with load method in TankStoreFile
     * Set the boolean isDone based on setting from .txt
     *
     * @param task   reference to Task object
     * @param isDone done status from storage
     */
    private static void setTaskDoneWhenLoading(Task task, boolean isDone) {
        if (isDone) {
            task.setDone();
        }
    }

    /**
     * To be overridden by subclasses
     * Used in conjunction with save method in TankStoreFile
     *
     * @return invalid as method in task is not intended to be called
     */
    public String toSave() {
        return "Invalid";
    }
}
