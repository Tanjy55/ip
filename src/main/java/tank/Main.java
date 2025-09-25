package tank;

import tank.commands.Command;
import tank.commands.CommandResult;
import tank.commands.ExitCommand;
import tank.parser.Parser;
import tank.storage.TankStoreFile;
import tank.ui.TextUi;
import tank.data.TaskList;

/**
 * Entry point of Tank CLI chatbot program
 */

public class Main {

    /**
     * Object instances for UI, data, file storage
     */
    private TextUi ui;
    private TankStoreFile storage;
    private TaskList tasklist;

    public static void main(String... launchArgs) {
        new Main().run(launchArgs);
    }

    /**
     * Run the program until Exit Command
     */
    public void run(String[] launchArgs) {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Sets up the required objects
     * Load up the data from the storage file
     * Print the welcome message
     */
    private void start() {
        try {
            this.ui = new TextUi();
            this.tasklist = new TaskList();
            this.storage = new TankStoreFile();
            storage.load(tasklist.getAllTasks());
            ui.printWelcomeMessage();
        } catch (Exception e) {
            ui.printInvalidInput();
        }
    }

    /**
     * Print exit message and terminate program
     */
    private void exit() {
        ui.printExitMessage();
        System.exit(0);
    }

    /**
     * Read user command, parse and call for execute command
     * Prompt user if invalid command is given
     */
    private void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            String userCommandText = ui.getUserCommand();
            command = new Parser().parseCommand(userCommandText);
            ui.printNewLine();
            CommandResult result = executeCommand(command);
            ui.printCommandResult(result);
            storage.save(tasklist.getAllTasks());
        } while (!ExitCommand.isExit(command));
    }

    /**
     * Executes the command and returns the result
     *
     * @param command user command
     * @return result of the command
     */
    private CommandResult executeCommand(Command command) {
        try {
            CommandResult result = command.execute(tasklist);
            return result;
        } catch (Exception e) {
            return new CommandResult("Error " + e.getMessage());
        }
    }
}
