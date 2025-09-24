package tank.data.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;
    protected String type = "Event";

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (from: "
                + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + ", to: "
                + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + ")";
    }

    public String toSave() {
        return type
                + " | "
                + isDone
                + " | "
                + super.description
                + " | "
                + from.toString()
                + " | "
                + to.toString();
    }
}
