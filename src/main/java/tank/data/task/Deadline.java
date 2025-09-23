package tank.data.task;

public class Deadline extends Task {

    protected String by;
    protected String type = "Deadline";

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: "
                + by
                + ")";
    }

    public String toSave() {
        return type + " | " + isDone + " | " + super.description + " | " + by + " | " + "pad";
    }
}
