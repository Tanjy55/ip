import java.util.Scanner;
import java.util.ArrayList;


public class Tank {

    private static final String DOTTED_LINES = "____________________________________________________________\n";
    private static final String INVALID_INPUT = "Hey I didnt understand that, did you mean something else?\n";
    private static final String HELP_SHEET = "Valid inputs are: \n" +
            "1) \"list\", display the current list of tasks\n" +
            "2) \"mark\", mark the specified task as done\n" +
            "3) \"unmark\", mark the specified task as not done\n" +
            "4) \"todo\", add the description afterwards to add this to the list\n" +
            "5) \"deadline\", add \"\\by\" followed by the date to add this to the list\n" +
            "6) \"event\", add \"\\from\" then date and \"\\to\" then date\n";

    static void printDottedLines() {
        System.out.print(DOTTED_LINES);
    }

    static void printInvalidInput() {
        System.out.print(INVALID_INPUT);
    }

    static void printHelp() {
        System.out.print(HELP_SHEET);
    }

    static void printTaskMessage(ArrayList<Task> list, int index) {
        System.out.println("\t" + list.get(index));
    }

    static void printTaskMessage(ArrayList<Task> list, String message, int index) {
        System.out.println("\t" + message);
        System.out.println("\t\t" + list.get(index));
    }

    static void printNumberOfTasks(ArrayList<Task> list) {
        System.out.println(" Now you have " + list.size() + " tasks in the list.");
    }

    static int getArrayIndex(String line) {
        String[] parts = line.split(" ");
        return Integer.parseInt(parts[1]) - 1;
    }

    static boolean checkIndexValidity(ArrayList<Task> list, int index) {
        boolean isValid = index <= list.size();
        if (!isValid) {
            printInvalidInput();
            printDottedLines();
        }
        //return true if index is not valid
        return !isValid;
    }

    static boolean checkCommandValidity(String[] parts) {
        if (parts.length == 1) {
            printInvalidInput();
            printDottedLines();
           return true;
        }
        return false;
    }

    static boolean checkValidDeadline(String line) {
        if (line.isEmpty()) {
            System.out.println("Invalid deadline format, please try again.");
            printDottedLines();
            return true;
        }

        if (!line.contains("/by")) {
            System.out.println("Invalid deadline format, did you forget to include \"/by\"? please try again.");
            printDottedLines();
            return true;
        }

        String[] message = processDeadlineInput(line);
        String description = message[0];
        String byDate = message[1];
        if (description.isEmpty() || byDate.isEmpty()) {
            System.out.println("Invalid deadline format, did you forget to specify the deadline correctly?");
            printDottedLines();
            return true;
        }
        return false;
    }

    static boolean checkValidEvent(String line) {
        if (line.isEmpty()) {
            System.out.println("Invalid event format, please try again.");
            printDottedLines();
            return true;
        }

        if (!line.contains("/from") || !line.contains("/to")) {
            System.out.println("Invalid event format, did you forget to include \"/from\" or \"/to\"? please try again.");
            printDottedLines();
            return true;
        }

        String[] message = processEventInput(line);
        String description = message[0];
        String from = message[1];
        String to = message[2];
        System.out.println(description + from + to);
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            System.out.println("Invalid event format, did you forget to specify the deadline correctly?");
            printDottedLines();
            return true;
        }
        return false;
    }

    static void markTaskDone(ArrayList<Task> list, String line) {
        int arrayIndex = getArrayIndex(line);
        if (checkIndexValidity(list, arrayIndex)) {
            return;
        }
        list.get(arrayIndex).setDone();
        printDottedLines();
        printTaskMessage(list, "Task marked as done! Nice job :)", arrayIndex);
        printDottedLines();
    }

    static void markTaskNotDone(ArrayList<Task> list, String line) {
        int arrayIndex = getArrayIndex(line);
        if (checkIndexValidity(list, arrayIndex)) {
            return;
        }
        list.get(arrayIndex).setNotDone();
        printDottedLines();
        printTaskMessage(list, "Task marked as not done :(", arrayIndex);
        printDottedLines();
    }

    static void displayList(ArrayList<Task> list) {
        int taskCounter = 1;
        System.out.println("\tHere are the tasks in your list:");
        for (Task item : list) {
            System.out.println("\t" + taskCounter + "." + item);
            taskCounter++;
        }
        printDottedLines();
    }

    static void createTodo(ArrayList<Task> list, String line) {
        list.add(new Todo(line.trim()));
        int currentIndex = list.size() - 1;
        printDottedLines();
        System.out.println("Added Todo successfully!");
        printTaskMessage(list, currentIndex);
        printNumberOfTasks(list);
        printDottedLines();
    }

    static String[] processDeadlineInput(String line) {
        String[] message = line.split("/by", 2);
        message[0] = message[0].trim();
        message[1] = message[1].trim();
        return message;
    }

    static String[] processEventInput(String line) {
        String[] returnArray = new  String[3];
        String[] message = line.split("/from", 2);
        returnArray[0] = message[0].trim();

        String[] stringToSplit = message[1].split("/to", 2);
        returnArray[1] = stringToSplit[0].trim();
        returnArray[2] = stringToSplit[1].trim();
        return returnArray;
    }

    static void createDeadline(ArrayList<Task> list, String line) {

        if (checkValidDeadline(line)) {
            return;
        }
        String[] deadlineInput = processDeadlineInput(line);
        list.add(new Deadline(deadlineInput[0], deadlineInput[1]));
        int currentIndex = list.size() - 1;

        printDottedLines();
        System.out.println("Added that. Let's get it boss!");
        printTaskMessage(list, currentIndex);
        printNumberOfTasks(list);
        printDottedLines();
    }

    static void createEvent(ArrayList<Task> list, String line) {

        if (checkValidEvent(line)) {
            return;
        }
        String[] eventInput = processEventInput(line);

        list.add(new Event(eventInput[0], eventInput[1], eventInput[2]));
        int currentIndex = list.size() - 1;

        printDottedLines();
        System.out.println("Event has been added!");
        printTaskMessage(list, currentIndex);
        printNumberOfTasks(list);
        printDottedLines();
    }

    /**
     * Tank greets the user (The name comes from the movie matrix, Tank is the operator over the phone)
     * Awaits keyboard input
     * 1) if command is "list", display the current list of tasks
     * 2) if command is "mark", mark the specified task as done
     * 3) if command is "unmark", mark the specified task as not done
     * Exits once user inputs "bye"
     */

    public static void main(String[] args) {

        String line;
        Scanner in = new Scanner(System.in);
        ArrayList<Task> listOfTasks = new ArrayList<>();

        //greet the user
        printDottedLines();
        System.out.println("Hello! I'm Tank");
        System.out.println("I'm here to help track your tasks, what shall we talk about today?");
        printDottedLines();

        //expect inputs
        do {
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            }

            //format line string for switch case
            String[] parts = line.split("\\s+", 2);
            String switchCommand = parts[0].toLowerCase();



            switch (switchCommand) {
            case "list":
                displayList(listOfTasks);
                continue;

            case "mark":
                markTaskDone(listOfTasks, line);
                continue;

            case "unmark":
                markTaskNotDone(listOfTasks, line);
                continue;

            case "deadline":
                if(checkCommandValidity(parts)) {
                    continue;
                }
                createDeadline(listOfTasks, parts[1]);
                continue;

            case "todo":
                if(checkCommandValidity(parts)) {
                    continue;
                }
                createTodo(listOfTasks, parts[1]);
                continue;

            case "event":
                if(checkCommandValidity(parts)) {
                    continue;
                }
                createEvent(listOfTasks, parts[1]);
                continue;

            default:
                printDottedLines();
                printInvalidInput();
                printHelp();
                printDottedLines();
            }

        } while (true);

        //exit message
        printDottedLines();
        System.out.println("Bye. Hope to see you again soon!");
        printDottedLines();
    }
}