package tank.data;

import tank.data.task.Task;
import tank.data.task.UniqueTaskList;

import java.util.ArrayList;

/**
 * Contains getters and setters for all tasks
 */
public class TaskList {
    private final UniqueTaskList allTasks;

    public TaskList() {
        allTasks = new UniqueTaskList();
    }

    public void addTask(Task toAdd) {
        allTasks.add(toAdd);
    }

    public void removeTask(int toRemove) {
        allTasks.remove(toRemove);
    }

    /**
     * Fetch the arrayList
     *
     * @return the ArrayList data
     */
    public ArrayList<Task> getAllTasks() {
        return allTasks.getTasks();
    }

    public void setTaskDone(int toSet) {
        allTasks.setDone(toSet);
    }

    public void setTaskNotDone(int toSet) {
        allTasks.setNotDone(toSet);
    }

    /**
     * Fetch the array index of the Task added most recently to allTasks
     *
     * @return is an integer representing the index
     */
    public int getLatestIndex() {
        return allTasks.getTasks().size() - 1;
    }
}
