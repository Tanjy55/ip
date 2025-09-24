package tank.parser;

import tank.commands.AddCommand;
import tank.commands.Command;
import tank.commands.DeleteCommand;
import tank.commands.ErrorCommand;
import tank.commands.ExitCommand;
import tank.commands.FindCommand;
import tank.commands.HelpCommand;
import tank.commands.ListCommand;
import tank.commands.MarkCommand;
import tank.commands.UnmarkCommand;
import tank.data.exception.TankArgumentMissingException;
import tank.data.exception.TankCommandInvalidException;
import tank.data.task.Deadline;
import tank.data.task.Event;
import tank.data.task.Task;
import tank.data.task.Todo;


public class Parser {
    public Command parseCommand(String line) {

        //split the string into command and argument
        String[] parts = line.split("\\s+", 2);
        String command = parts[0].toLowerCase();
        String arguments;

        //special case for no argument command "list"
        if (command.equals("list")) {
            return new ListCommand();
        } else if (command.equals("bye")) {
            return new ExitCommand();
        }

        //check if command is missing argument
        try {
            checkArgumentsExist(parts);
        } catch (TankCommandInvalidException e) {
            return new HelpCommand();
        }

        arguments = parts[1].trim();


        switch (command) {

        case "todo":
            return prepareTodo(arguments);

        case "deadline":
            return prepareDeadline(arguments);

        case "event":
            return prepareEvent(arguments);

        case "delete":
            return prepareDelete(arguments);

        case "mark":
            return prepareMark(arguments);

        case "unmark":
            return prepareUnmark(arguments);

        case "find":
            return prepareFind(arguments);

        default:
            return new HelpCommand();
        }
    }


    private Command prepareTodo(String arguments) {
        try {
            checkIfStringEmpty(arguments);
        } catch (TankArgumentMissingException e) {
            return new ErrorCommand(e.getMessage());
        }

        Task toAdd = new Todo(arguments);
        return new AddCommand(toAdd);
    }


    private Command prepareDeadline(String arguments) {
        try {
            checkValidDeadline(arguments);
        } catch (TankCommandInvalidException e) {
            return new ErrorCommand(e.getMessage() + "/by");
        } catch (TankArgumentMissingException e) {
            return new ErrorCommand(e.getMessage());
        }

        String[] deadlineInput = processDeadlineInput(arguments);
        Task toAdd = new Deadline(deadlineInput[0], deadlineInput[1]);
        return new AddCommand(toAdd);
    }


    private Command prepareEvent(String arguments) {
        try {
            checkValidEvent(arguments);
        } catch (TankCommandInvalidException e) {
            return new ErrorCommand(e.getMessage() + "/from or /to");
        } catch (TankArgumentMissingException e) {
            return new ErrorCommand(e.getMessage());
        }

        String[] eventInput = processEventInput(arguments);
        Task toAdd = new Event(eventInput[0], eventInput[1], eventInput[2]);
        return new AddCommand(toAdd);
    }


    private Command prepareDelete(String arguments) {
        try {
            int arrayIndex = Integer.parseInt(arguments) - 1;
            return new DeleteCommand(arrayIndex);
        } catch (NumberFormatException e) {
            return new ErrorCommand("Input given should be a number!");
        }
    }


    private Command prepareMark(String arguments) {
        try {
            int arrayIndex = Integer.parseInt(arguments) - 1;
            return new MarkCommand(arrayIndex);
        } catch (NumberFormatException e) {
            return new ErrorCommand("Input given should be a number!");
        }
    }


    private Command prepareUnmark(String arguments) {
        try {
            int arrayIndex = Integer.parseInt(arguments) - 1;
            return new UnmarkCommand(arrayIndex);
        } catch (NumberFormatException e) {
            return new ErrorCommand("Input given should be a number!");
        }
    }

    private Command prepareFind(String arguments) {
        try {
            String keyWord = arguments.trim();
            return new FindCommand(keyWord);
        } catch (Exception e) {
            return new ErrorCommand("Unable to find keyword!");
        }
    }


    static void checkArgumentsExist(String[] parts) throws TankCommandInvalidException {
        if (parts.length == 1) {
            throw new TankCommandInvalidException("Incomplete command arguments, please see the help sheet!");
        }
    }


    static void checkIfStringEmpty(String string) throws TankArgumentMissingException {
        if (string.isEmpty()) {
            throw new TankArgumentMissingException("Invalid input, some information is missing!");
        }
    }


    static void checkIfStringContains(String string, String string2) throws TankCommandInvalidException {
        if (!string.contains(string2)) {
            throw new TankCommandInvalidException("Hmmm something went wrong, check if you forgot to include: ");
        }
    }


    static String[] processDeadlineInput(String arguments) {
        String[] message = arguments.split("/by", 2);
        message[0] = message[0].trim();
        message[1] = message[1].trim();
        return message;
    }


    static String[] processEventInput(String arguments) {
        String[] returnArray = new String[3];
        String[] message = arguments.split("/from", 2);
        returnArray[0] = message[0].trim();

        String[] stringToSplit = message[1].split("/to", 2);
        returnArray[1] = stringToSplit[0].trim();
        returnArray[2] = stringToSplit[1].trim();
        return returnArray;
    }


    static void checkValidDeadline(String arguments) throws TankCommandInvalidException, TankArgumentMissingException {

        checkIfStringEmpty(arguments);
        checkIfStringContains(arguments, "/by");

        String[] message = processDeadlineInput(arguments);
        String description = message[0];
        String byDate = message[1];

        checkIfStringEmpty(description);
        checkIfStringEmpty(byDate);
    }


    static void checkValidEvent(String arguments) throws TankCommandInvalidException, TankArgumentMissingException {

        checkIfStringEmpty(arguments);
        checkIfStringContains(arguments, "/from");
        checkIfStringContains(arguments, "/to");

        String[] message = processEventInput(arguments);
        String description = message[0];
        String from = message[1];
        String to = message[2];

        checkIfStringEmpty(description);
        checkIfStringEmpty(from);
        checkIfStringEmpty(to);
    }
}
