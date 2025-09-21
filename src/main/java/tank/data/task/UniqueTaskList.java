package tank.data.task;

import java.util.ArrayList;

public class UniqueTaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    //construct empty task list
    public UniqueTaskList() {}

    //add
    public void add(Task toAdd) {
        tasks.add(toAdd);
    }

    //remove
    public void remove(int toRemove) {
        tasks.remove(toRemove);
    }

    //return arraylist of all tasks
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setDone(int toSet) {
        tasks.get(toSet).setDone();
    }

    public void setNotDone(int toSet) {
        tasks.get(toSet).setNotDone();
    }
}
