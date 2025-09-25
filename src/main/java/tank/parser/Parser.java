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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses all user input and returns new command
 */
public class Parser {

    /**
     * Main method for parsing user input
     *
     * @param line user input string
     * @return new Command instance for main to call execute
     */
    public Command parseCommand(String line) {

        String[] parts = line.split("\\s+", 2);
        String command = parts[0].toLowerCase();
        String arguments;

        if (command.equals("list")) {
            return new ListCommand();
        } else if (command.equals("bye")) {
            return new ExitCommand();
        }

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

    /**
     * Prepares for Todo addition to ArrayList
     * Unless exception is caught due to invalid input
     *
     * @param arguments contains description for Todo object
     * @return new addCommand with reference to new Todo object
     */
    private Command prepareTodo(String arguments) {
        try {
            checkIfStringEmpty(arguments);
        } catch (TankArgumentMissingException e) {
            return new ErrorCommand(e.getMessage());
        }

        Task toAdd = new Todo(arguments);
        return new AddCommand(toAdd);
    }

    /**
     * Prepares for Deadline addition to ArrayList
     * Unless exception is caught due to invalid input
     * Parses by into a LocalDateTime object
     *
     * @param arguments contains description and by for Deadline object
     * @return new addCommand with reference to new Deadline object
     */
    private Command prepareDeadline(String arguments) {
        try {
            checkValidDeadline(arguments);
        } catch (TankCommandInvalidException e) {
            return new ErrorCommand(e.getMessage() + "/by");
        } catch (TankArgumentMissingException e) {
            return new ErrorCommand(e.getMessage());
        }

        String[] deadlineInput = processDeadlineInput(arguments);
        String description = deadlineInput[0];
        String by = deadlineInput[1];
        LocalDateTime localDateTime;

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("d/M/uuuu HHmm");
        try {
            localDateTime = LocalDateTime.parse(by.trim(), fmt);
        } catch (DateTimeParseException e) {
            return new ErrorCommand(
                    "Please give date and time the format: DD/MM/YYYY HHMM");
        }

        Task toAdd = new Deadline(description, localDateTime);
        return new AddCommand(toAdd);
    }

    /**
     * Prepares for Event addition to ArrayList
     * Unless exception is caught due to invalid input
     * Parses from and to into a LocalDateTime object
     *
     * @param arguments contains description, from and to for Event object
     * @return new addCommand with reference to new Event object
     */
    private Command prepareEvent(String arguments) {
        try {
            checkValidEvent(arguments);
        } catch (TankCommandInvalidException e) {
            return new ErrorCommand(e.getMessage() + "/from or /to");
        } catch (TankArgumentMissingException e) {
            return new ErrorCommand(e.getMessage());
        }

        String[] eventInput = processEventInput(arguments);
        String description = eventInput[0];
        String from = eventInput[1];
        String to = eventInput[2];
        LocalDateTime localDateTimeFrom;
        LocalDateTime localDateTimeTo;

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("d/M/uuuu HHmm");
        try {
            localDateTimeFrom = LocalDateTime.parse(from.trim(), fmt);
            localDateTimeTo = LocalDateTime.parse(to.trim(), fmt);
        } catch (DateTimeParseException e) {
            return new ErrorCommand(
                    "Please give date and time the format: DD/MM/YYYY HHMM");
        }

        Task toAdd = new Event(description, localDateTimeFrom, localDateTimeTo);
        return new AddCommand(toAdd);
    }

    /**
     * Prepare Task for deletion
     *
     * @param arguments number in list to be deleted given by user
     * @return command to delete with the parsed ArrayList index
     */
    private Command prepareDelete(String arguments) {
        try {
            int arrayIndex = Integer.parseInt(arguments) - 1;
            return new DeleteCommand(arrayIndex);
        } catch (NumberFormatException e) {
            return new ErrorCommand("Input given should be a number!");
        }
    }

    /**
     * Prepare Task to be marked as done
     *
     * @param arguments number in list to be marked given by user
     * @return command to mark Task as done
     */
    private Command prepareMark(String arguments) {
        try {
            int arrayIndex = Integer.parseInt(arguments) - 1;
            return new MarkCommand(arrayIndex);
        } catch (NumberFormatException e) {
            return new ErrorCommand("Input given should be a number!");
        }
    }

    /**
     * Prepare Task to be marked as not done
     *
     * @param arguments number in list to be unmarked given by user
     * @return command to mark Task as not done
     */
    private Command prepareUnmark(String arguments) {
        try {
            int arrayIndex = Integer.parseInt(arguments) - 1;
            return new UnmarkCommand(arrayIndex);
        } catch (NumberFormatException e) {
            return new ErrorCommand("Input given should be a number!");
        }
    }

    /**
     * Prepare to search in ArrayList for keyword
     *
     * @param arguments keyword to search for given by user
     * @return command to search ArrayList for Tasks that contains keyword
     */
    private Command prepareFind(String arguments) {
        try {
            String keyWord = arguments.trim();
            return new FindCommand(keyWord);
        } catch (Exception e) {
            return new ErrorCommand("Unable to find keyword!");
        }
    }


    static void checkArgumentsExist(String[] parts)
            throws TankCommandInvalidException {
        if (parts.length == 1) {
            throw new TankCommandInvalidException(
                    "Incomplete command arguments, please see the help sheet!");
        }
    }


    static void checkIfStringEmpty(String string)
            throws TankArgumentMissingException {

        if (string.isEmpty()) {
            throw new TankArgumentMissingException(
                    "Invalid input, some information is missing!");
        }
    }


    static void checkIfStringContains(String string, String string2)
            throws TankCommandInvalidException {

        if (!string.contains(string2)) {
            throw new TankCommandInvalidException(
                    "Hmmm something went wrong, check if you forgot to include: ");
        }
    }

    /**
     * Parses user input for Deadline addition
     *
     * @param arguments user input without command word
     * @return array of string containing description and by
     */
    static String[] processDeadlineInput(String arguments) {
        String[] message = arguments.split("/by", 2);
        message[0] = message[0].trim();
        message[1] = message[1].trim();
        return message;
    }

    /**
     * Parses user input for Event addition
     *
     * @param arguments user input without command word
     * @return array of string containing description, from and to
     */
    static String[] processEventInput(String arguments) {
        String[] returnArray = new String[3];
        String[] message = arguments.split("/from", 2);
        returnArray[0] = message[0].trim();

        String[] stringToSplit = message[1].split("/to", 2);
        returnArray[1] = stringToSplit[0].trim();
        returnArray[2] = stringToSplit[1].trim();
        return returnArray;
    }

    /**
     * Checks if all Deadline attributes are valid and contains the correct command word
     * Throw exceptions if invalid or missing field detected
     *
     * @param arguments user input without command word
     * @throws TankCommandInvalidException  if deadline fields do not contain required keyword
     * @throws TankArgumentMissingException if deadline fields are missing argument information
     */
    static void checkValidDeadline(String arguments)
            throws TankCommandInvalidException, TankArgumentMissingException {

        checkIfStringEmpty(arguments);
        checkIfStringContains(arguments, "/by");

        String[] message = processDeadlineInput(arguments);
        String description = message[0];
        String byDate = message[1];

        checkIfStringEmpty(description);
        checkIfStringEmpty(byDate);
    }

    /**
     * Checks if all Event attributes are valid and contains the correct command words
     * Throw exceptions if invalid or missing field detected
     *
     * @param arguments user input without command word
     * @throws TankCommandInvalidException  if event fields missing required keyword
     * @throws TankArgumentMissingException if event fields missing argument information
     */
    static void checkValidEvent(String arguments)
            throws TankCommandInvalidException, TankArgumentMissingException {

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
