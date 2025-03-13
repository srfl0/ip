package Ixo.Ui;

import Ixo.Parser.Ui;
import Ixo.Storage.Storage;
import Ixo.Tasks.TaskList;

public class Ixo implements FlatString{

    Storage storage;
    TaskList taskList;
    Ui ui;

    public Ixo(String filePath){
        storage = new Storage(filePath);

        try{
            taskList = new TaskList(storage.load());
        }
        catch (NullPointerException e){
            taskList = new TaskList();
        }

        ui = new Ui(taskList.getTasks(),filePath);
    }

    public void run(){
        ui.begin();
    }

    public static void main(String[] args) {
        new Ixo("data/tasks.txt").run();
    }

}