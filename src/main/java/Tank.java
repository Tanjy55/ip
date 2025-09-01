import java.util.Scanner;
import java.util.ArrayList;


public class Tank {
    static void displayList(ArrayList<Task> list) {
        int counter = 1;
        System.out.println("\tHere are the tasks in your list:");
        for (Task item : list) {
            System.out.println("\t" + counter + "." + item.getStatusIcon() + " " + item.description);
            counter++;
        }
        System.out.println("____________________________________________________________");
    }

    static void markTaskDone(ArrayList<Task> list, String line) {
        String[] parts = line.split(" ");
        int ArrayIndex = Integer.parseInt(parts[1]) - 1;
        list.get(ArrayIndex).setDone();
        System.out.println("____________________________________________________________");
        System.out.println("\t Good job! I've marked this task done:");
        System.out.println("\t\t" + list.get(ArrayIndex).getStatusIcon() + " " + list.get(ArrayIndex).description);
        System.out.println("____________________________________________________________");
    }

    static void markTaskNotDone(ArrayList<Task> list, String line) {
        String[] parts = line.split(" ");
        int ArrayIndex = Integer.parseInt(parts[1]) - 1;
        list.get(ArrayIndex).setNotDone();
        System.out.println("____________________________________________________________");
        System.out.println("\t Alright boss! I've marked this task as not done yet:");
        System.out.println("\t\t" + list.get(ArrayIndex).getStatusIcon() + " " + list.get(ArrayIndex).description);
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {

        String line = "";
        Scanner in = new Scanner(System.in);
        ArrayList<Task> listOfTasks = new ArrayList<>();
        int numberOfTasks = 0;

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Tank");
        System.out.println("What shall we talk about today?\n");
        System.out.println("____________________________________________________________\n");

        do {
            line = in.nextLine();
            if (line.equals(("list"))) {
                displayList(listOfTasks);
                continue;
            }

            if (line.contains(("unmark"))) {
                markTaskNotDone(listOfTasks, line);
                continue;
            }

            if (line.contains(("mark"))) {
                markTaskDone(listOfTasks, line);
                continue;
            }

            if (!line.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("\t" + "added: " + line);
                System.out.println("____________________________________________________________");
                listOfTasks.add(new Task(line));
                numberOfTasks++;
            }
        } while (!line.equals("bye"));
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
