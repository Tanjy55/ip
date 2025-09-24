package tank.common;

public class Messages {
    public static final String SAVE_LOCATION = "./data/tank.txt";
    public static final String DOTTED_LINES = "____________________________________________________________\n";
    public static final String INVALID_INPUT = "Hey I didnt understand that, did you mean something else?\n";
    public static final String HELP_SHEET = """
            Valid inputs are:\s
            1) "list", display the current list of tasks
            2) "mark", mark the specified task as done
            3) "unmark", mark the specified task as not done
            4) "delete", delete the specified task
            5) "todo", add the description afterwards to add this to the list
            6) "deadline", add "\\by" followed by the date to add this to the list
            7) "event", add "\\from" then date and "\\to" then date
            8) "find", then type in the keyword to search
            9) "bye" to finish the chat
            """;
}
