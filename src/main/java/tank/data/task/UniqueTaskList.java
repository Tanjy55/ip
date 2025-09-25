package tank.data.task;

import java.util.ArrayList;

/**
 * A list of tasks, implemented using ArrayList
 */
public class UniqueTaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public UniqueTaskList() {
    }

    public void add(Task toAdd) {
        tasks.add(toAdd);
    }

    public void remove(int toRemove) {
        tasks.remove(toRemove);
    }

    /**
     * Returns an arrayList of Tasks
     *
     * @return arrayList
     */
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
