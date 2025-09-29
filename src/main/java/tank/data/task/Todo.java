package tank.data.task;

/**
 * Todo Task type
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    protected String type = "Todo";

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Processes Tdo attributes into format for .txt storage
     *
     * @return a string for .txt storage
     */
    public String toSave() {
        return type
                + " | "
                + isDone
                + " | "
                + super.description
                + " | "
                + "pad"
                + " | "
                + "pad";
    }
}
