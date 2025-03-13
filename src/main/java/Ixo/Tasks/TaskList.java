package Ixo.Tasks;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks = new ArrayList<Task>();

    public TaskList(ArrayList<Task> tasks){
        if (tasks == null){
            throw new NullPointerException();
        }
        tasks.addFirst(null); //dummy task addition
        this.tasks = tasks;

    }

    public TaskList(){
        tasks.add(null); // dummy task to pad index
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
