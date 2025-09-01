import java.util.Scanner;
import java.util.ArrayList;


public class Tank {
    static void displayList(ArrayList<String> list) {
        for (String text : list) {
            System.out.println(text);
        }
    }

    public static void main(String[] args) {

        String line = "";
        Scanner in = new Scanner(System.in);
        ArrayList<String> listOfTexts = new ArrayList<>();
        int numberInList = 1;

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Tank");
        System.out.println("What shall we talk about today?\n");
        System.out.println("____________________________________________________________\n");
        do {
            line = in.nextLine();
            if (line.equals(("list"))) {
                displayList(listOfTexts);
                continue;
            }

            if (!line.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("\t" + "added: " + line);
                System.out.println("____________________________________________________________");
                listOfTexts.add(Integer.toString(numberInList) + ". " + line);
                numberInList++;
            }
        } while (!line.equals("bye"));
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
