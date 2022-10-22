package com.example.agenda_pessoal.Model.Sort;

import java.util.ArrayList;

public class TaskTree {
    private TaskNode root;

    public TaskTree(TaskNode root) {
        root = null;
    }

    public void add(int idTask, String[] dateTime){
        if (root == null){
            root = new TaskNode(idTask, dateTime);
        }else{
            root.add(idTask,dateTime);
        }
    }

    public ArrayList<Integer> sort(){
        ArrayList<Integer> sortedList = new ArrayList<>();
        if (root != null){
            sortedList.addAll(root.sort());
        }
        return sortedList;
    }
}
