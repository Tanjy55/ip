import java.util.Scanner;

public class Tank {
    public static void main(String[] args) {
        String line = "";
        Scanner in = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Tank");
        System.out.println("What can I do for you?\n");
        System.out.println("____________________________________________________________\n");
        do {
            line = in.nextLine();
            if (!line.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("\t" + line);
                System.out.println("____________________________________________________________");
            }
        } while (!line.equals("bye"));
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

    }
}
