package tank.data.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event Task type
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;
    protected String type = "Event";

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Convert attributes of event into sequence of Strings
     *
     * @return String containing type, description, from and to
     */
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

    /**
     * Used in conjunction with save method in TankStoreFile
     * Specific to Event type
     *
     * @return string formatted specifically for storage
     */
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
