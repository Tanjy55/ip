package tank.data.task;

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
                return new Todo(arguments[2]);
            case "Deadline":
                return new Deadline(arguments[2], arguments[3]);
            case "Event":
                return new Event(arguments[2], arguments[3], arguments[4]);
        }
        return null;
    }

    public String toSave() {
        return "Invalid";
    }
}
