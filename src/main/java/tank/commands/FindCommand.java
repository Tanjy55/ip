package tank.commands;

import tank.data.TaskList;
import tank.data.task.Task;
import tank.ui.TextUi;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyWord;
    private TextUi ui;

    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
        this.ui = new TextUi();
    }

    @Override
    public CommandResult execute(TaskList taskList) {
        ArrayList<Task> foundTasks = new ArrayList<>();

        for (Task t : taskList.getAllTasks()) {
            if (t.getDescription().contains(keyWord)) {
                foundTasks.add(t);
            }
        }

        if (foundTasks.size() == 0) {
            ui.printMessage("No matching keywords found in any tasks!");
            return new CommandResult("Search complete");
        }

        ui.printMessage("Here are the matching tasks in your list:");
        ui.displayList(foundTasks);
        return new CommandResult("Search complete");
    }
}
