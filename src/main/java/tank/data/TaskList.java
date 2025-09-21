package tank.data;

import tank.data.task.Task;
import tank.data.task.UniqueTaskList;

import java.util.ArrayList;

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

    public ArrayList<Task> getAllTasks() {
        return allTasks.getTasks();
    }

    public void setTaskDone(int toSet) {
        allTasks.setDone(toSet);
    }

    public void setTaskNotDone(int toSet) {
        allTasks.setNotDone(toSet);
    }
}
