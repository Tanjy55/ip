package tank;

import tank.commands.Command;
import tank.commands.CommandResult;
import tank.commands.ExitCommand;
import tank.parser.Parser;
import tank.storage.TankStoreFile;
import tank.ui.TextUi;
import tank.data.TaskList;

public class Main {
    private TextUi ui;
    private TankStoreFile storage;
    private TaskList tasklist;

    public static void main(String... launchArgs) {
        new Main().run(launchArgs);
    }

    public void run(String[] launchArgs) {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }

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

    private void exit() {
        ui.printExitMessage();
        System.exit(0);
    }

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

    private CommandResult executeCommand(Command command) {
        try {
            CommandResult result = command.execute(tasklist);
            return result;
        } catch (Exception e) {
            return new CommandResult("Error " + e.getMessage());
        }
    }
}
