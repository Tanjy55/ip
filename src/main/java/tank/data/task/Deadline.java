package tank.data.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;
    protected String type = "Deadline";

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + ")";
    }

    public String toSave() {
        return type
                + " | "
                + isDone
                + " | "
                + super.description
                + " | "
                + by.toString()
                + " | "
                + "pad";
    }
}
