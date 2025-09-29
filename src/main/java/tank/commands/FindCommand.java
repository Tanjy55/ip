package tank.commands;

import tank.data.TaskList;
import tank.data.task.Task;
import tank.ui.TextUi;

import java.util.ArrayList;

/**
 * Implemented find tasks whose description contains argument keywords
 */
public class FindCommand extends Command {
    private String keyWord;
    private TextUi ui;

    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
        this.ui = new TextUi();
    }

    /**
     * Iterates through TaskList
     * Print all Tasks which description contains keywords
     * Keyword matching is not case-sensitive
     *
     * @param taskList TaskList to search from
     * @return CommandResult with message of search result
     */
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
