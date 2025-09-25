package tank.ui;

import static tank.common.Messages.INVALID_INPUT;
import static tank.common.Messages.DOTTED_LINES;
import static tank.common.Messages.HELP_SHEET;

import tank.commands.CommandResult;
import tank.data.task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Text UI of the application
 */
public class TextUi {

    private final Scanner in;
    private final PrintStream out;

    public TextUi() {
        this(System.in, System.out);
    }

    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String getUserCommand() {
        return in.nextLine();
    }

    private void printDottedLines() {
        System.out.print(DOTTED_LINES);
    }


    /**
     * Calls toString method for given Task in Arraylist
     *
     * @param list  ArrayList data reference
     * @param index index of item in list
     */
    public void printTask(ArrayList<Task> list, int index) {
        out.println("\t" + list.get(index));
    }

    /**
     * Prints number of tasks in ArrayList
     *
     * @param list ArrayList data reference
     */
    public void printNumberOfTasks(ArrayList<Task> list) {
        out.println(" Now you have "
                + list.size()
                + " tasks in the list.");
    }

    /**
     * Prints all Task sequentially in Arraylist
     *
     * @param list ArrayList data reference
     */
    public void displayList(ArrayList<Task> list) {
        int taskCounter = 1;
        for (Task item : list) {
            out.println("\t" + taskCounter + "." + item);
            taskCounter++;
        }
    }

    public void printInvalidInput() {
        out.print(INVALID_INPUT);
    }

    public void printNewLine() {
        out.println();
    }

    public void printHelp() {
        out.print(INVALID_INPUT + HELP_SHEET);
    }

    /**
     * Prints message stored in CommandResult
     *
     * @param commandResult object reference
     */
    public void printCommandResult(CommandResult commandResult) {
        printDottedLines();
        out.println("\t" + commandResult.feedbackToUser);
        printDottedLines();
    }

    public void printMessage(String message) {
        out.println(message);
    }

    public void printWelcomeMessage() {
        printDottedLines();
        out.println("Hello! I'm Tank!");
        out.println("I'm here to help track your tasks,"
                + "what shall we talk about today?");
        printDottedLines();
    }

    public void printExitMessage() {
        printDottedLines();
        out.println("Bye. Hope to see you again soon!");
        printDottedLines();
    }
}
