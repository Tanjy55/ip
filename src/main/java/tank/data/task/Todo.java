package tank.data.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    protected String type = "Todo";

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toSave() {
        return type + " " + isDone + " " + super.description + " " + "pad" + " " + "pad";
    }
}
