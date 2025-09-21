package tank.data.task;

public class Event extends Task {

    protected String from;
    protected String to;
    protected String type = "Event";

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (from: "
                + from
                + ", to: "
                + to + ")";
    }

    public String toSave() {
        return type + " " + isDone + " " + super.description + " " + from + " " + to;
    }
}
