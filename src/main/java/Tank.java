import java.util.Scanner;
import java.util.ArrayList;


public class Tank {

    private static final String DOTTED_LINES = "____________________________________________________________\n";
    private static final String INVALID_INPUT = "My guy that's not a valid input!\n";

    static void printDottedLines() {
        System.out.print(DOTTED_LINES);
    }

    static void printInvalidInput() {
        System.out.print(INVALID_INPUT);
    }

    static int getArrayIndex(ArrayList<Task> list, String line) {
        String[] parts = line.split(" ");
        return Integer.parseInt(parts[1]) - 1;
    }

    static boolean checkIndexValidity(ArrayList<Task> list, int index) {
        boolean isValid = index <= list.size();
        if (!isValid) {
            printInvalidInput();
            printDottedLines();
        }
        return isValid;
    }

    static void markTaskDone(ArrayList<Task> list, String line) {
        int arrayIndex = getArrayIndex(list, line);
        if (!checkIndexValidity(list, arrayIndex)) {
            return;
        }
        list.get(arrayIndex).setDone();
        printDottedLines();
        System.out.println("\t Good job! I've marked this task done:");
        System.out.println("\t\t"
                + list.get(arrayIndex).getStatusIcon()
                + " "
                + list.get(arrayIndex).description);
        printDottedLines();
    }

    static void markTaskNotDone(ArrayList<Task> list, String line) {
        int arrayIndex = getArrayIndex(list, line);
        if (!checkIndexValidity(list, arrayIndex)) {
            return;
        }
        list.get(arrayIndex).setNotDone();
        printDottedLines();
        System.out.println("\t Alright boss! I've marked this task as not done yet:");
        System.out.println("\t\t"
                + list.get(arrayIndex).getStatusIcon()
                + " "
                + list.get(arrayIndex).description);
        printDottedLines();
    }

    static void displayList(ArrayList<Task> list) {
        int taskCounter = 1;
        System.out.println("\tHere are the tasks in your list:");
        for (Task item : list) {
            System.out.println("\t"
                    + taskCounter
                    + "."
                    + item.getStatusIcon()
                    + " " + item.description);
            taskCounter++;
        }
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

        String line = "";
        Scanner in = new Scanner(System.in);
        ArrayList<Task> listOfTasks = new ArrayList<>();
        int numberOfTasks = 0;

        printDottedLines();
        System.out.println("Hello! I'm Tank");
        System.out.println("What shall we talk about today?");
        printDottedLines();

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

            default:
                printDottedLines();
                System.out.println("\t" + "added: " + line);
                printDottedLines();
                listOfTasks.add(new Task(line));
                numberOfTasks++;
            }

        } while (true);

        printDottedLines();
        System.out.println("Bye. Hope to see you again soon!");
        printDottedLines();
    }
}
